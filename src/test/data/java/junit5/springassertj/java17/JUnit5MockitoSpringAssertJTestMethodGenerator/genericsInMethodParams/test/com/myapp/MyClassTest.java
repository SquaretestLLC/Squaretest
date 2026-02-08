package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass();
    }

    @Test
    void testDoSomethingWithList1() {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingWithList(List.of("value"));

        // Verify the results
    }

    @Test
    void testDoSomethingWithMultimap1() {
        // Setup
        final Map<String, List<String>> theMap = Map.ofEntries(Map.entry("value", List.of("value")));

        // Run the test
        myClassUnderTest.doSomethingWithMultimap(theMap);

        // Verify the results
    }

    @Test
    void testStaticDoSomethingWithList1() {
        assertThat(MyClass.staticDoSomethingWithList(List.of("value"))).isEqualTo("result");
    }
}