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
package com.squaretest.completion.frameworkdetection;

import com.intellij.ide.fileTemplates.FileTemplate;
import com.squaretest.completion.TemplateInfo;
import com.squaretest.generation.runconfig.infoprovider.FrameworkInfo;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

public class FrameworkInferencer {

    private static final String[] JUnit5SearchStrings = new String[]{"org.junit.jupiter"};
    private static final String[] JUnit4SearchStrings = new String[]{"org.junit.Test", "org.junit.Before"};
    private static final String[] AndroidJUnit4SearchStrings = new String[]{"AndroidJUnit4"};
    private static final String[] Robolectric3SearchStrings = new String[]{"org.robolectric.Robolectric", "RobolectricTestRunner"};
    private static final String[] TestNGSearchStrings = new String[]{"import org.testng.", "import static org.testng."};
    private static final String[] SpringSearchStrings = new String[]{"import org.springframework."};
    private static final String[] AssertJSearchStrings = new String[]{"org.assertj."};
    private static final String[] GoogleTruthSearchStrings = new String[]{"com.google.common.truth."};

    @NotNull
    public FrameworkInfo determineFrameworkInfoFromTemplate(
            @NotNull final TemplateInfo templateInfo) {
        if(templateInfo.isInternalTemplate()) {
            return determineFrameworkInfoFromInternalTemplate(templateInfo.getInternalTemplateName());
        }
        // Inspect the template code to determine which frameworks and libraries are used.
        final String templateText = templateInfo.getTemplateText();
        return determineFrameworkFromTemplateText(templateText);
    }

    public FrameworkInfo determineFrameworkInfoFromTemplate(final FileTemplate fileTemplate) {
        final String templateText = fileTemplate.getText();
        return determineFrameworkFromTemplateText(templateText);
    }

    @NotNull
    private static FrameworkInfo determineFrameworkFromTemplateText(final String templateText) {
        return new FrameworkInfo(
                StringUtils.containsAny(templateText, JUnit5SearchStrings),
                StringUtils.containsAny(templateText, JUnit4SearchStrings),
                StringUtils.containsAny(templateText, AndroidJUnit4SearchStrings),
                StringUtils.containsAny(templateText, Robolectric3SearchStrings),
                StringUtils.containsAny(templateText, TestNGSearchStrings),
                StringUtils.containsAny(templateText, SpringSearchStrings),
                StringUtils.containsAny(templateText, AssertJSearchStrings),
                StringUtils.containsAny(templateText, GoogleTruthSearchStrings));
    }

    private FrameworkInfo determineFrameworkInfoFromInternalTemplate(final String internalTemplateName) {
        return new FrameworkInfo(
                internalTemplateName.contains("JUnit5Mockito"),
                internalTemplateName.contains("JUnit4Mockito"),
                internalTemplateName.contains("AndroidJUnit4"),
                internalTemplateName.contains("Robolectric"),
                internalTemplateName.contains("TestNG"),
                internalTemplateName.contains("Spring"),
                internalTemplateName.contains("AssertJ"),
                false);
    }
}
