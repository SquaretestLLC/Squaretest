package com.myapp

import groovy.transform.CompileStatic
import org.junit.Test

import static org.junit.Assert.assertFalse

@CompileStatic
class MyClassTest {

    @Test
    void testAtLeast() {
        assertFalse(MyClass.JAVA_0_9.atLeast(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_1_1.atLeast(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_1_2.atLeast(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_1_3.atLeast(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_1_4.atLeast(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_1_5.atLeast(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_1_6.atLeast(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_1_7.atLeast(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_1_8.atLeast(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_1_9.atLeast(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_9.atLeast(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_10.atLeast(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_11.atLeast(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_12.atLeast(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_13.atLeast(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_14.atLeast(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_15.atLeast(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_16.atLeast(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_RECENT.atLeast(MyClass.JAVA_0_9))
    }

    @Test
    void testAtMost() {
        assertFalse(MyClass.JAVA_0_9.atMost(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_1_1.atMost(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_1_2.atMost(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_1_3.atMost(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_1_4.atMost(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_1_5.atMost(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_1_6.atMost(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_1_7.atMost(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_1_8.atMost(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_1_9.atMost(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_9.atMost(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_10.atMost(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_11.atMost(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_12.atMost(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_13.atMost(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_14.atMost(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_15.atMost(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_16.atMost(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_RECENT.atMost(MyClass.JAVA_0_9))
    }

    @Test
    void testGetJavaVersion() {
        assert MyClass.JAVA_0_9 == MyClass.getJavaVersion("nom")
    }

    @Test
    void testGet() {
        assert MyClass.JAVA_0_9 == MyClass.get("nom")
    }

    @Test
    void testToString() {
        assert "name" == MyClass.JAVA_0_9.toString()
        assert "name" == MyClass.JAVA_1_1.toString()
        assert "name" == MyClass.JAVA_1_2.toString()
        assert "name" == MyClass.JAVA_1_3.toString()
        assert "name" == MyClass.JAVA_1_4.toString()
        assert "name" == MyClass.JAVA_1_5.toString()
        assert "name" == MyClass.JAVA_1_6.toString()
        assert "name" == MyClass.JAVA_1_7.toString()
        assert "name" == MyClass.JAVA_1_8.toString()
        assert "name" == MyClass.JAVA_1_9.toString()
        assert "name" == MyClass.JAVA_9.toString()
        assert "name" == MyClass.JAVA_10.toString()
        assert "name" == MyClass.JAVA_11.toString()
        assert "name" == MyClass.JAVA_12.toString()
        assert "name" == MyClass.JAVA_13.toString()
        assert "name" == MyClass.JAVA_14.toString()
        assert "name" == MyClass.JAVA_15.toString()
        assert "name" == MyClass.JAVA_16.toString()
        assert "name" == MyClass.JAVA_RECENT.toString()
    }

    @Test
    void testName() {
        assert "name" == MyClass.JAVA_0_9.name()
        assert "name" == MyClass.JAVA_1_1.name()
        assert "name" == MyClass.JAVA_1_2.name()
        assert "name" == MyClass.JAVA_1_3.name()
        assert "name" == MyClass.JAVA_1_4.name()
        assert "name" == MyClass.JAVA_1_5.name()
        assert "name" == MyClass.JAVA_1_6.name()
        assert "name" == MyClass.JAVA_1_7.name()
        assert "name" == MyClass.JAVA_1_8.name()
        assert "name" == MyClass.JAVA_1_9.name()
        assert "name" == MyClass.JAVA_9.name()
        assert "name" == MyClass.JAVA_10.name()
        assert "name" == MyClass.JAVA_11.name()
        assert "name" == MyClass.JAVA_12.name()
        assert "name" == MyClass.JAVA_13.name()
        assert "name" == MyClass.JAVA_14.name()
        assert "name" == MyClass.JAVA_15.name()
        assert "name" == MyClass.JAVA_16.name()
        assert "name" == MyClass.JAVA_RECENT.name()
    }

    @Test
    void testOrdinal() {
        assert 0 == MyClass.JAVA_0_9.ordinal()
        assert 0 == MyClass.JAVA_1_1.ordinal()
        assert 0 == MyClass.JAVA_1_2.ordinal()
        assert 0 == MyClass.JAVA_1_3.ordinal()
        assert 0 == MyClass.JAVA_1_4.ordinal()
        assert 0 == MyClass.JAVA_1_5.ordinal()
        assert 0 == MyClass.JAVA_1_6.ordinal()
        assert 0 == MyClass.JAVA_1_7.ordinal()
        assert 0 == MyClass.JAVA_1_8.ordinal()
        assert 0 == MyClass.JAVA_1_9.ordinal()
        assert 0 == MyClass.JAVA_9.ordinal()
        assert 0 == MyClass.JAVA_10.ordinal()
        assert 0 == MyClass.JAVA_11.ordinal()
        assert 0 == MyClass.JAVA_12.ordinal()
        assert 0 == MyClass.JAVA_13.ordinal()
        assert 0 == MyClass.JAVA_14.ordinal()
        assert 0 == MyClass.JAVA_15.ordinal()
        assert 0 == MyClass.JAVA_16.ordinal()
        assert 0 == MyClass.JAVA_RECENT.ordinal()
    }

    @Test
    void testEquals() {
        assertFalse(MyClass.JAVA_0_9.equals("other"))
        assertFalse(MyClass.JAVA_1_1.equals("other"))
        assertFalse(MyClass.JAVA_1_2.equals("other"))
        assertFalse(MyClass.JAVA_1_3.equals("other"))
        assertFalse(MyClass.JAVA_1_4.equals("other"))
        assertFalse(MyClass.JAVA_1_5.equals("other"))
        assertFalse(MyClass.JAVA_1_6.equals("other"))
        assertFalse(MyClass.JAVA_1_7.equals("other"))
        assertFalse(MyClass.JAVA_1_8.equals("other"))
        assertFalse(MyClass.JAVA_1_9.equals("other"))
        assertFalse(MyClass.JAVA_9.equals("other"))
        assertFalse(MyClass.JAVA_10.equals("other"))
        assertFalse(MyClass.JAVA_11.equals("other"))
        assertFalse(MyClass.JAVA_12.equals("other"))
        assertFalse(MyClass.JAVA_13.equals("other"))
        assertFalse(MyClass.JAVA_14.equals("other"))
        assertFalse(MyClass.JAVA_15.equals("other"))
        assertFalse(MyClass.JAVA_16.equals("other"))
        assertFalse(MyClass.JAVA_RECENT.equals("other"))
    }

    @Test
    void testHashCode() {
        assert 0 == MyClass.JAVA_0_9.hashCode()
        assert 0 == MyClass.JAVA_1_1.hashCode()
        assert 0 == MyClass.JAVA_1_2.hashCode()
        assert 0 == MyClass.JAVA_1_3.hashCode()
        assert 0 == MyClass.JAVA_1_4.hashCode()
        assert 0 == MyClass.JAVA_1_5.hashCode()
        assert 0 == MyClass.JAVA_1_6.hashCode()
        assert 0 == MyClass.JAVA_1_7.hashCode()
        assert 0 == MyClass.JAVA_1_8.hashCode()
        assert 0 == MyClass.JAVA_1_9.hashCode()
        assert 0 == MyClass.JAVA_9.hashCode()
        assert 0 == MyClass.JAVA_10.hashCode()
        assert 0 == MyClass.JAVA_11.hashCode()
        assert 0 == MyClass.JAVA_12.hashCode()
        assert 0 == MyClass.JAVA_13.hashCode()
        assert 0 == MyClass.JAVA_14.hashCode()
        assert 0 == MyClass.JAVA_15.hashCode()
        assert 0 == MyClass.JAVA_16.hashCode()
        assert 0 == MyClass.JAVA_RECENT.hashCode()
    }

    @Test
    void testCompareTo() {
        assert 0 == MyClass.JAVA_0_9.compareTo(MyClass.JAVA_0_9)
        assert 0 == MyClass.JAVA_1_1.compareTo(MyClass.JAVA_0_9)
        assert 0 == MyClass.JAVA_1_2.compareTo(MyClass.JAVA_0_9)
        assert 0 == MyClass.JAVA_1_3.compareTo(MyClass.JAVA_0_9)
        assert 0 == MyClass.JAVA_1_4.compareTo(MyClass.JAVA_0_9)
        assert 0 == MyClass.JAVA_1_5.compareTo(MyClass.JAVA_0_9)
        assert 0 == MyClass.JAVA_1_6.compareTo(MyClass.JAVA_0_9)
        assert 0 == MyClass.JAVA_1_7.compareTo(MyClass.JAVA_0_9)
        assert 0 == MyClass.JAVA_1_8.compareTo(MyClass.JAVA_0_9)
        assert 0 == MyClass.JAVA_1_9.compareTo(MyClass.JAVA_0_9)
        assert 0 == MyClass.JAVA_9.compareTo(MyClass.JAVA_0_9)
        assert 0 == MyClass.JAVA_10.compareTo(MyClass.JAVA_0_9)
        assert 0 == MyClass.JAVA_11.compareTo(MyClass.JAVA_0_9)
        assert 0 == MyClass.JAVA_12.compareTo(MyClass.JAVA_0_9)
        assert 0 == MyClass.JAVA_13.compareTo(MyClass.JAVA_0_9)
        assert 0 == MyClass.JAVA_14.compareTo(MyClass.JAVA_0_9)
        assert 0 == MyClass.JAVA_15.compareTo(MyClass.JAVA_0_9)
        assert 0 == MyClass.JAVA_16.compareTo(MyClass.JAVA_0_9)
        assert 0 == MyClass.JAVA_RECENT.compareTo(MyClass.JAVA_0_9)
    }

    @Test(expected = NullPointerException.class)
    void testCompareTo_ThrowsNullPointerException() {
        MyClass.JAVA_0_9.compareTo(MyClass.JAVA_0_9)
    }

    @Test(expected = ClassCastException.class)
    void testCompareTo_ThrowsClassCastException() {
        MyClass.JAVA_0_9.compareTo(MyClass.JAVA_0_9)
    }

    @Test
    void testGetDeclaringClass() {
        assert MyClass.class == MyClass.JAVA_0_9.getDeclaringClass()
        assert MyClass.class == MyClass.JAVA_1_1.getDeclaringClass()
        assert MyClass.class == MyClass.JAVA_1_2.getDeclaringClass()
        assert MyClass.class == MyClass.JAVA_1_3.getDeclaringClass()
        assert MyClass.class == MyClass.JAVA_1_4.getDeclaringClass()
        assert MyClass.class == MyClass.JAVA_1_5.getDeclaringClass()
        assert MyClass.class == MyClass.JAVA_1_6.getDeclaringClass()
        assert MyClass.class == MyClass.JAVA_1_7.getDeclaringClass()
        assert MyClass.class == MyClass.JAVA_1_8.getDeclaringClass()
        assert MyClass.class == MyClass.JAVA_1_9.getDeclaringClass()
        assert MyClass.class == MyClass.JAVA_9.getDeclaringClass()
        assert MyClass.class == MyClass.JAVA_10.getDeclaringClass()
        assert MyClass.class == MyClass.JAVA_11.getDeclaringClass()
        assert MyClass.class == MyClass.JAVA_12.getDeclaringClass()
        assert MyClass.class == MyClass.JAVA_13.getDeclaringClass()
        assert MyClass.class == MyClass.JAVA_14.getDeclaringClass()
        assert MyClass.class == MyClass.JAVA_15.getDeclaringClass()
        assert MyClass.class == MyClass.JAVA_16.getDeclaringClass()
        assert MyClass.class == MyClass.JAVA_RECENT.getDeclaringClass()
    }

    @Test
    void testValueOf() {
        assert MyClass.JAVA_0_9 == Enum.valueOf(MyClass.class, "name")
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
