package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.Test

import java.lang.constant.ClassDesc

import static org.junit.jupiter.api.Assertions.assertFalse
import static org.junit.jupiter.api.Assertions.assertThrows

@CompileStatic
class MyClassTest {

    @Test
    void testGetLowercaseColor1() {
        assert "result" == MyClass.RED.getLowercaseColor()
        assert "result" == MyClass.BLUE.getLowercaseColor()
        assert "result" == MyClass.GREEN.getLowercaseColor()
        assert "result" == MyClass.PURPLE.getLowercaseColor()
    }

    @Test
    void testGetUppercaseColor1() {
        assert "result" == MyClass.RED.getUppercaseColor()
        assert "result" == MyClass.BLUE.getUppercaseColor()
        assert "result" == MyClass.GREEN.getUppercaseColor()
        assert "result" == MyClass.PURPLE.getUppercaseColor()
    }

    @Test
    void testIsGreen1() {
        assertFalse(MyClass.RED.isGreen())
        assertFalse(MyClass.BLUE.isGreen())
        assertFalse(MyClass.GREEN.isGreen())
        assertFalse(MyClass.PURPLE.isGreen())
    }

    @Test
    void testSomethingThatThrows1() {
        assertThrows(IOException.class, {
            MyClass.RED.somethingThatThrows("arg")
        })
    }

    @Test
    void testIsSupported1() {
        assertFalse(MyClass.isSupported("colorName"))
    }

    @Test
    void testConvertTo1() {
        assertThrows(IOException.class, {
            MyClass.convertTo("name")
        })
    }

    @Test
    void testConvertToSafe1() {
        assert "" == MyClass.convertToSafe("name")
    }

    @Test
    void testToString1() {
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
    void testDescribeConstable() {
        assert Optional.of(Enum.EnumDesc.of(ClassDesc.of("name"), "name")) == MyClass.RED.describeConstable()
        assert Optional.of(Enum.EnumDesc.of(ClassDesc.of("name"), "name")) == MyClass.BLUE.describeConstable()
        assert Optional.of(Enum.EnumDesc.of(ClassDesc.of("name"), "name")) == MyClass.GREEN.describeConstable()
        assert Optional.of(Enum.EnumDesc.of(ClassDesc.of("name"), "name")) == MyClass.PURPLE.describeConstable()
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