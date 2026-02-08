package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = null;
    }

    @Test
    void testGetFullName1() {
        assertThat(myClassUnderTest.getFullName()).isEqualTo("result");
    }

    @Test
    void testGetFullNameWithSuffix1() {
        assertThat(myClassUnderTest.getFullNameWithSuffix()).isEqualTo("result");
    }
}