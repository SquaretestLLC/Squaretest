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

import java.util.Objects;

public class InnerBeanDTO extends InnerBeanDTOSuper {
    private String value4;
    private String value5;
    private String value6;
    private String value7;
    private String value8;
    private String value9;
    private String value10;
    private String value11;
    private String value12;
    private String value13;
    private String value14;
    private String value15;
    private String value16;
    private String value17;
    private String value18;
    private String value19;

    @Override
    public String getValue1() {
        return value1;
    }
    @Override
    public void setValue1(final String value1) {
        this.value1 = value1;
    }
    @Override
    public String getValue2() {
        return value2;
    }
    @Override
    public void setValue2(final String value2) {
        this.value2 = value2;
    }
    @Override
    public String getValue3() {
        return value3;
    }
    @Override
    public void setValue3(final String value3) {
        this.value3 = value3;
    }

    public String getValue4() {
        return value4;
    }

    public void setValue4(final String value4) {
        this.value4 = value4;
    }

    public String getValue5() {
        return value5;
    }

    public void setValue5(final String value5) {
        this.value5 = value5;
    }

    public String getValue6() {
        return value6;
    }

    public void setValue6(final String value6) {
        this.value6 = value6;
    }

    public String getValue7() {
        return value7;
    }

    public void setValue7(final String value7) {
        this.value7 = value7;
    }

    public String getValue8() {
        return value8;
    }

    public void setValue8(final String value8) {
        this.value8 = value8;
    }

    public String getValue9() {
        return value9;
    }

    public void setValue9(final String value9) {
        this.value9 = value9;
    }

    public String getValue10() {
        return value10;
    }

    public void setValue10(final String value10) {
        this.value10 = value10;
    }

    public String getValue11() {
        return value11;
    }

    public void setValue11(final String value11) {
        this.value11 = value11;
    }

    public String getValue12() {
        return value12;
    }

    public void setValue12(final String value12) {
        this.value12 = value12;
    }

    public String getValue13() {
        return value13;
    }

    public void setValue13(final String value13) {
        this.value13 = value13;
    }

    public String getValue14() {
        return value14;
    }

    public void setValue14(final String value14) {
        this.value14 = value14;
    }

    public String getValue15() {
        return value15;
    }

    public void setValue15(final String value15) {
        this.value15 = value15;
    }

    public String getValue16() {
        return value16;
    }

    public void setValue16(final String value16) {
        this.value16 = value16;
    }

    public String getValue17() {
        return value17;
    }

    public void setValue17(final String value17) {
        this.value17 = value17;
    }

    public String getValue18() {
        return value18;
    }

    public void setValue18(final String value18) {
        this.value18 = value18;
    }

    public String getValue19() {
        return value19;
    }

    public void setValue19(final String value19) {
        this.value19 = value19;
    }


    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final InnerBeanDTO that = (InnerBeanDTO) o;
        return Objects.equals(value4, that.value4) && Objects.equals(value5, that.value5) && Objects.equals(value6, that.value6) && Objects.equals(value7, that.value7) && Objects.equals(value8, that.value8) && Objects.equals(value9, that.value9) && Objects.equals(value10, that.value10) && Objects.equals(value11, that.value11) && Objects.equals(value12, that.value12) && Objects.equals(value13, that.value13) && Objects.equals(value14, that.value14) && Objects.equals(value15, that.value15) && Objects.equals(value16, that.value16) && Objects.equals(value17, that.value17) && Objects.equals(value18, that.value18) && Objects.equals(value19, that.value19);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value4, value5, value6, value7, value8, value9, value10, value11, value12, value13, value14, value15, value16, value17, value18, value19);
    }
}
