package com.myapp;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.AmazonDynamoDBException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private FooDataRepo mockFooDataRepo;
    @Mock
    private DynamoDBMapper mockDynamoDBMapper;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFooDataRepo, mockDynamoDBMapper);
    }

    @Test
    void testTrySave1() {
        // Setup
        final FooData fooData = new FooData("id", "name", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final FooData expectedResult = new FooData("id", "name", LocalDateTime.of(2020, 1, 1, 0, 0, 0));

        // Configure FooDataRepo.save(...).
        final FooData fooData1 = new FooData("id", "name", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockFooDataRepo.save(new FooData("id", "name", LocalDateTime.of(2020, 1, 1, 0, 0, 0))))
                .thenReturn(fooData1);

        // Run the test
        final FooData result = myClassUnderTest.trySave1(fooData);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testTrySave2() {
        // Setup
        final FooData fooData = new FooData("id", "name", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final FooData expectedResult = new FooData("id", "name", LocalDateTime.of(2020, 1, 1, 0, 0, 0));

        // Configure FooDataRepo.save(...).
        final FooData fooData1 = new FooData("id", "name", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockFooDataRepo.save(new FooData("id", "name", LocalDateTime.of(2020, 1, 1, 0, 0, 0))))
                .thenReturn(fooData1);

        // Run the test
        final FooData result = myClassUnderTest.trySave2(fooData);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testTrySave3() {
        // Setup
        final FooData fooData = new FooData("id", "name", LocalDateTime.of(2020, 1, 1, 0, 0, 0));

        // Configure FooDataRepo.save(...).
        final FooData fooData1 = new FooData("id", "name", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockFooDataRepo.save(new FooData("id", "name", LocalDateTime.of(2020, 1, 1, 0, 0, 0))))
                .thenReturn(fooData1);

        // Run the test
        myClassUnderTest.trySave3(fooData);

        // Verify the results
    }

    @Test
    void testTrySave4() {
        // Setup
        final FooData fooData = new FooData("id", "name", LocalDateTime.of(2020, 1, 1, 0, 0, 0));

        // Configure FooDataRepo.save(...).
        final FooData fooData1 = new FooData("id", "name", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockFooDataRepo.save(new FooData("id", "name", LocalDateTime.of(2020, 1, 1, 0, 0, 0))))
                .thenReturn(fooData1);

        // Run the test
        myClassUnderTest.trySave4(fooData);

        // Verify the results
        verify(mockFooDataRepo).save(new FooData("id", "name", LocalDateTime.of(2020, 1, 1, 0, 0, 0)));
    }

    @Test
    void testTrySave5() {
        // Setup
        final FooData fooData = new FooData("id", "name", LocalDateTime.of(2020, 1, 1, 0, 0, 0));

        // Run the test
        myClassUnderTest.trySave5(fooData);

        // Verify the results
        verify(mockDynamoDBMapper).save(new FooData("id", "name", LocalDateTime.of(2020, 1, 1, 0, 0, 0)));
    }

    @Test
    void testTrySave5_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        final FooData fooData = new FooData("id", "name", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        doThrow(AmazonDynamoDBException.class).when(mockDynamoDBMapper).save(
                new FooData("id", "name", LocalDateTime.of(2020, 1, 1, 0, 0, 0)));

        // Run the test
        assertThrows(AmazonDynamoDBException.class, () -> myClassUnderTest.trySave5(fooData));
    }

    @Test
    void testTrySave6() {
        // Setup
        final FooData fooData = new FooData("id", "name", LocalDateTime.of(2020, 1, 1, 0, 0, 0));

        // Run the test
        myClassUnderTest.trySave6(fooData);

        // Verify the results
        verify(mockDynamoDBMapper).save(new FooData("id", "name", LocalDateTime.of(2020, 1, 1, 0, 0, 0)));
    }

    @Test
    void testTrySave6_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        final FooData fooData = new FooData("id", "name", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        doThrow(AmazonDynamoDBException.class).when(mockDynamoDBMapper).save(
                new FooData("id", "name", LocalDateTime.of(2020, 1, 1, 0, 0, 0)));

        // Run the test
        assertThrows(AmazonDynamoDBException.class, () -> myClassUnderTest.trySave6(fooData));
    }

    @Test
    void testTryGet1() {
        // Setup
        final FooData fooData = new FooData("id", "name", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final FooData expectedResult = new FooData("id", "name", LocalDateTime.of(2020, 1, 1, 0, 0, 0));

        // Configure DynamoDBMapper.load(...).
        final FooData fooData1 = new FooData("id", "name", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockDynamoDBMapper.load(new FooData("id", "name", LocalDateTime.of(2020, 1, 1, 0, 0, 0))))
                .thenReturn(fooData1);

        // Run the test
        final FooData result = myClassUnderTest.tryGet1(fooData);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testTryGet1_DynamoDBMapperReturnsNull() {
        // Setup
        final FooData fooData = new FooData("id", "name", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockDynamoDBMapper.load(new FooData("id", "name", LocalDateTime.of(2020, 1, 1, 0, 0, 0))))
                .thenReturn(null);

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.tryGet1(fooData));
    }

    @Test
    void testTryGet1_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        final FooData fooData = new FooData("id", "name", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockDynamoDBMapper.load(new FooData("id", "name", LocalDateTime.of(2020, 1, 1, 0, 0, 0))))
                .thenThrow(AmazonDynamoDBException.class);

        // Run the test
        assertThrows(AmazonDynamoDBException.class, () -> myClassUnderTest.tryGet1(fooData));
    }

    @Test
    void testTryGet2() {
        // Setup
        final FooData fooData = new FooData("id", "name", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final FooData expectedResult = new FooData("id", "name", LocalDateTime.of(2020, 1, 1, 0, 0, 0));

        // Configure DynamoDBMapper.load(...).
        final FooData fooData1 = new FooData("id", "name", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockDynamoDBMapper.load(new FooData("id", "name", LocalDateTime.of(2020, 1, 1, 0, 0, 0))))
                .thenReturn(fooData1);

        // Run the test
        final FooData result = myClassUnderTest.tryGet2(fooData);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testTryGet2_DynamoDBMapperReturnsNull() {
        // Setup
        final FooData fooData = new FooData("id", "name", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockDynamoDBMapper.load(new FooData("id", "name", LocalDateTime.of(2020, 1, 1, 0, 0, 0))))
                .thenReturn(null);

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.tryGet2(fooData));
    }

    @Test
    void testTryGet2_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        final FooData fooData = new FooData("id", "name", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockDynamoDBMapper.load(new FooData("id", "name", LocalDateTime.of(2020, 1, 1, 0, 0, 0))))
                .thenThrow(AmazonDynamoDBException.class);

        // Run the test
        assertThrows(AmazonDynamoDBException.class, () -> myClassUnderTest.tryGet2(fooData));
    }

    @Test
    void testTryGet3() {
        // Setup
        final FooData fooData = new FooData("id", "name", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final FooData expectedResult = new FooData("id", "name", LocalDateTime.of(2020, 1, 1, 0, 0, 0));

        // Configure DynamoDBMapper.load(...).
        final FooData fooData1 = new FooData("id", "name", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockDynamoDBMapper.load(new FooData("id", "name", LocalDateTime.of(2020, 1, 1, 0, 0, 0))))
                .thenReturn(fooData1);

        // Run the test
        final FooData result = myClassUnderTest.tryGet3(fooData);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testTryGet3_DynamoDBMapperReturnsNull() {
        // Setup
        final FooData fooData = new FooData("id", "name", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockDynamoDBMapper.load(new FooData("id", "name", LocalDateTime.of(2020, 1, 1, 0, 0, 0))))
                .thenReturn(null);

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.tryGet3(fooData));
    }

    @Test
    void testTryGet3_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        final FooData fooData = new FooData("id", "name", LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockDynamoDBMapper.load(new FooData("id", "name", LocalDateTime.of(2020, 1, 1, 0, 0, 0))))
                .thenThrow(AmazonDynamoDBException.class);

        // Run the test
        assertThrows(AmazonDynamoDBException.class, () -> myClassUnderTest.tryGet3(fooData));
    }
}
