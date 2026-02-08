package com.myapp;

import com.google.common.util.concurrent.MoreExecutors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private FooService mockFooService;
    @Mock
    private BarService mockBarService;
    @Mock
    private FooExtraService mockFooExtraService;
    @Mock
    private OtherService mockOtherDataService;
    @Mock
    private MetricsService mockMetricsService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(MoreExecutors.newDirectExecutorService(), mockFooService, mockBarService,
                mockFooExtraService, mockOtherDataService, mockMetricsService);
    }

    @Test
    void testGetFooAndBar1() {
        // Setup
        final FooAndBarData expectedResult = new FooAndBarData(new FooData("id", "barId", "extraDataId", "name"),
                new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0)));

        // Configure FooService.getFooById(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooById("fooId")).thenReturn(fooData);

        // Configure BarService.getBarForId(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId("barId")).thenReturn(barData);

        // Run the test
        final FooAndBarData result = myClassUnderTest.getFooAndBar1("fooId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooAndBar1_FooServiceThrowsFooServiceException() {
        // Setup
        when(mockFooService.getFooById("fooId")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar1("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testGetFooAndBar1_BarServiceThrowsBarServiceException() {
        // Setup
        // Configure FooService.getFooById(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooById("fooId")).thenReturn(fooData);

        when(mockBarService.getBarForId("barId")).thenThrow(BarServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar1("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testGetFooAndBar2() {
        // Setup
        final FooAndBarData expectedResult = new FooAndBarData(new FooData("id", "barId", "extraDataId", "name"),
                new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0)));

        // Configure FooService.getFooById(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooById("fooId")).thenReturn(fooData);

        // Configure BarService.getBarForId(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId("barId")).thenReturn(barData);

        // Run the test
        final FooAndBarData result = myClassUnderTest.getFooAndBar2("fooId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooAndBar2_FooServiceThrowsFooServiceException() {
        // Setup
        when(mockFooService.getFooById("fooId")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar2("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testGetFooAndBar2_BarServiceThrowsBarServiceException() {
        // Setup
        // Configure FooService.getFooById(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooById("fooId")).thenReturn(fooData);

        when(mockBarService.getBarForId("barId")).thenThrow(BarServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar2("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testGetFooAndBar3() {
        // Setup
        final FooAndBarData expectedResult = new FooAndBarData(new FooData("id", "barId", "extraDataId", "name"),
                new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0)));

        // Configure FooService.getFooById(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooById("fooId")).thenReturn(fooData);

        // Configure BarService.getBarForId(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId("barId")).thenReturn(barData);

        // Run the test
        final FooAndBarData result = myClassUnderTest.getFooAndBar3("fooId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooAndBar3_FooServiceThrowsFooServiceException() {
        // Setup
        when(mockFooService.getFooById("fooId")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar3("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testGetFooAndBar3_BarServiceThrowsBarServiceException() {
        // Setup
        // Configure FooService.getFooById(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooById("fooId")).thenReturn(fooData);

        when(mockBarService.getBarForId("barId")).thenThrow(BarServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar3("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testGetFooAndBar4() {
        // Setup
        final FooAndBarData expectedResult = new FooAndBarData(new FooData("id", "barId", "extraDataId", "name"),
                new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0)));

        // Configure FooService.getFooById(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooById("fooId")).thenReturn(fooData);

        // Configure BarService.getBarForId(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId("barId")).thenReturn(barData);

        // Run the test
        final FooAndBarData result = myClassUnderTest.getFooAndBar4("fooId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooAndBar4_FooServiceThrowsFooServiceException() {
        // Setup
        when(mockFooService.getFooById("fooId")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar4("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testGetFooAndBar4_BarServiceThrowsBarServiceException() {
        // Setup
        // Configure FooService.getFooById(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooById("fooId")).thenReturn(fooData);

        when(mockBarService.getBarForId("barId")).thenThrow(BarServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar4("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testGetFooAndBar5() {
        // Setup
        final FooAndBarData expectedResult = new FooAndBarData(new FooData("id", "barId", "extraDataId", "name"),
                new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0)));

        // Configure FooService.getFooByIdAsync(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure BarService.getBarForId(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId("barId")).thenReturn(barData);

        // Run the test
        final FooAndBarData result = myClassUnderTest.getFooAndBar5("fooId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooAndBar5_FooServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooByIdAsync(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = new CompletableFuture<>();
        fooDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockFooService.getFooByIdAsync("fooId")).thenReturn(fooDataCompletableFuture);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar5("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testGetFooAndBar5_BarServiceThrowsBarServiceException() {
        // Setup
        // Configure FooService.getFooByIdAsync(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync("fooId")).thenReturn(fooDataCompletableFuture);

        when(mockBarService.getBarForId("barId")).thenThrow(BarServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar5("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testGetAllData1() {
        // Setup
        final AllData expectedResult = new AllData(new FooData("id", "barId", "extraDataId", "name"),
                new FooExtraData("id", "content".getBytes()),
                new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0)),
                new OtherData("id", "description"));

        // Configure FooService.getFooById(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooById("fooId")).thenReturn(fooData);

        // Configure FooExtraService.getExtraData(...).
        final FooExtraData fooExtraData = new FooExtraData("id", "content".getBytes());
        when(mockFooExtraService.getExtraData("extraDataId")).thenReturn(fooExtraData);

        // Configure BarService.getBarForId(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId("barId")).thenReturn(barData);

        when(mockOtherDataService.getData("otherId")).thenReturn(new OtherData("id", "description"));

        // Run the test
        final AllData result = myClassUnderTest.getAllData1("fooId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetAllData1_FooServiceThrowsFooServiceException() {
        // Setup
        when(mockFooService.getFooById("fooId")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getAllData1("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testGetAllData1_FooExtraServiceThrowsFooExtraServiceException() {
        // Setup
        // Configure FooService.getFooById(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooById("fooId")).thenReturn(fooData);

        when(mockFooExtraService.getExtraData("extraDataId")).thenThrow(FooExtraServiceException.class);

        // Configure BarService.getBarForId(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId("barId")).thenReturn(barData);

        when(mockOtherDataService.getData("otherId")).thenReturn(new OtherData("id", "description"));

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getAllData1("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testGetAllData1_BarServiceThrowsBarServiceException() {
        // Setup
        // Configure FooService.getFooById(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooById("fooId")).thenReturn(fooData);

        // Configure FooExtraService.getExtraData(...).
        final FooExtraData fooExtraData = new FooExtraData("id", "content".getBytes());
        when(mockFooExtraService.getExtraData("extraDataId")).thenReturn(fooExtraData);

        when(mockBarService.getBarForId("barId")).thenThrow(BarServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getAllData1("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testGetAllData1_OtherServiceThrowsOtherServiceException() {
        // Setup
        // Configure FooService.getFooById(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooById("fooId")).thenReturn(fooData);

        // Configure FooExtraService.getExtraData(...).
        final FooExtraData fooExtraData = new FooExtraData("id", "content".getBytes());
        when(mockFooExtraService.getExtraData("extraDataId")).thenReturn(fooExtraData);

        // Configure BarService.getBarForId(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId("barId")).thenReturn(barData);

        when(mockOtherDataService.getData("otherId")).thenThrow(OtherServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getAllData1("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testGetAllData2() {
        // Setup
        final AllData expectedResult = new AllData(new FooData("id", "barId", "extraDataId", "name"),
                new FooExtraData("id", "content".getBytes()),
                new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0)),
                new OtherData("id", "description"));

        // Configure FooService.getFooById(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooById("fooId")).thenReturn(fooData);

        // Configure FooExtraService.getExtraData(...).
        final FooExtraData fooExtraData = new FooExtraData("id", "content".getBytes());
        when(mockFooExtraService.getExtraData("extraDataId")).thenReturn(fooExtraData);

        // Configure BarService.getBarForId(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId("barId")).thenReturn(barData);

        when(mockOtherDataService.getData("otherId")).thenReturn(new OtherData("id", "description"));

        // Run the test
        final AllData result = myClassUnderTest.getAllData2("fooId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetAllData2_FooServiceThrowsFooServiceException() {
        // Setup
        when(mockFooService.getFooById("fooId")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getAllData2("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testGetAllData2_FooExtraServiceThrowsFooExtraServiceException() {
        // Setup
        // Configure FooService.getFooById(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooById("fooId")).thenReturn(fooData);

        when(mockFooExtraService.getExtraData("extraDataId")).thenThrow(FooExtraServiceException.class);

        // Configure BarService.getBarForId(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId("barId")).thenReturn(barData);

        when(mockOtherDataService.getData("otherId")).thenReturn(new OtherData("id", "description"));

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getAllData2("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testGetAllData2_BarServiceThrowsBarServiceException() {
        // Setup
        // Configure FooService.getFooById(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooById("fooId")).thenReturn(fooData);

        // Configure FooExtraService.getExtraData(...).
        final FooExtraData fooExtraData = new FooExtraData("id", "content".getBytes());
        when(mockFooExtraService.getExtraData("extraDataId")).thenReturn(fooExtraData);

        when(mockBarService.getBarForId("barId")).thenThrow(BarServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getAllData2("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testGetAllData2_OtherServiceThrowsOtherServiceException() {
        // Setup
        // Configure FooService.getFooById(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooById("fooId")).thenReturn(fooData);

        // Configure FooExtraService.getExtraData(...).
        final FooExtraData fooExtraData = new FooExtraData("id", "content".getBytes());
        when(mockFooExtraService.getExtraData("extraDataId")).thenReturn(fooExtraData);

        // Configure BarService.getBarForId(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId("barId")).thenReturn(barData);

        when(mockOtherDataService.getData("otherId")).thenThrow(OtherServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getAllData2("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testGetAllData3() {
        // Setup
        final AllData expectedResult = new AllData(new FooData("id", "barId", "extraDataId", "name"),
                new FooExtraData("id", "content".getBytes()),
                new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0)),
                new OtherData("id", "description"));

        // Configure FooService.getFooById(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooById("fooId")).thenReturn(fooData);

        // Configure FooExtraService.getExtraData(...).
        final FooExtraData fooExtraData = new FooExtraData("id", "content".getBytes());
        when(mockFooExtraService.getExtraData("extraDataId")).thenReturn(fooExtraData);

        // Configure BarService.getBarForId(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId("barId")).thenReturn(barData);

        when(mockOtherDataService.getData("otherId")).thenReturn(new OtherData("id", "description"));

        // Run the test
        final AllData result = myClassUnderTest.getAllData3("fooId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetAllData3_FooServiceThrowsFooServiceException() {
        // Setup
        when(mockFooService.getFooById("fooId")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getAllData3("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testGetAllData3_FooExtraServiceThrowsFooExtraServiceException() {
        // Setup
        // Configure FooService.getFooById(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooById("fooId")).thenReturn(fooData);

        when(mockFooExtraService.getExtraData("extraDataId")).thenThrow(FooExtraServiceException.class);

        // Configure BarService.getBarForId(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId("barId")).thenReturn(barData);

        when(mockOtherDataService.getData("otherId")).thenReturn(new OtherData("id", "description"));

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getAllData3("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testGetAllData3_BarServiceThrowsBarServiceException() {
        // Setup
        // Configure FooService.getFooById(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooById("fooId")).thenReturn(fooData);

        // Configure FooExtraService.getExtraData(...).
        final FooExtraData fooExtraData = new FooExtraData("id", "content".getBytes());
        when(mockFooExtraService.getExtraData("extraDataId")).thenReturn(fooExtraData);

        when(mockBarService.getBarForId("barId")).thenThrow(BarServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getAllData3("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testGetAllData3_OtherServiceThrowsOtherServiceException() {
        // Setup
        // Configure FooService.getFooById(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooById("fooId")).thenReturn(fooData);

        // Configure FooExtraService.getExtraData(...).
        final FooExtraData fooExtraData = new FooExtraData("id", "content".getBytes());
        when(mockFooExtraService.getExtraData("extraDataId")).thenReturn(fooExtraData);

        // Configure BarService.getBarForId(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId("barId")).thenReturn(barData);

        when(mockOtherDataService.getData("otherId")).thenThrow(OtherServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getAllData3("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testGetAllData4() {
        // Setup
        final AllData expectedResult = new AllData(new FooData("id", "barId", "extraDataId", "name"),
                new FooExtraData("id", "content".getBytes()),
                new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0)),
                new OtherData("id", "description"));

        // Configure FooService.getFooById(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooById("fooId")).thenReturn(fooData);

        // Configure FooExtraService.getExtraDataAsync(...).
        final CompletableFuture<FooExtraData> fooExtraDataCompletableFuture = CompletableFuture.completedFuture(
                new FooExtraData("id", "content".getBytes()));
        when(mockFooExtraService.getExtraDataAsync("extraDataId")).thenReturn(fooExtraDataCompletableFuture);

        // Configure BarService.getBarForIdAsync(...).
        final CompletableFuture<BarData> barDataCompletableFuture = CompletableFuture.completedFuture(
                new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0)));
        when(mockBarService.getBarForIdAsync("barId")).thenReturn(barDataCompletableFuture);

        // Configure OtherService.getDataAsync(...).
        final CompletableFuture<OtherData> otherDataCompletableFuture = CompletableFuture.completedFuture(
                new OtherData("id", "description"));
        when(mockOtherDataService.getDataAsync("otherId")).thenReturn(otherDataCompletableFuture);

        // Run the test
        final AllData result = myClassUnderTest.getAllData4("fooId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetAllData4_FooServiceThrowsFooServiceException() {
        // Setup
        when(mockFooService.getFooById("fooId")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getAllData4("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testGetAllData4_FooExtraServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooById(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooById("fooId")).thenReturn(fooData);

        // Configure FooExtraService.getExtraDataAsync(...).
        final CompletableFuture<FooExtraData> fooExtraDataCompletableFuture = new CompletableFuture<>();
        fooExtraDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockFooExtraService.getExtraDataAsync("extraDataId")).thenReturn(fooExtraDataCompletableFuture);

        // Configure BarService.getBarForIdAsync(...).
        final CompletableFuture<BarData> barDataCompletableFuture = CompletableFuture.completedFuture(
                new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0)));
        when(mockBarService.getBarForIdAsync("barId")).thenReturn(barDataCompletableFuture);

        // Configure OtherService.getDataAsync(...).
        final CompletableFuture<OtherData> otherDataCompletableFuture = CompletableFuture.completedFuture(
                new OtherData("id", "description"));
        when(mockOtherDataService.getDataAsync("otherId")).thenReturn(otherDataCompletableFuture);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getAllData4("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testGetAllData4_BarServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooById(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooById("fooId")).thenReturn(fooData);

        // Configure FooExtraService.getExtraDataAsync(...).
        final CompletableFuture<FooExtraData> fooExtraDataCompletableFuture = CompletableFuture.completedFuture(
                new FooExtraData("id", "content".getBytes()));
        when(mockFooExtraService.getExtraDataAsync("extraDataId")).thenReturn(fooExtraDataCompletableFuture);

        // Configure BarService.getBarForIdAsync(...).
        final CompletableFuture<BarData> barDataCompletableFuture = new CompletableFuture<>();
        barDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockBarService.getBarForIdAsync("barId")).thenReturn(barDataCompletableFuture);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getAllData4("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testGetAllData4_OtherServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooById(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooById("fooId")).thenReturn(fooData);

        // Configure FooExtraService.getExtraDataAsync(...).
        final CompletableFuture<FooExtraData> fooExtraDataCompletableFuture = CompletableFuture.completedFuture(
                new FooExtraData("id", "content".getBytes()));
        when(mockFooExtraService.getExtraDataAsync("extraDataId")).thenReturn(fooExtraDataCompletableFuture);

        // Configure BarService.getBarForIdAsync(...).
        final CompletableFuture<BarData> barDataCompletableFuture = CompletableFuture.completedFuture(
                new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0)));
        when(mockBarService.getBarForIdAsync("barId")).thenReturn(barDataCompletableFuture);

        // Configure OtherService.getDataAsync(...).
        final CompletableFuture<OtherData> otherDataCompletableFuture = new CompletableFuture<>();
        otherDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockOtherDataService.getDataAsync("otherId")).thenReturn(otherDataCompletableFuture);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getAllData4("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testGetAllData5() {
        // Setup
        final AllData expectedResult = new AllData(new FooData("id", "barId", "extraDataId", "name"),
                new FooExtraData("id", "content".getBytes()),
                new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0)),
                new OtherData("id", "description"));

        // Configure FooService.getFooById(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooById("fooId")).thenReturn(fooData);

        // Configure FooExtraService.getExtraDataAsync(...).
        final CompletableFuture<FooExtraData> fooExtraDataCompletableFuture = CompletableFuture.completedFuture(
                new FooExtraData("id", "content".getBytes()));
        when(mockFooExtraService.getExtraDataAsync("extraDataId")).thenReturn(fooExtraDataCompletableFuture);

        // Configure BarService.getBarForIdAsync(...).
        final CompletableFuture<BarData> barDataCompletableFuture = CompletableFuture.completedFuture(
                new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0)));
        when(mockBarService.getBarForIdAsync("barId")).thenReturn(barDataCompletableFuture);

        // Configure OtherService.getDataAsync(...).
        final CompletableFuture<OtherData> otherDataCompletableFuture = CompletableFuture.completedFuture(
                new OtherData("id", "description"));
        when(mockOtherDataService.getDataAsync("otherId")).thenReturn(otherDataCompletableFuture);

        // Run the test
        final AllData result = myClassUnderTest.getAllData5("fooId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetAllData5_FooServiceThrowsFooServiceException() {
        // Setup
        when(mockFooService.getFooById("fooId")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getAllData5("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testGetAllData5_FooExtraServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooById(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooById("fooId")).thenReturn(fooData);

        // Configure FooExtraService.getExtraDataAsync(...).
        final CompletableFuture<FooExtraData> fooExtraDataCompletableFuture = new CompletableFuture<>();
        fooExtraDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockFooExtraService.getExtraDataAsync("extraDataId")).thenReturn(fooExtraDataCompletableFuture);

        // Configure BarService.getBarForIdAsync(...).
        final CompletableFuture<BarData> barDataCompletableFuture = CompletableFuture.completedFuture(
                new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0)));
        when(mockBarService.getBarForIdAsync("barId")).thenReturn(barDataCompletableFuture);

        // Configure OtherService.getDataAsync(...).
        final CompletableFuture<OtherData> otherDataCompletableFuture = CompletableFuture.completedFuture(
                new OtherData("id", "description"));
        when(mockOtherDataService.getDataAsync("otherId")).thenReturn(otherDataCompletableFuture);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getAllData5("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testGetAllData5_BarServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooById(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooById("fooId")).thenReturn(fooData);

        // Configure FooExtraService.getExtraDataAsync(...).
        final CompletableFuture<FooExtraData> fooExtraDataCompletableFuture = CompletableFuture.completedFuture(
                new FooExtraData("id", "content".getBytes()));
        when(mockFooExtraService.getExtraDataAsync("extraDataId")).thenReturn(fooExtraDataCompletableFuture);

        // Configure BarService.getBarForIdAsync(...).
        final CompletableFuture<BarData> barDataCompletableFuture = new CompletableFuture<>();
        barDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockBarService.getBarForIdAsync("barId")).thenReturn(barDataCompletableFuture);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getAllData5("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testGetAllData5_OtherServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooById(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooById("fooId")).thenReturn(fooData);

        // Configure FooExtraService.getExtraDataAsync(...).
        final CompletableFuture<FooExtraData> fooExtraDataCompletableFuture = CompletableFuture.completedFuture(
                new FooExtraData("id", "content".getBytes()));
        when(mockFooExtraService.getExtraDataAsync("extraDataId")).thenReturn(fooExtraDataCompletableFuture);

        // Configure BarService.getBarForIdAsync(...).
        final CompletableFuture<BarData> barDataCompletableFuture = CompletableFuture.completedFuture(
                new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0)));
        when(mockBarService.getBarForIdAsync("barId")).thenReturn(barDataCompletableFuture);

        // Configure OtherService.getDataAsync(...).
        final CompletableFuture<OtherData> otherDataCompletableFuture = new CompletableFuture<>();
        otherDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockOtherDataService.getDataAsync("otherId")).thenReturn(otherDataCompletableFuture);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getAllData5("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testGetAllData6() {
        // Setup
        final AllData expectedResult = new AllData(new FooData("id", "barId", "extraDataId", "name"),
                new FooExtraData("id", "content".getBytes()),
                new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0)),
                new OtherData("id", "description"));

        // Configure FooService.getFooById(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooById("fooId")).thenReturn(fooData);

        // Configure FooExtraService.getExtraDataAsync(...).
        final CompletableFuture<FooExtraData> fooExtraDataCompletableFuture = CompletableFuture.completedFuture(
                new FooExtraData("id", "content".getBytes()));
        when(mockFooExtraService.getExtraDataAsync("extraDataId")).thenReturn(fooExtraDataCompletableFuture);

        // Configure BarService.getBarForIdAsync(...).
        final CompletableFuture<BarData> barDataCompletableFuture = CompletableFuture.completedFuture(
                new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0)));
        when(mockBarService.getBarForIdAsync("barId")).thenReturn(barDataCompletableFuture);

        // Configure OtherService.getDataAsync(...).
        final CompletableFuture<OtherData> otherDataCompletableFuture = CompletableFuture.completedFuture(
                new OtherData("id", "description"));
        when(mockOtherDataService.getDataAsync("otherId")).thenReturn(otherDataCompletableFuture);

        // Run the test
        final AllData result = myClassUnderTest.getAllData6("fooId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetAllData6_FooServiceThrowsFooServiceException() {
        // Setup
        when(mockFooService.getFooById("fooId")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getAllData6("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testGetAllData6_FooExtraServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooById(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooById("fooId")).thenReturn(fooData);

        // Configure FooExtraService.getExtraDataAsync(...).
        final CompletableFuture<FooExtraData> fooExtraDataCompletableFuture = new CompletableFuture<>();
        fooExtraDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockFooExtraService.getExtraDataAsync("extraDataId")).thenReturn(fooExtraDataCompletableFuture);

        // Configure BarService.getBarForIdAsync(...).
        final CompletableFuture<BarData> barDataCompletableFuture = CompletableFuture.completedFuture(
                new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0)));
        when(mockBarService.getBarForIdAsync("barId")).thenReturn(barDataCompletableFuture);

        // Configure OtherService.getDataAsync(...).
        final CompletableFuture<OtherData> otherDataCompletableFuture = CompletableFuture.completedFuture(
                new OtherData("id", "description"));
        when(mockOtherDataService.getDataAsync("otherId")).thenReturn(otherDataCompletableFuture);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getAllData6("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testGetAllData6_BarServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooById(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooById("fooId")).thenReturn(fooData);

        // Configure FooExtraService.getExtraDataAsync(...).
        final CompletableFuture<FooExtraData> fooExtraDataCompletableFuture = CompletableFuture.completedFuture(
                new FooExtraData("id", "content".getBytes()));
        when(mockFooExtraService.getExtraDataAsync("extraDataId")).thenReturn(fooExtraDataCompletableFuture);

        // Configure BarService.getBarForIdAsync(...).
        final CompletableFuture<BarData> barDataCompletableFuture = new CompletableFuture<>();
        barDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockBarService.getBarForIdAsync("barId")).thenReturn(barDataCompletableFuture);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getAllData6("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testGetAllData6_OtherServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooById(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooById("fooId")).thenReturn(fooData);

        // Configure FooExtraService.getExtraDataAsync(...).
        final CompletableFuture<FooExtraData> fooExtraDataCompletableFuture = CompletableFuture.completedFuture(
                new FooExtraData("id", "content".getBytes()));
        when(mockFooExtraService.getExtraDataAsync("extraDataId")).thenReturn(fooExtraDataCompletableFuture);

        // Configure BarService.getBarForIdAsync(...).
        final CompletableFuture<BarData> barDataCompletableFuture = CompletableFuture.completedFuture(
                new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0)));
        when(mockBarService.getBarForIdAsync("barId")).thenReturn(barDataCompletableFuture);

        // Configure OtherService.getDataAsync(...).
        final CompletableFuture<OtherData> otherDataCompletableFuture = new CompletableFuture<>();
        otherDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockOtherDataService.getDataAsync("otherId")).thenReturn(otherDataCompletableFuture);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getAllData6("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testGetAllData7() {
        // Setup
        final AllData expectedResult = new AllData(new FooData("id", "barId", "extraDataId", "name"),
                new FooExtraData("id", "content".getBytes()),
                new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0)),
                new OtherData("id", "description"));

        // Configure FooService.getFooById(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooById("fooId")).thenReturn(fooData);

        // Configure FooExtraService.getExtraDataAsync(...).
        final CompletableFuture<FooExtraData> fooExtraDataCompletableFuture = CompletableFuture.completedFuture(
                new FooExtraData("id", "content".getBytes()));
        when(mockFooExtraService.getExtraDataAsync("extraDataId")).thenReturn(fooExtraDataCompletableFuture);

        // Configure BarService.getBarForIdAsync(...).
        final CompletableFuture<BarData> barDataCompletableFuture = CompletableFuture.completedFuture(
                new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0)));
        when(mockBarService.getBarForIdAsync("barId")).thenReturn(barDataCompletableFuture);

        // Configure OtherService.getDataAsync(...).
        final CompletableFuture<OtherData> otherDataCompletableFuture = CompletableFuture.completedFuture(
                new OtherData("id", "description"));
        when(mockOtherDataService.getDataAsync("otherId")).thenReturn(otherDataCompletableFuture);

        // Run the test
        final AllData result = myClassUnderTest.getAllData7("fooId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetAllData7_FooServiceThrowsFooServiceException() {
        // Setup
        when(mockFooService.getFooById("fooId")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getAllData7("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testGetAllData7_FooExtraServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooById(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooById("fooId")).thenReturn(fooData);

        // Configure FooExtraService.getExtraDataAsync(...).
        final CompletableFuture<FooExtraData> fooExtraDataCompletableFuture = new CompletableFuture<>();
        fooExtraDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockFooExtraService.getExtraDataAsync("extraDataId")).thenReturn(fooExtraDataCompletableFuture);

        // Configure BarService.getBarForIdAsync(...).
        final CompletableFuture<BarData> barDataCompletableFuture = CompletableFuture.completedFuture(
                new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0)));
        when(mockBarService.getBarForIdAsync("barId")).thenReturn(barDataCompletableFuture);

        // Configure OtherService.getDataAsync(...).
        final CompletableFuture<OtherData> otherDataCompletableFuture = CompletableFuture.completedFuture(
                new OtherData("id", "description"));
        when(mockOtherDataService.getDataAsync("otherId")).thenReturn(otherDataCompletableFuture);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getAllData7("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testGetAllData7_BarServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooById(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooById("fooId")).thenReturn(fooData);

        // Configure FooExtraService.getExtraDataAsync(...).
        final CompletableFuture<FooExtraData> fooExtraDataCompletableFuture = CompletableFuture.completedFuture(
                new FooExtraData("id", "content".getBytes()));
        when(mockFooExtraService.getExtraDataAsync("extraDataId")).thenReturn(fooExtraDataCompletableFuture);

        // Configure BarService.getBarForIdAsync(...).
        final CompletableFuture<BarData> barDataCompletableFuture = new CompletableFuture<>();
        barDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockBarService.getBarForIdAsync("barId")).thenReturn(barDataCompletableFuture);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getAllData7("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testGetAllData7_OtherServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooById(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooById("fooId")).thenReturn(fooData);

        // Configure FooExtraService.getExtraDataAsync(...).
        final CompletableFuture<FooExtraData> fooExtraDataCompletableFuture = CompletableFuture.completedFuture(
                new FooExtraData("id", "content".getBytes()));
        when(mockFooExtraService.getExtraDataAsync("extraDataId")).thenReturn(fooExtraDataCompletableFuture);

        // Configure BarService.getBarForIdAsync(...).
        final CompletableFuture<BarData> barDataCompletableFuture = CompletableFuture.completedFuture(
                new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0)));
        when(mockBarService.getBarForIdAsync("barId")).thenReturn(barDataCompletableFuture);

        // Configure OtherService.getDataAsync(...).
        final CompletableFuture<OtherData> otherDataCompletableFuture = new CompletableFuture<>();
        otherDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockOtherDataService.getDataAsync("otherId")).thenReturn(otherDataCompletableFuture);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getAllData7("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testGetFooFuture0() {
        // Setup
        // Configure FooService.getFooById(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooById("fooId")).thenReturn(fooData);

        // Run the test
        final CompletableFuture<FooData> result = myClassUnderTest.getFooFuture0("fooId");

        // Verify the results
    }

    @Test
    void testGetFooFuture0_FooServiceThrowsFooServiceException() {
        // Setup
        when(mockFooService.getFooById("fooId")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.getFooFuture0("fooId"));
    }

    @Test
    void testGetFooFuture1() {
        // Setup
        // Configure FooService.getFooByIdNullable(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooByIdNullable("fooId")).thenReturn(fooData);

        // Run the test
        final CompletableFuture<FooData> result = myClassUnderTest.getFooFuture1("fooId");

        // Verify the results
    }

    @Test
    void testGetFooFuture1_FooServiceReturnsNull() {
        // Setup
        when(mockFooService.getFooByIdNullable("fooId")).thenReturn(null);

        // Run the test
        final CompletableFuture<FooData> result = myClassUnderTest.getFooFuture1("fooId");

        // Verify the results
    }

    @Test
    void testGetFooFuture1_FooServiceThrowsFooServiceException() {
        // Setup
        when(mockFooService.getFooByIdNullable("fooId")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.getFooFuture1("fooId"));
    }

    @Test
    void testGetFooFuture2() {
        // Setup
        // Configure FooService.getFooByIdNullable(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooByIdNullable("fooId")).thenReturn(fooData);

        // Run the test
        final CompletableFuture<FooData> result = myClassUnderTest.getFooFuture2("fooId");

        // Verify the results
    }

    @Test
    void testGetFooFuture2_FooServiceReturnsNull() {
        // Setup
        when(mockFooService.getFooByIdNullable("fooId")).thenReturn(null);

        // Run the test
        final CompletableFuture<FooData> result = myClassUnderTest.getFooFuture2("fooId");

        // Verify the results
    }

    @Test
    void testGetFooFuture2_FooServiceThrowsFooServiceException() {
        // Setup
        when(mockFooService.getFooByIdNullable("fooId")).thenThrow(FooServiceException.class);

        // Run the test
        final CompletableFuture<FooData> result = myClassUnderTest.getFooFuture2("fooId");

        // Verify the results
    }

    @Test
    void testGetFooFuture3() {
        // Setup
        // Configure FooService.getFooByIdNullable(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooByIdNullable("fooId")).thenReturn(fooData);

        // Run the test
        final CompletableFuture<FooData> result = myClassUnderTest.getFooFuture3("fooId");

        // Verify the results
    }

    @Test
    void testGetFooFuture3_FooServiceReturnsNull() {
        // Setup
        when(mockFooService.getFooByIdNullable("fooId")).thenReturn(null);

        // Run the test
        final CompletableFuture<FooData> result = myClassUnderTest.getFooFuture3("fooId");

        // Verify the results
    }

    @Test
    void testGetFooFuture3_FooServiceThrowsFooServiceException() {
        // Setup
        when(mockFooService.getFooByIdNullable("fooId")).thenThrow(FooServiceException.class);

        // Run the test
        final CompletableFuture<FooData> result = myClassUnderTest.getFooFuture3("fooId");

        // Verify the results
    }

    @Test
    void testGetFooFuture4() throws Exception {
        // Setup
        final FooData expectedResult = new FooData("id", "barId", "extraDataId", "name");

        // Configure FooService.getFooByIdNullable(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooByIdNullable("fooId")).thenReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.getFooFuture4("fooId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooFuture4_FooServiceReturnsNull() throws Exception {
        // Setup
        when(mockFooService.getFooByIdNullable("fooId")).thenReturn(null);

        // Run the test
        final FooData result = myClassUnderTest.getFooFuture4("fooId");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetFooFuture4_FooServiceThrowsFooServiceException() {
        // Setup
        when(mockFooService.getFooByIdNullable("fooId")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(ExecutionException.class, () -> myClassUnderTest.getFooFuture4("fooId"));
    }

    @Test
    void testGetFooFuture5() throws Exception {
        // Setup
        final FooData expectedResult = new FooData("id", "barId", "extraDataId", "name");

        // Configure FooService.getFooByIdNullable(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooByIdNullable("fooId")).thenReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.getFooFuture5("fooId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooFuture5_FooServiceReturnsNull() throws Exception {
        // Setup
        final FooData expectedResult = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooByIdNullable("fooId")).thenReturn(null);

        // Run the test
        final FooData result = myClassUnderTest.getFooFuture5("fooId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooFuture5_FooServiceThrowsFooServiceException() throws Exception {
        // Setup
        final FooData expectedResult = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooByIdNullable("fooId")).thenThrow(FooServiceException.class);

        // Run the test
        final FooData result = myClassUnderTest.getFooFuture5("fooId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testVerifyAllData1() {
        // Setup
        // Configure FooService.getFooById(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooById("fooId")).thenReturn(fooData);

        // Configure FooExtraService.getExtraData(...).
        final FooExtraData fooExtraData = new FooExtraData("id", "content".getBytes());
        when(mockFooExtraService.getExtraData("extraDataId")).thenReturn(fooExtraData);

        // Configure BarService.getBarForId(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId("barId")).thenReturn(barData);

        when(mockOtherDataService.getData("otherId")).thenReturn(new OtherData("id", "description"));

        // Run the test
        myClassUnderTest.verifyAllData1("fooId");

        // Verify the results
    }

    @Test
    void testVerifyAllData1_FooServiceThrowsFooServiceException() {
        // Setup
        when(mockFooService.getFooById("fooId")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.verifyAllData1("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testVerifyAllData1_FooExtraServiceThrowsFooExtraServiceException() {
        // Setup
        // Configure FooService.getFooById(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooById("fooId")).thenReturn(fooData);

        when(mockFooExtraService.getExtraData("extraDataId")).thenThrow(FooExtraServiceException.class);

        // Configure BarService.getBarForId(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId("barId")).thenReturn(barData);

        when(mockOtherDataService.getData("otherId")).thenReturn(new OtherData("id", "description"));

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.verifyAllData1("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testVerifyAllData1_BarServiceThrowsBarServiceException() {
        // Setup
        // Configure FooService.getFooById(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooById("fooId")).thenReturn(fooData);

        // Configure FooExtraService.getExtraData(...).
        final FooExtraData fooExtraData = new FooExtraData("id", "content".getBytes());
        when(mockFooExtraService.getExtraData("extraDataId")).thenReturn(fooExtraData);

        when(mockBarService.getBarForId("barId")).thenThrow(BarServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.verifyAllData1("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testVerifyAllData1_OtherServiceThrowsOtherServiceException() {
        // Setup
        // Configure FooService.getFooById(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooById("fooId")).thenReturn(fooData);

        // Configure FooExtraService.getExtraData(...).
        final FooExtraData fooExtraData = new FooExtraData("id", "content".getBytes());
        when(mockFooExtraService.getExtraData("extraDataId")).thenReturn(fooExtraData);

        // Configure BarService.getBarForId(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId("barId")).thenReturn(barData);

        when(mockOtherDataService.getData("otherId")).thenThrow(OtherServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.verifyAllData1("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testVerifyAllData2() {
        // Setup
        // Configure FooService.getFooById(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooById("fooId")).thenReturn(fooData);

        // Configure FooExtraService.getExtraData(...).
        final FooExtraData fooExtraData = new FooExtraData("id", "content".getBytes());
        when(mockFooExtraService.getExtraData("extraDataId")).thenReturn(fooExtraData);

        // Configure BarService.getBarForId(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId("barId")).thenReturn(barData);

        when(mockOtherDataService.getData("otherId")).thenReturn(new OtherData("id", "description"));

        // Run the test
        myClassUnderTest.verifyAllData2("fooId");

        // Verify the results
    }

    @Test
    void testVerifyAllData2_FooServiceThrowsFooServiceException() {
        // Setup
        when(mockFooService.getFooById("fooId")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.verifyAllData2("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testVerifyAllData2_FooExtraServiceThrowsFooExtraServiceException() {
        // Setup
        // Configure FooService.getFooById(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooById("fooId")).thenReturn(fooData);

        when(mockFooExtraService.getExtraData("extraDataId")).thenThrow(FooExtraServiceException.class);

        // Configure BarService.getBarForId(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId("barId")).thenReturn(barData);

        when(mockOtherDataService.getData("otherId")).thenReturn(new OtherData("id", "description"));

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.verifyAllData2("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testVerifyAllData2_BarServiceThrowsBarServiceException() {
        // Setup
        // Configure FooService.getFooById(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooById("fooId")).thenReturn(fooData);

        // Configure FooExtraService.getExtraData(...).
        final FooExtraData fooExtraData = new FooExtraData("id", "content".getBytes());
        when(mockFooExtraService.getExtraData("extraDataId")).thenReturn(fooExtraData);

        when(mockBarService.getBarForId("barId")).thenThrow(BarServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.verifyAllData2("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testVerifyAllData2_OtherServiceThrowsOtherServiceException() {
        // Setup
        // Configure FooService.getFooById(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooById("fooId")).thenReturn(fooData);

        // Configure FooExtraService.getExtraData(...).
        final FooExtraData fooExtraData = new FooExtraData("id", "content".getBytes());
        when(mockFooExtraService.getExtraData("extraDataId")).thenReturn(fooExtraData);

        // Configure BarService.getBarForId(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId("barId")).thenReturn(barData);

        when(mockOtherDataService.getData("otherId")).thenThrow(OtherServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.verifyAllData2("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testVerifyAllData3() {
        // Setup
        // Configure FooService.getFooById(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooById("fooId")).thenReturn(fooData);

        // Configure FooExtraService.getExtraData(...).
        final FooExtraData fooExtraData = new FooExtraData("id", "content".getBytes());
        when(mockFooExtraService.getExtraData("extraDataId")).thenReturn(fooExtraData);

        // Configure BarService.getBarForId(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId("barId")).thenReturn(barData);

        when(mockOtherDataService.getData("otherId")).thenReturn(new OtherData("id", "description"));

        // Run the test
        myClassUnderTest.verifyAllData3("fooId");

        // Verify the results
    }

    @Test
    void testVerifyAllData3_FooServiceThrowsFooServiceException() {
        // Setup
        when(mockFooService.getFooById("fooId")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.verifyAllData3("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testVerifyAllData3_FooExtraServiceThrowsFooExtraServiceException() {
        // Setup
        // Configure FooService.getFooById(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooById("fooId")).thenReturn(fooData);

        when(mockFooExtraService.getExtraData("extraDataId")).thenThrow(FooExtraServiceException.class);

        // Configure BarService.getBarForId(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId("barId")).thenReturn(barData);

        when(mockOtherDataService.getData("otherId")).thenReturn(new OtherData("id", "description"));

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.verifyAllData3("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testVerifyAllData3_BarServiceThrowsBarServiceException() {
        // Setup
        // Configure FooService.getFooById(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooById("fooId")).thenReturn(fooData);

        // Configure FooExtraService.getExtraData(...).
        final FooExtraData fooExtraData = new FooExtraData("id", "content".getBytes());
        when(mockFooExtraService.getExtraData("extraDataId")).thenReturn(fooExtraData);

        when(mockBarService.getBarForId("barId")).thenThrow(BarServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.verifyAllData3("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testVerifyAllData3_OtherServiceThrowsOtherServiceException() {
        // Setup
        // Configure FooService.getFooById(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooById("fooId")).thenReturn(fooData);

        // Configure FooExtraService.getExtraData(...).
        final FooExtraData fooExtraData = new FooExtraData("id", "content".getBytes());
        when(mockFooExtraService.getExtraData("extraDataId")).thenReturn(fooExtraData);

        // Configure BarService.getBarForId(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId("barId")).thenReturn(barData);

        when(mockOtherDataService.getData("otherId")).thenThrow(OtherServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.verifyAllData3("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testVerifyAllData4() {
        // Setup
        // Configure FooService.getFooById(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooById("fooId")).thenReturn(fooData);

        // Configure FooExtraService.getExtraData(...).
        final FooExtraData fooExtraData = new FooExtraData("id", "content".getBytes());
        when(mockFooExtraService.getExtraData("extraDataId")).thenReturn(fooExtraData);

        // Configure BarService.getBarForId(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId("barId")).thenReturn(barData);

        when(mockOtherDataService.getData("otherId")).thenReturn(new OtherData("id", "description"));

        // Run the test
        myClassUnderTest.verifyAllData4("fooId");

        // Verify the results
    }

    @Test
    void testVerifyAllData4_FooServiceThrowsFooServiceException() {
        // Setup
        when(mockFooService.getFooById("fooId")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(CompletionException.class, () -> myClassUnderTest.verifyAllData4("fooId"));
    }

    @Test
    void testVerifyAllData4_FooExtraServiceThrowsFooExtraServiceException() {
        // Setup
        // Configure FooService.getFooById(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooById("fooId")).thenReturn(fooData);

        when(mockFooExtraService.getExtraData("extraDataId")).thenThrow(FooExtraServiceException.class);

        // Configure BarService.getBarForId(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId("barId")).thenReturn(barData);

        when(mockOtherDataService.getData("otherId")).thenReturn(new OtherData("id", "description"));

        // Run the test
        assertThrows(CompletionException.class, () -> myClassUnderTest.verifyAllData4("fooId"));
    }

    @Test
    void testVerifyAllData4_BarServiceThrowsBarServiceException() {
        // Setup
        // Configure FooService.getFooById(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooById("fooId")).thenReturn(fooData);

        // Configure FooExtraService.getExtraData(...).
        final FooExtraData fooExtraData = new FooExtraData("id", "content".getBytes());
        when(mockFooExtraService.getExtraData("extraDataId")).thenReturn(fooExtraData);

        when(mockBarService.getBarForId("barId")).thenThrow(BarServiceException.class);

        // Run the test
        assertThrows(CompletionException.class, () -> myClassUnderTest.verifyAllData4("fooId"));
    }

    @Test
    void testVerifyAllData4_OtherServiceThrowsOtherServiceException() {
        // Setup
        // Configure FooService.getFooById(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooById("fooId")).thenReturn(fooData);

        // Configure FooExtraService.getExtraData(...).
        final FooExtraData fooExtraData = new FooExtraData("id", "content".getBytes());
        when(mockFooExtraService.getExtraData("extraDataId")).thenReturn(fooExtraData);

        // Configure BarService.getBarForId(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId("barId")).thenReturn(barData);

        when(mockOtherDataService.getData("otherId")).thenThrow(OtherServiceException.class);

        // Run the test
        assertThrows(CompletionException.class, () -> myClassUnderTest.verifyAllData4("fooId"));
    }

    @Test
    void testVerifyAllData5() {
        // Setup
        // Configure FooService.getFooById(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooById("fooId")).thenReturn(fooData);

        // Configure FooExtraService.getExtraData(...).
        final FooExtraData fooExtraData = new FooExtraData("id", "content".getBytes());
        when(mockFooExtraService.getExtraData("extraDataId")).thenReturn(fooExtraData);

        // Configure BarService.getBarForId(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId("barId")).thenReturn(barData);

        when(mockOtherDataService.getData("otherId")).thenReturn(new OtherData("id", "description"));

        // Run the test
        myClassUnderTest.verifyAllData5("fooId");

        // Verify the results
    }

    @Test
    void testVerifyAllData5_FooServiceThrowsFooServiceException() {
        // Setup
        when(mockFooService.getFooById("fooId")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(CompletionException.class, () -> myClassUnderTest.verifyAllData5("fooId"));
    }

    @Test
    void testVerifyAllData5_FooExtraServiceThrowsFooExtraServiceException() {
        // Setup
        // Configure FooService.getFooById(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooById("fooId")).thenReturn(fooData);

        when(mockFooExtraService.getExtraData("extraDataId")).thenThrow(FooExtraServiceException.class);

        // Configure BarService.getBarForId(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId("barId")).thenReturn(barData);

        when(mockOtherDataService.getData("otherId")).thenReturn(new OtherData("id", "description"));

        // Run the test
        assertThrows(CompletionException.class, () -> myClassUnderTest.verifyAllData5("fooId"));
    }

    @Test
    void testVerifyAllData5_BarServiceThrowsBarServiceException() {
        // Setup
        // Configure FooService.getFooById(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooById("fooId")).thenReturn(fooData);

        // Configure FooExtraService.getExtraData(...).
        final FooExtraData fooExtraData = new FooExtraData("id", "content".getBytes());
        when(mockFooExtraService.getExtraData("extraDataId")).thenReturn(fooExtraData);

        when(mockBarService.getBarForId("barId")).thenThrow(BarServiceException.class);

        // Run the test
        assertThrows(CompletionException.class, () -> myClassUnderTest.verifyAllData5("fooId"));
    }

    @Test
    void testVerifyAllData5_OtherServiceThrowsOtherServiceException() {
        // Setup
        // Configure FooService.getFooById(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooById("fooId")).thenReturn(fooData);

        // Configure FooExtraService.getExtraData(...).
        final FooExtraData fooExtraData = new FooExtraData("id", "content".getBytes());
        when(mockFooExtraService.getExtraData("extraDataId")).thenReturn(fooExtraData);

        // Configure BarService.getBarForId(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId("barId")).thenReturn(barData);

        when(mockOtherDataService.getData("otherId")).thenThrow(OtherServiceException.class);

        // Run the test
        assertThrows(CompletionException.class, () -> myClassUnderTest.verifyAllData5("fooId"));
    }
}
