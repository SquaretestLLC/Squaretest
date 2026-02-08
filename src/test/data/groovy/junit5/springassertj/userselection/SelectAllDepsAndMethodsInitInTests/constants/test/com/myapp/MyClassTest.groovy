package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.Test

import java.time.LocalTime
import java.time.ZoneOffset

import static org.assertj.core.api.Assertions.assertThat
import static org.assertj.core.api.Assertions.within

@CompileStatic
class MyClassTest {

    @Test
    void testGetStringVal1() {
        def myClassUnderTest = new MyClass()
        assertThat(myClassUnderTest.getStringVal1()).isEqualTo("Constant1")
    }

    @Test
    void testGetStringVal2() {
        def myClassUnderTest = new MyClass()
        assertThat(myClassUnderTest.getStringVal2()).isEqualTo("MyConst1Val")
    }

    @Test
    void testGetStringVal4() {
        def myClassUnderTest = new MyClass()
        assertThat(myClassUnderTest.getStringVal4()).isEqualTo("MyConst1Val10OtherVal")
    }

    @Test
    void testGetStringVal5() {
        def myClassUnderTest = new MyClass()
        assertThat(myClassUnderTest.getStringVal5()).isEqualTo("class [I")
    }

    @Test
    void testGetStringArrayVal1() {
        def myClassUnderTest = new MyClass()
        assertThat(myClassUnderTest.getStringArrayVal1()).isEqualTo(["result"] as String[])
    }

    @Test
    void testGetStringArrayVal2() {
        def myClassUnderTest = new MyClass()
        assertThat(myClassUnderTest.getStringArrayVal2()).isEqualTo(["result"] as String[])
    }

    @Test
    void testGetMyObjConstantVal1() {
        def myClassUnderTest = new MyClass()
        assertThat(myClassUnderTest.getMyObjConstantVal1()).isEqualTo("ObjConstantVal1")
    }

    @Test
    void testGetMyObjConstantVal2() {
        def myClassUnderTest = new MyClass()
        assertThat(myClassUnderTest.getMyObjConstantVal2()).isEqualTo("ObjConstantVal1")
    }

    @Test
    void testGetColorVa1() {
        def myClassUnderTest = new MyClass()
        assertThat(myClassUnderTest.getColorVa1()).isEqualTo(MyClass.Color.Red)
    }

    @Test
    void testGetColorVal2() {
        def myClassUnderTest = new MyClass()
        assertThat(myClassUnderTest.getColorVal2()).isEqualTo("lowerCaseName")
    }

    @Test
    void testGetColorVal3() {
        def myClassUnderTest = new MyClass()
        assertThat(myClassUnderTest.getColorVal3()).isEqualTo("Red")
    }

    @Test
    void testGetColorVal4() {
        def myClassUnderTest = new MyClass()
        assertThat(myClassUnderTest.getColorVal4()).isEqualTo(MyClass.Color.Red)
    }

    @Test
    void testGetSmallIntVal1() {
        def myClassUnderTest = new MyClass()
        assertThat(myClassUnderTest.getSmallIntVal1()).isEqualTo(5)
    }

    @Test
    void testGetSmallIntVal2() {
        def myClassUnderTest = new MyClass()
        assertThat(myClassUnderTest.getSmallIntVal2()).isEqualTo(5)
    }

    @Test
    void testGetSmallIntVal3() {
        def myClassUnderTest = new MyClass()
        assertThat(myClassUnderTest.getSmallIntVal3()).isEqualTo(5)
    }

    @Test
    void testGetSmallIntVal4() {
        def myClassUnderTest = new MyClass()
        assertThat(myClassUnderTest.getSmallIntVal4()).isEqualTo(-6)
    }

    @Test
    void testGetSmallIntVal5() {
        def myClassUnderTest = new MyClass()
        assertThat(myClassUnderTest.getSmallIntVal5()).isEqualTo(10)
    }

    @Test
    void testGetSmallIntVal6() {
        def myClassUnderTest = new MyClass()
        assertThat(myClassUnderTest.getSmallIntVal6()).isEqualTo(20)
    }

    @Test
    void testGetBigIntVal() {
        def myClassUnderTest = new MyClass()
        assertThat(myClassUnderTest.getBigIntVal()).isEqualTo(5)
    }

    @Test
    void testGetDoubleIntVal() {
        def myClassUnderTest = new MyClass()
        assertThat(myClassUnderTest.getDoubleIntVal()).isEqualTo(5.0d, within(0.0001))
    }

    @Test
    void testGetBigDoubleIntVal1() {
        def myClassUnderTest = new MyClass()
        assertThat(myClassUnderTest.getBigDoubleIntVal1()).isEqualTo(5.5d, within(0.0001))
    }

