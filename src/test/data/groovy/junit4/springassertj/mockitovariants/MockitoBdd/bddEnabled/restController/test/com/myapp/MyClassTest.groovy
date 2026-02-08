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

import static org.assertj.core.api.Assertions.assertThat
import static org.mockito.ArgumentMatchers.any
import static org.mockito.ArgumentMatchers.eq
import static org.mockito.BDDMockito.*
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
        given(mockFooService.getFooDataById(0L)).willReturn(fooData)

        // Run the test and verify the results
        mockMvc.perform(get("/data/foo/{id}", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true))
        then(mockMetricsService).should().recordMetric("getFooById success")
    }

    @Test
    void testGetFooById_FooServiceThrowsInvalidFooDataIdException() {
        // Setup
        given(mockFooService.getFooDataById(0L)).willThrow(InvalidFooDataIdException.class)

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
        given(mockFooService.getFooDataById(0L)).willReturn(fooData)

        // Run the test and verify the results
        mockMvc.perform(get("/data/safefoo/{id}", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true))
        then(mockMetricsService).should().recordMetric("getFooById success")
    }

    @Test
    void testSafeGetFooById_FooServiceThrowsInvalidFooDataIdException() {
        // Setup
        given(mockFooService.getFooDataById(0L)).willThrow(InvalidFooDataIdException.class)

        // Run the test and verify the results
        mockMvc.perform(get("/data/safefoo/{id}", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("", true))
        then(mockMetricsService).should().recordMetric(eq("getFooById error"), any(InvalidFooDataIdException.class))
    }

    @Test
    void testGetAllFoos() {
        // Setup
        // Configure FooService.getAllFoos(...).
        def fooData = [new FooData(0L, "name", "shortDisplayName", LocalDateTime.of(2020, 1, 1, 0, 0, 0))]
        given(mockFooService.getAllFoos()).willReturn(fooData)

        // Run the test and verify the results
        mockMvc.perform(get("/data/foos/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true))
        then(mockMetricsService).should().recordMetric("getAllFoos success")
    }

    @Test
    void testGetAllFoos_FooServiceReturnsNoItems() {
        // Setup
        given(mockFooService.getAllFoos()).willReturn([])

        // Run the test and verify the results
        mockMvc.perform(get("/data/foos/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[]", true))
        then(mockMetricsService).should().recordMetric("getAllFoos success")
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
        given(mockBarService.getBarDataById(0L)).willReturn(barBean)

        // Run the test and verify the results
        mockMvc.perform(get("/data/bar/{id}", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true))
        then(mockMetricsService).should().recordMetric("getBarById success")
    }

    @Test
    void testGetBarById_BarServiceThrowsInvalidBarDataIdException() {
        // Setup
        given(mockBarService.getBarDataById(0L)).willThrow(InvalidBarDataIdException.class)

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
        given(mockBarService.getBarDataById(0L)).willReturn(barBean)

        // Run the test and verify the results
        mockMvc.perform(get("/data/safebar/{id}", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true))
        then(mockMetricsService).should().recordMetric("getBarById success")
    }

    @Test
    void testSafeGetBarById_BarServiceThrowsInvalidBarDataIdException() {
        // Setup
        given(mockBarService.getBarDataById(0L)).willThrow(InvalidBarDataIdException.class)

        // Run the test and verify the results
        mockMvc.perform(get("/data/safebar/{id}", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("", true))
        then(mockMetricsService).should().recordMetric(eq("getBarById error"), any(InvalidBarDataIdException.class))
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
        given(mockBarService.getAllBars()).willReturn(barBeans)

        // Run the test and verify the results
        mockMvc.perform(get("/data/bars/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true))
        then(mockMetricsService).should().recordMetric("getAllBars success")
    }

    @Test
    void testGetAllBars_BarServiceReturnsNoItems() {
        // Setup
        given(mockBarService.getAllBars()).willReturn([])

        // Run the test and verify the results
        mockMvc.perform(get("/data/bars/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[]", true))
        then(mockMetricsService).should().recordMetric("getAllBars success")
    }

    @Test
    void testGetAllBars_BarServiceThrowsIOException() {
        // Setup
        given(mockBarService.getAllBars()).willThrow(IOException.class)

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
        given(mockBarService.getBarDataById(0L)).willReturn(barBean)

        // Run the test and verify the results
        mockMvc.perform(get("/data/bar")
                .param("id", "id")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true))
        then(mockMetricsService).should().recordMetric("getBarWithGetParam success")
    }

    @Test
    void testGetBarWithGetParam_BarServiceThrowsInvalidBarDataIdException() {
        // Setup
        given(mockBarService.getBarDataById(0L)).willThrow(InvalidBarDataIdException.class)

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
        given(mockBarService.getBarDataById(0L)).willReturn(barBean)

        // Run the test and verify the results
        mockMvc.perform(get("/data/favoriteBar")
                .cookie(new Cookie("favoriteBarId", "id"))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true))
        then(mockMetricsService).should().recordMetric("getFavoriteBarFromCookie success")
    }

    @Test
    void testGetFavoriteBarFromCookie_BarServiceThrowsInvalidBarDataIdException() {
        // Setup
        given(mockBarService.getBarDataById(0L)).willThrow(InvalidBarDataIdException.class)

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
        given(mockBarService.getBarDataById(0L)).willReturn(barBean)

        // Run the test and verify the results
        mockMvc.perform(get("/data/favoriteBarWithResponseEntity")
                .cookie(new Cookie("favoriteBarId", "id"))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true))
        then(mockMetricsService).should().recordMetric("getFavoriteBarFromCookie success")
    }

    @Test
    void testGetFavoriteBarFromCookieWithResponseEntity_BarServiceThrowsInvalidBarDataIdException() {
        // Setup
        given(mockBarService.getBarDataById(0L)).willThrow(InvalidBarDataIdException.class)

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
        given(mockBarService.getAllBars()).willReturn(barBeans)

        // Run the test and verify the results
        mockMvc.perform(get("/data/barsearch")
                .param("name", "name")
                .param("startDate", "startDate")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true))
        then(mockMetricsService).should().recordMetric("getBarWithMultipleGetParams success")
    }

    @Test
    void testGetBarWithMultipleGetParams_BarServiceReturnsNoItems() {
        // Setup
        given(mockBarService.getAllBars()).willReturn([])

        // Run the test and verify the results
        mockMvc.perform(get("/data/barsearch")
                .param("name", "name")
                .param("startDate", "startDate")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[]", true))
        then(mockMetricsService).should().recordMetric("getBarWithMultipleGetParams success")
    }

    @Test
    void testGetBarWithMultipleGetParams_BarServiceThrowsIOException() {
        // Setup
        given(mockBarService.getAllBars()).willThrow(IOException.class)

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
        then(mockBarService).should().saveBar(any(BarBean.class))
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
        then(mockBarService).should().saveBar(any(BarBean.class))
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
        then(mockBarService).should().saveBar(any(BarBean.class))
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
        then(mockBarService).should().saveBar(any(BarBean.class))
    }

    @Test
    void testDeleteBarWithId() {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(delete("/data/bar/{id}", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true))
        then(mockBarService).should().deleteBarWithId(0)
        then(mockMetricsService).should().recordMetric("deleteBarWithId success")
    }

    @Test
    void testDeleteBarWithId_BarServiceThrowsInvalidBarDataIdException() {
        // Setup
        willThrow(InvalidBarDataIdException.class).given(mockBarService).deleteBarWithId(0)

        // Run the test and verify the results
        mockMvc.perform(delete("/data/bar/{id}", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError())
                .andExpect(content().json("{}", true))
    }

    @Test
    void testDeleteBarWithId_BarServiceThrowsNetworkException() {
        // Setup
        willThrow(NetworkException.class).given(mockBarService).deleteBarWithId(0)

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
        assertThat(MyClass.getControllerId()).isEqualTo("MyClassController")
    }

    @Test
    void testUrlEncode() {
        assertThat(MyClass.urlEncode("paramValue")).isEqualTo("paramValue")
    }
}
