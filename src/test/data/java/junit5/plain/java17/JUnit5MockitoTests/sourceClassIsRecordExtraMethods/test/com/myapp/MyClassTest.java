package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass(0L, "name", "otherValue");
    }

    @Test
    void testComputeFullValue() {
        assertEquals("result", myClassUnderTest.computeFullValue());
    }

    @Test
    void testId() {
        assertEquals(0L, myClassUnderTest.id());
    }

    @Test
    void testName() {
        assertEquals("name", myClassUnderTest.name());
    }

    @Test
    void testOtherValue() {
        assertEquals("otherValue", myClassUnderTest.otherValue());
    }
}
