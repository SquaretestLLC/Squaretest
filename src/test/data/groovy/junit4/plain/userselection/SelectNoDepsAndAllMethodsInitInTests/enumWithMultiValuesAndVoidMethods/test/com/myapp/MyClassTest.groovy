package com.myapp

import groovy.transform.CompileStatic
import org.apache.commons.lang3.mutable.MutableInt
import org.junit.Test

import static org.junit.Assert.assertFalse

@CompileStatic
class MyClassTest {

    @Test
    void testNormalize() {
        // Setup
        def theIntToNormalize = new MutableInt(0)

        // Run the test
        MyClass.FirstSpace.normalize(theIntToNormalize)
        MyClass.SecondSpace.normalize(theIntToNormalize)
        MyClass.ThirdSpace.normalize(theIntToNormalize)
        MyClass.FourthSpace.normalize(theIntToNormalize)

        // Verify the results
    }

    @Test
    void testNormalizeNoArg() {
        // Run the test
        MyClass.FirstSpace.normalizeNoArg()
        MyClass.SecondSpace.normalizeNoArg()
        MyClass.ThirdSpace.normalizeNoArg()
        MyClass.FourthSpace.normalizeNoArg()

        // Verify the results
    }

    @Test
    void testTryNormalize() {
        // Setup
        def someInt = new MutableInt(0)

        // Run the test
        MyClass.FirstSpace.tryNormalize(someInt)
        MyClass.SecondSpace.tryNormalize(someInt)
        MyClass.ThirdSpace.tryNormalize(someInt)
        MyClass.FourthSpace.tryNormalize(someInt)

        // Verify the results
    }

    @Test(expected = RuntimeException.class)
    void testTryNormalize_ThrowsRuntimeException() {
        MyClass.FirstSpace.tryNormalize(new MutableInt(0))
    }

    @Test
    void testTryNormalizeNoArg() {
        // Run the test
        MyClass.FirstSpace.tryNormalizeNoArg()
        MyClass.SecondSpace.tryNormalizeNoArg()
        MyClass.ThirdSpace.tryNormalizeNoArg()
        MyClass.FourthSpace.tryNormalizeNoArg()

        // Verify the results
    }

    @Test(expected = RuntimeException.class)
    void testTryNormalizeNoArg_ThrowsRuntimeException() {
        MyClass.FirstSpace.tryNormalizeNoArg()
    }

    @Test
    void testName() {
        assert "name" == MyClass.FirstSpace.name()
        assert "name" == MyClass.SecondSpace.name()
        assert "name" == MyClass.ThirdSpace.name()
        assert "name" == MyClass.FourthSpace.name()
    }

    @Test
    void testOrdinal() {
        assert 0 == MyClass.FirstSpace.ordinal()
        assert 0 == MyClass.SecondSpace.ordinal()
        assert 0 == MyClass.ThirdSpace.ordinal()
        assert 0 == MyClass.FourthSpace.ordinal()
    }

    @Test
    void testToString() {
        assert "name" == MyClass.FirstSpace.toString()
        assert "name" == MyClass.SecondSpace.toString()
        assert "name" == MyClass.ThirdSpace.toString()
        assert "name" == MyClass.FourthSpace.toString()
    }

    @Test
    void testEquals() {
        assertFalse(MyClass.FirstSpace.equals("other"))
        assertFalse(MyClass.SecondSpace.equals("other"))
        assertFalse(MyClass.ThirdSpace.equals("other"))
        assertFalse(MyClass.FourthSpace.equals("other"))
    }

    @Test
    void testHashCode() {
        assert 0 == MyClass.FirstSpace.hashCode()
        assert 0 == MyClass.SecondSpace.hashCode()
        assert 0 == MyClass.ThirdSpace.hashCode()
        assert 0 == MyClass.FourthSpace.hashCode()
    }

    @Test
    void testCompareTo() {
        assert 0 == MyClass.FirstSpace.compareTo(MyClass.FirstSpace)
        assert 0 == MyClass.SecondSpace.compareTo(MyClass.FirstSpace)
        assert 0 == MyClass.ThirdSpace.compareTo(MyClass.FirstSpace)
        assert 0 == MyClass.FourthSpace.compareTo(MyClass.FirstSpace)
    }

    @Test(expected = NullPointerException.class)
    void testCompareTo_ThrowsNullPointerException() {
        MyClass.FirstSpace.compareTo(MyClass.FirstSpace)
    }

    @Test(expected = ClassCastException.class)
    void testCompareTo_ThrowsClassCastException() {
        MyClass.FirstSpace.compareTo(MyClass.FirstSpace)
    }

    @Test
    void testGetDeclaringClass() {
        assert MyClass.class == MyClass.FirstSpace.getDeclaringClass()
        assert MyClass.class == MyClass.SecondSpace.getDeclaringClass()
        assert MyClass.class == MyClass.ThirdSpace.getDeclaringClass()
        assert MyClass.class == MyClass.FourthSpace.getDeclaringClass()
    }

    @Test
    void testValueOf() {
        assert MyClass.FirstSpace == Enum.valueOf(MyClass.class, "name")
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
