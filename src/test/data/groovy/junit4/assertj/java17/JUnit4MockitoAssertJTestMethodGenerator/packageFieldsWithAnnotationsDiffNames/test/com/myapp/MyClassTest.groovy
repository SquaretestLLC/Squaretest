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
    private FooService theMockFooService
    @Mock
    private BarService theMockBarService

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass()
        myClassUnderTest.fooService = theMockFooService
        myClassUnderTest.barService = theMockBarService
        myClassUnderTest.defaultBarId = "defaultBarId"
    }

    @Test
    void testGetFooAndBar11() {
        // Setup
        // Configure FooService.getFoo1(...).
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")
        when(theMockFooService.getFoo1("id")).thenReturn(fooData)

        // Configure BarService.getDefaultBar1(...).
        def barData = new BarData()
        barData.setId("id")
        barData.setName("name")
        when(theMockBarService.getDefaultBar1("defaultBarId")).thenReturn(barData)

        // Run the test
        def result = myClassUnderTest.getFooAndBar1("id")

        // Verify the results
    }
}