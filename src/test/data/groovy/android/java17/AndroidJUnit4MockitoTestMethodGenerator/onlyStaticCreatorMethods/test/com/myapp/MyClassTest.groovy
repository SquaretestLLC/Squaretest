package com.myapp


import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import groovy.transform.CompileStatic
import org.junit.Test
import org.junit.runner.RunWith

import static org.junit.Assert.assertFalse

@CompileStatic
@RunWith(AndroidJUnit4.class)
@SmallTest
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