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

import com.intellij.openapi.project.Project;
import com.squaretest.template.api.Api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DialogBasedSettingsConfirmerV1 {

    private final Project project;
    private final Api.CodeStyleUtils codeStyleUtils;

    public DialogBasedSettingsConfirmerV1(final Project project, final Api.CodeStyleUtils codeStyleUtils) {
        this.project = project;
        this.codeStyleUtils = codeStyleUtils;
    }

    public Map<String, Object> confirmSettings(final Map<String, Object> settingsToConfirm) throws Api.UserCancelledGenerationException {
        final List<? extends Api.Variable> dependencies = Utils.safeGetDependencies(settingsToConfirm);
        final boolean shouldSetPackageLocalField = Utils.safeGetShouldSetPackageLocalFields(settingsToConfirm);
        final ConfirmMocksDialog dialog = new ConfirmMocksDialog(project, dependencies, new VariableUpdater(shouldSetPackageLocalField, codeStyleUtils));
        final boolean isOk = dialog.showAndGet();
        if(!isOk) {
            throw new Api.UserCancelledGenerationException();
        }
        final Map<String, Object> ret = new HashMap<>(settingsToConfirm);
        final List<Api.Variable> members = dependencies.stream().filter(Api.Variable::getShouldStoreInReference).collect(Collectors.toList());
        final List<Api.Variable> mockMembers = members.stream().filter(Api.Variable::getShouldBeMocked).collect(Collectors.toList());
        ret.put("memberFields", members);
        ret.put("mockMemberFields", mockMembers);
        ret.put("nonMockMemberFields", members.stream().filter(x -> !x.getShouldBeMocked()).collect(Collectors.toList()));
        ret.put("mocksNeeded", !mockMembers.isEmpty());
        return ret;
    }
}
