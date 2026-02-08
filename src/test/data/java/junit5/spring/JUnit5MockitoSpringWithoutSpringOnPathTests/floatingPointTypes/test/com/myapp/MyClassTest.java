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
    void testAddDoubles() {
        assertEquals(0.0, myClassUnderTest.addDoubles(0.0, 0.0), 0.0001);
    }

    @Test
    void testAddBigDoubles() {
        assertEquals(0.0, myClassUnderTest.addBigDoubles(0.0, 0.0), 0.0001);
    }

    @Test
    void testAddFloats() {
        assertEquals(0.0f, myClassUnderTest.addFloats(0.0f, 0.0f), 0.0001);
    }

    @Test
    void testAddBigFloats() {
        assertEquals(0.0f, myClassUnderTest.addBigFloats(0.0f, 0.0f), 0.0001);
    }

    @Test
    void testStaticAddDoubles() {
        assertEquals(0.0, MyClass.staticAddDoubles(0.0, 0.0), 0.0001);
    }

    @Test
    void testStaticAddBigDoubles() {
        assertEquals(0.0, MyClass.staticAddBigDoubles(0.0, 0.0), 0.0001);
    }

    @Test
    void testStaticAddFloats() {
        assertEquals(0.0f, MyClass.staticAddFloats(0.0f, 0.0f), 0.0001);
    }

    @Test
    void testStaticAddBigFloats() {
        assertEquals(0.0f, MyClass.staticAddBigFloats(0.0f, 0.0f), 0.0001);
    }
}
