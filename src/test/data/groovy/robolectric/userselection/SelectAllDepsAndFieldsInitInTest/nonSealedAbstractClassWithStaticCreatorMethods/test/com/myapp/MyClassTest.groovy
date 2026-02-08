package com.myapp

import groovy.transform.CompileStatic
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

import static org.junit.Assert.assertFalse
import static org.junit.Assert.assertNull

@CompileStatic
@RunWith(RobolectricTestRunner.class)
class MyClassTest {

    @Test
    void testParse() {
        // Run the test
        def result = MyClass.parse("uriString")
        assertFalse(result.isHierarchical())
        assertFalse(result.isOpaque())
        assertFalse(result.isRelative())
        assertFalse(result.isAbsolute())
        assert "result" == result.getScheme()
        assert "result" == result.getSchemeSpecificPart()
        assert "result" == result.getEncodedSchemeSpecificPart()
        assert "result" == result.getAuthority()
        assert "result" == result.getEncodedAuthority()
        assert "result" == result.getUserInfo()
        assert "result" == result.getEncodedUserInfo()
        assert "result" == result.getHost()
        assert 0 == result.getPort()
        assert "result" == result.getPath()
        assert "result" == result.getEncodedPath()
        assert "result" == result.getQuery()
        assert "result" == result.getEncodedQuery()
        assert "result" == result.getFragment()
        assert "result" == result.getEncodedFragment()
        assert ["value"] == result.getPathSegments()
        assert "result" == result.getLastPathSegment()
        assertFalse(result.equals("o"))
        assert 0 == result.hashCode()
        def other = MyClass.parse("uriString")
        assert 0 == result.compareTo(other)
        assert "result" == result.toString()
        assert "result" == result.toSafeString()
        assert new MyClass.Builder() == result.buildUpon()
        assert new HashSet<>(["value"]) == result.getQueryParameterNames()
        assert ["value"] == result.getQueryParameters("key")
        assert "" == result.getQueryParameter("key")
        assertFalse(result.getBooleanQueryParameter("key", false))
        assert MyClass.parse("uriString") == result.normalizeScheme()
        assert MyClass.parse("uriString") == result.getCanonicalUri()
        def prefix = MyClass.parse("uriString")
        assertFalse(result.isPathPrefixMatch(prefix))
    }

    @Test(expected = NullPointerException.class)
    void testParse_ThrowsNullPointerException() {
        // Setup
        // Run the test
        MyClass.parse("uriString")
    }

    @Test
    void testFromFile() {
        // Setup
        def file = new File("filename.txt")

        // Run the test
        def result = MyClass.fromFile(file)
        assertFalse(result.isHierarchical())
        assertFalse(result.isOpaque())
        assertFalse(result.isRelative())
        assertFalse(result.isAbsolute())
        assert "result" == result.getScheme()
        assert "result" == result.getSchemeSpecificPart()
        assert "result" == result.getEncodedSchemeSpecificPart()
        assert "result" == result.getAuthority()
        assert "result" == result.getEncodedAuthority()
        assert "result" == result.getUserInfo()
        assert "result" == result.getEncodedUserInfo()
        assert "result" == result.getHost()
        assert 0 == result.getPort()
        assert "result" == result.getPath()
        assert "result" == result.getEncodedPath()
        assert "result" == result.getQuery()
        assert "result" == result.getEncodedQuery()
        assert "result" == result.getFragment()
        assert "result" == result.getEncodedFragment()
        assert ["value"] == result.getPathSegments()
        assert "result" == result.getLastPathSegment()
        assertFalse(result.equals("o"))
        assert 0 == result.hashCode()
        def other = MyClass.parse("uriString")
        assert 0 == result.compareTo(other)
        assert "result" == result.toString()
        assert "result" == result.toSafeString()
        assert new MyClass.Builder() == result.buildUpon()
        assert new HashSet<>(["value"]) == result.getQueryParameterNames()
        assert ["value"] == result.getQueryParameters("key")
        assert "" == result.getQueryParameter("key")
        assertFalse(result.getBooleanQueryParameter("key", false))
        assert MyClass.parse("uriString") == result.normalizeScheme()
        assert MyClass.parse("uriString") == result.getCanonicalUri()
        def prefix = MyClass.parse("uriString")
        assertFalse(result.isPathPrefixMatch(prefix))
    }

