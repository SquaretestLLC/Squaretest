package com.myapp;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(AndroidJUnit4.class)
@SmallTest
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