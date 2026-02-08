package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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
    public void testGetFoo1() {
        // Setup
        final FooData expectedResult = new FooData();
        expectedResult.setId("id");
        expectedResult.setName("name");

        // Configure FooService.getFoo1(...).
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");
        when(mockFooService.getFoo1("id")).thenReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.getFoo1("id");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testGetFoo1_FooServiceThrowsNoSuchElementException() {
        // Setup
        when(mockFooService.getFoo1("id")).thenThrow(NoSuchElementException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getFoo1("id")).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    public void testGetFoo2() {
        // Setup
        final FooData expectedResult = new FooData();
        expectedResult.setId("id");
        expectedResult.setName("name");

        // Configure FooService.getFoo2(...).
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");
        when(mockFooService.getFoo2("id")).thenReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.getFoo2("id");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testGetFoo2_FooServiceThrowsNoSuchElementException() {
        // Setup
        when(mockFooService.getFoo2("id")).thenThrow(NoSuchElementException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getFoo2("id")).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    public void testGetFoo3() {
        // Setup
        final FooData expectedResult = new FooData();
        expectedResult.setId("id");
        expectedResult.setName("name");

        // Configure FooService.getFoo2(...).
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");
        when(mockFooService.getFoo2("id")).thenReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.getFoo3("id");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testGetFoo3_FooServiceThrowsNoSuchElementException() {
        // Setup
        when(mockFooService.getFoo2("id")).thenThrow(NoSuchElementException.class);

        // Run the test
        final FooData result = myClassUnderTest.getFoo3("id");

        // Verify the results
        assertThat(result).isNull();
    }
}
