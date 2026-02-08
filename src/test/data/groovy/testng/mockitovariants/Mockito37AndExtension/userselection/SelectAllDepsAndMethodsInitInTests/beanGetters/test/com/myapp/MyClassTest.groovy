package com.myapp

import groovy.transform.CompileStatic
import org.mockito.Mock
import org.mockito.testng.MockitoTestNGListener
import org.testng.annotations.Listeners
import org.testng.annotations.Test

@CompileStatic
@Listeners(MockitoTestNGListener.class)
class MyClassTest {

    @Mock
    private AlphaData mockAlphaData
    @Mock
    private BetaData mockBetaData

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
