package br.com.lab.impacta.investment.domain.event;

public class InvestmentDoneEvent {
    private Long investmentId;

    private Long accountId;

    private Double valueOfInvestment;

    public Long getInvestmentId() {
        return investmentId;
    }

    public void setInvestmentId(Long investmentId) {
        this.investmentId = investmentId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Double getValueOfInvestment() {
        return valueOfInvestment;
    }

    public void setValueOfInvestment(Double valueOfInvestment) {
        this.valueOfInvestment = valueOfInvestment;
    }

    public InvestmentDoneEvent(){}

    public InvestmentDoneEvent(Long investmentId, Long accountId, Double valueOfInvestment) {
        this.investmentId = investmentId;
        this.accountId = accountId;
        this.valueOfInvestment = valueOfInvestment;
    }
}
