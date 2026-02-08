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
package com.squaretest.confirmsettings.v1;

import com.squaretest.template.api.Api;
import com.squaretest.template.impl.TypeImpl;

public class VariableWrapper {

    private final Api.Variable variable;
    private boolean shouldMock;

    public VariableWrapper(final Api.Variable variable) {
        this.variable = variable;
        this.shouldMock = variable.getShouldBeMocked();
    }

    public String getDisplayName() {
        return this.variable.getDeclaredName();
    }

    public boolean wasChanged() {
        return this.variable.getShouldBeMocked() != shouldMock;
    }

    public boolean shouldMock() {
        return shouldMock;
    }

    public void setShouldMock(final boolean newVal) {
        this.shouldMock = newVal;
    }

    public boolean canMock() {
        final TypeImpl type = (TypeImpl) this.variable.getType();
        if(type.isMockable()) {
            // If the type is mockable under the old Mockito rules, return true.
            return true;
        }
        if(type.isArray() || type.isEnum()) {
            // Do not allow mocking of arrays or enums.
            return false;
        }
        if(type.isRecognized() && !type.isMockable()) {
            // If we recognize the type, and it's not mockable return false.
            // This covers primitives, boxed types, java.lang.Class, etc.
            return false;
        }

        // The class is considered to be unmockable, because it is static or final. The user may want to mock it
        // using the new, inline mock maker. We should allow them to do that.
        return true;
    }

    public boolean isField() {
        return this.variable instanceof Api.ClassMember;
    }

    public Api.Variable getVariable() {
        return this.variable;
    }
}
