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
    private MyClass theMockOldDataStore

    private MyClass myClassUnderTest

    @BeforeMethod
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass(theMockFooService, theMockOldDataStore)
    }

    @Test
    void testGetFooById() {
        // Setup
        when(theMockFooService.getFooById("id")).thenReturn(new Foo("id", "name"))

        // Run the test
        final Foo result = myClassUnderTest.getFooById("id")

        // Verify the results
    }

    @Test
    void testGetFooById_FooServiceReturnsNull() {
        // Setup
        when(theMockFooService.getFooById("id")).thenReturn(null)
        when(theMockOldDataStore.getFooById("id")).thenReturn(new Foo("id", "name"))

        // Run the test
        final Foo result = myClassUnderTest.getFooById("id")

        // Verify the results
    }
}
