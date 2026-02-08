package com.myapp;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

public class MyClassTest {

    @Test
    public void testGetStringVal1() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getStringVal1()).isEqualTo("Constant1");
    }

    @Test
    public void testGetStringVal2() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getStringVal2()).isEqualTo("MyConst1Val");
    }

    @Test
    public void testGetStringVal4() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getStringVal4()).isEqualTo("MyConst1Val10OtherVal");
    }

    @Test
    public void testGetStringVal5() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getStringVal5()).isEqualTo("class [I");
    }

    @Test
    public void testGetStringArrayVal1() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getStringArrayVal1()).isEqualTo(new String[]{"result"});
    }

    @Test
    public void testGetStringArrayVal2() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getStringArrayVal2()).isEqualTo(new String[]{"result"});
    }

    @Test
    public void testGetMyObjConstantVal1() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getMyObjConstantVal1()).isEqualTo("ObjConstantVal1");
    }

    @Test
    public void testGetMyObjConstantVal2() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getMyObjConstantVal2()).isEqualTo("ObjConstantVal1");
    }

    @Test
    public void testGetColorVa1() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getColorVa1()).isEqualTo(MyClass.Color.Red);
    }

    @Test
    public void testGetColorVal2() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getColorVal2()).isEqualTo("lowerCaseName");
    }

    @Test
    public void testGetColorVal3() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getColorVal3()).isEqualTo("Red");
    }

    @Test
    public void testGetColorVal4() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getColorVal4()).isEqualTo(MyClass.Color.Red);
    }

    @Test
    public void testGetSmallIntVal1() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getSmallIntVal1()).isEqualTo(5);
    }

    @Test
    public void testGetSmallIntVal2() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getSmallIntVal2()).isEqualTo(5);
    }

    @Test
    public void testGetSmallIntVal3() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getSmallIntVal3()).isEqualTo(5);
    }

    @Test
    public void testGetSmallIntVal4() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getSmallIntVal4()).isEqualTo(-6);
    }

    @Test
    public void testGetSmallIntVal5() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getSmallIntVal5()).isEqualTo(10);
    }

    @Test
    public void testGetSmallIntVal6() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getSmallIntVal6()).isEqualTo(20);
    }

    @Test
    public void testGetBigIntVal() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getBigIntVal()).isEqualTo(5);
    }

    @Test
    public void testGetDoubleIntVal() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getDoubleIntVal()).isEqualTo(5.0, within(0.0001));
    }

    @Test
    public void testGetBigDoubleIntVal1() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getBigDoubleIntVal1()).isEqualTo(5.5, within(0.0001));
    }

    @Test
    public void testGetBigDoubleIntVal2() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getBigDoubleIntVal2()).isEqualTo(0.0, within(0.0001));
    }

    @Test
    public void testGetBigDoubleIntVal3() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getBigDoubleIntVal3()).isEqualTo(Double.NaN, within(0.0001));
    }

    @Test
    public void testGetBigDoubleIntVal4() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getBigDoubleIntVal4()).isEqualTo(Double.NEGATIVE_INFINITY, within(0.0001));
    }

    @Test
    public void testGetBigDoubleIntVal5() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getBigDoubleIntVal5()).isEqualTo(Double.POSITIVE_INFINITY, within(0.0001));
    }

    @Test
    public void testGetFloatIntVal() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getFloatIntVal()).isEqualTo(5.0f, within(0.0001f));
    }

    @Test
    public void testGetBigFloatIntVal1() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getBigFloatIntVal1()).isEqualTo(5.5f, within(0.0001f));
    }

    @Test
    public void testGetBigFloatIntVal2() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getBigFloatIntVal2()).isEqualTo(0.0f, within(0.0001f));
    }

    @Test
    public void testGetBigFloatIntVal3() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getBigFloatIntVal3()).isEqualTo(Float.NaN, within(0.0001f));
    }

    @Test
    public void testGetBigFloatIntVal4() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getBigFloatIntVal4()).isEqualTo(Float.NEGATIVE_INFINITY, within(0.0001f));
    }

    @Test
    public void testGetBigFloatIntVal5() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getBigFloatIntVal5()).isEqualTo(Float.POSITIVE_INFINITY, within(0.0001f));
    }

    @Test
    public void testGetFloatIntVal6() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getFloatIntVal6()).isEqualTo(5.0, within(0.0001f));
    }

    @Test
    public void testGetBigFloatIntVal7() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getBigFloatIntVal7()).isEqualTo(5.5f, within(0.0001f));
    }

    @Test
    public void testGetShortVal1() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getShortVal1()).isEqualTo((short) 10);
    }

    @Test
    public void testGetShortVal2() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getShortVal2()).isEqualTo((short) 10);
    }

    @Test
    public void testGetByteVal1() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getByteVal1()).isEqualTo((byte) 2);
    }

    @Test
    public void testGetByteVal2() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getByteVal2()).isEqualTo((byte) 2);
    }

    @Test
    public void testGetLongVal1() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getLongVal1()).isEqualTo(20L);
    }

    @Test
    public void testGetLongVal2() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getLongVal2()).isEqualTo(20L);
    }

    @Test
    public void testGetLongVal3() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getLongVal3()).isEqualTo(20L);
    }

    @Test
    public void testGetCharVal1() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getCharVal1()).isEqualTo('k');
    }

    @Test
    public void testGetCharVal2() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getCharVal2()).isEqualTo('k');
    }

    @Test
    public void testGetCharVal3() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getCharVal3()).isEqualTo('/');
    }

    @Test
    public void testGetTheClass1() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getTheClass1()).isEqualTo(MyClass.class);
    }

    @Test
    public void testGetTheClass2() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getTheClass2()).isEqualTo(MyClass.InnerClass.class);
    }

    @Test
    public void testGetTheClass3() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getTheClass3()).isEqualTo(int[].class);
    }

    @Test
    public void testGetTheClass4() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getTheClass4()).isEqualTo(int.class);
    }

    @Test
    public void testGetTheClass5() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getTheClass5()).isEqualTo(Integer.class);
    }

    @Test
    public void testGetTheClass6() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getTheClass6()).isEqualTo(Integer[].class);
    }

    @Test
    public void testGetTheClass7() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getTheClass7()).isEqualTo(Runnable.class);
    }

    @Test
    public void testGetTheFooData1() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getTheFooData1()).isNull();
    }

    @Test
    public void testGetTheBoolean1() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getTheBoolean1()).isTrue();
    }

    @Test
    public void testGetTheBoolean2() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getTheBoolean2()).isFalse();
    }

    @Test
    public void testGetTheBoolean3() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getTheBoolean3()).isTrue();
    }

    @Test
    public void testGetTheBoolean4() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getTheBoolean4()).isFalse();
    }

    @Test
    public void testGetTheBoolean5() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getTheBoolean5()).isTrue();
    }

    @Test
    public void testGetTheBoolean6() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getTheBoolean6()).isFalse();
    }

    @Test
    public void testGetTheMaxZoneOffset() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getTheMaxZoneOffset()).isEqualTo(ZoneOffset.MAX);
    }

    @Test
    public void testGetTheMidnight() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getTheMidnight()).isEqualTo(LocalTime.MIDNIGHT);
    }

    @Test
    public void testGetTheBigDecimal() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getTheBigDecimal()).isEqualTo(BigDecimal.TEN);
    }

    @Test
    public void testGetTheBigInt() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getTheBigInt()).isEqualTo(BigInteger.TWO);
    }

    @Test
    public void testGetTheList1() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getTheList1()).isEqualTo(Collections.emptyList());
    }

    @Test
    public void testGetTheList2() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getTheList2()).isEqualTo(Collections.emptyList());
    }

    @Test
    public void testGetTheString() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getTheString()).isEmpty();
    }

    @Test
    public void testGetTheOptInt() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getTheOptInt()).isEmpty();
    }

    @Test
    public void testGetTheByte1() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getTheByte1()).isEqualTo((byte) 2);
    }

    @Test
    public void testGetTheByte2() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getTheByte2()).isEqualTo((byte) 2);
    }

    @Test
    public void testGetTheFoo1() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getTheFoo1()).isNull();
    }
}
