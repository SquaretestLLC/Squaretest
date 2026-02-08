package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

import static org.mockito.Mockito.when

@CompileStatic
@RunWith(MockitoJUnitRunner.class)
class MyClassTest {

    @Mock
    private FooService mockFooService

    private MyClass myClassUnderTest

    @Before
    void setUp() {
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

    @Test(expected = FileNotFoundException.class)
    void testGetFooData_FooServiceThrowsFileNotFoundException() {
        // Setup
        when(mockFooService.getFooData(0L)).thenThrow(FileNotFoundException.class)

        // Run the test
        myClassUnderTest.getFooData(0L)
    }
}
