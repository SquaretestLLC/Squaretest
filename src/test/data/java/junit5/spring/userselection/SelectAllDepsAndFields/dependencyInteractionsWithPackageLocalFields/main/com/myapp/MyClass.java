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

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.logging.Logger;

public class MyClass {

    UserServiceAdapter userServiceAdapter;
    ExecutorService executorService;
    Logger logger;

    public MyClass() {
    }

    /**
     * Test simple dependency with return value.
     */
    public List<User> getAllUsersSync() {
        logger.info("getAllUsersSync()");
        return this.userServiceAdapter.getUsers();
    }

    /**
     * Test executor service with runnable.
     */
    public Future<List<User>> getAllUsersAsync() {
        logger.info("getAllUsersAsync()");
        return executorService.submit(() -> getAllUsersSync());
    }

    /**
     * Test dependency with no return value.
     */
    public void storeUserSync(final User user) {
        userServiceAdapter.putUser(user);
    }

    /**
     * Test executor service with runnable.
     */
    public Future<?> storeUserAsync(final User user) {
        return executorService.submit(() -> storeUserSync(user));
    }

    /**
     * Test dependency with return value and argument.
     */
    public User getUserWithIdSync(final String userId) {
        return userServiceAdapter.getUserWithId("userId");
    }

    /**
     * Test executor service with callable.
     */
    public Future<User> getUserWithIdAsync(final String userId) {
        logger.info("getUserWithIdAsync()");
        return executorService.submit(() -> getUserWithIdSync("userId"));
    }

    /**
     * Test throwing same exception as dep (just let the dep exception bubble up).
     */
    public void doSomethingThatThrowsSameExceptionAsDep() throws IOException {
        userServiceAdapter.doSomethingThatThrows();
    }

    /**
     * Test catch and rethrow dep exception.
     */
    public void doSomethingThatThrowsDifferentExceptionThanDep() throws SQLException {
        try {
            userServiceAdapter.doSomethingThatThrows();
        } catch (IOException e) {
            throw new SQLException("Asdf");
        }
    }
}