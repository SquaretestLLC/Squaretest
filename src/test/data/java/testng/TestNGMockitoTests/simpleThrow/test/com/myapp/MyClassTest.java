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

    @Test(expectedExceptions = {UnsupportedOperationException.class})
    public void testGetTheStringg1() {
        myClassUnderTest.getTheStringg1("id");
    }

    @Test(expectedExceptions = {IllegalStateException.class})
    public void testGetTheString2() {
        myClassUnderTest.getTheString2("id");
    }

    @Test
    public void testGetTheString3() {
        assertEquals("id", myClassUnderTest.getTheString3("id"));
    }

    @Test(expectedExceptions = {UnsupportedOperationException.class})
    public void testGetTheString4() {
        myClassUnderTest.getTheString4(new FooData());
    }

    @Test(expectedExceptions = {RuntimeException.class})
    public void testGetTheString5() {
        myClassUnderTest.getTheString5(new FooData());
    }
}
