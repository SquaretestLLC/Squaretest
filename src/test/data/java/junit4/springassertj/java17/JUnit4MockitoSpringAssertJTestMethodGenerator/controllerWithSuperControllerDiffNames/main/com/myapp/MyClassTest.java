package com.myapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@WebMvcTest(MyClass.class)
class MyClassTest {

    @Autowired
    private MockMvc theMockMvc;

    @MockBean
    private DealsProvider theMockDealsProvider;

    @Test
    void testGetDeal1() throws Exception {
        // Setup
        // Configure DealsProvider.getMainDeal(...).
        final Deal deal = new Deal();
        deal.setId("id");
        deal.setProductName("productName");
        deal.setPrice(new BigDecimal("0.00"));
        deal.setQuantityRemaining(0);
        deal.setImageUrl("imageUrl");
        when(theMockDealsProvider.getMainDeal()).thenReturn(deal);

        // Configure DealsProvider.getDealForId1(...).
        final Deal deal1 = new Deal();
        deal1.setId("id");
        deal1.setProductName("productName");
        deal1.setPrice(new BigDecimal("0.00"));
        deal1.setQuantityRemaining(0);
        deal1.setImageUrl("imageUrl");
        when(theMockDealsProvider.getDealForId1("dealId")).thenReturn(deal1);

        // Run the test
        final MockHttpServletResponse response = theMockMvc.perform(get("/products/deals1")
                        .param("dealId", "dealId")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("expectedResponse", response.getContentAsString());
    }
}
