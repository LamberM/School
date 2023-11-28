package org.lamberm.school.error.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(value = {IllegalArgumentException.class})
    protected ResponseEntity<RestErrorResponse> handleConflict(IllegalArgumentException e) {
        return ResponseEntity.badRequest()
                .body(new RestErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
    }

    @ExceptionHandler(value = {HttpClientErrorException.class})
    protected ResponseEntity<RestErrorResponse> handleRestClientException(HttpClientErrorException e) {
        return ResponseEntity.status(e.getStatusCode()).body(new RestErrorResponse(e.getStatusCode().value(), e.getMessage()));
    }
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    protected ResponseEntity<RestErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ResponseEntity.badRequest()
                .body(new RestErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
    }
    @ExceptionHandler(value = {IdNotExistException.class})
    protected ResponseEntity<RestErrorResponse> handleIdNotExistException(IdNotExistException e){
        return ResponseEntity.status(HttpStatusCode.valueOf(404))
                .body(new RestErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage()));
    }
    @ExceptionHandler(value = {PeselNotExistException.class})
    protected ResponseEntity<RestErrorResponse> handlePeselNotExistException(PeselNotExistException e){
        return ResponseEntity.status(HttpStatusCode.valueOf(404))
                .body(new RestErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage()));
    }
    @ExceptionHandler(value = {StudentNotExistException.class})
    protected ResponseEntity<RestErrorResponse> handlePeselNotExistException(StudentNotExistException e){
        return ResponseEntity.status(HttpStatusCode.valueOf(404))
                .body(new RestErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage()));
    }
    @ExceptionHandler(value = {PeselExistException.class})
    protected ResponseEntity<RestErrorResponse> handlePeselNotExistException(PeselExistException e){
        return ResponseEntity.badRequest()
                .body(new RestErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage()));
    }
}
