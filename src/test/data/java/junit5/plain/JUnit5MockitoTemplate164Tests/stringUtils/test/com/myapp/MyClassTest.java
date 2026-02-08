package com.myapp;

import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Locale;
import java.util.regex.PatternSyntaxException;

import static org.junit.jupiter.api.Assertions.*;

class MyClassTest {

    @Test
    void testAbbreviate1() {
        assertEquals("result", MyClass.abbreviate("str", 0));
        assertThrows(IllegalArgumentException.class, () -> MyClass.abbreviate("str", 0));
    }

    @Test
    void testAbbreviate2() {
        assertEquals("result", MyClass.abbreviate("str", 0, 0));
        assertThrows(IllegalArgumentException.class, () -> MyClass.abbreviate("str", 0, 0));
    }

    @Test
    void testAbbreviate3() {
        assertEquals("result", MyClass.abbreviate("str", "abbrevMarker", 0));
        assertThrows(IllegalArgumentException.class, () -> MyClass.abbreviate("str", "abbrevMarker", 0));
    }

    @Test
    void testAbbreviate4() {
        assertEquals("str", MyClass.abbreviate("str", "abbrevMarker", 0, 0));
        assertThrows(IllegalArgumentException.class, () -> MyClass.abbreviate("str", "abbrevMarker", 0, 0));
    }

    @Test
    void testAbbreviateMiddle() {
        assertEquals("str", MyClass.abbreviateMiddle("str", "middle", 0));
    }

    @Test
    void testAppendIfMissing2() {
        assertEquals("result", MyClass.appendIfMissing("str", "suffix", "suffixes"));
    }

    @Test
    void testAppendIfMissingIgnoreCase() {
        assertEquals("result", MyClass.appendIfMissingIgnoreCase("str", "suffix", "suffixes"));
    }

    @Test
    void testCapitalize() {
        assertEquals("str", MyClass.capitalize("str"));
    }

    @Test
    void testCenter1() {
        assertEquals("result", MyClass.center("str", 0));
    }

    @Test
    void testCenter2() {
        assertEquals("str", MyClass.center("str", 0, 'a'));
    }

    @Test
    void testCenter3() {
        assertEquals("str", MyClass.center("str", 0, "padStr"));
        assertThrows(IllegalArgumentException.class, () -> MyClass.center("str", 0, "padStr"));
    }

    @Test
    void testChomp1() {
        assertEquals("str", MyClass.chomp("str"));
    }

    @Test
    void testChomp2() {
        assertEquals("result", MyClass.chomp("str", "separator"));
        assertNull(MyClass.chomp("str", "separator"));
    }

    @Test
    void testChop() {
        assertEquals("", MyClass.chop("str"));
    }

    @Test
    void testCompare1() {
        assertEquals(0, MyClass.compare("str1", "str2"));
    }

    @Test
    void testCompare2() {
        assertEquals(0, MyClass.compare("str1", "str2", false));
    }

    @Test
    void testCompareIgnoreCase1() {
        assertEquals(0, MyClass.compareIgnoreCase("str1", "str2"));
    }

    @Test
    void testCompareIgnoreCase2() {
        assertEquals(0, MyClass.compareIgnoreCase("str1", "str2", false));
    }

    @Test
    void testContains1() {
        assertTrue(MyClass.contains("seq", "searchSeq"));
    }

    @Test
    void testContains2() {
        assertTrue(MyClass.contains("seq", 0));
    }

    @Test
    void testContainsAny1() {
        assertTrue(MyClass.containsAny("cs", 'a'));
    }

    @Test
    void testContainsAny2() {
        assertTrue(MyClass.containsAny("cs", "searchChars"));
    }

    @Test
    void testContainsAny3() {
        assertTrue(MyClass.containsAny("cs", "searchCharSequences"));
    }

    @Test
    void testContainsIgnoreCase() {
        assertTrue(MyClass.containsIgnoreCase("str", "searchStr"));
    }

    @Test
    void testContainsNone1() {
        assertTrue(MyClass.containsNone("cs", 'a'));
    }

    @Test
    void testContainsNone2() {
        assertTrue(MyClass.containsNone("cs", "invalidChars"));
    }

    @Test
    void testContainsOnly1() {
        assertTrue(MyClass.containsOnly("cs", 'a'));
    }

    @Test
    void testContainsOnly2() {
        assertTrue(MyClass.containsOnly("cs", "validChars"));
    }

    @Test
    void testContainsWhitespace() {
        assertTrue(MyClass.containsWhitespace("seq"));
    }

    @Test
    void testCountMatches1() {
        assertEquals(0, MyClass.countMatches("str", 'a'));
    }

