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

    @BeforeEach
    void setUp() {
        initMocks(this)
    }

    @Test
    void testGetFooData1() {
        // Setup
        def theClass = new MyClass(mockFooService)
        when(mockFooService.getFooData("id")).thenReturn("result")

        // Run the test
        def result = theClass.getFooData("id")

        // Verify the results
        assert "result" == result
    }
}