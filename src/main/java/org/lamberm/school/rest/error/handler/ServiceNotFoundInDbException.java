package org.lamberm.school.rest.error.handler;

public class ServiceNotFoundInDbException extends RuntimeException{
    public ServiceNotFoundInDbException(String message){
        super(message);
    }
}
