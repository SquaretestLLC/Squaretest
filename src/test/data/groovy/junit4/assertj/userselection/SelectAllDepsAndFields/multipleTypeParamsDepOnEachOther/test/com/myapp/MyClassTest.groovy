package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test

import static org.assertj.core.api.Assertions.assertThat

@CompileStatic
class MyClassTest {

    private MyClass<String, String> myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass<>()
    }

    @Test
    void testLeftGetterAndSetter() {
        def left = "left"
        myClassUnderTest.setTheLeft(left)
        assertThat(myClassUnderTest.getTheLeft()).isEqualTo(left)
    }

    @Test
    void testRightGetterAndSetter() {
        def right = "right"
        myClassUnderTest.setTheRight(right)
        assertThat(myClassUnderTest.getTheRight()).isEqualTo(right)
    }

    @Test
    void testCombine() {
        // Setup
        // Run the test
        def result = myClassUnderTest.combine()

        // Verify the results
        assertThat(result).isEqualTo("result")
    }
}
