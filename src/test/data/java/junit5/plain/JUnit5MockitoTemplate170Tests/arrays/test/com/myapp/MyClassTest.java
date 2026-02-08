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
    void testCompareArray() throws Exception {
        // Setup
        final Socket[][] sockets = new Socket[][]{{new Socket("host", 80)}};

        // Run the test
        myClassUnderTest.compareArray(new String[]{"array"}, sockets);

        // Verify the results
    }

    @Test
    void testReturnTheArrays() {
        // Setup
        // Run the test
        final Socket[][] result = myClassUnderTest.returnTheArrays();

        // Verify the results
    }
}
