package com.myapp;

import com.google.common.util.concurrent.MoreExecutors;
import org.mockito.Mock;
import org.mockito.testng.MockitoTestNGListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.EventListener;

import static org.mockito.Mockito.mock;

@Listeners(MockitoTestNGListener.class)
public class MyClassTest {

    @Mock
    private EventListener mockDefaultListener;

    private MyClass myClassUnderTest;

    @BeforeMethod
    public void setUp() {
        myClassUnderTest = new MyClass(MoreExecutors.newDirectExecutorService(), mockDefaultListener);
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
