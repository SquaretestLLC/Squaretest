package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock

import static org.mockito.Mockito.when
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
class MyClassTest {

    @Mock
    private FooService mockFooService
    @Mock
    private BarService mockBarService

    @InjectMocks
    private MyClass myClassUnderTest

    @BeforeEach
    void setUp() {
        initMocks(this)
        // TODO: Set the following fields: configBean, defaultFooId, specialValuesMap.
    }

    @Test
    void testGetFooAndBar1() {
        // Setup
        // Configure FooService.getFoo1(...).
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")
        when(mockFooService.getFoo1("id")).thenReturn(fooData)

        // Configure BarService.getDefaultBar1(...).
        def barData = new BarData()
        barData.setId("id")
        barData.setName("name")
        when(mockBarService.getDefaultBar1("defaultFooId")).thenReturn(barData)

        // Run the test
        def result = myClassUnderTest.getFooAndBar1("id")

        // Verify the results
    }

    @Test
    void testGetDefaultFoo1() {
        // Setup
        // Configure FooService.getDefaultFoo1(...).
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")
        when(mockFooService.getDefaultFoo1("defaultFooId")).thenReturn(fooData)

        // Run the test
        def result = myClassUnderTest.getDefaultFoo1()

        // Verify the results
    }

    @Test
    void testGetSpecialFoo1() {
        // Setup
        // Configure FooService.getSpecialFoo1(...).
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")
        when(mockFooService.getSpecialFoo1("defaultFooId")).thenReturn(fooData)

        // Run the test
        def result = myClassUnderTest.getSpecialFoo1()

        // Verify the results
    }

    @Test
    void testGetSpecialFoo2() {
        // Setup
        // Configure FooService.getSpecialFoo2(...).
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")
        when(mockFooService.getSpecialFoo2("specialPath")).thenReturn(fooData)

        // Run the test
        def result = myClassUnderTest.getSpecialFoo2()

        // Verify the results
    }
}
