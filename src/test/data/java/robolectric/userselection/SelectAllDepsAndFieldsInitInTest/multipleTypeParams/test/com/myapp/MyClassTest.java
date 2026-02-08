package com.myapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
public class MyClassTest {

    @Test
    public void testLeftGetterAndSetter() {
        final MyClass<String, String> myClassUnderTest = new MyClass<>();
        final String left = "setKeyParam";
        myClassUnderTest.setKey(left);
        assertEquals(left, myClassUnderTest.getKey());
    }

    @Test
    public void testGetValue() {
        final MyClass<String, String> myClassUnderTest = new MyClass<>();
        assertEquals("result", myClassUnderTest.getValue());
    }

    @Test
    public void testSetValue() {
        final MyClass<String, String> myClassUnderTest = new MyClass<>();
        assertEquals("result", myClassUnderTest.setValue("setValueParam"));
    }
}
