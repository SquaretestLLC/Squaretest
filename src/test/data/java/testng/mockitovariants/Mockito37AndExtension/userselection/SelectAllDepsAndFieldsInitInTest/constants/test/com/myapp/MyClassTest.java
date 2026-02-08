package com.myapp;

import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.Optional;
import java.util.OptionalInt;

import static org.testng.Assert.*;

public class MyClassTest {

    @Test
    public void testGetStringVal1() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals("Constant1", myClassUnderTest.getStringVal1());
    }

    @Test
    public void testGetStringVal2() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals("MyConst1Val", myClassUnderTest.getStringVal2());
    }

    @Test
    public void testGetStringVal4() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals("MyConst1Val10OtherVal", myClassUnderTest.getStringVal4());
    }

    @Test
    public void testGetStringVal5() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals("class [I", myClassUnderTest.getStringVal5());
    }

    @Test
    public void testGetStringArrayVal1() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(new String[]{"result"}, myClassUnderTest.getStringArrayVal1());
    }

    @Test
    public void testGetStringArrayVal2() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(new String[]{"result"}, myClassUnderTest.getStringArrayVal2());
    }

    @Test
    public void testGetMyObjConstantVal1() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals("ObjConstantVal1", myClassUnderTest.getMyObjConstantVal1());
    }

    @Test
    public void testGetMyObjConstantVal2() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals("ObjConstantVal1", myClassUnderTest.getMyObjConstantVal2());
    }

    @Test
    public void testGetColorVa1() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(MyClass.Color.Red, myClassUnderTest.getColorVa1());
    }

    @Test
    public void testGetColorVal2() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals("lowerCaseName", myClassUnderTest.getColorVal2());
    }

    @Test
    public void testGetColorVal3() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals("Red", myClassUnderTest.getColorVal3());
    }

    @Test
    public void testGetColorVal4() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(MyClass.Color.Red, myClassUnderTest.getColorVal4());
    }

    @Test
    public void testGetSmallIntVal1() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(5, myClassUnderTest.getSmallIntVal1());
    }

    @Test
    public void testGetSmallIntVal2() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(5, myClassUnderTest.getSmallIntVal2());
    }

    @Test
    public void testGetSmallIntVal3() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(5, myClassUnderTest.getSmallIntVal3());
    }

    @Test
    public void testGetSmallIntVal4() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(-6, myClassUnderTest.getSmallIntVal4());
    }

    @Test
    public void testGetSmallIntVal5() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(10, myClassUnderTest.getSmallIntVal5());
    }

    @Test
    public void testGetSmallIntVal6() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(20, myClassUnderTest.getSmallIntVal6());
    }

    @Test
    public void testGetBigIntVal() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(5, myClassUnderTest.getBigIntVal());
    }

    @Test
    public void testGetDoubleIntVal() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(5.0, myClassUnderTest.getDoubleIntVal(), 0.0001);
    }

    @Test
    public void testGetBigDoubleIntVal1() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(5.5, myClassUnderTest.getBigDoubleIntVal1(), 0.0001);
    }

    @Test
    public void testGetBigDoubleIntVal2() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(0.0, myClassUnderTest.getBigDoubleIntVal2(), 0.0001);
    }

    @Test
    public void testGetBigDoubleIntVal3() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(Double.NaN, myClassUnderTest.getBigDoubleIntVal3(), 0.0001);
    }

    @Test
    public void testGetBigDoubleIntVal4() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(Double.NEGATIVE_INFINITY, myClassUnderTest.getBigDoubleIntVal4(), 0.0001);
    }

    @Test
    public void testGetBigDoubleIntVal5() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(Double.POSITIVE_INFINITY, myClassUnderTest.getBigDoubleIntVal5(), 0.0001);
    }

    @Test
    public void testGetFloatIntVal() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(5.0f, myClassUnderTest.getFloatIntVal(), 0.0001);
    }

    @Test
    public void testGetBigFloatIntVal1() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(5.5f, myClassUnderTest.getBigFloatIntVal1(), 0.0001);
    }

    @Test
    public void testGetBigFloatIntVal2() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(0.0f, myClassUnderTest.getBigFloatIntVal2(), 0.0001);
    }

    @Test
    public void testGetBigFloatIntVal3() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(Float.NaN, myClassUnderTest.getBigFloatIntVal3(), 0.0001);
    }

    @Test
    public void testGetBigFloatIntVal4() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(Float.NEGATIVE_INFINITY, myClassUnderTest.getBigFloatIntVal4(), 0.0001);
    }

    @Test
    public void testGetBigFloatIntVal5() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(Float.POSITIVE_INFINITY, myClassUnderTest.getBigFloatIntVal5(), 0.0001);
    }

    @Test
    public void testGetFloatIntVal6() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(5.0, myClassUnderTest.getFloatIntVal6(), 0.0001);
    }

    @Test
    public void testGetBigFloatIntVal7() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(5.5f, myClassUnderTest.getBigFloatIntVal7(), 0.0001);
    }

    @Test
    public void testGetShortVal1() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals((short) 10, myClassUnderTest.getShortVal1());
    }

    @Test
    public void testGetShortVal2() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals((short) 10, myClassUnderTest.getShortVal2());
    }

    @Test
    public void testGetByteVal1() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals((byte) 2, myClassUnderTest.getByteVal1());
    }

    @Test
    public void testGetByteVal2() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals((byte) 2, myClassUnderTest.getByteVal2());
    }

    @Test
    public void testGetLongVal1() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(20L, myClassUnderTest.getLongVal1());
    }

    @Test
    public void testGetLongVal2() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(20L, myClassUnderTest.getLongVal2());
    }

    @Test
    public void testGetLongVal3() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(20L, myClassUnderTest.getLongVal3());
    }

    @Test
    public void testGetCharVal1() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals('k', myClassUnderTest.getCharVal1());
    }

    @Test
    public void testGetCharVal2() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals('k', myClassUnderTest.getCharVal2());
    }

    @Test
    public void testGetCharVal3() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals('/', myClassUnderTest.getCharVal3());
    }

    @Test
    public void testGetTheClass1() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(MyClass.class, myClassUnderTest.getTheClass1());
    }

    @Test
    public void testGetTheClass2() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(MyClass.InnerClass.class, myClassUnderTest.getTheClass2());
    }

    @Test
    public void testGetTheClass3() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(int[].class, myClassUnderTest.getTheClass3());
    }

    @Test
    public void testGetTheClass4() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(int.class, myClassUnderTest.getTheClass4());
    }

    @Test
    public void testGetTheClass5() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(Integer.class, myClassUnderTest.getTheClass5());
    }

    @Test
    public void testGetTheClass6() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(Integer[].class, myClassUnderTest.getTheClass6());
    }

    @Test
    public void testGetTheClass7() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(Runnable.class, myClassUnderTest.getTheClass7());
    }

    @Test
    public void testGetTheFooData1() {
        final MyClass myClassUnderTest = new MyClass();
        assertNull(myClassUnderTest.getTheFooData1());
    }

    @Test
    public void testGetTheBoolean1() {
        final MyClass myClassUnderTest = new MyClass();
        assertTrue(myClassUnderTest.getTheBoolean1());
    }

    @Test
    public void testGetTheBoolean2() {
        final MyClass myClassUnderTest = new MyClass();
        assertFalse(myClassUnderTest.getTheBoolean2());
    }

    @Test
    public void testGetTheBoolean3() {
        final MyClass myClassUnderTest = new MyClass();
        assertTrue(myClassUnderTest.getTheBoolean3());
    }

    @Test
    public void testGetTheBoolean4() {
        final MyClass myClassUnderTest = new MyClass();
        assertFalse(myClassUnderTest.getTheBoolean4());
    }

    @Test
    public void testGetTheBoolean5() {
        final MyClass myClassUnderTest = new MyClass();
        assertTrue(myClassUnderTest.getTheBoolean5());
    }

    @Test
    public void testGetTheBoolean6() {
        final MyClass myClassUnderTest = new MyClass();
        assertFalse(myClassUnderTest.getTheBoolean6());
    }

    @Test
    public void testGetTheMaxZoneOffset() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(ZoneOffset.MAX, myClassUnderTest.getTheMaxZoneOffset());
    }

    @Test
    public void testGetTheMidnight() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(LocalTime.MIDNIGHT, myClassUnderTest.getTheMidnight());
    }

    @Test
    public void testGetTheBigDecimal() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(BigDecimal.TEN, myClassUnderTest.getTheBigDecimal());
    }

    @Test
    public void testGetTheBigInt() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(BigInteger.TWO, myClassUnderTest.getTheBigInt());
    }

    @Test
    public void testGetTheList1() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(Collections.emptyList(), myClassUnderTest.getTheList1());
    }

    @Test
    public void testGetTheList2() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(Collections.emptyList(), myClassUnderTest.getTheList2());
    }

    @Test
    public void testGetTheString() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(Optional.empty(), myClassUnderTest.getTheString());
    }

    @Test
    public void testGetTheOptInt() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals(OptionalInt.empty(), myClassUnderTest.getTheOptInt());
    }

    @Test
    public void testGetTheByte1() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals((byte) 2, myClassUnderTest.getTheByte1());
    }

    @Test
    public void testGetTheByte2() {
        final MyClass myClassUnderTest = new MyClass();
        assertEquals((byte) 2, myClassUnderTest.getTheByte2());
    }

    @Test
    public void testGetTheFoo1() {
        final MyClass myClassUnderTest = new MyClass();
        assertNull(myClassUnderTest.getTheFoo1());
    }
}
