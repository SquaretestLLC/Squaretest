package com.myapp;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MyClassTest {

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = null;
    }

    @Test
    public void testGetFullName() {
        assertEquals("result", myClassUnderTest.getFullName());
    }

    @Test
    public void testGetFullNameWithSuffix() {
        assertEquals("result", myClassUnderTest.getFullNameWithSuffix());
    }
}
