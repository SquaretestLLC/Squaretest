package com.myapp;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MyClassTest {

    @Test
    public void testCreateDefault1() {
        // Run the test
        final MyClass result = MyClass.createDefault();
        assertEquals(new FooData(), result.getFoo1("id"));
        assertEquals(new FooData(), result.getDefaultFoo());
        assertEquals("result", result.getDefaultFooId());
    }
}