package com.myapp;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MyClassTest {

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass(new FooService());
    }

    @Test
    public void testGetData() throws Exception {
        // Setup
        // Run the test
        final String result = myClassUnderTest.getData("someValue");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testGetData_ThrowsIOException() {
        // Setup
        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getData("someValue")).isInstanceOf(IOException.class);
    }

    @Test
    public void testGetOtherData() throws Exception {
        // Setup
        // Run the test
        final String result = myClassUnderTest.getOtherData("otherValue");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testGetOtherData_ThrowsIOException() {
        // Setup
        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getOtherData("otherValue")).isInstanceOf(IOException.class);
    }

    @Test
    public void testGetThingFromDatabase() throws Exception {
        // Setup
        // Run the test
        final String result = myClassUnderTest.getThingFromDatabase("value");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testGetThingFromDatabase_ThrowsSQLException() {
        // Setup
        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getThingFromDatabase("value")).isInstanceOf(SQLException.class);
    }

    @Test
    public void testDoSomething() {
        // Setup
        // Run the test
        final String result = myClassUnderTest.doSomething("purchaseId");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testActivateBar() {
        // Setup
        // Run the test
        final String result = myClassUnderTest.activateBar("computeCodeForOrder");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testActivateBar2() {
        // Setup
        // Run the test
        final String result = myClassUnderTest.activateBar2("something");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testActivateBar3() {
        // Setup
        // Run the test
        final String result = myClassUnderTest.activateBar3("something");

        // Verify the results
        assertThat(result).isEqualTo("something");
    }
}
