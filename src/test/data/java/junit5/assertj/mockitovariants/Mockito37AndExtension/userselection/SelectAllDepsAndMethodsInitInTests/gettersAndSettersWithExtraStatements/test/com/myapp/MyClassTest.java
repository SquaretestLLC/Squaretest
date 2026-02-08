package com.myapp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MyClassTest {

    @Mock
    private AlphaData mockAlphaData;
    @Mock
    private BetaData mockBetaData;
    @Mock
    private GammaData mockGammaData;

    @Test
    void testAlphaDataGetterAndSetter() {
        final MyClass myClassUnderTest = new MyClass(mockAlphaData, mockBetaData);
        myClassUnderTest.setGammaData(mockGammaData);
        final AlphaData alphaData = new AlphaData();
        myClassUnderTest.setAlphaData(alphaData);
        assertThat(myClassUnderTest.getAlphaData()).isEqualTo(alphaData);
    }

    @Test
    void testGetTheAlphaString() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockAlphaData, mockBetaData);
        myClassUnderTest.setGammaData(mockGammaData);
        when(mockAlphaData.getId()).thenReturn("result");
        when(mockAlphaData.getName()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getTheAlphaString();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testBetaDataGetterAndSetter() {
        final MyClass myClassUnderTest = new MyClass(mockAlphaData, mockBetaData);
        myClassUnderTest.setGammaData(mockGammaData);
        final BetaData betaData = new BetaData();
        myClassUnderTest.setBetaData(betaData);
        assertThat(myClassUnderTest.getBetaData()).isEqualTo(betaData);
    }

    @Test
    void testGetTheBetaString() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockAlphaData, mockBetaData);
        myClassUnderTest.setGammaData(mockGammaData);
        when(mockBetaData.getId()).thenReturn("result");
        when(mockBetaData.getName()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getTheBetaString();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetGammaData() {
        final MyClass myClassUnderTest = new MyClass(mockAlphaData, mockBetaData);
        myClassUnderTest.setGammaData(mockGammaData);
        assertThat(myClassUnderTest.getGammaData()).isEqualTo(mockGammaData);
    }

    @Test
    void testStartDateGetterAndSetter() {
        final MyClass myClassUnderTest = new MyClass(mockAlphaData, mockBetaData);
        myClassUnderTest.setGammaData(mockGammaData);
        final LocalDate startDate = LocalDate.of(2020, 1, 1);
        myClassUnderTest.setStartDate(startDate);
        assertThat(myClassUnderTest.getStartDate()).isEqualTo(startDate);
    }

    @Test
    void testSerializedValueGetterAndSetter() {
        final MyClass myClassUnderTest = new MyClass(mockAlphaData, mockBetaData);
        myClassUnderTest.setGammaData(mockGammaData);
        final String serializedValue = "serializedValue";
        myClassUnderTest.setSerializedValue(serializedValue);
        assertThat(myClassUnderTest.getSerializedValue()).isEqualTo(serializedValue);
    }
}