    @Test
    void testCountMatches2() {
        assertEquals(0, MyClass.countMatches("str", "sub"));
    }

    @Test
    void testDefaultIfBlank() {
        assertEquals("result", MyClass.defaultIfBlank("str", "defaultStr"));
        assertNull(MyClass.defaultIfBlank("str", "defaultStr"));
    }

    @Test
    void testDefaultIfEmpty() {
        assertEquals("result", MyClass.defaultIfEmpty("str", "defaultStr"));
        assertNull(MyClass.defaultIfEmpty("str", "defaultStr"));
    }

    @Test
    void testDefaultString1() {
        assertEquals("result", MyClass.defaultString("str"));
    }

    @Test
    void testDefaultString2() {
        assertEquals("result", MyClass.defaultString("str", "defaultStr"));
    }

    @Test
    void testDeleteWhitespace() {
        assertEquals("str", MyClass.deleteWhitespace("str"));
    }

    @Test
    void testDifference() {
        assertEquals("str1", MyClass.difference("str1", "str1"));
    }

    @Test
    void testEndsWith1() {
        assertTrue(MyClass.endsWith("str", "suffix"));
    }

    @Test
    void testEndsWithAny() {
        assertTrue(MyClass.endsWithAny("sequence", "searchStrings"));
    }

    @Test
    void testEndsWithIgnoreCase() {
        assertTrue(MyClass.endsWithIgnoreCase("str", "suffix"));
    }

    @Test
    void testEquals() {
        assertTrue(MyClass.equals("cs1", "cs2"));
    }

    @Test
    void testEqualsAny() {
        assertTrue(MyClass.equalsAny("string", "searchStrings"));
    }

    @Test
    void testEqualsAnyIgnoreCase() {
        assertTrue(MyClass.equalsAnyIgnoreCase("string", "searchStrings"));
    }

    @Test
    void testEqualsIgnoreCase() {
        assertTrue(MyClass.equalsIgnoreCase("cs1", "cs2"));
    }

    @Test
    void testFirstNonBlank() {
        assertEquals("result", MyClass.firstNonBlank("values"));
        assertNull(MyClass.firstNonBlank("values"));
    }

    @Test
    void testFirstNonEmpty() {
        assertEquals("result", MyClass.firstNonEmpty("values"));
        assertNull(MyClass.firstNonEmpty("values"));
    }

    @Test
    void testGetBytes1() {
        assertArrayEquals("content".getBytes(), MyClass.getBytes("string", StandardCharsets.UTF_8));
    }

    @Test
    void testGetBytes2() throws Exception {
        assertArrayEquals("content".getBytes(), MyClass.getBytes("string", "UTF-8"));
        assertThrows(UnsupportedEncodingException.class, () -> MyClass.getBytes("string", "UTF-8"));
    }

    @Test
    void testGetCommonPrefix() {
        assertEquals("", MyClass.getCommonPrefix("strs"));
    }

    @Test
    void testGetDigits() {
        assertEquals("str", MyClass.getDigits("str"));
        assertNull(MyClass.getDigits("str"));
    }

    @Test
    void testGetFuzzyDistance() {
        assertEquals(0, MyClass.getFuzzyDistance("term", "query", Locale.US));
        assertThrows(IllegalArgumentException.class, () -> MyClass.getFuzzyDistance("term", "query", Locale.US));
    }

    @Test
    void testGetIfBlank() {
        assertEquals("result", MyClass.getIfBlank("str", () -> "value"));
        assertNull(MyClass.getIfBlank("str", () -> "value"));
    }

    @Test
    void testGetIfEmpty() {
        assertEquals("result", MyClass.getIfEmpty("str", () -> "value"));
        assertNull(MyClass.getIfEmpty("str", () -> "value"));
    }

    @Test
    void testGetJaroWinklerDistance() {
        assertEquals(0.0, MyClass.getJaroWinklerDistance("first", "second"), 0.0001);
        assertThrows(IllegalArgumentException.class, () -> MyClass.getJaroWinklerDistance("first", "second"));
    }

    @Test
    void testGetLevenshteinDistance1() {
        assertEquals(0, MyClass.getLevenshteinDistance("s", "t"));
        assertThrows(IllegalArgumentException.class, () -> MyClass.getLevenshteinDistance("s", "t"));
    }

    @Test
    void testGetLevenshteinDistance2() {
        assertEquals(0, MyClass.getLevenshteinDistance("s", "t", 0));
        assertThrows(IllegalArgumentException.class, () -> MyClass.getLevenshteinDistance("s", "t", 0));
    }

