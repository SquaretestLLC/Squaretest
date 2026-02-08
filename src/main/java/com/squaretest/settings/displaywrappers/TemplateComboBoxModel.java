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

import com.squaretest.TemplateProvider;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import javax.swing.*;

/**
 * Overrides {@link #addElement(DisplayTemplate)} to ensure
 * 1. Placeholder items are first.
 * 2. user-templates are next, and in alphabetical order
 * 3. internal-templates are last and in alphabetical order.
 */
public class TemplateComboBoxModel extends DefaultComboBoxModel<DisplayTemplate> {

    public TemplateComboBoxModel(@NotNull final List<DisplayTemplate> templates) {
        super();
        for(final DisplayTemplate template : templates) {
            addElement(template);
        }
    }

    @Override
    public void addElement(final DisplayTemplate displayTemplate) {

        // Do nothing if the element is already in the model.
        if(getIndexOf(displayTemplate) != -1) {
            return;
        }

        // The placeholder template goes first.
        if(displayTemplate.isNullPlaceholderTemplate()) {
            insertElementAt(displayTemplate, 0);
            return;
        }

        // Handle user-created template.
        if(displayTemplate.isInternal()) {
            insertInternalTemplate(displayTemplate);
        } else {
            insertUserTemplate(displayTemplate);
        }
    }

    private void insertUserTemplate(final DisplayTemplate displayTemplateToInsert) {
        final String templateToInsertName = displayTemplateToInsert.getTemplate().getPresentationName();
        final int indexOfLastUserTemplate = findIndexOfLastUserTemplate();
        if(indexOfLastUserTemplate == -1) {
            // This is the first user template. add it after any placeholders.
            int indexToInsertAt = 0;
            for(int i = 0; i < getSize(); i++) {
                final DisplayTemplate currentTemplate = getElementAt(i);
                if(currentTemplate.isNullPlaceholderTemplate()) {
                    indexToInsertAt = i + 1;
                    break;
                }
            }
            insertElementAt(displayTemplateToInsert, indexToInsertAt);
        } else {
            // The last possible position we could insert at is the index of the first internal template.
            int indexToInsertTemplateAt = indexOfLastUserTemplate + 1;
            for(int i = 0; i <= indexOfLastUserTemplate; i++) {
                final DisplayTemplate currentTemplate = getElementAt(i);
                if(currentTemplate.isNullPlaceholderTemplate()) {
                    continue;
                }
                final String currentTemplateName = currentTemplate.getTemplate().getPresentationName();
                if(templateToInsertName.compareToIgnoreCase(currentTemplateName) < 0) {
                    indexToInsertTemplateAt = i;
                    break;
                }
            }
            insertElementAt(displayTemplateToInsert, indexToInsertTemplateAt);
        }
    }

    private void insertInternalTemplate(final DisplayTemplate displayTemplateToInsert) {
        final String templateToInsertName = displayTemplateToInsert.getTemplate().getInternalTemplateSaveName() + ".ft";
        final int templateToInsertOrder = TemplateProvider.InternalTemplates.indexOf(templateToInsertName);
        final int indexOfLastUserTemplate = findIndexOfLastUserTemplate();
        int indexToStartSearchingForPosition = 0;
        if(indexOfLastUserTemplate != -1) {
            indexToStartSearchingForPosition = indexOfLastUserTemplate + 1;
        }
        int indexToInsertTemplateAt = getSize();
        for(int i = indexToStartSearchingForPosition; i < getSize(); i++) {
            final DisplayTemplate currentTemplate = getElementAt(i);
            if(currentTemplate.isNullPlaceholderTemplate()) {
                continue;
            }
            final String currentTemplateName = currentTemplate.getTemplate().getInternalTemplateSaveName() + ".ft";
            final int currentTemplateOrder = TemplateProvider.InternalTemplates.indexOf(currentTemplateName);
            if(templateToInsertOrder < currentTemplateOrder) {
                indexToInsertTemplateAt = i;
                break;
            }
        }
        insertElementAt(displayTemplateToInsert, indexToInsertTemplateAt);
    }

    private int findIndexOfLastUserTemplate() {
        int lastIndexOfUserTemplate = -1;
        for(int i = 0; i < getSize(); i++) {
            final DisplayTemplate template = getElementAt(i);
            if(template.isUser()) {
                lastIndexOfUserTemplate = i;
            } else if(template.isInternal()) {
                break;
            }
        }
        return lastIndexOfUserTemplate;
    }
}
