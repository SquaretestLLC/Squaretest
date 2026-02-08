package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private FooBar mockFoobar;

    private MyClass myClassUnderTest;

    @Before
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFoobar, new HashMap<>(), new OtherFooBar[]{});
    }

    @Test
    void testAddFoo() {
        // Setup
        final DifferentFooBar differentFooBar = null;

        // Run the test
        myClassUnderTest.addFoo(differentFooBar);

        // Verify the results
    }

    @Test
    void testRetrieveFooBar() {
        // Setup
        final DifferentFooBar expectedResult = null;

        // Run the test
        final DifferentFooBar result = myClassUnderTest.retrieveFooBar();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testAddSpecialFoo() {
        // Setup
        final List<? extends OtherFooBars> otherFooBarList = Arrays.asList();

        // Run the test
        myClassUnderTest.addSpecialFoo(otherFooBarList);

        // Verify the results
    }
}
