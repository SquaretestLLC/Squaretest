package com.myapp;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

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
    public void testDoSomething() {
        myClassUnderTest.doSomething();
    }

    @Test
    public void testGetData() {
        // Setup
        final Map<String, String> expectedResult = new HashMap<>();

        // Run the test
        final Map<String, String> result = myClassUnderTest.getData();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}
