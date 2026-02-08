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

import javax.annotation.Nonnull;
import java.io.IOException;
import java.sql.SQLException;

public class MyClass {

    @Nonnull
    private final FooService fooService;
    @Nonnull
    private final MetricsAdapter metricsAdapter;

    public MyClass(
            @Nonnull FooService fooService,
            @Nonnull MetricsAdapter metricsAdapter) {
        this.fooService = fooService;
        this.metricsAdapter = metricsAdapter;
    }

    public String getSomething1(String theValue) throws SQLException, IOException {
        metricsAdapter.recordCall0(theValue);
        String data = fooService.getData(theValue);
        metricsAdapter.recordCall1(theValue);
        if (data == null) {
            data = fooService.getOtherData(theValue);
            metricsAdapter.recordCall2(theValue);
        }

        if (data == null) {
            data = fooService.getThingFromDatabase(theValue);
            metricsAdapter.recordCall3(theValue);
        }
        metricsAdapter.recordCall4(theValue);
        return data;
    }

    public String getSomething2(String theValue) throws SQLException, IOException {
        String data = fooService.getData(theValue);
        metricsAdapter.recordCall1(theValue);
        if (data == null) {
            data = fooService.getOtherData(theValue);
            metricsAdapter.recordCall2(theValue);
        }

        if (data == null) {
            data = fooService.getThingFromDatabase(theValue);
            metricsAdapter.recordCall3(theValue);
        }
        metricsAdapter.recordCall4(theValue);
        return data;
    }

}
