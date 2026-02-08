package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.Optional;
import java.util.OptionalInt;

import static org.junit.jupiter.api.Assertions.*;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass();
    }

    @Test
    void testGetStringVal1() {
        assertEquals("Constant1", myClassUnderTest.getStringVal1());
    }

    @Test
    void testGetStringVal2() {
        assertEquals("MyConst1Val", myClassUnderTest.getStringVal2());
    }

    @Test
    void testGetStringVal4() {
        assertEquals("MyConst1Val10OtherVal", myClassUnderTest.getStringVal4());
    }

    @Test
    void testGetStringVal5() {
        assertEquals("class [I", myClassUnderTest.getStringVal5());
    }

    @Test
    void testGetStringArrayVal1() {
        assertArrayEquals(new String[]{"result"}, myClassUnderTest.getStringArrayVal1());
    }

    @Test
    void testGetStringArrayVal2() {
        assertArrayEquals(new String[]{"result"}, myClassUnderTest.getStringArrayVal2());
    }

    @Test
    void testGetMyObjConstantVal1() {
        assertEquals("ObjConstantVal1", myClassUnderTest.getMyObjConstantVal1());
    }

    @Test
    void testGetMyObjConstantVal2() {
        assertEquals("ObjConstantVal1", myClassUnderTest.getMyObjConstantVal2());
    }

    @Test
    void testGetColorVa1() {
        assertEquals(MyClass.Color.Red, myClassUnderTest.getColorVa1());
    }

    @Test
    void testGetColorVal2() {
        assertEquals("lowerCaseName", myClassUnderTest.getColorVal2());
    }

    @Test
    void testGetColorVal3() {
        assertEquals("Red", myClassUnderTest.getColorVal3());
    }

    @Test
    void testGetColorVal4() {
        assertEquals(MyClass.Color.Red, myClassUnderTest.getColorVal4());
    }

    @Test
    void testGetSmallIntVal1() {
        assertEquals(5, myClassUnderTest.getSmallIntVal1());
    }

    @Test
    void testGetSmallIntVal2() {
        assertEquals(5, myClassUnderTest.getSmallIntVal2());
    }

    @Test
    void testGetSmallIntVal3() {
        assertEquals(5, myClassUnderTest.getSmallIntVal3());
    }

    @Test
    void testGetSmallIntVal4() {
        assertEquals(-6, myClassUnderTest.getSmallIntVal4());
    }

    @Test
    void testGetSmallIntVal5() {
        assertEquals(10, myClassUnderTest.getSmallIntVal5());
    }

    @Test
    void testGetSmallIntVal6() {
        assertEquals(20, myClassUnderTest.getSmallIntVal6());
    }

    @Test
    void testGetBigIntVal() {
        assertEquals(Integer.valueOf(5), myClassUnderTest.getBigIntVal());
    }

    @Test
    void testGetDoubleIntVal() {
        assertEquals(5.0, myClassUnderTest.getDoubleIntVal(), 0.0001);
    }

    @Test
    void testGetBigDoubleIntVal1() {
        assertEquals(5.5, myClassUnderTest.getBigDoubleIntVal1(), 0.0001);
    }

    @Test
    void testGetBigDoubleIntVal2() {
        assertEquals(0.0, myClassUnderTest.getBigDoubleIntVal2(), 0.0001);
    }

    @Test
    void testGetBigDoubleIntVal3() {
        assertEquals(Double.NaN, myClassUnderTest.getBigDoubleIntVal3(), 0.0001);
    }

    @Test
    void testGetBigDoubleIntVal4() {
        assertEquals(Double.NEGATIVE_INFINITY, myClassUnderTest.getBigDoubleIntVal4(), 0.0001);
    }

    @Test
    void testGetBigDoubleIntVal5() {
        assertEquals(Double.POSITIVE_INFINITY, myClassUnderTest.getBigDoubleIntVal5(), 0.0001);
    }

    @Test
    void testGetFloatIntVal() {
        assertEquals(5.0f, myClassUnderTest.getFloatIntVal(), 0.0001);
    }

    @Test
    void testGetBigFloatIntVal1() {
        assertEquals(5.5f, myClassUnderTest.getBigFloatIntVal1(), 0.0001);
    }

    @Test
    void testGetBigFloatIntVal2() {
        assertEquals(0.0f, myClassUnderTest.getBigFloatIntVal2(), 0.0001);
    }

    @Test
    void testGetBigFloatIntVal3() {
        assertEquals(Float.NaN, myClassUnderTest.getBigFloatIntVal3(), 0.0001);
    }

    @Test
    void testGetBigFloatIntVal4() {
        assertEquals(Float.NEGATIVE_INFINITY, myClassUnderTest.getBigFloatIntVal4(), 0.0001);
    }

    @Test
    void testGetBigFloatIntVal5() {
        assertEquals(Float.POSITIVE_INFINITY, myClassUnderTest.getBigFloatIntVal5(), 0.0001);
    }

    @Test
    void testGetFloatIntVal6() {
        assertEquals(5.0, myClassUnderTest.getFloatIntVal6(), 0.0001);
    }

    @Test
    void testGetBigFloatIntVal7() {
        assertEquals(5.5f, myClassUnderTest.getBigFloatIntVal7(), 0.0001);
    }

    @Test
    void testGetShortVal1() {
        assertEquals((short) 10, myClassUnderTest.getShortVal1());
    }

    @Test
    void testGetShortVal2() {
        assertEquals(Short.valueOf((short) 10), myClassUnderTest.getShortVal2());
    }

    @Test
    void testGetByteVal1() {
        assertEquals((byte) 2, myClassUnderTest.getByteVal1());
    }

    @Test
    void testGetByteVal2() {
        assertEquals(Byte.valueOf((byte) 2), myClassUnderTest.getByteVal2());
    }

    @Test
    void testGetLongVal1() {
        assertEquals(20L, myClassUnderTest.getLongVal1());
    }

    @Test
    void testGetLongVal2() {
        assertEquals(Long.valueOf(20L), myClassUnderTest.getLongVal2());
    }

    @Test
    void testGetLongVal3() {
        assertEquals(20L, myClassUnderTest.getLongVal3());
    }

    @Test
    void testGetCharVal1() {
        assertEquals('k', myClassUnderTest.getCharVal1());
    }

    @Test
    void testGetCharVal2() {
        assertEquals(Character.valueOf('k'), myClassUnderTest.getCharVal2());
    }

    @Test
    void testGetCharVal3() {
        assertEquals(Character.valueOf('/'), myClassUnderTest.getCharVal3());
    }

    @Test
    void testGetTheClass1() {
        assertEquals(MyClass.class, myClassUnderTest.getTheClass1());
    }

    @Test
    void testGetTheClass2() {
        assertEquals(MyClass.InnerClass.class, myClassUnderTest.getTheClass2());
    }

    @Test
    void testGetTheClass3() {
        assertEquals(int[].class, myClassUnderTest.getTheClass3());
    }

    @Test
    void testGetTheClass4() {
        assertEquals(int.class, myClassUnderTest.getTheClass4());
    }

    @Test
    void testGetTheClass5() {
        assertEquals(Integer.class, myClassUnderTest.getTheClass5());
    }

    @Test
    void testGetTheClass6() {
        assertEquals(Integer[].class, myClassUnderTest.getTheClass6());
    }

    @Test
    void testGetTheClass7() {
        assertEquals(Runnable.class, myClassUnderTest.getTheClass7());
    }

    @Test
    void testGetTheFooData1() {
        assertNull(myClassUnderTest.getTheFooData1());
    }

    @Test
    void testGetTheBoolean1() {
        assertTrue(myClassUnderTest.getTheBoolean1());
    }

    @Test
    void testGetTheBoolean2() {
        assertFalse(myClassUnderTest.getTheBoolean2());
    }

    @Test
    void testGetTheBoolean3() {
        assertTrue(myClassUnderTest.getTheBoolean3());
    }

    @Test
    void testGetTheBoolean4() {
        assertFalse(myClassUnderTest.getTheBoolean4());
    }

    @Test
    void testGetTheBoolean5() {
        assertTrue(myClassUnderTest.getTheBoolean5());
    }

    @Test
    void testGetTheBoolean6() {
        assertFalse(myClassUnderTest.getTheBoolean6());
    }

    @Test
    void testGetTheMaxZoneOffset() {
        assertEquals(ZoneOffset.MAX, myClassUnderTest.getTheMaxZoneOffset());
    }

    @Test
    void testGetTheMidnight() {
        assertEquals(LocalTime.MIDNIGHT, myClassUnderTest.getTheMidnight());
    }

    @Test
    void testGetTheBigDecimal() {
        assertEquals(BigDecimal.TEN, myClassUnderTest.getTheBigDecimal());
    }

    @Test
    void testGetTheBigInt() {
        assertEquals(BigInteger.TWO, myClassUnderTest.getTheBigInt());
    }

    @Test
    void testGetTheList1() {
        assertEquals(Collections.emptyList(), myClassUnderTest.getTheList1());
    }

    @Test
    void testGetTheList2() {
        assertEquals(Collections.emptyList(), myClassUnderTest.getTheList2());
    }

    @Test
    void testGetTheString() {
        assertEquals(Optional.empty(), myClassUnderTest.getTheString());
    }

    @Test
    void testGetTheOptInt() {
        assertEquals(OptionalInt.empty(), myClassUnderTest.getTheOptInt());
    }

    @Test
    void testGetTheCharset() {
        assertEquals(StandardCharsets.UTF_16BE, myClassUnderTest.getTheCharset());
    }

    @Test
    void testGetTheComparator() {
        assertEquals(String.CASE_INSENSITIVE_ORDER, myClassUnderTest.getTheComparator());
    }

    @Test
    void testGetTheEmptyList() {
        assertEquals(Collections.emptyList(), myClassUnderTest.getTheEmptyList());
    }

    @Test
    void testGetTheByte1() {
        assertEquals((byte) 2, myClassUnderTest.getTheByte1());
    }

    @Test
    void testGetTheByte2() {
        assertEquals(Byte.valueOf((byte) 2), myClassUnderTest.getTheByte2());
    }

    @Test
    void testGetTheFoo1() {
        assertNull(myClassUnderTest.getTheFoo1());
    }

    @Test
    void testGetTheXml1() {
        assertEquals("<?xml version=\"1.0\"?>", myClassUnderTest.getTheXml1());
    }

    @Test
    void testGetCommentFollowedByOneOrMoreBlankLinesRegex1() {
        assertEquals(
                "^([\\h]*\\/\\/.*)([\\u000D\\u000A|[\\u000A\\u000B\\u000C\\u000D\\u0085\\u2028\\u2029]][\\h]*)+(\\u000D\\u000A|[\\u000A\\u000B\\u000C\\u000D\\u0085\\u2028\\u2029])",
                MyClass.getCommentFollowedByOneOrMoreBlankLinesRegex1());
    }

    @Test
    void testGetEndOfText1() {
        assertEquals('\u0003', MyClass.getEndOfText1());
    }

    @Test
    void testGetEndOfText2() {
        assertEquals(Character.valueOf('a'), MyClass.getEndOfText2());
    }

    @Test
    void testGetEndOfText3() {
        assertEquals((char) 3, MyClass.getEndOfText3());
    }

    @Test
    void testGetEndOfText4() {
        assertEquals(Character.valueOf((char) 3), MyClass.getEndOfText4());
    }
}
