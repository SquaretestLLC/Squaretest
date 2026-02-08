package com.myapp;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
@SmallTest
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
