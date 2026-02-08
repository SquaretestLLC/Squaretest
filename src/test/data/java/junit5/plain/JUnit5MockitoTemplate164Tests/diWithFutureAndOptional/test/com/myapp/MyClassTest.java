package com.myapp;

import io.reactivex.Observable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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
        when(mockFoo.capWithOptional("theStr")).thenReturn(Optional.of(new InnerFoo("myStr")));
        when(mockFoo.capWithObservable("theStr")).thenReturn(Observable.just(new InnerFoo("myStr")));

        // Configure Foo.capWithFuture(...).
        final Future<InnerFoo> innerFooFuture = CompletableFuture.completedFuture(new InnerFoo("myStr"));
        when(mockFoo.capWithFuture("theStr")).thenReturn(innerFooFuture);

        // Configure Foo.capWithCompletable(...).
        final CompletableFuture<InnerFoo> innerFooCompletableFuture = CompletableFuture.completedFuture(
                new InnerFoo("myStr"));
        when(mockFoo.capWithCompletable("theStr")).thenReturn(innerFooCompletableFuture);

        // Run the test
        myClassUnderTest.doSomething();

        // Verify the results
        verify(mockFoo).capWithFuture("theStr");
        verify(mockFoo).capWithCompletable("theStr");
    }

    @Test
    void testDoSomething_FooCapWithOptionalReturnsAbsent() {
        // Setup
        when(mockFoo.capWithOptional("theStr")).thenReturn(Optional.empty());
        when(mockFoo.capWithObservable("theStr")).thenReturn(Observable.just(new InnerFoo("myStr")));

        // Configure Foo.capWithFuture(...).
        final Future<InnerFoo> innerFooFuture = CompletableFuture.completedFuture(new InnerFoo("myStr"));
        when(mockFoo.capWithFuture("theStr")).thenReturn(innerFooFuture);

        // Configure Foo.capWithCompletable(...).
        final CompletableFuture<InnerFoo> innerFooCompletableFuture = CompletableFuture.completedFuture(
                new InnerFoo("myStr"));
        when(mockFoo.capWithCompletable("theStr")).thenReturn(innerFooCompletableFuture);

        // Run the test
        myClassUnderTest.doSomething();

        // Verify the results
        verify(mockFoo).capWithFuture("theStr");
        verify(mockFoo).capWithCompletable("theStr");
    }

    @Test
    void testDoSomething_FooCapWithObservableReturnsNoItem() {
        // Setup
        when(mockFoo.capWithOptional("theStr")).thenReturn(Optional.of(new InnerFoo("myStr")));
        when(mockFoo.capWithObservable("theStr")).thenReturn(Observable.empty());

        // Configure Foo.capWithFuture(...).
        final Future<InnerFoo> innerFooFuture = CompletableFuture.completedFuture(new InnerFoo("myStr"));
        when(mockFoo.capWithFuture("theStr")).thenReturn(innerFooFuture);

        // Configure Foo.capWithCompletable(...).
        final CompletableFuture<InnerFoo> innerFooCompletableFuture = CompletableFuture.completedFuture(
                new InnerFoo("myStr"));
        when(mockFoo.capWithCompletable("theStr")).thenReturn(innerFooCompletableFuture);

        // Run the test
        myClassUnderTest.doSomething();

        // Verify the results
        verify(mockFoo).capWithFuture("theStr");
        verify(mockFoo).capWithCompletable("theStr");
    }

    @Test
    void testDoSomething_FooCapWithObservableReturnsError() {
        // Setup
        when(mockFoo.capWithOptional("theStr")).thenReturn(Optional.of(new InnerFoo("myStr")));
        when(mockFoo.capWithObservable("theStr")).thenReturn(Observable.error(new Exception("message")));

        // Run the test
        myClassUnderTest.doSomething();

        // Verify the results
    }

    @Test
    void testDoSomething_FooCapWithFutureReturnsFailure() {
        // Setup
        when(mockFoo.capWithOptional("theStr")).thenReturn(Optional.of(new InnerFoo("myStr")));
        when(mockFoo.capWithObservable("theStr")).thenReturn(Observable.just(new InnerFoo("myStr")));

        // Configure Foo.capWithFuture(...).
        final CompletableFuture<InnerFoo> innerFooFuture = new CompletableFuture<>();
        innerFooFuture.completeExceptionally(new Exception("message"));
        when(mockFoo.capWithFuture("theStr")).thenReturn(innerFooFuture);

        // Run the test
        myClassUnderTest.doSomething();

        // Verify the results
        verify(mockFoo).capWithFuture("theStr");
    }

    @Test
    void testDoSomething_FooCapWithCompletableReturnsFailure() {
        // Setup
        when(mockFoo.capWithOptional("theStr")).thenReturn(Optional.of(new InnerFoo("myStr")));
        when(mockFoo.capWithObservable("theStr")).thenReturn(Observable.just(new InnerFoo("myStr")));

        // Configure Foo.capWithFuture(...).
        final Future<InnerFoo> innerFooFuture = CompletableFuture.completedFuture(new InnerFoo("myStr"));
        when(mockFoo.capWithFuture("theStr")).thenReturn(innerFooFuture);

        // Configure Foo.capWithCompletable(...).
        final CompletableFuture<InnerFoo> innerFooCompletableFuture = new CompletableFuture<>();
        innerFooCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockFoo.capWithCompletable("theStr")).thenReturn(innerFooCompletableFuture);

        // Run the test
        myClassUnderTest.doSomething();

        // Verify the results
        verify(mockFoo).capWithFuture("theStr");
        verify(mockFoo).capWithCompletable("theStr");
    }
}
