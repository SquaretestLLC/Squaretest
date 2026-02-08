package com.myapp;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class MyClassTest {

    @Mock
    private FooService mockFooService;

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
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
        assertEquals(expectedResult, result);
    }

    @Test(expected = NoSuchElementException.class)
    public void testGetFoo1_FooServiceThrowsNoSuchElementException() {
        // Setup
        when(mockFooService.getFoo1("id")).thenThrow(NoSuchElementException.class);

        // Run the test
        myClassUnderTest.getFoo1("id");
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
        assertEquals(expectedResult, result);
    }

    @Test(expected = NoSuchElementException.class)
    public void testGetFoo2_FooServiceThrowsNoSuchElementException() {
        // Setup
        when(mockFooService.getFoo2("id")).thenThrow(NoSuchElementException.class);

        // Run the test
        myClassUnderTest.getFoo2("id");
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
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetFoo3_FooServiceThrowsNoSuchElementException() {
        // Setup
        when(mockFooService.getFoo2("id")).thenThrow(NoSuchElementException.class);

        // Run the test
        final FooData result = myClassUnderTest.getFoo3("id");

        // Verify the results
        assertNull(result);
    }
}
