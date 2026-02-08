package com.myapp

import groovy.transform.CompileStatic
import org.mockito.Mock
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import java.time.LocalDate

import static org.mockito.Mockito.when
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
class MyClassTest {

    @Mock
    private AlphaData mockAlphaData
    @Mock
    private BetaData mockBetaData
    @Mock
    private GammaData mockGammaData

    @BeforeMethod
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
