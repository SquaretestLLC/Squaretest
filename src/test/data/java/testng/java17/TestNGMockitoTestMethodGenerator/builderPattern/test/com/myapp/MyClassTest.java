package com.myapp;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeMethod
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