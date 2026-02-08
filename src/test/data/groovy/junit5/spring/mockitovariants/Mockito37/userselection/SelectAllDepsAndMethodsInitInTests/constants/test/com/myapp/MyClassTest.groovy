package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.Test

import java.time.LocalTime
import java.time.ZoneOffset

import static org.junit.jupiter.api.Assertions.*

@CompileStatic
class MyClassTest {

    @Test
    void testGetStringVal1() {
        def myClassUnderTest = new MyClass()
        assert "Constant1" == myClassUnderTest.getStringVal1()
    }

    @Test
    void testGetStringVal2() {
        def myClassUnderTest = new MyClass()
        assert "MyConst1Val" == myClassUnderTest.getStringVal2()
    }

    @Test
    void testGetStringVal4() {
        def myClassUnderTest = new MyClass()
        assert "MyConst1Val10OtherVal" == myClassUnderTest.getStringVal4()
    }

    @Test
    void testGetStringVal5() {
        def myClassUnderTest = new MyClass()
        assert "class [I" == myClassUnderTest.getStringVal5()
    }

    @Test
    void testGetStringArrayVal1() {
        def myClassUnderTest = new MyClass()
        assert ["result"] as String[] == myClassUnderTest.getStringArrayVal1()
    }

    @Test
    void testGetStringArrayVal2() {
        def myClassUnderTest = new MyClass()
        assert ["result"] as String[] == myClassUnderTest.getStringArrayVal2()
    }

    @Test
    void testGetMyObjConstantVal1() {
        def myClassUnderTest = new MyClass()
        assert "ObjConstantVal1" == myClassUnderTest.getMyObjConstantVal1()
    }

    @Test
    void testGetMyObjConstantVal2() {
        def myClassUnderTest = new MyClass()
        assert "ObjConstantVal1" == myClassUnderTest.getMyObjConstantVal2()
    }

    @Test
    void testGetColorVa1() {
        def myClassUnderTest = new MyClass()
        assert MyClass.Color.Red == myClassUnderTest.getColorVa1()
    }

    @Test
    void testGetColorVal2() {
        def myClassUnderTest = new MyClass()
        assert "lowerCaseName" == myClassUnderTest.getColorVal2()
    }

    @Test
    void testGetColorVal3() {
        def myClassUnderTest = new MyClass()
        assert "Red" == myClassUnderTest.getColorVal3()
    }

    @Test
    void testGetColorVal4() {
        def myClassUnderTest = new MyClass()
        assert MyClass.Color.Red == myClassUnderTest.getColorVal4()
    }

    @Test
    void testGetSmallIntVal1() {
        def myClassUnderTest = new MyClass()
        assert 5 == myClassUnderTest.getSmallIntVal1()
    }

    @Test
    void testGetSmallIntVal2() {
        def myClassUnderTest = new MyClass()
        assert 5 == myClassUnderTest.getSmallIntVal2()
    }

    @Test
    void testGetSmallIntVal3() {
        def myClassUnderTest = new MyClass()
        assert 5 == myClassUnderTest.getSmallIntVal3()
    }

    @Test
    void testGetSmallIntVal4() {
        def myClassUnderTest = new MyClass()
        assert -6 == myClassUnderTest.getSmallIntVal4()
    }

    @Test
    void testGetSmallIntVal5() {
        def myClassUnderTest = new MyClass()
        assert 10 == myClassUnderTest.getSmallIntVal5()
    }

    @Test
    void testGetSmallIntVal6() {
        def myClassUnderTest = new MyClass()
        assert 20 == myClassUnderTest.getSmallIntVal6()
    }

    @Test
    void testGetBigIntVal() {
        def myClassUnderTest = new MyClass()
        assert 5 == myClassUnderTest.getBigIntVal()
    }

    @Test
    void testGetDoubleIntVal() {
        def myClassUnderTest = new MyClass()
        assertEquals(5.0d, myClassUnderTest.getDoubleIntVal(), 0.0001d)
    }

    @Test
    void testGetBigDoubleIntVal1() {
        def myClassUnderTest = new MyClass()
        assertEquals(5.5d, myClassUnderTest.getBigDoubleIntVal1(), 0.0001d)
    }

