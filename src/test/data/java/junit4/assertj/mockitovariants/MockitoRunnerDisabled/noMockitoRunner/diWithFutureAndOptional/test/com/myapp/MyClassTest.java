package com.myapp;

import io.reactivex.Observable;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class MyClassTest {

    @Mock
    private Foo mockFoo;

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFoo);
    }

    @Test
    public void testDoSomething() {
        // Setup
        when(mockFoo.capWithOptional("theStr")).thenReturn(Optional.of(new InnerFoo("myStr")));
        when(mockFoo.capWithObservable("theStr")).thenReturn(Observable.just(new InnerFoo("myStr")));

        // Run the test
        myClassUnderTest.doSomething();

        // Verify the results
        verify(mockFoo).capWithFuture("theStr");
        verify(mockFoo).capWithCompletable("theStr");
    }

    @Test
    public void testDoSomething_FooCapWithOptionalReturnsAbsent() {
        // Setup
        when(mockFoo.capWithOptional("theStr")).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.doSomething()).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    public void testDoSomething_FooCapWithObservableReturnsNoItem() {
        // Setup
        when(mockFoo.capWithOptional("theStr")).thenReturn(Optional.of(new InnerFoo("myStr")));
        when(mockFoo.capWithObservable("theStr")).thenReturn(Observable.empty());

        // Run the test
        myClassUnderTest.doSomething();

        // Verify the results
        verify(mockFoo).capWithFuture("theStr");
        verify(mockFoo).capWithCompletable("theStr");
    }

    @Test
    public void testDoSomething_FooCapWithObservableReturnsError() {
        // Setup
        when(mockFoo.capWithOptional("theStr")).thenReturn(Optional.of(new InnerFoo("myStr")));
        when(mockFoo.capWithObservable("theStr")).thenReturn(Observable.error(new Exception("message")));

        // Run the test
        myClassUnderTest.doSomething();

        // Verify the results
        verify(mockFoo).capWithFuture("theStr");
        verify(mockFoo).capWithCompletable("theStr");
    }
}
