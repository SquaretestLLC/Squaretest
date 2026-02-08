package com.myapp

import com.myapp.bases.SubFooService
import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test

import static org.assertj.core.api.Assertions.assertThat

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass(new SubFooService())
    }

    @Test
    void testGetFoo() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getFoo("key")

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testGetFoo2() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getFoo2("key")

        // Verify the results
        assertThat(result).isEqualTo("result")
    }
}
