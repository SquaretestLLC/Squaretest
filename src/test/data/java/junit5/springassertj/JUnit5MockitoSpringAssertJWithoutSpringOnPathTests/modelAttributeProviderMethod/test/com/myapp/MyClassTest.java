package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private BarService mockBarService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockBarService);
    }

    @Test
    void testGreeting() throws Exception {
        // Setup
        final Model model = null;

        // Configure BarService.getBarDataById(...).
        final BarBean barBean = new BarBean();
        barBean.setId(0L);
        barBean.setName("name");
        barBean.setShortDisplayName("shortDisplayName");
        barBean.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarDataById(0L)).thenReturn(barBean);

        // Run the test
        final String result = myClassUnderTest.greeting("name", model);

        // Verify the results
        assertThat(result).isEqualTo("greeting");
    }

    @Test
    void testGreeting_BarServiceThrowsInvalidBarDataIdException() throws Exception {
        // Setup
        final Model model = null;
        when(mockBarService.getBarDataById(0L)).thenThrow(InvalidBarDataIdException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.greeting("name", model))
                .isInstanceOf(InvalidBarDataIdException.class);
    }

    @Test
    void testGreeting1() throws Exception {
        // Setup
        final Model model = null;

        // Configure BarService.getBarDataById(...).
        final BarBean barBean = new BarBean();
        barBean.setId(0L);
        barBean.setName("name");
        barBean.setShortDisplayName("shortDisplayName");
        barBean.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockBarService.getBarDataById(0L)).thenReturn(barBean);

        // Run the test
        final String result = myClassUnderTest.greeting1(Locale.US, "name", model);

        // Verify the results
        assertThat(result).isEqualTo("greeting");
    }

    @Test
    void testGreeting1_BarServiceThrowsInvalidBarDataIdException() throws Exception {
        // Setup
        final Model model = null;
        when(mockBarService.getBarDataById(0L)).thenThrow(InvalidBarDataIdException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.greeting1(Locale.US, "name", model))
                .isInstanceOf(InvalidBarDataIdException.class);
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
}
