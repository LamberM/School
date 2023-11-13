package org.lamberm.school.error.handler;

public class PeselExistException extends RuntimeException {
    public PeselExistException() {
        super("PESEL exist");
    }
}
