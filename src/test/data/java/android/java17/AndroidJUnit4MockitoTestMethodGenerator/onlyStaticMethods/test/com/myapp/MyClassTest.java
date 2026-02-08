package com.myapp;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
@SmallTest
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