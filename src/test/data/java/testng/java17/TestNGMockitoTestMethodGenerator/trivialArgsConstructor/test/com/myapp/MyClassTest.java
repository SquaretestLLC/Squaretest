package com.myapp;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeMethod
    public void setUp() {
        myClassUnderTest = new MyClass(List.of("value"), Map.ofEntries(Map.entry("value", "value")), Set.of("value"));
    }

    @Test
    public void testGetMapEntry1() {
        assertNull(myClassUnderTest.getMapEntry("customerKey"));
    }
}