package com.example.demo.Custom;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomBaseResponse<T> {
    private String responseCode;
    private String responseMsg;
    private T responseDetails;
    private LocalDateTime timestamp;

    public CustomBaseResponse() {
        this.timestamp = LocalDateTime.now();
    }

    public CustomBaseResponse(String responseCode, String responseMsg) {
        this();
        this.responseCode = responseCode;
        this.responseMsg = responseMsg;
    }

    public CustomBaseResponse(String responseCode, String responseMsg, T responseDetails) {
        this();
        this.responseCode = responseCode;
        this.responseMsg = responseMsg;
        this.responseDetails = responseDetails;
    }
}
