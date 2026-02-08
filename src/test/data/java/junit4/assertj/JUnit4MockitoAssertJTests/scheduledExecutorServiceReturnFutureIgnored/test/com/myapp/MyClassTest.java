package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ScheduledExecutorService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MyClassTest {

    @Mock
    private ScheduledExecutorService mockScheduledExecutorService;

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass(mockScheduledExecutorService);
    }

    @Test
    public void testStartProcess() {
        // Setup
        doAnswer(invocation -> {
            ((Runnable) invocation.getArguments()[0]).run();
            return CompletableFuture.completedFuture(null);
        }).when(mockScheduledExecutorService).submit(any(Runnable.class));

        // Run the test
        myClassUnderTest.startProcess();

        // Verify the results
        verify(mockScheduledExecutorService).submit(any(Runnable.class));
    }

    @Test
    public void testStopProcess() {
        // Setup
        // Run the test
        myClassUnderTest.stopProcess();

        // Verify the results
        verify(mockScheduledExecutorService).shutdownNow();
    }
}
