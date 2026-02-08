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

import java.net.SocketImplFactory;
import java.net.SocketOptions;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyClass {

    private static MyClass instance;

    private final ExecutorService mExecutorService;
    private final SocketImplFactory mSocketFactory;
    private final long mTimeout;

    MyClass(final ExecutorService executorService, final SocketImplFactory socketFactory, final long timeout) {
        mExecutorService = executorService;
        mSocketFactory = socketFactory;
        mTimeout = timeout;
    }

    public static synchronized MyClass getInstance() {
        if (instance == null) {
            instance = new MyClass(Executors.newSingleThreadExecutor(), null, 10);
        }
        return instance;
    }

    public SocketOptions createNewConnection() {
        return mSocketFactory.createSocketImpl();
    }
}
