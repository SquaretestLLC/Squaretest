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

import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.editor.EditorSettings;
import com.intellij.openapi.editor.event.DocumentEvent;
import com.intellij.openapi.editor.event.DocumentListener;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.ui.DocumentAdapter;
import com.intellij.ui.HyperlinkLabel;
import com.squaretest.Icons;
import com.squaretest.TemplateNameInvalidException;
import com.squaretest.TemplateProvider;
import com.squaretest.settings.displaywrappers.DisplayTemplate;
import com.squaretest.settings.displaywrappers.DisplayTemplateLanguage;
import com.squaretest.settings.displaywrappers.TemplateComboBoxModel;
import com.squaretest.settings.displaywrappers.TemplateComboBoxRendererWrapper;
import com.squaretest.settings.editor.EditorCreator;
import com.squaretest.settings.notification.NewTemplateListener;
import com.squaretest.settings.notification.NewTemplateNotifier;
import com.squaretest.settings.store.Template;
import com.squaretest.settings.store.TemplateLanguage;
import com.squaretest.settings.viewdataproviders.TemplateSettingsView;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import javax.swing.*;

public class TemplateConfigComponent implements NewTemplateListener {

    private static final String DefaultSelectTemplatePlaceholderString = "Not Specified";
    private static final String ComboboxPreviousValueKey = "previous_template";
    public static final int SelectTemplateMaxRows = 13;

    private JComboBox<DisplayTemplateLanguage> selectTemplateLanguage;
    private JComboBox<DisplayTemplate> selectTemplate;
    private JButton btnSave;
    private JButton btnSaveAs;
    private JTextField txtSaveAsName;
    private JPanel rootPanel;
    private JPanel editorPanel;
    private JLabel lblTestLanguage;
    private JLabel lblTemplate;
    private JPanel saveAsButtonPanel;
    private SQHelpIcon lblTemplateTooltip;
    private JPanel saveButtonsContainerPanel;
    private HyperlinkLabel lblTemplateDocsHyperlink;

    // Non-Ui Fields
    private Editor projectEditor;
    private boolean templateTextModified = false;
    // The initial default button set when the view is created; this will usually be the OK button.
    // We need to set the default button back to this when the save-button(s) are disabled.
    private JButton initialDefaultButton;

    // Dependencies.
    @NotNull
    private final TemplateProvider templateProvider;
    @NotNull
    private final TemplateSettingsView templateSettingsView;
    @NotNull
    private final String defaultNewTemplateSaveName;
    @NotNull
    private final String defaultTemplatePlaceholderString;
    @NotNull
    private final String defaultTemplateLanguagePlaceholderString;
    @NotNull
    private final MyDocumentListener documentListener;
    @NotNull
    private final Project project;
    @NotNull
    private final EditorCreator editorCreator;
    @NotNull
    private final Supplier<JPanel> editorPanelSupplier;

    // Listeners
    private List<TemplateSelectionChangedListener> templateSelectionChangedListeners;
    private DisplayTemplateLanguage displayTemplateLanguagePlaceholder;
    private DisplayTemplate displayTemplatePlaceholder;
    @NotNull
    private final NewTemplateNotifier newTemplateNotifier;

    TemplateConfigComponent(
            @NotNull final TemplateProvider templateProvider, @NotNull final TemplateSettingsView templateSettingsView,
            @NotNull final String defaultNewTemplateSaveName, @NotNull final Project project,
            @NotNull final EditorCreator editorCreator, @NotNull final Supplier<JPanel> editorPanelSupplier, @NotNull final NewTemplateNotifier newTemplateNotifier,
            @NotNull final String defaultTemplateLanguagePlaceholderString,
            @NotNull final String defaultTemplatePlaceholderString) {
        this.templateProvider = templateProvider;
        this.templateSettingsView = templateSettingsView;
        this.defaultNewTemplateSaveName = defaultNewTemplateSaveName;
        this.editorCreator = editorCreator;
        this.editorPanelSupplier = editorPanelSupplier;
        this.defaultTemplateLanguagePlaceholderString = defaultTemplateLanguagePlaceholderString;
        this.defaultTemplatePlaceholderString = defaultTemplatePlaceholderString;
        this.project = project;
        this.newTemplateNotifier = newTemplateNotifier;
        documentListener = new MyDocumentListener();
        templateSelectionChangedListeners = new ArrayList<>();
        this.newTemplateNotifier.addListener(this);
    }

