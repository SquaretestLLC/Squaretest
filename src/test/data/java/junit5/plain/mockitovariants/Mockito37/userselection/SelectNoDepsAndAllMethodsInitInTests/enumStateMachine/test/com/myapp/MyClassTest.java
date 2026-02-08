package com.myapp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyClassTest {

    @Test
    void testNextState() {
        assertEquals(MyClass.Submitted, MyClass.Submitted.nextState());
        assertEquals(MyClass.Submitted, MyClass.Escalated.nextState());
        assertEquals(MyClass.Submitted, MyClass.Approved.nextState());
    }

    @Test
    void testResponsiblePerson() {
        assertEquals("result", MyClass.Submitted.responsiblePerson());
        assertEquals("result", MyClass.Escalated.responsiblePerson());
        assertEquals("result", MyClass.Approved.responsiblePerson());
    }

    @Test
    void testName() {
        assertEquals("name", MyClass.Submitted.name());
        assertEquals("name", MyClass.Escalated.name());
        assertEquals("name", MyClass.Approved.name());
    }

    @Test
    void testOrdinal() {
        assertEquals(0, MyClass.Submitted.ordinal());
        assertEquals(0, MyClass.Escalated.ordinal());
        assertEquals(0, MyClass.Approved.ordinal());
    }

    @Test
    void testToString() {
        assertEquals("name", MyClass.Submitted.toString());
        assertEquals("name", MyClass.Escalated.toString());
        assertEquals("name", MyClass.Approved.toString());
    }

    @Test
    void testEquals() {
        assertFalse(MyClass.Submitted.equals("other"));
        assertFalse(MyClass.Escalated.equals("other"));
        assertFalse(MyClass.Approved.equals("other"));
    }

    @Test
    void testHashCode() {
        assertEquals(0, MyClass.Submitted.hashCode());
        assertEquals(0, MyClass.Escalated.hashCode());
        assertEquals(0, MyClass.Approved.hashCode());
    }

    @Test
    void testCompareTo() {
        assertEquals(0, MyClass.Submitted.compareTo(MyClass.Submitted));
        assertEquals(0, MyClass.Escalated.compareTo(MyClass.Submitted));
        assertEquals(0, MyClass.Approved.compareTo(MyClass.Submitted));
    }

    @Test
    void testCompareTo_ThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> MyClass.Submitted.compareTo(MyClass.Submitted));
        assertThrows(NullPointerException.class, () -> MyClass.Escalated.compareTo(MyClass.Submitted));
        assertThrows(NullPointerException.class, () -> MyClass.Approved.compareTo(MyClass.Submitted));
    }

    @Test
    void testCompareTo_ThrowsClassCastException() {
        assertThrows(ClassCastException.class, () -> MyClass.Submitted.compareTo(MyClass.Submitted));
        assertThrows(ClassCastException.class, () -> MyClass.Escalated.compareTo(MyClass.Submitted));
        assertThrows(ClassCastException.class, () -> MyClass.Approved.compareTo(MyClass.Submitted));
    }

    @Test
    void testGetDeclaringClass() {
        assertEquals(MyClass.class, MyClass.Submitted.getDeclaringClass());
        assertEquals(MyClass.class, MyClass.Escalated.getDeclaringClass());
        assertEquals(MyClass.class, MyClass.Approved.getDeclaringClass());
    }

    @Test
    void testValueOf() {
        assertEquals(MyClass.Submitted, Enum.valueOf(MyClass.class, "name"));
        assertThrows(IllegalArgumentException.class, () -> Enum.valueOf(MyClass.class, "name"));
        assertThrows(NullPointerException.class, () -> Enum.valueOf(MyClass.class, "name"));
    }
}
