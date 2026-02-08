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
package com.squaretest;

import com.intellij.ide.fileTemplates.FileTemplate;
import com.intellij.ide.fileTemplates.FileTemplateManager;
import com.intellij.openapi.util.text.StringUtil;
import com.squaretest.settings.store.Template;
import com.squaretest.settings.store.TemplateLanguage;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TemplateProvider {

    public static final String SQTemplatePrefixJava = "SQTJ-";
    public static final String SQTemplatePrefixGroovy = "SQTG-";
    // Note: we include this hardcoded list, because it is difficult to query all files in the resource directory at runtime.
    // The resource directory is baked into the jar file. We would need to unzip the jar file and then scan the
    // folders to find the velocity templates. There is no easy way to do this. The various solutions on StackOverflow all end
    // with a NullPointerException in BufferedReader.java.
    public static final List<String> InternalTemplates = Arrays.asList(
            "SQTJ-JUnit4Mockito.java.ft",
            "SQTG-JUnit4Mockito.groovy.ft",
            "SQTJ-JUnit4MockitoAssertJ.java.ft",
            "SQTG-JUnit4MockitoAssertJ.groovy.ft",
            "SQTJ-JUnit4MockitoSpring.java.ft",
            "SQTG-JUnit4MockitoSpring.groovy.ft",
            "SQTJ-JUnit4MockitoSpringAssertJ.java.ft",
            "SQTG-JUnit4MockitoSpringAssertJ.groovy.ft",
            "SQTJ-JUnit5Mockito.java.ft",
            "SQTG-JUnit5Mockito.groovy.ft",
            "SQTJ-JUnit5MockitoAssertJ.java.ft",
            "SQTG-JUnit5MockitoAssertJ.groovy.ft",
            "SQTJ-JUnit5MockitoSpring.java.ft",
            "SQTG-JUnit5MockitoSpring.groovy.ft",
            "SQTJ-JUnit5MockitoSpringAssertJ.java.ft",
            "SQTG-JUnit5MockitoSpringAssertJ.groovy.ft",
            "SQTJ-AndroidJUnit4Mockito.java.ft",
            "SQTG-AndroidJUnit4Mockito.groovy.ft",
            "SQTJ-Robolectric3Mockito.java.ft",
            "SQTG-Robolectric3Mockito.groovy.ft",
            "SQTJ-TestNGMockito.java.ft",
            "SQTG-TestNGMockito.groovy.ft");

    public List<Template> getTemplates(@NotNull final TemplateLanguage language) {
        final List<Template> userTemplates = getUserTemplates(language);
        final List<Template> internalTemplates = getInternalTemplatesForLanguage(language);
        Collections.sort(internalTemplates);

        // The templates are sorted by user-templates first, then alphabetically.
        userTemplates.addAll(internalTemplates);
        return userTemplates;
    }

    public List<Template> getUserTemplates(@NotNull final TemplateLanguage language) {
        final FileTemplateManager manager = FileTemplateManager.getDefaultInstance();
        FileTemplate[] defaultTemplates = manager
                .getTemplates(FileTemplateManager.DEFAULT_TEMPLATES_CATEGORY);
        return getTemplatesFromFileTemplateArray(language, defaultTemplates);
    }

    /**
     * Throws an exception if the name is not valid for a user template.
     * This does not throw if a template with the same name exists.
     *
     * @param templateLanguage the template language
     * @param newTemplateName  the new template name
     */
    public void validateUserTemplateName(@NotNull final TemplateLanguage templateLanguage, @NotNull final String newTemplateName)
            throws TemplateNameInvalidException {
        // Verify the name contains at least one non-whitespace character.
        final String newTemplateNameWithoutSpaces = newTemplateName.trim();
        if(StringUtils.isWhitespace(newTemplateNameWithoutSpaces)) {
            throw new TemplateNameInvalidException("The name contains only whitespace.");
        }

        // Verify the name is different from the default templates.
        // SQTJ-aa.java
        final String internalTemplateSaveName = computeInternalTemplateNameFromPresentationName(newTemplateNameWithoutSpaces, templateLanguage);
        // SQTJ-aa.java.ft
        final String internalTemplateSaveNameWithVelocityExtension = internalTemplateSaveName + ".ft";
        if(InternalTemplates.contains(internalTemplateSaveNameWithVelocityExtension)) {
            throw new TemplateNameInvalidException("A template with this name already exists.");
        }
    }

    public static Template getJUnit4JavaTemplate() {
        final String templateName = "SQTJ-JUnit4Mockito.java";
        final String templatePresentationName = computePresentationName(templateName);
        return new Template(templateName, templatePresentationName, true);
    }

    public static Template getJUnit4AssertJJavaTemplate() {
        final String templateName = "SQTJ-JUnit4MockitoAssertJ.java";
        final String templatePresentationName = computePresentationName(templateName);
        return new Template(templateName, templatePresentationName, true);
    }

    public static Template getJUnit4SpringJavaTemplate() {
        final String templateName = "SQTJ-JUnit4MockitoSpring.java";
        final String templatePresentationName = computePresentationName(templateName);
        return new Template(templateName, templatePresentationName, true);
    }

    public static Template getJUnit4SpringAssertJJavaTemplate() {
        final String templateName = "SQTJ-JUnit4MockitoSpringAssertJ.java";
        final String templatePresentationName = computePresentationName(templateName);
        return new Template(templateName, templatePresentationName, true);
    }

    public static Template getJUnit4GroovyTemplate() {
        final String templateName = "SQTG-JUnit4Mockito.groovy";
        final String templatePresentationName = computePresentationName(templateName);
        return new Template(templateName, templatePresentationName, true);
    }

    public static Template getJUnit4AssertJGroovyTemplate() {
        final String templateName = "SQTG-JUnit4MockitoAssertJ.groovy";
        final String templatePresentationName = computePresentationName(templateName);
        return new Template(templateName, templatePresentationName, true);
    }

    public static Template getJUnit4SpringGroovyTemplate() {
        final String templateName = "SQTG-JUnit4MockitoSpring.groovy";
        final String templatePresentationName = computePresentationName(templateName);
        return new Template(templateName, templatePresentationName, true);
    }

    public static Template getJUnit4SpringAssertJGroovyTemplate() {
        final String templateName = "SQTG-JUnit4MockitoSpringAssertJ.groovy";
        final String templatePresentationName = computePresentationName(templateName);
        return new Template(templateName, templatePresentationName, true);
    }

    public static Template getJUnit5JavaTemplate() {
        final String templateName = "SQTJ-JUnit5Mockito.java";
        final String templatePresentationName = computePresentationName(templateName);
        return new Template(templateName, templatePresentationName, true);
    }

    public static Template getJUnit5SpringJavaTemplate() {
        final String templateName = "SQTJ-JUnit5MockitoSpring.java";
        final String templatePresentationName = computePresentationName(templateName);
        return new Template(templateName, templatePresentationName, true);
    }

    public static Template getJUnit5AssertJJavaTemplate() {
        final String templateName = "SQTJ-JUnit5MockitoAssertJ.java";
        final String templatePresentationName = computePresentationName(templateName);
        return new Template(templateName, templatePresentationName, true);
    }

    public static Template getJUnit5SpringAssertJJavaTemplate() {
        final String templateName = "SQTJ-JUnit5MockitoSpringAssertJ.java";
        final String templatePresentationName = computePresentationName(templateName);
        return new Template(templateName, templatePresentationName, true);
    }

    public static Template getJUnit5GroovyTemplate() {
        final String templateName = "SQTG-JUnit5Mockito.groovy";
        final String templatePresentationName = computePresentationName(templateName);
        return new Template(templateName, templatePresentationName, true);
    }

    public static Template getJUnit5AssertJGroovyTemplate() {
        final String templateName = "SQTG-JUnit5MockitoAssertJ.groovy";
        final String templatePresentationName = computePresentationName(templateName);
        return new Template(templateName, templatePresentationName, true);
    }

    public static Template getJUnit5SpringGroovyTemplate() {
        final String templateName = "SQTG-JUnit5MockitoSpring.groovy";
        final String templatePresentationName = computePresentationName(templateName);
        return new Template(templateName, templatePresentationName, true);
    }

    public static Template getJUnit5SpringAssertJGroovyTemplate() {
        final String templateName = "SQTG-JUnit5MockitoSpringAssertJ.groovy";
        final String templatePresentationName = computePresentationName(templateName);
        return new Template(templateName, templatePresentationName, true);
    }

    public static Template getTestNGJavaTemplate() {
        final String templateName = "SQTJ-TestNGMockito.java";
        final String templatePresentationName = computePresentationName(templateName);
        return new Template(templateName, templatePresentationName, true);
    }

    public static Template getTestNGGroovyTemplate() {
        final String templateName = "SQTG-TestNGMockito.groovy";
        final String templatePresentationName = computePresentationName(templateName);
        return new Template(templateName, templatePresentationName, true);
    }

    public static Template getRobolectricJavaTemplate() {
        final String templateName = "SQTJ-Robolectric3Mockito.java";
        final String templatePresentationName = computePresentationName(templateName);
        return new Template(templateName, templatePresentationName, true);
    }

    public static Template getRobolectricGroovyTemplate() {
        final String templateName = "SQTG-Robolectric3Mockito.groovy";
        final String templatePresentationName = computePresentationName(templateName);
        return new Template(templateName, templatePresentationName, true);
    }

    public static Template getAndroidJUnit4JavaTemplate() {
        final String templateName = "SQTJ-AndroidJUnit4Mockito.java";
        final String templatePresentationName = computePresentationName(templateName);
        return new Template(templateName, templatePresentationName, true);
    }

    public static Template getAndroidJUnit4GroovyTemplate() {
        final String templateName = "SQTG-AndroidJUnit4Mockito.groovy";
        final String templatePresentationName = computePresentationName(templateName);
        return new Template(templateName, templatePresentationName, true);
    }

    /**
     * Gets the internal templates (internal meaning shipped with Squaretest) for the given language.
     * Note that we cannot call {@link FileTemplateManager#getInternalTemplates()}, then scan them to find our template,
     * because {@link FileTemplateManager#getInternalTemplates()} only returns the IntelliJ internal templates, not
     * our own internal template; this is weird, because {@link FileTemplateManager#getInternalTemplate(String)} does
     * return our template when called with the correct template name.
     *
     * @param language the language for which to return internal templates.
     * @return the {@link List List<Template>}
     */
    private List<Template> getInternalTemplatesForLanguage(@NotNull final TemplateLanguage language) {
        final List<Template> fileTemplates = new ArrayList<>();
        final String fileExtensionToSearchFor = String.format("%s.ft", language.getFileExtension());
        for(final String templateFileName : InternalTemplates) {
            if(templateFileName.endsWith(fileExtensionToSearchFor)) {
                final String templateName = templateFileName.substring(0, templateFileName.lastIndexOf(".ft"));
                final String templatePresentationName = computePresentationName(templateName);
                fileTemplates.add(new Template(templateName, templatePresentationName, true));
            }
        }
        return fileTemplates;
    }

    private List<Template> getTemplatesFromFileTemplateArray(
            final TemplateLanguage language, final FileTemplate[] templates) {
        final List<Template> sqTemplates = new ArrayList<>();
        for(final FileTemplate template : templates) {
            if(language.getFileExtension().equals(template.getExtension())) {
                final Template sqTemplate = createTemplateFromFileTemplate(template);
                if(sqTemplate != null) {
                    sqTemplates.add(sqTemplate);
                }
            }
        }

        // Sort each list of templates alphabetically.
        Collections.sort(sqTemplates);
        return sqTemplates;
    }

    /**
     * Creates the {@link Template} from the {@link FileTemplate}. Returns null if the {@link FileTemplate} is not
     * one of ours (does not start with SQTJ- or SQTG-).
     *
     * @param template the template for which to create the Template.
     * @return the Template or null.
     */
    @Nullable
    private Template createTemplateFromFileTemplate(final FileTemplate template) {
        final String templateName = template.getName();
        if(templateName.startsWith(SQTemplatePrefixJava) || templateName.startsWith(SQTemplatePrefixGroovy)) {
            final String internalTemplateSaveName = String.format("%s.%s", templateName, template.getExtension());
            final String presentationName = computePresentationName(internalTemplateSaveName);
            return new Template(internalTemplateSaveName, presentationName, false);
        }
        return null;
    }

    public String getTemplateText(@NotNull final Template template) {
        if(template.isInternal()) {
            return StringUtil.convertLineSeparators(FileTemplateManager.getDefaultInstance().getInternalTemplate(template.getInternalTemplateSaveName()).getText());
        } else {
            return StringUtil.convertLineSeparators(FileTemplateManager.getDefaultInstance().getTemplate(template.getInternalTemplateSaveName()).getText());
        }
    }

    public void saveTemplate(@NotNull final Template template, @NotNull final String newTemplateText) {
        final FileTemplateManager templateManager = FileTemplateManager.getDefaultInstance();
        final FileTemplate existingTemplate = templateManager.getTemplate(template.getInternalTemplateSaveName());
        existingTemplate.setText(newTemplateText);
        templateManager.saveAllTemplates();
    }

    /**
     * Renames the originalTemplate to the template name created from templateNameInputFromUser.
     *
     * @param originalTemplate          the original template.
     * @param templateNameInputFromUser the template name entered by the user.
     * @return the updated template.
     */
    public Template renameTemplate(
            final Template originalTemplate,
            final String templateNameInputFromUser) {

        final FileTemplateManager templateManager = FileTemplateManager.getDefaultInstance();

        // internalTemplateSaveName = "aa.java"
        final String internalTemplateSaveName = computeInternalTemplateNameFromPresentationName(templateNameInputFromUser.trim(), originalTemplate.getTemplateLanguage());
        // internalTemplateSaveNameWithoutExtension = "aa".
        final String internalTemplateSaveNameWithoutExtension = removeLanguageAndVelocityExtensions(internalTemplateSaveName);

        final FileTemplate existingTemplate = getFileTemplate(originalTemplate);
        final FileTemplate newTemplate = templateManager.addTemplate(internalTemplateSaveNameWithoutExtension, originalTemplate.getTemplateLanguage().getFileExtension());
        newTemplate.setText(existingTemplate.getText());

        FileTemplate[] allTemplates = templateManager.getAllTemplates();
        final int indexOfExistingTemplate = ArrayUtils.indexOf(allTemplates, existingTemplate);
        allTemplates[indexOfExistingTemplate] = newTemplate;
        templateManager.setTemplates(FileTemplateManager.DEFAULT_TEMPLATES_CATEGORY, Arrays.asList(allTemplates));

        return createTemplateFromFileTemplate(newTemplate);
    }

    public void deleteTemplate(final Template template) {
        final FileTemplate fileTemplate = getFileTemplate(template);
        final FileTemplateManager templateManager = FileTemplateManager.getDefaultInstance();
        FileTemplate[] allTemplates = templateManager.getAllTemplates();
        allTemplates = ArrayUtils.remove(allTemplates, ArrayUtils.indexOf(allTemplates, fileTemplate));
        templateManager.setTemplates(FileTemplateManager.DEFAULT_TEMPLATES_CATEGORY, Arrays.asList(allTemplates));
    }

    public Template saveNewTemplate(
            final TemplateLanguage templateLanguage, final String templateNameInputFromUser,
            final String templateText) {

        // Construct the FileTemplate to save.
        final String internalTemplateSaveName = computeInternalTemplateNameFromPresentationName(templateNameInputFromUser, templateLanguage);
        final String internalTemplateSaveNameWithoutExtension = removeLanguageAndVelocityExtensions(internalTemplateSaveName);
        final String newTemplatePresentationName = computePresentationName(internalTemplateSaveName);
        final String templateSaveExt = templateLanguage.getFileExtension();

        // Tell the template manager to save it.
        final FileTemplateManager templateManager = FileTemplateManager.getDefaultInstance();
        final FileTemplate template = templateManager.addTemplate(internalTemplateSaveNameWithoutExtension, templateSaveExt);
        template.setText(templateText);

        // Calling templateManager.saveAllTemplates() does NOT write the templates to disk. If you only call
        // saveAllTemplates(), the template will not be there when you restart IntelliJ.

        // Instead, use the same logic the AllFileTemplatesConfigurable uses to save the template. This is the logic applied
        // when the user creates a template via the "File and Code Templates" view.
        FileTemplate[] templates = templateManager.getAllTemplates();
        ArrayUtils.add(templates, template);
        templateManager.setTemplates(FileTemplateManager.DEFAULT_TEMPLATES_CATEGORY, Arrays.asList(templates));

        return new Template(
                internalTemplateSaveName,
                newTemplatePresentationName,
                false);
    }

    public FileTemplate getFileTemplate(final Template template) {
        final FileTemplateManager manager = FileTemplateManager.getDefaultInstance();
        if(template.isInternal()) {
            return manager.getInternalTemplate(template.getInternalTemplateSaveName());
        } else {
            return manager.getTemplate(template.getInternalTemplateSaveName());
        }
    }

    public static String computePresentationNameFromTemplate(final FileTemplate template) {
        final String templateName = template.getName();
        final String extension = template.getExtension();

        String presentationName = templateName;
        if(templateName.startsWith(SQTemplatePrefixJava)) {
            presentationName = templateName.replaceFirst(SQTemplatePrefixJava, "");
        } else if(templateName.startsWith(SQTemplatePrefixGroovy)) {
            presentationName = templateName.replaceFirst(SQTemplatePrefixGroovy, "");
        }
        presentationName = presentationName + "." + extension + ".ft";
        return presentationName;
    }

    public boolean userTemplateExists(final TemplateLanguage templateLanguage, final String saveNameFromUser) {
        final String internalTemplateSaveName = computeInternalTemplateNameFromPresentationName(saveNameFromUser, templateLanguage);
        final FileTemplateManager manager = FileTemplateManager.getDefaultInstance();
        final FileTemplate existingTemplate = manager.getTemplate(internalTemplateSaveName);
        return existingTemplate != null;
    }

    private static String computeInternalTemplateNameFromPresentationName(
            final String templatePresentationName,
            final TemplateLanguage templateLanguage) {
        // Remove any .java.ft or .groovy.ft extension the user may have added.
        final String templatePresentationNameWithoutExtension = removeLanguageAndVelocityExtensions(templatePresentationName);
        final String prefix = templateLanguage == TemplateLanguage.GROOVY ? SQTemplatePrefixGroovy : SQTemplatePrefixJava;
        return prefix + templatePresentationNameWithoutExtension + templateLanguage.getFileExtensionWithDot();
    }


    public static String removeLanguageAndVelocityExtensions(@NotNull final String templateSaveName) {
        final List<String> fileExtensions = Arrays.asList(".java.ft", ".groovy.ft", ".java", ".groovy");
        for(final String extension : fileExtensions) {
            if(templateSaveName.endsWith(extension)) {
                return templateSaveName.substring(0, templateSaveName.indexOf(extension));
            }
        }
        return templateSaveName;
    }

    private static String computePresentationName(final String internalTemplateSaveName) {
        if(internalTemplateSaveName.startsWith(SQTemplatePrefixJava)) {
            return internalTemplateSaveName.replaceFirst(SQTemplatePrefixJava, "") + ".ft";
        } else if(internalTemplateSaveName.startsWith(SQTemplatePrefixGroovy)) {
            return internalTemplateSaveName.replaceFirst(SQTemplatePrefixGroovy, "") + ".ft";
        } else {
            return internalTemplateSaveName + ".ft";
        }
    }
}
