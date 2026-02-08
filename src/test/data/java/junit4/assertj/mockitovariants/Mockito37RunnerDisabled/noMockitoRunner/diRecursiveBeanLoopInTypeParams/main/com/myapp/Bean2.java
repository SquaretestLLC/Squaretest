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

import java.util.List;

public class Bean2 {
    private String bean2Name;
    private List<Bean1> bean1List;

    public String getBean2Name() {
        return bean2Name;
    }

    public void setBean2Name(String bean2Name) {
        this.bean2Name = bean2Name;
    }

    public List<Bean1> getBean1List() {
        return bean1List;
    }

    public void setBean1List(List<Bean1> bean1List) {
        this.bean1List = bean1List;
    }
}
