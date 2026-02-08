package com.myapp;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeMethod
    public void setUp() {
        myClassUnderTest = new MyClass();
    }

    @Test
    public void testDoSomethingWithList() {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingWithList(Arrays.asList("value"));

        // Verify the results
    }

    @Test
    public void testDoSomethingWithMultimap() {
        // Setup
        final Map<String, List<String>> theMap = new HashMap<>();

        // Run the test
        myClassUnderTest.doSomethingWithMultimap(theMap);

        // Verify the results
    }

    @Test
    public void testStaticDoSomethingWithList() {
        assertEquals("result", MyClass.staticDoSomethingWithList(Arrays.asList("value")));
    }
}
