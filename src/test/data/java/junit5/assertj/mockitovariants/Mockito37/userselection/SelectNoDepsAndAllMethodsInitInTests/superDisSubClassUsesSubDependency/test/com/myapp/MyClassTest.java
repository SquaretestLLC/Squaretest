package com.myapp;

import com.myapp.bases.SubFooService;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MyClassTest {

    @Test
    void testGetFoo() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(new SubFooService());

        // Run the test
        final String result = myClassUnderTest.getFoo("key");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo2() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(new SubFooService());

        // Run the test
        final String result = myClassUnderTest.getFoo2("key");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }
}
