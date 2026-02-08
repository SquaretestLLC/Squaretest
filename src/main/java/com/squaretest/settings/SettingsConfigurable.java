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
package com.squaretest.settings;

import com.intellij.ide.DataManager;
import com.intellij.ide.impl.ProjectUtil;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.ui.components.fields.IntegerField;
import com.intellij.uiDesigner.core.GridConstraints;
import com.squaretest.TemplateProvider;
import com.squaretest.settings.displaywrappers.DisplayOpenTestFilePreference;
import com.squaretest.settings.store.OpenTestFilePreference;
import com.squaretest.settings.store.SettingsProvider;
import com.squaretest.settings.templatemanager.TemplateManagerDialog;
import com.squaretest.settings.viewdataproviders.TemplateSettingsViewProvider;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.Objects;
import javax.swing.*;

import static com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTHWEST;

public class SettingsConfigurable implements SearchableConfigurable, Configurable.NoScroll {

    private static final Logger Log = Logger.getInstance(SettingsConfigurable.class);

    private JPanel rootPanel;
    private ProjectConfigUi projectConfigComponent;
    private JComboBox<DisplayOpenTestFilePreference> selectOpenTestFilePreference;
    private JLabel lblOpenTestFilePreference;
    private JScrollPane scrollPane;
    private JLabel lblOpenTestFileHelp;
    private JButton btnManageTemplates;
    private JPanel projectPanelPlaceholder;
    private JPanel settingsContainerPanel;
    private IntegerField txtDataConstructorLimitWhenBeanOptionAvailable;
    private JLabel lblDataConstructorLimitWhenBeanOptionAvailable;
    private IntegerField txtConstructorLimitWhenBeanOptionAvailable;
    private JLabel lblConstructorLimitWhenBeanOptionAvailable;
    private IntegerField txtConstructorLimitWhenOtherOptionsAvailable;
    private JLabel lblConstructorLimitWhenOtherOptionsAvailable;
    private IntegerField txtFinalConstructorLimit;
    private JLabel lblFinalConstructorLimit;
    private IntegerField txtMaxNumberOfBuilderMethodsToCall;
    private JLabel lblMaxNumberOfBuilderMethodsToCall;
    private JCheckBox cbEnableCompletionSuggestions;
    private boolean hasInitializedComponentsRequiringJPane = false;

    private Project project;

    @NotNull
    private ProjectInfoProvider projectInfoProvider;
    @NotNull
    private SettingsProvider settingsProvider;
    @NotNull
    private TemplateProvider templateProvider;
    @NotNull
    private TemplateSettingsViewProvider templateSettingsViewProvider;

    /**
     * This is the main entry point. This method is called by the bytecode injected by the IntelliJ IDEA UI designer.
     * Bootstrap the dependencies here.
     */
    private void createUIComponents() {
        settingsProvider = new SettingsProvider();
        templateProvider = new TemplateProvider();
        projectInfoProvider = new ProjectInfoProvider();
        templateSettingsViewProvider = new TemplateSettingsViewProvider(this.settingsProvider);
        lblOpenTestFileHelp = new SQHelpIcon(ToolTipLoader.getInstance().getOpenFilePreferenceToolTip());
        lblDataConstructorLimitWhenBeanOptionAvailable = new SQHelpIcon(ToolTipLoader.getInstance().getDataConstructorLimitToolTip());
        lblConstructorLimitWhenBeanOptionAvailable = new SQHelpIcon(ToolTipLoader.getInstance().getBeanConstructorLimitToolTip());
        lblConstructorLimitWhenOtherOptionsAvailable = new SQHelpIcon(ToolTipLoader.getInstance().getOtherOptionConstructorLimitToolTip());
        lblFinalConstructorLimit = new SQHelpIcon(ToolTipLoader.getInstance().getFinalConstructorLimitToolTip());
        lblMaxNumberOfBuilderMethodsToCall = new SQHelpIcon(ToolTipLoader.getInstance().getMaxNumberOfBuilderMethodsToCall());
        this.project = guessProjectOpenInCurrentWindow();
        if(project == null) {
            this.projectConfigComponent = new NullProjectConfigComponent();
        } else {
            this.projectConfigComponent = new ProjectConfigComponent(this.templateProvider, this.project, this.templateSettingsViewProvider, this.settingsProvider);
        }
    }

    private int getLongestLeftLabelWidth() {
        final int testFilePrefLabelWidth = UiUtils.computeJLabelWidth(lblOpenTestFilePreference);
        return Math.max(testFilePrefLabelWidth, projectConfigComponent.getLongestLeftLabelSize());
    }

    private void setMinimumLeftLabelWidth(final int minWidth) {
        projectConfigComponent.setMinimumLeftLabelWidth(minWidth);
        lblOpenTestFilePreference.setMinimumSize(new Dimension(minWidth, lblOpenTestFilePreference.getMinimumSize().height));
    }

