package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
        assertThat(myClassUnderTest.getBetaData()).isEqualTo(expectedResult);
    }
}
