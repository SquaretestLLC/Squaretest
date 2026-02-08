package com.myapp

import groovy.transform.CompileStatic
import org.testng.annotations.Test

import static org.testng.Assert.assertFalse

@CompileStatic
class MyClassTest {

    @Test
    void testFromString() {
        // Run the test
        def result = MyClass.fromString("uriString")
        assertFalse(result.isRelative())
        assertFalse(result.isHierarchical())
        assert "result" == result.getSchemeSpecificPart()
        assertFalse(result.isAbsolute())
    }

    @Test
    void testFromFile() {
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
    void testFromParts() {
        // Run the test
        def result = MyClass.fromParts("scheme", "ssp", "fragment")
        assertFalse(result.isRelative())
        assertFalse(result.isHierarchical())
        assert "result" == result.getSchemeSpecificPart()
        assertFalse(result.isAbsolute())
    }
}
