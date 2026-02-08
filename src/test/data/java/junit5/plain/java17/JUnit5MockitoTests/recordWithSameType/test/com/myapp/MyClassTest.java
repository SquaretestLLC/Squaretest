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
    void testGetFooById() {
        // Setup
        final Foo expectedResult = new Foo("id", "name", null);
        when(mockFooService.getFooById("id")).thenReturn(new Foo("id", "name", null));

        // Run the test
        final Foo result = myClassUnderTest.getFooById("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
