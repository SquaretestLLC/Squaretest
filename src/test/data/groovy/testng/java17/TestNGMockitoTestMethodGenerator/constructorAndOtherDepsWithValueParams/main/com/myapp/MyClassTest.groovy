package com.myapp

import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test
import org.mockito.Mock
import org.springframework.test.util.ReflectionTestUtils

import java.util.Map

import static org.testng.Assert.assertNull
import static org.mockito.Mockito.when
import static org.mockito.MockitoAnnotations.initMocks

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

    @BeforeMethod
    void setUp() {
        initMocks(this)
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
        final FooData fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")
        when(mockFooService.getFoo1("id")).thenReturn(fooData)

        // Configure BarService.getDefaultBar1(...).
        final BarData barData = new BarData()
        barData.setId("id")
        barData.setName("name")
        when(mockBarService.getDefaultBar1("defaultBarId")).thenReturn(barData)

        // Run the test
        final FooAndBar result = myClassUnderTest.getFooAndBar1("id")

        // Verify the results
    }

    @Test
    void testGetAlpha1() {
        // Setup
        // Configure AlphaService.getAlpha1(...).
        final AlphaData alphaData = new AlphaData()
        alphaData.setId("id")
        alphaData.setName("name")
        when(mockAlphaService.getAlpha1("id")).thenReturn(alphaData)

        // Run the test
        final AlphaData result = myClassUnderTest.getAlpha1("id")

        // Verify the results
    }

    @Test
    void testGetBeta1() {
        // Setup
        // Configure BetaService.getBeta1(...).
        final BetaData betaData = new BetaData()
        betaData.setId("id")
        betaData.setName("name")
        when(mockBetaService.getBeta1("id")).thenReturn(betaData)

        // Run the test
        final BetaData result = myClassUnderTest.getBeta1("id")

        // Verify the results
    }

    @Test
    void testGetGamma1() {
        // Setup
        // Configure GammaService.getGamma1(...).
        final GammaData gammaData = new GammaData()
        gammaData.setId("id")
        gammaData.setName("name")
        when(mockGammaService.getGamma1("id")).thenReturn(gammaData)

        // Run the test
        final GammaData result = myClassUnderTest.getGamma1("id")

        // Verify the results
    }

    @Test
    void testGetGamma2() {
        // Setup
        when(mockSpecialMap.get("GammaId")).thenReturn("id")

        // Configure GammaService.getGamma1(...).
        final GammaData gammaData = new GammaData()
        gammaData.setId("id")
        gammaData.setName("name")
        when(mockGammaService.getGamma1("id")).thenReturn(gammaData)

        // Run the test
        final GammaData result = myClassUnderTest.getGamma2()

        // Verify the results
    }

    @Test
    void testGetGamma2_MapReturnsNull() {
        // Setup
        when(mockSpecialMap.get("GammaId")).thenReturn(null)

        // Run the test
        final GammaData result = myClassUnderTest.getGamma2()

        // Verify the results
        assertNull(result)
    }

    @Test
    void testGetEpsilon1() {
        // Setup
        // Run the test
        final EpsilonData result = MyClass.getEpsilon1("id")

        // Verify the results
    }

    @Test
    void testGetZetaData1() {
        // Setup
        // Run the test
        final ZetaData result = MyClass.getZetaData1("id")

        // Verify the results
    }

    @Test
    void testSetEpsilonService() {
        // Setup
        final EpsilonService value = null

        // Run the test
        MyClass.setEpsilonService(value)

        // Verify the results
    }
}
