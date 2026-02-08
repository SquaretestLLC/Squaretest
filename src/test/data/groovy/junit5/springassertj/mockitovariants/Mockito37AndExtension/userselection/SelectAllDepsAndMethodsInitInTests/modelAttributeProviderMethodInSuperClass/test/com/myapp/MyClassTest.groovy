package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc

import java.time.LocalDateTime

import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.verify
import static org.mockito.Mockito.when
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(MyClass.class)
@CompileStatic
class MyClassTest {

    @Autowired
    private MockMvc mockMvc

    @MockBean
    private BarService mockBarService
    @MockBean
    private MetricsServiceAdapter mockMetricsServiceAdapter

    @Test
    void testGreeting() {
        // Setup
        // Configure BarService.getBarDataById(...).
        def barBean = new BarBean()
        barBean.setId(0L)
        barBean.setName("name")
        barBean.setShortDisplayName("shortDisplayName")
        barBean.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
        when(mockBarService.getBarDataById(0L)).thenReturn(barBean)

        // Run the test and verify the results
        mockMvc.perform(post("/greeting")
                .param("name", "name")
                .with(csrf())
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testGreeting_BarServiceThrowsInvalidBarDataIdException() {
        // Setup
        when(mockBarService.getBarDataById(0L)).thenThrow(InvalidBarDataIdException.class)

        // Run the test and verify the results
        mockMvc.perform(post("/greeting")
                .param("name", "name")
                .with(csrf())
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().is5xxServerError())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testGreeting1() {
        // Setup
        // Configure BarService.getBarDataById(...).
        def barBean = new BarBean()
        barBean.setId(0L)
        barBean.setName("name")
        barBean.setShortDisplayName("shortDisplayName")
        barBean.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
        when(mockBarService.getBarDataById(0L)).thenReturn(barBean)

        // Run the test and verify the results
        mockMvc.perform(post("/greeting1")
                .locale(Locale.US)
                .param("name", "name")
                .with(csrf())
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
        verify(mockBarService).saveBar(any(BarBean.class))
        verify(mockMetricsServiceAdapter).recordMetric("greeting1")
    }

    @Test
    void testGreeting1_BarServiceGetBarDataByIdThrowsInvalidBarDataIdException() {
        // Setup
        when(mockBarService.getBarDataById(0L)).thenThrow(InvalidBarDataIdException.class)

        // Run the test and verify the results
        mockMvc.perform(post("/greeting1")
                .locale(Locale.US)
                .param("name", "name")
                .with(csrf())
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().is5xxServerError())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testGreeting2() {
        // Setup
        // Configure BarService.getBarDataById(...).
        def barBean = new BarBean()
        barBean.setId(0L)
        barBean.setName("name")
        barBean.setShortDisplayName("shortDisplayName")
        barBean.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
        when(mockBarService.getBarDataById(0L)).thenReturn(barBean)

        // Run the test and verify the results
        mockMvc.perform(post("/greeting2")
                .locale(Locale.US)
                .param("name", "name")
                .with(csrf())
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
        verify(mockBarService).saveBar(any(BarBean.class))
        verify(mockMetricsServiceAdapter).recordMetric("greeting1")
    }

    @Test
    void testGreeting2_BarServiceGetBarDataByIdThrowsInvalidBarDataIdException() {
        // Setup
        when(mockBarService.getBarDataById(0L)).thenThrow(InvalidBarDataIdException.class)

        // Run the test and verify the results
        mockMvc.perform(post("/greeting2")
                .locale(Locale.US)
                .param("name", "name")
                .with(csrf())
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().is5xxServerError())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testGreetAll() {
        // Setup
        // Configure BarService.getBarDataById(...).
        def barBean = new BarBean()
        barBean.setId(0L)
        barBean.setName("name")
        barBean.setShortDisplayName("shortDisplayName")
        barBean.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
        when(mockBarService.getBarDataById(0L)).thenReturn(barBean)

        // Configure BarService.getAllBars(...).
        def barBean1 = new BarBean()
        barBean1.setId(0L)
        barBean1.setName("name")
        barBean1.setShortDisplayName("shortDisplayName")
        barBean1.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
        def barBeans = [barBean1]
        when(mockBarService.getAllBars()).thenReturn(barBeans)

        // Run the test and verify the results
        mockMvc.perform(post("/greetAll")
                .param("name", "name")
                .with(csrf())
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
        verify(mockMetricsServiceAdapter).recordMetric("greetAll")
    }

    @Test
    void testGreetAll_BarServiceGetBarDataByIdThrowsInvalidBarDataIdException() {
        // Setup
        when(mockBarService.getBarDataById(0L)).thenThrow(InvalidBarDataIdException.class)

        // Run the test and verify the results
        mockMvc.perform(post("/greetAll")
                .param("name", "name")
                .with(csrf())
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().is5xxServerError())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testGreetAll_BarServiceGetAllBarsReturnsNoItems() {
        // Setup
        // Configure BarService.getBarDataById(...).
        def barBean = new BarBean()
        barBean.setId(0L)
        barBean.setName("name")
        barBean.setShortDisplayName("shortDisplayName")
        barBean.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
        when(mockBarService.getBarDataById(0L)).thenReturn(barBean)

        when(mockBarService.getAllBars()).thenReturn([])

        // Run the test and verify the results
        mockMvc.perform(post("/greetAll")
                .param("name", "name")
                .with(csrf())
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
        verify(mockMetricsServiceAdapter).recordMetric("greetAll")
    }

    @Test
    void testGreetAll_BarServiceGetAllBarsThrowsIOException() {
        // Setup
        // Configure BarService.getBarDataById(...).
        def barBean = new BarBean()
        barBean.setId(0L)
        barBean.setName("name")
        barBean.setShortDisplayName("shortDisplayName")
        barBean.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
        when(mockBarService.getBarDataById(0L)).thenReturn(barBean)

        when(mockBarService.getAllBars()).thenThrow(IOException.class)

        // Run the test and verify the results
        mockMvc.perform(post("/greetAll")
                .param("name", "name")
                .with(csrf())
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().is5xxServerError())
                .andExpect(content().string("expectedResponse"))
    }
}
