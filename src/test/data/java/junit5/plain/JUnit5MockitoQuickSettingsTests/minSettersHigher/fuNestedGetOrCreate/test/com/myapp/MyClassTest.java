package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private AlphaService mockAlphaService;
    @Mock
    private BetaService mockBetaService;
    @Mock
    private GammaService mockGammaService;
    @Mock
    private DeltaService mockDeltaService;
    @Mock
    private EpsilonService mockEpsilonService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockAlphaService, mockBetaService, mockGammaService, mockDeltaService,
                mockEpsilonService);
    }

    @Test
    void testGetOrCreateAlphaData1() {
        // Setup
        final FullData fullData = new FullData();
        final AlphaData alphaData = new AlphaData();
        alphaData.setId("id");
        alphaData.setCreateDate(LocalDate.of(2020, 1, 1));
        alphaData.setBetaId("betaId");
        final BetaData betaData = new BetaData();
        betaData.setId("id");
        betaData.setCreateDate(LocalDate.of(2020, 1, 1));
        betaData.setDescription("description");
        betaData.setGammaId("gammaId");
        final GammaData gammaData = new GammaData();
        gammaData.setId("id");
        gammaData.setCreateDate(LocalDate.of(2020, 1, 1));
        gammaData.setDescription("description");
        betaData.setGammaData(gammaData);
        alphaData.setBetaData(betaData);
        final DeltaData deltaData = new DeltaData();
        deltaData.setId("id");
        deltaData.setCreateDate(LocalDate.of(2020, 1, 1));
        deltaData.setDescription("description");
        deltaData.setEpsilonId("epsilonId");
        final EpsilonData epsilonData = new EpsilonData();
        epsilonData.setId("id");
        epsilonData.setCreateDate(LocalDate.of(2020, 1, 1));
        epsilonData.setDescription("description");
        deltaData.setEpsilonData(epsilonData);
        alphaData.setDeltaData(deltaData);
        fullData.setAlphaData(alphaData);
        final BetaData betaData1 = new BetaData();
        betaData1.setId("id");
        betaData1.setCreateDate(LocalDate.of(2020, 1, 1));
        betaData1.setDescription("description");
        betaData1.setGammaId("gammaId");
        final GammaData gammaData1 = new GammaData();
        gammaData1.setId("id");
        gammaData1.setCreateDate(LocalDate.of(2020, 1, 1));
        gammaData1.setDescription("description");
        betaData1.setGammaData(gammaData1);
        fullData.setBetaData(betaData1);
        final GammaData gammaData2 = new GammaData();
        gammaData2.setId("id");
        gammaData2.setCreateDate(LocalDate.of(2020, 1, 1));
        gammaData2.setDescription("description");
        fullData.setGammaData(gammaData2);
        final DeltaData deltaData1 = new DeltaData();
        deltaData1.setId("id");
        deltaData1.setCreateDate(LocalDate.of(2020, 1, 1));
        deltaData1.setDescription("description");
        deltaData1.setEpsilonId("epsilonId");
        final EpsilonData epsilonData1 = new EpsilonData();
        epsilonData1.setId("id");
        epsilonData1.setCreateDate(LocalDate.of(2020, 1, 1));
        epsilonData1.setDescription("description");
        deltaData1.setEpsilonData(epsilonData1);
        fullData.setDeltaData(deltaData1);
        final EpsilonData epsilonData2 = new EpsilonData();
        epsilonData2.setId("id");
        epsilonData2.setCreateDate(LocalDate.of(2020, 1, 1));
        epsilonData2.setDescription("description");
        fullData.setEpsilonData(epsilonData2);

        final AlphaData expectedResult = new AlphaData();
        expectedResult.setId("id");
        expectedResult.setCreateDate(LocalDate.of(2020, 1, 1));
        expectedResult.setBetaId("betaId");
        final BetaData betaData2 = new BetaData();
        betaData2.setId("id");
        betaData2.setCreateDate(LocalDate.of(2020, 1, 1));
        betaData2.setDescription("description");
        betaData2.setGammaId("gammaId");
        final GammaData gammaData3 = new GammaData();
        gammaData3.setId("id");
        gammaData3.setCreateDate(LocalDate.of(2020, 1, 1));
        gammaData3.setDescription("description");
        betaData2.setGammaData(gammaData3);
        expectedResult.setBetaData(betaData2);
        final DeltaData deltaData2 = new DeltaData();
        deltaData2.setId("id");
        deltaData2.setCreateDate(LocalDate.of(2020, 1, 1));
        deltaData2.setDescription("description");
        deltaData2.setEpsilonId("epsilonId");
        final EpsilonData epsilonData3 = new EpsilonData();
        epsilonData3.setId("id");
        epsilonData3.setCreateDate(LocalDate.of(2020, 1, 1));
        epsilonData3.setDescription("description");
        deltaData2.setEpsilonData(epsilonData3);
        expectedResult.setDeltaData(deltaData2);

        // Configure AlphaService.findById(...).
        final AlphaData alphaData2 = new AlphaData();
        alphaData2.setId("id");
        alphaData2.setCreateDate(LocalDate.of(2020, 1, 1));
        alphaData2.setBetaId("betaId");
        final BetaData betaData3 = new BetaData();
        betaData3.setId("id");
        betaData3.setCreateDate(LocalDate.of(2020, 1, 1));
        betaData3.setDescription("description");
        betaData3.setGammaId("gammaId");
        final GammaData gammaData4 = new GammaData();
        gammaData4.setId("id");
        gammaData4.setCreateDate(LocalDate.of(2020, 1, 1));
        gammaData4.setDescription("description");
        betaData3.setGammaData(gammaData4);
        alphaData2.setBetaData(betaData3);
        final DeltaData deltaData3 = new DeltaData();
        deltaData3.setId("id");
        deltaData3.setCreateDate(LocalDate.of(2020, 1, 1));
        deltaData3.setDescription("description");
        deltaData3.setEpsilonId("epsilonId");
        final EpsilonData epsilonData4 = new EpsilonData();
        epsilonData4.setId("id");
        epsilonData4.setCreateDate(LocalDate.of(2020, 1, 1));
        epsilonData4.setDescription("description");
        deltaData3.setEpsilonData(epsilonData4);
        alphaData2.setDeltaData(deltaData3);
        final Optional<AlphaData> alphaData1 = Optional.of(alphaData2);
        when(mockAlphaService.findById("id")).thenReturn(alphaData1);

        // Run the test
        final AlphaData result = myClassUnderTest.getOrCreateAlphaData1(fullData);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetOrCreateAlphaData1_AlphaServiceFindByIdReturnsAbsent() {
        // Setup
        final FullData fullData = new FullData();
        final AlphaData alphaData = new AlphaData();
        alphaData.setId("id");
        alphaData.setCreateDate(LocalDate.of(2020, 1, 1));
        alphaData.setBetaId("betaId");
        final BetaData betaData = new BetaData();
        betaData.setId("id");
        betaData.setCreateDate(LocalDate.of(2020, 1, 1));
        betaData.setDescription("description");
        betaData.setGammaId("gammaId");
        final GammaData gammaData = new GammaData();
        gammaData.setId("id");
        gammaData.setCreateDate(LocalDate.of(2020, 1, 1));
        gammaData.setDescription("description");
        betaData.setGammaData(gammaData);
        alphaData.setBetaData(betaData);
        final DeltaData deltaData = new DeltaData();
        deltaData.setId("id");
        deltaData.setCreateDate(LocalDate.of(2020, 1, 1));
        deltaData.setDescription("description");
        deltaData.setEpsilonId("epsilonId");
        final EpsilonData epsilonData = new EpsilonData();
        epsilonData.setId("id");
        epsilonData.setCreateDate(LocalDate.of(2020, 1, 1));
        epsilonData.setDescription("description");
        deltaData.setEpsilonData(epsilonData);
        alphaData.setDeltaData(deltaData);
        fullData.setAlphaData(alphaData);
        final BetaData betaData1 = new BetaData();
        betaData1.setId("id");
        betaData1.setCreateDate(LocalDate.of(2020, 1, 1));
        betaData1.setDescription("description");
        betaData1.setGammaId("gammaId");
        final GammaData gammaData1 = new GammaData();
        gammaData1.setId("id");
        gammaData1.setCreateDate(LocalDate.of(2020, 1, 1));
        gammaData1.setDescription("description");
        betaData1.setGammaData(gammaData1);
        fullData.setBetaData(betaData1);
        final GammaData gammaData2 = new GammaData();
        gammaData2.setId("id");
        gammaData2.setCreateDate(LocalDate.of(2020, 1, 1));
        gammaData2.setDescription("description");
        fullData.setGammaData(gammaData2);
        final DeltaData deltaData1 = new DeltaData();
        deltaData1.setId("id");
        deltaData1.setCreateDate(LocalDate.of(2020, 1, 1));
        deltaData1.setDescription("description");
        deltaData1.setEpsilonId("epsilonId");
        final EpsilonData epsilonData1 = new EpsilonData();
        epsilonData1.setId("id");
        epsilonData1.setCreateDate(LocalDate.of(2020, 1, 1));
        epsilonData1.setDescription("description");
        deltaData1.setEpsilonData(epsilonData1);
        fullData.setDeltaData(deltaData1);
        final EpsilonData epsilonData2 = new EpsilonData();
        epsilonData2.setId("id");
        epsilonData2.setCreateDate(LocalDate.of(2020, 1, 1));
        epsilonData2.setDescription("description");
        fullData.setEpsilonData(epsilonData2);

        final AlphaData expectedResult = new AlphaData();
        expectedResult.setId("id");
        expectedResult.setCreateDate(LocalDate.of(2020, 1, 1));
        expectedResult.setBetaId("betaId");
        final BetaData betaData2 = new BetaData();
        betaData2.setId("id");
        betaData2.setCreateDate(LocalDate.of(2020, 1, 1));
        betaData2.setDescription("description");
        betaData2.setGammaId("gammaId");
        final GammaData gammaData3 = new GammaData();
        gammaData3.setId("id");
        gammaData3.setCreateDate(LocalDate.of(2020, 1, 1));
        gammaData3.setDescription("description");
        betaData2.setGammaData(gammaData3);
        expectedResult.setBetaData(betaData2);
        final DeltaData deltaData2 = new DeltaData();
        deltaData2.setId("id");
        deltaData2.setCreateDate(LocalDate.of(2020, 1, 1));
        deltaData2.setDescription("description");
        deltaData2.setEpsilonId("epsilonId");
        final EpsilonData epsilonData3 = new EpsilonData();
        epsilonData3.setId("id");
        epsilonData3.setCreateDate(LocalDate.of(2020, 1, 1));
        epsilonData3.setDescription("description");
        deltaData2.setEpsilonData(epsilonData3);
        expectedResult.setDeltaData(deltaData2);

        when(mockAlphaService.findById("id")).thenReturn(Optional.empty());

        // Configure BetaService.findById(...).
        final BetaData betaData4 = new BetaData();
        betaData4.setId("id");
        betaData4.setCreateDate(LocalDate.of(2020, 1, 1));
        betaData4.setDescription("description");
        betaData4.setGammaId("gammaId");
        final GammaData gammaData4 = new GammaData();
        gammaData4.setId("id");
        gammaData4.setCreateDate(LocalDate.of(2020, 1, 1));
        gammaData4.setDescription("description");
        betaData4.setGammaData(gammaData4);
        final Optional<BetaData> betaData3 = Optional.of(betaData4);
        when(mockBetaService.findById("id")).thenReturn(betaData3);

        // Configure DeltaService.findById(...).
        final DeltaData deltaData4 = new DeltaData();
        deltaData4.setId("id");
        deltaData4.setCreateDate(LocalDate.of(2020, 1, 1));
        deltaData4.setDescription("description");
        deltaData4.setEpsilonId("epsilonId");
        final EpsilonData epsilonData4 = new EpsilonData();
        epsilonData4.setId("id");
        epsilonData4.setCreateDate(LocalDate.of(2020, 1, 1));
        epsilonData4.setDescription("description");
        deltaData4.setEpsilonData(epsilonData4);
        final Optional<DeltaData> deltaData3 = Optional.of(deltaData4);
        when(mockDeltaService.findById("id")).thenReturn(deltaData3);

        // Configure AlphaService.save(...).
        final AlphaData alphaData1 = new AlphaData();
        alphaData1.setId("id");
        alphaData1.setCreateDate(LocalDate.of(2020, 1, 1));
        alphaData1.setBetaId("betaId");
        final BetaData betaData5 = new BetaData();
        betaData5.setId("id");
        betaData5.setCreateDate(LocalDate.of(2020, 1, 1));
        betaData5.setDescription("description");
        betaData5.setGammaId("gammaId");
        final GammaData gammaData5 = new GammaData();
        gammaData5.setId("id");
        gammaData5.setCreateDate(LocalDate.of(2020, 1, 1));
        gammaData5.setDescription("description");
        betaData5.setGammaData(gammaData5);
        alphaData1.setBetaData(betaData5);
        final DeltaData deltaData5 = new DeltaData();
        deltaData5.setId("id");
        deltaData5.setCreateDate(LocalDate.of(2020, 1, 1));
        deltaData5.setDescription("description");
        deltaData5.setEpsilonId("epsilonId");
        final EpsilonData epsilonData5 = new EpsilonData();
        epsilonData5.setId("id");
        epsilonData5.setCreateDate(LocalDate.of(2020, 1, 1));
        epsilonData5.setDescription("description");
        deltaData5.setEpsilonData(epsilonData5);
        alphaData1.setDeltaData(deltaData5);
        final AlphaData alphaData2 = new AlphaData();
        alphaData2.setId("id");
        alphaData2.setCreateDate(LocalDate.of(2020, 1, 1));
        alphaData2.setBetaId("betaId");
        final BetaData betaData6 = new BetaData();
        betaData6.setId("id");
        betaData6.setCreateDate(LocalDate.of(2020, 1, 1));
        betaData6.setDescription("description");
        betaData6.setGammaId("gammaId");
        final GammaData gammaData6 = new GammaData();
        gammaData6.setId("id");
        gammaData6.setCreateDate(LocalDate.of(2020, 1, 1));
        gammaData6.setDescription("description");
        betaData6.setGammaData(gammaData6);
        alphaData2.setBetaData(betaData6);
        final DeltaData deltaData6 = new DeltaData();
        deltaData6.setId("id");
        deltaData6.setCreateDate(LocalDate.of(2020, 1, 1));
        deltaData6.setDescription("description");
        deltaData6.setEpsilonId("epsilonId");
        final EpsilonData epsilonData6 = new EpsilonData();
        epsilonData6.setId("id");
        epsilonData6.setCreateDate(LocalDate.of(2020, 1, 1));
        epsilonData6.setDescription("description");
        deltaData6.setEpsilonData(epsilonData6);
        alphaData2.setDeltaData(deltaData6);
        when(mockAlphaService.save(alphaData2)).thenReturn(alphaData1);

        // Run the test
        final AlphaData result = myClassUnderTest.getOrCreateAlphaData1(fullData);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetOrCreateAlphaData1_BetaServiceFindByIdReturnsAbsent() {
        // Setup
        final FullData fullData = new FullData();
        final AlphaData alphaData = new AlphaData();
        alphaData.setId("id");
        alphaData.setCreateDate(LocalDate.of(2020, 1, 1));
        alphaData.setBetaId("betaId");
        final BetaData betaData = new BetaData();
        betaData.setId("id");
        betaData.setCreateDate(LocalDate.of(2020, 1, 1));
        betaData.setDescription("description");
        betaData.setGammaId("gammaId");
        final GammaData gammaData = new GammaData();
        gammaData.setId("id");
        gammaData.setCreateDate(LocalDate.of(2020, 1, 1));
        gammaData.setDescription("description");
        betaData.setGammaData(gammaData);
        alphaData.setBetaData(betaData);
        final DeltaData deltaData = new DeltaData();
        deltaData.setId("id");
        deltaData.setCreateDate(LocalDate.of(2020, 1, 1));
        deltaData.setDescription("description");
        deltaData.setEpsilonId("epsilonId");
        final EpsilonData epsilonData = new EpsilonData();
        epsilonData.setId("id");
        epsilonData.setCreateDate(LocalDate.of(2020, 1, 1));
        epsilonData.setDescription("description");
        deltaData.setEpsilonData(epsilonData);
        alphaData.setDeltaData(deltaData);
        fullData.setAlphaData(alphaData);
        final BetaData betaData1 = new BetaData();
        betaData1.setId("id");
        betaData1.setCreateDate(LocalDate.of(2020, 1, 1));
        betaData1.setDescription("description");
        betaData1.setGammaId("gammaId");
        final GammaData gammaData1 = new GammaData();
        gammaData1.setId("id");
        gammaData1.setCreateDate(LocalDate.of(2020, 1, 1));
        gammaData1.setDescription("description");
        betaData1.setGammaData(gammaData1);
        fullData.setBetaData(betaData1);
        final GammaData gammaData2 = new GammaData();
        gammaData2.setId("id");
        gammaData2.setCreateDate(LocalDate.of(2020, 1, 1));
        gammaData2.setDescription("description");
        fullData.setGammaData(gammaData2);
        final DeltaData deltaData1 = new DeltaData();
        deltaData1.setId("id");
        deltaData1.setCreateDate(LocalDate.of(2020, 1, 1));
        deltaData1.setDescription("description");
        deltaData1.setEpsilonId("epsilonId");
        final EpsilonData epsilonData1 = new EpsilonData();
        epsilonData1.setId("id");
        epsilonData1.setCreateDate(LocalDate.of(2020, 1, 1));
        epsilonData1.setDescription("description");
        deltaData1.setEpsilonData(epsilonData1);
        fullData.setDeltaData(deltaData1);
        final EpsilonData epsilonData2 = new EpsilonData();
        epsilonData2.setId("id");
        epsilonData2.setCreateDate(LocalDate.of(2020, 1, 1));
        epsilonData2.setDescription("description");
        fullData.setEpsilonData(epsilonData2);

        final AlphaData expectedResult = new AlphaData();
        expectedResult.setId("id");
        expectedResult.setCreateDate(LocalDate.of(2020, 1, 1));
        expectedResult.setBetaId("betaId");
        final BetaData betaData2 = new BetaData();
        betaData2.setId("id");
        betaData2.setCreateDate(LocalDate.of(2020, 1, 1));
        betaData2.setDescription("description");
        betaData2.setGammaId("gammaId");
        final GammaData gammaData3 = new GammaData();
        gammaData3.setId("id");
        gammaData3.setCreateDate(LocalDate.of(2020, 1, 1));
        gammaData3.setDescription("description");
        betaData2.setGammaData(gammaData3);
        expectedResult.setBetaData(betaData2);
        final DeltaData deltaData2 = new DeltaData();
        deltaData2.setId("id");
        deltaData2.setCreateDate(LocalDate.of(2020, 1, 1));
        deltaData2.setDescription("description");
        deltaData2.setEpsilonId("epsilonId");
        final EpsilonData epsilonData3 = new EpsilonData();
        epsilonData3.setId("id");
        epsilonData3.setCreateDate(LocalDate.of(2020, 1, 1));
        epsilonData3.setDescription("description");
        deltaData2.setEpsilonData(epsilonData3);
        expectedResult.setDeltaData(deltaData2);

        when(mockAlphaService.findById("id")).thenReturn(Optional.empty());
        when(mockBetaService.findById("id")).thenReturn(Optional.empty());

        // Configure GammaService.findById(...).
        final GammaData gammaData5 = new GammaData();
        gammaData5.setId("id");
        gammaData5.setCreateDate(LocalDate.of(2020, 1, 1));
        gammaData5.setDescription("description");
        final Optional<GammaData> gammaData4 = Optional.of(gammaData5);
        when(mockGammaService.findById("id")).thenReturn(gammaData4);

        // Configure BetaService.save(...).
        final BetaData betaData3 = new BetaData();
        betaData3.setId("id");
        betaData3.setCreateDate(LocalDate.of(2020, 1, 1));
        betaData3.setDescription("description");
        betaData3.setGammaId("gammaId");
        final GammaData gammaData6 = new GammaData();
        gammaData6.setId("id");
        gammaData6.setCreateDate(LocalDate.of(2020, 1, 1));
        gammaData6.setDescription("description");
        betaData3.setGammaData(gammaData6);
        final BetaData alphaData1 = new BetaData();
        alphaData1.setId("id");
        alphaData1.setCreateDate(LocalDate.of(2020, 1, 1));
        alphaData1.setDescription("description");
        alphaData1.setGammaId("gammaId");
        final GammaData gammaData7 = new GammaData();
        gammaData7.setId("id");
        gammaData7.setCreateDate(LocalDate.of(2020, 1, 1));
        gammaData7.setDescription("description");
        alphaData1.setGammaData(gammaData7);
        when(mockBetaService.save(alphaData1)).thenReturn(betaData3);

        // Configure DeltaService.findById(...).
        final DeltaData deltaData4 = new DeltaData();
        deltaData4.setId("id");
        deltaData4.setCreateDate(LocalDate.of(2020, 1, 1));
        deltaData4.setDescription("description");
        deltaData4.setEpsilonId("epsilonId");
        final EpsilonData epsilonData4 = new EpsilonData();
        epsilonData4.setId("id");
        epsilonData4.setCreateDate(LocalDate.of(2020, 1, 1));
        epsilonData4.setDescription("description");
        deltaData4.setEpsilonData(epsilonData4);
        final Optional<DeltaData> deltaData3 = Optional.of(deltaData4);
        when(mockDeltaService.findById("id")).thenReturn(deltaData3);

        // Configure AlphaService.save(...).
        final AlphaData alphaData2 = new AlphaData();
        alphaData2.setId("id");
        alphaData2.setCreateDate(LocalDate.of(2020, 1, 1));
        alphaData2.setBetaId("betaId");
        final BetaData betaData4 = new BetaData();
        betaData4.setId("id");
        betaData4.setCreateDate(LocalDate.of(2020, 1, 1));
        betaData4.setDescription("description");
        betaData4.setGammaId("gammaId");
        final GammaData gammaData8 = new GammaData();
        gammaData8.setId("id");
        gammaData8.setCreateDate(LocalDate.of(2020, 1, 1));
        gammaData8.setDescription("description");
        betaData4.setGammaData(gammaData8);
        alphaData2.setBetaData(betaData4);
        final DeltaData deltaData5 = new DeltaData();
        deltaData5.setId("id");
        deltaData5.setCreateDate(LocalDate.of(2020, 1, 1));
        deltaData5.setDescription("description");
        deltaData5.setEpsilonId("epsilonId");
        final EpsilonData epsilonData5 = new EpsilonData();
        epsilonData5.setId("id");
        epsilonData5.setCreateDate(LocalDate.of(2020, 1, 1));
        epsilonData5.setDescription("description");
        deltaData5.setEpsilonData(epsilonData5);
        alphaData2.setDeltaData(deltaData5);
        final AlphaData alphaData3 = new AlphaData();
        alphaData3.setId("id");
        alphaData3.setCreateDate(LocalDate.of(2020, 1, 1));
        alphaData3.setBetaId("betaId");
        final BetaData betaData5 = new BetaData();
        betaData5.setId("id");
        betaData5.setCreateDate(LocalDate.of(2020, 1, 1));
        betaData5.setDescription("description");
        betaData5.setGammaId("gammaId");
        final GammaData gammaData9 = new GammaData();
        gammaData9.setId("id");
        gammaData9.setCreateDate(LocalDate.of(2020, 1, 1));
        gammaData9.setDescription("description");
        betaData5.setGammaData(gammaData9);
        alphaData3.setBetaData(betaData5);
        final DeltaData deltaData6 = new DeltaData();
        deltaData6.setId("id");
        deltaData6.setCreateDate(LocalDate.of(2020, 1, 1));
        deltaData6.setDescription("description");
        deltaData6.setEpsilonId("epsilonId");
        final EpsilonData epsilonData6 = new EpsilonData();
        epsilonData6.setId("id");
        epsilonData6.setCreateDate(LocalDate.of(2020, 1, 1));
        epsilonData6.setDescription("description");
        deltaData6.setEpsilonData(epsilonData6);
        alphaData3.setDeltaData(deltaData6);
        when(mockAlphaService.save(alphaData3)).thenReturn(alphaData2);

        // Run the test
        final AlphaData result = myClassUnderTest.getOrCreateAlphaData1(fullData);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetOrCreateAlphaData1_GammaServiceFindByIdReturnsAbsent() {
        // Setup
        final FullData fullData = new FullData();
        final AlphaData alphaData = new AlphaData();
        alphaData.setId("id");
        alphaData.setCreateDate(LocalDate.of(2020, 1, 1));
        alphaData.setBetaId("betaId");
        final BetaData betaData = new BetaData();
        betaData.setId("id");
        betaData.setCreateDate(LocalDate.of(2020, 1, 1));
        betaData.setDescription("description");
        betaData.setGammaId("gammaId");
        final GammaData gammaData = new GammaData();
        gammaData.setId("id");
        gammaData.setCreateDate(LocalDate.of(2020, 1, 1));
        gammaData.setDescription("description");
        betaData.setGammaData(gammaData);
        alphaData.setBetaData(betaData);
        final DeltaData deltaData = new DeltaData();
        deltaData.setId("id");
        deltaData.setCreateDate(LocalDate.of(2020, 1, 1));
        deltaData.setDescription("description");
        deltaData.setEpsilonId("epsilonId");
        final EpsilonData epsilonData = new EpsilonData();
        epsilonData.setId("id");
        epsilonData.setCreateDate(LocalDate.of(2020, 1, 1));
        epsilonData.setDescription("description");
        deltaData.setEpsilonData(epsilonData);
        alphaData.setDeltaData(deltaData);
        fullData.setAlphaData(alphaData);
        final BetaData betaData1 = new BetaData();
        betaData1.setId("id");
        betaData1.setCreateDate(LocalDate.of(2020, 1, 1));
        betaData1.setDescription("description");
        betaData1.setGammaId("gammaId");
        final GammaData gammaData1 = new GammaData();
        gammaData1.setId("id");
        gammaData1.setCreateDate(LocalDate.of(2020, 1, 1));
        gammaData1.setDescription("description");
        betaData1.setGammaData(gammaData1);
        fullData.setBetaData(betaData1);
        final GammaData gammaData2 = new GammaData();
        gammaData2.setId("id");
        gammaData2.setCreateDate(LocalDate.of(2020, 1, 1));
        gammaData2.setDescription("description");
        fullData.setGammaData(gammaData2);
        final DeltaData deltaData1 = new DeltaData();
        deltaData1.setId("id");
        deltaData1.setCreateDate(LocalDate.of(2020, 1, 1));
        deltaData1.setDescription("description");
        deltaData1.setEpsilonId("epsilonId");
        final EpsilonData epsilonData1 = new EpsilonData();
        epsilonData1.setId("id");
        epsilonData1.setCreateDate(LocalDate.of(2020, 1, 1));
        epsilonData1.setDescription("description");
        deltaData1.setEpsilonData(epsilonData1);
        fullData.setDeltaData(deltaData1);
        final EpsilonData epsilonData2 = new EpsilonData();
        epsilonData2.setId("id");
        epsilonData2.setCreateDate(LocalDate.of(2020, 1, 1));
        epsilonData2.setDescription("description");
        fullData.setEpsilonData(epsilonData2);

        final AlphaData expectedResult = new AlphaData();
        expectedResult.setId("id");
        expectedResult.setCreateDate(LocalDate.of(2020, 1, 1));
        expectedResult.setBetaId("betaId");
        final BetaData betaData2 = new BetaData();
        betaData2.setId("id");
        betaData2.setCreateDate(LocalDate.of(2020, 1, 1));
        betaData2.setDescription("description");
        betaData2.setGammaId("gammaId");
        final GammaData gammaData3 = new GammaData();
        gammaData3.setId("id");
        gammaData3.setCreateDate(LocalDate.of(2020, 1, 1));
        gammaData3.setDescription("description");
        betaData2.setGammaData(gammaData3);
        expectedResult.setBetaData(betaData2);
        final DeltaData deltaData2 = new DeltaData();
        deltaData2.setId("id");
        deltaData2.setCreateDate(LocalDate.of(2020, 1, 1));
        deltaData2.setDescription("description");
        deltaData2.setEpsilonId("epsilonId");
        final EpsilonData epsilonData3 = new EpsilonData();
        epsilonData3.setId("id");
        epsilonData3.setCreateDate(LocalDate.of(2020, 1, 1));
        epsilonData3.setDescription("description");
        deltaData2.setEpsilonData(epsilonData3);
        expectedResult.setDeltaData(deltaData2);

        when(mockAlphaService.findById("id")).thenReturn(Optional.empty());
        when(mockBetaService.findById("id")).thenReturn(Optional.empty());
        when(mockGammaService.findById("id")).thenReturn(Optional.empty());

        // Configure GammaService.save(...).
        final GammaData gammaData4 = new GammaData();
        gammaData4.setId("id");
        gammaData4.setCreateDate(LocalDate.of(2020, 1, 1));
        gammaData4.setDescription("description");
        final GammaData alphaData1 = new GammaData();
        alphaData1.setId("id");
        alphaData1.setCreateDate(LocalDate.of(2020, 1, 1));
        alphaData1.setDescription("description");
        when(mockGammaService.save(alphaData1)).thenReturn(gammaData4);

        // Configure BetaService.save(...).
        final BetaData betaData3 = new BetaData();
        betaData3.setId("id");
        betaData3.setCreateDate(LocalDate.of(2020, 1, 1));
        betaData3.setDescription("description");
        betaData3.setGammaId("gammaId");
        final GammaData gammaData5 = new GammaData();
        gammaData5.setId("id");
        gammaData5.setCreateDate(LocalDate.of(2020, 1, 1));
        gammaData5.setDescription("description");
        betaData3.setGammaData(gammaData5);
        final BetaData alphaData2 = new BetaData();
        alphaData2.setId("id");
        alphaData2.setCreateDate(LocalDate.of(2020, 1, 1));
        alphaData2.setDescription("description");
        alphaData2.setGammaId("gammaId");
        final GammaData gammaData6 = new GammaData();
        gammaData6.setId("id");
        gammaData6.setCreateDate(LocalDate.of(2020, 1, 1));
        gammaData6.setDescription("description");
        alphaData2.setGammaData(gammaData6);
        when(mockBetaService.save(alphaData2)).thenReturn(betaData3);

        // Configure DeltaService.findById(...).
        final DeltaData deltaData4 = new DeltaData();
        deltaData4.setId("id");
        deltaData4.setCreateDate(LocalDate.of(2020, 1, 1));
        deltaData4.setDescription("description");
        deltaData4.setEpsilonId("epsilonId");
        final EpsilonData epsilonData4 = new EpsilonData();
        epsilonData4.setId("id");
        epsilonData4.setCreateDate(LocalDate.of(2020, 1, 1));
        epsilonData4.setDescription("description");
        deltaData4.setEpsilonData(epsilonData4);
        final Optional<DeltaData> deltaData3 = Optional.of(deltaData4);
        when(mockDeltaService.findById("id")).thenReturn(deltaData3);

        // Configure AlphaService.save(...).
        final AlphaData alphaData3 = new AlphaData();
        alphaData3.setId("id");
        alphaData3.setCreateDate(LocalDate.of(2020, 1, 1));
        alphaData3.setBetaId("betaId");
        final BetaData betaData4 = new BetaData();
        betaData4.setId("id");
        betaData4.setCreateDate(LocalDate.of(2020, 1, 1));
        betaData4.setDescription("description");
        betaData4.setGammaId("gammaId");
        final GammaData gammaData7 = new GammaData();
        gammaData7.setId("id");
        gammaData7.setCreateDate(LocalDate.of(2020, 1, 1));
        gammaData7.setDescription("description");
        betaData4.setGammaData(gammaData7);
        alphaData3.setBetaData(betaData4);
        final DeltaData deltaData5 = new DeltaData();
        deltaData5.setId("id");
        deltaData5.setCreateDate(LocalDate.of(2020, 1, 1));
        deltaData5.setDescription("description");
        deltaData5.setEpsilonId("epsilonId");
        final EpsilonData epsilonData5 = new EpsilonData();
        epsilonData5.setId("id");
        epsilonData5.setCreateDate(LocalDate.of(2020, 1, 1));
        epsilonData5.setDescription("description");
        deltaData5.setEpsilonData(epsilonData5);
        alphaData3.setDeltaData(deltaData5);
        final AlphaData alphaData4 = new AlphaData();
        alphaData4.setId("id");
        alphaData4.setCreateDate(LocalDate.of(2020, 1, 1));
        alphaData4.setBetaId("betaId");
        final BetaData betaData5 = new BetaData();
        betaData5.setId("id");
        betaData5.setCreateDate(LocalDate.of(2020, 1, 1));
        betaData5.setDescription("description");
        betaData5.setGammaId("gammaId");
        final GammaData gammaData8 = new GammaData();
        gammaData8.setId("id");
        gammaData8.setCreateDate(LocalDate.of(2020, 1, 1));
        gammaData8.setDescription("description");
        betaData5.setGammaData(gammaData8);
        alphaData4.setBetaData(betaData5);
        final DeltaData deltaData6 = new DeltaData();
        deltaData6.setId("id");
        deltaData6.setCreateDate(LocalDate.of(2020, 1, 1));
        deltaData6.setDescription("description");
        deltaData6.setEpsilonId("epsilonId");
        final EpsilonData epsilonData6 = new EpsilonData();
        epsilonData6.setId("id");
        epsilonData6.setCreateDate(LocalDate.of(2020, 1, 1));
        epsilonData6.setDescription("description");
        deltaData6.setEpsilonData(epsilonData6);
        alphaData4.setDeltaData(deltaData6);
        when(mockAlphaService.save(alphaData4)).thenReturn(alphaData3);

        // Run the test
        final AlphaData result = myClassUnderTest.getOrCreateAlphaData1(fullData);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetOrCreateAlphaData1_DeltaServiceFindByIdReturnsAbsent() {
        // Setup
        final FullData fullData = new FullData();
        final AlphaData alphaData = new AlphaData();
        alphaData.setId("id");
        alphaData.setCreateDate(LocalDate.of(2020, 1, 1));
        alphaData.setBetaId("betaId");
        final BetaData betaData = new BetaData();
        betaData.setId("id");
        betaData.setCreateDate(LocalDate.of(2020, 1, 1));
        betaData.setDescription("description");
        betaData.setGammaId("gammaId");
        final GammaData gammaData = new GammaData();
        gammaData.setId("id");
        gammaData.setCreateDate(LocalDate.of(2020, 1, 1));
        gammaData.setDescription("description");
        betaData.setGammaData(gammaData);
        alphaData.setBetaData(betaData);
        final DeltaData deltaData = new DeltaData();
        deltaData.setId("id");
        deltaData.setCreateDate(LocalDate.of(2020, 1, 1));
        deltaData.setDescription("description");
        deltaData.setEpsilonId("epsilonId");
        final EpsilonData epsilonData = new EpsilonData();
        epsilonData.setId("id");
        epsilonData.setCreateDate(LocalDate.of(2020, 1, 1));
        epsilonData.setDescription("description");
        deltaData.setEpsilonData(epsilonData);
        alphaData.setDeltaData(deltaData);
        fullData.setAlphaData(alphaData);
        final BetaData betaData1 = new BetaData();
        betaData1.setId("id");
        betaData1.setCreateDate(LocalDate.of(2020, 1, 1));
        betaData1.setDescription("description");
        betaData1.setGammaId("gammaId");
        final GammaData gammaData1 = new GammaData();
        gammaData1.setId("id");
        gammaData1.setCreateDate(LocalDate.of(2020, 1, 1));
        gammaData1.setDescription("description");
        betaData1.setGammaData(gammaData1);
        fullData.setBetaData(betaData1);
        final GammaData gammaData2 = new GammaData();
        gammaData2.setId("id");
        gammaData2.setCreateDate(LocalDate.of(2020, 1, 1));
        gammaData2.setDescription("description");
        fullData.setGammaData(gammaData2);
        final DeltaData deltaData1 = new DeltaData();
        deltaData1.setId("id");
        deltaData1.setCreateDate(LocalDate.of(2020, 1, 1));
        deltaData1.setDescription("description");
        deltaData1.setEpsilonId("epsilonId");
        final EpsilonData epsilonData1 = new EpsilonData();
        epsilonData1.setId("id");
        epsilonData1.setCreateDate(LocalDate.of(2020, 1, 1));
        epsilonData1.setDescription("description");
        deltaData1.setEpsilonData(epsilonData1);
        fullData.setDeltaData(deltaData1);
        final EpsilonData epsilonData2 = new EpsilonData();
        epsilonData2.setId("id");
        epsilonData2.setCreateDate(LocalDate.of(2020, 1, 1));
        epsilonData2.setDescription("description");
        fullData.setEpsilonData(epsilonData2);

        final AlphaData expectedResult = new AlphaData();
        expectedResult.setId("id");
        expectedResult.setCreateDate(LocalDate.of(2020, 1, 1));
        expectedResult.setBetaId("betaId");
        final BetaData betaData2 = new BetaData();
        betaData2.setId("id");
        betaData2.setCreateDate(LocalDate.of(2020, 1, 1));
        betaData2.setDescription("description");
        betaData2.setGammaId("gammaId");
        final GammaData gammaData3 = new GammaData();
        gammaData3.setId("id");
        gammaData3.setCreateDate(LocalDate.of(2020, 1, 1));
        gammaData3.setDescription("description");
        betaData2.setGammaData(gammaData3);
        expectedResult.setBetaData(betaData2);
        final DeltaData deltaData2 = new DeltaData();
        deltaData2.setId("id");
        deltaData2.setCreateDate(LocalDate.of(2020, 1, 1));
        deltaData2.setDescription("description");
        deltaData2.setEpsilonId("epsilonId");
        final EpsilonData epsilonData3 = new EpsilonData();
        epsilonData3.setId("id");
        epsilonData3.setCreateDate(LocalDate.of(2020, 1, 1));
        epsilonData3.setDescription("description");
        deltaData2.setEpsilonData(epsilonData3);
        expectedResult.setDeltaData(deltaData2);

        when(mockAlphaService.findById("id")).thenReturn(Optional.empty());

        // Configure BetaService.findById(...).
        final BetaData betaData4 = new BetaData();
        betaData4.setId("id");
        betaData4.setCreateDate(LocalDate.of(2020, 1, 1));
        betaData4.setDescription("description");
        betaData4.setGammaId("gammaId");
        final GammaData gammaData4 = new GammaData();
        gammaData4.setId("id");
        gammaData4.setCreateDate(LocalDate.of(2020, 1, 1));
        gammaData4.setDescription("description");
        betaData4.setGammaData(gammaData4);
        final Optional<BetaData> betaData3 = Optional.of(betaData4);
        when(mockBetaService.findById("id")).thenReturn(betaData3);

        when(mockDeltaService.findById("id")).thenReturn(Optional.empty());

        // Configure EpsilonService.findById(...).
        final EpsilonData epsilonData5 = new EpsilonData();
        epsilonData5.setId("id");
        epsilonData5.setCreateDate(LocalDate.of(2020, 1, 1));
        epsilonData5.setDescription("description");
        final Optional<EpsilonData> epsilonData4 = Optional.of(epsilonData5);
        when(mockEpsilonService.findById("id")).thenReturn(epsilonData4);

        // Configure DeltaService.save(...).
        final DeltaData deltaData3 = new DeltaData();
        deltaData3.setId("id");
        deltaData3.setCreateDate(LocalDate.of(2020, 1, 1));
        deltaData3.setDescription("description");
        deltaData3.setEpsilonId("epsilonId");
        final EpsilonData epsilonData6 = new EpsilonData();
        epsilonData6.setId("id");
        epsilonData6.setCreateDate(LocalDate.of(2020, 1, 1));
        epsilonData6.setDescription("description");
        deltaData3.setEpsilonData(epsilonData6);
        final DeltaData alphaData1 = new DeltaData();
        alphaData1.setId("id");
        alphaData1.setCreateDate(LocalDate.of(2020, 1, 1));
        alphaData1.setDescription("description");
        alphaData1.setEpsilonId("epsilonId");
        final EpsilonData epsilonData7 = new EpsilonData();
        epsilonData7.setId("id");
        epsilonData7.setCreateDate(LocalDate.of(2020, 1, 1));
        epsilonData7.setDescription("description");
        alphaData1.setEpsilonData(epsilonData7);
        when(mockDeltaService.save(alphaData1)).thenReturn(deltaData3);

        // Configure AlphaService.save(...).
        final AlphaData alphaData2 = new AlphaData();
        alphaData2.setId("id");
        alphaData2.setCreateDate(LocalDate.of(2020, 1, 1));
        alphaData2.setBetaId("betaId");
        final BetaData betaData5 = new BetaData();
        betaData5.setId("id");
        betaData5.setCreateDate(LocalDate.of(2020, 1, 1));
        betaData5.setDescription("description");
        betaData5.setGammaId("gammaId");
        final GammaData gammaData5 = new GammaData();
        gammaData5.setId("id");
        gammaData5.setCreateDate(LocalDate.of(2020, 1, 1));
        gammaData5.setDescription("description");
        betaData5.setGammaData(gammaData5);
        alphaData2.setBetaData(betaData5);
        final DeltaData deltaData4 = new DeltaData();
        deltaData4.setId("id");
        deltaData4.setCreateDate(LocalDate.of(2020, 1, 1));
        deltaData4.setDescription("description");
        deltaData4.setEpsilonId("epsilonId");
        final EpsilonData epsilonData8 = new EpsilonData();
        epsilonData8.setId("id");
        epsilonData8.setCreateDate(LocalDate.of(2020, 1, 1));
        epsilonData8.setDescription("description");
        deltaData4.setEpsilonData(epsilonData8);
        alphaData2.setDeltaData(deltaData4);
        final AlphaData alphaData3 = new AlphaData();
        alphaData3.setId("id");
        alphaData3.setCreateDate(LocalDate.of(2020, 1, 1));
        alphaData3.setBetaId("betaId");
        final BetaData betaData6 = new BetaData();
        betaData6.setId("id");
        betaData6.setCreateDate(LocalDate.of(2020, 1, 1));
        betaData6.setDescription("description");
        betaData6.setGammaId("gammaId");
        final GammaData gammaData6 = new GammaData();
        gammaData6.setId("id");
        gammaData6.setCreateDate(LocalDate.of(2020, 1, 1));
        gammaData6.setDescription("description");
        betaData6.setGammaData(gammaData6);
        alphaData3.setBetaData(betaData6);
        final DeltaData deltaData5 = new DeltaData();
        deltaData5.setId("id");
        deltaData5.setCreateDate(LocalDate.of(2020, 1, 1));
        deltaData5.setDescription("description");
        deltaData5.setEpsilonId("epsilonId");
        final EpsilonData epsilonData9 = new EpsilonData();
        epsilonData9.setId("id");
        epsilonData9.setCreateDate(LocalDate.of(2020, 1, 1));
        epsilonData9.setDescription("description");
        deltaData5.setEpsilonData(epsilonData9);
        alphaData3.setDeltaData(deltaData5);
        when(mockAlphaService.save(alphaData3)).thenReturn(alphaData2);

        // Run the test
        final AlphaData result = myClassUnderTest.getOrCreateAlphaData1(fullData);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetOrCreateAlphaData1_EpsilonServiceFindByIdReturnsAbsent() {
        // Setup
        final FullData fullData = new FullData();
        final AlphaData alphaData = new AlphaData();
        alphaData.setId("id");
        alphaData.setCreateDate(LocalDate.of(2020, 1, 1));
        alphaData.setBetaId("betaId");
        final BetaData betaData = new BetaData();
        betaData.setId("id");
        betaData.setCreateDate(LocalDate.of(2020, 1, 1));
        betaData.setDescription("description");
        betaData.setGammaId("gammaId");
        final GammaData gammaData = new GammaData();
        gammaData.setId("id");
        gammaData.setCreateDate(LocalDate.of(2020, 1, 1));
        gammaData.setDescription("description");
        betaData.setGammaData(gammaData);
        alphaData.setBetaData(betaData);
        final DeltaData deltaData = new DeltaData();
        deltaData.setId("id");
        deltaData.setCreateDate(LocalDate.of(2020, 1, 1));
        deltaData.setDescription("description");
        deltaData.setEpsilonId("epsilonId");
        final EpsilonData epsilonData = new EpsilonData();
        epsilonData.setId("id");
        epsilonData.setCreateDate(LocalDate.of(2020, 1, 1));
        epsilonData.setDescription("description");
        deltaData.setEpsilonData(epsilonData);
        alphaData.setDeltaData(deltaData);
        fullData.setAlphaData(alphaData);
        final BetaData betaData1 = new BetaData();
        betaData1.setId("id");
        betaData1.setCreateDate(LocalDate.of(2020, 1, 1));
        betaData1.setDescription("description");
        betaData1.setGammaId("gammaId");
        final GammaData gammaData1 = new GammaData();
        gammaData1.setId("id");
        gammaData1.setCreateDate(LocalDate.of(2020, 1, 1));
        gammaData1.setDescription("description");
        betaData1.setGammaData(gammaData1);
        fullData.setBetaData(betaData1);
        final GammaData gammaData2 = new GammaData();
        gammaData2.setId("id");
        gammaData2.setCreateDate(LocalDate.of(2020, 1, 1));
        gammaData2.setDescription("description");
        fullData.setGammaData(gammaData2);
        final DeltaData deltaData1 = new DeltaData();
        deltaData1.setId("id");
        deltaData1.setCreateDate(LocalDate.of(2020, 1, 1));
        deltaData1.setDescription("description");
        deltaData1.setEpsilonId("epsilonId");
        final EpsilonData epsilonData1 = new EpsilonData();
        epsilonData1.setId("id");
        epsilonData1.setCreateDate(LocalDate.of(2020, 1, 1));
        epsilonData1.setDescription("description");
        deltaData1.setEpsilonData(epsilonData1);
        fullData.setDeltaData(deltaData1);
        final EpsilonData epsilonData2 = new EpsilonData();
        epsilonData2.setId("id");
        epsilonData2.setCreateDate(LocalDate.of(2020, 1, 1));
        epsilonData2.setDescription("description");
        fullData.setEpsilonData(epsilonData2);

        final AlphaData expectedResult = new AlphaData();
        expectedResult.setId("id");
        expectedResult.setCreateDate(LocalDate.of(2020, 1, 1));
        expectedResult.setBetaId("betaId");
        final BetaData betaData2 = new BetaData();
        betaData2.setId("id");
        betaData2.setCreateDate(LocalDate.of(2020, 1, 1));
        betaData2.setDescription("description");
        betaData2.setGammaId("gammaId");
        final GammaData gammaData3 = new GammaData();
        gammaData3.setId("id");
        gammaData3.setCreateDate(LocalDate.of(2020, 1, 1));
        gammaData3.setDescription("description");
        betaData2.setGammaData(gammaData3);
        expectedResult.setBetaData(betaData2);
        final DeltaData deltaData2 = new DeltaData();
        deltaData2.setId("id");
        deltaData2.setCreateDate(LocalDate.of(2020, 1, 1));
        deltaData2.setDescription("description");
        deltaData2.setEpsilonId("epsilonId");
        final EpsilonData epsilonData3 = new EpsilonData();
        epsilonData3.setId("id");
        epsilonData3.setCreateDate(LocalDate.of(2020, 1, 1));
        epsilonData3.setDescription("description");
        deltaData2.setEpsilonData(epsilonData3);
        expectedResult.setDeltaData(deltaData2);

        when(mockAlphaService.findById("id")).thenReturn(Optional.empty());

        // Configure BetaService.findById(...).
        final BetaData betaData4 = new BetaData();
        betaData4.setId("id");
        betaData4.setCreateDate(LocalDate.of(2020, 1, 1));
        betaData4.setDescription("description");
        betaData4.setGammaId("gammaId");
        final GammaData gammaData4 = new GammaData();
        gammaData4.setId("id");
        gammaData4.setCreateDate(LocalDate.of(2020, 1, 1));
        gammaData4.setDescription("description");
        betaData4.setGammaData(gammaData4);
        final Optional<BetaData> betaData3 = Optional.of(betaData4);
        when(mockBetaService.findById("id")).thenReturn(betaData3);

        when(mockDeltaService.findById("id")).thenReturn(Optional.empty());
        when(mockEpsilonService.findById("id")).thenReturn(Optional.empty());

        // Configure EpsilonService.save(...).
        final EpsilonData epsilonData4 = new EpsilonData();
        epsilonData4.setId("id");
        epsilonData4.setCreateDate(LocalDate.of(2020, 1, 1));
        epsilonData4.setDescription("description");
        final EpsilonData alphaData1 = new EpsilonData();
        alphaData1.setId("id");
        alphaData1.setCreateDate(LocalDate.of(2020, 1, 1));
        alphaData1.setDescription("description");
        when(mockEpsilonService.save(alphaData1)).thenReturn(epsilonData4);

        // Configure DeltaService.save(...).
        final DeltaData deltaData3 = new DeltaData();
        deltaData3.setId("id");
        deltaData3.setCreateDate(LocalDate.of(2020, 1, 1));
        deltaData3.setDescription("description");
        deltaData3.setEpsilonId("epsilonId");
        final EpsilonData epsilonData5 = new EpsilonData();
        epsilonData5.setId("id");
        epsilonData5.setCreateDate(LocalDate.of(2020, 1, 1));
        epsilonData5.setDescription("description");
        deltaData3.setEpsilonData(epsilonData5);
        final DeltaData alphaData2 = new DeltaData();
        alphaData2.setId("id");
        alphaData2.setCreateDate(LocalDate.of(2020, 1, 1));
        alphaData2.setDescription("description");
        alphaData2.setEpsilonId("epsilonId");
        final EpsilonData epsilonData6 = new EpsilonData();
        epsilonData6.setId("id");
        epsilonData6.setCreateDate(LocalDate.of(2020, 1, 1));
        epsilonData6.setDescription("description");
        alphaData2.setEpsilonData(epsilonData6);
        when(mockDeltaService.save(alphaData2)).thenReturn(deltaData3);

        // Configure AlphaService.save(...).
        final AlphaData alphaData3 = new AlphaData();
        alphaData3.setId("id");
        alphaData3.setCreateDate(LocalDate.of(2020, 1, 1));
        alphaData3.setBetaId("betaId");
        final BetaData betaData5 = new BetaData();
        betaData5.setId("id");
        betaData5.setCreateDate(LocalDate.of(2020, 1, 1));
        betaData5.setDescription("description");
        betaData5.setGammaId("gammaId");
        final GammaData gammaData5 = new GammaData();
        gammaData5.setId("id");
        gammaData5.setCreateDate(LocalDate.of(2020, 1, 1));
        gammaData5.setDescription("description");
        betaData5.setGammaData(gammaData5);
        alphaData3.setBetaData(betaData5);
        final DeltaData deltaData4 = new DeltaData();
        deltaData4.setId("id");
        deltaData4.setCreateDate(LocalDate.of(2020, 1, 1));
        deltaData4.setDescription("description");
        deltaData4.setEpsilonId("epsilonId");
        final EpsilonData epsilonData7 = new EpsilonData();
        epsilonData7.setId("id");
        epsilonData7.setCreateDate(LocalDate.of(2020, 1, 1));
        epsilonData7.setDescription("description");
        deltaData4.setEpsilonData(epsilonData7);
        alphaData3.setDeltaData(deltaData4);
        final AlphaData alphaData4 = new AlphaData();
        alphaData4.setId("id");
        alphaData4.setCreateDate(LocalDate.of(2020, 1, 1));
        alphaData4.setBetaId("betaId");
        final BetaData betaData6 = new BetaData();
        betaData6.setId("id");
        betaData6.setCreateDate(LocalDate.of(2020, 1, 1));
        betaData6.setDescription("description");
        betaData6.setGammaId("gammaId");
        final GammaData gammaData6 = new GammaData();
        gammaData6.setId("id");
        gammaData6.setCreateDate(LocalDate.of(2020, 1, 1));
        gammaData6.setDescription("description");
        betaData6.setGammaData(gammaData6);
        alphaData4.setBetaData(betaData6);
        final DeltaData deltaData5 = new DeltaData();
        deltaData5.setId("id");
        deltaData5.setCreateDate(LocalDate.of(2020, 1, 1));
        deltaData5.setDescription("description");
        deltaData5.setEpsilonId("epsilonId");
        final EpsilonData epsilonData8 = new EpsilonData();
        epsilonData8.setId("id");
        epsilonData8.setCreateDate(LocalDate.of(2020, 1, 1));
        epsilonData8.setDescription("description");
        deltaData5.setEpsilonData(epsilonData8);
        alphaData4.setDeltaData(deltaData5);
        when(mockAlphaService.save(alphaData4)).thenReturn(alphaData3);

        // Run the test
        final AlphaData result = myClassUnderTest.getOrCreateAlphaData1(fullData);

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
