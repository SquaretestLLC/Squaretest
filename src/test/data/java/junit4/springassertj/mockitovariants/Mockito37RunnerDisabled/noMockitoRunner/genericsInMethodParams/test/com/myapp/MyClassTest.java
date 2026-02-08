package com.myapp;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class MyClassTest {

    private MyClass myClassUnderTest;

    @Before
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
        assertThat(MyClass.staticDoSomethingWithList(Arrays.asList("value"))).isEqualTo("result");
    }
}
