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
        final BetaData betaData = new BetaData();
        betaData.setId("id");
        final GammaData gammaData = new GammaData();
        gammaData.setId("id");
        betaData.setGammaData(gammaData);
        alphaData.setBetaData(betaData);
        final DeltaData deltaData = new DeltaData();
        deltaData.setId("id");
        final EpsilonData epsilonData = new EpsilonData();
        epsilonData.setId("id");
        deltaData.setEpsilonData(epsilonData);
        alphaData.setDeltaData(deltaData);
        fullData.setAlphaData(alphaData);
        final BetaData betaData1 = new BetaData();
        betaData1.setId("id");
        final GammaData gammaData1 = new GammaData();
        gammaData1.setId("id");
        betaData1.setGammaData(gammaData1);
        fullData.setBetaData(betaData1);
        final DeltaData deltaData1 = new DeltaData();
        deltaData1.setId("id");
        final EpsilonData epsilonData1 = new EpsilonData();
        epsilonData1.setId("id");
        deltaData1.setEpsilonData(epsilonData1);
        fullData.setDeltaData(deltaData1);

        final AlphaData expectedResult = new AlphaData();
        expectedResult.setId("id");
        expectedResult.setCreateDate(LocalDate.of(2020, 1, 1));
        expectedResult.setBetaId("betaId");
        final BetaData betaData2 = new BetaData();
        betaData2.setId("id");
        betaData2.setCreateDate(LocalDate.of(2020, 1, 1));
        betaData2.setDescription("description");
        betaData2.setGammaId("gammaId");
        final GammaData gammaData2 = new GammaData();
        gammaData2.setId("id");
        gammaData2.setCreateDate(LocalDate.of(2020, 1, 1));
        betaData2.setGammaData(gammaData2);
        expectedResult.setBetaData(betaData2);
        final DeltaData deltaData2 = new DeltaData();
        deltaData2.setId("id");
        final EpsilonData epsilonData2 = new EpsilonData();
        epsilonData2.setId("id");
        deltaData2.setEpsilonData(epsilonData2);
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
        final GammaData gammaData3 = new GammaData();
        gammaData3.setId("id");
        gammaData3.setCreateDate(LocalDate.of(2020, 1, 1));
        betaData3.setGammaData(gammaData3);
        alphaData2.setBetaData(betaData3);
        final DeltaData deltaData3 = new DeltaData();
        deltaData3.setId("id");
        final EpsilonData epsilonData3 = new EpsilonData();
        epsilonData3.setId("id");
        deltaData3.setEpsilonData(epsilonData3);
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
        final BetaData betaData = new BetaData();
        betaData.setId("id");
        final GammaData gammaData = new GammaData();
        gammaData.setId("id");
        betaData.setGammaData(gammaData);
        alphaData.setBetaData(betaData);
        final DeltaData deltaData = new DeltaData();
        deltaData.setId("id");
        final EpsilonData epsilonData = new EpsilonData();
        epsilonData.setId("id");
        deltaData.setEpsilonData(epsilonData);
        alphaData.setDeltaData(deltaData);
        fullData.setAlphaData(alphaData);
        final BetaData betaData1 = new BetaData();
        betaData1.setId("id");
        final GammaData gammaData1 = new GammaData();
        gammaData1.setId("id");
        betaData1.setGammaData(gammaData1);
        fullData.setBetaData(betaData1);
        final DeltaData deltaData1 = new DeltaData();
        deltaData1.setId("id");
        final EpsilonData epsilonData1 = new EpsilonData();
        epsilonData1.setId("id");
        deltaData1.setEpsilonData(epsilonData1);
        fullData.setDeltaData(deltaData1);

        final AlphaData expectedResult = new AlphaData();
        expectedResult.setId("id");
        expectedResult.setCreateDate(LocalDate.of(2020, 1, 1));
        expectedResult.setBetaId("betaId");
        final BetaData betaData2 = new BetaData();
        betaData2.setId("id");
        betaData2.setCreateDate(LocalDate.of(2020, 1, 1));
        betaData2.setDescription("description");
        betaData2.setGammaId("gammaId");
        final GammaData gammaData2 = new GammaData();
        gammaData2.setId("id");
        gammaData2.setCreateDate(LocalDate.of(2020, 1, 1));
        betaData2.setGammaData(gammaData2);
        expectedResult.setBetaData(betaData2);
        final DeltaData deltaData2 = new DeltaData();
        deltaData2.setId("id");
        final EpsilonData epsilonData2 = new EpsilonData();
        epsilonData2.setId("id");
        deltaData2.setEpsilonData(epsilonData2);
        expectedResult.setDeltaData(deltaData2);

        when(mockAlphaService.findById("id")).thenReturn(Optional.empty());

        // Configure BetaService.findById(...).
        final BetaData betaData4 = new BetaData();
        betaData4.setId("id");
        betaData4.setCreateDate(LocalDate.of(2020, 1, 1));
        betaData4.setDescription("description");
        betaData4.setGammaId("gammaId");
        final GammaData gammaData3 = new GammaData();
        gammaData3.setId("id");
        gammaData3.setCreateDate(LocalDate.of(2020, 1, 1));
        gammaData3.setDescription("description");
        betaData4.setGammaData(gammaData3);
        final Optional<BetaData> betaData3 = Optional.of(betaData4);
        when(mockBetaService.findById("id")).thenReturn(betaData3);

        // Configure DeltaService.findById(...).
        final DeltaData deltaData4 = new DeltaData();
        deltaData4.setId("id");
        deltaData4.setCreateDate(LocalDate.of(2020, 1, 1));
        deltaData4.setDescription("description");
        deltaData4.setEpsilonId("epsilonId");
        final EpsilonData epsilonData3 = new EpsilonData();
        epsilonData3.setId("id");
        epsilonData3.setCreateDate(LocalDate.of(2020, 1, 1));
        epsilonData3.setDescription("description");
        deltaData4.setEpsilonData(epsilonData3);
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
        final GammaData gammaData4 = new GammaData();
        gammaData4.setId("id");
        gammaData4.setCreateDate(LocalDate.of(2020, 1, 1));
        betaData5.setGammaData(gammaData4);
        alphaData1.setBetaData(betaData5);
        final DeltaData deltaData5 = new DeltaData();
        deltaData5.setId("id");
        final EpsilonData epsilonData4 = new EpsilonData();
        epsilonData4.setId("id");
        deltaData5.setEpsilonData(epsilonData4);
        alphaData1.setDeltaData(deltaData5);
        when(mockAlphaService.save(new AlphaData())).thenReturn(alphaData1);

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
        final BetaData betaData = new BetaData();
        betaData.setId("id");
        final GammaData gammaData = new GammaData();
        gammaData.setId("id");
        betaData.setGammaData(gammaData);
        alphaData.setBetaData(betaData);
        final DeltaData deltaData = new DeltaData();
        deltaData.setId("id");
        final EpsilonData epsilonData = new EpsilonData();
        epsilonData.setId("id");
        deltaData.setEpsilonData(epsilonData);
        alphaData.setDeltaData(deltaData);
        fullData.setAlphaData(alphaData);
        final BetaData betaData1 = new BetaData();
        betaData1.setId("id");
        final GammaData gammaData1 = new GammaData();
        gammaData1.setId("id");
        betaData1.setGammaData(gammaData1);
        fullData.setBetaData(betaData1);
        final DeltaData deltaData1 = new DeltaData();
        deltaData1.setId("id");
        final EpsilonData epsilonData1 = new EpsilonData();
        epsilonData1.setId("id");
        deltaData1.setEpsilonData(epsilonData1);
        fullData.setDeltaData(deltaData1);

        final AlphaData expectedResult = new AlphaData();
        expectedResult.setId("id");
        expectedResult.setCreateDate(LocalDate.of(2020, 1, 1));
        expectedResult.setBetaId("betaId");
        final BetaData betaData2 = new BetaData();
        betaData2.setId("id");
        betaData2.setCreateDate(LocalDate.of(2020, 1, 1));
        betaData2.setDescription("description");
        betaData2.setGammaId("gammaId");
        final GammaData gammaData2 = new GammaData();
        gammaData2.setId("id");
        gammaData2.setCreateDate(LocalDate.of(2020, 1, 1));
        betaData2.setGammaData(gammaData2);
        expectedResult.setBetaData(betaData2);
        final DeltaData deltaData2 = new DeltaData();
        deltaData2.setId("id");
        final EpsilonData epsilonData2 = new EpsilonData();
        epsilonData2.setId("id");
        deltaData2.setEpsilonData(epsilonData2);
        expectedResult.setDeltaData(deltaData2);

        when(mockAlphaService.findById("id")).thenReturn(Optional.empty());
        when(mockBetaService.findById("id")).thenReturn(Optional.empty());

        // Configure GammaService.findById(...).
        final GammaData gammaData4 = new GammaData();
        gammaData4.setId("id");
        gammaData4.setCreateDate(LocalDate.of(2020, 1, 1));
        gammaData4.setDescription("description");
        final Optional<GammaData> gammaData3 = Optional.of(gammaData4);
        when(mockGammaService.findById("id")).thenReturn(gammaData3);

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
        when(mockBetaService.save(new BetaData())).thenReturn(betaData3);

        // Configure DeltaService.findById(...).
        final DeltaData deltaData4 = new DeltaData();
        deltaData4.setId("id");
        deltaData4.setCreateDate(LocalDate.of(2020, 1, 1));
        deltaData4.setDescription("description");
        deltaData4.setEpsilonId("epsilonId");
        final EpsilonData epsilonData3 = new EpsilonData();
        epsilonData3.setId("id");
        epsilonData3.setCreateDate(LocalDate.of(2020, 1, 1));
        epsilonData3.setDescription("description");
        deltaData4.setEpsilonData(epsilonData3);
        final Optional<DeltaData> deltaData3 = Optional.of(deltaData4);
        when(mockDeltaService.findById("id")).thenReturn(deltaData3);

        // Configure AlphaService.save(...).
        final AlphaData alphaData1 = new AlphaData();
        alphaData1.setId("id");
        alphaData1.setCreateDate(LocalDate.of(2020, 1, 1));
        alphaData1.setBetaId("betaId");
        final BetaData betaData4 = new BetaData();
        betaData4.setId("id");
        betaData4.setCreateDate(LocalDate.of(2020, 1, 1));
        betaData4.setDescription("description");
        betaData4.setGammaId("gammaId");
        final GammaData gammaData6 = new GammaData();
        gammaData6.setId("id");
        gammaData6.setCreateDate(LocalDate.of(2020, 1, 1));
        betaData4.setGammaData(gammaData6);
        alphaData1.setBetaData(betaData4);
        final DeltaData deltaData5 = new DeltaData();
        deltaData5.setId("id");
        final EpsilonData epsilonData4 = new EpsilonData();
        epsilonData4.setId("id");
        deltaData5.setEpsilonData(epsilonData4);
        alphaData1.setDeltaData(deltaData5);
        when(mockAlphaService.save(new AlphaData())).thenReturn(alphaData1);

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
        final BetaData betaData = new BetaData();
        betaData.setId("id");
        final GammaData gammaData = new GammaData();
        gammaData.setId("id");
        betaData.setGammaData(gammaData);
        alphaData.setBetaData(betaData);
        final DeltaData deltaData = new DeltaData();
        deltaData.setId("id");
        final EpsilonData epsilonData = new EpsilonData();
        epsilonData.setId("id");
        deltaData.setEpsilonData(epsilonData);
        alphaData.setDeltaData(deltaData);
        fullData.setAlphaData(alphaData);
        final BetaData betaData1 = new BetaData();
        betaData1.setId("id");
        final GammaData gammaData1 = new GammaData();
        gammaData1.setId("id");
        betaData1.setGammaData(gammaData1);
        fullData.setBetaData(betaData1);
        final DeltaData deltaData1 = new DeltaData();
        deltaData1.setId("id");
        final EpsilonData epsilonData1 = new EpsilonData();
        epsilonData1.setId("id");
        deltaData1.setEpsilonData(epsilonData1);
        fullData.setDeltaData(deltaData1);

        final AlphaData expectedResult = new AlphaData();
        expectedResult.setId("id");
        expectedResult.setCreateDate(LocalDate.of(2020, 1, 1));
        expectedResult.setBetaId("betaId");
        final BetaData betaData2 = new BetaData();
        betaData2.setId("id");
        betaData2.setCreateDate(LocalDate.of(2020, 1, 1));
        betaData2.setDescription("description");
        betaData2.setGammaId("gammaId");
        final GammaData gammaData2 = new GammaData();
        gammaData2.setId("id");
        gammaData2.setCreateDate(LocalDate.of(2020, 1, 1));
        betaData2.setGammaData(gammaData2);
        expectedResult.setBetaData(betaData2);
        final DeltaData deltaData2 = new DeltaData();
        deltaData2.setId("id");
        final EpsilonData epsilonData2 = new EpsilonData();
        epsilonData2.setId("id");
        deltaData2.setEpsilonData(epsilonData2);
        expectedResult.setDeltaData(deltaData2);

        when(mockAlphaService.findById("id")).thenReturn(Optional.empty());
        when(mockBetaService.findById("id")).thenReturn(Optional.empty());
        when(mockGammaService.findById("id")).thenReturn(Optional.empty());

        // Configure GammaService.save(...).
        final GammaData gammaData3 = new GammaData();
        gammaData3.setId("id");
        gammaData3.setCreateDate(LocalDate.of(2020, 1, 1));
        gammaData3.setDescription("description");
        when(mockGammaService.save(new GammaData())).thenReturn(gammaData3);

        // Configure BetaService.save(...).
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
        when(mockBetaService.save(new BetaData())).thenReturn(betaData3);

        // Configure DeltaService.findById(...).
        final DeltaData deltaData4 = new DeltaData();
        deltaData4.setId("id");
        deltaData4.setCreateDate(LocalDate.of(2020, 1, 1));
        deltaData4.setDescription("description");
        deltaData4.setEpsilonId("epsilonId");
        final EpsilonData epsilonData3 = new EpsilonData();
        epsilonData3.setId("id");
        epsilonData3.setCreateDate(LocalDate.of(2020, 1, 1));
        epsilonData3.setDescription("description");
        deltaData4.setEpsilonData(epsilonData3);
        final Optional<DeltaData> deltaData3 = Optional.of(deltaData4);
        when(mockDeltaService.findById("id")).thenReturn(deltaData3);

        // Configure AlphaService.save(...).
        final AlphaData alphaData1 = new AlphaData();
        alphaData1.setId("id");
        alphaData1.setCreateDate(LocalDate.of(2020, 1, 1));
        alphaData1.setBetaId("betaId");
        final BetaData betaData4 = new BetaData();
        betaData4.setId("id");
        betaData4.setCreateDate(LocalDate.of(2020, 1, 1));
        betaData4.setDescription("description");
        betaData4.setGammaId("gammaId");
        final GammaData gammaData5 = new GammaData();
        gammaData5.setId("id");
        gammaData5.setCreateDate(LocalDate.of(2020, 1, 1));
        betaData4.setGammaData(gammaData5);
        alphaData1.setBetaData(betaData4);
        final DeltaData deltaData5 = new DeltaData();
        deltaData5.setId("id");
        final EpsilonData epsilonData4 = new EpsilonData();
        epsilonData4.setId("id");
        deltaData5.setEpsilonData(epsilonData4);
        alphaData1.setDeltaData(deltaData5);
        when(mockAlphaService.save(new AlphaData())).thenReturn(alphaData1);

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
        final BetaData betaData = new BetaData();
        betaData.setId("id");
        final GammaData gammaData = new GammaData();
        gammaData.setId("id");
        betaData.setGammaData(gammaData);
        alphaData.setBetaData(betaData);
        final DeltaData deltaData = new DeltaData();
        deltaData.setId("id");
        final EpsilonData epsilonData = new EpsilonData();
        epsilonData.setId("id");
        deltaData.setEpsilonData(epsilonData);
        alphaData.setDeltaData(deltaData);
        fullData.setAlphaData(alphaData);
        final BetaData betaData1 = new BetaData();
        betaData1.setId("id");
        final GammaData gammaData1 = new GammaData();
        gammaData1.setId("id");
        betaData1.setGammaData(gammaData1);
        fullData.setBetaData(betaData1);
        final DeltaData deltaData1 = new DeltaData();
        deltaData1.setId("id");
        final EpsilonData epsilonData1 = new EpsilonData();
        epsilonData1.setId("id");
        deltaData1.setEpsilonData(epsilonData1);
        fullData.setDeltaData(deltaData1);

        final AlphaData expectedResult = new AlphaData();
        expectedResult.setId("id");
        expectedResult.setCreateDate(LocalDate.of(2020, 1, 1));
        expectedResult.setBetaId("betaId");
        final BetaData betaData2 = new BetaData();
        betaData2.setId("id");
        betaData2.setCreateDate(LocalDate.of(2020, 1, 1));
        betaData2.setDescription("description");
        betaData2.setGammaId("gammaId");
        final GammaData gammaData2 = new GammaData();
        gammaData2.setId("id");
        gammaData2.setCreateDate(LocalDate.of(2020, 1, 1));
        betaData2.setGammaData(gammaData2);
        expectedResult.setBetaData(betaData2);
        final DeltaData deltaData2 = new DeltaData();
        deltaData2.setId("id");
        final EpsilonData epsilonData2 = new EpsilonData();
        epsilonData2.setId("id");
        deltaData2.setEpsilonData(epsilonData2);
        expectedResult.setDeltaData(deltaData2);

        when(mockAlphaService.findById("id")).thenReturn(Optional.empty());

        // Configure BetaService.findById(...).
        final BetaData betaData4 = new BetaData();
        betaData4.setId("id");
        betaData4.setCreateDate(LocalDate.of(2020, 1, 1));
        betaData4.setDescription("description");
        betaData4.setGammaId("gammaId");
        final GammaData gammaData3 = new GammaData();
        gammaData3.setId("id");
        gammaData3.setCreateDate(LocalDate.of(2020, 1, 1));
        gammaData3.setDescription("description");
        betaData4.setGammaData(gammaData3);
        final Optional<BetaData> betaData3 = Optional.of(betaData4);
        when(mockBetaService.findById("id")).thenReturn(betaData3);

        when(mockDeltaService.findById("id")).thenReturn(Optional.empty());

        // Configure EpsilonService.findById(...).
        final EpsilonData epsilonData4 = new EpsilonData();
        epsilonData4.setId("id");
        epsilonData4.setCreateDate(LocalDate.of(2020, 1, 1));
        epsilonData4.setDescription("description");
        final Optional<EpsilonData> epsilonData3 = Optional.of(epsilonData4);
        when(mockEpsilonService.findById("id")).thenReturn(epsilonData3);

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
        when(mockDeltaService.save(new DeltaData())).thenReturn(deltaData3);

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
        final GammaData gammaData4 = new GammaData();
        gammaData4.setId("id");
        gammaData4.setCreateDate(LocalDate.of(2020, 1, 1));
        betaData5.setGammaData(gammaData4);
        alphaData1.setBetaData(betaData5);
        final DeltaData deltaData4 = new DeltaData();
        deltaData4.setId("id");
        final EpsilonData epsilonData6 = new EpsilonData();
        epsilonData6.setId("id");
        deltaData4.setEpsilonData(epsilonData6);
        alphaData1.setDeltaData(deltaData4);
        when(mockAlphaService.save(new AlphaData())).thenReturn(alphaData1);

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
        final BetaData betaData = new BetaData();
        betaData.setId("id");
        final GammaData gammaData = new GammaData();
        gammaData.setId("id");
        betaData.setGammaData(gammaData);
        alphaData.setBetaData(betaData);
        final DeltaData deltaData = new DeltaData();
        deltaData.setId("id");
        final EpsilonData epsilonData = new EpsilonData();
        epsilonData.setId("id");
        deltaData.setEpsilonData(epsilonData);
        alphaData.setDeltaData(deltaData);
        fullData.setAlphaData(alphaData);
        final BetaData betaData1 = new BetaData();
        betaData1.setId("id");
        final GammaData gammaData1 = new GammaData();
        gammaData1.setId("id");
        betaData1.setGammaData(gammaData1);
        fullData.setBetaData(betaData1);
        final DeltaData deltaData1 = new DeltaData();
        deltaData1.setId("id");
        final EpsilonData epsilonData1 = new EpsilonData();
        epsilonData1.setId("id");
        deltaData1.setEpsilonData(epsilonData1);
        fullData.setDeltaData(deltaData1);

        final AlphaData expectedResult = new AlphaData();
        expectedResult.setId("id");
        expectedResult.setCreateDate(LocalDate.of(2020, 1, 1));
        expectedResult.setBetaId("betaId");
        final BetaData betaData2 = new BetaData();
        betaData2.setId("id");
        betaData2.setCreateDate(LocalDate.of(2020, 1, 1));
        betaData2.setDescription("description");
        betaData2.setGammaId("gammaId");
        final GammaData gammaData2 = new GammaData();
        gammaData2.setId("id");
        gammaData2.setCreateDate(LocalDate.of(2020, 1, 1));
        betaData2.setGammaData(gammaData2);
        expectedResult.setBetaData(betaData2);
        final DeltaData deltaData2 = new DeltaData();
        deltaData2.setId("id");
        final EpsilonData epsilonData2 = new EpsilonData();
        epsilonData2.setId("id");
        deltaData2.setEpsilonData(epsilonData2);
        expectedResult.setDeltaData(deltaData2);

        when(mockAlphaService.findById("id")).thenReturn(Optional.empty());

        // Configure BetaService.findById(...).
        final BetaData betaData4 = new BetaData();
        betaData4.setId("id");
        betaData4.setCreateDate(LocalDate.of(2020, 1, 1));
        betaData4.setDescription("description");
        betaData4.setGammaId("gammaId");
        final GammaData gammaData3 = new GammaData();
        gammaData3.setId("id");
        gammaData3.setCreateDate(LocalDate.of(2020, 1, 1));
        gammaData3.setDescription("description");
        betaData4.setGammaData(gammaData3);
        final Optional<BetaData> betaData3 = Optional.of(betaData4);
        when(mockBetaService.findById("id")).thenReturn(betaData3);

        when(mockDeltaService.findById("id")).thenReturn(Optional.empty());
        when(mockEpsilonService.findById("id")).thenReturn(Optional.empty());

        // Configure EpsilonService.save(...).
        final EpsilonData epsilonData3 = new EpsilonData();
        epsilonData3.setId("id");
        epsilonData3.setCreateDate(LocalDate.of(2020, 1, 1));
        epsilonData3.setDescription("description");
        when(mockEpsilonService.save(new EpsilonData())).thenReturn(epsilonData3);

        // Configure DeltaService.save(...).
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
        when(mockDeltaService.save(new DeltaData())).thenReturn(deltaData3);

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
        final GammaData gammaData4 = new GammaData();
        gammaData4.setId("id");
        gammaData4.setCreateDate(LocalDate.of(2020, 1, 1));
        betaData5.setGammaData(gammaData4);
        alphaData1.setBetaData(betaData5);
        final DeltaData deltaData4 = new DeltaData();
        deltaData4.setId("id");
        final EpsilonData epsilonData5 = new EpsilonData();
        epsilonData5.setId("id");
        deltaData4.setEpsilonData(epsilonData5);
        alphaData1.setDeltaData(deltaData4);
        when(mockAlphaService.save(new AlphaData())).thenReturn(alphaData1);

        // Run the test
        final AlphaData result = myClassUnderTest.getOrCreateAlphaData1(fullData);

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
