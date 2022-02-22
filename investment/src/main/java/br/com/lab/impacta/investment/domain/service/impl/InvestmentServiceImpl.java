package br.com.lab.impacta.investment.domain.service.impl;

import br.com.lab.impacta.investment.domain.exception.InvestmentAccountIsNotDebitException;
import br.com.lab.impacta.investment.domain.exception.InvestmentAccountWithoutBalanceException;
import br.com.lab.impacta.investment.domain.exception.InvestmentAccountWithoutBalanceForProductPrivateException;
import br.com.lab.impacta.investment.domain.exception.InvestmentProductNotFoundException;
import br.com.lab.impacta.investment.domain.model.Investment;
import br.com.lab.impacta.investment.domain.model.Product;
import br.com.lab.impacta.investment.domain.service.InvestmentService;
import br.com.lab.impacta.investment.domain.service.facade.AccountFacade;
import br.com.lab.impacta.investment.domain.service.facade.valueObject.AccountBalanceVO;
import br.com.lab.impacta.investment.infrastructure.repository.InvestmentRepository;
import br.com.lab.impacta.investment.infrastructure.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InvestmentServiceImpl implements InvestmentService {

    @Autowired
    private InvestmentRepository investmentRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AccountFacade accountFacade;

    @Value("${lab.investment.exceptions.product-dont-exists-message}")
    private String messageExceptionProductNotFound;

    @Value("${lab.investment.exceptions.product-dont-exists-description}")
    private String descriptionExceptionProductNotFound;

    @Value("${lab.investment.exceptions.account-without-balance-message}")
    private String messageExceptionAccountWithoutBalance;

    @Value("${lab.investment.exceptions.account-without-balance-description}")
    private String descriptionExceptionAccountWithoutBalance;

    @Value("${lab.investment.exceptions.account-without-balance-for-product-private-message}")
    private String messageExceptionAccountWithoutBalanceForProductPrivate;

    @Value("${lab.investment.exceptions.account-without-balance-for-product-private-description}")
    private String descriptionExceptionAccountWithoutBalanceForProductPrivate;

    @Value("${lab.investment.exceptions.account-is-not-debited-message}")
    private String messageExceptionAccountIfNotDebited;

    @Value("${lab.investment.exceptions.account-is-not-debited-description}")
    private String descriptionExceptionAccountIfNotDebited;

    @Override
    public Investment invest(Long productId, Long accountId, Double valueInvestment) {
        Optional<Product> product = productRepository.findById(productId);

        if (product.isEmpty())
            throw new InvestmentProductNotFoundException(
                    messageExceptionProductNotFound,
                    descriptionExceptionProductNotFound);

        Investment investment = new Investment(productId, accountId, valueInvestment);

        AccountBalanceVO accountBalanceVO = accountFacade.getAccountBalanceById(accountId);

        if (!investment.sufficientBalanceForInvestment(accountBalanceVO.getBalance()))
            throw new InvestmentAccountWithoutBalanceException(
                    messageExceptionAccountWithoutBalance,
                    descriptionExceptionAccountWithoutBalance);

        if (!investment.verifyProductPrivateOrDefaultForInvestment(accountBalanceVO.getBalance(),
                product.get()))
            throw new InvestmentAccountWithoutBalanceForProductPrivateException(
                    messageExceptionAccountWithoutBalanceForProductPrivate,
                    descriptionExceptionAccountWithoutBalanceForProductPrivate);

        boolean isDebited = accountFacade.debitAccount(accountId, valueInvestment);

        if (!isDebited)
            throw new InvestmentAccountIsNotDebitException(
                    messageExceptionAccountIfNotDebited,
                    descriptionExceptionAccountIfNotDebited);

        investmentRepository.save(investment);

        return investment;
    }
}
