package com.myapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MyClass.class)
class MyClassTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DealsStore mockDealsStore;

    @Test
    void testCreateNewDeal() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(post("/admin/dealsWithUUID/new")
                        .param("id", "78b61cf2-bea3-462c-9982-63c40af0ca7b")
                        .param("productName", "productName")
                        .param("price", "0")
                        .param("quantityRemaining", "0")
                        .param("imageUrl", "imageUrl")
                        .with(csrf())
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
        verify(mockDealsStore).save(
                new Deal(UUID.fromString("78b61cf2-bea3-462c-9982-63c40af0ca7b"), "productName", new BigDecimal("0.00"),
                        0, "imageUrl"));
    }

    @Test
    void testCreateNewDeal_DealsStoreThrowsNetworkException() throws Exception {
        // Setup
        doThrow(NetworkException.class).when(mockDealsStore).save(
                new Deal(UUID.fromString("78b61cf2-bea3-462c-9982-63c40af0ca7b"), "productName", new BigDecimal("0.00"),
                        0, "imageUrl"));

        // Run the test and verify the results
        mockMvc.perform(post("/admin/dealsWithUUID/new")
                        .param("id", "78b61cf2-bea3-462c-9982-63c40af0ca7b")
                        .param("productName", "productName")
                        .param("price", "0")
                        .param("quantityRemaining", "0")
                        .param("imageUrl", "imageUrl")
                        .with(csrf())
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().is5xxServerError())
                .andExpect(content().string("expectedResponse"));
    }

    @Test
    void testCreateNewDeal_DealsStoreThrowsServiceException() throws Exception {
        // Setup
        doThrow(ServiceException.class).when(mockDealsStore).save(
                new Deal(UUID.fromString("78b61cf2-bea3-462c-9982-63c40af0ca7b"), "productName", new BigDecimal("0.00"),
                        0, "imageUrl"));

        // Run the test and verify the results
        mockMvc.perform(post("/admin/dealsWithUUID/new")
                        .param("id", "78b61cf2-bea3-462c-9982-63c40af0ca7b")
                        .param("productName", "productName")
                        .param("price", "0")
                        .param("quantityRemaining", "0")
                        .param("imageUrl", "imageUrl")
                        .with(csrf())
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().is5xxServerError())
                .andExpect(content().string("expectedResponse"));
    }
}
