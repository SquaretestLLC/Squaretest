package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass("id", "name", LocalDate.of(2020, 1, 1));
    }

    @Test
    void testId1() {
        assertEquals("id", myClassUnderTest.id());
    }

    @Test
    void testName1() {
        assertEquals("name", myClassUnderTest.name());
    }

    @Test
    void testCreateDate1() {
        assertEquals(LocalDate.of(2020, 1, 1), myClassUnderTest.createDate());
    }
}