package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
public class MyClassTest {

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass();
    }

    @Test
    public void testAddDoubles1() {
        assertEquals(0.0, myClassUnderTest.addDoubles(0.0, 0.0), 0.0001);
    }

    @Test
    public void testAddBigDoubles1() {
        assertEquals(0.0, myClassUnderTest.addBigDoubles(0.0, 0.0), 0.0001);
    }

    @Test
    public void testAddFloats1() {
        assertEquals(0.0f, myClassUnderTest.addFloats(0.0f, 0.0f), 0.0001);
    }

    @Test
    public void testAddBigFloats1() {
        assertEquals(0.0f, myClassUnderTest.addBigFloats(0.0f, 0.0f), 0.0001);
    }

    @Test
    public void testStaticAddDoubles1() {
        assertEquals(0.0, MyClass.staticAddDoubles(0.0, 0.0), 0.0001);
    }

    @Test
    public void testStaticAddBigDoubles1() {
        assertEquals(0.0, MyClass.staticAddBigDoubles(0.0, 0.0), 0.0001);
    }

    @Test
    public void testStaticAddFloats1() {
        assertEquals(0.0f, MyClass.staticAddFloats(0.0f, 0.0f), 0.0001);
    }

    @Test
    public void testStaticAddBigFloats1() {
        assertEquals(0.0f, MyClass.staticAddBigFloats(0.0f, 0.0f), 0.0001);
    }
}