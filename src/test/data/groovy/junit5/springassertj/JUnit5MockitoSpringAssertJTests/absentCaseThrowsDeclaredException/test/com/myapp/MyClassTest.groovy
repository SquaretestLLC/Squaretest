package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock

import static org.assertj.core.api.Assertions.assertThatThrownBy
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
    void testGetData() {
        // Setup
        when(mockFooService.getData(0L)).thenReturn(Optional.of(new FooData(0L, "name")))

        // Run the test
        def result = myClassUnderTest.getData(0L)

        // Verify the results
    }

    @Test
    void testGetData_FooServiceReturnsAbsent() {
        // Setup
        when(mockFooService.getData(0L)).thenReturn(Optional.empty())

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.getData(0L)
        }).isInstanceOf(FooException.class)
    }
}
