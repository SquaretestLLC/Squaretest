package com.myapp;

import com.google.common.base.VerifyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private FooService mockFooService;
    @Mock
    private ErrorMessageProvider mockErrorMessageProvider;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFooService, mockErrorMessageProvider);
    }

    @Test
    void testStoreFoo1_ThrowsVerifyException() {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockFooService.getFoos1("id")).thenReturn(Arrays.asList(new FooData("id", "name")));

        // Run the test
        assertThrows(VerifyException.class, () -> myClassUnderTest.storeFoo1(fooData));
    }

    @Test
    void testStoreFoo1_FooServiceGetFoos1ReturnsNoItems() {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockFooService.getFoos1("id")).thenReturn(Collections.emptyList());

        // Run the test
        myClassUnderTest.storeFoo1(fooData);

        // Verify the results
        verify(mockFooService).storeFoo(new FooData("id", "name"));
    }

    @Test
    void testStoreFoo1_FooServiceStoreFooThrowsFooServiceException() {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockFooService.getFoos1("id")).thenReturn(Collections.emptyList());
        doThrow(FooServiceException.class).when(mockFooService).storeFoo(new FooData("id", "name"));

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.storeFoo1(fooData));
    }

    @Test
    void testStoreFoo2_ThrowsVerifyException() {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockFooService.getFoos1("id")).thenReturn(Arrays.asList(new FooData("id", "name")));
        when(mockErrorMessageProvider.getAlreadyExistsMessage("id")).thenReturn("result");

        // Run the test
        assertThrows(VerifyException.class, () -> myClassUnderTest.storeFoo2(fooData));
    }

    @Test
    void testStoreFoo2_FooServiceGetFoos1ReturnsNoItems() {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockFooService.getFoos1("id")).thenReturn(Collections.emptyList());
        when(mockErrorMessageProvider.getAlreadyExistsMessage("id")).thenReturn("result");

        // Run the test
        myClassUnderTest.storeFoo2(fooData);

        // Verify the results
        verify(mockFooService).storeFoo(new FooData("id", "name"));
    }

    @Test
    void testStoreFoo2_FooServiceStoreFooThrowsFooServiceException() {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockFooService.getFoos1("id")).thenReturn(Collections.emptyList());
        when(mockErrorMessageProvider.getAlreadyExistsMessage("id")).thenReturn("result");
        doThrow(FooServiceException.class).when(mockFooService).storeFoo(new FooData("id", "name"));

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.storeFoo2(fooData));
    }

    @Test
    void testGetFoo1() {
        // Setup
        final FooData expectedResult = new FooData("id", "name");
        when(mockFooService.getFoo1("id")).thenReturn(new FooData("id", "name"));

        // Run the test
        final FooData result = myClassUnderTest.getFoo1("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFoo1_FooServiceReturnsNull() {
        // Setup
        when(mockFooService.getFoo1("id")).thenReturn(null);

        // Run the test
        assertThrows(VerifyException.class, () -> myClassUnderTest.getFoo1("id"));
    }

    @Test
    void testGetFoo2() {
        // Setup
        final FooData expectedResult = new FooData("id", "name");
        when(mockFooService.getFoo1("id")).thenReturn(new FooData("id", "name"));
        when(mockErrorMessageProvider.getFooNotFoundMessage("id")).thenReturn("result");

        // Run the test
        final FooData result = myClassUnderTest.getFoo2("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFoo2_FooServiceReturnsNull() {
        // Setup
        when(mockFooService.getFoo1("id")).thenReturn(null);
        when(mockErrorMessageProvider.getFooNotFoundMessage("id")).thenReturn("result");

        // Run the test
        assertThrows(VerifyException.class, () -> myClassUnderTest.getFoo2("id"));
    }
}
