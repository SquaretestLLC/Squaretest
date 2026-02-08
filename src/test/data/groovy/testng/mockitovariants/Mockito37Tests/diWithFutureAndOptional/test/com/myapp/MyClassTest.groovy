package com.myapp

import groovy.transform.CompileStatic
import io.reactivex.Observable
import org.mockito.Mock
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import static org.mockito.Mockito.verify
import static org.mockito.Mockito.when
import static org.mockito.MockitoAnnotations.openMocks

@CompileStatic
class MyClassTest {

    @Mock
    private Foo mockFoo

    private MyClass myClassUnderTest

    private AutoCloseable mockitoCloseable

    @BeforeMethod
    void setUp() {
        mockitoCloseable = openMocks(this)
        myClassUnderTest = new MyClass(mockFoo)
    }

    @AfterMethod
    void tearDown() {
        mockitoCloseable.close()
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

    @Test(expectedExceptions = [NoSuchElementException.class])
    void testDoSomething_FooCapWithOptionalReturnsAbsent() {
        // Setup
        when(mockFoo.capWithOptional("theStr")).thenReturn(Optional.empty())

        // Run the test
        myClassUnderTest.doSomething()
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
