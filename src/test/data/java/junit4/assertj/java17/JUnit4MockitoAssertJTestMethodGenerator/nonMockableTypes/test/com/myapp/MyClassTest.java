package com.myapp;

import org.junit.Before;
import org.junit.Test;

public class MyClassTest {

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass(new FinalFoo(), new MyClass.StaticFoo(), EnumFoo.RED,
                new Object[]{"someObjects"}, 0, "someOtherObjects");
    }

    @Test
    public void testPerformWithStaticFoo1() {
        // Setup
        final MyClass.StaticFoo listener = new MyClass.StaticFoo();

        // Run the test
        myClassUnderTest.performWithStaticFoo(listener);

        // Verify the results
    }

    @Test
    public void testPerformWithFinalFoo1() {
        // Setup
        final FinalFoo finalFoo = new FinalFoo();

        // Run the test
        myClassUnderTest.performWithFinalFoo(finalFoo);

        // Verify the results
    }

    @Test
    public void testPerformWithListener1() {
        // Setup
        final FinalFoo finalFooListener = new FinalFoo();

        // Run the test
        myClassUnderTest.performWithListener(finalFooListener);

        // Verify the results
    }

    @Test
    public void testPerformWithStaticListener1() {
        // Setup
        final MyClass.StaticFoo staticFooListener = new MyClass.StaticFoo();

        // Run the test
        myClassUnderTest.performWithStaticListener(staticFooListener);

        // Verify the results
    }

    @Test
    public void testPerformWithCallback1() {
        // Setup
        final FinalFoo finalFooCallback = new FinalFoo();

        // Run the test
        myClassUnderTest.performWithCallback(finalFooCallback);

        // Verify the results
    }

    @Test
    public void testPerformWithArrayListener1() {
        // Setup
        // Run the test
        myClassUnderTest.performWithArrayListener(new Object[]{"listener"});

        // Verify the results
    }

    @Test
    public void testPerformWithArbitraryListeners1() {
        // Setup
        // Run the test
        myClassUnderTest.performWithArbitraryListeners("listeners");

        // Verify the results
    }
}