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
    public void testDoSomethingCool() {
        myClassUnderTest.doSomethingCool();
    }

    @Test
    public void testDoSomethingInC() {
        assertEquals("result", myClassUnderTest.doSomethingInC());
    }
}
