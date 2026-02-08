package com.myapp

import groovy.transform.CompileStatic
import org.mockito.Mock
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import static org.mockito.Mockito.when
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
class MyClassTest {

    @Mock
    private FooService mockMainFooService
    @Mock
    private FooService mockOldFooService

    private MyClass myClassUnderTest

    @BeforeMethod
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass(mockMainFooService, mockOldFooService)
    }

    @Test
    void testGetFooData1() {
        // Setup
        def expectedResult = new FooData("id", "name")
        when(mockOldFooService.getFooData("id")).thenReturn(new FooData("id", "name"))
        when(mockMainFooService.getFooData("id")).thenReturn(new FooData("id", "name"))

        // Run the test
        def result = myClassUnderTest.getFooData1("id")

        // Verify the results
        assert expectedResult == result
    }

    @Test(expectedExceptions = [FooServiceException.class])
    void testGetFooData1_OldFooServiceThrowsFooServiceException() {
        // Setup
        when(mockOldFooService.getFooData("id")).thenThrow(FooServiceException.class)

        // Run the test
        myClassUnderTest.getFooData1("id")
    }

    @Test(expectedExceptions = [FooServiceException.class])
    void testGetFooData1_MainFooServiceThrowsFooServiceException() {
        // Setup
        when(mockMainFooService.getFooData("id")).thenThrow(FooServiceException.class)

        // Run the test
        myClassUnderTest.getFooData1("id")
    }

    @Test
    void testGetFooData2() {
        // Setup
        def expectedResult = new FooData("id", "name")
        when(mockMainFooService.getFooData(FooType.Normal)).thenReturn(new FooData("id", "name"))
        when(mockMainFooService.getFooData("id")).thenReturn(new FooData("id", "name"))

        // Run the test
        def result = myClassUnderTest.getFooData2("id")

        // Verify the results
        assert expectedResult == result
    }

    @Test(expectedExceptions = [FooServiceException.class])
    void testGetFooData2_FooServiceGetFooData1ThrowsFooServiceException() {
        // Setup
        when(mockMainFooService.getFooData(FooType.Normal)).thenThrow(FooServiceException.class)

        // Run the test
        myClassUnderTest.getFooData2("id")
    }

    @Test(expectedExceptions = [FooServiceException.class])
    void testGetFooData2_FooServiceGetFooData2ThrowsFooServiceException() {
        // Setup
        when(mockMainFooService.getFooData("id")).thenThrow(FooServiceException.class)

        // Run the test
        myClassUnderTest.getFooData2("id")
    }

    @Test
    void testGetFooData3() {
        // Setup
        def expectedResult = new FooData("id", "name")
        when(mockMainFooService.getFooData(FooType.Normal)).thenReturn(new FooData("id", "name"))
        when(mockMainFooService.getFooData("id")).thenReturn(new FooData("id", "name"))
        when(mockMainFooService.getFooData("id", FooType.Normal)).thenReturn(new FooData("id", "name"))

        // Run the test
        def result = myClassUnderTest.getFooData3("id")

        // Verify the results
        assert expectedResult == result
    }

    @Test(expectedExceptions = [FooServiceException.class])
    void testGetFooData3_FooServiceGetFooData1ThrowsFooServiceException() {
        // Setup
        when(mockMainFooService.getFooData(FooType.Normal)).thenThrow(FooServiceException.class)

        // Run the test
        myClassUnderTest.getFooData3("id")
    }

    @Test(expectedExceptions = [FooServiceException.class])
    void testGetFooData3_FooServiceGetFooData2ThrowsFooServiceException() {
        // Setup
        when(mockMainFooService.getFooData("id")).thenThrow(FooServiceException.class)

        // Run the test
        myClassUnderTest.getFooData3("id")
    }

    @Test(expectedExceptions = [FooServiceException.class])
    void testGetFooData3_FooServiceGetFooData3ThrowsFooServiceException() {
        // Setup
        when(mockMainFooService.getFooData("id", FooType.Normal)).thenThrow(FooServiceException.class)

        // Run the test
        myClassUnderTest.getFooData3("id")
    }

    @Test
    void testGetFooData4() {
        // Setup
        def expectedResult = new FooData("id", "name")
        when(mockMainFooService.getFooData("id")).thenReturn(new FooData("id", "name"))

        // Run the test
        def result = myClassUnderTest.getFooData4("id")

        // Verify the results
        assert expectedResult == result
    }

    @Test(expectedExceptions = [FooServiceException.class])
    void testGetFooData4_FooServiceThrowsFooServiceException() {
        // Setup
        when(mockMainFooService.getFooData("id")).thenThrow(FooServiceException.class)

        // Run the test
        myClassUnderTest.getFooData4("id")
    }
}