    @Test
    void testIndexOf1() {
        assertEquals(0, MyClass.indexOf("seq", "searchSeq"));
    }

    @Test
    void testIndexOf2() {
        assertEquals(0, MyClass.indexOf("seq", "searchSeq", 0));
    }

    @Test
    void testIndexOf3() {
        assertEquals(0, MyClass.indexOf("seq", 0));
    }

    @Test
    void testIndexOf4() {
        assertEquals(0, MyClass.indexOf("seq", 0, 0));
    }

    @Test
    void testIndexOfAny1() {
        assertEquals(0, MyClass.indexOfAny("cs", 'a'));
    }

    @Test
    void testIndexOfAny2() {
        assertEquals(0, MyClass.indexOfAny("str", "searchStrs"));
    }

    @Test
    void testIndexOfAny3() {
        assertEquals(0, MyClass.indexOfAny("cs", "searchChars"));
    }

    @Test
    void testIndexOfAnyBut1() {
        assertEquals(0, MyClass.indexOfAnyBut("cs", 'a'));
    }

    @Test
    void testIndexOfAnyBut2() {
        assertEquals(0, MyClass.indexOfAnyBut("seq", "searchChars"));
    }

    @Test
    void testIndexOfDifference1() {
        assertEquals(0, MyClass.indexOfDifference("css"));
    }

    @Test
    void testIndexOfDifference2() {
        assertEquals(0, MyClass.indexOfDifference("cs1", "cs2"));
    }

    @Test
    void testIndexOfIgnoreCase1() {
        assertEquals(0, MyClass.indexOfIgnoreCase("str", "searchStr"));
    }

    @Test
    void testIndexOfIgnoreCase2() {
        assertEquals(0, MyClass.indexOfIgnoreCase("str", "searchStr", 0));
    }

    @Test
    void testIsAllBlank() {
        assertTrue(MyClass.isAllBlank("css"));
    }

    @Test
    void testIsAllEmpty() {
        assertTrue(MyClass.isAllEmpty("css"));
    }

    @Test
    void testIsAllLowerCase() {
        assertTrue(MyClass.isAllLowerCase("cs"));
    }

    @Test
    void testIsAllUpperCase() {
        assertTrue(MyClass.isAllUpperCase("cs"));
    }

    @Test
    void testIsAlpha() {
        assertTrue(MyClass.isAlpha("cs"));
    }

    @Test
    void testIsAlphanumeric() {
        assertTrue(MyClass.isAlphanumeric("cs"));
    }

    @Test
    void testIsAlphanumericSpace() {
        assertTrue(MyClass.isAlphanumericSpace("cs"));
    }

    @Test
    void testIsAlphaSpace() {
        assertTrue(MyClass.isAlphaSpace("cs"));
    }

    @Test
    void testIsAnyBlank() {
        assertTrue(MyClass.isAnyBlank("css"));
    }

    @Test
    void testIsAnyEmpty() {
        assertTrue(MyClass.isAnyEmpty("css"));
    }

    @Test
    void testIsAsciiPrintable() {
        assertTrue(MyClass.isAsciiPrintable("cs"));
    }

    @Test
    void testIsBlank() {
        assertTrue(MyClass.isBlank("cs"));
    }

    @Test
    void testIsEmpty() {
        assertTrue(MyClass.isEmpty("cs"));
    }

    @Test
    void testIsMixedCase() {
        assertTrue(MyClass.isMixedCase("cs"));
    }

    @Test
    void testIsNoneBlank() {
        assertTrue(MyClass.isNoneBlank("css"));
    }

    @Test
    void testIsNoneEmpty() {
        assertTrue(MyClass.isNoneEmpty("css"));
    }

    @Test
    void testIsNotBlank() {
        assertTrue(MyClass.isNotBlank("cs"));
    }

    @Test
    void testIsNotEmpty() {
        assertTrue(MyClass.isNotEmpty("cs"));
    }

    @Test
    void testIsNumeric() {
        assertTrue(MyClass.isNumeric("cs"));
    }

    @Test
    void testIsNumericSpace() {
        assertTrue(MyClass.isNumericSpace("cs"));
    }

    @Test
    void testIsWhitespace() {
        assertTrue(MyClass.isWhitespace("cs"));
    }

    @Test
    void testJoin1() {
        assertEquals("", MyClass.join("content".getBytes(), 'a'));
    }

    @Test
    void testJoin2() {
        assertEquals("", MyClass.join("content".getBytes(), 'a', 0, 0));
    }

    @Test
    void testJoin3() {
        assertEquals("", MyClass.join(new char[]{'a'}, 'a'));
    }

