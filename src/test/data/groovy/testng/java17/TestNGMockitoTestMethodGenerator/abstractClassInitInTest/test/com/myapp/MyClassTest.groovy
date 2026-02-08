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
    private FooService mockFooService

    @BeforeMethod
    void setUp() {
        initMocks(this)
    }

    @Test
    void testGetFoo11() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService) {
            @Override
            public String getDefaultFooId() {
                return null
            }
        }

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
        def myClassUnderTest = new MyClass(mockFooService) {
            @Override
            public String getDefaultFooId() {
                return null
            }
        }

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