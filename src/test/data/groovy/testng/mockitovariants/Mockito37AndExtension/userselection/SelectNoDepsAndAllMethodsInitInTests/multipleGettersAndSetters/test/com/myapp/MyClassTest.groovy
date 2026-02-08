package com.myapp

import groovy.transform.CompileStatic
import org.testng.annotations.Test

import java.time.LocalDate

@CompileStatic
class MyClassTest {

    @Test
    void testAlphaDataGetterAndSetter() {
        def alphaData = new AlphaData()
        alphaData.setId("id")
        alphaData.setName("name")
        def betaData = new BetaData()
        betaData.setId("id")
        betaData.setName("name")
        def myClassUnderTest = new MyClass(alphaData, betaData)
        def alphaData1 = new AlphaData()
        myClassUnderTest.setPrimaryValue(alphaData1)
        assert alphaData1 == myClassUnderTest.getPrimaryValue()
    }

    @Test
    void testAlphaData1GetterAndSetter() {
        def alphaData = new AlphaData()
        alphaData.setId("id")
        alphaData.setName("name")
        def betaData = new BetaData()
        betaData.setId("id")
        betaData.setName("name")
        def myClassUnderTest = new MyClass(alphaData, betaData)
        def alphaData1 = new AlphaData()
        myClassUnderTest.setPrimaryValue(alphaData1)
        assert alphaData1 == myClassUnderTest.getAlphaData()
    }

    @Test
    void testAlphaData2GetterAndSetter() {
        def alphaData = new AlphaData()
        alphaData.setId("id")
        alphaData.setName("name")
        def betaData = new BetaData()
        betaData.setId("id")
        betaData.setName("name")
        def myClassUnderTest = new MyClass(alphaData, betaData)
        def alphaData1 = new AlphaData()
        myClassUnderTest.setAlphaData(alphaData1)
        assert alphaData1 == myClassUnderTest.getPrimaryValue()
    }

    @Test
    void testGetTheAlphaString() {
        // Setup
        def alphaData = new AlphaData()
        alphaData.setId("id")
        alphaData.setName("name")
        def betaData = new BetaData()
        betaData.setId("id")
        betaData.setName("name")
        def myClassUnderTest = new MyClass(alphaData, betaData)

        // Run the test
        def result = myClassUnderTest.getTheAlphaString()

        // Verify the results
        assert "result" == result
    }

    @Test
    void testBetaDataGetterAndSetter() {
        def alphaData = new AlphaData()
        alphaData.setId("id")
        alphaData.setName("name")
        def betaData = new BetaData()
        betaData.setId("id")
        betaData.setName("name")
        def myClassUnderTest = new MyClass(alphaData, betaData)
        def betaData1 = new BetaData()
        myClassUnderTest.setBetaData(betaData1)
        assert betaData1 == myClassUnderTest.getSecondaryValue()
    }

    @Test
    void testBetaData1GetterAndSetter() {
        def alphaData = new AlphaData()
        alphaData.setId("id")
        alphaData.setName("name")
        def betaData = new BetaData()
        betaData.setId("id")
        betaData.setName("name")
        def myClassUnderTest = new MyClass(alphaData, betaData)
        def betaData1 = new BetaData()
        myClassUnderTest.setBetaData(betaData1)
        assert betaData1 == myClassUnderTest.getThirdValue()
    }

    @Test
    void testBetaData2GetterAndSetter() {
        def alphaData = new AlphaData()
        alphaData.setId("id")
        alphaData.setName("name")
        def betaData = new BetaData()
        betaData.setId("id")
        betaData.setName("name")
        def myClassUnderTest = new MyClass(alphaData, betaData)
        def betaData1 = new BetaData()
        myClassUnderTest.setBetaData(betaData1)
        assert betaData1 == myClassUnderTest.getFourthValue()
    }

    @Test
    void testBetaData3GetterAndSetter() {
        def alphaData = new AlphaData()
        alphaData.setId("id")
        alphaData.setName("name")
        def betaData = new BetaData()
        betaData.setId("id")
        betaData.setName("name")
        def myClassUnderTest = new MyClass(alphaData, betaData)
        def betaData1 = new BetaData()
        myClassUnderTest.setBetaData(betaData1)
        assert betaData1 == myClassUnderTest.getBetaData()
    }

