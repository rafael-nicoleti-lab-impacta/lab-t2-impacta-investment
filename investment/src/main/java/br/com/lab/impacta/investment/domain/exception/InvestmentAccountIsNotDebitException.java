package br.com.lab.impacta.investment.domain.exception;

public class InvestmentAccountIsNotDebitException extends RuntimeException {
    private String description;

    public String getDescription() {
        return this.description;
    }

    public InvestmentAccountIsNotDebitException(){
        super();
    }

    public InvestmentAccountIsNotDebitException(String message, String description) {
        super(message);

        this.description = description;
    }
}
