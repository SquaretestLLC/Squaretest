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
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class VariableUpdater {

    private final boolean shouldSetPackageLocalField;
    private final Api.CodeStyleUtils codeStyleUtils;

    public VariableUpdater(
            final boolean shouldSetPackageLocalField, final Api.CodeStyleUtils codeStyleUtils) {
        this.shouldSetPackageLocalField = shouldSetPackageLocalField;
        this.codeStyleUtils = codeStyleUtils;
    }

    public void updateVariables(final List<VariableWrapper> varsToUpdate) {
        for(final VariableWrapper variableWrapper : varsToUpdate) {
            final boolean shouldBeMocked = variableWrapper.shouldMock();
            final boolean wasChanged = variableWrapper.wasChanged();
            if(wasChanged) {
                final Api.Variable variable = variableWrapper.getVariable();
                variable.setShouldBeMocked(shouldBeMocked);
                if(shouldBeMocked) {
                    // The dependency was changed from "don't mock" to "do mock".
                    variable.setTestClassMemberName("mock" + StringUtils.capitalize(variable.getDeclaredNameWithoutPrefix()));
                    variable.setTestClassLocalFieldName("mock" + StringUtils.capitalize(variable.getDeclaredNameWithoutPrefix()));
                    if(shouldSetPackageLocalField) {
                        variable.setShouldStoreInReference(false);
                        variable.setInitExpression(String.format("mock(%s.class)", variable.getType().getCanonicalName()));
                    } else {
                        variable.setShouldStoreInReference(true);
                    }
                } else {
                    // The dependency was changed from "mock" to "don't mock".
                    variable.setTestClassMemberName(this.codeStyleUtils.suggestMemberName(variable.getType().getName()));
                    variable.setTestClassLocalFieldName(this.codeStyleUtils.suggestLocalFieldName(variable.getType().getName()));
                    variable.setShouldStoreInReference(false);
                    // Set the init expression back to the default to avoid the case where it is set to mock(Foo.class).
                    // This happens when the template variable: $shouldSetPackageLocalFields = true and the template
                    // also determines that the variable should be mocked.
                    variable.setInitExpression(variable.getDefaultInitExpression());
                }
            }
        }
    }
}
