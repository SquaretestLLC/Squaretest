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

import java.time.LocalDateTime

import static org.mockito.ArgumentMatchers.any
import static org.mockito.BDDMockito.given
import static org.mockito.BDDMockito.then
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
        given(mockBarService.getBarDataById(0L)).willReturn(barBean)

        // Run the test and verify the results
        mockMvc.perform(get("/greeting")
                .param("name", "name")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testGreeting_BarServiceThrowsInvalidBarDataIdException() {
        // Setup
        given(mockBarService.getBarDataById(0L)).willThrow(InvalidBarDataIdException.class)

        // Run the test and verify the results
        mockMvc.perform(get("/greeting")
                .param("name", "name")
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
        given(mockBarService.getBarDataById(0L)).willReturn(barBean)

        // Run the test and verify the results
        mockMvc.perform(get("/greeting1")
                .locale(Locale.US)
                .param("name", "name")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
        then(mockBarService).should().saveBar(any(BarBean.class))
        then(mockMetricsServiceAdapter).should().recordMetric("greeting1")
    }

    @Test
    void testGreeting1_BarServiceGetBarDataByIdThrowsInvalidBarDataIdException() {
        // Setup
        given(mockBarService.getBarDataById(0L)).willThrow(InvalidBarDataIdException.class)

        // Run the test and verify the results
        mockMvc.perform(get("/greeting1")
                .locale(Locale.US)
                .param("name", "name")
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
        given(mockBarService.getBarDataById(0L)).willReturn(barBean)

        // Run the test and verify the results
        mockMvc.perform(get("/greeting2")
                .locale(Locale.US)
                .param("name", "name")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
        then(mockBarService).should().saveBar(any(BarBean.class))
        then(mockMetricsServiceAdapter).should().recordMetric("greeting1")
    }

    @Test
    void testGreeting2_BarServiceGetBarDataByIdThrowsInvalidBarDataIdException() {
        // Setup
        given(mockBarService.getBarDataById(0L)).willThrow(InvalidBarDataIdException.class)

        // Run the test and verify the results
        mockMvc.perform(get("/greeting2")
                .locale(Locale.US)
                .param("name", "name")
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
        given(mockBarService.getBarDataById(0L)).willReturn(barBean)

        // Configure BarService.getAllBars(...).
        def barBean1 = new BarBean()
        barBean1.setId(0L)
        barBean1.setName("name")
        barBean1.setShortDisplayName("shortDisplayName")
        barBean1.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
        def barBeans = [barBean1]
        given(mockBarService.getAllBars()).willReturn(barBeans)

        // Run the test and verify the results
        mockMvc.perform(get("/greetAll")
                .param("name", "name")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
        then(mockMetricsServiceAdapter).should().recordMetric("greetAll")
    }

    @Test
    void testGreetAll_BarServiceGetBarDataByIdThrowsInvalidBarDataIdException() {
        // Setup
        given(mockBarService.getBarDataById(0L)).willThrow(InvalidBarDataIdException.class)

        // Run the test and verify the results
        mockMvc.perform(get("/greetAll")
                .param("name", "name")
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
        given(mockBarService.getBarDataById(0L)).willReturn(barBean)

        given(mockBarService.getAllBars()).willReturn([])

        // Run the test and verify the results
        mockMvc.perform(get("/greetAll")
                .param("name", "name")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
        then(mockMetricsServiceAdapter).should().recordMetric("greetAll")
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
        given(mockBarService.getBarDataById(0L)).willReturn(barBean)

        given(mockBarService.getAllBars()).willThrow(IOException.class)

        // Run the test and verify the results
        mockMvc.perform(get("/greetAll")
                .param("name", "name")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().is5xxServerError())
                .andExpect(content().string("expectedResponse"))
    }
}
