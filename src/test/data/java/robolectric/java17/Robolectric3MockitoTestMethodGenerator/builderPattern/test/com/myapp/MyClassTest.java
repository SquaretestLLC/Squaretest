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