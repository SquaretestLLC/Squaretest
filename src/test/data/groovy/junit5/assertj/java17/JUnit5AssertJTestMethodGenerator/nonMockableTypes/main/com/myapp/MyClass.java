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
import java.util.concurrent.ExecutorService;

public class MyClass {

    public MyClass(
            FinalFoo finalFoo,
            StaticFoo staticFoo,
            EnumFoo enumFoo,
            Object[] someObjects,
            int defaultCapacity,
            Object... someOtherObjects) {
    }

    public void performWithStaticFoo(StaticFoo listener) {
        System.out.println(listener);
    }

    public void performWithFinalFoo(FinalFoo finalFoo) {
        System.out.println(finalFoo);
    }

    public void performWithListener(FinalFoo finalFooListener) {
        System.out.println(finalFooListener);
    }

    public void performWithStaticListener(StaticFoo staticFooListener) {
        System.out.println(staticFooListener);
    }

    public void performWithCallback(FinalFoo finalFooCallback) {
        System.out.println(finalFooCallback);
    }

    public void performWithArrayListener(Object[] listener) {
        System.out.println(listener);
    }

    public void performWithArbitraryListeners(Object... listeners) {
        System.out.println(listeners);
    }

    public static class StaticFoo {
    }
}
