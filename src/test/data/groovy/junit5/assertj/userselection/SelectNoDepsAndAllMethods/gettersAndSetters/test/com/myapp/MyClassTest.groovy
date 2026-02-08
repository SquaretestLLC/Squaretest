package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import java.time.LocalDate

import static org.assertj.core.api.Assertions.assertThat

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @BeforeEach
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
        assertThat(myClassUnderTest.getAlphaData()).isEqualTo(alphaData)
    }

    @Test
    void testGetTheAlphaString() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getTheAlphaString()

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testBetaDataGetterAndSetter() {
        def betaData = new BetaData()
        myClassUnderTest.setBetaData(betaData)
        assertThat(myClassUnderTest.getBetaData()).isEqualTo(betaData)
    }

    @Test
    void testGetTheBetaString() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getTheBetaString()

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testGammaDataGetterAndSetter() {
        def gammaData = new GammaData()
        myClassUnderTest.setGammaData(gammaData)
        assertThat(myClassUnderTest.getGammaData()).isEqualTo(gammaData)
    }

    @Test
    void testStartDateGetterAndSetter() {
        def startDate = LocalDate.of(2020, 1, 1)
        myClassUnderTest.setStartDate(startDate)
        assertThat(myClassUnderTest.getStartDate()).isEqualTo(startDate)
    }

    @Test
    void testSerializedValueGetterAndSetter() {
        def serializedValue = "serializedValue"
        myClassUnderTest.setSerializedValue(serializedValue)
        assertThat(myClassUnderTest.getSerializedValue()).isEqualTo(serializedValue)
    }
}
