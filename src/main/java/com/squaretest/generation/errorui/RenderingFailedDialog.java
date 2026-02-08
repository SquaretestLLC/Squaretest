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
package com.squaretest.generation.errorui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.IdeBorderFactory;
import com.intellij.util.ui.UIUtil;
import com.squaretest.TemplateProvider;
import com.squaretest.generation.filetemplateutil.TemplateRenderingException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * The dialog shown when we failed to render the test class. This is caused by things like: the user's template
 * called a method that triggered a NullPointerException or ArrayIndexOutOfBounds exception.
 * This is loosely based on com.intellij.diagnostic.errordialog.DetailsTabForm.
 */
public class RenderingFailedDialog extends DialogWrapper {

    @NotNull
    private final TemplateRenderingException templateRenderingException;

    private JTextArea textStackTrace;
    private JPanel rootPanel;
    private JLabel lblTemplateName;
    private JPanel stackTraceHolderPanel;
    private JTextField txtTemplateName;

    public RenderingFailedDialog(
            @NotNull final Project project,
            @NotNull final TemplateRenderingException templateRenderingException) {
        super(project);
        this.templateRenderingException = templateRenderingException;
        // createUiComponents() is called here by the IntelliJ-injected bytecode.
        init();
        setTitle("Test Generation Error");
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        initUi();
        return rootPanel;
    }

    @NotNull
    @Override
    protected Action[] createActions() {
        // Override this to remove the Cancel button.
        return new Action[]{getOKAction()};
    }

    private void initUi() {
        // Copied from DetailsTabForm
        stackTraceHolderPanel.setBorder(IdeBorderFactory.createBorder());
        final String templateDisplayName = TemplateProvider.computePresentationNameFromTemplate(templateRenderingException.getFileTemplate());
        txtTemplateName.setText(templateDisplayName);
        final Throwable cause = templateRenderingException.getCause();
        final String stackTrace = ExceptionUtils.getStackTrace(cause);
        textStackTrace.setText(stackTrace);
        textStackTrace.setBackground(UIUtil.getTextFieldBackground());
        // Needed to make the containing JScrollPane scroll to the top.
        textStackTrace.setCaretPosition(0);
    }
}
