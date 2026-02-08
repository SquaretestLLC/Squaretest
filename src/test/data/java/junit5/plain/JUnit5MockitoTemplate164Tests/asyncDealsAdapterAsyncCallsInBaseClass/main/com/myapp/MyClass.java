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
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.logging.Logger;

public class MyClass extends BaseClass {
    private static final Logger Log = Logger.getLogger(MyClass.class.getName());

    public MyClass(
            final DealsServiceClient dealsService, final ExecutorService executor) {
        super(dealsService, executor);
    }

    public CompletableFuture<List<DisplayDeal>> getDeals(final String userId) {
        Log.info("getDeals(..) called");
        return super.getDeals(userId);
    }
}
