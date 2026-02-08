package com.myapp;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MyClassTest {

    @Test
    public void testGetInstance() {
        // Setup
        // Run the test
        final MyClass result = MyClass.getInstance();

        // Verify the results
    }

    @Test
    public void testCreateNewConnection() {
        final MyClass myClassUnderTest = MyClass.getInstance();
        assertThat(myClassUnderTest.createNewConnection()).isNull();
    }
}
