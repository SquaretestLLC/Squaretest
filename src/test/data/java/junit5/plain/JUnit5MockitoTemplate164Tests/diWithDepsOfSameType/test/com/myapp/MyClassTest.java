package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private FooService mockNewFooService;
    @Mock
    private FooService mockOldFooService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockNewFooService, mockOldFooService);
    }

    @Test
    void testGetFooData() {
        // Setup
        when(mockNewFooService.getFooData(0L)).thenReturn(new FooData("name", 0L));
        when(mockOldFooService.getFooData(0L)).thenReturn(new FooData("name", 0L));

        // Run the test
        final FooData result = myClassUnderTest.getFooData(0);

        // Verify the results
    }

    @Test
    void testGetFooData_NewFooServiceReturnsNull() {
        // Setup
        when(mockNewFooService.getFooData(0L)).thenReturn(null);
        when(mockOldFooService.getFooData(0L)).thenReturn(new FooData("name", 0L));

        // Run the test
        final FooData result = myClassUnderTest.getFooData(0);

        // Verify the results
    }

    @Test
    void testGetFooData_NewFooServiceThrowsFooServiceException() {
        // Setup
        when(mockNewFooService.getFooData(0L)).thenThrow(FooServiceException.class);
        when(mockOldFooService.getFooData(0L)).thenReturn(new FooData("name", 0L));

        // Run the test
        final FooData result = myClassUnderTest.getFooData(0);

        // Verify the results
    }

    @Test
    void testGetFooData_NewFooServiceThrowsFooNetworkException() {
        // Setup
        when(mockNewFooService.getFooData(0L)).thenThrow(FooNetworkException.class);
        when(mockOldFooService.getFooData(0L)).thenReturn(new FooData("name", 0L));

        // Run the test
        final FooData result = myClassUnderTest.getFooData(0);

        // Verify the results
    }

    @Test
    void testGetFooData_OldFooServiceThrowsFooServiceException() {
        // Setup
        when(mockNewFooService.getFooData(0L)).thenReturn(new FooData("name", 0L));
        when(mockOldFooService.getFooData(0L)).thenThrow(FooServiceException.class);

        // Run the test
        final FooData result = myClassUnderTest.getFooData(0);

        // Verify the results
    }

    @Test
    void testGetFooData_OldFooServiceThrowsFooNetworkException() {
        // Setup
        when(mockNewFooService.getFooData(0L)).thenReturn(new FooData("name", 0L));
        when(mockOldFooService.getFooData(0L)).thenThrow(FooNetworkException.class);

        // Run the test
        final FooData result = myClassUnderTest.getFooData(0);

        // Verify the results
    }
}
