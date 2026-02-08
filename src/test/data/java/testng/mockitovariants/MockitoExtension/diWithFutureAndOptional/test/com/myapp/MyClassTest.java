package com.myapp;

import io.reactivex.Observable;
import org.mockito.Mock;
import org.mockito.testng.MockitoTestNGListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertThrows;

@Listeners(MockitoTestNGListener.class)
public class MyClassTest {

    @Mock
    private Foo mockFoo;

    private MyClass myClassUnderTest;

    @BeforeMethod
    public void setUp() {
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
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.doSomething());
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
