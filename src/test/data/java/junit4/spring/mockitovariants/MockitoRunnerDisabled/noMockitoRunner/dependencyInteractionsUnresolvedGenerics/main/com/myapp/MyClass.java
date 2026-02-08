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

public class MyClass<T> {

    public static class Bar {
    }

    public static class GenericBar<K> {
        private final K t;

        public GenericBar(K t) {
            this.t = t;
        }
    }

    private final Producer<T> producer;

    public MyClass(final Producer<T> producer) {
        this.producer = producer;
    }

    public T createNewT(final String theString) {
        return producer.createNewT(theString);
    }

    // Test cases where type parameter T is not resolved by the call expression.

    public T createNewT(final T templateT) {
        return producer.createNewT(templateT);
    }

    public <K> K createNewK(final K templateK) {
        return producer.createNewK(templateK);
    }

    public <K> K createNewK2(final K templateK) {
        return null;
    }

    public <K> K createNewK(final Class<K> kClass, final String key) {
        return producer.createNewK(kClass, key);
    }

    // Test cases where type parameter is resolved by the call expression
    public Bar createNewBar() {
        return producer.createNewK(new Bar());
    }

    public Bar createNewBar2() {
        return producer.createNewK(Bar.class, "bob");
    }

    // Test cases where type parameter is resolved by the call expression and the resolved type has a generic.
    public GenericBar<Bar> createNewGenericBar(final Bar bar) {
        return producer.createNewK(new GenericBar<>(bar));
    }

    public GenericBar createNewGenericBar2(Class<? extends GenericBar> klass) {
        return producer.createNewK(klass, "bob");
    }
}
