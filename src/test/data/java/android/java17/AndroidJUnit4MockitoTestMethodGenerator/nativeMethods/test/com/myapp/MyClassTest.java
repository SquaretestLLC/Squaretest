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

    @Test
    public void testDoSomethingCool1() {
        myClassUnderTest.doSomethingCool();
    }

    @Test
    public void testDoSomethingInC1() {
        assertEquals("result", myClassUnderTest.doSomethingInC());
    }
}