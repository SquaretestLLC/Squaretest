package com.myapp;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class MyClassTest {

    @Test
    public void testNextState() {
        assertEquals(MyClass.Submitted, MyClass.Submitted.nextState());
        assertEquals(MyClass.Submitted, MyClass.Escalated.nextState());
        assertEquals(MyClass.Submitted, MyClass.Approved.nextState());
    }

    @Test
    public void testResponsiblePerson() {
        assertEquals("result", MyClass.Submitted.responsiblePerson());
        assertEquals("result", MyClass.Escalated.responsiblePerson());
        assertEquals("result", MyClass.Approved.responsiblePerson());
    }

    @Test
    public void testName() {
        assertEquals("name", MyClass.Submitted.name());
        assertEquals("name", MyClass.Escalated.name());
        assertEquals("name", MyClass.Approved.name());
    }

    @Test
    public void testOrdinal() {
        assertEquals(0, MyClass.Submitted.ordinal());
        assertEquals(0, MyClass.Escalated.ordinal());
        assertEquals(0, MyClass.Approved.ordinal());
    }

    @Test
    public void testToString() {
        assertEquals("name", MyClass.Submitted.toString());
        assertEquals("name", MyClass.Escalated.toString());
        assertEquals("name", MyClass.Approved.toString());
    }

    @Test
    public void testEquals() {
        assertFalse(MyClass.Submitted.equals("other"));
        assertFalse(MyClass.Escalated.equals("other"));
        assertFalse(MyClass.Approved.equals("other"));
    }

    @Test
    public void testHashCode() {
        assertEquals(0, MyClass.Submitted.hashCode());
        assertEquals(0, MyClass.Escalated.hashCode());
        assertEquals(0, MyClass.Approved.hashCode());
    }

    @Test
    public void testCompareTo() {
        assertEquals(0, MyClass.Submitted.compareTo(MyClass.Submitted));
        assertEquals(0, MyClass.Escalated.compareTo(MyClass.Submitted));
        assertEquals(0, MyClass.Approved.compareTo(MyClass.Submitted));
    }

    @Test(expected = NullPointerException.class)
    public void testCompareTo_ThrowsNullPointerException() {
        MyClass.Submitted.compareTo(MyClass.Submitted);
    }

    @Test(expected = ClassCastException.class)
    public void testCompareTo_ThrowsClassCastException() {
        MyClass.Submitted.compareTo(MyClass.Submitted);
    }

    @Test
    public void testGetDeclaringClass() {
        assertEquals(MyClass.class, MyClass.Submitted.getDeclaringClass());
        assertEquals(MyClass.class, MyClass.Escalated.getDeclaringClass());
        assertEquals(MyClass.class, MyClass.Approved.getDeclaringClass());
    }

    @Test
    public void testValueOf() {
        assertEquals(MyClass.Submitted, Enum.valueOf(MyClass.class, "name"));
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