    TemplateConfigComponent(
            final TemplateProvider templateProvider, final TemplateSettingsView templateSettingsView,
            final String defaultTemplateSaveName, final Project project, final EditorCreator editorCreator,
            final Supplier<JPanel> editorPanelSupplier, final NewTemplateNotifier newTemplateNotifier) {
        this(templateProvider, templateSettingsView, defaultTemplateSaveName,
                project, editorCreator, editorPanelSupplier, newTemplateNotifier,
                DefaultSelectTemplatePlaceholderString, DefaultSelectTemplatePlaceholderString);
    }

    public boolean isModified() {

        // Get the existing language and template from the stored settings.
        final TemplateLanguage existingTemplateLanguage = templateSettingsView.getTemplateLanguage();
        final Template existingTemplate = templateSettingsView.getTemplate();

        final DisplayTemplateLanguage selectedLanguage = (DisplayTemplateLanguage) selectTemplateLanguage.getSelectedItem();
        if(existingTemplateLanguage == null) {
            // If there are no stored project settings, return whether the "Select Language" dropdown is set.
            // You must select a language before you can do anything else, at least until we implement module-specific
            // settings.
            return !selectedLanguage.isNullPlaceholderTemplate();
        }

        // The stored project settings are non-null. Check to see if the template language in the UI is different.
        if(!Objects.equals(existingTemplateLanguage, selectedLanguage.getTemplateLanguage())) {
            return true;
        }

        // Check the template selection.
        final DisplayTemplate selectedTemplate = (DisplayTemplate) selectTemplate.getSelectedItem();
        if(!Objects.equals(existingTemplate, selectedTemplate.getTemplate())) {
            return true;
        }

        // Return true of the template text has been modified; if it has been, clicking "Apply" will save the template;
        // Or, if the selected template is internal, this will save the template at the suggested name.
        // This will show a warning if that would overwrite an existing template.
        if(templateTextModified) {
            return true;
        }

        // No need to check the save-as text; if the template text was not modified, there is nothing to "Apply"
        // unless we enable the "save-as" button when the template text has not been modified.
        return false;
    }

    void reset() {
        if(!isModified()) {
            return;
        }
        final TemplateLanguage existingTemplateLanguage = templateSettingsView.getTemplateLanguage();
        final Template existingTemplate = templateSettingsView.getTemplate();
        // Set the template language back
        if(existingTemplateLanguage == null) {
            selectTemplateLanguage.setSelectedItem(displayTemplateLanguagePlaceholder);
        } else {
            selectTemplateLanguage.setSelectedItem(DisplayTemplateLanguage.fromTemplateLanguage(existingTemplateLanguage));
        }
        // Set the template back.
        if(existingTemplate == null) {
            selectTemplate.setSelectedItem(displayTemplatePlaceholder);
        } else {
            selectTemplate.setSelectedItem(DisplayTemplate.fromTemplate(existingTemplate));
        }

        // Set the text back (in case the customer only changed the template text, then clicked reset).
        if(existingTemplate != null && templateTextModified) {
            resetTemplateEditorText();
        }

        // Set the text in txtSaveAsName back (in case the customer modified it, then clicked reset).
        if(existingTemplate != null) {
            if(existingTemplate.isInternal()) {
                txtSaveAsName.setText(defaultNewTemplateSaveName + existingTemplateLanguage.getFileExtensionWithDot() + ".ft");
            } else {
                txtSaveAsName.setText(existingTemplate.getPresentationName());
            }
        }
    }

