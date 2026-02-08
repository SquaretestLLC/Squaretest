package com.myapp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyClassTest {

    @Test
    void testFromString() {
        // Run the test
        final MyClass result = MyClass.fromString("str");
        assertEquals("result", result.getFoo("key"));
    }
}
