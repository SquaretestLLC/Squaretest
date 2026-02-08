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

import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class MyClass<T> extends MyList<T> {
    private final FooService fooService;
    private final MyList<T> values;

    public MyClass(final FooService fooService) {
        this.fooService = fooService;
        this.values = new MyList<>();
    }

    @Override
    public T get(final int index) {
        return super.get(index);
    }

    @Override
    public boolean addAll(final Collection<? extends T> c) {
        return super.addAll(c);
    }

    @Override
    public boolean removeAll(final Collection<?> c) {
        return super.removeAll(c);
    }

    @Override
    public boolean removeIf(final Predicate<? super T> filter) {
        return super.removeIf(filter);
    }

    public MyList<T> getTheValues() {
        return values;
    }

    public MyList<FooData1> getFooDatas() {
        return new MyList<>(Arrays.asList(new FooData1()));
    }

    public MyList<FooData1> getFooDatasEmpty() {
        return new MyList<>();
    }

    public List<FooData1> getFooDatas(ArrayList<FooData1> fooDatas) {
        return new ArrayList<>();
    }

    public MyList<FooData1> getFooFromService(final String id) {
        return fooService.getFooData1(id);
    }

    public <K> K loadData1(final Class<K> theClass, final String theId) {
        return fooService.loadData1(theClass, theId);
    }

    public <K> List<K> loadDatas1(final Class<K> theClass, final String theId) {
        return fooService.loadDatas1(theClass, theId);
    }

    public <K extends FooData2> K loadData2(final Class<K> theClass, final String theId) {
        return fooService.loadData1(theClass, theId);
    }

    public <K extends FooData2> List<K> loadDatas2(final Class<K> theClass, final String theId) {
        return fooService.loadDatas1(theClass, theId);
    }

    public FooData3 loadFooData3(final String loadFooData3Param) {
        return loadDataHelper1(FooData3.class, loadFooData3Param);
    }

    public FooData4 loadFooData4(final String loadFooData3Param) {
        return loadDataHelper1(FooData4.class, loadFooData3Param);
    }

    private <K> K loadDataHelper1(final Class<K> theClass, final String theId) {
        // Squaretest will stub this call to use String instead of the params from loadFooData3(..) and loadFooData4(..)
        // This is expected.
        return fooService.loadData2(theClass, theId);
    }

    public static <T extends Comparable<? super T>> void sort(List<T> list) {
        list.sort(null);
    }
    public static <T> void sort(List<T> list, Comparator<? super T> c) {
        list.sort(c);
    }
    public static <T> int binarySearch(List<? extends Comparable<? super T>> list, T key) {
        return -1;
    }
    public static <T> int binarySearch(List<? extends T> list, T key, Comparator<? super T> c) {
        return -1;
    }
    public static void shuffle(List<?> list) {
        System.out.println(list);
    }
    public static <T> void copy(List<? super T> dest, List<? extends T> src) {
        System.out.println(dest);
        System.out.println(src);
    }
    public static <T extends Object & Comparable<? super T>> T min(Collection<? extends T> coll) {
        return null;
    }
    public static <T extends CharSequence> T defaultIfBlank(final T str, final T defaultStr) {
        return StringUtils.isBlank(str) ? defaultStr : str;
    }
    @SafeVarargs
    public static <T extends CharSequence> T firstNonBlank(final T... values) {
        if (values != null) {
            for (final T val : values) {
                if (StringUtils.isNotBlank(val)) {
                    return val;
                }
            }
        }
        return null;
    }
    public static <T extends CharSequence> T getIfBlank(final T str, final Supplier<T> defaultSupplier) {
        return StringUtils.isBlank(str) ? defaultSupplier == null ? null : defaultSupplier.get() : str;
    }
    public static <T extends CharSequence> T getIfEmpty(final T str, final Supplier<T> defaultSupplier) {
        return StringUtils.isEmpty(str) ? defaultSupplier == null ? null : defaultSupplier.get() : str;
    }
    @SafeVarargs
    public static <T> String join(final T... elements) {
        return join(elements, null);
    }
}
