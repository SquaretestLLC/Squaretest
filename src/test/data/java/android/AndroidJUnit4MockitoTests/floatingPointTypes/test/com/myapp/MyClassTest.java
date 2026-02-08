package com.myapp;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class MyClassTest {

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass();
    }

    @Test
    public void testAddDoubles() {
        assertEquals(0.0, myClassUnderTest.addDoubles(0.0, 0.0), 0.0001);
    }

    @Test
    public void testAddBigDoubles() {
        assertEquals(0.0, myClassUnderTest.addBigDoubles(0.0, 0.0), 0.0001);
    }

    @Test
    public void testAddFloats() {
        assertEquals(0.0f, myClassUnderTest.addFloats(0.0f, 0.0f), 0.0001);
    }

    @Test
    public void testAddBigFloats() {
        assertEquals(0.0f, myClassUnderTest.addBigFloats(0.0f, 0.0f), 0.0001);
    }

    @Test
    public void testStaticAddDoubles() {
        assertEquals(0.0, MyClass.staticAddDoubles(0.0, 0.0), 0.0001);
    }

    @Test
    public void testStaticAddBigDoubles() {
        assertEquals(0.0, MyClass.staticAddBigDoubles(0.0, 0.0), 0.0001);
    }

    @Test
    public void testStaticAddFloats() {
        assertEquals(0.0f, MyClass.staticAddFloats(0.0f, 0.0f), 0.0001);
    }

    @Test
    public void testStaticAddBigFloats() {
        assertEquals(0.0f, MyClass.staticAddBigFloats(0.0f, 0.0f), 0.0001);
    }
}
