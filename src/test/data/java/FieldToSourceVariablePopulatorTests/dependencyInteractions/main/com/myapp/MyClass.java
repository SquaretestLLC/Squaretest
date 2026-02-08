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
import java.util.concurrent.Future;
import java.util.logging.Logger;

public class MyClass {

    private final UserServiceAdapter userServiceAdapter;
    private final ExecutorService executorService;
    private final Foo foo;
    private final Logger logger;


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

    /**
     * For some reason, executorService.submit() does not resolve correctly when you pass it a callable (instead
     * of a lambda or a method-reference expression) in the project that IDEA creates for unit-tests.
     *
     * This method tests Squaretest's ability to correctly determine dependencies for such a case by using a method
     * defined in Foo instead of ExecutorService.
     * @param userId the userId
     * @return a future.
     */
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