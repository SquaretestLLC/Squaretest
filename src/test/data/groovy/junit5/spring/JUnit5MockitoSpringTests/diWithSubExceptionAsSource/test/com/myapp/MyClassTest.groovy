package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock

import static org.junit.jupiter.api.Assertions.assertThrows
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
    void testGetFooData() {
        // Setup
        when(mockFooService.getFooData(0L)).thenReturn(new FooData("name", 0L))

        // Run the test
        def result = myClassUnderTest.getFooData(0L)

        // Verify the results
    }

    @Test
    void testGetFooData_FooServiceThrowsFileNotFoundException() {
        // Setup
        when(mockFooService.getFooData(0L)).thenThrow(FileNotFoundException.class)

        // Run the test
        assertThrows(FileNotFoundException.class, {
            myClassUnderTest.getFooData(0L)
        })
    }
}
