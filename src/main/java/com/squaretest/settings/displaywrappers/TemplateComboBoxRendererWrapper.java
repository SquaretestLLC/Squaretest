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

import com.squaretest.settings.store.Template;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import javax.swing.*;

public class TemplateComboBoxRendererWrapper implements ListCellRenderer<DisplayTemplate> {

    @NotNull
    private final ListCellRenderer<? super DisplayTemplate> delegate;
    private final Separator userInternalTemplateSeparator = new Separator();
    private final Separator junit4And5Separator = new Separator();
    private final Separator jUnit5AndOtherSeparator = new Separator();

    public TemplateComboBoxRendererWrapper(@NotNull final ListCellRenderer<? super DisplayTemplate> delegate) {
        this.delegate = delegate;
    }

    @Override
    public Component getListCellRendererComponent(final JList<? extends DisplayTemplate> list, final DisplayTemplate value, final int index, final boolean isSelected, final boolean cellHasFocus) {
        // Solution based on comment: https://stackoverflow.com/questions/138793/how-do-i-add-a-separator-to-a-jcombobox-in-java#comment9348129_138922.
        final Component comp = delegate.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        final Separator separator = getSeparatorForPosition(list, value, index);
        if(separator != null) {
            separator.setComponent(comp);
            return separator.getContainerPanel();
        } else {
            return comp;
        }
    }

    @Nullable
    private Separator getSeparatorForPosition(final JList<? extends DisplayTemplate> list, final DisplayTemplate currentTemplate, final int index) {
        if(index == -1 || currentTemplate.isNullPlaceholderTemplate() || currentTemplate.getTemplate() == null) {
            return null;
        }
        final String currentTemplateName = currentTemplate.getTemplate().getPresentationName();
        final ListModel<? extends DisplayTemplate> model = list.getModel();

        if(model.getSize() > index + 1) {
            final DisplayTemplate nextDisplayTemplate = model.getElementAt(index + 1);
            if(currentTemplate.isUser() && !nextDisplayTemplate.isUser()) {
                return userInternalTemplateSeparator;
            }
            final Template nextTemplate = nextDisplayTemplate.getTemplate();
            if(nextTemplate == null) {
                return null;
            }
            if(currentTemplateName.startsWith("JUnit4") && nextTemplate.getPresentationName().startsWith("JUnit5")) {
                return junit4And5Separator;
            }

            if(currentTemplateName.startsWith("JUnit5") && !nextTemplate.getPresentationName().startsWith("JUnit5")) {
                return jUnit5AndOtherSeparator;
            }
        }
        return null;
    }
}
