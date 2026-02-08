package com.myapp;

import com.myapp.other.FooService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.IOException;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private FooService mockFooService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFooService);
    }

    @Test
    void testGetSomething() throws Exception {
        // Setup
        given(mockFooService.getData("value")).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getSomething("value");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetSomething_FooServiceGetDataReturnsNull() throws Exception {
        // Setup
        given(mockFooService.getData("value")).willReturn(null);
        given(mockFooService.getOtherData("value")).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getSomething("value");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetSomething_FooServiceGetDataThrowsIOException() throws Exception {
        // Setup
        given(mockFooService.getData("value")).willThrow(IOException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getSomething("value")).isInstanceOf(IOException.class);
    }

    @Test
    void testGetSomething_FooServiceGetOtherDataReturnsNull() throws Exception {
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
    void testGetSomething_FooServiceGetOtherDataThrowsIOException() throws Exception {
        // Setup
        given(mockFooService.getData("value")).willReturn(null);
        given(mockFooService.getOtherData("value")).willThrow(IOException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getSomething("value")).isInstanceOf(IOException.class);
    }

    @Test
    void testGetSomething_FooServiceGetThingFromDatabaseThrowsSQLException() throws Exception {
        // Setup
        given(mockFooService.getData("value")).willReturn(null);
        given(mockFooService.getOtherData("value")).willReturn(null);
        given(mockFooService.getThingFromDatabase("value")).willThrow(SQLException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getSomething("value")).isInstanceOf(SQLException.class);
    }
}
