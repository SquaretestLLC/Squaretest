package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc

import static org.mockito.Mockito.when
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(MyClass.class)
@CompileStatic
class MyClassTest {

    @Autowired
    private MockMvc mockMvc

    @MockBean
    private DealsProvider mockDealsProvider

    @Test
    void testGetDeal11() {
        // Setup
        // Configure DealsProvider.getMainDeal(...).
        def deal = new Deal()
        deal.setId("id")
        deal.setProductName("productName")
        deal.setPrice(new BigDecimal("0.00"))
        deal.setQuantityRemaining(0)
        deal.setImageUrl("imageUrl")
        when(mockDealsProvider.getMainDeal()).thenReturn(deal)

        // Configure DealsProvider.getDealForId1(...).
        def deal1 = new Deal()
        deal1.setId("id")
        deal1.setProductName("productName")
        deal1.setPrice(new BigDecimal("0.00"))
        deal1.setQuantityRemaining(0)
        deal1.setImageUrl("imageUrl")
        when(mockDealsProvider.getDealForId1("dealId")).thenReturn(deal1)

        // Run the test and verify the results
        mockMvc.perform(get("/products/deals1")
                .param("dealId", "dealId")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true))
    }

    @Test
    void testGetDeal0() {
        // Setup
        // Configure DealsProvider.getMainDeal(...).
        def deal = new Deal()
        deal.setId("id")
        deal.setProductName("productName")
        deal.setPrice(new BigDecimal("0.00"))
        deal.setQuantityRemaining(0)
        deal.setImageUrl("imageUrl")
        when(mockDealsProvider.getMainDeal()).thenReturn(deal)

        // Configure DealsProvider.getDealForId0(...).
        def deal1 = new Deal()
        deal1.setId("id")
        deal1.setProductName("productName")
        deal1.setPrice(new BigDecimal("0.00"))
        deal1.setQuantityRemaining(0)
        deal1.setImageUrl("imageUrl")
        when(mockDealsProvider.getDealForId0("dealId")).thenReturn(deal1)

        // Run the test and verify the results
        mockMvc.perform(get("/products/deals1")
                .param("dealId", "dealId")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true))
    }

    @Test
    void testCapitalize() {
        assert "result" == BaseController.capitalize("str")
    }
}