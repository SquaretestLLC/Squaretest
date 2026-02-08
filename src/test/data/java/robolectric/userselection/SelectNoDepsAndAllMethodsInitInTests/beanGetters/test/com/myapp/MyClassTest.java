package com.myapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
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
