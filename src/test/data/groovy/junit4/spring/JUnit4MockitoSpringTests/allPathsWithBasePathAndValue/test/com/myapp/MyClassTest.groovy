package com.myapp

import groovy.transform.CompileStatic
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc

import static org.mockito.Mockito.when
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@RunWith(SpringRunner.class)
@WebMvcTest(MyClass.class)
@CompileStatic
class MyClassTest {

    @Autowired
    private MockMvc mockMvc

    @MockBean
    private DealsProvider mockDealsProvider

    @Test
    void testGetDeal1() {
        // Setup
        // Configure DealsProvider.getDealForId(...).
        def deal = new Deal()
        deal.setId("id")
        deal.setProductName("productName")
        deal.setPrice(new BigDecimal("0.00"))
        deal.setQuantityRemaining(0)
        deal.setImageUrl("imageUrl")
        when(mockDealsProvider.getDealForId("dealId")).thenReturn(deal)

        // Run the test and verify the results
        mockMvc.perform(get("/baseDeals/deals1")
                .param("dealId", "dealId")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true))
    }

    @Test
    void testGetDeal2() {
        // Setup
        // Configure DealsProvider.getDealForId(...).
        def deal = new Deal()
        deal.setId("id")
        deal.setProductName("productName")
        deal.setPrice(new BigDecimal("0.00"))
        deal.setQuantityRemaining(0)
        deal.setImageUrl("imageUrl")
        when(mockDealsProvider.getDealForId("dealId")).thenReturn(deal)

        // Run the test and verify the results
        mockMvc.perform(get("/baseDeals/")
                .param("dealId", "dealId")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true))
    }

    @Test
    void testGetDeal3() {
        // Setup
        // Configure DealsProvider.getDealForId(...).
        def deal = new Deal()
        deal.setId("id")
        deal.setProductName("productName")
        deal.setPrice(new BigDecimal("0.00"))
        deal.setQuantityRemaining(0)
        deal.setImageUrl("imageUrl")
        when(mockDealsProvider.getDealForId("dealId")).thenReturn(deal)

        // Run the test and verify the results
        mockMvc.perform(get("/baseDeals/")
                .param("dealId", "dealId")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true))
    }

    @Test
    void testGetDeal4() {
        // Setup
        // Configure DealsProvider.getDealForId(...).
        def deal = new Deal()
        deal.setId("id")
        deal.setProductName("productName")
        deal.setPrice(new BigDecimal("0.00"))
        deal.setQuantityRemaining(0)
        deal.setImageUrl("imageUrl")
        when(mockDealsProvider.getDealForId("dealId")).thenReturn(deal)

        // Run the test and verify the results
        mockMvc.perform(get("/baseDeals/companyName/deals")
                .param("dealId", "dealId")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true))
    }

    @Test
    void testGetDeal5() {
        // Setup
        // Configure DealsProvider.getDealForId(...).
        def deal = new Deal()
        deal.setId("id")
        deal.setProductName("productName")
        deal.setPrice(new BigDecimal("0.00"))
        deal.setQuantityRemaining(0)
        deal.setImageUrl("imageUrl")
        when(mockDealsProvider.getDealForId("dealId")).thenReturn(deal)

        // Run the test and verify the results
        mockMvc.perform(get("/baseDeals/deals2")
                .param("dealId", "dealId")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true))
    }

    @Test
    void testGetDeal6() {
        // Setup
        // Configure DealsProvider.getDealForId(...).
        def deal = new Deal()
        deal.setId("id")
        deal.setProductName("productName")
        deal.setPrice(new BigDecimal("0.00"))
        deal.setQuantityRemaining(0)
        deal.setImageUrl("imageUrl")
        when(mockDealsProvider.getDealForId("dealId")).thenReturn(deal)

        // Run the test and verify the results
        mockMvc.perform(get("/baseDeals/deals3")
                .param("dealId", "dealId")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true))
    }

    @Test
    void testGetDeal7() {
        // Setup
        // Configure DealsProvider.getDealForId(...).
        def deal = new Deal()
        deal.setId("id")
        deal.setProductName("productName")
        deal.setPrice(new BigDecimal("0.00"))
        deal.setQuantityRemaining(0)
        deal.setImageUrl("imageUrl")
        when(mockDealsProvider.getDealForId("dealId")).thenReturn(deal)

        // Run the test and verify the results
        mockMvc.perform(get("/baseDeals/deals3")
                .param("dealId", "dealId")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true))
    }

    @Test
    void testGetDeal8() {
        // Setup
        // Configure DealsProvider.getDealForId(...).
        def deal = new Deal()
        deal.setId("id")
        deal.setProductName("productName")
        deal.setPrice(new BigDecimal("0.00"))
        deal.setQuantityRemaining(0)
        deal.setImageUrl("imageUrl")
        when(mockDealsProvider.getDealForId("dealId")).thenReturn(deal)

        // Run the test and verify the results
        mockMvc.perform(get("/baseDeals/deals6")
                .param("dealId", "dealId")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true))
    }

    @Test
    void testGetDeal9() {
        // Setup
        // Configure DealsProvider.getDealForId(...).
        def deal = new Deal()
        deal.setId("id")
        deal.setProductName("productName")
        deal.setPrice(new BigDecimal("0.00"))
        deal.setQuantityRemaining(0)
        deal.setImageUrl("imageUrl")
        when(mockDealsProvider.getDealForId("dealId")).thenReturn(deal)

        // Run the test and verify the results
        mockMvc.perform(get("/baseDeals/deals8")
                .param("dealId", "dealId")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true))
    }

    @Test
    void testGetDeal10() {
        // Setup
        // Configure DealsProvider.getDealForId(...).
        def deal = new Deal()
        deal.setId("id")
        deal.setProductName("productName")
        deal.setPrice(new BigDecimal("0.00"))
        deal.setQuantityRemaining(0)
        deal.setImageUrl("imageUrl")
        when(mockDealsProvider.getDealForId("dealId")).thenReturn(deal)

        // Run the test and verify the results
        mockMvc.perform(get("/baseDeals/companyName/deals10")
                .param("dealId", "dealId")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true))
    }

    @Test
    void testGetDeal11() {
        // Setup
        // Configure DealsProvider.getDealForId(...).
        def deal = new Deal()
        deal.setId("id")
        deal.setProductName("productName")
        deal.setPrice(new BigDecimal("0.00"))
        deal.setQuantityRemaining(0)
        deal.setImageUrl("imageUrl")
        when(mockDealsProvider.getDealForId("dealId")).thenReturn(deal)

        // Run the test and verify the results
        mockMvc.perform(get("/baseDeals/")
                .param("dealId", "dealId")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true))
    }

    @Test
    void testGetDeal12() {
        // Setup
        // Configure DealsProvider.getDealForId(...).
        def deal = new Deal()
        deal.setId("id")
        deal.setProductName("productName")
        deal.setPrice(new BigDecimal("0.00"))
        deal.setQuantityRemaining(0)
        deal.setImageUrl("imageUrl")
        when(mockDealsProvider.getDealForId("dealId")).thenReturn(deal)

        // Run the test and verify the results
        mockMvc.perform(get("/baseDeals/deals13")
                .param("dealId", "dealId")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true))
    }

    @Test
    void testGetDeal13() {
        // Setup
        // Configure DealsProvider.getDealForId(...).
        def deal = new Deal()
        deal.setId("id")
        deal.setProductName("productName")
        deal.setPrice(new BigDecimal("0.00"))
        deal.setQuantityRemaining(0)
        deal.setImageUrl("imageUrl")
        when(mockDealsProvider.getDealForId("dealId")).thenReturn(deal)

        // Run the test and verify the results
        mockMvc.perform(get("/baseDeals/\"/deals14\"")
                .param("dealId", "dealId")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true))
    }

    @Test
    void testGetDeal14() {
        // Setup
        // Configure DealsProvider.getDealForId(...).
        def deal = new Deal()
        deal.setId("id")
        deal.setProductName("productName")
        deal.setPrice(new BigDecimal("0.00"))
        deal.setQuantityRemaining(0)
        deal.setImageUrl("imageUrl")
        when(mockDealsProvider.getDealForId("dealId")).thenReturn(deal)

        // Run the test and verify the results
        mockMvc.perform(get("/baseDeals/<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>/deals1")
                .param("dealId", "dealId")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true))
    }

    @Test
    void testGetDeal15() {
        // Setup
        // Configure DealsProvider.getDealForId(...).
        def deal = new Deal()
        deal.setId("id")
        deal.setProductName("productName")
        deal.setPrice(new BigDecimal("0.00"))
        deal.setQuantityRemaining(0)
        deal.setImageUrl("imageUrl")
        when(mockDealsProvider.getDealForId("dealId")).thenReturn(deal)

        // Run the test and verify the results
        mockMvc.perform(get("/baseDeals/<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>/deals4")
                .param("dealId", "dealId")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true))
    }

    @Test
    void testGetDeal16() {
        // Setup
        // Configure DealsProvider.getDealForId(...).
        def deal = new Deal()
        deal.setId("id")
        deal.setProductName("productName")
        deal.setPrice(new BigDecimal("0.00"))
        deal.setQuantityRemaining(0)
        deal.setImageUrl("imageUrl")
        when(mockDealsProvider.getDealForId("dealId")).thenReturn(deal)

        // Run the test and verify the results
        mockMvc.perform(get("/baseDeals/<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>/deals2")
                .param("dealId", "dealId")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true))
    }

    @Test
    void testGetDeal17() {
        // Setup
        // Configure DealsProvider.getDealForId(...).
        def deal = new Deal()
        deal.setId("id")
        deal.setProductName("productName")
        deal.setPrice(new BigDecimal("0.00"))
        deal.setQuantityRemaining(0)
        deal.setImageUrl("imageUrl")
        when(mockDealsProvider.getDealForId("dealId")).thenReturn(deal)

        // Run the test and verify the results
        mockMvc.perform(get("/baseDeals/<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>/deals5")
                .param("dealId", "dealId")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true))
    }
}
