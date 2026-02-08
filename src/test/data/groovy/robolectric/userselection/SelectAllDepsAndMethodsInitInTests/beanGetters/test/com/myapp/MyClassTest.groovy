package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.robolectric.RobolectricTestRunner

import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
@RunWith(RobolectricTestRunner.class)
class MyClassTest {

    @Mock
    private AlphaData mockAlphaData
    @Mock
    private BetaData mockBetaData

    @Before
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
