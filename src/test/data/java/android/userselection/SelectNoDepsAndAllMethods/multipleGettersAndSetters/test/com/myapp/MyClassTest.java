package com.myapp;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
@SmallTest
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
        myClassUnderTest.setPrimaryValue(alphaData);
        assertEquals(alphaData, myClassUnderTest.getPrimaryValue());
    }

    @Test
    public void testAlphaData1GetterAndSetter() {
        final AlphaData alphaData = new AlphaData();
        myClassUnderTest.setPrimaryValue(alphaData);
        assertEquals(alphaData, myClassUnderTest.getAlphaData());
    }

    @Test
    public void testAlphaData2GetterAndSetter() {
        final AlphaData alphaData = new AlphaData();
        myClassUnderTest.setAlphaData(alphaData);
        assertEquals(alphaData, myClassUnderTest.getPrimaryValue());
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
        assertEquals(betaData, myClassUnderTest.getSecondaryValue());
    }

    @Test
    public void testBetaData1GetterAndSetter() {
        final BetaData betaData = new BetaData();
        myClassUnderTest.setBetaData(betaData);
        assertEquals(betaData, myClassUnderTest.getThirdValue());
    }

    @Test
    public void testBetaData2GetterAndSetter() {
        final BetaData betaData = new BetaData();
        myClassUnderTest.setBetaData(betaData);
        assertEquals(betaData, myClassUnderTest.getFourthValue());
    }

    @Test
    public void testBetaData3GetterAndSetter() {
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
    public void testStartDate1GetterAndSetter() {
        final LocalDate startDate = LocalDate.of(2020, 1, 1);
        myClassUnderTest.setStartDate(startDate);
        assertEquals(startDate, myClassUnderTest.getFirstDate());
    }

    @Test
    public void testStartDate2GetterAndSetter() {
        final LocalDate startDate = LocalDate.of(2020, 1, 1);
        myClassUnderTest.setFirstDate(startDate);
        assertEquals(startDate, myClassUnderTest.getStartDate());
    }

    @Test
    public void testSerializedValueGetterAndSetter() {
        final String serializedValue = "serializedValue";
        myClassUnderTest.setSerializedValue(serializedValue);
        assertEquals(serializedValue, myClassUnderTest.getSerializedValue());
    }

    @Test
    public void testSerializedValue1GetterAndSetter() {
        final String serializedValue = "serializedValue";
        myClassUnderTest.setSerializedValue(serializedValue);
        assertEquals(serializedValue, myClassUnderTest.getFirstSerializedValue());
    }

    @Test
    public void testSerializedValue2GetterAndSetter() {
        final String serializedValue = "serializedValue";
        myClassUnderTest.setFirstSerializedValue(serializedValue);
        assertEquals(serializedValue, myClassUnderTest.getSerializedValue());
    }
}
