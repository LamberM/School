package org.lamberm.school.error.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    protected ResponseEntity<RestErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        return ResponseEntity.badRequest()
                .body(new RestErrorResponse(HttpStatus.BAD_REQUEST.value(), methodArgumentNotValidException.getMessage()));
    }

    @ExceptionHandler(value = {StudentClassExistException.class})
    protected ResponseEntity<RestErrorResponse> handleStudentClassExistException(StudentClassExistException studentClassExistException) {
        return ResponseEntity.badRequest()
                .body(new RestErrorResponse(HttpStatus.BAD_REQUEST.value(), studentClassExistException.getMessage()));
    }

    @ExceptionHandler(value = {StudentClassNotExistException.class})
    protected ResponseEntity<RestErrorResponse> handleStudentClassNotExistException(StudentClassExistException studentClassExistException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RestErrorResponse(HttpStatus.NOT_FOUND.value(), studentClassExistException.getMessage()));
    }

    @ExceptionHandler(value = {TeacherLastNameNotExistException.class})
    protected ResponseEntity<RestErrorResponse> handleTeacherLastNameNotExistException(TeacherLastNameNotExistException teacherLastNameNotExistException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RestErrorResponse(HttpStatus.NOT_FOUND.value(), teacherLastNameNotExistException.getMessage()));
    }
}
