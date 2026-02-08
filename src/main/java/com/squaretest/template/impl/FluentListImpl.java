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
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Equator;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static com.squaretest.template.impl.ListHelpers.*;

public class FluentListImpl<T> extends ArrayList<T> implements Api.FluentList<T> {

    private final Map<Object, Object> props = new LinkedHashMap<>();

    public FluentListImpl() {
        super();
    }

    public FluentListImpl(int capacity) {
        super(capacity);
    }

    public FluentListImpl(final List<T> delegateList) {
        super(delegateList);
    }

    @Override
    public Api.FluentList<T> newList() {
        return new FluentListImpl<>();
    }

    @Override
    @Nullable
    public T getFirst() {
        return first();
    }

    @Override
    @Nullable
    public T getSecond() {
        return second();
    }

    @Override
    @Nullable
    public T getThird() {
        return third();
    }

    @Override
    @Nullable
    public T getFourth() {
        return fourth();
    }

    @Override
    @Nullable
    public T getFifth() {
        return fifth();
    }

    @Override
    @Nullable
    public T first() {
        return getOrNull(0);
    }

    @Override
    @Nullable
    public T second() {
        return getOrNull(1);
    }

    @Override
    @Nullable
    public T third() {
        return getOrNull(2);
    }

    @Override
    @Nullable
    public T fourth() {
        return getOrNull(3);
    }

    @Override
    @Nullable
    public T fifth() {
        return getOrNull(4);
    }

    @Override
    @Nullable
    public T getLast() {
        return getOrNull(size() - 1);
    }

    @Nullable
    @Override
    public T getOrNull(final int index) {
        if(-1 < index && index < size()) {
            return get(index);
        }
        return null;
    }

    @Override
    public Api.FluentList<T> itemsBefore(final Object obj) {
        final Api.FluentList<T> ret = newList();
        final int index = this.indexOf(obj);
        if(index == -1) {
            return ret;
        }
        ret.addAll(super.subList(0, index));
        return ret;
    }

    @Override
    public Api.FluentList<T> itemsAfter(final Object obj) {
        final Api.FluentList<T> ret = newList();
        final int index = this.indexOf(obj);
        if(index == -1) {
            return ret;
        }
        ret.addAll(super.subList(index + 1, this.size()));
        return ret;
    }

    @Override
    public Api.FluentList<T> limit(final int limit) {
        final Api.FluentList<T> ret = newList();
        for(int i = 0; i < limit && i < size(); i++) {
            ret.add(get(i));
        }
        return ret;
    }

    @Override
    public String join(String delimiter) {
        if(delimiter == null) {
            delimiter = ", ";
        }
        final StringBuilder ret = new StringBuilder();
        for(int i = 0; i < size(); i++) {
            final T obj = this.get(i);
            ret.append(obj);
            if(i != size() - 1) {
                ret.append(delimiter);
            }
        }
        return ret.toString();
    }

