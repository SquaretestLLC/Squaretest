package com.myapp

import groovy.transform.CompileStatic
import org.testng.annotations.Test

import java.sql.SQLException

import static org.testng.Assert.assertThrows

@CompileStatic
class MyClassTest {

    @Test
    void testGetData() {
        // Setup
        def myClassUnderTest = new MyClass(new FooService())

        // Run the test
        def result = myClassUnderTest.getData("someValue")

        // Verify the results
        assert "result" == result
    }

    @Test
    void testGetData_ThrowsIOException() {
        // Setup
        def myClassUnderTest = new MyClass(new FooService())

        // Run the test
        assertThrows(IOException.class, {
            myClassUnderTest.getData("someValue")
        })
    }

    @Test
    void testGetOtherData() {
        // Setup
        def myClassUnderTest = new MyClass(new FooService())

        // Run the test
        def result = myClassUnderTest.getOtherData("otherValue")

        // Verify the results
        assert "result" == result
    }

    @Test
    void testGetOtherData_ThrowsIOException() {
        // Setup
        def myClassUnderTest = new MyClass(new FooService())

        // Run the test
        assertThrows(IOException.class, {
            myClassUnderTest.getOtherData("otherValue")
        })
    }

    @Test
    void testGetThingFromDatabase() {
        // Setup
        def myClassUnderTest = new MyClass(new FooService())

        // Run the test
        def result = myClassUnderTest.getThingFromDatabase("value")

        // Verify the results
        assert "result" == result
    }

    @Test
    void testGetThingFromDatabase_ThrowsSQLException() {
        // Setup
        def myClassUnderTest = new MyClass(new FooService())

        // Run the test
        assertThrows(SQLException.class, {
            myClassUnderTest.getThingFromDatabase("value")
        })
    }

    @Test
    void testDoSomething() {
        // Setup
        def myClassUnderTest = new MyClass(new FooService())

        // Run the test
        def result = myClassUnderTest.doSomething("purchaseId")

        // Verify the results
        assert "result" == result
    }

    @Test
    void testActivateBar() {
        // Setup
        def myClassUnderTest = new MyClass(new FooService())

        // Run the test
        def result = myClassUnderTest.activateBar("computeCodeForOrder")

        // Verify the results
        assert "result" == result
    }

    @Test
    void testActivateBar2() {
        // Setup
        def myClassUnderTest = new MyClass(new FooService())

        // Run the test
        def result = myClassUnderTest.activateBar2("something")

        // Verify the results
        assert "result" == result
    }

    @Test
    void testActivateBar3() {
        // Setup
        def myClassUnderTest = new MyClass(new FooService())

        // Run the test
        def result = myClassUnderTest.activateBar3("something")

        // Verify the results
        assert "something" == result
    }
}
