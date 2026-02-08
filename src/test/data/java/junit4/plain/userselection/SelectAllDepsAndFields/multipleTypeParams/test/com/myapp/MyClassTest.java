package com.myapp;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MyClassTest {

    private MyClass<String, String> myClassUnderTest;

    @Before
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

    @Test(expected = UnsupportedOperationException.class)
    public void testSetValue_ThrowsUnsupportedOperationException() {
        myClassUnderTest.setValue("setValueParam");
    }

    @Test(expected = ClassCastException.class)
    public void testSetValue_ThrowsClassCastException() {
        myClassUnderTest.setValue("setValueParam");
    }

    @Test(expected = NullPointerException.class)
    public void testSetValue_ThrowsNullPointerException() {
        myClassUnderTest.setValue("setValueParam");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetValue_ThrowsIllegalArgumentException() {
        myClassUnderTest.setValue("setValueParam");
    }

    @Test(expected = IllegalStateException.class)
    public void testSetValue_ThrowsIllegalStateException() {
        myClassUnderTest.setValue("setValueParam");
    }
}
