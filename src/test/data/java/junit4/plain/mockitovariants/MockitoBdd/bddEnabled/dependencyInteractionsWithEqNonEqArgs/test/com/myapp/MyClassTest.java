package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willAnswer;

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
