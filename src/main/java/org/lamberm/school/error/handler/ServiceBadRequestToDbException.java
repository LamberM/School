package org.lamberm.school.error.handler;

public class ServiceBadRequestToDbException extends RuntimeException {
    public ServiceBadRequestToDbException(String message){
        super(message);
    }
}
