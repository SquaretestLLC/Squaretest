package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.assertEquals

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass()
    }

    @Test
    void testAddDoubles() {
        assertEquals(0.0d, myClassUnderTest.addDoubles(0.0d, 0.0d), 0.0001d)
    }

    @Test
    void testAddBigDoubles() {
        assertEquals(0.0d, myClassUnderTest.addBigDoubles(0.0d, 0.0d), 0.0001d)
    }

    @Test
    void testAddFloats() {
        assertEquals(0.0f, myClassUnderTest.addFloats(0.0f, 0.0f), 0.0001f)
    }

    @Test
    void testAddBigFloats() {
        assertEquals(0.0f, myClassUnderTest.addBigFloats(0.0f, 0.0f), 0.0001f)
    }

    @Test
    void testStaticAddDoubles() {
        assertEquals(0.0d, MyClass.staticAddDoubles(0.0d, 0.0d), 0.0001d)
    }

    @Test
    void testStaticAddBigDoubles() {
        assertEquals(0.0d, MyClass.staticAddBigDoubles(0.0d, 0.0d), 0.0001d)
    }

    @Test
    void testStaticAddFloats() {
        assertEquals(0.0f, MyClass.staticAddFloats(0.0f, 0.0f), 0.0001f)
    }

    @Test
    void testStaticAddBigFloats() {
        assertEquals(0.0f, MyClass.staticAddBigFloats(0.0f, 0.0f), 0.0001f)
    }
}
