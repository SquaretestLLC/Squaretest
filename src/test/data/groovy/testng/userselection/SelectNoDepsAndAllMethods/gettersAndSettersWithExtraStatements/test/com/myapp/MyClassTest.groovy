package com.myapp

import groovy.transform.CompileStatic
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import java.time.LocalDate

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @BeforeMethod
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
        myClassUnderTest.setAlphaData(alphaData)
        assert alphaData == myClassUnderTest.getAlphaData()
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
    void testSerializedValueGetterAndSetter() {
        def serializedValue = "serializedValue"
        myClassUnderTest.setSerializedValue(serializedValue)
        assert serializedValue == myClassUnderTest.getSerializedValue()
    }
}
