package com.myapp;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MyClassTest {

    @Test
    public void testAtLeast() {
        assertFalse(MyClass.JAVA_0_9.atLeast(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_1_1.atLeast(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_1_2.atLeast(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_1_3.atLeast(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_1_4.atLeast(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_1_5.atLeast(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_1_6.atLeast(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_1_7.atLeast(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_1_8.atLeast(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_1_9.atLeast(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_9.atLeast(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_10.atLeast(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_11.atLeast(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_12.atLeast(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_13.atLeast(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_14.atLeast(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_15.atLeast(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_16.atLeast(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_RECENT.atLeast(MyClass.JAVA_0_9));
    }

    @Test
    public void testAtMost() {
        assertFalse(MyClass.JAVA_0_9.atMost(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_1_1.atMost(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_1_2.atMost(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_1_3.atMost(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_1_4.atMost(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_1_5.atMost(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_1_6.atMost(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_1_7.atMost(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_1_8.atMost(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_1_9.atMost(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_9.atMost(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_10.atMost(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_11.atMost(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_12.atMost(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_13.atMost(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_14.atMost(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_15.atMost(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_16.atMost(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_RECENT.atMost(MyClass.JAVA_0_9));
    }

    @Test
    public void testGetJavaVersion() {
        assertEquals(MyClass.JAVA_0_9, MyClass.getJavaVersion("nom"));
    }

    @Test
    public void testGet() {
        assertEquals(MyClass.JAVA_0_9, MyClass.get("nom"));
    }

    @Test
    public void testToString() {
        assertEquals("name", MyClass.JAVA_0_9.toString());
        assertEquals("name", MyClass.JAVA_1_1.toString());
        assertEquals("name", MyClass.JAVA_1_2.toString());
        assertEquals("name", MyClass.JAVA_1_3.toString());
        assertEquals("name", MyClass.JAVA_1_4.toString());
        assertEquals("name", MyClass.JAVA_1_5.toString());
        assertEquals("name", MyClass.JAVA_1_6.toString());
        assertEquals("name", MyClass.JAVA_1_7.toString());
        assertEquals("name", MyClass.JAVA_1_8.toString());
        assertEquals("name", MyClass.JAVA_1_9.toString());
        assertEquals("name", MyClass.JAVA_9.toString());
        assertEquals("name", MyClass.JAVA_10.toString());
        assertEquals("name", MyClass.JAVA_11.toString());
        assertEquals("name", MyClass.JAVA_12.toString());
        assertEquals("name", MyClass.JAVA_13.toString());
        assertEquals("name", MyClass.JAVA_14.toString());
        assertEquals("name", MyClass.JAVA_15.toString());
        assertEquals("name", MyClass.JAVA_16.toString());
        assertEquals("name", MyClass.JAVA_RECENT.toString());
    }

    @Test
    public void testName() {
        assertEquals("name", MyClass.JAVA_0_9.name());
        assertEquals("name", MyClass.JAVA_1_1.name());
        assertEquals("name", MyClass.JAVA_1_2.name());
        assertEquals("name", MyClass.JAVA_1_3.name());
        assertEquals("name", MyClass.JAVA_1_4.name());
        assertEquals("name", MyClass.JAVA_1_5.name());
        assertEquals("name", MyClass.JAVA_1_6.name());
        assertEquals("name", MyClass.JAVA_1_7.name());
        assertEquals("name", MyClass.JAVA_1_8.name());
        assertEquals("name", MyClass.JAVA_1_9.name());
        assertEquals("name", MyClass.JAVA_9.name());
        assertEquals("name", MyClass.JAVA_10.name());
        assertEquals("name", MyClass.JAVA_11.name());
        assertEquals("name", MyClass.JAVA_12.name());
        assertEquals("name", MyClass.JAVA_13.name());
        assertEquals("name", MyClass.JAVA_14.name());
        assertEquals("name", MyClass.JAVA_15.name());
        assertEquals("name", MyClass.JAVA_16.name());
        assertEquals("name", MyClass.JAVA_RECENT.name());
    }

    @Test
    public void testOrdinal() {
        assertEquals(0, MyClass.JAVA_0_9.ordinal());
        assertEquals(0, MyClass.JAVA_1_1.ordinal());
        assertEquals(0, MyClass.JAVA_1_2.ordinal());
        assertEquals(0, MyClass.JAVA_1_3.ordinal());
        assertEquals(0, MyClass.JAVA_1_4.ordinal());
        assertEquals(0, MyClass.JAVA_1_5.ordinal());
        assertEquals(0, MyClass.JAVA_1_6.ordinal());
        assertEquals(0, MyClass.JAVA_1_7.ordinal());
        assertEquals(0, MyClass.JAVA_1_8.ordinal());
        assertEquals(0, MyClass.JAVA_1_9.ordinal());
        assertEquals(0, MyClass.JAVA_9.ordinal());
        assertEquals(0, MyClass.JAVA_10.ordinal());
        assertEquals(0, MyClass.JAVA_11.ordinal());
        assertEquals(0, MyClass.JAVA_12.ordinal());
        assertEquals(0, MyClass.JAVA_13.ordinal());
        assertEquals(0, MyClass.JAVA_14.ordinal());
        assertEquals(0, MyClass.JAVA_15.ordinal());
        assertEquals(0, MyClass.JAVA_16.ordinal());
        assertEquals(0, MyClass.JAVA_RECENT.ordinal());
    }

    @Test
    public void testEquals() {
        assertFalse(MyClass.JAVA_0_9.equals("other"));
        assertFalse(MyClass.JAVA_1_1.equals("other"));
        assertFalse(MyClass.JAVA_1_2.equals("other"));
        assertFalse(MyClass.JAVA_1_3.equals("other"));
        assertFalse(MyClass.JAVA_1_4.equals("other"));
        assertFalse(MyClass.JAVA_1_5.equals("other"));
        assertFalse(MyClass.JAVA_1_6.equals("other"));
        assertFalse(MyClass.JAVA_1_7.equals("other"));
        assertFalse(MyClass.JAVA_1_8.equals("other"));
        assertFalse(MyClass.JAVA_1_9.equals("other"));
        assertFalse(MyClass.JAVA_9.equals("other"));
        assertFalse(MyClass.JAVA_10.equals("other"));
        assertFalse(MyClass.JAVA_11.equals("other"));
        assertFalse(MyClass.JAVA_12.equals("other"));
        assertFalse(MyClass.JAVA_13.equals("other"));
        assertFalse(MyClass.JAVA_14.equals("other"));
        assertFalse(MyClass.JAVA_15.equals("other"));
        assertFalse(MyClass.JAVA_16.equals("other"));
        assertFalse(MyClass.JAVA_RECENT.equals("other"));
    }

    @Test
    public void testHashCode() {
        assertEquals(0, MyClass.JAVA_0_9.hashCode());
        assertEquals(0, MyClass.JAVA_1_1.hashCode());
        assertEquals(0, MyClass.JAVA_1_2.hashCode());
        assertEquals(0, MyClass.JAVA_1_3.hashCode());
        assertEquals(0, MyClass.JAVA_1_4.hashCode());
        assertEquals(0, MyClass.JAVA_1_5.hashCode());
        assertEquals(0, MyClass.JAVA_1_6.hashCode());
        assertEquals(0, MyClass.JAVA_1_7.hashCode());
        assertEquals(0, MyClass.JAVA_1_8.hashCode());
        assertEquals(0, MyClass.JAVA_1_9.hashCode());
        assertEquals(0, MyClass.JAVA_9.hashCode());
        assertEquals(0, MyClass.JAVA_10.hashCode());
        assertEquals(0, MyClass.JAVA_11.hashCode());
        assertEquals(0, MyClass.JAVA_12.hashCode());
        assertEquals(0, MyClass.JAVA_13.hashCode());
        assertEquals(0, MyClass.JAVA_14.hashCode());
        assertEquals(0, MyClass.JAVA_15.hashCode());
        assertEquals(0, MyClass.JAVA_16.hashCode());
        assertEquals(0, MyClass.JAVA_RECENT.hashCode());
    }

    @Test
    public void testCompareTo() {
        assertEquals(0, MyClass.JAVA_0_9.compareTo(MyClass.JAVA_0_9));
        assertEquals(0, MyClass.JAVA_1_1.compareTo(MyClass.JAVA_0_9));
        assertEquals(0, MyClass.JAVA_1_2.compareTo(MyClass.JAVA_0_9));
        assertEquals(0, MyClass.JAVA_1_3.compareTo(MyClass.JAVA_0_9));
        assertEquals(0, MyClass.JAVA_1_4.compareTo(MyClass.JAVA_0_9));
        assertEquals(0, MyClass.JAVA_1_5.compareTo(MyClass.JAVA_0_9));
        assertEquals(0, MyClass.JAVA_1_6.compareTo(MyClass.JAVA_0_9));
        assertEquals(0, MyClass.JAVA_1_7.compareTo(MyClass.JAVA_0_9));
        assertEquals(0, MyClass.JAVA_1_8.compareTo(MyClass.JAVA_0_9));
        assertEquals(0, MyClass.JAVA_1_9.compareTo(MyClass.JAVA_0_9));
        assertEquals(0, MyClass.JAVA_9.compareTo(MyClass.JAVA_0_9));
        assertEquals(0, MyClass.JAVA_10.compareTo(MyClass.JAVA_0_9));
        assertEquals(0, MyClass.JAVA_11.compareTo(MyClass.JAVA_0_9));
        assertEquals(0, MyClass.JAVA_12.compareTo(MyClass.JAVA_0_9));
        assertEquals(0, MyClass.JAVA_13.compareTo(MyClass.JAVA_0_9));
        assertEquals(0, MyClass.JAVA_14.compareTo(MyClass.JAVA_0_9));
        assertEquals(0, MyClass.JAVA_15.compareTo(MyClass.JAVA_0_9));
        assertEquals(0, MyClass.JAVA_16.compareTo(MyClass.JAVA_0_9));
        assertEquals(0, MyClass.JAVA_RECENT.compareTo(MyClass.JAVA_0_9));
    }

    @Test
    public void testCompareTo_ThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> MyClass.JAVA_0_9.compareTo(MyClass.JAVA_0_9));
        assertThrows(NullPointerException.class, () -> MyClass.JAVA_1_1.compareTo(MyClass.JAVA_0_9));
        assertThrows(NullPointerException.class, () -> MyClass.JAVA_1_2.compareTo(MyClass.JAVA_0_9));
        assertThrows(NullPointerException.class, () -> MyClass.JAVA_1_3.compareTo(MyClass.JAVA_0_9));
        assertThrows(NullPointerException.class, () -> MyClass.JAVA_1_4.compareTo(MyClass.JAVA_0_9));
        assertThrows(NullPointerException.class, () -> MyClass.JAVA_1_5.compareTo(MyClass.JAVA_0_9));
        assertThrows(NullPointerException.class, () -> MyClass.JAVA_1_6.compareTo(MyClass.JAVA_0_9));
        assertThrows(NullPointerException.class, () -> MyClass.JAVA_1_7.compareTo(MyClass.JAVA_0_9));
        assertThrows(NullPointerException.class, () -> MyClass.JAVA_1_8.compareTo(MyClass.JAVA_0_9));
        assertThrows(NullPointerException.class, () -> MyClass.JAVA_1_9.compareTo(MyClass.JAVA_0_9));
        assertThrows(NullPointerException.class, () -> MyClass.JAVA_9.compareTo(MyClass.JAVA_0_9));
        assertThrows(NullPointerException.class, () -> MyClass.JAVA_10.compareTo(MyClass.JAVA_0_9));
        assertThrows(NullPointerException.class, () -> MyClass.JAVA_11.compareTo(MyClass.JAVA_0_9));
        assertThrows(NullPointerException.class, () -> MyClass.JAVA_12.compareTo(MyClass.JAVA_0_9));
        assertThrows(NullPointerException.class, () -> MyClass.JAVA_13.compareTo(MyClass.JAVA_0_9));
        assertThrows(NullPointerException.class, () -> MyClass.JAVA_14.compareTo(MyClass.JAVA_0_9));
        assertThrows(NullPointerException.class, () -> MyClass.JAVA_15.compareTo(MyClass.JAVA_0_9));
        assertThrows(NullPointerException.class, () -> MyClass.JAVA_16.compareTo(MyClass.JAVA_0_9));
        assertThrows(NullPointerException.class, () -> MyClass.JAVA_RECENT.compareTo(MyClass.JAVA_0_9));
    }

    @Test
    public void testCompareTo_ThrowsClassCastException() {
        assertThrows(ClassCastException.class, () -> MyClass.JAVA_0_9.compareTo(MyClass.JAVA_0_9));
        assertThrows(ClassCastException.class, () -> MyClass.JAVA_1_1.compareTo(MyClass.JAVA_0_9));
        assertThrows(ClassCastException.class, () -> MyClass.JAVA_1_2.compareTo(MyClass.JAVA_0_9));
        assertThrows(ClassCastException.class, () -> MyClass.JAVA_1_3.compareTo(MyClass.JAVA_0_9));
        assertThrows(ClassCastException.class, () -> MyClass.JAVA_1_4.compareTo(MyClass.JAVA_0_9));
        assertThrows(ClassCastException.class, () -> MyClass.JAVA_1_5.compareTo(MyClass.JAVA_0_9));
        assertThrows(ClassCastException.class, () -> MyClass.JAVA_1_6.compareTo(MyClass.JAVA_0_9));
        assertThrows(ClassCastException.class, () -> MyClass.JAVA_1_7.compareTo(MyClass.JAVA_0_9));
        assertThrows(ClassCastException.class, () -> MyClass.JAVA_1_8.compareTo(MyClass.JAVA_0_9));
        assertThrows(ClassCastException.class, () -> MyClass.JAVA_1_9.compareTo(MyClass.JAVA_0_9));
        assertThrows(ClassCastException.class, () -> MyClass.JAVA_9.compareTo(MyClass.JAVA_0_9));
        assertThrows(ClassCastException.class, () -> MyClass.JAVA_10.compareTo(MyClass.JAVA_0_9));
        assertThrows(ClassCastException.class, () -> MyClass.JAVA_11.compareTo(MyClass.JAVA_0_9));
        assertThrows(ClassCastException.class, () -> MyClass.JAVA_12.compareTo(MyClass.JAVA_0_9));
        assertThrows(ClassCastException.class, () -> MyClass.JAVA_13.compareTo(MyClass.JAVA_0_9));
        assertThrows(ClassCastException.class, () -> MyClass.JAVA_14.compareTo(MyClass.JAVA_0_9));
        assertThrows(ClassCastException.class, () -> MyClass.JAVA_15.compareTo(MyClass.JAVA_0_9));
        assertThrows(ClassCastException.class, () -> MyClass.JAVA_16.compareTo(MyClass.JAVA_0_9));
        assertThrows(ClassCastException.class, () -> MyClass.JAVA_RECENT.compareTo(MyClass.JAVA_0_9));
    }

    @Test
    public void testGetDeclaringClass() {
        assertEquals(MyClass.class, MyClass.JAVA_0_9.getDeclaringClass());
        assertEquals(MyClass.class, MyClass.JAVA_1_1.getDeclaringClass());
        assertEquals(MyClass.class, MyClass.JAVA_1_2.getDeclaringClass());
        assertEquals(MyClass.class, MyClass.JAVA_1_3.getDeclaringClass());
        assertEquals(MyClass.class, MyClass.JAVA_1_4.getDeclaringClass());
        assertEquals(MyClass.class, MyClass.JAVA_1_5.getDeclaringClass());
        assertEquals(MyClass.class, MyClass.JAVA_1_6.getDeclaringClass());
        assertEquals(MyClass.class, MyClass.JAVA_1_7.getDeclaringClass());
        assertEquals(MyClass.class, MyClass.JAVA_1_8.getDeclaringClass());
        assertEquals(MyClass.class, MyClass.JAVA_1_9.getDeclaringClass());
        assertEquals(MyClass.class, MyClass.JAVA_9.getDeclaringClass());
        assertEquals(MyClass.class, MyClass.JAVA_10.getDeclaringClass());
        assertEquals(MyClass.class, MyClass.JAVA_11.getDeclaringClass());
        assertEquals(MyClass.class, MyClass.JAVA_12.getDeclaringClass());
        assertEquals(MyClass.class, MyClass.JAVA_13.getDeclaringClass());
        assertEquals(MyClass.class, MyClass.JAVA_14.getDeclaringClass());
        assertEquals(MyClass.class, MyClass.JAVA_15.getDeclaringClass());
        assertEquals(MyClass.class, MyClass.JAVA_16.getDeclaringClass());
        assertEquals(MyClass.class, MyClass.JAVA_RECENT.getDeclaringClass());
    }

    @Test
    public void testValueOf() {
        assertEquals(MyClass.JAVA_0_9, Enum.valueOf(MyClass.class, "name"));
        assertThrows(IllegalArgumentException.class, () -> Enum.valueOf(MyClass.class, "name"));
        assertThrows(NullPointerException.class, () -> Enum.valueOf(MyClass.class, "name"));
    }
}
