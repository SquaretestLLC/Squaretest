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

    private MyClass myClassUnderTest

    @BeforeEach
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass(mockFooService)
    }

    @Test
    void testCreateInstance() {
        // Setup
        def fooService = null

        // Run the test
        def result = MyClass.createInstance(fooService)
        assert "result" == result.getFooData("id")
    }

    @Test
    void testGetFooData1() {
        // Setup
        when(mockFooService.getFooData("id")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.getFooData("id")

        // Verify the results
        assert "result" == result
    }
}