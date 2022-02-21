package br.com.lab.impacta.investment.domain.exception;


public class InvestmentProductNotFoundException extends RuntimeException {

    private String description;

    public String getDescription() {
        return this.description;
    }

    public InvestmentProductNotFoundException(){
        super();
    }

    public InvestmentProductNotFoundException(String message, String description) {
        super(message);

        this.description = description;
    }
}
