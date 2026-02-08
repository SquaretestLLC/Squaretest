package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock

import static org.mockito.Mockito.when
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
class MyClassTest {

    @Mock
    private FooService mockFooService
    @Mock
    private BarService mockBarService

    @BeforeEach
    void setUp() {
        initMocks(this)
    }

    @Test
    void testGetFooAndBar1() {
        // Setup
        def myClassUnderTest = new MyClass()
        myClassUnderTest.setFooService(mockFooService)
        myClassUnderTest.setBarService(mockBarService)
        myClassUnderTest.setDefaultBarId("defaultBarId")

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
