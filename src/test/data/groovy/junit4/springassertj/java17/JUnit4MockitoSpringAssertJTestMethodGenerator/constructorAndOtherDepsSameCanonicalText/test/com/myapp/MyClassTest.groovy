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
    private FooService mockBarService
    @Mock
    private FooService mockGammaService
    @Mock
    private FooService mockBetaService
    @Mock
    private FooService mockDeltaService
    @Mock
    private Map<String, String> mockSpecialMap
    @Mock
    private FooService mockAlphaService

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
    void testGetFooAndBar11() {
        // Setup
        // Configure FooService.getFoo1(...).
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")
        when(mockFooService.getFoo1("id")).thenReturn(fooData)

        // Configure FooService.getDefaultBar1(...).
        def barData = new BarData()
        barData.setId("id")
        barData.setName("name")
        when(mockBarService.getDefaultBar1("defaultBarId")).thenReturn(barData)

        // Run the test
        def result = myClassUnderTest.getFooAndBar1("id")

        // Verify the results
    }

    @Test
    void testGetAlpha11() {
        // Setup
        // Configure FooService.getAlpha1(...).
        def alphaData = new AlphaData()
        alphaData.setId("id")
        alphaData.setName("name")
        when(mockAlphaService.getAlpha1("id")).thenReturn(alphaData)

        // Run the test
        def result = myClassUnderTest.getAlpha1("id")

        // Verify the results
    }

    @Test
    void testGetBeta11() {
        // Setup
        // Configure FooService.getBeta1(...).
        def betaData = new BetaData()
        betaData.setId("id")
        betaData.setName("name")
        when(mockBetaService.getBeta1("id")).thenReturn(betaData)

        // Run the test
        def result = myClassUnderTest.getBeta1("id")

        // Verify the results
    }

    @Test
    void testGetGamma11() {
        // Setup
        // Configure FooService.getGamma1(...).
        def gammaData = new GammaData()
        gammaData.setId("id")
        gammaData.setName("name")
        when(mockGammaService.getGamma1("id")).thenReturn(gammaData)

        // Run the test
        def result = myClassUnderTest.getGamma1("id")

        // Verify the results
    }

    @Test
    void testGetGamma21() {
        // Setup
        when(mockSpecialMap.get("GammaId")).thenReturn("id")

        // Configure FooService.getGamma1(...).
        def gammaData = new GammaData()
        gammaData.setId("id")
        gammaData.setName("name")
        when(mockGammaService.getGamma1("id")).thenReturn(gammaData)

        // Run the test
        def result = myClassUnderTest.getGamma2()

        // Verify the results
    }

    @Test
    void testGetGamma2_MapReturnsNull1() {
        // Setup
        when(mockSpecialMap.get("GammaId")).thenReturn(null)

        // Run the test
        def result = myClassUnderTest.getGamma2()

        // Verify the results
        assertThat(result).isNull()
    }

    @Test
    void testGetEpsilon11() {
        // Setup
        // Run the test
        def result = MyClass.getEpsilon1("id")

        // Verify the results
    }

    @Test
    void testGetZetaData11() {
        // Setup
        // Run the test
        def result = MyClass.getZetaData1("id")

        // Verify the results
    }

    @Test
    void testSetFooService1() {
        def fooService = null
        myClassUnderTest.setFooService(fooService)
    }

    @Test
    void testSetEpsilonService1() {
        // Setup
        def epsilonService = null

        // Run the test
        MyClass.setEpsilonService(epsilonService)

        // Verify the results
    }
}