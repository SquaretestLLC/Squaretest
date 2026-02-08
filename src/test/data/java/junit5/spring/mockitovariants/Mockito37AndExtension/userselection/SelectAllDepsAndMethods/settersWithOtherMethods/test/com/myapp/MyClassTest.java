package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class MyClassTest {

    @Mock
    private AlphaData mockAlphaData;
    @Mock
    private BetaData mockBetaData;
    @Mock
    private GammaData mockGammaData;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass();
        myClassUnderTest.setAlphaData(mockAlphaData);
        myClassUnderTest.setBetaData(mockBetaData);
        myClassUnderTest.setGammaData(mockGammaData);
        myClassUnderTest.setStartDate(LocalDate.of(2020, 1, 1));
        myClassUnderTest.setSerializedValue("serializedValue");
    }

    @Test
    void testToString() {
        assertEquals("result", myClassUnderTest.toString());
    }
}
