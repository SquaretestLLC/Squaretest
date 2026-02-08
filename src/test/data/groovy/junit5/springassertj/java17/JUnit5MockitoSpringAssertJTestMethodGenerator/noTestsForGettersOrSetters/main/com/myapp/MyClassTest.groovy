package com.myapp

import org.junit.jupiter.api.BeforeEach

import java.util.Arrays

class MyClassTest {

    private MyClass myClassUnderTest

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass("displayName", "canonicalName", Arrays.asList(), "canonicalText", "defaultInitExpression", false)
    }
}
