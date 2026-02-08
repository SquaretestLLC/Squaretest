/*
 *
 *  * Copyright 2026 Squaretest LLC.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *   http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */
package com.squaretest.template.impl;

import com.squaretest.template.api.Api;
import com.squaretest.template.impl.noeqimpl.BarNoEq;
import com.squaretest.template.impl.noeqimpl.FooNoEq;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.intellij.testFramework.UsefulTestCase.assertThrows;
import static org.junit.Assert.*;

public class FluentListImplTest {

    @Test
    public void testItemsBefore() {
        final Api.FluentList<Integer> fluentList = new FluentListImpl<>(Arrays.asList(1, -99, 3, -2, 30, 4));
        assertEquals(Arrays.asList(1, -99), fluentList.itemsBefore(3));
        assertEquals(Collections.emptyList(), fluentList.itemsBefore(322));
        assertEquals(Collections.emptyList(), fluentList.itemsBefore(1));
        assertEquals(Arrays.asList(1, -99, 3, -2, 30), fluentList.itemsBefore(4));
        assertEquals(Collections.emptyList(), fluentList.itemsBefore(null));
        assertEquals(Arrays.asList(1, 2), new FluentListImpl<>(Arrays.asList(1, 2, null, 3)).itemsBefore(null));
    }

    @Test
    public void testItemsAfter() {
        final Api.FluentList<Integer> fluentList = new FluentListImpl<>(Arrays.asList(1, -99, 3, -2, 30, 4));
        assertEquals(Arrays.asList(-2, 30, 4), fluentList.itemsAfter(3));
        assertEquals(Collections.emptyList(), fluentList.itemsAfter(322));
        assertEquals(Collections.emptyList(), fluentList.itemsAfter(4));
        assertEquals(Arrays.asList(-99, 3, -2, 30, 4), fluentList.itemsAfter(1));
        assertEquals(Collections.emptyList(), fluentList.itemsAfter(null));
        assertEquals(Collections.singletonList(3), new FluentListImpl<>(Arrays.asList(1, 2, null, 3)).itemsAfter(null));
    }

    @Test
    public void testFilterOutItem() {
        final FluentListImpl<Integer> fluentList = new FluentListImpl<>(Arrays.asList(1, -99, 3, -2, 30, 4));
        assertEquals(Arrays.asList(1, 3, -2, 30, 4), fluentList.filterOutItem(-99));
        assertEquals(Arrays.asList(1, -99, 3, -2, 30, 4), fluentList.filterOutItem(333));
        assertEquals(Arrays.asList(-99, 3, -2, 30, 4), fluentList.filterOutItem(1));
        assertEquals(Arrays.asList(1, -99, 3, -2, 30), fluentList.filterOutItem(4));
        assertEquals(Collections.emptyList(), new FluentListImpl<>().filterOutItem(4));
        assertEquals(Arrays.asList(1, -99, 3, -2, 30, 4), fluentList.filterOutItem(null));
        assertEquals(Arrays.asList(1, 2, 3), new FluentListImpl<>(Arrays.asList(1, 2, null, 3)).filterOutItem(null));
    }

    @Test
    public void testFirst() {
        assertEquals(Integer.valueOf(1), new FluentListImpl<>(Arrays.asList(1, -99, 3, -2, 30, 4)).first());
        assertNull(new FluentListImpl<>(Collections.emptyList()).first());
    }

    @Test
    public void testGetLast_OneItem() {
        final FluentListImpl<String> fluentList = new FluentListImpl<>();
        fluentList.add("a");
        assertEquals("a", fluentList.getLast());
    }

    @Test
    public void testGetLast_TwoItems() {
        final FluentListImpl<String> fluentList = new FluentListImpl<>();
        fluentList.add("a");
        fluentList.add("b");
        assertEquals("b", fluentList.getLast());
    }

    @Test
    public void testGetLast_NoItems() {
        final FluentListImpl<String> fluentList = new FluentListImpl<>(0);
        assertNull(fluentList.getLast());
    }

    @Test
    public void testZeroCapacity() {
        final FluentListImpl<Integer> fluentList = new FluentListImpl<>(0);
        assertEquals(0, fluentList.size());
        fluentList.add(0);
        assertEquals(1, fluentList.size());
        assertEquals(0, (int) fluentList.get(0));
    }

