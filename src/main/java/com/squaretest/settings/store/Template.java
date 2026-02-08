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
package com.squaretest.settings.store;

import com.intellij.util.xmlb.annotations.Property;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Template implements Comparable<Template> {
    @Property
    private String presentationName;
    @Property
    private String internalTemplateSaveName;
    @Property
    private boolean isInternal;

    /**
     * This exists to support the IntelliJ persistence logic.
     */
    Template() {
    }

    public Template(final String internalTemplateSaveName, final String presentationName, final boolean isInternal) {
        this.internalTemplateSaveName = internalTemplateSaveName;
        this.presentationName = presentationName;
        this.isInternal = isInternal;
    }

    public String getPresentationName() {
        return presentationName;
    }

    public boolean isInternal() {
        return isInternal;
    }

    public String getInternalTemplateSaveName() {
        return internalTemplateSaveName;
    }

    public TemplateLanguage getTemplateLanguage() {
        if(internalTemplateSaveName.endsWith(".groovy")) {
            return TemplateLanguage.GROOVY;
        } else {
            return TemplateLanguage.JAVA;
        }
    }

    @Override
    public int compareTo(@NotNull final Template other) {
        return this.internalTemplateSaveName.compareTo(other.internalTemplateSaveName);
    }

    @Override
    public boolean equals(final Object o) {
        if(this == o) {
            return true;
        }
        if(o == null || getClass() != o.getClass()) {
            return false;
        }
        final Template template = (Template) o;
        return isInternal == template.isInternal &&
                Objects.equals(presentationName, template.presentationName) &&
                Objects.equals(internalTemplateSaveName, template.internalTemplateSaveName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(presentationName, internalTemplateSaveName, isInternal);
    }
}
