package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass(new FinalFoo(), new MyClass.StaticFoo(), EnumFoo.RED,
                new Object[]{"someObjects"}, 0, "someOtherObjects");
    }

    @Test
    void testPerformWithStaticFoo1() {
        // Setup
        final MyClass.StaticFoo listener = new MyClass.StaticFoo();

        // Run the test
        myClassUnderTest.performWithStaticFoo(listener);

        // Verify the results
    }

    @Test
    void testPerformWithFinalFoo1() {
        // Setup
        final FinalFoo finalFoo = new FinalFoo();

        // Run the test
        myClassUnderTest.performWithFinalFoo(finalFoo);

        // Verify the results
    }

    @Test
    void testPerformWithListener1() {
        // Setup
        final FinalFoo finalFooListener = new FinalFoo();

        // Run the test
        myClassUnderTest.performWithListener(finalFooListener);

        // Verify the results
    }

    @Test
    void testPerformWithStaticListener1() {
        // Setup
        final MyClass.StaticFoo staticFooListener = new MyClass.StaticFoo();

        // Run the test
        myClassUnderTest.performWithStaticListener(staticFooListener);

        // Verify the results
    }

    @Test
    void testPerformWithCallback1() {
        // Setup
        final FinalFoo finalFooCallback = new FinalFoo();

        // Run the test
        myClassUnderTest.performWithCallback(finalFooCallback);

        // Verify the results
    }

    @Test
    void testPerformWithArrayListener1() {
        // Setup
        // Run the test
        myClassUnderTest.performWithArrayListener(new Object[]{"listener"});

        // Verify the results
    }

    @Test
    void testPerformWithArbitraryListeners1() {
        // Setup
        // Run the test
        myClassUnderTest.performWithArbitraryListeners("listeners");

        // Verify the results
    }
}