package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private MetricService mockMetricService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockMetricService);
    }

    @Test
    void testDoSomething() {
        // Setup
        // Run the test
        final String result = myClassUnderTest.doSomething("param");

        // Verify the results
        assertEquals("result", result);
        verify(mockMetricService).recordCall19("param");
        verify(mockMetricService).recordCall18("param");
        verify(mockMetricService).recordCall17("param");
        verify(mockMetricService).recordCall16("param");
        verify(mockMetricService).recordCall15("param");
        verify(mockMetricService).recordCall14("param");
        verify(mockMetricService).recordCall13("param");
        verify(mockMetricService).recordCall12("param");
        verify(mockMetricService).recordCall11("param");
        verify(mockMetricService).recordCall10("param");
        verify(mockMetricService).recordCall9("param");
        verify(mockMetricService).recordCall8("param");
        verify(mockMetricService).recordCall7("param");
        verify(mockMetricService).recordCall6("param");
        verify(mockMetricService).recordCall5("param");
        verify(mockMetricService).recordCall4("param");
        verify(mockMetricService).recordCall3("param");
        verify(mockMetricService).recordCall2("param");
        verify(mockMetricService).recordCall1("param");
        verify(mockMetricService).recordCall0("param");
    }
}
