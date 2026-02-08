package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import static org.junit.Assert.assertNull;

@RunWith(RobolectricTestRunner.class)
public class MyClassTest {

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass(Arrays.asList("value"), new HashMap<>(), new HashSet<>(Arrays.asList("value")));
    }

    @Test
    public void testGetMapEntry() {
        assertNull(myClassUnderTest.getMapEntry("customerKey"));
    }
}
