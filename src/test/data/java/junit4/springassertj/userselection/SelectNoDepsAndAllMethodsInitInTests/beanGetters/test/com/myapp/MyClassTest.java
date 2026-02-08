package com.myapp;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MyClassTest {

    @Test
    public void testGetAlphaData() {
        final MyClass myClassUnderTest = new MyClass(new AlphaData(), new BetaData());
        final AlphaData result = myClassUnderTest.getAlphaData();
    }

    @Test
    public void testGetBetaData() {
        final MyClass myClassUnderTest = new MyClass(new AlphaData(), new BetaData());
        final BetaData expectedResult = new BetaData();
        assertThat(myClassUnderTest.getBetaData()).isEqualTo(expectedResult);
    }
}
