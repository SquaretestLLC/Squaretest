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

public class FooDTO {
    private String fooDTOName;
    private SubFoo1 subFoo1;

    public String getFooDTOName() {
        return fooDTOName;
    }

    public void setFooDTOName(String fooDTOName) {
        this.fooDTOName = fooDTOName;
    }

    public SubFoo1 getSubFoo1() {
        return subFoo1;
    }

    public void setSubFoo1(SubFoo1 subFoo1) {
        this.subFoo1 = subFoo1;
    }
}
