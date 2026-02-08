package com.myapp;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MyClassTest {

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetTheStringg1() {
        myClassUnderTest.getTheStringg1("id");
    }

    @Test(expected = IllegalStateException.class)
    public void testGetTheString2() {
        myClassUnderTest.getTheString2("id");
    }

    @Test
    public void testGetTheString3() {
        assertEquals("id", myClassUnderTest.getTheString3("id"));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetTheString4() {
        myClassUnderTest.getTheString4(new FooData());
    }

    @Test(expected = RuntimeException.class)
    public void testGetTheString5() {
        myClassUnderTest.getTheString5(new FooData());
    }
}
