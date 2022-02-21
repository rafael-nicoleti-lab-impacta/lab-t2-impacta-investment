package br.com.lab.impacta.investment.domain.service.impl;

import br.com.lab.impacta.investment.domain.exception.InvestmentProductNotFoundException;
import br.com.lab.impacta.investment.domain.model.Investment;
import br.com.lab.impacta.investment.domain.model.Product;
import br.com.lab.impacta.investment.domain.service.InvestmentService;
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

    @Value("${lab.investment.exceptions.product-dont-exists-message}")
    private String messageExceptionProductNotFound;

    @Value("${lab.investment.exceptions.product-dont-exists-description}")
    private String descriptionExceptionProductNotFound;

    @Override
    public Investment invest(Long productId, Long accountId, Double valueInvestment) {
        Optional<Product> product = productRepository.findById(productId);

        if (product.isEmpty())
            throw new InvestmentProductNotFoundException(
                    messageExceptionProductNotFound,
                    descriptionExceptionProductNotFound);

        Investment investment = new Investment(productId, accountId, valueInvestment);

//        investment.sufficientBalanceForInvestment(???????)

        return null;
    }
}
