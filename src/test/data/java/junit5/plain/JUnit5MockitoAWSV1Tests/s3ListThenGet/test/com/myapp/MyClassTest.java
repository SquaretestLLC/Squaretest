package com.myapp;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private AmazonS3 mockS3Client;
    @Mock
    private MetricsAdapter mockMetricsAdapter;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockS3Client, mockMetricsAdapter, "bucketName");
    }

    @Test
    void testGetFirstPage1() throws Exception {
        // Setup
        final List<DataObject> expectedResult = Arrays.asList(new DataObject("id", "content", "specialValue"));

        // Configure AmazonS3.listObjectsV2(...).
        final ListObjectsV2Result listObjectsV2Result = new ListObjectsV2Result();
        final S3ObjectSummary s3ObjectSummary = new S3ObjectSummary();
        s3ObjectSummary.setBucketName("bucketName");
        s3ObjectSummary.setKey("key");
        listObjectsV2Result.getObjectSummaries().addAll(Arrays.asList(s3ObjectSummary));
        when(mockS3Client.listObjectsV2("bucketName", "path")).thenReturn(listObjectsV2Result);

        // Configure AmazonS3.getObject(...).
        final S3Object spyS3Object = spy(new S3Object());
        spyS3Object.setBucketName("bucketName");
        spyS3Object.setKey("id");
        spyS3Object.setObjectContent(new ByteArrayInputStream("content".getBytes()));
        final ObjectMetadata metadata = new ObjectMetadata();
        metadata.setUserMetadata(new HashMap<>());
        spyS3Object.setObjectMetadata(metadata);
        when(mockS3Client.getObject("bucketName", "key")).thenReturn(spyS3Object);

        // Run the test
        final List<DataObject> result = myClassUnderTest.getFirstPage1("path");

        // Verify the results
        assertEquals(expectedResult, result);
        verify(spyS3Object).close();
    }

    @Test
    void testGetFirstPage1_AmazonS3ListObjectsV2ReturnsNoItems() throws Exception {
        // Setup
        when(mockS3Client.listObjectsV2("bucketName", "path")).thenReturn(new ListObjectsV2Result());

        // Run the test
        final List<DataObject> result = myClassUnderTest.getFirstPage1("path");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetFirstPage1_AmazonS3ListObjectsV2ThrowsSdkClientException() {
        // Setup
        when(mockS3Client.listObjectsV2("bucketName", "path")).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.getFirstPage1("path"));
    }

    @Test
    void testGetFirstPage1_AmazonS3ListObjectsV2ThrowsAmazonServiceException() {
        // Setup
        when(mockS3Client.listObjectsV2("bucketName", "path")).thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.getFirstPage1("path"));
    }

    @Test
    void testGetFirstPage1_AmazonS3GetObjectReturnsNoContent() throws Exception {
        // Setup
        final List<DataObject> expectedResult = Arrays.asList(new DataObject("id", "content", "specialValue"));

        // Configure AmazonS3.listObjectsV2(...).
        final ListObjectsV2Result listObjectsV2Result = new ListObjectsV2Result();
        final S3ObjectSummary s3ObjectSummary = new S3ObjectSummary();
        s3ObjectSummary.setBucketName("bucketName");
        s3ObjectSummary.setKey("key");
        listObjectsV2Result.getObjectSummaries().addAll(Arrays.asList(s3ObjectSummary));
        when(mockS3Client.listObjectsV2("bucketName", "path")).thenReturn(listObjectsV2Result);

        // Configure AmazonS3.getObject(...).
        final S3Object spyS3Object = spy(new S3Object());
        spyS3Object.setBucketName("bucketName");
        spyS3Object.setKey("id");
        spyS3Object.setObjectContent(new ByteArrayInputStream(new byte[]{}));
        final ObjectMetadata metadata = new ObjectMetadata();
        metadata.setUserMetadata(new HashMap<>());
        spyS3Object.setObjectMetadata(metadata);
        when(mockS3Client.getObject("bucketName", "key")).thenReturn(spyS3Object);

        // Run the test
        final List<DataObject> result = myClassUnderTest.getFirstPage1("path");

        // Verify the results
        assertEquals(expectedResult, result);
        verify(spyS3Object).close();
    }

    @Test
    void testGetFirstPage1_AmazonS3GetObjectReturnsBrokenIo() throws Exception {
        // Setup
        // Configure AmazonS3.listObjectsV2(...).
        final ListObjectsV2Result listObjectsV2Result = new ListObjectsV2Result();
        final S3ObjectSummary s3ObjectSummary = new S3ObjectSummary();
        s3ObjectSummary.setBucketName("bucketName");
        s3ObjectSummary.setKey("key");
        listObjectsV2Result.getObjectSummaries().addAll(Arrays.asList(s3ObjectSummary));
        when(mockS3Client.listObjectsV2("bucketName", "path")).thenReturn(listObjectsV2Result);

        // Configure AmazonS3.getObject(...).
        final S3Object spyS3Object = spy(new S3Object());
        spyS3Object.setBucketName("bucketName");
        spyS3Object.setKey("id");
        spyS3Object.setObjectContent(new InputStream() {
            private final IOException exception = new IOException("Error");

            @Override
            public int read() throws IOException {
                throw exception;
            }

            @Override
            public int available() throws IOException {
                throw exception;
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception;
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception;
            }

            @Override
            public void close() throws IOException {
                throw exception;
            }
        });
        final ObjectMetadata metadata = new ObjectMetadata();
        metadata.setUserMetadata(new HashMap<>());
        spyS3Object.setObjectMetadata(metadata);
        when(mockS3Client.getObject("bucketName", "key")).thenReturn(spyS3Object);

        // Run the test
        assertThrows(ServiceException.class, () -> myClassUnderTest.getFirstPage1("path"));
        verify(spyS3Object).close();
        verify(mockMetricsAdapter).recordIOException(eq("key"), any(IOException.class));
    }

    @Test
    void testGetFirstPage1_AmazonS3GetObjectThrowsSdkClientException() {
        // Setup
        // Configure AmazonS3.listObjectsV2(...).
        final ListObjectsV2Result listObjectsV2Result = new ListObjectsV2Result();
        final S3ObjectSummary s3ObjectSummary = new S3ObjectSummary();
        s3ObjectSummary.setBucketName("bucketName");
        s3ObjectSummary.setKey("key");
        listObjectsV2Result.getObjectSummaries().addAll(Arrays.asList(s3ObjectSummary));
        when(mockS3Client.listObjectsV2("bucketName", "path")).thenReturn(listObjectsV2Result);

        when(mockS3Client.getObject("bucketName", "key")).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(ServiceException.class, () -> myClassUnderTest.getFirstPage1("path"));
        verify(mockMetricsAdapter).recordClientException(eq("key"), any(SdkClientException.class));
    }

    @Test
    void testGetFirstPage1_AmazonS3GetObjectThrowsAmazonServiceException() {
        // Setup
        // Configure AmazonS3.listObjectsV2(...).
        final ListObjectsV2Result listObjectsV2Result = new ListObjectsV2Result();
        final S3ObjectSummary s3ObjectSummary = new S3ObjectSummary();
        s3ObjectSummary.setBucketName("bucketName");
        s3ObjectSummary.setKey("key");
        listObjectsV2Result.getObjectSummaries().addAll(Arrays.asList(s3ObjectSummary));
        when(mockS3Client.listObjectsV2("bucketName", "path")).thenReturn(listObjectsV2Result);

        when(mockS3Client.getObject("bucketName", "key")).thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(ServiceException.class, () -> myClassUnderTest.getFirstPage1("path"));
        verify(mockMetricsAdapter).recordAmazonServiceException(eq("key"), any(AmazonServiceException.class));
    }
}
