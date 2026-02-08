package com.myapp

import groovy.transform.CompileStatic
import org.testng.annotations.Test

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
        assert expectedResult == myClassUnderTest.getBetaData()
    }
}
