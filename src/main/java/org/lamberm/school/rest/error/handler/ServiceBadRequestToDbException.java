package org.lamberm.school.rest.error.handler;

public class ServiceBadRequestToDbException extends RuntimeException {
    public ServiceBadRequestToDbException(String message){
        super(message);
    }
}
