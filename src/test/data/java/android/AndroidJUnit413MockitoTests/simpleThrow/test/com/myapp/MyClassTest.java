package com.myapp;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class MyClassTest {

    private MyClass myClassUnderTest;

    @Before
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