    private void resetTemplateEditorText() {
        // Get the selected language.
        final DisplayTemplate displayTemplate = (DisplayTemplate) selectTemplate.getSelectedItem();
        if(displayTemplate.isNullPlaceholderTemplate()) {
            return;
        }
        // Set the text back to the content in the stored template.
        final String templateText = templateProvider.getTemplateText(displayTemplate.getTemplate());
        documentListener.setCurrentlyStoredTemplateText(templateText);

        WriteCommandAction.runWriteCommandAction(project, () -> projectEditor.getDocument().setText(templateText));
    }

    void addTemplateSelectionChangedListener(
            @Nullable final TemplateSelectionChangedListener templateSelectionChangedListener) {
        if(templateSelectionChangedListener == null) {
            return;
        }
        for(final TemplateSelectionChangedListener existingListener : templateSelectionChangedListeners) {
            if(templateSelectionChangedListener == existingListener) {
                return;
            }
        }
        this.templateSelectionChangedListeners.add(templateSelectionChangedListener);
    }

    boolean isTemplateLanguageSelected() {
        final DisplayTemplateLanguage selectedLang = (DisplayTemplateLanguage) this.selectTemplateLanguage.getSelectedItem();
        return !selectedLang.isNullPlaceholderTemplate();
    }

    boolean isTemplateSelected() {
        final DisplayTemplate selectedTemplate = (DisplayTemplate) this.selectTemplate.getSelectedItem();
        return !selectedTemplate.isNullPlaceholderTemplate();
    }

    void initUiComponentsRequiringAWTThread() {

        // Set the  renderer to our TemplateComboBoxRendererWrapper to render the separator correctly.
        selectTemplate.setModel(createModel());
        selectTemplate.setMaximumRowCount(SelectTemplateMaxRows);
        selectTemplate.setRenderer(new TemplateComboBoxRendererWrapper(selectTemplate.getRenderer()));

        // Hide editor panel and disable buttons by default.
        hideEditorAndButtons();
        btnSave.setEnabled(false);
        btnSaveAs.setEnabled(false);

        // Setup default choices for JComboBoxes and wire up selection-changed action listeners.
        this.displayTemplatePlaceholder = DisplayTemplate.newPlaceholder(defaultTemplatePlaceholderString);
        this.displayTemplateLanguagePlaceholder = DisplayTemplateLanguage.newPlaceholderTemplateLanguage(this.defaultTemplateLanguagePlaceholderString);
        this.selectTemplateLanguage.addItem(displayTemplateLanguagePlaceholder);
        this.selectTemplateLanguage.addItem(DisplayTemplateLanguage.fromTemplateLanguage(TemplateLanguage.JAVA));
        this.selectTemplateLanguage.addItem(DisplayTemplateLanguage.fromTemplateLanguage(TemplateLanguage.GROOVY));
        this.selectTemplateLanguage.addActionListener(e -> {
            if(!hasChangedFromPreviousValue(selectTemplateLanguage)) {
                return;
            }
            // Update the select-templates dropdown list when the user changes the language to only show the
            // templates available for that language.
            final DisplayTemplateLanguage displayTemplateLanguage = (DisplayTemplateLanguage) selectTemplateLanguage.getSelectedItem();
            selectTemplateLanguage.hidePopup();

            if(displayTemplateLanguage.isNullPlaceholderTemplate()) {
                // The user selected "Not Specified" for the template language.
                this.selectTemplate.setModel(createModel());
                this.selectTemplate.setEnabled(false);
            } else {
                final TemplateLanguage selectedTemplateLanguage = displayTemplateLanguage.getTemplateLanguage();
                final List<Template> templatesForSelectedTemplateLanguage = templateProvider.getTemplates(selectedTemplateLanguage);
                final TemplateComboBoxModel model = createModel(templatesForSelectedTemplateLanguage);
                this.selectTemplate.setModel(model);
                this.selectTemplate.setEnabled(true);

                // If the selected language matches the stored settings for this project, set the template dropdown
                // list to the template stored in the project settings.
                if(selectedTemplateLanguage == templateSettingsView.getTemplateLanguage()) {
                    final Template templateFromProjectSettings = templateSettingsView.getTemplate();
                    if(templatesForSelectedTemplateLanguage.contains(templateFromProjectSettings)) {
                        this.selectTemplate.setSelectedItem(DisplayTemplate.fromTemplate(templateFromProjectSettings));
                    }
                }
            }
        });

        // Replacing the model does not fire an ActionEvent like choosing as selecting a new item does.
        // We need to listen for property-change events to detect that.
        this.selectTemplate.addActionListener(e -> onSelectedTemplateChanged());
        this.selectTemplate.addPropertyChangeListener(evt -> {
            if("model".equals(evt.getPropertyName())) {
                onSelectedTemplateChanged();
            }
        });

        this.btnSaveAs.addActionListener(e -> performSaveAsWithPromptIfNeeded());

        this.btnSave.addActionListener(e -> internalPerformSave());
        updateButtonMinSize(btnSave);
        updateButtonMinSize(btnSaveAs);

        // Read stored project-specific settings and update the UI to match.
        final TemplateLanguage templateLanguage = templateSettingsView.getTemplateLanguage();
        if(templateLanguage == null) {
            this.selectTemplateLanguage.setSelectedItem(DisplayTemplateLanguage.newPlaceholderTemplateLanguage(this.defaultTemplateLanguagePlaceholderString));
            return;
        }

        // Set the display template language in the UI.
        final DisplayTemplateLanguage projectTemplateLanguage = DisplayTemplateLanguage.fromTemplateLanguage(templateLanguage);
        this.selectTemplateLanguage.setSelectedItem(projectTemplateLanguage);

        txtSaveAsName.getDocument().addDocumentListener(new DocumentAdapter() {
            @Override
            protected void textChanged(@NotNull final javax.swing.event.DocumentEvent e) {
                setDefaultButtonToSaveOrSaveAs();
            }
        });
    }

