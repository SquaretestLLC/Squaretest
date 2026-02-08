package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock

import static org.assertj.core.api.Assertions.assertThatThrownBy
import static org.mockito.BDDMockito.given
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
        given(mockFooService.getData(0L)).willReturn(Optional.of(new FooData(0L, "name")))

        // Run the test
        def result = myClassUnderTest.getData(0L)

        // Verify the results
    }

    @Test
    void testGetData_FooServiceReturnsAbsent() {
        // Setup
        given(mockFooService.getData(0L)).willReturn(Optional.empty())

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.getData(0L)
        }).isInstanceOf(FooException.class)
    }
}
