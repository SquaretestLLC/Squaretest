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

public class SubFoo1 {
    private String subFoo1Name;
    private SubFoo2 subFoo2;

    public String getSubFoo1Name() {
        return subFoo1Name;
    }

    public void setSubFoo1Name(String subFoo1Name) {
        this.subFoo1Name = subFoo1Name;
    }

    public SubFoo2 getSubFoo2() {
        return subFoo2;
    }

    public void setSubFoo2(SubFoo2 subFoo2) {
        this.subFoo2 = subFoo2;
    }
}
