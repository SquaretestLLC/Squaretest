package com.myapp;

import com.myapp.bases.FooService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MyClassTest {

    @Mock
    private FooService mockFooService;

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass(mockFooService);
    }

    @Test
    public void testPerformGetUpData() throws Exception {
        // Setup
        when(mockFooService.getThingFromDatabase("data")).thenReturn("result");
        when(mockFooService.activateBar("data")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.performGetUpData("data");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testPerformGetUpData_FooServiceGetThingFromDatabaseThrowsSQLException() throws Exception {
        // Setup
        when(mockFooService.getThingFromDatabase("data")).thenThrow(SQLException.class);
        when(mockFooService.activateBar("data")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.performGetUpData("data");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testPerformGetUpData1() {
        assertThat(myClassUnderTest.performGetUpData1("data")).isEqualTo("result");
    }

    @Test
    public void testGetFoo() throws Exception {
        // Setup
        when(mockFooService.getThingFromDatabase("data")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo("data");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testGetFoo_FooServiceThrowsSQLException() throws Exception {
        // Setup
        when(mockFooService.getThingFromDatabase("data")).thenThrow(SQLException.class);

        // Run the test
        final String result = myClassUnderTest.getFoo("data");

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    public void testGetFoo2() {
        // Setup
        when(mockFooService.activateBar("data")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo2("data");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }
}
