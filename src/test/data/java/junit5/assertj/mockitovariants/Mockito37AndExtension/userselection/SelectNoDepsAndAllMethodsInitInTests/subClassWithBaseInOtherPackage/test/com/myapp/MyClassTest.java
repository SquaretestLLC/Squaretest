package com.myapp;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MyClassTest {

    @Test
    void testGetItems1() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null);

        // Run the test
        final List<String> result = myClassUnderTest.getItems();

        // Verify the results
        assertThat(result).isEqualTo(Arrays.asList("value"));
    }

    @Test
    void testGetItems2() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null);

        // Run the test
        final List<String> result = myClassUnderTest.getItems("criteria");

        // Verify the results
        assertThat(result).isEqualTo(Arrays.asList("value"));
    }

    @Test
    void testGetItems3() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null);

        // Run the test
        final List<String> result = myClassUnderTest.getItems("criteria", "otherData", "thirdData", "fourthData");

        // Verify the results
        assertThat(result).isEqualTo(Arrays.asList("value"));
    }
}
