package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.robolectric.RobolectricTestRunner

import java.time.LocalDate

import static org.mockito.Mockito.when
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
@RunWith(RobolectricTestRunner.class)
class MyClassTest {

    @Mock
    private AlphaData mockAlphaData
    @Mock
    private BetaData mockBetaData
    @Mock
    private GammaData mockGammaData

    @Before
    void setUp() {
        initMocks(this)
    }

    @Test
    void testAlphaDataGetterAndSetter() {
        def myClassUnderTest = new MyClass(mockAlphaData, mockBetaData)
        myClassUnderTest.setGammaData(mockGammaData)
        def alphaData = new AlphaData()
        myClassUnderTest.setAlphaData(alphaData)
        assert alphaData == myClassUnderTest.getAlphaData()
    }

    @Test
    void testGetTheAlphaString() {
        // Setup
        def myClassUnderTest = new MyClass(mockAlphaData, mockBetaData)
        myClassUnderTest.setGammaData(mockGammaData)
        when(mockAlphaData.getId()).thenReturn("result")
        when(mockAlphaData.getName()).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.getTheAlphaString()

        // Verify the results
        assert "result" == result
    }

    @Test
    void testBetaDataGetterAndSetter() {
        def myClassUnderTest = new MyClass(mockAlphaData, mockBetaData)
        myClassUnderTest.setGammaData(mockGammaData)
        def betaData = new BetaData()
        myClassUnderTest.setBetaData(betaData)
        assert betaData == myClassUnderTest.getBetaData()
    }

    @Test
    void testGetTheBetaString() {
        // Setup
        def myClassUnderTest = new MyClass(mockAlphaData, mockBetaData)
        myClassUnderTest.setGammaData(mockGammaData)
        when(mockBetaData.getId()).thenReturn("result")
        when(mockBetaData.getName()).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.getTheBetaString()

        // Verify the results
        assert "result" == result
    }

    @Test
    void testGetGammaData() {
        def myClassUnderTest = new MyClass(mockAlphaData, mockBetaData)
        myClassUnderTest.setGammaData(mockGammaData)
        assert mockGammaData == myClassUnderTest.getGammaData()
    }

    @Test
    void testStartDateGetterAndSetter() {
        def myClassUnderTest = new MyClass(mockAlphaData, mockBetaData)
        myClassUnderTest.setGammaData(mockGammaData)
        def startDate = LocalDate.of(2020, 1, 1)
        myClassUnderTest.setStartDate(startDate)
        assert startDate == myClassUnderTest.getStartDate()
    }

    @Test
    void testSerializedValueGetterAndSetter() {
        def myClassUnderTest = new MyClass(mockAlphaData, mockBetaData)
        myClassUnderTest.setGammaData(mockGammaData)
        def serializedValue = "serializedValue"
        myClassUnderTest.setSerializedValue(serializedValue)
        assert serializedValue == myClassUnderTest.getSerializedValue()
    }
}
