package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
public class MyClassTest {

    @Mock
    private FooBar mockFoobar;

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass(mockFoobar, Map.ofEntries(), new OtherFooBar[]{});
    }

    @Test
    public void testAddFoo1() {
        // Setup
        // Run the test
        myClassUnderTest.addFoo(null);

        // Verify the results
    }

    @Test
    public void testRetrieveFooBar1() {
        // Setup
        // Run the test
        final DifferentFooBar result = myClassUnderTest.retrieveFooBar();

        // Verify the results
    }

    @Test
    public void testAddSpecialFoo1() {
        // Setup
        final List<? extends OtherFooBars> otherFooBarList = List.of();

        // Run the test
        myClassUnderTest.addSpecialFoo(otherFooBarList);

        // Verify the results
    }
}