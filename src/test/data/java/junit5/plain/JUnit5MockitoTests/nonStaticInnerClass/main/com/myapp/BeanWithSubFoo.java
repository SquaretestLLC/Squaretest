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

public class BeanWithSubFoo {
    private String beanId;
    private FooService.SubFoo subFoo;

    public String getBeanId() {
        return beanId;
    }

    public void setBeanId(String beanId) {
        this.beanId = beanId;
    }

    public FooService.SubFoo getSubFoo() {
        return subFoo;
    }

    public void setSubFoo(FooService.SubFoo subFoo) {
        this.subFoo = subFoo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BeanWithSubFoo that = (BeanWithSubFoo) o;
        return Objects.equals(beanId, that.beanId) && Objects.equals(subFoo, that.subFoo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(beanId, subFoo);
    }
}
