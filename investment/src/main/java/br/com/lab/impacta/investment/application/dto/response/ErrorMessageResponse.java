package br.com.lab.impacta.investment.application.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorMessageResponse {
    private int statusCode;
    private Date timestamp;
    private String message;
    private String description;

    public ErrorMessageResponse(int statusCode, Date timestamp, String message, String description) {
        this.statusCode = statusCode;
        this.timestamp = timestamp;
        this.message = message;
        this.description = description;
    }
}