    @NotNull
    @Override
    public String getId() {
        return "preference.SquaretestSettings";
    }

    @Nullable
    @Override
    public Runnable enableSearch(final String option) {
        return null;
    }

    @Nls
    @Override
    public String getDisplayName() {
        return "Squaretest";
    }

    @Nullable
    @Override
    public String getHelpTopic() {
        return null;
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        // Replace the placeholder project settings panel with the correct project settings config ui.
        this.settingsContainerPanel.remove(projectPanelPlaceholder);
        final GridConstraints gridConstraints = new GridConstraints();
        gridConstraints.setAnchor(ANCHOR_NORTHWEST);
        gridConstraints.setRow(1);
        gridConstraints.setColSpan(1);
        this.settingsContainerPanel.add(projectConfigComponent.getRootPanel(), gridConstraints, 1);
        // The default scroll velocity is 1, which is way too slow; scrolling past one template editor takes
        // several full mouse-wheel spins. After trial and error, using unit-increment=25 feels about right.
        this.scrollPane.getVerticalScrollBar().setUnitIncrement(25);
        this.projectConfigComponent.initUiComponentsRequiringAWTThread();
        final int longestLeftLabelSize = getLongestLeftLabelWidth();
        setMinimumLeftLabelWidth(longestLeftLabelSize);
        selectOpenTestFilePreference.addItem(new DisplayOpenTestFilePreference(OpenTestFilePreference.ALWAYS_IN_SAME_EDITOR));
        selectOpenTestFilePreference.addItem(new DisplayOpenTestFilePreference(OpenTestFilePreference.NEXT_EDITOR_IF_AVAILABLE));
        selectOpenTestFilePreference.addItem(new DisplayOpenTestFilePreference(OpenTestFilePreference.ALWAYS_IN_NEXT_EDITOR));
        final DisplayOpenTestFilePreference pref = new DisplayOpenTestFilePreference(settingsProvider.getOpenTestFilePreference());
        selectOpenTestFilePreference.setSelectedItem(pref);
        txtDataConstructorLimitWhenBeanOptionAvailable.setValue(settingsProvider.getConstructorMaxLength());
        txtConstructorLimitWhenBeanOptionAvailable.setValue(settingsProvider.getConstructorLimitWhenBeanOptionAvailable());
        txtConstructorLimitWhenOtherOptionsAvailable.setValue(settingsProvider.getConstructorLimitWhenOtherOptionAvailable());
        txtFinalConstructorLimit.setValue(settingsProvider.getConstructorLimitFinal());
        txtMaxNumberOfBuilderMethodsToCall.setValue(settingsProvider.getMaxNumberOfBuilderMethodsToCall());
        cbEnableCompletionSuggestions.setSelected(settingsProvider.getEnableCompletionSuggestionsForTestMethods());
        // We need all the left-side-labels in the child forms and their child forms to align.
        // The labels are not all in the same grid-layout (each form has its own JFrame and LayoutManager).
        // Determine the left-side JLabel with the longest width in the current form and sub-forms; then set the
        // minimum-width for all the left-side JLabels to the longest width.
        final int longestLeftLabelWidth = getLongestLeftLabelWidth();
        setMinimumLeftLabelWidth(longestLeftLabelWidth);

        // Wire up the template manager.
        btnManageTemplates.addActionListener(e -> {
            if(isModified()) {
                Messages.showErrorDialog(rootPanel, "Please save or discard the pending changes to settings first.", "Error");
                return;
            }
            // Show the dialog.
            new TemplateManagerDialog(project).showAndGet();

            // Reset the settings to handle updates made by TemplateManagerDialog.
            refreshTemplatesInAllComponents();
        });

        return this.rootPanel;
    }

    @Override
    public boolean isModified() {
        initUiComponentsRequiringJPaneIfNeeded();
        // Check to see if the open-test-file setting was changed.
        final DisplayOpenTestFilePreference pref = new DisplayOpenTestFilePreference(settingsProvider.getOpenTestFilePreference());
        if(!Objects.equals(pref, selectOpenTestFilePreference.getSelectedItem())) {
            return true;
        }
        if(!Objects.equals(txtDataConstructorLimitWhenBeanOptionAvailable.getValue(), settingsProvider.getConstructorMaxLength())) {
            return true;
        }
        if(!Objects.equals(txtConstructorLimitWhenBeanOptionAvailable.getValue(), settingsProvider.getConstructorLimitWhenBeanOptionAvailable())) {
            return true;
        }
        if(!Objects.equals(txtConstructorLimitWhenOtherOptionsAvailable.getValue(), settingsProvider.getConstructorLimitWhenOtherOptionAvailable())) {
            return true;
        }
        if(!Objects.equals(txtFinalConstructorLimit.getValue(), settingsProvider.getConstructorLimitFinal())) {
            return true;
        }
        if(!Objects.equals(txtMaxNumberOfBuilderMethodsToCall.getValue(), settingsProvider.getMaxNumberOfBuilderMethodsToCall())) {
            return true;
        }
        if(cbEnableCompletionSuggestions.isSelected() != settingsProvider.getEnableCompletionSuggestionsForTestMethods()) {
            return true;
        }
        // Check to see if the project settings were changed.
        return this.projectConfigComponent.isModified();
    }

