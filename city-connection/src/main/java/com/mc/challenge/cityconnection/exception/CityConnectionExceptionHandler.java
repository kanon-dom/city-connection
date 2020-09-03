package com.mc.challenge.cityconnection.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.Timestamp;

@ControllerAdvice
public class CityConnectionExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<CityConnectionErrorResponse> handle (Exception ex){
        CityConnectionErrorResponse errorResp = new CityConnectionErrorResponse();
        errorResp.setStatus(HttpStatus.SERVICE_UNAVAILABLE);
        errorResp.setMessage(ex.getMessage());
        errorResp.setTimestamp(new Timestamp(System.currentTimeMillis()));

        return new ResponseEntity<>(errorResp, HttpStatus.NOT_FOUND);
    }
}