    @Test
    void testJoin4() {
        assertEquals("", MyClass.join(new char[]{'a'}, 'a', 0, 0));
    }

    @Test
    void testJoin5() {
        assertEquals("", MyClass.join(new double[]{0.0}, 'a'));
    }

    @Test
    void testJoin6() {
        assertEquals("", MyClass.join(new double[]{0.0}, 'a', 0, 0));
    }

    @Test
    void testJoin7() {
        assertEquals("", MyClass.join(new float[]{0.0f}, 'a'));
    }

    @Test
    void testJoin8() {
        assertEquals("", MyClass.join(new float[]{0.0f}, 'a', 0, 0));
    }

    @Test
    void testJoin9() {
        assertEquals("", MyClass.join(new int[]{0}, 'a'));
    }

    @Test
    void testJoin10() {
        assertEquals("", MyClass.join(new int[]{0}, 'a', 0, 0));
    }

    @Test
    void testJoin11() {
        assertEquals("", MyClass.join(Arrays.asList("value"), 'a'));
    }

    @Test
    void testJoin12() {
        assertEquals("result", MyClass.join(Arrays.asList("value"), "separator"));
    }

    @Test
    void testJoin13() {
        assertEquals("", MyClass.join(Arrays.asList("value").iterator(), 'a'));
    }

    @Test
    void testJoin14() {
        assertEquals("", MyClass.join(Arrays.asList("value").iterator(), "separator"));
    }

    @Test
    void testJoin15() {
        assertEquals("", MyClass.join(Arrays.asList("value"), 'a', 0, 0));
    }

    @Test
    void testJoin16() {
        assertEquals("", MyClass.join(Arrays.asList("value"), "separator", 0, 0));
    }

    @Test
    void testJoin17() {
        assertEquals("", MyClass.join(new long[]{0L}, 'a'));
    }

    @Test
    void testJoin18() {
        assertEquals("", MyClass.join(new long[]{0L}, 'a', 0, 0));
    }

    @Test
    void testJoin19() {
        assertEquals("result", MyClass.join(new Object[]{"array"}, 'a'));
    }

    @Test
    void testJoin20() {
        assertEquals("", MyClass.join(new Object[]{"array"}, 'a', 0, 0));
    }

    @Test
    void testJoin21() {
        assertEquals("result", MyClass.join(new Object[]{"array"}, "separator"));
    }

