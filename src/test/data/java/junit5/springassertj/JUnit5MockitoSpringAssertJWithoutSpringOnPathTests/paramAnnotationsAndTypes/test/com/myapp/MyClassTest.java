package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

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
        final Model model = null;

        // Run the test
        final String result = myClassUnderTest.greeting("theName", model);

        // Verify the results
        assertThat(result).isEqualTo("greeting");
    }

    @Test
    void testGreeting1() {
        // Setup
        final Model model = null;

        // Run the test
        final String result = myClassUnderTest.greeting1("theName", model);

        // Verify the results
        assertThat(result).isEqualTo("greeting");
    }

    @Test
    void testGreeting2() {
        // Setup
        final Model model = null;

        // Run the test
        final String result = myClassUnderTest.greeting2("theName", model);

        // Verify the results
        assertThat(result).isEqualTo("greeting");
    }

    @Test
    void testGreeting3() {
        // Setup
        final Model model = null;

        // Run the test
        final String result = myClassUnderTest.greeting3("theName", model);

        // Verify the results
        assertThat(result).isEqualTo("greeting");
    }

    @Test
    void testGreeting4() {
        // Setup
        final Model model = null;

        // Run the test
        final String result = myClassUnderTest.greeting4("theName", model);

        // Verify the results
        assertThat(result).isEqualTo("greeting");
    }

    @Test
    void testSubmitFormAsBody1() {
        assertThat(myClassUnderTest.submitFormAsBody1(Mono.just(null))).isEqualTo("submitFormAsBody1");
    }

    @Test
    void testSubmitFormAsBodyJsonResponse() {
        assertThat(myClassUnderTest.submitFormAsBodyJsonResponse(Mono.just(null))).isEqualTo("submitFormAsBody1");
    }

    @Test
    void testSubmitFormAsPojo() {
        assertThat(myClassUnderTest.submitFormAsPojo(new BarBean())).isEqualTo("submitFormAsBody1");
    }

    @Test
    void testSubmitFormAsPojoWithCsrf() {
        assertThat(myClassUnderTest.submitFormAsPojoWithCsrf(new BarBean(), null)).isEqualTo("submitFormAsBody1");
    }

    @Test
    void testSubmitFormAsPojoWithCsrfAndJson() {
        assertThat(myClassUnderTest.submitFormAsPojoWithCsrfAndJson(new BarBean(), null))
                .isEqualTo("submitFormAsBody1");
    }

    @Test
    void testSubmitFormAsPojoJsonResponse() {
        assertThat(myClassUnderTest.submitFormAsPojoJsonResponse(new BarBean())).isEqualTo("submitFormAsBody1");
    }

    @Test
    void testSubmitStringAsBody() {
        assertThat(myClassUnderTest.submitStringAsBody("bodyString")).isEqualTo("submitStringAsBody");
    }

    @Test
    void testSubmitStringAsBody1() {
        assertThat(myClassUnderTest.submitStringAsBody1(Mono.just(null))).isEqualTo("submitStringAsBody1");
    }

    @Test
    void testSubmitStringAsBody2() {
        assertThat(myClassUnderTest.submitStringAsBody2(Mono.just(null))).isEqualTo("submitStringAsBody2");
    }

    @Test
    void testSubmitStringAsBody3() {
        assertThat(myClassUnderTest.submitStringAsBody3(Mono.just("value"))).isEqualTo("submitStringAsBody3");
    }

    @Test
    void testSubmitStringAsBody4() {
        assertThat(myClassUnderTest.submitStringAsBody4(null)).isEqualTo("submitStringAsBody4");
    }

    @Test
    void testSubmitStringAsBody5() {
        assertThat(myClassUnderTest.submitStringAsBody5(null)).isEqualTo("submitStringAsBody5");
    }

    @Test
    void testSubmitStringAsBodyWithSession() {
        assertThat(myClassUnderTest.submitStringAsBodyWithSession("bodyString", "previousValue"))
                .isEqualTo("submitStringAsBodyWithSession");
    }

    @Test
    void testSubmitStringAsBodyWithRequestAttr() {
        assertThat(myClassUnderTest.submitStringAsBodyWithRequestAttr("bodyString", "previousValue"))
                .isEqualTo("submitStringAsBodyWithRequestAttr");
    }
}
