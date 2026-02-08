package com.myapp;

import com.google.common.util.concurrent.MoreExecutors;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.EventListener;

@RunWith(MockitoJUnitRunner.class)
public class MyClassTest {

    @Mock
    private EventListener mockDefaultListener;

    private MyClass myClassUnderTest;

    @Before
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
        final EventListener mockListener = Mockito.mock(EventListener.class);

        // Run the test
        myClassUnderTest.performOperation(mockListener);

        // Verify the results
    }
}
