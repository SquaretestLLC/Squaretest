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

import com.squaretest.confirmsettings.v1.DependencyToExistingFieldMatcher;
import com.squaretest.generation.existingtest.common.Constants;
import com.squaretest.generation.existingtest.common.InvokedConstructorInfo;
import com.squaretest.generation.existingtest.common.MemberField;
import com.squaretest.generation.existingtest.common.PackageLocalFieldsInfo;
import com.squaretest.generation.existingtest.common.RefInfo;
import com.squaretest.generation.existingtest.common.RefType;
import com.squaretest.generation.existingtest.common.TestClassInfo;
import com.squaretest.generation.existingtest.common.VariableInfo;
import com.squaretest.generation.existingtest.main.LocalVariableInfo;
import com.squaretest.template.api.Api;
import com.squaretest.template.impl.FluentListImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SuppressWarnings("OptionalIsPresent")
public class ExistingTestClassSettingsConfirmerV2 {
    private final Api.SourceClass sourceClass;
    private final TestClassInfo existingTestClassInfo;

    public ExistingTestClassSettingsConfirmerV2(
            final Api.SourceClass sourceClass, final TestClassInfo existingTestClassInfo) {
        this.sourceClass = sourceClass;
        this.existingTestClassInfo = existingTestClassInfo;
    }

