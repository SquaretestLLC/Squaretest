package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private AlphaData mockAlphaData;
    @Mock
    private BetaData mockBetaData;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void testGetAlphaData() {
        final MyClass myClassUnderTest = new MyClass(mockAlphaData, mockBetaData);
        assertEquals(mockAlphaData, myClassUnderTest.getAlphaData());
    }

    @Test
    void testGetBetaData() {
        final MyClass myClassUnderTest = new MyClass(mockAlphaData, mockBetaData);
        assertEquals(mockBetaData, myClassUnderTest.getBetaData());
    }
}
