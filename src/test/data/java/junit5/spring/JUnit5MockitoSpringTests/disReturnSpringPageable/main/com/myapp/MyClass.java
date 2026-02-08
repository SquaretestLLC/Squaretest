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

import org.springframework.data.domain.Page;
import org.springframework.data.util.Streamable;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyClass {
    private final FooService fooService;
    private final FooConverter fooConverter;

    public MyClass(FooService fooService, FooConverter fooConverter) {
        this.fooService = fooService;
        this.fooConverter = fooConverter;
    }

    public List<FooData> getFooData(final String query) {
        Page<String> items = fooService.getItems(query);
        return items.map(fooConverter::convert).stream().collect(Collectors.toList());
    }

    public Streamable<FooData> getFooData1(final String query) {
        final FooData item = fooService.getItem(query);
        Streamable<FooData> streamable = Streamable.of(item);
        return streamable;
    }

    public Streamable<FooData> getFooData2(final String query) {
        final Supplier<Stream<FooData>> item = fooService.getItemLazy(query);
        Streamable<FooData> streamable = Streamable.of(item);
        return streamable;
    }

    public List<FooData> getFooItems1(final String query) {
        List<FooData> fooItems = fooService.getFooItems(query);
        return Streamable.of(fooItems).stream().collect(Collectors.toList());
    }

    public List<FooData> getFooItems2(final String query) {
        List<FooData> fooItems = fooService.getFooItems(query);
        return Streamable.of(fooItems).filter(fooService::isValid).stream().collect(Collectors.toList());
    }

    public List<FooData> getFooItems3(final String query) {
        List<FooData> fooItems = fooService.getFooItems(query);
        return Streamable.of(fooItems).filter(x -> fooService.getAlternateName(x).startsWith("a")).stream().collect(Collectors.toList());
    }

    public List<OtherData> getFooItems4(final String query) {
        List<FooData> fooItems = fooService.getFooItems(query);
        return Streamable.of(fooItems).map(fooConverter::convert).stream().collect(Collectors.toList());
    }

    public List<OtherData> getFooItems5(final String query) {
        List<FooData> fooItems = fooService.getFooItems(query);
        return Streamable.of(fooItems).flatMap(x -> Stream.ofNullable(fooConverter.convert(x))).stream().collect(Collectors.toList());
    }

    public List<FooData> getFooItems6(final String query) {
        List<FooData> fooItems = fooService.getFooItems(query);
        Streamable.of(fooItems).forEach(fooData -> {
            if(!fooService.isValid(fooData)) {
                throw new RuntimeException("Invalid foo");
            }
        });
        return fooItems;
    }

    public List<FooData> getFooItems7(final String query) {
        List<FooData> fooItems = fooService.getFooItems(query);
        return Streamable.of(fooItems).toList();
    }

    public Set<FooData> getFooItems8(final String query) {
        List<FooData> fooItems = fooService.getFooItems(query);
        return Streamable.of(fooItems).toSet();
    }

    public Stream<FooData> getFooItems9(final String query) {
        List<FooData> fooItems = fooService.getFooItems(query);
        return Streamable.of(fooItems).get();
    }

    public boolean getFooItems10(final String query) {
        List<FooData> fooItems = fooService.getFooItems(query);
        return Streamable.of(fooItems).isEmpty();
    }
}
