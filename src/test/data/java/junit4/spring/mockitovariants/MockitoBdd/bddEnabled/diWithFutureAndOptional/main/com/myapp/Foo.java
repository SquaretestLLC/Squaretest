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

import io.reactivex.Observable;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class Foo {

    public Optional<InnerFoo> capWithOptional(final String str) {
        return Optional.of(new InnerFoo(str));
    }

    public Observable<InnerFoo> capWithObservable(final String str) {
        return Observable.just(new InnerFoo(str));
    }

    public Future<InnerFoo> capWithFuture(final String str) {
        return CompletableFuture.completedFuture(new InnerFoo(str));
    }

    public CompletableFuture<InnerFoo> capWithCompletable(final String str) {
        return CompletableFuture.completedFuture(new InnerFoo(str));
    }

}
