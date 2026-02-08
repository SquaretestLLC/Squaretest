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
    void testGetDeal() {
        // Setup
        // Configure DealsProvider.getDealForId(...).
        def deal = new Deal()
        deal.setId("id")
        deal.setProductName("productName")
        deal.setPrice(new BigDecimal("0.00"))
        deal.setQuantityRemaining(0)
        deal.setImageUrl("imageUrl")
        when(mockDealsProvider.getDealForId(UUID.fromString("88861380-b64c-4701-ad8b-e792bd554286"))).thenReturn(deal)

        // Run the test and verify the results
        mockMvc.perform(get("/deals/getDeal")
                .param("dealId", "88861380-b64c-4701-ad8b-e792bd554286")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true))
    }
}
