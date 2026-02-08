package com.myapp;

import org.testng.annotations.Test;

import java.util.Optional;

import static org.testng.Assert.assertEquals;

public class MyClassTest {

    @Test
    public void testIsEmpty1() {
        assertFalse(MyClass.isEmpty("cs"));
    }

    @Test
    public void testTrim1() {
        assertEquals("result", MyClass.trim("str"));
    }

    @Test
    public void testTrimToNull1() {
        assertEquals("result", MyClass.trimToNull("str"));
        assertNull(MyClass.trimToNull("str"));
    }

    @Test
    public void testTrimToOptional() {
        assertEquals(Optional.of("value"), MyClass.trimToOptional("str"));
        assertEquals(Optional.empty(), MyClass.trimToOptional("str"));
    }
}