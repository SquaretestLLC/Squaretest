package com.myapp;

import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willAnswer;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.Assert.assertEquals;

public class MyClassTest {

    @Mock
    private Foo mockFoo;

    private MyClass myClassUnderTest;

    @BeforeMethod
    public void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFoo);
    }

    @Test
    public void testCallAllDIs() {
        // Setup
        willAnswer(invocation -> {
            ((Runnable) invocation.getArguments()[0]).run();
            return null;
        }).given(mockFoo).useRunnableAndEqualsArg(any(Runnable.class), eq(new Foo.BarWithEquals()));
        willAnswer(invocation -> {
            ((Runnable) invocation.getArguments()[0]).run();
            return null;
        }).given(mockFoo).useRunnableAndNonEqualsArg(any(Runnable.class), any(Foo.Bar.class));

        // Run the test
        final String result = myClassUnderTest.callAllDIs("arg");

        // Verify the results
        assertEquals("theValue", result);
        then(mockFoo).should().doSomethingWithPrimitiveArgs(0, 1);
        then(mockFoo).should().useNonEqualsOverridingArgs(any(Foo.Bar.class));
        then(mockFoo).should().useNonEqualsOverridingArgsWithNormalArgs(any(Foo.Bar.class), eq("asdf"), eq(1));
        then(mockFoo).should().useEqualsOverridingArg(new Foo.BarWithEquals());
        then(mockFoo).should().useEqualsOverridingArgSubtype(new Foo.SubBarWithEquals());
        then(mockFoo).should().useRunnableAndEqualsArg(any(Runnable.class), eq(new Foo.BarWithEquals()));
        then(mockFoo).should().useRunnableAndNonEqualsArg(any(Runnable.class), any(Foo.Bar.class));
    }
}
