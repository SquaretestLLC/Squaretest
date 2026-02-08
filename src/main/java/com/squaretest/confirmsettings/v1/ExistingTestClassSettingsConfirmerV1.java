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
package com.squaretest.confirmsettings.v1;

import com.squaretest.generation.existingtest.common.InvokedConstructorInfo;
import com.squaretest.generation.existingtest.common.MemberField;
import com.squaretest.generation.existingtest.common.PackageLocalFieldsInfo;
import com.squaretest.generation.existingtest.common.RefInfo;
import com.squaretest.generation.existingtest.common.TestClassInfo;
import com.squaretest.generation.existingtest.common.VariableInfo;
import com.squaretest.template.api.Api;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ExistingTestClassSettingsConfirmerV1 {

    private final Api.SourceClass sourceClass;
    private final TestClassInfo existingTestClassInfo;

    public ExistingTestClassSettingsConfirmerV1(
            final Api.SourceClass sourceClass, final TestClassInfo existingTestClassInfo) {
        this.sourceClass = sourceClass;
        this.existingTestClassInfo = existingTestClassInfo;
    }

    public Map<String, Object> confirmSettings(final Map<String, Object> settingsToConfirm) {
        final InvokedConstructorInfo invokedConstructorInfo = existingTestClassInfo.getInvokedConstructorInfo();
        final PackageLocalFieldsInfo packageLocalFieldsInfo = existingTestClassInfo.getPackageLocalFieldsInfo();

        // Handle the case where this is a Spring test class.
        if(existingTestClassInfo.isSpringTestClass()) {
            final Map<String, Object> newSettings = applyGeneralDependencyUpdateLogic(settingsToConfirm, existingTestClassInfo);
            if(existingTestClassInfo.getMockMvcMemberName() != null) {
                newSettings.put("mockMvcMemberName", existingTestClassInfo.getMockMvcMemberName());
            } else if(existingTestClassInfo.getRestTemplateMemberName() != null) {
                newSettings.put("mockMvcMemberName", existingTestClassInfo.getRestTemplateMemberName());
            }
            return newSettings;
        }

        // If we've found the source class member inside the test class, update the SourceClass.testClassMemberName
        // so that the template will use the same name for the member as the existing test class.
        if(existingTestClassInfo.getTestClassMemberName() != null) {
            sourceClass.setTestClassMemberName(existingTestClassInfo.getTestClassMemberName());
        }

        if(invokedConstructorInfo != null) {
            if(!invokedConstructorInfo.constructor().getParameters().isEmpty()) {
                return createMapForInvokedConstructor(settingsToConfirm, invokedConstructorInfo);
            }
        }
        if(packageLocalFieldsInfo != null && !packageLocalFieldsInfo.variableInfoMap().isEmpty()) {
            return createMapForPackageLocalInfo(settingsToConfirm, packageLocalFieldsInfo);
        }

        return applyGeneralDependencyUpdateLogic(settingsToConfirm, existingTestClassInfo);
    }

    private void setToNonMockDefault(final Api.Variable variable) {
        variable.setShouldBeMocked(false);
        variable.setShouldStoreInReference(false);
        variable.setInitExpression(variable.getDefaultInitExpression());
    }

    private Map<String, Object> applyGeneralDependencyUpdateLogic(
            @NotNull final Map<String, Object> settingsFromTemplate,
            @NotNull final TestClassInfo existingTestClassInfo) {
        final List<? extends Api.Variable> dependenciesFromTemplate = Utils.safeGetDependencies(settingsFromTemplate);
        final List<MemberField> existingTestClassMembers = existingTestClassInfo.getTestClassMembers();
        final DependencyToExistingFieldMatcher matcher = new DependencyToExistingFieldMatcher(existingTestClassMembers);
        for(final Api.Variable variable : dependenciesFromTemplate) {
            final MemberField matchingMember = matcher.chooseBestMatchForVariable(variable);
            updateVariable(variable, matchingMember);
        }

        final List<Api.Variable> members = dependenciesFromTemplate.stream().filter(Api.Variable::getShouldStoreInReference).collect(Collectors.toList());
        final List<Api.Variable> mockMembers = members.stream().filter(Api.Variable::getShouldBeMocked).collect(Collectors.toList());
        final List<Api.Variable> nonMockMembers = members.stream().filter(x -> !x.getShouldBeMocked()).collect(Collectors.toList());
        final Map<String, Object> newSettings = new HashMap<>(settingsFromTemplate);
        newSettings.put("memberFields", members);
        newSettings.put("mockMemberFields", mockMembers);
        newSettings.put("nonMockMemberFields", nonMockMembers);
        newSettings.put("mocksNeeded", !mockMembers.isEmpty());
        return newSettings;

    }

    private void updateVariable(@NotNull final Api.Variable variable, @Nullable final MemberField matchingMember) {
        if(matchingMember == null) {
            variable.setShouldBeMocked(false);
            variable.setShouldStoreInReference(false);
        } else {
            variable.setShouldBeMocked(matchingMember.isMock());
            variable.setTestClassMemberName(matchingMember.name());
            variable.setShouldStoreInReference(true);
        }
    }

    private Map<String, Object> createMapForPackageLocalInfo(
            @NotNull final Map<String, Object> settingsFromTemplate,
            @NotNull final PackageLocalFieldsInfo packageLocalFieldsInfo) {
        Set<Api.Variable> dependenciesFromTestClass = packageLocalFieldsInfo.variableInfoMap().keySet();
        updateVariables(dependenciesFromTestClass, packageLocalFieldsInfo.variableInfoMap());
        final List<Api.Variable> members = dependenciesFromTestClass.stream().filter(Api.Variable::getShouldStoreInReference).collect(Collectors.toList());
        final List<Api.Variable> mockMembers = members.stream().filter(Api.Variable::getShouldBeMocked).collect(Collectors.toList());
        final List<Api.Variable> nonMockMembers = members.stream().filter(x -> !x.getShouldBeMocked()).collect(Collectors.toList());
        final Map<String, Object> newSettings = new HashMap<>(settingsFromTemplate);
        newSettings.put("memberFields", members);
        newSettings.put("mockMemberFields", mockMembers);
        newSettings.put("nonMockMemberFields", nonMockMembers);
        newSettings.put("mocksNeeded", !mockMembers.isEmpty());
        newSettings.put("shouldSetPackageLocalFields", true);
        newSettings.put("shouldUseInjectMocks", false);
        newSettings.put("sourceClassMemberNeeded", true);
        return newSettings;
    }

    private Map<String, Object> createMapForInvokedConstructor(
            @NotNull final Map<String, Object> settingsFromTemplate,
            @NotNull final InvokedConstructorInfo invokedConstructorInfo) {
        final Api.Constructor invokedConstructor = invokedConstructorInfo.constructor();
        sourceClass.setPreferredConstructor(invokedConstructor);
        final List<Api.Variable> dependenciesFromTestClass = invokedConstructor.getParameters();
        updateVariables(dependenciesFromTestClass, invokedConstructorInfo.variableToTestClassVarInfoMap());
        final List<Api.Variable> members = dependenciesFromTestClass.stream().filter(Api.Variable::getShouldStoreInReference).collect(Collectors.toList());
        final List<Api.Variable> mockMembers = members.stream().filter(Api.Variable::getShouldBeMocked).collect(Collectors.toList());
        final List<Api.Variable> nonMockMembers = members.stream().filter(x -> !x.getShouldBeMocked()).collect(Collectors.toList());

        final Map<String, Object> newSettings = new HashMap<>(settingsFromTemplate);
        newSettings.put("memberFields", members);
        newSettings.put("mockMemberFields", mockMembers);
        newSettings.put("nonMockMemberFields", nonMockMembers);
        newSettings.put("mocksNeeded", !mockMembers.isEmpty());
        newSettings.put("shouldSetPackageLocalFields", false);
        newSettings.put("shouldUseInjectMocks", false);
        newSettings.put("sourceClassMemberNeeded", true);
        return newSettings;
    }

    private void updateVariables(
            final Collection<Api.Variable> dependenciesFromTestClass,
            final Map<Api.Variable, VariableInfo> variableToTestClassVarInfoMap) {
        for(final Api.Variable variable : dependenciesFromTestClass) {
            final VariableInfo variableInfo = variableToTestClassVarInfoMap.get(variable);
            if(variableInfo != null) {
                final RefInfo refInfo = variableInfo.getRefInfo();
                if(refInfo != null) {
                    variable.setTestClassMemberName(refInfo.name());
                }
                variable.setShouldBeMocked(variableInfo.shouldBeMocked());
                variable.setShouldStoreInReference(variableInfo.shouldStoreInReference());
                if(!variableInfo.shouldBeMocked()) {
                    variable.setInitExpression(variable.getDefaultInitExpression());
                }
            } else {
                // If we have no VariableInfo for the given Variable, that means there was no actual parameter for this
                // variable in the constructor call; e.g. the constructor requires 4 arguments, but the call only provided
                // 3. The user's build is broken, as this will cause a compiler error.
                // Just set the variable to the default, non-mock state.
                setToNonMockDefault(variable);
            }
        }
    }
}
