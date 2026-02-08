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

import javax.annotation.Nullable;
import java.util.List;
import java.util.Arrays;

public class FooService {

    public List<OtherFooData> getFooByIdList1(final long id) {
        return Arrays.asList(new OtherFooData(id, "name"));
    }


    public List<FooDataWithEq> getFooByIdList(final long id) {
        return Arrays.asList(new FooDataWithEq(id, "name"));
    }

    public List<FooDataWithEq> getFooByIdListSame(final long id) {
        return Arrays.asList(new FooDataWithEq(id, "name"));
    }

    @Nullable
    public List<FooDataWithEq> getFooByIdNullable(final long id) {
        return Arrays.asList(new FooDataWithEq(id, "name"));
    }

    @Nullable
    public FooDataWithEq getFooByIdNullable2(final long id) {
        return new FooDataWithEq(id, "name");
    }
}
