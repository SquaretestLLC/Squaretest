package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass();
    }

    @Test
    void testGreeting() {
        assertEquals("greeting",
                myClassUnderTest.greeting("theName", 0L, 0, 0.0f, 0.0, 'a', (byte) 0b0, new BigDecimal("0.00"), 0.0,
                        'a', false, false, null));
    }

    @Test
    void testSubmitFormWithPrimitives() {
        assertEquals("submitFormWithPrimitives",
                myClassUnderTest.submitFormWithPrimitives(new FormDataWithPrimitives(), null));
    }

    @Test
    void testSubmitFormWithPrimitives2() {
        assertEquals("submitFormWithPrimitives2",
                myClassUnderTest.submitFormWithPrimitives2(new FormDataWithPrimitives2(), null));
    }
}
