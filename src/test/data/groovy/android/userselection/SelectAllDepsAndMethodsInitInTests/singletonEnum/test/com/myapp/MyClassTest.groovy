package com.myapp

import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import groovy.transform.CompileStatic
import org.junit.Test
import org.junit.runner.RunWith

import static org.junit.Assert.assertFalse
import static org.junit.Assert.assertNull

@CompileStatic
@RunWith(AndroidJUnit4.class)
@SmallTest
class MyClassTest {

    @Test
    void testCreateNewConnection() {
        assertNull(MyClass.INSTANCE.createNewConnection())
    }

    @Test
    void testDoSomething() {
        MyClass.INSTANCE.doSomething()
    }

    @Test
    void testName() {
        // Setup
        // Run the test
        def result = MyClass.INSTANCE.name()

        // Verify the results
        assert "result" == result
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
        assert "result" == result
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

    @Test
    void testGetDeclaringClass() {
        // Setup
        // Run the test
        def result = MyClass.INSTANCE.getDeclaringClass()

        // Verify the results
        assert MyClass.class == result
    }

    @Test
    void testValueOf() {
        assert MyClass.INSTANCE == Enum.valueOf(MyClass.class, "name")
    }
}
