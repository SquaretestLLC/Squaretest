package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private FooBar mockFoobar;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFoobar, new HashMap<>(), new OtherFooBar[]{});
    }

    @Test
    void testAddFoo() {
        // Setup
        // Run the test
        myClassUnderTest.addFoo(null);

        // Verify the results
    }

    @Test
    void testRetrieveFooBar() {
        // Setup
        // Run the test
        final DifferentFooBar result = myClassUnderTest.retrieveFooBar();

        // Verify the results
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
