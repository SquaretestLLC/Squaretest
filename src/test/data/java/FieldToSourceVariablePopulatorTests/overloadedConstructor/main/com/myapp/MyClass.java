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
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Logger;

public class MyClass {

    private final UserServiceAdapter userServiceAdapter;
    private final ExecutorService executorService;
    private final Foo foo;
    private final Logger logger;


    public MyClass(final UserServiceAdapter userServiceAdapter) {
        this(userServiceAdapter, Executors.newSingleThreadScheduledExecutor());
    }

    public MyClass(final UserServiceAdapter userServiceAdapter, final ExecutorService executorService) {
        this(userServiceAdapter, executorService, Logger.getLogger(MyClass.class.getName()), new Foo());
    }

    public MyClass(
            final UserServiceAdapter userServiceAdapter,
            final ExecutorService executorService,
            final Logger logger,
            final Foo foo) {
        this.userServiceAdapter = userServiceAdapter;
        this.executorService = executorService;
        this.logger = logger;
        this.foo = foo;
    }

    public List<User> getAllUsersSync() {
        logger.info("getAllUsersSync()");
        return this.userServiceAdapter.getUsers();

    }

    public Future<List<User>> getAllUsersAsync() {
        logger.info("getAllUsersAsync()");
        return executorService.submit(() -> getAllUsersSync());
    }

    public User getUserWithIdSync(final String userId) {
        return userServiceAdapter.getUserWithId("userId");
    }

    public Future<User> getUserWithIdAsync(final String userId) {
        logger.info("getUserWithIdAsync()");
        return executorService.submit(() -> getUserWithIdSync("userId"));
    }

    public Future<User> getUserWithIdAsync1(final String userId) {
        logger.info("getUserWithIdAsync1()");
        return foo.submit(() -> getUserWithIdSync("userId"));
    }

    public Future<User> getUserWithIdAsync2(final String userId) {
        logger.info("getUserWithIdAsync2()");
        return foo.submit(new Callable<User>() {
            @Override
            public User call() {
                return getUserWithIdSync("userId");
            }
        });
    }
}