    @Test(expected = NullPointerException.class)
    void testFromFile_ThrowsNullPointerException() {
        // Setup
        def file = new File("filename.txt")

        // Run the test
        MyClass.fromFile(file)
    }

    @Test
    void testFromParts() {
        // Run the test
        def result = MyClass.fromParts("scheme", "fragment", "fragment")
        assertFalse(result.isHierarchical())
        assertFalse(result.isOpaque())
        assertFalse(result.isRelative())
        assertFalse(result.isAbsolute())
        assert "result" == result.getScheme()
        assert "result" == result.getSchemeSpecificPart()
        assert "result" == result.getEncodedSchemeSpecificPart()
        assert "result" == result.getAuthority()
        assert "result" == result.getEncodedAuthority()
        assert "result" == result.getUserInfo()
        assert "result" == result.getEncodedUserInfo()
        assert "result" == result.getHost()
        assert 0 == result.getPort()
        assert "result" == result.getPath()
        assert "result" == result.getEncodedPath()
        assert "result" == result.getQuery()
        assert "result" == result.getEncodedQuery()
        assert "result" == result.getFragment()
        assert "result" == result.getEncodedFragment()
        assert ["value"] == result.getPathSegments()
        assert "result" == result.getLastPathSegment()
        assertFalse(result.equals("o"))
        assert 0 == result.hashCode()
        def other = MyClass.parse("uriString")
        assert 0 == result.compareTo(other)
        assert "result" == result.toString()
        assert "result" == result.toSafeString()
        assert new MyClass.Builder() == result.buildUpon()
        assert new HashSet<>(["value"]) == result.getQueryParameterNames()
        assert ["value"] == result.getQueryParameters("key")
        assert "" == result.getQueryParameter("key")
        assertFalse(result.getBooleanQueryParameter("key", false))
        assert MyClass.parse("uriString") == result.normalizeScheme()
        assert MyClass.parse("uriString") == result.getCanonicalUri()
        def prefix = MyClass.parse("uriString")
        assertFalse(result.isPathPrefixMatch(prefix))
    }

    @Test(expected = NullPointerException.class)
    void testFromParts_ThrowsNullPointerException() {
        // Setup
        // Run the test
        MyClass.fromParts("scheme", "fragment", "fragment")
    }

    @Test
    void testWriteToParcel() {
        MyClass.writeToParcel("out", MyClass.parse("uriString"))
    }

    @Test
    void testEncode1() {
        assert "result" == MyClass.encode("s")
        assertNull(MyClass.encode("s"))
    }

    @Test
    void testEncode2() {
        assert "s" == MyClass.encode("s", "allow")
        assertNull(MyClass.encode("s", "allow"))
    }

    @Test
    void testDecode() {
        assert "s" == MyClass.decode("s")
        assertNull(MyClass.decode("s"))
    }

    @Test
    void testWithAppendedPath() {
        // Setup
        def baseUri = MyClass.parse("uriString")
        def expectedResult = MyClass.parse("uriString")

        // Run the test
        def result = MyClass.withAppendedPath(baseUri, "pathSegment")

        // Verify the results
        assert expectedResult == result
    }

    @Test(expected = NullPointerException.class)
    void testWithAppendedPath_ThrowsNullPointerException() {
        // Setup
        def baseUri = MyClass.parse("uriString")

        // Run the test
        MyClass.withAppendedPath(baseUri, "pathSegment")
    }
}
