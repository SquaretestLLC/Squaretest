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

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.Objects;

public class FooData {
    private String id;
    private String name;
    private String firstProp;
    @JacksonXmlProperty(localName = "specialSecondProp")
    private String secondProp;
    private String thirdProp;
    private String fourthProp;
    private String fifthProp;

    public FooData() {
    }

    public FooData(String id, String name, String firstProp, String secondProp, String thirdProp, String fourthProp, String fifthProp) {
        this.id = id;
        this.name = name;
        this.firstProp = firstProp;
        this.secondProp = secondProp;
        this.thirdProp = thirdProp;
        this.fourthProp = fourthProp;
        this.fifthProp = fifthProp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstProp() {
        return firstProp;
    }

    public void setFirstProp(String firstProp) {
        this.firstProp = firstProp;
    }

    public String getSecondProp() {
        return secondProp;
    }

    public void setSecondProp(String secondProp) {
        this.secondProp = secondProp;
    }

    public String getThirdProp() {
        return thirdProp;
    }

    public void setThirdProp(String thirdProp) {
        this.thirdProp = thirdProp;
    }

    public String getFourthProp() {
        return fourthProp;
    }

    public void setFourthProp(String fourthProp) {
        this.fourthProp = fourthProp;
    }

    public String getFifthProp() {
        return fifthProp;
    }

    public void setFifthProp(String fifthProp) {
        this.fifthProp = fifthProp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FooData fooData = (FooData) o;
        return Objects.equals(id, fooData.id) && Objects.equals(name, fooData.name) && Objects.equals(firstProp, fooData.firstProp) && Objects.equals(secondProp, fooData.secondProp) && Objects.equals(thirdProp, fooData.thirdProp) && Objects.equals(fourthProp, fooData.fourthProp) && Objects.equals(fifthProp, fooData.fifthProp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, firstProp, secondProp, thirdProp, fourthProp, fifthProp);
    }
}
