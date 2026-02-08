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

    @BeforeEach
    void setUp() {
        initMocks(this)
    }

    @Test
    void testGetAlphaData() {
        def myClassUnderTest = new MyClass(mockAlphaData, mockBetaData)
        assert mockAlphaData == myClassUnderTest.getAlphaData()
    }

    @Test
    void testGetBetaData() {
        def myClassUnderTest = new MyClass(mockAlphaData, mockBetaData)
        assert mockBetaData == myClassUnderTest.getBetaData()
    }
}
