package com.myapp;

import com.squaretest.supertypes.base.FooService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private FooService mockFooService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFooService);
    }

    @Test
    void testPerformGetUpData() {
        assertEquals("result", myClassUnderTest.performGetUpData("data"));
    }

    @Test
    void testPerformGetUpData1() {
        assertEquals("result", myClassUnderTest.performGetUpData1("data"));
    }
}
