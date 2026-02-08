package com.myapp

import groovy.transform.CompileStatic
import org.mockito.Mock
import org.mockito.testng.MockitoTestNGListener
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Listeners
import org.testng.annotations.Test

import static org.mockito.Mockito.when

@CompileStatic
@Listeners(MockitoTestNGListener.class)
class MyClassTest {

    @Mock
    private FooService mockFooService
    @Mock
    private MyClass mockOldDataStore

    private MyClass myClassUnderTest

    @BeforeMethod
    void setUp() {
        myClassUnderTest = new MyClass(mockFooService, mockOldDataStore)
    }

    @Test
    void testGetFooById() {
        // Setup
        when(mockFooService.getFooById("id")).thenReturn(new Foo("id", "name"))

        // Run the test
        def result = myClassUnderTest.getFooById("id")

        // Verify the results
    }

    @Test
    void testGetFooById_FooServiceReturnsNull() {
        // Setup
        when(mockFooService.getFooById("id")).thenReturn(null)
        when(mockOldDataStore.getFooById("id")).thenReturn(new Foo("id", "name"))

        // Run the test
        def result = myClassUnderTest.getFooById("id")

        // Verify the results
    }
}