    public Map<String, Object> confirmSettings(final Map<String, Object> settingsToConfirm) {
        // Handle the case where this is a Spring test class.
        if(existingTestClassInfo.isSpringTestClass()) {
            final Map<String, Object> newSettings = applyGeneralDependencyUpdateLogic(settingsToConfirm, existingTestClassInfo);
            if(existingTestClassInfo.getMockMvcMemberName() != null) {
                newSettings.put("mockMvcMemberName", existingTestClassInfo.getMockMvcMemberName());
            } else if(existingTestClassInfo.getRestTemplateMemberName() != null) {
                newSettings.put("mockMvcMemberName", existingTestClassInfo.getRestTemplateMemberName());
            }
            updateMethods(newSettings);
            return newSettings;
        }

        // If we use InjectMocks, apply general dependency update logic.
        final List<MemberField> testClassMembers = existingTestClassInfo.getTestClassMembers();
        final Optional<MemberField> injectMocksField = testClassMembers.stream().filter(x -> x.hasAnnotation(Constants.InjectMocksCanonicalName)).findFirst();
        if(injectMocksField.isPresent()) {
            final Map<String, Object> newSettings = applyGeneralDependencyUpdateLogic(settingsToConfirm, existingTestClassInfo);
            String testClassMemberName = existingTestClassInfo.getTestClassMemberName();
            if(testClassMemberName == null) {
                testClassMemberName = injectMocksField.get().name();
            }
            newSettings.put("testClassMemberName", testClassMemberName);
            newSettings.put("shouldCreateSourceClassInEachTestMethod", false);
            updateMethods(newSettings);
            return newSettings;
        }

        // Configure the options normally.
        final Map<String, Object> newSettings = new HashMap<>(settingsToConfirm);
        updateMethods(newSettings);
        final String testClassMemberName = existingTestClassInfo.getTestClassMemberName();
        final LocalVariableInfo testClassLocalVariableInfo = existingTestClassInfo.getTestClassLocalVariableInfo();
        final String testClassLocalVariableName = testClassLocalVariableInfo.getName();
        if(testClassMemberName == null && testClassLocalVariableInfo.didConstructSourceClass()) {
            newSettings.put("shouldCreateSourceClassInEachTestMethod", true);
        } else {
            newSettings.put("shouldCreateSourceClassInEachTestMethod", false);
        }
        if(testClassMemberName != null) {
            newSettings.put("testClassMemberName", testClassMemberName);
        }
        if(testClassLocalVariableName != null) {
            newSettings.put("testClassLocalFieldName", testClassLocalVariableName);
        }

        final InvokedConstructorInfo invokedConstructorInfo = existingTestClassInfo.getInvokedConstructorInfo();
        final PackageLocalFieldsInfo packageLocalFieldsInfo = existingTestClassInfo.getPackageLocalFieldsInfo();
        final Map<Api.Variable, VariableInfo> calledSetterInfo = existingTestClassInfo.getCalledSetterInfo();
        final Map<Api.Variable, VariableInfo> reflectionInfo = existingTestClassInfo.getReflectionInfo();
        final List<MemberField> testClassMembersWithoutSourceMember = testClassMembers.stream().filter(x -> !StringUtils.equals(x.name(), testClassMemberName)).collect(Collectors.toList());

        // Determine which sourceClass members are set.
        Set<MemberField> usedMembers = new HashSet<>();
        final Map<Api.ClassMember, VariableInfo> fieldToVariableInfo = new HashMap<>();
        if(invokedConstructorInfo != null) {
            final Api.Constructor constructor = invokedConstructorInfo.constructor();
            for(final Api.Variable param : constructor.getParameters()) {
                final List<Api.ClassMember> fieldsSatisfiedByParam = getFieldsSatisfiedByParam(param);
                final VariableInfo variableInfo = invokedConstructorInfo.variableToTestClassVarInfoMap().get(param);
                if(variableInfo != null) {
                    putAllIfAbsent(fieldToVariableInfo, fieldsSatisfiedByParam, variableInfo);
                }
            }
        }

        if(calledSetterInfo != null) {
            for(final Map.Entry<Api.Variable, VariableInfo> entry : calledSetterInfo.entrySet()) {
                final Api.Variable param = entry.getKey();
                final VariableInfo variableInfo = entry.getValue();
                final List<Api.ClassMember> fieldsSatisfiedByParam = getFieldsSatisfiedByParam(param);
                putAllIfAbsent(fieldToVariableInfo, fieldsSatisfiedByParam, variableInfo);
            }
        }

        if(packageLocalFieldsInfo != null) {
            for(final Map.Entry<Api.Variable, VariableInfo> entry : packageLocalFieldsInfo.variableInfoMap().entrySet()) {
                final Api.Variable param = entry.getKey();
                final VariableInfo variableInfo = entry.getValue();
                final List<Api.ClassMember> fieldsSatisfiedByParam = getFieldsSatisfiedByParam(param);
                putAllIfAbsent(fieldToVariableInfo, fieldsSatisfiedByParam, variableInfo);
            }
        }

        if(reflectionInfo != null) {
            for(final Map.Entry<Api.Variable, VariableInfo> entry : reflectionInfo.entrySet()) {
                final Api.Variable param = entry.getKey();
                final VariableInfo variableInfo = entry.getValue();
                final List<Api.ClassMember> fieldsSatisfiedByParam = getFieldsSatisfiedByParam(param);
                putAllIfAbsent(fieldToVariableInfo, fieldsSatisfiedByParam, variableInfo);
            }
        }

        // Apply that information.
        // Foreach dep: look at field.possibleSourceVars.intersection(dep) to find the field for the dep.
        // Look up the field in the map, and apply the VariableInfo to the dep.
        // Keep track of which mock members are used. If we have a mockMember that is not used, search for a field
        // that matches. If the field is not satisfied, and the member and field are the only ones of that type, match them up.
        // Possibly consider: only do this if the field is assigned in the constructor or is final.
        final Set<Api.Variable> usedDeps = new HashSet<>();
        final List<? extends Api.Variable> dependenciesFromTemplate = Utils.safeGetDependencies(settingsToConfirm);
        final List<? extends Api.Variable> otherFieldsFromTemplate = Utils.safeGetPossibleDependencies(settingsToConfirm);
        final List<? extends Api.Variable> allDepsFromTemplate = Stream.concat(dependenciesFromTemplate.stream(), otherFieldsFromTemplate.stream()).toList();
        final Set<Api.ClassMember> fieldsThatWereSet = new HashSet<>();
        for(final Api.Variable dependency : dependenciesFromTemplate) {
            final Api.FluentList<Api.ClassMember> fieldsSatisfiedByParam = getFieldsSatisfiedByParam(dependency);
            final VariableInfo variableInfo = getFirstEntry(fieldToVariableInfo, fieldsSatisfiedByParam);
            if(variableInfo != null) {
                fieldsThatWereSet.addAll(fieldsSatisfiedByParam);
                final RefInfo refInfo = variableInfo.getRefInfo();
                if(refInfo != null) {
                    if(refInfo.refType() == RefType.TestClassMember && variableInfo.shouldBeMocked()) {
                        final Optional<MemberField> memberFieldUsed = testClassMembersWithoutSourceMember.stream().filter(x -> x.name().equals(variableInfo.getRefInfo().name())).findFirst();
                        if(memberFieldUsed.isPresent()) {
                            usedMembers.add(memberFieldUsed.get());
                        }
                    }
                }
                updateVariable(dependency, variableInfo);
                usedDeps.add(dependency);
                if(dependency instanceof Api.ClassMember) {
                    usedDeps.addAll(((Api.ClassMember) dependency).getPossibleSourceVariables());
                }
                usedDeps.addAll(fieldsSatisfiedByParam);
            }
        }
        for(final Api.Variable dependency : otherFieldsFromTemplate) {
            final Api.FluentList<Api.ClassMember> fieldsSatisfiedByParam = getFieldsSatisfiedByParam(dependency);
            final VariableInfo variableInfo = getFirstEntry(fieldToVariableInfo, fieldsSatisfiedByParam);
            if(variableInfo != null) {
                fieldsThatWereSet.addAll(fieldsSatisfiedByParam);
                final RefInfo refInfo = variableInfo.getRefInfo();
                if(refInfo != null) {
                    if(refInfo.refType() == RefType.TestClassMember && variableInfo.shouldBeMocked()) {
                        final Optional<MemberField> memberFieldUsed = testClassMembersWithoutSourceMember.stream().filter(x -> x.name().equals(variableInfo.getRefInfo().name())).findFirst();
                        if(memberFieldUsed.isPresent()) {
                            usedMembers.add(memberFieldUsed.get());
                        }
                    }
                }
                updateVariable(dependency, variableInfo);
                usedDeps.add(dependency);
                if(dependency instanceof Api.ClassMember) {
                    usedDeps.addAll(((Api.ClassMember) dependency).getPossibleSourceVariables());
                }
                usedDeps.addAll(fieldsSatisfiedByParam);
            }
        }

        // Match the mocked, unused test class member fields and unset source class fields.
        // Determine the test class members that were not used to satisfy any dependencies.
        final Collection<MemberField> unusedTestClassMembers = CollectionUtils.subtract(testClassMembersWithoutSourceMember, usedMembers);
        // Determine how many unused test class members have the same type (canonical text).
        final Map<String, Integer> unusedTestClassMembersCanonicalTextToCountMap = new HashMap<>();
        for(final MemberField memberField : unusedTestClassMembers) {
            // Increment the count associated with this canonicalText key.
            unusedTestClassMembersCanonicalTextToCountMap.merge(memberField.canonicalText(), 1, Integer::sum);
        }

        // Determine the dependencies (fields provided by the template) that were not set.
        final List<Api.Variable> unsetDepsFromTemplate = new ArrayList<>();
        for(final Api.Variable dep : allDepsFromTemplate) {
            if(hasAnyWithSourceVar(fieldsThatWereSet, dep)) {
                continue;
            }
            unsetDepsFromTemplate.add(dep);
        }
        // Determine how many unused dependencies have the same type (canonical text).
        final Map<String, Integer> unusedDepsCanonicalTextToCountMap = new HashMap<>();
        for(final Api.Variable dep : unsetDepsFromTemplate) {
            if(isConstructorParamWithNoFields(dep)) {
                continue;
            }
            // Increment the count associated with this canonicalText key.
            unusedDepsCanonicalTextToCountMap.merge(dep.getType().getCanonicalText(), 1, Integer::sum);
        }

        for(final Api.Variable dep : unsetDepsFromTemplate) {
            final String depCanonicalText = dep.getType().getCanonicalText();
            final List<MemberField> membersWithSameTypeText = unusedTestClassMembers.stream().filter(x -> x.canonicalText().equals(depCanonicalText)).toList();
            // Check to see if we have a member field with member.name=dep.testClassMemberName.
            final List<MemberField> membersWithSameTestClassMemberName = membersWithSameTypeText.stream().filter(x -> x.name().equals(dep.getTestClassMemberName())).toList();
            if(membersWithSameTestClassMemberName.size() == 1) {
                updateVariable(dep, membersWithSameTestClassMemberName.get(0));
                usedDeps.add(dep);
                if(dep instanceof Api.ClassMember) {
                    usedDeps.addAll(((Api.ClassMember) dep).getPossibleSourceVariables());
                }
                continue;
            }

            // If there is only one member field with the same canonicalText as the dependency, use it.
            if(unusedTestClassMembersCanonicalTextToCountMap.getOrDefault(depCanonicalText, 0) == 1 && unusedDepsCanonicalTextToCountMap.getOrDefault(depCanonicalText, 0) == 1 && !membersWithSameTypeText.isEmpty() && membersWithSameTypeText.get(0).isMock()) {
                // Provision the Api.Variable with the member info.
                updateVariable(dep, membersWithSameTypeText.get(0));
                usedDeps.add(dep);
                if(dep instanceof Api.ClassMember) {
                    usedDeps.addAll(((Api.ClassMember) dep).getPossibleSourceVariables());
                }
            }
        }

        final Collection<Api.Variable> finalUnsetDepsFromTemplate = CollectionUtils.subtract(dependenciesFromTemplate, usedDeps);
        for(final Api.Variable dep : finalUnsetDepsFromTemplate) {
            dep.setShouldBeMocked(false);
            dep.setShouldStoreInReference(false);
        }
        return newSettings;
    }

