package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import java.time.LocalTime
import java.time.ZoneOffset

import static org.assertj.core.api.Assertions.assertThat
import static org.assertj.core.api.Assertions.within

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass()
    }

    @Test
    void testGetStringVal1() {
        assertThat(myClassUnderTest.getStringVal1()).isEqualTo("Constant1")
    }

    @Test
    void testGetStringVal2() {
        assertThat(myClassUnderTest.getStringVal2()).isEqualTo("MyConst1Val")
    }

    @Test
    void testGetStringVal4() {
        assertThat(myClassUnderTest.getStringVal4()).isEqualTo("MyConst1Val10OtherVal")
    }

    @Test
    void testGetStringVal5() {
        assertThat(myClassUnderTest.getStringVal5()).isEqualTo("class [I")
    }

    @Test
    void testGetStringArrayVal1() {
        assertThat(myClassUnderTest.getStringArrayVal1()).isEqualTo(["result"] as String[])
    }

    @Test
    void testGetStringArrayVal2() {
        assertThat(myClassUnderTest.getStringArrayVal2()).isEqualTo(["result"] as String[])
    }

    @Test
    void testGetMyObjConstantVal1() {
        assertThat(myClassUnderTest.getMyObjConstantVal1()).isEqualTo("ObjConstantVal1")
    }

    @Test
    void testGetMyObjConstantVal2() {
        assertThat(myClassUnderTest.getMyObjConstantVal2()).isEqualTo("ObjConstantVal1")
    }

    @Test
    void testGetColorVa1() {
        assertThat(myClassUnderTest.getColorVa1()).isEqualTo(MyClass.Color.Red)
    }

    @Test
    void testGetColorVal2() {
        assertThat(myClassUnderTest.getColorVal2()).isEqualTo("lowerCaseName")
    }

    @Test
    void testGetColorVal3() {
        assertThat(myClassUnderTest.getColorVal3()).isEqualTo("Red")
    }

    @Test
    void testGetColorVal4() {
        assertThat(myClassUnderTest.getColorVal4()).isEqualTo(MyClass.Color.Red)
    }

    @Test
    void testGetSmallIntVal1() {
        assertThat(myClassUnderTest.getSmallIntVal1()).isEqualTo(5)
    }

    @Test
    void testGetSmallIntVal2() {
        assertThat(myClassUnderTest.getSmallIntVal2()).isEqualTo(5)
    }

    @Test
    void testGetSmallIntVal3() {
        assertThat(myClassUnderTest.getSmallIntVal3()).isEqualTo(5)
    }

    @Test
    void testGetSmallIntVal4() {
        assertThat(myClassUnderTest.getSmallIntVal4()).isEqualTo(-6)
    }

    @Test
    void testGetSmallIntVal5() {
        assertThat(myClassUnderTest.getSmallIntVal5()).isEqualTo(10)
    }

    @Test
    void testGetSmallIntVal6() {
        assertThat(myClassUnderTest.getSmallIntVal6()).isEqualTo(20)
    }

    @Test
    void testGetBigIntVal() {
        assertThat(myClassUnderTest.getBigIntVal()).isEqualTo(5)
    }

    @Test
    void testGetDoubleIntVal() {
        assertThat(myClassUnderTest.getDoubleIntVal()).isEqualTo(5.0d, within(0.0001))
    }

    @Test
    void testGetBigDoubleIntVal1() {
        assertThat(myClassUnderTest.getBigDoubleIntVal1()).isEqualTo(5.5d, within(0.0001))
    }

    @Test
    void testGetBigDoubleIntVal2() {
        assertThat(myClassUnderTest.getBigDoubleIntVal2()).isEqualTo(0.0d, within(0.0001))
    }

    @Test
    void testGetBigDoubleIntVal3() {
        assertThat(myClassUnderTest.getBigDoubleIntVal3()).isEqualTo(Double.NaN, within(0.0001))
    }

    @Test
    void testGetBigDoubleIntVal4() {
        assertThat(myClassUnderTest.getBigDoubleIntVal4()).isEqualTo(Double.NEGATIVE_INFINITY, within(0.0001))
    }

    @Test
    void testGetBigDoubleIntVal5() {
        assertThat(myClassUnderTest.getBigDoubleIntVal5()).isEqualTo(Double.POSITIVE_INFINITY, within(0.0001))
    }

    @Test
    void testGetFloatIntVal() {
        assertThat(myClassUnderTest.getFloatIntVal()).isEqualTo(5.0f, within(0.0001f))
    }

    @Test
    void testGetBigFloatIntVal1() {
        assertThat(myClassUnderTest.getBigFloatIntVal1()).isEqualTo(5.5f, within(0.0001f))
    }

    @Test
    void testGetBigFloatIntVal2() {
        assertThat(myClassUnderTest.getBigFloatIntVal2()).isEqualTo(0.0f, within(0.0001f))
    }

    @Test
    void testGetBigFloatIntVal3() {
        assertThat(myClassUnderTest.getBigFloatIntVal3()).isEqualTo(Float.NaN, within(0.0001f))
    }

    @Test
    void testGetBigFloatIntVal4() {
        assertThat(myClassUnderTest.getBigFloatIntVal4()).isEqualTo(Float.NEGATIVE_INFINITY, within(0.0001f))
    }

    @Test
    void testGetBigFloatIntVal5() {
        assertThat(myClassUnderTest.getBigFloatIntVal5()).isEqualTo(Float.POSITIVE_INFINITY, within(0.0001f))
    }

    @Test
    void testGetFloatIntVal6() {
        assertThat(myClassUnderTest.getFloatIntVal6()).isEqualTo(5.0d, within(0.0001f))
    }

    @Test
    void testGetBigFloatIntVal7() {
        assertThat(myClassUnderTest.getBigFloatIntVal7()).isEqualTo(5.5f, within(0.0001f))
    }

    @Test
    void testGetShortVal1() {
        assertThat(myClassUnderTest.getShortVal1()).isEqualTo((short) 10)
    }

    @Test
    void testGetShortVal2() {
        assertThat(myClassUnderTest.getShortVal2()).isEqualTo((short) 10)
    }

    @Test
    void testGetByteVal1() {
        assertThat(myClassUnderTest.getByteVal1()).isEqualTo((byte) 2)
    }

    @Test
    void testGetByteVal2() {
        assertThat(myClassUnderTest.getByteVal2()).isEqualTo((byte) 2)
    }

    @Test
    void testGetLongVal1() {
        assertThat(myClassUnderTest.getLongVal1()).isEqualTo(20L)
    }

    @Test
    void testGetLongVal2() {
        assertThat(myClassUnderTest.getLongVal2()).isEqualTo(20L)
    }

    @Test
    void testGetLongVal3() {
        assertThat(myClassUnderTest.getLongVal3()).isEqualTo(20L)
    }

    @Test
    void testGetCharVal1() {
        assertThat(myClassUnderTest.getCharVal1()).isEqualTo('k')
    }

    @Test
    void testGetCharVal2() {
        assertThat(myClassUnderTest.getCharVal2()).isEqualTo('k')
    }

    @Test
    void testGetCharVal3() {
        assertThat(myClassUnderTest.getCharVal3()).isEqualTo('/')
    }

    @Test
    void testGetTheClass1() {
        assertThat(myClassUnderTest.getTheClass1()).isEqualTo(MyClass.class)
    }

    @Test
    void testGetTheClass2() {
        assertThat(myClassUnderTest.getTheClass2()).isEqualTo(MyClass.InnerClass.class)
    }

    @Test
    void testGetTheClass3() {
        assertThat(myClassUnderTest.getTheClass3()).isEqualTo(int[].class)
    }

    @Test
    void testGetTheClass4() {
        assertThat(myClassUnderTest.getTheClass4()).isEqualTo(int.class)
    }

    @Test
    void testGetTheClass5() {
        assertThat(myClassUnderTest.getTheClass5()).isEqualTo(Integer.class)
    }

    @Test
    void testGetTheClass6() {
        assertThat(myClassUnderTest.getTheClass6()).isEqualTo(Integer[].class)
    }

    @Test
    void testGetTheClass7() {
        assertThat(myClassUnderTest.getTheClass7()).isEqualTo(Runnable.class)
    }

    @Test
    void testGetTheFooData1() {
        assertThat(myClassUnderTest.getTheFooData1()).isNull()
    }

    @Test
    void testGetTheBoolean1() {
        assertThat(myClassUnderTest.getTheBoolean1()).isTrue()
    }

    @Test
    void testGetTheBoolean2() {
        assertThat(myClassUnderTest.getTheBoolean2()).isFalse()
    }

    @Test
    void testGetTheBoolean3() {
        assertThat(myClassUnderTest.getTheBoolean3()).isTrue()
    }

    @Test
    void testGetTheBoolean4() {
        assertThat(myClassUnderTest.getTheBoolean4()).isFalse()
    }

    @Test
    void testGetTheBoolean5() {
        assertThat(myClassUnderTest.getTheBoolean5()).isTrue()
    }

    @Test
    void testGetTheBoolean6() {
        assertThat(myClassUnderTest.getTheBoolean6()).isFalse()
    }

    @Test
    void testGetTheMaxZoneOffset() {
        assertThat(myClassUnderTest.getTheMaxZoneOffset()).isEqualTo(ZoneOffset.MAX)
    }

    @Test
    void testGetTheMidnight() {
        assertThat(myClassUnderTest.getTheMidnight()).isEqualTo(LocalTime.MIDNIGHT)
    }

    @Test
    void testGetTheBigDecimal() {
        assertThat(myClassUnderTest.getTheBigDecimal()).isEqualTo(BigDecimal.TEN)
    }

    @Test
    void testGetTheBigInt() {
        assertThat(myClassUnderTest.getTheBigInt()).isEqualTo(BigInteger.TWO)
    }

    @Test
    void testGetTheList1() {
        assertThat(myClassUnderTest.getTheList1()).isEqualTo([])
    }

    @Test
    void testGetTheList2() {
        assertThat(myClassUnderTest.getTheList2()).isEqualTo([])
    }

    @Test
    void testGetTheString() {
        assertThat(myClassUnderTest.getTheString()).isEmpty()
    }

    @Test
    void testGetTheOptInt() {
        assertThat(myClassUnderTest.getTheOptInt()).isEmpty()
    }

    @Test
    void testGetTheByte1() {
        assertThat(myClassUnderTest.getTheByte1()).isEqualTo((byte) 2)
    }

    @Test
    void testGetTheByte2() {
        assertThat(myClassUnderTest.getTheByte2()).isEqualTo((byte) 2)
    }

    @Test
    void testGetTheFoo1() {
        assertThat(myClassUnderTest.getTheFoo1()).isNull()
    }
}
