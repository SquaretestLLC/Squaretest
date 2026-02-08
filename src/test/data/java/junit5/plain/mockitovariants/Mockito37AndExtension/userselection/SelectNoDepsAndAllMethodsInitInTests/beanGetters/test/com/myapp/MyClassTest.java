package com.myapp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyClassTest {

    @Test
    void testGetAlphaData() {
        final MyClass myClassUnderTest = new MyClass(new AlphaData(), new BetaData());
        final AlphaData result = myClassUnderTest.getAlphaData();
    }

    @Test
    void testGetBetaData() {
        final MyClass myClassUnderTest = new MyClass(new AlphaData(), new BetaData());
        final BetaData expectedResult = new BetaData();
        assertEquals(expectedResult, myClassUnderTest.getBetaData());
    }
}
