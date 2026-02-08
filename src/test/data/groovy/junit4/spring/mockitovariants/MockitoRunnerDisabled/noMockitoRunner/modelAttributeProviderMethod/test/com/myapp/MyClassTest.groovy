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

import static org.mockito.Mockito.when
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
        mockMvc.perform(get("/greeting")
                .param("name", "name")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testGreeting_BarServiceThrowsInvalidBarDataIdException() {
        // Setup
        when(mockBarService.getBarDataById(0L)).thenThrow(InvalidBarDataIdException.class)

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
        when(mockBarService.getBarDataById(0L)).thenReturn(barBean)

        // Run the test and verify the results
        mockMvc.perform(get("/greeting1")
                .locale(Locale.US)
                .param("name", "name")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("expectedResponse"))
    }

    @Test
    void testGreeting1_BarServiceThrowsInvalidBarDataIdException() {
        // Setup
        when(mockBarService.getBarDataById(0L)).thenThrow(InvalidBarDataIdException.class)

        // Run the test and verify the results
        mockMvc.perform(get("/greeting1")
                .locale(Locale.US)
                .param("name", "name")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().is5xxServerError())
                .andExpect(content().string("expectedResponse"))
    }
}
