package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.Socket;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        myClassUnderTest = new MyClass(new int[]{0}, new String[]{"stringArray"},
                new Socket[][]{{new Socket("host", 80)}});
    }

    @Test
    void testCompareArray1() throws Exception {
        myClassUnderTest.compareArray(new String[]{"array"}, new Socket[][]{{new Socket("host", 80)}});
    }

    @Test
    void testReturnTheArrays1() {
        // Setup
        // Run the test
        final Socket[][] result = myClassUnderTest.returnTheArrays();

        // Verify the results
    }
}