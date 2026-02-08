package com.myapp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyClassTest {

    @Test
    void testParse() {
        // Run the test
        final MyClass result = MyClass.parse("serialized");
        assertEquals("result", result.getId());
        assertEquals("result", result.getName());
        assertEquals("result", result.getCombined());
    }
}
