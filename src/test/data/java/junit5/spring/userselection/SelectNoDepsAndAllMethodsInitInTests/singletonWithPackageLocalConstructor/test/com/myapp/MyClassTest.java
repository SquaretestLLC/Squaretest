package com.myapp;

import com.google.common.util.concurrent.MoreExecutors;
import org.junit.jupiter.api.Test;

import java.net.SocketOptions;

class MyClassTest {

    @Test
    void testGetInstance() {
        // Setup
        // Run the test
        final MyClass result = MyClass.getInstance();

        // Verify the results
    }

    @Test
    void testCreateNewConnection() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(MoreExecutors.newDirectExecutorService(), null, 0L);

        // Run the test
        final SocketOptions result = myClassUnderTest.createNewConnection();

        // Verify the results
    }
}
