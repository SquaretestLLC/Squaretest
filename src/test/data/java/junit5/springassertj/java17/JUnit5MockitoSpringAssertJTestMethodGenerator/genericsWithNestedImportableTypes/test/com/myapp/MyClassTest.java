package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.*;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass(Map.ofEntries(
                Map.entry(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(),
                        Map.ofEntries(Map.entry("value", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'", Locale.US))))));
    }

    @Test
    void testDoSomethingWithMultimap1() throws Exception {
        // Setup
        final Map<String, List<Socket>> idToSocketMap = Map.ofEntries(
                Map.entry("value", List.of(new Socket("host", 80))));

        // Run the test
        myClassUnderTest.doSomethingWithMultimap(idToSocketMap);

        // Verify the results
    }

    @Test
    void testDoSomethingWithListOfCurrencies1() {
        // Setup
        final Map<String, Currency> theMap = Map.ofEntries(Map.entry("value", Currency.getInstance("USD")));

        // Run the test
        final Currency result = MyClass.doSomethingWithListOfCurrencies(theMap);

        // Verify the results
    }
}