    private void updateButtonMinSize(final JButton button) {
        // Make the button min width 10% larger. This is needed to prevent the text from becoming abbreviated (showing
        // "...") when we call rootPane.setDefaultButton(button). When a button becomes the default button, the text
        // becomes bold. That increases the width the text needs to "fit" in the button. That causes Swing to abbreviate
        // the text.
        final Dimension minSize = button.getMinimumSize();
        final Dimension newMinSize = new Dimension((int) (minSize.getWidth() * 1.1 + .5), (int) (minSize.getHeight() + .5));
        button.setMinimumSize(newMinSize);
    }

    void initUiComponentsRequiringJPane() {
        // Store a pointer to the default-button so that we can restore its status as the default-button when our
        // save-buttons are hidden or disabled (unset as the default-button).
        if(initialDefaultButton != null) {
            return;
        }
        final JRootPane rootPane = SwingUtilities.getRootPane(btnSave);
        if(rootPane != null) {
            initialDefaultButton = rootPane.getDefaultButton();
        }
    }

    boolean canPerformSave() {
        return shouldEnableSaveButton();
    }

    void performSave() {
        if(!isTemplateTextModified()) {
            throw new IllegalStateException("The template text is not modified");
        }
        if(!canPerformSave()) {
            throw new IllegalStateException("Unable to perform save, because doing so would overwrite an internal template");
        }
        internalPerformSave();
    }

    boolean isTemplateTextModified() {
        return templateTextModified;
    }

