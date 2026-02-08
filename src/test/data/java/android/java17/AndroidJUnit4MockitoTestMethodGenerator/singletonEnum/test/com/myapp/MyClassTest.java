package com.myapp;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.constant.ClassDesc;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class MyClassTest {

    @Test
    public void testCreateNewConnection1() throws Exception {
        assertNull(MyClass.INSTANCE.createNewConnection());
    }

    @Test
    public void testDoSomething1() {
        MyClass.INSTANCE.doSomething();
    }

    @Test
    public void testName() {
        // Setup
        // Run the test
        final String result = MyClass.INSTANCE.name();

        // Verify the results
        assertEquals("name", result);
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
        assertEquals("name", result);
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

    @Test(expected = NullPointerException.class)
    public void testCompareTo_ThrowsNullPointerException() {
        // Run the test
        MyClass.INSTANCE.compareTo(MyClass.INSTANCE);
    }

    @Test(expected = ClassCastException.class)
    public void testCompareTo_ThrowsClassCastException() {
        // Run the test
        MyClass.INSTANCE.compareTo(MyClass.INSTANCE);
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
    public void testDescribeConstable() {
        // Setup
        final Optional<Enum.EnumDesc<MyClass>> expectedResult = Optional.of(
                Enum.EnumDesc.of(ClassDesc.of("name"), "name"));

        // Run the test
        final Optional<Enum.EnumDesc<MyClass>> result = MyClass.INSTANCE.describeConstable();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testValueOf() {
        assertEquals(MyClass.INSTANCE, Enum.valueOf(MyClass.class, "name"));
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