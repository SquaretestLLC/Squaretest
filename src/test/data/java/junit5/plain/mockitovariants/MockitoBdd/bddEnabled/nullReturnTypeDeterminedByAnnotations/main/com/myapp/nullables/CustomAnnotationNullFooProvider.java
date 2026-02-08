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
package com.myapp.nullables;

import com.myapp.nullables.annotations.Nonnull;
import com.myapp.nullables.annotations.NotNull;
import com.myapp.nullables.annotations.Nullable;

public abstract class CustomAnnotationNullFooProvider {
    @Nullable
    public abstract String safeGetFoo1();

    // The javadocs should be ignored, because the method has @Nonnull annotation.
    /**
     * This returns null.
     * @return null.
     */
    @Nonnull
    public abstract String getFoo1() throws RuntimeException;

    // The javadocs should be ignored, because the method has @Nonnull annotation.
    /**
     * This returns null.
     * @return null.
     */
    @NotNull
    public abstract String getOtherFoo1() throws RuntimeException;
}
