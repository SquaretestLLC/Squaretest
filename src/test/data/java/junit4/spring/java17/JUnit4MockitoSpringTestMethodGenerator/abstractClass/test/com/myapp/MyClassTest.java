package com.myapp;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class MyClassTest {

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass("someStringDep") {
            @Override
            public void doSomethingImportant() {

            }

            @Override
            protected Map<String, String> getSomethingSpecial() {
                return null;
            }
        };
    }

    @Test
    public void testDoSomething1() {
        myClassUnderTest.doSomething();
    }

    @Test
    public void testGetData1() {
        // Setup
        final Map<String, String> expectedResult = Map.ofEntries(Map.entry("value", "value"));

        // Run the test
        final Map<String, String> result = myClassUnderTest.getData();

        // Verify the results
        assertEquals(expectedResult, result);
    }
}