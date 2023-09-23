package com.clock.time.exception;

import lombok.Data;

@Data
public class InvalidTimeException extends RuntimeException {
    private String message;

    public InvalidTimeException(String message) {
        this.message = message;
    }
}
