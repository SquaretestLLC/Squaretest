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
public class MyClass {
    private final FooService fooService;

    public MyClass(FooService fooService) {
        this.fooService = fooService;
    }

    public FooData getFoos1(final String id) {
        List<FooData> foos = fooService.getFoos1(id);
        if(foos.isEmpty()) {
            return null;
        }
        return foos.get(0);
    }

    public FooData getFoos2() {
        List<FooData> foos = fooService.getFoos1();
        if(foos.isEmpty()) {
            return null;
        }
        return foos.get(0);
    }

    public FooData getFoos3(final String... id1) {
        List<FooData> foos = fooService.getFoos1(id1);
        if(foos.isEmpty()) {
            return null;
        }
        return foos.get(0);
    }

    public FooData getFoos4(final String id) {
        List<FooData> foos = fooService.getFoos1(id, "OLD_" + id);
        if(foos.isEmpty()) {
            return null;
        }
        return foos.get(0);
    }

    public FooData getFoos5(final String id) {
        List<FooData> foos = fooService.getFoos1(id, id, id, id, id);
        if(foos.isEmpty()) {
            return null;
        }
        return foos.get(0);
    }

    public List<FooData> getFoos6(final String prefix) {
        return fooService.getFoos2(prefix);
    }

    public List<FooData> getFoos7(final String prefix) {
        return fooService.getFoos2(prefix, FooStatus.Creating);
    }

    public List<FooData> getFoos8(final String prefix) {
        return fooService.getFoos2(prefix, FooStatus.Creating, FooStatus.Created);
    }

    public List<FooData> getFoos9(final String prefix, final FooStatus... fooStatuses) {
        return fooService.getFoos2(prefix, fooStatuses);
    }

    public List<FooData> getFoos10(final String prefix) {
        final FooStatus[] fooStatuses = new FooStatus[]{FooStatus.Creating, FooStatus.Created};
        return fooService.getFoos2(prefix, fooStatuses);
    }

    public List<FooData> getFoos11(final String prefix) {
        return fooService.getFoos3(prefix);
    }

    public List<FooData> getFoos12(final String prefix) {
        return fooService.getFoos3(prefix, x -> true);
    }

    public List<FooData> getFoos13(final String prefix) {
        return fooService.getFoos3(prefix, x -> true, x -> true);
    }

    public void storeFoo1() {
        fooService.storeFoos1();
    }

    public void storeFoo2(final FooData fooData) {
        fooService.storeFoos1(fooData);
    }

    public void storeFoo3(final FooData fooData) {
        fooService.storeFoos1(fooData, fooData);
    }

    public void storeFoo4(final FooData... fooData) {
        fooService.storeFoos1(fooData);
    }

    public void storeFoo5(final FooData fooData) {
        fooService.storeFoos2(fooData);
    }

    public void storeFoo6(final FooData fooData) {
        fooService.storeFoos2(fooData, fooData);
    }

    public void storeFoo7(final FooData mainFoo, final FooData... fooData) {
        fooService.storeFoos2(mainFoo, fooData);
    }

    public void storeFoo8(final FooData fooData) {
        final FooData[] other = new FooData[]{fooData};
        fooService.storeFoos2(fooData, other);
    }
    public void storeFoo9(final List<FooData> fooDatas) {
        fooDatas.forEach(fooService::storeFoos1);
    }

    public void storeFoo10(final List<FooData> fooDatas) {
        fooDatas.forEach(fooService::storeFoos2);
    }
}
