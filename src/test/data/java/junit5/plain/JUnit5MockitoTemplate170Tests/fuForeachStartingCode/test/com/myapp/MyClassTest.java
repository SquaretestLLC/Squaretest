package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private FooService mockFooService;
    @Mock
    private OtherService mockOtherService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFooService, mockOtherService);
    }

    @Test
    void testValidateFooData1() {
        // Setup
        // Configure FooService.getFoos1(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", "otherId"));
        when(mockFooService.getFoos1("id")).thenReturn(fooData);

        when(mockOtherService.getOtherData1("id")).thenReturn(Optional.of("content".getBytes()));

        // Run the test
        final boolean result = myClassUnderTest.validateFooData1("id");

        // Verify the results
        assertTrue(result);
    }

    @Test
    void testValidateFooData1_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos1("id")).thenReturn(Collections.emptyList());

        // Run the test
        final boolean result = myClassUnderTest.validateFooData1("id");

        // Verify the results
        assertTrue(result);
    }

    @Test
    void testValidateFooData1_OtherServiceReturnsAbsent() {
        // Setup
        // Configure FooService.getFoos1(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", "otherId"));
        when(mockFooService.getFoos1("id")).thenReturn(fooData);

        when(mockOtherService.getOtherData1("id")).thenReturn(Optional.empty());

        // Run the test
        final boolean result = myClassUnderTest.validateFooData1("id");

        // Verify the results
        assertFalse(result);
    }

    @Test
    void testValidateFooData2() {
        // Setup
        // Configure FooService.getFoos2(...).
        final FooDataResponse fooDataResponse = new FooDataResponse(
                Arrays.asList(new FooData("id", "name", "otherId")));
        when(mockFooService.getFoos2("id")).thenReturn(fooDataResponse);

        when(mockOtherService.getOtherData1("id")).thenReturn(Optional.of("content".getBytes()));

        // Run the test
        final boolean result = myClassUnderTest.validateFooData2("id");

        // Verify the results
        assertTrue(result);
    }

    @Test
    void testValidateFooData2_OtherServiceReturnsAbsent() {
        // Setup
        // Configure FooService.getFoos2(...).
        final FooDataResponse fooDataResponse = new FooDataResponse(
                Arrays.asList(new FooData("id", "name", "otherId")));
        when(mockFooService.getFoos2("id")).thenReturn(fooDataResponse);

        when(mockOtherService.getOtherData1("id")).thenReturn(Optional.empty());

        // Run the test
        final boolean result = myClassUnderTest.validateFooData2("id");

        // Verify the results
        assertFalse(result);
    }
}
