package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass("id", "prop1", "prop2", "prop3", "prop4");
    }

    @Test
    void testGetConcat1() {
        assertEquals("result", myClassUnderTest.getConcat1());
    }
}
