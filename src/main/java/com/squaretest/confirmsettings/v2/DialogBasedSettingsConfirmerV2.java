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
package com.squaretest.confirmsettings.v2;

import com.intellij.openapi.project.Project;
import com.squaretest.template.api.Api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DialogBasedSettingsConfirmerV2 {
    private final Project project;

    public DialogBasedSettingsConfirmerV2(final Project project) {
        this.project = project;
    }

    public Map<String, Object> confirmSettings(final Map<String, Object> settingsToConfirm) throws Api.UserCancelledGenerationException {
        final List<? extends Api.Variable> dependencies = Utils.safeGetDependencies(settingsToConfirm);
        final List<? extends Api.Variable> possibleDependencies = Utils.safeGetPossibleDependencies(settingsToConfirm);
        final boolean canCreateSourceClassInEachTestMethod = Utils.safeGetCanCreateSourceClassInEachTestMethod(settingsToConfirm);
        final boolean shouldCreateSourceInEachTestMethod = Utils.safeGetShouldCreateSourceClassInEachTestMethod(settingsToConfirm);
        final List<? extends Api.Method> methodsThatCanBeTested = Utils.safeGetMethodsThatCanBeTested(settingsToConfirm);
        final ConfirmOptionsDialog dialog = new ConfirmOptionsDialog(project, canCreateSourceClassInEachTestMethod, shouldCreateSourceInEachTestMethod, dependencies, possibleDependencies, methodsThatCanBeTested);
        final boolean isOk = dialog.showAndGet();
        if(!isOk) {
            throw new Api.UserCancelledGenerationException();
        }
        final boolean newShouldCreateSourceInEachTest = dialog.shouldCreateSourceClassInEachTest();
        final Map<String, Object> ret = new HashMap<>(settingsToConfirm);
        ret.put("shouldCreateSourceClassInEachTestMethod", newShouldCreateSourceInEachTest);
        return ret;
    }
}
