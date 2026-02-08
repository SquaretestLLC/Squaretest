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
    void testGetFoo1() {
        // Setup
        final FooData expectedResult = new FooData("id", "name");
        when(mockFooService.getFoo1(10)).thenReturn(new FooData("id", "name"));

        // Run the test
        final FooData result = myClassUnderTest.getFoo1();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFoo2() {
        // Setup
        final FooData expectedResult = new FooData("id", "name");
        when(mockFooService.getFoo2(0)).thenReturn(new FooData("id", "name"));

        // Run the test
        final FooData result = myClassUnderTest.getFoo2();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFoo3() {
        // Setup
        final FooData expectedResult = new FooData("id", "name");
        when(mockFooService.getFoo3(11L)).thenReturn(new FooData("id", "name"));

        // Run the test
        final FooData result = myClassUnderTest.getFoo3();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFoo4() {
        // Setup
        final FooData expectedResult = new FooData("id", "name");
        when(mockFooService.getFoo4(0)).thenReturn(new FooData("id", "name"));

        // Run the test
        final FooData result = myClassUnderTest.getFoo4();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFoo5() {
        // Setup
        final FooData expectedResult = new FooData("id", "name");
        when(mockFooService.getFoo5(new FooData("id", "name"))).thenReturn(new FooData("id", "name"));

        // Run the test
        final FooData result = myClassUnderTest.getFoo5();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFoo6() {
        // Setup
        final FooData expectedResult = new FooData("id", "name");
        when(mockFooService.getFoo6(0)).thenReturn(new FooData("id", "name"));

        // Run the test
        final FooData result = myClassUnderTest.getFoo6(0);

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
