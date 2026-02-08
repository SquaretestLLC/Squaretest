package com.myapp


import groovy.transform.CompileStatic
import org.junit.Test

import static org.assertj.core.api.Assertions.assertThat
import static org.assertj.core.api.Assertions.assertThatThrownBy

@CompileStatic
class MyClassTest {

    @Test
    void testParse1() {
        // Run the test
        def result = MyClass.parse("uriString")
        assertThat(result.isHierarchical()).isFalse()
        assertThat(result.isOpaque()).isFalse()
        assertThat(result.isRelative()).isFalse()
        assertThat(result.isAbsolute()).isFalse()
        assertThat(result.getScheme()).isEqualTo("result")
        assertThat(result.getSchemeSpecificPart()).isEqualTo("result")
        assertThat(result.getEncodedSchemeSpecificPart()).isEqualTo("result")
        assertThat(result.getAuthority()).isEqualTo("result")
        assertThat(result.getEncodedAuthority()).isEqualTo("result")
        assertThat(result.getUserInfo()).isEqualTo("result")
        assertThat(result.getEncodedUserInfo()).isEqualTo("result")
        assertThat(result.getHost()).isEqualTo("result")
        assertThat(result.getPort()).isEqualTo(0)
        assertThat(result.getPath()).isEqualTo("result")
        assertThat(result.getEncodedPath()).isEqualTo("result")
        assertThat(result.getQuery()).isEqualTo("result")
        assertThat(result.getEncodedQuery()).isEqualTo("result")
        assertThat(result.getFragment()).isEqualTo("result")
        assertThat(result.getEncodedFragment()).isEqualTo("result")
        assertThat(result.getPathSegments()).isEqualTo(["value"])
        assertThat(result.getLastPathSegment()).isEqualTo("result")
        assertThat(result.equals("o")).isFalse()
        assertThat(result.hashCode()).isEqualTo(0)
        def other = MyClass.parse("uriString")
        assertThat(result.compareTo(other)).isEqualTo(0)
        assertThat(result.toString()).isEqualTo("result")
        assertThat(result.toSafeString()).isEqualTo("result")
        assertThat(result.buildUpon()).isEqualTo(new MyClass.Builder())
        assertThat(result.getQueryParameterNames()).isEqualTo(new HashSet<>(["value"]))
        assertThat(result.getQueryParameters("key")).isEqualTo(["value"])
        assertThat(result.getQueryParameter("key")).isEqualTo("")
        assertThat(result.getBooleanQueryParameter("key", false)).isFalse()
        assertThat(result.normalizeScheme()).isEqualTo(MyClass.parse("uriString"))
        assertThat(result.getCanonicalUri()).isEqualTo(MyClass.parse("uriString"))
        def prefix = MyClass.parse("uriString")
        assertThat(result.isPathPrefixMatch(prefix)).isFalse()
    }

    @Test
    void testParse_ThrowsNullPointerException1() {
        // Setup
        // Run the test
        assertThatThrownBy({
            MyClass.parse("uriString")
        }).isInstanceOf(NullPointerException.class)
    }

    @Test
    void testFromFile1() {
        // Setup
        def file = new File("filename.txt")

        // Run the test
        def result = MyClass.fromFile(file)
        assertThat(result.isHierarchical()).isFalse()
        assertThat(result.isOpaque()).isFalse()
        assertThat(result.isRelative()).isFalse()
        assertThat(result.isAbsolute()).isFalse()
        assertThat(result.getScheme()).isEqualTo("result")
        assertThat(result.getSchemeSpecificPart()).isEqualTo("result")
        assertThat(result.getEncodedSchemeSpecificPart()).isEqualTo("result")
        assertThat(result.getAuthority()).isEqualTo("result")
        assertThat(result.getEncodedAuthority()).isEqualTo("result")
        assertThat(result.getUserInfo()).isEqualTo("result")
        assertThat(result.getEncodedUserInfo()).isEqualTo("result")
        assertThat(result.getHost()).isEqualTo("result")
        assertThat(result.getPort()).isEqualTo(0)
        assertThat(result.getPath()).isEqualTo("result")
        assertThat(result.getEncodedPath()).isEqualTo("result")
        assertThat(result.getQuery()).isEqualTo("result")
        assertThat(result.getEncodedQuery()).isEqualTo("result")
        assertThat(result.getFragment()).isEqualTo("result")
        assertThat(result.getEncodedFragment()).isEqualTo("result")
        assertThat(result.getPathSegments()).isEqualTo(["value"])
        assertThat(result.getLastPathSegment()).isEqualTo("result")
        assertThat(result.equals("o")).isFalse()
        assertThat(result.hashCode()).isEqualTo(0)
        def other = MyClass.parse("uriString")
        assertThat(result.compareTo(other)).isEqualTo(0)
        assertThat(result.toString()).isEqualTo("result")
        assertThat(result.toSafeString()).isEqualTo("result")
        assertThat(result.buildUpon()).isEqualTo(new MyClass.Builder())
        assertThat(result.getQueryParameterNames()).isEqualTo(new HashSet<>(["value"]))
        assertThat(result.getQueryParameters("key")).isEqualTo(["value"])
        assertThat(result.getQueryParameter("key")).isEqualTo("")
        assertThat(result.getBooleanQueryParameter("key", false)).isFalse()
        assertThat(result.normalizeScheme()).isEqualTo(MyClass.parse("uriString"))
        assertThat(result.getCanonicalUri()).isEqualTo(MyClass.parse("uriString"))
        def prefix = MyClass.parse("uriString")
        assertThat(result.isPathPrefixMatch(prefix)).isFalse()
    }

