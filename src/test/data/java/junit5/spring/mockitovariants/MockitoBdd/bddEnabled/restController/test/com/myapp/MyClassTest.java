package com.myapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.Cookie;
import java.io.IOException;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MyClass.class)
class MyClassTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FooService mockFooService;
    @MockBean
    private BarService mockBarService;
    @MockBean
    private MetricsServiceAdapter mockMetricsService;

    @Test
    void testGetFooById() throws Exception {
        // Setup
        // Configure FooService.getFooDataById(...).
        final FooData fooData = new FooData(0L, "name", "shortDisplayName", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        given(mockFooService.getFooDataById(0L)).willReturn(fooData);

        // Run the test and verify the results
        mockMvc.perform(get("/data/foo/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
        then(mockMetricsService).should().recordMetric("getFooById success");
    }

    @Test
    void testGetFooById_FooServiceThrowsInvalidFooDataIdException() throws Exception {
        // Setup
        given(mockFooService.getFooDataById(0L)).willThrow(InvalidFooDataIdException.class);

        // Run the test and verify the results
        mockMvc.perform(get("/data/foo/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError())
                .andExpect(content().json("{}", true));
    }

    @Test
    void testSafeGetFooById() throws Exception {
        // Setup
        // Configure FooService.getFooDataById(...).
        final FooData fooData = new FooData(0L, "name", "shortDisplayName", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        given(mockFooService.getFooDataById(0L)).willReturn(fooData);

        // Run the test and verify the results
        mockMvc.perform(get("/data/safefoo/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
        then(mockMetricsService).should().recordMetric("getFooById success");
    }

    @Test
    void testSafeGetFooById_FooServiceThrowsInvalidFooDataIdException() throws Exception {
        // Setup
        given(mockFooService.getFooDataById(0L)).willThrow(InvalidFooDataIdException.class);

        // Run the test and verify the results
        mockMvc.perform(get("/data/safefoo/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("", true));
        then(mockMetricsService).should().recordMetric(eq("getFooById error"), any(InvalidFooDataIdException.class));
    }

    @Test
    void testGetAllFoos() throws Exception {
        // Setup
        // Configure FooService.getAllFoos(...).
        final List<FooData> fooData = Arrays.asList(
                new FooData(0L, "name", "shortDisplayName", LocalDateTime.of(2020, 1, 1, 0, 0, 0)));
        given(mockFooService.getAllFoos()).willReturn(fooData);

        // Run the test and verify the results
        mockMvc.perform(get("/data/foos/")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
        then(mockMetricsService).should().recordMetric("getAllFoos success");
    }

    @Test
    void testGetAllFoos_FooServiceReturnsNoItems() throws Exception {
        // Setup
        given(mockFooService.getAllFoos()).willReturn(Collections.emptyList());

        // Run the test and verify the results
        mockMvc.perform(get("/data/foos/")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[]", true));
        then(mockMetricsService).should().recordMetric("getAllFoos success");
    }

    @Test
    void testGetBarById() throws Exception {
        // Setup
        // Configure BarService.getBarDataById(...).
        final BarBean barBean = new BarBean();
        barBean.setId(0L);
        barBean.setName("name");
        barBean.setShortDisplayName("shortDisplayName");
        barBean.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        given(mockBarService.getBarDataById(0L)).willReturn(barBean);

        // Run the test and verify the results
        mockMvc.perform(get("/data/bar/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
        then(mockMetricsService).should().recordMetric("getBarById success");
    }

    @Test
    void testGetBarById_BarServiceThrowsInvalidBarDataIdException() throws Exception {
        // Setup
        given(mockBarService.getBarDataById(0L)).willThrow(InvalidBarDataIdException.class);

        // Run the test and verify the results
        mockMvc.perform(get("/data/bar/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError())
                .andExpect(content().json("{}", true));
    }

    @Test
    void testSafeGetBarById() throws Exception {
        // Setup
        // Configure BarService.getBarDataById(...).
        final BarBean barBean = new BarBean();
        barBean.setId(0L);
        barBean.setName("name");
        barBean.setShortDisplayName("shortDisplayName");
        barBean.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        given(mockBarService.getBarDataById(0L)).willReturn(barBean);

        // Run the test and verify the results
        mockMvc.perform(get("/data/safebar/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
        then(mockMetricsService).should().recordMetric("getBarById success");
    }

    @Test
    void testSafeGetBarById_BarServiceThrowsInvalidBarDataIdException() throws Exception {
        // Setup
        given(mockBarService.getBarDataById(0L)).willThrow(InvalidBarDataIdException.class);

        // Run the test and verify the results
        mockMvc.perform(get("/data/safebar/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("", true));
        then(mockMetricsService).should().recordMetric(eq("getBarById error"), any(InvalidBarDataIdException.class));
    }

    @Test
    void testGetAllBars() throws Exception {
        // Setup
        // Configure BarService.getAllBars(...).
        final BarBean barBean = new BarBean();
        barBean.setId(0L);
        barBean.setName("name");
        barBean.setShortDisplayName("shortDisplayName");
        barBean.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final List<BarBean> barBeans = Arrays.asList(barBean);
        given(mockBarService.getAllBars()).willReturn(barBeans);

        // Run the test and verify the results
        mockMvc.perform(get("/data/bars/")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
        then(mockMetricsService).should().recordMetric("getAllBars success");
    }

    @Test
    void testGetAllBars_BarServiceReturnsNoItems() throws Exception {
        // Setup
        given(mockBarService.getAllBars()).willReturn(Collections.emptyList());

        // Run the test and verify the results
        mockMvc.perform(get("/data/bars/")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[]", true));
        then(mockMetricsService).should().recordMetric("getAllBars success");
    }

    @Test
    void testGetAllBars_BarServiceThrowsIOException() throws Exception {
        // Setup
        given(mockBarService.getAllBars()).willThrow(IOException.class);

        // Run the test and verify the results
        mockMvc.perform(get("/data/bars/")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError())
                .andExpect(content().json("{}", true));
    }

    @Test
    void testGetBarWithGetParam() throws Exception {
        // Setup
        // Configure BarService.getBarDataById(...).
        final BarBean barBean = new BarBean();
        barBean.setId(0L);
        barBean.setName("name");
        barBean.setShortDisplayName("shortDisplayName");
        barBean.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        given(mockBarService.getBarDataById(0L)).willReturn(barBean);

        // Run the test and verify the results
        mockMvc.perform(get("/data/bar")
                        .param("id", "id")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
        then(mockMetricsService).should().recordMetric("getBarWithGetParam success");
    }

    @Test
    void testGetBarWithGetParam_BarServiceThrowsInvalidBarDataIdException() throws Exception {
        // Setup
        given(mockBarService.getBarDataById(0L)).willThrow(InvalidBarDataIdException.class);

        // Run the test and verify the results
        mockMvc.perform(get("/data/bar")
                        .param("id", "id")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError())
                .andExpect(content().json("{}", true));
    }

    @Test
    void testGetFavoriteBarFromCookie() throws Exception {
        // Setup
        // Configure BarService.getBarDataById(...).
        final BarBean barBean = new BarBean();
        barBean.setId(0L);
        barBean.setName("name");
        barBean.setShortDisplayName("shortDisplayName");
        barBean.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        given(mockBarService.getBarDataById(0L)).willReturn(barBean);

        // Run the test and verify the results
        mockMvc.perform(get("/data/favoriteBar")
                        .cookie(new Cookie("favoriteBarId", "id"))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
        then(mockMetricsService).should().recordMetric("getFavoriteBarFromCookie success");
    }

    @Test
    void testGetFavoriteBarFromCookie_BarServiceThrowsInvalidBarDataIdException() throws Exception {
        // Setup
        given(mockBarService.getBarDataById(0L)).willThrow(InvalidBarDataIdException.class);

        // Run the test and verify the results
        mockMvc.perform(get("/data/favoriteBar")
                        .cookie(new Cookie("favoriteBarId", "id"))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError())
                .andExpect(content().json("{}", true));
    }

    @Test
    void testGetFavoriteBarFromCookieWithResponseEntity() throws Exception {
        // Setup
        // Configure BarService.getBarDataById(...).
        final BarBean barBean = new BarBean();
        barBean.setId(0L);
        barBean.setName("name");
        barBean.setShortDisplayName("shortDisplayName");
        barBean.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        given(mockBarService.getBarDataById(0L)).willReturn(barBean);

        // Run the test and verify the results
        mockMvc.perform(get("/data/favoriteBarWithResponseEntity")
                        .cookie(new Cookie("favoriteBarId", "id"))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
        then(mockMetricsService).should().recordMetric("getFavoriteBarFromCookie success");
    }

    @Test
    void testGetFavoriteBarFromCookieWithResponseEntity_BarServiceThrowsInvalidBarDataIdException() throws Exception {
        // Setup
        given(mockBarService.getBarDataById(0L)).willThrow(InvalidBarDataIdException.class);

        // Run the test and verify the results
        mockMvc.perform(get("/data/favoriteBarWithResponseEntity")
                        .cookie(new Cookie("favoriteBarId", "id"))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError())
                .andExpect(content().json("{}", true));
    }

    @Test
    void testGetBarWithMultipleGetParams() throws Exception {
        // Setup
        // Configure BarService.getAllBars(...).
        final BarBean barBean = new BarBean();
        barBean.setId(0L);
        barBean.setName("name");
        barBean.setShortDisplayName("shortDisplayName");
        barBean.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final List<BarBean> barBeans = Arrays.asList(barBean);
        given(mockBarService.getAllBars()).willReturn(barBeans);

        // Run the test and verify the results
        mockMvc.perform(get("/data/barsearch")
                        .param("name", "name")
                        .param("startDate", "startDate")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
        then(mockMetricsService).should().recordMetric("getBarWithMultipleGetParams success");
    }

    @Test
    void testGetBarWithMultipleGetParams_BarServiceReturnsNoItems() throws Exception {
        // Setup
        given(mockBarService.getAllBars()).willReturn(Collections.emptyList());

        // Run the test and verify the results
        mockMvc.perform(get("/data/barsearch")
                        .param("name", "name")
                        .param("startDate", "startDate")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[]", true));
        then(mockMetricsService).should().recordMetric("getBarWithMultipleGetParams success");
    }

    @Test
    void testGetBarWithMultipleGetParams_BarServiceThrowsIOException() throws Exception {
        // Setup
        given(mockBarService.getAllBars()).willThrow(IOException.class);

        // Run the test and verify the results
        mockMvc.perform(get("/data/barsearch")
                        .param("name", "name")
                        .param("startDate", "startDate")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError())
                .andExpect(content().json("{}", true));
    }

    @Test
    void testPostNewBar() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(post("/data/postBar")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
        then(mockBarService).should().saveBar(any(BarBean.class));
    }

    @Test
    void testPutNewBar() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(put("/data/putBar")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
        then(mockBarService).should().saveBar(any(BarBean.class));
    }

    @Test
    void testPutNewBarWithHttpEntity() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(put("/data/putBarWithHttpEntity")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
        then(mockBarService).should().saveBar(any(BarBean.class));
    }

    @Test
    void testPatchBar() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(patch("/data/patchBar")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
        then(mockBarService).should().saveBar(any(BarBean.class));
    }

    @Test
    void testDeleteBarWithId() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(delete("/data/bar/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
        then(mockBarService).should().deleteBarWithId(0);
        then(mockMetricsService).should().recordMetric("deleteBarWithId success");
    }

    @Test
    void testDeleteBarWithId_BarServiceThrowsInvalidBarDataIdException() throws Exception {
        // Setup
        willThrow(InvalidBarDataIdException.class).given(mockBarService).deleteBarWithId(0);

        // Run the test and verify the results
        mockMvc.perform(delete("/data/bar/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError())
                .andExpect(content().json("{}", true));
    }

    @Test
    void testDeleteBarWithId_BarServiceThrowsNetworkException() throws Exception {
        // Setup
        willThrow(NetworkException.class).given(mockBarService).deleteBarWithId(0);

        // Run the test and verify the results
        mockMvc.perform(delete("/data/bar/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError())
                .andExpect(content().json("{}", true));
    }

    @Test
    void testTryHttpRequestStuff() throws Exception {
        // Setup
        final BarBean barBean = new BarBean();
        barBean.setId(0L);
        barBean.setName("name");
        barBean.setShortDisplayName("shortDisplayName");
        barBean.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final RequestEntity<BarBean> request = new RequestEntity<>(barBean, HttpMethod.GET,
                new URI("https://example.com/"));

        // Run the test
        MyClass.tryHttpRequestStuff(request);

        // Verify the results
    }

    @Test
    void testTryHttpStuff() {
        // Setup
        final BarBean barBean = new BarBean();
        barBean.setId(0L);
        barBean.setName("name");
        barBean.setShortDisplayName("shortDisplayName");
        barBean.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final HttpEntity<BarBean> request = new HttpEntity<>(barBean, new HttpHeaders());

        // Run the test
        MyClass.tryHttpStuff(request);

        // Verify the results
    }

    @Test
    void testGetControllerId() {
        assertEquals("MyClassController", MyClass.getControllerId());
    }

    @Test
    void testUrlEncode() {
        assertEquals("paramValue", MyClass.urlEncode("paramValue"));
    }
}
