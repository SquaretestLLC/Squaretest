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
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.logging.Logger;

public class MyClass {
    private UserServiceAdapter userServiceAdapter;
    private ExecutorService executorService;
    private Executor executor;
    private Logger logger;
    private Object stateVariable;
    private Integer intDependency;

    public MyClass(
            final UserServiceAdapter userServiceAdapter, final ExecutorService executorService, final Executor executor, final Logger logger, final Integer intDependency) {
        this.userServiceAdapter = userServiceAdapter;
        this.executorService = executorService;
        this.logger = logger;
        this.executor = executor;
        this.intDependency = intDependency;
        this.stateVariable = new Object();
    }

    public UserServiceAdapter getUserServiceAdapter() {
        return userServiceAdapter;
    }

    public Executor getExecutor() {
        return executor;
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }

    /**
     * Test simple dependency with return value.
     */
    public List<User> getAllUsersSync() {
        logger.info("getAllUsersSync()");
        stateVariable.notify();
        intDependency.byteValue();
        intDependency.notify();
        return getUserServiceAdapter().getUsers();
    }

    /**
     * Test executor service with runnable.
     */
    public Future<List<User>> getAllUsersAsync() {
        stateVariable.notify();
        intDependency.byteValue();
        intDependency.notify();
        logger.info("getAllUsersAsync()");
        logger.warning("Warn: getAllUsersAsync()");
        logger.fine("Fine: getAllUsersAsync()");
        final User user = getUserServiceAdapter().getUserWithId("userId");
        return getExecutorService().submit(() -> getAllUsersSync());
    }

    /**
     * Test pass runnable to execute method with void return type.
     * In this case, the lambda in the doAnswer(...) stub must return null.
     */
    public void getAllUsersAsyncWithExecutor() {
        stateVariable.notify();
        intDependency.byteValue();
        intDependency.notify();
        logger.info("getAllUsersAsync()");
        logger.warning("Warn: getAllUsersAsync()");
        logger.fine("Fine: getAllUsersAsync()");
        final User user = getUserServiceAdapter().getUserWithId("userId");
        getExecutor().execute(() -> getAllUsersSync());
    }

    /**
     * Test dependency with no return value.
     */
    public void storeUserSync(final User user) {
        stateVariable.notify();
        getUserServiceAdapter().putUser(user);
    }

    /**
     * Test executor service with runnable.
     */
    public Future<?> storeUserAsync(final User user) {
        stateVariable.notify();
        return getExecutorService().submit(() -> storeUserSync(user));
    }

    /**
     * Test dependency with return value and argument.
     */
    public User getUserWithIdSync(final String userId) {
        return getUserServiceAdapter().getUserWithId("userId");
    }

    /**
     * Test executor service with callable.
     */
    public Future<User> getUserWithIdAsync(final String userId) {
        logger.info("getUserWithIdAsync()");
        return getExecutorService().submit(() -> getUserWithIdSync("userId"));
    }

    /**
     * Test throwing same exception as dep (just let the dep exception bubble up).
     */
    public void doSomethingThatThrowsSameExceptionAsDep() throws IOException {
        getUserServiceAdapter().doSomethingThatThrows();
    }

    /**
     * Test catch and rethrow dep exception.
     */
    public void doSomethingThatThrowsDifferentExceptionThanDep() throws SQLException {
        try {
            getUserServiceAdapter().doSomethingThatThrows();
        } catch (IOException e) {
            throw new SQLException("Asdf");
        }
    }

    /**
     * Tests case where the source-method calls 2 methods on the same dependency, where each of those methods
     * throws an exception of the same type. In this case, the test-methods for the alt-flows should include the
     * name of the method (doSomethingThatThrows and doSomethingThatThrows1).
     *
     * This case also confirms the above works if the source-method lets the exception bubble up.
     *
     */
    public void call2MethodsThatThrow() throws IOException {
        getUserServiceAdapter().doSomethingThatThrows();
        getUserServiceAdapter().doSomethingThatThrows1();
    }

    /**
     * Tests case where the source-method calls 2 methods on the same dependency, where each of those methods
     * throws an exception of the same type. In this case, the test-methods for the alt-flows should include the
     * name of the method (doSomethingThatThrows and doSomethingThatThrows1).
     */
    public void safeCall2MethodsThatThrow() {
        try {
            getUserServiceAdapter().doSomethingThatThrows();
            getUserServiceAdapter().doSomethingThatThrows1();
        } catch (final Exception ex) {
            throw new RuntimeException(ex);
        }

    }
}