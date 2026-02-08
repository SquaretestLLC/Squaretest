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

import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtil;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.TextComponentAccessor;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.ComboboxWithBrowseButton;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.squaretest.ModuleTestSourcesRootFinder;
import com.squaretest.TemplateProvider;
import com.squaretest.settings.displaywrappers.DisplayTemplate;
import com.squaretest.settings.displaywrappers.DisplayTemplateLanguage;
import com.squaretest.settings.editor.EditorCreator;
import com.squaretest.settings.editor.PlatformInfoProvider;
import com.squaretest.settings.editor.VelocityEditorHighlighterProvider;
import com.squaretest.settings.notification.NewTemplateNotifier;
import com.squaretest.settings.store.ModuleSettings;
import com.squaretest.settings.store.SettingsProvider;
import com.squaretest.settings.store.Template;
import com.squaretest.settings.store.TemplateLanguage;
import com.squaretest.settings.viewdataproviders.TemplateSettingsView;
import com.squaretest.settings.viewdataproviders.TemplateSettingsViewProvider;
import com.squaretest.utils.PathUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class ModuleConfigComponent {
    private static final String DefaultTestRootsOption = "Not Specified";
    private JPanel rootPanel;
    private TemplateConfigComponent templateConfigComponent;
    private ComboboxWithBrowseButton selectTestSourcesRoot;
    private JLabel lblTestSourcesRoot;
    private SQHelpIcon lblTestSourcesHelp;

    @NotNull
    private final TemplateProvider templateProvider;
    @NotNull
    private final Project project;
    @NotNull
    private final Module module;
    @NotNull
    private final TemplateSettingsViewProvider templateSettingsViewProvider;
    @NotNull
    private final SettingsProvider settingsProvider;
    @NotNull
    private final String defaultTemplateLanguagePlaceholderString;
    @NotNull
    private final String defaultTemplatePlaceholderString;
    @NotNull
    private final String defaultTemplateSaveAsName;
    @Nullable
    private final String defaultTestSourcesRootToShowIfNoneInModuleSettings;
    @NotNull
    private final NewTemplateNotifier newTemplateNotifier;
    private Supplier<JPanel> editorPanelSupplier;

    public ModuleConfigComponent(
            @NotNull final TemplateProvider templateProvider, @NotNull final Project project, @NotNull final Module module,
            @NotNull final TemplateSettingsViewProvider templateSettingsViewProvider,
            @NotNull final SettingsProvider settingsProvider, @NotNull final NewTemplateNotifier newTemplateNotifier,
            @NotNull final Supplier<JPanel> editorPanelSupplier, @NotNull final String defaultTemplateLanguagePlaceholderString,
            @NotNull final String defaultTemplatePlaceholderString, @NotNull final String defaultTemplateSaveAsName,
            @Nullable final String defaultTestSourcesRootToShowIfNoneInModuleSettings) {
        this.templateProvider = templateProvider;
        this.project = project;
        this.module = module;
        this.templateSettingsViewProvider = templateSettingsViewProvider;
        this.settingsProvider = settingsProvider;
        this.newTemplateNotifier = newTemplateNotifier;
        this.editorPanelSupplier = editorPanelSupplier;
        this.defaultTemplateLanguagePlaceholderString = defaultTemplateLanguagePlaceholderString;
        this.defaultTemplatePlaceholderString = defaultTemplatePlaceholderString;
        this.defaultTemplateSaveAsName = defaultTemplateSaveAsName;
        this.defaultTestSourcesRootToShowIfNoneInModuleSettings = defaultTestSourcesRootToShowIfNoneInModuleSettings;
    }

    private void createUIComponents() {
        final TemplateSettingsView templateSettingsView = this.templateSettingsViewProvider.newModuleSettingsView(this.project, this.module);
        final PlatformInfoProvider platformInfoProvider = new PlatformInfoProvider();
        final EditorCreator editorCreator = new EditorCreator(project, new VelocityEditorHighlighterProvider(project,
                platformInfoProvider), platformInfoProvider);
        this.templateConfigComponent = new TemplateConfigComponent(
                templateProvider,
                templateSettingsView,
                defaultTemplateSaveAsName,
                this.project,
                editorCreator, editorPanelSupplier, this.newTemplateNotifier,
                this.defaultTemplateLanguagePlaceholderString,
                this.defaultTemplatePlaceholderString);

        // Select roots UI.
        final ModuleSettings moduleSettings = this.settingsProvider.getModuleSettings(project.getName(), module.getName());
        final String existingModueTestRootFromSettings = moduleSettings == null ? null : moduleSettings.getTestDirectoryRoot();
        final JComboBox<String> suggestedRootsComboBox = new JComboBox<>();
        if(existingModueTestRootFromSettings != null) {
            suggestedRootsComboBox.addItem(existingModueTestRootFromSettings);
            suggestedRootsComboBox.setSelectedItem(existingModueTestRootFromSettings);
        } else if(this.defaultTestSourcesRootToShowIfNoneInModuleSettings != null) {
            final String relativeTestSourcesRoot = PathUtils.getRelativePathFromProjectRootIfInProject(project, this.defaultTestSourcesRootToShowIfNoneInModuleSettings);
            suggestedRootsComboBox.addItem(relativeTestSourcesRoot);
            suggestedRootsComboBox.setSelectedItem(relativeTestSourcesRoot);
        } else {
            suggestedRootsComboBox.addItem(DefaultTestRootsOption);
            suggestedRootsComboBox.setSelectedItem(DefaultTestRootsOption);
        }
        final List<VirtualFile> suggestedTestRootsForModule = ModuleTestSourcesRootFinder.suggestTestRootsForModule(module).testRoots();
        final List<String> suggestedTestRootsRelPaths = PathUtils.computeRelativePaths(suggestedTestRootsForModule, project);
        Collections.sort(suggestedTestRootsRelPaths);
        for(final String suggestedTestRoot : suggestedTestRootsRelPaths) {
            if(!UiUtils.containsItem(suggestedRootsComboBox, suggestedTestRoot)) {
                // Don't add the previously stored test-root or default-root-to-show to the combo box again.
                suggestedRootsComboBox.addItem(suggestedTestRoot);
            }
        }
        this.selectTestSourcesRoot = new ComboboxWithBrowseButton(suggestedRootsComboBox);
        this.selectTestSourcesRoot.addBrowseFolderListener(
                null,
                null,
                this.project,
                new FileChooserDescriptor(
                        false,
                        true,
                        false,
                        false,
                        false,
                        false),
                new TextComponentAccessor<>() {
                    @Override
                    public String getText(final JComboBox component) {
                        final String selectedTestRoot = component.getSelectedItem().toString();
                        if(DefaultTestRootsOption.equals(selectedTestRoot)) {
                            return ModuleUtil.getModuleDirPath(module);
                        }
                        return getAbsolutePath(component.getSelectedItem().toString());
                    }

                    @Override
                    @SuppressWarnings("unchecked") // The JComboBox required by the override has no <String> generic.
                    public void setText(final JComboBox component, @NotNull final String path) {
                        final String relativizedPath = PathUtils.getRelativePathFromProjectRootIfInProject(project, path);
                        if(!UiUtils.containsItem(component, relativizedPath)) {
                            // This is a JComboBox<String>; we created the ComboboxWithBrowseButton with a
                            // JComboBox<String>.
                            component.addItem(relativizedPath);
                        }
                        component.setSelectedItem(relativizedPath);
                    }
                });
        lblTestSourcesHelp = new SQHelpIcon(ToolTipLoader.getInstance().getTestSourcesRootToolTip());
    }

    /**
     * Call this right after the constructor. This is required, because we need to initialize the UI components with the
     * values set in the form (via the form creator), then set specific values (titles,...etc) on them based on the
     * module. I do not want to use createUiComponents to create the component, because I do not want to create the
     * component myself; I want it created exactly as the form specifies. However, I cannot get a reference to
     * components created by the intellij-injected byte-code inside of createUiComponents; createUiComponents is
     * called after the last line of code in the constructor.
     */
    public void initUiComponentsRequiringAWTThread() {
        // Set the JPanel border.
        final String title = String.format("Module Settings - %s", module.getName());
        final TitledBorder border = new TitledBorder(title);
        border.setTitleJustification(TitledBorder.LEFT);
        border.setTitlePosition(TitledBorder.TOP);
        this.rootPanel.setBorder(border);
        this.templateConfigComponent.initUiComponentsRequiringAWTThread();
    }

    public void initUiComponentsRequiringJPane() {
        this.templateConfigComponent.initUiComponentsRequiringJPane();
    }

    public void removeBorder() {
        final LayoutManager layoutManager = this.rootPanel.getLayout();
        final GridLayoutManager gridLayout = (GridLayoutManager) layoutManager;
        final Insets existingMargin = gridLayout.getMargin();
        gridLayout.setMargin(new Insets(existingMargin.top, 0, existingMargin.bottom, 0));
        this.rootPanel.setBorder(null);
    }

    public JPanel getRootPanel() {
        return this.rootPanel;
    }

    public boolean isModified() {
        // If the template has been changed, return true.
        if(templateConfigComponent.isModified()) {
            return true;
        }

        // Check to see if the test-root-dir has been changed from either the stored-settings or the default-value
        // (if there are no stored settings).
        final ModuleSettings existingModuleSettings = this.settingsProvider.getModuleSettings(project.getName(), module.getName());
        final String existingTestRootDir = existingModuleSettings == null ? null : existingModuleSettings.getTestDirectoryRoot();
        final String selectedTestRootDir = (String) selectTestSourcesRoot.getComboBox().getSelectedItem();
        if(existingTestRootDir != null) {
            return !existingTestRootDir.equals(selectedTestRootDir);
        } else {
            return !DefaultTestRootsOption.equals(selectedTestRootDir);
        }
    }

    public void apply() {
        // save template selection info.
        templateConfigComponent.apply();

        // Save the test root dir info
        String selectedTestRootDir = (String) selectTestSourcesRoot.getChildComponent().getSelectedItem();
        if(DefaultTestRootsOption.equals(selectedTestRootDir)) {
            selectedTestRootDir = null;
        }
        ModuleSettings existingModuleSettings = this.settingsProvider.getModuleSettings(project.getName(), module.getName());
        ModuleSettings moduleSettingsToSave;
        if(existingModuleSettings != null) {
            moduleSettingsToSave = new ModuleSettings(
                    existingModuleSettings.getName(),
                    existingModuleSettings.getTemplateLanguage(),
                    existingModuleSettings.getTemplate(),
                    selectedTestRootDir);
        } else {
            // If moduleSettings is null, no template settings (language or template) were saved by templateConfigComponent.
            // Create new settings with the selectedTestRootDir.
            moduleSettingsToSave = new ModuleSettings(
                    module.getName(),
                    null,
                    null,
                    selectedTestRootDir);
        }

        this.settingsProvider.putModuleSettings(project.getName(), moduleSettingsToSave);
    }

    public void reset() {
        if(!isModified()) {
            return;
        }
        // Reset the template config.
        templateConfigComponent.reset();

        // Reset the selected test-root dir to either the stored value or the default value.
        final ModuleSettings existingModuleSettings = this.settingsProvider.getModuleSettings(project.getName(), module.getName());
        final String existingTestRootDir = existingModuleSettings == null ? null : existingModuleSettings.getTestDirectoryRoot();
        final JComboBox selectTestSourcesComboBox = selectTestSourcesRoot.getComboBox();
        if(existingTestRootDir != null) {
            selectTestSourcesComboBox.setSelectedItem(existingTestRootDir);
        } else {
            selectTestSourcesComboBox.setSelectedItem(DefaultTestRootsOption);
        }
    }

    private String getAbsolutePath(@NotNull final String absPathOrPathInProjectDir) {
        final Path path = Paths.get(absPathOrPathInProjectDir);
        if(path.isAbsolute()) {
            return absPathOrPathInProjectDir;
        }

        final String projectBasePath = project.getBasePath();
        if(projectBasePath == null) {
            return absPathOrPathInProjectDir;
        }

        return Paths.get(projectBasePath, absPathOrPathInProjectDir).toString();
    }

    public int getLongestLeftLabelWidth() {
        return Math.max(UiUtils.computeJLabelWidth(lblTestSourcesRoot),
                this.templateConfigComponent.getLongestLeftLabelSize());
    }

    public void setMinimumLeftLabelWidth(final int minWidth) {
        lblTestSourcesRoot.setMinimumSize(new Dimension(minWidth, lblTestSourcesRoot.getMinimumSize().height));
        this.templateConfigComponent.setMinimumLeftLabelWidth(minWidth);
    }

    public DisplayTemplateLanguage getSelectedTemplateLanguage() {
        return this.templateConfigComponent.getSelectedTemplateLanguage();
    }

    public DisplayTemplate getSelectedTemplate() {
        return this.templateConfigComponent.getSelectedTemplate();
    }

    @Nullable
    public String getSelectedModuleSourcesRoot() {
        final String selectedSourcesRoot = (String) selectTestSourcesRoot.getChildComponent().getSelectedItem();
        if(Objects.equals(DefaultTestRootsOption, selectedSourcesRoot)) {
            return null;
        }
        return selectedSourcesRoot;
    }

    public boolean isTemplateLanguageSelected() {
        return this.templateConfigComponent.isTemplateLanguageSelected();
    }

    public boolean isTemplateSelected() {
        return this.templateConfigComponent.isTemplateSelected();
    }

    @Nullable
    public String getSelectedTestSourcesRoot() {
        final Object selectedItem = selectTestSourcesRoot.getComboBox().getSelectedItem();
        if(selectedItem == null || Objects.equals(DefaultTestRootsOption, selectedItem)) {
            return null;
        }
        return (String) selectedItem;
    }

    public JComponent getTemplateLanguageSelectComponent() {
        return this.templateConfigComponent.getTemplateLanguageSelectComponent();
    }

    public JComponent getTemplateSelectComponent() {
        return this.templateConfigComponent.getTemplateSelectComponent();
    }

    public ComboboxWithBrowseButton getSelectSourcesRootComponent() {
        return selectTestSourcesRoot;
    }

    public void disposeUIResources() {
        this.templateConfigComponent.disposeUIResources();
    }

    public boolean isTemplateTextModified() {
        return this.templateConfigComponent.isTemplateTextModified();
    }

    public boolean canClickTemplateTextSave() {
        return this.templateConfigComponent.canPerformSave();
    }

    public void performTemplateTextSave() {
        this.templateConfigComponent.performSave();
    }

    public boolean performTemplateTextSaveAsWithPromptIfNeeded() {
        return this.templateConfigComponent.performSaveAsWithPromptIfNeeded();
    }

    public void refreshTemplates() {
        this.templateConfigComponent.refreshTemplates();
    }

    public void selectTemplateLanguage(final TemplateLanguage templateLanguage) {
        this.templateConfigComponent.selectTemplateLanguage(templateLanguage);
    }

    public void selectTemplate(final Template template) {
        this.templateConfigComponent.selectTemplate(template);
    }

    public void addTemplateSelectionChangedListener(final TemplateSelectionChangedListener listener) {
        this.templateConfigComponent.addTemplateSelectionChangedListener(listener);
    }
}
