package com.myapp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class MyClassTest {

    @Test
    void testGetLowercaseColor1() {
        assertEquals("result", MyClass.RED.getLowercaseColor());
        assertEquals("result", MyClass.BLUE.getLowercaseColor());
        assertEquals("result", MyClass.GREEN.getLowercaseColor());
        assertEquals("result", MyClass.PURPLE.getLowercaseColor());
    }

    @Test
    void testGetUppercaseColor1() {
        assertEquals("result", MyClass.RED.getUppercaseColor());
        assertEquals("result", MyClass.BLUE.getUppercaseColor());
        assertEquals("result", MyClass.GREEN.getUppercaseColor());
        assertEquals("result", MyClass.PURPLE.getUppercaseColor());
    }

    @Test
    void testIsGreen1() {
        assertFalse(MyClass.RED.isGreen());
        assertFalse(MyClass.BLUE.isGreen());
        assertFalse(MyClass.GREEN.isGreen());
        assertFalse(MyClass.PURPLE.isGreen());
    }

    @Test
    void testIsSupported1() {
        assertFalse(MyClass.isSupported("colorName"));
    }

    @Test
    void testToString1() {
        assertEquals("result", MyClass.RED.toString());
        assertEquals("result", MyClass.BLUE.toString());
        assertEquals("result", MyClass.GREEN.toString());
        assertEquals("result", MyClass.PURPLE.toString());
    }
}