    @Test
    void testGetBigDoubleIntVal2() {
        def myClassUnderTest = new MyClass()
        assertThat(myClassUnderTest.getBigDoubleIntVal2()).isEqualTo(0.0d, within(0.0001))
    }

    @Test
    void testGetBigDoubleIntVal3() {
        def myClassUnderTest = new MyClass()
        assertThat(myClassUnderTest.getBigDoubleIntVal3()).isEqualTo(Double.NaN, within(0.0001))
    }

    @Test
    void testGetBigDoubleIntVal4() {
        def myClassUnderTest = new MyClass()
        assertThat(myClassUnderTest.getBigDoubleIntVal4()).isEqualTo(Double.NEGATIVE_INFINITY, within(0.0001))
    }

    @Test
    void testGetBigDoubleIntVal5() {
        def myClassUnderTest = new MyClass()
        assertThat(myClassUnderTest.getBigDoubleIntVal5()).isEqualTo(Double.POSITIVE_INFINITY, within(0.0001))
    }

    @Test
    void testGetFloatIntVal() {
        def myClassUnderTest = new MyClass()
        assertThat(myClassUnderTest.getFloatIntVal()).isEqualTo(5.0f, within(0.0001f))
    }

    @Test
    void testGetBigFloatIntVal1() {
        def myClassUnderTest = new MyClass()
        assertThat(myClassUnderTest.getBigFloatIntVal1()).isEqualTo(5.5f, within(0.0001f))
    }

    @Test
    void testGetBigFloatIntVal2() {
        def myClassUnderTest = new MyClass()
        assertThat(myClassUnderTest.getBigFloatIntVal2()).isEqualTo(0.0f, within(0.0001f))
    }

    @Test
    void testGetBigFloatIntVal3() {
        def myClassUnderTest = new MyClass()
        assertThat(myClassUnderTest.getBigFloatIntVal3()).isEqualTo(Float.NaN, within(0.0001f))
    }

    @Test
    void testGetBigFloatIntVal4() {
        def myClassUnderTest = new MyClass()
        assertThat(myClassUnderTest.getBigFloatIntVal4()).isEqualTo(Float.NEGATIVE_INFINITY, within(0.0001f))
    }

    @Test
    void testGetBigFloatIntVal5() {
        def myClassUnderTest = new MyClass()
        assertThat(myClassUnderTest.getBigFloatIntVal5()).isEqualTo(Float.POSITIVE_INFINITY, within(0.0001f))
    }

    @Test
    void testGetFloatIntVal6() {
        def myClassUnderTest = new MyClass()
        assertThat(myClassUnderTest.getFloatIntVal6()).isEqualTo(5.0d, within(0.0001f))
    }

    @Test
    void testGetBigFloatIntVal7() {
        def myClassUnderTest = new MyClass()
        assertThat(myClassUnderTest.getBigFloatIntVal7()).isEqualTo(5.5f, within(0.0001f))
    }

    @Test
    void testGetShortVal1() {
        def myClassUnderTest = new MyClass()
        assertThat(myClassUnderTest.getShortVal1()).isEqualTo((short) 10)
    }

    @Test
    void testGetShortVal2() {
        def myClassUnderTest = new MyClass()
        assertThat(myClassUnderTest.getShortVal2()).isEqualTo((short) 10)
    }

    @Test
    void testGetByteVal1() {
        def myClassUnderTest = new MyClass()
        assertThat(myClassUnderTest.getByteVal1()).isEqualTo((byte) 2)
    }

    @Test
    void testGetByteVal2() {
        def myClassUnderTest = new MyClass()
        assertThat(myClassUnderTest.getByteVal2()).isEqualTo((byte) 2)
    }

    @Test
    void testGetLongVal1() {
        def myClassUnderTest = new MyClass()
        assertThat(myClassUnderTest.getLongVal1()).isEqualTo(20L)
    }

    @Test
    void testGetLongVal2() {
        def myClassUnderTest = new MyClass()
        assertThat(myClassUnderTest.getLongVal2()).isEqualTo(20L)
    }

    @Test
    void testGetLongVal3() {
        def myClassUnderTest = new MyClass()
        assertThat(myClassUnderTest.getLongVal3()).isEqualTo(20L)
    }

    @Test
    void testGetCharVal1() {
        def myClassUnderTest = new MyClass()
        assertThat(myClassUnderTest.getCharVal1()).isEqualTo('k')
    }

    @Test
    void testGetCharVal2() {
        def myClassUnderTest = new MyClass()
        assertThat(myClassUnderTest.getCharVal2()).isEqualTo('k')
    }

    @Test
    void testGetCharVal3() {
        def myClassUnderTest = new MyClass()
        assertThat(myClassUnderTest.getCharVal3()).isEqualTo('/')
    }

