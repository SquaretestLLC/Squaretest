package com.myapp;

import com.myapp.foos.ObjectSummary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass();
    }

    @Test
    void testDoSomething() {
        // Setup
        final ObjectSummary objectSummary = new ObjectSummary();
        objectSummary.setETag("eTag");

        // Run the test
        myClassUnderTest.doSomething(objectSummary);

        // Verify the results
    }
}
