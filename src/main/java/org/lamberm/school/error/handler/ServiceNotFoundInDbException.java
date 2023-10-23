package org.lamberm.school.error.handler;

public class ServiceNotFoundInDbException extends RuntimeException{
    public ServiceNotFoundInDbException(String message){
        super(message);
    }
}
