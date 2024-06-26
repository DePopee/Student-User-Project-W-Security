package com.example.demo.DTO.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseWrapper<T> {
    private String responseCode = "00";
    private String responseMsg = "Successful";
    private T responseDetails;

    public ResponseWrapper() {
    }

    public ResponseWrapper(String responseCode, String responseMsg) {
        this.responseCode = responseCode;
        this.responseMsg = responseMsg;
    }

    /**
     * For the default response message passing only the body
     *
     * @param body
     */
    public ResponseWrapper(T body) {
        setSuccessParams(body);
    }

    /**
     * For response with the body and custom messages
     *
     * @param body
     * @param message
     */
    public ResponseWrapper(T body, String message) {
        setSuccessParams(body, message);
    }

    public ResponseWrapper(T body, String message, String code) {
        this.responseCode = code;
        setSuccessParams(body, message);
    }

    private void setSuccessParams(T body, String message) {
        setResponseMsg(message);
        setResponseDetails(body);
    }

    private void setSuccessParams(T body) {
        setSuccessParams(body, responseMsg);
    }
}
