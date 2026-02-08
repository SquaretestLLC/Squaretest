package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass(Arrays.asList("value"), new HashMap<>(), new HashSet<>(Arrays.asList("value")));
    }

    @Test
    void testGetMapEntry() {
        assertThat(myClassUnderTest.getMapEntry("customerKey")).isNull();
    }
}
