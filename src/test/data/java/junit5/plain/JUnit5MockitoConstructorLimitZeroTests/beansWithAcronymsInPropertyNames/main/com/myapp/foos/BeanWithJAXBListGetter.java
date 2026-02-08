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

public class BeanWithJAXBListGetter {
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
        if(favoriteQuotes == null) {
            favoriteQuotes = new ArrayList<>();
        }
        return favoriteQuotes;
    }

    public Collection<BeanWithOtherListGetters> getBeanWithOtherListGetters() {
        if(beanWithOtherListGetters == null) {
            beanWithOtherListGetters = new ArrayList<>();
        }
        return beanWithOtherListGetters;
    }

    public Set<Foo1> getFoo1Set() {
        if(foo1Set == null) {
            foo1Set = new HashSet<>();
        }
        return foo1Set;
    }

    public String getETag() {
        return eTag;
    }

    public void setETag(String eTag) {
        this.eTag = eTag;
    }

    /**
     * ListGetter with acronym in name (the standard setter should be called geteTags(). Squaretest searches for that
     * and also getETags().
     */
    public List<String> getETags() {
        if(eTags == null) {
            eTags = new ArrayList<>();
        }
        return eTags;
    }

    // Test standard JAXB getter for name with acronym and field with upper case prefix.
    public List<String> getLAIDNumbers() {
        return LAIDNumbers;
    }

    // Test adjusted JAXB getter for name with acronym and field with lower case prefix.
    public List<String> getSAIDNumbers() {
        return sAIDNumbers;
    }

    // Test standard JAXB getter for name with acronym and lowercase prefix.
    public List<String> getsAMNumbers() {
        return sAMNumbers;
    }

    public List<String> getA() {
        return a;
    }

    public List<String> getB() {
        return B;
    }
}
