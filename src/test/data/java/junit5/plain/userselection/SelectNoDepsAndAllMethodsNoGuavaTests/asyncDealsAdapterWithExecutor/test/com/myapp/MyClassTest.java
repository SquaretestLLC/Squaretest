package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.CompletableFuture;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass(new DealsServiceClient(), Runnable::run);
    }

    @Test
    void testGetDeals() {
        // Setup
        // Run the test
        final CompletableFuture<List<DisplayDeal>> result = myClassUnderTest.getDeals("userId");

        // Verify the results
    }
}
