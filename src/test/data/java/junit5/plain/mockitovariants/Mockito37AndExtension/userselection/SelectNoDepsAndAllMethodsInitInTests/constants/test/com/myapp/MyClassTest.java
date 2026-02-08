package com.myapp;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.Optional;
import java.util.OptionalInt;

import static org.junit.jupiter.api.Assertions.*;

class MyClassTest {

    @Test
    void testGetStringVal1() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals("Constant1", myClassUnderTest.getStringVal1());
    }

    @Test
    void testGetStringVal2() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals("MyConst1Val", myClassUnderTest.getStringVal2());
    }

    @Test
    void testGetStringVal4() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals("MyConst1Val10OtherVal", myClassUnderTest.getStringVal4());
    }

    @Test
    void testGetStringVal5() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals("class [I", myClassUnderTest.getStringVal5());
    }

    @Test
    void testGetStringArrayVal1() {
        final MyClass myClassUnderTest = new MyClass();
        assertArrayEquals(new String[]{"result"}, myClassUnderTest.getStringArrayVal1());
    }

    @Test
    void testGetStringArrayVal2() {
        final MyClass myClassUnderTest = new MyClass();
        assertArrayEquals(new String[]{"result"}, myClassUnderTest.getStringArrayVal2());
    }

    @Test
    void testGetMyObjConstantVal1() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals("ObjConstantVal1", myClassUnderTest.getMyObjConstantVal1());
    }

    @Test
    void testGetMyObjConstantVal2() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals("ObjConstantVal1", myClassUnderTest.getMyObjConstantVal2());
    }

    @Test
    void testGetColorVa1() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(MyClass.Color.Red, myClassUnderTest.getColorVa1());
    }

    @Test
    void testGetColorVal2() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals("lowerCaseName", myClassUnderTest.getColorVal2());
    }

    @Test
    void testGetColorVal3() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals("Red", myClassUnderTest.getColorVal3());
    }

    @Test
    void testGetColorVal4() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(MyClass.Color.Red, myClassUnderTest.getColorVal4());
    }

    @Test
    void testGetSmallIntVal1() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(5, myClassUnderTest.getSmallIntVal1());
    }

    @Test
    void testGetSmallIntVal2() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(5, myClassUnderTest.getSmallIntVal2());
    }

    @Test
    void testGetSmallIntVal3() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(5, myClassUnderTest.getSmallIntVal3());
    }

    @Test
    void testGetSmallIntVal4() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(-6, myClassUnderTest.getSmallIntVal4());
    }

    @Test
    void testGetSmallIntVal5() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(10, myClassUnderTest.getSmallIntVal5());
    }

    @Test
    void testGetSmallIntVal6() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(20, myClassUnderTest.getSmallIntVal6());
    }

    @Test
    void testGetBigIntVal() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(5, myClassUnderTest.getBigIntVal());
    }

    @Test
    void testGetDoubleIntVal() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(5.0, myClassUnderTest.getDoubleIntVal(), 0.0001);
    }

    @Test
    void testGetBigDoubleIntVal1() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(5.5, myClassUnderTest.getBigDoubleIntVal1(), 0.0001);
    }

    @Test
    void testGetBigDoubleIntVal2() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(0.0, myClassUnderTest.getBigDoubleIntVal2(), 0.0001);
    }

    @Test
    void testGetBigDoubleIntVal3() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(Double.NaN, myClassUnderTest.getBigDoubleIntVal3(), 0.0001);
    }

    @Test
    void testGetBigDoubleIntVal4() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(Double.NEGATIVE_INFINITY, myClassUnderTest.getBigDoubleIntVal4(), 0.0001);
    }

    @Test
    void testGetBigDoubleIntVal5() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(Double.POSITIVE_INFINITY, myClassUnderTest.getBigDoubleIntVal5(), 0.0001);
    }

    @Test
    void testGetFloatIntVal() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(5.0f, myClassUnderTest.getFloatIntVal(), 0.0001);
    }

    @Test
    void testGetBigFloatIntVal1() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(5.5f, myClassUnderTest.getBigFloatIntVal1(), 0.0001);
    }

    @Test
    void testGetBigFloatIntVal2() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(0.0f, myClassUnderTest.getBigFloatIntVal2(), 0.0001);
    }

    @Test
    void testGetBigFloatIntVal3() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(Float.NaN, myClassUnderTest.getBigFloatIntVal3(), 0.0001);
    }

    @Test
    void testGetBigFloatIntVal4() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(Float.NEGATIVE_INFINITY, myClassUnderTest.getBigFloatIntVal4(), 0.0001);
    }

    @Test
    void testGetBigFloatIntVal5() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(Float.POSITIVE_INFINITY, myClassUnderTest.getBigFloatIntVal5(), 0.0001);
    }

    @Test
    void testGetFloatIntVal6() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(5.0, myClassUnderTest.getFloatIntVal6(), 0.0001);
    }

    @Test
    void testGetBigFloatIntVal7() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(5.5f, myClassUnderTest.getBigFloatIntVal7(), 0.0001);
    }

    @Test
    void testGetShortVal1() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals((short) 10, myClassUnderTest.getShortVal1());
    }

    @Test
    void testGetShortVal2() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals((short) 10, myClassUnderTest.getShortVal2());
    }

    @Test
    void testGetByteVal1() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals((byte) 2, myClassUnderTest.getByteVal1());
    }

    @Test
    void testGetByteVal2() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals((byte) 2, myClassUnderTest.getByteVal2());
    }

    @Test
    void testGetLongVal1() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(20L, myClassUnderTest.getLongVal1());
    }

    @Test
    void testGetLongVal2() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(20L, myClassUnderTest.getLongVal2());
    }

    @Test
    void testGetLongVal3() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(20L, myClassUnderTest.getLongVal3());
    }

    @Test
    void testGetCharVal1() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals('k', myClassUnderTest.getCharVal1());
    }

    @Test
    void testGetCharVal2() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals('k', myClassUnderTest.getCharVal2());
    }

    @Test
    void testGetCharVal3() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals('/', myClassUnderTest.getCharVal3());
    }

    @Test
    void testGetTheClass1() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(MyClass.class, myClassUnderTest.getTheClass1());
    }

    @Test
    void testGetTheClass2() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(MyClass.InnerClass.class, myClassUnderTest.getTheClass2());
    }

    @Test
    void testGetTheClass3() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(int[].class, myClassUnderTest.getTheClass3());
    }

    @Test
    void testGetTheClass4() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(int.class, myClassUnderTest.getTheClass4());
    }

    @Test
    void testGetTheClass5() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(Integer.class, myClassUnderTest.getTheClass5());
    }

    @Test
    void testGetTheClass6() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(Integer[].class, myClassUnderTest.getTheClass6());
    }

    @Test
    void testGetTheClass7() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(Runnable.class, myClassUnderTest.getTheClass7());
    }

    @Test
    void testGetTheFooData1() {
        final MyClass myClassUnderTest = new MyClass();
        assertNull(myClassUnderTest.getTheFooData1());
    }

    @Test
    void testGetTheBoolean1() {
        final MyClass myClassUnderTest = new MyClass();
        assertTrue(myClassUnderTest.getTheBoolean1());
    }

    @Test
    void testGetTheBoolean2() {
        final MyClass myClassUnderTest = new MyClass();
        assertFalse(myClassUnderTest.getTheBoolean2());
    }

    @Test
    void testGetTheBoolean3() {
        final MyClass myClassUnderTest = new MyClass();
        assertTrue(myClassUnderTest.getTheBoolean3());
    }

    @Test
    void testGetTheBoolean4() {
        final MyClass myClassUnderTest = new MyClass();
        assertFalse(myClassUnderTest.getTheBoolean4());
    }

    @Test
    void testGetTheBoolean5() {
        final MyClass myClassUnderTest = new MyClass();
        assertTrue(myClassUnderTest.getTheBoolean5());
    }

    @Test
    void testGetTheBoolean6() {
        final MyClass myClassUnderTest = new MyClass();
        assertFalse(myClassUnderTest.getTheBoolean6());
    }

    @Test
    void testGetTheMaxZoneOffset() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(ZoneOffset.MAX, myClassUnderTest.getTheMaxZoneOffset());
    }

    @Test
    void testGetTheMidnight() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(LocalTime.MIDNIGHT, myClassUnderTest.getTheMidnight());
    }

    @Test
    void testGetTheBigDecimal() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(BigDecimal.TEN, myClassUnderTest.getTheBigDecimal());
    }

    @Test
    void testGetTheBigInt() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(BigInteger.TWO, myClassUnderTest.getTheBigInt());
    }

    @Test
    void testGetTheList1() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(Collections.emptyList(), myClassUnderTest.getTheList1());
    }

    @Test
    void testGetTheList2() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(Collections.emptyList(), myClassUnderTest.getTheList2());
    }

    @Test
    void testGetTheString() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(Optional.empty(), myClassUnderTest.getTheString());
    }

    @Test
    void testGetTheOptInt() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(OptionalInt.empty(), myClassUnderTest.getTheOptInt());
    }

    @Test
    void testGetTheByte1() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals((byte) 2, myClassUnderTest.getTheByte1());
    }

    @Test
    void testGetTheByte2() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals((byte) 2, myClassUnderTest.getTheByte2());
    }

    @Test
    void testGetTheFoo1() {
        final MyClass myClassUnderTest = new MyClass();
        assertNull(myClassUnderTest.getTheFoo1());
    }
}
