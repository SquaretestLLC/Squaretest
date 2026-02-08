package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MyClassTest {

    private MyClass<String, String> myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass<>();
    }

    @Test
    void testSetValue() {
        assertEquals("result", myClassUnderTest.setValue("setValueParam"));
        assertThrows(UnsupportedOperationException.class, () -> myClassUnderTest.setValue("setValueParam"));
        assertThrows(ClassCastException.class, () -> myClassUnderTest.setValue("setValueParam"));
        assertThrows(NullPointerException.class, () -> myClassUnderTest.setValue("setValueParam"));
        assertThrows(IllegalArgumentException.class, () -> myClassUnderTest.setValue("setValueParam"));
        assertThrows(IllegalStateException.class, () -> myClassUnderTest.setValue("setValueParam"));
    }
}
