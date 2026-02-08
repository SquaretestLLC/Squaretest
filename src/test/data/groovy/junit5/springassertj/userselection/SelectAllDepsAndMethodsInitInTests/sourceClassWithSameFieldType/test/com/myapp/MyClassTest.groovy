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
    private MyClass mockOldDataStore

    @BeforeEach
    void setUp() {
        initMocks(this)
    }

    @Test
    void testGetFooById() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockOldDataStore)
        when(mockFooService.getFooById("id")).thenReturn(new Foo("id", "name"))

        // Run the test
        def result = myClassUnderTest.getFooById("id")

        // Verify the results
    }

    @Test
    void testGetFooById_FooServiceReturnsNull() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockOldDataStore)
        when(mockFooService.getFooById("id")).thenReturn(null)
        when(mockOldDataStore.getFooById("id")).thenReturn(new Foo("id", "name"))

        // Run the test
        def result = myClassUnderTest.getFooById("id")

        // Verify the results
    }
}
