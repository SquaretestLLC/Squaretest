package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private FooService theMockFooService;
    @Mock
    private BarService theMockBarService;
    @Mock
    private GammaService theMockGammaService;
    @Mock
    private BetaService theMockBetaService;
    @Mock
    private DeltaService theMockDeltaService;
    @Mock
    private Map<String, String> theMockSpecialMap;
    @Mock
    private AlphaService theMockAlphaService;

    private MyClass myClass;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClass = new MyClass(theMockFooService, theMockBarService, theMockGammaService, "defaultBarId");
        myClass.setDeltaService(theMockDeltaService);
        ReflectionTestUtils.setField(myClass, "specialMap", theMockSpecialMap);
        ReflectionTestUtils.setField(myClass, "alphaService", theMockAlphaService);
        myClass.betaService = theMockBetaService;
    }

    @Test
    void testGetFooAndBar11() {
        // Setup
        // Configure FooService.getFoo1(...).
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");
        when(theMockFooService.getFoo1("id")).thenReturn(fooData);

        // Configure BarService.getDefaultBar1(...).
        final BarData barData = new BarData();
        barData.setId("id");
        barData.setName("name");
        when(theMockBarService.getDefaultBar1("defaultBarId")).thenReturn(barData);

        // Run the test
        final FooAndBar result = myClass.getFooAndBar1("id");

        // Verify the results
    }

    @Test
    void testGetAlpha11() {
        // Setup
        // Configure AlphaService.getAlpha1(...).
        final AlphaData alphaData = new AlphaData();
        alphaData.setId("id");
        alphaData.setName("name");
        when(theMockAlphaService.getAlpha1("id")).thenReturn(alphaData);

        // Run the test
        final AlphaData result = myClass.getAlpha1("id");

        // Verify the results
    }

    @Test
    void testGetBeta11() {
        // Setup
        // Configure BetaService.getBeta1(...).
        final BetaData betaData = new BetaData();
        betaData.setId("id");
        betaData.setName("name");
        when(theMockBetaService.getBeta1("id")).thenReturn(betaData);

        // Run the test
        final BetaData result = myClass.getBeta1("id");

        // Verify the results
    }

    @Test
    void testGetGamma11() {
        // Setup
        // Configure GammaService.getGamma1(...).
        final GammaData gammaData = new GammaData();
        gammaData.setId("id");
        gammaData.setName("name");
        when(theMockGammaService.getGamma1("id")).thenReturn(gammaData);

        // Run the test
        final GammaData result = myClass.getGamma1("id");

        // Verify the results
    }

    @Test
    void testGetGamma21() {
        // Setup
        when(theMockSpecialMap.get("GammaId")).thenReturn("id");

        // Configure GammaService.getGamma1(...).
        final GammaData gammaData = new GammaData();
        gammaData.setId("id");
        gammaData.setName("name");
        when(theMockGammaService.getGamma1("id")).thenReturn(gammaData);

        // Run the test
        final GammaData result = myClass.getGamma2();

        // Verify the results
    }

    @Test
    void testGetGamma2_MapReturnsNull1() {
        // Setup
        when(theMockSpecialMap.get("GammaId")).thenReturn(null);

        // Run the test
        final GammaData result = myClass.getGamma2();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetEpsilon11() {
        // Setup
        // Run the test
        final EpsilonData result = MyClass.getEpsilon1("id");

        // Verify the results
    }

    @Test
    void testGetZetaData11() {
        // Setup
        // Run the test
        final ZetaData result = MyClass.getZetaData1("id");

        // Verify the results
    }

    @Test
    void testSetFooService() {
        final FooService fooService = null;
        myClass.setFooService(fooService);
    }

    @Test
    void testSetEpsilonService1() {
        // Setup
        final EpsilonService epsilonService = null;

        // Run the test
        MyClass.setEpsilonService(epsilonService);

        // Verify the results
    }
}