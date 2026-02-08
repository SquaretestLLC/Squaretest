package com.myapp

import groovy.transform.CompileStatic
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

import static org.assertj.core.api.Assertions.assertThat

@CompileStatic
@RunWith(MockitoJUnitRunner.class)
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
