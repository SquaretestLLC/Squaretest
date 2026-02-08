package com.myapp;

import com.google.common.util.concurrent.MoreExecutors;
import org.testng.annotations.Test;

import java.net.SocketOptions;

public class MyClassTest {

    @Test
    public void testGetInstance() {
        // Setup
        // Run the test
        final MyClass result = MyClass.getInstance();

        // Verify the results
    }

    @Test
    public void testCreateNewConnection() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(MoreExecutors.newDirectExecutorService(), null, 0L);

        // Run the test
        final SocketOptions result = myClassUnderTest.createNewConnection();

        // Verify the results
    }
}
