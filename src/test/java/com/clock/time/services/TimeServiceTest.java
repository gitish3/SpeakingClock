package com.clock.time.services;

import com.clock.time.exception.InvalidTimeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class TimeServiceTest {

    private TimeService timeService;

    @BeforeEach
    public void setUp() {
        timeService = new TimeService();
    }

    @Test
    public void testSolve_ValidTime_ReturnsTimeInWords() {
        // Arrange
        String inputTime = "15:45";
        String expectedOutput = "It's three forty five ";

        // Act
        String result = timeService.convert(inputTime);

        // Assert
        assertEquals(expectedOutput, result);
    }

    @Test
    public void testSolve_InvalidHourFormat_ThrowsNumberFormatException() {
        // Arrange
        String inputTime = "invalid_time";

        // Act and Assert
        assertThrows(NumberFormatException.class, () -> timeService.convert(inputTime));
    }

    @Test
    public void testSolve_InvalidHourValue_ThrowsInvalidTimeException() {
        // Arrange
        String inputTime = "25:00";

        // Act and Assert
        assertThrows(InvalidTimeException.class, () -> timeService.convert(inputTime));
    }

    @Test
    public void testSolve_InvalidMinuteValue_ThrowsInvalidTimeException() {
        // Arrange
        String inputTime = "12:60";

        // Act and Assert
        assertThrows(InvalidTimeException.class, () -> timeService.convert(inputTime));
    }

    @Test
    public void testConvertCurrentTime_ReturnsTimeInWords() {
        //Arrange
        Date currentTime = new Date();

        // Act
        String result = timeService.convertCurrentTime(currentTime);

        // Assert
        assertNotNull(result);
        assertTrue(result.startsWith("It's"));
    }
}

