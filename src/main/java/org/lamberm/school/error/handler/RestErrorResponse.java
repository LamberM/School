package org.lamberm.school.error.handler;

public record RestErrorResponse(int httpCode, String message) {
}
