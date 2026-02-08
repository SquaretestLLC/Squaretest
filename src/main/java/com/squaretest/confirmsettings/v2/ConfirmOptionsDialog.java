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
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.components.JBScrollPane;
import com.squaretest.template.api.Api;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import javax.swing.*;

public class ConfirmOptionsDialog extends DialogWrapper {

    private final boolean canCreateSourceClassInEachMember;
    private final boolean shouldCreateSourceClassInEachMember;
    private final List<? extends Api.Variable> dependencies;
    private final List<? extends Api.Variable> possibleDependencies;
    private final List<? extends Api.Method> allMethodsThatCanBeTested;
    private JPanel rootPanel;
    private JCheckBox cbShouldCreateSourceInEachTest;
    private JTable tblSelectDependenciesToMock;
    private JTable tblSelectOtherItemsToMock;
    private JCheckBox cbShowSuperMethodsCheckBox;
    private JTable tblSelectMethodsToTest;
    private JScrollPane spSelectDependenciesToMock;
    private JScrollPane spSelectOtherItemsToMock;
    private JScrollPane spSelectMethodsToTest;
    private JLabel lblSelectOtherItemsToMock;
    private JLabel lblSelectDependenciesToMock;

    protected ConfirmOptionsDialog(
            @Nullable final Project project,
            final boolean canCreateSourceClassInEachMember,
            final boolean shouldCreateSourceClassInEachMember,
            final List<? extends Api.Variable> dependencies,
            final List<? extends Api.Variable> possibleDependencies,
            final List<? extends Api.Method> allMethodsThatCanBeTested) {
        super(project);
        this.canCreateSourceClassInEachMember = canCreateSourceClassInEachMember;
        this.shouldCreateSourceClassInEachMember = shouldCreateSourceClassInEachMember;
        this.dependencies = dependencies;
        this.possibleDependencies = possibleDependencies;
        this.allMethodsThatCanBeTested = allMethodsThatCanBeTested;
        // createUiComponents() is called here by the IntelliJ-injected bytecode.
        init();
        setTitle("Confirm Options");
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        // If possibleDependencies is empty remove its UI components.
        final boolean possibleDepsEmpty = possibleDependencies.stream().noneMatch(SelectMocksTable::canMock);
        if(possibleDepsEmpty) {
            lblSelectOtherItemsToMock.getParent().remove(lblSelectOtherItemsToMock);
            spSelectOtherItemsToMock.getParent().remove(spSelectOtherItemsToMock);
        }
        if(dependencies.isEmpty() && !possibleDepsEmpty) {
            lblSelectDependenciesToMock.getParent().remove(lblSelectDependenciesToMock);
            spSelectDependenciesToMock.getParent().remove(spSelectDependenciesToMock);
        }
        return rootPanel;
    }

    @NotNull
    @Override
    protected Action[] createActions() {
        // Override this to remove the Cancel button.
        return new Action[]{getOKAction()};
    }

    private void createUIComponents() {
        spSelectDependenciesToMock = new JBScrollPane();
        tblSelectDependenciesToMock = new SelectMocksTable(dependencies, "Dependency");
        spSelectOtherItemsToMock = new JBScrollPane();
        tblSelectOtherItemsToMock = new SelectMocksTable(possibleDependencies, "Possible Dependency");
        cbShouldCreateSourceInEachTest = new JCheckBox("Construct Source Class In Each Test Method");
        cbShouldCreateSourceInEachTest.setEnabled(canCreateSourceClassInEachMember);
        cbShouldCreateSourceInEachTest.setSelected(shouldCreateSourceClassInEachMember);
        cbShouldCreateSourceInEachTest.setHorizontalTextPosition(SwingConstants.LEADING);
        cbShowSuperMethodsCheckBox = new JCheckBox("Show Super Methods");
        cbShowSuperMethodsCheckBox.setHorizontalTextPosition(SwingConstants.LEADING);
        spSelectMethodsToTest = new JBScrollPane();
        tblSelectMethodsToTest = new SelectMethodsTable(allMethodsThatCanBeTested, cbShowSuperMethodsCheckBox);
    }

    public boolean shouldCreateSourceClassInEachTest() {
        return cbShouldCreateSourceInEachTest.isSelected();
    }
}
