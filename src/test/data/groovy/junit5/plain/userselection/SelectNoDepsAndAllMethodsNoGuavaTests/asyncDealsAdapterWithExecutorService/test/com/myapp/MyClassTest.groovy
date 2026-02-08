package com.myapp

import com.myapp.asyncdealadapter1.DealsServiceClient
import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import java.util.concurrent.Executors

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass(new DealsServiceClient(), Executors.newSingleThreadExecutor())
    }

    @Test
    void testGetDeals() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getDeals("userId")

        // Verify the results
    }
}
