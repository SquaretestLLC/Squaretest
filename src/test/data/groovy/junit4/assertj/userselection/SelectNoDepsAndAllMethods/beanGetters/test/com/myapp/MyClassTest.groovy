package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test

import static org.assertj.core.api.Assertions.assertThat

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass(new AlphaData(), new BetaData())
    }

    @Test
    void testGetAlphaData() {
        def result = myClassUnderTest.getAlphaData()
    }

    @Test
    void testGetBetaData() {
        def expectedResult = new BetaData()
        assertThat(myClassUnderTest.getBetaData()).isEqualTo(expectedResult)
    }
}
