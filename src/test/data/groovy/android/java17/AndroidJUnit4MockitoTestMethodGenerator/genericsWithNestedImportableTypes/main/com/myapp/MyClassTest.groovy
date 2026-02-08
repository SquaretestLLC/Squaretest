package com.myapp

import org.junit.Before
import org.junit.Test

import java.net.Socket
import java.util.HashMap
import java.util.List
import java.util.Map

import static org.junit.Assert.assertEquals

class MyClassTest {

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass(new HashMap<>())
    }

    @Test
    void testDoSomethingWithMultimap() {
        // Setup
        final Map<String, List<Socket>> idToSocketMap = new HashMap<>()

        // Run the test
        myClassUnderTest.doSomethingWithMultimap(idToSocketMap)

        // Verify the results
    }

    @Test
    void testDoSomethingWithListOfCurrencies() {
        // Setup
        final Map<String, T> theMap = new HashMap<>()
        final T expectedResult = null

        // Run the test
        final T result = MyClass.doSomethingWithListOfCurrencies(theMap)

        // Verify the results
        assertEquals(expectedResult, result)
    }
}
