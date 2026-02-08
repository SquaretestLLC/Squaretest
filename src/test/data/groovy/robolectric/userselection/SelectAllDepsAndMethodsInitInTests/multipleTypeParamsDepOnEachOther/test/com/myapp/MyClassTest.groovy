package com.myapp

import groovy.transform.CompileStatic
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@CompileStatic
@RunWith(RobolectricTestRunner.class)
class MyClassTest {

    @Test
    void testLeftGetterAndSetter() {
        def myClassUnderTest = new MyClass<>()
        def left = "left"
        myClassUnderTest.setTheLeft(left)
        assert left == myClassUnderTest.getTheLeft()
    }

    @Test
    void testRightGetterAndSetter() {
        def myClassUnderTest = new MyClass<>()
        def right = "right"
        myClassUnderTest.setTheRight(right)
        assert right == myClassUnderTest.getTheRight()
    }

    @Test
    void testCombine() {
        // Setup
        def myClassUnderTest = new MyClass<>()

        // Run the test
        def result = myClassUnderTest.combine()

        // Verify the results
        assert "result" == result
    }
}
