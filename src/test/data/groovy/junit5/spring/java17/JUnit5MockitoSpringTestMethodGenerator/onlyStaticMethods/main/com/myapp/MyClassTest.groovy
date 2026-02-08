package com.myapp

import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.assertEquals
import static org.junit.jupiter.api.Assertions.assertTrue

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
