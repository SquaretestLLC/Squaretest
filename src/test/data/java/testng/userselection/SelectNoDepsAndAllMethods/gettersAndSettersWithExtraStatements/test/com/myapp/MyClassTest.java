package com.myapp;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;

import static org.testng.Assert.assertEquals;

public class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeMethod
    public void setUp() {
        final AlphaData alphaData = new AlphaData();
        alphaData.setId("id");
        alphaData.setName("name");
        final BetaData betaData = new BetaData();
        betaData.setId("id");
        betaData.setName("name");
        myClassUnderTest = new MyClass(alphaData, betaData);
    }

    @Test
    public void testAlphaDataGetterAndSetter() {
        final AlphaData alphaData = new AlphaData();
        myClassUnderTest.setAlphaData(alphaData);
        assertEquals(alphaData, myClassUnderTest.getAlphaData());
    }

    @Test
    public void testGetTheAlphaString() {
        // Setup
        // Run the test
        final String result = myClassUnderTest.getTheAlphaString();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testBetaDataGetterAndSetter() {
        final BetaData betaData = new BetaData();
        myClassUnderTest.setBetaData(betaData);
        assertEquals(betaData, myClassUnderTest.getBetaData());
    }

    @Test
    public void testGetTheBetaString() {
        // Setup
        // Run the test
        final String result = myClassUnderTest.getTheBetaString();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGammaDataGetterAndSetter() {
        final GammaData gammaData = new GammaData();
        myClassUnderTest.setGammaData(gammaData);
        assertEquals(gammaData, myClassUnderTest.getGammaData());
    }

    @Test
    public void testStartDateGetterAndSetter() {
        final LocalDate startDate = LocalDate.of(2020, 1, 1);
        myClassUnderTest.setStartDate(startDate);
        assertEquals(startDate, myClassUnderTest.getStartDate());
    }

    @Test
    public void testSerializedValueGetterAndSetter() {
        final String serializedValue = "serializedValue";
        myClassUnderTest.setSerializedValue(serializedValue);
        assertEquals(serializedValue, myClassUnderTest.getSerializedValue());
    }
}
