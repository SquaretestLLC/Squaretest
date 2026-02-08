package com.myapp


import groovy.transform.CompileStatic
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

import static org.junit.Assert.assertFalse

@CompileStatic
@RunWith(RobolectricTestRunner.class)
class MyClassTest {

    @Test
    void testFromString1() {
        // Run the test
        def result = MyClass.fromString("uriString")
        assertFalse(result.isRelative())
        assertFalse(result.isHierarchical())
        assert "result" == result.getSchemeSpecificPart()
        assertFalse(result.isAbsolute())
    }

    @Test
    void testFromFile1() {
        // Setup
        def file = new File("filename.txt")

        // Run the test
        def result = MyClass.fromFile(file)
        assertFalse(result.isRelative())
        assertFalse(result.isHierarchical())
        assert "result" == result.getSchemeSpecificPart()
        assertFalse(result.isAbsolute())
    }

    @Test
    void testFromParts1() {
        // Run the test
        def result = MyClass.fromParts("scheme", "ssp", "fragment")
        assertFalse(result.isRelative())
        assertFalse(result.isHierarchical())
        assert "result" == result.getSchemeSpecificPart()
        assertFalse(result.isAbsolute())
    }
}