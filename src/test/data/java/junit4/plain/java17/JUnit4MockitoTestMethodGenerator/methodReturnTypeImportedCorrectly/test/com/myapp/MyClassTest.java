package com.myapp;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNull;

public class MyClassTest {

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass();
    }

    @Test
    public void testCreateStringSocketMap1() {
        assertNull(myClassUnderTest.createStringSocketMap());
    }
}