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
        assertEquals("result", myClassUnderTest.doSomething("firstValue", "secondValue"));
    }

    @Test
    void testDoSomethingElse() {
        // Setup
        // Run the test
        final String result = myClassUnderTest.doSomethingElse("otherFirstValue", "otherSecondValue");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testDoThirdThing() {
        // Setup
        // Run the test
        final String result = myClassUnderTest.doThirdThing("thirdThingParam");

        // Verify the results
        assertEquals("result", result);
        verify(mockMetricService).recordError("Third thing param is null", "thirdThingParam");
    }

    @Test
    void testDoSomething1() {
        assertEquals("result", myClassUnderTest.doSomething1("firstValue1", "secondValue1"));
    }
}
