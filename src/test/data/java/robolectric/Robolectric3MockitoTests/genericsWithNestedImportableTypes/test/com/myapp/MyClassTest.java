package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.net.Socket;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(RobolectricTestRunner.class)
public class MyClassTest {

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass(new HashMap<>());
    }

    @Test
    public void testDoSomethingWithMultimap() {
        // Setup
        final Map<String, List<Socket>> idToSocketMap = new HashMap<>();

        // Run the test
        myClassUnderTest.doSomethingWithMultimap(idToSocketMap);

        // Verify the results
    }

    @Test
    public void testDoSomethingWithListOfCurrencies() {
        // Setup
        final Map<String, Currency> theMap = new HashMap<>();

        // Run the test
        final Currency result = MyClass.doSomethingWithListOfCurrencies(theMap);

        // Verify the results
    }
}
