package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.test.util.ReflectionTestUtils

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @BeforeEach
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
