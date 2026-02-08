package com.myapp;

import com.google.common.util.concurrent.MoreExecutors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private FooService mockFooService;
    @Mock
    private FooService mockOldFooService;
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
        myClassUnderTest = new MyClass(MoreExecutors.newDirectExecutorService(), mockFooService, mockOldFooService,
                mockBarService, mockOtherDataService, mockMetricsService);
    }

    @Test
    void testGetFooAndBar1() {
        // Setup
        final OtherData expectedResult = new OtherData("id", "description");

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure BarService.getBarForId1(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId1("barId")).thenReturn(barData);

        when(mockOtherDataService.getData1("id", "barId")).thenReturn(new OtherData("id", "description"));

        // Run the test
        final OtherData result = myClassUnderTest.getFooAndBar1("fooId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooAndBar1_FooServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = new CompletableFuture<>();
        fooDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar1("fooId"));
    }

    @Test
    void testGetFooAndBar1_BarServiceThrowsBarServiceException() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        when(mockBarService.getBarForId1("barId")).thenThrow(BarServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar1("fooId"));
    }

    @Test
    void testGetFooAndBar1_OtherServiceThrowsOtherServiceException() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure BarService.getBarForId1(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId1("barId")).thenReturn(barData);

        when(mockOtherDataService.getData1("id", "barId")).thenThrow(OtherServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar1("fooId"));
    }

    @Test
    void testGetFooAndBar2() {
        // Setup
        final OtherData expectedResult = new OtherData("id", "description");

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure BarService.getBarForId1(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId1("barId")).thenReturn(barData);

        when(mockOtherDataService.getData1("id", "barId")).thenReturn(new OtherData("id", "description"));

        // Run the test
        final OtherData result = myClassUnderTest.getFooAndBar2("fooId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooAndBar2_FooServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = new CompletableFuture<>();
        fooDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar2("fooId"));
    }

    @Test
    void testGetFooAndBar2_BarServiceThrowsBarServiceException() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        when(mockBarService.getBarForId1("barId")).thenThrow(BarServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar2("fooId"));
    }

    @Test
    void testGetFooAndBar2_OtherServiceThrowsOtherServiceException() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure BarService.getBarForId1(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId1("barId")).thenReturn(barData);

        when(mockOtherDataService.getData1("id", "barId")).thenThrow(OtherServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar2("fooId"));
    }

    @Test
    void testGetFooAndBar3() {
        // Setup
        final OtherData expectedResult = new OtherData("id", "description");

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure BarService.getBarForId1(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId1("barId")).thenReturn(barData);

        when(mockOtherDataService.getData1("id", "barId")).thenReturn(new OtherData("id", "description"));

        // Run the test
        final OtherData result = myClassUnderTest.getFooAndBar3("fooId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooAndBar3_FooServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = new CompletableFuture<>();
        fooDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar3("fooId"));
    }

    @Test
    void testGetFooAndBar3_BarServiceThrowsBarServiceException() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        when(mockBarService.getBarForId1("barId")).thenThrow(BarServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar3("fooId"));
    }

    @Test
    void testGetFooAndBar3_OtherServiceThrowsOtherServiceException() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure BarService.getBarForId1(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId1("barId")).thenReturn(barData);

        when(mockOtherDataService.getData1("id", "barId")).thenThrow(OtherServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar3("fooId"));
    }

    @Test
    void testGetFooAndBar4() {
        // Setup
        final OtherData expectedResult = new OtherData("id", "description");

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure BarService.getBarForId1(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId1("barId")).thenReturn(barData);

        when(mockOtherDataService.getData1("id", "barId")).thenReturn(new OtherData("id", "description"));

        // Run the test
        final OtherData result = myClassUnderTest.getFooAndBar4("fooId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooAndBar4_FooServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = new CompletableFuture<>();
        fooDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar4("fooId"));
    }

    @Test
    void testGetFooAndBar4_BarServiceThrowsBarServiceException() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        when(mockBarService.getBarForId1("barId")).thenThrow(BarServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar4("fooId"));
    }

    @Test
    void testGetFooAndBar4_OtherServiceThrowsOtherServiceException() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure BarService.getBarForId1(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId1("barId")).thenReturn(barData);

        when(mockOtherDataService.getData1("id", "barId")).thenThrow(OtherServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar4("fooId"));
    }

    @Test
    void testGetFooAndBar5() {
        // Setup
        final OtherData expectedResult = new OtherData("id", "description");

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure BarService.getBarForId1(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId1("barId")).thenReturn(barData);

        when(mockOtherDataService.getData1("id", "barId")).thenReturn(new OtherData("id", "description"));

        // Run the test
        final OtherData result = myClassUnderTest.getFooAndBar5("fooId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooAndBar5_FooServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = new CompletableFuture<>();
        fooDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar5("fooId"));
    }

    @Test
    void testGetFooAndBar5_BarServiceThrowsBarServiceException() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        when(mockBarService.getBarForId1("barId")).thenThrow(BarServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar5("fooId"));
    }

    @Test
    void testGetFooAndBar5_OtherServiceThrowsOtherServiceException() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure BarService.getBarForId1(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId1("barId")).thenReturn(barData);

        when(mockOtherDataService.getData1("id", "barId")).thenThrow(OtherServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar5("fooId"));
    }

    @Test
    void testGetFooAndBar6() {
        // Setup
        final OtherData expectedResult = new OtherData("id", "description");

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure BarService.getBarForId1(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId1("barId")).thenReturn(barData);

        when(mockOtherDataService.getData1("id", "barId")).thenReturn(new OtherData("id", "description"));

        // Run the test
        final OtherData result = myClassUnderTest.getFooAndBar6("fooId");

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
    }

    @Test
    void testGetFooAndBar6_BarServiceThrowsBarServiceException() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        when(mockBarService.getBarForId1("barId")).thenThrow(BarServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar6("fooId"));
    }

    @Test
    void testGetFooAndBar6_OtherServiceThrowsOtherServiceException() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure BarService.getBarForId1(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId1("barId")).thenReturn(barData);

        when(mockOtherDataService.getData1("id", "barId")).thenThrow(OtherServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar6("fooId"));
    }

    @Test
    void testGetFooAndBar7() {
        // Setup
        final OtherData expectedResult = new OtherData("id", "description");

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure BarService.getBarForId1(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId1("barId")).thenReturn(barData);

        when(mockOtherDataService.getData1("id", "barId")).thenReturn(new OtherData("id", "description"));

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
    }

    @Test
    void testGetFooAndBar7_BarServiceThrowsBarServiceException() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        when(mockBarService.getBarForId1("barId")).thenThrow(BarServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar7("fooId"));
    }

    @Test
    void testGetFooAndBar7_OtherServiceThrowsOtherServiceException() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure BarService.getBarForId1(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId1("barId")).thenReturn(barData);

        when(mockOtherDataService.getData1("id", "barId")).thenThrow(OtherServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar7("fooId"));
    }

    @Test
    void testGetFooAndBar8() {
        // Setup
        final OtherData expectedResult = new OtherData("id", "description");

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooById1(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockOldFooService.getFooById1("fooId")).thenReturn(fooData);

        // Configure BarService.getBarForId1(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId1("barId")).thenReturn(barData);

        when(mockOtherDataService.getData1("id", "barId")).thenReturn(new OtherData("id", "description"));

        // Run the test
        final OtherData result = myClassUnderTest.getFooAndBar8("fooId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooAndBar8_FooServiceReturnsFailure() {
        // Setup
        final OtherData expectedResult = new OtherData("id", "description");

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = new CompletableFuture<>();
        fooDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooById1(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockOldFooService.getFooById1("fooId")).thenReturn(fooData);

        // Configure BarService.getBarForId1(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId1("barId")).thenReturn(barData);

        when(mockOtherDataService.getData1("id", "barId")).thenReturn(new OtherData("id", "description"));

        // Run the test
        final OtherData result = myClassUnderTest.getFooAndBar8("fooId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooAndBar8_OldFooServiceThrowsFooServiceException() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = new CompletableFuture<>();
        fooDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        when(mockOldFooService.getFooById1("fooId")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar8("fooId"));
    }

    @Test
    void testGetFooAndBar8_BarServiceThrowsBarServiceException() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        when(mockBarService.getBarForId1("barId")).thenThrow(BarServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar8("fooId"));
    }

    @Test
    void testGetFooAndBar8_OtherServiceThrowsOtherServiceException() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure BarService.getBarForId1(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId1("barId")).thenReturn(barData);

        when(mockOtherDataService.getData1("id", "barId")).thenThrow(OtherServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar8("fooId"));
    }

    @Test
    void testGetFooAndBar9() {
        // Setup
        final OtherData expectedResult = new OtherData("id", "description");

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooById1(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockOldFooService.getFooById1("fooId")).thenReturn(fooData);

        // Configure BarService.getBarForId1(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId1("barId")).thenReturn(barData);

        when(mockOtherDataService.getData1("id", "barId")).thenReturn(new OtherData("id", "description"));

        // Run the test
        final OtherData result = myClassUnderTest.getFooAndBar9("fooId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooAndBar9_FooServiceReturnsFailure() {
        // Setup
        final OtherData expectedResult = new OtherData("id", "description");

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = new CompletableFuture<>();
        fooDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooById1(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockOldFooService.getFooById1("fooId")).thenReturn(fooData);

        // Configure BarService.getBarForId1(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId1("barId")).thenReturn(barData);

        when(mockOtherDataService.getData1("id", "barId")).thenReturn(new OtherData("id", "description"));

        // Run the test
        final OtherData result = myClassUnderTest.getFooAndBar9("fooId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooAndBar9_OldFooServiceThrowsFooServiceException() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        when(mockOldFooService.getFooById1("fooId")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.getFooAndBar9("fooId"));
    }

    @Test
    void testGetFooAndBar9_BarServiceThrowsBarServiceException() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooById1(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockOldFooService.getFooById1("fooId")).thenReturn(fooData);

        when(mockBarService.getBarForId1("barId")).thenThrow(BarServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar9("fooId"));
    }

    @Test
    void testGetFooAndBar9_OtherServiceThrowsOtherServiceException() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooById1(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockOldFooService.getFooById1("fooId")).thenReturn(fooData);

        // Configure BarService.getBarForId1(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId1("barId")).thenReturn(barData);

        when(mockOtherDataService.getData1("id", "barId")).thenThrow(OtherServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar9("fooId"));
    }

    @Test
    void testGetFooAndBar10() {
        // Setup
        final OtherData expectedResult = new OtherData("id", "description");

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooById1(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockOldFooService.getFooById1("fooId")).thenReturn(fooData);

        // Configure BarService.getBarForId1(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId1("barId")).thenReturn(barData);

        when(mockOtherDataService.getData1("id", "barId")).thenReturn(new OtherData("id", "description"));

        // Run the test
        final OtherData result = myClassUnderTest.getFooAndBar10("fooId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooAndBar10_FooServiceReturnsFailure() {
        // Setup
        final OtherData expectedResult = new OtherData("id", "description");

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = new CompletableFuture<>();
        fooDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooById1(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockOldFooService.getFooById1("fooId")).thenReturn(fooData);

        // Configure BarService.getBarForId1(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId1("barId")).thenReturn(barData);

        when(mockOtherDataService.getData1("id", "barId")).thenReturn(new OtherData("id", "description"));

        // Run the test
        final OtherData result = myClassUnderTest.getFooAndBar10("fooId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooAndBar10_OldFooServiceThrowsFooServiceException() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        when(mockOldFooService.getFooById1("fooId")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.getFooAndBar10("fooId"));
    }

    @Test
    void testGetFooAndBar10_BarServiceThrowsBarServiceException() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooById1(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockOldFooService.getFooById1("fooId")).thenReturn(fooData);

        when(mockBarService.getBarForId1("barId")).thenThrow(BarServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar10("fooId"));
    }

    @Test
    void testGetFooAndBar10_OtherServiceThrowsOtherServiceException() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooById1(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockOldFooService.getFooById1("fooId")).thenReturn(fooData);

        // Configure BarService.getBarForId1(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId1("barId")).thenReturn(barData);

        when(mockOtherDataService.getData1("id", "barId")).thenThrow(OtherServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar10("fooId"));
    }

    @Test
    void testGetFooAndBar11() {
        // Setup
        final OtherData expectedResult = new OtherData("id", "description");

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture1 = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockOldFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture1);

        // Configure BarService.getBarForId1(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId1("barId")).thenReturn(barData);

        when(mockOtherDataService.getData1("id", "barId")).thenReturn(new OtherData("id", "description"));

        // Run the test
        final OtherData result = myClassUnderTest.getFooAndBar11("fooId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooAndBar11_FooServiceReturnsFailure() {
        // Setup
        final OtherData expectedResult = new OtherData("id", "description");

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = new CompletableFuture<>();
        fooDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture1 = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockOldFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture1);

        // Configure BarService.getBarForId1(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId1("barId")).thenReturn(barData);

        when(mockOtherDataService.getData1("id", "barId")).thenReturn(new OtherData("id", "description"));

        // Run the test
        final OtherData result = myClassUnderTest.getFooAndBar11("fooId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooAndBar11_OldFooServiceReturnsFailure() {
        // Setup
        final OtherData expectedResult = new OtherData("id", "description");

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture1 = new CompletableFuture<>();
        fooDataCompletableFuture1.completeExceptionally(new Exception("message"));
        when(mockOldFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture1);

        // Configure BarService.getBarForId1(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId1("barId")).thenReturn(barData);

        when(mockOtherDataService.getData1("id", "barId")).thenReturn(new OtherData("id", "description"));

        // Run the test
        final OtherData result = myClassUnderTest.getFooAndBar11("fooId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooAndBar11_BarServiceThrowsBarServiceException() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture1 = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockOldFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture1);

        when(mockBarService.getBarForId1("barId")).thenThrow(BarServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar11("fooId"));
    }

    @Test
    void testGetFooAndBar11_OtherServiceThrowsOtherServiceException() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture1 = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockOldFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture1);

        // Configure BarService.getBarForId1(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId1("barId")).thenReturn(barData);

        when(mockOtherDataService.getData1("id", "barId")).thenThrow(OtherServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar11("fooId"));
    }

    @Test
    void testGetFooAndBar12() {
        // Setup
        final OtherData expectedResult = new OtherData("id", "description");

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture1 = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockOldFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture1);

        // Configure BarService.getBarForId1(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId1("barId")).thenReturn(barData);

        when(mockOtherDataService.getData1("id", "barId")).thenReturn(new OtherData("id", "description"));

        // Run the test
        final OtherData result = myClassUnderTest.getFooAndBar12("fooId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooAndBar12_FooServiceReturnsFailure() {
        // Setup
        final OtherData expectedResult = new OtherData("id", "description");

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = new CompletableFuture<>();
        fooDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture1 = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockOldFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture1);

        // Configure BarService.getBarForId1(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId1("barId")).thenReturn(barData);

        when(mockOtherDataService.getData1("id", "barId")).thenReturn(new OtherData("id", "description"));

        // Run the test
        final OtherData result = myClassUnderTest.getFooAndBar12("fooId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooAndBar12_OldFooServiceReturnsFailure() {
        // Setup
        final OtherData expectedResult = new OtherData("id", "description");

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture1 = new CompletableFuture<>();
        fooDataCompletableFuture1.completeExceptionally(new Exception("message"));
        when(mockOldFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture1);

        // Configure BarService.getBarForId1(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId1("barId")).thenReturn(barData);

        when(mockOtherDataService.getData1("id", "barId")).thenReturn(new OtherData("id", "description"));

        // Run the test
        final OtherData result = myClassUnderTest.getFooAndBar12("fooId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooAndBar12_BarServiceThrowsBarServiceException() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture1 = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockOldFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture1);

        when(mockBarService.getBarForId1("barId")).thenThrow(BarServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar12("fooId"));
    }

    @Test
    void testGetFooAndBar12_OtherServiceThrowsOtherServiceException() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture1 = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockOldFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture1);

        // Configure BarService.getBarForId1(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId1("barId")).thenReturn(barData);

        when(mockOtherDataService.getData1("id", "barId")).thenThrow(OtherServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar12("fooId"));
    }

    @Test
    void testGetFooAndBar13() {
        // Setup
        final OtherData expectedResult = new OtherData("id", "description");

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture1 = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockOldFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture1);

        // Configure BarService.getBarForId1(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId1("barId")).thenReturn(barData);

        when(mockOtherDataService.getData1("id", "barId")).thenReturn(new OtherData("id", "description"));

        // Run the test
        final OtherData result = myClassUnderTest.getFooAndBar13("fooId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooAndBar13_FooServiceReturnsFailure() {
        // Setup
        final OtherData expectedResult = new OtherData("id", "description");

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = new CompletableFuture<>();
        fooDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture1 = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockOldFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture1);

        // Configure BarService.getBarForId1(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId1("barId")).thenReturn(barData);

        when(mockOtherDataService.getData1("id", "barId")).thenReturn(new OtherData("id", "description"));

        // Run the test
        final OtherData result = myClassUnderTest.getFooAndBar13("fooId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooAndBar13_OldFooServiceReturnsFailure() {
        // Setup
        final OtherData expectedResult = new OtherData("id", "description");

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture1 = new CompletableFuture<>();
        fooDataCompletableFuture1.completeExceptionally(new Exception("message"));
        when(mockOldFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture1);

        // Configure BarService.getBarForId1(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId1("barId")).thenReturn(barData);

        when(mockOtherDataService.getData1("id", "barId")).thenReturn(new OtherData("id", "description"));

        // Run the test
        final OtherData result = myClassUnderTest.getFooAndBar13("fooId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooAndBar13_BarServiceThrowsBarServiceException() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture1 = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockOldFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture1);

        when(mockBarService.getBarForId1("barId")).thenThrow(BarServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar13("fooId"));
    }

    @Test
    void testGetFooAndBar13_OtherServiceThrowsOtherServiceException() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture1 = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockOldFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture1);

        // Configure BarService.getBarForId1(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId1("barId")).thenReturn(barData);

        when(mockOtherDataService.getData1("id", "barId")).thenThrow(OtherServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar13("fooId"));
    }

    @Test
    void testGetFooAndBar14() {
        // Setup
        final OtherData expectedResult = new OtherData("id", "description");

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture1 = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockOldFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture1);

        // Configure BarService.getBarForId1(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId1("barId")).thenReturn(barData);

        when(mockOtherDataService.getData1("id", "barId")).thenReturn(new OtherData("id", "description"));

        // Run the test
        final OtherData result = myClassUnderTest.getFooAndBar14("fooId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooAndBar14_FooServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = new CompletableFuture<>();
        fooDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture1 = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockOldFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture1);

        // Configure BarService.getBarForId1(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId1("barId")).thenReturn(barData);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar14("fooId"));
    }

    @Test
    void testGetFooAndBar14_OldFooServiceReturnsFailure() {
        // Setup
        final OtherData expectedResult = new OtherData("id", "description");

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture1 = new CompletableFuture<>();
        fooDataCompletableFuture1.completeExceptionally(new Exception("message"));
        when(mockOldFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture1);

        // Configure BarService.getBarForId1(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId1("barId")).thenReturn(barData);

        when(mockOtherDataService.getData1("id", "barId")).thenReturn(new OtherData("id", "description"));

        // Run the test
        final OtherData result = myClassUnderTest.getFooAndBar14("fooId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooAndBar14_BarServiceThrowsBarServiceException() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture1 = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockOldFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture1);

        when(mockBarService.getBarForId1("barId")).thenThrow(BarServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar14("fooId"));
    }

    @Test
    void testGetFooAndBar14_OtherServiceThrowsOtherServiceException() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture1 = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockOldFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture1);

        // Configure BarService.getBarForId1(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId1("barId")).thenReturn(barData);

        when(mockOtherDataService.getData1("id", "barId")).thenThrow(OtherServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar14("fooId"));
    }

    @Test
    void testGetFooAndBar15() {
        // Setup
        final OtherData expectedResult = new OtherData("id", "description");

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture1 = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockOldFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture1);

        // Configure BarService.getBarForId1(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId1("barId")).thenReturn(barData);

        when(mockOtherDataService.getData1("id", "barId")).thenReturn(new OtherData("id", "description"));

        // Run the test
        final OtherData result = myClassUnderTest.getFooAndBar15("fooId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooAndBar15_FooServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = new CompletableFuture<>();
        fooDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture1 = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockOldFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture1);

        // Configure BarService.getBarForId1(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId1("barId")).thenReturn(barData);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar15("fooId"));
    }

    @Test
    void testGetFooAndBar15_OldFooServiceReturnsFailure() {
        // Setup
        final OtherData expectedResult = new OtherData("id", "description");

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture1 = new CompletableFuture<>();
        fooDataCompletableFuture1.completeExceptionally(new Exception("message"));
        when(mockOldFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture1);

        // Configure BarService.getBarForId1(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId1("barId")).thenReturn(barData);

        when(mockOtherDataService.getData1("id", "barId")).thenReturn(new OtherData("id", "description"));

        // Run the test
        final OtherData result = myClassUnderTest.getFooAndBar15("fooId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooAndBar15_BarServiceThrowsBarServiceException() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture1 = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockOldFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture1);

        when(mockBarService.getBarForId1("barId")).thenThrow(BarServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar15("fooId"));
    }

    @Test
    void testGetFooAndBar15_OtherServiceThrowsOtherServiceException() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture1 = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockOldFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture1);

        // Configure BarService.getBarForId1(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId1("barId")).thenReturn(barData);

        when(mockOtherDataService.getData1("id", "barId")).thenThrow(OtherServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar15("fooId"));
    }

    @Test
    void testGetFooAndBar16() {
        // Setup
        final OtherData expectedResult = new OtherData("id", "description");

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture1 = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockOldFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture1);

        // Configure BarService.getBarForId1(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId1("barId")).thenReturn(barData);

        when(mockOtherDataService.getData1("id", "barId")).thenReturn(new OtherData("id", "description"));

        // Run the test
        final OtherData result = myClassUnderTest.getFooAndBar16("fooId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooAndBar16_FooServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = new CompletableFuture<>();
        fooDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture1 = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockOldFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture1);

        // Configure BarService.getBarForId1(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId1("barId")).thenReturn(barData);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar16("fooId"));
    }

    @Test
    void testGetFooAndBar16_OldFooServiceReturnsFailure() {
        // Setup
        final OtherData expectedResult = new OtherData("id", "description");

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture1 = new CompletableFuture<>();
        fooDataCompletableFuture1.completeExceptionally(new Exception("message"));
        when(mockOldFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture1);

        // Configure BarService.getBarForId1(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId1("barId")).thenReturn(barData);

        when(mockOtherDataService.getData1("id", "barId")).thenReturn(new OtherData("id", "description"));

        // Run the test
        final OtherData result = myClassUnderTest.getFooAndBar16("fooId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooAndBar16_BarServiceThrowsBarServiceException() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture1 = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockOldFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture1);

        when(mockBarService.getBarForId1("barId")).thenThrow(BarServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar16("fooId"));
    }

    @Test
    void testGetFooAndBar16_OtherServiceThrowsOtherServiceException() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture1 = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockOldFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture1);

        // Configure BarService.getBarForId1(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId1("barId")).thenReturn(barData);

        when(mockOtherDataService.getData1("id", "barId")).thenThrow(OtherServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar16("fooId"));
    }

    @Test
    void testGetFooAndBar17() {
        // Setup
        final FooData expectedResult = new FooData("id", "barId", "extraDataId", "name");

        // Configure FooService.getFooById1(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooById1("fooId")).thenReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.getFooAndBar17("fooId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooAndBar17_FooServiceThrowsFooServiceException() {
        // Setup
        when(mockFooService.getFooById1("fooId")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.getFooAndBar17("fooId"));
    }

    @Test
    void testGetFooAndBar18() {
        // Setup
        final FooData expectedResult = new FooData("id", "barId", "extraDataId", "name");

        // Configure FooService.getFooById1(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooById1("fooId")).thenReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.getFooAndBar18("fooId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooAndBar18_FooServiceThrowsFooServiceException() {
        // Setup
        final FooData expectedResult = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooById1("fooId")).thenThrow(FooServiceException.class);

        // Run the test
        final FooData result = myClassUnderTest.getFooAndBar18("fooId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooAndBar19() {
        // Setup
        final FooData expectedResult = new FooData("id", "barId", "extraDataId", "name");

        // Configure FooService.getFooById1(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooById1("fooId")).thenReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.getFooAndBar19("fooId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooAndBar19_FooServiceThrowsFooServiceException() {
        // Setup
        final FooData expectedResult = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooById1("fooId")).thenThrow(FooServiceException.class);

        // Run the test
        final FooData result = myClassUnderTest.getFooAndBar19("fooId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooAndBar20() {
        // Setup
        final FooData expectedResult = new FooData("id", "barId", "extraDataId", "name");

        // Configure FooService.getFooById1(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockFooService.getFooById1("fooId")).thenReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.getFooAndBar20("fooId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooAndBar20_FooServiceThrowsFooServiceException() {
        // Setup
        when(mockFooService.getFooById1("fooId")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooAndBar20("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testValidateFooData1() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture1 = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockOldFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture1);

        // Run the test
        myClassUnderTest.validateFooData1("fooId");

        // Verify the results
        verify(mockMetricsService).recordValidFoo("fooId");
    }

    @Test
    void testValidateFooData1_FooServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = new CompletableFuture<>();
        fooDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture1 = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockOldFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture1);

        // Run the test
        myClassUnderTest.validateFooData1("fooId");

        // Verify the results
        verify(mockMetricsService).recordValidFoo("fooId");
    }

    @Test
    void testValidateFooData1_OldFooServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture1 = new CompletableFuture<>();
        fooDataCompletableFuture1.completeExceptionally(new Exception("message"));
        when(mockOldFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture1);

        // Run the test
        myClassUnderTest.validateFooData1("fooId");

        // Verify the results
        verify(mockMetricsService).recordValidFoo("fooId");
    }

    @Test
    void testValidateFooData2() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture1 = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockOldFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture1);

        // Run the test
        myClassUnderTest.validateFooData2("fooId");

        // Verify the results
        verify(mockMetricsService).recordValidFoo("fooId");
    }

    @Test
    void testValidateFooData2_FooServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = new CompletableFuture<>();
        fooDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture1 = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockOldFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture1);

        // Run the test
        myClassUnderTest.validateFooData2("fooId");

        // Verify the results
        verify(mockMetricsService).recordValidFoo("fooId");
    }

    @Test
    void testValidateFooData2_OldFooServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture1 = new CompletableFuture<>();
        fooDataCompletableFuture1.completeExceptionally(new Exception("message"));
        when(mockOldFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture1);

        // Run the test
        myClassUnderTest.validateFooData2("fooId");

        // Verify the results
        verify(mockMetricsService).recordValidFoo("fooId");
    }

    @Test
    void testValidateFooData3() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture1 = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockOldFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture1);

        // Run the test
        myClassUnderTest.validateFooData3("fooId");

        // Verify the results
        verify(mockMetricsService).recordValidFoo("fooId");
    }

    @Test
    void testValidateFooData3_FooServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = new CompletableFuture<>();
        fooDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture1 = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockOldFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture1);

        // Run the test
        myClassUnderTest.validateFooData3("fooId");

        // Verify the results
        verify(mockMetricsService).recordValidFoo("fooId");
    }

    @Test
    void testValidateFooData3_OldFooServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture1 = new CompletableFuture<>();
        fooDataCompletableFuture1.completeExceptionally(new Exception("message"));
        when(mockOldFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture1);

        // Run the test
        myClassUnderTest.validateFooData3("fooId");

        // Verify the results
        verify(mockMetricsService).recordValidFoo("fooId");
    }

    @Test
    void testValidateFooData4() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture1 = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockOldFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture1);

        // Run the test
        myClassUnderTest.validateFooData4("fooId");

        // Verify the results
        verify(mockMetricsService).recordValidFoo("fooId");
    }

    @Test
    void testValidateFooData4_FooServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = new CompletableFuture<>();
        fooDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture1 = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockOldFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture1);

        // Run the test
        myClassUnderTest.validateFooData4("fooId");

        // Verify the results
        verify(mockMetricsService).recordValidFoo("fooId");
    }

    @Test
    void testValidateFooData4_OldFooServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture1 = new CompletableFuture<>();
        fooDataCompletableFuture1.completeExceptionally(new Exception("message"));
        when(mockOldFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture1);

        // Run the test
        myClassUnderTest.validateFooData4("fooId");

        // Verify the results
        verify(mockMetricsService).recordValidFoo("fooId");
    }

    @Test
    void testValidateFooData5() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture1 = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockOldFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture1);

        // Run the test
        myClassUnderTest.validateFooData5("fooId");

        // Verify the results
        verify(mockMetricsService).recordValidFoo("fooId");
    }

    @Test
    void testValidateFooData5_FooServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = new CompletableFuture<>();
        fooDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture1 = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockOldFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture1);

        // Run the test
        myClassUnderTest.validateFooData5("fooId");

        // Verify the results
        verify(mockMetricsService).recordValidFoo("fooId");
    }

    @Test
    void testValidateFooData5_OldFooServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture1 = new CompletableFuture<>();
        fooDataCompletableFuture1.completeExceptionally(new Exception("message"));
        when(mockOldFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture1);

        // Run the test
        myClassUnderTest.validateFooData5("fooId");

        // Verify the results
        verify(mockMetricsService).recordValidFoo("fooId");
    }

    @Test
    void testValidateFooData6() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture1 = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockOldFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture1);

        // Run the test
        myClassUnderTest.validateFooData6("fooId");

        // Verify the results
        verify(mockMetricsService).recordValidFoo("fooId");
    }

    @Test
    void testValidateFooData6_FooServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = new CompletableFuture<>();
        fooDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture1 = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockOldFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture1);

        // Run the test
        myClassUnderTest.validateFooData6("fooId");

        // Verify the results
        verify(mockMetricsService).recordValidFoo("fooId");
    }

    @Test
    void testValidateFooData6_OldFooServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture1 = new CompletableFuture<>();
        fooDataCompletableFuture1.completeExceptionally(new Exception("message"));
        when(mockOldFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture1);

        // Run the test
        myClassUnderTest.validateFooData6("fooId");

        // Verify the results
        verify(mockMetricsService).recordValidFoo("fooId");
    }

    @Test
    void testValidateFooData7() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooById1(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockOldFooService.getFooById1("fooId")).thenReturn(fooData);

        // Run the test
        myClassUnderTest.validateFooData7("fooId");

        // Verify the results
        verify(mockMetricsService).recordValidFoo("fooId");
    }

    @Test
    void testValidateFooData7_FooServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = new CompletableFuture<>();
        fooDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooById1(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockOldFooService.getFooById1("fooId")).thenReturn(fooData);

        // Run the test
        myClassUnderTest.validateFooData7("fooId");

        // Verify the results
        verify(mockMetricsService).recordValidFoo("fooId");
    }

    @Test
    void testValidateFooData7_OldFooServiceThrowsFooServiceException() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = new CompletableFuture<>();
        fooDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        when(mockOldFooService.getFooById1("fooId")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.validateFooData7("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testValidateFooData8() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooById1(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockOldFooService.getFooById1("fooId")).thenReturn(fooData);

        // Run the test
        myClassUnderTest.validateFooData8("fooId");

        // Verify the results
        verify(mockMetricsService).recordValidFoo("fooId");
    }

    @Test
    void testValidateFooData8_FooServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = new CompletableFuture<>();
        fooDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooById1(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockOldFooService.getFooById1("fooId")).thenReturn(fooData);

        // Run the test
        myClassUnderTest.validateFooData8("fooId");

        // Verify the results
        verify(mockMetricsService).recordValidFoo("fooId");
    }

    @Test
    void testValidateFooData8_OldFooServiceThrowsFooServiceException() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = new CompletableFuture<>();
        fooDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        when(mockOldFooService.getFooById1("fooId")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.validateFooData8("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testValidateFooData9() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooById1(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockOldFooService.getFooById1("fooId")).thenReturn(fooData);

        // Run the test
        myClassUnderTest.validateFooData9("fooId");

        // Verify the results
        verify(mockMetricsService).recordValidFoo("fooId");
    }

    @Test
    void testValidateFooData9_FooServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = new CompletableFuture<>();
        fooDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooById1(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockOldFooService.getFooById1("fooId")).thenReturn(fooData);

        // Run the test
        myClassUnderTest.validateFooData9("fooId");

        // Verify the results
        verify(mockMetricsService).recordValidFoo("fooId");
    }

    @Test
    void testValidateFooData9_OldFooServiceThrowsFooServiceException() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = new CompletableFuture<>();
        fooDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        when(mockOldFooService.getFooById1("fooId")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.validateFooData9("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testValidateFooData10() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooById1(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockOldFooService.getFooById1("fooId")).thenReturn(fooData);

        // Run the test
        myClassUnderTest.validateFooData10("fooId");

        // Verify the results
        verify(mockMetricsService).recordValidFoo("fooId");
    }

    @Test
    void testValidateFooData10_FooServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = new CompletableFuture<>();
        fooDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooById1(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockOldFooService.getFooById1("fooId")).thenReturn(fooData);

        // Run the test
        myClassUnderTest.validateFooData10("fooId");

        // Verify the results
        verify(mockMetricsService).recordValidFoo("fooId");
    }

    @Test
    void testValidateFooData10_OldFooServiceThrowsFooServiceException() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = new CompletableFuture<>();
        fooDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        when(mockOldFooService.getFooById1("fooId")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.validateFooData10("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testValidateFooData11() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Run the test
        myClassUnderTest.validateFooData11("fooId");

        // Verify the results
        verify(mockMetricsService).recordValidFoo("fooId");
    }

    @Test
    void testValidateFooData11_FooServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = new CompletableFuture<>();
        fooDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Run the test
        myClassUnderTest.validateFooData11("fooId");

        // Verify the results
        verify(mockMetricsService).recordValidFoo("fooId");
    }

    @Test
    void testValidateFooData12() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Run the test
        myClassUnderTest.validateFooData12("fooId");

        // Verify the results
        verify(mockMetricsService).recordValidFoo("fooId");
    }

    @Test
    void testValidateFooData12_FooServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = new CompletableFuture<>();
        fooDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Run the test
        myClassUnderTest.validateFooData12("fooId");

        // Verify the results
        verify(mockMetricsService).recordValidFoo("fooId");
    }

    @Test
    void testValidateFooData13() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Run the test
        myClassUnderTest.validateFooData13("fooId");

        // Verify the results
        verify(mockMetricsService).recordValidFoo("fooId");
    }

    @Test
    void testValidateFooData13_FooServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = new CompletableFuture<>();
        fooDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Run the test
        myClassUnderTest.validateFooData13("fooId");

        // Verify the results
        verify(mockMetricsService).recordValidFoo("fooId");
    }

    @Test
    void testValidateFooData14() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooById1(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockOldFooService.getFooById1("fooId")).thenReturn(fooData);

        // Run the test
        myClassUnderTest.validateFooData14("fooId");

        // Verify the results
        verify(mockMetricsService).recordValidFoo("fooId");
    }

    @Test
    void testValidateFooData14_FooServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = new CompletableFuture<>();
        fooDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooById1(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockOldFooService.getFooById1("fooId")).thenReturn(fooData);

        // Run the test
        myClassUnderTest.validateFooData14("fooId");

        // Verify the results
        verify(mockMetricsService).recordValidFoo("fooId");
    }

    @Test
    void testValidateFooData14_OldFooServiceThrowsFooServiceException() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = new CompletableFuture<>();
        fooDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        when(mockOldFooService.getFooById1("fooId")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.validateFooData14("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testValidateFooData15() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooById1(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockOldFooService.getFooById1("fooId")).thenReturn(fooData);

        // Run the test
        myClassUnderTest.validateFooData15("fooId");

        // Verify the results
        verify(mockMetricsService).recordValidFoo("fooId");
    }

    @Test
    void testValidateFooData15_FooServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = new CompletableFuture<>();
        fooDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooById1(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockOldFooService.getFooById1("fooId")).thenReturn(fooData);

        // Run the test
        myClassUnderTest.validateFooData15("fooId");

        // Verify the results
        verify(mockMetricsService).recordValidFoo("fooId");
    }

    @Test
    void testValidateFooData15_OldFooServiceThrowsFooServiceException() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = new CompletableFuture<>();
        fooDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        when(mockOldFooService.getFooById1("fooId")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.validateFooData15("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testValidateFooData16() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooById1(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockOldFooService.getFooById1("fooId")).thenReturn(fooData);

        // Run the test
        myClassUnderTest.validateFooData16("fooId");

        // Verify the results
        verify(mockMetricsService).recordValidFoo("fooId");
    }

    @Test
    void testValidateFooData16_FooServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = new CompletableFuture<>();
        fooDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooById1(...).
        final FooData fooData = new FooData("id", "barId", "extraDataId", "name");
        when(mockOldFooService.getFooById1("fooId")).thenReturn(fooData);

        // Run the test
        myClassUnderTest.validateFooData16("fooId");

        // Verify the results
        verify(mockMetricsService).recordValidFoo("fooId");
    }

    @Test
    void testValidateFooData16_OldFooServiceThrowsFooServiceException() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = new CompletableFuture<>();
        fooDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        when(mockOldFooService.getFooById1("fooId")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.validateFooData16("fooId"));
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testValidateFooData17() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Run the test
        myClassUnderTest.validateFooData17("fooId");

        // Verify the results
        verify(mockMetricsService).recordTasksComplete("fooId");
        verify(mockMetricsService).recordValidFoo("fooId");
    }

    @Test
    void testValidateFooData17_FooServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = new CompletableFuture<>();
        fooDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.validateFooData17("fooId"));
        verify(mockMetricsService).recordTasksComplete("fooId");
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testValidateFooData18() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Run the test
        myClassUnderTest.validateFooData18("fooId");

        // Verify the results
        verify(mockMetricsService).recordTasksComplete("fooId");
        verify(mockMetricsService).recordValidFoo("fooId");
    }

    @Test
    void testValidateFooData18_FooServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = new CompletableFuture<>();
        fooDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.validateFooData18("fooId"));
        verify(mockMetricsService).recordTasksComplete("fooId");
        verify(mockMetricsService).recordExecutionException("fooId");
    }

    @Test
    void testValidateOtherData1() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure BarService.getBarForId1(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId1("barId")).thenReturn(barData);

        // Run the test
        myClassUnderTest.validateOtherData1("fooId");

        // Verify the results
        verify(mockOtherDataService).getData1("id", "barId");
    }

    @Test
    void testValidateOtherData1_FooServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = new CompletableFuture<>();
        fooDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.validateOtherData1("fooId"));
    }

    @Test
    void testValidateOtherData1_BarServiceThrowsBarServiceException() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        when(mockBarService.getBarForId1("barId")).thenThrow(BarServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.validateOtherData1("fooId"));
    }

    @Test
    void testValidateOtherData1_OtherServiceThrowsOtherServiceException() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure BarService.getBarForId1(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId1("barId")).thenReturn(barData);

        when(mockOtherDataService.getData1("id", "barId")).thenThrow(OtherServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.validateOtherData1("fooId"));
    }

    @Test
    void testValidateOtherData2() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure BarService.getBarForId1(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId1("barId")).thenReturn(barData);

        // Run the test
        myClassUnderTest.validateOtherData2("fooId");

        // Verify the results
        verify(mockOtherDataService).getData1("id", "barId");
    }

    @Test
    void testValidateOtherData2_FooServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = new CompletableFuture<>();
        fooDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.validateOtherData2("fooId"));
    }

    @Test
    void testValidateOtherData2_BarServiceThrowsBarServiceException() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        when(mockBarService.getBarForId1("barId")).thenThrow(BarServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.validateOtherData2("fooId"));
    }

    @Test
    void testValidateOtherData2_OtherServiceThrowsOtherServiceException() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure BarService.getBarForId1(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId1("barId")).thenReturn(barData);

        when(mockOtherDataService.getData1("id", "barId")).thenThrow(OtherServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.validateOtherData2("fooId"));
    }

    @Test
    void testValidateOtherData3() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure BarService.getBarForId1(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId1("barId")).thenReturn(barData);

        // Run the test
        myClassUnderTest.validateOtherData3("fooId");

        // Verify the results
        verify(mockOtherDataService).getData1("id", "barId");
    }

    @Test
    void testValidateOtherData3_FooServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = new CompletableFuture<>();
        fooDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.validateOtherData3("fooId"));
    }

    @Test
    void testValidateOtherData3_BarServiceThrowsBarServiceException() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        when(mockBarService.getBarForId1("barId")).thenThrow(BarServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.validateOtherData3("fooId"));
    }

    @Test
    void testValidateOtherData3_OtherServiceThrowsOtherServiceException() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure BarService.getBarForId1(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId1("barId")).thenReturn(barData);

        when(mockOtherDataService.getData1("id", "barId")).thenThrow(OtherServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.validateOtherData3("fooId"));
    }

    @Test
    void testValidateOtherData4() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure BarService.getBarForId1(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId1("barId")).thenReturn(barData);

        // Run the test
        myClassUnderTest.validateOtherData4("fooId");

        // Verify the results
        verify(mockOtherDataService).getData1("id", "barId");
    }

    @Test
    void testValidateOtherData4_FooServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = new CompletableFuture<>();
        fooDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.validateOtherData4("fooId"));
    }

    @Test
    void testValidateOtherData4_BarServiceThrowsBarServiceException() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        when(mockBarService.getBarForId1("barId")).thenThrow(BarServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.validateOtherData4("fooId"));
    }

    @Test
    void testValidateOtherData4_OtherServiceThrowsOtherServiceException() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure BarService.getBarForId1(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId1("barId")).thenReturn(barData);

        when(mockOtherDataService.getData1("id", "barId")).thenThrow(OtherServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.validateOtherData4("fooId"));
    }

    @Test
    void testValidateOtherData5() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure BarService.getBarForId1(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId1("barId")).thenReturn(barData);

        // Run the test
        myClassUnderTest.validateOtherData5("fooId");

        // Verify the results
        verify(mockOtherDataService).getData1("id", "barId");
    }

    @Test
    void testValidateOtherData5_FooServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = new CompletableFuture<>();
        fooDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.validateOtherData5("fooId"));
    }

    @Test
    void testValidateOtherData5_BarServiceThrowsBarServiceException() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        when(mockBarService.getBarForId1("barId")).thenThrow(BarServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.validateOtherData5("fooId"));
    }

    @Test
    void testValidateOtherData5_OtherServiceThrowsOtherServiceException() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure BarService.getBarForId1(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId1("barId")).thenReturn(barData);

        when(mockOtherDataService.getData1("id", "barId")).thenThrow(OtherServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.validateOtherData5("fooId"));
    }

    @Test
    void testValidateOtherData6() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure BarService.getBarForId1(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId1("barId")).thenReturn(barData);

        // Run the test
        myClassUnderTest.validateOtherData6("fooId");

        // Verify the results
        verify(mockOtherDataService).getData1("id", "barId");
    }

    @Test
    void testValidateOtherData6_FooServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = new CompletableFuture<>();
        fooDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.validateOtherData6("fooId"));
    }

    @Test
    void testValidateOtherData6_BarServiceThrowsBarServiceException() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        when(mockBarService.getBarForId1("barId")).thenThrow(BarServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.validateOtherData6("fooId"));
    }

    @Test
    void testValidateOtherData6_OtherServiceThrowsOtherServiceException() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure BarService.getBarForId1(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId1("barId")).thenReturn(barData);

        when(mockOtherDataService.getData1("id", "barId")).thenThrow(OtherServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.validateOtherData6("fooId"));
    }

    @Test
    void testValidateOtherData7() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure BarService.getBarForId1(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId1("barId")).thenReturn(barData);

        // Run the test
        myClassUnderTest.validateOtherData7("fooId");

        // Verify the results
        verify(mockMetricsService).recordGetDefaultTimeoutCalled();
        verify(mockOtherDataService).getData1("id", "barId");
    }

    @Test
    void testValidateOtherData7_FooServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = new CompletableFuture<>();
        fooDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.validateOtherData7("fooId"));
        verify(mockMetricsService).recordGetDefaultTimeoutCalled();
    }

    @Test
    void testValidateOtherData7_BarServiceThrowsBarServiceException() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        when(mockBarService.getBarForId1("barId")).thenThrow(BarServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.validateOtherData7("fooId"));
        verify(mockMetricsService).recordGetDefaultTimeoutCalled();
    }

    @Test
    void testValidateOtherData7_OtherServiceThrowsOtherServiceException() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure BarService.getBarForId1(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId1("barId")).thenReturn(barData);

        when(mockOtherDataService.getData1("id", "barId")).thenThrow(OtherServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.validateOtherData7("fooId"));
        verify(mockMetricsService).recordGetDefaultTimeoutCalled();
    }

    @Test
    void testValidateOtherData8() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure BarService.getBarForId1(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId1("barId")).thenReturn(barData);

        // Run the test
        myClassUnderTest.validateOtherData8("fooId");

        // Verify the results
        verify(mockMetricsService).recordGetDefaultTimeoutCalled();
        verify(mockOtherDataService).getData1("id", "barId");
    }

    @Test
    void testValidateOtherData8_FooServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = new CompletableFuture<>();
        fooDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.validateOtherData8("fooId"));
        verify(mockMetricsService).recordGetDefaultTimeoutCalled();
    }

    @Test
    void testValidateOtherData8_BarServiceThrowsBarServiceException() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        when(mockBarService.getBarForId1("barId")).thenThrow(BarServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.validateOtherData8("fooId"));
        verify(mockMetricsService).recordGetDefaultTimeoutCalled();
    }

    @Test
    void testValidateOtherData8_OtherServiceThrowsOtherServiceException() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Configure BarService.getBarForId1(...).
        final BarData barData = new BarData("barId", "otherId", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarForId1("barId")).thenReturn(barData);

        when(mockOtherDataService.getData1("id", "barId")).thenThrow(OtherServiceException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.validateOtherData8("fooId"));
        verify(mockMetricsService).recordGetDefaultTimeoutCalled();
    }

    @Test
    void testGetFooData1() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Run the test
        final boolean result = myClassUnderTest.getFooData1("fooId");

        // Verify the results
        assertTrue(result);
    }

    @Test
    void testGetFooData1_FooServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = new CompletableFuture<>();
        fooDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Run the test
        final boolean result = myClassUnderTest.getFooData1("fooId");

        // Verify the results
        assertTrue(result);
    }

    @Test
    void testGetFooData2() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "barId", "extraDataId", "name"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Run the test
        final boolean result = myClassUnderTest.getFooData2("fooId");

        // Verify the results
        assertFalse(result);
    }

    @Test
    void testGetFooData2_FooServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooByIdAsync1(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = new CompletableFuture<>();
        fooDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockFooService.getFooByIdAsync1("fooId")).thenReturn(fooDataCompletableFuture);

        // Run the test
        final boolean result = myClassUnderTest.getFooData2("fooId");

        // Verify the results
        assertTrue(result);
    }
}
