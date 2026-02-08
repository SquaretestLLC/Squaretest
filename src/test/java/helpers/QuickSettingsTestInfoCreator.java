/*
 *
 *  * Copyright 2026 Squaretest LLC.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *   http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */
package helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intellij.ide.fileTemplates.FileTemplate;
import com.intellij.ide.fileTemplates.FileTemplateManager;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class QuickSettingsTestInfoCreator {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Collection<? extends QuickSettingsTestInfo> createTestsForGroup(
            final String testRootDir, @NotNull final File testGroupDir,
            @NotNull final String testClassFileName) {

        final InternalTemplateModificationConfig internalTemplateModificationConfig = findInternalTemplateConfig(testGroupDir);

        // Enumerate the test cases.
        final File[] testCaseDirectories = testGroupDir.listFiles((dir, name) -> !name.endsWith(".json"));

        final Path testRootDirPath = Paths.get(testRootDir);
        final List<QuickSettingsTestInfo> ret = new LinkedList<>();

        for(final File testCaseDir : testCaseDirectories) {
            System.out.println("testCaseDir=" + testCaseDir.getPath());
            ret.add(new QuickSettingsTestInfo(
                    testRootDirPath.relativize(Paths.get(testCaseDir.getPath())).toString(),
                    internalTemplateModificationConfig,
                    testGroupDir.getName() + "_" + testCaseDir.getName(),
                    testClassFileName));
        }
        return ret;
    }

    @NotNull
    public static FileTemplate createFileTemplateFromConfig(
            final QuickSettingsTestInfo quickSettingsTestInfo) {

        final FileTemplateManager templateManager = FileTemplateManager.getDefaultInstance();
        final InternalTemplateModificationConfig templateConfig = quickSettingsTestInfo.templateConfig();
        final FileTemplate internalTemplate = templateManager.getInternalTemplate(templateConfig.internalTemplateName());
        String currentTemplateText = internalTemplate.getText();
        final List<String> replacementLines = templateConfig.replacementLines();
        if(replacementLines.size() % 2 != 0) {
            throw new RuntimeException("There should be an even number of replacement lines");
        }
        for(int i = 0; i < replacementLines.size() - 1; i += 2) {
            final String originalLine = replacementLines.get(i);
            final String replacementLine = replacementLines.get(i + 1);
            final int numberOfOriginalLineOccurrences = StringUtils.countMatches(currentTemplateText, originalLine);
            if(numberOfOriginalLineOccurrences != 1) {
                throw new RuntimeException(
                        String.format("File template %s contains %d instances of originalLine=%s",
                                internalTemplate.getName(), numberOfOriginalLineOccurrences, originalLine));
            }
            currentTemplateText = currentTemplateText.replace(originalLine, replacementLine);
        }

        final FileTemplate fileTemplate = templateManager.addTemplate("UNIT_TEST_TEMPLATE", internalTemplate.getExtension());
        fileTemplate.setText(currentTemplateText);
        return fileTemplate;
    }

    @NotNull
    private static InternalTemplateModificationConfig findInternalTemplateConfig(@NotNull final File testGroupDir) {
        if(!testGroupDir.exists()) {
            throw new RuntimeException("Test group dir does not exist " + testGroupDir.getAbsolutePath());
        }
        final File[] internalTemplateModificationConfigFiles = testGroupDir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(final File dir, final String name) {
                return "config.json".equals(name);
            }
        });

        if(internalTemplateModificationConfigFiles == null || internalTemplateModificationConfigFiles.length != 1) {
            throw new RuntimeException("cannot find config.json in " + testGroupDir.getAbsolutePath());
        }

        final File internalTemplateModificationConfigFile = internalTemplateModificationConfigFiles[0];
        try {
            return objectMapper.readValue(internalTemplateModificationConfigFile, InternalTemplateModificationConfig.class);
        } catch(final IOException e) {
            throw new RuntimeException("Could not parse JSON object from config.json file in " + internalTemplateModificationConfigFile.getAbsolutePath(), e);
        }
    }
}
