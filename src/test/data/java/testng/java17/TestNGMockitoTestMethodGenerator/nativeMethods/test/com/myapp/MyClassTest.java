package com.myapp;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeMethod
    public void setUp() {
        myClassUnderTest = new MyClass();
    }

    @Test
    public void testDoSomethingCool1() {
        myClassUnderTest.doSomethingCool();
    }

    @Test
    public void testDoSomethingInC1() {
        assertEquals("result", myClassUnderTest.doSomethingInC());
    }
}