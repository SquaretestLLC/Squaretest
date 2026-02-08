package com.myapp

import groovy.transform.CompileStatic
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

import java.time.LocalDate

@RunWith(MockitoJUnitRunner.class)
@CompileStatic
class MyClassTest {

    @Mock
    private AlphaData mockAlphaData
    @Mock
    private BetaData mockBetaData
    @Mock
    private GammaData mockGammaData

    @Test
    void testToString() {
        def myClassUnderTest = new MyClass()
        myClassUnderTest.setAlphaData(mockAlphaData)
        myClassUnderTest.setBetaData(mockBetaData)
        myClassUnderTest.setGammaData(mockGammaData)
        myClassUnderTest.setStartDate(LocalDate.of(2020, 1, 1))
        myClassUnderTest.setSerializedValue("serializedValue")
        assert "result" == myClassUnderTest.toString()
    }
}
