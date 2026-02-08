package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass();
    }

    @Test
    void testGreeting() {
        assertThat(myClassUnderTest.greeting("theName", 0L, 0, 0.0f, 0.0, 'a', (byte) 0b0, new BigDecimal("0.00"), 0.0,
                'a', false, false, null)).isEqualTo("greeting");
    }

    @Test
    void testSubmitFormWithPrimitives() {
        assertThat(myClassUnderTest.submitFormWithPrimitives(new FormDataWithPrimitives(), null))
                .isEqualTo("submitFormWithPrimitives");
    }

    @Test
    void testSubmitFormWithPrimitives2() {
        assertThat(myClassUnderTest.submitFormWithPrimitives2(new FormDataWithPrimitives2(), null))
                .isEqualTo("submitFormWithPrimitives2");
    }
}
