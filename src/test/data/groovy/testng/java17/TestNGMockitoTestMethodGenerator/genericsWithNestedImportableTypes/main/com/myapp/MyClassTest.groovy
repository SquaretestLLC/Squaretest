package com.myapp

import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import java.net.Socket
import java.util.HashMap
import java.util.List
import java.util.Map

import static org.testng.Assert.assertEquals

class MyClassTest {

    private MyClass myClassUnderTest

    @BeforeMethod
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