    private boolean isConstructorParamWithNoFields(final Api.Variable dep) {
        if(!isConstructorParam(dep)) {
            return false;
        }
        for(final Api.ClassMember member : sourceClass.getAllFields()) {
            if(member.getPossibleSourceVariables().contains(dep)) {
                return false;
            }
        }
        return true;
    }

    private boolean hasAnyWithSourceVar(final Collection<Api.ClassMember> fields, final Api.Variable dep) {
        for(final Api.ClassMember field : fields) {
            if(field.getPossibleSourceVariables().contains(dep)) {
                return true;
            }
        }
        return false;
    }

    private boolean isConstructorParam(final Api.Variable dep) {
        final Api.Method containingMethod = dep.getContainingMethod();
        return containingMethod instanceof Api.Constructor;
    }

    private void updateMethods(final Map<String, Object> newSettings) {
        final List<? extends Api.Method> methods = Utils.safeGetMethodsThatCanBeTested(newSettings);
        for(final Api.Method method : methods) {
            method.setShouldTest(true);
        }
    }

    private void updateVariable(final Api.Variable dependency, final VariableInfo variableInfo) {
        dependency.setShouldStoreInReference(variableInfo.shouldStoreInReference());
        final RefInfo refInfo = variableInfo.getRefInfo();
        if(refInfo != null) {
            dependency.setShouldStoreInReference(true);
            dependency.setTestClassMemberName(refInfo.name());
        } else {
            dependency.setShouldStoreInReference(false);
        }
        dependency.setShouldBeMocked(variableInfo.shouldBeMocked());
    }

