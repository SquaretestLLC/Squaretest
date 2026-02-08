package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test

import static org.assertj.core.api.Assertions.assertThat

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass([0] as int[], ["stringArray"] as String[], [] as Socket[][])
    }

    @Test
    void testCompareArray() {
        myClassUnderTest.compareArray(["array"] as String[], [] as Socket[][])
    }

    @Test
    void testReturnTheArray() {
        assertThat(myClassUnderTest.returnTheArray()).isEqualTo(["result"] as String[])
    }

    @Test
    void testReturnTheArrays() {
        // Setup
        // Run the test
        def result = myClassUnderTest.returnTheArrays()

        // Verify the results
    }
}
