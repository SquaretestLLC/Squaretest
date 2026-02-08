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
    void testRec1() {
        // Setup
        final Foo foo = new Foo("id", "name");

        // Run the test
        final String result = myClassUnderTest.rec1(foo);

        // Verify the results
        assertEquals("id", result);
    }

    @Test
    void testRec2() {
        // Setup
        final Foo foo = new Foo("id", "name");

        // Run the test
        final String result = myClassUnderTest.rec2(foo);

        // Verify the results
        assertEquals("id", result);
    }

    @Test
    void testRec3() {
        // Setup
        final Foo foo = new Foo("id", "name");

        // Run the test
        final String result = myClassUnderTest.rec3(foo);

        // Verify the results
        assertEquals("id", result);
    }

    @Test
    void testRec4() {
        // Setup
        final Foo foo = new Foo("id", "name");

        // Run the test
        final String result = myClassUnderTest.rec4(foo);

        // Verify the results
        assertEquals("id", result);
    }

    @Test
    void testRec5() {
        // Setup
        final Foo foo = new Foo("id", "name");

        // Run the test
        final String result = myClassUnderTest.rec5(foo);

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testRecSimple1() {
        // Setup
        final Foo foo = new Foo("id", "name");

        // Run the test
        final String result = myClassUnderTest.recSimple1(foo);

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testRecSimple2() {
        // Setup
        final Foo foo = new Foo("id", "name");

        // Run the test
        final String result = myClassUnderTest.recSimple2(foo);

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoo() {
        // Setup
        when(mockFooService.convertId("id")).thenReturn("id");
        when(mockFooService.getFooById("id")).thenReturn(new Foo("id", "name"));

        // Run the test
        final Foo result = myClassUnderTest.getFoo("id");

        // Verify the results
    }
}
