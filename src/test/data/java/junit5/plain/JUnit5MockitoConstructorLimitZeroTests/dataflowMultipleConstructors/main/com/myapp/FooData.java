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

public class FooData {
    private String fooIdField;
    private String fooNameField;
    private String otherValueField;

    public FooData(String fooIdConstructorParam) {
        this.fooIdField = fooIdConstructorParam;
    }

    public FooData(String fooIdConstructorParam, String fooNameConstructorParam) {
        this.fooIdField = fooIdConstructorParam;
        this.fooNameField = fooNameConstructorParam;
    }

    public FooData(String fooIdConstructorParam, String fooNameConstructorParam, String fooOtherValueConstructorParam) {
        this.fooIdField = fooIdConstructorParam;
        this.fooNameField = fooNameConstructorParam;
        this.otherValueField = fooOtherValueConstructorParam;
    }

    public String getFooIdField() {
        return fooIdField;
    }

    public void setFooIdField(String fooIdSetterParam) {
        this.fooIdField = fooIdSetterParam;
    }

    public String getFooNameField() {
        return fooNameField;
    }

    public void setFooNameField(String fooNameSetterParam) {
        this.fooNameField = fooNameSetterParam;
    }

    public String getOtherValueField() {
        return otherValueField;
    }

    public void setOtherValueField(String fooOtherValueSetterParam) {
        this.otherValueField = fooOtherValueSetterParam;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FooData fooData = (FooData) o;
        return Objects.equals(fooIdField, fooData.fooIdField) && Objects.equals(fooNameField, fooData.fooNameField) && Objects.equals(otherValueField, fooData.otherValueField);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fooIdField, fooNameField, otherValueField);
    }
}
