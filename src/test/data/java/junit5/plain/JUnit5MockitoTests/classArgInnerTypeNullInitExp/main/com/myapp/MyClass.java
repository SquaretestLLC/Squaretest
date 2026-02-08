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

import java.sql.Connection;
import java.sql.SQLException;

public class MyClass {
    private final FooService fooService;

    public MyClass(FooService fooService) {
        this.fooService = fooService;
    }

    public String getFoo1() throws SQLException {
        Connection connection = fooService.unwrap1(Connection.class);
        return connection.getCatalog();
    }
    public String getFoo2(final String id) throws SQLException {
        Object connection = fooService.unwrap1(fooService.getTheClass1(id));
        return connection.toString();
    }
    public String getFoo3(final String id) throws SQLException {
        Connection connection = fooService.unwrap1(fooService.getTheClass2(id));
        return connection.toString();
    }
}
