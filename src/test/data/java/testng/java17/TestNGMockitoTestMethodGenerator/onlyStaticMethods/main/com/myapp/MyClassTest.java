package com.myapp;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

class MyClassTest {

    @Test
    void testIsEmpty() {
        assertTrue(MyClass.isEmpty("cs"));
    }

    @Test
    void testTrim() {
        assertEquals("result", MyClass.trim("str"));
    }

    @Test
    void testTrimToNull() {
        assertEquals("result", MyClass.trimToNull("str"));
    }
}
