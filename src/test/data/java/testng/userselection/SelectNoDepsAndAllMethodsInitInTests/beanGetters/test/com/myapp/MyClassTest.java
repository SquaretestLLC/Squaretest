package com.myapp;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class MyClassTest {

    @Test
    public void testGetAlphaData() {
        final MyClass myClassUnderTest = new MyClass(new AlphaData(), new BetaData());
        final AlphaData result = myClassUnderTest.getAlphaData();
    }

    @Test
    public void testGetBetaData() {
        final MyClass myClassUnderTest = new MyClass(new AlphaData(), new BetaData());
        final BetaData expectedResult = new BetaData();
        assertEquals(expectedResult, myClassUnderTest.getBetaData());
    }
}
