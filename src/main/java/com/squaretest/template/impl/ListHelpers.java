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

import com.squaretest.template.api.Api;
import org.apache.commons.collections4.Equator;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class ListHelpers {
    public static <T> Object safeGetAttributeValue(final T obj, final String property) {
        try {
            return getAttributeValue(obj, property);
        } catch(InvocationTargetException | IllegalAccessException e) {
            return null;
        }
    }

    public static <T> boolean contains(final List<? extends T> list, final T obj, final Equator<T> equator) {
        for(final T item : list) {
            if(equator.equate(item, obj)) {
                return true;
            }
        }
        return false;
    }

    public static Object getAttributeValue(final Object obj, final String attributeNameExpression) throws InvocationTargetException, IllegalAccessException {
        if(obj == null) {
            return null;
        }
        final String[] attributeNames = attributeNameExpression.split(Pattern.quote("."));
        Object currentObject = obj;
        for(final String attributeName : attributeNames) {
            final Method objAttributeGetter = findGetterMatchingAttribute(currentObject, attributeName);
            if(objAttributeGetter == null) {
                return null;
            }

            currentObject = objAttributeGetter.invoke(currentObject);
            if(currentObject == null) {
                return null;
            }
        }
        return currentObject;
    }

    @Nullable
    public static Method findGetterMatchingAttribute(final Object obj, final String attributeName) {
        final String getterName = "get" + StringUtils.capitalize(attributeName);
        Method method = tryGetMethod(obj, getterName);
        if(method != null) {
            return method;
        }

        final String isGetterName = "is" + StringUtils.capitalize(attributeName);
        method = tryGetMethod(obj, isGetterName);
        return method;
    }

    @Nullable
    public static Method tryGetMethod(final Object obj, final String methodName) {
        try {
            return obj.getClass().getMethod(methodName);
        } catch(final NoSuchMethodException e) {
            return null;
        }
    }

    public static boolean containsAnyImpl(final Object valueToSearchFor, final Object[] values) {
        for(final Object value : values) {
            if(Objects.equals(value, valueToSearchFor)) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsAnyImpl(final Object valueToSearchFor, final List<?> values) {
        for(final Object value : values) {
            if(Objects.equals(value, valueToSearchFor)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasAnnotation(final Object obj, final String[] annotations) {
        if(obj instanceof final Api.AnnotationHolder annotationHolderItem) {
            return annotationHolderItem.hasAnnotation(annotations);
        }
        return false;
    }

    public static boolean hasAnnotationPrefix(Object obj, String[] prefixes) {
        if(obj instanceof final Api.AnnotationHolder annotationHolderItem) {
            return annotationHolderItem.hasAnnotationWithPrefix(prefixes);
        }
        return false;
    }

    public static class ObjectEqualsEquator<T> implements Equator<T> {
        @Override
        public boolean equate(T o1, T o2) {
            return Objects.equals(o1, o2);
        }

        @Override
        public int hash(T o) {
            return o.hashCode();
        }
    }
}
