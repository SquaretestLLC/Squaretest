package com.myapp;

import com.myapp.bases.FooService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MyClassTest {

    @Mock
    private FooService mockFooService;

    @Test
    void testPerformGetUpData() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        when(mockFooService.getThingFromDatabase("data")).thenReturn("result");
        when(mockFooService.activateBar("data")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.performGetUpData("data");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testPerformGetUpData_FooServiceGetThingFromDatabaseThrowsSQLException() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        when(mockFooService.getThingFromDatabase("data")).thenThrow(SQLException.class);
        when(mockFooService.activateBar("data")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.performGetUpData("data");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testPerformGetUpData1() {
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        assertThat(myClassUnderTest.performGetUpData1("data")).isEqualTo("result");
    }

    @Test
    void testGetFoo() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        when(mockFooService.getThingFromDatabase("data")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo("data");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo_FooServiceThrowsSQLException() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        when(mockFooService.getThingFromDatabase("data")).thenThrow(SQLException.class);

        // Run the test
        final String result = myClassUnderTest.getFoo("data");

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo2() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        when(mockFooService.activateBar("data")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo2("data");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }
}
