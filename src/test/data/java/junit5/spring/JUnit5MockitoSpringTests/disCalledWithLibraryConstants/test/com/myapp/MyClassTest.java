package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private RestTemplate mockRestTemplate;
    @Mock
    private FooService mockFooService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockRestTemplate, mockFooService);
    }

    @Test
    void testGetFooData() {
        // Setup
        final ResponseEntity<String> expectedResult = new ResponseEntity<>("body", HttpStatus.OK);
        when(mockRestTemplate.exchange("https://example.org", HttpMethod.GET,
                new HttpEntity<>("value", new HttpHeaders()), String.class))
                .thenReturn(new ResponseEntity<>("body", HttpStatus.OK));

        // Run the test
        final ResponseEntity<String> result = myClassUnderTest.getFooData();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooData_RestTemplateThrowsRestClientException() {
        // Setup
        when(mockRestTemplate.exchange("https://example.org", HttpMethod.GET,
                new HttpEntity<>("value", new HttpHeaders()), String.class)).thenThrow(RestClientException.class);

        // Run the test
        assertThrows(RestClientException.class, () -> myClassUnderTest.getFooData());
    }

    @Test
    void testPutFooData() {
        // Setup
        final ResponseEntity<String> expectedResult = new ResponseEntity<>("body", HttpStatus.OK);
        when(mockRestTemplate.exchange("https://example.org", HttpMethod.PUT,
                new HttpEntity<>("value", new HttpHeaders()), String.class, "params"))
                .thenReturn(new ResponseEntity<>("body", HttpStatus.OK));

        // Run the test
        final ResponseEntity<String> result = myClassUnderTest.putFooData("input");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testPutFooData_RestTemplateThrowsRestClientException() {
        // Setup
        when(mockRestTemplate.exchange("https://example.org", HttpMethod.PUT,
                new HttpEntity<>("value", new HttpHeaders()), String.class, "params"))
                .thenThrow(RestClientException.class);

        // Run the test
        assertThrows(RestClientException.class, () -> myClassUnderTest.putFooData("input"));
    }

    @Test
    void testPutFooDataOther() {
        // Setup
        final ResponseEntity<String> expectedResult = new ResponseEntity<>("body", HttpStatus.OK);
        when(mockRestTemplate.exchange(eq("https://example.org"), eq(HttpMethod.PUT),
                eq(new HttpEntity<>("value", new HttpHeaders())), eq(String.class), any(Object.class)))
                .thenReturn(new ResponseEntity<>("body", HttpStatus.OK));

        // Run the test
        final ResponseEntity<String> result = myClassUnderTest.putFooDataOther("input");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testPutFooDataOther_RestTemplateThrowsRestClientException() {
        // Setup
        when(mockRestTemplate.exchange(eq("https://example.org"), eq(HttpMethod.PUT),
                eq(new HttpEntity<>("value", new HttpHeaders())), eq(String.class), any(Object.class)))
                .thenThrow(RestClientException.class);

        // Run the test
        assertThrows(RestClientException.class, () -> myClassUnderTest.putFooDataOther("input"));
    }

    @Test
    void testDoSomething() {
        // Setup
        // Run the test
        myClassUnderTest.doSomething();

        // Verify the results
        verify(mockFooService).doSomethingWithCode(HttpStatus.CREATED);
    }

    @Test
    void testDoSomethingElse() {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingElse();

        // Verify the results
        verify(mockFooService).doSomethingElseWithCode();
    }

    @Test
    void testDoSomethingElse1() {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingElse1();

        // Verify the results
        verify(mockFooService).doSomethingElseWithCode(HttpStatus.OK);
    }
}
