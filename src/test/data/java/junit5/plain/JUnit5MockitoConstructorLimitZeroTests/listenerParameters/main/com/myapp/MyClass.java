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
import java.util.List;
import java.util.EventListener;
import java.util.concurrent.ExecutorService;

public class MyClass {

    @Nonnull
    private final ExecutorService mExecutorService;
    private final EventListener mDefaultListener;

    /**
     * defaultListener should not be mocked with mock(Object.class). It should be treated as a normal dependency.
     * the useMocksForListeners flag should only apply to methods, not constructors.
     * @param executorServiceToUseForThings
     * @param defaultListener
     */
    public MyClass(@Nonnull ExecutorService executorServiceToUseForThings, EventListener defaultListener) {
        mExecutorService = executorServiceToUseForThings;
        mDefaultListener = defaultListener;
    }

    public void performOperationWithListenerUnused(final EventListener listener) {
        System.out.println("");
    }

    public void performOperation(final EventListener listener) {
        final String ret = listener.toString();
    }
}
