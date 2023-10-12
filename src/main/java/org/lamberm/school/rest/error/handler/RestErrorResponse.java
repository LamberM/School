package org.lamberm.school.rest.error.handler;

public record RestErrorResponse(int httpCode, String message) {
}
