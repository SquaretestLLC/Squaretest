package com.myapp;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

class MyClassTest {

    @Test
    void testCreateDefault() {
        // Run the test
        final MyClass result = MyClass.createDefault();
        assertEquals(new FooData(), result.getFoo1("id"));
        assertEquals(new FooData(), result.getDefaultFoo());
        assertEquals("result", result.getDefaultFooId());
    }
}
