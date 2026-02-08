package com.myapp;

import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MyClassTest {

    @Test
    public void testGetData1() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(new FooService());

        // Run the test
        final String result = myClassUnderTest.getData("someValue");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testGetData_ThrowsIOException1() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(new FooService());

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getData("someValue")).isInstanceOf(IOException.class);
    }

    @Test
    public void testGetOtherData1() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(new FooService());

        // Run the test
        final String result = myClassUnderTest.getOtherData("otherValue");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testGetOtherData_ThrowsIOException1() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(new FooService());

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getOtherData("otherValue")).isInstanceOf(IOException.class);
    }

    @Test
    public void testGetThingFromDatabase1() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(new FooService());

        // Run the test
        final String result = myClassUnderTest.getThingFromDatabase("value");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testGetThingFromDatabase_ThrowsSQLException1() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(new FooService());

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getThingFromDatabase("value")).isInstanceOf(SQLException.class);
    }

    @Test
    public void testDoSomething1() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(new FooService());

        // Run the test
        final String result = myClassUnderTest.doSomething("purchaseId");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testActivateBar1() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(new FooService());

        // Run the test
        final String result = myClassUnderTest.activateBar("computeCodeForOrder");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testActivateBar21() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(new FooService());

        // Run the test
        final String result = myClassUnderTest.activateBar2("something");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testActivateBar31() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(new FooService());

        // Run the test
        final String result = myClassUnderTest.activateBar3("something");

        // Verify the results
        assertThat(result).isEqualTo("something");
    }
}