    /**
     * Performs the same as though the user clicked the "Save As" button. This will show a dialog prompting the user
     * to overwrite an existing template if needed.
     *
     * @return true if the template was saved; false otherwise.
     */
    boolean performSaveAsWithPromptIfNeeded() {
        final String saveName = txtSaveAsName.getText().trim();
        final DisplayTemplateLanguage selectedLanguage = (DisplayTemplateLanguage) selectTemplateLanguage.getSelectedItem();
        if(selectedLanguage.isNullPlaceholderTemplate()) {
            // No template language is selected. The template isn't even showing!
            return false;
        }

        // Validate the save-as-name is a valid template name.
        if(!validateNewTemplateNameAndShowErrorIfNeeded(saveName, selectedLanguage.getTemplateLanguage())) {
            return false;
        }

        // Check to see if saving the template would overwrite an existing template with the same name;
        // Show a dialog to confirm if it will.
        final TemplateLanguage templateLanguage = selectedLanguage.getTemplateLanguage();
        final boolean willOverwriteExistingTemplate = templateProvider.userTemplateExists(templateLanguage, saveName);
        if(willOverwriteExistingTemplate) {
            // A template exists; we need to prompt the user to overwrite the existing file.
            final String message = String.format("The template: %s already exists. \n"
                            + "Please click Overwrite to save changes and overwrite %s or click Cancel to do nothing.",
                    saveName, saveName);
            final String title = "Template Already Exists";
            final int okOrCancelResultCode = Messages.showOkCancelDialog(
                    rootPanel,
                    message,
                    title,
                    "Overwrite",
                    "Cancel",
                    Icons.Warning);
            if(okOrCancelResultCode != Messages.OK) {
                return false;
            }
        }

        final String templateText = projectEditor.getDocument().getText();
        final Template savedTemplate = templateProvider.saveNewTemplate(
                ((DisplayTemplateLanguage) selectTemplateLanguage.getSelectedItem()).getTemplateLanguage(),
                saveName,
                templateText);
        final DisplayTemplate newDisplayTemplate = DisplayTemplate.fromTemplate(savedTemplate);
        if(!UiUtils.containsItem(selectTemplate, newDisplayTemplate)) {
            selectTemplate.addItem(newDisplayTemplate);
        }
        selectTemplate.setSelectedItem(newDisplayTemplate);
        templateTextModified = false;
        documentListener.setCurrentlyStoredTemplateText(templateText);
        disableAllSaveControls();

        // If we created a new-template, notify any new-template-created listeners.
        if(!willOverwriteExistingTemplate) {
            newTemplateNotifier.notifyNewTemplateAdded(templateLanguage, savedTemplate);
        }

        return true;
    }

    /**
     * @return true if the name is valid; false otherwise.
     */
    private boolean validateNewTemplateNameAndShowErrorIfNeeded(final String templateName, final TemplateLanguage templateLanguage) {
        try {
            templateProvider.validateUserTemplateName(templateLanguage, templateName);
            return true;
        } catch(TemplateNameInvalidException e) {
            Messages.showErrorDialog(e.getUserErrorMessage(), TemplateMessages.UnableToRenameTemplateMessageTitle);
            return false;
        }
    }

    private void internalPerformSave() {
        final String templateText = projectEditor.getDocument().getText();
        final DisplayTemplate selectedDisplayTemplate = (DisplayTemplate) selectTemplate.getSelectedItem();
        final Template selectedTemplate = selectedDisplayTemplate.getTemplate();
        templateProvider.saveTemplate(
                selectedTemplate,
                templateText);
        templateTextModified = false;
        documentListener.setCurrentlyStoredTemplateText(templateText);
        disableAllSaveControls();
    }

    private void onSelectedTemplateChanged() {
        if(!hasChangedFromPreviousValue(this.selectTemplate)) {
            return;
        }

        final DisplayTemplate displayTemplate = (DisplayTemplate) this.selectTemplate.getSelectedItem();
        // If the selected template is not the placeholder template.
        if(displayTemplate != null && !displayTemplate.isNullPlaceholderTemplate()) {

            // Get the selected language.
            final DisplayTemplateLanguage displayTemplateLanguage = (DisplayTemplateLanguage) selectTemplateLanguage.getSelectedItem();

            // Construct the editor if needed.
            final String templateText = templateProvider.getTemplateText(displayTemplate.getTemplate());
            if(projectEditor == null) {
                projectEditor = editorCreator.createEditor(displayTemplateLanguage.getTemplateLanguage(), templateText);
                final EditorSettings editorSettings = projectEditor.getSettings();
                editorSettings.setLineMarkerAreaShown(false);
                final JComponent component = projectEditor.getComponent();
                editorPanel.add(component, BoxLayout.X_AXIS);
                projectEditor.getDocument().addDocumentListener(documentListener);
            }

            // This sets the editor's text to the selected-templates stored-text and updates the listener
            // accordingly.
            resetTemplateEditorText();

            // Show the editor and buttons panel.
            showEditorAndButtonsPanel();

            // Set the save-as text.
            final Template selectedTemplate = displayTemplate.getTemplate();
            if(selectedTemplate.isInternal()) {
                final TemplateLanguage templateLanguage = ((DisplayTemplateLanguage) selectTemplateLanguage.getSelectedItem()).getTemplateLanguage();
                txtSaveAsName.setText(defaultNewTemplateSaveName + templateLanguage.getFileExtensionWithDot() + ".ft");
            } else {
                txtSaveAsName.setText(selectedTemplate.getPresentationName());
            }

            // Disable save controls; they will be re-enabled when the user changes the template.
            disableAllSaveControls();
            notifyTemplateChangeListener(true);
        } else {
            templateTextModified = false;
            btnSaveAs.setEnabled(false);
            txtSaveAsName.setText("");
            hideEditorAndButtons();
            releaseEditorAndHidePanel();
            notifyTemplateChangeListener(false);
        }
    }

