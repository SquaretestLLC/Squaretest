package com.myapp;

import com.google.common.util.concurrent.MoreExecutors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

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
    private OtherService mockOtherDataService;
    @Mock
    private MetricsService mockMetricsService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(MoreExecutors.newDirectExecutorService(), mockFooService, mockBarService,
                mockOtherDataService, mockMetricsService);
    }

    @Test
    void testGetFooAndBar1() {
        // Setup
        final FooAndBarData expectedResult = new FooAndBarData(new FooData("id", "barId", "extraDataId", "name"),
                new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0)));

        // Configure FooService.getFooByIdAsync2(...).
        final CompletableFuture<Optional<FooData>> optionalCompletableFuture = CompletableFuture.completedFuture(
                Optional.of(new FooData("id", "barId", "extraDataId", "name")));
        when(mockFooService.getFooByIdAsync2("fooId")).thenReturn(optionalCompletableFuture);

        // Configure BarService.getBarForId1(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId1("barId")).thenReturn(barData);

        // Run the test
        final FooAndBarData result = myClassUnderTest.getFooAndBar1("fooId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooAndBar1_FooServiceReturnsNoItem() {
        // Setup
        final FooAndBarData expectedResult = new FooAndBarData(new FooData("id", "barId", "extraDataId", "name"),
                new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0)));
        when(mockFooService.getFooByIdAsync2("fooId")).thenReturn(CompletableFuture.completedFuture(Optional.empty()));

        // Configure BarService.getBarForId1(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId1("barId")).thenReturn(barData);

        // Run the test
        final FooAndBarData result = myClassUnderTest.getFooAndBar1("fooId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooAndBar1_FooServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooByIdAsync2(...).
        final CompletableFuture<Optional<FooData>> optionalCompletableFuture = new CompletableFuture<>();
        optionalCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockFooService.getFooByIdAsync2("fooId")).thenReturn(optionalCompletableFuture);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar1("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testGetFooAndBar1_BarServiceThrowsBarServiceException() {
        // Setup
        // Configure FooService.getFooByIdAsync2(...).
        final CompletableFuture<Optional<FooData>> optionalCompletableFuture = CompletableFuture.completedFuture(
                Optional.of(new FooData("id", "barId", "extraDataId", "name")));
        when(mockFooService.getFooByIdAsync2("fooId")).thenReturn(optionalCompletableFuture);

        when(mockBarService.getBarForId1("barId")).thenThrow(BarServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar1("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testGetFooAndBar2() {
        // Setup
        final List<FooAndBarData> expectedResult = Arrays.asList(
                new FooAndBarData(new FooData("id", "barId", "extraDataId", "name"),
                        new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0))));

        // Configure FooService.getFooByIdAsync3(...).
        final CompletableFuture<List<FooData>> listCompletableFuture = CompletableFuture.completedFuture(
                Arrays.asList(new FooData("id", "barId", "extraDataId", "name")));
        when(mockFooService.getFooByIdAsync3("fooId")).thenReturn(listCompletableFuture);

        // Configure BarService.getBarForId1(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId1("barId")).thenReturn(barData);

        // Run the test
        final List<FooAndBarData> result = myClassUnderTest.getFooAndBar2("fooId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooAndBar2_FooServiceReturnsNoItems() {
        // Setup
        // Configure FooService.getFooByIdAsync3(...).
        final CompletableFuture<List<FooData>> listCompletableFuture = CompletableFuture.completedFuture(
                Collections.emptyList());
        when(mockFooService.getFooByIdAsync3("fooId")).thenReturn(listCompletableFuture);

        // Configure BarService.getBarForId1(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId1("barId")).thenReturn(barData);

        // Run the test
        final List<FooAndBarData> result = myClassUnderTest.getFooAndBar2("fooId");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetFooAndBar2_FooServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooByIdAsync3(...).
        final CompletableFuture<List<FooData>> listCompletableFuture = new CompletableFuture<>();
        listCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockFooService.getFooByIdAsync3("fooId")).thenReturn(listCompletableFuture);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar2("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testGetFooAndBar2_BarServiceThrowsBarServiceException() {
        // Setup
        // Configure FooService.getFooByIdAsync3(...).
        final CompletableFuture<List<FooData>> listCompletableFuture = CompletableFuture.completedFuture(
                Arrays.asList(new FooData("id", "barId", "extraDataId", "name")));
        when(mockFooService.getFooByIdAsync3("fooId")).thenReturn(listCompletableFuture);

        when(mockBarService.getBarForId1("barId")).thenThrow(BarServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar2("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testGetFooAndBar3() {
        // Setup
        final List<FooAndBarData> expectedResult = Arrays.asList(
                new FooAndBarData(new FooData("id", "barId", "extraDataId", "name"),
                        new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0))));

        // Configure FooService.getFooByIdAsync3(...).
        final CompletableFuture<List<FooData>> listCompletableFuture = CompletableFuture.completedFuture(
                Arrays.asList(new FooData("id", "barId", "extraDataId", "name")));
        when(mockFooService.getFooByIdAsync3("fooId")).thenReturn(listCompletableFuture);

        // Configure BarService.getBarForId2(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId2("barId")).thenReturn(barData);

        // Run the test
        final List<FooAndBarData> result = myClassUnderTest.getFooAndBar3("fooId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooAndBar3_FooServiceReturnsNoItems() {
        // Setup
        // Configure FooService.getFooByIdAsync3(...).
        final CompletableFuture<List<FooData>> listCompletableFuture = CompletableFuture.completedFuture(
                Collections.emptyList());
        when(mockFooService.getFooByIdAsync3("fooId")).thenReturn(listCompletableFuture);

        // Configure BarService.getBarForId2(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId2("barId")).thenReturn(barData);

        // Run the test
        final List<FooAndBarData> result = myClassUnderTest.getFooAndBar3("fooId");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetFooAndBar3_FooServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooByIdAsync3(...).
        final CompletableFuture<List<FooData>> listCompletableFuture = new CompletableFuture<>();
        listCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockFooService.getFooByIdAsync3("fooId")).thenReturn(listCompletableFuture);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar3("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testGetFooAndBar3_BarServiceReturnsNull() {
        // Setup
        // Configure FooService.getFooByIdAsync3(...).
        final CompletableFuture<List<FooData>> listCompletableFuture = CompletableFuture.completedFuture(
                Arrays.asList(new FooData("id", "barId", "extraDataId", "name")));
        when(mockFooService.getFooByIdAsync3("fooId")).thenReturn(listCompletableFuture);

        when(mockBarService.getBarForId2("barId")).thenReturn(null);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar3("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testGetFooAndBar3_BarServiceThrowsBarServiceException() {
        // Setup
        // Configure FooService.getFooByIdAsync3(...).
        final CompletableFuture<List<FooData>> listCompletableFuture = CompletableFuture.completedFuture(
                Arrays.asList(new FooData("id", "barId", "extraDataId", "name")));
        when(mockFooService.getFooByIdAsync3("fooId")).thenReturn(listCompletableFuture);

        when(mockBarService.getBarForId2("barId")).thenThrow(BarServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar3("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testGetFooAndBar4() {
        // Setup
        final Optional<List<FooAndBarData>> expectedResult = Optional.of(Arrays.asList(
                new FooAndBarData(new FooData("id", "barId", "extraDataId", "name"),
                        new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0)))));

        // Configure FooService.getFooByIdAsync4(...).
        final CompletableFuture<Optional<List<FooData>>> optionalCompletableFuture = CompletableFuture.completedFuture(
                Optional.of(Arrays.asList(new FooData("id", "barId", "extraDataId", "name"))));
        when(mockFooService.getFooByIdAsync4("fooId")).thenReturn(optionalCompletableFuture);

        // Configure BarService.getBarForId2(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId2("barId")).thenReturn(barData);

        // Run the test
        final Optional<List<FooAndBarData>> result = myClassUnderTest.getFooAndBar4("fooId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooAndBar4_FooServiceReturnsNoItem() {
        // Setup
        final Optional<List<FooAndBarData>> expectedResult = Optional.of(Arrays.asList(
                new FooAndBarData(new FooData("id", "barId", "extraDataId", "name"),
                        new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0)))));
        when(mockFooService.getFooByIdAsync4("fooId")).thenReturn(CompletableFuture.completedFuture(Optional.empty()));

        // Configure BarService.getBarForId2(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId2("barId")).thenReturn(barData);

        // Run the test
        final Optional<List<FooAndBarData>> result = myClassUnderTest.getFooAndBar4("fooId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooAndBar4_FooServiceReturnsNoItems() {
        // Setup
        // Configure FooService.getFooByIdAsync4(...).
        final CompletableFuture<Optional<List<FooData>>> optionalCompletableFuture = CompletableFuture.completedFuture(
                Optional.of(Collections.emptyList()));
        when(mockFooService.getFooByIdAsync4("fooId")).thenReturn(optionalCompletableFuture);

        // Configure BarService.getBarForId2(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId2("barId")).thenReturn(barData);

        // Run the test
        final Optional<List<FooAndBarData>> result = myClassUnderTest.getFooAndBar4("fooId");

        // Verify the results
        assertEquals(Optional.of(Collections.emptyList()), result);
    }

    @Test
    void testGetFooAndBar4_FooServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooByIdAsync4(...).
        final CompletableFuture<Optional<List<FooData>>> optionalCompletableFuture = new CompletableFuture<>();
        optionalCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockFooService.getFooByIdAsync4("fooId")).thenReturn(optionalCompletableFuture);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar4("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testGetFooAndBar4_BarServiceReturnsNull() {
        // Setup
        // Configure FooService.getFooByIdAsync4(...).
        final CompletableFuture<Optional<List<FooData>>> optionalCompletableFuture = CompletableFuture.completedFuture(
                Optional.of(Arrays.asList(new FooData("id", "barId", "extraDataId", "name"))));
        when(mockFooService.getFooByIdAsync4("fooId")).thenReturn(optionalCompletableFuture);

        when(mockBarService.getBarForId2("barId")).thenReturn(null);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar4("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testGetFooAndBar4_BarServiceThrowsBarServiceException() {
        // Setup
        // Configure FooService.getFooByIdAsync4(...).
        final CompletableFuture<Optional<List<FooData>>> optionalCompletableFuture = CompletableFuture.completedFuture(
                Optional.of(Arrays.asList(new FooData("id", "barId", "extraDataId", "name"))));
        when(mockFooService.getFooByIdAsync4("fooId")).thenReturn(optionalCompletableFuture);

        when(mockBarService.getBarForId2("barId")).thenThrow(BarServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar4("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testGetFooAndBar5() {
        // Setup
        final Optional<List<FooAndBarData>> expectedResult = Optional.of(Arrays.asList(
                new FooAndBarData(new FooData("id", "barId", "extraDataId", "name"),
                        new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0)))));

        // Configure FooService.getFooByIdAsync4(...).
        final CompletableFuture<Optional<List<FooData>>> optionalCompletableFuture = CompletableFuture.completedFuture(
                Optional.of(Arrays.asList(new FooData("id", "barId", "extraDataId", "name"))));
        when(mockFooService.getFooByIdAsync4("fooId")).thenReturn(optionalCompletableFuture);

        // Configure BarService.getBarForId2(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId2("barId")).thenReturn(barData);

        // Run the test
        final Optional<List<FooAndBarData>> result = myClassUnderTest.getFooAndBar5("fooId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooAndBar5_FooServiceReturnsNoItem() {
        // Setup
        final Optional<List<FooAndBarData>> expectedResult = Optional.of(Arrays.asList(
                new FooAndBarData(new FooData("id", "barId", "extraDataId", "name"),
                        new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0)))));
        when(mockFooService.getFooByIdAsync4("fooId")).thenReturn(CompletableFuture.completedFuture(Optional.empty()));

        // Configure BarService.getBarForId2(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId2("barId")).thenReturn(barData);

        // Run the test
        final Optional<List<FooAndBarData>> result = myClassUnderTest.getFooAndBar5("fooId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooAndBar5_FooServiceReturnsNoItems() {
        // Setup
        // Configure FooService.getFooByIdAsync4(...).
        final CompletableFuture<Optional<List<FooData>>> optionalCompletableFuture = CompletableFuture.completedFuture(
                Optional.of(Collections.emptyList()));
        when(mockFooService.getFooByIdAsync4("fooId")).thenReturn(optionalCompletableFuture);

        // Configure BarService.getBarForId2(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId2("barId")).thenReturn(barData);

        // Run the test
        final Optional<List<FooAndBarData>> result = myClassUnderTest.getFooAndBar5("fooId");

        // Verify the results
        assertEquals(Optional.of(Collections.emptyList()), result);
    }

    @Test
    void testGetFooAndBar5_FooServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooByIdAsync4(...).
        final CompletableFuture<Optional<List<FooData>>> optionalCompletableFuture = new CompletableFuture<>();
        optionalCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockFooService.getFooByIdAsync4("fooId")).thenReturn(optionalCompletableFuture);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar5("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testGetFooAndBar5_BarServiceReturnsNull() {
        // Setup
        // Configure FooService.getFooByIdAsync4(...).
        final CompletableFuture<Optional<List<FooData>>> optionalCompletableFuture = CompletableFuture.completedFuture(
                Optional.of(Arrays.asList(new FooData("id", "barId", "extraDataId", "name"))));
        when(mockFooService.getFooByIdAsync4("fooId")).thenReturn(optionalCompletableFuture);

        when(mockBarService.getBarForId2("barId")).thenReturn(null);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar5("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testGetFooAndBar5_BarServiceThrowsBarServiceException() {
        // Setup
        // Configure FooService.getFooByIdAsync4(...).
        final CompletableFuture<Optional<List<FooData>>> optionalCompletableFuture = CompletableFuture.completedFuture(
                Optional.of(Arrays.asList(new FooData("id", "barId", "extraDataId", "name"))));
        when(mockFooService.getFooByIdAsync4("fooId")).thenReturn(optionalCompletableFuture);

        when(mockBarService.getBarForId2("barId")).thenThrow(BarServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar5("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testGetFooAndBar6() {
        // Setup
        final BarData expectedResult = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure BarService.getBarForIdAsync(...).
        final CompletableFuture<BarData> barDataCompletableFuture = CompletableFuture.completedFuture(
                new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0)));
        when(mockBarService.getBarForIdAsync("barId")).thenReturn(barDataCompletableFuture);

        // Run the test
        final BarData result = myClassUnderTest.getFooAndBar6("fooId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooAndBar6_FooServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = new CompletableFuture<>();
        fooDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar6("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testGetFooAndBar6_BarServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure BarService.getBarForIdAsync(...).
        final CompletableFuture<BarData> barDataCompletableFuture = new CompletableFuture<>();
        barDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockBarService.getBarForIdAsync("barId")).thenReturn(barDataCompletableFuture);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar6("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testGetFooAndBar7() {
        // Setup
        final OtherData expectedResult = new OtherData("id", "description");

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure BarService.getBarForIdAsync(...).
        final CompletableFuture<BarData> barDataCompletableFuture = CompletableFuture.completedFuture(
                new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0)));
        when(mockBarService.getBarForIdAsync("barId")).thenReturn(barDataCompletableFuture);

        // Configure OtherService.getDataAsync(...).
        final CompletableFuture<OtherData> otherDataCompletableFuture = CompletableFuture.completedFuture(
                new OtherData("id", "description"));
        when(mockOtherDataService.getDataAsync("otherId")).thenReturn(otherDataCompletableFuture);

        // Run the test
        final OtherData result = myClassUnderTest.getFooAndBar7("fooId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooAndBar7_FooServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = new CompletableFuture<>();
        fooDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar7("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testGetFooAndBar7_BarServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure BarService.getBarForIdAsync(...).
        final CompletableFuture<BarData> barDataCompletableFuture = new CompletableFuture<>();
        barDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockBarService.getBarForIdAsync("barId")).thenReturn(barDataCompletableFuture);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar7("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testGetFooAndBar7_OtherServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure BarService.getBarForIdAsync(...).
        final CompletableFuture<BarData> barDataCompletableFuture = CompletableFuture.completedFuture(
                new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0)));
        when(mockBarService.getBarForIdAsync("barId")).thenReturn(barDataCompletableFuture);

        // Configure OtherService.getDataAsync(...).
        final CompletableFuture<OtherData> otherDataCompletableFuture = new CompletableFuture<>();
        otherDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockOtherDataService.getDataAsync("otherId")).thenReturn(otherDataCompletableFuture);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar7("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testGetFooAndBar8() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure BarService.getBarForIdAsync(...).
        final CompletableFuture<BarData> barDataCompletableFuture = CompletableFuture.completedFuture(
                new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0)));
        when(mockBarService.getBarForIdAsync("barId")).thenReturn(barDataCompletableFuture);

        // Configure OtherService.getDataAsync(...).
        final CompletableFuture<OtherData> otherDataCompletableFuture = CompletableFuture.completedFuture(
                new OtherData("id", "description"));
        when(mockOtherDataService.getDataAsync("otherId")).thenReturn(otherDataCompletableFuture);

        // Run the test
        final CompletableFuture<OtherData> result = myClassUnderTest.getFooAndBar8("fooId");

        // Verify the results
    }

    @Test
    void testGetFooAndBar8_FooServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = new CompletableFuture<>();
        fooDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Run the test
        final CompletableFuture<OtherData> result = myClassUnderTest.getFooAndBar8("fooId");

        // Verify the results
    }

    @Test
    void testGetFooAndBar8_BarServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure BarService.getBarForIdAsync(...).
        final CompletableFuture<BarData> barDataCompletableFuture = new CompletableFuture<>();
        barDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockBarService.getBarForIdAsync("barId")).thenReturn(barDataCompletableFuture);

        // Run the test
        final CompletableFuture<OtherData> result = myClassUnderTest.getFooAndBar8("fooId");

        // Verify the results
    }

    @Test
    void testGetFooAndBar8_OtherServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure BarService.getBarForIdAsync(...).
        final CompletableFuture<BarData> barDataCompletableFuture = CompletableFuture.completedFuture(
                new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0)));
        when(mockBarService.getBarForIdAsync("barId")).thenReturn(barDataCompletableFuture);

        // Configure OtherService.getDataAsync(...).
        final CompletableFuture<OtherData> otherDataCompletableFuture = new CompletableFuture<>();
        otherDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockOtherDataService.getDataAsync("otherId")).thenReturn(otherDataCompletableFuture);

        // Run the test
        final CompletableFuture<OtherData> result = myClassUnderTest.getFooAndBar8("fooId");

        // Verify the results
    }

    @Test
    void testGetFooAndBar9() {
        // Setup
        final OtherData expectedResult = new OtherData("id", "description");

        // Configure FooService.getFooById2(...).
        final Optional<FooData> fooData = Optional.of(new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooById2("fooId")).thenReturn(fooData);

        // Configure BarService.getBarForId3(...).
        final Optional<BarData> barData = Optional.of(
                new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0)));
        when(mockBarService.getBarForId3("barId")).thenReturn(barData);

        // Configure OtherService.getData2(...).
        final Optional<OtherData> otherData = Optional.of(new OtherData("id", "description"));
        when(mockOtherDataService.getData2("otherId")).thenReturn(otherData);

        // Run the test
        final OtherData result = myClassUnderTest.getFooAndBar9("fooId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooAndBar9_FooServiceReturnsAbsent() {
        // Setup
        when(mockFooService.getFooById2("fooId")).thenReturn(Optional.empty());

        // Run the test
        final OtherData result = myClassUnderTest.getFooAndBar9("fooId");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetFooAndBar9_FooServiceThrowsFooServiceException() {
        // Setup
        when(mockFooService.getFooById2("fooId")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.getFooAndBar9("fooId"));
    }

    @Test
    void testGetFooAndBar9_BarServiceReturnsAbsent() {
        // Setup
        // Configure FooService.getFooById2(...).
        final Optional<FooData> fooData = Optional.of(new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooById2("fooId")).thenReturn(fooData);

        when(mockBarService.getBarForId3("barId")).thenReturn(Optional.empty());

        // Run the test
        final OtherData result = myClassUnderTest.getFooAndBar9("fooId");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetFooAndBar9_BarServiceThrowsBarServiceException() {
        // Setup
        // Configure FooService.getFooById2(...).
        final Optional<FooData> fooData = Optional.of(new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooById2("fooId")).thenReturn(fooData);

        when(mockBarService.getBarForId3("barId")).thenThrow(BarServiceException.class);

        // Run the test
        assertThrows(BarServiceException.class, () -> myClassUnderTest.getFooAndBar9("fooId"));
    }

    @Test
    void testGetFooAndBar9_OtherServiceReturnsAbsent() {
        // Setup
        // Configure FooService.getFooById2(...).
        final Optional<FooData> fooData = Optional.of(new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooById2("fooId")).thenReturn(fooData);

        // Configure BarService.getBarForId3(...).
        final Optional<BarData> barData = Optional.of(
                new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0)));
        when(mockBarService.getBarForId3("barId")).thenReturn(barData);

        when(mockOtherDataService.getData2("otherId")).thenReturn(Optional.empty());

        // Run the test
        final OtherData result = myClassUnderTest.getFooAndBar9("fooId");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetFooAndBar9_OtherServiceThrowsOtherServiceException() {
        // Setup
        // Configure FooService.getFooById2(...).
        final Optional<FooData> fooData = Optional.of(new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooById2("fooId")).thenReturn(fooData);

        // Configure BarService.getBarForId3(...).
        final Optional<BarData> barData = Optional.of(
                new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0)));
        when(mockBarService.getBarForId3("barId")).thenReturn(barData);

        when(mockOtherDataService.getData2("otherId")).thenThrow(OtherServiceException.class);

        // Run the test
        assertThrows(OtherServiceException.class, () -> myClassUnderTest.getFooAndBar9("fooId"));
    }

    @Test
    void testGetFooAndBar10() {
        // Setup
        final OtherData expectedResult = new OtherData("id", "description");

        // Configure FooService.getFooByIdNullable(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooByIdNullable("fooId")).thenReturn(fooData);

        // Configure BarService.getBarForId2(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId2("barId")).thenReturn(barData);

        when(mockOtherDataService.getData3("otherId")).thenReturn(new OtherData("id", "description"));

        // Run the test
        final OtherData result = myClassUnderTest.getFooAndBar10("fooId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooAndBar10_FooServiceReturnsNull() {
        // Setup
        when(mockFooService.getFooByIdNullable("fooId")).thenReturn(null);

        // Run the test
        final OtherData result = myClassUnderTest.getFooAndBar10("fooId");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetFooAndBar10_FooServiceThrowsFooServiceException() {
        // Setup
        when(mockFooService.getFooByIdNullable("fooId")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.getFooAndBar10("fooId"));
    }

    @Test
    void testGetFooAndBar10_BarServiceReturnsNull() {
        // Setup
        // Configure FooService.getFooByIdNullable(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooByIdNullable("fooId")).thenReturn(fooData);

        when(mockBarService.getBarForId2("barId")).thenReturn(null);

        // Run the test
        final OtherData result = myClassUnderTest.getFooAndBar10("fooId");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetFooAndBar10_BarServiceThrowsBarServiceException() {
        // Setup
        // Configure FooService.getFooByIdNullable(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooByIdNullable("fooId")).thenReturn(fooData);

        when(mockBarService.getBarForId2("barId")).thenThrow(BarServiceException.class);

        // Run the test
        assertThrows(BarServiceException.class, () -> myClassUnderTest.getFooAndBar10("fooId"));
    }

    @Test
    void testGetFooAndBar10_OtherServiceReturnsNull() {
        // Setup
        // Configure FooService.getFooByIdNullable(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooByIdNullable("fooId")).thenReturn(fooData);

        // Configure BarService.getBarForId2(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId2("barId")).thenReturn(barData);

        when(mockOtherDataService.getData3("otherId")).thenReturn(null);

        // Run the test
        final OtherData result = myClassUnderTest.getFooAndBar10("fooId");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetFooAndBar10_OtherServiceThrowsOtherServiceException() {
        // Setup
        // Configure FooService.getFooByIdNullable(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooByIdNullable("fooId")).thenReturn(fooData);

        // Configure BarService.getBarForId2(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId2("barId")).thenReturn(barData);

        when(mockOtherDataService.getData3("otherId")).thenThrow(OtherServiceException.class);

        // Run the test
        assertThrows(OtherServiceException.class, () -> myClassUnderTest.getFooAndBar10("fooId"));
    }

    @Test
    void testGetFooAndBar11() {
        // Setup
        final OtherData expectedResult = new OtherData("id", "description");

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure BarService.getBarForIdAsync(...).
        final CompletableFuture<BarData> barDataCompletableFuture = CompletableFuture.completedFuture(
                new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0)));
        when(mockBarService.getBarForIdAsync("barId")).thenReturn(barDataCompletableFuture);

        // Configure OtherService.getDataAsync(...).
        final CompletableFuture<OtherData> otherDataCompletableFuture = CompletableFuture.completedFuture(
                new OtherData("id", "description"));
        when(mockOtherDataService.getDataAsync("otherId")).thenReturn(otherDataCompletableFuture);

        // Run the test
        final OtherData result = myClassUnderTest.getFooAndBar11("fooId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooAndBar11_FooServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = new CompletableFuture<>();
        fooDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar11("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testGetFooAndBar11_BarServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure BarService.getBarForIdAsync(...).
        final CompletableFuture<BarData> barDataCompletableFuture = new CompletableFuture<>();
        barDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockBarService.getBarForIdAsync("barId")).thenReturn(barDataCompletableFuture);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar11("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testGetFooAndBar11_OtherServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure BarService.getBarForIdAsync(...).
        final CompletableFuture<BarData> barDataCompletableFuture = CompletableFuture.completedFuture(
                new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0)));
        when(mockBarService.getBarForIdAsync("barId")).thenReturn(barDataCompletableFuture);

        // Configure OtherService.getDataAsync(...).
        final CompletableFuture<OtherData> otherDataCompletableFuture = new CompletableFuture<>();
        otherDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockOtherDataService.getDataAsync("otherId")).thenReturn(otherDataCompletableFuture);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar11("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }
}
