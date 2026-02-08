package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass()
    }

    @Test
    void testFindBestColor() {
        // Setup
        // Run the test
        myClassUnderTest.findBestColor(MyEnum.RED)

        // Verify the results
    }
}
