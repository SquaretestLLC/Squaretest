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
package com.squaretest.generation.existingtest.common;

import org.jetbrains.annotations.Nullable;

public class VariableInfo {

    @Nullable
    private RefInfo refInfo;
    private boolean shouldBeMocked;
    private String initExpression;
    private boolean shouldStoreInReference;

    public VariableInfo(
            @Nullable final RefInfo refInfo,
            final boolean shouldBeMocked,
            final String initExpression,
            final boolean shouldStoreInReference) {
        this.refInfo = refInfo;
        this.shouldBeMocked = shouldBeMocked;
        this.initExpression = initExpression;
        this.shouldStoreInReference = shouldStoreInReference;
    }

    public VariableInfo() {
    }

    @Nullable
    public RefInfo getRefInfo() {
        return refInfo;
    }

    public void setRefInfo(@Nullable final RefInfo refInfo) {
        this.refInfo = refInfo;
    }

    public boolean shouldBeMocked() {
        return shouldBeMocked;
    }

    public void setShouldBeMocked(final boolean shouldBeMocked) {
        this.shouldBeMocked = shouldBeMocked;
    }

    public String getInitExpression() {
        return initExpression;
    }

    public void setInitExpression(final String initExpression) {
        this.initExpression = initExpression;
    }

    public boolean shouldStoreInReference() {
        return shouldStoreInReference;
    }

    public void setShouldStoreInReference(final boolean shouldStoreInReference) {
        this.shouldStoreInReference = shouldStoreInReference;
    }
}
