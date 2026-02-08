package com.myapp;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class MyClassTest {

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass(List.of("value"), Map.ofEntries(Map.entry("value", "value")), Set.of("value"));
    }

    @Test
    public void testGetMapEntry1() {
        assertThat(myClassUnderTest.getMapEntry("customerKey")).isNull();
    }
}