package com.myapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.Cookie;
import java.io.IOException;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MyClass.class)
public class MyClassTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FooService mockFooService;
    @MockBean
    private BarService mockBarService;
    @MockBean
    private MetricsServiceAdapter mockMetricsService;

    @Test
    public void testGetFooById() throws Exception {
        // Setup
        // Configure FooService.getFooDataById(...).
        final FooData fooData = new FooData(0L, "name", "shortDisplayName", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockFooService.getFooDataById(0L)).thenReturn(fooData);

        // Run the test and verify the results
        mockMvc.perform(get("/data/foo/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
        verify(mockMetricsService).recordMetric("getFooById success");
    }

    @Test
    public void testGetFooById_FooServiceThrowsInvalidFooDataIdException() throws Exception {
        // Setup
        when(mockFooService.getFooDataById(0L)).thenThrow(InvalidFooDataIdException.class);

        // Run the test and verify the results
        mockMvc.perform(get("/data/foo/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError())
                .andExpect(content().json("{}", true));
    }

    @Test
    public void testSafeGetFooById() throws Exception {
        // Setup
        // Configure FooService.getFooDataById(...).
        final FooData fooData = new FooData(0L, "name", "shortDisplayName", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockFooService.getFooDataById(0L)).thenReturn(fooData);

        // Run the test and verify the results
        mockMvc.perform(get("/data/safefoo/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
        verify(mockMetricsService).recordMetric("getFooById success");
    }

    @Test
    public void testSafeGetFooById_FooServiceThrowsInvalidFooDataIdException() throws Exception {
        // Setup
        when(mockFooService.getFooDataById(0L)).thenThrow(InvalidFooDataIdException.class);

        // Run the test and verify the results
        mockMvc.perform(get("/data/safefoo/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("", true));
        verify(mockMetricsService).recordMetric(eq("getFooById error"), any(InvalidFooDataIdException.class));
    }

    @Test
    public void testGetAllFoos() throws Exception {
        // Setup
        // Configure FooService.getAllFoos(...).
        final List<FooData> fooData = Arrays.asList(
                new FooData(0L, "name", "shortDisplayName", LocalDateTime.of(2020, 1, 1, 0, 0, 0)));
        when(mockFooService.getAllFoos()).thenReturn(fooData);

        // Run the test and verify the results
        mockMvc.perform(get("/data/foos/")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
        verify(mockMetricsService).recordMetric("getAllFoos success");
    }

    @Test
    public void testGetAllFoos_FooServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockFooService.getAllFoos()).thenReturn(Collections.emptyList());

        // Run the test and verify the results
        mockMvc.perform(get("/data/foos/")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[]", true));
        verify(mockMetricsService).recordMetric("getAllFoos success");
    }

    @Test
    public void testGetBarById() throws Exception {
        // Setup
        // Configure BarService.getBarDataById(...).
        final BarBean barBean = new BarBean();
        barBean.setId(0L);
        barBean.setName("name");
        barBean.setShortDisplayName("shortDisplayName");
        barBean.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarDataById(0L)).thenReturn(barBean);

        // Run the test and verify the results
        mockMvc.perform(get("/data/bar/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
        verify(mockMetricsService).recordMetric("getBarById success");
    }

    @Test
    public void testGetBarById_BarServiceThrowsInvalidBarDataIdException() throws Exception {
        // Setup
        when(mockBarService.getBarDataById(0L)).thenThrow(InvalidBarDataIdException.class);

        // Run the test and verify the results
        mockMvc.perform(get("/data/bar/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError())
                .andExpect(content().json("{}", true));
    }

    @Test
    public void testSafeGetBarById() throws Exception {
        // Setup
        // Configure BarService.getBarDataById(...).
        final BarBean barBean = new BarBean();
        barBean.setId(0L);
        barBean.setName("name");
        barBean.setShortDisplayName("shortDisplayName");
        barBean.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarDataById(0L)).thenReturn(barBean);

        // Run the test and verify the results
        mockMvc.perform(get("/data/safebar/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
        verify(mockMetricsService).recordMetric("getBarById success");
    }

    @Test
    public void testSafeGetBarById_BarServiceThrowsInvalidBarDataIdException() throws Exception {
        // Setup
        when(mockBarService.getBarDataById(0L)).thenThrow(InvalidBarDataIdException.class);

        // Run the test and verify the results
        mockMvc.perform(get("/data/safebar/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("", true));
        verify(mockMetricsService).recordMetric(eq("getBarById error"), any(InvalidBarDataIdException.class));
    }

    @Test
    public void testGetAllBars() throws Exception {
        // Setup
        // Configure BarService.getAllBars(...).
        final BarBean barBean = new BarBean();
        barBean.setId(0L);
        barBean.setName("name");
        barBean.setShortDisplayName("shortDisplayName");
        barBean.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final List<BarBean> barBeans = Arrays.asList(barBean);
        when(mockBarService.getAllBars()).thenReturn(barBeans);

        // Run the test and verify the results
        mockMvc.perform(get("/data/bars/")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
        verify(mockMetricsService).recordMetric("getAllBars success");
    }

    @Test
    public void testGetAllBars_BarServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockBarService.getAllBars()).thenReturn(Collections.emptyList());

        // Run the test and verify the results
        mockMvc.perform(get("/data/bars/")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[]", true));
        verify(mockMetricsService).recordMetric("getAllBars success");
    }

    @Test
    public void testGetAllBars_BarServiceThrowsIOException() throws Exception {
        // Setup
        when(mockBarService.getAllBars()).thenThrow(IOException.class);

        // Run the test and verify the results
        mockMvc.perform(get("/data/bars/")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError())
                .andExpect(content().json("{}", true));
    }

    @Test
    public void testGetBarWithGetParam() throws Exception {
        // Setup
        // Configure BarService.getBarDataById(...).
        final BarBean barBean = new BarBean();
        barBean.setId(0L);
        barBean.setName("name");
        barBean.setShortDisplayName("shortDisplayName");
        barBean.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarDataById(0L)).thenReturn(barBean);

        // Run the test and verify the results
        mockMvc.perform(get("/data/bar")
                        .param("id", "id")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
        verify(mockMetricsService).recordMetric("getBarWithGetParam success");
    }

    @Test
    public void testGetBarWithGetParam_BarServiceThrowsInvalidBarDataIdException() throws Exception {
        // Setup
        when(mockBarService.getBarDataById(0L)).thenThrow(InvalidBarDataIdException.class);

        // Run the test and verify the results
        mockMvc.perform(get("/data/bar")
                        .param("id", "id")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError())
                .andExpect(content().json("{}", true));
    }

    @Test
    public void testGetFavoriteBarFromCookie() throws Exception {
        // Setup
        // Configure BarService.getBarDataById(...).
        final BarBean barBean = new BarBean();
        barBean.setId(0L);
        barBean.setName("name");
        barBean.setShortDisplayName("shortDisplayName");
        barBean.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarDataById(0L)).thenReturn(barBean);

        // Run the test and verify the results
        mockMvc.perform(get("/data/favoriteBar")
                        .cookie(new Cookie("favoriteBarId", "id"))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
        verify(mockMetricsService).recordMetric("getFavoriteBarFromCookie success");
    }

    @Test
    public void testGetFavoriteBarFromCookie_BarServiceThrowsInvalidBarDataIdException() throws Exception {
        // Setup
        when(mockBarService.getBarDataById(0L)).thenThrow(InvalidBarDataIdException.class);

        // Run the test and verify the results
        mockMvc.perform(get("/data/favoriteBar")
                        .cookie(new Cookie("favoriteBarId", "id"))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError())
                .andExpect(content().json("{}", true));
    }

    @Test
    public void testGetFavoriteBarFromCookieWithResponseEntity() throws Exception {
        // Setup
        // Configure BarService.getBarDataById(...).
        final BarBean barBean = new BarBean();
        barBean.setId(0L);
        barBean.setName("name");
        barBean.setShortDisplayName("shortDisplayName");
        barBean.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarDataById(0L)).thenReturn(barBean);

        // Run the test and verify the results
        mockMvc.perform(get("/data/favoriteBarWithResponseEntity")
                        .cookie(new Cookie("favoriteBarId", "id"))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
        verify(mockMetricsService).recordMetric("getFavoriteBarFromCookie success");
    }

    @Test
    public void testGetFavoriteBarFromCookieWithResponseEntity_BarServiceThrowsInvalidBarDataIdException() throws Exception {
        // Setup
        when(mockBarService.getBarDataById(0L)).thenThrow(InvalidBarDataIdException.class);

        // Run the test and verify the results
        mockMvc.perform(get("/data/favoriteBarWithResponseEntity")
                        .cookie(new Cookie("favoriteBarId", "id"))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError())
                .andExpect(content().json("{}", true));
    }

    @Test
    public void testGetBarWithMultipleGetParams() throws Exception {
        // Setup
        // Configure BarService.getAllBars(...).
        final BarBean barBean = new BarBean();
        barBean.setId(0L);
        barBean.setName("name");
        barBean.setShortDisplayName("shortDisplayName");
        barBean.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final List<BarBean> barBeans = Arrays.asList(barBean);
        when(mockBarService.getAllBars()).thenReturn(barBeans);

        // Run the test and verify the results
        mockMvc.perform(get("/data/barsearch")
                        .param("name", "name")
                        .param("startDate", "startDate")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
        verify(mockMetricsService).recordMetric("getBarWithMultipleGetParams success");
    }

    @Test
    public void testGetBarWithMultipleGetParams_BarServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockBarService.getAllBars()).thenReturn(Collections.emptyList());

        // Run the test and verify the results
        mockMvc.perform(get("/data/barsearch")
                        .param("name", "name")
                        .param("startDate", "startDate")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[]", true));
        verify(mockMetricsService).recordMetric("getBarWithMultipleGetParams success");
    }

    @Test
    public void testGetBarWithMultipleGetParams_BarServiceThrowsIOException() throws Exception {
        // Setup
        when(mockBarService.getAllBars()).thenThrow(IOException.class);

        // Run the test and verify the results
        mockMvc.perform(get("/data/barsearch")
                        .param("name", "name")
                        .param("startDate", "startDate")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError())
                .andExpect(content().json("{}", true));
    }

    @Test
    public void testPostNewBar() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(post("/data/postBar")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
        verify(mockBarService).saveBar(any(BarBean.class));
    }

    @Test
    public void testPutNewBar() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(put("/data/putBar")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
        verify(mockBarService).saveBar(any(BarBean.class));
    }

    @Test
    public void testPutNewBarWithHttpEntity() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(put("/data/putBarWithHttpEntity")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
        verify(mockBarService).saveBar(any(BarBean.class));
    }

    @Test
    public void testPatchBar() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(patch("/data/patchBar")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
        verify(mockBarService).saveBar(any(BarBean.class));
    }

    @Test
    public void testDeleteBarWithId() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(delete("/data/bar/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
        verify(mockBarService).deleteBarWithId(0);
        verify(mockMetricsService).recordMetric("deleteBarWithId success");
    }

    @Test
    public void testDeleteBarWithId_BarServiceThrowsInvalidBarDataIdException() throws Exception {
        // Setup
        doThrow(InvalidBarDataIdException.class).when(mockBarService).deleteBarWithId(0);

        // Run the test and verify the results
        mockMvc.perform(delete("/data/bar/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError())
                .andExpect(content().json("{}", true));
    }

    @Test
    public void testDeleteBarWithId_BarServiceThrowsNetworkException() throws Exception {
        // Setup
        doThrow(NetworkException.class).when(mockBarService).deleteBarWithId(0);

        // Run the test and verify the results
        mockMvc.perform(delete("/data/bar/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError())
                .andExpect(content().json("{}", true));
    }

    @Test
    public void testTryHttpRequestStuff() throws Exception {
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
    public void testTryHttpStuff() {
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
    public void testGetControllerId() {
        assertThat(MyClass.getControllerId()).isEqualTo("MyClassController");
    }

    @Test
    public void testUrlEncode() {
        assertThat(MyClass.urlEncode("paramValue")).isEqualTo("paramValue");
    }
}
