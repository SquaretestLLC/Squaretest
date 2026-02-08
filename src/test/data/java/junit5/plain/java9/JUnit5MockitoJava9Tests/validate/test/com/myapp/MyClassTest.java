package com.myapp;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MyClassTest {

    @Test
    void testIsTrue1() {
        // Setup
        // Run the test
        MyClass.isTrue(false, "message", 0L);

        // Verify the results
    }

    @Test
    void testIsTrue1_ThrowsIllegalArgumentException() {
        // Setup
        // Run the test
        assertThrows(IllegalArgumentException.class, () -> MyClass.isTrue(false, "message", 0L));
    }

    @Test
    void testIsTrue2() {
        // Setup
        // Run the test
        MyClass.isTrue(false, "message", 0.0);

        // Verify the results
    }

    @Test
    void testIsTrue2_ThrowsIllegalArgumentException() {
        // Setup
        // Run the test
        assertThrows(IllegalArgumentException.class, () -> MyClass.isTrue(false, "message", 0.0));
    }

    @Test
    void testIsTrue3() {
        // Setup
        // Run the test
        MyClass.isTrue(false, "message", "values");

        // Verify the results
    }

    @Test
    void testIsTrue3_ThrowsIllegalArgumentException() {
        // Setup
        // Run the test
        assertThrows(IllegalArgumentException.class, () -> MyClass.isTrue(false, "message", "values"));
    }

    @Test
    void testIsTrue4() {
        // Setup
        // Run the test
        MyClass.isTrue(false);

        // Verify the results
    }

    @Test
    void testIsTrue4_ThrowsIllegalArgumentException() {
        // Setup
        // Run the test
        assertThrows(IllegalArgumentException.class, () -> MyClass.isTrue(false));
    }

    @Test
    void testNotNull1() {
        assertEquals("result", MyClass.notNull("object"));
        assertThrows(NullPointerException.class, () -> MyClass.notNull("object"));
    }

    @Test
    void testNotNull2() {
        assertEquals("result", MyClass.notNull("object", "message", "values"));
        assertThrows(NullPointerException.class, () -> MyClass.notNull("object", "message", "values"));
    }

    @Test
    void testNotEmpty1() {
        assertArrayEquals(new String[]{"result"}, MyClass.notEmpty(new String[]{"array"}, "message", "values"));
        assertArrayEquals(new String[]{}, MyClass.notEmpty(new String[]{"array"}, "message", "values"));
        assertThrows(NullPointerException.class, () -> MyClass.notEmpty(new String[]{"array"}, "message", "values"));
        assertThrows(IllegalArgumentException.class,
                () -> MyClass.notEmpty(new String[]{"array"}, "message", "values"));
    }

    @Test
    void testNotEmpty2() {
        assertArrayEquals(new String[]{"result"}, MyClass.notEmpty(new String[]{"array"}));
        assertArrayEquals(new String[]{}, MyClass.notEmpty(new String[]{"array"}));
        assertThrows(NullPointerException.class, () -> MyClass.notEmpty(new String[]{"array"}));
        assertThrows(IllegalArgumentException.class, () -> MyClass.notEmpty(new String[]{"array"}));
    }

    @Test
    void testNotEmpty3() {
        assertEquals(List.of("value"), MyClass.notEmpty(List.of("value"), "message", "values"));
        assertEquals(Collections.emptyList(), MyClass.notEmpty(List.of("value"), "message", "values"));
        assertThrows(NullPointerException.class, () -> MyClass.notEmpty(List.of("value"), "message", "values"));
        assertThrows(IllegalArgumentException.class, () -> MyClass.notEmpty(List.of("value"), "message", "values"));
    }

    @Test
    void testNotEmpty4() {
        assertEquals(List.of("value"), MyClass.notEmpty(List.of("value")));
        assertEquals(Collections.emptyList(), MyClass.notEmpty(List.of("value")));
        assertThrows(NullPointerException.class, () -> MyClass.notEmpty(List.of("value")));
        assertThrows(IllegalArgumentException.class, () -> MyClass.notEmpty(List.of("value")));
    }

    @Test
    void testNotEmpty5() {
        // Setup
        final Map<?, ?> map = Map.ofEntries(Map.entry("value", "value"));
        final Map<?, ?> expectedResult = Map.ofEntries(Map.entry("value", "value"));

        // Run the test
        final Map<?, ?> result = MyClass.notEmpty(map, "message", "values");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testNotEmpty5_ThrowsNullPointerException() {
        // Setup
        final Map<?, ?> map = Map.ofEntries(Map.entry("value", "value"));

        // Run the test
        assertThrows(NullPointerException.class, () -> MyClass.notEmpty(map, "message", "values"));
    }

    @Test
    void testNotEmpty5_ThrowsIllegalArgumentException() {
        // Setup
        final Map<?, ?> map = Map.ofEntries(Map.entry("value", "value"));

        // Run the test
        assertThrows(IllegalArgumentException.class, () -> MyClass.notEmpty(map, "message", "values"));
    }

    @Test
    void testNotEmpty6() {
        // Setup
        final Map<?, ?> map = Map.ofEntries(Map.entry("value", "value"));
        final Map<?, ?> expectedResult = Map.ofEntries(Map.entry("value", "value"));

        // Run the test
        final Map<?, ?> result = MyClass.notEmpty(map);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testNotEmpty6_ThrowsNullPointerException() {
        // Setup
        final Map<?, ?> map = Map.ofEntries(Map.entry("value", "value"));

        // Run the test
        assertThrows(NullPointerException.class, () -> MyClass.notEmpty(map));
    }

    @Test
    void testNotEmpty6_ThrowsIllegalArgumentException() {
        // Setup
        final Map<?, ?> map = Map.ofEntries(Map.entry("value", "value"));

        // Run the test
        assertThrows(IllegalArgumentException.class, () -> MyClass.notEmpty(map));
    }

    @Test
    void testNotEmpty7() {
        assertEquals("result", MyClass.notEmpty("chars", "message", "values"));
        assertThrows(NullPointerException.class, () -> MyClass.notEmpty("chars", "message", "values"));
        assertThrows(IllegalArgumentException.class, () -> MyClass.notEmpty("chars", "message", "values"));
    }

    @Test
    void testNotEmpty8() {
        assertEquals("result", MyClass.notEmpty("chars"));
        assertThrows(NullPointerException.class, () -> MyClass.notEmpty("chars"));
        assertThrows(IllegalArgumentException.class, () -> MyClass.notEmpty("chars"));
    }

    @Test
    void testNotBlank1() {
        assertEquals("result", MyClass.notBlank("chars", "message", "values"));
        assertThrows(NullPointerException.class, () -> MyClass.notBlank("chars", "message", "values"));
        assertThrows(IllegalArgumentException.class, () -> MyClass.notBlank("chars", "message", "values"));
    }

    @Test
    void testNotBlank2() {
        assertEquals("result", MyClass.notBlank("chars"));
        assertThrows(NullPointerException.class, () -> MyClass.notBlank("chars"));
        assertThrows(IllegalArgumentException.class, () -> MyClass.notBlank("chars"));
    }

    @Test
    void testNoNullElements1() {
        assertArrayEquals(new String[]{"result"}, MyClass.noNullElements(new String[]{"array"}, "message", "values"));
        assertArrayEquals(new String[]{}, MyClass.noNullElements(new String[]{"array"}, "message", "values"));
        assertThrows(NullPointerException.class,
                () -> MyClass.noNullElements(new String[]{"array"}, "message", "values"));
        assertThrows(IllegalArgumentException.class,
                () -> MyClass.noNullElements(new String[]{"array"}, "message", "values"));
    }

    @Test
    void testNoNullElements2() {
        assertArrayEquals(new String[]{"result"}, MyClass.noNullElements(new String[]{"array"}));
        assertArrayEquals(new String[]{}, MyClass.noNullElements(new String[]{"array"}));
        assertThrows(NullPointerException.class, () -> MyClass.noNullElements(new String[]{"array"}));
        assertThrows(IllegalArgumentException.class, () -> MyClass.noNullElements(new String[]{"array"}));
    }

    @Test
    void testNoNullElements3() {
        assertEquals(List.of("value"), MyClass.noNullElements(List.of("value"), "message", "values"));
        assertEquals(Collections.emptyList(), MyClass.noNullElements(List.of("value"), "message", "values"));
        assertThrows(NullPointerException.class, () -> MyClass.noNullElements(List.of("value"), "message", "values"));
        assertThrows(IllegalArgumentException.class,
                () -> MyClass.noNullElements(List.of("value"), "message", "values"));
    }

    @Test
    void testNoNullElements4() {
        assertEquals(List.of("value"), MyClass.noNullElements(List.of("value")));
        assertEquals(Collections.emptyList(), MyClass.noNullElements(List.of("value")));
        assertThrows(NullPointerException.class, () -> MyClass.noNullElements(List.of("value")));
        assertThrows(IllegalArgumentException.class, () -> MyClass.noNullElements(List.of("value")));
    }

    @Test
    void testValidIndex1() {
        assertArrayEquals(new String[]{"result"}, MyClass.validIndex(new String[]{"array"}, 0, "message", "values"));
        assertArrayEquals(new String[]{}, MyClass.validIndex(new String[]{"array"}, 0, "message", "values"));
        assertThrows(NullPointerException.class,
                () -> MyClass.validIndex(new String[]{"array"}, 0, "message", "values"));
        assertThrows(IndexOutOfBoundsException.class,
                () -> MyClass.validIndex(new String[]{"array"}, 0, "message", "values"));
    }

    @Test
    void testValidIndex2() {
        assertArrayEquals(new String[]{"result"}, MyClass.validIndex(new String[]{"array"}, 0));
        assertArrayEquals(new String[]{}, MyClass.validIndex(new String[]{"array"}, 0));
        assertThrows(NullPointerException.class, () -> MyClass.validIndex(new String[]{"array"}, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> MyClass.validIndex(new String[]{"array"}, 0));
    }

    @Test
    void testValidIndex3() {
        assertEquals(List.of("value"), MyClass.validIndex(List.of("value"), 0, "message", "values"));
        assertEquals(Collections.emptyList(), MyClass.validIndex(List.of("value"), 0, "message", "values"));
        assertThrows(NullPointerException.class, () -> MyClass.validIndex(List.of("value"), 0, "message", "values"));
        assertThrows(IndexOutOfBoundsException.class,
                () -> MyClass.validIndex(List.of("value"), 0, "message", "values"));
    }

    @Test
    void testValidIndex4() {
        assertEquals(List.of("value"), MyClass.validIndex(List.of("value"), 0));
        assertEquals(Collections.emptyList(), MyClass.validIndex(List.of("value"), 0));
        assertThrows(NullPointerException.class, () -> MyClass.validIndex(List.of("value"), 0));
        assertThrows(IndexOutOfBoundsException.class, () -> MyClass.validIndex(List.of("value"), 0));
    }

    @Test
    void testValidIndex5() {
        assertEquals("result", MyClass.validIndex("chars", 0, "message", "values"));
        assertThrows(NullPointerException.class, () -> MyClass.validIndex("chars", 0, "message", "values"));
        assertThrows(IndexOutOfBoundsException.class, () -> MyClass.validIndex("chars", 0, "message", "values"));
    }

    @Test
    void testValidIndex6() {
        assertEquals("result", MyClass.validIndex("chars", 0));
        assertThrows(NullPointerException.class, () -> MyClass.validIndex("chars", 0));
        assertThrows(IndexOutOfBoundsException.class, () -> MyClass.validIndex("chars", 0));
    }

    @Test
    void testValidState1() {
        // Setup
        // Run the test
        MyClass.validState(false);

        // Verify the results
    }

    @Test
    void testValidState1_ThrowsIllegalStateException() {
        // Setup
        // Run the test
        assertThrows(IllegalStateException.class, () -> MyClass.validState(false));
    }

    @Test
    void testValidState2() {
        // Setup
        // Run the test
        MyClass.validState(false, "message", "values");

        // Verify the results
    }

    @Test
    void testValidState2_ThrowsIllegalStateException() {
        // Setup
        // Run the test
        assertThrows(IllegalStateException.class, () -> MyClass.validState(false, "message", "values"));
    }

    @Test
    void testMatchesPattern1() {
        // Setup
        // Run the test
        MyClass.matchesPattern("input", "pattern");

        // Verify the results
    }

    @Test
    void testMatchesPattern1_ThrowsIllegalArgumentException() {
        // Setup
        // Run the test
        assertThrows(IllegalArgumentException.class, () -> MyClass.matchesPattern("input", "pattern"));
    }

    @Test
    void testMatchesPattern2() {
        // Setup
        // Run the test
        MyClass.matchesPattern("input", "pattern", "message", "values");

        // Verify the results
    }

    @Test
    void testMatchesPattern2_ThrowsIllegalArgumentException() {
        // Setup
        // Run the test
        assertThrows(IllegalArgumentException.class,
                () -> MyClass.matchesPattern("input", "pattern", "message", "values"));
    }

    @Test
    void testNotNaN1() {
        // Setup
        // Run the test
        MyClass.notNaN(0.0);

        // Verify the results
    }

    @Test
    void testNotNaN1_ThrowsIllegalArgumentException() {
        // Setup
        // Run the test
        assertThrows(IllegalArgumentException.class, () -> MyClass.notNaN(0.0));
    }

    @Test
    void testNotNaN2() {
        // Setup
        // Run the test
        MyClass.notNaN(0.0, "message", "values");

        // Verify the results
    }

    @Test
    void testNotNaN2_ThrowsIllegalArgumentException() {
        // Setup
        // Run the test
        assertThrows(IllegalArgumentException.class, () -> MyClass.notNaN(0.0, "message", "values"));
    }

    @Test
    void testFinite1() {
        // Setup
        // Run the test
        MyClass.finite(0.0);

        // Verify the results
    }

    @Test
    void testFinite1_ThrowsIllegalArgumentException() {
        // Setup
        // Run the test
        assertThrows(IllegalArgumentException.class, () -> MyClass.finite(0.0));
    }

    @Test
    void testFinite2() {
        // Setup
        // Run the test
        MyClass.finite(0.0, "message", "values");

        // Verify the results
    }

    @Test
    void testFinite2_ThrowsIllegalArgumentException() {
        // Setup
        // Run the test
        assertThrows(IllegalArgumentException.class, () -> MyClass.finite(0.0, "message", "values"));
    }

    @Test
    void testInclusiveBetween1() {
        // Setup
        final Comparable<String> value = null;

        // Run the test
        MyClass.inclusiveBetween("start", "end", value);

        // Verify the results
    }

    @Test
    void testInclusiveBetween1_ThrowsIllegalArgumentException() {
        // Setup
        final Comparable<String> value = null;

        // Run the test
        assertThrows(IllegalArgumentException.class, () -> MyClass.inclusiveBetween("start", "end", value));
    }

    @Test
    void testInclusiveBetween2() {
        // Setup
        final Comparable<String> value = null;

        // Run the test
        MyClass.inclusiveBetween("start", "end", value, "message", "values");

        // Verify the results
    }

    @Test
    void testInclusiveBetween2_ThrowsIllegalArgumentException() {
        // Setup
        final Comparable<String> value = null;

        // Run the test
        assertThrows(IllegalArgumentException.class,
                () -> MyClass.inclusiveBetween("start", "end", value, "message", "values"));
    }

    @Test
    void testInclusiveBetween3() {
        // Setup
        // Run the test
        MyClass.inclusiveBetween(0L, 0L, 0L);

        // Verify the results
    }

    @Test
    void testInclusiveBetween3_ThrowsIllegalArgumentException() {
        // Setup
        // Run the test
        assertThrows(IllegalArgumentException.class, () -> MyClass.inclusiveBetween(0L, 0L, 0L));
    }

    @Test
    void testInclusiveBetween4() {
        // Setup
        // Run the test
        MyClass.inclusiveBetween(0L, 0L, 0L, "message");

        // Verify the results
    }

    @Test
    void testInclusiveBetween4_ThrowsIllegalArgumentException() {
        // Setup
        // Run the test
        assertThrows(IllegalArgumentException.class, () -> MyClass.inclusiveBetween(0L, 0L, 0L, "message"));
    }

    @Test
    void testInclusiveBetween5() {
        // Setup
        // Run the test
        MyClass.inclusiveBetween(0.0, 0.0, 0.0);

        // Verify the results
    }

    @Test
    void testInclusiveBetween5_ThrowsIllegalArgumentException() {
        // Setup
        // Run the test
        assertThrows(IllegalArgumentException.class, () -> MyClass.inclusiveBetween(0.0, 0.0, 0.0));
    }

    @Test
    void testInclusiveBetween6() {
        // Setup
        // Run the test
        MyClass.inclusiveBetween(0.0, 0.0, 0.0, "message");

        // Verify the results
    }

    @Test
    void testInclusiveBetween6_ThrowsIllegalArgumentException() {
        // Setup
        // Run the test
        assertThrows(IllegalArgumentException.class, () -> MyClass.inclusiveBetween(0.0, 0.0, 0.0, "message"));
    }

    @Test
    void testExclusiveBetween1() {
        // Setup
        final Comparable<String> value = null;

        // Run the test
        MyClass.exclusiveBetween("start", "end", value);

        // Verify the results
    }

    @Test
    void testExclusiveBetween1_ThrowsIllegalArgumentException() {
        // Setup
        final Comparable<String> value = null;

        // Run the test
        assertThrows(IllegalArgumentException.class, () -> MyClass.exclusiveBetween("start", "end", value));
    }

    @Test
    void testExclusiveBetween2() {
        // Setup
        final Comparable<String> value = null;

        // Run the test
        MyClass.exclusiveBetween("start", "end", value, "message", "values");

        // Verify the results
    }

    @Test
    void testExclusiveBetween2_ThrowsIllegalArgumentException() {
        // Setup
        final Comparable<String> value = null;

        // Run the test
        assertThrows(IllegalArgumentException.class,
                () -> MyClass.exclusiveBetween("start", "end", value, "message", "values"));
    }

    @Test
    void testExclusiveBetween3() {
        // Setup
        // Run the test
        MyClass.exclusiveBetween(0L, 0L, 0L);

        // Verify the results
    }

    @Test
    void testExclusiveBetween3_ThrowsIllegalArgumentException() {
        // Setup
        // Run the test
        assertThrows(IllegalArgumentException.class, () -> MyClass.exclusiveBetween(0L, 0L, 0L));
    }

    @Test
    void testExclusiveBetween4() {
        // Setup
        // Run the test
        MyClass.exclusiveBetween(0L, 0L, 0L, "message");

        // Verify the results
    }

    @Test
    void testExclusiveBetween4_ThrowsIllegalArgumentException() {
        // Setup
        // Run the test
        assertThrows(IllegalArgumentException.class, () -> MyClass.exclusiveBetween(0L, 0L, 0L, "message"));
    }

    @Test
    void testExclusiveBetween5() {
        // Setup
        // Run the test
        MyClass.exclusiveBetween(0.0, 0.0, 0.0);

        // Verify the results
    }

    @Test
    void testExclusiveBetween5_ThrowsIllegalArgumentException() {
        // Setup
        // Run the test
        assertThrows(IllegalArgumentException.class, () -> MyClass.exclusiveBetween(0.0, 0.0, 0.0));
    }

    @Test
    void testExclusiveBetween6() {
        // Setup
        // Run the test
        MyClass.exclusiveBetween(0.0, 0.0, 0.0, "message");

        // Verify the results
    }

    @Test
    void testExclusiveBetween6_ThrowsIllegalArgumentException() {
        // Setup
        // Run the test
        assertThrows(IllegalArgumentException.class, () -> MyClass.exclusiveBetween(0.0, 0.0, 0.0, "message"));
    }

    @Test
    void testIsInstanceOf1() {
        // Setup
        // Run the test
        MyClass.isInstanceOf(String.class, "obj");

        // Verify the results
    }

    @Test
    void testIsInstanceOf1_ThrowsIllegalArgumentException() {
        // Setup
        // Run the test
        assertThrows(IllegalArgumentException.class, () -> MyClass.isInstanceOf(String.class, "obj"));
    }

    @Test
    void testIsInstanceOf2() {
        // Setup
        // Run the test
        MyClass.isInstanceOf(String.class, "obj", "message", "values");

        // Verify the results
    }

    @Test
    void testIsInstanceOf2_ThrowsIllegalArgumentException() {
        // Setup
        // Run the test
        assertThrows(IllegalArgumentException.class,
                () -> MyClass.isInstanceOf(String.class, "obj", "message", "values"));
    }

    @Test
    void testIsAssignableFrom1() {
        // Setup
        // Run the test
        MyClass.isAssignableFrom(String.class, String.class);

        // Verify the results
    }

    @Test
    void testIsAssignableFrom1_ThrowsIllegalArgumentException() {
        // Setup
        // Run the test
        assertThrows(IllegalArgumentException.class, () -> MyClass.isAssignableFrom(String.class, String.class));
    }

    @Test
    void testIsAssignableFrom2() {
        // Setup
        // Run the test
        MyClass.isAssignableFrom(String.class, String.class, "message", "values");

        // Verify the results
    }

    @Test
    void testIsAssignableFrom2_ThrowsIllegalArgumentException() {
        // Setup
        // Run the test
        assertThrows(IllegalArgumentException.class,
                () -> MyClass.isAssignableFrom(String.class, String.class, "message", "values"));
    }
}
