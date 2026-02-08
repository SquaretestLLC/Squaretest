package com.myapp

import org.testng.annotations.BeforeMethod

import java.util.Arrays

class MyClassTest {

    private MyClass myClassUnderTest

    @BeforeMethod
    void setUp() {
        myClassUnderTest = new MyClass("displayName", "canonicalName", Arrays.asList(), "canonicalText", "defaultInitExpression", false)
    }
}
