package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNull;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass(List.of("value"), Map.ofEntries(Map.entry("value", "value")), Set.of("value"));
    }

    @Test
    void testGetMapEntry1() {
        assertNull(myClassUnderTest.getMapEntry("customerKey"));
    }
}