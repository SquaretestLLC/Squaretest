package com.myapp;

import org.testng.annotations.Test;

import java.io.File;

import static org.testng.Assert.assertEquals;

public class MyClassTest {

    @Test
    public void testFromString1() {
        // Run the test
        final MyClass result = MyClass.fromString("uriString");
        assertFalse(result.isRelative());
        assertFalse(result.isHierarchical());
        assertEquals("result", result.getSchemeSpecificPart());
        assertFalse(result.isAbsolute());
    }

    @Test
    public void testFromFile1() {
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
    public void testFromParts1() {
        // Run the test
        final MyClass result = MyClass.fromParts("scheme", "ssp", "fragment");
        assertFalse(result.isRelative());
        assertFalse(result.isHierarchical());
        assertEquals("result", result.getSchemeSpecificPart());
        assertFalse(result.isAbsolute());
    }
}