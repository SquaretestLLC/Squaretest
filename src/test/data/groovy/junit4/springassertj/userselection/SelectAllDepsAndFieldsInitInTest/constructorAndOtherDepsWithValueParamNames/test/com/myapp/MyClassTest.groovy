package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.springframework.test.util.ReflectionTestUtils

import static org.assertj.core.api.Assertions.assertThat
import static org.mockito.Mockito.when

@RunWith(MockitoJUnitRunner.class)
@CompileStatic
class MyClassTest {

    @Mock
    private FooService mockFooService
    @Mock
    private BarService mockBarService
    @Mock
    private GammaService mockGammaService
    @Mock
    private BetaService mockBetaService
    @Mock
    private DeltaService mockDeltaService
    @Mock
    private Map<String, String> mockSpecialMap
    @Mock
    private AlphaService mockAlphaService

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass(mockFooService, mockBarService, mockGammaService, "defaultBarId")
        myClassUnderTest.setDeltaService(mockDeltaService)
        ReflectionTestUtils.setField(myClassUnderTest, "specialMap", mockSpecialMap)
        ReflectionTestUtils.setField(myClassUnderTest, "alphaService", mockAlphaService)
        myClassUnderTest.betaService = mockBetaService
    }

    @Test
    void testGetFooAndBar1() {
        // Setup
        // Configure FooService.getFoo1(...).
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")
        when(mockFooService.getFoo1("id")).thenReturn(fooData)

        // Configure BarService.getDefaultBar1(...).
        def barData = new BarData()
        barData.setId("id")
        barData.setName("name")
        when(mockBarService.getDefaultBar1("defaultBarId")).thenReturn(barData)

        // Run the test
        def result = myClassUnderTest.getFooAndBar1("id")

        // Verify the results
    }

    @Test
    void testGetAlpha1() {
        // Setup
        // Configure AlphaService.getAlpha1(...).
        def alphaData = new AlphaData()
        alphaData.setId("id")
        alphaData.setName("name")
        when(mockAlphaService.getAlpha1("id")).thenReturn(alphaData)

        // Run the test
        def result = myClassUnderTest.getAlpha1("id")

        // Verify the results
    }

    @Test
    void testGetBeta1() {
        // Setup
        // Configure BetaService.getBeta1(...).
        def betaData = new BetaData()
        betaData.setId("id")
        betaData.setName("name")
        when(mockBetaService.getBeta1("id")).thenReturn(betaData)

        // Run the test
        def result = myClassUnderTest.getBeta1("id")

        // Verify the results
    }

    @Test
    void testGetGamma1() {
        // Setup
        // Configure GammaService.getGamma1(...).
        def gammaData = new GammaData()
        gammaData.setId("id")
        gammaData.setName("name")
        when(mockGammaService.getGamma1("id")).thenReturn(gammaData)

        // Run the test
        def result = myClassUnderTest.getGamma1("id")

        // Verify the results
    }

    @Test
    void testGetGamma2() {
        // Setup
        when(mockSpecialMap.get("GammaId")).thenReturn("id")

        // Configure GammaService.getGamma1(...).
        def gammaData = new GammaData()
        gammaData.setId("id")
        gammaData.setName("name")
        when(mockGammaService.getGamma1("id")).thenReturn(gammaData)

        // Run the test
        def result = myClassUnderTest.getGamma2()

        // Verify the results
    }

    @Test
    void testGetGamma2_MapReturnsNull() {
        // Setup
        when(mockSpecialMap.get("GammaId")).thenReturn(null)

        // Run the test
        def result = myClassUnderTest.getGamma2()

        // Verify the results
        assertThat(result).isNull()
    }

    @Test
    void testGetEpsilon1() {
        // Setup
        // Run the test
        def result = MyClass.getEpsilon1("id")

        // Verify the results
    }

    @Test
    void testGetZetaData1() {
        // Setup
        // Run the test
        def result = MyClass.getZetaData1("id")

        // Verify the results
    }

    @Test
    void testSetFooService() {
        def fooService = null
        myClassUnderTest.setFooService(fooService)
    }

    @Test
    void testSetEpsilonService() {
        // Setup
        def value = null

        // Run the test
        MyClass.setEpsilonService(value)

        // Verify the results
    }
}
