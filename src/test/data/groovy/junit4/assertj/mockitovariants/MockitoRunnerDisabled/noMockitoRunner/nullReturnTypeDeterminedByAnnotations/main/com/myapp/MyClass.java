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

import com.myapp.nullables.*;

public class MyClass {

    private final StandardAnnotationNullFooProvider standardAnnotationNullFooProvider;
    private final CustomAnnotationNullFooProvider customAnnotationNullFooProvider;
    private final MissingAnnotationNullFooProvider missingAnnotationNullFooProvider;
    private final SubNullFooProvider subNullFooProvider;
    private final SubJavadocNullFooProvider subJavadocNullFooProvider;

    public MyClass(final StandardAnnotationNullFooProvider standardAnnotationNullFooProvider, final CustomAnnotationNullFooProvider customAnnotationNullFooProvider, final MissingAnnotationNullFooProvider missingAnnotationNullFooProvider, final SubNullFooProvider subNullFooProvider, final SubJavadocNullFooProvider subJavadocNullFooProvider) {
        this.standardAnnotationNullFooProvider = standardAnnotationNullFooProvider;
        this.customAnnotationNullFooProvider = customAnnotationNullFooProvider;
        this.missingAnnotationNullFooProvider = missingAnnotationNullFooProvider;
        this.subNullFooProvider = subNullFooProvider;
        this.subJavadocNullFooProvider = subJavadocNullFooProvider;
    }

    public String safeGetFoo() {
        return standardAnnotationNullFooProvider.safeGetFoo();
    }

    public String getFoo() throws RuntimeException {
        return standardAnnotationNullFooProvider.getFoo();
    }

    public String safeGetFoo1() {
        return customAnnotationNullFooProvider.safeGetFoo1();
    }

    public String getFoo1() throws RuntimeException {
        return customAnnotationNullFooProvider.getFoo1();
    }

    public String getOtherFoo1() throws RuntimeException {
        return customAnnotationNullFooProvider.getOtherFoo1();
    }

    public String safeGetFoo2() {
        return missingAnnotationNullFooProvider.safeGetFoo2();
    }

    public String getFoo2() throws RuntimeException {
        return missingAnnotationNullFooProvider.getFoo2();
    }

    public String getOtherFoo2() throws RuntimeException {
        return missingAnnotationNullFooProvider.getOtherFoo2();
    }

    public String safeGetFoo3() {
        return subNullFooProvider.safeGetFoo3();
    }

    public String getFoo3() throws RuntimeException {
        return subNullFooProvider.getFoo3();
    }

    public String safeGetFoo4() {
        return subJavadocNullFooProvider.safeGetFoo4();
    }

    public String getFoo4() throws RuntimeException {
        return subJavadocNullFooProvider.getFoo4();
    }
}
