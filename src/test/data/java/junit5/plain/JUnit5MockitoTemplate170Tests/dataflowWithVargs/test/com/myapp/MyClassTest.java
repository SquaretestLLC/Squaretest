package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private FooRestClient mockFooRestClient;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFooRestClient);
    }

    @Test
    void testCreateFoo1() {
        // Setup
        when(mockFooRestClient.getData1("https://example.com/{fooId}/{fooName}/{fooOtherValue}/",
                "createFooIdParam1")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.createFoo1("createFooIdParam1", "createFooNameParam1",
                "createFooOtherParam1");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testCreateFoo2() {
        // Setup
        when(mockFooRestClient.getData2("https://example.com/{fooId}", "createFooIdParam2")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.createFoo2("createFooIdParam2", "createFooNameParam2",
                "createFooOtherParam2");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testCreateFoo3() {
        // Setup
        when(mockFooRestClient.getData3(eq("https://example.com/"), any(Object.class))).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.createFoo3("createFooIdParam3", "createFooNameParam3",
                "createFooOtherParam3");

        // Verify the results
        assertEquals("result", result);
    }
}
