package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass();
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
    void testLoadBarForSession() {
        assertThat(myClassUnderTest.loadBarForSession(0L)).isNull();
    }
}
