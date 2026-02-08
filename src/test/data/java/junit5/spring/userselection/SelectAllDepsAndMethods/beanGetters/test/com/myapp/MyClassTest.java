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

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockAlphaData, mockBetaData);
    }

    @Test
    void testGetAlphaData() {
        assertEquals(mockAlphaData, myClassUnderTest.getAlphaData());
    }

    @Test
    void testGetBetaData() {
        assertEquals(mockBetaData, myClassUnderTest.getBetaData());
    }
}
