package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(RobolectricTestRunner.class)
public class MyClassTest {

    @Mock
    private FooService mockNewFooService;
    @Mock
    private FooService mockOldFooService;

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockNewFooService, mockOldFooService);
    }

    @Test
    public void testGetFooData() {
        // Setup
        when(mockNewFooService.getFooData(0L)).thenReturn(new FooData("name", 0L));

        // Run the test
        final FooData result = myClassUnderTest.getFooData(0);

        // Verify the results
    }

    @Test
    public void testGetFooData_NewFooServiceReturnsNull() {
        // Setup
        when(mockNewFooService.getFooData(0L)).thenReturn(null);
        when(mockOldFooService.getFooData(0L)).thenReturn(new FooData("name", 0L));

        // Run the test
        final FooData result = myClassUnderTest.getFooData(0);

        // Verify the results
    }

    @Test(expected = FooServiceException.class)
    public void testGetFooData_NewFooServiceThrowsFooServiceException() {
        // Setup
        when(mockNewFooService.getFooData(0L)).thenThrow(FooServiceException.class);

        // Run the test
        myClassUnderTest.getFooData(0);
    }

    @Test(expected = FooNetworkException.class)
    public void testGetFooData_NewFooServiceThrowsFooNetworkException() {
        // Setup
        when(mockNewFooService.getFooData(0L)).thenThrow(FooNetworkException.class);

        // Run the test
        myClassUnderTest.getFooData(0);
    }

    @Test(expected = FooServiceException.class)
    public void testGetFooData_OldFooServiceThrowsFooServiceException() {
        // Setup
        when(mockNewFooService.getFooData(0L)).thenReturn(null);
        when(mockOldFooService.getFooData(0L)).thenThrow(FooServiceException.class);

        // Run the test
        myClassUnderTest.getFooData(0);
    }

    @Test(expected = FooNetworkException.class)
    public void testGetFooData_OldFooServiceThrowsFooNetworkException() {
        // Setup
        when(mockNewFooService.getFooData(0L)).thenReturn(null);
        when(mockOldFooService.getFooData(0L)).thenThrow(FooNetworkException.class);

        // Run the test
        myClassUnderTest.getFooData(0);
    }
}
