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

import java.util.NoSuchElementException;
import java.util.function.Function;

public class MyClass<T, X extends Throwable> {
    private T value;
    private X error;

    public static <T, X extends Throwable> MyClass<T, X> of(T value) {
        return new MyClass<>(value);
    }
    public static <T, X extends Throwable> MyClass<T, X> ofError(X error) {
        return new MyClass<>(error);
    }
    private MyClass(T value) {
        this.value = value;
    }
    private MyClass(X error) {
        this.error = error;
    }

    public T onError(Function<X, ? extends RuntimeException> exceptionSupplier) {
        if (value != null) {
            return value;
        } else {
            throw exceptionSupplier.apply(error);
        }
    }

    public T get() {
        if (value != null) {
            return value;
        } else {
            throw new NoSuchElementException("Result contains no value, did you call get() instead of onError?");
        }
    }
    public X getError() {
        if (error != null) {
            return error;
        } else {
            throw new NoSuchElementException("Result contains no error, did you call getError() instead of onError?");
        }
    }
    public boolean isError() {
        return error != null;
    }
}
