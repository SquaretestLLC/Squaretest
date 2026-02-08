package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass([0] as int[], ["stringArray"] as String[], [] as Socket[][])
    }

    @Test
    void testCompareArray1() {
        myClassUnderTest.compareArray(["array"] as String[], [] as Socket[][])
    }

    @Test
    void testReturnTheArrays1() {
        // Setup
        // Run the test
        def result = myClassUnderTest.returnTheArrays()

        // Verify the results
    }
}