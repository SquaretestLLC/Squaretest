package com.myapp


import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@CompileStatic
@RunWith(AndroidJUnit4.class)
@SmallTest
class MyClassTest {

    private MyClass myClassUnderTest

    @Before
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