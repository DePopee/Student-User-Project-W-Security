package com.example.demo.DTO.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomBaseResponse<T> {
    private String responseCode = "00";
    private String responseMsg = "Successful";
    private T responseDetails;

    public CustomBaseResponse() {
    }

    public CustomBaseResponse(T body) {
        setSuccessParams(body);
    }

    public CustomBaseResponse(T body, String message) {
        setSuccessParams(body, message);
    }

    public CustomBaseResponse(T body, String message, String code) {
        this.responseCode = code;
        setSuccessParams(body, message);
    }

    public CustomBaseResponse(String errorCode, String errorMessage) {
        setFailureParams(errorCode, errorMessage);
    }

    public CustomBaseResponse(String errorCode, String errorMessage, String code) {
        setFailureParams(errorCode, errorMessage);
        this.responseCode = code;
    }

    private void setSuccessParams(T body, String message) {
        setResponseMsg(message);
        setResponseDetails(body);
    }

    private void setSuccessParams(T body) {
        setSuccessParams(body, responseMsg);
    }

    private void setFailureParams(String errorCode, String errorMessage) {
        this.responseCode = errorCode;
        this.responseMsg = errorMessage;
    }
}
