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
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class MyClass {
    private com.myapp.UserServiceAdapter userServiceAdapter;
    private ScheduledExecutorService scheduledExecutorService;
    private Executor executor;
    private Logger logger;
    private Object stateVariable;
    private Integer intDependency;

    public MyClass(
            final com.myapp.UserServiceAdapter userServiceAdapter, final ScheduledExecutorService scheduledExecutorService, final Executor executor, final Logger logger, final Integer intDependency) {
        this.userServiceAdapter = userServiceAdapter;
        this.scheduledExecutorService = scheduledExecutorService;
        this.logger = logger;
        this.executor = executor;
        this.intDependency = intDependency;
        this.stateVariable = new Object();
    }

    /**
     * Tests that matchers are used for all arguments of scheduledExecutorService.schedule(...) call.
     */
    public Future<List<com.myapp.User>> scheduleGetAllUsersAsync() {
        stateVariable.notify();
        intDependency.byteValue();
        intDependency.notify();
        logger.info("getAllUsersAsync()");
        logger.warning("Warn: getAllUsersAsync()");
        logger.fine("Fine: getAllUsersAsync()");
        final User user = this.userServiceAdapter.getUserWithId("userId");
        return scheduledExecutorService.schedule(() -> this.userServiceAdapter.getUsers(), 60, TimeUnit.SECONDS);
    }
}