package com.myapp;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.AmazonDynamoDBException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private CrudRepository<String, String> mockFooDataRepo;
    @Mock
    private DynamoDBMapper mockDynamoDBMapper;

    private MyClass<String, String> myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass<>(mockFooDataRepo, mockDynamoDBMapper);
    }

    @Test
    void testTrySave1() {
        // Setup
        when(mockFooDataRepo.save("entity")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.trySave1("theData");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testTrySave2() {
        // Setup
        when(mockFooDataRepo.save("entity")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.trySave2("theData");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testTrySave3() {
        // Setup
        when(mockFooDataRepo.save("entity")).thenReturn("result");

        // Run the test
        myClassUnderTest.trySave3("theData");

        // Verify the results
    }

    @Test
    void testTrySave4() {
        // Setup
        when(mockFooDataRepo.save("entity")).thenReturn("result");

        // Run the test
        myClassUnderTest.trySave4("theData");

        // Verify the results
        verify(mockFooDataRepo).save("entity");
    }

    @Test
    void testTrySave5() {
        // Setup
        // Run the test
        myClassUnderTest.trySave5("theData");

        // Verify the results
        verify(mockDynamoDBMapper).save("object");
    }

    @Test
    void testTrySave5_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        doThrow(AmazonDynamoDBException.class).when(mockDynamoDBMapper).save("object");

        // Run the test
        assertThrows(AmazonDynamoDBException.class, () -> myClassUnderTest.trySave5("theData"));
    }

    @Test
    void testTrySave6() {
        // Setup
        // Run the test
        myClassUnderTest.trySave6("theData");

        // Verify the results
        verify(mockDynamoDBMapper).save("object");
    }

    @Test
    void testTrySave6_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        doThrow(AmazonDynamoDBException.class).when(mockDynamoDBMapper).save("object");

        // Run the test
        assertThrows(AmazonDynamoDBException.class, () -> myClassUnderTest.trySave6("theData"));
    }

    @Test
    void testTryGet1() {
        // Setup
        when(mockDynamoDBMapper.load("keyObject")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.tryGet1("theData");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testTryGet1_DynamoDBMapperReturnsNull() {
        // Setup
        when(mockDynamoDBMapper.load("keyObject")).thenReturn(null);

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.tryGet1("theData"));
    }

    @Test
    void testTryGet1_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.load("keyObject")).thenThrow(AmazonDynamoDBException.class);

        // Run the test
        assertThrows(AmazonDynamoDBException.class, () -> myClassUnderTest.tryGet1("theData"));
    }

    @Test
    void testTryGet2() {
        // Setup
        when(mockDynamoDBMapper.load("keyObject")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.tryGet2("theData");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testTryGet2_DynamoDBMapperReturnsNull() {
        // Setup
        when(mockDynamoDBMapper.load("keyObject")).thenReturn(null);

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.tryGet2("theData"));
    }

    @Test
    void testTryGet2_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.load("keyObject")).thenThrow(AmazonDynamoDBException.class);

        // Run the test
        assertThrows(AmazonDynamoDBException.class, () -> myClassUnderTest.tryGet2("theData"));
    }

    @Test
    void testTryGet3() {
        // Setup
        when(mockDynamoDBMapper.load("keyObject")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.tryGet3("theData");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testTryGet3_DynamoDBMapperReturnsNull() {
        // Setup
        when(mockDynamoDBMapper.load("keyObject")).thenReturn(null);

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.tryGet3("theData"));
    }

    @Test
    void testTryGet3_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.load("keyObject")).thenThrow(AmazonDynamoDBException.class);

        // Run the test
        assertThrows(AmazonDynamoDBException.class, () -> myClassUnderTest.tryGet3("theData"));
    }
}
