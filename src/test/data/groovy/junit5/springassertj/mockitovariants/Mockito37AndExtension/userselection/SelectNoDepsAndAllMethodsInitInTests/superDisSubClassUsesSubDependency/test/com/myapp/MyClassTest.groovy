package com.myapp

import com.myapp.bases.SubFooService
import groovy.transform.CompileStatic
import org.junit.jupiter.api.Test

import static org.assertj.core.api.Assertions.assertThat

@CompileStatic
class MyClassTest {

    @Test
    void testGetFoo() {
        // Setup
        def myClassUnderTest = new MyClass(new SubFooService())

        // Run the test
        def result = myClassUnderTest.getFoo("key")

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testGetFoo2() {
        // Setup
        def myClassUnderTest = new MyClass(new SubFooService())

        // Run the test
        def result = myClassUnderTest.getFoo2("key")

        // Verify the results
        assertThat(result).isEqualTo("result")
    }
}
