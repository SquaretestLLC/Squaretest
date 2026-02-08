package com.myapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MyClassTest {

    @Mock
    private FooService mockFooService;

    @Test
    public void testGetData1() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        when(mockFooService.getData("someValue")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getData("someValue");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testGetData_FooServiceThrowsIOException1() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        when(mockFooService.getData("someValue")).thenThrow(IOException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getData("someValue")).isInstanceOf(IOException.class);
    }

    @Test
    public void testGetOtherData1() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        when(mockFooService.getOtherData("otherValue")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getOtherData("otherValue");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testGetOtherData_FooServiceThrowsIOException1() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        when(mockFooService.getOtherData("otherValue")).thenThrow(IOException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getOtherData("otherValue")).isInstanceOf(IOException.class);
    }

    @Test
    public void testGetThingFromDatabase1() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        when(mockFooService.getThingFromDatabase("value")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getThingFromDatabase("value");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testGetThingFromDatabase_FooServiceThrowsSQLException1() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        when(mockFooService.getThingFromDatabase("value")).thenThrow(SQLException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getThingFromDatabase("value")).isInstanceOf(SQLException.class);
    }

    @Test
    public void testDoSomething1() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        when(mockFooService.doSomething("purchaseId")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.doSomething("purchaseId");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testActivateBar1() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        when(mockFooService.activateBar("computeCodeForOrder")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.activateBar("computeCodeForOrder");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testActivateBar21() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        when(mockFooService.activateBar("computeCodeForOrder")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.activateBar2("something");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testActivateBar31() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        when(mockFooService.activateBar("computeCodeForOrder")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.activateBar3("something");

        // Verify the results
        assertThat(result).isEqualTo("something");
    }
}