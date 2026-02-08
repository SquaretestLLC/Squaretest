package com.myapp;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class MyClassTest {

    @Test
    public void testFromString() {
        // Run the test
        final MyClass result = MyClass.fromString("uriString");
        assertFalse(result.isRelative());
        assertFalse(result.isHierarchical());
        assertEquals("result", result.getSchemeSpecificPart());
        assertFalse(result.isAbsolute());
    }

    @Test
    public void testFromFile() {
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
    public void testFromParts() {
        // Run the test
        final MyClass result = MyClass.fromParts("scheme", "ssp", "fragment");
        assertFalse(result.isRelative());
        assertFalse(result.isHierarchical());
        assertEquals("result", result.getSchemeSpecificPart());
        assertFalse(result.isAbsolute());
    }
}
