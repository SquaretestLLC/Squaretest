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
package com.myapp.javadocthrows;

import java.io.IOException;

public abstract class ThrowingFooProvider extends ThrowingFooProviderBase {

    /**
     * Gets the object.
     * @param key the key of the object
     * @throws RuntimeException in some cases
     * @throws NullPointerException in other cases.
     * @throws {@link FooServiceException incorrect value}
     * @throws UnresolvedException should be ignored
     * @throws IOException never, this is a checked exception not declared by the method and should be ignored.
     * @return a String containing the object contents.
     */
    public abstract String getFooFromPlace(final String key);

    // This should generate tests for FooServiceException.
    /**
     * @inheritDoc
     */
    @Override
    public String getBar(final String barName) {
        return barName;
    }

    // This should generate tests for FooServiceException.
    @Override
    public String getBar1(final String barName) {
        return barName;
    }

    // This should generate tests for OtherException.
    // The FooServiceException in the supertype javadocs should be ignored, because this javadoc comment
    // has throws sections.
    /**
     * Gets the bar object.
     * @param barName the name of the bar
     * @throws OtherException in other cases.
     * @return a String containing the object contents.
     */
    @Override
    public String getBar2(final String barName) {
        return barName;
    }

    // This should create tests for FooServiceException and OtherException.
    // This tests the @exception tag as well as the throws tag.
    @Override
    public String getBar3(final String barName) {
        return null;
    }

    /**
     * Gets the bar object.
     * @param barName the name of the bar
     * @exception FooServiceException in some cases
     * @throws FooServiceException in other cases.
     * @throws OtherException in other cases.
     * @throws OtherException in other cases.
     * @return a String containing the object contents.
     */
    public String tryGetBarWithDupExceptions(final String barName) {
        return barName;
    }

    /**
     * Gets the bar object.
     * @param barName the name of the bar
     * @exception FooServiceException in some cases
     * @throws OtherException in other cases.
     * @return a String containing the object contents.
     */
    public String tryGetBarWithTwoExceptions(final String barName) {
        return barName;
    }
}
