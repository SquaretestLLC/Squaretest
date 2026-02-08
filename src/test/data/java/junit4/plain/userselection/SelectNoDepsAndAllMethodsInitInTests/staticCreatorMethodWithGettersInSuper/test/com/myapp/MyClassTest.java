package com.myapp;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MyClassTest {

    @Test
    public void testFrom() {
        // Setup
        final MyData myData = new MyData("name", 0L, "path");

        // Run the test
        final MyClass result = MyClass.from(myData);
        assertEquals("name", result.getName());
        assertEquals(0L, result.getId());
        assertEquals("path", result.getPath());
    }

    @Test
    public void testGetConstantVal() {
        assertEquals("ignored", MyClassBase.getConstantVal());
    }
}
