package com.myapp

import groovy.transform.CompileStatic
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.*
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc

import javax.servlet.http.Cookie
import java.time.LocalDateTime

import static org.mockito.ArgumentMatchers.any
import static org.mockito.ArgumentMatchers.eq
import static org.mockito.Mockito.*
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@RunWith(SpringRunner.class)
@WebMvcTest(MyClass.class)
@CompileStatic
class MyClassTest {

    @Autowired
    private MockMvc mockMvc

    @MockBean
    private FooService mockFooService
    @MockBean
    private BarService mockBarService
    @MockBean
    private MetricsServiceAdapter mockMetricsService

    @Test
    void testGetFooById() {
        // Setup
        // Configure FooService.getFooDataById(...).
        def fooData = new FooData(0L, "name", "shortDisplayName", LocalDateTime.of(2020, 1, 1, 0, 0, 0))
        when(mockFooService.getFooDataById(0L)).thenReturn(fooData)

        // Run the test and verify the results
        mockMvc.perform(get("/data/foo/{id}", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true))
        verify(mockMetricsService).recordMetric("getFooById success")
    }

    @Test
    void testGetFooById_FooServiceThrowsInvalidFooDataIdException() {
        // Setup
        when(mockFooService.getFooDataById(0L)).thenThrow(InvalidFooDataIdException.class)

        // Run the test and verify the results
        mockMvc.perform(get("/data/foo/{id}", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError())
                .andExpect(content().json("{}", true))
    }

    @Test
    void testSafeGetFooById() {
        // Setup
        // Configure FooService.getFooDataById(...).
        def fooData = new FooData(0L, "name", "shortDisplayName", LocalDateTime.of(2020, 1, 1, 0, 0, 0))
        when(mockFooService.getFooDataById(0L)).thenReturn(fooData)

        // Run the test and verify the results
        mockMvc.perform(get("/data/safefoo/{id}", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true))
        verify(mockMetricsService).recordMetric("getFooById success")
    }

    @Test
    void testSafeGetFooById_FooServiceThrowsInvalidFooDataIdException() {
        // Setup
        when(mockFooService.getFooDataById(0L)).thenThrow(InvalidFooDataIdException.class)

        // Run the test and verify the results
        mockMvc.perform(get("/data/safefoo/{id}", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("", true))
        verify(mockMetricsService).recordMetric(eq("getFooById error"), any(InvalidFooDataIdException.class))
    }

    @Test
    void testGetAllFoos() {
        // Setup
        // Configure FooService.getAllFoos(...).
        def fooData = [new FooData(0L, "name", "shortDisplayName", LocalDateTime.of(2020, 1, 1, 0, 0, 0))]
        when(mockFooService.getAllFoos()).thenReturn(fooData)

        // Run the test and verify the results
        mockMvc.perform(get("/data/foos/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true))
        verify(mockMetricsService).recordMetric("getAllFoos success")
    }

    @Test
    void testGetAllFoos_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getAllFoos()).thenReturn([])

        // Run the test and verify the results
        mockMvc.perform(get("/data/foos/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[]", true))
        verify(mockMetricsService).recordMetric("getAllFoos success")
    }

    @Test
    void testGetBarById() {
        // Setup
        // Configure BarService.getBarDataById(...).
        def barBean = new BarBean()
        barBean.setId(0L)
        barBean.setName("name")
        barBean.setShortDisplayName("shortDisplayName")
        barBean.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
        when(mockBarService.getBarDataById(0L)).thenReturn(barBean)

        // Run the test and verify the results
        mockMvc.perform(get("/data/bar/{id}", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true))
        verify(mockMetricsService).recordMetric("getBarById success")
    }

    @Test
    void testGetBarById_BarServiceThrowsInvalidBarDataIdException() {
        // Setup
        when(mockBarService.getBarDataById(0L)).thenThrow(InvalidBarDataIdException.class)

        // Run the test and verify the results
        mockMvc.perform(get("/data/bar/{id}", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError())
                .andExpect(content().json("{}", true))
    }

    @Test
    void testSafeGetBarById() {
        // Setup
        // Configure BarService.getBarDataById(...).
        def barBean = new BarBean()
        barBean.setId(0L)
        barBean.setName("name")
        barBean.setShortDisplayName("shortDisplayName")
        barBean.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
        when(mockBarService.getBarDataById(0L)).thenReturn(barBean)

        // Run the test and verify the results
        mockMvc.perform(get("/data/safebar/{id}", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true))
        verify(mockMetricsService).recordMetric("getBarById success")
    }

    @Test
    void testSafeGetBarById_BarServiceThrowsInvalidBarDataIdException() {
        // Setup
        when(mockBarService.getBarDataById(0L)).thenThrow(InvalidBarDataIdException.class)

        // Run the test and verify the results
        mockMvc.perform(get("/data/safebar/{id}", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("", true))
        verify(mockMetricsService).recordMetric(eq("getBarById error"), any(InvalidBarDataIdException.class))
    }

    @Test
    void testGetAllBars() {
        // Setup
        // Configure BarService.getAllBars(...).
        def barBean = new BarBean()
        barBean.setId(0L)
        barBean.setName("name")
        barBean.setShortDisplayName("shortDisplayName")
        barBean.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
        def barBeans = [barBean]
        when(mockBarService.getAllBars()).thenReturn(barBeans)

        // Run the test and verify the results
        mockMvc.perform(get("/data/bars/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true))
        verify(mockMetricsService).recordMetric("getAllBars success")
    }

    @Test
    void testGetAllBars_BarServiceReturnsNoItems() {
        // Setup
        when(mockBarService.getAllBars()).thenReturn([])

        // Run the test and verify the results
        mockMvc.perform(get("/data/bars/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[]", true))
        verify(mockMetricsService).recordMetric("getAllBars success")
    }

    @Test
    void testGetAllBars_BarServiceThrowsIOException() {
        // Setup
        when(mockBarService.getAllBars()).thenThrow(IOException.class)

        // Run the test and verify the results
        mockMvc.perform(get("/data/bars/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError())
                .andExpect(content().json("{}", true))
    }

    @Test
    void testGetBarWithGetParam() {
        // Setup
        // Configure BarService.getBarDataById(...).
        def barBean = new BarBean()
        barBean.setId(0L)
        barBean.setName("name")
        barBean.setShortDisplayName("shortDisplayName")
        barBean.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
        when(mockBarService.getBarDataById(0L)).thenReturn(barBean)

        // Run the test and verify the results
        mockMvc.perform(get("/data/bar")
                .param("id", "id")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true))
        verify(mockMetricsService).recordMetric("getBarWithGetParam success")
    }

    @Test
    void testGetBarWithGetParam_BarServiceThrowsInvalidBarDataIdException() {
        // Setup
        when(mockBarService.getBarDataById(0L)).thenThrow(InvalidBarDataIdException.class)

        // Run the test and verify the results
        mockMvc.perform(get("/data/bar")
                .param("id", "id")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError())
                .andExpect(content().json("{}", true))
    }

    @Test
    void testGetFavoriteBarFromCookie() {
        // Setup
        // Configure BarService.getBarDataById(...).
        def barBean = new BarBean()
        barBean.setId(0L)
        barBean.setName("name")
        barBean.setShortDisplayName("shortDisplayName")
        barBean.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
        when(mockBarService.getBarDataById(0L)).thenReturn(barBean)

        // Run the test and verify the results
        mockMvc.perform(get("/data/favoriteBar")
                .cookie(new Cookie("favoriteBarId", "id"))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true))
        verify(mockMetricsService).recordMetric("getFavoriteBarFromCookie success")
    }

    @Test
    void testGetFavoriteBarFromCookie_BarServiceThrowsInvalidBarDataIdException() {
        // Setup
        when(mockBarService.getBarDataById(0L)).thenThrow(InvalidBarDataIdException.class)

        // Run the test and verify the results
        mockMvc.perform(get("/data/favoriteBar")
                .cookie(new Cookie("favoriteBarId", "id"))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError())
                .andExpect(content().json("{}", true))
    }

    @Test
    void testGetFavoriteBarFromCookieWithResponseEntity() {
        // Setup
        // Configure BarService.getBarDataById(...).
        def barBean = new BarBean()
        barBean.setId(0L)
        barBean.setName("name")
        barBean.setShortDisplayName("shortDisplayName")
        barBean.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
        when(mockBarService.getBarDataById(0L)).thenReturn(barBean)

        // Run the test and verify the results
        mockMvc.perform(get("/data/favoriteBarWithResponseEntity")
                .cookie(new Cookie("favoriteBarId", "id"))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true))
        verify(mockMetricsService).recordMetric("getFavoriteBarFromCookie success")
    }

    @Test
    void testGetFavoriteBarFromCookieWithResponseEntity_BarServiceThrowsInvalidBarDataIdException() {
        // Setup
        when(mockBarService.getBarDataById(0L)).thenThrow(InvalidBarDataIdException.class)

        // Run the test and verify the results
        mockMvc.perform(get("/data/favoriteBarWithResponseEntity")
                .cookie(new Cookie("favoriteBarId", "id"))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError())
                .andExpect(content().json("{}", true))
    }

    @Test
    void testGetBarWithMultipleGetParams() {
        // Setup
        // Configure BarService.getAllBars(...).
        def barBean = new BarBean()
        barBean.setId(0L)
        barBean.setName("name")
        barBean.setShortDisplayName("shortDisplayName")
        barBean.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
        def barBeans = [barBean]
        when(mockBarService.getAllBars()).thenReturn(barBeans)

        // Run the test and verify the results
        mockMvc.perform(get("/data/barsearch")
                .param("name", "name")
                .param("startDate", "startDate")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true))
        verify(mockMetricsService).recordMetric("getBarWithMultipleGetParams success")
    }

    @Test
    void testGetBarWithMultipleGetParams_BarServiceReturnsNoItems() {
        // Setup
        when(mockBarService.getAllBars()).thenReturn([])

        // Run the test and verify the results
        mockMvc.perform(get("/data/barsearch")
                .param("name", "name")
                .param("startDate", "startDate")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[]", true))
        verify(mockMetricsService).recordMetric("getBarWithMultipleGetParams success")
    }

    @Test
    void testGetBarWithMultipleGetParams_BarServiceThrowsIOException() {
        // Setup
        when(mockBarService.getAllBars()).thenThrow(IOException.class)

        // Run the test and verify the results
        mockMvc.perform(get("/data/barsearch")
                .param("name", "name")
                .param("startDate", "startDate")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError())
                .andExpect(content().json("{}", true))
    }

    @Test
    void testPostNewBar() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(post("/data/postBar")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true))
        verify(mockBarService).saveBar(any(BarBean.class))
    }

    @Test
    void testPutNewBar() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(put("/data/putBar")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true))
        verify(mockBarService).saveBar(any(BarBean.class))
    }

    @Test
    void testPutNewBarWithHttpEntity() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(put("/data/putBarWithHttpEntity")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true))
        verify(mockBarService).saveBar(any(BarBean.class))
    }

    @Test
    void testPatchBar() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(patch("/data/patchBar")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true))
        verify(mockBarService).saveBar(any(BarBean.class))
    }

    @Test
    void testDeleteBarWithId() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(delete("/data/bar/{id}", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true))
        verify(mockBarService).deleteBarWithId(0)
        verify(mockMetricsService).recordMetric("deleteBarWithId success")
    }

    @Test
    void testDeleteBarWithId_BarServiceThrowsInvalidBarDataIdException() {
        // Setup
        doThrow(InvalidBarDataIdException.class).when(mockBarService).deleteBarWithId(0)

        // Run the test and verify the results
        mockMvc.perform(delete("/data/bar/{id}", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError())
                .andExpect(content().json("{}", true))
    }

    @Test
    void testDeleteBarWithId_BarServiceThrowsNetworkException() {
        // Setup
        doThrow(NetworkException.class).when(mockBarService).deleteBarWithId(0)

        // Run the test and verify the results
        mockMvc.perform(delete("/data/bar/{id}", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError())
                .andExpect(content().json("{}", true))
    }

    @Test
    void testTryHttpRequestStuff() {
        // Setup
        def barBean = new BarBean()
        barBean.setId(0L)
        barBean.setName("name")
        barBean.setShortDisplayName("shortDisplayName")
        barBean.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
        def request = new RequestEntity<>(barBean, HttpMethod.GET, new URI("https://example.com/"))

        // Run the test
        MyClass.tryHttpRequestStuff(request)

        // Verify the results
    }

    @Test
    void testTryHttpStuff() {
        // Setup
        def barBean = new BarBean()
        barBean.setId(0L)
        barBean.setName("name")
        barBean.setShortDisplayName("shortDisplayName")
        barBean.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
        def request = new HttpEntity<>(barBean, new HttpHeaders())

        // Run the test
        MyClass.tryHttpStuff(request)

        // Verify the results
    }

    @Test
    void testGetControllerId() {
        assert "MyClassController" == MyClass.getControllerId()
    }

    @Test
    void testUrlEncode() {
        assert "paramValue" == MyClass.urlEncode("paramValue")
    }
}
