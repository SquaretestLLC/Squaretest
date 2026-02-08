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

public class MyClass {
    private final FooService fooService;
    private final MetricsAdapter metricsAdapter;
    private final FooMapper fooMapper;

    public MyClass(final FooService fooService, final MetricsAdapter metricsAdapter, final FooMapper fooMapper) {
        this.fooService = fooService;
        this.metricsAdapter = metricsAdapter;
        this.fooMapper = fooMapper;
    }

    public List<FooDataWithEq> getFooByIdList(long id) {
        return fooService.getFooByIdList(id);
    }

    public List<FooDataWithEq> getFooByIdList1(long id) {
        final List<OtherFooData> other = fooService.getFooByIdList1(id);
        return fooService.getFooByIdList(id);
    }

    public List<FooDataWithEq> getFooByIdList2(long id) {
        final List<OtherFooData> other = fooService.getFooByIdList1(id);
        return convert(other);
    }

    public List<FooDataWithEq> getFooByIdList3(long id) {
        final List<OtherFooData> other = fooService.getFooByIdList1(id);
        metricsAdapter.recordMetricEventWithoutReturn("event");
        return convert(other);
    }

    public List<FooDataWithEq> getFooByIdList4(long id) {
        final List<OtherFooData> other = fooService.getFooByIdList1(id);
        metricsAdapter.recordMetricWithReturn("event");
        return convert(other);
    }

    public List<FooDataWithEq> getFooByIdList5(long id) {
        // This should have expected value = Collections.emptyList().
        // The fooService.getFooByIdList1(id) call is the only one that returns
        // a list (and is also not a *Mapper object).
        final List<OtherFooData> other = fooService.getFooByIdList1(id);
        final MetricEvent event = metricsAdapter.recordMetricWithReturn("event");
        return convert(other);
    }

    public List<FooDataWithEq> getFooByIdList6(long id) {
        // This should have expected value = Collections.emptyList().
        // This is the only DI that returns the correct type.
        final List<FooDataWithEq> other = fooService.getFooByIdList(id);
        final MetricEvent event = metricsAdapter.recordMetricWithReturn("event");
        return other;
    }

    public List<FooDataWithEq> getFooByIdList7(long id) {
        // This should not have expected value = Collections.emptyList().
        // This is not the only DI that returns the correct type.
        final List<FooDataWithEq> other = fooService.getFooByIdList(id);
        if (other.isEmpty()) {
            return other;
        }
        final List<FooDataWithEq> other1 = fooService.getFooByIdListSame(id);
        return other;
    }

    public List<FooDataWithEq> getFooByIdList8(long id) {
        // This should have expected value = Collections.emptyList().
        // This is the only DI that returns the correct type and whose return value is not ignored.
        // Realistically the second API call would be something like a save(..) method that returns a copy of the
        // instance of the FooDataWithEq that was passed as a param.
        final List<FooDataWithEq> other = fooService.getFooByIdList(id);
        fooService.getFooByIdListSame(id);
        return other;
    }

    public List<FooDataWithEq> getFooByIdList9(long id) {
        // This should have expected value = Collections.emptyList().
        // The DI returns the List's type, and it's the only DI that does so.
        return fooService.getFooByIdNullable(id);
    }

    public List<FooDataWithEq> getFooByIdList10(long id) {
        // This should have expected value = Collections.emptyList().
        // There is only one DI that returns the list.
        final FooDataWithEq other = fooService.getFooByIdNullable2(id);
        if (other != null) {
            return Arrays.asList(other);
        }
        return fooService.getFooByIdNullable(id);
    }

    public List<FooDataWithEq> getFooByIdList11(long id) {
        // This should have expected value = Collections.emptyList().
        // The DI returns the List's type, and it's NOT the only DI that does so,
        // but the other id return value is ignored.
        fooService.getFooByIdListSame(id);
        return fooService.getFooByIdNullable(id);
    }

    private List<FooDataWithEq> convert(final List<OtherFooData> other) {
        return Arrays.asList(fooMapper.convert(other.get(0)));
    }

    @Nullable
    public FooDataWithEq getFooByIdNullable(long id) {
        return fooService.getFooByIdNullable(id).get(0);
    }

    @Nullable
    public FooDataWithEq getFooByIdNullable2(long id) {
        final List<FooDataWithEq> other = fooService.getFooByIdList(id);
        if (other.isEmpty()) {
            throw new IllegalArgumentException("");
        }
        return other.get(0);
    }

    @Nullable
    public FooDataWithEq getFooByIdNullable3(long id) {
        // This should not have expected value = null.
        // The DI returns the List type, and it's not the only DI that does so.
        final List<FooDataWithEq> other = fooService.getFooByIdList(id);
        if (!other.isEmpty()) {
            return other.get(0);
        }
        final List<FooDataWithEq> other1 = fooService.getFooByIdListSame(id);
        return other1.get(0);
    }

    @Nullable
    public List<FooDataWithEq> getFooByIdNullable4(long id) {
        // This should have expected value = empty list.
        // The DI returns the List's type, and it's NOT the only DI that does so,
        // but the other id return value is ignored.
        fooService.getFooByIdList(id);
        final List<FooDataWithEq> other1 = fooService.getFooByIdListSame(id);
        return other1;
    }

    @Nullable
    public List<FooDataWithEq> getFooByIdNullable5(long id) {
        // This should have expected value = empty list.
        // It is the only DI whose return value is not ignored and that is not a *Mapper method.
        final List<OtherFooData> other = fooService.getFooByIdList1(id);
        metricsAdapter.recordMetricWithReturn("event");
        return convert(other);
    }

    @Nullable
    public FooDataWithEq getFooByIdNullable6(long id) {
        final List<OtherFooData> other = fooService.getFooByIdList1(id);
        MetricEvent event = metricsAdapter.recordMetricWithReturn("event");
        return convert(other).get(0);
    }
}
