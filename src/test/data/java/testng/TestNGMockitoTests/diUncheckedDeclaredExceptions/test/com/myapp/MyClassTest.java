package com.myapp;

import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.NoSuchElementException;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class MyClassTest {

    @Mock
    private FooService mockFooService;

    private MyClass myClassUnderTest;

    @BeforeMethod
    public void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFooService);
    }

    @Test
    public void testGetFoo1() throws Exception {
        // Setup
        // Configure FooService.getFoo1(...).
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");
        when(mockFooService.getFoo1("id")).thenReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.getFoo1("id");

        // Verify the results
    }

    @Test(expectedExceptions = {FooServiceException.class})
    public void testGetFoo1_FooServiceThrowsFooServiceException() throws Exception {
        // Setup
        when(mockFooService.getFoo1("id")).thenThrow(FooServiceException.class);

        // Run the test
        myClassUnderTest.getFoo1("id");
    }

    @Test(expectedExceptions = {NoSuchElementException.class})
    public void testGetFoo1_FooServiceThrowsNoSuchElementException() throws Exception {
        // Setup
        when(mockFooService.getFoo1("id")).thenThrow(NoSuchElementException.class);

        // Run the test
        myClassUnderTest.getFoo1("id");
    }

    @Test
    public void testGetFoo2() throws Exception {
        // Setup
        // Configure FooService.getFoo2(...).
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");
        when(mockFooService.getFoo2("id")).thenReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.getFoo2("id");

        // Verify the results
    }

    @Test(expectedExceptions = {FooServiceException.class})
    public void testGetFoo2_FooServiceThrowsFooServiceException() throws Exception {
        // Setup
        when(mockFooService.getFoo2("id")).thenThrow(FooServiceException.class);

        // Run the test
        myClassUnderTest.getFoo2("id");
    }

    @Test(expectedExceptions = {RuntimeException.class})
    public void testGetFoo2_FooServiceThrowsRuntimeException() throws Exception {
        // Setup
        when(mockFooService.getFoo2("id")).thenThrow(RuntimeException.class);

        // Run the test
        myClassUnderTest.getFoo2("id");
    }

    @Test
    public void testGetFoo3() throws Exception {
        // Setup
        // Configure FooService.getFoo1(...).
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");
        when(mockFooService.getFoo1("id")).thenReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.getFoo3("id");

        // Verify the results
    }

    @Test(expectedExceptions = {FooServiceException.class})
    public void testGetFoo3_FooServiceThrowsFooServiceException() throws Exception {
        // Setup
        when(mockFooService.getFoo1("id")).thenThrow(FooServiceException.class);

        // Run the test
        myClassUnderTest.getFoo3("id");
    }

    @Test(expectedExceptions = {NoSuchElementException.class})
    public void testGetFoo3_FooServiceThrowsNoSuchElementException() throws Exception {
        // Setup
        when(mockFooService.getFoo1("id")).thenThrow(NoSuchElementException.class);

        // Run the test
        myClassUnderTest.getFoo3("id");
    }

    @Test
    public void testGetFoo4() throws Exception {
        // Setup
        // Configure FooService.getFoo2(...).
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");
        when(mockFooService.getFoo2("id")).thenReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.getFoo4("id");

        // Verify the results
    }

    @Test(expectedExceptions = {FooServiceException.class})
    public void testGetFoo4_FooServiceThrowsFooServiceException() throws Exception {
        // Setup
        when(mockFooService.getFoo2("id")).thenThrow(FooServiceException.class);

        // Run the test
        myClassUnderTest.getFoo4("id");
    }

    @Test(expectedExceptions = {RuntimeException.class})
    public void testGetFoo4_FooServiceThrowsRuntimeException() throws Exception {
        // Setup
        when(mockFooService.getFoo2("id")).thenThrow(RuntimeException.class);

        // Run the test
        myClassUnderTest.getFoo4("id");
    }
}
