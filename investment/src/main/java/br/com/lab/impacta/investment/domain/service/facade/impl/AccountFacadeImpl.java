package br.com.lab.impacta.investment.domain.service.facade.impl;

import br.com.lab.impacta.investment.domain.service.facade.AccountFacade;
import br.com.lab.impacta.investment.domain.service.facade.valueObject.AccountBalanceVO;
import org.springframework.stereotype.Component;

@Component
public class AccountFacadeImpl implements AccountFacade {
    @Override
    public AccountBalanceVO getAccountBalanceById(Long accountId) {
        return null;
    }
}