    @Test
    void testFromFile_ThrowsNullPointerException1() {
        // Setup
        def file = new File("filename.txt")

        // Run the test
        assertThatThrownBy({
            MyClass.fromFile(file)
        }).isInstanceOf(NullPointerException.class)
    }

    @Test
    void testFromParts1() {
        // Run the test
        def result = MyClass.fromParts("scheme", "fragment", "fragment")
        assertThat(result.isHierarchical()).isFalse()
        assertThat(result.isOpaque()).isFalse()
        assertThat(result.isRelative()).isFalse()
        assertThat(result.isAbsolute()).isFalse()
        assertThat(result.getScheme()).isEqualTo("result")
        assertThat(result.getSchemeSpecificPart()).isEqualTo("result")
        assertThat(result.getEncodedSchemeSpecificPart()).isEqualTo("result")
        assertThat(result.getAuthority()).isEqualTo("result")
        assertThat(result.getEncodedAuthority()).isEqualTo("result")
        assertThat(result.getUserInfo()).isEqualTo("result")
        assertThat(result.getEncodedUserInfo()).isEqualTo("result")
        assertThat(result.getHost()).isEqualTo("result")
        assertThat(result.getPort()).isEqualTo(0)
        assertThat(result.getPath()).isEqualTo("result")
        assertThat(result.getEncodedPath()).isEqualTo("result")
        assertThat(result.getQuery()).isEqualTo("result")
        assertThat(result.getEncodedQuery()).isEqualTo("result")
        assertThat(result.getFragment()).isEqualTo("result")
        assertThat(result.getEncodedFragment()).isEqualTo("result")
        assertThat(result.getPathSegments()).isEqualTo(["value"])
        assertThat(result.getLastPathSegment()).isEqualTo("result")
        assertThat(result.equals("o")).isFalse()
        assertThat(result.hashCode()).isEqualTo(0)
        def other = MyClass.parse("uriString")
        assertThat(result.compareTo(other)).isEqualTo(0)
        assertThat(result.toString()).isEqualTo("result")
        assertThat(result.toSafeString()).isEqualTo("result")
        assertThat(result.buildUpon()).isEqualTo(new MyClass.Builder())
        assertThat(result.getQueryParameterNames()).isEqualTo(new HashSet<>(["value"]))
        assertThat(result.getQueryParameters("key")).isEqualTo(["value"])
        assertThat(result.getQueryParameter("key")).isEqualTo("")
        assertThat(result.getBooleanQueryParameter("key", false)).isFalse()
        assertThat(result.normalizeScheme()).isEqualTo(MyClass.parse("uriString"))
        assertThat(result.getCanonicalUri()).isEqualTo(MyClass.parse("uriString"))
        def prefix = MyClass.parse("uriString")
        assertThat(result.isPathPrefixMatch(prefix)).isFalse()
    }

    @Test
    void testFromParts_ThrowsNullPointerException1() {
        // Setup
        // Run the test
        assertThatThrownBy({
            MyClass.fromParts("scheme", "fragment", "fragment")
        }).isInstanceOf(NullPointerException.class)
    }

    @Test
    void testWriteToParcel1() {
        MyClass.writeToParcel("out", MyClass.parse("uriString"))
    }

    @Test
    void testEncode11() {
        assertThat(MyClass.encode("s")).isEqualTo("result")
        assertThat(MyClass.encode("s")).isNull()
    }

    @Test
    void testEncode21() {
        assertThat(MyClass.encode("s", "allow")).isEqualTo("s")
        assertThat(MyClass.encode("s", "allow")).isNull()
    }

    @Test
    void testDecode1() {
        assertThat(MyClass.decode("s")).isEqualTo("s")
        assertThat(MyClass.decode("s")).isNull()
    }

    @Test
    void testWithAppendedPath1() {
        // Setup
        def baseUri = MyClass.parse("uriString")
        def expectedResult = MyClass.parse("uriString")

        // Run the test
        def result = MyClass.withAppendedPath(baseUri, "pathSegment")

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testWithAppendedPath_ThrowsNullPointerException1() {
        // Setup
        def baseUri = MyClass.parse("uriString")

        // Run the test
        assertThatThrownBy({
            MyClass.withAppendedPath(baseUri, "pathSegment")
        }).isInstanceOf(NullPointerException.class)
    }
}