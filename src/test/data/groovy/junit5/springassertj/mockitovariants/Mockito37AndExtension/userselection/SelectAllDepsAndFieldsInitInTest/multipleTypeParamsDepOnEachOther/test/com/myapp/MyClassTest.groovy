package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.Test

import static org.assertj.core.api.Assertions.assertThat

@CompileStatic
class MyClassTest {

    @Test
    void testLeftGetterAndSetter() {
        def myClassUnderTest = new MyClass<>()
        def left = "left"
        myClassUnderTest.setTheLeft(left)
        assertThat(myClassUnderTest.getTheLeft()).isEqualTo(left)
    }

    @Test
    void testRightGetterAndSetter() {
        def myClassUnderTest = new MyClass<>()
        def right = "right"
        myClassUnderTest.setTheRight(right)
        assertThat(myClassUnderTest.getTheRight()).isEqualTo(right)
    }

    @Test
    void testCombine() {
        // Setup
        def myClassUnderTest = new MyClass<>()

        // Run the test
        def result = myClassUnderTest.combine()

        // Verify the results
        assertThat(result).isEqualTo("result")
    }
}
