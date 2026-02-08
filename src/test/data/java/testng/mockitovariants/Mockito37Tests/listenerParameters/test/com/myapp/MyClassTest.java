package com.myapp;

import com.google.common.util.concurrent.MoreExecutors;
import org.mockito.Mock;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.EventListener;

import static org.mockito.Mockito.mock;
import static org.mockito.MockitoAnnotations.openMocks;

public class MyClassTest {

    @Mock
    private EventListener mockDefaultListener;

    private MyClass myClassUnderTest;

    private AutoCloseable mockitoCloseable;

    @BeforeMethod
    public void setUp() {
        mockitoCloseable = openMocks(this);
        myClassUnderTest = new MyClass(MoreExecutors.newDirectExecutorService(), mockDefaultListener);
    }

    @AfterMethod
    public void tearDown() throws Exception {
        mockitoCloseable.close();
    }

    @Test
    public void testPerformOperationWithListenerUnused() {
        // Setup
        // Run the test
        myClassUnderTest.performOperationWithListenerUnused(null);

        // Verify the results
    }

    @Test
    public void testPerformOperation() {
        // Setup
        final EventListener mockListener = mock(EventListener.class);

        // Run the test
        myClassUnderTest.performOperation(mockListener);

        // Verify the results
    }
}
