package com.myapp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyClassTest {

    @Test
    void testFrom() {
        // Setup
        final MyData myData = new MyData("name", 0L, "path");

        // Run the test
        final MyClass result = MyClass.from(myData);
        assertEquals("name", result.getName());
        assertEquals(0L, result.getId());
        assertEquals("path", result.getPath());
    }
}
