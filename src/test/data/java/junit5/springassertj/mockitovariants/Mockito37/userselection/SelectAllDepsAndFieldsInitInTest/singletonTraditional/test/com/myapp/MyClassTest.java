package com.myapp;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MyClassTest {

    @Test
    void testGetInstance() {
        // Setup
        // Run the test
        final MyClass result = MyClass.getInstance();

        // Verify the results
    }

    @Test
    void testCreateNewConnection() {
        final MyClass myClassUnderTest = MyClass.getInstance();
        assertThat(myClassUnderTest.createNewConnection()).isNull();
    }
}
