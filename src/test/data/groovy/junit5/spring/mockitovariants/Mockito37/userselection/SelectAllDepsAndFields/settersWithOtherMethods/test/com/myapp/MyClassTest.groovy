package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock

import java.time.LocalDate

import static org.mockito.MockitoAnnotations.openMocks

@CompileStatic
class MyClassTest {

    @Mock
    private AlphaData mockAlphaData
    @Mock
    private BetaData mockBetaData
    @Mock
    private GammaData mockGammaData

    private MyClass myClassUnderTest

    private AutoCloseable mockitoCloseable

    @BeforeEach
    void setUp() {
        mockitoCloseable = openMocks(this)
        myClassUnderTest = new MyClass()
        myClassUnderTest.setAlphaData(mockAlphaData)
        myClassUnderTest.setBetaData(mockBetaData)
        myClassUnderTest.setGammaData(mockGammaData)
        myClassUnderTest.setStartDate(LocalDate.of(2020, 1, 1))
        myClassUnderTest.setSerializedValue("serializedValue")
    }

    @AfterEach
    void tearDown() {
        mockitoCloseable.close()
    }

    @Test
    void testToString() {
        assert "result" == myClassUnderTest.toString()
    }
}
