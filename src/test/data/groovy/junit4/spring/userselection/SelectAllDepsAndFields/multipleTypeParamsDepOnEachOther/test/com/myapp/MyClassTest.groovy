package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test

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
        assert left == myClassUnderTest.getTheLeft()
    }

    @Test
    void testRightGetterAndSetter() {
        def right = "right"
        myClassUnderTest.setTheRight(right)
        assert right == myClassUnderTest.getTheRight()
    }

    @Test
    void testCombine() {
        // Setup
        // Run the test
        def result = myClassUnderTest.combine()

        // Verify the results
        assert "result" == result
    }
}