    @Test
    void testGetTheClass1() {
        def myClassUnderTest = new MyClass()
        assertThat(myClassUnderTest.getTheClass1()).isEqualTo(MyClass.class)
    }

    @Test
    void testGetTheClass2() {
        def myClassUnderTest = new MyClass()
        assertThat(myClassUnderTest.getTheClass2()).isEqualTo(MyClass.InnerClass.class)
    }

    @Test
    void testGetTheClass3() {
        def myClassUnderTest = new MyClass()
        assertThat(myClassUnderTest.getTheClass3()).isEqualTo(int[].class)
    }

    @Test
    void testGetTheClass4() {
        def myClassUnderTest = new MyClass()
        assertThat(myClassUnderTest.getTheClass4()).isEqualTo(int.class)
    }

    @Test
    void testGetTheClass5() {
        def myClassUnderTest = new MyClass()
        assertThat(myClassUnderTest.getTheClass5()).isEqualTo(Integer.class)
    }

    @Test
    void testGetTheClass6() {
        def myClassUnderTest = new MyClass()
        assertThat(myClassUnderTest.getTheClass6()).isEqualTo(Integer[].class)
    }

    @Test
    void testGetTheClass7() {
        def myClassUnderTest = new MyClass()
        assertThat(myClassUnderTest.getTheClass7()).isEqualTo(Runnable.class)
    }

    @Test
    void testGetTheFooData1() {
        def myClassUnderTest = new MyClass()
        assertThat(myClassUnderTest.getTheFooData1()).isNull()
    }

    @Test
    void testGetTheBoolean1() {
        def myClassUnderTest = new MyClass()
        assertThat(myClassUnderTest.getTheBoolean1()).isTrue()
    }

    @Test
    void testGetTheBoolean2() {
        def myClassUnderTest = new MyClass()
        assertThat(myClassUnderTest.getTheBoolean2()).isFalse()
    }

    @Test
    void testGetTheBoolean3() {
        def myClassUnderTest = new MyClass()
        assertThat(myClassUnderTest.getTheBoolean3()).isTrue()
    }

    @Test
    void testGetTheBoolean4() {
        def myClassUnderTest = new MyClass()
        assertThat(myClassUnderTest.getTheBoolean4()).isFalse()
    }

    @Test
    void testGetTheBoolean5() {
        def myClassUnderTest = new MyClass()
        assertThat(myClassUnderTest.getTheBoolean5()).isTrue()
    }

    @Test
    void testGetTheBoolean6() {
        def myClassUnderTest = new MyClass()
        assertThat(myClassUnderTest.getTheBoolean6()).isFalse()
    }

    @Test
    void testGetTheMaxZoneOffset() {
        def myClassUnderTest = new MyClass()
        assertThat(myClassUnderTest.getTheMaxZoneOffset()).isEqualTo(ZoneOffset.MAX)
    }

    @Test
    void testGetTheMidnight() {
        def myClassUnderTest = new MyClass()
        assertThat(myClassUnderTest.getTheMidnight()).isEqualTo(LocalTime.MIDNIGHT)
    }

    @Test
    void testGetTheBigDecimal() {
        def myClassUnderTest = new MyClass()
        assertThat(myClassUnderTest.getTheBigDecimal()).isEqualTo(BigDecimal.TEN)
    }

    @Test
    void testGetTheBigInt() {
        def myClassUnderTest = new MyClass()
        assertThat(myClassUnderTest.getTheBigInt()).isEqualTo(BigInteger.TWO)
    }

    @Test
    void testGetTheList1() {
        def myClassUnderTest = new MyClass()
        assertThat(myClassUnderTest.getTheList1()).isEqualTo([])
    }

    @Test
    void testGetTheList2() {
        def myClassUnderTest = new MyClass()
        assertThat(myClassUnderTest.getTheList2()).isEqualTo([])
    }

    @Test
    void testGetTheString() {
        def myClassUnderTest = new MyClass()
        assertThat(myClassUnderTest.getTheString()).isEmpty()
    }

    @Test
    void testGetTheOptInt() {
        def myClassUnderTest = new MyClass()
        assertThat(myClassUnderTest.getTheOptInt()).isEmpty()
    }

    @Test
    void testGetTheByte1() {
        def myClassUnderTest = new MyClass()
        assertThat(myClassUnderTest.getTheByte1()).isEqualTo((byte) 2)
    }

    @Test
    void testGetTheByte2() {
        def myClassUnderTest = new MyClass()
        assertThat(myClassUnderTest.getTheByte2()).isEqualTo((byte) 2)
    }

    @Test
    void testGetTheFoo1() {
        def myClassUnderTest = new MyClass()
        assertThat(myClassUnderTest.getTheFoo1()).isNull()
    }
}
