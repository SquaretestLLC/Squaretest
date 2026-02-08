package com.myapp;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class MyClassTest {

    private MyClass<String, String> myClassUnderTest;

    @BeforeMethod
    public void setUp() {
        myClassUnderTest = new MyClass<>();
    }

    @Test
    public void testLeftGetterAndSetter() {
        final String left = "setKeyParam";
        myClassUnderTest.setKey(left);
        assertEquals(left, myClassUnderTest.getKey());
    }

    @Test
    public void testGetValue() {
        assertEquals("result", myClassUnderTest.getValue());
    }

    @Test
    public void testSetValue() {
        assertEquals("result", myClassUnderTest.setValue("setValueParam"));
    }

    @Test(expectedExceptions = {UnsupportedOperationException.class})
    public void testSetValue_ThrowsUnsupportedOperationException() {
        myClassUnderTest.setValue("setValueParam");
    }

    @Test(expectedExceptions = {ClassCastException.class})
    public void testSetValue_ThrowsClassCastException() {
        myClassUnderTest.setValue("setValueParam");
    }

    @Test(expectedExceptions = {NullPointerException.class})
    public void testSetValue_ThrowsNullPointerException() {
        myClassUnderTest.setValue("setValueParam");
    }

    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testSetValue_ThrowsIllegalArgumentException() {
        myClassUnderTest.setValue("setValueParam");
    }

    @Test(expectedExceptions = {IllegalStateException.class})
    public void testSetValue_ThrowsIllegalStateException() {
        myClassUnderTest.setValue("setValueParam");
    }
}
