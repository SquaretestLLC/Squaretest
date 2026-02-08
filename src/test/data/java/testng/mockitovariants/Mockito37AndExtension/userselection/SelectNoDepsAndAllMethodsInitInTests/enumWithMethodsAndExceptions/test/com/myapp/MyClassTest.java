package com.myapp;

import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.*;

public class MyClassTest {

    @Test
    public void testGetLowercaseColor() {
        assertEquals("result", MyClass.RED.getLowercaseColor());
        assertEquals("result", MyClass.BLUE.getLowercaseColor());
        assertEquals("result", MyClass.GREEN.getLowercaseColor());
        assertEquals("result", MyClass.PURPLE.getLowercaseColor());
    }

    @Test
    public void testGetUppercaseColor() {
        assertEquals("result", MyClass.RED.getUppercaseColor());
        assertEquals("result", MyClass.BLUE.getUppercaseColor());
        assertEquals("result", MyClass.GREEN.getUppercaseColor());
        assertEquals("result", MyClass.PURPLE.getUppercaseColor());
    }

    @Test
    public void testIsGreen() {
        assertFalse(MyClass.RED.isGreen());
        assertFalse(MyClass.BLUE.isGreen());
        assertFalse(MyClass.GREEN.isGreen());
        assertFalse(MyClass.PURPLE.isGreen());
    }

    @Test
    public void testSomethingThatThrows() {
        assertThrows(IOException.class, () -> MyClass.RED.somethingThatThrows("arg"));
    }

    @Test
    public void testIsSupported() {
        assertFalse(MyClass.isSupported("colorName"));
    }

    @Test
    public void testConvertTo() {
        assertThrows(IOException.class, () -> MyClass.convertTo("name"));
    }

    @Test
    public void testToString() {
        assertEquals("result", MyClass.RED.toString());
        assertEquals("result", MyClass.BLUE.toString());
        assertEquals("result", MyClass.GREEN.toString());
        assertEquals("result", MyClass.PURPLE.toString());
    }

    @Test
    public void testName() {
        assertEquals("name", MyClass.RED.name());
        assertEquals("name", MyClass.BLUE.name());
        assertEquals("name", MyClass.GREEN.name());
        assertEquals("name", MyClass.PURPLE.name());
    }

    @Test
    public void testOrdinal() {
        assertEquals(0, MyClass.RED.ordinal());
        assertEquals(0, MyClass.BLUE.ordinal());
        assertEquals(0, MyClass.GREEN.ordinal());
        assertEquals(0, MyClass.PURPLE.ordinal());
    }

    @Test
    public void testEquals() {
        assertFalse(MyClass.RED.equals("other"));
        assertFalse(MyClass.BLUE.equals("other"));
        assertFalse(MyClass.GREEN.equals("other"));
        assertFalse(MyClass.PURPLE.equals("other"));
    }

    @Test
    public void testHashCode() {
        assertEquals(0, MyClass.RED.hashCode());
        assertEquals(0, MyClass.BLUE.hashCode());
        assertEquals(0, MyClass.GREEN.hashCode());
        assertEquals(0, MyClass.PURPLE.hashCode());
    }

    @Test
    public void testCompareTo() {
        assertEquals(0, MyClass.RED.compareTo(MyClass.RED));
        assertEquals(0, MyClass.BLUE.compareTo(MyClass.RED));
        assertEquals(0, MyClass.GREEN.compareTo(MyClass.RED));
        assertEquals(0, MyClass.PURPLE.compareTo(MyClass.RED));
    }

    @Test
    public void testCompareTo_ThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> MyClass.RED.compareTo(MyClass.RED));
        assertThrows(NullPointerException.class, () -> MyClass.BLUE.compareTo(MyClass.RED));
        assertThrows(NullPointerException.class, () -> MyClass.GREEN.compareTo(MyClass.RED));
        assertThrows(NullPointerException.class, () -> MyClass.PURPLE.compareTo(MyClass.RED));
    }

    @Test
    public void testCompareTo_ThrowsClassCastException() {
        assertThrows(ClassCastException.class, () -> MyClass.RED.compareTo(MyClass.RED));
        assertThrows(ClassCastException.class, () -> MyClass.BLUE.compareTo(MyClass.RED));
        assertThrows(ClassCastException.class, () -> MyClass.GREEN.compareTo(MyClass.RED));
        assertThrows(ClassCastException.class, () -> MyClass.PURPLE.compareTo(MyClass.RED));
    }

    @Test
    public void testGetDeclaringClass() {
        assertEquals(MyClass.class, MyClass.RED.getDeclaringClass());
        assertEquals(MyClass.class, MyClass.BLUE.getDeclaringClass());
        assertEquals(MyClass.class, MyClass.GREEN.getDeclaringClass());
        assertEquals(MyClass.class, MyClass.PURPLE.getDeclaringClass());
    }

    @Test
    public void testValueOf() {
        assertEquals(MyClass.RED, Enum.valueOf(MyClass.class, "name"));
        assertThrows(IllegalArgumentException.class, () -> Enum.valueOf(MyClass.class, "name"));
        assertThrows(NullPointerException.class, () -> Enum.valueOf(MyClass.class, "name"));
    }
}
