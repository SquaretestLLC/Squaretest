package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.Test

import java.time.LocalDate

import static org.assertj.core.api.Assertions.assertThat

@CompileStatic
class MyClassTest {

    @Test
    void testToString() {
        def myClassUnderTest = new MyClass()
        myClassUnderTest.setAlphaData(new AlphaData())
        myClassUnderTest.setBetaData(new BetaData())
        myClassUnderTest.setGammaData(new GammaData())
        myClassUnderTest.setStartDate(LocalDate.of(2020, 1, 1))
        myClassUnderTest.setSerializedValue("serializedValue")
        assertThat(myClassUnderTest.toString()).isEqualTo("result")
    }
}
