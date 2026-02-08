package com.myapp;

import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class MyClassTest {

    @Test
    public void testAlphaDataGetterAndSetter() {
        final AlphaData alphaData = new AlphaData();
        alphaData.setId("id");
        alphaData.setName("name");
        final BetaData betaData = new BetaData();
        betaData.setId("id");
        betaData.setName("name");
        final MyClass myClassUnderTest = new MyClass(alphaData, betaData);
        final AlphaData alphaData1 = new AlphaData();
        myClassUnderTest.setPrimaryValue(alphaData1);
        assertThat(myClassUnderTest.getPrimaryValue()).isEqualTo(alphaData1);
    }

    @Test
    public void testAlphaData1GetterAndSetter() {
        final AlphaData alphaData = new AlphaData();
        alphaData.setId("id");
        alphaData.setName("name");
        final BetaData betaData = new BetaData();
        betaData.setId("id");
        betaData.setName("name");
        final MyClass myClassUnderTest = new MyClass(alphaData, betaData);
        final AlphaData alphaData1 = new AlphaData();
        myClassUnderTest.setPrimaryValue(alphaData1);
        assertThat(myClassUnderTest.getAlphaData()).isEqualTo(alphaData1);
    }

    @Test
    public void testAlphaData2GetterAndSetter() {
        final AlphaData alphaData = new AlphaData();
        alphaData.setId("id");
        alphaData.setName("name");
        final BetaData betaData = new BetaData();
        betaData.setId("id");
        betaData.setName("name");
        final MyClass myClassUnderTest = new MyClass(alphaData, betaData);
        final AlphaData alphaData1 = new AlphaData();
        myClassUnderTest.setAlphaData(alphaData1);
        assertThat(myClassUnderTest.getPrimaryValue()).isEqualTo(alphaData1);
    }

    @Test
    public void testGetTheAlphaString() {
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
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testBetaDataGetterAndSetter() {
        final AlphaData alphaData = new AlphaData();
        alphaData.setId("id");
        alphaData.setName("name");
        final BetaData betaData = new BetaData();
        betaData.setId("id");
        betaData.setName("name");
        final MyClass myClassUnderTest = new MyClass(alphaData, betaData);
        final BetaData betaData1 = new BetaData();
        myClassUnderTest.setBetaData(betaData1);
        assertThat(myClassUnderTest.getSecondaryValue()).isEqualTo(betaData1);
    }

    @Test
    public void testBetaData1GetterAndSetter() {
        final AlphaData alphaData = new AlphaData();
        alphaData.setId("id");
        alphaData.setName("name");
        final BetaData betaData = new BetaData();
        betaData.setId("id");
        betaData.setName("name");
        final MyClass myClassUnderTest = new MyClass(alphaData, betaData);
        final BetaData betaData1 = new BetaData();
        myClassUnderTest.setBetaData(betaData1);
        assertThat(myClassUnderTest.getThirdValue()).isEqualTo(betaData1);
    }

    @Test
    public void testBetaData2GetterAndSetter() {
        final AlphaData alphaData = new AlphaData();
        alphaData.setId("id");
        alphaData.setName("name");
        final BetaData betaData = new BetaData();
        betaData.setId("id");
        betaData.setName("name");
        final MyClass myClassUnderTest = new MyClass(alphaData, betaData);
        final BetaData betaData1 = new BetaData();
        myClassUnderTest.setBetaData(betaData1);
        assertThat(myClassUnderTest.getFourthValue()).isEqualTo(betaData1);
    }

    @Test
    public void testBetaData3GetterAndSetter() {
        final AlphaData alphaData = new AlphaData();
        alphaData.setId("id");
        alphaData.setName("name");
        final BetaData betaData = new BetaData();
        betaData.setId("id");
        betaData.setName("name");
        final MyClass myClassUnderTest = new MyClass(alphaData, betaData);
        final BetaData betaData1 = new BetaData();
        myClassUnderTest.setBetaData(betaData1);
        assertThat(myClassUnderTest.getBetaData()).isEqualTo(betaData1);
    }

    @Test
    public void testGetTheBetaString() {
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
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testGammaDataGetterAndSetter() {
        final AlphaData alphaData = new AlphaData();
        alphaData.setId("id");
        alphaData.setName("name");
        final BetaData betaData = new BetaData();
        betaData.setId("id");
        betaData.setName("name");
        final MyClass myClassUnderTest = new MyClass(alphaData, betaData);
        final GammaData gammaData = new GammaData();
        myClassUnderTest.setGammaData(gammaData);
        assertThat(myClassUnderTest.getGammaData()).isEqualTo(gammaData);
    }

    @Test
    public void testStartDateGetterAndSetter() {
        final AlphaData alphaData = new AlphaData();
        alphaData.setId("id");
        alphaData.setName("name");
        final BetaData betaData = new BetaData();
        betaData.setId("id");
        betaData.setName("name");
        final MyClass myClassUnderTest = new MyClass(alphaData, betaData);
        final LocalDate startDate = LocalDate.of(2020, 1, 1);
        myClassUnderTest.setStartDate(startDate);
        assertThat(myClassUnderTest.getStartDate()).isEqualTo(startDate);
    }

    @Test
    public void testStartDate1GetterAndSetter() {
        final AlphaData alphaData = new AlphaData();
        alphaData.setId("id");
        alphaData.setName("name");
        final BetaData betaData = new BetaData();
        betaData.setId("id");
        betaData.setName("name");
        final MyClass myClassUnderTest = new MyClass(alphaData, betaData);
        final LocalDate startDate = LocalDate.of(2020, 1, 1);
        myClassUnderTest.setStartDate(startDate);
        assertThat(myClassUnderTest.getFirstDate()).isEqualTo(startDate);
    }

    @Test
    public void testStartDate2GetterAndSetter() {
        final AlphaData alphaData = new AlphaData();
        alphaData.setId("id");
        alphaData.setName("name");
        final BetaData betaData = new BetaData();
        betaData.setId("id");
        betaData.setName("name");
        final MyClass myClassUnderTest = new MyClass(alphaData, betaData);
        final LocalDate startDate = LocalDate.of(2020, 1, 1);
        myClassUnderTest.setFirstDate(startDate);
        assertThat(myClassUnderTest.getStartDate()).isEqualTo(startDate);
    }

    @Test
    public void testSerializedValueGetterAndSetter() {
        final AlphaData alphaData = new AlphaData();
        alphaData.setId("id");
        alphaData.setName("name");
        final BetaData betaData = new BetaData();
        betaData.setId("id");
        betaData.setName("name");
        final MyClass myClassUnderTest = new MyClass(alphaData, betaData);
        final String serializedValue = "serializedValue";
        myClassUnderTest.setSerializedValue(serializedValue);
        assertThat(myClassUnderTest.getSerializedValue()).isEqualTo(serializedValue);
    }

    @Test
    public void testSerializedValue1GetterAndSetter() {
        final AlphaData alphaData = new AlphaData();
        alphaData.setId("id");
        alphaData.setName("name");
        final BetaData betaData = new BetaData();
        betaData.setId("id");
        betaData.setName("name");
        final MyClass myClassUnderTest = new MyClass(alphaData, betaData);
        final String serializedValue = "serializedValue";
        myClassUnderTest.setSerializedValue(serializedValue);
        assertThat(myClassUnderTest.getFirstSerializedValue()).isEqualTo(serializedValue);
    }

    @Test
    public void testSerializedValue2GetterAndSetter() {
        final AlphaData alphaData = new AlphaData();
        alphaData.setId("id");
        alphaData.setName("name");
        final BetaData betaData = new BetaData();
        betaData.setId("id");
        betaData.setName("name");
        final MyClass myClassUnderTest = new MyClass(alphaData, betaData);
        final String serializedValue = "serializedValue";
        myClassUnderTest.setFirstSerializedValue(serializedValue);
        assertThat(myClassUnderTest.getSerializedValue()).isEqualTo(serializedValue);
    }
}
