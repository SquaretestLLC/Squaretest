package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        final AlphaData alphaData = new AlphaData();
        alphaData.setId("id");
        alphaData.setName("name");
        final BetaData betaData = new BetaData();
        betaData.setId("id");
        betaData.setName("name");
        myClassUnderTest = new MyClass(alphaData, betaData);
    }

    @Test
    void testAlphaDataGetterAndSetter() {
        final AlphaData alphaData = new AlphaData();
        myClassUnderTest.setPrimaryValue(alphaData);
        assertEquals(alphaData, myClassUnderTest.getPrimaryValue());
    }

    @Test
    void testAlphaData1GetterAndSetter() {
        final AlphaData alphaData = new AlphaData();
        myClassUnderTest.setPrimaryValue(alphaData);
        assertEquals(alphaData, myClassUnderTest.getAlphaData());
    }

    @Test
    void testAlphaData2GetterAndSetter() {
        final AlphaData alphaData = new AlphaData();
        myClassUnderTest.setAlphaData(alphaData);
        assertEquals(alphaData, myClassUnderTest.getPrimaryValue());
    }

    @Test
    void testGetTheAlphaString() {
        // Setup
        // Run the test
        final String result = myClassUnderTest.getTheAlphaString();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testBetaDataGetterAndSetter() {
        final BetaData betaData = new BetaData();
        myClassUnderTest.setBetaData(betaData);
        assertEquals(betaData, myClassUnderTest.getSecondaryValue());
    }

    @Test
    void testBetaData1GetterAndSetter() {
        final BetaData betaData = new BetaData();
        myClassUnderTest.setBetaData(betaData);
        assertEquals(betaData, myClassUnderTest.getThirdValue());
    }

    @Test
    void testBetaData2GetterAndSetter() {
        final BetaData betaData = new BetaData();
        myClassUnderTest.setBetaData(betaData);
        assertEquals(betaData, myClassUnderTest.getFourthValue());
    }

    @Test
    void testBetaData3GetterAndSetter() {
        final BetaData betaData = new BetaData();
        myClassUnderTest.setBetaData(betaData);
        assertEquals(betaData, myClassUnderTest.getBetaData());
    }

    @Test
    void testGetTheBetaString() {
        // Setup
        // Run the test
        final String result = myClassUnderTest.getTheBetaString();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGammaDataGetterAndSetter() {
        final GammaData gammaData = new GammaData();
        myClassUnderTest.setGammaData(gammaData);
        assertEquals(gammaData, myClassUnderTest.getGammaData());
    }

    @Test
    void testStartDateGetterAndSetter() {
        final LocalDate startDate = LocalDate.of(2020, 1, 1);
        myClassUnderTest.setStartDate(startDate);
        assertEquals(startDate, myClassUnderTest.getStartDate());
    }

    @Test
    void testStartDate1GetterAndSetter() {
        final LocalDate startDate = LocalDate.of(2020, 1, 1);
        myClassUnderTest.setStartDate(startDate);
        assertEquals(startDate, myClassUnderTest.getFirstDate());
    }

    @Test
    void testStartDate2GetterAndSetter() {
        final LocalDate startDate = LocalDate.of(2020, 1, 1);
        myClassUnderTest.setFirstDate(startDate);
        assertEquals(startDate, myClassUnderTest.getStartDate());
    }

    @Test
    void testSerializedValueGetterAndSetter() {
        final String serializedValue = "serializedValue";
        myClassUnderTest.setSerializedValue(serializedValue);
        assertEquals(serializedValue, myClassUnderTest.getSerializedValue());
    }

    @Test
    void testSerializedValue1GetterAndSetter() {
        final String serializedValue = "serializedValue";
        myClassUnderTest.setSerializedValue(serializedValue);
        assertEquals(serializedValue, myClassUnderTest.getFirstSerializedValue());
    }

    @Test
    void testSerializedValue2GetterAndSetter() {
        final String serializedValue = "serializedValue";
        myClassUnderTest.setFirstSerializedValue(serializedValue);
        assertEquals(serializedValue, myClassUnderTest.getSerializedValue());
    }
}
