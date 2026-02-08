package com.myapp

import groovy.transform.CompileStatic
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @BeforeMethod
    void setUp() {
        myClassUnderTest = new MyClass([0] as int[], ["stringArray"] as String[], [] as Socket[][])
    }

    @Test
    void testCompareArray1() {
        myClassUnderTest.compareArray(["array"] as String[], [] as Socket[][])
    }

    @Test
    void testReturnTheArray1() {
        assert ["result"] as String[] == myClassUnderTest.returnTheArray()
    }

    @Test
    void testReturnTheArrays1() {
        // Setup
        // Run the test
        def result = myClassUnderTest.returnTheArrays()

        // Verify the results
    }
}