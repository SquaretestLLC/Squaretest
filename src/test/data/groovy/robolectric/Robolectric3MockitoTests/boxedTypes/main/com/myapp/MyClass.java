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

public class MyClass {
    private final FooService fooService;

    public MyClass(FooService fooService) {
        this.fooService = fooService;
    }

    public Boolean getTheBoolean(String id) {
        return fooService.getTheBoolean(id);
    }

    public Byte getTheByte(String id) {
        return fooService.getTheByte(id);
    }

    public Byte getTheByte1() {
        return (byte) 5;
    }

    public Character getTheCharacter(String id) {
        return fooService.getTheCharacter(id);
    }

    public Float getTheFloat(String id) {
        return fooService.getTheFloat(id);
    }

    public Integer getTheInt(String id) {
        return fooService.getTheInt(id);
    }

    public Long getTheLong(String id) {
        return fooService.getTheLong(id);
    }

    public Short getTheShort(String id) {
        return fooService.getTheShort(id);
    }

    public Double getTheDouble(String id) {
        return fooService.getTheDouble(id);
    }

    public Double getTheDouble1(final Double input) {
        return input + .01;
    }

    public Object getTheObject1() {
        return 5;
    }

    public Object getTheObject2() {
        return 5L;
    }

    public Object getTheObject3() {
        return 5.1;
    }

    public Object getTheObject4() {
        return 5.1d;
    }

    public Object getTheObject5() {
        return 5.1f;
    }

    public Object getTheObject6() {
        return false;
    }

    public Object getTheObject7() {
        return "hello";
    }

    public Object getTheObject8() {
        return 0b1001;
    }

    public Object getTheObject9() {
        return (short) 5;
    }

    public Object getTheObject10() {
        return Short.valueOf((short)5);
    }

    public Object getTheObject11() {
        return 'b';
    }

    public Byte getTheByte2() {
        return 0B1001;
    }

    public byte getTheByte3() {
        return 0B1001;
    }

    public byte getTheByte4() {
        return -0B1001;
    }

    public byte getTheByte5() {
        return 0X0000;
    }

    public Integer getTheInteger1() {
        return 0B1001;
    }

    public Integer getTheInteger2() {
        return 0b1001;
    }

    public Long getTheLong1() {
        return 0b1010000101000101101000010100010110100001010001011010000101000101L;
    }

    public Long getTheLong2() {
        return 0B1010000101000101101000010100010110100001010001011010000101000101L;
    }

    public Long getTheLong3() {
        return (long) 0B101000010100010110100;
    }

    public Long getTheLong4() {
        return 0X123L;
    }

    public Integer getTheInt1() {
        return 0X123;
    }

    public int getTheInt2() {
        return 0X123;
    }

    public int getTheInt3(final String id) {
        if(id.startsWith("a")) {
            return 0X123;
        }
        return 0x555;
    }

    public Number getTheNumber1() {
        return 5;
    }

    public Number getTheNumber2() {
        return 5.5;
    }

    public Number getTheNumber3() {
        return 5.5f;
    }

    public Number getTheNumber4() {
        return 0b001;
    }

    public Number getTheNumber5() {
        return 5L;
    }

    public Number getTheNumber6() {
        return (byte) 4;
    }

    public Number getTheNumber7() {
        return (int) '4';
    }

    public Number getTheNumber8() {
        return (short) 5;
    }

    public Number getTheNumber9() {
        return new BigDecimal("5.55");
    }

    public Number getTheNumber10() {
        return new BigInteger("55");
    }

    public Character getTheChar1() {
        return 0b001;
    }

    public Character getTheChar2() {
        return '\u0000';
    }

    public Character getTheChar3() {
        return 100;
    }

    public Character getTheChar4() {
        return '\uD83D';
    }
}
