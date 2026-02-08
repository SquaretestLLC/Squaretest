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
    private FooService mockFooService1

    @BeforeEach
    void setUp() {
        initMocks(this)
    }

    @Test
    void testGetFoo1() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        // TODO: Set the following fields: fooService.
        // Configure FooService.getFoo1(...).
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")
        when(mockFooService1.getFoo1("id")).thenReturn(fooData)

        // Run the test
        def result = myClassUnderTest.getFoo1("id")

        // Verify the results
    }
}
