package org.lamberm.school.error.handler;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(value = {IllegalArgumentException.class})
    protected ResponseEntity<RestErrorResponse> handleConflict(IllegalArgumentException exception) {
        return ResponseEntity.badRequest()
                .body(new RestErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage()));
    }

    @ExceptionHandler(value = {HttpClientErrorException.class})
    protected ResponseEntity<RestErrorResponse> handleRestClientException(HttpClientErrorException httpClientErrorException) {
        return ResponseEntity.status(httpClientErrorException.getStatusCode()).body(new RestErrorResponse(httpClientErrorException.getStatusCode().value(), httpClientErrorException.getMessage()));
    }

    @ExceptionHandler(value = {NumberFormatException.class})
    protected ResponseEntity<RestErrorResponse> handleNumberBadRequest() {
        return ResponseEntity.badRequest()
                .body(new RestErrorResponse(HttpStatus.BAD_REQUEST.value(), "this is not number"));
    }

    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    protected ResponseEntity<RestErrorResponse> handleWrongIntegrationWithDb() {
        return ResponseEntity.badRequest()
                .body(new RestErrorResponse(HttpStatus.BAD_REQUEST.value(), "bad request to db"));
    }

    @ExceptionHandler(value = {ServiceBadRequestToDbException.class})
    protected ResponseEntity<RestErrorResponse> handleWrongIntegrationInServiceWithDb(ServiceBadRequestToDbException serviceBadRequestToDbException) {
        return ResponseEntity.badRequest()
                .body(new RestErrorResponse(HttpStatus.BAD_REQUEST.value(), serviceBadRequestToDbException.getMessage()));
    }

    @ExceptionHandler(value = {ServiceNotFoundInDbException.class})
    protected ResponseEntity<RestErrorResponse> handleNotFoundInDb(ServiceNotFoundInDbException serviceNotFoundInDbException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(new RestErrorResponse(HttpStatus.NOT_FOUND.value(), serviceNotFoundInDbException.getMessage()));
    }

}
