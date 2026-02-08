package com.myapp;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeMethod
    public void setUp() {
        myClassUnderTest = new MyClass();
    }

    @Test
    public void testGetTheStringg1() {
        assertThrows(UnsupportedOperationException.class, () -> myClassUnderTest.getTheStringg1("id"));
    }

    @Test
    public void testGetTheString2() {
        assertThrows(IllegalStateException.class, () -> myClassUnderTest.getTheString2("id"));
    }

    @Test
    public void testGetTheString3() {
        assertEquals("id", myClassUnderTest.getTheString3("id"));
    }

    @Test
    public void testGetTheString4() {
        assertThrows(UnsupportedOperationException.class, () -> myClassUnderTest.getTheString4(new FooData()));
    }

    @Test
    public void testGetTheString5() {
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getTheString5(new FooData()));
    }
}
