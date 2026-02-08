package com.myapp;

import org.apache.commons.lang3.mutable.MutableInt;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyClassTest {

    @Test
    void testNormalize() {
        // Setup
        final MutableInt theIntToNormalize = new MutableInt(0);

        // Run the test
        MyClass.FirstSpace.normalize(theIntToNormalize);
        MyClass.SecondSpace.normalize(theIntToNormalize);
        MyClass.ThirdSpace.normalize(theIntToNormalize);
        MyClass.FourthSpace.normalize(theIntToNormalize);

        // Verify the results
    }

    @Test
    void testNormalizeNoArg() {
        // Run the test
        MyClass.FirstSpace.normalizeNoArg();
        MyClass.SecondSpace.normalizeNoArg();
        MyClass.ThirdSpace.normalizeNoArg();
        MyClass.FourthSpace.normalizeNoArg();

        // Verify the results
    }

    @Test
    void testTryNormalize() {
        // Setup
        final MutableInt someInt = new MutableInt(0);

        // Run the test
        MyClass.FirstSpace.tryNormalize(someInt);
        MyClass.SecondSpace.tryNormalize(someInt);
        MyClass.ThirdSpace.tryNormalize(someInt);
        MyClass.FourthSpace.tryNormalize(someInt);

        // Verify the results
    }

    @Test
    void testTryNormalize_ThrowsRuntimeException() {
        assertThrows(RuntimeException.class, () -> MyClass.FirstSpace.tryNormalize(new MutableInt(0)));
        assertThrows(RuntimeException.class, () -> MyClass.SecondSpace.tryNormalize(new MutableInt(0)));
        assertThrows(RuntimeException.class, () -> MyClass.ThirdSpace.tryNormalize(new MutableInt(0)));
        assertThrows(RuntimeException.class, () -> MyClass.FourthSpace.tryNormalize(new MutableInt(0)));
    }

    @Test
    void testTryNormalizeNoArg() {
        // Run the test
        MyClass.FirstSpace.tryNormalizeNoArg();
        MyClass.SecondSpace.tryNormalizeNoArg();
        MyClass.ThirdSpace.tryNormalizeNoArg();
        MyClass.FourthSpace.tryNormalizeNoArg();

        // Verify the results
    }

    @Test
    void testTryNormalizeNoArg_ThrowsRuntimeException() {
        assertThrows(RuntimeException.class, () -> MyClass.FirstSpace.tryNormalizeNoArg());
        assertThrows(RuntimeException.class, () -> MyClass.SecondSpace.tryNormalizeNoArg());
        assertThrows(RuntimeException.class, () -> MyClass.ThirdSpace.tryNormalizeNoArg());
        assertThrows(RuntimeException.class, () -> MyClass.FourthSpace.tryNormalizeNoArg());
    }

    @Test
    void testName() {
        assertEquals("name", MyClass.FirstSpace.name());
        assertEquals("name", MyClass.SecondSpace.name());
        assertEquals("name", MyClass.ThirdSpace.name());
        assertEquals("name", MyClass.FourthSpace.name());
    }

    @Test
    void testOrdinal() {
        assertEquals(0, MyClass.FirstSpace.ordinal());
        assertEquals(0, MyClass.SecondSpace.ordinal());
        assertEquals(0, MyClass.ThirdSpace.ordinal());
        assertEquals(0, MyClass.FourthSpace.ordinal());
    }

    @Test
    void testToString() {
        assertEquals("name", MyClass.FirstSpace.toString());
        assertEquals("name", MyClass.SecondSpace.toString());
        assertEquals("name", MyClass.ThirdSpace.toString());
        assertEquals("name", MyClass.FourthSpace.toString());
    }

    @Test
    void testEquals() {
        assertFalse(MyClass.FirstSpace.equals("other"));
        assertFalse(MyClass.SecondSpace.equals("other"));
        assertFalse(MyClass.ThirdSpace.equals("other"));
        assertFalse(MyClass.FourthSpace.equals("other"));
    }

    @Test
    void testHashCode() {
        assertEquals(0, MyClass.FirstSpace.hashCode());
        assertEquals(0, MyClass.SecondSpace.hashCode());
        assertEquals(0, MyClass.ThirdSpace.hashCode());
        assertEquals(0, MyClass.FourthSpace.hashCode());
    }

    @Test
    void testCompareTo() {
        assertEquals(0, MyClass.FirstSpace.compareTo(MyClass.FirstSpace));
        assertEquals(0, MyClass.SecondSpace.compareTo(MyClass.FirstSpace));
        assertEquals(0, MyClass.ThirdSpace.compareTo(MyClass.FirstSpace));
        assertEquals(0, MyClass.FourthSpace.compareTo(MyClass.FirstSpace));
    }

    @Test
    void testCompareTo_ThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> MyClass.FirstSpace.compareTo(MyClass.FirstSpace));
        assertThrows(NullPointerException.class, () -> MyClass.SecondSpace.compareTo(MyClass.FirstSpace));
        assertThrows(NullPointerException.class, () -> MyClass.ThirdSpace.compareTo(MyClass.FirstSpace));
        assertThrows(NullPointerException.class, () -> MyClass.FourthSpace.compareTo(MyClass.FirstSpace));
    }

    @Test
    void testCompareTo_ThrowsClassCastException() {
        assertThrows(ClassCastException.class, () -> MyClass.FirstSpace.compareTo(MyClass.FirstSpace));
        assertThrows(ClassCastException.class, () -> MyClass.SecondSpace.compareTo(MyClass.FirstSpace));
        assertThrows(ClassCastException.class, () -> MyClass.ThirdSpace.compareTo(MyClass.FirstSpace));
        assertThrows(ClassCastException.class, () -> MyClass.FourthSpace.compareTo(MyClass.FirstSpace));
    }

    @Test
    void testGetDeclaringClass() {
        assertEquals(MyClass.class, MyClass.FirstSpace.getDeclaringClass());
        assertEquals(MyClass.class, MyClass.SecondSpace.getDeclaringClass());
        assertEquals(MyClass.class, MyClass.ThirdSpace.getDeclaringClass());
        assertEquals(MyClass.class, MyClass.FourthSpace.getDeclaringClass());
    }

    @Test
    void testValueOf() {
        assertEquals(MyClass.FirstSpace, Enum.valueOf(MyClass.class, "name"));
        assertThrows(IllegalArgumentException.class, () -> Enum.valueOf(MyClass.class, "name"));
        assertThrows(NullPointerException.class, () -> Enum.valueOf(MyClass.class, "name"));
    }
}
