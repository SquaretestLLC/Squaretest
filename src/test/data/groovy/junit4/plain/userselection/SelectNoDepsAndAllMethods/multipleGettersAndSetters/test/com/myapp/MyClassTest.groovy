package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test

import java.time.LocalDate

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        def alphaData = new AlphaData()
        alphaData.setId("id")
        alphaData.setName("name")
        def betaData = new BetaData()
        betaData.setId("id")
        betaData.setName("name")
        myClassUnderTest = new MyClass(alphaData, betaData)
    }

    @Test
    void testAlphaDataGetterAndSetter() {
        def alphaData = new AlphaData()
        myClassUnderTest.setPrimaryValue(alphaData)
        assert alphaData == myClassUnderTest.getPrimaryValue()
    }

    @Test
    void testAlphaData1GetterAndSetter() {
        def alphaData = new AlphaData()
        myClassUnderTest.setPrimaryValue(alphaData)
        assert alphaData == myClassUnderTest.getAlphaData()
    }

    @Test
    void testAlphaData2GetterAndSetter() {
        def alphaData = new AlphaData()
        myClassUnderTest.setAlphaData(alphaData)
        assert alphaData == myClassUnderTest.getPrimaryValue()
    }

    @Test
    void testGetTheAlphaString() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getTheAlphaString()

        // Verify the results
        assert "result" == result
    }

    @Test
    void testBetaDataGetterAndSetter() {
        def betaData = new BetaData()
        myClassUnderTest.setBetaData(betaData)
        assert betaData == myClassUnderTest.getSecondaryValue()
    }

    @Test
    void testBetaData1GetterAndSetter() {
        def betaData = new BetaData()
        myClassUnderTest.setBetaData(betaData)
        assert betaData == myClassUnderTest.getThirdValue()
    }

    @Test
    void testBetaData2GetterAndSetter() {
        def betaData = new BetaData()
        myClassUnderTest.setBetaData(betaData)
        assert betaData == myClassUnderTest.getFourthValue()
    }

    @Test
    void testBetaData3GetterAndSetter() {
        def betaData = new BetaData()
        myClassUnderTest.setBetaData(betaData)
        assert betaData == myClassUnderTest.getBetaData()
    }

    @Test
    void testGetTheBetaString() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getTheBetaString()

        // Verify the results
        assert "result" == result
    }

    @Test
    void testGammaDataGetterAndSetter() {
        def gammaData = new GammaData()
        myClassUnderTest.setGammaData(gammaData)
        assert gammaData == myClassUnderTest.getGammaData()
    }

    @Test
    void testStartDateGetterAndSetter() {
        def startDate = LocalDate.of(2020, 1, 1)
        myClassUnderTest.setStartDate(startDate)
        assert startDate == myClassUnderTest.getStartDate()
    }

    @Test
    void testStartDate1GetterAndSetter() {
        def startDate = LocalDate.of(2020, 1, 1)
        myClassUnderTest.setStartDate(startDate)
        assert startDate == myClassUnderTest.getFirstDate()
    }

    @Test
    void testStartDate2GetterAndSetter() {
        def startDate = LocalDate.of(2020, 1, 1)
        myClassUnderTest.setFirstDate(startDate)
        assert startDate == myClassUnderTest.getStartDate()
    }

    @Test
    void testSerializedValueGetterAndSetter() {
        def serializedValue = "serializedValue"
        myClassUnderTest.setSerializedValue(serializedValue)
        assert serializedValue == myClassUnderTest.getSerializedValue()
    }

    @Test
    void testSerializedValue1GetterAndSetter() {
        def serializedValue = "serializedValue"
        myClassUnderTest.setSerializedValue(serializedValue)
        assert serializedValue == myClassUnderTest.getFirstSerializedValue()
    }

    @Test
    void testSerializedValue2GetterAndSetter() {
        def serializedValue = "serializedValue"
        myClassUnderTest.setFirstSerializedValue(serializedValue)
        assert serializedValue == myClassUnderTest.getSerializedValue()
    }
}
