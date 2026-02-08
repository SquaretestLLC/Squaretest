package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock

import static org.assertj.core.api.Assertions.assertThat
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
    void testGetFoo1() {
        // Setup
        def expectedResult = new FooData()
        expectedResult.setId("id")
        expectedResult.setName("name")

        // Configure FooService.getFoo1(...).
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")
        when(mockFooService.getFoo1("id")).thenReturn(fooData)

        // Run the test
        def result = myClassUnderTest.getFoo1("id")

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testGetFoo1_FooServiceThrowsNoSuchElementException() {
        // Setup
        when(mockFooService.getFoo1("id")).thenThrow(NoSuchElementException.class)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.getFoo1("id")
        }).isInstanceOf(NoSuchElementException.class)
    }

    @Test
    void testGetFoo2() {
        // Setup
        def expectedResult = new FooData()
        expectedResult.setId("id")
        expectedResult.setName("name")

        // Configure FooService.getFoo2(...).
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")
        when(mockFooService.getFoo2("id")).thenReturn(fooData)

        // Run the test
        def result = myClassUnderTest.getFoo2("id")

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testGetFoo2_FooServiceThrowsNoSuchElementException() {
        // Setup
        when(mockFooService.getFoo2("id")).thenThrow(NoSuchElementException.class)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.getFoo2("id")
        }).isInstanceOf(NoSuchElementException.class)
    }

    @Test
    void testGetFoo3() {
        // Setup
        def expectedResult = new FooData()
        expectedResult.setId("id")
        expectedResult.setName("name")

        // Configure FooService.getFoo2(...).
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")
        when(mockFooService.getFoo2("id")).thenReturn(fooData)

        // Run the test
        def result = myClassUnderTest.getFoo3("id")

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testGetFoo3_FooServiceThrowsNoSuchElementException() {
        // Setup
        when(mockFooService.getFoo2("id")).thenThrow(NoSuchElementException.class)

        // Run the test
        def result = myClassUnderTest.getFoo3("id")

        // Verify the results
        assertThat(result).isNull()
    }
}
