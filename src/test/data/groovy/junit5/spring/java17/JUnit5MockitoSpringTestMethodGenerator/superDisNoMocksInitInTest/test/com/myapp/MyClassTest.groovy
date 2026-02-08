package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.Test

import java.sql.SQLException

import static org.junit.jupiter.api.Assertions.assertThrows

@CompileStatic
class MyClassTest {

    @Test
    void testGetData1() {
        // Setup
        def myClassUnderTest = new MyClass(new FooService())

        // Run the test
        def result = myClassUnderTest.getData("someValue")

        // Verify the results
        assert "result" == result
    }

    @Test
    void testGetData_ThrowsIOException1() {
        // Setup
        def myClassUnderTest = new MyClass(new FooService())

        // Run the test
        assertThrows(IOException.class, {
            myClassUnderTest.getData("someValue")
        })
    }

    @Test
    void testGetOtherData1() {
        // Setup
        def myClassUnderTest = new MyClass(new FooService())

        // Run the test
        def result = myClassUnderTest.getOtherData("otherValue")

        // Verify the results
        assert "result" == result
    }

    @Test
    void testGetOtherData_ThrowsIOException1() {
        // Setup
        def myClassUnderTest = new MyClass(new FooService())

        // Run the test
        assertThrows(IOException.class, {
            myClassUnderTest.getOtherData("otherValue")
        })
    }

    @Test
    void testGetThingFromDatabase1() {
        // Setup
        def myClassUnderTest = new MyClass(new FooService())

        // Run the test
        def result = myClassUnderTest.getThingFromDatabase("value")

        // Verify the results
        assert "result" == result
    }

    @Test
    void testGetThingFromDatabase_ThrowsSQLException1() {
        // Setup
        def myClassUnderTest = new MyClass(new FooService())

        // Run the test
        assertThrows(SQLException.class, {
            myClassUnderTest.getThingFromDatabase("value")
        })
    }

    @Test
    void testDoSomething1() {
        // Setup
        def myClassUnderTest = new MyClass(new FooService())

        // Run the test
        def result = myClassUnderTest.doSomething("purchaseId")

        // Verify the results
        assert "result" == result
    }

    @Test
    void testActivateBar1() {
        // Setup
        def myClassUnderTest = new MyClass(new FooService())

        // Run the test
        def result = myClassUnderTest.activateBar("computeCodeForOrder")

        // Verify the results
        assert "result" == result
    }

    @Test
    void testActivateBar21() {
        // Setup
        def myClassUnderTest = new MyClass(new FooService())

        // Run the test
        def result = myClassUnderTest.activateBar2("something")

        // Verify the results
        assert "result" == result
    }

    @Test
    void testActivateBar31() {
        // Setup
        def myClassUnderTest = new MyClass(new FooService())

        // Run the test
        def result = myClassUnderTest.activateBar3("something")

        // Verify the results
        assert "something" == result
    }
}