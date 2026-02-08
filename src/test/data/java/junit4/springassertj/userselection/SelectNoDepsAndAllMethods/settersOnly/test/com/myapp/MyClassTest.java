package com.myapp;

import org.junit.Before;

import java.time.LocalDate;

public class MyClassTest {

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass();
        myClassUnderTest.setAlphaData(new AlphaData());
        myClassUnderTest.setBetaData(new BetaData());
        myClassUnderTest.setGammaData(new GammaData());
        myClassUnderTest.setStartDate(LocalDate.of(2020, 1, 1));
        myClassUnderTest.setSerializedValue("serializedValue");
    }
}
