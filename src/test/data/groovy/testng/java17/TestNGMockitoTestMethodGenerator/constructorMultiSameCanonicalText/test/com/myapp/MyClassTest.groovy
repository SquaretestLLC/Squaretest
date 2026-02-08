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
    private FooService mockAltFooService

    private MyClass myClassUnderTest

    @BeforeMethod
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass(mockMainFooService, mockAltFooService)
    }

    @Test
    void testGetFoo11() {
        // Setup
        // Configure FooService.getFoo1(...).
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")
        when(mockMainFooService.getFoo1("id")).thenReturn(fooData)

        // Run the test
        def result = myClassUnderTest.getFoo1("id")

        // Verify the results
    }

    @Test
    void testGetFoo1_MainFooServiceReturnsNull1() {
        // Setup
        when(mockMainFooService.getFoo1("id")).thenReturn(null)

        // Configure FooService.getFoo1(...).
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")
        when(mockAltFooService.getFoo1("id")).thenReturn(fooData)

        // Run the test
        def result = myClassUnderTest.getFoo1("id")

        // Verify the results
    }
}