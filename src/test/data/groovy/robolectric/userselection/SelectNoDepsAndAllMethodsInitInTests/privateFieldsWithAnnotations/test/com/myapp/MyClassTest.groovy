package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.springframework.test.util.ReflectionTestUtils

@CompileStatic
@RunWith(RobolectricTestRunner.class)
class MyClassTest {

    private MyClass myClassUnderTest

    @Before
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
