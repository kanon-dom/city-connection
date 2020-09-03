package com.mc.challenge.cityconnection.exception;

import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.sql.Timestamp;

public class CityConnectionErrorResponse implements Serializable {
    private HttpStatus status;
    private String message;
    private Timestamp timestamp;

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
