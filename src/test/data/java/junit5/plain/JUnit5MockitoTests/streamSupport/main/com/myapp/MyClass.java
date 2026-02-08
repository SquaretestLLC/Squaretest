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

import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;
import java.util.Spliterator;
import java.util.stream.*;

public class MyClass {
    private final AuthService authService;
    private final FooService fooService;
    private final MetricService metricService;

    public MyClass(AuthService authService, FooService fooService, MetricService metricService) {
        this.authService = authService;
        this.fooService = fooService;
        this.metricService = metricService;
    }

    public List<String> getFoos1(final String userId, final String criteria) {
        if (!authService.getAllowedIds1().contains(userId)) {
            throw new NotAuthorizedException(userId);
        }
        final List<String> foos = fooService.getFoos1(criteria);
        Stream<String> parallelStream = StreamSupport.stream(foos.spliterator(), true);
        return parallelStream.map(String::toUpperCase).collect(Collectors.toList());
    }

    public List<String> getFoos2(final String userId, final String criteria) {
        if (!authService.getAllowedIds1().contains(userId)) {
            throw new NotAuthorizedException(userId);
        }
        final int[] foos = fooService.getFoos2(criteria);
        IntStream parallelStream = StreamSupport.intStream(Arrays.spliterator(foos), true);
        return parallelStream.mapToObj(Integer::toBinaryString).collect(Collectors.toList());
    }

    public List<String> getFoos3(final String userId, final String criteria) {
        if (!authService.getAllowedIds1().contains(userId)) {
            throw new NotAuthorizedException(userId);
        }
        final double[] foos = fooService.getFoos3(criteria);
        DoubleStream parallelStream = StreamSupport.doubleStream(Arrays.spliterator(foos), true);
        return parallelStream.mapToObj(Double::toHexString).collect(Collectors.toList());
    }

    public List<String> getFoos4(final String userId, final String criteria) {
        if (!authService.getAllowedIds1().contains(userId)) {
            throw new NotAuthorizedException(userId);
        }
        final long[] foos = fooService.getFoos4(criteria);
        LongStream parallelStream = StreamSupport.longStream(Arrays.spliterator(foos), true);
        return parallelStream.mapToObj(Double::toHexString).collect(Collectors.toList());
    }

    public List<String> getFoos5(final String userId, final String criteria) {
        if (!authService.getAllowedIds1().contains(userId)) {
            throw new NotAuthorizedException(userId);
        }
        final List<String> foos = fooService.getFoos1(criteria);
        final Spliterator<String> foosSplit = foos.spliterator();
        foosSplit.forEachRemaining(metricService::recordItem1);
        return foos;
    }

    public List<String> getFoos6(final String userId, final String criteria) {
        if (!authService.getAllowedIds1().contains(userId)) {
            throw new NotAuthorizedException(userId);
        }
        final List<String> foos = fooService.getFoos1(criteria);
        final Spliterator<String> foosSplit = foos.spliterator();
        while (foosSplit.tryAdvance(metricService::recordItem1)) {
            metricService.recordMultipleItems1(criteria);
        }
        return foos;
    }

    public List<String> getFoos7(final String userId, final String criteria) {
        if (!authService.getAllowedIds1().contains(userId)) {
            throw new NotAuthorizedException(userId);
        }
        final List<String> foos = fooService.getFoos1(criteria);
        final Spliterator<String> foosSplit = foos.spliterator().trySplit();
        foosSplit.forEachRemaining(metricService::recordItem1);
        return foos;
    }

    public List<String> getFoos8(final String userId, final String criteria) {
        if (!authService.getAllowedIds1().contains(userId)) {
            throw new NotAuthorizedException(userId);
        }
        final double[] foos = fooService.getFoos3(criteria);
        DoubleStream parallelStream = StreamSupport.doubleStream(Arrays.spliterator(foos), true);
        OptionalDouble min = parallelStream.min();
        if(min.isPresent()) {
            metricService.recordMin(min.getAsDouble());
        }
        OptionalDouble max = parallelStream.max();
        if(max.isPresent()) {
            metricService.recordMax(max.getAsDouble());
        }
        OptionalDouble average = parallelStream.average();
        if(average.isPresent()) {
            metricService.recordAverage(average.getAsDouble());
        }
        double sum = parallelStream.sum();
        metricService.recordSum(sum);
        return parallelStream.mapToObj(Double::toHexString).collect(Collectors.toList());
    }

    public List<String> getFoos9(final String userId, final String criteria) {
        if (!authService.getAllowedIds1().contains(userId)) {
            throw new NotAuthorizedException(userId);
        }
        final double[] foos = fooService.getFoos3(criteria);
        Stream<Double> parallelStream = StreamSupport.doubleStream(Arrays.spliterator(foos), true).boxed();
        final DoubleStream doubleStream = parallelStream.mapToDouble(x -> x);
        OptionalDouble min = doubleStream.min();
        if(min.isPresent()) {
            metricService.recordMin(min.getAsDouble());
        }
        OptionalDouble max = doubleStream.max();
        if(max.isPresent()) {
            metricService.recordMax(max.getAsDouble());
        }
        OptionalDouble average = doubleStream.average();
        if(average.isPresent()) {
            metricService.recordAverage(average.getAsDouble());
        }
        double sum = doubleStream.sum();
        metricService.recordSum(sum);
        return doubleStream.mapToObj(Double::toHexString).collect(Collectors.toList());
    }
}
