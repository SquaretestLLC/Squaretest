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
package com.myapp.annotationdto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class FooWithDataAnnotationMultiConstructors {

    @JsonIgnore
    private String transientValue;

    private String name;
    private long serialId;
    private String information;

    public FooWithDataAnnotationMultiConstructors() {
        this("");
    }

    public FooWithDataAnnotationMultiConstructors(String name) {
        this(name, -1);
    }

    public FooWithDataAnnotationMultiConstructors(String name, long serialId) {
        this(name, serialId, "");
    }

    public FooWithDataAnnotationMultiConstructors(String name, long serialId, String information) {
        this.name = name;
        this.serialId = serialId;
        this.information = information;
    }
}
