package com.myapp;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private Clock mockClockDep;
    @Mock
    private FooService mockFooService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(null, mockClockDep, null, mockFooService);
    }

    @Test
    void testGetInfo1() {
        // Setup
        final Claims claimsParam = null;

        // Run the test
        final String result = myClassUnderTest.getInfo1(claimsParam);

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetDefaultInfo() {
        // Setup
        // Run the test
        final String result = myClassUnderTest.getDefaultInfo();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetInfo2() {
        // Setup
        // Configure Clock.now(...).
        final Date date = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        when(mockClockDep.now()).thenReturn(date);

        // Run the test
        final Date result = myClassUnderTest.getInfo2();

        // Verify the results
        assertEquals(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), result);
    }

    @Test
    void testGetInfo3() {
        // Setup
        final Jwt<Header, String> input = null;

        // Run the test
        final String result = myClassUnderTest.getInfo3(input);

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetDefaultInfo2() {
        // Setup
        // Run the test
        final String result = myClassUnderTest.getDefaultInfo2();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFooData1() {
        // Setup
        final FooData expectedResult = new FooData("id", "name");
        when(mockFooService.getFoo1("id")).thenReturn(null);

        // Run the test
        final FooData result = myClassUnderTest.getFooData1("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooData2() {
        // Setup
        final List<FooData> expectedResult = Arrays.asList(new FooData("id", "name"));
        when(mockFooService.getFoo2("id")).thenReturn(null);

        // Run the test
        final List<FooData> result = myClassUnderTest.getFooData2("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooData3() {
        // Setup
        final Optional<FooData> expectedResult = Optional.of(new FooData("id", "name"));
        when(mockFooService.getFoo3("id")).thenReturn(null);

        // Run the test
        final Optional<FooData> result = myClassUnderTest.getFooData3("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooData4() {
        // Setup
        final Optional<List<FooData>> expectedResult = Optional.of(Arrays.asList(new FooData("id", "name")));
        when(mockFooService.getFoo4("id")).thenReturn(null);

        // Run the test
        final Optional<List<FooData>> result = myClassUnderTest.getFooData4("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
