package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

import java.time.LocalDate

import static org.assertj.core.api.Assertions.assertThat
import static org.mockito.Mockito.when

@ExtendWith(MockitoExtension.class)
@CompileStatic
class MyClassTest {

    @Mock
    private AlphaData mockAlphaData
    @Mock
    private BetaData mockBetaData
    @Mock
    private GammaData mockGammaData

    private MyClass myClassUnderTest

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass(mockAlphaData, mockBetaData)
        myClassUnderTest.setGammaData(mockGammaData)
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
        when(mockAlphaData.getId()).thenReturn("result")
        when(mockAlphaData.getName()).thenReturn("result")

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
        when(mockBetaData.getId()).thenReturn("result")
        when(mockBetaData.getName()).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.getTheBetaString()

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testGetGammaData() {
        assertThat(myClassUnderTest.getGammaData()).isEqualTo(mockGammaData)
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
