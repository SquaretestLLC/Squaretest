package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private FooService mockMainFooService;
    @Mock
    private FooService mockOldFooService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockMainFooService, mockOldFooService);
    }

    @Test
    void testGetFooData1() {
        // Setup
        final FooData expectedResult = new FooData("id", "name");
        when(mockOldFooService.getFooData("id")).thenReturn(new FooData("id", "name"));
        when(mockMainFooService.getFooData("id")).thenReturn(new FooData("id", "name"));

        // Run the test
        final FooData result = myClassUnderTest.getFooData1("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooData1_OldFooServiceThrowsFooServiceException() {
        // Setup
        when(mockOldFooService.getFooData("id")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.getFooData1("id"));
    }

    @Test
    void testGetFooData1_MainFooServiceThrowsFooServiceException() {
        // Setup
        when(mockMainFooService.getFooData("id")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.getFooData1("id"));
    }

    @Test
    void testGetFooData2() {
        // Setup
        final FooData expectedResult = new FooData("id", "name");
        when(mockMainFooService.getFooData(FooType.Normal)).thenReturn(new FooData("id", "name"));
        when(mockMainFooService.getFooData("id")).thenReturn(new FooData("id", "name"));

        // Run the test
        final FooData result = myClassUnderTest.getFooData2("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooData2_FooServiceGetFooDataThrowsFooServiceException() {
        // Setup
        when(mockMainFooService.getFooData(FooType.Normal)).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.getFooData2("id"));
    }

    @Test
    void testGetFooData2_FooServiceGetFooDataThrowsFooServiceException() {
        // Setup
        when(mockMainFooService.getFooData("id")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.getFooData2("id"));
    }

    @Test
    void testGetFooData3() {
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

    @Test
    void testGetFooData3_FooServiceGetFooDataThrowsFooServiceException() {
        // Setup
        when(mockMainFooService.getFooData(FooType.Normal)).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.getFooData3("id"));
    }

    @Test
    void testGetFooData3_FooServiceGetFooDataThrowsFooServiceException() {
        // Setup
        when(mockMainFooService.getFooData("id")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.getFooData3("id"));
    }

    @Test
    void testGetFooData3_FooServiceGetFooDataThrowsFooServiceException() {
        // Setup
        when(mockMainFooService.getFooData("id", FooType.Normal)).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.getFooData3("id"));
    }

    @Test
    void testGetFooData4() {
        // Setup
        final FooData expectedResult = new FooData("id", "name");
        when(mockMainFooService.getFooData("id")).thenReturn(new FooData("id", "name"));

        // Run the test
        final FooData result = myClassUnderTest.getFooData4("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooData4_FooServiceThrowsFooServiceException() {
        // Setup
        when(mockMainFooService.getFooData("id")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.getFooData4("id"));
    }
}
