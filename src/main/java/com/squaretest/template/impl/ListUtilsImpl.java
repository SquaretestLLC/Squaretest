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
import com.squaretest.template.api.Api.ListUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Equator;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static com.squaretest.template.impl.ListHelpers.getAttributeValue;
import static com.squaretest.template.impl.ListHelpers.safeGetAttributeValue;

public class ListUtilsImpl implements ListUtils {

    @SuppressWarnings("rawtypes")
    @Override
    public Api.FluentList<?> nullToEmpty(final List<?> theList) {
        if(theList == null) {
            return Api.FluentListFactory.emptyList();
        }
        if(theList instanceof FluentListImpl) {
            return (Api.FluentList) theList;
        }
        return new FluentListImpl<>(theList);
    }

    @Override
    public <T> Api.FluentList<T> itemsBefore(final List<T> theList, final Object obj) {
        if(theList == null) {
            return Api.FluentListFactory.emptyList();
        }
        final int index = theList.indexOf(obj);
        if(index == -1) {
            return Api.FluentListFactory.emptyList();
        }
        return new FluentListImpl<>(new ArrayList<>(theList.subList(0, index)));
    }

    @Override
    public <T> Api.FluentList<T> itemsAfter(final List<T> theList, final Object obj) {
        if(theList == null) {
            return Api.FluentListFactory.emptyList();
        }
        final int index = theList.indexOf(obj);
        if(index == -1) {
            return Api.FluentListFactory.emptyList();
        }
        return new FluentListImpl<>(new ArrayList<>(theList.subList(index + 1, theList.size())));
    }

    @Override
    public <T> boolean containsAnyWith(
            final List<T> theList, final String attributeNameExpression, Object... searchValues) throws InvocationTargetException, IllegalAccessException {
        if(searchValues == null) {
            searchValues = new Object[]{null};
        }
        return indexOfAny(theList, attributeNameExpression, searchValues) != -1;
    }

    @Override
    public <T> boolean containsAny(
            final List<T> theList, final String attributeNameExpression,
            final Object... searchValues) throws InvocationTargetException, IllegalAccessException {
        return containsAnyWith(theList, attributeNameExpression, searchValues);
    }

