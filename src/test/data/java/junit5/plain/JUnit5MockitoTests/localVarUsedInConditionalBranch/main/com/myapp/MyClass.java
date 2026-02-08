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

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyClass {
    private final FooService fooService;

    public MyClass(FooService fooService) {
        this.fooService = fooService;
    }

    public List<String> getFoos1(final String id, final boolean useFirstApi, final boolean capsOnly) {
        List<String> foos1 = useFirstApi ? fooService.getFoos1(id) : fooService.getFoos2(id);
        Stream<String> stream = foos1.stream();
        stream = capsOnly ? stream.filter(x -> !x.contains("a")) : stream;
        return stream.map(String::toLowerCase).collect(Collectors.toList());
    }
    public List<String> getFoos2(final String id, final boolean useFirstApi, final boolean capsOnly) {
        List<String> foos1 = useFirstApi ? fooService.getFoos1(id) : fooService.getFoos2(id);
        Stream<String> stream = foos1.stream();
        stream = capsOnly ? stream.filter(x -> !x.contains("a")) : stream;
        return stream.map(fooService::getPreferredName1).collect(Collectors.toList());
    }
    public List<String> getFoos3(final String id, final boolean useFirstApi, final boolean capsOnly) {
        List<String> foos1 = useFirstApi ? fooService.getFoos1(id) : fooService.getFoos2(id);
        Stream<String> stream = foos1.stream();
        stream = capsOnly ? stream.filter(x -> !x.contains("a")) : Stream.empty();
        return stream.map(fooService::getPreferredName1).collect(Collectors.toList());
    }

    public List<String> getFoos4(final String id, final boolean useFirstApi, final boolean capsOnly) {
        List<String> foos1 = useFirstApi ? fooService.getFoos1(id) : fooService.getFoos2(id);
        Stream<String> stream = foos1.stream();
        stream = capsOnly ? stream.filter(x -> !x.contains("a")) : Stream.of("other");
        return stream.map(fooService::getPreferredName1).collect(Collectors.toList());
    }
    public List<String> getFoos5(final String id, final boolean useFirstApi, final boolean capsOnly) {
        List<String> foos1 = useFirstApi ? fooService.getFoos1(id) : fooService.getFoos2(id);
        Stream<String> stream = foos1.stream();
        stream = capsOnly ? stream.filter(x -> !x.contains("a")) : fooService.getFoos3(id).stream();
        return stream.map(fooService::getPreferredName1).collect(Collectors.toList());
    }
    public List<String> getFoos6(final String id, final boolean useFirstApi, final boolean capsOnly, final boolean isOther) {
        List<String> foos1 = useFirstApi ? fooService.getFoos1(id) : fooService.getFoos2(id);
        Stream<String> stream = foos1.stream();
        stream = capsOnly ? stream.filter(x -> !x.contains("a")) : isOther ? fooService.getFoos3(id).stream() : stream;
        return stream.map(fooService::getPreferredName1).collect(Collectors.toList());
    }
    public List<String> getFoos7(final String id, final boolean useFirstApi, final boolean capsOnly, final boolean isOther) {
        List<String> foos1 = useFirstApi ? fooService.getFoos1(id) : fooService.getFoos2(id);
        Stream<String> stream = foos1.stream();
        stream = capsOnly ? stream.filter(x -> !x.contains("a")) : isOther ? stream : fooService.getFoos3(id).stream();
        return stream.map(fooService::getPreferredName1).collect(Collectors.toList());
    }
    public List<String> getFoos8(final String id, final boolean useFirstApi, final boolean capsOnly, final boolean isOther) {
        List<String> foos1 = useFirstApi ? fooService.getFoos1(id) : fooService.getFoos2(id);
        Stream<String> stream = foos1.stream();
        stream = capsOnly ? stream.filter(x -> !x.contains("a")) : isOther ? stream : Stream.empty();
        return stream.map(fooService::getPreferredName1).collect(Collectors.toList());
    }
    public List<String> getFoos9(final String id, final boolean useFirstApi, final boolean capsOnly, final boolean isOther) {
        List<String> foos1 = useFirstApi ? fooService.getFoos1(id) : fooService.getFoos2(id);
        Stream<String> stream = foos1.stream();
        stream = capsOnly ? stream.filter(x -> !x.contains("a")) : isOther ? stream : fooService.filterSomething(stream);
        return stream.map(fooService::getPreferredName1).collect(Collectors.toList());
    }
    public List<String> getFoos10(final String id, final boolean useFirstApi, final boolean capsOnly, final boolean isOther) {
        List<String> foos1 = useFirstApi ? fooService.getFoos1(id) : fooService.getFoos2(id);
        Stream<String> stream = foos1.stream();
        stream = capsOnly ? stream.filter(x -> !x.contains("a")) : stream.findAny().isPresent() ? stream : Stream.empty();
        return stream.map(fooService::getPreferredName1).collect(Collectors.toList());
    }
    public List<String> getFoos11(final String id, final boolean useFirstApi, final boolean capsOnly, final boolean isOther) {
        List<String> foos1 = useFirstApi ? fooService.getFoos1(id) : fooService.getFoos2(id);
        Stream<String> stream = foos1.stream();
        stream = capsOnly ? stream.filter(x -> !x.contains("a")) : stream.findAny().isPresent() ? stream : fooService.getFoos3(id).stream();
        return stream.map(fooService::getPreferredName1).collect(Collectors.toList());
    }
}
