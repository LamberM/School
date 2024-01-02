package org.lamberm.school.error.handler;

public class StudentClassNotExistException extends RuntimeException {
    public StudentClassNotExistException() {
        super("Student class not exist");
    }
}
