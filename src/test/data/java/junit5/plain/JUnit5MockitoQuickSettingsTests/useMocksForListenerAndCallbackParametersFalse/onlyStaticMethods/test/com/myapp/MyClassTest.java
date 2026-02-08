package com.myapp;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MyClassTest {

    @Test
    void testIsEmpty() {
        assertFalse(MyClass.isEmpty("cs"));
    }

    @Test
    void testTrim() {
        assertEquals("result", MyClass.trim("str"));
    }

    @Test
    void testTrimToNull() {
        assertEquals("result", MyClass.trimToNull("str"));
        assertNull(MyClass.trimToNull("str"));
    }

    @Test
    void testTrimToOptional() {
        assertEquals(Optional.of("value"), MyClass.trimToOptional("str"));
        assertEquals(Optional.empty(), MyClass.trimToOptional("str"));
    }
}
