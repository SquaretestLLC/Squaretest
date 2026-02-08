package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private RestTemplate mockRestTemplate;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockRestTemplate);
    }

    @Test
    void testGetStringValue() {
        // Setup
        // Configure RestTemplate.getForEntity(...).
        final ResponseEntity<String> stringResponseEntity = new ResponseEntity<>("45fa3168-d7b3-4e68-a5a2-72fea4bb108a",
                HttpStatus.OK);
        when(mockRestTemplate.getForEntity("https://example.com/str/", String.class, "getStringValueParam"))
                .thenReturn(stringResponseEntity);

        // Run the test
        final String result = myClassUnderTest.getStringValue("getStringValueParam");

        // Verify the results
        assertEquals("45fa3168-d7b3-4e68-a5a2-72fea4bb108a", result);
    }

    @Test
    void testGetStringValue_RestTemplateThrowsRestClientException() {
        // Setup
        when(mockRestTemplate.getForEntity("https://example.com/str/", String.class, "getStringValueParam"))
                .thenThrow(RestClientException.class);

        // Run the test
        assertThrows(RestClientException.class, () -> myClassUnderTest.getStringValue("getStringValueParam"));
    }

    @Test
    void testGetUUIDValue() {
        // Setup
        // Configure RestTemplate.getForEntity(...).
        final ResponseEntity<UUID> uuidResponseEntity = new ResponseEntity<>(
                UUID.fromString("45fa3168-d7b3-4e68-a5a2-72fea4bb108a"), HttpStatus.OK);
        when(mockRestTemplate.getForEntity("https://example.com/uuid/", UUID.class,
                "962d5272-c138-4ffd-9b13-93fc97d9c42f")).thenReturn(uuidResponseEntity);

        // Run the test
        final UUID result = myClassUnderTest.getUUIDValue("962d5272-c138-4ffd-9b13-93fc97d9c42f");

        // Verify the results
        assertEquals(UUID.fromString("45fa3168-d7b3-4e68-a5a2-72fea4bb108a"), result);
    }

    @Test
    void testGetUUIDValue_RestTemplateThrowsRestClientException() {
        // Setup
        when(mockRestTemplate.getForEntity("https://example.com/uuid/", UUID.class,
                "962d5272-c138-4ffd-9b13-93fc97d9c42f")).thenThrow(RestClientException.class);

        // Run the test
        assertThrows(RestClientException.class,
                () -> myClassUnderTest.getUUIDValue("962d5272-c138-4ffd-9b13-93fc97d9c42f"));
    }
}
