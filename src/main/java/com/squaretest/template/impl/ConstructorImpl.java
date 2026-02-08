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
package com.squaretest.template.impl;

import com.squaretest.generation.NullabilityStatus;
import com.squaretest.template.AccessModifier;
import com.squaretest.template.api.Api;
import com.squaretest.template.api.Api.Constructor;
import com.squaretest.template.api.Api.Variable;
import org.jetbrains.annotations.NotNull;

public class ConstructorImpl extends MethodImpl implements Constructor {

    public ConstructorImpl(
            @NotNull final String name, @NotNull final Api.FluentList<Variable> parameters,
            @NotNull final AccessModifier accessModifier, final Api.Type returnType, final Api.FluentList<Api.Annotation> annotations,
            final boolean isVisibleToTestClass, final String methodKey, final boolean isOverload, final boolean isDeprecated, final boolean isStatic, final boolean isAbstract,
            final boolean isWritable, final boolean isNative, final boolean isGetter, final boolean isSetter, final boolean isInMainSourceClass, final int overloadNumber, final int indexInSourceClass, final boolean calledInSource,
            final boolean propertyIsUsedInSource, final boolean calledInStaticHelpers, final boolean propertyIsUsedInStaticHelpers, final boolean doesNothing, final boolean isLombokSuperBuilderConstructor) {
        super(name, parameters, accessModifier, returnType, isVisibleToTestClass, Api.FluentListFactory.emptyList(), Api.FluentListFactory.emptyList(), Api.FluentListFactory.emptyList(), Api.FluentListFactory.emptyList(), annotations, null, SimpleExitInfoImpl.EmptyInfo, methodKey, NullabilityStatus.NOT_NULL, isOverload,
                isDeprecated, isStatic, isAbstract, isWritable, isNative, isGetter, isSetter,
                true, isInMainSourceClass, overloadNumber, false, false, indexInSourceClass, false, calledInSource, propertyIsUsedInSource, calledInStaticHelpers, propertyIsUsedInStaticHelpers, doesNothing, isLombokSuperBuilderConstructor, true);
    }

    @Override
    public int compareTo(@NotNull final Constructor other) {
        if(this.getParameters().size() < other.getParameters().size()) {
            return -1;
        } else if(this.getParameters().size() == other.getParameters().size()) {
            return 0;
        } else {
            return 1;
        }
    }
}
