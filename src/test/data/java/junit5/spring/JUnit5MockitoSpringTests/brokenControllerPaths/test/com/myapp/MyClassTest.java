package com.myapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MyClass.class)
class MyClassTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DealsProvider mockDealsProvider;

    @Test
    void testGetDeal1() throws Exception {
        // Setup
        // Configure DealsProvider.getDealForId(...).
        final Deal deal = new Deal();
        deal.setId("id");
        deal.setProductName("productName");
        deal.setPrice(new BigDecimal("0.00"));
        deal.setQuantityRemaining(0);
        deal.setImageUrl("imageUrl");
        when(mockDealsProvider.getDealForId("dealId")).thenReturn(deal);

        // Run the test and verify the results
        mockMvc.perform(get("/5")
                        .param("dealId", "dealId")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
    }

    @Test
    void testGetDeal2() throws Exception {
        // Setup
        // Configure DealsProvider.getDealForId(...).
        final Deal deal = new Deal();
        deal.setId("id");
        deal.setProductName("productName");
        deal.setPrice(new BigDecimal("0.00"));
        deal.setQuantityRemaining(0);
        deal.setImageUrl("imageUrl");
        when(mockDealsProvider.getDealForId("dealId")).thenReturn(deal);

        // Run the test and verify the results
        mockMvc.perform(get("/5")
                        .param("dealId", "dealId")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
    }

    @Test
    void testGetDeal3() throws Exception {
        // Setup
        // Configure DealsProvider.getDealForId(...).
        final Deal deal = new Deal();
        deal.setId("id");
        deal.setProductName("productName");
        deal.setPrice(new BigDecimal("0.00"));
        deal.setQuantityRemaining(0);
        deal.setImageUrl("imageUrl");
        when(mockDealsProvider.getDealForId("dealId")).thenReturn(deal);

        // Run the test and verify the results
        mockMvc.perform(get("")
                        .param("dealId", "dealId")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
    }

    @Test
    void testGetDeal4() throws Exception {
        // Setup
        // Configure DealsProvider.getDealForId(...).
        final Deal deal = new Deal();
        deal.setId("id");
        deal.setProductName("productName");
        deal.setPrice(new BigDecimal("0.00"));
        deal.setQuantityRemaining(0);
        deal.setImageUrl("imageUrl");
        when(mockDealsProvider.getDealForId("dealId")).thenReturn(deal);

        // Run the test and verify the results
        mockMvc.perform(get("")
                        .param("dealId", "dealId")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
    }

    @Test
    void testGetDeal5() throws Exception {
        // Setup
        // Configure DealsProvider.getDealForId(...).
        final Deal deal = new Deal();
        deal.setId("id");
        deal.setProductName("productName");
        deal.setPrice(new BigDecimal("0.00"));
        deal.setQuantityRemaining(0);
        deal.setImageUrl("imageUrl");
        when(mockDealsProvider.getDealForId("dealId")).thenReturn(deal);

        // Run the test and verify the results
        mockMvc.perform(get("")
                        .param("dealId", "dealId")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
    }
}
