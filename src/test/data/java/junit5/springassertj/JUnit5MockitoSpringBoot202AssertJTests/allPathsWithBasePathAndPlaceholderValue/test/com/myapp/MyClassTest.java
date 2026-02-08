package com.myapp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
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
        mockMvc.perform(get("/baseDeals/deals1")
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
        mockMvc.perform(get("/baseDeals/")
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
        mockMvc.perform(get("/baseDeals/")
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
        mockMvc.perform(get("/baseDeals/companyName/deals")
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
        mockMvc.perform(get("/baseDeals/deals2")
                        .param("dealId", "dealId")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
    }

    @Test
    void testGetDeal6() throws Exception {
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
        mockMvc.perform(get("/baseDeals/deals3")
                        .param("dealId", "dealId")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
    }

    @Test
    void testGetDeal7() throws Exception {
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
        mockMvc.perform(get("/baseDeals/deals3")
                        .param("dealId", "dealId")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
    }

    @Test
    void testGetDeal8() throws Exception {
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
        mockMvc.perform(get("/baseDeals/deals6")
                        .param("dealId", "dealId")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
    }

    @Test
    void testGetDeal9() throws Exception {
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
        mockMvc.perform(get("/baseDeals/deals8")
                        .param("dealId", "dealId")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
    }

    @Test
    void testGetDeal10() throws Exception {
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
        mockMvc.perform(get("/baseDeals/companyName/deals10")
                        .param("dealId", "dealId")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
    }

    @Test
    void testGetDeal11() throws Exception {
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
        mockMvc.perform(get("/baseDeals/")
                        .param("dealId", "dealId")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
    }

    @Test
    void testGetDeal12() throws Exception {
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
        mockMvc.perform(get("/baseDeals/deals13")
                        .param("dealId", "dealId")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
    }

    @Test
    void testGetDeal13() throws Exception {
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
        mockMvc.perform(get("/baseDeals/\"/deals14\"")
                        .param("dealId", "dealId")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
    }

    @Test
    void testGetDeal14() throws Exception {
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
        mockMvc.perform(get("/baseDeals/<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>/deals1")
                        .param("dealId", "dealId")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
    }

    @Test
    void testGetDeal15() throws Exception {
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
        mockMvc.perform(get("/baseDeals/<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>/deals4")
                        .param("dealId", "dealId")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
    }

    @Test
    void testGetDeal16() throws Exception {
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
        mockMvc.perform(get("/baseDeals/<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>/deals2")
                        .param("dealId", "dealId")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
    }

    @Test
    void testGetDeal17() throws Exception {
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
        mockMvc.perform(get("/baseDeals/<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>/deals5")
                        .param("dealId", "dealId")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
    }
}
