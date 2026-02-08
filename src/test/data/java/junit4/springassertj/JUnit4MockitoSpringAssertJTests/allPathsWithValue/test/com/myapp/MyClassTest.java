package com.myapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MyClass.class)
public class MyClassTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DealsProvider mockDealsProvider;

    @Test
    public void testGetDeal1() throws Exception {
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
        mockMvc.perform(get("/deals1")
                        .param("dealId", "dealId")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
    }

    @Test
    public void testGetDeal2() throws Exception {
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
    public void testGetDeal3() throws Exception {
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
    public void testGetDeal4() throws Exception {
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
        mockMvc.perform(get("/companyName/deals")
                        .param("dealId", "dealId")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
    }

    @Test
    public void testGetDeal5() throws Exception {
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
        mockMvc.perform(get("/deals2")
                        .param("dealId", "dealId")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
    }

    @Test
    public void testGetDeal6() throws Exception {
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
        mockMvc.perform(get("/deals3")
                        .param("dealId", "dealId")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
    }

    @Test
    public void testGetDeal7() throws Exception {
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
        mockMvc.perform(get("/deals3")
                        .param("dealId", "dealId")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
    }

    @Test
    public void testGetDeal8() throws Exception {
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
        mockMvc.perform(get("/deals6")
                        .param("dealId", "dealId")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
    }

    @Test
    public void testGetDeal9() throws Exception {
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
        mockMvc.perform(get("/deals8")
                        .param("dealId", "dealId")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
    }

    @Test
    public void testGetDeal10() throws Exception {
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
        mockMvc.perform(get("/companyName/deals10")
                        .param("dealId", "dealId")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
    }

    @Test
    public void testGetDeal11() throws Exception {
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
    public void testGetDeal12() throws Exception {
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
        mockMvc.perform(get("/deals13")
                        .param("dealId", "dealId")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
    }

    @Test
    public void testGetDeal13() throws Exception {
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
        mockMvc.perform(get("/\"/deals14\"")
                        .param("dealId", "dealId")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
    }

    @Test
    public void testGetDeal14() throws Exception {
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
        mockMvc.perform(get("/<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>/deals1")
                        .param("dealId", "dealId")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
    }

    @Test
    public void testGetDeal15() throws Exception {
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
        mockMvc.perform(get("/<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>/deals4")
                        .param("dealId", "dealId")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
    }

    @Test
    public void testGetDeal16() throws Exception {
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
        mockMvc.perform(get("/<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>/deals2")
                        .param("dealId", "dealId")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
    }

    @Test
    public void testGetDeal17() throws Exception {
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
        mockMvc.perform(get("/<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>/deals5")
                        .param("dealId", "dealId")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
    }
}
