package com.myapp;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MyClassTest {

    @Test
    public void testGetItems11() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null);

        // Run the test
        final List<String> result = myClassUnderTest.getItems();

        // Verify the results
        assertThat(result).isEqualTo(List.of("value"));
    }

    @Test
    public void testGetItems21() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null);

        // Run the test
        final List<String> result = myClassUnderTest.getItems("criteria");

        // Verify the results
        assertThat(result).isEqualTo(List.of("value"));
    }
}