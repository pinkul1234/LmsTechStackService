package com.bridgelabz.lmstechstackservice.exception;

import com.bridgelabz.lmstechstackservice.util.Response;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class TechStackNotFoundException extends RuntimeException {
    public Response getErrorResponse;
    private int statusCode;
    private String statusMessage;
    public TechStackNotFoundException(int id, String statusMessage) {
        super(statusMessage);
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }
}
