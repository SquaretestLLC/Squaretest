package com.myapp

import groovy.transform.CompileStatic
import org.mockito.Mock
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import static org.mockito.BDDMockito.given
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
    void testGetData() {
        // Setup
        given(mockFooService.getData(0L)).willReturn(Optional.of(new FooData(0L, "name")))

        // Run the test
        def result = myClassUnderTest.getData(0L)

        // Verify the results
    }

    @Test(expectedExceptions = [FooException.class])
    void testGetData_FooServiceReturnsAbsent() {
        // Setup
        given(mockFooService.getData(0L)).willReturn(Optional.empty())

        // Run the test
        myClassUnderTest.getData(0L)
    }
}
