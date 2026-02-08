package com.myapp

import groovy.transform.CompileStatic
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

import static org.mockito.Mockito.when

@RunWith(MockitoJUnitRunner.class)
@CompileStatic
class MyClassTest {

    @Mock
    private FooService mockFooService

    @Test
    void testGetFoo11() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)

        // Configure FooService.getFoo1(...).
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")
        when(mockFooService.getFoo1("id")).thenReturn(fooData)

        // Run the test
        def result = myClassUnderTest.getFoo1("id")

        // Verify the results
    }

    @Test
    void testGetDefaultFoo1() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)

        // Configure FooService.getDefaultFoo(...).
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")
        when(mockFooService.getDefaultFoo("defaultFooId")).thenReturn(fooData)

        // Run the test
        def result = myClassUnderTest.getDefaultFoo()

        // Verify the results
    }
}