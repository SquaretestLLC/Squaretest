package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test

import java.sql.SQLException

import static org.assertj.core.api.Assertions.assertThat
import static org.assertj.core.api.Assertions.assertThatThrownBy

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
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testGetData_ThrowsIOException1() {
        // Setup
        // Run the test
        assertThatThrownBy({
            myClassUnderTest.getData("someValue")
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testGetOtherData1() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getOtherData("otherValue")

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testGetOtherData_ThrowsIOException1() {
        // Setup
        // Run the test
        assertThatThrownBy({
            myClassUnderTest.getOtherData("otherValue")
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testGetThingFromDatabase1() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getThingFromDatabase("value")

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testGetThingFromDatabase_ThrowsSQLException1() {
        // Setup
        // Run the test
        assertThatThrownBy({
            myClassUnderTest.getThingFromDatabase("value")
        }).isInstanceOf(SQLException.class)
    }

    @Test
    void testDoSomething1() {
        // Setup
        // Run the test
        def result = myClassUnderTest.doSomething("purchaseId")

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testActivateBar1() {
        // Setup
        // Run the test
        def result = myClassUnderTest.activateBar("computeCodeForOrder")

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testActivateBar21() {
        // Setup
        // Run the test
        def result = myClassUnderTest.activateBar2("something")

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testActivateBar31() {
        // Setup
        // Run the test
        def result = myClassUnderTest.activateBar3("something")

        // Verify the results
        assertThat(result).isEqualTo("something")
    }
}