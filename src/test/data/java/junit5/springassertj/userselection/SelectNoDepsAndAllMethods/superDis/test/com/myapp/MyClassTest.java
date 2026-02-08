package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass(new FooService());
    }

    @Test
    void testGetData() throws Exception {
        // Setup
        // Run the test
        final String result = myClassUnderTest.getData("someValue");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetData_ThrowsIOException() {
        // Setup
        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getData("someValue")).isInstanceOf(IOException.class);
    }

    @Test
    void testGetOtherData() throws Exception {
        // Setup
        // Run the test
        final String result = myClassUnderTest.getOtherData("otherValue");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetOtherData_ThrowsIOException() {
        // Setup
        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getOtherData("otherValue")).isInstanceOf(IOException.class);
    }

    @Test
    void testGetThingFromDatabase() throws Exception {
        // Setup
        // Run the test
        final String result = myClassUnderTest.getThingFromDatabase("value");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetThingFromDatabase_ThrowsSQLException() {
        // Setup
        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getThingFromDatabase("value")).isInstanceOf(SQLException.class);
    }

    @Test
    void testDoSomething() {
        // Setup
        // Run the test
        final String result = myClassUnderTest.doSomething("purchaseId");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testActivateBar() {
        // Setup
        // Run the test
        final String result = myClassUnderTest.activateBar("computeCodeForOrder");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testActivateBar2() {
        // Setup
        // Run the test
        final String result = myClassUnderTest.activateBar2("something");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testActivateBar3() {
        // Setup
        // Run the test
        final String result = myClassUnderTest.activateBar3("something");

        // Verify the results
        assertThat(result).isEqualTo("something");
    }
}
