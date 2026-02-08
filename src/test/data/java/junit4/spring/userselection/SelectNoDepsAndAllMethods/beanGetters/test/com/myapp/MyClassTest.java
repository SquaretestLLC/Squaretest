package com.myapp;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MyClassTest {

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass(new AlphaData(), new BetaData());
    }

    @Test
    public void testGetAlphaData() {
        final AlphaData result = myClassUnderTest.getAlphaData();
    }

    @Test
    public void testGetBetaData() {
        final BetaData expectedResult = new BetaData();
        assertEquals(expectedResult, myClassUnderTest.getBetaData());
    }
}
