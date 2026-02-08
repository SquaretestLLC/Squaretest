package com.myapp;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MyClassTest {

    @Test
    void testFromString() {
        // Run the test
        final MyClass result = MyClass.fromString("uriString");
        assertTrue(result.isRelative());
        assertTrue(result.isHierarchical());
        assertEquals("result", result.getSchemeSpecificPart());
        assertTrue(result.isAbsolute());
    }

    @Test
    void testFromFile() {
        // Setup
        final File file = new File("filename.txt");

        // Run the test
        final MyClass result = MyClass.fromFile(file);
        assertTrue(result.isRelative());
        assertTrue(result.isHierarchical());
        assertEquals("result", result.getSchemeSpecificPart());
        assertTrue(result.isAbsolute());
    }

    @Test
    void testFromParts() {
        // Run the test
        final MyClass result = MyClass.fromParts("scheme", "ssp", "fragment");
        assertTrue(result.isRelative());
        assertTrue(result.isHierarchical());
        assertEquals("result", result.getSchemeSpecificPart());
        assertTrue(result.isAbsolute());
    }
}
