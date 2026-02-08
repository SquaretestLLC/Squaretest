package com.myapp;

import org.junit.jupiter.api.Test;

import java.net.Socket;

class MyClassTest {

    @Test
    void testCreateNewConnection() throws Exception {
        assertEquals(new Socket("host", 80), myClassUnderTest.createNewConnection());
    }

    @Test
    void testDoSomething() {
        // Run the test
        MyClass.INSTANCE.doSomething();

        // Verify the results
    }
}
