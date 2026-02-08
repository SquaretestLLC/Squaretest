package com.myapp;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.constant.ClassDesc;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(AndroidJUnit4.class)
@SmallTest
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

    @Test(expected = NullPointerException.class)
    public void testCompareTo_ThrowsNullPointerException() {
        MyClass.RED.compareTo(MyClass.RED);
    }

    @Test(expected = ClassCastException.class)
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
    public void testDescribeConstable() {
        assertEquals(Optional.of(Enum.EnumDesc.of(ClassDesc.of("name"), "name")), MyClass.RED.describeConstable());
        assertEquals(Optional.of(Enum.EnumDesc.of(ClassDesc.of("name"), "name")), MyClass.BLUE.describeConstable());
        assertEquals(Optional.of(Enum.EnumDesc.of(ClassDesc.of("name"), "name")), MyClass.GREEN.describeConstable());
        assertEquals(Optional.of(Enum.EnumDesc.of(ClassDesc.of("name"), "name")), MyClass.PURPLE.describeConstable());
    }

    @Test
    public void testValueOf() {
        assertEquals(MyClass.RED, Enum.valueOf(MyClass.class, "name"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValueOf_ThrowsIllegalArgumentException() {
        Enum.valueOf(MyClass.class, "name");
    }

    @Test(expected = NullPointerException.class)
    public void testValueOf_ThrowsNullPointerException() {
        Enum.valueOf(MyClass.class, "name");
    }
}