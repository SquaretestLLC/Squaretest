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

import java.util.Collection;
import java.util.Collections;
import java.util.List;

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
        final List<String> foos1 = fooService.getFoos1(criteria);
        if (CollectionUtils.isEmpty(foos1)) {
            throw new FooServiceException1(criteria);
        }
        final List<String> foos2 = fooService.getFoos2(criteria);
        if (CollectionUtils.isEmpty(foos2)) {
            throw new FooServiceException2(criteria);
        }
        foos1.addAll(foos2);
        return foos1;
    }

    public List<String> getFoos2(final String userId, final String criteria) {
        if (!authService.getAllowedIds1().contains(userId)) {
            throw new NotAuthorizedException(userId);
        }
        final List<String> foos1 = fooService.getFoos1(criteria);
        final List<String> foos2 = fooService.getFoos2(criteria);
        if (CollectionUtils.containsAll(foos1, foos2)) {
            metricService.recordListsAreTheSame(criteria);
            return foos1;
        } else {
            metricService.recordListsAreDifferent(criteria);
        }
        foos1.addAll(foos2);
        return foos1;
    }

    public Collection<String> getFoos3(final String userId, final String criteria) {
        if (!authService.getAllowedIds1().contains(userId)) {
            throw new NotAuthorizedException(userId);
        }
        final Collection<String> foos1 = CollectionUtils.emptyIfNull(fooService.getFoos1(criteria));
        final Collection<String> foos2 = CollectionUtils.emptyIfNull(fooService.getFoos2(criteria));
        if (!Collections.disjoint(foos1, foos2)) {
            metricService.recordSomeDuplicates(criteria);
        } else {
            metricService.recordNoDuplicates(criteria);
        }
        foos1.addAll(foos2);
        return foos1;
    }

    public Collection<String> getFoos4(final String userId, final String criteria) {
        if (!authService.getAllowedIds1().contains(userId)) {
            throw new NotAuthorizedException(userId);
        }
        final Collection<String> foos1 = CollectionUtils.emptyIfNull(fooService.getFoos1(criteria));
        final Collection<String> foos2 = CollectionUtils.emptyIfNull(fooService.getFoos2(criteria));
        if (CollectionUtils.containsAny(foos1, foos2)) {
            metricService.recordSomeDuplicates(criteria);
        } else {
            metricService.recordNoDuplicates(criteria);
        }
        foos1.addAll(foos2);
        return foos1;
    }

    public List<String> getFoos5(final String userId, final String criteria) {
        if (!authService.getAllowedIds1().contains(userId)) {
            throw new NotAuthorizedException(userId);
        }
        final List<String> foos1 = fooService.getFoos1(criteria);
        if (!CollectionUtils.isNotEmpty(foos1)) {
            throw new FooServiceException1(criteria);
        }
        final List<String> foos2 = fooService.getFoos2(criteria);
        if (!CollectionUtils.isNotEmpty(foos2)) {
            throw new FooServiceException2(criteria);
        }
        foos1.addAll(foos2);
        return foos1;
    }
}
