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
package com.squaretest.settings.viewdataproviders;

import com.squaretest.settings.store.Template;
import com.squaretest.settings.store.TemplateLanguage;
import org.jetbrains.annotations.Nullable;

/**
 * This does not provide UI Components. The view in the name refers to a view into the settings data model.
 * Provides a view into the stored settings to retrieve and update the template language and
 * template for one project or module.
 */
public interface TemplateSettingsView {

    @Nullable
    TemplateLanguage getTemplateLanguage();

    @Nullable
    Template getTemplate();

    void saveTemplateSettings(@Nullable final TemplateLanguage templateLanguage,
                              @Nullable final Template template);
}
