package com.myapp;

import org.junit.jupiter.api.Test;

import java.lang.constant.ClassDesc;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void testName() {
        assertEquals("name", MyClass.RED.name());
        assertEquals("name", MyClass.BLUE.name());
        assertEquals("name", MyClass.GREEN.name());
        assertEquals("name", MyClass.PURPLE.name());
    }

    @Test
    void testOrdinal() {
        assertEquals(0, MyClass.RED.ordinal());
        assertEquals(0, MyClass.BLUE.ordinal());
        assertEquals(0, MyClass.GREEN.ordinal());
        assertEquals(0, MyClass.PURPLE.ordinal());
    }

    @Test
    void testEquals() {
        assertFalse(MyClass.RED.equals("other"));
        assertFalse(MyClass.BLUE.equals("other"));
        assertFalse(MyClass.GREEN.equals("other"));
        assertFalse(MyClass.PURPLE.equals("other"));
    }

    @Test
    void testHashCode() {
        assertEquals(0, MyClass.RED.hashCode());
        assertEquals(0, MyClass.BLUE.hashCode());
        assertEquals(0, MyClass.GREEN.hashCode());
        assertEquals(0, MyClass.PURPLE.hashCode());
    }

    @Test
    void testCompareTo() {
        assertEquals(0, MyClass.RED.compareTo(MyClass.RED));
        assertEquals(0, MyClass.BLUE.compareTo(MyClass.RED));
        assertEquals(0, MyClass.GREEN.compareTo(MyClass.RED));
        assertEquals(0, MyClass.PURPLE.compareTo(MyClass.RED));
    }

    @Test
    void testCompareTo_ThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> MyClass.RED.compareTo(MyClass.RED));
        assertThrows(NullPointerException.class, () -> MyClass.BLUE.compareTo(MyClass.RED));
        assertThrows(NullPointerException.class, () -> MyClass.GREEN.compareTo(MyClass.RED));
        assertThrows(NullPointerException.class, () -> MyClass.PURPLE.compareTo(MyClass.RED));
    }

    @Test
    void testCompareTo_ThrowsClassCastException() {
        assertThrows(ClassCastException.class, () -> MyClass.RED.compareTo(MyClass.RED));
        assertThrows(ClassCastException.class, () -> MyClass.BLUE.compareTo(MyClass.RED));
        assertThrows(ClassCastException.class, () -> MyClass.GREEN.compareTo(MyClass.RED));
        assertThrows(ClassCastException.class, () -> MyClass.PURPLE.compareTo(MyClass.RED));
    }

    @Test
    void testGetDeclaringClass() {
        assertEquals(MyClass.class, MyClass.RED.getDeclaringClass());
        assertEquals(MyClass.class, MyClass.BLUE.getDeclaringClass());
        assertEquals(MyClass.class, MyClass.GREEN.getDeclaringClass());
        assertEquals(MyClass.class, MyClass.PURPLE.getDeclaringClass());
    }

    @Test
    void testDescribeConstable() {
        assertEquals(Optional.of(Enum.EnumDesc.of(ClassDesc.of("name"), "name")), MyClass.RED.describeConstable());
        assertEquals(Optional.of(Enum.EnumDesc.of(ClassDesc.of("name"), "name")), MyClass.BLUE.describeConstable());
        assertEquals(Optional.of(Enum.EnumDesc.of(ClassDesc.of("name"), "name")), MyClass.GREEN.describeConstable());
        assertEquals(Optional.of(Enum.EnumDesc.of(ClassDesc.of("name"), "name")), MyClass.PURPLE.describeConstable());
    }

    @Test
    void testValueOf() {
        assertEquals(MyClass.RED, Enum.valueOf(MyClass.class, "name"));
        assertThrows(IllegalArgumentException.class, () -> Enum.valueOf(MyClass.class, "name"));
        assertThrows(NullPointerException.class, () -> Enum.valueOf(MyClass.class, "name"));
    }
}