package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(RobolectricTestRunner.class)
public class MyClassTest {

    @Mock
    private FooService mockMainFooService;
    @Mock
    private FooService mockOldFooService;

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockMainFooService, mockOldFooService);
    }

    @Test
    public void testGetFooData1() {
        // Setup
        final FooData expectedResult = new FooData("id", "name");
        when(mockOldFooService.getFooData("id")).thenReturn(new FooData("id", "name"));
        when(mockMainFooService.getFooData("id")).thenReturn(new FooData("id", "name"));

        // Run the test
        final FooData result = myClassUnderTest.getFooData1("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test(expected = FooServiceException.class)
    public void testGetFooData1_OldFooServiceThrowsFooServiceException() {
        // Setup
        when(mockOldFooService.getFooData("id")).thenThrow(FooServiceException.class);

        // Run the test
        myClassUnderTest.getFooData1("id");
    }

    @Test(expected = FooServiceException.class)
    public void testGetFooData1_MainFooServiceThrowsFooServiceException() {
        // Setup
        when(mockMainFooService.getFooData("id")).thenThrow(FooServiceException.class);

        // Run the test
        myClassUnderTest.getFooData1("id");
    }

    @Test
    public void testGetFooData2() {
        // Setup
        final FooData expectedResult = new FooData("id", "name");
        when(mockMainFooService.getFooData(FooType.Normal)).thenReturn(new FooData("id", "name"));
        when(mockMainFooService.getFooData("id")).thenReturn(new FooData("id", "name"));

        // Run the test
        final FooData result = myClassUnderTest.getFooData2("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test(expected = FooServiceException.class)
    public void testGetFooData2_FooServiceGetFooData1ThrowsFooServiceException() {
        // Setup
        when(mockMainFooService.getFooData(FooType.Normal)).thenThrow(FooServiceException.class);

        // Run the test
        myClassUnderTest.getFooData2("id");
    }

    @Test(expected = FooServiceException.class)
    public void testGetFooData2_FooServiceGetFooData2ThrowsFooServiceException() {
        // Setup
        when(mockMainFooService.getFooData("id")).thenThrow(FooServiceException.class);

        // Run the test
        myClassUnderTest.getFooData2("id");
    }

    @Test
    public void testGetFooData3() {
        // Setup
        final FooData expectedResult = new FooData("id", "name");
        when(mockMainFooService.getFooData(FooType.Normal)).thenReturn(new FooData("id", "name"));
        when(mockMainFooService.getFooData("id")).thenReturn(new FooData("id", "name"));
        when(mockMainFooService.getFooData("id", FooType.Normal)).thenReturn(new FooData("id", "name"));

        // Run the test
        final FooData result = myClassUnderTest.getFooData3("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test(expected = FooServiceException.class)
    public void testGetFooData3_FooServiceGetFooData1ThrowsFooServiceException() {
        // Setup
        when(mockMainFooService.getFooData(FooType.Normal)).thenThrow(FooServiceException.class);

        // Run the test
        myClassUnderTest.getFooData3("id");
    }

    @Test(expected = FooServiceException.class)
    public void testGetFooData3_FooServiceGetFooData2ThrowsFooServiceException() {
        // Setup
        when(mockMainFooService.getFooData("id")).thenThrow(FooServiceException.class);

        // Run the test
        myClassUnderTest.getFooData3("id");
    }

    @Test(expected = FooServiceException.class)
    public void testGetFooData3_FooServiceGetFooData3ThrowsFooServiceException() {
        // Setup
        when(mockMainFooService.getFooData("id", FooType.Normal)).thenThrow(FooServiceException.class);

        // Run the test
        myClassUnderTest.getFooData3("id");
    }

    @Test
    public void testGetFooData4() {
        // Setup
        final FooData expectedResult = new FooData("id", "name");
        when(mockMainFooService.getFooData("id")).thenReturn(new FooData("id", "name"));

        // Run the test
        final FooData result = myClassUnderTest.getFooData4("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test(expected = FooServiceException.class)
    public void testGetFooData4_FooServiceThrowsFooServiceException() {
        // Setup
        when(mockMainFooService.getFooData("id")).thenThrow(FooServiceException.class);

        // Run the test
        myClassUnderTest.getFooData4("id");
    }
}
