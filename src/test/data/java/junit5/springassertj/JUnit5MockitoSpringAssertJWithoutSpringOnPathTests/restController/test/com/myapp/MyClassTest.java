package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private FooService mockFooService;
    @Mock
    private BarService mockBarService;
    @Mock
    private MetricsServiceAdapter mockMetricsService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFooService, mockBarService, mockMetricsService);
    }

    @Test
    void testGetFooById() throws Exception {
        // Setup
        final FooData expectedResult = new FooData(0L, "name", "shortDisplayName",
                LocalDateTime.of(2020, 1, 1, 0, 0, 0));

        // Configure FooService.getFooDataById(...).
        final FooData fooData = new FooData(0L, "name", "shortDisplayName", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockFooService.getFooDataById(0L)).thenReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.getFooById(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
        verify(mockMetricsService).recordMetric("getFooById success");
    }

    @Test
    void testGetFooById_FooServiceThrowsInvalidFooDataIdException() throws Exception {
        // Setup
        when(mockFooService.getFooDataById(0L)).thenThrow(InvalidFooDataIdException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getFooById(0)).isInstanceOf(InvalidFooDataIdException.class);
    }

    @Test
    void testSafeGetFooById() throws Exception {
        // Setup
        final FooData expectedResult = new FooData(0L, "name", "shortDisplayName",
                LocalDateTime.of(2020, 1, 1, 0, 0, 0));

        // Configure FooService.getFooDataById(...).
        final FooData fooData = new FooData(0L, "name", "shortDisplayName", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockFooService.getFooDataById(0L)).thenReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.safeGetFooById(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
        verify(mockMetricsService).recordMetric("getFooById success");
    }

    @Test
    void testSafeGetFooById_FooServiceThrowsInvalidFooDataIdException() throws Exception {
        // Setup
        when(mockFooService.getFooDataById(0L)).thenThrow(InvalidFooDataIdException.class);

        // Run the test
        final FooData result = myClassUnderTest.safeGetFooById(0);

        // Verify the results
        assertThat(result).isNull();
        verify(mockMetricsService).recordMetric(eq("getFooById error"), any(InvalidFooDataIdException.class));
    }

    @Test
    void testGetAllFoos() throws Exception {
        // Setup
        final List<FooData> expectedResult = Arrays.asList(
                new FooData(0L, "name", "shortDisplayName", LocalDateTime.of(2020, 1, 1, 0, 0, 0)));

        // Configure FooService.getAllFoos(...).
        final List<FooData> fooData = Arrays.asList(
                new FooData(0L, "name", "shortDisplayName", LocalDateTime.of(2020, 1, 1, 0, 0, 0)));
        when(mockFooService.getAllFoos()).thenReturn(fooData);

        // Run the test
        final List<FooData> result = myClassUnderTest.getAllFoos();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
        verify(mockMetricsService).recordMetric("getAllFoos success");
    }

    @Test
    void testGetAllFoos_FooServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockFooService.getAllFoos()).thenReturn(Collections.emptyList());

        // Run the test
        final List<FooData> result = myClassUnderTest.getAllFoos();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
        verify(mockMetricsService).recordMetric("getAllFoos success");
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
        when(mockBarService.getBarDataById(0L)).thenReturn(barBean);

        // Run the test
        final BarBean result = myClassUnderTest.getBarById(0);

        // Verify the results
        verify(mockMetricsService).recordMetric("getBarById success");
    }

    @Test
    void testGetBarById_BarServiceThrowsInvalidBarDataIdException() throws Exception {
        // Setup
        when(mockBarService.getBarDataById(0L)).thenThrow(InvalidBarDataIdException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getBarById(0)).isInstanceOf(InvalidBarDataIdException.class);
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
        when(mockBarService.getBarDataById(0L)).thenReturn(barBean);

        // Run the test
        final BarBean result = myClassUnderTest.safeGetBarById(0);

        // Verify the results
        verify(mockMetricsService).recordMetric("getBarById success");
    }

    @Test
    void testSafeGetBarById_BarServiceThrowsInvalidBarDataIdException() throws Exception {
        // Setup
        when(mockBarService.getBarDataById(0L)).thenThrow(InvalidBarDataIdException.class);

        // Run the test
        final BarBean result = myClassUnderTest.safeGetBarById(0);

        // Verify the results
        assertThat(result).isNull();
        verify(mockMetricsService).recordMetric(eq("getBarById error"), any(InvalidBarDataIdException.class));
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
        when(mockBarService.getAllBars()).thenReturn(barBeans);

        // Run the test
        final List<BarBean> result = myClassUnderTest.getAllBars();

        // Verify the results
        verify(mockMetricsService).recordMetric("getAllBars success");
    }

    @Test
    void testGetAllBars_BarServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockBarService.getAllBars()).thenReturn(Collections.emptyList());

        // Run the test
        final List<BarBean> result = myClassUnderTest.getAllBars();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
        verify(mockMetricsService).recordMetric("getAllBars success");
    }

    @Test
    void testGetAllBars_BarServiceThrowsIOException() throws Exception {
        // Setup
        when(mockBarService.getAllBars()).thenThrow(IOException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getAllBars()).isInstanceOf(IOException.class);
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
        when(mockBarService.getBarDataById(0L)).thenReturn(barBean);

        // Run the test
        final BarBean result = myClassUnderTest.getBarWithGetParam("id");

        // Verify the results
        verify(mockMetricsService).recordMetric("getBarWithGetParam success");
    }

    @Test
    void testGetBarWithGetParam_BarServiceThrowsInvalidBarDataIdException() throws Exception {
        // Setup
        when(mockBarService.getBarDataById(0L)).thenThrow(InvalidBarDataIdException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getBarWithGetParam("id"))
                .isInstanceOf(InvalidBarDataIdException.class);
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
        when(mockBarService.getBarDataById(0L)).thenReturn(barBean);

        // Run the test
        final BarBean result = myClassUnderTest.getFavoriteBarFromCookie("id");

        // Verify the results
        verify(mockMetricsService).recordMetric("getFavoriteBarFromCookie success");
    }

    @Test
    void testGetFavoriteBarFromCookie_BarServiceThrowsInvalidBarDataIdException() throws Exception {
        // Setup
        when(mockBarService.getBarDataById(0L)).thenThrow(InvalidBarDataIdException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getFavoriteBarFromCookie("id"))
                .isInstanceOf(InvalidBarDataIdException.class);
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
        when(mockBarService.getBarDataById(0L)).thenReturn(barBean);

        // Run the test
        final ResponseEntity<BarBean> result = myClassUnderTest.getFavoriteBarFromCookieWithResponseEntity("id");

        // Verify the results
        verify(mockMetricsService).recordMetric("getFavoriteBarFromCookie success");
    }

    @Test
    void testGetFavoriteBarFromCookieWithResponseEntity_BarServiceThrowsInvalidBarDataIdException() throws Exception {
        // Setup
        when(mockBarService.getBarDataById(0L)).thenThrow(InvalidBarDataIdException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getFavoriteBarFromCookieWithResponseEntity("id"))
                .isInstanceOf(InvalidBarDataIdException.class);
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
        when(mockBarService.getAllBars()).thenReturn(barBeans);

        // Run the test
        final List<BarBean> result = myClassUnderTest.getBarWithMultipleGetParams("name", "startDate");

        // Verify the results
        verify(mockMetricsService).recordMetric("getBarWithMultipleGetParams success");
    }

    @Test
    void testGetBarWithMultipleGetParams_BarServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockBarService.getAllBars()).thenReturn(Collections.emptyList());

        // Run the test
        final List<BarBean> result = myClassUnderTest.getBarWithMultipleGetParams("name", "startDate");

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
        verify(mockMetricsService).recordMetric("getBarWithMultipleGetParams success");
    }

    @Test
    void testGetBarWithMultipleGetParams_BarServiceThrowsIOException() throws Exception {
        // Setup
        when(mockBarService.getAllBars()).thenThrow(IOException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getBarWithMultipleGetParams("name", "startDate"))
                .isInstanceOf(IOException.class);
    }

    @Test
    void testPostNewBar() {
        // Setup
        final BarBean barBean = new BarBean();
        barBean.setId(0L);
        barBean.setName("name");
        barBean.setShortDisplayName("shortDisplayName");
        barBean.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));

        // Run the test
        myClassUnderTest.postNewBar(barBean);

        // Verify the results
        verify(mockBarService).saveBar(any(BarBean.class));
    }

    @Test
    void testPutNewBar() {
        // Setup
        final BarBean barBean = new BarBean();
        barBean.setId(0L);
        barBean.setName("name");
        barBean.setShortDisplayName("shortDisplayName");
        barBean.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));

        // Run the test
        myClassUnderTest.putNewBar(barBean);

        // Verify the results
        verify(mockBarService).saveBar(any(BarBean.class));
    }

    @Test
    void testPutNewBarWithHttpEntity() {
        // Setup
        final HttpEntity<BarBean> entity = null;

        // Run the test
        myClassUnderTest.putNewBarWithHttpEntity(entity);

        // Verify the results
        verify(mockBarService).saveBar(any(BarBean.class));
    }

    @Test
    void testPatchBar() {
        // Setup
        final BarBean barBean = new BarBean();
        barBean.setId(0L);
        barBean.setName("name");
        barBean.setShortDisplayName("shortDisplayName");
        barBean.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));

        // Run the test
        myClassUnderTest.patchBar(barBean);

        // Verify the results
        verify(mockBarService).saveBar(any(BarBean.class));
    }

    @Test
    void testDeleteBarWithId() throws Exception {
        // Setup
        // Run the test
        myClassUnderTest.deleteBarWithId(0);

        // Verify the results
        verify(mockBarService).deleteBarWithId(0);
        verify(mockMetricsService).recordMetric("deleteBarWithId success");
    }

    @Test
    void testDeleteBarWithId_BarServiceThrowsInvalidBarDataIdException() throws Exception {
        // Setup
        doThrow(InvalidBarDataIdException.class).when(mockBarService).deleteBarWithId(0);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.deleteBarWithId(0)).isInstanceOf(InvalidBarDataIdException.class);
    }

    @Test
    void testDeleteBarWithId_BarServiceThrowsNetworkException() throws Exception {
        // Setup
        doThrow(NetworkException.class).when(mockBarService).deleteBarWithId(0);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.deleteBarWithId(0)).isInstanceOf(NetworkException.class);
    }

    @Test
    void testTryHttpRequestStuff() {
        MyClass.tryHttpRequestStuff(null);
    }

    @Test
    void testTryHttpStuff() {
        MyClass.tryHttpStuff(null);
    }

    @Test
    void testGetBar() {
        assertThat(myClassUnderTest.getBar()).isEqualTo("bar");
    }

    @Test
    void testGetControllerId() {
        assertThat(MyClass.getControllerId()).isEqualTo("MyClassController");
    }

    @Test
    void testUrlEncode() {
        assertThat(MyClass.urlEncode("paramValue")).isEqualTo("paramValue");
    }
}
