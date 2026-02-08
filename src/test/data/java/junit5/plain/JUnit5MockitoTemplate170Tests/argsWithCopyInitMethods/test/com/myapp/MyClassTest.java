package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass();
    }

    @Test
    void testProcessFoo0() throws Exception {
        // Setup
        final FooWithFutureConstructor fooWithFutureConstructor = new FooWithFutureConstructor(
                CompletableFuture.completedFuture(null));

        // Run the test
        myClassUnderTest.processFoo0(fooWithFutureConstructor);

        // Verify the results
    }

    @Test
    void testProcessFoo1() {
        // Setup
        // Run the test
        myClassUnderTest.processFoo1(null);

        // Verify the results
    }

    @Test
    void testProcessFoo2() {
        // Setup
        final FooWithMultipleConstrucotrs foo = new FooWithMultipleConstrucotrs("str");

        // Run the test
        myClassUnderTest.processFoo2(foo);

        // Verify the results
    }

    @Test
    void testProcessFoo4() {
        // Setup
        // Run the test
        myClassUnderTest.processFoo4(null);

        // Verify the results
    }

    @Test
    void testProcessFoo3() {
        // Setup
        final FooWithMultipleStaticInitMethods foo = FooWithMultipleStaticInitMethods.fromStr("str");

        // Run the test
        myClassUnderTest.processFoo3(foo);

        // Verify the results
    }
}
