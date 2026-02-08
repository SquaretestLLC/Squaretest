package com.myapp;

import io.reactivex.Observable;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@RunWith(MockitoJUnitRunner.class)
public class MyClassTest {

    @Mock
    private Foo mockFoo;

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass(mockFoo);
    }

    @Test
    public void testDoSomething() {
        // Setup
        given(mockFoo.capWithOptional("theStr")).willReturn(Optional.of(new InnerFoo("myStr")));
        given(mockFoo.capWithObservable("theStr")).willReturn(Observable.just(new InnerFoo("myStr")));

        // Run the test
        myClassUnderTest.doSomething();

        // Verify the results
        then(mockFoo).should().capWithFuture("theStr");
        then(mockFoo).should().capWithCompletable("theStr");
    }

    @Test(expected = NoSuchElementException.class)
    public void testDoSomething_FooCapWithOptionalReturnsAbsent() {
        // Setup
        given(mockFoo.capWithOptional("theStr")).willReturn(Optional.empty());

        // Run the test
        myClassUnderTest.doSomething();
    }

    @Test
    public void testDoSomething_FooCapWithObservableReturnsNoItem() {
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
    public void testDoSomething_FooCapWithObservableReturnsError() {
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
