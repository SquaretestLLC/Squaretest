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

import io.reactivex.rxjava3.core.Flowable ;
import io.reactivex.rxjava3.core.Observable;
import org.reactivestreams.Publisher;

import java.util.Optional;

public class MyClass {
    private Foo foo;

    public MyClass(Foo foo) {
        this.foo = foo;
    }

    public Optional<? extends SimpleBean> tryMakeSimpleBean(String name) {
        return foo.tryMakeSimpleBean(name);
    }

    public Observable<? extends SimpleBean> tryMakeObservableOfSimpleBean(String name) {
        return foo.tryMakeObservableOfSimpleBean(name);
    }

    public Observable<SimpleBean> tryMakeObservableOfOnlySimpleBean(String name) {
        return foo.tryMakeObservableOfOnlySimpleBean(name);
    }

    public Publisher<SimpleBean> tryMakePublisherOfOnlySimpleBean(String name) {
        return foo.tryMakePublisherOfOnlySimpleBean(name);
    }

    public Flowable<SimpleBean> tryMakeFlowableOfOnlySimpleBean(String name) {
        return foo.tryMakeFlowableOfOnlySimpleBean(name);
    }
}
