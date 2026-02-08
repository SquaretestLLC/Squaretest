package com.myapp

import groovy.transform.CompileStatic
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @BeforeMethod
    void setUp() {
        myClassUnderTest = new MyClass()
    }

    @Test
    void testDoSomethingCool1() {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingCool("theStrings")

        // Verify the results
    }

    @Test
    void testDoSomethingCoolWithChars1() {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingCoolWithChars('a')

        // Verify the results
    }

    @Test
    void testDoSomethingCoolWithTables1() {
        myClassUnderTest.doSomethingCoolWithTables([] as int[][])
    }

    @Test
    void testDoSomethingInC1() {
        assert "result" == myClassUnderTest.doSomethingInC()
    }
}