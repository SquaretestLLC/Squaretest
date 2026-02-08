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
package com.squaretest.annotations;

import com.intellij.psi.PsiElement;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.yaml.YAMLUtil;
import org.jetbrains.yaml.psi.YAMLKeyValue;
import org.jetbrains.yaml.psi.YAMLScalar;
import org.jetbrains.yaml.psi.YAMLValue;

import java.util.Map;

public class YAMLPropertyResolver implements IPropertyResolver {
    @Nullable
    @Override
    public Map.Entry<String, String> getKeyValuePair(final PsiElement resolveResultElement) {
        if(!(resolveResultElement instanceof final YAMLKeyValue property)) {
            return null;
        }
        final String propertyKey = YAMLUtil.getConfigFullName(property);
        if(StringUtils.isBlank(propertyKey)) {
            return null;
        }
        final YAMLValue value = property.getValue();
        if(!(value instanceof YAMLScalar)) {
            return null;
        }
        final String propertyValue = ((YAMLScalar) value).getTextValue();
        return Pair.of(propertyKey, propertyValue);
    }
}
