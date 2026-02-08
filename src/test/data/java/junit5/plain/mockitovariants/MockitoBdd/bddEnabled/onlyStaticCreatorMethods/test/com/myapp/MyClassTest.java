package com.myapp;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class MyClassTest {

    @Test
    void testFromString() {
        // Run the test
        final MyClass result = MyClass.fromString("uriString");
        assertFalse(result.isRelative());
        assertFalse(result.isHierarchical());
        assertEquals("result", result.getSchemeSpecificPart());
        assertFalse(result.isAbsolute());
    }

    @Test
    void testFromFile() {
        // Setup
        final File file = new File("filename.txt");

        // Run the test
        final MyClass result = MyClass.fromFile(file);
        assertFalse(result.isRelative());
        assertFalse(result.isHierarchical());
        assertEquals("result", result.getSchemeSpecificPart());
        assertFalse(result.isAbsolute());
    }

    @Test
    void testFromParts() {
        // Run the test
        final MyClass result = MyClass.fromParts("scheme", "ssp", "fragment");
        assertFalse(result.isRelative());
        assertFalse(result.isHierarchical());
        assertEquals("result", result.getSchemeSpecificPart());
        assertFalse(result.isAbsolute());
    }
}
