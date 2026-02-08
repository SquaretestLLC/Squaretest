package com.myapp

import groovy.transform.CompileStatic
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

import java.time.LocalDate

import static org.assertj.core.api.Assertions.assertThat
import static org.mockito.Mockito.when

@RunWith(MockitoJUnitRunner.class)
@CompileStatic
class MyClassTest {

    @Mock
    private AlphaData mockAlphaData
    @Mock
    private BetaData mockBetaData
    @Mock
    private GammaData mockGammaData

    @Test
    void testAlphaDataGetterAndSetter() {
        def myClassUnderTest = new MyClass(mockAlphaData, mockBetaData)
        myClassUnderTest.setGammaData(mockGammaData)
        def alphaData = new AlphaData()
        myClassUnderTest.setAlphaData(alphaData)
        assertThat(myClassUnderTest.getAlphaData()).isEqualTo(alphaData)
    }

    @Test
    void testGetTheAlphaString() {
        // Setup
        def myClassUnderTest = new MyClass(mockAlphaData, mockBetaData)
        myClassUnderTest.setGammaData(mockGammaData)
        when(mockAlphaData.getId()).thenReturn("result")
        when(mockAlphaData.getName()).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.getTheAlphaString()

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testBetaDataGetterAndSetter() {
        def myClassUnderTest = new MyClass(mockAlphaData, mockBetaData)
        myClassUnderTest.setGammaData(mockGammaData)
        def betaData = new BetaData()
        myClassUnderTest.setBetaData(betaData)
        assertThat(myClassUnderTest.getBetaData()).isEqualTo(betaData)
    }

    @Test
    void testGetTheBetaString() {
        // Setup
        def myClassUnderTest = new MyClass(mockAlphaData, mockBetaData)
        myClassUnderTest.setGammaData(mockGammaData)
        when(mockBetaData.getId()).thenReturn("result")
        when(mockBetaData.getName()).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.getTheBetaString()

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testGetGammaData() {
        def myClassUnderTest = new MyClass(mockAlphaData, mockBetaData)
        myClassUnderTest.setGammaData(mockGammaData)
        assertThat(myClassUnderTest.getGammaData()).isEqualTo(mockGammaData)
    }

    @Test
    void testStartDateGetterAndSetter() {
        def myClassUnderTest = new MyClass(mockAlphaData, mockBetaData)
        myClassUnderTest.setGammaData(mockGammaData)
        def startDate = LocalDate.of(2020, 1, 1)
        myClassUnderTest.setStartDate(startDate)
        assertThat(myClassUnderTest.getStartDate()).isEqualTo(startDate)
    }

    @Test
    void testSerializedValueGetterAndSetter() {
        def myClassUnderTest = new MyClass(mockAlphaData, mockBetaData)
        myClassUnderTest.setGammaData(mockGammaData)
        def serializedValue = "serializedValue"
        myClassUnderTest.setSerializedValue(serializedValue)
        assertThat(myClassUnderTest.getSerializedValue()).isEqualTo(serializedValue)
    }
}
