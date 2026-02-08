package com.myapp

import groovy.transform.CompileStatic
import org.mockito.Mock
import org.mockito.testng.MockitoTestNGListener
import org.testng.annotations.Listeners
import org.testng.annotations.Test

import static org.mockito.Mockito.when

@CompileStatic
@Listeners(MockitoTestNGListener.class)
class MyClassTest {

    @Mock
    private FooService mockFooService
    @Mock
    private BarService mockBarService

    @Test
    void testGetFooAndBar1() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockBarService, "defaultBarId")

        // Configure FooService.getFoo1(...).
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")
        when(mockFooService.getFoo1("id")).thenReturn(fooData)

        // Configure BarService.getDefaultBar1(...).
        def barData = new BarData()
        barData.setId("id")
        barData.setName("name")
        when(mockBarService.getDefaultBar1("defaultBarId")).thenReturn(barData)

        // Run the test
        def result = myClassUnderTest.getFooAndBar1("id")

        // Verify the results
    }
}
