package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import java.time.LocalDate

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass()
        myClassUnderTest.setAlphaData(new AlphaData())
        myClassUnderTest.setBetaData(new BetaData())
        myClassUnderTest.setGammaData(new GammaData())
        myClassUnderTest.setStartDate(LocalDate.of(2020, 1, 1))
        myClassUnderTest.setSerializedValue("serializedValue")
    }

    @Test
    void testToString() {
        assert "result" == myClassUnderTest.toString()
    }
}
