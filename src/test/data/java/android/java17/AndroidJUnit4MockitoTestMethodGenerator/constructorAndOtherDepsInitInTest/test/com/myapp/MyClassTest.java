package com.myapp;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Map;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class MyClassTest {

    @Mock
    private FooService mockFooService;
    @Mock
    private BarService mockBarService;
    @Mock
    private GammaService mockGammaService;
    @Mock
    private BetaService mockBetaService;
    @Mock
    private DeltaService mockDeltaService;
    @Mock
    private Map<String, String> mockSpecialMap;
    @Mock
    private AlphaService mockAlphaService;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testGetFooAndBar11() {
        // Setup
        final MyClass myClass = new MyClass(mockFooService, mockBarService, mockGammaService, "defaultBarId");
        myClass.setDeltaService(mockDeltaService);
        ReflectionTestUtils.setField(myClassUnderTest, "specialMap", mockSpecialMap);
        ReflectionTestUtils.setField(myClassUnderTest, "alphaService", mockAlphaService);
        myClass.betaService = mockBetaService;

        // Configure FooService.getFoo1(...).
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");
        when(mockFooService.getFoo1("id")).thenReturn(fooData);

        // Configure BarService.getDefaultBar1(...).
        final BarData barData = new BarData();
        barData.setId("id");
        barData.setName("name");
        when(mockBarService.getDefaultBar1("defaultBarId")).thenReturn(barData);

        // Run the test
        final FooAndBar result = myClass.getFooAndBar1("id");

        // Verify the results
    }

    @Test
    public void testGetAlpha11() {
        // Setup
        final MyClass myClass = new MyClass(mockFooService, mockBarService, mockGammaService, "defaultBarId");
        myClass.setDeltaService(mockDeltaService);
        ReflectionTestUtils.setField(myClassUnderTest, "specialMap", mockSpecialMap);
        ReflectionTestUtils.setField(myClassUnderTest, "alphaService", mockAlphaService);
        myClass.betaService = mockBetaService;

        // Configure AlphaService.getAlpha1(...).
        final AlphaData alphaData = new AlphaData();
        alphaData.setId("id");
        alphaData.setName("name");
        when(mockAlphaService.getAlpha1("id")).thenReturn(alphaData);

        // Run the test
        final AlphaData result = myClass.getAlpha1("id");

        // Verify the results
    }

    @Test
    public void testGetBeta11() {
        // Setup
        final MyClass myClass = new MyClass(mockFooService, mockBarService, mockGammaService, "defaultBarId");
        myClass.setDeltaService(mockDeltaService);
        ReflectionTestUtils.setField(myClassUnderTest, "specialMap", mockSpecialMap);
        ReflectionTestUtils.setField(myClassUnderTest, "alphaService", mockAlphaService);
        myClass.betaService = mockBetaService;

        // Configure BetaService.getBeta1(...).
        final BetaData betaData = new BetaData();
        betaData.setId("id");
        betaData.setName("name");
        when(mockBetaService.getBeta1("id")).thenReturn(betaData);

        // Run the test
        final BetaData result = myClass.getBeta1("id");

        // Verify the results
    }

    @Test
    public void testGetGamma11() {
        // Setup
        final MyClass myClass = new MyClass(mockFooService, mockBarService, mockGammaService, "defaultBarId");
        myClass.setDeltaService(mockDeltaService);
        ReflectionTestUtils.setField(myClassUnderTest, "specialMap", mockSpecialMap);
        ReflectionTestUtils.setField(myClassUnderTest, "alphaService", mockAlphaService);
        myClass.betaService = mockBetaService;

        // Configure GammaService.getGamma1(...).
        final GammaData gammaData = new GammaData();
        gammaData.setId("id");
        gammaData.setName("name");
        when(mockGammaService.getGamma1("id")).thenReturn(gammaData);

        // Run the test
        final GammaData result = myClass.getGamma1("id");

        // Verify the results
    }

    @Test
    public void testGetGamma21() {
        // Setup
        final MyClass myClass = new MyClass(mockFooService, mockBarService, mockGammaService, "defaultBarId");
        myClass.setDeltaService(mockDeltaService);
        ReflectionTestUtils.setField(myClassUnderTest, "specialMap", mockSpecialMap);
        ReflectionTestUtils.setField(myClassUnderTest, "alphaService", mockAlphaService);
        myClass.betaService = mockBetaService;
        when(mockSpecialMap.get("GammaId")).thenReturn("id");

        // Configure GammaService.getGamma1(...).
        final GammaData gammaData = new GammaData();
        gammaData.setId("id");
        gammaData.setName("name");
        when(mockGammaService.getGamma1("id")).thenReturn(gammaData);

        // Run the test
        final GammaData result = myClass.getGamma2();

        // Verify the results
    }

    @Test
    public void testGetGamma2_MapReturnsNull1() {
        // Setup
        final MyClass myClass = new MyClass(mockFooService, mockBarService, mockGammaService, "defaultBarId");
        myClass.setDeltaService(mockDeltaService);
        ReflectionTestUtils.setField(myClassUnderTest, "specialMap", mockSpecialMap);
        ReflectionTestUtils.setField(myClassUnderTest, "alphaService", mockAlphaService);
        myClass.betaService = mockBetaService;
        when(mockSpecialMap.get("GammaId")).thenReturn(null);

        // Run the test
        final GammaData result = myClass.getGamma2();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetEpsilon11() {
        // Setup
        // Run the test
        final EpsilonData result = MyClass.getEpsilon1("id");

        // Verify the results
    }

    @Test
    public void testGetZetaData11() {
        // Setup
        // Run the test
        final ZetaData result = MyClass.getZetaData1("id");

        // Verify the results
    }

    @Test
    public void testSetFooService1() {
        final MyClass myClass = new MyClass(mockFooService, mockBarService, mockGammaService, "defaultBarId");
        myClass.setDeltaService(mockDeltaService);
        ReflectionTestUtils.setField(myClassUnderTest, "specialMap", mockSpecialMap);
        ReflectionTestUtils.setField(myClassUnderTest, "alphaService", mockAlphaService);
        myClass.betaService = mockBetaService;
        final FooService fooService = null;
        myClass.setFooService(fooService);
    }

    @Test
    public void testSetEpsilonService1() {
        // Setup
        final EpsilonService epsilonService = null;

        // Run the test
        MyClass.setEpsilonService(epsilonService);

        // Verify the results
    }
}