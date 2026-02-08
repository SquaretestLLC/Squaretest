package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private Clock mockClock;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockClock);
    }

    @Test
    void testGetInstant() {
        // Setup
        // Configure Clock.instant(...).
        final Instant instant = LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC);
        when(mockClock.instant()).thenReturn(instant);

        // Run the test
        final Instant result = myClassUnderTest.getInstant();

        // Verify the results
        assertEquals(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC), result);
    }

    @Test
    void testGetDate() {
        // Setup
        // Configure Clock.instant(...).
        final Instant instant = LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC);
        when(mockClock.instant()).thenReturn(instant);

        when(mockClock.getZone()).thenReturn(ZoneId.of("UTC"));

        // Run the test
        final LocalDate result = myClassUnderTest.getDate();

        // Verify the results
        assertEquals(LocalDate.of(2020, 1, 1), result);
    }

    @Test
    void testGetDateTime() {
        // Setup
        // Configure Clock.instant(...).
        final Instant instant = LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC);
        when(mockClock.instant()).thenReturn(instant);

        when(mockClock.getZone()).thenReturn(ZoneId.of("UTC"));

        // Run the test
        final LocalDateTime result = myClassUnderTest.getDateTime();

        // Verify the results
        assertEquals(LocalDateTime.of(2020, 1, 1, 0, 0, 0), result);
    }

    @Test
    void testGetLocalTime() {
        // Setup
        // Configure Clock.instant(...).
        final Instant instant = LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC);
        when(mockClock.instant()).thenReturn(instant);

        when(mockClock.getZone()).thenReturn(ZoneId.of("UTC"));

        // Run the test
        final LocalTime result = myClassUnderTest.getLocalTime();

        // Verify the results
        assertEquals(LocalTime.of(0, 0, 0), result);
    }

    @Test
    void testGetOffsetDateTime() {
        // Setup
        // Configure Clock.instant(...).
        final Instant instant = LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC);
        when(mockClock.instant()).thenReturn(instant);

        when(mockClock.getZone()).thenReturn(ZoneId.of("UTC"));

        // Run the test
        final OffsetDateTime result = myClassUnderTest.getOffsetDateTime();

        // Verify the results
        assertEquals(OffsetDateTime.of(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0), ZoneOffset.UTC), result);
    }

    @Test
    void testGetOffsetTime() {
        // Setup
        final OffsetTime expectedResult = OffsetTime.now(ZoneId.of("UTC"));

        // Configure Clock.instant(...).
        final Instant instant = LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC);
        when(mockClock.instant()).thenReturn(instant);

        when(mockClock.getZone()).thenReturn(ZoneId.of("UTC"));

        // Run the test
        final OffsetTime result = myClassUnderTest.getOffsetTime();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetZonedDateTime() {
        // Setup
        // Configure Clock.instant(...).
        final Instant instant = LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC);
        when(mockClock.instant()).thenReturn(instant);

        when(mockClock.getZone()).thenReturn(ZoneId.of("UTC"));

        // Run the test
        final ZonedDateTime result = myClassUnderTest.getZonedDateTime();

        // Verify the results
        assertEquals(ZonedDateTime.of(LocalDateTime.of(2020, 1, 1, 0, 0, 0), ZoneId.of("UTC")), result);
    }
}
