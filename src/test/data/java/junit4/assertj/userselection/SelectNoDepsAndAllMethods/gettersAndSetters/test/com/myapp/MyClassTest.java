package com.myapp;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class MyClassTest {

    private MyClass myClassUnderTest;

    @Before
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
        assertThat(myClassUnderTest.getAlphaData()).isEqualTo(alphaData);
    }

    @Test
    public void testGetTheAlphaString() {
        // Setup
        // Run the test
        final String result = myClassUnderTest.getTheAlphaString();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testBetaDataGetterAndSetter() {
        final BetaData betaData = new BetaData();
        myClassUnderTest.setBetaData(betaData);
        assertThat(myClassUnderTest.getBetaData()).isEqualTo(betaData);
    }

    @Test
    public void testGetTheBetaString() {
        // Setup
        // Run the test
        final String result = myClassUnderTest.getTheBetaString();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testGammaDataGetterAndSetter() {
        final GammaData gammaData = new GammaData();
        myClassUnderTest.setGammaData(gammaData);
        assertThat(myClassUnderTest.getGammaData()).isEqualTo(gammaData);
    }

    @Test
    public void testStartDateGetterAndSetter() {
        final LocalDate startDate = LocalDate.of(2020, 1, 1);
        myClassUnderTest.setStartDate(startDate);
        assertThat(myClassUnderTest.getStartDate()).isEqualTo(startDate);
    }

    @Test
    public void testSerializedValueGetterAndSetter() {
        final String serializedValue = "serializedValue";
        myClassUnderTest.setSerializedValue(serializedValue);
        assertThat(myClassUnderTest.getSerializedValue()).isEqualTo(serializedValue);
    }
}
