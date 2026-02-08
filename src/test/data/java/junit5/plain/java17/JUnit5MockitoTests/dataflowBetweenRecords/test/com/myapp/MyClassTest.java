package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

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
    void testGetFoo() {
        // Setup
        final FooDTO expectedResult = new FooDTO(0L, "fooResponseName", "fooResponseValue");

        // Configure FooService.getFoo(...).
        final FooResponse fooResponse = new FooResponse(0L, "fooResponseName", "fooResponseValue");
        when(mockFooService.getFoo("fooId")).thenReturn(fooResponse);

        // Run the test
        final FooDTO result = myClassUnderTest.getFoo("fooId");

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
