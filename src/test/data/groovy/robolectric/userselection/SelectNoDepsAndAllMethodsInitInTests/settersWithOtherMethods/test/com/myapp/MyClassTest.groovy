package com.myapp

import groovy.transform.CompileStatic
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

import java.time.LocalDate

@CompileStatic
@RunWith(RobolectricTestRunner.class)
class MyClassTest {

    @Test
    void testToString() {
        def myClassUnderTest = new MyClass()
        myClassUnderTest.setAlphaData(new AlphaData())
        myClassUnderTest.setBetaData(new BetaData())
        myClassUnderTest.setGammaData(new GammaData())
        myClassUnderTest.setStartDate(LocalDate.of(2020, 1, 1))
        myClassUnderTest.setSerializedValue("serializedValue")
        assert "result" == myClassUnderTest.toString()
    }
}