    @Test
    void testGetTheBetaString() {
        // Setup
        def alphaData = new AlphaData()
        alphaData.setId("id")
        alphaData.setName("name")
        def betaData = new BetaData()
        betaData.setId("id")
        betaData.setName("name")
        def myClassUnderTest = new MyClass(alphaData, betaData)

        // Run the test
        def result = myClassUnderTest.getTheBetaString()

        // Verify the results
        assert "result" == result
    }

    @Test
    void testGammaDataGetterAndSetter() {
        def alphaData = new AlphaData()
        alphaData.setId("id")
        alphaData.setName("name")
        def betaData = new BetaData()
        betaData.setId("id")
        betaData.setName("name")
        def myClassUnderTest = new MyClass(alphaData, betaData)
        def gammaData = new GammaData()
        myClassUnderTest.setGammaData(gammaData)
        assert gammaData == myClassUnderTest.getGammaData()
    }

    @Test
    void testStartDateGetterAndSetter() {
        def alphaData = new AlphaData()
        alphaData.setId("id")
        alphaData.setName("name")
        def betaData = new BetaData()
        betaData.setId("id")
        betaData.setName("name")
        def myClassUnderTest = new MyClass(alphaData, betaData)
        def startDate = LocalDate.of(2020, 1, 1)
        myClassUnderTest.setStartDate(startDate)
        assert startDate == myClassUnderTest.getStartDate()
    }

    @Test
    void testStartDate1GetterAndSetter() {
        def alphaData = new AlphaData()
        alphaData.setId("id")
        alphaData.setName("name")
        def betaData = new BetaData()
        betaData.setId("id")
        betaData.setName("name")
        def myClassUnderTest = new MyClass(alphaData, betaData)
        def startDate = LocalDate.of(2020, 1, 1)
        myClassUnderTest.setStartDate(startDate)
        assert startDate == myClassUnderTest.getFirstDate()
    }

    @Test
    void testStartDate2GetterAndSetter() {
        def alphaData = new AlphaData()
        alphaData.setId("id")
        alphaData.setName("name")
        def betaData = new BetaData()
        betaData.setId("id")
        betaData.setName("name")
        def myClassUnderTest = new MyClass(alphaData, betaData)
        def startDate = LocalDate.of(2020, 1, 1)
        myClassUnderTest.setFirstDate(startDate)
        assert startDate == myClassUnderTest.getStartDate()
    }

    @Test
    void testSerializedValueGetterAndSetter() {
        def alphaData = new AlphaData()
        alphaData.setId("id")
        alphaData.setName("name")
        def betaData = new BetaData()
        betaData.setId("id")
        betaData.setName("name")
        def myClassUnderTest = new MyClass(alphaData, betaData)
        def serializedValue = "serializedValue"
        myClassUnderTest.setSerializedValue(serializedValue)
        assert serializedValue == myClassUnderTest.getSerializedValue()
    }

    @Test
    void testSerializedValue1GetterAndSetter() {
        def alphaData = new AlphaData()
        alphaData.setId("id")
        alphaData.setName("name")
        def betaData = new BetaData()
        betaData.setId("id")
        betaData.setName("name")
        def myClassUnderTest = new MyClass(alphaData, betaData)
        def serializedValue = "serializedValue"
        myClassUnderTest.setSerializedValue(serializedValue)
        assert serializedValue == myClassUnderTest.getFirstSerializedValue()
    }

    @Test
    void testSerializedValue2GetterAndSetter() {
        def alphaData = new AlphaData()
        alphaData.setId("id")
        alphaData.setName("name")
        def betaData = new BetaData()
        betaData.setId("id")
        betaData.setName("name")
        def myClassUnderTest = new MyClass(alphaData, betaData)
        def serializedValue = "serializedValue"
        myClassUnderTest.setFirstSerializedValue(serializedValue)
        assert serializedValue == myClassUnderTest.getSerializedValue()
    }
}
