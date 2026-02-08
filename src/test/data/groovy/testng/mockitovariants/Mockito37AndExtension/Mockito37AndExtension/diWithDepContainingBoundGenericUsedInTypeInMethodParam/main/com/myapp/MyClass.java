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

import com.myapp.GenericStore;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class MyClass {
    private final GenericStore<StoredOrder> genericStore;

    public MyClass(final GenericStore<StoredOrder> genericStore) {
        this.genericStore = genericStore;
    }

    public void storeObjs(final String serializedObjs) {
        genericStore.storeObjs(unpackObjs(serializedObjs));
    }

    // APIs that use variants of the List formal param type. We use List because it overrides Object.equals(...) and
    // the matcher logic uses inline init expressions accordingly.
    public void storeObjsWithPublicStaticList(final String serializedObjs) {
        final MyPublicStaticList<StoredOrder> myPublicStaticList = new MyPublicStaticList<>();
        genericStore.storeObjs(myPublicStaticList);
    }

    public void storeObjsWithPublicInstanceList(final String serializedObjs) {
        final MyPublicInstanceList<StoredOrder> myPublicInstanceList = new MyPublicInstanceList<>();
        genericStore.storeObjs(myPublicInstanceList);
    }
    public void storeObjsWithPackageStaticList(final String serializedObjs) {
        final MyPackageStaticList<StoredOrder> myPackageStaticList = new MyPackageStaticList<>();
        genericStore.storeObjs(myPackageStaticList);
    }

    public void storeObjsWithPackageInstanceList(final String serializedObjs) {
        final MyPackageInstanceList<StoredOrder> myPackageInstanceList = new MyPackageInstanceList<>();
        genericStore.storeObjs(myPackageInstanceList);
    }
    public void storeObjsWithPrivateStaticList(final String serializedObjs) {
        final MyPrivateStaticList<StoredOrder> myPrivateStaticList = new MyPrivateStaticList<>();
        genericStore.storeObjs(myPrivateStaticList);
    }

    public void storeObjsWithPrivateInstanceList(final String serializedObjs) {
        final MyPrivateInstanceList<StoredOrder> myPrivateInstanceList = new MyPrivateInstanceList<>();
        genericStore.storeObjs(myPrivateInstanceList);
    }

    public void storeObjsWithAnonList(final String serializedObjs) {
        genericStore.storeObjs(new List<StoredOrder>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<StoredOrder> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(StoredOrder storedOrder) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends StoredOrder> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, Collection<? extends StoredOrder> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public StoredOrder get(int index) {
                return null;
            }

            @Override
            public StoredOrder set(int index, StoredOrder element) {
                return null;
            }

            @Override
            public void add(int index, StoredOrder element) {

            }

            @Override
            public StoredOrder remove(int index) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @Override
            public ListIterator<StoredOrder> listIterator() {
                return null;
            }

            @Override
            public ListIterator<StoredOrder> listIterator(int index) {
                return null;
            }

            @Override
            public List<StoredOrder> subList(int fromIndex, int toIndex) {
                return null;
            }
        });
    }

    // APIs that use variants of the Consumer formal param type. We use Consumer because it does not override
    // Object.equals(...) and the matcher logic uses inline init expressions accordingly. Also, the formal type is:
    // Consumer<Builder<T>> (the generic in the formal param is nested in another level). This ensures Squaretest
    // correctly uses the actual type in certain cases.
    public void storeObjsWithBuilderConsumer(final String key) {
        genericStore.putObj(storedOrderBuilder -> storedOrderBuilder.build());
    }

    public void storeObjsWithMethodRefConsumer(final String key) {
        genericStore.putObj(Builder::build);
    }

    public void storeObjsWithPublicStaticBuilderConsumer(final String key) {
        genericStore.putObj(new MyPublicStaticConsumer());
    }

    public void storeObjsWithPublicStaticBuilderConsumerWithEquals(final String key) {
        genericStore.putObj(new MyPublicStaticConsumerWithEquals());
    }

    public static class MyPublicStaticConsumer implements Consumer<Builder<StoredOrder>> {

        @Override
        public void accept(Builder<StoredOrder> storedOrderBuilder) {

        }

        @Override
        public Consumer<Builder<StoredOrder>> andThen(Consumer<? super Builder<StoredOrder>> after) {
            return null;
        }
    }

    public static class MyPublicStaticConsumerWithEquals implements Consumer<Builder<StoredOrder>> {

        @Override
        public void accept(Builder<StoredOrder> storedOrderBuilder) {

        }

        @Override
        public Consumer<Builder<StoredOrder>> andThen(Consumer<? super Builder<StoredOrder>> after) {
            return null;
        }

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }
    }

    public class MyPublicInstanceConsumerWithEquals implements Consumer<Builder<StoredOrder>> {

        @Override
        public void accept(Builder<StoredOrder> storedOrderBuilder) {

        }

        @Override
        public Consumer<Builder<StoredOrder>> andThen(Consumer<? super Builder<StoredOrder>> after) {
            return null;
        }

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }
    }

    // APIs that use variants of the Supplier formal param type. We use Supplier because it does not override
    // Object.equals(...) and the matcher logic uses inline init expressions accordingly.
    public void storeObjWithPrivateInstanceSupplier(final String key) {
        genericStore.putObjWithSupplier(new MyPrivateInstanceSupplier());
    }

    public void storeObjWithPrivateStaticSupplier(final String key) {
        genericStore.putObjWithSupplier(new MyPrivateStaticSupplier());
    }

    public void storeObjWithPublicInstanceSupplier(final String key) {
        genericStore.putObjWithSupplier(new MyPublicInstanceSupplier());
    }

    public void storeObjWithPublicStaticSupplier(final String key) {
        genericStore.putObjWithSupplier(new MyPublicStaticSupplier());
    }

    public void storeObjWithPackageInstanceSupplier(final String key) {
        genericStore.putObjWithSupplier(new MyPackageInstanceSupplier());
    }

    public void storeObjWithPackageStaticSupplier(final String key) {
        genericStore.putObjWithSupplier(new MyPackageStaticSupplier());
    }

    public void storeObjWithAnonSupplier(final String key) {
        genericStore.putObjWithSupplier(new Supplier<StoredOrder>() {
            @Override
            public StoredOrder get() {
                return new StoredOrder();
            }
        });
    }

    public void storeObjWithLambdaSupplier(final String key) {
        genericStore.putObjWithSupplier(() -> new StoredOrder());
    }

    public void storeObjWithMethodRefSupplier(final String key) {
        genericStore.putObjWithSupplier(StoredOrder::new);
    }

    private List<StoredOrder> unpackObjs(final String serializedObjs) {
        return new ArrayList<>();
    }

    public static class MyPublicStaticSupplier implements Supplier<StoredOrder> {
        @Override
        public StoredOrder get() {
            return new StoredOrder();
        }
    }

    public class MyPublicInstanceSupplier implements Supplier<StoredOrder> {
        @Override
        public StoredOrder get() {
            return new StoredOrder();
        }
    }

    static class MyPackageStaticSupplier implements Supplier<StoredOrder> {
        @Override
        public StoredOrder get() {
            return new StoredOrder();
        }
    }

    class MyPackageInstanceSupplier implements Supplier<StoredOrder> {
        @Override
        public StoredOrder get() {
            return new StoredOrder();
        }
    }

    private class MyPrivateInstanceSupplier implements Supplier<StoredOrder> {
        @Override
        public StoredOrder get() {
            return new StoredOrder();
        }
    }

    private class MyPrivateStaticSupplier implements Supplier<StoredOrder> {

        @Override
        public StoredOrder get() {
            return new StoredOrder();
        }
    }

    public static class MyPublicStaticList<T> extends ArrayList<T> {
    }

    public class MyPublicInstanceList<T> extends ArrayList<T> {
    }

    static class MyPackageStaticList<T> extends ArrayList<T> {
    }

    class MyPackageInstanceList<T> extends ArrayList<T> {
    }

    private static class MyPrivateStaticList<T> extends ArrayList<T> {

    }

    private class MyPrivateInstanceList<T> extends ArrayList<T> {

    }
}
