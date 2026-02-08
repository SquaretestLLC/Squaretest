/*
 *
 *  * Copyright 2026 Squaretest LLC.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *   http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */
package com.squaretest.template.impl;

import com.squaretest.template.api.Api;

public class SimpleAnnotationHolder implements Api.AnnotationHolder {

    private final Api.FluentList<Api.Annotation> annotations;

    public SimpleAnnotationHolder(final Api.FluentList<Api.Annotation> annotations) {
        this.annotations = annotations;
    }

    @Override
    public Api.FluentList<Api.Annotation> getAnnotations() {
        return annotations;
    }
}
