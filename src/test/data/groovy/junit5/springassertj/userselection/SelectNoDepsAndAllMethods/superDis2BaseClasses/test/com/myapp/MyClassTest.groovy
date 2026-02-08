package com.myapp

import com.myapp.bases.FooService
import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import java.sql.SQLException

import static org.assertj.core.api.Assertions.assertThat
import static org.assertj.core.api.Assertions.assertThatThrownBy

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass(new FooService())
    }

    @Test
    void testGetData() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getData("someValue")

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testGetData_ThrowsIOException() {
        // Setup
        // Run the test
        assertThatThrownBy({
            myClassUnderTest.getData("someValue")
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testGetOtherData() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getOtherData("otherValue")

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testGetOtherData_ThrowsIOException() {
        // Setup
        // Run the test
        assertThatThrownBy({
            myClassUnderTest.getOtherData("otherValue")
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testGetThingFromDatabase() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getThingFromDatabase("value")

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testGetThingFromDatabase_ThrowsSQLException() {
        // Setup
        // Run the test
        assertThatThrownBy({
            myClassUnderTest.getThingFromDatabase("value")
        }).isInstanceOf(SQLException.class)
    }

    @Test
    void testDoSomething() {
        // Setup
        // Run the test
        def result = myClassUnderTest.doSomething("purchaseId")

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testActivateBar() {
        // Setup
        // Run the test
        def result = myClassUnderTest.activateBar("computeCodeForOrder")

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testActivateBar2() {
        // Setup
        // Run the test
        def result = myClassUnderTest.activateBar2("something")

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testActivateBar3() {
        // Setup
        // Run the test
        def result = myClassUnderTest.activateBar3("something")

        // Verify the results
        assertThat(result).isEqualTo("something")
    }
}
