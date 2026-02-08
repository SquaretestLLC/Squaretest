package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass();
    }

    @Test
    void testGetTheStringg1() {
        assertThrows(UnsupportedOperationException.class, () -> myClassUnderTest.getTheStringg1("id"));
    }

    @Test
    void testGetTheString2() {
        assertThrows(IllegalStateException.class, () -> myClassUnderTest.getTheString2("id"));
    }

    @Test
    void testGetTheString3() {
        assertEquals("id", myClassUnderTest.getTheString3("id"));
    }

    @Test
    void testGetTheString4() {
        assertThrows(UnsupportedOperationException.class, () -> myClassUnderTest.getTheString4(new FooData()));
    }

    @Test
    void testGetTheString5() {
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getTheString5(new FooData()));
    }
}
