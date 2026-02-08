package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
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

    @InjectMocks
    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void testGreeting() {
        // Setup
        final BarBean barBeanFromModel = new BarBean();
        barBeanFromModel.setId(0L);
        barBeanFromModel.setName("name");
        barBeanFromModel.setShortDisplayName("shortDisplayName");
        barBeanFromModel.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));

        final Model model = null;

        // Run the test
        final String result = myClassUnderTest.greeting(barBeanFromModel, "name", model);

        // Verify the results
        assertThat(result).isEqualTo("greeting");
    }

    @Test
    void testGreeting1() {
        // Setup
        final BarBean barBeanFromModel = new BarBean();
        barBeanFromModel.setId(0L);
        barBeanFromModel.setName("name");
        barBeanFromModel.setShortDisplayName("shortDisplayName");
        barBeanFromModel.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));

        final Model model = null;

        // Run the test
        final String result = myClassUnderTest.greeting1(Locale.US, barBeanFromModel, "name", model);

        // Verify the results
        assertThat(result).isEqualTo("greeting");
        verify(mockBarService).saveBar(any(BarBean.class));
        verify(mockMetricsServiceAdapter).recordMetric("greeting1");
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

        // Run the test
        final String result = myClassUnderTest.greeting2(Locale.US, barBeanFromModel, "name", model);

        // Verify the results
        assertThat(result).isEqualTo("greeting");
        verify(mockBarService).saveBar(any(BarBean.class));
        verify(mockBarService).getBarDataById(0L);
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
        verify(mockBarService).saveBar(any(BarBean.class));
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

        // Configure BarService.getAllBars(...).
        final BarBean barBean = new BarBean();
        barBean.setId(0L);
        barBean.setName("name");
        barBean.setShortDisplayName("shortDisplayName");
        barBean.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final List<BarBean> barBeans = Arrays.asList(barBean);
        when(mockBarService.getAllBars()).thenReturn(barBeans);

        // Run the test
        final String result = myClassUnderTest.greetAll(barBeanFromModel, "name", model);

        // Verify the results
        assertThat(result).isEqualTo("greeting");
        verify(mockMetricsServiceAdapter).recordMetric("greetAll");
    }

    @Test
    void testGreetAll_BarServiceReturnsNoItems() throws Exception {
        // Setup
        final BarBean barBeanFromModel = new BarBean();
        barBeanFromModel.setId(0L);
        barBeanFromModel.setName("name");
        barBeanFromModel.setShortDisplayName("shortDisplayName");
        barBeanFromModel.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));

        final Model model = null;
        when(mockBarService.getAllBars()).thenReturn(Collections.emptyList());

        // Run the test
        final String result = myClassUnderTest.greetAll(barBeanFromModel, "name", model);

        // Verify the results
        assertThat(result).isEqualTo("greeting");
        verify(mockMetricsServiceAdapter).recordMetric("greetAll");
    }

    @Test
    void testGreetAll_BarServiceThrowsIOException() throws Exception {
        // Setup
        final BarBean barBeanFromModel = new BarBean();
        barBeanFromModel.setId(0L);
        barBeanFromModel.setName("name");
        barBeanFromModel.setShortDisplayName("shortDisplayName");
        barBeanFromModel.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));

        final Model model = null;
        when(mockBarService.getAllBars()).thenThrow(IOException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.greetAll(barBeanFromModel, "name", model))
                .isInstanceOf(IOException.class);
    }

    @Test
    void testHelperToIgnore() throws Exception {
        // Setup
        // Run the test
        myClassUnderTest.helperToIgnore();

        // Verify the results
        verify(mockBarService).deleteBarWithId(0);
    }

    @Test
    void testHelperToIgnore_BarServiceThrowsInvalidBarDataIdException() throws Exception {
        // Setup
        doThrow(InvalidBarDataIdException.class).when(mockBarService).deleteBarWithId(0);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.helperToIgnore()).isInstanceOf(InvalidBarDataIdException.class);
    }

    @Test
    void testHelperToIgnore_BarServiceThrowsNetworkException() throws Exception {
        // Setup
        doThrow(NetworkException.class).when(mockBarService).deleteBarWithId(0);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.helperToIgnore()).isInstanceOf(NetworkException.class);
    }
}