    @Test
    public void testContainsAnyWith_ItemInMiddleOfList() throws Exception {
        // Setup
        final String attributeNameExpression = "bar.canonicalName";
        final Object searchValue = "java.lang.String";

        // Run the test
        final Api.FluentList<Foo> fluentList = new FluentListImpl<>(
                Arrays.asList(new Foo(new Bar("org.apache.commons.collections4.list.AbstractListDecorator")),
                        new Foo(new Bar("java.lang.String")), new Foo(new Bar("java.util.Collections"))));

        final boolean result = fluentList.containsAnyWith(attributeNameExpression, searchValue);

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testContainsAnyWith_SecondSearchValueMatches() throws Exception {
        // Setup
        final String attributeNameExpression = "bar.canonicalName";

        // Run the test
        final Api.FluentList<Foo> fluentList = new FluentListImpl<>(
                Arrays.asList(new Foo(new Bar("org.apache.commons.collections4.list.AbstractListDecorator")),
                        new Foo(new Bar("java.lang.String")), new Foo(new Bar("java.util.Collections"))));

        final boolean result = fluentList.containsAnyWith(attributeNameExpression, "java.util.List", "java.lang.String");

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testContainsAnyWith_ListOnlyContainsItem() throws Exception {
        // Setup
        final String attributeNameExpression = "bar.canonicalName";
        final Object searchValue = "java.lang.String";

        // Run the test
        final Api.FluentList<Foo> fluentList = new FluentListImpl<>(
                List.of(new Foo(new Bar("java.lang.String"))));

        final boolean result = fluentList.containsAnyWith(attributeNameExpression, searchValue);

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testContainsAnyWith_ListDoesNotContainItem() throws Exception {
        // Setup
        final String attributeNameExpression = "bar.canonicalName";

        // Run the test
        final Api.FluentList<Foo> fluentList = new FluentListImpl<>(
                Arrays.asList(new Foo(new Bar("org.apache.commons.collections4.list.AbstractListDecorator")),
                        new Foo(new Bar("java.util.Collections"))));

        final boolean result = fluentList.containsAnyWith(attributeNameExpression, "java.lang.String", "java.util.Arrays");

        // Verify the results
        assertFalse(result);
    }

    @Test
    public void testContainsAnyWith_ListDoesNotContainItem_WrongTypes() throws Exception {
        // Setup
        final String attributeNameExpression = "bar.canonicalName";
        final Object searchValue = new Object();

        // Run the test
        final Api.FluentList<Foo> fluentList = new FluentListImpl<>(
                Arrays.asList(new Foo(new Bar("org.apache.commons.collections4.list.AbstractListDecorator")),
                        new Foo(new Bar("java.util.Collections"))));

        final boolean result = fluentList.containsAnyWith(attributeNameExpression, searchValue);

        // Verify the results
        assertFalse(result);
    }

    @Test
    public void testContainsAnyWith_ListDoesNotContainItemWrongKey() throws Exception {
        // Setup
        final String attributeNameExpression = "bar.wrongValue";

        // Run the test
        final Api.FluentList<Foo> fluentList = new FluentListImpl<>(
                Arrays.asList(new Foo(new Bar("org.apache.commons.collections4.list.AbstractListDecorator")),
                        new Foo(new Bar("java.lang.String")), new Foo(new Bar("java.util.Collections"))));

        final boolean result = fluentList.containsAnyWith(attributeNameExpression, "java.lang.String");

        // Verify the results
        assertFalse(result);
    }

    @Test
    public void testContainsAnyWith_EmptyList() throws Exception {
        // Setup
        final String attributeNameExpression = "bar.canonicalName";
        final Object searchValue = "java.lang.String";

        // Run the test
        final Api.FluentList<Foo> fluentList = new FluentListImpl<>();
        final boolean result = fluentList.containsAnyWith(attributeNameExpression, searchValue);

        // Verify the results
        assertFalse(result);
    }

    @Test
    public void testContainsAnyWithPrefix() throws Exception {
        // Setup
        final Api.FluentList<Foo> fluentList = new FluentListImpl<>(
                Arrays.asList(
                        new Foo(new Bar("java.lang.String", false)),
                        new Foo(new Bar("java.net.Socket", true)),
                        new Foo(new Bar("java.util.Collections", false)),
                        new Foo(new Bar("java.lang.String", false)),
                        new Foo(new Bar(null, true))));

        // Test match
        assertTrue(fluentList.containsAnyWithPrefix("bar.canonicalName", "java.net"));
        // Test no match.
        assertFalse(fluentList.containsAnyWithPrefix("bar.canonicalName", "androidx."));
        // Test empty string.
        assertTrue(fluentList.containsAnyWithPrefix("bar.canonicalName", ""));

        // Test list with one item with null property
        assertFalse(new FluentListImpl<>(
                List.of(new Foo(new Bar(null, true))))
                .containsAnyWithPrefix("bar.canonicalName", "java.net"));
        assertFalse(new FluentListImpl<>(
                List.of(new Foo(new Bar(null, true))))
                .containsAnyWithPrefix("bar.canonicalName", ""));
        assertFalse(new FluentListImpl<>(
                List.of(new Foo(new Bar(null, true))))
                .containsAnyWithPrefix("bar.canonicalName", (String) null));
        assertFalse(new FluentListImpl<>(
                List.of(new Foo(new Bar(null, true))))
                .containsAnyWithPrefix("bar.canonicalName", (String[]) null));
    }

    @Test
    public void testContainsAnyWithPrefix_ListArgs() throws Exception {
        // Setup
        final Api.FluentList<Foo> fluentList = new FluentListImpl<>(
                Arrays.asList(
                        new Foo(new Bar("java.lang.String", false)),
                        new Foo(new Bar("java.net.Socket", true)),
                        new Foo(new Bar("java.util.Collections", false)),
                        new Foo(new Bar("java.lang.String", false)),
                        new Foo(new Bar(null, true))));

        // Test match
        assertTrue(fluentList.containsAnyWithPrefix("bar.canonicalName", List.of("java.net")));
        // Test no match.
        assertFalse(fluentList.containsAnyWithPrefix("bar.canonicalName", List.of("androidx.")));
        // Test empty string.
        assertTrue(fluentList.containsAnyWithPrefix("bar.canonicalName", List.of("")));

        // Test list with one item with null property
        assertFalse(new FluentListImpl<>(
                List.of(new Foo(new Bar(null, true))))
                .containsAnyWithPrefix("bar.canonicalName", List.of("java.net")));
        assertFalse(new FluentListImpl<>(
                List.of(new Foo(new Bar(null, true))))
                .containsAnyWithPrefix("bar.canonicalName", List.of("")));
        assertFalse(new FluentListImpl<>(
                List.of(new Foo(new Bar(null, true))))
                .containsAnyWithPrefix("bar.canonicalName", (List<String>) null));
        assertFalse(new FluentListImpl<>(
                List.of(new Foo(new Bar(null, true))))
                .containsAnyWithPrefix("bar.canonicalName", Collections.singletonList(null)));
    }

    @Test
    public void testContainsAnyWithRegex() throws Exception {
        // Setup
        final Api.FluentList<Foo> fluentList = new FluentListImpl<>(
                Arrays.asList(
                        new Foo(new Bar("java.lang.String", false)),
                        new Foo(new Bar("java.net.Socket", true)),
                        new Foo(new Bar("java.util.Collections", false)),
                        new Foo(new Bar("java.lang.String", false)),
                        new Foo(new Bar(null, true))));

        // Test match
        assertTrue(fluentList.containsAnyWithRegex("bar.canonicalName", "java.net.*"));
        // Test no match.
        assertFalse(fluentList.containsAnyWithRegex("bar.canonicalName", "androidx.*"));
        // Test empty string.
        assertFalse(fluentList.containsAnyWithRegex("bar.canonicalName", ""));
        assertThrows(NullPointerException.class, () ->
                fluentList.containsAnyWithRegex("bar.canonicalName", null));

        // Test list with one item with null property
        assertFalse(new FluentListImpl<>(
                List.of(new Foo(new Bar(null, true))))
                .containsAnyWithRegex("bar.canonicalName", "java.net"));
        assertFalse(new FluentListImpl<>(
                List.of(new Foo(new Bar(null, true))))
                .containsAnyWithRegex("bar.canonicalName", ""));
    }

    @Test
    public void testIndexOfAny() throws Exception {
        // Setup
        final String attributeNameExpression = "bar.canonicalName";
        final Object searchValues = "java.lang.String";

        // Run the test
        final Api.FluentList<Foo> fluentList = new FluentListImpl<>(
                Arrays.asList(new Foo(new Bar("org.apache.commons.collections4.list.AbstractListDecorator")),
                        new Foo(new Bar("java.lang.String")), new Foo(new Bar("java.util.Collections"))));

        final int result = fluentList.indexOfAny(attributeNameExpression, searchValues);

        // Verify the results
        assertEquals(1, result);
    }

    @Test
    public void testIndexOfAny_NoValueProvided() throws Exception {
        // Setup
        final String attributeNameExpression = "bar.canonicalName";

        // Run the test
        final Api.FluentList<Foo> fluentList = new FluentListImpl<>(
                Arrays.asList(new Foo(new Bar("org.apache.commons.collections4.list.AbstractListDecorator")),
                        new Foo(new Bar("java.lang.String")), new Foo(new Bar("java.util.Collections"))));

        final int result = fluentList.indexOfAny(attributeNameExpression);

        // Verify the results
        assertEquals(-1, result);
    }

    @Test
    public void testIndexOfAny_NullValueProvided() throws Exception {
        // Setup
        final String attributeNameExpression = "bar.canonicalName";
        final Api.FluentList<Foo> fluentList = new FluentListImpl<>(
                Arrays.asList(
                        new Foo(new Bar("org.apache.commons.collections4.list.AbstractListDecorator")),
                        new Foo(new Bar("java.lang.String")),
                        new Foo(new Bar("java.util.Collections"))));
        final Api.FluentList<Foo> fluentListWithNulls = new FluentListImpl<>(
                Arrays.asList(
                        new Foo(new Bar("org.apache.commons.collections4.list.AbstractListDecorator")),
                        new Foo(new Bar(null)),
                        new Foo(new Bar("java.util.Collections"))));

        // Search for a value that appears after an item with null value.
        assertEquals(2, fluentListWithNulls.indexOfAny(attributeNameExpression, "java.util.Collections"));

        // Search for null in a list with only non-null values.
        assertEquals(-1, fluentList.indexOfAny(attributeNameExpression, (Object) null));
        assertEquals(-1, fluentList.indexOfAny(attributeNameExpression, (Object[]) null));

        // Search for null in list with one null item.
        assertEquals(1, fluentListWithNulls.indexOfAny(attributeNameExpression, (Object) null));
        assertEquals(1, fluentListWithNulls.indexOfAny(attributeNameExpression, (Object[]) null));
    }

    @Test
    public void testIndexOfAny_ListOnlyContainsItem() throws Exception {
        // Setup
        final String attributeNameExpression = "bar.canonicalName";
        final Object searchValue = "java.lang.String";

        // Run the test
        final Api.FluentList<Foo> fluentList = new FluentListImpl<>(
                List.of(new Foo(new Bar("java.lang.String"))));
        final int result = fluentList.indexOfAny(attributeNameExpression, searchValue);

        // Verify the results
        assertEquals(0, result);
    }

    @Test
    public void testIndexOfAny_ListDoesNotContainItem() throws Exception {
        // Setup
        final String attributeNameExpression = "bar.canonicalName";
        final Object searchValue = "java.lang.String";

        // Run the test
        final Api.FluentList<Foo> fluentList = new FluentListImpl<>(
                Arrays.asList(new Foo(new Bar("org.apache.commons.collections4.list.AbstractListDecorator")),
                        new Foo(new Bar("java.util.Collections"))));
        final int result = fluentList.indexOfAny(attributeNameExpression, searchValue);

        // Verify the results
        assertEquals(-1, result);
    }

    @Test
    public void testIndexOfAny_ListDoesNotContainItem_WrongTypes() throws Exception {
        // Setup
        final String attributeNameExpression = "bar.canonicalName";
        final Object searchValue = new Object();

        // Run the test
        final Api.FluentList<Foo> fluentList = new FluentListImpl<>(
                Arrays.asList(new Foo(new Bar("org.apache.commons.collections4.list.AbstractListDecorator")),
                        new Foo(new Bar("java.util.Collections"))));
        final int result = fluentList.indexOfAny(attributeNameExpression, searchValue);

        // Verify the results
        assertEquals(-1, result);
    }

    @Test
    public void testIndexOfAny_ListDoesNotContainItemWrongKey() throws Exception {
        // Setup
        final String attributeNameExpression = "bar.wrongValue";
        final Object searchValue = new Object();

        // Run the test
        final Api.FluentList<Foo> fluentList = new FluentListImpl<>(
                Arrays.asList(new Foo(new Bar("org.apache.commons.collections4.list.AbstractListDecorator")),
                        new Foo(new Bar("java.lang.String")), new Foo(new Bar("java.util.Collections"))));

        final int result = fluentList.indexOfAny(attributeNameExpression, searchValue);

        // Verify the results
        assertEquals(-1, result);
    }

    @Test
    public void testIndexOfAny_EmptyList() throws Exception {
        // Setup
        final String attributeNameExpression = "bar.canonicalName";
        final Object searchValue = "java.lang.String";

        // Run the test
        final Api.FluentList<Foo> fluentList = new FluentListImpl<>();
        final int result = fluentList.indexOfAny(attributeNameExpression, searchValue);

        // Verify the results
        assertEquals(-1, result);
    }

    @Test
    public void testFilter() throws Exception {
        // Setup
        final Api.FluentList<Foo> fluentList = new FluentListImpl<>(
                Arrays.asList(
                        new Foo(new Bar("java.lang.String", false)),
                        new Foo(new Bar("java.net.Socket", true)),
                        new Foo(new Bar("java.util.Collections", false)),
                        new Foo(new Bar("java.lang.String", false)),
                        new Foo(new Bar(null, true))));

        // Test return one item
        assertListsHaveSameItems(
                List.of(new Foo(new Bar("java.net.Socket"))),
                fluentList.filter("bar.canonicalName", "java.net.Socket"));

        // Test return two items.
        assertListsHaveSameItems(
                Arrays.asList(new Foo(new Bar("java.lang.String")), new Foo(new Bar("java.lang.String"))),
                fluentList.filter("bar.canonicalName", "java.lang.String"));

        // Test two search values.
        assertListsHaveSameItems(
                Arrays.asList(new Foo(new Bar("java.lang.String")), new Foo(new Bar("java.util.Collections")), new Foo(new Bar("java.lang.String"))),
                fluentList.filter("bar.canonicalName", "java.lang.String", "java.util.Collections"));

        // Test implied boolean.
        assertListsHaveSameItems(
                Arrays.asList(new Foo(new Bar("java.net.Socket")), new Foo(new Bar(null, true))),
                fluentList.filter("bar.mockable"));

        // Test explicit boolean
        assertListsHaveSameItems(
                Arrays.asList(new Foo(new Bar("java.net.Socket")), new Foo(new Bar(null, true))),
                fluentList.filter("bar.mockable", true));

        // Test boolean with false
        assertListsHaveSameItems(
                Arrays.asList(
                        new Foo(new Bar("java.lang.String", false)),
                        new Foo(new Bar("java.util.Collections", false)),
                        new Foo(new Bar("java.lang.String", false))),
                fluentList.filter("bar.mockable", false));

        // Test with null search value
        assertListsHaveSameItems(
                List.of(new Foo(new Bar(null, true))),
                fluentList.filter("bar.canonicalName", (Object[]) null));

        // Test with list of null search values
        assertListsHaveSameItems(
                List.of(new Foo(new Bar(null, true))),
                fluentList.filter("bar.canonicalName", null, null));
    }

    @Test
    public void testFilterItemsWithPrefix() throws Exception {
        // Setup
        final Api.FluentList<Foo> fluentList = new FluentListImpl<>(
                Arrays.asList(
                        new Foo(new Bar("java.lang.String", false)),
                        new Foo(new Bar("java.net.Socket", true)),
                        new Foo(new Bar("java.util.Collections", false)),
                        new Foo(new Bar("java.lang.String", false)),
                        new Foo(new Bar(null, true))));

        // Test return one item
        assertListsHaveSameItems(
                List.of(new Foo(new Bar("java.net.Socket"))),
                fluentList.filterItemsWithPrefix("bar.canonicalName", "java.net"));

        // Test return two items.
        assertListsHaveSameItems(
                Arrays.asList(new Foo(new Bar("java.lang.String")), new Foo(new Bar("java.lang.String"))),
                fluentList.filterItemsWithPrefix("bar.canonicalName", "java.lang"));

        // Test two search values.
        assertListsHaveSameItems(
                Arrays.asList(new Foo(new Bar("java.lang.String")), new Foo(new Bar("java.util.Collections")), new Foo(new Bar("java.lang.String"))),
                fluentList.filterItemsWithPrefix("bar.canonicalName", "java.lang.String", "java.util.Collections"));

        // Test no prefixes passed in.
        assertListsHaveSameItems(
                Collections.emptyList(),
                fluentList.filterItemsWithPrefix("bar.canonicalName"));

        // Test empty string.
        assertListsHaveSameItems(
                Arrays.asList(
                        new Foo(new Bar("java.lang.String", false)),
                        new Foo(new Bar("java.net.Socket", true)),
                        new Foo(new Bar("java.util.Collections", false)),
                        new Foo(new Bar("java.lang.String", false))),
                fluentList.filterItemsWithPrefix("bar.canonicalName", ""));

        // Test with null search value
        assertListsHaveSameItems(
                Collections.emptyList(),
                fluentList.filterItemsWithPrefix("bar.canonicalName", (String) null));

        // Test with list of null search values
        assertListsHaveSameItems(
                Collections.emptyList(),
                fluentList.filterItemsWithPrefix("bar.canonicalName", null, null));
    }

    @Test
    public void testFilterItemsWithPrefix_ListArg() throws Exception {
        // Setup
        final Api.FluentList<Foo> fluentList = new FluentListImpl<>(
                Arrays.asList(
                        new Foo(new Bar("java.lang.String", false)),
                        new Foo(new Bar("java.net.Socket", true)),
                        new Foo(new Bar("java.util.Collections", false)),
                        new Foo(new Bar("java.lang.String", false)),
                        new Foo(new Bar(null, true))));

        // Test return one item
        assertListsHaveSameItems(
                List.of(new Foo(new Bar("java.net.Socket"))),
                fluentList.filterItemsWithPrefix("bar.canonicalName", Collections.singletonList("java.net")));

        // Test return two items.
        assertListsHaveSameItems(
                Arrays.asList(new Foo(new Bar("java.lang.String")), new Foo(new Bar("java.lang.String"))),
                fluentList.filterItemsWithPrefix("bar.canonicalName", "java.lang"));

        // Test two search values.
        assertListsHaveSameItems(
                Arrays.asList(new Foo(new Bar("java.lang.String")), new Foo(new Bar("java.util.Collections")), new Foo(new Bar("java.lang.String"))),
                fluentList.filterItemsWithPrefix("bar.canonicalName", Arrays.asList("java.lang.String", "java.util.Collections")));

        // Test no prefixes passed in.
        assertListsHaveSameItems(
                Collections.emptyList(),
                fluentList.filterItemsWithPrefix("bar.canonicalName"));

        // Test empty string.
        assertListsHaveSameItems(
                Arrays.asList(
                        new Foo(new Bar("java.lang.String", false)),
                        new Foo(new Bar("java.net.Socket", true)),
                        new Foo(new Bar("java.util.Collections", false)),
                        new Foo(new Bar("java.lang.String", false))),
                fluentList.filterItemsWithPrefix("bar.canonicalName", List.of("")));

        // Test with null search value
        assertListsHaveSameItems(
                Collections.emptyList(),
                fluentList.filterItemsWithPrefix("bar.canonicalName", (String) null));

        // Test with list of null search values
        assertListsHaveSameItems(
                Collections.emptyList(),
                fluentList.filterItemsWithPrefix("bar.canonicalName", null, null));
    }

    @Test
    public void testFilterItemsWithRegex() throws Exception {
        // Setup
        final Api.FluentList<Foo> fluentList = new FluentListImpl<>(
                Arrays.asList(
                        new Foo(new Bar("java.lang.String", false)),
                        new Foo(new Bar("java.net.Socket", true)),
                        new Foo(new Bar("java.util.Collections", false)),
                        new Foo(new Bar("java.lang.String", false)),
                        new Foo(new Bar(null, true))));

        // Test return one item
        assertListsHaveSameItems(
                List.of(new Foo(new Bar("java.net.Socket"))),
                fluentList.filterItemsWithRegex("bar.canonicalName", "java.net.*"));

        // Test return two items.
        assertListsHaveSameItems(
                Arrays.asList(new Foo(new Bar("java.lang.String")), new Foo(new Bar("java.lang.String"))),
                fluentList.filterItemsWithRegex("bar.canonicalName", "java.lang.*"));

        // Test two search values.
        assertListsHaveSameItems(
                Arrays.asList(new Foo(new Bar("java.lang.String")), new Foo(new Bar("java.util.Collections")), new Foo(new Bar("java.lang.String"))),
                fluentList.filterItemsWithRegex("bar.canonicalName", "java.*(String|Collections).*"));

        // Test empty string.
        assertListsHaveSameItems(
                Collections.emptyList(),
                fluentList.filterItemsWithRegex("bar.canonicalName", ""));

        // Test with null search value (should throw NPE).
        assertThrows(NullPointerException.class, () ->
                fluentList.filterItemsWithRegex("bar.canonicalName", null));
    }

    @Test
    public void testFilterOut() throws Exception {
        // Setup
        final Api.FluentList<Foo> fluentList = new FluentListImpl<>(
                Arrays.asList(
                        new Foo(new Bar("java.lang.String", false)),
                        new Foo(new Bar("java.net.Socket", true)),
                        new Foo(new Bar("java.util.Collections", false)),
                        new Foo(new Bar("java.lang.String", false)),
                        new Foo(new Bar(null, true))));

        // Test remove one item
        assertListsHaveSameItems(
                Arrays.asList(
                        new Foo(new Bar("java.lang.String", false)),
                        new Foo(new Bar("java.util.Collections", false)),
                        new Foo(new Bar("java.lang.String", false)),
                        new Foo(new Bar(null, true))),
                fluentList.filterOut("bar.canonicalName", "java.net.Socket"));

        // Test remove two items.
        assertListsHaveSameItems(
                Arrays.asList(
                        new Foo(new Bar("java.net.Socket", true)),
                        new Foo(new Bar("java.util.Collections", false)),
                        new Foo(new Bar(null, true))),
                fluentList.filterOut("bar.canonicalName", "java.lang.String"));

        // Test two search values.
        assertListsHaveSameItems(
                Arrays.asList(
                        new Foo(new Bar("java.net.Socket", true)),
                        new Foo(new Bar(null, true))),
                fluentList.filterOut("bar.canonicalName", "java.lang.String", "java.util.Collections"));

        // Test implied boolean.
        assertListsHaveSameItems(
                Arrays.asList(
                        new Foo(new Bar("java.lang.String", false)),
                        new Foo(new Bar("java.util.Collections", false)),
                        new Foo(new Bar("java.lang.String", false))),
                fluentList.filterOut("bar.mockable"));

        // Test explicit boolean
        assertListsHaveSameItems(
                Arrays.asList(
                        new Foo(new Bar("java.lang.String", false)),
                        new Foo(new Bar("java.util.Collections", false)),
                        new Foo(new Bar("java.lang.String", false))),
                fluentList.filterOut("bar.mockable", true));

        // Test boolean with false
        assertListsHaveSameItems(
                Arrays.asList(
                        new Foo(new Bar("java.net.Socket", true)),
                        new Foo(new Bar(null, true))),
                fluentList.filterOut("bar.mockable", false));

        // Test with null search value
        assertListsHaveSameItems(
                Arrays.asList(
                        new Foo(new Bar("java.lang.String", false)),
                        new Foo(new Bar("java.net.Socket", true)),
                        new Foo(new Bar("java.util.Collections", false)),
                        new Foo(new Bar("java.lang.String", false))),
                fluentList.filterOut("bar.canonicalName", (Object[]) null));

        // Test with list of null search values
        assertListsHaveSameItems(
                Arrays.asList(
                        new Foo(new Bar("java.lang.String", false)),
                        new Foo(new Bar("java.net.Socket", true)),
                        new Foo(new Bar("java.util.Collections", false)),
                        new Foo(new Bar("java.lang.String", false))),
                fluentList.filterOut("bar.canonicalName", null, null));
    }

    @Test
    public void testFilterOutItemsWithPrefix() throws Exception {
        // Setup
        final Api.FluentList<Foo> fluentList = new FluentListImpl<>(
                Arrays.asList(
                        new Foo(new Bar("java.lang.String", false)),
                        new Foo(new Bar("java.net.Socket", true)),
                        new Foo(new Bar("java.util.Collections", false)),
                        new Foo(new Bar("java.lang.String", false)),
                        new Foo(new Bar(null, true))));

        // Test remove one item
        assertListsHaveSameItems(
                Arrays.asList(
                        new Foo(new Bar("java.lang.String", false)),
                        new Foo(new Bar("java.util.Collections", false)),
                        new Foo(new Bar("java.lang.String", false)),
                        new Foo(new Bar(null, true))),
                fluentList.filterOutItemsWithPrefix("bar.canonicalName", "java.net."));

        // Test remove two items.
        assertListsHaveSameItems(
                Arrays.asList(
                        new Foo(new Bar("java.net.Socket", true)),
                        new Foo(new Bar("java.util.Collections", false)),
                        new Foo(new Bar(null, true))),
                fluentList.filterOutItemsWithPrefix("bar.canonicalName", "java.lang."));

        // Test two search values.
        assertListsHaveSameItems(
                Arrays.asList(
                        new Foo(new Bar("java.net.Socket", true)),
                        new Foo(new Bar(null, true))),
                fluentList.filterOutItemsWithPrefix("bar.canonicalName", "java.lang.String", "java.util.Collections"));

        // Test empty string.
        assertListsHaveSameItems(
                List.of(
                        new Foo(new Bar(null, true))),
                fluentList.filterOutItemsWithPrefix("bar.canonicalName", ""));

        // Test with null search value.
        assertListsHaveSameItems(
                Arrays.asList(
                        new Foo(new Bar("java.lang.String", false)),
                        new Foo(new Bar("java.net.Socket", true)),
                        new Foo(new Bar("java.util.Collections", false)),
                        new Foo(new Bar("java.lang.String", false)),
                        new Foo(new Bar(null, true))),
                fluentList.filterOutItemsWithPrefix("bar.canonicalName", (String) null));
        assertListsHaveSameItems(
                Arrays.asList(
                        new Foo(new Bar("java.lang.String", false)),
                        new Foo(new Bar("java.net.Socket", true)),
                        new Foo(new Bar("java.util.Collections", false)),
                        new Foo(new Bar("java.lang.String", false)),
                        new Foo(new Bar(null, true))),
                fluentList.filterOutItemsWithPrefix("bar.canonicalName", (String[]) null));
    }

    @Test
    public void testFilterOutItemsWithRegex() throws Exception {
        // Setup
        final Api.FluentList<Foo> fluentList = new FluentListImpl<>(
                Arrays.asList(
                        new Foo(new Bar("java.lang.String", false)),
                        new Foo(new Bar("java.net.Socket", true)),
                        new Foo(new Bar("java.util.Collections", false)),
                        new Foo(new Bar("java.lang.String", false)),
                        new Foo(new Bar(null, true))));

        // Test remove one item
        assertListsHaveSameItems(
                Arrays.asList(
                        new Foo(new Bar("java.lang.String", false)),
                        new Foo(new Bar("java.util.Collections", false)),
                        new Foo(new Bar("java.lang.String", false)),
                        new Foo(new Bar(null, true))),
                fluentList.filterOutItemsWithRegex("bar.canonicalName", "java.net.*"));

        // Test remove two items.
        assertListsHaveSameItems(
                Arrays.asList(
                        new Foo(new Bar("java.net.Socket", true)),
                        new Foo(new Bar("java.util.Collections", false)),
                        new Foo(new Bar(null, true))),
                fluentList.filterOutItemsWithRegex("bar.canonicalName", "java.lang.*"));

        // Test two search values.
        assertListsHaveSameItems(
                Arrays.asList(
                        new Foo(new Bar("java.net.Socket", true)),
                        new Foo(new Bar(null, true))),
                fluentList.filterOutItemsWithRegex("bar.canonicalName", "java.*(String|Collections).*"));

        // Test empty string.
        assertListsHaveSameItems(
                Arrays.asList(
                        new Foo(new Bar("java.lang.String", false)),
                        new Foo(new Bar("java.net.Socket", true)),
                        new Foo(new Bar("java.util.Collections", false)),
                        new Foo(new Bar("java.lang.String", false)),
                        new Foo(new Bar(null, true))),
                fluentList.filterOutItemsWithRegex("bar.canonicalName", ""));

        // Test with null search value (should throw NPE).
        assertThrows(NullPointerException.class, () ->
                fluentList.filterOutItemsWithRegex("bar.canonicalName", null));
    }

    @Test
    public void testFilterItemsWithAnnotation_OneItem() {
        // Setup
        final Api.FluentList<SimpleAnnotationHolder> fluentListUnderTest = Api.FluentListFactory.of(
                new SimpleAnnotationHolder(Api.FluentListFactory.of(
                        new AnnotationImpl("RestController", "org.springframework.web.bind.annotation.RestController", Api.FluentListFactory.of()))
                )
        );


        // Run the test
        assertEquals(1, fluentListUnderTest.filterItemsWithAnnotation("RestController").size());
        assertEquals(1, fluentListUnderTest.filterItemsWithAnnotation("org.springframework.web.bind.annotation.RestController").size());
        assertEquals(0, fluentListUnderTest.filterItemsWithAnnotation("org.springframework.stereotype.Controller").size());
        assertEquals(0, fluentListUnderTest.filterItemsWithAnnotation("OtherName").size());
        assertEquals(0, fluentListUnderTest.filterItemsWithAnnotation("").size());

        assertEquals(1, fluentListUnderTest.filterItemsWithAnnotation(Collections.singletonList("RestController")).size());
        assertEquals(1, fluentListUnderTest.filterItemsWithAnnotation(Collections.singletonList("org.springframework.web.bind.annotation.RestController")).size());
        assertEquals(0, fluentListUnderTest.filterItemsWithAnnotation(Collections.singletonList("org.springframework.stereotype.Controller")).size());
        assertEquals(0, fluentListUnderTest.filterItemsWithAnnotation(Collections.singletonList("OtherName")).size());
        assertEquals(0, fluentListUnderTest.filterItemsWithAnnotation(Collections.singletonList("")).size());

        // Multiple Item Queries
        assertEquals(1, fluentListUnderTest.filterItemsWithAnnotation("RestController", "org.springframework.web.bind.annotation.RestController").size());
        assertEquals(1, fluentListUnderTest.filterItemsWithAnnotation("org.springframework.web.bind.annotation.RestController", "org.springframework.stereotype.Controller").size());
        assertEquals(1, fluentListUnderTest.filterItemsWithAnnotation("RestController", "org.springframework.stereotype.Controller").size());
        assertEquals(1, fluentListUnderTest.filterItemsWithAnnotation("RestController", "Controller").size());
        assertEquals(0, fluentListUnderTest.filterItemsWithAnnotation("org.springframework.stereotype.Controller").size());
        assertEquals(0, fluentListUnderTest.filterItemsWithAnnotation("Controller").size());

        assertEquals(1, fluentListUnderTest.filterItemsWithAnnotation(Arrays.asList("RestController", "org.springframework.web.bind.annotation.RestController")).size());
        assertEquals(1, fluentListUnderTest.filterItemsWithAnnotation(Arrays.asList("org.springframework.web.bind.annotation.RestController", "org.springframework.stereotype.Controller")).size());
        assertEquals(1, fluentListUnderTest.filterItemsWithAnnotation(Arrays.asList("RestController", "org.springframework.stereotype.Controller")).size());
        assertEquals(1, fluentListUnderTest.filterItemsWithAnnotation(Arrays.asList("RestController", "Controller")).size());
        assertEquals(0, fluentListUnderTest.filterItemsWithAnnotation(Collections.singletonList("org.springframework.stereotype.Controller")).size());
        assertEquals(0, fluentListUnderTest.filterItemsWithAnnotation(Collections.singletonList("Controller")).size());

        // Empty cases
        assertEquals(0, fluentListUnderTest.filterItemsWithAnnotation(null, null).size());
        assertEquals(0, fluentListUnderTest.filterItemsWithAnnotation(Arrays.asList(null, null)).size());
        assertEquals(0, fluentListUnderTest.filterItemsWithAnnotation((String) null).size());
        assertEquals(0, fluentListUnderTest.filterItemsWithAnnotation((List<String>) null).size());
        assertEquals(0, fluentListUnderTest.filterItemsWithAnnotation().size());
    }

    @Test
    public void testFilterItemsWithAnnotation_SingleItemMultipleAnnotations() {
        // Setup
        final Api.FluentList<SimpleAnnotationHolder> fluentListUnderTest = Api.FluentListFactory.of(
                new SimpleAnnotationHolder(Api.FluentListFactory.of(
                        new AnnotationImpl("RestController", "org.springframework.web.bind.annotation.RestController", Api.FluentListFactory.of()),
                        new AnnotationImpl("Controller", "org.springframework.stereotype.Controller", Api.FluentListFactory.of()))
                )
        );

        // Run the test
        assertEquals(1, fluentListUnderTest.filterItemsWithAnnotation("RestController").size());
        assertEquals(1, fluentListUnderTest.filterItemsWithAnnotation("org.springframework.web.bind.annotation.RestController").size());
        assertEquals(1, fluentListUnderTest.filterItemsWithAnnotation("org.springframework.stereotype.Controller").size());

        assertEquals(1, fluentListUnderTest.filterItemsWithAnnotation(Collections.singletonList("RestController")).size());
        assertEquals(1, fluentListUnderTest.filterItemsWithAnnotation(Collections.singletonList("org.springframework.web.bind.annotation.RestController")).size());
        assertEquals(1, fluentListUnderTest.filterItemsWithAnnotation(Collections.singletonList("org.springframework.stereotype.Controller")).size());

        // Multiple Item Queries
        assertEquals(1, fluentListUnderTest.filterItemsWithAnnotation("RestController", "org.springframework.web.bind.annotation.RestController").size());
        assertEquals(1, fluentListUnderTest.filterItemsWithAnnotation("org.springframework.web.bind.annotation.RestController", "org.springframework.stereotype.Controller").size());
        assertEquals(1, fluentListUnderTest.filterItemsWithAnnotation("RestController", "org.springframework.stereotype.Controller").size());
        assertEquals(1, fluentListUnderTest.filterItemsWithAnnotation("RestController", "Controller").size());
        assertEquals(1, fluentListUnderTest.filterItemsWithAnnotation("org.springframework.stereotype.Controller").size());
        assertEquals(1, fluentListUnderTest.filterItemsWithAnnotation("Controller").size());

        assertEquals(1, fluentListUnderTest.filterItemsWithAnnotation(Arrays.asList("RestController", "org.springframework.web.bind.annotation.RestController")).size());
        assertEquals(1, fluentListUnderTest.filterItemsWithAnnotation(Arrays.asList("org.springframework.web.bind.annotation.RestController", "org.springframework.stereotype.Controller")).size());
        assertEquals(1, fluentListUnderTest.filterItemsWithAnnotation(Arrays.asList("RestController", "org.springframework.stereotype.Controller")).size());
        assertEquals(1, fluentListUnderTest.filterItemsWithAnnotation(Arrays.asList("RestController", "Controller")).size());
        assertEquals(1, fluentListUnderTest.filterItemsWithAnnotation(Collections.singletonList("org.springframework.stereotype.Controller")).size());
        assertEquals(1, fluentListUnderTest.filterItemsWithAnnotation(Collections.singletonList("Controller")).size());

        // Empty cases
        assertEquals(0, fluentListUnderTest.filterItemsWithAnnotation("OtherName").size());
        assertEquals(0, fluentListUnderTest.filterItemsWithAnnotation("").size());
        assertEquals(0, fluentListUnderTest.filterItemsWithAnnotation((String) null).size());
        assertEquals(0, fluentListUnderTest.filterItemsWithAnnotation((List<String>) null).size());
        assertEquals(0, fluentListUnderTest.filterItemsWithAnnotation().size());
    }

    @Test
    public void testFilterItemsWithAnnotation_SingleItemMultipleAnnotationsMixedListTypes() {
        // Setup
        final Api.FluentList<Object> fluentListUnderTest = Api.FluentListFactory.of(
                null,
                new Bar("java.lang.String", false),
                new SimpleAnnotationHolder(Api.FluentListFactory.of(
                        new AnnotationImpl("RestController", "org.springframework.web.bind.annotation.RestController", Api.FluentListFactory.of()),
                        new AnnotationImpl("Controller", "org.springframework.stereotype.Controller", Api.FluentListFactory.of()))
                ),
                null,
                "someString",
                new Foo(new Bar("java.lang.String", false))
        );

        // Run the test
        assertEquals(1, fluentListUnderTest.filterItemsWithAnnotation("RestController").size());
        assertEquals(1, fluentListUnderTest.filterItemsWithAnnotation("org.springframework.web.bind.annotation.RestController").size());
        assertEquals(1, fluentListUnderTest.filterItemsWithAnnotation("org.springframework.stereotype.Controller").size());

        // Multiple Item Queries
        assertEquals(1, fluentListUnderTest.filterItemsWithAnnotation("RestController", "org.springframework.web.bind.annotation.RestController").size());
        assertEquals(1, fluentListUnderTest.filterItemsWithAnnotation("org.springframework.web.bind.annotation.RestController", "org.springframework.stereotype.Controller").size());
        assertEquals(1, fluentListUnderTest.filterItemsWithAnnotation("RestController", "org.springframework.stereotype.Controller").size());
        assertEquals(1, fluentListUnderTest.filterItemsWithAnnotation("RestController", "Controller").size());
        assertEquals(1, fluentListUnderTest.filterItemsWithAnnotation("org.springframework.stereotype.Controller").size());
        assertEquals(1, fluentListUnderTest.filterItemsWithAnnotation("Controller").size());

        // Empty cases
        assertEquals(0, fluentListUnderTest.filterItemsWithAnnotation("OtherName").size());
        assertEquals(0, fluentListUnderTest.filterItemsWithAnnotation("").size());
        assertEquals(0, fluentListUnderTest.filterItemsWithAnnotation((String) null).size());
        assertEquals(0, fluentListUnderTest.filterItemsWithAnnotation((List<String>) null).size());
        assertEquals(0, fluentListUnderTest.filterItemsWithAnnotation().size());
    }

    @Test
    public void testFilterItemsWithAnnotation_MultipleItems() {
        // Setup
        final Api.FluentList<SimpleAnnotationHolder> fluentListUnderTest = Api.FluentListFactory.of(
                new SimpleAnnotationHolder(Api.FluentListFactory.of(
                        new AnnotationImpl("RestController", "org.springframework.web.bind.annotation.RestController", Api.FluentListFactory.of()))
                ),
                new SimpleAnnotationHolder(Api.FluentListFactory.of(
                        new AnnotationImpl("Controller", "org.springframework.stereotype.Controller", Api.FluentListFactory.of()))
                )
        );

        // Run the test
        assertEquals(1, fluentListUnderTest.filterItemsWithAnnotation("RestController").size());
        assertEquals(1, fluentListUnderTest.filterItemsWithAnnotation("org.springframework.web.bind.annotation.RestController").size());
        assertEquals(1, fluentListUnderTest.filterItemsWithAnnotation("org.springframework.stereotype.Controller").size());

        // Multiple Item Queries
        assertEquals(1, fluentListUnderTest.filterItemsWithAnnotation("RestController", "org.springframework.web.bind.annotation.RestController").size());
        assertEquals(2, fluentListUnderTest.filterItemsWithAnnotation("org.springframework.web.bind.annotation.RestController", "org.springframework.stereotype.Controller").size());
        assertEquals(2, fluentListUnderTest.filterItemsWithAnnotation("RestController", "org.springframework.stereotype.Controller").size());
        assertEquals(2, fluentListUnderTest.filterItemsWithAnnotation("RestController", "Controller").size());
        assertEquals(1, fluentListUnderTest.filterItemsWithAnnotation("org.springframework.stereotype.Controller").size());
        assertEquals(1, fluentListUnderTest.filterItemsWithAnnotation("Controller").size());

        // Empty cases
        assertEquals(0, fluentListUnderTest.filterItemsWithAnnotation("OtherName").size());
        assertEquals(0, fluentListUnderTest.filterItemsWithAnnotation("").size());
        assertEquals(0, fluentListUnderTest.filterItemsWithAnnotation((String) null).size());
        assertEquals(0, fluentListUnderTest.filterItemsWithAnnotation((List<String>) null).size());
        assertEquals(0, fluentListUnderTest.filterItemsWithAnnotation().size());
    }

    @Test
    public void testFilterItemsWithAnnotation_MultipleItemsNullCanonicalNames() {
        // Setup
        final Api.FluentList<SimpleAnnotationHolder> fluentListUnderTest = Api.FluentListFactory.of(
                new SimpleAnnotationHolder(Api.FluentListFactory.of(
                        new AnnotationImpl("RestController", null, Api.FluentListFactory.of()))
                ),
                new SimpleAnnotationHolder(Api.FluentListFactory.of(
                        new AnnotationImpl("Controller", null, Api.FluentListFactory.of()))
                )
        );

        // Run the test
        assertEquals(1, fluentListUnderTest.filterItemsWithAnnotation("RestController").size());
        assertEquals(0, fluentListUnderTest.filterItemsWithAnnotation("org.springframework.web.bind.annotation.RestController").size());
        assertEquals(0, fluentListUnderTest.filterItemsWithAnnotation("org.springframework.stereotype.Controller").size());

        // Multiple Item Queries
        assertEquals(1, fluentListUnderTest.filterItemsWithAnnotation("RestController", "org.springframework.web.bind.annotation.RestController").size());
        assertEquals(0, fluentListUnderTest.filterItemsWithAnnotation("org.springframework.web.bind.annotation.RestController", "org.springframework.stereotype.Controller").size());
        assertEquals(1, fluentListUnderTest.filterItemsWithAnnotation("RestController", "org.springframework.stereotype.Controller").size());
        assertEquals(2, fluentListUnderTest.filterItemsWithAnnotation("RestController", "Controller").size());
        assertEquals(0, fluentListUnderTest.filterItemsWithAnnotation("org.springframework.stereotype.Controller").size());
        assertEquals(1, fluentListUnderTest.filterItemsWithAnnotation("Controller").size());

        // Empty cases
        assertEquals(0, fluentListUnderTest.filterItemsWithAnnotation("OtherName").size());
        assertEquals(0, fluentListUnderTest.filterItemsWithAnnotation("").size());
        assertEquals(0, fluentListUnderTest.filterItemsWithAnnotation((String) null).size());
        assertEquals(0, fluentListUnderTest.filterItemsWithAnnotation((List<String>) null).size());
        assertEquals(0, fluentListUnderTest.filterItemsWithAnnotation().size());
    }

    @Test
    public void testFilterItemsWithAnnotation_EmptyList() {
        // Setup
        final Api.FluentList<SimpleAnnotationHolder> fluentListUnderTest = Api.FluentListFactory.of(
                new SimpleAnnotationHolder(Api.FluentListFactory.of()
                ));

        // Run the test
        assertEquals(0, fluentListUnderTest.filterItemsWithAnnotation("RestController").size());
        assertEquals(0, fluentListUnderTest.filterItemsWithAnnotation("org.springframework.web.bind.annotation.RestController").size());
        assertEquals(0, fluentListUnderTest.filterItemsWithAnnotation("org.springframework.stereotype.Controller").size());

        // Multiple Item Queries
        assertEquals(0, fluentListUnderTest.filterItemsWithAnnotation("RestController", "org.springframework.web.bind.annotation.RestController").size());
        assertEquals(0, fluentListUnderTest.filterItemsWithAnnotation("org.springframework.web.bind.annotation.RestController", "org.springframework.stereotype.Controller").size());
        assertEquals(0, fluentListUnderTest.filterItemsWithAnnotation("RestController", "org.springframework.stereotype.Controller").size());
        assertEquals(0, fluentListUnderTest.filterItemsWithAnnotation("RestController", "Controller").size());
        assertEquals(0, fluentListUnderTest.filterItemsWithAnnotation("org.springframework.stereotype.Controller").size());
        assertEquals(0, fluentListUnderTest.filterItemsWithAnnotation("Controller").size());

        // Empty cases
        assertEquals(0, fluentListUnderTest.filterItemsWithAnnotation(null, null).size());
        assertEquals(0, fluentListUnderTest.filterItemsWithAnnotation(Arrays.asList(null, null)).size());
        assertEquals(0, fluentListUnderTest.filterItemsWithAnnotation("OtherName").size());
        assertEquals(0, fluentListUnderTest.filterItemsWithAnnotation("").size());
        assertEquals(0, fluentListUnderTest.filterItemsWithAnnotation((String) null).size());
        assertEquals(0, fluentListUnderTest.filterItemsWithAnnotation((List<String>) null).size());
        assertEquals(0, fluentListUnderTest.filterItemsWithAnnotation().size());
    }


    @Test
    public void testFilterItemsWithAnnotationPrefix_MultipleItemsNullCanonicalNames() {
        // Setup
        final Api.FluentList<SimpleAnnotationHolder> fluentListUnderTest = Api.FluentListFactory.of(
                new SimpleAnnotationHolder(Api.FluentListFactory.of(
                        new AnnotationImpl("RestController", null, Api.FluentListFactory.of()))
                ),
                new SimpleAnnotationHolder(Api.FluentListFactory.of(
                        new AnnotationImpl("Controller", null, Api.FluentListFactory.of()))
                )
        );

        // Run the test
        assertEquals(1, fluentListUnderTest.filterItemsWithAnnotationPrefix("RestController").size());
        assertEquals(1, fluentListUnderTest.filterItemsWithAnnotationPrefix("Rest").size());
        assertEquals(0, fluentListUnderTest.filterItemsWithAnnotationPrefix("org.springframework.web.bind.annotation.RestController").size());
        assertEquals(0, fluentListUnderTest.filterItemsWithAnnotationPrefix("org.springframework.stereotype.Controller").size());
        assertEquals(1, fluentListUnderTest.filterItemsWithAnnotationPrefix("Controller").size());
        assertEquals(1, fluentListUnderTest.filterItemsWithAnnotationPrefix("Cont").size());

        // Multiple Item Queries
        assertEquals(2, fluentListUnderTest.filterItemsWithAnnotationPrefix("RestController", "Controller").size());
        assertEquals(2, fluentListUnderTest.filterItemsWithAnnotationPrefix("Rest", "Cont").size());

        // Empty cases
        assertEquals(0, fluentListUnderTest.filterItemsWithAnnotationPrefix("OtherName").size());
        assertEquals(2, fluentListUnderTest.filterItemsWithAnnotationPrefix("").size());
        assertEquals(0, fluentListUnderTest.filterItemsWithAnnotationPrefix((String) null).size());
        assertEquals(0, fluentListUnderTest.filterItemsWithAnnotationPrefix((List<String>) null).size());
        assertEquals(0, fluentListUnderTest.filterItemsWithAnnotationPrefix().size());
    }

    @Test
    public void testFilterItemsWithAnnotationPrefix_MultipleItemsCanonicalNames() {
        // Setup
        final Api.FluentList<SimpleAnnotationHolder> fluentListUnderTest = Api.FluentListFactory.of(
                new SimpleAnnotationHolder(Api.FluentListFactory.of(
                        new AnnotationImpl("RestController", "org.springframework.web.bind.annotation.RestController", Api.FluentListFactory.of()))
                ),
                new SimpleAnnotationHolder(Api.FluentListFactory.of(
                        new AnnotationImpl("Controller", "org.springframework.stereotype.Controller", Api.FluentListFactory.of()))
                )
        );

        // Run the test
        assertEquals(1, fluentListUnderTest.filterItemsWithAnnotationPrefix("RestController").size());
        assertEquals(1, fluentListUnderTest.filterItemsWithAnnotationPrefix("Rest").size());
        assertEquals(1, fluentListUnderTest.filterItemsWithAnnotationPrefix("org.springframework.web.bind.annotation.RestController").size());
        assertEquals(1, fluentListUnderTest.filterItemsWithAnnotationPrefix("org.springframework.stereotype.Controller").size());
        assertEquals(1, fluentListUnderTest.filterItemsWithAnnotationPrefix("org.springframework.web.bind.annotation.").size());
        assertEquals(1, fluentListUnderTest.filterItemsWithAnnotationPrefix("org.springframework.stereotype.").size());
        assertEquals(2, fluentListUnderTest.filterItemsWithAnnotationPrefix("org.springframework").size());
        assertEquals(1, fluentListUnderTest.filterItemsWithAnnotationPrefix("Controller").size());
        assertEquals(1, fluentListUnderTest.filterItemsWithAnnotationPrefix("Cont").size());

        // Multiple Item Queries
        assertEquals(2, fluentListUnderTest.filterItemsWithAnnotationPrefix("RestController", "Controller").size());
        assertEquals(2, fluentListUnderTest.filterItemsWithAnnotationPrefix("Rest", "Cont").size());

        // Empty cases
        assertEquals(0, fluentListUnderTest.filterItemsWithAnnotationPrefix(null, null).size());
        assertEquals(0, fluentListUnderTest.filterItemsWithAnnotationPrefix(Arrays.asList(null, null)).size());
        assertEquals(0, fluentListUnderTest.filterItemsWithAnnotationPrefix("OtherName").size());
        assertEquals(2, fluentListUnderTest.filterItemsWithAnnotationPrefix("").size());
        assertEquals(0, fluentListUnderTest.filterItemsWithAnnotationPrefix((String) null).size());
        assertEquals(0, fluentListUnderTest.filterItemsWithAnnotationPrefix((List<String>) null).size());
        assertEquals(0, fluentListUnderTest.filterItemsWithAnnotationPrefix().size());
    }

    @Test
    public void testUnion() {
        // Setup
        // Prepare the main list.
        final Foo foo1 = new Foo(new Bar("java.lang.Number", true));
        final Api.FluentList<Foo> fluentListImplUnderTest = new FluentListImpl<>();
        fluentListImplUnderTest.add(foo1);

        // Prepare the other list.
        final Foo foo2 = new Foo(new Bar("java.lang.String", false));
        final Iterable<Foo> otherList = List.of(foo2);

        // Run the test
        final Api.FluentList<? extends Foo> result = fluentListImplUnderTest.union(otherList);

        // Verify the results
        assertEquals(Arrays.asList(foo1, foo2), result);
    }

    @Test
    public void testUnion_DuplicatesInOtherListRemoved() {
        // Setup
        // Prepare the main list.
        final Foo foo1 = new Foo(new Bar("java.lang.Number", true));
        final Api.FluentList<Foo> fluentListImplUnderTest = new FluentListImpl<>();
        fluentListImplUnderTest.add(foo1);

        // Prepare the other list.
        final Foo foo2 = new Foo(new Bar("java.lang.String", false));
        final Foo foo3 = new Foo(new Bar("java.lang.String", false));
        final Iterable<Foo> otherList = Arrays.asList(foo2, foo3);

        // Run the test
        final Api.FluentList<? extends Foo> result = fluentListImplUnderTest.union(otherList);

        // Verify the results
        assertEquals(Arrays.asList(foo1, foo2), result);
    }

    @Test
    public void testUnion_DuplicatesInBothListsRemoved() {
        // Setup
        // Prepare the main list.
        final Foo foo1 = new Foo(new Bar("java.lang.Number", true));
        final Api.FluentList<Foo> fluentListImplUnderTest = new FluentListImpl<>();
        fluentListImplUnderTest.add(foo1);

        // Prepare the other list.
        final Foo foo2 = new Foo(new Bar("java.lang.String", false));
        final Foo foo3 = new Foo(new Bar("java.lang.Number", true));
        final Iterable<Foo> otherList = Arrays.asList(foo2, foo3);

        // Run the test
        final Api.FluentList<? extends Foo> result = fluentListImplUnderTest.union(otherList);

        // Verify the results
        assertEquals(Arrays.asList(foo1, foo2), result);
    }

    @Test
    public void testUnion_DuplicatesInFirstListRemoved() {
        // Setup
        // Prepare the main list.
        final Foo foo1 = new Foo(new Bar("java.lang.Number", true));
        final Foo foo3 = new Foo(new Bar("java.lang.Number", true));
        final Api.FluentList<Foo> fluentListImplUnderTest = new FluentListImpl<>();
        fluentListImplUnderTest.add(foo1);
        fluentListImplUnderTest.add(foo3);

        // Prepare the other list.
        final Foo foo2 = new Foo(new Bar("java.lang.String", false));
        final Iterable<Foo> otherList = List.of(foo2);

        // Run the test
        final Api.FluentList<? extends Foo> result = fluentListImplUnderTest.union(otherList);

        // Verify the results
        assertEquals(Arrays.asList(foo1, foo2), result);
    }

    @Test
    public void testUnion_NullList() {
        // Setup
        // Prepare the main list.
        final Foo foo1 = new Foo(new Bar("java.lang.Number", true));
        final Foo foo3 = new Foo(new Bar("java.lang.Number", true));
        final Api.FluentList<Foo> fluentListImplUnderTest = new FluentListImpl<>();
        fluentListImplUnderTest.add(foo1);
        fluentListImplUnderTest.add(foo3);

        // Run the test
        final Api.FluentList<? extends Foo> result = fluentListImplUnderTest.union(null);

        // Verify the results
        assertEquals(List.of(foo1), result);
    }

    @Test
    public void testUnion2_RemoveItemsWithDupProps() {
        // Setup
        // Prepare the main list.
        final FooNoEq foo1 = new FooNoEq(new BarNoEq("java.lang.Number", true));
        final FooNoEq foo3 = new FooNoEq(new BarNoEq("java.lang.Number", true));
        final Api.FluentList<FooNoEq> fluentListImplUnderTest = new FluentListImpl<>();
        fluentListImplUnderTest.add(foo1);
        fluentListImplUnderTest.add(foo3);

        // Prepare the other list.
        final FooNoEq foo2 = new FooNoEq(new BarNoEq("java.lang.String", false));
        final Iterable<FooNoEq> otherList = List.of(foo2);

        // Run the test
        final Api.FluentList<? extends FooNoEq> result = fluentListImplUnderTest.union(otherList, "bar.canonicalName");

        // Verify the results
        assertEquals(Arrays.asList(foo1, foo2), result);
    }

    @Test
    public void testUnion2_RemoveItemsWithDupNullProps() {
        // Setup
        // Prepare the main list.
        final FooNoEq foo1 = new FooNoEq(new BarNoEq(null, true));
        final FooNoEq foo3 = new FooNoEq(new BarNoEq(null, true));
        final Api.FluentList<FooNoEq> fluentListImplUnderTest = new FluentListImpl<>();
        fluentListImplUnderTest.add(foo1);
        fluentListImplUnderTest.add(foo3);

        // Prepare the other list.
        final FooNoEq foo2 = new FooNoEq(new BarNoEq("java.lang.String", false));
        final FooNoEq foo4 = new FooNoEq(new BarNoEq(null, false));
        final Iterable<FooNoEq> otherList = Arrays.asList(foo2, foo4);

        // Run the test
        final Api.FluentList<? extends FooNoEq> result = fluentListImplUnderTest.union(otherList, "bar.canonicalName");

        // Verify the results
        assertEquals(Arrays.asList(foo1, foo2), result);
    }

    @Test
    public void testUnion2_RemoveItemsWithDupMissingProps() {
        // Setup
        // Prepare the main list.
        final FooNoEq foo1 = new FooNoEq(new BarNoEq(null, true));
        final FooNoEq foo3 = new FooNoEq(new BarNoEq(null, true));
        final Api.FluentList<FooNoEq> fluentListImplUnderTest = new FluentListImpl<>();
        fluentListImplUnderTest.add(foo1);
        fluentListImplUnderTest.add(foo3);

        // Prepare the other list.
        final FooNoEq foo2 = new FooNoEq(new BarNoEq("java.lang.String", false));
        final FooNoEq foo4 = new FooNoEq(new BarNoEq(null, false));
        final Iterable<FooNoEq> otherList = Arrays.asList(foo2, foo4);

        // Run the test
        final Api.FluentList<? extends FooNoEq> result = fluentListImplUnderTest.union(otherList, "bar.badAttrValue");

        // Verify the results
        assertEquals(List.of(foo1), result);
    }

    @Test
    public void testUnion2_NullList() {
        // Setup
        // Prepare the main list.
        final FooNoEq foo1 = new FooNoEq(new BarNoEq(null, true));
        final FooNoEq foo3 = new FooNoEq(new BarNoEq(null, true));
        final Api.FluentList<FooNoEq> fluentListImplUnderTest = new FluentListImpl<>();
        fluentListImplUnderTest.add(foo1);
        fluentListImplUnderTest.add(foo3);

        // Run the test
        final Api.FluentList<? extends FooNoEq> result = fluentListImplUnderTest.union(null, "bar.canonicalName");

        // Verify the results
        assertEquals(List.of(foo1), result);
    }

    @Test(expected = NullPointerException.class)
    public void testUnion2_NullAttributeExpression() {
        // Setup
        // Prepare the main list.
        final FooNoEq foo1 = new FooNoEq(new BarNoEq(null, true));
        final FooNoEq foo3 = new FooNoEq(new BarNoEq(null, true));
        final Api.FluentList<FooNoEq> fluentListImplUnderTest = new FluentListImpl<>();
        fluentListImplUnderTest.add(foo1);
        fluentListImplUnderTest.add(foo3);

        // Run the test
        fluentListImplUnderTest.union(Api.FluentListFactory.emptyList(), null);
    }

    private void assertListsHaveSameItems(final List<?> expectedList, final List<?> actualList) {
        assertEquals(expectedList.size(), actualList.size());
        for(int i = 0; i < expectedList.size(); i++) {
            assertEquals(expectedList.get(i), actualList.get(i));
        }
    }
}
