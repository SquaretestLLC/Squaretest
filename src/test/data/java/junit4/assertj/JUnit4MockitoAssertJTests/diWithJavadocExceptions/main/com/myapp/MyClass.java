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

import com.myapp.javadocthrows.FooServiceException;
import com.myapp.javadocthrows.OtherException;
import com.myapp.javadocthrows.ThrowingFooProvider;

public class MyClass {
    private final ThrowingFooProvider fooProvider;

    public MyClass(final ThrowingFooProvider fooProvider) {
        this.fooProvider = fooProvider;
    }

    public String getFooFromPlace(final String key) {
        return fooProvider.getFooFromPlace(key);
    }

    public String getBar(final String barName) {
        return fooProvider.getBar(barName);
    }

    public String getBar1(final String barName) {
        return fooProvider.getBar1(barName);
    }

    public String getBar2(final String barName) {
        return fooProvider.getBar2(barName);
    }

    public String getBar3(final String barName) {
        return fooProvider.getBar3(barName);
    }

    public String tryGetBarWithDupExceptions(final String barName) {
        return fooProvider.tryGetBarWithDupExceptions(barName);
    }

    /**
     * @param barName the name of the bar
     * @throws RuntimeException if fooProvider throws.
     * @return the bar
     */
    public String tryGetBarWithTwoExceptions(final String barName) {
        return fooProvider.tryGetBarWithTwoExceptions(barName);
    }

    /**
     * @param barName the name of the bar
     * @throws FooServiceException if fooProvider throws.
     * @return the bar
     */
    public String tryGetBarWithTwoExceptions1(final String barName) {
        return fooProvider.tryGetBarWithTwoExceptions(barName);
    }

    /**
     * @param barName the name of the bar
     * @throws FooServiceException if fooProvider throws.
     * @throws OtherException if fooProvider throws.
     * @return the bar
     */
    public String tryGetBarWithTwoExceptions2(final String barName) {
        return fooProvider.tryGetBarWithTwoExceptions(barName);
    }

    /**
     * @param barName the name of the bar
     * @return the bar
     */
    public String tryGetBarWithTwoExceptions3(final String barName) throws RuntimeException {
        return fooProvider.tryGetBarWithTwoExceptions(barName);
    }

    /**
     * @param barName the name of the bar
     * @return the bar
     */
    public String tryGetBarWithTwoExceptions4(final String barName) throws FooServiceException {
        return fooProvider.tryGetBarWithTwoExceptions(barName);
    }

    /**
     * @param barName the name of the bar
     * @return the bar
     */
    public String tryGetBarWithTwoExceptions5(final String barName) throws FooServiceException, OtherException {
        return fooProvider.tryGetBarWithTwoExceptions(barName);
    }


    /**
     * @param barName the name of the bar
     * @throws FooServiceException if fooProvider throws.
     * @throws OtherException if fooProvider throws.
     * @return the bar
     */
    public String tryGetBarWithTwoExceptions6(final String barName) throws FooServiceException, OtherException {
        return fooProvider.tryGetBarWithTwoExceptions(barName);
    }
}
