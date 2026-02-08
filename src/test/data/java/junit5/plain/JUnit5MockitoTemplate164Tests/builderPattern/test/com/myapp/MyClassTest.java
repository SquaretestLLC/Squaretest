package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = null /* TODO: construct the instance */;
    }

    @Test
    void testGetFullName() {
        assertEquals("result", myClassUnderTest.getFullName());
    }

    @Test
    void testGetFullNameWithSuffix() {
        assertEquals("result", myClassUnderTest.getFullNameWithSuffix());
    }
}
