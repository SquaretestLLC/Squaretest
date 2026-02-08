package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private BarService mockBarService;
    @Mock
    private MetricsServiceAdapter mockMetricsServiceAdapter;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockBarService, mockMetricsServiceAdapter);
    }

    @Test
    void testGreeting() throws Exception {
        // Setup
        final BarBean barBeanFromModel = new BarBean();
        barBeanFromModel.setId(0L);
        barBeanFromModel.setName("name");
        barBeanFromModel.setShortDisplayName("shortDisplayName");
        barBeanFromModel.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));

        final Model model = null;

        // Configure BarService.getBarDataById(...).
        final BarBean barBean = new BarBean();
        barBean.setId(0L);
        barBean.setName("name");
        barBean.setShortDisplayName("shortDisplayName");
        barBean.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarDataById(0L)).thenReturn(barBean);

        // Run the test
        final String result = myClassUnderTest.greeting(barBeanFromModel, "name", model);

        // Verify the results
        assertThat(result).isEqualTo("greeting");
    }

    @Test
    void testGreeting_BarServiceThrowsInvalidBarDataIdException() throws Exception {
        // Setup
        final BarBean barBeanFromModel = new BarBean();
        barBeanFromModel.setId(0L);
        barBeanFromModel.setName("name");
        barBeanFromModel.setShortDisplayName("shortDisplayName");
        barBeanFromModel.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));

        final Model model = null;
        when(mockBarService.getBarDataById(0L)).thenThrow(InvalidBarDataIdException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.greeting(barBeanFromModel, "name", model))
                .isInstanceOf(InvalidBarDataIdException.class);
    }

    @Test
    void testGreeting1() throws Exception {
        // Setup
        final BarBean barBeanFromModel = new BarBean();
        barBeanFromModel.setId(0L);
        barBeanFromModel.setName("name");
        barBeanFromModel.setShortDisplayName("shortDisplayName");
        barBeanFromModel.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));

        final Model model = null;

        // Configure BarService.getBarDataById(...).
        final BarBean barBean = new BarBean();
        barBean.setId(0L);
        barBean.setName("name");
        barBean.setShortDisplayName("shortDisplayName");
        barBean.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarDataById(0L)).thenReturn(barBean);

        // Run the test
        final String result = myClassUnderTest.greeting1(Locale.US, barBeanFromModel, "name", model);

        // Verify the results
        assertThat(result).isEqualTo("greeting");
        verify(mockBarService).saveBar(any(BarBean.class));
        verify(mockMetricsServiceAdapter).recordMetric("greeting1");
    }

    @Test
    void testGreeting1_BarServiceGetBarDataByIdThrowsInvalidBarDataIdException() throws Exception {
        // Setup
        final BarBean barBeanFromModel = new BarBean();
        barBeanFromModel.setId(0L);
        barBeanFromModel.setName("name");
        barBeanFromModel.setShortDisplayName("shortDisplayName");
        barBeanFromModel.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));

        final Model model = null;
        when(mockBarService.getBarDataById(0L)).thenThrow(InvalidBarDataIdException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.greeting1(Locale.US, barBeanFromModel, "name", model))
                .isInstanceOf(InvalidBarDataIdException.class);
    }

    @Test
    void testGreeting2() throws Exception {
        // Setup
        final BarBean barBeanFromModel = new BarBean();
        barBeanFromModel.setId(0L);
        barBeanFromModel.setName("name");
        barBeanFromModel.setShortDisplayName("shortDisplayName");
        barBeanFromModel.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));

        final Model model = null;

        // Configure BarService.getBarDataById(...).
        final BarBean barBean = new BarBean();
        barBean.setId(0L);
        barBean.setName("name");
        barBean.setShortDisplayName("shortDisplayName");
        barBean.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarDataById(0L)).thenReturn(barBean);

        // Run the test
        final String result = myClassUnderTest.greeting2(Locale.US, barBeanFromModel, "name", model);

        // Verify the results
        assertThat(result).isEqualTo("greeting");
        verify(mockBarService).saveBar(any(BarBean.class));
        verify(mockMetricsServiceAdapter).recordMetric("greeting1");
    }

    @Test
    void testGreeting2_BarServiceGetBarDataByIdThrowsInvalidBarDataIdException() throws Exception {
        // Setup
        final BarBean barBeanFromModel = new BarBean();
        barBeanFromModel.setId(0L);
        barBeanFromModel.setName("name");
        barBeanFromModel.setShortDisplayName("shortDisplayName");
        barBeanFromModel.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));

        final Model model = null;
        when(mockBarService.getBarDataById(0L)).thenThrow(InvalidBarDataIdException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.greeting2(Locale.US, barBeanFromModel, "name", model))
                .isInstanceOf(InvalidBarDataIdException.class);
    }

    @Test
    void testGreetAll() throws Exception {
        // Setup
        final BarBean barBeanFromModel = new BarBean();
        barBeanFromModel.setId(0L);
        barBeanFromModel.setName("name");
        barBeanFromModel.setShortDisplayName("shortDisplayName");
        barBeanFromModel.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));

        final Model model = null;

        // Configure BarService.getBarDataById(...).
        final BarBean barBean = new BarBean();
        barBean.setId(0L);
        barBean.setName("name");
        barBean.setShortDisplayName("shortDisplayName");
        barBean.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarDataById(0L)).thenReturn(barBean);

        // Configure BarService.getAllBars(...).
        final BarBean barBean1 = new BarBean();
        barBean1.setId(0L);
        barBean1.setName("name");
        barBean1.setShortDisplayName("shortDisplayName");
        barBean1.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final List<BarBean> barBeans = Arrays.asList(barBean1);
        when(mockBarService.getAllBars()).thenReturn(barBeans);

        // Run the test
        final String result = myClassUnderTest.greetAll(barBeanFromModel, "name", model);

        // Verify the results
        assertThat(result).isEqualTo("greeting");
        verify(mockMetricsServiceAdapter).recordMetric("greetAll");
    }

    @Test
    void testGreetAll_BarServiceGetBarDataByIdThrowsInvalidBarDataIdException() throws Exception {
        // Setup
        final BarBean barBeanFromModel = new BarBean();
        barBeanFromModel.setId(0L);
        barBeanFromModel.setName("name");
        barBeanFromModel.setShortDisplayName("shortDisplayName");
        barBeanFromModel.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));

        final Model model = null;
        when(mockBarService.getBarDataById(0L)).thenThrow(InvalidBarDataIdException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.greetAll(barBeanFromModel, "name", model))
                .isInstanceOf(InvalidBarDataIdException.class);
    }

    @Test
    void testGreetAll_BarServiceGetAllBarsReturnsNoItems() throws Exception {
        // Setup
        final BarBean barBeanFromModel = new BarBean();
        barBeanFromModel.setId(0L);
        barBeanFromModel.setName("name");
        barBeanFromModel.setShortDisplayName("shortDisplayName");
        barBeanFromModel.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));

        final Model model = null;

        // Configure BarService.getBarDataById(...).
        final BarBean barBean = new BarBean();
        barBean.setId(0L);
        barBean.setName("name");
        barBean.setShortDisplayName("shortDisplayName");
        barBean.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarDataById(0L)).thenReturn(barBean);

        when(mockBarService.getAllBars()).thenReturn(Collections.emptyList());

        // Run the test
        final String result = myClassUnderTest.greetAll(barBeanFromModel, "name", model);

        // Verify the results
        assertThat(result).isEqualTo("greeting");
        verify(mockMetricsServiceAdapter).recordMetric("greetAll");
    }

    @Test
    void testGreetAll_BarServiceGetAllBarsThrowsIOException() throws Exception {
        // Setup
        final BarBean barBeanFromModel = new BarBean();
        barBeanFromModel.setId(0L);
        barBeanFromModel.setName("name");
        barBeanFromModel.setShortDisplayName("shortDisplayName");
        barBeanFromModel.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));

        final Model model = null;

        // Configure BarService.getBarDataById(...).
        final BarBean barBean = new BarBean();
        barBean.setId(0L);
        barBean.setName("name");
        barBean.setShortDisplayName("shortDisplayName");
        barBean.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarDataById(0L)).thenReturn(barBean);

        when(mockBarService.getAllBars()).thenThrow(IOException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.greetAll(barBeanFromModel, "name", model))
                .isInstanceOf(IOException.class);
    }

    @Test
    void testLoadBarForSession() throws Exception {
        // Setup
        // Configure BarService.getBarDataById(...).
        final BarBean barBean = new BarBean();
        barBean.setId(0L);
        barBean.setName("name");
        barBean.setShortDisplayName("shortDisplayName");
        barBean.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarDataById(0L)).thenReturn(barBean);

        // Run the test
        final BarBean result = myClassUnderTest.loadBarForSession(0L);

        // Verify the results
    }

    @Test
    void testLoadBarForSession_BarServiceThrowsInvalidBarDataIdException() throws Exception {
        // Setup
        when(mockBarService.getBarDataById(0L)).thenThrow(InvalidBarDataIdException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.loadBarForSession(0L)).isInstanceOf(InvalidBarDataIdException.class);
    }

    @Test
    void testHelperToIgnore() throws Exception {
        // Setup
        // Configure BarService.getBarDataById(...).
        final BarBean barBean = new BarBean();
        barBean.setId(0L);
        barBean.setName("name");
        barBean.setShortDisplayName("shortDisplayName");
        barBean.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarDataById(0L)).thenReturn(barBean);

        // Run the test
        myClassUnderTest.helperToIgnore();

        // Verify the results
        verify(mockBarService).deleteBarWithId(0);
    }

    @Test
    void testHelperToIgnore_BarServiceGetBarDataByIdThrowsInvalidBarDataIdException() throws Exception {
        // Setup
        when(mockBarService.getBarDataById(0L)).thenThrow(InvalidBarDataIdException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.helperToIgnore()).isInstanceOf(InvalidBarDataIdException.class);
    }

    @Test
    void testHelperToIgnore_BarServiceDeleteBarWithIdThrowsInvalidBarDataIdException() throws Exception {
        // Setup
        // Configure BarService.getBarDataById(...).
        final BarBean barBean = new BarBean();
        barBean.setId(0L);
        barBean.setName("name");
        barBean.setShortDisplayName("shortDisplayName");
        barBean.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarDataById(0L)).thenReturn(barBean);

        doThrow(InvalidBarDataIdException.class).when(mockBarService).deleteBarWithId(0);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.helperToIgnore()).isInstanceOf(InvalidBarDataIdException.class);
    }

    @Test
    void testHelperToIgnore_BarServiceDeleteBarWithIdThrowsNetworkException() throws Exception {
        // Setup
        // Configure BarService.getBarDataById(...).
        final BarBean barBean = new BarBean();
        barBean.setId(0L);
        barBean.setName("name");
        barBean.setShortDisplayName("shortDisplayName");
        barBean.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarDataById(0L)).thenReturn(barBean);

        doThrow(NetworkException.class).when(mockBarService).deleteBarWithId(0);

        // Run the test
        myClassUnderTest.helperToIgnore();

        // Verify the results
    }
}
