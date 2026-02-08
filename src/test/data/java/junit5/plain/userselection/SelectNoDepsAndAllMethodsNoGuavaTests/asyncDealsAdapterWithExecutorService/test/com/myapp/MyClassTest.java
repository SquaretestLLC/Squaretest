package com.myapp;

import com.myapp.asyncdealadapter1.DealsServiceClient;
import com.myapp.asyncdealadapter1.DisplayDeal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass(new DealsServiceClient(), Executors.newSingleThreadExecutor());
    }

    @Test
    void testGetDeals() {
        // Setup
        // Run the test
        final CompletableFuture<List<DisplayDeal>> result = myClassUnderTest.getDeals("userId");

        // Verify the results
    }
}
