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
    private FooService mockFooService

    private MyClass myClassUnderTest

    @BeforeMethod
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass(mockFooService)
    }

    @Test
    void testGetFooData() {
        // Setup
        when(mockFooService.getFooData(0L)).thenReturn(new FooData("name", 0L))

        // Run the test
        def result = myClassUnderTest.getFooData(0L)

        // Verify the results
    }

    @Test(expectedExceptions = [FileNotFoundException.class])
    void testGetFooData_FooServiceThrowsFileNotFoundException() {
        // Setup
        when(mockFooService.getFooData(0L)).thenThrow(FileNotFoundException.class)

        // Run the test
        myClassUnderTest.getFooData(0L)
    }
}
