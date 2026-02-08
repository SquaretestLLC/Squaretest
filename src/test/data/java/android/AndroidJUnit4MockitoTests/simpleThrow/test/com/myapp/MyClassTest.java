package com.myapp;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
@SmallTest
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