    private VariableInfo getFirstEntry(final Map<Api.ClassMember, VariableInfo> fieldToVariableInfo, final Api.FluentList<Api.ClassMember> fieldsSatisfiedByParam) {
        for(final Api.ClassMember field : fieldsSatisfiedByParam) {
            final VariableInfo variableInfo = fieldToVariableInfo.get(field);
            if(variableInfo != null) {
                return variableInfo;
            }
        }
        return null;
    }

    private static void putAllIfAbsent(final Map<Api.ClassMember, VariableInfo> fieldToVariableInfo, final List<Api.ClassMember> fieldsSatisfiedByParam, final VariableInfo variableInfo) {
        for(final Api.ClassMember field : fieldsSatisfiedByParam) {
            if(!fieldToVariableInfo.containsKey(field)) {
                fieldToVariableInfo.put(field, variableInfo);
            }
        }
    }

    private Api.FluentList<Api.ClassMember> getFieldsSatisfiedByParam(final Api.Variable param) {
        final Api.FluentList<Api.ClassMember> ret = new FluentListImpl<>();
        for(final Api.ClassMember member : sourceClass.getAllFields()) {
            if(member.getPossibleSourceVariables().contains(param)) {
                ret.add(member);
            }
        }
        return ret;
    }

    private Map<String, Object> applyGeneralDependencyUpdateLogic(
            @NotNull final Map<String, Object> settingsFromTemplate,
            @NotNull final TestClassInfo existingTestClassInfo) {
        final List<? extends Api.Variable> dependenciesFromTemplate = Utils.safeGetDependencies(settingsFromTemplate);
        final List<? extends Api.Variable> possibleDependenciesFromTemplate = Utils.safeGetPossibleDependencies(settingsFromTemplate);
        final List<Api.Variable> allDeps = new ArrayList<>(dependenciesFromTemplate);
        allDeps.addAll(possibleDependenciesFromTemplate);
        final List<MemberField> existingTestClassMembers = existingTestClassInfo.getTestClassMembers();
        final DependencyToExistingFieldMatcher matcher = new DependencyToExistingFieldMatcher(existingTestClassMembers);
        for(final Api.Variable variable : allDeps) {
            final MemberField matchingMember = matcher.chooseBestMatchForVariable(variable);
            updateVariable(variable, matchingMember);
        }
        return new HashMap<>(settingsFromTemplate);
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
}
