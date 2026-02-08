package com.myapp

import groovy.transform.CompileStatic
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

import static org.assertj.core.api.Assertions.assertThatThrownBy
import static org.mockito.BDDMockito.given
import static org.mockito.BDDMockito.then

@RunWith(MockitoJUnitRunner.class)
@CompileStatic
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
        given(mockFoo.capWithOptional("theStr")).willReturn(Optional.of(new InnerFoo("myStr")))
        given(mockFoo.capWithObservable("theStr")).willReturn(Observable.just(new InnerFoo("myStr")))

        // Run the test
        myClassUnderTest.doSomething()

        // Verify the results
        then(mockFoo).should().capWithFuture("theStr")
        then(mockFoo).should().capWithCompletable("theStr")
    }

    @Test
    void testDoSomething_FooCapWithOptionalReturnsAbsent() {
        // Setup
        given(mockFoo.capWithOptional("theStr")).willReturn(Optional.empty())

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.doSomething()
        }).isInstanceOf(NoSuchElementException.class)
    }

    @Test
    void testDoSomething_FooCapWithObservableReturnsNoItem() {
        // Setup
        given(mockFoo.capWithOptional("theStr")).willReturn(Optional.of(new InnerFoo("myStr")))
        given(mockFoo.capWithObservable("theStr")).willReturn(Observable.empty())

        // Run the test
        myClassUnderTest.doSomething()

        // Verify the results
        then(mockFoo).should().capWithFuture("theStr")
        then(mockFoo).should().capWithCompletable("theStr")
    }

    @Test
    void testDoSomething_FooCapWithObservableReturnsError() {
        // Setup
        given(mockFoo.capWithOptional("theStr")).willReturn(Optional.of(new InnerFoo("myStr")))
        given(mockFoo.capWithObservable("theStr")).willReturn(Observable.error(new Exception("message")))

        // Run the test
        myClassUnderTest.doSomething()

        // Verify the results
        then(mockFoo).should().capWithFuture("theStr")
        then(mockFoo).should().capWithCompletable("theStr")
    }
}
