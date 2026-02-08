package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.concurrent.ExecutorService;

import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class MyClassTest {

    @Mock
    private ExecutorService mockExecutorServiceToUseForThings;
    @Mock
    private Object mockDefaultListener;

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass(mockExecutorServiceToUseForThings, mockDefaultListener);
    }

    @Test
    public void testPerformOperation1() {
        // Setup
        final Object mockListener = mock(Object.class);

        // Run the test
        myClassUnderTest.performOperation(mockListener);

        // Verify the results
    }
}