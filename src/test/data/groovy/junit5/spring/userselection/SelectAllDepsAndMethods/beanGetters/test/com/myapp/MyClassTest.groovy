package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock

import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
class MyClassTest {

    @Mock
    private AlphaData mockAlphaData
    @Mock
    private BetaData mockBetaData

    private MyClass myClassUnderTest

    @BeforeEach
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass(mockAlphaData, mockBetaData)
    }

    @Test
    void testGetAlphaData() {
        assert mockAlphaData == myClassUnderTest.getAlphaData()
    }

    @Test
    void testGetBetaData() {
        assert mockBetaData == myClassUnderTest.getBetaData()
    }
}
