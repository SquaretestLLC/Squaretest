package com.myapp

import groovy.transform.CompileStatic
import org.mockito.Mock
import org.mockito.testng.MockitoTestNGListener
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Listeners
import org.testng.annotations.Test

@CompileStatic
@Listeners(MockitoTestNGListener.class)
class MyClassTest {

    @Mock
    private AlphaData mockAlphaData
    @Mock
    private BetaData mockBetaData

    private MyClass myClassUnderTest

    @BeforeMethod
    void setUp() {
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