    @Test
    void testJoin22() {
        assertEquals("", MyClass.join(new Object[]{"array"}, "separator", 0, 0));
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> MyClass.join(new Object[]{"array"}, "separator", 0, 0));
    }

    @Test
    void testJoin23() {
        assertEquals("", MyClass.join(new short[]{(short) 0}, 'a'));
    }

    @Test
    void testJoin24() {
        assertEquals("", MyClass.join(new short[]{(short) 0}, 'a', 0, 0));
    }

    @Test
    void testJoin25() {
        assertEquals("result", MyClass.join("elements"));
    }

    @Test
    void testJoinWith() {
        assertEquals("result", MyClass.joinWith("separator", "objects"));
        assertThrows(IllegalArgumentException.class, () -> MyClass.joinWith("separator", "objects"));
    }

    @Test
    void testLastIndexOf1() {
        assertEquals(0, MyClass.lastIndexOf("seq", "searchSeq"));
    }

    @Test
    void testLastIndexOf2() {
        assertEquals(0, MyClass.lastIndexOf("seq", "searchSeq", 0));
    }

    @Test
    void testLastIndexOf3() {
        assertEquals(0, MyClass.lastIndexOf("seq", 0));
    }

    @Test
    void testLastIndexOf4() {
        assertEquals(0, MyClass.lastIndexOf("seq", 0, 0));
    }

    @Test
    void testLastIndexOfAny() {
        assertEquals(0, MyClass.lastIndexOfAny("str", "searchStrs"));
    }

    @Test
    void testLastIndexOfIgnoreCase1() {
        assertEquals(0, MyClass.lastIndexOfIgnoreCase("str", "searchStr"));
    }

    @Test
    void testLastIndexOfIgnoreCase2() {
        assertEquals(0, MyClass.lastIndexOfIgnoreCase("str", "searchStr", 0));
    }

    @Test
    void testLastOrdinalIndexOf() {
        assertEquals(0, MyClass.lastOrdinalIndexOf("str", "searchStr", 0));
    }

    @Test
    void testLeft() {
        assertEquals("str", MyClass.left("str", 0));
    }

    @Test
    void testLeftPad1() {
        assertEquals("result", MyClass.leftPad("str", 0));
        assertNull(MyClass.leftPad("str", 0));
    }

    @Test
    void testLeftPad2() {
        assertEquals("str", MyClass.leftPad("str", 0, 'a'));
        assertNull(MyClass.leftPad("str", 0, 'a'));
    }

    @Test
    void testLeftPad3() {
        assertEquals("str", MyClass.leftPad("str", 0, "padStr"));
        assertNull(MyClass.leftPad("str", 0, "padStr"));
    }

    @Test
    void testLength() {
        assertEquals(0, MyClass.length("cs"));
    }

    @Test
    void testLowerCase1() {
        assertEquals("result", MyClass.lowerCase("str"));
    }

    @Test
    void testLowerCase2() {
        assertEquals("result", MyClass.lowerCase("str", Locale.US));
    }

    @Test
    void testMid() {
        assertEquals("", MyClass.mid("str", 0, 0));
    }

    @Test
    void testNormalizeSpace() {
        assertEquals("str", MyClass.normalizeSpace("str"));
        assertNull(MyClass.normalizeSpace("str"));
    }

    @Test
    void testOrdinalIndexOf1() {
        assertEquals(0, MyClass.ordinalIndexOf("str", "searchStr", 0));
    }

    @Test
    void testOverlay() {
        assertEquals("result", MyClass.overlay("str", "overlay", 0, 0));
    }

    @Test
    void testPrependIfMissing2() {
        assertEquals("result", MyClass.prependIfMissing("str", "prefix", "prefixes"));
    }

    @Test
    void testPrependIfMissingIgnoreCase() {
        assertEquals("result", MyClass.prependIfMissingIgnoreCase("str", "prefix", "prefixes"));
    }

    @Test
    void testRemove1() {
        assertEquals("str", MyClass.remove("str", 'a'));
    }

    @Test
    void testRemove2() {
        assertEquals("str", MyClass.remove("str", "remove"));
    }

    @Test
    void testRemoveAll() {
        assertEquals("result", MyClass.removeAll("text", "regex"));
        assertThrows(PatternSyntaxException.class, () -> MyClass.removeAll("text", "regex"));
    }

    @Test
    void testRemoveEnd() {
        assertEquals("str", MyClass.removeEnd("str", "remove"));
    }

    @Test
    void testRemoveEndIgnoreCase() {
        assertEquals("str", MyClass.removeEndIgnoreCase("str", "remove"));
    }

    @Test
    void testRemoveFirst() {
        assertEquals("result", MyClass.removeFirst("text", "regex"));
        assertThrows(PatternSyntaxException.class, () -> MyClass.removeFirst("text", "regex"));
    }

    @Test
    void testRemoveIgnoreCase() {
        assertEquals("str", MyClass.removeIgnoreCase("str", "remove"));
    }

    @Test
    void testRemovePattern() {
        assertEquals("result", MyClass.removePattern("source", "regex"));
    }

    @Test
    void testRemoveStart() {
        assertEquals("str", MyClass.removeStart("str", "remove"));
    }

    @Test
    void testRemoveStartIgnoreCase() {
        assertEquals("str", MyClass.removeStartIgnoreCase("str", "remove"));
    }

    @Test
    void testRepeat1() {
        assertEquals("str", MyClass.repeat('a', 0));
    }

    @Test
    void testRepeat2() {
        assertEquals("str", MyClass.repeat("str", 0));
        assertNull(MyClass.repeat("str", 0));
    }

    @Test
    void testRepeat3() {
        assertEquals("result", MyClass.repeat("str", "separator", 0));
        assertNull(MyClass.repeat("str", "separator", 0));
    }

    @Test
    void testReplace1() {
        assertEquals("result", MyClass.replace("text", "searchString", "replacement"));
    }

    @Test
    void testReplace2() {
        assertEquals("result", MyClass.replace("text", "searchString", "replacement", 0));
    }

    @Test
    void testReplaceAll() {
        assertEquals("result", MyClass.replaceAll("text", "regex", "replacement"));
        assertThrows(PatternSyntaxException.class, () -> MyClass.replaceAll("text", "regex", "replacement"));
    }

    @Test
    void testReplaceChars1() {
        assertEquals("result", MyClass.replaceChars("str", 'a', 'a'));
    }

    @Test
    void testReplaceChars2() {
        assertEquals("str", MyClass.replaceChars("str", "searchChars", "replaceChars"));
    }

    @Test
    void testReplaceEach1() {
        assertEquals("result",
                MyClass.replaceEach("text", new String[]{"searchList"}, new String[]{"replacementList"}));
        assertThrows(IllegalArgumentException.class,
                () -> MyClass.replaceEach("text", new String[]{"searchList"}, new String[]{"replacementList"}));
    }

    @Test
    void testReplaceEachRepeatedly() {
        assertEquals("result",
                MyClass.replaceEachRepeatedly("text", new String[]{"searchList"}, new String[]{"replacementList"}));
        assertThrows(IllegalStateException.class,
                () -> MyClass.replaceEachRepeatedly("text", new String[]{"searchList"},
                        new String[]{"replacementList"}));
        assertThrows(IllegalArgumentException.class,
                () -> MyClass.replaceEachRepeatedly("text", new String[]{"searchList"},
                        new String[]{"replacementList"}));
    }

    @Test
    void testReplaceFirst() {
        assertEquals("result", MyClass.replaceFirst("text", "regex", "replacement"));
        assertThrows(PatternSyntaxException.class, () -> MyClass.replaceFirst("text", "regex", "replacement"));
    }

    @Test
    void testReplaceIgnoreCase1() {
        assertEquals("result", MyClass.replaceIgnoreCase("text", "searchString", "replacement"));
    }

    @Test
    void testReplaceIgnoreCase2() {
        assertEquals("result", MyClass.replaceIgnoreCase("text", "searchString", "replacement", 0));
    }

    @Test
    void testReplaceOnce() {
        assertEquals("result", MyClass.replaceOnce("text", "searchString", "replacement"));
    }

    @Test
    void testReplaceOnceIgnoreCase() {
        assertEquals("result", MyClass.replaceOnceIgnoreCase("text", "searchString", "replacement"));
    }

    @Test
    void testReplacePattern() {
        assertEquals("result", MyClass.replacePattern("source", "regex", "replacement"));
    }

    @Test
    void testReverse() {
        assertEquals("result", MyClass.reverse("str"));
    }

    @Test
    void testReverseDelimited() {
        assertEquals("result", MyClass.reverseDelimited("str", 'a'));
    }

    @Test
    void testRight() {
        assertEquals("str", MyClass.right("str", 0));
    }

    @Test
    void testRightPad1() {
        assertEquals("result", MyClass.rightPad("str", 0));
        assertNull(MyClass.rightPad("str", 0));
    }

    @Test
    void testRightPad2() {
        assertEquals("str", MyClass.rightPad("str", 0, 'a'));
        assertNull(MyClass.rightPad("str", 0, 'a'));
    }

    @Test
    void testRightPad3() {
        assertEquals("str", MyClass.rightPad("str", 0, "padStr"));
        assertNull(MyClass.rightPad("str", 0, "padStr"));
    }

    @Test
    void testRotate() {
        assertEquals("str", MyClass.rotate("str", 0));
        assertNull(MyClass.rotate("str", 0));
    }

    @Test
    void testSplit1() {
        assertArrayEquals(new String[]{"result"}, MyClass.split("str"));
        assertArrayEquals(new String[]{}, MyClass.split("str"));
    }

    @Test
    void testSplit2() {
        assertArrayEquals(new String[]{"result"}, MyClass.split("str", 'a'));
        assertArrayEquals(new String[]{}, MyClass.split("str", 'a'));
    }

    @Test
    void testSplit3() {
        assertArrayEquals(new String[]{"result"}, MyClass.split("str", "separatorChars"));
        assertArrayEquals(new String[]{}, MyClass.split("str", "separatorChars"));
    }

    @Test
    void testSplit4() {
        assertArrayEquals(new String[]{"result"}, MyClass.split("str", "separatorChars", 0));
        assertArrayEquals(new String[]{}, MyClass.split("str", "separatorChars", 0));
    }

    @Test
    void testSplitByCharacterType1() {
        assertArrayEquals(new String[]{"result"}, MyClass.splitByCharacterType("str"));
        assertArrayEquals(new String[]{}, MyClass.splitByCharacterType("str"));
    }

    @Test
    void testSplitByCharacterTypeCamelCase() {
        assertArrayEquals(new String[]{"result"}, MyClass.splitByCharacterTypeCamelCase("str"));
        assertArrayEquals(new String[]{}, MyClass.splitByCharacterTypeCamelCase("str"));
    }

    @Test
    void testSplitByWholeSeparator1() {
        assertArrayEquals(new String[]{"result"}, MyClass.splitByWholeSeparator("str", "separator"));
        assertArrayEquals(new String[]{}, MyClass.splitByWholeSeparator("str", "separator"));
    }

    @Test
    void testSplitByWholeSeparator2() {
        assertArrayEquals(new String[]{"result"}, MyClass.splitByWholeSeparator("str", "separator", 0));
        assertArrayEquals(new String[]{}, MyClass.splitByWholeSeparator("str", "separator", 0));
    }

    @Test
    void testSplitByWholeSeparatorPreserveAllTokens1() {
        assertArrayEquals(new String[]{"result"}, MyClass.splitByWholeSeparatorPreserveAllTokens("str", "separator"));
        assertArrayEquals(new String[]{}, MyClass.splitByWholeSeparatorPreserveAllTokens("str", "separator"));
    }

    @Test
    void testSplitByWholeSeparatorPreserveAllTokens2() {
        assertArrayEquals(new String[]{"result"},
                MyClass.splitByWholeSeparatorPreserveAllTokens("str", "separator", 0));
        assertArrayEquals(new String[]{}, MyClass.splitByWholeSeparatorPreserveAllTokens("str", "separator", 0));
    }

    @Test
    void testSplitPreserveAllTokens1() {
        assertArrayEquals(new String[]{"result"}, MyClass.splitPreserveAllTokens("str"));
        assertArrayEquals(new String[]{}, MyClass.splitPreserveAllTokens("str"));
    }

    @Test
    void testSplitPreserveAllTokens2() {
        assertArrayEquals(new String[]{"result"}, MyClass.splitPreserveAllTokens("str", 'a'));
        assertArrayEquals(new String[]{}, MyClass.splitPreserveAllTokens("str", 'a'));
    }

    @Test
    void testSplitPreserveAllTokens3() {
        assertArrayEquals(new String[]{"result"}, MyClass.splitPreserveAllTokens("str", "separatorChars"));
        assertArrayEquals(new String[]{}, MyClass.splitPreserveAllTokens("str", "separatorChars"));
    }

    @Test
    void testSplitPreserveAllTokens4() {
        assertArrayEquals(new String[]{"result"}, MyClass.splitPreserveAllTokens("str", "separatorChars", 0));
        assertArrayEquals(new String[]{}, MyClass.splitPreserveAllTokens("str", "separatorChars", 0));
    }

    @Test
    void testStartsWith1() {
        assertTrue(MyClass.startsWith("str", "prefix"));
    }

    @Test
    void testStartsWithAny() {
        assertTrue(MyClass.startsWithAny("sequence", "searchStrings"));
    }

    @Test
    void testStartsWithIgnoreCase() {
        assertTrue(MyClass.startsWithIgnoreCase("str", "prefix"));
    }

    @Test
    void testStrip1() {
        assertEquals("result", MyClass.strip("str"));
    }

    @Test
    void testStrip2() {
        assertEquals("str", MyClass.strip("str", "stripChars"));
    }

    @Test
    void testStripAccents() {
        assertEquals("result", MyClass.stripAccents("input"));
    }

    @Test
    void testStripAll1() {
        assertArrayEquals(new String[]{"result"}, MyClass.stripAll("strs"));
        assertArrayEquals(new String[]{}, MyClass.stripAll("strs"));
    }

    @Test
    void testStripAll2() {
        assertArrayEquals(new String[]{"result"}, MyClass.stripAll(new String[]{"strs"}, "stripChars"));
        assertArrayEquals(new String[]{}, MyClass.stripAll(new String[]{"strs"}, "stripChars"));
    }

    @Test
    void testStripEnd() {
        assertEquals("str", MyClass.stripEnd("str", "stripChars"));
    }

    @Test
    void testStripStart() {
        assertEquals("str", MyClass.stripStart("str", "stripChars"));
    }

    @Test
    void testStripToEmpty() {
        assertEquals("result", MyClass.stripToEmpty("str"));
    }

    @Test
    void testStripToNull() {
        assertEquals("result", MyClass.stripToNull("str"));
        assertNull(MyClass.stripToNull("str"));
    }

    @Test
    void testSubstring1() {
        assertEquals("", MyClass.substring("str", 0));
    }

    @Test
    void testSubstring2() {
        assertEquals("", MyClass.substring("str", 0, 0));
    }

    @Test
    void testSubstringAfter1() {
        assertEquals("str", MyClass.substringAfter("str", 0));
        assertNull(MyClass.substringAfter("str", 0));
    }

    @Test
    void testSubstringAfter2() {
        assertEquals("str", MyClass.substringAfter("str", "separator"));
        assertNull(MyClass.substringAfter("str", "separator"));
    }

    @Test
    void testSubstringAfterLast1() {
        assertEquals("str", MyClass.substringAfterLast("str", 0));
        assertNull(MyClass.substringAfterLast("str", 0));
    }

    @Test
    void testSubstringAfterLast2() {
        assertEquals("str", MyClass.substringAfterLast("str", "separator"));
        assertNull(MyClass.substringAfterLast("str", "separator"));
    }

    @Test
    void testSubstringBefore() {
        assertEquals("str", MyClass.substringBefore("str", "separator"));
        assertNull(MyClass.substringBefore("str", "separator"));
    }

    @Test
    void testSubstringBeforeLast() {
        assertEquals("str", MyClass.substringBeforeLast("str", "separator"));
        assertNull(MyClass.substringBeforeLast("str", "separator"));
    }

    @Test
    void testSubstringBetween1() {
        assertEquals("result", MyClass.substringBetween("str", "tag"));
        assertNull(MyClass.substringBetween("str", "tag"));
    }

    @Test
    void testSubstringBetween2() {
        assertEquals("result", MyClass.substringBetween("str", "open", "close"));
    }

    @Test
    void testSubstringsBetween() {
        assertArrayEquals(new String[]{"result"}, MyClass.substringsBetween("str", "open", "close"));
        assertNull(MyClass.substringsBetween("str", "open", "close"));
        assertArrayEquals(new String[]{}, MyClass.substringsBetween("str", "open", "close"));
    }

    @Test
    void testSwapCase() {
        assertEquals("str", MyClass.swapCase("str"));
    }

    @Test
    void testToCodePoints() {
        assertArrayEquals(new int[]{0}, MyClass.toCodePoints("str"));
        assertArrayEquals(new int[]{}, MyClass.toCodePoints("str"));
    }

    @Test
    void testToEncodedString() {
        assertEquals("result", MyClass.toEncodedString("content".getBytes(), StandardCharsets.UTF_8));
        assertThrows(NullPointerException.class,
                () -> MyClass.toEncodedString("content".getBytes(), StandardCharsets.UTF_8));
    }

    @Test
    void testToRootLowerCase() {
        assertEquals("result", MyClass.toRootLowerCase("source"));
        assertNull(MyClass.toRootLowerCase("source"));
    }

    @Test
    void testToRootUpperCase() {
        assertEquals("result", MyClass.toRootUpperCase("source"));
        assertNull(MyClass.toRootUpperCase("source"));
    }

    @Test
    void testToString() throws Exception {
        assertEquals("result", MyClass.toString("content".getBytes(), "UTF-8"));
        assertThrows(UnsupportedEncodingException.class, () -> MyClass.toString("content".getBytes(), "UTF-8"));
        assertThrows(NullPointerException.class, () -> MyClass.toString("content".getBytes(), "UTF-8"));
    }

    @Test
    void testTrim() {
        assertEquals("result", MyClass.trim("str"));
    }

    @Test
    void testTrimToEmpty() {
        assertEquals("result", MyClass.trimToEmpty("str"));
    }

    @Test
    void testTrimToNull() {
        assertEquals("result", MyClass.trimToNull("str"));
        assertNull(MyClass.trimToNull("str"));
    }

    @Test
    void testTruncate1() {
        assertEquals("result", MyClass.truncate("str", 0));
        assertThrows(IllegalArgumentException.class, () -> MyClass.truncate("str", 0));
    }

    @Test
    void testTruncate2() {
        assertEquals("", MyClass.truncate("str", 0, 0));
        assertThrows(IllegalArgumentException.class, () -> MyClass.truncate("str", 0, 0));
    }

    @Test
    void testUncapitalize() {
        assertEquals("str", MyClass.uncapitalize("str"));
    }

    @Test
    void testUnwrap1() {
        assertEquals("str", MyClass.unwrap("str", 'a'));
    }

    @Test
    void testUnwrap2() {
        assertEquals("str", MyClass.unwrap("str", "wrapToken"));
    }

    @Test
    void testUpperCase1() {
        assertEquals("result", MyClass.upperCase("str"));
    }

    @Test
    void testUpperCase2() {
        assertEquals("result", MyClass.upperCase("str", Locale.US));
    }

    @Test
    void testValueOf() {
        assertEquals("result", MyClass.valueOf(new char[]{'a'}));
        assertNull(MyClass.valueOf(new char[]{'a'}));
    }

    @Test
    void testWrap1() {
        assertEquals("str", MyClass.wrap("str", 'a'));
        assertNull(MyClass.wrap("str", 'a'));
    }

    @Test
    void testWrap2() {
        assertEquals("str", MyClass.wrap("str", "wrapWith"));
    }

    @Test
    void testWrapIfMissing1() {
        assertEquals("str", MyClass.wrapIfMissing("str", 'a'));
        assertNull(MyClass.wrapIfMissing("str", 'a'));
    }

    @Test
    void testWrapIfMissing2() {
        assertEquals("str", MyClass.wrapIfMissing("str", "wrapWith"));
        assertNull(MyClass.wrapIfMissing("str", "wrapWith"));
    }
}
