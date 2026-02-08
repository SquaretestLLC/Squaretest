package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

import static org.assertj.core.api.Assertions.assertThat

@ExtendWith(MockitoExtension.class)
@CompileStatic
class MyClassTest {

    @Mock
    private AlphaData mockAlphaData
    @Mock
    private BetaData mockBetaData

    @Test
    void testGetAlphaData() {
        def myClassUnderTest = new MyClass(mockAlphaData, mockBetaData)
        assertThat(myClassUnderTest.getAlphaData()).isEqualTo(mockAlphaData)
    }

    @Test
    void testGetBetaData() {
        def myClassUnderTest = new MyClass(mockAlphaData, mockBetaData)
        assertThat(myClassUnderTest.getBetaData()).isEqualTo(mockBetaData)
    }
}
