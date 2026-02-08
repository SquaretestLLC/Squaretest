package com.myapp;

import com.google.common.util.concurrent.MoreExecutors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private DealsServiceClient mockDealsService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockDealsService, MoreExecutors.newDirectExecutorService());
    }

    @Test
    void testGetDeals() throws Exception {
        // Setup
        // Configure DealsServiceClient.getDeals(...).
        final DealsServiceResponse dealsServiceResponse = new DealsServiceResponse();
        final Deal deal = new Deal();
        deal.setId("id");
        deal.setProductName("productName");
        deal.setPrice(new BigDecimal("0.00"));
        deal.setImageUrl("imageUrl");
        dealsServiceResponse.setActiveDeals(Arrays.asList(deal));
        given(mockDealsService.getDeals(any(GetDealsRequest.class))).willReturn(dealsServiceResponse);

        // Run the test
        final CompletableFuture<List<DisplayDeal>> result = myClassUnderTest.getDeals("userId");

        // Verify the results
    }

    @Test
    void testGetDeals_DealsServiceClientThrowsServiceException() throws Exception {
        // Setup
        given(mockDealsService.getDeals(any(GetDealsRequest.class))).willThrow(ServiceException.class);

        // Run the test
        final CompletableFuture<List<DisplayDeal>> result = myClassUnderTest.getDeals("userId");

        // Verify the results
    }

    @Test
    void testGetDeals_DealsServiceClientThrowsNetworkException() throws Exception {
        // Setup
        given(mockDealsService.getDeals(any(GetDealsRequest.class))).willThrow(NetworkException.class);

        // Run the test
        final CompletableFuture<List<DisplayDeal>> result = myClassUnderTest.getDeals("userId");

        // Verify the results
    }
}
