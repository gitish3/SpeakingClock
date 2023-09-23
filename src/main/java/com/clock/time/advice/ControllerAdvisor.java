package com.clock.time.advice;

import com.clock.time.controllers.TimeController;
import com.clock.time.exception.InvalidTimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@Slf4j
@RestControllerAdvice(
        assignableTypes = {
                TimeController.class,
        })
public class ControllerAdvisor {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidTimeException.class)
    public ResponseEntity<?> handleInvalidTimeException(
            InvalidTimeException exception) {
        log.error("[InvalidTimeException] occurred", exception);
        return new ResponseEntity<>(
                new HashMap() {
                    {
                        put("message", exception.getMessage());
                    }
                },
                HttpStatus.BAD_REQUEST);
    }
}