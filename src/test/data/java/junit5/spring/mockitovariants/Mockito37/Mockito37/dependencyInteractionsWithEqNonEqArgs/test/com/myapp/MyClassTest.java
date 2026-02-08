package com.myapp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

class MyClassTest {

    @Mock
    private Foo mockFoo;

    private MyClass myClassUnderTest;

    private AutoCloseable mockitoCloseable;

    @BeforeEach
    void setUp() {
        mockitoCloseable = openMocks(this);
        myClassUnderTest = new MyClass(mockFoo);
    }

    @AfterEach
    void tearDown() throws Exception {
        mockitoCloseable.close();
    }

    @Test
    void testCallAllDIs() {
        // Setup
        doAnswer(invocation -> {
            ((Runnable) invocation.getArguments()[0]).run();
            return null;
        }).when(mockFoo).useRunnableAndEqualsArg(any(Runnable.class), eq(new Foo.BarWithEquals()));
        doAnswer(invocation -> {
            ((Runnable) invocation.getArguments()[0]).run();
            return null;
        }).when(mockFoo).useRunnableAndNonEqualsArg(any(Runnable.class), any(Foo.Bar.class));

        // Run the test
        final String result = myClassUnderTest.callAllDIs("arg");

        // Verify the results
        assertEquals("theValue", result);
        verify(mockFoo).doSomethingWithPrimitiveArgs(0, 1);
        verify(mockFoo).useNonEqualsOverridingArgs(any(Foo.Bar.class));
        verify(mockFoo).useNonEqualsOverridingArgsWithNormalArgs(any(Foo.Bar.class), eq("asdf"), eq(1));
        verify(mockFoo).useEqualsOverridingArg(new Foo.BarWithEquals());
        verify(mockFoo).useEqualsOverridingArgSubtype(new Foo.SubBarWithEquals());
        verify(mockFoo).useRunnableAndEqualsArg(any(Runnable.class), eq(new Foo.BarWithEquals()));
        verify(mockFoo).useRunnableAndNonEqualsArg(any(Runnable.class), any(Foo.Bar.class));
    }
}
