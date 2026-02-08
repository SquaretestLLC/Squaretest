package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
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

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass(mockAlphaData, mockBetaData)
    }

    @Test
    void testGetAlphaData() {
        assertThat(myClassUnderTest.getAlphaData()).isEqualTo(mockAlphaData)
    }

    @Test
    void testGetBetaData() {
        assertThat(myClassUnderTest.getBetaData()).isEqualTo(mockBetaData)
    }
}
