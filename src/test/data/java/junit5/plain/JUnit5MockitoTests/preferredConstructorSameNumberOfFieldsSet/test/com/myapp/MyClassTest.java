package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private DependencyProvider mockDependencyProvider;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockDependencyProvider, "url", "params");
    }

    @Test
    void testGetFoo1() {
        // Setup
        final FooData expectedResult = new FooData("id", "name");

        // Run the test
        final FooData result = myClassUnderTest.getFoo1("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
