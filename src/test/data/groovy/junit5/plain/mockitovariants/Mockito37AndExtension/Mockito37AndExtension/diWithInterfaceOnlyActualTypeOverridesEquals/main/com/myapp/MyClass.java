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

import com.myapp.foos.BooleanBuilder;
import com.myapp.foos.User;
import com.myapp.foos.UserAdapter;

import java.util.List;

/**
 * This test uses classes based on QueryDSL (https://github.com/querydsl/querydsl).
 * The UserAdapter.getUsers(predicate) method takes in a predicate. Predicate does not override Object.equals().
 * MyClass passes in a BooleanBuilder. BooleanBuilder does override Object.equals(); this allows us to use an inline
 * initialization expression in the mockito stubs (when statement).
 */
public class MyClass {

    private final UserAdapter userAdapter;
    public MyClass(final UserAdapter userAdapter) {
        this.userAdapter = userAdapter;
    }

    public List<User> getAllUsers() {
        return this.userAdapter.getUsers(new BooleanBuilder());
    }

    public List<User> getAllSimilarUsers(final User user) {
        return this.userAdapter.getSimilarUsers(user, new BooleanBuilder());
    }
}
