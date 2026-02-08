package com.myapp;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
    public void testDoSomethingWithList1() {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingWithList(List.of("value"));

        // Verify the results
    }

    @Test
    public void testDoSomethingWithMultimap1() {
        // Setup
        final Map<String, List<String>> theMap = Map.ofEntries(Map.entry("value", List.of("value")));

        // Run the test
        myClassUnderTest.doSomethingWithMultimap(theMap);

        // Verify the results
    }

    @Test
    public void testStaticDoSomethingWithList1() {
        assertEquals("result", MyClass.staticDoSomethingWithList(List.of("value")));
    }
}