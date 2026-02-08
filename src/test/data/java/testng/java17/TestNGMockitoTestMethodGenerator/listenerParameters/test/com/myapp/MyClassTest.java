package com.myapp;

import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.ExecutorService;

import static org.mockito.Mockito.mock;
import static org.mockito.MockitoAnnotations.initMocks;

public class MyClassTest {

    @Mock
    private ExecutorService mockExecutorServiceToUseForThings;
    @Mock
    private Object mockDefaultListener;

    private MyClass myClassUnderTest;

    @BeforeMethod
    public void setUp() {
        initMocks(this);
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