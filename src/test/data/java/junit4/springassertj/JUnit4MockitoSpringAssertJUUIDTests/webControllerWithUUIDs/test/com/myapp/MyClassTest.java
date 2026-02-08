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
import java.util.UUID;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MyClass.class)
public class MyClassTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DealsStore mockDealsStore;

    @Test
    public void testCreateNewDeal() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(post("/admin/dealsWithUUID/new")
                        .param("id", "36932f16-5347-47d9-8114-c31a00cbddc6")
                        .param("productName", "productName")
                        .param("price", "0")
                        .param("quantityRemaining", "0")
                        .param("imageUrl", "imageUrl")
                        .with(csrf())
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
        verify(mockDealsStore).save(
                new Deal(UUID.fromString("36932f16-5347-47d9-8114-c31a00cbddc6"), "productName", new BigDecimal("0.00"),
                        0, "imageUrl"));
    }

    @Test
    public void testCreateNewDeal_DealsStoreThrowsNetworkException() throws Exception {
        // Setup
        doThrow(NetworkException.class).when(mockDealsStore).save(
                new Deal(UUID.fromString("36932f16-5347-47d9-8114-c31a00cbddc6"), "productName", new BigDecimal("0.00"),
                        0, "imageUrl"));

        // Run the test and verify the results
        mockMvc.perform(post("/admin/dealsWithUUID/new")
                        .param("id", "36932f16-5347-47d9-8114-c31a00cbddc6")
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
    public void testCreateNewDeal_DealsStoreThrowsServiceException() throws Exception {
        // Setup
        doThrow(ServiceException.class).when(mockDealsStore).save(
                new Deal(UUID.fromString("36932f16-5347-47d9-8114-c31a00cbddc6"), "productName", new BigDecimal("0.00"),
                        0, "imageUrl"));

        // Run the test and verify the results
        mockMvc.perform(post("/admin/dealsWithUUID/new")
                        .param("id", "36932f16-5347-47d9-8114-c31a00cbddc6")
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
