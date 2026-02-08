package com.myapp;

import com.myapp.bases.SubFooService;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MyClassTest {

    @Test
    public void testGetFoo() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(new SubFooService());

        // Run the test
        final String result = myClassUnderTest.getFoo("key");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testGetFoo2() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(new SubFooService());

        // Run the test
        final String result = myClassUnderTest.getFoo2("key");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }
}
