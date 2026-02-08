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
package com.myapp.otherbeans;

import java.time.LocalDateTime;
import io.swagger.annotations.ApiParam;

public class BeanWithFieldNameBeanAnnotation {
    @ApiParam
    private long id;
    private String name;
    private String description;
    private LocalDateTime startDate;

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(final LocalDateTime startDate) {
        this.startDate = startDate;
    }

    /**
     * This is needed to stop this class from being considered to be a bean.
     * Having a non-getter-setter method disqualifies the class from being a bean;
     * however, the class/fields/methods having DTO bean annotation should make
     * Squaretest consider this class to be a bean.
     */
    public void perform(final String firstValue, final String secondValue) {

    }
}
