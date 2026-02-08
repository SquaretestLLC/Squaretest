package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.Socket;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass(new HashMap<>());
    }

    @Test
    void testDoSomethingWithMultimap() {
        // Setup
        final Map<String, List<Socket>> idToSocketMap = new HashMap<>();

        // Run the test
        myClassUnderTest.doSomethingWithMultimap(idToSocketMap);

        // Verify the results
    }

    @Test
    void testDoSomethingWithListOfCurrencies() {
        // Setup
        final Map<String, Currency> theMap = new HashMap<>();

        // Run the test
        final Currency result = MyClass.doSomethingWithListOfCurrencies(theMap);

        // Verify the results
    }
}
