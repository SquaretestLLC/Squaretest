package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass("firstName", "lastName");
    }

    @Test
    void testGetStr1() {
        assertEquals("result", myClassUnderTest.getStr1());
    }

    @Test
    void testGetStr2() {
        assertEquals("result", myClassUnderTest.getStr2());
    }

    @Test
    void testGetStr3() {
        assertEquals("result", myClassUnderTest.getStr3());
    }

    @Test
    void testGetStr4() {
        assertEquals("result", myClassUnderTest.getStr4());
    }

    @Test
    void testToString() {
        assertEquals("result", myClassUnderTest.toString());
    }

    @Test
    void testClone() {
        assertEquals("result", myClassUnderTest.clone());
    }
}
