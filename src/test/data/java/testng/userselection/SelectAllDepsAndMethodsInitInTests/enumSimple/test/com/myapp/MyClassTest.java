package com.myapp;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

public class MyClassTest {

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
    public void testToString() {
        assertEquals("name", MyClass.RED.toString());
        assertEquals("name", MyClass.BLUE.toString());
        assertEquals("name", MyClass.GREEN.toString());
        assertEquals("name", MyClass.PURPLE.toString());
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

    @Test(expectedExceptions = {NullPointerException.class})
    public void testCompareTo_ThrowsNullPointerException() {
        MyClass.RED.compareTo(MyClass.RED);
    }

    @Test(expectedExceptions = {ClassCastException.class})
    public void testCompareTo_ThrowsClassCastException() {
        MyClass.RED.compareTo(MyClass.RED);
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
    }

    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testValueOf_ThrowsIllegalArgumentException() {
        Enum.valueOf(MyClass.class, "name");
    }

    @Test(expectedExceptions = {NullPointerException.class})
    public void testValueOf_ThrowsNullPointerException() {
        Enum.valueOf(MyClass.class, "name");
    }
}
