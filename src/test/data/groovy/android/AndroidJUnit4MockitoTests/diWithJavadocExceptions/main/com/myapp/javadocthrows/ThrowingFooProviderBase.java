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

public abstract class ThrowingFooProviderBase {
    /**
     * Gets the bar object.
     * @param barName the name of the bar
     * @throws FooServiceException in some cases
     * @throws IllegalArgumentException in other cases.
     * @return a String containing the object contents.
     */
    public abstract String getBar(String barName);

    /**
     * Gets the bar object.
     * @param barName the name of the bar
     * @throws FooServiceException in some cases
     * @throws IllegalArgumentException in other cases.
     * @return a String containing the object contents.
     */
    public abstract String getBar1(String barName);

    /**
     * Gets the bar object.
     * @param barName the name of the bar
     * @throws FooServiceException in some cases
     * @throws OtherException in other cases.
     * @return a String containing the object contents.
     */
    public abstract String getBar2(String barName);

    /**
     * Gets the bar object.
     * @param barName the name of the bar
     * @exception FooServiceException in some cases
     * @throws OtherException in other cases.
     * @return a String containing the object contents.
     */
    public abstract String getBar3(String barName);
}
