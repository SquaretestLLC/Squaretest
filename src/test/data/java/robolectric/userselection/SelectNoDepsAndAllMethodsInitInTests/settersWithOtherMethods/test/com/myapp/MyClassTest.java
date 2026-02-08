package com.myapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
public class MyClassTest {

    @Test
    public void testToString() {
        final MyClass myClassUnderTest = new MyClass();
        myClassUnderTest.setAlphaData(new AlphaData());
        myClassUnderTest.setBetaData(new BetaData());
        myClassUnderTest.setGammaData(new GammaData());
        myClassUnderTest.setStartDate(LocalDate.of(2020, 1, 1));
        myClassUnderTest.setSerializedValue("serializedValue");
        assertEquals("result", myClassUnderTest.toString());
    }
}
