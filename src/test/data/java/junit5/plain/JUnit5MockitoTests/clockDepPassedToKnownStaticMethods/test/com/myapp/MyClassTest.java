package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass(Clock.fixed(Instant.parse("2020-01-01T00:00:00Z"), ZoneId.of("UTC")));
    }

    @Test
    void testGetInstant() {
        // Setup
        // Run the test
        final Instant result = myClassUnderTest.getInstant();

        // Verify the results
        assertEquals(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC), result);
    }

    @Test
    void testGetDate() {
        // Setup
        // Run the test
        final LocalDate result = myClassUnderTest.getDate();

        // Verify the results
        assertEquals(LocalDate.of(2020, 1, 1), result);
    }

    @Test
    void testGetDateTime() {
        // Setup
        // Run the test
        final LocalDateTime result = myClassUnderTest.getDateTime();

        // Verify the results
        assertEquals(LocalDateTime.of(2020, 1, 1, 0, 0, 0), result);
    }

    @Test
    void testGetLocalTime() {
        // Setup
        // Run the test
        final LocalTime result = myClassUnderTest.getLocalTime();

        // Verify the results
        assertEquals(LocalTime.of(0, 0, 0), result);
    }

    @Test
    void testGetOffsetDateTime() {
        // Setup
        // Run the test
        final OffsetDateTime result = myClassUnderTest.getOffsetDateTime();

        // Verify the results
        assertEquals(OffsetDateTime.of(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0), ZoneOffset.UTC), result);
    }

    @Test
    void testGetOffsetTime() {
        // Setup
        final OffsetTime expectedResult = OffsetTime.now(ZoneId.of("UTC"));

        // Run the test
        final OffsetTime result = myClassUnderTest.getOffsetTime();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetZonedDateTime() {
        // Setup
        // Run the test
        final ZonedDateTime result = myClassUnderTest.getZonedDateTime();

        // Verify the results
        assertEquals(ZonedDateTime.of(LocalDateTime.of(2020, 1, 1, 0, 0, 0), ZoneId.of("UTC")), result);
    }
}
