package com.myapp;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

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
        assertThrows(UnsupportedOperationException.class, () -> myClassUnderTest.setValue("setValueParam"));
        assertThrows(ClassCastException.class, () -> myClassUnderTest.setValue("setValueParam"));
        assertThrows(NullPointerException.class, () -> myClassUnderTest.setValue("setValueParam"));
        assertThrows(IllegalArgumentException.class, () -> myClassUnderTest.setValue("setValueParam"));
        assertThrows(IllegalStateException.class, () -> myClassUnderTest.setValue("setValueParam"));
    }
}
