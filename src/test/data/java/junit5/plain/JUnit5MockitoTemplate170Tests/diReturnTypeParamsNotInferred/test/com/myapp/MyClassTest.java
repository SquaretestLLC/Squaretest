package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private WeirdFactory mockWeirdFactory;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockWeirdFactory);
    }

    @Test
    void testDoSomething1() {
        // Setup
        when(mockWeirdFactory.createFrom("name")).thenReturn(Wrapper.create("value"));

        // Run the test
        final String result = myClassUnderTest.doSomething1("name");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testDoSomething2() {
        // Setup
        when(mockWeirdFactory.createFrom(Class.class)).thenReturn(Wrapper.create(null));

        // Run the test
        final String result = myClassUnderTest.doSomething2("name");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testDoSomething3() {
        // Setup
        when(mockWeirdFactory.createFrom(Class.class)).thenReturn(Wrapper.create(null));

        // Run the test
        final String result = myClassUnderTest.doSomething3("name");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testDoSomething4() {
        // Setup
        doReturn(Wrapper.create(null)).when(mockWeirdFactory).createFrom(Object.class);

        // Run the test
        final String result = myClassUnderTest.doSomething4(String.class);

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testDoSomething5() {
        // Setup
        when(mockWeirdFactory.createFrom(Class.class)).thenReturn(Wrapper.create(null));

        // Run the test
        final String result = myClassUnderTest.doSomething5();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testDoSomething6() {
        // Setup
        doReturn(Wrapper.create(null)).when(mockWeirdFactory).createFrom(any(Wrapper.class));

        // Run the test
        final String result = myClassUnderTest.doSomething6();

        // Verify the results
        assertEquals("result", result);
    }
}
