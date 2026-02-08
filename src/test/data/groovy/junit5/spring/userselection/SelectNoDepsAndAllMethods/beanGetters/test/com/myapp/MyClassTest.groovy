package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @BeforeEach
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
        assert expectedResult == myClassUnderTest.getBetaData()
    }
}
