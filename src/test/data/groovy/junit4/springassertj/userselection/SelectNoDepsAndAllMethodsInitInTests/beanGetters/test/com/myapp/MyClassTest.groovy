package com.myapp

import groovy.transform.CompileStatic
import org.junit.Test

import static org.assertj.core.api.Assertions.assertThat

@CompileStatic
class MyClassTest {

    @Test
    void testGetAlphaData() {
        def myClassUnderTest = new MyClass(new AlphaData(), new BetaData())
        def result = myClassUnderTest.getAlphaData()
    }

    @Test
    void testGetBetaData() {
        def myClassUnderTest = new MyClass(new AlphaData(), new BetaData())
        def expectedResult = new BetaData()
        assertThat(myClassUnderTest.getBetaData()).isEqualTo(expectedResult)
    }
}
