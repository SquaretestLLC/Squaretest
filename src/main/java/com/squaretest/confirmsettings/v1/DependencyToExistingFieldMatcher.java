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

import com.squaretest.generation.existingtest.common.MemberField;
import com.squaretest.template.api.Api;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class DependencyToExistingFieldMatcher {
    private final List<MemberField> existingMembers;

    public DependencyToExistingFieldMatcher(
            final List<MemberField> existingMembers) {
        this.existingMembers = new ArrayList<>(existingMembers);
    }

    public MemberField chooseBestMatchForVariable(final Api.Variable variable) {
        final MemberField ret = chooseBestMatchForVariableImpl(variable);
        if(ret != null) {
            this.existingMembers.remove(ret);
        }
        return ret;
    }

    @Nullable
    private MemberField chooseBestMatchForVariableImpl(final Api.Variable variable) {
        // Find members with the exact type of the variable.
        final String varCanonicalName = variable.getType().getCanonicalName();
        if(varCanonicalName == null) {
            return null;
        }

        final List<MemberField> membersWithTypeMatchingName = existingMembers.stream().filter(x -> Objects.equals(varCanonicalName, x.typeCanonicalName())).collect(Collectors.toList());
        if(membersWithTypeMatchingName.size() == 1) {
            return membersWithTypeMatchingName.get(0);
        }

        if(membersWithTypeMatchingName.isEmpty()) {
            // Search for fields that are a subtype of the variable.
            final List<MemberField> membersWithSuperTypeMatchingName = existingMembers.stream().filter(x -> x.superTypeCanonicalNames().contains(varCanonicalName)).collect(Collectors.toList());
            if(membersWithSuperTypeMatchingName.size() == 1) {
                return membersWithSuperTypeMatchingName.get(0);
            }
            if(membersWithSuperTypeMatchingName.isEmpty()) {
                return null;
            }

            // Multiple members with supertypes.
            return findBestMatch(membersWithSuperTypeMatchingName, variable);
        }

        return findBestMatch(membersWithTypeMatchingName, variable);
    }

    @NotNull
    private MemberField findBestMatch(
            final List<MemberField> membersWithSuperTypeMatchingName, final Api.Variable variable) {
        // Look for fields with named annotations; e.g. @Mock(name = variable.declaredName).
        final List<MemberField> fieldsWithAnnotationNameMatchingDeclaredName = membersWithSuperTypeMatchingName.stream().filter(x -> x.hasAnnotationWithNameParameter(variable.getDeclaredName())).collect(Collectors.toList());
        if(!fieldsWithAnnotationNameMatchingDeclaredName.isEmpty()) {
            return fieldsWithAnnotationNameMatchingDeclaredName.get(0);
        }

        // There are no named annotations.
        final Optional<MemberField> fieldMatchingDeclaredName =
                membersWithSuperTypeMatchingName.stream().filter(x -> x.name().equals(variable.getDeclaredName())
                        || x.name().equals("mock" + StringUtils.capitalize(variable.getDeclaredNameWithoutPrefix()))).findFirst();

        return fieldMatchingDeclaredName.orElseGet(() -> membersWithSuperTypeMatchingName.get(0));
    }
}
