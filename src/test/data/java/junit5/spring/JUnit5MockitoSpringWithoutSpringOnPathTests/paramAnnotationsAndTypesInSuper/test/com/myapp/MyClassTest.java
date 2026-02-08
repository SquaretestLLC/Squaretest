package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals("greeting", result);
    }

    @Test
    void testGreeting1() {
        // Setup
        final Model model = null;

        // Run the test
        final String result = myClassUnderTest.greeting1("theName", model);

        // Verify the results
        assertEquals("greeting", result);
    }

    @Test
    void testGreeting2() {
        // Setup
        final Model model = null;

        // Run the test
        final String result = myClassUnderTest.greeting2("theName", model);

        // Verify the results
        assertEquals("greeting", result);
    }

    @Test
    void testGreeting3() {
        // Setup
        final Model model = null;

        // Run the test
        final String result = myClassUnderTest.greeting3("theName", model);

        // Verify the results
        assertEquals("greeting", result);
    }

    @Test
    void testGreeting4() {
        // Setup
        final Model model = null;

        // Run the test
        final String result = myClassUnderTest.greeting4("theName", model);

        // Verify the results
        assertEquals("greeting", result);
    }

    @Test
    void testSubmitFormAsBody1() {
        assertEquals("submitFormAsBody1", myClassUnderTest.submitFormAsBody1(Mono.just(null)));
    }

    @Test
    void testSubmitFormAsBodyJsonResponse() {
        assertEquals("submitFormAsBody1", myClassUnderTest.submitFormAsBodyJsonResponse(Mono.just(null)));
    }

    @Test
    void testSubmitFormAsPojo() {
        assertEquals("submitFormAsBody1", myClassUnderTest.submitFormAsPojo(new BarBean()));
    }

    @Test
    void testSubmitFormAsPojoWithCsrf() {
        assertEquals("submitFormAsBody1", myClassUnderTest.submitFormAsPojoWithCsrf(new BarBean(), null));
    }

    @Test
    void testSubmitFormAsPojoWithCsrfAndJson() {
        assertEquals("submitFormAsBody1", myClassUnderTest.submitFormAsPojoWithCsrfAndJson(new BarBean(), null));
    }

    @Test
    void testSubmitFormAsPojoJsonResponse() {
        assertEquals("submitFormAsBody1", myClassUnderTest.submitFormAsPojoJsonResponse(new BarBean()));
    }

    @Test
    void testSubmitStringAsBody() {
        assertEquals("submitStringAsBody", myClassUnderTest.submitStringAsBody("bodyString"));
    }

    @Test
    void testSubmitStringAsBody1() {
        assertEquals("submitStringAsBody1", myClassUnderTest.submitStringAsBody1(Mono.just(null)));
    }

    @Test
    void testSubmitStringAsBody2() {
        assertEquals("submitStringAsBody2", myClassUnderTest.submitStringAsBody2(Mono.just(null)));
    }

    @Test
    void testSubmitStringAsBody3() {
        assertEquals("submitStringAsBody3", myClassUnderTest.submitStringAsBody3(Mono.just("value")));
    }

    @Test
    void testSubmitStringAsBody4() {
        assertEquals("submitStringAsBody4", myClassUnderTest.submitStringAsBody4(null));
    }

    @Test
    void testSubmitStringAsBody5() {
        assertEquals("submitStringAsBody5", myClassUnderTest.submitStringAsBody5(null));
    }

    @Test
    void testSubmitStringAsBodyWithSession() {
        assertEquals("submitStringAsBodyWithSession",
                myClassUnderTest.submitStringAsBodyWithSession("bodyString", "previousValue"));
    }

    @Test
    void testSubmitStringAsBodyWithRequestAttr() {
        assertEquals("submitStringAsBodyWithRequestAttr",
                myClassUnderTest.submitStringAsBodyWithRequestAttr("bodyString", "previousValue"));
    }
}
