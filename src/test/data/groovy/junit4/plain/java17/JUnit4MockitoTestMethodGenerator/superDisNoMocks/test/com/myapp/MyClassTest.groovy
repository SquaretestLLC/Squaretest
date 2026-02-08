package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test

import java.sql.SQLException

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass(new FooService())
    }

    @Test
    void testGetData1() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getData("someValue")

        // Verify the results
        assert "result" == result
    }

    @Test(expected = IOException.class)
    void testGetData_ThrowsIOException1() {
        // Setup
        // Run the test
        myClassUnderTest.getData("someValue")
    }

    @Test
    void testGetOtherData1() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getOtherData("otherValue")

        // Verify the results
        assert "result" == result
    }

    @Test(expected = IOException.class)
    void testGetOtherData_ThrowsIOException1() {
        // Setup
        // Run the test
        myClassUnderTest.getOtherData("otherValue")
    }

    @Test
    void testGetThingFromDatabase1() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getThingFromDatabase("value")

        // Verify the results
        assert "result" == result
    }

    @Test(expected = SQLException.class)
    void testGetThingFromDatabase_ThrowsSQLException1() {
        // Setup
        // Run the test
        myClassUnderTest.getThingFromDatabase("value")
    }

    @Test
    void testDoSomething1() {
        // Setup
        // Run the test
        def result = myClassUnderTest.doSomething("purchaseId")

        // Verify the results
        assert "result" == result
    }

    @Test
    void testActivateBar1() {
        // Setup
        // Run the test
        def result = myClassUnderTest.activateBar("computeCodeForOrder")

        // Verify the results
        assert "result" == result
    }

    @Test
    void testActivateBar21() {
        // Setup
        // Run the test
        def result = myClassUnderTest.activateBar2("something")

        // Verify the results
        assert "result" == result
    }

    @Test
    void testActivateBar31() {
        // Setup
        // Run the test
        def result = myClassUnderTest.activateBar3("something")

        // Verify the results
        assert "something" == result
    }
}