package br.com.lab.impacta.investment.domain.service.facade;

import br.com.lab.impacta.investment.domain.service.facade.valueObject.AccountBalanceVO;

public interface AccountFacade {
    AccountBalanceVO getAccountBalanceById(Long accountId);
}
