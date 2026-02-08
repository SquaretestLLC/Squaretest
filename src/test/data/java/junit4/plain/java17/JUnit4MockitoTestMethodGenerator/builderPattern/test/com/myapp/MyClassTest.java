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
    public void testGetFullName1() {
        assertEquals("result", myClassUnderTest.getFullName());
    }

    @Test
    public void testGetFullNameWithSuffix1() {
        assertEquals("result", myClassUnderTest.getFullNameWithSuffix());
    }
}