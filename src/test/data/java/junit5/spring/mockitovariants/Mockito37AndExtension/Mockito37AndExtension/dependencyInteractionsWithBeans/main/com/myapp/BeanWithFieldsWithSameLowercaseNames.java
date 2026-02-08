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

public class BeanWithFieldsWithSameLowercaseNames {
    private String webOne;
    private String weBone;

    private String whoRepresents;
    private String whorePresents;

    public String getWebOne() {
        return webOne;
    }

    public void setWebOne(String webOne) {
        this.webOne = webOne;
    }

    public String getWeBone() {
        return weBone;
    }

    public void setWeBone(String weBone) {
        this.weBone = weBone;
    }

    public String getWhoRepresents() {
        return whoRepresents;
    }

    public void setWhoRepresents(String whoRepresents) {
        this.whoRepresents = whoRepresents;
    }

    public String getWhorePresents() {
        return whorePresents;
    }

    public void setWhorePresents(String whorePresents) {
        this.whorePresents = whorePresents;
    }
}
