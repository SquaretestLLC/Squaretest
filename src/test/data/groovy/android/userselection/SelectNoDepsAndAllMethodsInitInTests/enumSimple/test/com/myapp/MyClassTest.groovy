package com.myapp

import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import groovy.transform.CompileStatic
import org.junit.Test
import org.junit.runner.RunWith

import static org.junit.Assert.assertFalse

@CompileStatic
@RunWith(AndroidJUnit4.class)
@SmallTest
class MyClassTest {

    @Test
    void testName() {
        assert "result" == MyClass.RED.name()
        assert "result" == MyClass.BLUE.name()
        assert "result" == MyClass.GREEN.name()
        assert "result" == MyClass.PURPLE.name()
    }

    @Test
    void testOrdinal() {
        assert 0 == MyClass.RED.ordinal()
        assert 0 == MyClass.BLUE.ordinal()
        assert 0 == MyClass.GREEN.ordinal()
        assert 0 == MyClass.PURPLE.ordinal()
    }

    @Test
    void testToString() {
        assert "result" == MyClass.RED.toString()
        assert "result" == MyClass.BLUE.toString()
        assert "result" == MyClass.GREEN.toString()
        assert "result" == MyClass.PURPLE.toString()
    }

    @Test
    void testEquals() {
        assertFalse(MyClass.RED.equals("other"))
        assertFalse(MyClass.BLUE.equals("other"))
        assertFalse(MyClass.GREEN.equals("other"))
        assertFalse(MyClass.PURPLE.equals("other"))
    }

    @Test
    void testHashCode() {
        assert 0 == MyClass.RED.hashCode()
        assert 0 == MyClass.BLUE.hashCode()
        assert 0 == MyClass.GREEN.hashCode()
        assert 0 == MyClass.PURPLE.hashCode()
    }

    @Test
    void testCompareTo() {
        assert 0 == MyClass.RED.compareTo(MyClass.RED)
        assert 0 == MyClass.BLUE.compareTo(MyClass.RED)
        assert 0 == MyClass.GREEN.compareTo(MyClass.RED)
        assert 0 == MyClass.PURPLE.compareTo(MyClass.RED)
    }

    @Test
    void testGetDeclaringClass() {
        assert MyClass.class == MyClass.RED.getDeclaringClass()
        assert MyClass.class == MyClass.BLUE.getDeclaringClass()
        assert MyClass.class == MyClass.GREEN.getDeclaringClass()
        assert MyClass.class == MyClass.PURPLE.getDeclaringClass()
    }

    @Test
    void testValueOf() {
        assert MyClass.RED == Enum.valueOf(MyClass.class, "name")
    }
}
