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
package com.squaretest.generation.setters;

import com.squaretest.generation.defaulttypes.DefaultTypeInfo;
import com.squaretest.generation.defaulttypes.DefaultTypesHolder;
import com.squaretest.template.api.Api;
import com.squaretest.template.impl.FluentListImpl;
import com.squaretest.template.impl.TypeImpl;
import com.squaretest.utils.SetterMethodSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DefaultInitSetterDecider {

    public static final Set<String> PrioritySetterNames = new HashSet<>(Arrays.asList(
            "setPayload",
            "set_payload",
            "payload",
            "setBody",
            "set_body",
            "body",
            "setResponseBody",
            "set_response_body",
            "responseBody",
            "setData",
            "set_Data",
            "data"
    ));
    private static final int BoundaryForPriorityInitSetters = 4;
    private static final int IndexToAddPriorityInitSetter = 3;

    @NotNull
    private final DefaultTypesHolder defaultTypesHolder;

    public DefaultInitSetterDecider() {
        defaultTypesHolder = DefaultTypesHolder.getInstance();
    }

    public Api.FluentList<Api.Method> determineInitSetters(final String sourceClassCanonicalName, final Api.FluentList<Api.Method> methods) {
        final List<String> initSettersFromConfig = getInitSetterNamesFromConfig(sourceClassCanonicalName);
        if(initSettersFromConfig != null) {
            return determineInitSettersFromConfig(initSettersFromConfig, methods);
        }
        return determineInitSettersNormally(methods);
    }

    private Api.FluentList<Api.Method> determineInitSettersFromConfig(final List<String> initSettersFromConfig, final Api.FluentList<Api.Method> methods) {
        final Api.FluentList<Api.Method> ret = new FluentListImpl<>(initSettersFromConfig.size());
        for(final String setterKey : initSettersFromConfig) {
            final Api.Method method = findMethodWithKey(setterKey, methods);
            if(method != null) {
                ret.add(method);
            }
        }
        return ret;
    }

    @Nullable
    private Api.Method findMethodWithKey(final String setterKey, final Api.FluentList<Api.Method> methods) {
        return methods.stream().filter(x -> setterKey.equals(getMethodKey(x))).findFirst().orElse(null);
    }

    private String getMethodKey(final Api.Method method) {
        // TODO: Replace with method.getMethodKey() ?
        return method.getName() + "_" + method.getParameters().stream().map(x -> x.getType().getName()).collect(Collectors.joining("_"));
    }

    @Nullable
    private List<String> getInitSetterNamesFromConfig(final String sourceClassCanonicalName) {
        final DefaultTypeInfo defaultTypeInfo = defaultTypesHolder.get(sourceClassCanonicalName);
        if(defaultTypeInfo == null) {
            return null;
        }
        final List<String> initSetterKeys = defaultTypeInfo.getInitSetterKeys();
        if(initSetterKeys == null) {
            return Collections.emptyList();
        }
        return initSetterKeys;
    }

    @NotNull
    private Api.FluentList<Api.Method> determineInitSettersNormally(final Api.FluentList<Api.Method> methods) {
        // Store the setter methods. Preserve the order they're returned in, as this is the order in which they appear in the
        // source class.
        final List<Api.Method> setters = methods.stream().filter(x -> x.isSetter() && !x.isDeprecated()).toList();

        // We may have multiple overloads for a one or more setters.
        // Keep track of the best overload to use in a map of MethodName -> Method.
        final SetterMethodSet setterMethodSet = new SetterMethodSet();
        for(final Api.Method setter : setters) {
            if(setterMethodSet.contains(setter)) {
                final Api.Method preferredSetter = getPreferredSetter(setter, setterMethodSet.getMethodWithSameNameOrField(setter));
                setterMethodSet.add(preferredSetter);
            } else {
                setterMethodSet.add(setter);
            }
        }

        final Api.FluentList<Api.Method> initSettersToUse = new FluentListImpl<>();
        for(final Api.Method setter : setters) {
            if(setterMethodSet.getMethodWithSameNameOrField(setter) == setter) {
                initSettersToUse.add(setter);
            }
        }

        // If we have a priority init setter, and its index is greater than BoundaryForPriorityInitSetters,
        // move it closer to the start of the list. This will ensure the template uses it.
        final int indexOfPrioritySetter = indexOfFirstPrioritySetter(initSettersToUse);
        if(indexOfPrioritySetter > BoundaryForPriorityInitSetters) {
            final Api.Method prioritySetter = initSettersToUse.remove(indexOfPrioritySetter);
            final int indexToInsert = Math.min(IndexToAddPriorityInitSetter, initSettersToUse.size());
            initSettersToUse.add(indexToInsert, prioritySetter);
        }

        // Determine if we have a setter that uses a type parameter.
        final int indexOfSetterWithTypeParam = indexOfSetterWithTypeParam(initSettersToUse);
        if(indexOfSetterWithTypeParam > BoundaryForPriorityInitSetters) {
            final Api.Method prioritySetter = initSettersToUse.remove(indexOfSetterWithTypeParam);
            final int indexToInsert = Math.min(IndexToAddPriorityInitSetter, initSettersToUse.size());
            initSettersToUse.add(indexToInsert, prioritySetter);
        }

        return initSettersToUse;
    }

    private static int indexOfFirstPrioritySetter(final Api.FluentList<Api.Method> initSettersToUse) {
        for(int i = 0; i < initSettersToUse.size(); i++) {
            final Api.Method method = initSettersToUse.get(i);
            if(PrioritySetterNames.contains(method.getName())) {
                return i;
            }
        }
        return -1;
    }

    private static int indexOfSetterWithTypeParam(final Api.FluentList<Api.Method> initSettersToUse) {
        for(int i = 0; i < initSettersToUse.size(); i++) {
            final Api.Method method = initSettersToUse.get(i);
            final Api.Variable setterParam = method.getParameters().first();
            if(setterParam == null) {
                // This shouldn't happen.
                continue;
            }
            if(setterParam.getType().isGeneric()) {
                return i;
            }
        }
        return -1;
    }

    private static Api.Method getPreferredSetter(final Api.Method setter, final Api.Method otherSetter) {
        if(allParamsRecognizedTypes(setter)) {
            return setter;
        }
        if(allParamsRecognizedTypes(otherSetter)) {
            return otherSetter;
        }
        return setter;
    }

    public static boolean allParamsRecognizedTypes(final Api.Method setter) {
        return setter.getParameters().stream().allMatch(x -> x.getType().isRecognized());
    }
}
