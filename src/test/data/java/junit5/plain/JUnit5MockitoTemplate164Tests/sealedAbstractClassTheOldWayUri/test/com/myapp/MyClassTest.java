package com.myapp;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class MyClassTest {

    @Test
    void testParse() {
        // Run the test
        final MyClass result = MyClass.parse("uriString");
        assertTrue(result.isHierarchical());
        assertTrue(result.isOpaque());
        assertTrue(result.isRelative());
        assertTrue(result.isAbsolute());
        assertEquals("result", result.getScheme());
        assertEquals("result", result.getSchemeSpecificPart());
        assertEquals("result", result.getEncodedSchemeSpecificPart());
        assertEquals("result", result.getAuthority());
        assertEquals("result", result.getEncodedAuthority());
        assertEquals("result", result.getUserInfo());
        assertEquals("result", result.getEncodedUserInfo());
        assertEquals("result", result.getHost());
        assertEquals(0, result.getPort());
        assertEquals("result", result.getPath());
        assertEquals("result", result.getEncodedPath());
        assertEquals("result", result.getQuery());
        assertEquals("result", result.getEncodedQuery());
        assertEquals("result", result.getFragment());
        assertEquals("result", result.getEncodedFragment());
        assertEquals(Arrays.asList("value"), result.getPathSegments());
        assertEquals("result", result.getLastPathSegment());
        assertTrue(result.equals("o"));
        assertEquals(0, result.hashCode());
        final MyClass other = MyClass.parse("uriString");
        assertEquals(0, result.compareTo(other));
        assertEquals("result", result.toString());
        assertEquals("result", result.toSafeString());
        assertEquals(new MyClass.Builder(), result.buildUpon());
        assertEquals(new HashSet<>(Arrays.asList("value")), result.getQueryParameterNames());
        assertEquals(Arrays.asList("value"), result.getQueryParameters("key"));
        assertEquals("", result.getQueryParameter("key"));
        assertTrue(result.getBooleanQueryParameter("key", false));
        assertEquals(MyClass.parse("uriString"), result.normalizeScheme());
        assertEquals(MyClass.parse("uriString"), result.getCanonicalUri());
        final MyClass prefix = MyClass.parse("uriString");
        assertTrue(result.isPathPrefixMatch(prefix));
    }

    @Test
    void testParse_ThrowsNullPointerException() {
        // Setup
        // Run the test
        assertThrows(NullPointerException.class, () -> MyClass.parse("uriString"));
    }

    @Test
    void testFromFile() {
        // Setup
        final File file = new File("filename.txt");

        // Run the test
        final MyClass result = MyClass.fromFile(file);
        assertTrue(result.isHierarchical());
        assertTrue(result.isOpaque());
        assertTrue(result.isRelative());
        assertTrue(result.isAbsolute());
        assertEquals("result", result.getScheme());
        assertEquals("result", result.getSchemeSpecificPart());
        assertEquals("result", result.getEncodedSchemeSpecificPart());
        assertEquals("result", result.getAuthority());
        assertEquals("result", result.getEncodedAuthority());
        assertEquals("result", result.getUserInfo());
        assertEquals("result", result.getEncodedUserInfo());
        assertEquals("result", result.getHost());
        assertEquals(0, result.getPort());
        assertEquals("result", result.getPath());
        assertEquals("result", result.getEncodedPath());
        assertEquals("result", result.getQuery());
        assertEquals("result", result.getEncodedQuery());
        assertEquals("result", result.getFragment());
        assertEquals("result", result.getEncodedFragment());
        assertEquals(Arrays.asList("value"), result.getPathSegments());
        assertEquals("result", result.getLastPathSegment());
        assertTrue(result.equals("o"));
        assertEquals(0, result.hashCode());
        final MyClass other = MyClass.parse("uriString");
        assertEquals(0, result.compareTo(other));
        assertEquals("result", result.toString());
        assertEquals("result", result.toSafeString());
        assertEquals(new MyClass.Builder(), result.buildUpon());
        assertEquals(new HashSet<>(Arrays.asList("value")), result.getQueryParameterNames());
        assertEquals(Arrays.asList("value"), result.getQueryParameters("key"));
        assertEquals("", result.getQueryParameter("key"));
        assertTrue(result.getBooleanQueryParameter("key", false));
        assertEquals(MyClass.parse("uriString"), result.normalizeScheme());
        assertEquals(MyClass.parse("uriString"), result.getCanonicalUri());
        final MyClass prefix = MyClass.parse("uriString");
        assertTrue(result.isPathPrefixMatch(prefix));
    }

    @Test
    void testFromFile_ThrowsNullPointerException() {
        // Setup
        final File file = new File("filename.txt");

        // Run the test
        assertThrows(NullPointerException.class, () -> MyClass.fromFile(file));
    }

    @Test
    void testFromParts() {
        // Run the test
        final MyClass result = MyClass.fromParts("scheme", "fragment", "fragment");
        assertTrue(result.isHierarchical());
        assertTrue(result.isOpaque());
        assertTrue(result.isRelative());
        assertTrue(result.isAbsolute());
        assertEquals("result", result.getScheme());
        assertEquals("result", result.getSchemeSpecificPart());
        assertEquals("result", result.getEncodedSchemeSpecificPart());
        assertEquals("result", result.getAuthority());
        assertEquals("result", result.getEncodedAuthority());
        assertEquals("result", result.getUserInfo());
        assertEquals("result", result.getEncodedUserInfo());
        assertEquals("result", result.getHost());
        assertEquals(0, result.getPort());
        assertEquals("result", result.getPath());
        assertEquals("result", result.getEncodedPath());
        assertEquals("result", result.getQuery());
        assertEquals("result", result.getEncodedQuery());
        assertEquals("result", result.getFragment());
        assertEquals("result", result.getEncodedFragment());
        assertEquals(Arrays.asList("value"), result.getPathSegments());
        assertEquals("result", result.getLastPathSegment());
        assertTrue(result.equals("o"));
        assertEquals(0, result.hashCode());
        final MyClass other = MyClass.parse("uriString");
        assertEquals(0, result.compareTo(other));
        assertEquals("result", result.toString());
        assertEquals("result", result.toSafeString());
        assertEquals(new MyClass.Builder(), result.buildUpon());
        assertEquals(new HashSet<>(Arrays.asList("value")), result.getQueryParameterNames());
        assertEquals(Arrays.asList("value"), result.getQueryParameters("key"));
        assertEquals("", result.getQueryParameter("key"));
        assertTrue(result.getBooleanQueryParameter("key", false));
        assertEquals(MyClass.parse("uriString"), result.normalizeScheme());
        assertEquals(MyClass.parse("uriString"), result.getCanonicalUri());
        final MyClass prefix = MyClass.parse("uriString");
        assertTrue(result.isPathPrefixMatch(prefix));
    }

    @Test
    void testFromParts_ThrowsNullPointerException() {
        // Setup
        // Run the test
        assertThrows(NullPointerException.class, () -> MyClass.fromParts("scheme", "fragment", "fragment"));
    }

    @Test
    void testWriteToParcel() {
        // Setup
        final MyClass uri = MyClass.parse("uriString");

        // Run the test
        MyClass.writeToParcel("out", uri);

        // Verify the results
    }

    @Test
    void testEncode1() {
        assertEquals("result", MyClass.encode("s"));
        assertNull(MyClass.encode("s"));
    }

    @Test
    void testEncode2() {
        assertEquals("s", MyClass.encode("s", "allow"));
        assertNull(MyClass.encode("s", "allow"));
    }

    @Test
    void testDecode() {
        assertEquals("s", MyClass.decode("s"));
        assertNull(MyClass.decode("s"));
    }

    @Test
    void testWithAppendedPath() {
        // Setup
        final MyClass baseUri = MyClass.parse("uriString");
        final MyClass expectedResult = MyClass.parse("uriString");

        // Run the test
        final MyClass result = MyClass.withAppendedPath(baseUri, "pathSegment");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testWithAppendedPath_ThrowsNullPointerException() {
        // Setup
        final MyClass baseUri = MyClass.parse("uriString");

        // Run the test
        assertThrows(NullPointerException.class, () -> MyClass.withAppendedPath(baseUri, "pathSegment"));
    }
}
