/*
 * Copyright 2026 Squaretest LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.myapp;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.*;

public class MyClass {
    private static final String MyConst1 = "MyConst1Val";
    private static final String MyConst2 = MyConst1 + 10 + "OtherVal";
    private static final String MyConst3 = int[].class + "";
    private static final String[] MyConstArray = new String[]{"Foo", "Bar"};
    private static final Color PreferredColor = Color.Blue;
    private static final Object MyObjConstant = "ObjConstantVal1";
    private static final int MyIntConst1 = 5;
    private static final int MyIntConst2 = MyIntConst1;
    private static final int MyIntConst3 = ~MyIntConst1;
    private static final int MyIntConst4 = MyIntConst1 << 1;
    private static final byte MyByteConstant = 0b0010;
    static class InnerClass {

    }
    enum Color {
        Red("red"),
        Blue("blue");
        private final String lowerCaseName;

        Color(final String lowerCaseName) {
            this.lowerCaseName = lowerCaseName;
        }

        public String getLowerCaseName() {
            return lowerCaseName;
        }
    }
    public String getStringVal1() {
        return "Constant1";
    }
    public String getStringVal2() {
        return MyConst1;
    }
    public String getStringVal4() {
        return MyConst2;
    }
    public String getStringVal5() {
        return MyConst3;
    }
    public String[] getStringArrayVal1() {
        return new String[]{"Constant1"};
    }
    public String[] getStringArrayVal2() {
        return MyConstArray;
    }
    public Object getMyObjConstantVal1() {
        return MyObjConstant;
    }
    public String getMyObjConstantVal2() {
        return (String) MyObjConstant;
    }
    public Color getColorVa1() {
        return Color.Red;
    }
    public String getColorVal2() {
        return Color.Red.getLowerCaseName();
    }
    public String getColorVal3() {
        return Color.Red.toString();
    }
    public Color getColorVal4() {
        return PreferredColor;
    }
    public int getSmallIntVal1() {
        return 5;
    }
    public int getSmallIntVal2() {
        return MyIntConst1;
    }
    public int getSmallIntVal3() {
        return MyIntConst2;
    }
    public int getSmallIntVal4() {
        return MyIntConst3;
    }
    public int getSmallIntVal5() {
        return MyIntConst4;
    }
    public int getSmallIntVal6() {
        return MyIntConst4 << 1;
    }
    public Integer getBigIntVal() {
        return 5;
    }
    public double getDoubleIntVal() {
        return 5.0;
    }
    public Double getBigDoubleIntVal1() {
        return 5.5;
    }
    public Double getBigDoubleIntVal2() {
        return new Double(5.5);
    }
    public Double getBigDoubleIntVal3() {
        return Double.NaN;
    }
    public Double getBigDoubleIntVal4() {
        return Double.NEGATIVE_INFINITY;
    }
    public Double getBigDoubleIntVal5() {
        return Double.POSITIVE_INFINITY;
    }
    public float getFloatIntVal() {
        return 5.0f;
    }
    public Float getBigFloatIntVal1() {
        return 5.5f;
    }
    public Float getBigFloatIntVal2() {
        return new Float(5.5);
    }
    public Float getBigFloatIntVal3() {
        return Float.NaN;
    }
    public Float getBigFloatIntVal4() {
        return Float.NEGATIVE_INFINITY;
    }
    public Float getBigFloatIntVal5() {
        return Float.POSITIVE_INFINITY;
    }
    public float getFloatIntVal6() {
        return (float) 5.0;
    }
    public Float getBigFloatIntVal7() {
        return (float) 5.5f;
    }
    public short getShortVal1() {
        return 10;
    }
    public Short getShortVal2() {
        return 10;
    }
    public byte getByteVal1() {
        return 0b10;
    }
    public Byte getByteVal2() {
        return 0b10;
    }
    public long getLongVal1() {
        return 20L;
    }
    public Long getLongVal2() {
        return 20L;
    }
    public long getLongVal3() {
        return 20;
    }
    public char getCharVal1() {
        return 'k';
    }
    public Character getCharVal2() {
        return 'k';
    }
    public Character getCharVal3() {
        return '\u002F';
    }
    public Class getTheClass1() {
        return MyClass.class;
    }
    public Class getTheClass2() {
        return MyClass.InnerClass.class;
    }
    public Class getTheClass3() {
        return int[].class;
    }
    public Class getTheClass4() {
        return int.class;
    }
    public Class getTheClass5() {
        return Integer.class;
    }
    public Class getTheClass6() {
        return Integer[].class;
    }
    public Class getTheClass7() {
        return Runnable.class;
    }
    public FooData1 getTheFooData1() {
        return null;
    }
    public boolean getTheBoolean1() {
        return true;
    }
    public boolean getTheBoolean2() {
        return false;
    }
    public Boolean getTheBoolean3() {
        return true;
    }
    public Boolean getTheBoolean4() {
        return false;
    }
    public Boolean getTheBoolean5() {
        return Boolean.TRUE;
    }
    public Boolean getTheBoolean6() {
        return Boolean.FALSE;
    }
    public ZoneOffset getTheMaxZoneOffset() {
        return ZoneOffset.MAX;
    }
    public LocalTime getTheMidnight() {
        return LocalTime.MIDNIGHT;
    }
    public BigDecimal getTheBigDecimal() {
        return BigDecimal.TEN;
    }
    public BigInteger getTheBigInt() {
        return BigInteger.TWO;
    }
    public List<String> getTheList1() {
        return Collections.emptyList();
    }
    public List<String> getTheList2() {
        return new ArrayList<>();
    }
    public Optional<String> getTheString() {
        return Optional.empty();
    }
    public OptionalInt getTheOptInt() {
        return OptionalInt.empty();
    }
    public byte getTheByte1() {
        // Test comment.
        return MyByteConstant;
    }
    public Byte getTheByte2() {
        // Test comment.
        return MyByteConstant /* Other comment. */;
    }
    public FooData1 getTheFoo1() {
        return null;
    }
}
