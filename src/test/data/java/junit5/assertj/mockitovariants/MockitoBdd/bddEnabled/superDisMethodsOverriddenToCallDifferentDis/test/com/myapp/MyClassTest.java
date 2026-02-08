package com.myapp;

import com.myapp.bases.FooService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
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
    void testPerformGetUpData() throws Exception {
        // Setup
        given(mockFooService.getThingFromDatabase("data")).willReturn("result");
        given(mockFooService.activateBar("data")).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.performGetUpData("data");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testPerformGetUpData_FooServiceGetThingFromDatabaseThrowsSQLException() throws Exception {
        // Setup
        given(mockFooService.getThingFromDatabase("data")).willThrow(SQLException.class);
        given(mockFooService.activateBar("data")).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.performGetUpData("data");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testPerformGetUpData1() {
        assertThat(myClassUnderTest.performGetUpData1("data")).isEqualTo("result");
    }

    @Test
    void testGetFoo() throws Exception {
        // Setup
        given(mockFooService.getThingFromDatabase("data")).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo("data");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo_FooServiceThrowsSQLException() throws Exception {
        // Setup
        given(mockFooService.getThingFromDatabase("data")).willThrow(SQLException.class);

        // Run the test
        final String result = myClassUnderTest.getFoo("data");

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo2() {
        // Setup
        given(mockFooService.activateBar("data")).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo2("data");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }
}
