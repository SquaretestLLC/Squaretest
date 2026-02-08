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
package com.squaretest.generation;

import com.squaretest.template.api.Api;
import com.squaretest.template.api.TemplateVariableNames;
import com.squaretest.template.impl.BooleanUtilsImpl;
import com.squaretest.template.impl.ListUtilsImpl;
import com.squaretest.template.impl.SQStringUtils;
import com.squaretest.template.impl.TemplateData;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class TemplateVariableMapUtil {
    public static Map<String, Object> createMapForTemplate(
            @NotNull final TemplateData templateData,
            final Api.TestInfoFactory testInfoFactory,
            final Api.CodeStyleUtils codeStyleUtils,
            final Api.ClassUtils classUtils,
            final Api.UiUtils uiUtils,
            final boolean shouldAskToConfirmMocks,
            final boolean springWebMvcTestIncludesSpringExtension) {
        final Map<String, Object> templateVariablesMap = new HashMap<>();
        templateVariablesMap.put(TemplateVariableNames.ClassUnderTest, templateData.getClassUnderTest());
        templateVariablesMap.put(TemplateVariableNames.ImportList, templateData.getImportLinesRequiredForDependencies());
        templateVariablesMap.put(TemplateVariableNames.StringUtils, SQStringUtils.class);
        templateVariablesMap.put(TemplateVariableNames.BooleanUtils, new BooleanUtilsImpl());
        templateVariablesMap.put(TemplateVariableNames.FluentList, Api.FluentListFactory.class);
        templateVariablesMap.put(TemplateVariableNames.AltInfo, Api.AltInfoFactory.class);
        templateVariablesMap.put(TemplateVariableNames.TestInfo, testInfoFactory);
        templateVariablesMap.put(TemplateVariableNames.BeanContext, Api.BeanContextFactory.class);
        templateVariablesMap.put(TemplateVariableNames.MutableInt, Api.MutableIntFactory.class);
        templateVariablesMap.put(TemplateVariableNames.MutableBoolean, Api.MutableBooleanFactory.class);
        templateVariablesMap.put(TemplateVariableNames.MutableString, Api.MutableStringFactory.class);
        templateVariablesMap.put(TemplateVariableNames.MutableObject, Api.MutableObjectFactory.class);
        templateVariablesMap.put(TemplateVariableNames.Newline, "\n");
        templateVariablesMap.put(TemplateVariableNames.ListUtils, new ListUtilsImpl());
        templateVariablesMap.put(TemplateVariableNames.CodeStyleUtils, codeStyleUtils);
        templateVariablesMap.put(TemplateVariableNames.ClassUtils, classUtils);
        templateVariablesMap.put(TemplateVariableNames.UiUtils, uiUtils);
        templateVariablesMap.put(TemplateVariableNames.ShouldAskToConfirmMocks, shouldAskToConfirmMocks);
        templateVariablesMap.put(TemplateVariableNames.Null, null);
        templateVariablesMap.put(TemplateVariableNames.IsCreatingNewTest, templateData.getTestClassInfo() == null);
        templateVariablesMap.put(TemplateVariableNames.SpringWebMvcTestIncludesSpringExtension, springWebMvcTestIncludesSpringExtension);
        return templateVariablesMap;
    }
}
