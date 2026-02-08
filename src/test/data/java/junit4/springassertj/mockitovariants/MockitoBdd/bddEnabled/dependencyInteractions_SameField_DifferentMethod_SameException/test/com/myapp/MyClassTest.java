package com.myapp;

import com.myapp.other.FooService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

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
    public void testGetSomething() throws Exception {
        // Setup
        given(mockFooService.getData("value")).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getSomething("value");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testGetSomething_FooServiceGetDataReturnsNull() throws Exception {
        // Setup
        given(mockFooService.getData("value")).willReturn(null);
        given(mockFooService.getOtherData("value")).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getSomething("value");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testGetSomething_FooServiceGetDataThrowsIOException() throws Exception {
        // Setup
        given(mockFooService.getData("value")).willThrow(IOException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getSomething("value")).isInstanceOf(IOException.class);
    }

    @Test
    public void testGetSomething_FooServiceGetOtherDataReturnsNull() throws Exception {
        // Setup
        given(mockFooService.getData("value")).willReturn(null);
        given(mockFooService.getOtherData("value")).willReturn(null);
        given(mockFooService.getThingFromDatabase("value")).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getSomething("value");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testGetSomething_FooServiceGetOtherDataThrowsIOException() throws Exception {
        // Setup
        given(mockFooService.getData("value")).willReturn(null);
        given(mockFooService.getOtherData("value")).willThrow(IOException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getSomething("value")).isInstanceOf(IOException.class);
    }

    @Test
    public void testGetSomething_FooServiceGetThingFromDatabaseThrowsSQLException() throws Exception {
        // Setup
        given(mockFooService.getData("value")).willReturn(null);
        given(mockFooService.getOtherData("value")).willReturn(null);
        given(mockFooService.getThingFromDatabase("value")).willThrow(SQLException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getSomething("value")).isInstanceOf(SQLException.class);
    }
}
