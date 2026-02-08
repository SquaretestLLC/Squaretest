package com.myapp

import groovy.transform.CompileStatic
import org.mockito.Mock
import org.springframework.test.util.ReflectionTestUtils
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import static org.mockito.Mockito.when
import static org.mockito.MockitoAnnotations.initMocks
import static org.testng.Assert.assertNull

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

    @BeforeMethod
    void setUp() {
        initMocks(this)
    }

    @Test
    void testGetFooAndBar11() {
        // Setup
        def myClass = new MyClass(mockFooService, mockBarService, mockGammaService, "defaultBarId")
        myClass.setDeltaService(mockDeltaService)
        ReflectionTestUtils.setField(myClassUnderTest, "specialMap", mockSpecialMap)
        ReflectionTestUtils.setField(myClassUnderTest, "alphaService", mockAlphaService)
        myClass.betaService = mockBetaService

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
        def result = myClass.getFooAndBar1("id")

        // Verify the results
    }

    @Test
    void testGetAlpha11() {
        // Setup
        def myClass = new MyClass(mockFooService, mockBarService, mockGammaService, "defaultBarId")
        myClass.setDeltaService(mockDeltaService)
        ReflectionTestUtils.setField(myClassUnderTest, "specialMap", mockSpecialMap)
        ReflectionTestUtils.setField(myClassUnderTest, "alphaService", mockAlphaService)
        myClass.betaService = mockBetaService

        // Configure AlphaService.getAlpha1(...).
        def alphaData = new AlphaData()
        alphaData.setId("id")
        alphaData.setName("name")
        when(mockAlphaService.getAlpha1("id")).thenReturn(alphaData)

        // Run the test
        def result = myClass.getAlpha1("id")

        // Verify the results
    }

    @Test
    void testGetBeta11() {
        // Setup
        def myClass = new MyClass(mockFooService, mockBarService, mockGammaService, "defaultBarId")
        myClass.setDeltaService(mockDeltaService)
        ReflectionTestUtils.setField(myClassUnderTest, "specialMap", mockSpecialMap)
        ReflectionTestUtils.setField(myClassUnderTest, "alphaService", mockAlphaService)
        myClass.betaService = mockBetaService

        // Configure BetaService.getBeta1(...).
        def betaData = new BetaData()
        betaData.setId("id")
        betaData.setName("name")
        when(mockBetaService.getBeta1("id")).thenReturn(betaData)

        // Run the test
        def result = myClass.getBeta1("id")

        // Verify the results
    }

    @Test
    void testGetGamma11() {
        // Setup
        def myClass = new MyClass(mockFooService, mockBarService, mockGammaService, "defaultBarId")
        myClass.setDeltaService(mockDeltaService)
        ReflectionTestUtils.setField(myClassUnderTest, "specialMap", mockSpecialMap)
        ReflectionTestUtils.setField(myClassUnderTest, "alphaService", mockAlphaService)
        myClass.betaService = mockBetaService

        // Configure GammaService.getGamma1(...).
        def gammaData = new GammaData()
        gammaData.setId("id")
        gammaData.setName("name")
        when(mockGammaService.getGamma1("id")).thenReturn(gammaData)

        // Run the test
        def result = myClass.getGamma1("id")

        // Verify the results
    }

    @Test
    void testGetGamma21() {
        // Setup
        def myClass = new MyClass(mockFooService, mockBarService, mockGammaService, "defaultBarId")
        myClass.setDeltaService(mockDeltaService)
        ReflectionTestUtils.setField(myClassUnderTest, "specialMap", mockSpecialMap)
        ReflectionTestUtils.setField(myClassUnderTest, "alphaService", mockAlphaService)
        myClass.betaService = mockBetaService
        when(mockSpecialMap.get("GammaId")).thenReturn("id")

        // Configure GammaService.getGamma1(...).
        def gammaData = new GammaData()
        gammaData.setId("id")
        gammaData.setName("name")
        when(mockGammaService.getGamma1("id")).thenReturn(gammaData)

        // Run the test
        def result = myClass.getGamma2()

        // Verify the results
    }

    @Test
    void testGetGamma2_MapReturnsNull1() {
        // Setup
        def myClass = new MyClass(mockFooService, mockBarService, mockGammaService, "defaultBarId")
        myClass.setDeltaService(mockDeltaService)
        ReflectionTestUtils.setField(myClassUnderTest, "specialMap", mockSpecialMap)
        ReflectionTestUtils.setField(myClassUnderTest, "alphaService", mockAlphaService)
        myClass.betaService = mockBetaService
        when(mockSpecialMap.get("GammaId")).thenReturn(null)

        // Run the test
        def result = myClass.getGamma2()

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
        def myClass = new MyClass(mockFooService, mockBarService, mockGammaService, "defaultBarId")
        myClass.setDeltaService(mockDeltaService)
        ReflectionTestUtils.setField(myClassUnderTest, "specialMap", mockSpecialMap)
        ReflectionTestUtils.setField(myClassUnderTest, "alphaService", mockAlphaService)
        myClass.betaService = mockBetaService
        def fooService = null
        myClass.setFooService(fooService)
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