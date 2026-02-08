package com.myapp


import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import groovy.transform.CompileStatic
import org.junit.Test
import org.junit.runner.RunWith

import java.lang.constant.ClassDesc

import static org.junit.Assert.assertFalse

@CompileStatic
@RunWith(AndroidJUnit4.class)
@SmallTest
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

    @Test(expected = IOException.class)
    void testSomethingThatThrows1() {
        MyClass.RED.somethingThatThrows("arg")
    }

    @Test
    void testIsSupported1() {
        assertFalse(MyClass.isSupported("colorName"))
    }

    @Test(expected = IOException.class)
    void testConvertTo1() {
        MyClass.convertTo("name")
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

    @Test(expected = NullPointerException.class)
    void testCompareTo_ThrowsNullPointerException() {
        MyClass.RED.compareTo(MyClass.RED)
    }

    @Test(expected = ClassCastException.class)
    void testCompareTo_ThrowsClassCastException() {
        MyClass.RED.compareTo(MyClass.RED)
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