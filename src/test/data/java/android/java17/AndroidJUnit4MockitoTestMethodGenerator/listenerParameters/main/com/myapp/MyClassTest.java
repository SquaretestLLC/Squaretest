package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.concurrent.ExecutorService;

import static org.mockito.Mockito.mock;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private ExecutorService mockExecutorServiceToUseForThings;
    @Mock
    private Object mockDefaultListener;

    private MyClass myClassUnderTest;

    @Before
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockExecutorServiceToUseForThings, mockDefaultListener);
    }

    @Test
    void testPerformOperation() {
        // Setup
        final Object mockListener = mock(Object.class);

        // Run the test
        myClassUnderTest.performOperation(mockListener);

        // Verify the results
    }
}
