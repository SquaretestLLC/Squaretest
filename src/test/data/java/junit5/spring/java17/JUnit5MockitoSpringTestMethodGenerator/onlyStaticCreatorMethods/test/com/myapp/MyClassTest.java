package com.myapp;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class MyClassTest {

    @Test
    void testFromString1() {
        // Run the test
        final MyClass result = MyClass.fromString("uriString");
        assertFalse(result.isRelative());
        assertFalse(result.isHierarchical());
        assertEquals("result", result.getSchemeSpecificPart());
        assertFalse(result.isAbsolute());
    }

    @Test
    void testFromFile1() {
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
    void testFromParts1() {
        // Run the test
        final MyClass result = MyClass.fromParts("scheme", "ssp", "fragment");
        assertFalse(result.isRelative());
        assertFalse(result.isHierarchical());
        assertEquals("result", result.getSchemeSpecificPart());
        assertFalse(result.isAbsolute());
    }
}