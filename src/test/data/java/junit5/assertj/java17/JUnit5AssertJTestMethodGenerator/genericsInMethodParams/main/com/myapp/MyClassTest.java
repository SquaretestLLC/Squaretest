package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass();
    }

    @Test
    void testDoSomethingWithList() {
        // Setup
        final List<?> theList = Arrays.asList();

        // Run the test
        myClassUnderTest.doSomethingWithList(theList);

        // Verify the results
    }

    @Test
    void testDoSomethingWithMultimap() {
        // Setup
        final Map<String, List<String>> theMap = new HashMap<>();

        // Run the test
        myClassUnderTest.doSomethingWithMultimap(theMap);

        // Verify the results
    }

    @Test
    void testStaticDoSomethingWithList() {
        // Setup
        final List<T> theList = Arrays.asList();
        final T expectedResult = null;

        // Run the test
        final T result = MyClass.staticDoSomethingWithList(theList);

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
