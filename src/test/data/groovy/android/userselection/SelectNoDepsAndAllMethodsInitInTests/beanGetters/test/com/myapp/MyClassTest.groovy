package com.myapp

import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import groovy.transform.CompileStatic
import org.junit.Test
import org.junit.runner.RunWith

@CompileStatic
@RunWith(AndroidJUnit4.class)
@SmallTest
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
