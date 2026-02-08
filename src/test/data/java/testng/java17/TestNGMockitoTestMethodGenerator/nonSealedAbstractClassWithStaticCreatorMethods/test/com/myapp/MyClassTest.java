package com.myapp;

import org.testng.annotations.Test;

import java.io.File;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.*;

public class MyClassTest {

    @Test
    public void testParse1() {
        // Run the test
        final MyClass result = MyClass.parse("uriString");
        assertFalse(result.isHierarchical());
        assertFalse(result.isOpaque());
        assertFalse(result.isRelative());
        assertFalse(result.isAbsolute());
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
        assertEquals(List.of("value"), result.getPathSegments());
        assertEquals("result", result.getLastPathSegment());
        assertFalse(result.equals("o"));
        assertEquals(0, result.hashCode());
        final MyClass other = MyClass.parse("uriString");
        assertEquals(0, result.compareTo(other));
        assertEquals("result", result.toString());
        assertEquals("result", result.toSafeString());
        assertEquals(new MyClass.Builder(), result.buildUpon());
        assertEquals(Set.of("value"), result.getQueryParameterNames());
        assertEquals(List.of("value"), result.getQueryParameters("key"));
        assertEquals("", result.getQueryParameter("key"));
        assertFalse(result.getBooleanQueryParameter("key", false));
        assertEquals(MyClass.parse("uriString"), result.normalizeScheme());
        assertEquals(MyClass.parse("uriString"), result.getCanonicalUri());
        final MyClass prefix = MyClass.parse("uriString");
        assertFalse(result.isPathPrefixMatch(prefix));
    }

    @Test(expectedExceptions = {NullPointerException.class})
    public void testParse_ThrowsNullPointerException1() {
        // Setup
        // Run the test
        MyClass.parse("uriString");
    }

    @Test
    public void testFromFile1() {
        // Setup
        final File file = new File("filename.txt");

        // Run the test
        final MyClass result = MyClass.fromFile(file);
        assertFalse(result.isHierarchical());
        assertFalse(result.isOpaque());
        assertFalse(result.isRelative());
        assertFalse(result.isAbsolute());
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
        assertEquals(List.of("value"), result.getPathSegments());
        assertEquals("result", result.getLastPathSegment());
        assertFalse(result.equals("o"));
        assertEquals(0, result.hashCode());
        final MyClass other = MyClass.parse("uriString");
        assertEquals(0, result.compareTo(other));
        assertEquals("result", result.toString());
        assertEquals("result", result.toSafeString());
        assertEquals(new MyClass.Builder(), result.buildUpon());
        assertEquals(Set.of("value"), result.getQueryParameterNames());
        assertEquals(List.of("value"), result.getQueryParameters("key"));
        assertEquals("", result.getQueryParameter("key"));
        assertFalse(result.getBooleanQueryParameter("key", false));
        assertEquals(MyClass.parse("uriString"), result.normalizeScheme());
        assertEquals(MyClass.parse("uriString"), result.getCanonicalUri());
        final MyClass prefix = MyClass.parse("uriString");
        assertFalse(result.isPathPrefixMatch(prefix));
    }

    @Test(expectedExceptions = {NullPointerException.class})
    public void testFromFile_ThrowsNullPointerException1() {
        // Setup
        final File file = new File("filename.txt");

        // Run the test
        MyClass.fromFile(file);
    }

    @Test
    public void testFromParts1() {
        // Run the test
        final MyClass result = MyClass.fromParts("scheme", "fragment", "fragment");
        assertFalse(result.isHierarchical());
        assertFalse(result.isOpaque());
        assertFalse(result.isRelative());
        assertFalse(result.isAbsolute());
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
        assertEquals(List.of("value"), result.getPathSegments());
        assertEquals("result", result.getLastPathSegment());
        assertFalse(result.equals("o"));
        assertEquals(0, result.hashCode());
        final MyClass other = MyClass.parse("uriString");
        assertEquals(0, result.compareTo(other));
        assertEquals("result", result.toString());
        assertEquals("result", result.toSafeString());
        assertEquals(new MyClass.Builder(), result.buildUpon());
        assertEquals(Set.of("value"), result.getQueryParameterNames());
        assertEquals(List.of("value"), result.getQueryParameters("key"));
        assertEquals("", result.getQueryParameter("key"));
        assertFalse(result.getBooleanQueryParameter("key", false));
        assertEquals(MyClass.parse("uriString"), result.normalizeScheme());
        assertEquals(MyClass.parse("uriString"), result.getCanonicalUri());
        final MyClass prefix = MyClass.parse("uriString");
        assertFalse(result.isPathPrefixMatch(prefix));
    }

    @Test(expectedExceptions = {NullPointerException.class})
    public void testFromParts_ThrowsNullPointerException1() {
        // Setup
        // Run the test
        MyClass.fromParts("scheme", "fragment", "fragment");
    }

    @Test
    public void testWriteToParcel1() {
        MyClass.writeToParcel("out", MyClass.parse("uriString"));
    }

    @Test
    public void testEncode11() {
        assertEquals("result", MyClass.encode("s"));
        assertNull(MyClass.encode("s"));
    }

    @Test
    public void testEncode21() {
        assertEquals("s", MyClass.encode("s", "allow"));
        assertNull(MyClass.encode("s", "allow"));
    }

    @Test
    public void testDecode1() {
        assertEquals("s", MyClass.decode("s"));
        assertNull(MyClass.decode("s"));
    }

    @Test
    public void testWithAppendedPath1() {
        // Setup
        final MyClass baseUri = MyClass.parse("uriString");
        final MyClass expectedResult = MyClass.parse("uriString");

        // Run the test
        final MyClass result = MyClass.withAppendedPath(baseUri, "pathSegment");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test(expectedExceptions = {NullPointerException.class})
    public void testWithAppendedPath_ThrowsNullPointerException1() {
        // Setup
        final MyClass baseUri = MyClass.parse("uriString");

        // Run the test
        MyClass.withAppendedPath(baseUri, "pathSegment");
    }
}