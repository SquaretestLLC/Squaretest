package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass();
    }

    @Test
    void testSubmitFormWithNestedTypes() {
        assertEquals("submitFormWithNestedTypesConfirmation",
                myClassUnderTest.submitFormWithNestedTypes(new FormWithFieldsWithNestedGenerics()));
    }
}