    @Override
    public <T> boolean containsAnyWithPrefix(
            final List<T> theList, final String attributeNameExpression,
            final String... prefixes) throws InvocationTargetException, IllegalAccessException {
        for(final T obj : theList) {
            final Object attributeValue = getAttributeValue(obj, attributeNameExpression);
            if(attributeValue instanceof final String attrValueStr) {
                if(StringUtils.startsWithAny(attrValueStr, prefixes)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public <T> boolean containsAnyWithPrefix(
            final List<T> theList, final String attributeNameExpression, final List<String> prefixes) throws InvocationTargetException, IllegalAccessException {
        if(attributeNameExpression == null || prefixes == null) {
            return false;
        }
        return containsAnyWithPrefix(theList, attributeNameExpression, prefixes.toArray(new String[]{}));
    }

    @Override
    public <T> boolean containsAnyWithRegex(
            final List<T> theList, final String attributeNameExpression,
            final String regex) throws InvocationTargetException, IllegalAccessException {
        for(final T obj : theList) {
            final Object attributeValue = getAttributeValue(obj, attributeNameExpression);
            if(attributeValue instanceof final String attrValueStr) {
                if(attrValueStr.matches(regex)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public <T> boolean contains(final List<T> theList, final String attributeNameExpression, final Object searchValue) throws InvocationTargetException, IllegalAccessException {
        return indexOfAny(theList, attributeNameExpression, searchValue) != -1;
    }

    @Override
    public <T> int indexOfAny(final List<T> theList, final String attributeNameExpression, final Object... searchValues) throws InvocationTargetException, IllegalAccessException {
        final List<Object> searchValuesInList = searchValues == null ? Collections.singletonList(null) : Arrays.asList(searchValues);
        int i = -1;
        for(final T obj : theList) {
            i++;
            final Object actualValue = getAttributeValue(obj, attributeNameExpression);
            if(searchValuesInList.contains(actualValue)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Api.FluentList<?> map(final List<?> theList, final String attributeNameExpression) throws InvocationTargetException, IllegalAccessException {
        final Api.FluentList<Object> ret = new FluentListImpl<>();
        for(final Object obj : theList) {
            final Object objAttributeValue = getAttributeValue(obj, attributeNameExpression);
            ret.add(objAttributeValue);
        }
        return ret;
    }

    @Override
    public Api.FluentList<?> flatMap(final List<?> theList, final String attributeNameExpression) throws InvocationTargetException, IllegalAccessException {
        final Api.FluentList<Object> ret = new FluentListImpl<>();
        for(final Object obj : theList) {
            final Object objAttributeValue = getAttributeValue(obj, attributeNameExpression);
            if(objAttributeValue instanceof final Collection<?> collection) {
                ret.addAll(collection);
            }
        }
        return ret;
    }

    @Override
    public <T> Api.FluentList<T> filter(final List<T> theList, final String attributeNameExpression, Object... attributeValues) throws InvocationTargetException, IllegalAccessException {
        // If this is called with filter(theList, "type.canonicalName", null), attributeValues will be null.
        // We don't want that. We want an Object[] with null in it.
        if(attributeValues == null) {
            attributeValues = new Object[]{null};
        }
        final FluentListImpl<T> ret = new FluentListImpl<>();
        for(final T obj : theList) {
            final Object objAttributeValue = getAttributeValue(obj, attributeNameExpression);
            if(ListHelpers.containsAnyImpl(objAttributeValue, attributeValues)) {
                ret.add(obj);
            }
        }
        return ret;
    }

    @Override
    public <T> Api.FluentList<T> filterItemsWithPrefix(
            final List<T> theList, final String attributeNameExpression, final String... prefixes) throws InvocationTargetException, IllegalAccessException {
        final FluentListImpl<T> ret = new FluentListImpl<>();
        for(final T obj : theList) {
            final Object attributeValue = getAttributeValue(obj, attributeNameExpression);
            if(attributeValue instanceof final String attrValueStr) {
                if(StringUtils.startsWithAny(attrValueStr, prefixes)) {
                    ret.add(obj);
                }
            }
        }
        return ret;
    }

    @Override
    public <T> Api.FluentList<T> filterItemsWithPrefix(
            final List<T> theList, final String attributeNameExpression, final List<String> prefixes) throws InvocationTargetException, IllegalAccessException {
        if(attributeNameExpression == null || prefixes == null) {
            return new FluentListImpl<>();
        }
        return filterItemsWithPrefix(theList, attributeNameExpression, prefixes.toArray(new String[]{}));
    }

    @Override
    public <T> Api.FluentList<T> filterItemsWithRegex(
            final List<T> theList, final String attributeNameExpression,
            final String regex) throws InvocationTargetException, IllegalAccessException {
        final FluentListImpl<T> ret = new FluentListImpl<>();
        for(final T obj : theList) {
            final Object attributeValue = getAttributeValue(obj, attributeNameExpression);
            if(attributeValue instanceof final String attrValueStr) {
                if(attrValueStr.matches(regex)) {
                    ret.add(obj);
                }
            }
        }
        return ret;
    }

    @Override
    public <T> Api.FluentList<T> filterOut(final List<T> theList, final String attributeExpression, Object... attributeValues) throws InvocationTargetException, IllegalAccessException {
        if(attributeValues == null) {
            attributeValues = new Object[]{null};
        }
        final FluentListImpl<T> ret = new FluentListImpl<>();
        for(final T obj : theList) {
            final Object actualValue = getAttributeValue(obj, attributeExpression);
            if(!ListHelpers.containsAnyImpl(actualValue, attributeValues)) {
                ret.add(obj);
            }
        }
        return ret;
    }

    @Override
    public <T> Api.FluentList<T> filterOut(
            final List<T> theList,
            final String attributeNameExpression) throws InvocationTargetException, IllegalAccessException {
        return filterOut(theList, attributeNameExpression, true);
    }

    @Override
    public <T> Api.FluentList<T> filterOutItem(final List<T> theList, final Object valueToRemove) {
        final FluentListImpl<T> ret = new FluentListImpl<>();
        for(final T item : theList) {
            if(!Objects.equals(item, valueToRemove)) {
                ret.add(item);
            }
        }
        return ret;
    }

    @Override
    public <T> Api.FluentList<T> filterOutItemsWithPrefix(
            final List<T> theList, final String attributeNameExpression, final String... prefixes) throws InvocationTargetException, IllegalAccessException {
        final FluentListImpl<T> ret = new FluentListImpl<>();
        for(final T obj : theList) {
            final Object attributeValue = getAttributeValue(obj, attributeNameExpression);
            if(attributeValue instanceof final String attrValueStr) {
                if(StringUtils.startsWithAny(attrValueStr, prefixes)) {
                    continue;
                }
            }
            ret.add(obj);
        }
        return ret;
    }

    @Override
    public <T> Api.FluentList<T> filterOutItemsWithPrefix(
            final List<T> theList, final String attributeExpression, final List<String> prefixes) throws InvocationTargetException, IllegalAccessException {
        if(prefixes == null) {
            // We need to return a new list with the same items as the old list.
            return new FluentListImpl<>(new ArrayList<>(theList));
        }
        return filterOutItemsWithPrefix(theList, attributeExpression, prefixes.toArray(new String[]{}));
    }

    @Override
    public <T> Api.FluentList<T> filterOutItemsWithRegex(
            final List<T> theList, final String attributeNameExpression,
            final String regex) throws InvocationTargetException, IllegalAccessException {
        final FluentListImpl<T> ret = new FluentListImpl<>();
        for(final T obj : theList) {
            final Object attributeValue = getAttributeValue(obj, attributeNameExpression);
            if(attributeValue instanceof final String attrValueStr) {
                if(attrValueStr.matches(regex)) {
                    continue;
                }
            }
            ret.add(obj);
        }
        return ret;
    }

    @Override
    public <T extends Object & Comparable<? super T>> T min(final List<? extends T> theList) {
        if(theList == null || theList.isEmpty()) {
            return null;
        }
        return Collections.min(theList);
    }

    @Override
    public <T extends Object & Comparable<? super T>> T max(final List<? extends T> theList) {
        if(theList == null || theList.isEmpty()) {
            return null;
        }
        return Collections.max(theList);
    }

    @Override
    public <T> Api.FluentList<T> intersection(final Iterable<? extends T> list1, final Iterable<? extends T> list2) {
        final Collection<T> ret = CollectionUtils.intersection(list1, list2);
        return new FluentListImpl<>(new ArrayList<>(ret));
    }

    @Override
    public <T> Api.FluentList<? extends T> union(final Iterable<? extends T> list1, final Iterable<? extends T> list2) {
        if(list1 == null) {
            if(list2 == null) {
                return Api.FluentListFactory.emptyList();
            } else {
                return unionImpl(list2, Collections.emptyList(), new ListHelpers.ObjectEqualsEquator<>());
            }
        }
        if(list2 == null) {
            return unionImpl(list1, Collections.emptyList(), new ListHelpers.ObjectEqualsEquator<>());
        }
        return unionImpl(list1, list2, new ListHelpers.ObjectEqualsEquator<>());
    }

    @Override
    public <T> Api.FluentList<? extends T> union(final Iterable<? extends T> list1, final Iterable<? extends T> list2, final String attributeNameExpression) {
        if(attributeNameExpression == null) {
            throw new NullPointerException("ListUtils.union(...) called with attributeNameExpression = null");
        }
        Equator<T> attributeEquator = new Equator<>() {
            @Override
            public boolean equate(T o1, T o2) {
                if(o1 == o2) {
                    return true;
                }
                if(o1 == null || o2 == null) {
                    return false;
                }
                final Object attributeValue1 = safeGetAttributeValue(o1, attributeNameExpression);
                final Object attributeValue2 = safeGetAttributeValue(o2, attributeNameExpression);
                return Objects.equals(attributeValue1, attributeValue2);
            }

            @Override
            public int hash(T o) {
                if(o == null) {
                    return 0;
                }
                final Object attributeValue = safeGetAttributeValue(o, attributeNameExpression);
                if(attributeValue == null) {
                    return 0;
                }
                return attributeValue.hashCode();
            }
        };

        if(list1 == null) {
            if(list2 == null) {
                return Api.FluentListFactory.emptyList();
            } else {
                return unionImpl(list2, Collections.emptyList(), attributeEquator);
            }
        }
        if(list2 == null) {
            return unionImpl(list1, Collections.emptyList(), attributeEquator);
        }


        return unionImpl(list1, list2, attributeEquator);
    }

    @Override
    public <T> Api.FluentList<T> filterItemsWithAnnotation(final List<T> theList, final String... annotations) {
        final Api.FluentList<T> ret = new FluentListImpl<>();
        for(final T item : theList) {
            if(ListHelpers.hasAnnotation(item, annotations)) {
                ret.add(item);
            }
        }
        return ret;
    }

    @Override
    public <T> Api.FluentList<T> filterItemsWithAnnotation(final List<T> theList, final List<String> annotations) {
        if(annotations == null) {
            return Api.FluentListFactory.emptyList();
        }
        return filterItemsWithAnnotation(theList, annotations.toArray(new String[]{}));
    }

    @Override
    public <T> Api.FluentList<T> filterOutItemsWithAnnotation(final List<T> theList, final String... annotations) {
        final Api.FluentList<T> ret = new FluentListImpl<>();
        for(final T item : theList) {
            if(!ListHelpers.hasAnnotation(item, annotations)) {
                ret.add(item);
            }
        }
        return ret;
    }

    @Override
    public <T> Api.FluentList<T> filterOutItemsWithAnnotation(final List<T> theList, final List<String> annotations) {
        if(annotations == null) {
            return new FluentListImpl<>(new ArrayList<>(theList));
        }
        return filterOutItemsWithAnnotation(theList, annotations.toArray(new String[]{}));
    }

    @Override
    public <T> Api.FluentList<T> filterItemsWithAnnotationPrefix(final List<T> theList, final String... prefixes) {
        final Api.FluentList<T> ret = new FluentListImpl<>();
        for(final T item : theList) {
            if(ListHelpers.hasAnnotationPrefix(item, prefixes)) {
                ret.add(item);
            }
        }
        return ret;
    }

    @Override
    public <T> Api.FluentList<T> filterItemsWithAnnotationPrefix(final List<T> theList, final List<String> prefixes) {
        if(prefixes == null) {
            return Api.FluentListFactory.emptyList();
        }
        return filterItemsWithAnnotationPrefix(theList, prefixes.toArray(new String[]{}));
    }

    @Override
    public <T> Api.FluentList<T> filterOutItemsWithAnnotationPrefix(final List<T> theList, final String... prefixes) {
        final Api.FluentList<T> ret = new FluentListImpl<>();
        for(final T item : theList) {
            if(!ListHelpers.hasAnnotationPrefix(item, prefixes)) {
                ret.add(item);
            }
        }
        return ret;
    }

    @Override
    public <T> Api.FluentList<T> filterOutItemsWithAnnotationPrefix(final List<T> theList, final List<String> prefixes) {
        if(prefixes == null) {
            return new FluentListImpl<>(new ArrayList<>(theList));
        }
        return filterOutItemsWithAnnotationPrefix(theList, prefixes.toArray(new String[]{}));
    }

    @Override
    public <T> boolean containsAnyWithAnnotation(final List<T> theList, final String... annotations) {
        for(final T item : theList) {
            if(ListHelpers.hasAnnotation(item, annotations)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public <T> boolean containsAnyWithAnnotation(final List<T> theList, final List<String> annotations) {
        if(annotations == null) {
            return false;
        }
        return containsAnyWithAnnotation(theList, annotations.toArray(new String[]{}));
    }

    @Override
    public <T> boolean containsAnyWithAnnotationPrefix(final List<T> theList, final String... annotations) {
        for(final T item : theList) {
            if(ListHelpers.hasAnnotationPrefix(item, annotations)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public <T> boolean containsAnyWithAnnotationPrefix(final List<T> theList, final List<String> annotations) {
        if(annotations == null) {
            return false;
        }
        return containsAnyWithAnnotationPrefix(theList, annotations.toArray(new String[]{}));
    }

    public static <T> Api.FluentList<? extends T> unionImpl(@NotNull Iterable<? extends T> list1, @NotNull Iterable<? extends T> list2, @NotNull final Equator<T> equator) {
        final Api.FluentList<T> ret = new FluentListImpl<>();
        for(final T obj : list1) {
            if(!ListHelpers.contains(ret, obj, equator)) {
                ret.add(obj);
            }
        }
        for(final T obj : list2) {
            if(!ListHelpers.contains(ret, obj, equator)) {
                ret.add(obj);
            }
        }
        return ret;
    }
}
