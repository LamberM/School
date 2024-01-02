package org.lamberm.school.error.handler;

public class StudentClassExistException extends RuntimeException {
    public StudentClassExistException() {
        super("Student class exist");
    }
}
