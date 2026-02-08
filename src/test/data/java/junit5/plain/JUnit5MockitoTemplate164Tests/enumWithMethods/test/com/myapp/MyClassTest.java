package com.myapp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MyClassTest {

    @Test
    void testGetLowercaseColor() {
        assertEquals("result", MyClass.RED.getLowercaseColor());
        assertEquals("result", MyClass.BLUE.getLowercaseColor());
        assertEquals("result", MyClass.GREEN.getLowercaseColor());
        assertEquals("result", MyClass.PURPLE.getLowercaseColor());
    }

    @Test
    void testGetUppercaseColor() {
        assertEquals("result", MyClass.RED.getUppercaseColor());
        assertEquals("result", MyClass.BLUE.getUppercaseColor());
        assertEquals("result", MyClass.GREEN.getUppercaseColor());
        assertEquals("result", MyClass.PURPLE.getUppercaseColor());
    }

    @Test
    void testIsGreen() {
        assertTrue(MyClass.RED.isGreen());
        assertTrue(MyClass.BLUE.isGreen());
        assertTrue(MyClass.GREEN.isGreen());
        assertTrue(MyClass.PURPLE.isGreen());
    }

    @Test
    void testToString() {
        assertEquals("result", MyClass.RED.toString());
        assertEquals("result", MyClass.BLUE.toString());
        assertEquals("result", MyClass.GREEN.toString());
        assertEquals("result", MyClass.PURPLE.toString());
    }

    @Test
    void testIsSupported() {
        assertTrue(MyClass.isSupported("colorName"));
    }
}
