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
    private Collection<BeanWithOtherListGetters> beanWithOtherListGetters;
    private Set<Foo1> foo1Set;
    private List<String> eTags;
    private String eTag;
    private String name;

    public Collection<BeanWithOtherListGetters> getBeanWithOtherListGetters() {
        return Collections.unmodifiableCollection(beanWithOtherListGetters);
    }

    public Set<Foo1> getFoo1Set() {
        return Collections.unmodifiableSet(foo1Set);
    }

    public String getETag() {
        return eTag;
    }

    public void setETag(String eTag) {
        this.eTag = eTag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getETags() {
        return Collections.unmodifiableList(eTags);
    }
}
