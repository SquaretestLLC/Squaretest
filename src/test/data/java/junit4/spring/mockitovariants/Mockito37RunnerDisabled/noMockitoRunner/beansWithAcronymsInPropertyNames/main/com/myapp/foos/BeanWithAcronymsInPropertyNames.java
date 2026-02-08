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
package com.myapp.foos;

import java.util.*;

public class BeanWithAcronymsInPropertyNames {
    private List<String> favoriteQuotes;
    private Collection<BeanWithOtherListGetters> beanWithOtherListGetters;
    private Set<Foo1> foo1Set;
    private String eTag;
    private List<String> eTags;
    private List<String> LAIDNumbers;
    private List<String> sAIDNumbers;
    private List<String> sAMNumbers;
    private List<String> a;
    private List<String> B;

    public List<String> getFavoriteQuotes() {
        return favoriteQuotes;
    }

    public void setFavoriteQuotes(List<String> favoriteQuotes) {
        this.favoriteQuotes = favoriteQuotes;
    }

    public Collection<BeanWithOtherListGetters> getBeanWithOtherListGetters() {
        return beanWithOtherListGetters;
    }

    public void setBeanWithOtherListGetters(Collection<BeanWithOtherListGetters> beanWithOtherListGetters) {
        this.beanWithOtherListGetters = beanWithOtherListGetters;
    }

    public Set<Foo1> getFoo1Set() {
        return foo1Set;
    }

    public void setFoo1Set(Set<Foo1> foo1Set) {
        this.foo1Set = foo1Set;
    }

    public String geteTag() {
        return eTag;
    }

    public void seteTag(String eTag) {
        this.eTag = eTag;
    }

    public List<String> getETags() {
        return eTags;
    }

    public void setETags(List<String> eTags) {
        this.eTags = eTags;
    }

    public List<String> getLAIDNumbers() {
        return LAIDNumbers;
    }

    public void setLAIDNumbers(List<String> LAIDNumbers) {
        this.LAIDNumbers = LAIDNumbers;
    }

    public List<String> getsAIDNumbers() {
        return sAIDNumbers;
    }

    public void setsAIDNumbers(List<String> sAIDNumbers) {
        this.sAIDNumbers = sAIDNumbers;
    }

    public List<String> getSAMNumbers() {
        return sAMNumbers;
    }

    public void setSAMNumbers(List<String> sAMNumbers) {
        this.sAMNumbers = sAMNumbers;
    }

    public List<String> getA() {
        return a;
    }

    public void setA(List<String> a) {
        this.a = a;
    }

    public List<String> getB() {
        return B;
    }

    public void setB(List<String> b) {
        B = b;
    }
}
