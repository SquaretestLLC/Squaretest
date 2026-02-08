package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass(new AlphaData(), new BetaData());
    }

    @Test
    void testGetAlphaData() {
        final AlphaData result = myClassUnderTest.getAlphaData();
    }

    @Test
    void testGetBetaData() {
        final BetaData expectedResult = new BetaData();
        assertEquals(expectedResult, myClassUnderTest.getBetaData());
    }
}
