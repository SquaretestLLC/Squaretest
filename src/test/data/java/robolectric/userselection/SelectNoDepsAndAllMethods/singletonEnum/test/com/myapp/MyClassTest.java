package com.myapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class MyClassTest {

    @Test
    public void testCreateNewConnection() {
        assertNull(MyClass.INSTANCE.createNewConnection());
    }

    @Test
    public void testDoSomething() {
        MyClass.INSTANCE.doSomething();
    }

    @Test
    public void testName() {
        // Setup
        // Run the test
        final String result = MyClass.INSTANCE.name();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testOrdinal() {
        // Setup
        // Run the test
        final int result = MyClass.INSTANCE.ordinal();

        // Verify the results
        assertEquals(0, result);
    }

    @Test
    public void testToString() {
        // Setup
        // Run the test
        final String result = MyClass.INSTANCE.toString();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testEquals() {
        // Setup
        // Run the test
        final boolean result = MyClass.INSTANCE.equals("other");

        // Verify the results
        assertFalse(result);
    }

    @Test
    public void testHashCode() {
        // Setup
        // Run the test
        final int result = MyClass.INSTANCE.hashCode();

        // Verify the results
        assertEquals(0, result);
    }

    @Test
    public void testCompareTo() {
        // Setup
        // Run the test
        final int result = MyClass.INSTANCE.compareTo(MyClass.INSTANCE);

        // Verify the results
        assertEquals(0, result);
    }

    @Test
    public void testGetDeclaringClass() {
        // Setup
        // Run the test
        final Class<MyClass> result = MyClass.INSTANCE.getDeclaringClass();

        // Verify the results
        assertEquals(MyClass.class, result);
    }

    @Test
    public void testValueOf() {
        assertEquals(MyClass.INSTANCE, Enum.valueOf(MyClass.class, "name"));
    }
}
