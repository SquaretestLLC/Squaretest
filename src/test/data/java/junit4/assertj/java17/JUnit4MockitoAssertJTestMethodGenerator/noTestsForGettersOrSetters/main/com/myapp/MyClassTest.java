package com.myapp;

import org.junit.Before;

import java.util.Arrays;

class MyClassTest {

    private MyClass myClassUnderTest;

    @Before
    void setUp() {
        myClassUnderTest = new MyClass("displayName", "canonicalName", Arrays.asList(), "canonicalText", "defaultInitExpression", false);
    }
}
