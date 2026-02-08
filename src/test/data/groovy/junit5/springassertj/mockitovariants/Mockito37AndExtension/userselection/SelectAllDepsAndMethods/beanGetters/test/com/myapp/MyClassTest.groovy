package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
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

    private MyClass myClassUnderTest

    @BeforeEach
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
