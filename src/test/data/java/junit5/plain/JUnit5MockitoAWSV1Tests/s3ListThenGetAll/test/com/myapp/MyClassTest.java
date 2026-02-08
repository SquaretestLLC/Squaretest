package com.myapp;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

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
        final List<DataObject> expectedResult = Arrays.asList(new DataObject("id", "content"));

        // Configure AmazonS3.listObjectsV2(...).
        final ListObjectsV2Result listObjectsV2Result = new ListObjectsV2Result();
        listObjectsV2Result.setTruncated(false);
        listObjectsV2Result.setNextContinuationToken("continuationToken");
        final S3ObjectSummary s3ObjectSummary = new S3ObjectSummary();
        s3ObjectSummary.setBucketName("bucketName");
        s3ObjectSummary.setKey("id");
        listObjectsV2Result.getObjectSummaries().addAll(Arrays.asList(s3ObjectSummary));
        when(mockS3Client.listObjectsV2("bucketName", "path")).thenReturn(listObjectsV2Result);

        // Configure AmazonS3.getObject(...).
        final S3Object spyS3Object = spy(new S3Object());
        spyS3Object.setBucketName("bucketName");
        spyS3Object.setKey("key");
        spyS3Object.setObjectContent(new ByteArrayInputStream("content".getBytes()));
        when(mockS3Client.getObject("bucketName", "id")).thenReturn(spyS3Object);

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
        final List<DataObject> expectedResult = Arrays.asList(new DataObject("id", "content"));

        // Configure AmazonS3.listObjectsV2(...).
        final ListObjectsV2Result listObjectsV2Result = new ListObjectsV2Result();
        listObjectsV2Result.setTruncated(false);
        listObjectsV2Result.setNextContinuationToken("continuationToken");
        final S3ObjectSummary s3ObjectSummary = new S3ObjectSummary();
        s3ObjectSummary.setBucketName("bucketName");
        s3ObjectSummary.setKey("id");
        listObjectsV2Result.getObjectSummaries().addAll(Arrays.asList(s3ObjectSummary));
        when(mockS3Client.listObjectsV2("bucketName", "path")).thenReturn(listObjectsV2Result);

        // Configure AmazonS3.getObject(...).
        final S3Object spyS3Object = spy(new S3Object());
        spyS3Object.setBucketName("bucketName");
        spyS3Object.setKey("key");
        spyS3Object.setObjectContent(new ByteArrayInputStream(new byte[]{}));
        when(mockS3Client.getObject("bucketName", "id")).thenReturn(spyS3Object);

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
        listObjectsV2Result.setTruncated(false);
        listObjectsV2Result.setNextContinuationToken("continuationToken");
        final S3ObjectSummary s3ObjectSummary = new S3ObjectSummary();
        s3ObjectSummary.setBucketName("bucketName");
        s3ObjectSummary.setKey("id");
        listObjectsV2Result.getObjectSummaries().addAll(Arrays.asList(s3ObjectSummary));
        when(mockS3Client.listObjectsV2("bucketName", "path")).thenReturn(listObjectsV2Result);

        // Configure AmazonS3.getObject(...).
        final S3Object spyS3Object = spy(new S3Object());
        spyS3Object.setBucketName("bucketName");
        spyS3Object.setKey("key");
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
        when(mockS3Client.getObject("bucketName", "id")).thenReturn(spyS3Object);

        // Run the test
        assertThrows(ServiceException.class, () -> myClassUnderTest.getFirstPage1("path"));
        verify(spyS3Object).close();
        verify(mockMetricsAdapter).recordIOException(eq("id"), any(IOException.class));
    }

    @Test
    void testGetFirstPage1_AmazonS3GetObjectThrowsSdkClientException() {
        // Setup
        // Configure AmazonS3.listObjectsV2(...).
        final ListObjectsV2Result listObjectsV2Result = new ListObjectsV2Result();
        listObjectsV2Result.setTruncated(false);
        listObjectsV2Result.setNextContinuationToken("continuationToken");
        final S3ObjectSummary s3ObjectSummary = new S3ObjectSummary();
        s3ObjectSummary.setBucketName("bucketName");
        s3ObjectSummary.setKey("id");
        listObjectsV2Result.getObjectSummaries().addAll(Arrays.asList(s3ObjectSummary));
        when(mockS3Client.listObjectsV2("bucketName", "path")).thenReturn(listObjectsV2Result);

        when(mockS3Client.getObject("bucketName", "id")).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(ServiceException.class, () -> myClassUnderTest.getFirstPage1("path"));
        verify(mockMetricsAdapter).recordClientException(eq("id"), any(SdkClientException.class));
    }

    @Test
    void testGetFirstPage1_AmazonS3GetObjectThrowsAmazonServiceException() {
        // Setup
        // Configure AmazonS3.listObjectsV2(...).
        final ListObjectsV2Result listObjectsV2Result = new ListObjectsV2Result();
        listObjectsV2Result.setTruncated(false);
        listObjectsV2Result.setNextContinuationToken("continuationToken");
        final S3ObjectSummary s3ObjectSummary = new S3ObjectSummary();
        s3ObjectSummary.setBucketName("bucketName");
        s3ObjectSummary.setKey("id");
        listObjectsV2Result.getObjectSummaries().addAll(Arrays.asList(s3ObjectSummary));
        when(mockS3Client.listObjectsV2("bucketName", "path")).thenReturn(listObjectsV2Result);

        when(mockS3Client.getObject("bucketName", "id")).thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(ServiceException.class, () -> myClassUnderTest.getFirstPage1("path"));
        verify(mockMetricsAdapter).recordAmazonServiceException(eq("id"), any(AmazonServiceException.class));
    }

    @Test
    void testGetAllObjects1() throws Exception {
        // Setup
        final Set<DataObject> expectedResult = new HashSet<>(Arrays.asList(new DataObject("id", "content")));

        // Configure AmazonS3.listObjectsV2(...).
        final ListObjectsV2Result listObjectsV2Result = new ListObjectsV2Result();
        listObjectsV2Result.setTruncated(false);
        listObjectsV2Result.setNextContinuationToken("continuationToken");
        final S3ObjectSummary s3ObjectSummary = new S3ObjectSummary();
        s3ObjectSummary.setBucketName("bucketName");
        s3ObjectSummary.setKey("id");
        listObjectsV2Result.getObjectSummaries().addAll(Arrays.asList(s3ObjectSummary));
        when(mockS3Client.listObjectsV2(any(ListObjectsV2Request.class))).thenReturn(listObjectsV2Result);

        // Configure AmazonS3.getObject(...).
        final S3Object spyS3Object = spy(new S3Object());
        spyS3Object.setBucketName("bucketName");
        spyS3Object.setKey("key");
        spyS3Object.setObjectContent(new ByteArrayInputStream("content".getBytes()));
        when(mockS3Client.getObject("bucketName", "id")).thenReturn(spyS3Object);

        // Run the test
        final Set<DataObject> result = myClassUnderTest.getAllObjects1("path");

        // Verify the results
        assertEquals(expectedResult, result);
        verify(spyS3Object).close();
    }

    @Test
    void testGetAllObjects1_AmazonS3ListObjectsV2ReturnsNoItems() throws Exception {
        // Setup
        when(mockS3Client.listObjectsV2(any(ListObjectsV2Request.class))).thenReturn(new ListObjectsV2Result());

        // Run the test
        final Set<DataObject> result = myClassUnderTest.getAllObjects1("path");

        // Verify the results
        assertEquals(Collections.emptySet(), result);
    }

    @Test
    void testGetAllObjects1_AmazonS3ListObjectsV2ThrowsSdkClientException() {
        // Setup
        when(mockS3Client.listObjectsV2(any(ListObjectsV2Request.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.getAllObjects1("path"));
    }

    @Test
    void testGetAllObjects1_AmazonS3ListObjectsV2ThrowsAmazonServiceException() {
        // Setup
        when(mockS3Client.listObjectsV2(any(ListObjectsV2Request.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.getAllObjects1("path"));
    }

    @Test
    void testGetAllObjects1_AmazonS3GetObjectReturnsNoContent() throws Exception {
        // Setup
        final Set<DataObject> expectedResult = new HashSet<>(Arrays.asList(new DataObject("id", "content")));

        // Configure AmazonS3.listObjectsV2(...).
        final ListObjectsV2Result listObjectsV2Result = new ListObjectsV2Result();
        listObjectsV2Result.setTruncated(false);
        listObjectsV2Result.setNextContinuationToken("continuationToken");
        final S3ObjectSummary s3ObjectSummary = new S3ObjectSummary();
        s3ObjectSummary.setBucketName("bucketName");
        s3ObjectSummary.setKey("id");
        listObjectsV2Result.getObjectSummaries().addAll(Arrays.asList(s3ObjectSummary));
        when(mockS3Client.listObjectsV2(any(ListObjectsV2Request.class))).thenReturn(listObjectsV2Result);

        // Configure AmazonS3.getObject(...).
        final S3Object spyS3Object = spy(new S3Object());
        spyS3Object.setBucketName("bucketName");
        spyS3Object.setKey("key");
        spyS3Object.setObjectContent(new ByteArrayInputStream(new byte[]{}));
        when(mockS3Client.getObject("bucketName", "id")).thenReturn(spyS3Object);

        // Run the test
        final Set<DataObject> result = myClassUnderTest.getAllObjects1("path");

        // Verify the results
        assertEquals(expectedResult, result);
        verify(spyS3Object).close();
    }

    @Test
    void testGetAllObjects1_AmazonS3GetObjectReturnsBrokenIo() throws Exception {
        // Setup
        // Configure AmazonS3.listObjectsV2(...).
        final ListObjectsV2Result listObjectsV2Result = new ListObjectsV2Result();
        listObjectsV2Result.setTruncated(false);
        listObjectsV2Result.setNextContinuationToken("continuationToken");
        final S3ObjectSummary s3ObjectSummary = new S3ObjectSummary();
        s3ObjectSummary.setBucketName("bucketName");
        s3ObjectSummary.setKey("id");
        listObjectsV2Result.getObjectSummaries().addAll(Arrays.asList(s3ObjectSummary));
        when(mockS3Client.listObjectsV2(any(ListObjectsV2Request.class))).thenReturn(listObjectsV2Result);

        // Configure AmazonS3.getObject(...).
        final S3Object spyS3Object = spy(new S3Object());
        spyS3Object.setBucketName("bucketName");
        spyS3Object.setKey("key");
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
        when(mockS3Client.getObject("bucketName", "id")).thenReturn(spyS3Object);

        // Run the test
        assertThrows(ServiceException.class, () -> myClassUnderTest.getAllObjects1("path"));
        verify(spyS3Object).close();
        verify(mockMetricsAdapter).recordIOException(eq("id"), any(IOException.class));
    }

    @Test
    void testGetAllObjects1_AmazonS3GetObjectThrowsSdkClientException() {
        // Setup
        // Configure AmazonS3.listObjectsV2(...).
        final ListObjectsV2Result listObjectsV2Result = new ListObjectsV2Result();
        listObjectsV2Result.setTruncated(false);
        listObjectsV2Result.setNextContinuationToken("continuationToken");
        final S3ObjectSummary s3ObjectSummary = new S3ObjectSummary();
        s3ObjectSummary.setBucketName("bucketName");
        s3ObjectSummary.setKey("id");
        listObjectsV2Result.getObjectSummaries().addAll(Arrays.asList(s3ObjectSummary));
        when(mockS3Client.listObjectsV2(any(ListObjectsV2Request.class))).thenReturn(listObjectsV2Result);

        when(mockS3Client.getObject("bucketName", "id")).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(ServiceException.class, () -> myClassUnderTest.getAllObjects1("path"));
        verify(mockMetricsAdapter).recordClientException(eq("id"), any(SdkClientException.class));
    }

    @Test
    void testGetAllObjects1_AmazonS3GetObjectThrowsAmazonServiceException() {
        // Setup
        // Configure AmazonS3.listObjectsV2(...).
        final ListObjectsV2Result listObjectsV2Result = new ListObjectsV2Result();
        listObjectsV2Result.setTruncated(false);
        listObjectsV2Result.setNextContinuationToken("continuationToken");
        final S3ObjectSummary s3ObjectSummary = new S3ObjectSummary();
        s3ObjectSummary.setBucketName("bucketName");
        s3ObjectSummary.setKey("id");
        listObjectsV2Result.getObjectSummaries().addAll(Arrays.asList(s3ObjectSummary));
        when(mockS3Client.listObjectsV2(any(ListObjectsV2Request.class))).thenReturn(listObjectsV2Result);

        when(mockS3Client.getObject("bucketName", "id")).thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(ServiceException.class, () -> myClassUnderTest.getAllObjects1("path"));
        verify(mockMetricsAdapter).recordAmazonServiceException(eq("id"), any(AmazonServiceException.class));
    }

    @Test
    void testGetAllObjects2() throws Exception {
        // Setup
        final Set<DataObject> expectedResult = new HashSet<>(Arrays.asList(new DataObject("id", "content")));

        // Configure AmazonS3.listObjects(...).
        final ObjectListing objectListing = new ObjectListing();
        objectListing.setNextMarker("marker");
        objectListing.setTruncated(false);
        final S3ObjectSummary s3ObjectSummary = new S3ObjectSummary();
        s3ObjectSummary.setBucketName("bucketName");
        s3ObjectSummary.setKey("id");
        objectListing.getObjectSummaries().addAll(Arrays.asList(s3ObjectSummary));
        when(mockS3Client.listObjects(any(ListObjectsRequest.class))).thenReturn(objectListing);

        // Configure AmazonS3.getObject(...).
        final S3Object spyS3Object = spy(new S3Object());
        spyS3Object.setBucketName("bucketName");
        spyS3Object.setKey("key");
        spyS3Object.setObjectContent(new ByteArrayInputStream("content".getBytes()));
        when(mockS3Client.getObject("bucketName", "id")).thenReturn(spyS3Object);

        // Run the test
        final Set<DataObject> result = myClassUnderTest.getAllObjects2("path");

        // Verify the results
        assertEquals(expectedResult, result);
        verify(spyS3Object).close();
    }

    @Test
    void testGetAllObjects2_AmazonS3ListObjectsReturnsNoItems() throws Exception {
        // Setup
        when(mockS3Client.listObjects(any(ListObjectsRequest.class))).thenReturn(new ObjectListing());

        // Run the test
        final Set<DataObject> result = myClassUnderTest.getAllObjects2("path");

        // Verify the results
        assertEquals(Collections.emptySet(), result);
    }

    @Test
    void testGetAllObjects2_AmazonS3ListObjectsThrowsSdkClientException() {
        // Setup
        when(mockS3Client.listObjects(any(ListObjectsRequest.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.getAllObjects2("path"));
    }

    @Test
    void testGetAllObjects2_AmazonS3ListObjectsThrowsAmazonServiceException() {
        // Setup
        when(mockS3Client.listObjects(any(ListObjectsRequest.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.getAllObjects2("path"));
    }

    @Test
    void testGetAllObjects2_AmazonS3GetObjectReturnsNoContent() throws Exception {
        // Setup
        final Set<DataObject> expectedResult = new HashSet<>(Arrays.asList(new DataObject("id", "content")));

        // Configure AmazonS3.listObjects(...).
        final ObjectListing objectListing = new ObjectListing();
        objectListing.setNextMarker("marker");
        objectListing.setTruncated(false);
        final S3ObjectSummary s3ObjectSummary = new S3ObjectSummary();
        s3ObjectSummary.setBucketName("bucketName");
        s3ObjectSummary.setKey("id");
        objectListing.getObjectSummaries().addAll(Arrays.asList(s3ObjectSummary));
        when(mockS3Client.listObjects(any(ListObjectsRequest.class))).thenReturn(objectListing);

        // Configure AmazonS3.getObject(...).
        final S3Object spyS3Object = spy(new S3Object());
        spyS3Object.setBucketName("bucketName");
        spyS3Object.setKey("key");
        spyS3Object.setObjectContent(new ByteArrayInputStream(new byte[]{}));
        when(mockS3Client.getObject("bucketName", "id")).thenReturn(spyS3Object);

        // Run the test
        final Set<DataObject> result = myClassUnderTest.getAllObjects2("path");

        // Verify the results
        assertEquals(expectedResult, result);
        verify(spyS3Object).close();
    }

    @Test
    void testGetAllObjects2_AmazonS3GetObjectReturnsBrokenIo() throws Exception {
        // Setup
        // Configure AmazonS3.listObjects(...).
        final ObjectListing objectListing = new ObjectListing();
        objectListing.setNextMarker("marker");
        objectListing.setTruncated(false);
        final S3ObjectSummary s3ObjectSummary = new S3ObjectSummary();
        s3ObjectSummary.setBucketName("bucketName");
        s3ObjectSummary.setKey("id");
        objectListing.getObjectSummaries().addAll(Arrays.asList(s3ObjectSummary));
        when(mockS3Client.listObjects(any(ListObjectsRequest.class))).thenReturn(objectListing);

        // Configure AmazonS3.getObject(...).
        final S3Object spyS3Object = spy(new S3Object());
        spyS3Object.setBucketName("bucketName");
        spyS3Object.setKey("key");
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
        when(mockS3Client.getObject("bucketName", "id")).thenReturn(spyS3Object);

        // Run the test
        assertThrows(ServiceException.class, () -> myClassUnderTest.getAllObjects2("path"));
        verify(spyS3Object).close();
        verify(mockMetricsAdapter).recordIOException(eq("id"), any(IOException.class));
    }

    @Test
    void testGetAllObjects2_AmazonS3GetObjectThrowsSdkClientException() {
        // Setup
        // Configure AmazonS3.listObjects(...).
        final ObjectListing objectListing = new ObjectListing();
        objectListing.setNextMarker("marker");
        objectListing.setTruncated(false);
        final S3ObjectSummary s3ObjectSummary = new S3ObjectSummary();
        s3ObjectSummary.setBucketName("bucketName");
        s3ObjectSummary.setKey("id");
        objectListing.getObjectSummaries().addAll(Arrays.asList(s3ObjectSummary));
        when(mockS3Client.listObjects(any(ListObjectsRequest.class))).thenReturn(objectListing);

        when(mockS3Client.getObject("bucketName", "id")).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(ServiceException.class, () -> myClassUnderTest.getAllObjects2("path"));
        verify(mockMetricsAdapter).recordClientException(eq("id"), any(SdkClientException.class));
    }

    @Test
    void testGetAllObjects2_AmazonS3GetObjectThrowsAmazonServiceException() {
        // Setup
        // Configure AmazonS3.listObjects(...).
        final ObjectListing objectListing = new ObjectListing();
        objectListing.setNextMarker("marker");
        objectListing.setTruncated(false);
        final S3ObjectSummary s3ObjectSummary = new S3ObjectSummary();
        s3ObjectSummary.setBucketName("bucketName");
        s3ObjectSummary.setKey("id");
        objectListing.getObjectSummaries().addAll(Arrays.asList(s3ObjectSummary));
        when(mockS3Client.listObjects(any(ListObjectsRequest.class))).thenReturn(objectListing);

        when(mockS3Client.getObject("bucketName", "id")).thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(ServiceException.class, () -> myClassUnderTest.getAllObjects2("path"));
        verify(mockMetricsAdapter).recordAmazonServiceException(eq("id"), any(AmazonServiceException.class));
    }

    @Test
    void testGetAllObjects3() throws Exception {
        // Setup
        final Set<DataObject> expectedResult = new HashSet<>(Arrays.asList(new DataObject("id", "content")));

        // Configure AmazonS3.listVersions(...).
        final VersionListing versionListing = new VersionListing();
        final S3VersionSummary s3VersionSummary = new S3VersionSummary();
        s3VersionSummary.setKey("id");
        s3VersionSummary.setVersionId("id");
        versionListing.setVersionSummaries(Arrays.asList(s3VersionSummary));
        versionListing.setNextKeyMarker("keyMarker");
        versionListing.setNextVersionIdMarker("versionIdMarker");
        versionListing.setTruncated(false);
        when(mockS3Client.listVersions(any(ListVersionsRequest.class))).thenReturn(versionListing);

        // Configure AmazonS3.getObject(...).
        final S3Object spyS3Object = spy(new S3Object());
        spyS3Object.setBucketName("bucketName");
        spyS3Object.setKey("key");
        spyS3Object.setObjectContent(new ByteArrayInputStream("content".getBytes()));
        when(mockS3Client.getObject(new GetObjectRequest("bucketName", "key"))).thenReturn(spyS3Object);

        // Run the test
        final Set<DataObject> result = myClassUnderTest.getAllObjects3("path");

        // Verify the results
        assertEquals(expectedResult, result);
        verify(spyS3Object).close();
    }

    @Test
    void testGetAllObjects3_AmazonS3ListVersionsReturnsNoItems() throws Exception {
        // Setup
        when(mockS3Client.listVersions(any(ListVersionsRequest.class))).thenReturn(new VersionListing());

        // Run the test
        final Set<DataObject> result = myClassUnderTest.getAllObjects3("path");

        // Verify the results
        assertEquals(Collections.emptySet(), result);
    }

    @Test
    void testGetAllObjects3_AmazonS3ListVersionsThrowsSdkClientException() {
        // Setup
        when(mockS3Client.listVersions(any(ListVersionsRequest.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.getAllObjects3("path"));
    }

    @Test
    void testGetAllObjects3_AmazonS3ListVersionsThrowsAmazonServiceException() {
        // Setup
        when(mockS3Client.listVersions(any(ListVersionsRequest.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.getAllObjects3("path"));
    }

    @Test
    void testGetAllObjects3_AmazonS3GetObjectReturnsNull() throws Exception {
        // Setup
        final Set<DataObject> expectedResult = new HashSet<>(Arrays.asList(new DataObject("id", "content")));

        // Configure AmazonS3.listVersions(...).
        final VersionListing versionListing = new VersionListing();
        final S3VersionSummary s3VersionSummary = new S3VersionSummary();
        s3VersionSummary.setKey("id");
        s3VersionSummary.setVersionId("id");
        versionListing.setVersionSummaries(Arrays.asList(s3VersionSummary));
        versionListing.setNextKeyMarker("keyMarker");
        versionListing.setNextVersionIdMarker("versionIdMarker");
        versionListing.setTruncated(false);
        when(mockS3Client.listVersions(any(ListVersionsRequest.class))).thenReturn(versionListing);

        when(mockS3Client.getObject(new GetObjectRequest("bucketName", "key"))).thenReturn(null);

        // Run the test
        final Set<DataObject> result = myClassUnderTest.getAllObjects3("path");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetAllObjects3_AmazonS3GetObjectReturnsNoContent() throws Exception {
        // Setup
        final Set<DataObject> expectedResult = new HashSet<>(Arrays.asList(new DataObject("id", "content")));

        // Configure AmazonS3.listVersions(...).
        final VersionListing versionListing = new VersionListing();
        final S3VersionSummary s3VersionSummary = new S3VersionSummary();
        s3VersionSummary.setKey("id");
        s3VersionSummary.setVersionId("id");
        versionListing.setVersionSummaries(Arrays.asList(s3VersionSummary));
        versionListing.setNextKeyMarker("keyMarker");
        versionListing.setNextVersionIdMarker("versionIdMarker");
        versionListing.setTruncated(false);
        when(mockS3Client.listVersions(any(ListVersionsRequest.class))).thenReturn(versionListing);

        // Configure AmazonS3.getObject(...).
        final S3Object spyS3Object = spy(new S3Object());
        spyS3Object.setBucketName("bucketName");
        spyS3Object.setKey("key");
        spyS3Object.setObjectContent(new ByteArrayInputStream(new byte[]{}));
        when(mockS3Client.getObject(new GetObjectRequest("bucketName", "key"))).thenReturn(spyS3Object);

        // Run the test
        final Set<DataObject> result = myClassUnderTest.getAllObjects3("path");

        // Verify the results
        assertEquals(expectedResult, result);
        verify(spyS3Object).close();
    }

    @Test
    void testGetAllObjects3_AmazonS3GetObjectReturnsBrokenIo() throws Exception {
        // Setup
        // Configure AmazonS3.listVersions(...).
        final VersionListing versionListing = new VersionListing();
        final S3VersionSummary s3VersionSummary = new S3VersionSummary();
        s3VersionSummary.setKey("id");
        s3VersionSummary.setVersionId("id");
        versionListing.setVersionSummaries(Arrays.asList(s3VersionSummary));
        versionListing.setNextKeyMarker("keyMarker");
        versionListing.setNextVersionIdMarker("versionIdMarker");
        versionListing.setTruncated(false);
        when(mockS3Client.listVersions(any(ListVersionsRequest.class))).thenReturn(versionListing);

        // Configure AmazonS3.getObject(...).
        final S3Object spyS3Object = spy(new S3Object());
        spyS3Object.setBucketName("bucketName");
        spyS3Object.setKey("key");
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
        when(mockS3Client.getObject(new GetObjectRequest("bucketName", "key"))).thenReturn(spyS3Object);

        // Run the test
        assertThrows(ServiceException.class, () -> myClassUnderTest.getAllObjects3("path"));
        verify(spyS3Object).close();
        verify(mockMetricsAdapter).recordIOException(eq("id"), any(IOException.class));
    }

    @Test
    void testGetAllObjects3_AmazonS3GetObjectThrowsSdkClientException() {
        // Setup
        // Configure AmazonS3.listVersions(...).
        final VersionListing versionListing = new VersionListing();
        final S3VersionSummary s3VersionSummary = new S3VersionSummary();
        s3VersionSummary.setKey("id");
        s3VersionSummary.setVersionId("id");
        versionListing.setVersionSummaries(Arrays.asList(s3VersionSummary));
        versionListing.setNextKeyMarker("keyMarker");
        versionListing.setNextVersionIdMarker("versionIdMarker");
        versionListing.setTruncated(false);
        when(mockS3Client.listVersions(any(ListVersionsRequest.class))).thenReturn(versionListing);

        when(mockS3Client.getObject(new GetObjectRequest("bucketName", "key"))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(ServiceException.class, () -> myClassUnderTest.getAllObjects3("path"));
        verify(mockMetricsAdapter).recordClientException(eq("id"), any(SdkClientException.class));
    }

    @Test
    void testGetAllObjects3_AmazonS3GetObjectThrowsAmazonServiceException() {
        // Setup
        // Configure AmazonS3.listVersions(...).
        final VersionListing versionListing = new VersionListing();
        final S3VersionSummary s3VersionSummary = new S3VersionSummary();
        s3VersionSummary.setKey("id");
        s3VersionSummary.setVersionId("id");
        versionListing.setVersionSummaries(Arrays.asList(s3VersionSummary));
        versionListing.setNextKeyMarker("keyMarker");
        versionListing.setNextVersionIdMarker("versionIdMarker");
        versionListing.setTruncated(false);
        when(mockS3Client.listVersions(any(ListVersionsRequest.class))).thenReturn(versionListing);

        when(mockS3Client.getObject(new GetObjectRequest("bucketName", "key"))).thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(ServiceException.class, () -> myClassUnderTest.getAllObjects3("path"));
        verify(mockMetricsAdapter).recordAmazonServiceException(eq("id"), any(AmazonServiceException.class));
    }
}
