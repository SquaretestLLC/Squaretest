package com.myapp;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

public class MyClassTest {

    @Test
    public void testAtLeast() {
        assertFalse(MyClass.JAVA_0_9.atLeast(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_1_1.atLeast(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_1_2.atLeast(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_1_3.atLeast(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_1_4.atLeast(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_1_5.atLeast(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_1_6.atLeast(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_1_7.atLeast(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_1_8.atLeast(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_1_9.atLeast(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_9.atLeast(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_10.atLeast(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_11.atLeast(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_12.atLeast(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_13.atLeast(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_14.atLeast(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_15.atLeast(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_16.atLeast(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_RECENT.atLeast(MyClass.JAVA_0_9));
    }

    @Test
    public void testAtMost() {
        assertFalse(MyClass.JAVA_0_9.atMost(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_1_1.atMost(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_1_2.atMost(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_1_3.atMost(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_1_4.atMost(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_1_5.atMost(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_1_6.atMost(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_1_7.atMost(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_1_8.atMost(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_1_9.atMost(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_9.atMost(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_10.atMost(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_11.atMost(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_12.atMost(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_13.atMost(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_14.atMost(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_15.atMost(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_16.atMost(MyClass.JAVA_0_9));
        assertFalse(MyClass.JAVA_RECENT.atMost(MyClass.JAVA_0_9));
    }

    @Test
    public void testGetJavaVersion() {
        assertEquals(MyClass.JAVA_0_9, MyClass.getJavaVersion("nom"));
    }

    @Test
    public void testGet() {
        assertEquals(MyClass.JAVA_0_9, MyClass.get("nom"));
    }

    @Test
    public void testToString() {
        assertEquals("name", MyClass.JAVA_0_9.toString());
        assertEquals("name", MyClass.JAVA_1_1.toString());
        assertEquals("name", MyClass.JAVA_1_2.toString());
        assertEquals("name", MyClass.JAVA_1_3.toString());
        assertEquals("name", MyClass.JAVA_1_4.toString());
        assertEquals("name", MyClass.JAVA_1_5.toString());
        assertEquals("name", MyClass.JAVA_1_6.toString());
        assertEquals("name", MyClass.JAVA_1_7.toString());
        assertEquals("name", MyClass.JAVA_1_8.toString());
        assertEquals("name", MyClass.JAVA_1_9.toString());
        assertEquals("name", MyClass.JAVA_9.toString());
        assertEquals("name", MyClass.JAVA_10.toString());
        assertEquals("name", MyClass.JAVA_11.toString());
        assertEquals("name", MyClass.JAVA_12.toString());
        assertEquals("name", MyClass.JAVA_13.toString());
        assertEquals("name", MyClass.JAVA_14.toString());
        assertEquals("name", MyClass.JAVA_15.toString());
        assertEquals("name", MyClass.JAVA_16.toString());
        assertEquals("name", MyClass.JAVA_RECENT.toString());
    }
}
