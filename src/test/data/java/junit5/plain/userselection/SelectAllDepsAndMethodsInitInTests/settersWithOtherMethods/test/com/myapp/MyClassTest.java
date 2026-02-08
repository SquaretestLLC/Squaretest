package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private AlphaData mockAlphaData;
    @Mock
    private BetaData mockBetaData;
    @Mock
    private GammaData mockGammaData;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void testToString() {
        final MyClass myClassUnderTest = new MyClass();
        myClassUnderTest.setAlphaData(mockAlphaData);
        myClassUnderTest.setBetaData(mockBetaData);
        myClassUnderTest.setGammaData(mockGammaData);
        myClassUnderTest.setStartDate(LocalDate.of(2020, 1, 1));
        myClassUnderTest.setSerializedValue("serializedValue");
        assertEquals("result", myClassUnderTest.toString());
    }
}