    /**
     * Tells the UI components to perform initialization code that requires this component to be attached
     * to a JRootPane.
     */
    private void initUiComponentsRequiringJPaneIfNeeded() {
        if(!hasInitializedComponentsRequiringJPane) {
            this.projectConfigComponent.initUiComponentsRequiringJPane();
            hasInitializedComponentsRequiringJPane = true;
        }
    }

    @Override
    public void apply() throws ConfigurationException {
        txtDataConstructorLimitWhenBeanOptionAvailable.validateContent();
        txtConstructorLimitWhenBeanOptionAvailable.validateContent();
        txtConstructorLimitWhenOtherOptionsAvailable.validateContent();
        txtFinalConstructorLimit.validateContent();
        txtMaxNumberOfBuilderMethodsToCall.validateContent();
        final DisplayOpenTestFilePreference selectedPref = (DisplayOpenTestFilePreference) selectOpenTestFilePreference.getSelectedItem();
        settingsProvider.putOpenTestFilePreference(selectedPref.openTestFilePreference());
        settingsProvider.setConstructorMaxLength(txtDataConstructorLimitWhenBeanOptionAvailable.getValue());
        settingsProvider.setConstructorLimitWhenBeanOptionAvailable(txtConstructorLimitWhenBeanOptionAvailable.getValue());
        settingsProvider.setConstructorLimitWhenOtherOptionAvailable(txtConstructorLimitWhenOtherOptionsAvailable.getValue());
        settingsProvider.setConstructorLimitFinal(txtFinalConstructorLimit.getValue());
        settingsProvider.setMaxNumberOfBuilderMethodsToCall(txtMaxNumberOfBuilderMethodsToCall.getValue());
        settingsProvider.setEnableCompletionSuggestionsForTestMethods(cbEnableCompletionSuggestions.isSelected());
        this.projectConfigComponent.apply();
    }

    @Override
    public void reset() {
        this.projectConfigComponent.reset();
        final DisplayOpenTestFilePreference storedPref = new DisplayOpenTestFilePreference(settingsProvider.getOpenTestFilePreference());
        this.selectOpenTestFilePreference.setSelectedItem(storedPref);
        txtDataConstructorLimitWhenBeanOptionAvailable.setValue(settingsProvider.getConstructorMaxLength());
        txtConstructorLimitWhenBeanOptionAvailable.setValue(settingsProvider.getConstructorLimitWhenBeanOptionAvailable());
        txtConstructorLimitWhenOtherOptionsAvailable.setValue(settingsProvider.getConstructorLimitWhenOtherOptionAvailable());
        txtFinalConstructorLimit.setValue(settingsProvider.getConstructorLimitFinal());
        txtMaxNumberOfBuilderMethodsToCall.setValue(settingsProvider.getMaxNumberOfBuilderMethodsToCall());
        cbEnableCompletionSuggestions.setSelected(settingsProvider.getEnableCompletionSuggestionsForTestMethods());
    }

    @Override
    public void disposeUIResources() {
        projectConfigComponent.disposeUIResources();
    }

    private void refreshTemplatesInAllComponents() {
        // Tell all components to refresh their template lists. This is needed for 2 flows.
        // 1. User updates templates in TemplateManager. Templates need to be removed/added to the combolists.
        // 2. User creates a new template in one module settings; this template needs to be added to the other
        //    dropdown lists.
        projectConfigComponent.refreshTemplates();
    }

    @Nullable
    private Project guessProjectOpenInCurrentWindow() {
        final Project[] projects = projectInfoProvider.getOpenProjects();
        if(projects.length == 0) {
            return null;
        }

        // We have at least one open project. Now try to determine which project is open in this IntelliJ window.
        // TODO: Consider using this com.intellij.openapi.project.ProjectUtil.guessCurrentProject(component).
        // com.intellij.openapi.project.ProjectUtil.guessCurrentProject(window);
        final Window window = ProjectUtil.getActiveFrameOrWelcomeScreen();
        final DataContext context = DataManager.getInstance().getDataContext(window);
        if(context != null) {
            final Project activeProject = CommonDataKeys.PROJECT.getData(context);
            if(activeProject != null) {
                return activeProject;
            }
        }

        if(projects.length > 1) {
            Log.warn("More than one open project detected; unable to which project is open in this window; selecting the first one.");
        }
        return projects[0];
    }
}
