package com.myapp;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyClassTest {

    @Test
    void testAlphaDataGetterAndSetter() {
        final AlphaData alphaData = new AlphaData();
        alphaData.setId("id");
        alphaData.setName("name");
        final BetaData betaData = new BetaData();
        betaData.setId("id");
        betaData.setName("name");
        final MyClass myClassUnderTest = new MyClass(alphaData, betaData);
        final AlphaData alphaData1 = new AlphaData();
        myClassUnderTest.setAlphaData(alphaData1);
        assertEquals(alphaData1, myClassUnderTest.getAlphaData());
    }

    @Test
    void testGetTheAlphaString() {
        // Setup
        final AlphaData alphaData = new AlphaData();
        alphaData.setId("id");
        alphaData.setName("name");
        final BetaData betaData = new BetaData();
        betaData.setId("id");
        betaData.setName("name");
        final MyClass myClassUnderTest = new MyClass(alphaData, betaData);

        // Run the test
        final String result = myClassUnderTest.getTheAlphaString();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testBetaDataGetterAndSetter() {
        final AlphaData alphaData = new AlphaData();
        alphaData.setId("id");
        alphaData.setName("name");
        final BetaData betaData = new BetaData();
        betaData.setId("id");
        betaData.setName("name");
        final MyClass myClassUnderTest = new MyClass(alphaData, betaData);
        final BetaData betaData1 = new BetaData();
        myClassUnderTest.setBetaData(betaData1);
        assertEquals(betaData1, myClassUnderTest.getBetaData());
    }

    @Test
    void testGetTheBetaString() {
        // Setup
        final AlphaData alphaData = new AlphaData();
        alphaData.setId("id");
        alphaData.setName("name");
        final BetaData betaData = new BetaData();
        betaData.setId("id");
        betaData.setName("name");
        final MyClass myClassUnderTest = new MyClass(alphaData, betaData);

        // Run the test
        final String result = myClassUnderTest.getTheBetaString();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGammaDataGetterAndSetter() {
        final AlphaData alphaData = new AlphaData();
        alphaData.setId("id");
        alphaData.setName("name");
        final BetaData betaData = new BetaData();
        betaData.setId("id");
        betaData.setName("name");
        final MyClass myClassUnderTest = new MyClass(alphaData, betaData);
        final GammaData gammaData = new GammaData();
        myClassUnderTest.setGammaData(gammaData);
        assertEquals(gammaData, myClassUnderTest.getGammaData());
    }

    @Test
    void testStartDateGetterAndSetter() {
        final AlphaData alphaData = new AlphaData();
        alphaData.setId("id");
        alphaData.setName("name");
        final BetaData betaData = new BetaData();
        betaData.setId("id");
        betaData.setName("name");
        final MyClass myClassUnderTest = new MyClass(alphaData, betaData);
        final LocalDate startDate = LocalDate.of(2020, 1, 1);
        myClassUnderTest.setStartDate(startDate);
        assertEquals(startDate, myClassUnderTest.getStartDate());
    }

    @Test
    void testSerializedValueGetterAndSetter() {
        final AlphaData alphaData = new AlphaData();
        alphaData.setId("id");
        alphaData.setName("name");
        final BetaData betaData = new BetaData();
        betaData.setId("id");
        betaData.setName("name");
        final MyClass myClassUnderTest = new MyClass(alphaData, betaData);
        final String serializedValue = "serializedValue";
        myClassUnderTest.setSerializedValue(serializedValue);
        assertEquals(serializedValue, myClassUnderTest.getSerializedValue());
    }
}
