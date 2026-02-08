package com.myapp

import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock

import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
@RunWith(AndroidJUnit4.class)
@SmallTest
class MyClassTest {

    @Mock
    private AlphaData mockAlphaData
    @Mock
    private BetaData mockBetaData

    private MyClass myClassUnderTest

    @Before
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
