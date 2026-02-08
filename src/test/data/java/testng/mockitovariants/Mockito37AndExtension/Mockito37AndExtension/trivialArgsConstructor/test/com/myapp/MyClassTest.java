package com.myapp;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import static org.testng.Assert.assertNull;

public class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeMethod
    public void setUp() {
        myClassUnderTest = new MyClass(Arrays.asList("value"), new HashMap<>(), new HashSet<>(Arrays.asList("value")));
    }

    @Test
    public void testGetMapEntry() {
        assertNull(myClassUnderTest.getMapEntry("customerKey"));
    }
}
