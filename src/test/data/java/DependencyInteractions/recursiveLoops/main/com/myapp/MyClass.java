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

    public void getUsers() {
        logger.info("testing recursion");
        getUsersRecursive();
    }


    public void getUsersRecursive() {
        logger.warning("testing other recursion method");
        getUsers();
    }

    public void logSomethingFine() {
        logSomethingFine();
        logger.fine("this is fine");
        logSomethingFine();
    }
}
