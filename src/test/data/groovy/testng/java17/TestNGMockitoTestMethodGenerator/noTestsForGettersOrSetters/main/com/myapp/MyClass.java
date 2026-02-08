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

import java.util.List;

public class MyClass {

    private final String displayName;
    private final String canonicalName;
    private final List<String> canonicalNamesRequiredForType;
    private final String canonicalText;
    private final String defaultInitExpression;
    private final boolean canBeMocked;

    public MyClass(String displayName, String canonicalName, List<String> canonicalNamesRequiredForType, String canonicalText, String defaultInitExpression, boolean canBeMocked) {
        this.displayName = displayName;
        this.canonicalName = canonicalName;
        this.canonicalNamesRequiredForType = canonicalNamesRequiredForType;
        this.canonicalText = canonicalText;
        this.defaultInitExpression = defaultInitExpression;
        this.canBeMocked = canBeMocked;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getCanonicalName() {
        return canonicalName;
    }

    public List<String> getCanonicalNamesRequiredForType() {
        return canonicalNamesRequiredForType;
    }

    public String getCanonicalText() {
        return canonicalText;
    }

    public String getDefaultInitExpression() {
        return defaultInitExpression;
    }

    public boolean isCanBeMocked() {
        return canBeMocked;
    }
}
