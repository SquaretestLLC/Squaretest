package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    void testGetNames1() {
        // Setup
        // Configure FooService.getAllFooData1(...).
        final Result<List<FooData>> listResult = new Result<>(Arrays.asList(new FooData("id", "name")));
        when(mockFooService.getAllFooData1("id")).thenReturn(listResult);

        // Run the test
        final List<String> result = myClassUnderTest.getNames1("id");

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
    }

    @Test
    void testGetNames1_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getAllFooData1("id")).thenReturn(new Result<>(Collections.emptyList()));

        // Run the test
        final List<String> result = myClassUnderTest.getNames1("id");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }
}
