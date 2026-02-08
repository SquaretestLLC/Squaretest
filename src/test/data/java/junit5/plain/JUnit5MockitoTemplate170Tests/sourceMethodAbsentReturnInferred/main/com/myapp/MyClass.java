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
import java.util.Optional;

public class MyClass {
    private final FooService fooService;
    private final MetricsAdapter metricsAdapter;
    private final FooMapper fooMapper;

    public MyClass(final FooService fooService, final MetricsAdapter metricsAdapter, final FooMapper fooMapper) {
        this.fooService = fooService;
        this.metricsAdapter = metricsAdapter;
        this.fooMapper = fooMapper;
    }

    public Optional<FooDataWithEq> getFooByIdOptional(long id) {
        return fooService.getFooByIdOptional(id);
    }

    public Optional<FooDataWithEq> getFooByIdOptional1(long id) {
        final Optional<OtherFooData> other = fooService.getFooByIdOptional1(id);
        return fooService.getFooByIdOptional(id);
    }

    public Optional<FooDataWithEq> getFooByIdOptional2(long id) {
        final Optional<OtherFooData> other = fooService.getFooByIdOptional1(id);
        return convert(other);
    }

    public Optional<FooDataWithEq> getFooByIdOptional3(long id) {
        final Optional<OtherFooData> other = fooService.getFooByIdOptional1(id);
        metricsAdapter.recordMetricEventWithoutReturn("event");
        return convert(other);
    }

    public Optional<FooDataWithEq> getFooByIdOptional4(long id) {
        final Optional<OtherFooData> other = fooService.getFooByIdOptional1(id);
        metricsAdapter.recordMetricWithReturn("event");
        return convert(other);
    }

    public Optional<FooDataWithEq> getFooByIdOptional5(long id) {
        // This should not have expected value = Optional.empty().
        // There is another DI whose return value is not ignored.
        final Optional<OtherFooData> other = fooService.getFooByIdOptional1(id);
        final MetricEvent event = metricsAdapter.recordMetricWithReturn("event");
        return convert(other);
    }

    public Optional<FooDataWithEq> getFooByIdOptional6(long id) {
        // This should have expected value = Optional.empty().
        // This is the only DI that returns the correct type.
        final Optional<FooDataWithEq> other = fooService.getFooByIdOptional(id);
        final MetricEvent event = metricsAdapter.recordMetricWithReturn("event");
        return other;
    }

    public Optional<FooDataWithEq> getFooByIdOptional7(long id) {
        // This should not have expected value = Optional.empty().
        // This is not the only DI that returns the correct type.
        final Optional<FooDataWithEq> other = fooService.getFooByIdOptional(id);
        if(other.isPresent()) {
            return other;
        }
        final Optional<FooDataWithEq> other1 = fooService.getFooByIdOptionalSame(id);
        return other;
    }

    public Optional<FooDataWithEq> getFooByIdOptional8(long id) {
        // This should have expected value = Optional.empty().
        // This is the only DI that returns the correct type and whose return value is not ignored.
        // Realistically the second API call would be something like a save(..) method that returns a copy of the
        // instance of the FooDataWithEq that was passed as a param.
        final Optional<FooDataWithEq> other = fooService.getFooByIdOptional(id);
        fooService.getFooByIdOptionalSame(id);
        return other;
    }

    public Optional<FooDataWithEq> getFooByIdOptional9(long id) {
        // This should have expected value = Optional.empty().
        // The DI returns the Optional's type, and it's the only DI that does so.
        return Optional.ofNullable(fooService.getFooByIdNullable(id));
    }

    public Optional<FooDataWithEq> getFooByIdOptional10(long id) {
        // This should NOT have expected value = Optional.empty().
        // The DI returns the Optional's type, and it's NOT the only DI that does so.
        final FooDataWithEq other = fooService.getFooByIdNullable2(id);
        if(other != null) {
            return Optional.ofNullable(other);
        }
        return Optional.ofNullable(fooService.getFooByIdNullable(id));
    }

    public Optional<FooDataWithEq> getFooByIdOptional11(long id) {
        // This should have expected value = Optional.empty().
        // The DI returns the Optional's type, and it's NOT the only DI that does so,
        // but the other id return value is ignored.
        fooService.getFooByIdNullable2(id);
        return Optional.ofNullable(fooService.getFooByIdNullable(id));
    }

    private Optional<FooDataWithEq> convert(final Optional<OtherFooData> other) {
        return Optional.ofNullable(fooMapper.convert(other.get()));
    }

    @Nullable
    public FooDataWithEq getFooByIdNullable(long id) {
        return fooService.getFooByIdNullable(id);
    }

    @Nullable
    public FooDataWithEq getFooByIdNullable2(long id) {
        final Optional<FooDataWithEq> other = fooService.getFooByIdOptional(id);
        return other.orElseThrow(IllegalArgumentException::new);
    }

    @Nullable
    public FooDataWithEq getFooByIdNullable3(long id) {
        // This should not have expected value = null.
        // The DI returns the Optional type, and it's not the only DI that does so.
        final Optional<FooDataWithEq> other = fooService.getFooByIdOptional(id);
        if(other.isPresent()) {
            return other.get();
        }
        final Optional<FooDataWithEq> other1 = fooService.getFooByIdOptionalSame(id);
        return other1.orElseThrow(IllegalArgumentException::new);
    }

    @Nullable
    public FooDataWithEq getFooByIdNullable4(long id) {
        // This should have expected value = null.
        // The DI returns the Optional's type, and it's NOT the only DI that does so,
        // but the other id return value is ignored.
        fooService.getFooByIdOptional(id);
        final Optional<FooDataWithEq> other1 = fooService.getFooByIdOptionalSame(id);
        return other1.orElseThrow(IllegalArgumentException::new);
    }

    @Nullable
    public FooDataWithEq getFooByIdNullable5(long id) {
        // This should have expected value = null.
        // It is the only DI whose return value is not ignored and that is not a *Mapper method.
        final Optional<OtherFooData> other = fooService.getFooByIdOptional1(id);
        metricsAdapter.recordMetricWithReturn("event");
        return convert(other).get();
    }

    @Nullable
    public FooDataWithEq getFooByIdNullable6(long id) {
        // This should have expected value = null.
        // This is the only DI that returns the correct type.
        final Optional<OtherFooData> other = fooService.getFooByIdOptional1(id);
        MetricEvent event = metricsAdapter.recordMetricWithReturn("event");
        return convert(other).get();
    }
}
