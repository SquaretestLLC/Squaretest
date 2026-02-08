package com.myapp;

import com.myapp.foos.ObjectSummary;
import org.junit.Before;
import org.junit.Test;

public class MyClassTest {

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass();
    }

    @Test
    public void testDoSomething() {
        // Setup
        final ObjectSummary objectSummary = new ObjectSummary();
        objectSummary.setETag("eTag");

        // Run the test
        myClassUnderTest.doSomething(objectSummary);

        // Verify the results
    }
}
