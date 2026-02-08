package com.myapp

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.mock.web.MockHttpServletResponse
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc

import static org.junit.jupiter.api.Assertions.assertEquals
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

@ExtendWith(SpringExtension.class)
@WebMvcTest(MyClass.class)
class MyClassTest {

    @Autowired
    private MockMvc mockMvc

    @Test
    void testGetDeal1() throws Exception {
        // Setup
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/products/deals1")
                        .param("dealId", "dealId")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse()

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus())
        assertEquals("expectedResponse", response.getContentAsString())
    }

    @Test
    void testGetDeal0() throws Exception {
        // Setup
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/products/deals1")
                        .param("dealId", "dealId")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse()

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus())
        assertEquals("expectedResponse", response.getContentAsString())
    }

    @Test
    void testCapitalize() {
        assertEquals("result", BaseController.capitalize("str"))
    }
}
