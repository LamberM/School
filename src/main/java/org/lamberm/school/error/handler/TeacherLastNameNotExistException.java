package org.lamberm.school.error.handler;

public class TeacherLastNameNotExistException extends RuntimeException {
    public TeacherLastNameNotExistException() {
        super("Teacher last name not exist");
    }
}
