package com.myapp;

import org.testng.annotations.BeforeMethod;

public class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeMethod
    public void setUp() {
        myClassUnderTest = new MyClass();
    }
}
