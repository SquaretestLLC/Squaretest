package com.myapp;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.squaretest.demo1.Artifact;
import com.squaretest.demo1.metrics.MetricAdapter;
import com.squaretest.demo1.metrics.MetricEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private AmazonS3Client mockS3Client;
    @Mock
    private MetricAdapter mockMetricAdapter;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockS3Client, mockMetricAdapter, "bucketName");
    }

    @Test
    void testGetArtifact() throws Exception {
        // Setup
        // Configure AmazonS3Client.getObject(...).
        final S3Object spyS3Object = spy(new S3Object());
        spyS3Object.setBucketName("bucketName");
        spyS3Object.setKey("key");
        spyS3Object.setObjectContent(new ByteArrayInputStream("content".getBytes()));
        when(mockS3Client.getObject(new GetObjectRequest("bucketName", "key"))).thenReturn(spyS3Object);

        // Run the test
        final Artifact result = myClassUnderTest.getArtifact("path");

        // Verify the results
        verify(spyS3Object).close();
        verify(mockMetricAdapter).recordEvent(MetricEvent.NoObjectFound);
    }

    @Test
    void testGetArtifact_AmazonS3ClientReturnsNull() {
        // Setup
        when(mockS3Client.getObject(new GetObjectRequest("bucketName", "key"))).thenReturn(null);

        // Run the test
        final Artifact result = myClassUnderTest.getArtifact("path");

        // Verify the results
        assertNull(result);
        verify(mockMetricAdapter).recordEvent(MetricEvent.NoObjectFound);
    }

    @Test
    void testGetArtifact_AmazonS3ClientReturnsNoContent() throws Exception {
        // Setup
        // Configure AmazonS3Client.getObject(...).
        final S3Object spyS3Object = spy(new S3Object());
        spyS3Object.setBucketName("bucketName");
        spyS3Object.setKey("key");
        spyS3Object.setObjectContent(new ByteArrayInputStream(new byte[]{}));
        when(mockS3Client.getObject(new GetObjectRequest("bucketName", "key"))).thenReturn(spyS3Object);

        // Run the test
        final Artifact result = myClassUnderTest.getArtifact("path");

        // Verify the results
        verify(spyS3Object).close();
        verify(mockMetricAdapter).recordEvent(MetricEvent.NoObjectFound);
    }

    @Test
    void testGetArtifact_AmazonS3ClientReturnsBrokenIo() throws Exception {
        // Setup
        // Configure AmazonS3Client.getObject(...).
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
        final Artifact result = myClassUnderTest.getArtifact("path");

        // Verify the results
        verify(spyS3Object).close();
        verify(mockMetricAdapter).recordEvent(MetricEvent.NoObjectFound);
    }

    @Test
    void testGetArtifact_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.getObject(new GetObjectRequest("bucketName", "key"))).thenThrow(SdkClientException.class);

        // Run the test
        final Artifact result = myClassUnderTest.getArtifact("path");

        // Verify the results
        verify(mockMetricAdapter).recordEvent(MetricEvent.NoObjectFound);
    }

    @Test
    void testGetArtifact_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockS3Client.getObject(new GetObjectRequest("bucketName", "key"))).thenThrow(AmazonServiceException.class);

        // Run the test
        final Artifact result = myClassUnderTest.getArtifact("path");

        // Verify the results
        verify(mockMetricAdapter).recordEvent(MetricEvent.NoObjectFound);
    }

    @Test
    void testGetArtifacts() throws Exception {
        // Setup
        // Configure AmazonS3.listObjectsV2(...).
        final ListObjectsV2Result listObjectsV2Result = new ListObjectsV2Result();
        listObjectsV2Result.setKeyCount(0);
        final S3ObjectSummary s3ObjectSummary = new S3ObjectSummary();
        s3ObjectSummary.setBucketName("bucketName");
        s3ObjectSummary.setKey("path");
        s3ObjectSummary.setETag("eTag");
        listObjectsV2Result.getObjectSummaries().addAll(Arrays.asList(s3ObjectSummary));
        when(mockS3Client.listObjectsV2("bucketName", "path")).thenReturn(listObjectsV2Result);

        // Configure AmazonS3Client.getObject(...).
        final S3Object spyS3Object = spy(new S3Object());
        spyS3Object.setBucketName("bucketName");
        spyS3Object.setKey("key");
        spyS3Object.setObjectContent(new ByteArrayInputStream("content".getBytes()));
        when(mockS3Client.getObject(new GetObjectRequest("bucketName", "key"))).thenReturn(spyS3Object);

        // Run the test
        final List<Artifact> result = myClassUnderTest.getArtifacts("path");

        // Verify the results
        verify(spyS3Object).close();
        verify(mockMetricAdapter).recordEvent(MetricEvent.NoObjectFound);
    }

    @Test
    void testGetArtifacts_AmazonS3ListObjectsV2ReturnsNoItems() {
        // Setup
        when(mockS3Client.listObjectsV2("bucketName", "path")).thenReturn(new ListObjectsV2Result());

        // Run the test
        final List<Artifact> result = myClassUnderTest.getArtifacts("path");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetArtifacts_AmazonS3ListObjectsV2ThrowsSdkClientException() {
        // Setup
        when(mockS3Client.listObjectsV2("bucketName", "path")).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.getArtifacts("path"));
    }

    @Test
    void testGetArtifacts_AmazonS3ListObjectsV2ThrowsAmazonServiceException() {
        // Setup
        when(mockS3Client.listObjectsV2("bucketName", "path")).thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.getArtifacts("path"));
    }

    @Test
    void testGetArtifacts_AmazonS3ClientGetObjectReturnsNull() {
        // Setup
        // Configure AmazonS3.listObjectsV2(...).
        final ListObjectsV2Result listObjectsV2Result = new ListObjectsV2Result();
        listObjectsV2Result.setKeyCount(0);
        final S3ObjectSummary s3ObjectSummary = new S3ObjectSummary();
        s3ObjectSummary.setBucketName("bucketName");
        s3ObjectSummary.setKey("path");
        s3ObjectSummary.setETag("eTag");
        listObjectsV2Result.getObjectSummaries().addAll(Arrays.asList(s3ObjectSummary));
        when(mockS3Client.listObjectsV2("bucketName", "path")).thenReturn(listObjectsV2Result);

        when(mockS3Client.getObject(new GetObjectRequest("bucketName", "key"))).thenReturn(null);

        // Run the test
        final List<Artifact> result = myClassUnderTest.getArtifacts("path");

        // Verify the results
        verify(mockMetricAdapter).recordEvent(MetricEvent.NoObjectFound);
    }

    @Test
    void testGetArtifacts_AmazonS3ClientGetObjectReturnsNoContent() throws Exception {
        // Setup
        // Configure AmazonS3.listObjectsV2(...).
        final ListObjectsV2Result listObjectsV2Result = new ListObjectsV2Result();
        listObjectsV2Result.setKeyCount(0);
        final S3ObjectSummary s3ObjectSummary = new S3ObjectSummary();
        s3ObjectSummary.setBucketName("bucketName");
        s3ObjectSummary.setKey("path");
        s3ObjectSummary.setETag("eTag");
        listObjectsV2Result.getObjectSummaries().addAll(Arrays.asList(s3ObjectSummary));
        when(mockS3Client.listObjectsV2("bucketName", "path")).thenReturn(listObjectsV2Result);

        // Configure AmazonS3Client.getObject(...).
        final S3Object spyS3Object = spy(new S3Object());
        spyS3Object.setBucketName("bucketName");
        spyS3Object.setKey("key");
        spyS3Object.setObjectContent(new ByteArrayInputStream(new byte[]{}));
        when(mockS3Client.getObject(new GetObjectRequest("bucketName", "key"))).thenReturn(spyS3Object);

        // Run the test
        final List<Artifact> result = myClassUnderTest.getArtifacts("path");

        // Verify the results
        verify(spyS3Object).close();
        verify(mockMetricAdapter).recordEvent(MetricEvent.NoObjectFound);
    }

    @Test
    void testGetArtifacts_AmazonS3ClientGetObjectReturnsBrokenIo() throws Exception {
        // Setup
        // Configure AmazonS3.listObjectsV2(...).
        final ListObjectsV2Result listObjectsV2Result = new ListObjectsV2Result();
        listObjectsV2Result.setKeyCount(0);
        final S3ObjectSummary s3ObjectSummary = new S3ObjectSummary();
        s3ObjectSummary.setBucketName("bucketName");
        s3ObjectSummary.setKey("path");
        s3ObjectSummary.setETag("eTag");
        listObjectsV2Result.getObjectSummaries().addAll(Arrays.asList(s3ObjectSummary));
        when(mockS3Client.listObjectsV2("bucketName", "path")).thenReturn(listObjectsV2Result);

        // Configure AmazonS3Client.getObject(...).
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
        final List<Artifact> result = myClassUnderTest.getArtifacts("path");

        // Verify the results
        verify(spyS3Object).close();
        verify(mockMetricAdapter).recordEvent(MetricEvent.NoObjectFound);
    }

    @Test
    void testGetArtifacts_AmazonS3ClientGetObjectThrowsSdkClientException() {
        // Setup
        // Configure AmazonS3.listObjectsV2(...).
        final ListObjectsV2Result listObjectsV2Result = new ListObjectsV2Result();
        listObjectsV2Result.setKeyCount(0);
        final S3ObjectSummary s3ObjectSummary = new S3ObjectSummary();
        s3ObjectSummary.setBucketName("bucketName");
        s3ObjectSummary.setKey("path");
        s3ObjectSummary.setETag("eTag");
        listObjectsV2Result.getObjectSummaries().addAll(Arrays.asList(s3ObjectSummary));
        when(mockS3Client.listObjectsV2("bucketName", "path")).thenReturn(listObjectsV2Result);

        when(mockS3Client.getObject(new GetObjectRequest("bucketName", "key"))).thenThrow(SdkClientException.class);

        // Run the test
        final List<Artifact> result = myClassUnderTest.getArtifacts("path");

        // Verify the results
        verify(mockMetricAdapter).recordEvent(MetricEvent.NoObjectFound);
    }

    @Test
    void testGetArtifacts_AmazonS3ClientGetObjectThrowsAmazonServiceException() {
        // Setup
        // Configure AmazonS3.listObjectsV2(...).
        final ListObjectsV2Result listObjectsV2Result = new ListObjectsV2Result();
        listObjectsV2Result.setKeyCount(0);
        final S3ObjectSummary s3ObjectSummary = new S3ObjectSummary();
        s3ObjectSummary.setBucketName("bucketName");
        s3ObjectSummary.setKey("path");
        s3ObjectSummary.setETag("eTag");
        listObjectsV2Result.getObjectSummaries().addAll(Arrays.asList(s3ObjectSummary));
        when(mockS3Client.listObjectsV2("bucketName", "path")).thenReturn(listObjectsV2Result);

        when(mockS3Client.getObject(new GetObjectRequest("bucketName", "key"))).thenThrow(AmazonServiceException.class);

        // Run the test
        final List<Artifact> result = myClassUnderTest.getArtifacts("path");

        // Verify the results
        verify(mockMetricAdapter).recordEvent(MetricEvent.NoObjectFound);
    }
}
