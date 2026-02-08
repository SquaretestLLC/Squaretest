package com.myapp

import groovy.transform.CompileStatic
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

import static org.junit.Assert.assertThrows
import static org.mockito.Mockito.verify
import static org.mockito.Mockito.when

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
    void testDoSomething() {
        // Setup
        when(mockFoo.capWithOptional("theStr")).thenReturn(Optional.of(new InnerFoo("myStr")))
        when(mockFoo.capWithObservable("theStr")).thenReturn(Observable.just(new InnerFoo("myStr")))

        // Run the test
        myClassUnderTest.doSomething()

        // Verify the results
        verify(mockFoo).capWithFuture("theStr")
        verify(mockFoo).capWithCompletable("theStr")
    }

    @Test
    void testDoSomething_FooCapWithOptionalReturnsAbsent() {
        // Setup
        when(mockFoo.capWithOptional("theStr")).thenReturn(Optional.empty())

        // Run the test
        assertThrows(NoSuchElementException.class, {
            myClassUnderTest.doSomething()
        })
    }

    @Test
    void testDoSomething_FooCapWithObservableReturnsNoItem() {
        // Setup
        when(mockFoo.capWithOptional("theStr")).thenReturn(Optional.of(new InnerFoo("myStr")))
        when(mockFoo.capWithObservable("theStr")).thenReturn(Observable.empty())

        // Run the test
        myClassUnderTest.doSomething()

        // Verify the results
        verify(mockFoo).capWithFuture("theStr")
        verify(mockFoo).capWithCompletable("theStr")
    }

    @Test
    void testDoSomething_FooCapWithObservableReturnsError() {
        // Setup
        when(mockFoo.capWithOptional("theStr")).thenReturn(Optional.of(new InnerFoo("myStr")))
        when(mockFoo.capWithObservable("theStr")).thenReturn(Observable.error(new Exception("message")))

        // Run the test
        myClassUnderTest.doSomething()

        // Verify the results
        verify(mockFoo).capWithFuture("theStr")
        verify(mockFoo).capWithCompletable("theStr")
    }
}
