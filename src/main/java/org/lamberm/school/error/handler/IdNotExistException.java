package org.lamberm.school.error.handler;

public class IdNotExistException extends RuntimeException {
    public IdNotExistException() {
        super("ID doesn't exist");
    }
}
