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
package com.squaretest.generation.existingtest.common;

import com.squaretest.generation.existingtest.main.LocalVariableInfo;
import com.squaretest.template.api.Api;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class TestClassInfo {

    @Nullable
    private String testClassMemberName;
    private LocalVariableInfo testClassLocalVariableInfo;
    @Nullable
    private String mockMvcMemberName;
    @Nullable
    private String testRestTemplateMemberName;
    private List<MemberField> testClassMembers = Collections.emptyList();
    @Nullable
    private InvokedConstructorInfo invokedConstructorInfo;
    @Nullable
    private PackageLocalFieldsInfo packageLocalFieldsInfo;
    private Map<Api.Variable, VariableInfo> calledSetterInfo;
    private Map<Api.Variable, VariableInfo> reflectionInfo;
    private boolean isSpringTestClass;

    @Nullable
    public String getTestClassMemberName() {
        return testClassMemberName;
    }

    public void setTestClassMemberName(@Nullable final String testClassMemberName) {
        this.testClassMemberName = testClassMemberName;
    }

    public LocalVariableInfo getTestClassLocalVariableInfo() {
        return testClassLocalVariableInfo;
    }

    public void setTestClassLocalVariableInfo(final LocalVariableInfo testClassLocalVariableInfo) {
        this.testClassLocalVariableInfo = testClassLocalVariableInfo;
    }

    public List<MemberField> getTestClassMembers() {
        return testClassMembers;
    }

    public void setTestClassMembers(final List<MemberField> testClassMembers) {
        this.testClassMembers = testClassMembers;
    }

    @Nullable
    public PackageLocalFieldsInfo getPackageLocalFieldsInfo() {
        return packageLocalFieldsInfo;
    }

    public void setPackageLocalFieldsInfo(
            @Nullable final PackageLocalFieldsInfo packageLocalFieldsInfo) {
        this.packageLocalFieldsInfo = packageLocalFieldsInfo;
    }

    public Map<Api.Variable, VariableInfo> getCalledSetterInfo() {
        return calledSetterInfo;
    }

    public void setCalledSetterInfo(final Map<Api.Variable, VariableInfo> calledSetterInfo) {
        this.calledSetterInfo = calledSetterInfo;
    }

    public Map<Api.Variable, VariableInfo> getReflectionInfo() {
        return reflectionInfo;
    }

    public void setReflectionInfo(final Map<Api.Variable, VariableInfo> reflectionInfo) {
        this.reflectionInfo = reflectionInfo;
    }

    @Nullable
    public InvokedConstructorInfo getInvokedConstructorInfo() {
        return invokedConstructorInfo;
    }

    public void setInvokedConstructorInfo(@Nullable final InvokedConstructorInfo invokedConstructorInfo) {
        this.invokedConstructorInfo = invokedConstructorInfo;
    }

    public boolean isSpringTestClass() {
        return isSpringTestClass;
    }

    public void setSpringTestClass(final boolean springTestClass) {
        isSpringTestClass = springTestClass;
    }

    @Nullable
    public String getMockMvcMemberName() {
        return mockMvcMemberName;
    }

    public void setMockMvcMemberName(@Nullable final String mockMvcMemberName) {
        this.mockMvcMemberName = mockMvcMemberName;
    }

    @Nullable
    public String getRestTemplateMemberName() {
        return testRestTemplateMemberName;
    }

    public void setTestRestTemplateMemberName(@Nullable final String testRestTemplateMemberName) {
        this.testRestTemplateMemberName = testRestTemplateMemberName;
    }
}
