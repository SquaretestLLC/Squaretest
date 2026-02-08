package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test

import java.time.LocalTime
import java.time.ZoneOffset

import static org.junit.Assert.*

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass()
    }

    @Test
    void testGetStringVal1() {
        assert "Constant1" == myClassUnderTest.getStringVal1()
    }

    @Test
    void testGetStringVal2() {
        assert "MyConst1Val" == myClassUnderTest.getStringVal2()
    }

    @Test
    void testGetStringVal4() {
        assert "MyConst1Val10OtherVal" == myClassUnderTest.getStringVal4()
    }

    @Test
    void testGetStringVal5() {
        assert "class [I" == myClassUnderTest.getStringVal5()
    }

    @Test
    void testGetStringArrayVal1() {
        assert ["result"] as String[] == myClassUnderTest.getStringArrayVal1()
    }

    @Test
    void testGetStringArrayVal2() {
        assert ["result"] as String[] == myClassUnderTest.getStringArrayVal2()
    }

    @Test
    void testGetMyObjConstantVal1() {
        assert "ObjConstantVal1" == myClassUnderTest.getMyObjConstantVal1()
    }

    @Test
    void testGetMyObjConstantVal2() {
        assert "ObjConstantVal1" == myClassUnderTest.getMyObjConstantVal2()
    }

    @Test
    void testGetColorVa1() {
        assert MyClass.Color.Red == myClassUnderTest.getColorVa1()
    }

    @Test
    void testGetColorVal2() {
        assert "lowerCaseName" == myClassUnderTest.getColorVal2()
    }

    @Test
    void testGetColorVal3() {
        assert "Red" == myClassUnderTest.getColorVal3()
    }

    @Test
    void testGetColorVal4() {
        assert MyClass.Color.Red == myClassUnderTest.getColorVal4()
    }

    @Test
    void testGetSmallIntVal1() {
        assert 5 == myClassUnderTest.getSmallIntVal1()
    }

    @Test
    void testGetSmallIntVal2() {
        assert 5 == myClassUnderTest.getSmallIntVal2()
    }

    @Test
    void testGetSmallIntVal3() {
        assert 5 == myClassUnderTest.getSmallIntVal3()
    }

    @Test
    void testGetSmallIntVal4() {
        assert -6 == myClassUnderTest.getSmallIntVal4()
    }

    @Test
    void testGetSmallIntVal5() {
        assert 10 == myClassUnderTest.getSmallIntVal5()
    }

    @Test
    void testGetSmallIntVal6() {
        assert 20 == myClassUnderTest.getSmallIntVal6()
    }

    @Test
    void testGetBigIntVal() {
        assert 5 == myClassUnderTest.getBigIntVal()
    }

    @Test
    void testGetDoubleIntVal() {
        assertEquals(5.0d, myClassUnderTest.getDoubleIntVal(), 0.0001d)
    }

    @Test
    void testGetBigDoubleIntVal1() {
        assertEquals(5.5d, myClassUnderTest.getBigDoubleIntVal1(), 0.0001d)
    }

    @Test
    void testGetBigDoubleIntVal2() {
        assertEquals(0.0d, myClassUnderTest.getBigDoubleIntVal2(), 0.0001d)
    }

    @Test
    void testGetBigDoubleIntVal3() {
        assertEquals(Double.NaN, myClassUnderTest.getBigDoubleIntVal3(), 0.0001d)
    }

    @Test
    void testGetBigDoubleIntVal4() {
        assertEquals(Double.NEGATIVE_INFINITY, myClassUnderTest.getBigDoubleIntVal4(), 0.0001d)
    }

    @Test
    void testGetBigDoubleIntVal5() {
        assertEquals(Double.POSITIVE_INFINITY, myClassUnderTest.getBigDoubleIntVal5(), 0.0001d)
    }

    @Test
    void testGetFloatIntVal() {
        assertEquals(5.0f, myClassUnderTest.getFloatIntVal(), 0.0001f)
    }

    @Test
    void testGetBigFloatIntVal1() {
        assertEquals(5.5f, myClassUnderTest.getBigFloatIntVal1(), 0.0001f)
    }

    @Test
    void testGetBigFloatIntVal2() {
        assertEquals(0.0f, myClassUnderTest.getBigFloatIntVal2(), 0.0001f)
    }

    @Test
    void testGetBigFloatIntVal3() {
        assertEquals(Float.NaN, myClassUnderTest.getBigFloatIntVal3(), 0.0001f)
    }

    @Test
    void testGetBigFloatIntVal4() {
        assertEquals(Float.NEGATIVE_INFINITY, myClassUnderTest.getBigFloatIntVal4(), 0.0001f)
    }

    @Test
    void testGetBigFloatIntVal5() {
        assertEquals(Float.POSITIVE_INFINITY, myClassUnderTest.getBigFloatIntVal5(), 0.0001f)
    }

    @Test
    void testGetFloatIntVal6() {
        assertEquals(5.0d, myClassUnderTest.getFloatIntVal6(), 0.0001f)
    }

    @Test
    void testGetBigFloatIntVal7() {
        assertEquals(5.5f, myClassUnderTest.getBigFloatIntVal7(), 0.0001f)
    }

    @Test
    void testGetShortVal1() {
        assert (short) 10 == myClassUnderTest.getShortVal1()
    }

    @Test
    void testGetShortVal2() {
        assert (short) 10 == myClassUnderTest.getShortVal2()
    }

    @Test
    void testGetByteVal1() {
        assert (byte) 2 == myClassUnderTest.getByteVal1()
    }

    @Test
    void testGetByteVal2() {
        assert (byte) 2 == myClassUnderTest.getByteVal2()
    }

    @Test
    void testGetLongVal1() {
        assert 20L == myClassUnderTest.getLongVal1()
    }

    @Test
    void testGetLongVal2() {
        assert 20L == myClassUnderTest.getLongVal2()
    }

    @Test
    void testGetLongVal3() {
        assert 20L == myClassUnderTest.getLongVal3()
    }

    @Test
    void testGetCharVal1() {
        assert 'k' == myClassUnderTest.getCharVal1()
    }

    @Test
    void testGetCharVal2() {
        assert 'k' == myClassUnderTest.getCharVal2()
    }

    @Test
    void testGetCharVal3() {
        assert '/' == myClassUnderTest.getCharVal3()
    }

    @Test
    void testGetTheClass1() {
        assert MyClass.class == myClassUnderTest.getTheClass1()
    }

    @Test
    void testGetTheClass2() {
        assert MyClass.InnerClass.class == myClassUnderTest.getTheClass2()
    }

    @Test
    void testGetTheClass3() {
        assert int[].class == myClassUnderTest.getTheClass3()
    }

    @Test
    void testGetTheClass4() {
        assert int.class == myClassUnderTest.getTheClass4()
    }

    @Test
    void testGetTheClass5() {
        assert Integer.class == myClassUnderTest.getTheClass5()
    }

    @Test
    void testGetTheClass6() {
        assert Integer[].class == myClassUnderTest.getTheClass6()
    }

    @Test
    void testGetTheClass7() {
        assert Runnable.class == myClassUnderTest.getTheClass7()
    }

    @Test
    void testGetTheFooData1() {
        assertNull(myClassUnderTest.getTheFooData1())
    }

    @Test
    void testGetTheBoolean1() {
        assertTrue(myClassUnderTest.getTheBoolean1())
    }

    @Test
    void testGetTheBoolean2() {
        assertFalse(myClassUnderTest.getTheBoolean2())
    }

    @Test
    void testGetTheBoolean3() {
        assertTrue(myClassUnderTest.getTheBoolean3())
    }

    @Test
    void testGetTheBoolean4() {
        assertFalse(myClassUnderTest.getTheBoolean4())
    }

    @Test
    void testGetTheBoolean5() {
        assertTrue(myClassUnderTest.getTheBoolean5())
    }

    @Test
    void testGetTheBoolean6() {
        assertFalse(myClassUnderTest.getTheBoolean6())
    }

    @Test
    void testGetTheMaxZoneOffset() {
        assert ZoneOffset.MAX == myClassUnderTest.getTheMaxZoneOffset()
    }

    @Test
    void testGetTheMidnight() {
        assert LocalTime.MIDNIGHT == myClassUnderTest.getTheMidnight()
    }

    @Test
    void testGetTheBigDecimal() {
        assert BigDecimal.TEN == myClassUnderTest.getTheBigDecimal()
    }

    @Test
    void testGetTheBigInt() {
        assert BigInteger.TWO == myClassUnderTest.getTheBigInt()
    }

    @Test
    void testGetTheList1() {
        assert [] == myClassUnderTest.getTheList1()
    }

    @Test
    void testGetTheList2() {
        assert [] == myClassUnderTest.getTheList2()
    }

    @Test
    void testGetTheString() {
        assert Optional.empty() == myClassUnderTest.getTheString()
    }

    @Test
    void testGetTheOptInt() {
        assert OptionalInt.empty() == myClassUnderTest.getTheOptInt()
    }

    @Test
    void testGetTheByte1() {
        assert (byte) 2 == myClassUnderTest.getTheByte1()
    }

    @Test
    void testGetTheByte2() {
        assert (byte) 2 == myClassUnderTest.getTheByte2()
    }

    @Test
    void testGetTheFoo1() {
        assertNull(myClassUnderTest.getTheFoo1())
    }
}