    /**
     * Updates a client-property on the {@link JComboBox} to store the currently-selected value.
     *
     * @param combobox the combobox
     * @return true if the value in the {@link JComboBox} is different from the previously-selected value.
     */
    public static boolean hasChangedFromPreviousValue(final JComboBox<?> combobox) {
        final Object previouslySelected = combobox.getClientProperty(ComboboxPreviousValueKey);
        if(Objects.equals(previouslySelected, combobox.getSelectedItem())) {
            return false;
        }
        // Store the selected template so that we can detect when it has changed the next time this fires.
        combobox.putClientProperty(ComboboxPreviousValueKey, combobox.getSelectedItem());
        return true;
    }

    private void disableAllSaveControls() {
        restoreDefaultJButton();
        btnSave.setEnabled(false);
        btnSaveAs.setEnabled(false);
        txtSaveAsName.setEnabled(false);
    }

    private void notifyTemplateChangeListener(final boolean templateSelected) {
        for(final TemplateSelectionChangedListener listener : templateSelectionChangedListeners) {
            listener.onTemplateSelectionChanged(templateSelected);
        }
    }

    private void showEditorAndButtonsPanel() {
        setEditorAndButtonVisibility(true);
    }

    private void hideEditorAndButtons() {
        setEditorAndButtonVisibility(false);
    }

    private void setEditorAndButtonVisibility(final boolean visible) {
        this.editorPanel.setVisible(visible);
        this.btnSave.setVisible(visible);
        this.btnSaveAs.setVisible(visible);
        this.txtSaveAsName.setVisible(visible);
        this.saveAsButtonPanel.setVisible(visible);
        this.saveButtonsContainerPanel.setVisible(visible);
        this.lblTemplateDocsHyperlink.setVisible(visible);
    }

    private void releaseEditorAndHidePanel() {
        if(projectEditor != null) {
            releaseEditorIfNeeded();
            editorPanel.removeAll();
        }
    }

    private void releaseEditorIfNeeded() {
        if(projectEditor != null) {
            EditorFactory.getInstance().releaseEditor(projectEditor);
            projectEditor = null;
        }
    }

    public void apply() {

        // Handles the user clicking "Apply" while the template text is dirty.
        // This happens when the user changes the template text and clicks "Apply" rather than "Save".
        // This may show a dialog asking the user to confirm overwriting an existing file.
        // Also, clicking "Save" and updating the drop-down will refresh other UI components; do this before
        // checking the DisplayTemplateLanguage and DisplayTemplate as they may have changed after this.
        if(templateTextModified) {
            if(canPerformSave()) {
                performSave();
            } else {
                performSaveAsWithPromptIfNeeded();
            }
        }

        final DisplayTemplateLanguage selectedLanguage = (DisplayTemplateLanguage) selectTemplateLanguage.getSelectedItem();
        final DisplayTemplate selectedTemplate = (DisplayTemplate) selectTemplate.getSelectedItem();
        templateSettingsView.saveTemplateSettings(
                selectedLanguage.getTemplateLanguage(), selectedTemplate.getTemplate());
    }

