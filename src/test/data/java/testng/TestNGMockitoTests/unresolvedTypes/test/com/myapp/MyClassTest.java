package com.myapp;

import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.mockito.MockitoAnnotations.initMocks;

public class MyClassTest {

    @Mock
    private FooBar mockFoobar;

    private MyClass myClassUnderTest;

    @BeforeMethod
    public void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFoobar, new HashMap<>(), new OtherFooBar[]{});
    }

    @Test
    public void testAddFoo() {
        // Setup
        // Run the test
        myClassUnderTest.addFoo(null);

        // Verify the results
    }

    @Test
    public void testRetrieveFooBar() {
        // Setup
        // Run the test
        final DifferentFooBar result = myClassUnderTest.retrieveFooBar();

        // Verify the results
    }

    @Test
    public void testAddSpecialFoo() {
        // Setup
        final List<? extends OtherFooBars> otherFooBarList = Arrays.asList();

        // Run the test
        myClassUnderTest.addSpecialFoo(otherFooBarList);

        // Verify the results
    }
}
