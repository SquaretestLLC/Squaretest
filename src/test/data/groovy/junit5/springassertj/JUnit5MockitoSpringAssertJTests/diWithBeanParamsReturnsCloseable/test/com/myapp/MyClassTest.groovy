package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock

import static org.mockito.Mockito.*
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
    void testDoSomething1() {
        // Setup
        // Configure FooService.getCloseable1(...).
        def mockSpecialCloseable = mock(SpecialCloseable.class)
        def fooParams = new FooParams()
        fooParams.setId("id")
        fooParams.setName("name")
        when(mockFooService.getCloseable1(fooParams)).thenReturn(mockSpecialCloseable)

        // Run the test
        myClassUnderTest.doSomething1()

        // Verify the results
        verify(mockSpecialCloseable).close()
    }

    @Test
    void testDoSomething2() {
        // Setup
        // Configure FooService.getCloseable2(...).
        def mockSpecialCloseable = mock(SpecialCloseable.class)
        when(mockFooService.getCloseable2()).thenReturn(mockSpecialCloseable)

        // Run the test
        myClassUnderTest.doSomething2()

        // Verify the results
        verify(mockSpecialCloseable).close()
    }

    @Test
    void testDoSomething3() {
        // Setup
        // Configure FooService.getCloseable3(...).
        def fooParams = new FooParams()
        fooParams.setId("id")
        fooParams.setName("name")
        def spyCloseableWithConstructor = spy(new CloseableWithConstructor("content", fooParams))
        def fooParams1 = new FooParams()
        fooParams1.setId("id")
        fooParams1.setName("name")
        when(mockFooService.getCloseable3(fooParams1)).thenReturn(spyCloseableWithConstructor)

        // Run the test
        myClassUnderTest.doSomething3()

        // Verify the results
        verify(spyCloseableWithConstructor).close()
    }

    @Test
    void testDoSomething4() {
        // Setup
        // Configure FooService.getCloseable4(...).
        def fooParams = new FooParams()
        fooParams.setId("id")
        fooParams.setName("name")
        def spyCloseableWithConstructor = spy(new CloseableWithConstructor("content", fooParams))
        when(mockFooService.getCloseable4()).thenReturn(spyCloseableWithConstructor)

        // Run the test
        myClassUnderTest.doSomething4()

        // Verify the results
        verify(spyCloseableWithConstructor).close()
    }

    @Test
    void testDoSomething5() {
        // Setup
        // Configure FooService.getCloseable1(...).
        def mockSpecialCloseable = mock(SpecialCloseable.class)
        def fooParams = new FooParams()
        fooParams.setId("id")
        fooParams.setName("name")
        when(mockFooService.getCloseable1(fooParams)).thenReturn(mockSpecialCloseable)

        // Run the test
        myClassUnderTest.doSomething5()

        // Verify the results
        verify(mockSpecialCloseable).close()
    }

    @Test
    void testDoSomething6() {
        // Setup
        // Configure FooService.getCloseable2(...).
        def mockSpecialCloseable = mock(SpecialCloseable.class)
        when(mockFooService.getCloseable2()).thenReturn(mockSpecialCloseable)

        // Run the test
        myClassUnderTest.doSomething6()

        // Verify the results
        verify(mockSpecialCloseable).close()
    }

    @Test
    void testDoSomething7() {
        // Setup
        // Configure FooService.getCloseable3(...).
        def fooParams = new FooParams()
        fooParams.setId("id")
        fooParams.setName("name")
        def spyCloseableWithConstructor = spy(new CloseableWithConstructor("content", fooParams))
        def fooParams1 = new FooParams()
        fooParams1.setId("id")
        fooParams1.setName("name")
        when(mockFooService.getCloseable3(fooParams1)).thenReturn(spyCloseableWithConstructor)

        // Run the test
        myClassUnderTest.doSomething7()

        // Verify the results
        verify(spyCloseableWithConstructor).close()
    }

    @Test
    void testDoSomething8() {
        // Setup
        // Configure FooService.getCloseable4(...).
        def fooParams = new FooParams()
        fooParams.setId("id")
        fooParams.setName("name")
        def spyCloseableWithConstructor = spy(new CloseableWithConstructor("content", fooParams))
        when(mockFooService.getCloseable4()).thenReturn(spyCloseableWithConstructor)

        // Run the test
        myClassUnderTest.doSomething8()

        // Verify the results
        verify(spyCloseableWithConstructor).close()
    }

    @Test
    void testDoSomething9() {
        // Setup
        // Configure FooService.getCloseable1(...).
        def mockSpecialCloseable = mock(SpecialCloseable.class)
        def fooParams = new FooParams()
        fooParams.setId("id")
        fooParams.setName("name")
        when(mockFooService.getCloseable1(fooParams)).thenReturn(mockSpecialCloseable)

        // Run the test
        def result = myClassUnderTest.doSomething9()

        // Verify the results
        verify(mockSpecialCloseable).close()
    }

    @Test
    void testDoSomething10() {
        // Setup
        // Configure FooService.getCloseable2(...).
        def mockSpecialCloseable = mock(SpecialCloseable.class)
        when(mockFooService.getCloseable2()).thenReturn(mockSpecialCloseable)

        // Run the test
        def result = myClassUnderTest.doSomething10()

        // Verify the results
        verify(mockSpecialCloseable).close()
    }

    @Test
    void testDoSomething11() {
        // Setup
        // Configure FooService.getCloseable3(...).
        def fooParams = new FooParams()
        fooParams.setId("id")
        fooParams.setName("name")
        def spyCloseableWithConstructor = spy(new CloseableWithConstructor("content", fooParams))
        def fooParams1 = new FooParams()
        fooParams1.setId("id")
        fooParams1.setName("name")
        when(mockFooService.getCloseable3(fooParams1)).thenReturn(spyCloseableWithConstructor)

        // Run the test
        def result = myClassUnderTest.doSomething11()

        // Verify the results
        verify(spyCloseableWithConstructor).close()
    }

    @Test
    void testDoSomething12() {
        // Setup
        // Configure FooService.getCloseable4(...).
        def fooParams = new FooParams()
        fooParams.setId("id")
        fooParams.setName("name")
        def spyCloseableWithConstructor = spy(new CloseableWithConstructor("content", fooParams))
        when(mockFooService.getCloseable4()).thenReturn(spyCloseableWithConstructor)

        // Run the test
        def result = myClassUnderTest.doSomething12()

        // Verify the results
        verify(spyCloseableWithConstructor).close()
    }
}
