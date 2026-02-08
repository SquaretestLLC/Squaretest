package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private FooService mockFooService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFooService);
    }

    @Test
    void testGetFooData1() {
        // Setup
        final FooData expectedResult = new FooData("id", "name", "otherId");

        // Configure FooService.getFoos1(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", "otherId"));
        when(mockFooService.getFoos1("id")).thenReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.getFooData1("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooData1_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos1("id")).thenReturn(Collections.emptyList());

        // Run the test
        assertThrows(AssertionError.class, () -> myClassUnderTest.getFooData1("id"));
    }

    @Test
    void testGetFooData2() {
        // Setup
        final FooData expectedResult = new FooData("id", "name", "otherId");

        // Configure FooService.getFoos1(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", "otherId"));
        when(mockFooService.getFoos1("id")).thenReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.getFooData2("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooData2_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos1("id")).thenReturn(Collections.emptyList());

        // Run the test
        assertThrows(AssertionError.class, () -> myClassUnderTest.getFooData2("id"));
    }

    @Test
    void testGetFooData3() {
        // Setup
        final FooData expectedResult = new FooData("id", "name", "otherId");

        // Configure FooService.getFoos1(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", "otherId"));
        when(mockFooService.getFoos1("id")).thenReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.getFooData3("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooData3_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos1("id")).thenReturn(Collections.emptyList());

        // Run the test
        assertThrows(AssertionError.class, () -> myClassUnderTest.getFooData3("id"));
    }

    @Test
    void testGetFooData4_ThrowsAssertionError() {
        // Setup
        // Configure FooService.getFoos1(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", "otherId"));
        when(mockFooService.getFoos1("id")).thenReturn(fooData);

        // Run the test
        assertThrows(AssertionError.class, () -> myClassUnderTest.getFooData4("id"));
    }

    @Test
    void testGetFooData4_FooServiceReturnsNoItems() {
        // Setup
        final FooData expectedResult = new FooData("id", "name", "otherId");
        when(mockFooService.getFoos1("id")).thenReturn(Collections.emptyList());

        // Run the test
        final FooData result = myClassUnderTest.getFooData4("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
