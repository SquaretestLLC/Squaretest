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
package com.squaretest.generation.existingtest.main;

import com.squaretest.generation.existingtest.common.MemberField;
import com.squaretest.generation.existingtest.common.TestClassInfo;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public class SpringInfoPopulator {

    @SuppressWarnings("OptionalIsPresent")
    public void provisionSpringInfoIfNeeded(
            @NotNull final List<MemberField> memberFields,
            @NotNull final TestClassInfo testClassInfo) {

        // Determine if this is a spring test class.
        final Optional<MemberField> mockMvcMember = memberFields.stream().filter(x -> x.isExactType("org.springframework.test.web.servlet.MockMvc")).findFirst();
        final Optional<MemberField> testTemplateMember = memberFields.stream().filter(x -> x.isType("org.springframework.boot.test.web.client.TestRestTemplate")).findFirst();

        if(mockMvcMember.isPresent() || testTemplateMember.isPresent()) {
            testClassInfo.setSpringTestClass(true);
        }
        if(mockMvcMember.isPresent()) {
            testClassInfo.setMockMvcMemberName(mockMvcMember.get().name());
        }
        if(testTemplateMember.isPresent()) {
            testClassInfo.setTestRestTemplateMemberName(testTemplateMember.get().name());
        }
    }
}
