package com.myapp

import groovy.transform.CompileStatic
import org.springframework.test.util.ReflectionTestUtils
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @BeforeMethod
    void setUp() {
        myClassUnderTest = new MyClass()
        ReflectionTestUtils.setField(myClassUnderTest, "fooService", null)
        ReflectionTestUtils.setField(myClassUnderTest, "barService", null)
        ReflectionTestUtils.setField(myClassUnderTest, "defaultBarId", "defaultBarId")
    }

    @Test
    void testGetFooAndBar1() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getFooAndBar1("id")

        // Verify the results
    }
}
