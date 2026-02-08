package com.myapp;

import com.google.common.util.concurrent.MoreExecutors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.EventListener;

import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class MyClassTest {

    @Mock
    private EventListener mockDefaultListener;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass(MoreExecutors.newDirectExecutorService(), mockDefaultListener);
    }

    @Test
    void testPerformOperationWithListenerUnused() {
        // Setup
        // Run the test
        myClassUnderTest.performOperationWithListenerUnused(null);

        // Verify the results
    }

    @Test
    void testPerformOperation() {
        // Setup
        final EventListener mockListener = mock(EventListener.class);

        // Run the test
        myClassUnderTest.performOperation(mockListener);

        // Verify the results
    }
}
