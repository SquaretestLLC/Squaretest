package com.myapp;

import com.myapp.bases.FooService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MyClassTest {

    @Mock
    private FooService mockFooService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass(mockFooService);
    }

    @Test
    void testGetData() throws Exception {
        // Setup
        when(mockFooService.getData("someValue")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getData("someValue");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetData_FooServiceThrowsIOException() throws Exception {
        // Setup
        when(mockFooService.getData("someValue")).thenThrow(IOException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getData("someValue")).isInstanceOf(IOException.class);
    }

    @Test
    void testGetOtherData() throws Exception {
        // Setup
        when(mockFooService.getOtherData("otherValue")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getOtherData("otherValue");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetOtherData_FooServiceThrowsIOException() throws Exception {
        // Setup
        when(mockFooService.getOtherData("otherValue")).thenThrow(IOException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getOtherData("otherValue")).isInstanceOf(IOException.class);
    }

    @Test
    void testGetThingFromDatabase() throws Exception {
        // Setup
        when(mockFooService.getThingFromDatabase("value")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getThingFromDatabase("value");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetThingFromDatabase_FooServiceThrowsSQLException() throws Exception {
        // Setup
        when(mockFooService.getThingFromDatabase("value")).thenThrow(SQLException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getThingFromDatabase("value")).isInstanceOf(SQLException.class);
    }

    @Test
    void testDoSomething() {
        // Setup
        when(mockFooService.doSomething("purchaseId")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.doSomething("purchaseId");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testActivateBar() {
        // Setup
        when(mockFooService.activateBar("computeCodeForOrder")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.activateBar("computeCodeForOrder");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testActivateBar2() {
        // Setup
        when(mockFooService.activateBar("computeCodeForOrder")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.activateBar2("something");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testActivateBar3() {
        // Setup
        when(mockFooService.activateBar("computeCodeForOrder")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.activateBar3("something");

        // Verify the results
        assertThat(result).isEqualTo("something");
    }
}
