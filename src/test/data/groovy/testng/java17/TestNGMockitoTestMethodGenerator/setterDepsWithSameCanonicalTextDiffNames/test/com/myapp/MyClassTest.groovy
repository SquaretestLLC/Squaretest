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
    private FooService theMockFooService
    @Mock
    private FooService theMockBarService

    private MyClass myClassUnderTest

    @BeforeMethod
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass()
        myClassUnderTest.setFooService(theMockFooService)
        myClassUnderTest.setBarService(theMockBarService)
    }

    @Test
    void testGetFooAndBar11() {
        // Setup
        // Configure FooService.getFoo1(...).
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")
        when(theMockFooService.getFoo1("id")).thenReturn(fooData)

        // Configure FooService.getFoo1(...).
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        when(theMockBarService.getFoo1("id")).thenReturn(fooData1)

        // Run the test
        def result = myClassUnderTest.getFooAndBar1("id")

        // Verify the results
    }
}