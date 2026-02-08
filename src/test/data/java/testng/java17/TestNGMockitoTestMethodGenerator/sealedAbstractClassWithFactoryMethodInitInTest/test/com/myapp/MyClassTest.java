package com.myapp;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

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