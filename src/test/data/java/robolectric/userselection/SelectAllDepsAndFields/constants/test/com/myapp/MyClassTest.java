package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.Optional;
import java.util.OptionalInt;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class MyClassTest {

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass();
    }

    @Test
    public void testGetStringVal1() {
        assertEquals("Constant1", myClassUnderTest.getStringVal1());
    }

    @Test
    public void testGetStringVal2() {
        assertEquals("MyConst1Val", myClassUnderTest.getStringVal2());
    }

    @Test
    public void testGetStringVal4() {
        assertEquals("MyConst1Val10OtherVal", myClassUnderTest.getStringVal4());
    }

    @Test
    public void testGetStringVal5() {
        assertEquals("class [I", myClassUnderTest.getStringVal5());
    }

    @Test
    public void testGetStringArrayVal1() {
        assertArrayEquals(new String[]{"result"}, myClassUnderTest.getStringArrayVal1());
    }

    @Test
    public void testGetStringArrayVal2() {
        assertArrayEquals(new String[]{"result"}, myClassUnderTest.getStringArrayVal2());
    }

    @Test
    public void testGetMyObjConstantVal1() {
        assertEquals("ObjConstantVal1", myClassUnderTest.getMyObjConstantVal1());
    }

    @Test
    public void testGetMyObjConstantVal2() {
        assertEquals("ObjConstantVal1", myClassUnderTest.getMyObjConstantVal2());
    }

    @Test
    public void testGetColorVa1() {
        assertEquals(MyClass.Color.Red, myClassUnderTest.getColorVa1());
    }

    @Test
    public void testGetColorVal2() {
        assertEquals("lowerCaseName", myClassUnderTest.getColorVal2());
    }

    @Test
    public void testGetColorVal3() {
        assertEquals("Red", myClassUnderTest.getColorVal3());
    }

    @Test
    public void testGetColorVal4() {
        assertEquals(MyClass.Color.Red, myClassUnderTest.getColorVal4());
    }

    @Test
    public void testGetSmallIntVal1() {
        assertEquals(5, myClassUnderTest.getSmallIntVal1());
    }

    @Test
    public void testGetSmallIntVal2() {
        assertEquals(5, myClassUnderTest.getSmallIntVal2());
    }

    @Test
    public void testGetSmallIntVal3() {
        assertEquals(5, myClassUnderTest.getSmallIntVal3());
    }

    @Test
    public void testGetSmallIntVal4() {
        assertEquals(-6, myClassUnderTest.getSmallIntVal4());
    }

    @Test
    public void testGetSmallIntVal5() {
        assertEquals(10, myClassUnderTest.getSmallIntVal5());
    }

    @Test
    public void testGetSmallIntVal6() {
        assertEquals(20, myClassUnderTest.getSmallIntVal6());
    }

    @Test
    public void testGetBigIntVal() {
        assertEquals(Integer.valueOf(5), myClassUnderTest.getBigIntVal());
    }

    @Test
    public void testGetDoubleIntVal() {
        assertEquals(5.0, myClassUnderTest.getDoubleIntVal(), 0.0001);
    }

    @Test
    public void testGetBigDoubleIntVal1() {
        assertEquals(5.5, myClassUnderTest.getBigDoubleIntVal1(), 0.0001);
    }

    @Test
    public void testGetBigDoubleIntVal2() {
        assertEquals(0.0, myClassUnderTest.getBigDoubleIntVal2(), 0.0001);
    }

    @Test
    public void testGetBigDoubleIntVal3() {
        assertEquals(Double.NaN, myClassUnderTest.getBigDoubleIntVal3(), 0.0001);
    }

    @Test
    public void testGetBigDoubleIntVal4() {
        assertEquals(Double.NEGATIVE_INFINITY, myClassUnderTest.getBigDoubleIntVal4(), 0.0001);
    }

    @Test
    public void testGetBigDoubleIntVal5() {
        assertEquals(Double.POSITIVE_INFINITY, myClassUnderTest.getBigDoubleIntVal5(), 0.0001);
    }

    @Test
    public void testGetFloatIntVal() {
        assertEquals(5.0f, myClassUnderTest.getFloatIntVal(), 0.0001);
    }

    @Test
    public void testGetBigFloatIntVal1() {
        assertEquals(5.5f, myClassUnderTest.getBigFloatIntVal1(), 0.0001);
    }

    @Test
    public void testGetBigFloatIntVal2() {
        assertEquals(0.0f, myClassUnderTest.getBigFloatIntVal2(), 0.0001);
    }

    @Test
    public void testGetBigFloatIntVal3() {
        assertEquals(Float.NaN, myClassUnderTest.getBigFloatIntVal3(), 0.0001);
    }

    @Test
    public void testGetBigFloatIntVal4() {
        assertEquals(Float.NEGATIVE_INFINITY, myClassUnderTest.getBigFloatIntVal4(), 0.0001);
    }

    @Test
    public void testGetBigFloatIntVal5() {
        assertEquals(Float.POSITIVE_INFINITY, myClassUnderTest.getBigFloatIntVal5(), 0.0001);
    }

    @Test
    public void testGetFloatIntVal6() {
        assertEquals(5.0, myClassUnderTest.getFloatIntVal6(), 0.0001);
    }

    @Test
    public void testGetBigFloatIntVal7() {
        assertEquals(5.5f, myClassUnderTest.getBigFloatIntVal7(), 0.0001);
    }

    @Test
    public void testGetShortVal1() {
        assertEquals((short) 10, myClassUnderTest.getShortVal1());
    }

    @Test
    public void testGetShortVal2() {
        assertEquals(Short.valueOf((short) 10), myClassUnderTest.getShortVal2());
    }

    @Test
    public void testGetByteVal1() {
        assertEquals((byte) 2, myClassUnderTest.getByteVal1());
    }

    @Test
    public void testGetByteVal2() {
        assertEquals(Byte.valueOf((byte) 2), myClassUnderTest.getByteVal2());
    }

    @Test
    public void testGetLongVal1() {
        assertEquals(20L, myClassUnderTest.getLongVal1());
    }

    @Test
    public void testGetLongVal2() {
        assertEquals(Long.valueOf(20L), myClassUnderTest.getLongVal2());
    }

    @Test
    public void testGetLongVal3() {
        assertEquals(20L, myClassUnderTest.getLongVal3());
    }

    @Test
    public void testGetCharVal1() {
        assertEquals('k', myClassUnderTest.getCharVal1());
    }

    @Test
    public void testGetCharVal2() {
        assertEquals(Character.valueOf('k'), myClassUnderTest.getCharVal2());
    }

    @Test
    public void testGetCharVal3() {
        assertEquals(Character.valueOf('/'), myClassUnderTest.getCharVal3());
    }

    @Test
    public void testGetTheClass1() {
        assertEquals(MyClass.class, myClassUnderTest.getTheClass1());
    }

    @Test
    public void testGetTheClass2() {
        assertEquals(MyClass.InnerClass.class, myClassUnderTest.getTheClass2());
    }

    @Test
    public void testGetTheClass3() {
        assertEquals(int[].class, myClassUnderTest.getTheClass3());
    }

    @Test
    public void testGetTheClass4() {
        assertEquals(int.class, myClassUnderTest.getTheClass4());
    }

    @Test
    public void testGetTheClass5() {
        assertEquals(Integer.class, myClassUnderTest.getTheClass5());
    }

    @Test
    public void testGetTheClass6() {
        assertEquals(Integer[].class, myClassUnderTest.getTheClass6());
    }

    @Test
    public void testGetTheClass7() {
        assertEquals(Runnable.class, myClassUnderTest.getTheClass7());
    }

    @Test
    public void testGetTheFooData1() {
        assertNull(myClassUnderTest.getTheFooData1());
    }

    @Test
    public void testGetTheBoolean1() {
        assertTrue(myClassUnderTest.getTheBoolean1());
    }

    @Test
    public void testGetTheBoolean2() {
        assertFalse(myClassUnderTest.getTheBoolean2());
    }

    @Test
    public void testGetTheBoolean3() {
        assertTrue(myClassUnderTest.getTheBoolean3());
    }

    @Test
    public void testGetTheBoolean4() {
        assertFalse(myClassUnderTest.getTheBoolean4());
    }

    @Test
    public void testGetTheBoolean5() {
        assertTrue(myClassUnderTest.getTheBoolean5());
    }

    @Test
    public void testGetTheBoolean6() {
        assertFalse(myClassUnderTest.getTheBoolean6());
    }

    @Test
    public void testGetTheMaxZoneOffset() {
        assertEquals(ZoneOffset.MAX, myClassUnderTest.getTheMaxZoneOffset());
    }

    @Test
    public void testGetTheMidnight() {
        assertEquals(LocalTime.MIDNIGHT, myClassUnderTest.getTheMidnight());
    }

    @Test
    public void testGetTheBigDecimal() {
        assertEquals(BigDecimal.TEN, myClassUnderTest.getTheBigDecimal());
    }

    @Test
    public void testGetTheBigInt() {
        assertEquals(new BigInteger("100"), myClassUnderTest.getTheBigInt());
    }

    @Test
    public void testGetTheList1() {
        assertEquals(Collections.emptyList(), myClassUnderTest.getTheList1());
    }

    @Test
    public void testGetTheList2() {
        assertEquals(Collections.emptyList(), myClassUnderTest.getTheList2());
    }

    @Test
    public void testGetTheString() {
        assertEquals(Optional.empty(), myClassUnderTest.getTheString());
    }

    @Test
    public void testGetTheOptInt() {
        assertEquals(OptionalInt.empty(), myClassUnderTest.getTheOptInt());
    }

    @Test
    public void testGetTheByte1() {
        assertEquals((byte) 2, myClassUnderTest.getTheByte1());
    }

    @Test
    public void testGetTheByte2() {
        assertEquals(Byte.valueOf((byte) 2), myClassUnderTest.getTheByte2());
    }

    @Test
    public void testGetTheFoo1() {
        assertNull(myClassUnderTest.getTheFoo1());
    }
}
