package com.myapp

import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test
import org.mockito.Mock

import static org.mockito.Mockito.when
import static org.mockito.MockitoAnnotations.initMocks

class MyClassTest {

    @Mock
    private FooService theMockFooService
    @Mock
    private BarService theMockBarService

    private MyClass myClassUnderTest

    @BeforeMethod
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass()
        myClassUnderTest.setFooService(theMockFooService)
        myClassUnderTest.setBarService(theMockBarService)
        myClassUnderTest.setDefaultBarId("defaultBarId")
    }

    @Test
    void testGetFooAndBar1() {
        // Setup
        // Configure FooService.getFoo1(...).
        final FooData fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")
        when(theMockFooService.getFoo1("id")).thenReturn(fooData)

        // Configure BarService.getDefaultBar1(...).
        final BarData barData = new BarData()
        barData.setId("id")
        barData.setName("name")
        when(theMockBarService.getDefaultBar1("defaultBarId")).thenReturn(barData)

        // Run the test
        final FooAndBar result = myClassUnderTest.getFooAndBar1("id")

        // Verify the results
    }
}
