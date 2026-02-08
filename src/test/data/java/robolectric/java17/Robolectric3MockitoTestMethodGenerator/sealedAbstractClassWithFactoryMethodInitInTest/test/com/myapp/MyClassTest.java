package com.myapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
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