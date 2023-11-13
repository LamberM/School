package org.lamberm.school.error.handler;

public class PeselNotExistException extends RuntimeException {
    public PeselNotExistException(){
        super("PESEL doesn't exist");
    }
}
