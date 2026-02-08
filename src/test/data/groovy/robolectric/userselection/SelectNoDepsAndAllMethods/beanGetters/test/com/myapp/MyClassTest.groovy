package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@CompileStatic
@RunWith(RobolectricTestRunner.class)
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
        assert expectedResult == myClassUnderTest.getBetaData()
    }
}
