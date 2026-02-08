package com.myapp;

import io.reactivex.Observable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private Foo mockFoo;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFoo);
    }

    @Test
    void testDoSomething() {
        // Setup
        given(mockFoo.capWithOptional("theStr")).willReturn(Optional.of(new InnerFoo("myStr")));
        given(mockFoo.capWithObservable("theStr")).willReturn(Observable.just(new InnerFoo("myStr")));

        // Run the test
        myClassUnderTest.doSomething();

        // Verify the results
        then(mockFoo).should().capWithFuture("theStr");
        then(mockFoo).should().capWithCompletable("theStr");
    }

    @Test
    void testDoSomething_FooCapWithOptionalReturnsAbsent() {
        // Setup
        given(mockFoo.capWithOptional("theStr")).willReturn(Optional.empty());

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.doSomething());
    }

    @Test
    void testDoSomething_FooCapWithObservableReturnsNoItem() {
        // Setup
        given(mockFoo.capWithOptional("theStr")).willReturn(Optional.of(new InnerFoo("myStr")));
        given(mockFoo.capWithObservable("theStr")).willReturn(Observable.empty());

        // Run the test
        myClassUnderTest.doSomething();

        // Verify the results
        then(mockFoo).should().capWithFuture("theStr");
        then(mockFoo).should().capWithCompletable("theStr");
    }

    @Test
    void testDoSomething_FooCapWithObservableReturnsError() {
        // Setup
        given(mockFoo.capWithOptional("theStr")).willReturn(Optional.of(new InnerFoo("myStr")));
        given(mockFoo.capWithObservable("theStr")).willReturn(Observable.error(new Exception("message")));

        // Run the test
        myClassUnderTest.doSomething();

        // Verify the results
        then(mockFoo).should().capWithFuture("theStr");
        then(mockFoo).should().capWithCompletable("theStr");
    }
}