    private void createUIComponents() {
        this.editorPanel = editorPanelSupplier.get();
        // Determine min-width for text area using reference template name string for sizing.
        txtSaveAsName = new JTextField(defaultNewTemplateSaveName, 28);

        lblTemplateTooltip = new SQHelpIcon(ToolTipLoader.getInstance().getSelectTemplateToolTip());
        lblTemplateDocsHyperlink = new HyperlinkLabel();
        final String linkText = "Template API Documentation";
        lblTemplateDocsHyperlink.setHyperlinkText(linkText);
        lblTemplateDocsHyperlink.setHyperlinkTarget("https://squaretest.com#template_api");
    }

    int getLongestLeftLabelSize() {
        return Math.max(
                UiUtils.computeJLabelWidth(lblTemplate),
                UiUtils.computeJLabelWidth(lblTestLanguage));
    }

    void setMinimumLeftLabelWidth(final int minWidth) {
        lblTestLanguage.setMinimumSize(new Dimension(minWidth, lblTestLanguage.getMinimumSize().height));
        lblTemplate.setMinimumSize(new Dimension(minWidth, lblTemplate.getMinimumSize().height));
    }

    JComponent getTemplateLanguageSelectComponent() {
        return selectTemplateLanguage;
    }

    JComponent getTemplateSelectComponent() {
        return selectTemplate;
    }

    DisplayTemplateLanguage getSelectedTemplateLanguage() {
        return (DisplayTemplateLanguage) selectTemplateLanguage.getSelectedItem();
    }

    DisplayTemplate getSelectedTemplate() {
        return (DisplayTemplate) selectTemplate.getSelectedItem();
    }

    private TemplateComboBoxModel createModel(final List<Template> templates) {
        final List<DisplayTemplate> displayTemplates = new ArrayList<>(templates.size() + 1);
        final DisplayTemplate placeholderTemplate = DisplayTemplate.newPlaceholder(this.defaultTemplatePlaceholderString);
        displayTemplates.add(placeholderTemplate);
        for(final Template template : templates) {
            displayTemplates.add(DisplayTemplate.fromTemplate(template));
        }
        final TemplateComboBoxModel model = new TemplateComboBoxModel(displayTemplates);
        model.setSelectedItem(placeholderTemplate);
        return model;
    }

    private TemplateComboBoxModel createModel() {
        final DisplayTemplate placeholder = DisplayTemplate.newPlaceholder(this.defaultTemplatePlaceholderString);
        final TemplateComboBoxModel model = new TemplateComboBoxModel(Collections.singletonList(placeholder));
        model.setSelectedItem(placeholder);
        return model;
    }

    void disposeUIResources() {
        releaseEditorIfNeeded();
    }

    void refreshTemplates() {
        final DisplayTemplateLanguage displayTemplateLanguage = (DisplayTemplateLanguage) selectTemplateLanguage.getSelectedItem();
        // No template language is selected. The model will be created when one is selected.
        if(displayTemplateLanguage.isNullPlaceholderTemplate()) {
            return;
        }

        final TemplateLanguage selectedTemplateLanguage = displayTemplateLanguage.getTemplateLanguage();
        final List<Template> templatesForSelectedTemplateLanguage = templateProvider.getTemplates(selectedTemplateLanguage);
        final TemplateComboBoxModel model = createModel(templatesForSelectedTemplateLanguage);
        final Template templateFromSettings = templateSettingsView.getTemplate();
        if(templatesForSelectedTemplateLanguage.contains(templateFromSettings)) {
            model.setSelectedItem(DisplayTemplate.fromTemplate(templateFromSettings));
        }
        this.selectTemplate.setModel(model);
    }

    @Override
    public void onNewTemplateCreated(
            final TemplateLanguage templateLanguage, final Template template) {
        final DisplayTemplateLanguage selectedLanguage = (DisplayTemplateLanguage) selectTemplateLanguage.getSelectedItem();
        if(selectedLanguage == null || selectedLanguage.isNullPlaceholderTemplate() || !(Objects.equals(selectedLanguage.getTemplateLanguage(), templateLanguage))) {
            // If no template language is selected or the selected language is different from the newly-created template, do nothing.
            // The new template will appear in the list of templates if the user changes the template language.
            return;
        }
        ((TemplateComboBoxModel) selectTemplate.getModel()).addElement(DisplayTemplate.fromTemplate(template));
    }

