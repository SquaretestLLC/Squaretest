package com.myapp;

import io.reactivex.Observable;
import org.mockito.Mock;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class MyClassTest {

    @Mock
    private Foo mockFoo;

    private MyClass myClassUnderTest;

    private AutoCloseable mockitoCloseable;

    @BeforeMethod
    public void setUp() {
        mockitoCloseable = openMocks(this);
        myClassUnderTest = new MyClass(mockFoo);
    }

    @AfterMethod
    public void tearDown() throws Exception {
        mockitoCloseable.close();
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

    @Test(expectedExceptions = {NoSuchElementException.class})
    public void testDoSomething_FooCapWithOptionalReturnsAbsent() {
        // Setup
        when(mockFoo.capWithOptional("theStr")).thenReturn(Optional.empty());

        // Run the test
        myClassUnderTest.doSomething();
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
