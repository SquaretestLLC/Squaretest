package com.myapp;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertNull;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class MyClassTest {

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass(List.of("value"), Map.ofEntries(Map.entry("value", "value")), Set.of("value"));
    }

    @Test
    public void testGetMapEntry1() {
        assertNull(myClassUnderTest.getMapEntry("customerKey"));
    }
}