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
    void testAddDoubles1() {
        assertEquals(0.0, myClassUnderTest.addDoubles(0.0, 0.0), 0.0001);
    }

    @Test
    void testAddBigDoubles1() {
        assertEquals(0.0, myClassUnderTest.addBigDoubles(0.0, 0.0), 0.0001);
    }

    @Test
    void testAddFloats1() {
        assertEquals(0.0f, myClassUnderTest.addFloats(0.0f, 0.0f), 0.0001);
    }

    @Test
    void testAddBigFloats1() {
        assertEquals(0.0f, myClassUnderTest.addBigFloats(0.0f, 0.0f), 0.0001);
    }

    @Test
    void testStaticAddDoubles1() {
        assertEquals(0.0, MyClass.staticAddDoubles(0.0, 0.0), 0.0001);
    }

    @Test
    void testStaticAddBigDoubles1() {
        assertEquals(0.0, MyClass.staticAddBigDoubles(0.0, 0.0), 0.0001);
    }

    @Test
    void testStaticAddFloats1() {
        assertEquals(0.0f, MyClass.staticAddFloats(0.0f, 0.0f), 0.0001);
    }

    @Test
    void testStaticAddBigFloats1() {
        assertEquals(0.0f, MyClass.staticAddBigFloats(0.0f, 0.0f), 0.0001);
    }
}