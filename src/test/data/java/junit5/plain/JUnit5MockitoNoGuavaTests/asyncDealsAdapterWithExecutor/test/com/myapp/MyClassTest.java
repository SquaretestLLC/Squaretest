package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private DealsServiceClient mockDealsService;
    @Mock
    private Executor mockExecutor;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockDealsService, mockExecutor);
    }

    @Test
    void testGetDeals() throws Exception {
        // Setup
        doAnswer(invocation -> {
            ((Runnable) invocation.getArguments()[0]).run();
            return null;
        }).when(mockExecutor).execute(any(Runnable.class));

        // Configure DealsServiceClient.getDeals(...).
        final DealsServiceResponse dealsServiceResponse = new DealsServiceResponse();
        final Deal deal = new Deal();
        deal.setId("id");
        deal.setProductName("productName");
        deal.setPrice(new BigDecimal("0.00"));
        deal.setImageUrl("imageUrl");
        dealsServiceResponse.setActiveDeals(Arrays.asList(deal));
        when(mockDealsService.getDeals(any(GetDealsRequest.class))).thenReturn(dealsServiceResponse);

        // Run the test
        final CompletableFuture<List<DisplayDeal>> result = myClassUnderTest.getDeals("userId");

        // Verify the results
        verify(mockExecutor).execute(any(Runnable.class));
    }

    @Test
    void testGetDeals_DealsServiceClientThrowsServiceException() throws Exception {
        // Setup
        doAnswer(invocation -> {
            ((Runnable) invocation.getArguments()[0]).run();
            return null;
        }).when(mockExecutor).execute(any(Runnable.class));
        when(mockDealsService.getDeals(any(GetDealsRequest.class))).thenThrow(ServiceException.class);

        // Run the test
        final CompletableFuture<List<DisplayDeal>> result = myClassUnderTest.getDeals("userId");

        // Verify the results
        verify(mockExecutor).execute(any(Runnable.class));
    }

    @Test
    void testGetDeals_DealsServiceClientThrowsNetworkException() throws Exception {
        // Setup
        doAnswer(invocation -> {
            ((Runnable) invocation.getArguments()[0]).run();
            return null;
        }).when(mockExecutor).execute(any(Runnable.class));
        when(mockDealsService.getDeals(any(GetDealsRequest.class))).thenThrow(NetworkException.class);

        // Run the test
        final CompletableFuture<List<DisplayDeal>> result = myClassUnderTest.getDeals("userId");

        // Verify the results
        verify(mockExecutor).execute(any(Runnable.class));
    }
}
