package com.myapp

import groovy.transform.CompileStatic
import io.reactivex.Observable
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock

import static org.assertj.core.api.Assertions.assertThatThrownBy
import static org.mockito.Mockito.verify
import static org.mockito.Mockito.when
import static org.mockito.MockitoAnnotations.openMocks

@CompileStatic
class MyClassTest {

    @Mock
    private Foo mockFoo

    private MyClass myClassUnderTest

    private AutoCloseable mockitoCloseable

    @BeforeEach
    void setUp() {
        mockitoCloseable = openMocks(this)
        myClassUnderTest = new MyClass(mockFoo)
    }

    @AfterEach
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

    @Test
    void testDoSomething_FooCapWithOptionalReturnsAbsent() {
        // Setup
        when(mockFoo.capWithOptional("theStr")).thenReturn(Optional.empty())

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.doSomething()
        }).isInstanceOf(NoSuchElementException.class)
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
