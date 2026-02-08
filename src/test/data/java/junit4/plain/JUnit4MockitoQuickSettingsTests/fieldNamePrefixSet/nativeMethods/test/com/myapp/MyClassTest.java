package com.myapp;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
