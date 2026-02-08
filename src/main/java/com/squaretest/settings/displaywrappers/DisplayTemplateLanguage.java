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
package com.squaretest.settings.displaywrappers;

import com.squaretest.settings.store.TemplateLanguage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class DisplayTemplateLanguage {

    @Nullable
    private final TemplateLanguage templateLanguage;
    private final boolean isNullPlaceholderTemplate;
    @Nullable
    private final String placeholderString;

    private DisplayTemplateLanguage(@NotNull final TemplateLanguage templateLanguage) {
        this.templateLanguage = templateLanguage;
        this.isNullPlaceholderTemplate = false;
        this.placeholderString = null;
    }

    private DisplayTemplateLanguage(final boolean isNullPlaceholder, @NotNull final String placeholderString) {
        this.isNullPlaceholderTemplate = isNullPlaceholder;
        this.templateLanguage = null;
        this.placeholderString = placeholderString;
    }

    public static DisplayTemplateLanguage fromTemplateLanguage(@NotNull final TemplateLanguage projectTemplateLanguage) {
        return new DisplayTemplateLanguage(projectTemplateLanguage);
    }

    public static DisplayTemplateLanguage newPlaceholderTemplateLanguage(final String placeholderString) {
        return new DisplayTemplateLanguage(true, placeholderString);
    }

    @Nullable
    public TemplateLanguage getTemplateLanguage() {
        return templateLanguage;
    }

    public boolean isNullPlaceholderTemplate() {
        return isNullPlaceholderTemplate;
    }

    @Override
    public String toString() {
        if(isNullPlaceholderTemplate) {
            return this.placeholderString;
        } else {
            return this.templateLanguage.getDisplayName();
        }
    }

    @Override
    public boolean equals(final Object o) {
        if(this == o) {
            return true;
        }
        if(o == null || getClass() != o.getClass()) {
            return false;
        }
        final DisplayTemplateLanguage that = (DisplayTemplateLanguage) o;
        return isNullPlaceholderTemplate == that.isNullPlaceholderTemplate &&
                templateLanguage == that.templateLanguage &&
                Objects.equals(placeholderString, that.placeholderString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(templateLanguage, isNullPlaceholderTemplate, placeholderString);
    }
}
