package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.assertFalse
import static org.junit.jupiter.api.Assertions.assertThrows

@CompileStatic
class MyClassTest {

    @Test
    void testGetLowercaseColor() {
        assert "result" == MyClass.RED.getLowercaseColor()
        assert "result" == MyClass.BLUE.getLowercaseColor()
        assert "result" == MyClass.GREEN.getLowercaseColor()
        assert "result" == MyClass.PURPLE.getLowercaseColor()
    }

    @Test
    void testGetUppercaseColor() {
        assert "result" == MyClass.RED.getUppercaseColor()
        assert "result" == MyClass.BLUE.getUppercaseColor()
        assert "result" == MyClass.GREEN.getUppercaseColor()
        assert "result" == MyClass.PURPLE.getUppercaseColor()
    }

    @Test
    void testIsGreen() {
        assertFalse(MyClass.RED.isGreen())
        assertFalse(MyClass.BLUE.isGreen())
        assertFalse(MyClass.GREEN.isGreen())
        assertFalse(MyClass.PURPLE.isGreen())
    }

    @Test
    void testIsSupported() {
        assertFalse(MyClass.isSupported("colorName"))
    }

    @Test
    void testToString() {
        assert "result" == MyClass.RED.toString()
        assert "result" == MyClass.BLUE.toString()
        assert "result" == MyClass.GREEN.toString()
        assert "result" == MyClass.PURPLE.toString()
    }

    @Test
    void testName() {
        assert "name" == MyClass.RED.name()
        assert "name" == MyClass.BLUE.name()
        assert "name" == MyClass.GREEN.name()
        assert "name" == MyClass.PURPLE.name()
    }

    @Test
    void testOrdinal() {
        assert 0 == MyClass.RED.ordinal()
        assert 0 == MyClass.BLUE.ordinal()
        assert 0 == MyClass.GREEN.ordinal()
        assert 0 == MyClass.PURPLE.ordinal()
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
    void testCompareTo_ThrowsNullPointerException() {
        assertThrows(NullPointerException.class, {
            MyClass.RED.compareTo(MyClass.RED)
        })
        assertThrows(NullPointerException.class, {
            MyClass.BLUE.compareTo(MyClass.RED)
        })
        assertThrows(NullPointerException.class, {
            MyClass.GREEN.compareTo(MyClass.RED)
        })
        assertThrows(NullPointerException.class, {
            MyClass.PURPLE.compareTo(MyClass.RED)
        })
    }

    @Test
    void testCompareTo_ThrowsClassCastException() {
        assertThrows(ClassCastException.class, {
            MyClass.RED.compareTo(MyClass.RED)
        })
        assertThrows(ClassCastException.class, {
            MyClass.BLUE.compareTo(MyClass.RED)
        })
        assertThrows(ClassCastException.class, {
            MyClass.GREEN.compareTo(MyClass.RED)
        })
        assertThrows(ClassCastException.class, {
            MyClass.PURPLE.compareTo(MyClass.RED)
        })
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
        assertThrows(IllegalArgumentException.class, {
            Enum.valueOf(MyClass.class, "name")
        })
        assertThrows(NullPointerException.class, {
            Enum.valueOf(MyClass.class, "name")
        })
    }
}
