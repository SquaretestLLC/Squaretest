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
    void testGetFullName1() {
        assertEquals("result", myClassUnderTest.getFullName());
    }

    @Test
    void testGetFullNameWithSuffix1() {
        assertEquals("result", myClassUnderTest.getFullNameWithSuffix());
    }
}