    @Test
    void testGetBigDoubleIntVal2() {
        def myClassUnderTest = new MyClass()
        assertEquals(0.0d, myClassUnderTest.getBigDoubleIntVal2(), 0.0001d)
    }

    @Test
    void testGetBigDoubleIntVal3() {
        def myClassUnderTest = new MyClass()
        assertEquals(Double.NaN, myClassUnderTest.getBigDoubleIntVal3(), 0.0001d)
    }

    @Test
    void testGetBigDoubleIntVal4() {
        def myClassUnderTest = new MyClass()
        assertEquals(Double.NEGATIVE_INFINITY, myClassUnderTest.getBigDoubleIntVal4(), 0.0001d)
    }

    @Test
    void testGetBigDoubleIntVal5() {
        def myClassUnderTest = new MyClass()
        assertEquals(Double.POSITIVE_INFINITY, myClassUnderTest.getBigDoubleIntVal5(), 0.0001d)
    }

    @Test
    void testGetFloatIntVal() {
        def myClassUnderTest = new MyClass()
        assertEquals(5.0f, myClassUnderTest.getFloatIntVal(), 0.0001f)
    }

    @Test
    void testGetBigFloatIntVal1() {
        def myClassUnderTest = new MyClass()
        assertEquals(5.5f, myClassUnderTest.getBigFloatIntVal1(), 0.0001f)
    }

    @Test
    void testGetBigFloatIntVal2() {
        def myClassUnderTest = new MyClass()
        assertEquals(0.0f, myClassUnderTest.getBigFloatIntVal2(), 0.0001f)
    }

    @Test
    void testGetBigFloatIntVal3() {
        def myClassUnderTest = new MyClass()
        assertEquals(Float.NaN, myClassUnderTest.getBigFloatIntVal3(), 0.0001f)
    }

    @Test
    void testGetBigFloatIntVal4() {
        def myClassUnderTest = new MyClass()
        assertEquals(Float.NEGATIVE_INFINITY, myClassUnderTest.getBigFloatIntVal4(), 0.0001f)
    }

    @Test
    void testGetBigFloatIntVal5() {
        def myClassUnderTest = new MyClass()
        assertEquals(Float.POSITIVE_INFINITY, myClassUnderTest.getBigFloatIntVal5(), 0.0001f)
    }

    @Test
    void testGetFloatIntVal6() {
        def myClassUnderTest = new MyClass()
        assertEquals(5.0d, myClassUnderTest.getFloatIntVal6(), 0.0001f)
    }

    @Test
    void testGetBigFloatIntVal7() {
        def myClassUnderTest = new MyClass()
        assertEquals(5.5f, myClassUnderTest.getBigFloatIntVal7(), 0.0001f)
    }

    @Test
    void testGetShortVal1() {
        def myClassUnderTest = new MyClass()
        assert (short) 10 == myClassUnderTest.getShortVal1()
    }

    @Test
    void testGetShortVal2() {
        def myClassUnderTest = new MyClass()
        assert (short) 10 == myClassUnderTest.getShortVal2()
    }

    @Test
    void testGetByteVal1() {
        def myClassUnderTest = new MyClass()
        assert (byte) 2 == myClassUnderTest.getByteVal1()
    }

    @Test
    void testGetByteVal2() {
        def myClassUnderTest = new MyClass()
        assert (byte) 2 == myClassUnderTest.getByteVal2()
    }

    @Test
    void testGetLongVal1() {
        def myClassUnderTest = new MyClass()
        assert 20L == myClassUnderTest.getLongVal1()
    }

    @Test
    void testGetLongVal2() {
        def myClassUnderTest = new MyClass()
        assert 20L == myClassUnderTest.getLongVal2()
    }

    @Test
    void testGetLongVal3() {
        def myClassUnderTest = new MyClass()
        assert 20L == myClassUnderTest.getLongVal3()
    }

    @Test
    void testGetCharVal1() {
        def myClassUnderTest = new MyClass()
        assert 'k' == myClassUnderTest.getCharVal1()
    }

    @Test
    void testGetCharVal2() {
        def myClassUnderTest = new MyClass()
        assert 'k' == myClassUnderTest.getCharVal2()
    }

    @Test
    void testGetCharVal3() {
        def myClassUnderTest = new MyClass()
        assert '/' == myClassUnderTest.getCharVal3()
    }

