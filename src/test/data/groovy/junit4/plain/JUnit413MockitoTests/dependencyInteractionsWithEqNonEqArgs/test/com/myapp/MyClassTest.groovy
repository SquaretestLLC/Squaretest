package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.invocation.InvocationOnMock
import org.mockito.junit.MockitoJUnitRunner

import static org.mockito.ArgumentMatchers.any
import static org.mockito.ArgumentMatchers.eq
import static org.mockito.Mockito.doAnswer
import static org.mockito.Mockito.verify

@CompileStatic
@RunWith(MockitoJUnitRunner.class)
class MyClassTest {

    @Mock
    private Foo mockFoo

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass(mockFoo)
    }

    @Test
    void testCallAllDIs() {
        // Setup
        doAnswer({ InvocationOnMock invocation ->
            ((Runnable) invocation.getArguments()[0]).run()
            return null
        }).when(mockFoo).useRunnableAndEqualsArg(any(Runnable.class), eq(new Foo.BarWithEquals()))
        doAnswer({ InvocationOnMock invocation ->
            ((Runnable) invocation.getArguments()[0]).run()
            return null
        }).when(mockFoo).useRunnableAndNonEqualsArg(any(Runnable.class), any(Foo.Bar.class))

        // Run the test
        def result = myClassUnderTest.callAllDIs("arg")

        // Verify the results
        assert "theValue" == result
        verify(mockFoo).doSomethingWithPrimitiveArgs(0, 1)
        verify(mockFoo).useNonEqualsOverridingArgs(any(Foo.Bar.class))
        verify(mockFoo).useNonEqualsOverridingArgsWithNormalArgs(any(Foo.Bar.class), eq("asdf"), eq(1))
        verify(mockFoo).useEqualsOverridingArg(new Foo.BarWithEquals())
        verify(mockFoo).useEqualsOverridingArgSubtype(new Foo.SubBarWithEquals())
        verify(mockFoo).useRunnableAndEqualsArg(any(Runnable.class), eq(new Foo.BarWithEquals()))
        verify(mockFoo).useRunnableAndNonEqualsArg(any(Runnable.class), any(Foo.Bar.class))
    }
}
