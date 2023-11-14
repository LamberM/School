package org.lamberm.school.error.handler;

public class StudentNotExistException extends RuntimeException {
    public StudentNotExistException() {
        super("Student doesn't exist");
    }
}
