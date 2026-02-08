package com.myapp;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

public class MyClassTest {

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass();
    }

    @Test
    public void testGetStringVal1() {
        assertThat(myClassUnderTest.getStringVal1()).isEqualTo("Constant1");
    }

    @Test
    public void testGetStringVal2() {
        assertThat(myClassUnderTest.getStringVal2()).isEqualTo("MyConst1Val");
    }

    @Test
    public void testGetStringVal4() {
        assertThat(myClassUnderTest.getStringVal4()).isEqualTo("MyConst1Val10OtherVal");
    }

    @Test
    public void testGetStringVal5() {
        assertThat(myClassUnderTest.getStringVal5()).isEqualTo("class [I");
    }

    @Test
    public void testGetStringArrayVal1() {
        assertThat(myClassUnderTest.getStringArrayVal1()).isEqualTo(new String[]{"result"});
    }

    @Test
    public void testGetStringArrayVal2() {
        assertThat(myClassUnderTest.getStringArrayVal2()).isEqualTo(new String[]{"result"});
    }

    @Test
    public void testGetMyObjConstantVal1() {
        assertThat(myClassUnderTest.getMyObjConstantVal1()).isEqualTo("ObjConstantVal1");
    }

    @Test
    public void testGetMyObjConstantVal2() {
        assertThat(myClassUnderTest.getMyObjConstantVal2()).isEqualTo("ObjConstantVal1");
    }

    @Test
    public void testGetColorVa1() {
        assertThat(myClassUnderTest.getColorVa1()).isEqualTo(MyClass.Color.Red);
    }

    @Test
    public void testGetColorVal2() {
        assertThat(myClassUnderTest.getColorVal2()).isEqualTo("lowerCaseName");
    }

    @Test
    public void testGetColorVal3() {
        assertThat(myClassUnderTest.getColorVal3()).isEqualTo("Red");
    }

    @Test
    public void testGetColorVal4() {
        assertThat(myClassUnderTest.getColorVal4()).isEqualTo(MyClass.Color.Red);
    }

    @Test
    public void testGetSmallIntVal1() {
        assertThat(myClassUnderTest.getSmallIntVal1()).isEqualTo(5);
    }

    @Test
    public void testGetSmallIntVal2() {
        assertThat(myClassUnderTest.getSmallIntVal2()).isEqualTo(5);
    }

    @Test
    public void testGetSmallIntVal3() {
        assertThat(myClassUnderTest.getSmallIntVal3()).isEqualTo(5);
    }

    @Test
    public void testGetSmallIntVal4() {
        assertThat(myClassUnderTest.getSmallIntVal4()).isEqualTo(-6);
    }

    @Test
    public void testGetSmallIntVal5() {
        assertThat(myClassUnderTest.getSmallIntVal5()).isEqualTo(10);
    }

    @Test
    public void testGetSmallIntVal6() {
        assertThat(myClassUnderTest.getSmallIntVal6()).isEqualTo(20);
    }

    @Test
    public void testGetBigIntVal() {
        assertThat(myClassUnderTest.getBigIntVal()).isEqualTo(5);
    }

    @Test
    public void testGetDoubleIntVal() {
        assertThat(myClassUnderTest.getDoubleIntVal()).isEqualTo(5.0, within(0.0001));
    }

    @Test
    public void testGetBigDoubleIntVal1() {
        assertThat(myClassUnderTest.getBigDoubleIntVal1()).isEqualTo(5.5, within(0.0001));
    }

    @Test
    public void testGetBigDoubleIntVal2() {
        assertThat(myClassUnderTest.getBigDoubleIntVal2()).isEqualTo(0.0, within(0.0001));
    }

    @Test
    public void testGetBigDoubleIntVal3() {
        assertThat(myClassUnderTest.getBigDoubleIntVal3()).isEqualTo(Double.NaN, within(0.0001));
    }

    @Test
    public void testGetBigDoubleIntVal4() {
        assertThat(myClassUnderTest.getBigDoubleIntVal4()).isEqualTo(Double.NEGATIVE_INFINITY, within(0.0001));
    }

    @Test
    public void testGetBigDoubleIntVal5() {
        assertThat(myClassUnderTest.getBigDoubleIntVal5()).isEqualTo(Double.POSITIVE_INFINITY, within(0.0001));
    }

    @Test
    public void testGetFloatIntVal() {
        assertThat(myClassUnderTest.getFloatIntVal()).isEqualTo(5.0f, within(0.0001f));
    }

    @Test
    public void testGetBigFloatIntVal1() {
        assertThat(myClassUnderTest.getBigFloatIntVal1()).isEqualTo(5.5f, within(0.0001f));
    }

    @Test
    public void testGetBigFloatIntVal2() {
        assertThat(myClassUnderTest.getBigFloatIntVal2()).isEqualTo(0.0f, within(0.0001f));
    }

    @Test
    public void testGetBigFloatIntVal3() {
        assertThat(myClassUnderTest.getBigFloatIntVal3()).isEqualTo(Float.NaN, within(0.0001f));
    }

    @Test
    public void testGetBigFloatIntVal4() {
        assertThat(myClassUnderTest.getBigFloatIntVal4()).isEqualTo(Float.NEGATIVE_INFINITY, within(0.0001f));
    }

    @Test
    public void testGetBigFloatIntVal5() {
        assertThat(myClassUnderTest.getBigFloatIntVal5()).isEqualTo(Float.POSITIVE_INFINITY, within(0.0001f));
    }

    @Test
    public void testGetFloatIntVal6() {
        assertThat(myClassUnderTest.getFloatIntVal6()).isEqualTo(5.0, within(0.0001f));
    }

    @Test
    public void testGetBigFloatIntVal7() {
        assertThat(myClassUnderTest.getBigFloatIntVal7()).isEqualTo(5.5f, within(0.0001f));
    }

    @Test
    public void testGetShortVal1() {
        assertThat(myClassUnderTest.getShortVal1()).isEqualTo((short) 10);
    }

    @Test
    public void testGetShortVal2() {
        assertThat(myClassUnderTest.getShortVal2()).isEqualTo((short) 10);
    }

    @Test
    public void testGetByteVal1() {
        assertThat(myClassUnderTest.getByteVal1()).isEqualTo((byte) 2);
    }

    @Test
    public void testGetByteVal2() {
        assertThat(myClassUnderTest.getByteVal2()).isEqualTo((byte) 2);
    }

    @Test
    public void testGetLongVal1() {
        assertThat(myClassUnderTest.getLongVal1()).isEqualTo(20L);
    }

    @Test
    public void testGetLongVal2() {
        assertThat(myClassUnderTest.getLongVal2()).isEqualTo(20L);
    }

    @Test
    public void testGetLongVal3() {
        assertThat(myClassUnderTest.getLongVal3()).isEqualTo(20L);
    }

    @Test
    public void testGetCharVal1() {
        assertThat(myClassUnderTest.getCharVal1()).isEqualTo('k');
    }

    @Test
    public void testGetCharVal2() {
        assertThat(myClassUnderTest.getCharVal2()).isEqualTo('k');
    }

    @Test
    public void testGetCharVal3() {
        assertThat(myClassUnderTest.getCharVal3()).isEqualTo('/');
    }

    @Test
    public void testGetTheClass1() {
        assertThat(myClassUnderTest.getTheClass1()).isEqualTo(MyClass.class);
    }

    @Test
    public void testGetTheClass2() {
        assertThat(myClassUnderTest.getTheClass2()).isEqualTo(MyClass.InnerClass.class);
    }

    @Test
    public void testGetTheClass3() {
        assertThat(myClassUnderTest.getTheClass3()).isEqualTo(int[].class);
    }

    @Test
    public void testGetTheClass4() {
        assertThat(myClassUnderTest.getTheClass4()).isEqualTo(int.class);
    }

    @Test
    public void testGetTheClass5() {
        assertThat(myClassUnderTest.getTheClass5()).isEqualTo(Integer.class);
    }

    @Test
    public void testGetTheClass6() {
        assertThat(myClassUnderTest.getTheClass6()).isEqualTo(Integer[].class);
    }

    @Test
    public void testGetTheClass7() {
        assertThat(myClassUnderTest.getTheClass7()).isEqualTo(Runnable.class);
    }

    @Test
    public void testGetTheFooData1() {
        assertThat(myClassUnderTest.getTheFooData1()).isNull();
    }

    @Test
    public void testGetTheBoolean1() {
        assertThat(myClassUnderTest.getTheBoolean1()).isTrue();
    }

    @Test
    public void testGetTheBoolean2() {
        assertThat(myClassUnderTest.getTheBoolean2()).isFalse();
    }

    @Test
    public void testGetTheBoolean3() {
        assertThat(myClassUnderTest.getTheBoolean3()).isTrue();
    }

    @Test
    public void testGetTheBoolean4() {
        assertThat(myClassUnderTest.getTheBoolean4()).isFalse();
    }

    @Test
    public void testGetTheBoolean5() {
        assertThat(myClassUnderTest.getTheBoolean5()).isTrue();
    }

    @Test
    public void testGetTheBoolean6() {
        assertThat(myClassUnderTest.getTheBoolean6()).isFalse();
    }

    @Test
    public void testGetTheMaxZoneOffset() {
        assertThat(myClassUnderTest.getTheMaxZoneOffset()).isEqualTo(ZoneOffset.MAX);
    }

    @Test
    public void testGetTheMidnight() {
        assertThat(myClassUnderTest.getTheMidnight()).isEqualTo(LocalTime.MIDNIGHT);
    }

    @Test
    public void testGetTheBigDecimal() {
        assertThat(myClassUnderTest.getTheBigDecimal()).isEqualTo(BigDecimal.TEN);
    }

    @Test
    public void testGetTheBigInt() {
        assertThat(myClassUnderTest.getTheBigInt()).isEqualTo(BigInteger.TWO);
    }

    @Test
    public void testGetTheList1() {
        assertThat(myClassUnderTest.getTheList1()).isEqualTo(Collections.emptyList());
    }

    @Test
    public void testGetTheList2() {
        assertThat(myClassUnderTest.getTheList2()).isEqualTo(Collections.emptyList());
    }

    @Test
    public void testGetTheString() {
        assertThat(myClassUnderTest.getTheString()).isEmpty();
    }

    @Test
    public void testGetTheOptInt() {
        assertThat(myClassUnderTest.getTheOptInt()).isEmpty();
    }

    @Test
    public void testGetTheByte1() {
        assertThat(myClassUnderTest.getTheByte1()).isEqualTo((byte) 2);
    }

    @Test
    public void testGetTheByte2() {
        assertThat(myClassUnderTest.getTheByte2()).isEqualTo((byte) 2);
    }

    @Test
    public void testGetTheFoo1() {
        assertThat(myClassUnderTest.getTheFoo1()).isNull();
    }
}
