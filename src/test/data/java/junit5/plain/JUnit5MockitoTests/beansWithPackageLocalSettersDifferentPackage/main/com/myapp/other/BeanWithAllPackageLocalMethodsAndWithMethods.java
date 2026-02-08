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
package com.myapp.other;

import java.util.Objects;

public class BeanWithAllPackageLocalMethodsAndWithMethods {
    private String id;
    private String name;
    private String prop1;
    private String prop2;
    private String prop3;
    private String prop4;
    private String prop5;

    BeanWithAllPackageLocalMethodsAndWithMethods() {
    }

    public static BeanWithAllPackageLocalMethodsAndWithMethods makeBeanWithAllPackageLocalMethodsAndWithMethods(final String serialized) {
        return new BeanWithAllPackageLocalMethodsAndWithMethods();
    }

    String getId() {
        return id;
    }

    void setId(final String id) {
        this.id = id;
    }

    String getName() {
        return name;
    }

    void setName(final String name) {
        this.name = name;
    }

    public String getProp1() {
        return prop1;
    }

    public String getProp2() {
        return prop2;
    }

    public String getProp3() {
        return prop3;
    }

    public String getProp4() {
        return prop4;
    }

    public String getProp5() {
        return prop5;
    }

    public void setProp1(final String prop1) {
        this.prop1 = prop1;
    }

    public void setProp2(final String prop2) {
        this.prop2 = prop2;
    }

    public void setProp3(final String prop3) {
        this.prop3 = prop3;
    }

    public void setProp4(final String prop4) {
        this.prop4 = prop4;
    }

    public void setProp5(final String prop5) {
        this.prop5 = prop5;
    }

    BeanWithAllPackageLocalMethodsAndWithMethods withId(final String name) {
        this.id = name;
        return this;
    }

    BeanWithAllPackageLocalMethodsAndWithMethods withName(final String name) {
        this.name = name;
        return this;
    }

    BeanWithAllPackageLocalMethodsAndWithMethods withProp1(final String prop1) {
        this.prop1 = prop1;
        return this;
    }

    BeanWithAllPackageLocalMethodsAndWithMethods withProp2(final String prop2) {
        this.prop2 = prop2;
        return this;
    }

    BeanWithAllPackageLocalMethodsAndWithMethods withProp3(final String prop3) {
        this.prop3 = prop3;
        return this;
    }

    BeanWithAllPackageLocalMethodsAndWithMethods withProp4(final String prop4) {
        this.prop4 = prop4;
        return this;
    }

    BeanWithAllPackageLocalMethodsAndWithMethods withProp5(final String prop5) {
        this.prop5 = prop5;
        return this;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final BeanWithAllPackageLocalMethodsAndWithMethods that = (BeanWithAllPackageLocalMethodsAndWithMethods) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
