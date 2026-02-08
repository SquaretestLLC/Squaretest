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
    void testDoSomethingWithList1() {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingWithList(["value"])

        // Verify the results
    }

    @Test
    void testDoSomethingWithMultimap1() {
        // Setup
        def theMap = ["value": ["value"]]

        // Run the test
        myClassUnderTest.doSomethingWithMultimap(theMap)

        // Verify the results
    }

    @Test
    void testStaticDoSomethingWithList1() {
        assert "result" == MyClass.staticDoSomethingWithList(["value"])
    }
}