package com.myapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MyClass.class)
class MyClassTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BarService mockBarService;

    @Test
    void testGreeting1() throws Exception {
        // Setup
        // Configure BarService.getBarDataById1(...).
        final BarBean barBean1 = new BarBean();
        barBean1.setId(0L);
        barBean1.setName("name");
        barBean1.setShortDisplayName("shortDisplayName");
        barBean1.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        barBean1.setOtherId("otherId");
        final Optional<BarBean> barBean = Optional.of(barBean1);
        when(mockBarService.getBarDataById1(0L)).thenReturn(barBean);

        // Configure BarService.getBarDataById2(...).
        final BarBean barBean2 = new BarBean();
        barBean2.setId(0L);
        barBean2.setName("name");
        barBean2.setShortDisplayName("shortDisplayName");
        barBean2.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        barBean2.setOtherId("otherId");
        when(mockBarService.getBarDataById2(0L)).thenReturn(barBean2);

        // Configure BarService.getMainBar1(...).
        final BarBean barBean4 = new BarBean();
        barBean4.setId(0L);
        barBean4.setName("name");
        barBean4.setShortDisplayName("shortDisplayName");
        barBean4.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        barBean4.setOtherId("otherId");
        final Optional<BarBean> barBean3 = Optional.of(barBean4);
        when(mockBarService.getMainBar1(0L)).thenReturn(barBean3);

        // Run the test and verify the results
        mockMvc.perform(get("/greeting")
                        .param("name", "name")
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
    }

    @Test
    void testGreeting1_BarServiceGetBarDataById1ReturnsAbsent() throws Exception {
        // Setup
        when(mockBarService.getBarDataById1(0L)).thenReturn(Optional.empty());

        // Configure BarService.getBarDataById2(...).
        final BarBean barBean = new BarBean();
        barBean.setId(0L);
        barBean.setName("name");
        barBean.setShortDisplayName("shortDisplayName");
        barBean.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        barBean.setOtherId("otherId");
        when(mockBarService.getBarDataById2(0L)).thenReturn(barBean);

        // Configure BarService.getMainBar1(...).
        final BarBean barBean2 = new BarBean();
        barBean2.setId(0L);
        barBean2.setName("name");
        barBean2.setShortDisplayName("shortDisplayName");
        barBean2.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        barBean2.setOtherId("otherId");
        final Optional<BarBean> barBean1 = Optional.of(barBean2);
        when(mockBarService.getMainBar1(0L)).thenReturn(barBean1);

        // Run the test and verify the results
        mockMvc.perform(get("/greeting")
                        .param("name", "name")
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
    }

    @Test
    void testGreeting1_BarServiceGetBarDataById2ThrowsInvalidBarDataIdException() throws Exception {
        // Setup
        when(mockBarService.getBarDataById1(0L)).thenReturn(Optional.empty());
        when(mockBarService.getBarDataById2(0L)).thenThrow(InvalidBarDataIdException.class);

        // Run the test and verify the results
        mockMvc.perform(get("/greeting")
                        .param("name", "name")
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().is5xxServerError())
                .andExpect(content().string("expectedResponse"));
    }

    @Test
    void testGreeting1_BarServiceGetMainBar1ReturnsAbsent() throws Exception {
        // Setup
        // Configure BarService.getBarDataById1(...).
        final BarBean barBean1 = new BarBean();
        barBean1.setId(0L);
        barBean1.setName("name");
        barBean1.setShortDisplayName("shortDisplayName");
        barBean1.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        barBean1.setOtherId("otherId");
        final Optional<BarBean> barBean = Optional.of(barBean1);
        when(mockBarService.getBarDataById1(0L)).thenReturn(barBean);

        // Configure BarService.getBarDataById2(...).
        final BarBean barBean2 = new BarBean();
        barBean2.setId(0L);
        barBean2.setName("name");
        barBean2.setShortDisplayName("shortDisplayName");
        barBean2.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        barBean2.setOtherId("otherId");
        when(mockBarService.getBarDataById2(0L)).thenReturn(barBean2);

        when(mockBarService.getMainBar1(0L)).thenReturn(Optional.empty());

        // Run the test and verify the results
        mockMvc.perform(get("/greeting")
                        .param("name", "name")
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
    }

    @Test
    void testGreeting2() throws Exception {
        // Setup
        // Configure BarService.getBarDataById1(...).
        final BarBean barBean1 = new BarBean();
        barBean1.setId(0L);
        barBean1.setName("name");
        barBean1.setShortDisplayName("shortDisplayName");
        barBean1.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        barBean1.setOtherId("otherId");
        final Optional<BarBean> barBean = Optional.of(barBean1);
        when(mockBarService.getBarDataById1(0L)).thenReturn(barBean);

        // Configure BarService.getBarDataById2(...).
        final BarBean barBean2 = new BarBean();
        barBean2.setId(0L);
        barBean2.setName("name");
        barBean2.setShortDisplayName("shortDisplayName");
        barBean2.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        barBean2.setOtherId("otherId");
        when(mockBarService.getBarDataById2(0L)).thenReturn(barBean2);

        // Run the test and verify the results
        mockMvc.perform(get("/greeting1")
                        .locale(Locale.US)
                        .param("name", "name")
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
    }

    @Test
    void testGreeting2_BarServiceGetBarDataById1ReturnsAbsent() throws Exception {
        // Setup
        when(mockBarService.getBarDataById1(0L)).thenReturn(Optional.empty());

        // Configure BarService.getBarDataById2(...).
        final BarBean barBean = new BarBean();
        barBean.setId(0L);
        barBean.setName("name");
        barBean.setShortDisplayName("shortDisplayName");
        barBean.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        barBean.setOtherId("otherId");
        when(mockBarService.getBarDataById2(0L)).thenReturn(barBean);

        // Run the test and verify the results
        mockMvc.perform(get("/greeting1")
                        .locale(Locale.US)
                        .param("name", "name")
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"));
    }

    @Test
    void testGreeting2_BarServiceGetBarDataById2ThrowsInvalidBarDataIdException() throws Exception {
        // Setup
        when(mockBarService.getBarDataById1(0L)).thenReturn(Optional.empty());
        when(mockBarService.getBarDataById2(0L)).thenThrow(InvalidBarDataIdException.class);

        // Run the test and verify the results
        mockMvc.perform(get("/greeting1")
                        .locale(Locale.US)
                        .param("name", "name")
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().is5xxServerError())
                .andExpect(content().string("expectedResponse"));
    }
}
