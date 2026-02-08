package com.myapp

import org.junit.Test

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertTrue

class MyClassTest {

    @Test
    void testIsEmpty() {
        assertTrue(MyClass.isEmpty("cs"))
    }

    @Test
    void testTrim() {
        assertEquals("result", MyClass.trim("str"))
    }

    @Test
    void testTrimToNull() {
        assertEquals("result", MyClass.trimToNull("str"))
    }
}