    @Test
    void testGetTheClass1() {
        def myClassUnderTest = new MyClass()
        assert MyClass.class == myClassUnderTest.getTheClass1()
    }

    @Test
    void testGetTheClass2() {
        def myClassUnderTest = new MyClass()
        assert MyClass.InnerClass.class == myClassUnderTest.getTheClass2()
    }

    @Test
    void testGetTheClass3() {
        def myClassUnderTest = new MyClass()
        assert int[].class == myClassUnderTest.getTheClass3()
    }

    @Test
    void testGetTheClass4() {
        def myClassUnderTest = new MyClass()
        assert int.class == myClassUnderTest.getTheClass4()
    }

    @Test
    void testGetTheClass5() {
        def myClassUnderTest = new MyClass()
        assert Integer.class == myClassUnderTest.getTheClass5()
    }

    @Test
    void testGetTheClass6() {
        def myClassUnderTest = new MyClass()
        assert Integer[].class == myClassUnderTest.getTheClass6()
    }

    @Test
    void testGetTheClass7() {
        def myClassUnderTest = new MyClass()
        assert Runnable.class == myClassUnderTest.getTheClass7()
    }

    @Test
    void testGetTheFooData1() {
        def myClassUnderTest = new MyClass()
        assertNull(myClassUnderTest.getTheFooData1())
    }

    @Test
    void testGetTheBoolean1() {
        def myClassUnderTest = new MyClass()
        assertTrue(myClassUnderTest.getTheBoolean1())
    }

    @Test
    void testGetTheBoolean2() {
        def myClassUnderTest = new MyClass()
        assertFalse(myClassUnderTest.getTheBoolean2())
    }

    @Test
    void testGetTheBoolean3() {
        def myClassUnderTest = new MyClass()
        assertTrue(myClassUnderTest.getTheBoolean3())
    }

    @Test
    void testGetTheBoolean4() {
        def myClassUnderTest = new MyClass()
        assertFalse(myClassUnderTest.getTheBoolean4())
    }

    @Test
    void testGetTheBoolean5() {
        def myClassUnderTest = new MyClass()
        assertTrue(myClassUnderTest.getTheBoolean5())
    }

    @Test
    void testGetTheBoolean6() {
        def myClassUnderTest = new MyClass()
        assertFalse(myClassUnderTest.getTheBoolean6())
    }

    @Test
    void testGetTheMaxZoneOffset() {
        def myClassUnderTest = new MyClass()
        assert ZoneOffset.MAX == myClassUnderTest.getTheMaxZoneOffset()
    }

    @Test
    void testGetTheMidnight() {
        def myClassUnderTest = new MyClass()
        assert LocalTime.MIDNIGHT == myClassUnderTest.getTheMidnight()
    }

    @Test
    void testGetTheBigDecimal() {
        def myClassUnderTest = new MyClass()
        assert BigDecimal.TEN == myClassUnderTest.getTheBigDecimal()
    }

    @Test
    void testGetTheBigInt() {
        def myClassUnderTest = new MyClass()
        assert BigInteger.TWO == myClassUnderTest.getTheBigInt()
    }

    @Test
    void testGetTheList1() {
        def myClassUnderTest = new MyClass()
        assert [] == myClassUnderTest.getTheList1()
    }

    @Test
    void testGetTheList2() {
        def myClassUnderTest = new MyClass()
        assert [] == myClassUnderTest.getTheList2()
    }

    @Test
    void testGetTheString() {
        def myClassUnderTest = new MyClass()
        assert Optional.empty() == myClassUnderTest.getTheString()
    }

    @Test
    void testGetTheOptInt() {
        def myClassUnderTest = new MyClass()
        assert OptionalInt.empty() == myClassUnderTest.getTheOptInt()
    }

    @Test
    void testGetTheByte1() {
        def myClassUnderTest = new MyClass()
        assert (byte) 2 == myClassUnderTest.getTheByte1()
    }

    @Test
    void testGetTheByte2() {
        def myClassUnderTest = new MyClass()
        assert (byte) 2 == myClassUnderTest.getTheByte2()
    }

    @Test
    void testGetTheFoo1() {
        def myClassUnderTest = new MyClass()
        assertNull(myClassUnderTest.getTheFoo1())
    }
}
