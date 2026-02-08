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

public class OtherData3 {
    private String id;
    private String name;
    private String other;

    public String getId() {
        return id;
    }

    public void setId(final String value) {
        this.id = value;
    }

    public OtherData3 withId(final String value) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(final String value) {
        this.name = value;
    }

    public OtherData3 withName(final String value) {
        this.name = value;
        return this;
    }

    public String getOther() {
        return other;
    }

    public void setOther(final String value) {
        this.other = value;
    }

    public OtherData3 withOther(final String value) {
        this.other = value;
        return this;
    }
}