    @Override
    public boolean containsAny(final List<?> searchItems) {
        if(searchItems == null) {
            return false;
        }
        for(final Object searchItem : searchItems) {
            if(contains(searchItem)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAnyWith(final String attributeNameExpression, Object... searchValues) throws InvocationTargetException, IllegalAccessException {
        if(searchValues == null) {
            searchValues = new Object[]{null};
        }
        return indexOfAny(attributeNameExpression, searchValues) != -1;
    }

    @Override
    public boolean containsAnyWithItem(final String attributeNameExpression, List<?> searchValues) throws InvocationTargetException, IllegalAccessException {
        if(searchValues == null) {
            searchValues = Collections.emptyList();
        }
        return indexOfAny(attributeNameExpression, searchValues.toArray()) != -1;
    }

    @Override
    public boolean containsAnyWithNonNull(final String attributeNameExpression) throws InvocationTargetException, IllegalAccessException {
        return indexOfFirstNonNull(attributeNameExpression) != -1;
    }

    private int indexOfFirstNonNull(final String attributeNameExpression) throws InvocationTargetException, IllegalAccessException {
        int i = -1;
        for(final T obj : this) {
            i++;
            final Object actualValue = ListHelpers.getAttributeValue(obj, attributeNameExpression);
            if(actualValue != null) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean containsAnyWithPrefix(
            final String attributeNameExpression,
            final String... prefixes) throws InvocationTargetException, IllegalAccessException {
        for(final T obj : this) {
            final Object attributeValue = ListHelpers.getAttributeValue(obj, attributeNameExpression);
            if(attributeValue instanceof final String attrValueStr) {
                if(StringUtils.startsWithAny(attrValueStr, prefixes)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsAnyWithPrefix(
            final String attributeNameExpression,
            final List<String> prefixes) throws InvocationTargetException, IllegalAccessException {
        if(attributeNameExpression == null || prefixes == null) {
            return false;
        }
        return containsAnyWithPrefix(attributeNameExpression, prefixes.toArray(new String[]{}));
    }

    @Override
    public boolean containsAnyWithRegex(
            final String attributeNameExpression,
            final String regex) throws InvocationTargetException, IllegalAccessException {
        for(final T obj : this) {
            final Object attributeValue = ListHelpers.getAttributeValue(obj, attributeNameExpression);
            if(attributeValue instanceof final String attrValueStr) {
                if(attrValueStr.matches(regex)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Api.FluentList<T> concat(final Collection<T> listToAppend) {
        final Api.FluentList<T> newList = newList();
        newList.addAll(this);
        newList.addAll(listToAppend);
        return newList;
    }

    @Override
    public Api.FluentList<T> concat(T itemToAppend) {
        final Api.FluentList<T> newList = newList();
        newList.addAll(this);
        newList.add(itemToAppend);
        return newList;
    }

    @Override
    public int indexOfAny(final String attributeNameExpression, final Object... searchValues) throws InvocationTargetException, IllegalAccessException {
        final List<Object> searchValuesInList = searchValues == null ? Collections.singletonList(null) : Arrays.asList(searchValues);
        int i = -1;
        for(final T obj : this) {
            i++;
            final Object actualValue = ListHelpers.getAttributeValue(obj, attributeNameExpression);
            if(searchValuesInList.contains(actualValue)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Api.FluentList<?> map(final String attributeNameExpression) throws InvocationTargetException, IllegalAccessException {
        final Api.FluentList<Object> ret = new FluentListImpl<>();
        for(final Object obj : this) {
            final Object objAttributeValue = ListHelpers.getAttributeValue(obj, attributeNameExpression);
            ret.add(objAttributeValue);
        }
        return ret;
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public Api.FluentList<?> flatMap(final String attributeNameExpression) throws InvocationTargetException, IllegalAccessException {
        final Api.FluentList<?> ret = new FluentListImpl<>();
        for(final Object obj : this) {
            final Object objAttributeValue = ListHelpers.getAttributeValue(obj, attributeNameExpression);
            if(objAttributeValue instanceof final Collection collection) {
                ret.addAll(collection);
            }
        }
        return ret;
    }

    @Override
    public Api.FluentList<Api.DependencyInteraction> satisfiedBy(
            final List<? extends Api.Variable> dependencies) {
        if(dependencies == null || dependencies.isEmpty()) {
            return new FluentListImpl<>(0);
        }
        // Avoid adding duplicate (sourceVar, methodKey) tuples.
        final Set<Pair<Api.Variable, String>> sourceVarMethodKeySet = new HashSet<>();
        final Api.FluentList<Api.DependencyInteraction> ret = new FluentListImpl<>();
        for(final T obj : this) {
            if(obj instanceof final Api.DependencyInteraction interaction) {
                Api.FluentList<? extends Api.Variable> sourceVars = interaction.getField().getPossibleSourceVariables().intersection(dependencies);
                if(!sourceVars.isEmpty()) {
                    // The field in this di was set to one of the provided dependencies.
                    // Check to see if we've seen this tuple before.
                    final Api.Variable firstSourceVar = sourceVars.get(0);
                    final Set<Pair<Api.Variable, String>> newSourceVarMethodKeyPairs = getPairs(firstSourceVar, interaction.getMethod());
                    if(Collections.disjoint(sourceVarMethodKeySet, newSourceVarMethodKeyPairs)) {
                        ret.add(interaction);
                    }
                    sourceVarMethodKeySet.addAll(newSourceVarMethodKeyPairs);
                }
            }
        }
        return ret;
    }

    private Set<Pair<Api.Variable, String>> getPairs(final Api.Variable firstSourceVar, final Api.Method method) {
        final Api.FluentList<Api.Method> superMethods = method.getSuperMethods();
        if(superMethods.isEmpty()) {
            return Collections.singleton(Pair.of(firstSourceVar, method.getMethodKey()));
        }
        final Set<Pair<Api.Variable, String>> ret = new HashSet<>();
        ret.add(Pair.of(firstSourceVar, method.getMethodKey()));
        for(final Api.Method superMethod : superMethods) {
            ret.add(Pair.of(firstSourceVar, superMethod.getMethodKey()));
        }
        return ret;
    }

    @Override
    public Api.FluentList<Api.DependencyInteraction> filterItemsWithSameSourceVar(final Api.DependencyInteraction dependencyInteraction) {
        final Api.FluentList<Api.DependencyInteraction> ret = new FluentListImpl<>();
        if(dependencyInteraction == null) {
            return ret;
        }
        final Api.FluentList<Api.Variable> sourceVarsToSearchFor = dependencyInteraction.getField().getPossibleSourceVariables();
        for(final T obj : this) {
            if(!(obj instanceof final Api.DependencyInteraction di)) {
                continue;
            }
            if(!Collections.disjoint(di.getField().getPossibleSourceVariables(), sourceVarsToSearchFor)) {
                ret.add(di);
            }
        }
        return ret;
    }

    @Override
    public Api.FluentList<Api.DependencyInteraction> filterOutItemsWithSameSourceVar(final Api.DependencyInteraction dependencyInteraction) {
        final Api.FluentList<Api.DependencyInteraction> ret = new FluentListImpl<>();
        if(dependencyInteraction == null) {
            return ret;
        }
        final Api.FluentList<Api.Variable> sourceVarsToSearchFor = dependencyInteraction.getField().getPossibleSourceVariables();
        for(final T obj : this) {
            if(!(obj instanceof final Api.DependencyInteraction di)) {
                continue;
            }
            if(Collections.disjoint(di.getField().getPossibleSourceVariables(), sourceVarsToSearchFor)) {
                ret.add(di);
            }
        }
        return ret;
    }

    @Override
    public Api.FluentList<T> filter(
            final String attributeNameExpression) throws InvocationTargetException, IllegalAccessException {
        return filter(attributeNameExpression, true);
    }

    @Override
    public Api.FluentList<T> filter(final String attributeNameExpression, Object... attributeValues) throws InvocationTargetException, IllegalAccessException {
        // If this is called with filter(theList, "type.canonicalName", null), attributeValues will be null.
        // We don't want that. We want an Object[] with null in it.
        if(attributeValues == null) {
            attributeValues = new Object[]{null};
        }
        final Api.FluentList<T> ret = newList();
        for(final T obj : this) {
            final Object objAttributeValue = ListHelpers.getAttributeValue(obj, attributeNameExpression);
            if(containsAnyImpl(objAttributeValue, attributeValues)) {
                ret.add(obj);
            }
        }
        return ret;
    }

    @Override
    public Api.FluentList<T> filter2(final String attributeNameExpression, List<?> attributeValues) throws InvocationTargetException, IllegalAccessException {
        // If this is called with filter(theList, "type.canonicalName", null), attributeValues will be null.
        // We don't want that. We want an Object[] with null in it.
        if(attributeValues == null) {
            attributeValues = Arrays.asList(new Object[]{null});
        }
        final Api.FluentList<T> ret = newList();
        for(final T obj : this) {
            final Object objAttributeValue = ListHelpers.getAttributeValue(obj, attributeNameExpression);
            if(containsAnyImpl(objAttributeValue, attributeValues)) {
                ret.add(obj);
            }
        }
        return ret;
    }

    @Override
    public Api.FluentList<T> filterItemsWithAny(final String attributeNameExpression, List<Object> attributeValues) throws InvocationTargetException, IllegalAccessException {
        final Api.FluentList<T> ret = newList();
        for(final T obj : this) {
            final Object objAttributeValue = ListHelpers.getAttributeValue(obj, attributeNameExpression);
            if(containsAnyImpl(objAttributeValue, attributeValues.toArray())) {
                ret.add(obj);
            }
        }
        return ret;
    }

    @Override
    public Api.FluentList<T> filterItemsWithPrefix(
            final String attributeNameExpression,
            final String... prefixes) throws InvocationTargetException, IllegalAccessException {
        final Api.FluentList<T> ret = newList();
        for(final T obj : this) {
            final Object attributeValue = ListHelpers.getAttributeValue(obj, attributeNameExpression);
            if(attributeValue instanceof final String attrValueStr) {
                if(StringUtils.startsWithAny(attrValueStr, prefixes)) {
                    ret.add(obj);
                }
            }
        }
        return ret;
    }

    @Override
    public Api.FluentList<T> filterItemsWithPrefix(
            final String attributeNameExpression,
            final List<String> prefixes) throws InvocationTargetException, IllegalAccessException {
        if(attributeNameExpression == null || prefixes == null) {
            return newList();
        }
        return filterItemsWithPrefix(attributeNameExpression, prefixes.toArray(new String[]{}));
    }

    @Override
    public Api.FluentList<T> filterItemsWithRegex(
            final String attributeNameExpression,
            final String regex) throws InvocationTargetException, IllegalAccessException {
        final Api.FluentList<T> ret = newList();
        for(final T obj : this) {
            final Object attributeValue = ListHelpers.getAttributeValue(obj, attributeNameExpression);
            if(attributeValue instanceof final String attrValueStr) {
                if(attrValueStr.matches(regex)) {
                    ret.add(obj);
                }
            }
        }
        return ret;
    }

    @Override
    public Api.FluentList<T> filterOut(
            final String attributeNameExpression) throws InvocationTargetException, IllegalAccessException {
        return filterOut(attributeNameExpression, true);
    }

    @Override
    public Api.FluentList<T> filterOut(final String attributeNameExpression, Object... attributeValues) throws InvocationTargetException, IllegalAccessException {
        if(attributeValues == null) {
            attributeValues = new Object[]{null};
        }
        final Api.FluentList<T> ret = newList();
        for(final T obj : this) {
            final Object actualValue = ListHelpers.getAttributeValue(obj, attributeNameExpression);
            if(!containsAnyImpl(actualValue, attributeValues)) {
                ret.add(obj);
            }
        }
        return ret;
    }

    @Override
    public Api.FluentList<T> filterOut2(final String attributeNameExpression, List<?> attributeValues) throws InvocationTargetException, IllegalAccessException {
        if(attributeValues == null) {
            attributeValues = Arrays.asList(new Object[]{null});
        }
        final Api.FluentList<T> ret = newList();
        for(final T obj : this) {
            final Object actualValue = ListHelpers.getAttributeValue(obj, attributeNameExpression);
            if(!containsAnyImpl(actualValue, attributeValues)) {
                ret.add(obj);
            }
        }
        return ret;
    }

    @Override
    public Api.FluentList<T> filterOutItemsWithPrefix(
            final String attributeNameExpression, final List<String> prefixes) throws InvocationTargetException, IllegalAccessException {
        if(prefixes == null) {
            // We need to return a new list with the same items as the old list.
            final Api.FluentList<T> ret = newList();
            ret.addAll(this);
            return ret;
        }
        return filterOutItemsWithPrefix(attributeNameExpression, prefixes.toArray(new String[]{}));
    }

    @Override
    public Api.FluentList<T> filterOutItemsWithPrefix(final String attributeNameExpression, final String... prefixes) throws InvocationTargetException, IllegalAccessException {
        final Api.FluentList<T> ret = newList();
        for(final T obj : this) {
            final Object attributeValue = ListHelpers.getAttributeValue(obj, attributeNameExpression);
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
    public Api.FluentList<T> filterOutItemsWithRegex(
            final String attributeNameExpression, final String regex) throws InvocationTargetException, IllegalAccessException {
        final Api.FluentList<T> ret = newList();
        for(final T obj : this) {
            final Object attributeValue = ListHelpers.getAttributeValue(obj, attributeNameExpression);
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
    public Api.FluentList<T> filterOutItem(final T valueToRemove) {
        final Api.FluentList<T> ret = newList();
        for(final T item : this) {
            if(!Objects.equals(item, valueToRemove)) {
                ret.add(item);
            }
        }
        return ret;
    }

    @Override
    public Api.FluentList<T> filterItemsWithAnnotation(final String... annotations) {
        final Api.FluentList<T> ret = newList();
        for(final T item : this) {
            if(hasAnnotation(item, annotations)) {
                ret.add(item);
            }
        }
        return ret;
    }

    @Override
    public Api.FluentList<T> filterItemsWithAnnotation(final List<String> annotations) {
        if(annotations == null) {
            return newList();
        }
        return filterItemsWithAnnotation(annotations.toArray(new String[]{}));
    }

    @Override
    public Api.FluentList<T> filterOutItemsWithAnnotation(final String... annotations) {
        final Api.FluentList<T> ret = newList();
        for(final T item : this) {
            if(!hasAnnotation(item, annotations)) {
                ret.add(item);
            }
        }
        return ret;
    }

    @Override
    public Api.FluentList<T> filterOutItemsWithAnnotation(final List<String> annotations) {
        if(annotations == null) {
            final Api.FluentList<T> ret = newList();
            ret.addAll(this);
            return ret;
        }
        return filterOutItemsWithAnnotation(annotations.toArray(new String[]{}));
    }

    @Override
    public Api.FluentList<T> filterItemsWithAnnotationPrefix(final String... prefixes) {
        final Api.FluentList<T> ret = newList();
        for(final T item : this) {
            if(hasAnnotationPrefix(item, prefixes)) {
                ret.add(item);
            }
        }
        return ret;
    }

    @Override
    public Api.FluentList<T> filterItemsWithAnnotationPrefix(final List<String> prefixes) {
        if(prefixes == null) {
            return newList();
        }
        return filterItemsWithAnnotationPrefix(prefixes.toArray(new String[]{}));
    }

    @Override
    public Api.FluentList<T> filterOutItemsWithAnnotationPrefix(final String... prefixes) {
        final Api.FluentList<T> ret = newList();
        for(final T item : this) {
            if(!hasAnnotationPrefix(item, prefixes)) {
                ret.add(item);
            }
        }
        return ret;
    }

    @Override
    public Api.FluentList<T> filterOutItemsWithAnnotationPrefix(List<String> prefixes) {
        if(prefixes == null) {
            final Api.FluentList<T> ret = newList();
            ret.addAll(this);
            return ret;
        }
        return filterOutItemsWithAnnotationPrefix(prefixes.toArray(new String[]{}));
    }

    @Override
    public boolean containsAnyWithAnnotation(final String... annotations) {
        for(final T item : this) {
            if(hasAnnotation(item, annotations)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAnyWithAnnotation(final List<String> annotations) {
        if(annotations == null) {
            return false;
        }
        return containsAnyWithAnnotation(annotations.toArray(new String[]{}));
    }

    @Override
    public boolean containsAnyWithAnnotationPrefix(final String... prefixes) {
        for(final T item : this) {
            if(hasAnnotationPrefix(item, prefixes)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAnyWithAnnotationPrefix(final List<String> prefixes) {
        if(prefixes == null) {
            return false;
        }
        return containsAnyWithAnnotationPrefix(prefixes.toArray(new String[]{}));
    }

    @Override
    public Api.FluentList<? extends T> intersection(final Iterable<? extends T> iterable) {
        final Collection<T> intersection = CollectionUtils.intersection(this, iterable);
        final Api.FluentList<T> ret = newList();
        ret.addAll(intersection);
        return ret;
    }

    @Override
    public Api.FluentList<? extends T> removeDuplicates() {
        return union(Collections.emptyList());
    }

    @Override
    public Api.FluentList<? extends T> removeDuplicates(final String attributeNameExpression) {
        return union(Collections.emptyList(), attributeNameExpression);
    }

    @Override
    public Api.FluentList<? extends T> union(Iterable<? extends T> otherList) {
        if(otherList == null) {
            otherList = Collections.emptyList();
        }
        return unionImpl(otherList, new ListHelpers.ObjectEqualsEquator<>());
    }

    @Override
    public Api.FluentList<? extends T> union(final Iterable<? extends T> otherList, final String attributeNameExpression) {
        if(attributeNameExpression == null) {
            throw new NullPointerException("FluentList.union(...) called with attributeNameExpression = null");
        }
        final Equator<T> attributeEquator = new Equator<>() {
            @Override
            public boolean equate(T o1, T o2) {
                if(o1 == o2) {
                    return true;
                }
                if(o1 == null || o2 == null) {
                    return false;
                }
                final Object attributeValue1 = ListHelpers.safeGetAttributeValue(o1, attributeNameExpression);
                final Object attributeValue2 = ListHelpers.safeGetAttributeValue(o2, attributeNameExpression);
                return Objects.equals(attributeValue1, attributeValue2);
            }

            @Override
            public int hash(T o) {
                if(o == null) {
                    return 0;
                }
                final Object attributeValue = ListHelpers.safeGetAttributeValue(o, attributeNameExpression);
                if(attributeValue == null) {
                    return 0;
                }
                return attributeValue.hashCode();
            }
        };

        if(otherList == null) {
            return unionImpl(Collections.emptyList(), attributeEquator);
        }
        return unionImpl(otherList, attributeEquator);
    }


    public Api.FluentList<? extends T> unionImpl(@NotNull Iterable<? extends T> list2, @NotNull final Equator<T> equator) {
        final Api.FluentList<T> ret = newList();
        for(final T obj : this) {
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

    @NotNull
    @Override
    public Api.FluentList<T> subList(final int fromIndex, final int toIndex) {
        return new FluentListImpl<>(super.subList(fromIndex, toIndex));
    }

    @Override
    public Map<Object, Object> getProps() {
        return props;
    }
}
