package com.myapp

import groovy.transform.CompileStatic
import org.junit.Test

import java.lang.constant.ClassDesc

import static org.junit.Assert.assertFalse
import static org.junit.Assert.assertNull

@CompileStatic
class MyClassTest {

    @Test
    void testCreateNewConnection1() {
        assertNull(MyClass.INSTANCE.createNewConnection())
    }

    @Test
    void testDoSomething1() {
        MyClass.INSTANCE.doSomething()
    }

    @Test
    void testName() {
        // Setup
        // Run the test
        def result = MyClass.INSTANCE.name()

        // Verify the results
        assert "name" == result
    }

    @Test
    void testOrdinal() {
        // Setup
        // Run the test
        def result = MyClass.INSTANCE.ordinal()

        // Verify the results
        assert 0 == result
    }

    @Test
    void testToString() {
        // Setup
        // Run the test
        def result = MyClass.INSTANCE.toString()

        // Verify the results
        assert "name" == result
    }

    @Test
    void testEquals() {
        // Setup
        // Run the test
        def result = MyClass.INSTANCE.equals("other")

        // Verify the results
        assertFalse(result)
    }

    @Test
    void testHashCode() {
        // Setup
        // Run the test
        def result = MyClass.INSTANCE.hashCode()

        // Verify the results
        assert 0 == result
    }

    @Test
    void testCompareTo() {
        // Setup
        // Run the test
        def result = MyClass.INSTANCE.compareTo(MyClass.INSTANCE)

        // Verify the results
        assert 0 == result
    }

    @Test(expected = NullPointerException.class)
    void testCompareTo_ThrowsNullPointerException() {
        // Run the test
        MyClass.INSTANCE.compareTo(MyClass.INSTANCE)
    }

    @Test(expected = ClassCastException.class)
    void testCompareTo_ThrowsClassCastException() {
        // Run the test
        MyClass.INSTANCE.compareTo(MyClass.INSTANCE)
    }

    @Test
    void testGetDeclaringClass() {
        // Setup
        // Run the test
        def result = MyClass.INSTANCE.getDeclaringClass()

        // Verify the results
        assert MyClass.class == result
    }

    @Test
    void testDescribeConstable() {
        // Setup
        def expectedResult = Optional.of(Enum.EnumDesc.of(ClassDesc.of("name"), "name"))

        // Run the test
        def result = MyClass.INSTANCE.describeConstable()

        // Verify the results
        assert expectedResult == result
    }

    @Test
    void testValueOf() {
        assert MyClass.INSTANCE == Enum.valueOf(MyClass.class, "name")
    }

    @Test(expected = IllegalArgumentException.class)
    void testValueOf_ThrowsIllegalArgumentException() {
        Enum.valueOf(MyClass.class, "name")
    }

    @Test(expected = NullPointerException.class)
    void testValueOf_ThrowsNullPointerException() {
        Enum.valueOf(MyClass.class, "name")
    }
}