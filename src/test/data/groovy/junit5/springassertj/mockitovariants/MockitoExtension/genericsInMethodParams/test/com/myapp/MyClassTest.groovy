package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import static org.assertj.core.api.Assertions.assertThat

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass()
    }

    @Test
    void testDoSomethingWithList() {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingWithList(["value"])

        // Verify the results
    }

    @Test
    void testDoSomethingWithMultimap() {
        // Setup
        def theMap = ["value": ["value"]]

        // Run the test
        myClassUnderTest.doSomethingWithMultimap(theMap)

        // Verify the results
    }

    @Test
    void testStaticDoSomethingWithList() {
        assertThat(MyClass.staticDoSomethingWithList(["value"])).isEqualTo("result")
    }
}
