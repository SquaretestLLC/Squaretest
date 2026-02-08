package com.myapp

import groovy.transform.CompileStatic
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @BeforeMethod
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
