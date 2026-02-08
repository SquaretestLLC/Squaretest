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

import com.intellij.ide.fileTemplates.FileTemplate;
import com.intellij.ide.fileTemplates.impl.BundledFileTemplate;
import org.jetbrains.annotations.NotNull;

public class TemplateValidator {

    private static final String AskUserToConfirmSearchString = "UiUtils.askUserToConfirmSettings";

    public boolean templateContainsCodeToConfirmMocks(@NotNull final FileTemplate template) {
        // Internal templates have the API call.
        if(template instanceof BundledFileTemplate) {
            return true;
        }
        final String templateText = template.getText();
        return templateText.contains(AskUserToConfirmSearchString);
    }
}
