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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

public class MyClass extends BaseClass<FooData5> {
    public MyClass(final FooService fooService) {
        super(fooService);
    }

    @Override
    public FooData5 get(final int index) {
        return super.get(index);
    }

    @Override
    public boolean addAll(final Collection<? extends FooData5> c) {
        return super.addAll(c);
    }

    @Override
    public boolean removeAll(final Collection<?> c) {
        return super.removeAll(c);
    }

    @Override
    public boolean removeIf(final Predicate<? super FooData5> filter) {
        return super.removeIf(filter);
    }

    @Override
    public MyList<FooData5> getTheValues() {
        return super.getTheValues();
    }

    @Override
    public MyList<FooData1> getFooDatas() {
        return super.getFooDatas();
    }

    @Override
    public List<FooData1> getFooDatas(final ArrayList<FooData1> fooDatas) {
        return super.getFooDatas(fooDatas);
    }

    @Override
    public MyList<FooData1> getFooFromService(final String id) {
        return super.getFooFromService(id);
    }

    @Override
    public <K> K loadData1(final Class<K> theClass, final String theId) {
        return super.loadData1(theClass, theId);
    }

    @Override
    public <K> List<K> loadDatas1(final Class<K> theClass, final String theId) {
        return super.loadDatas1(theClass, theId);
    }

    @Override
    public <K extends FooData2> K loadData2(final Class<K> theClass, final String theId) {
        return super.loadData2(theClass, theId);
    }

    @Override
    public <K extends FooData2> List<K> loadDatas2(final Class<K> theClass, final String theId) {
        return super.loadDatas2(theClass, theId);
    }

    @Override
    public FooData3 loadFooData3(final String loadFooData3Param) {
        return super.loadFooData3(loadFooData3Param);
    }

    @Override
    public FooData4 loadFooData4(final String loadFooData3Param) {
        return super.loadFooData4(loadFooData3Param);
    }
}
