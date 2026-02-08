package com.myapp;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
@SmallTest
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
}
