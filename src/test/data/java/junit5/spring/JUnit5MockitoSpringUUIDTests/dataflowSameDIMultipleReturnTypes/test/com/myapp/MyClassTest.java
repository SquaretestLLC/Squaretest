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
        when(mockRestTemplate.getForEntity("https://example.com/str/", String.class, "getStringValueParam"))
                .thenReturn(new ResponseEntity<>("body", HttpStatus.OK));

        // Run the test
        final String result = myClassUnderTest.getStringValue("getStringValueParam");

        // Verify the results
        assertEquals("ca6d384b-091a-4bdc-8223-c878aafca544", result);
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
                UUID.fromString("518cb2a5-9476-4e05-8fec-d436b2c909f3"), HttpStatus.OK);
        when(mockRestTemplate.getForEntity("https://example.com/uuid/", UUID.class,
                "1f4fdc6d-611b-4e10-bc52-162df70c87d4")).thenReturn(uuidResponseEntity);

        // Run the test
        final UUID result = myClassUnderTest.getUUIDValue("1f4fdc6d-611b-4e10-bc52-162df70c87d4");

        // Verify the results
        assertEquals(UUID.fromString("ca6d384b-091a-4bdc-8223-c878aafca544"), result);
    }

    @Test
    void testGetUUIDValue_RestTemplateThrowsRestClientException() {
        // Setup
        when(mockRestTemplate.getForEntity("https://example.com/uuid/", UUID.class,
                "1f4fdc6d-611b-4e10-bc52-162df70c87d4")).thenThrow(RestClientException.class);

        // Run the test
        assertThrows(RestClientException.class,
                () -> myClassUnderTest.getUUIDValue("1f4fdc6d-611b-4e10-bc52-162df70c87d4"));
    }
}
