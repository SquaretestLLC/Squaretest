package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass();
    }

    @Test
    void testSubmitFormWithNestedTypes() {
        assertThat(myClassUnderTest.submitFormWithNestedTypes(new FormWithFieldsWithNestedGenerics()))
                .isEqualTo("submitFormWithNestedTypesConfirmation");
    }
}
