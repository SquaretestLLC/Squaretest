package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass();
    }

    @Test
    void testGetTheString() {
        assertEquals("result", myClassUnderTest.getTheString("getTheStringParam"));
    }

    @Test
    void testGetTheOtherString() {
        assertEquals("result", myClassUnderTest.getTheOtherString("getTheOtherStringParam"));
    }
}
