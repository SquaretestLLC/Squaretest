package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.springframework.test.util.ReflectionTestUtils

import static org.junit.Assert.assertNull
import static org.mockito.Mockito.when

@RunWith(MockitoJUnitRunner.class)
@CompileStatic
class MyClassTest {

    @Mock
    private FooService theMockFooService
    @Mock
    private FooService theMockBarService
    @Mock
    private FooService theMockGammaService
    @Mock
    private FooService theMockBetaService
    @Mock
    private FooService theMockDeltaService
    @Mock
    private Map<String, String> theMockSpecialMap
    @Mock
    private FooService theMockAlphaService

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass(theMockFooService, theMockBarService, theMockGammaService, "defaultBarId")
        myClassUnderTest.setDeltaService(theMockDeltaService)
        ReflectionTestUtils.setField(myClassUnderTest, "specialMap", theMockSpecialMap)
        ReflectionTestUtils.setField(myClassUnderTest, "alphaService", theMockAlphaService)
        myClassUnderTest.betaService = theMockBetaService
    }

    @Test
    void testGetFooAndBar11() {
        // Setup
        // Configure FooService.getFoo1(...).
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")
        when(theMockFooService.getFoo1("id")).thenReturn(fooData)

        // Configure FooService.getDefaultBar1(...).
        def barData = new BarData()
        barData.setId("id")
        barData.setName("name")
        when(theMockBarService.getDefaultBar1("defaultBarId")).thenReturn(barData)

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
        when(theMockAlphaService.getAlpha1("id")).thenReturn(alphaData)

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
        when(theMockBetaService.getBeta1("id")).thenReturn(betaData)

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
        when(theMockGammaService.getGamma1("id")).thenReturn(gammaData)

        // Run the test
        def result = myClassUnderTest.getGamma1("id")

        // Verify the results
    }

    @Test
    void testGetGamma21() {
        // Setup
        when(theMockSpecialMap.get("GammaId")).thenReturn("id")

        // Configure FooService.getGamma1(...).
        def gammaData = new GammaData()
        gammaData.setId("id")
        gammaData.setName("name")
        when(theMockGammaService.getGamma1("id")).thenReturn(gammaData)

        // Run the test
        def result = myClassUnderTest.getGamma2()

        // Verify the results
    }

    @Test
    void testGetGamma2_MapReturnsNull1() {
        // Setup
        when(theMockSpecialMap.get("GammaId")).thenReturn(null)

        // Run the test
        def result = myClassUnderTest.getGamma2()

        // Verify the results
        assertNull(result)
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