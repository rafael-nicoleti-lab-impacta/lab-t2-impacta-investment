package br.com.lab.impacta.investment.domain.service.facade.valueObject;

import lombok.Data;

@Data
public class AccountBalanceVO {
    private Long accountId;

    private Double balance;
}
