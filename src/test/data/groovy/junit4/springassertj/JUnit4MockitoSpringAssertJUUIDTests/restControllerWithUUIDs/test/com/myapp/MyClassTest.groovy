package com.myapp

import groovy.transform.CompileStatic
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc

import static org.assertj.core.api.Assertions.assertThat
import static org.mockito.Mockito.when
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

@RunWith(SpringRunner.class)
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
        when(mockDealsProvider.getDealForId(UUID.fromString("86cbe88d-e62b-4396-b4a6-eed34e0c6854"))).thenReturn(deal)

        // Run the test
        def response = mockMvc.perform(get("/deals/getDeal")
                .param("dealId", "86cbe88d-e62b-4396-b4a6-eed34e0c6854")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse()

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value())
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse")
    }
}