    public void selectTemplateLanguage(final TemplateLanguage templateLanguage) {
        for(int i = 0; i < selectTemplateLanguage.getItemCount(); i++) {
            final DisplayTemplateLanguage language = selectTemplateLanguage.getItemAt(i);
            final TemplateLanguage languageFromCombobox = language.getTemplateLanguage();
            if(languageFromCombobox != null && languageFromCombobox == templateLanguage) {
                selectTemplateLanguage.setSelectedIndex(i);
                return;
            }
        }
    }

    public void selectTemplate(final Template template) {
        if(!selectTemplate.isEnabled()) {
            return;
        }
        for(int i = 0; i < selectTemplate.getItemCount(); i++) {
            final DisplayTemplate displayTemplate = selectTemplate.getItemAt(i);
            final Template templateFromCombobox = displayTemplate.getTemplate();
            if(templateFromCombobox != null && Objects.equals(templateFromCombobox, template)) {
                selectTemplate.setSelectedIndex(i);
                return;
            }
        }
    }

    private class MyDocumentListener implements DocumentListener {

        private String currentlyStoredTemplateText = "";

        @Override
        public void beforeDocumentChange(final DocumentEvent event) {
        }

        void setCurrentlyStoredTemplateText(final String originalTemplateText) {
            this.currentlyStoredTemplateText = originalTemplateText;
        }

        @Override
        public void documentChanged(final DocumentEvent event) {
            final String currentTemplateText = event.getDocument().getText();
            templateTextModified = !currentlyStoredTemplateText.equals(currentTemplateText);
            if(templateTextModified) {
                btnSaveAs.setEnabled(true);
                txtSaveAsName.setEnabled(true);
                setDefaultButtonToSaveOrSaveAs();
                final boolean shouldEnableSave = shouldEnableSaveButton();
                btnSave.setEnabled(shouldEnableSave);
                setDefaultButtonToSaveOrSaveAs();
            } else {
                // Clear the default-button if we're about to disable it.
                disableAllSaveControls();
            }
        }
    }

    private void setDefaultButtonToSaveOrSaveAs() {
        if(!templateTextModified) {
            return;
        }

        // If the save-button should not be enabled, make the save-as button the default.
        if(!shouldEnableSaveButton()) {
            setDefaultButton(btnSaveAs);
            return;
        }
        // Otherwise, we have a user-template.
        final String saveAsText = txtSaveAsName.getText().trim();
        final DisplayTemplate selectedTemplate = (DisplayTemplate) selectTemplate.getSelectedItem();
        if(selectedTemplate != null && !Objects.equals(selectedTemplate.getTemplate().getPresentationName(), saveAsText)) {
            setDefaultButton(btnSaveAs);
        } else {
            setDefaultButton(btnSave);
        }
    }

    private static void setDefaultButton(final JButton button) {
        if(button.isDefaultButton()) {
            return;
        }
        final JRootPane rootPane = SwingUtilities.getRootPane(button);
        if(rootPane != null) {
            rootPane.setDefaultButton(button);
        }
    }

    private void restoreDefaultJButton() {
        final JRootPane rootPane = SwingUtilities.getRootPane(btnSave);
        if(rootPane != null) {
            final JButton defaultButton = rootPane.getDefaultButton();
            if(defaultButton == btnSaveAs || defaultButton == btnSave) {
                rootPane.setDefaultButton(initialDefaultButton);
            }
        }
    }

    /**
     * Determines if the save-button should be enabled.
     * The save button should be enabled if the template text has been modified and the template is not an internal-
     * template.
     *
     * @return whether or not the save-button should be enabled.
     */
    private boolean shouldEnableSaveButton() {
        final DisplayTemplate displayTemplate = (DisplayTemplate) selectTemplate.getSelectedItem();
        return templateTextModified && !displayTemplate.isInternal();
    }

}
