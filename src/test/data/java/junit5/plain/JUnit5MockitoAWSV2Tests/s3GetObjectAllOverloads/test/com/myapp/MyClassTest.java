package com.myapp;

import com.myapp.metrics.MetricAdapter;
import com.myapp.metrics.MetricEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.http.AbortableInputStream;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.NoSuchKeyException;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private S3Client mockS3Client;
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
        // Configure S3Client.getObject(...).
        final ResponseInputStream<GetObjectResponse> spyResponseInputStream = spy(
                new ResponseInputStream<>(GetObjectResponse.builder().build(),
                        AbortableInputStream.create(new ByteArrayInputStream("objectContent".getBytes()))));
        when(mockS3Client.getObject(GetObjectRequest.builder()
                .bucket("bucketName")
                .key("path")
                .build())).thenReturn(spyResponseInputStream);

        // Run the test
        final Artifact result = myClassUnderTest.getArtifact("path");

        // Verify the results
        verify(spyResponseInputStream).close();
        verify(mockMetricAdapter).recordEvent(MetricEvent.Success);
    }

    @Test
    void testGetArtifact_S3ClientReturnsNoContent() throws Exception {
        // Setup
        // Configure S3Client.getObject(...).
        final ResponseInputStream<GetObjectResponse> spyResponseInputStream = spy(
                new ResponseInputStream<>(GetObjectResponse.builder().build(),
                        AbortableInputStream.create(new ByteArrayInputStream(new byte[]{}))));
        when(mockS3Client.getObject(GetObjectRequest.builder()
                .bucket("bucketName")
                .key("path")
                .build())).thenReturn(spyResponseInputStream);

        // Run the test
        final Artifact result = myClassUnderTest.getArtifact("path");

        // Verify the results
        verify(spyResponseInputStream).close();
        verify(mockMetricAdapter).recordEvent(MetricEvent.Success);
    }

    @Test
    void testGetArtifact_S3ClientReturnsBrokenIo() throws Exception {
        // Setup
        // Configure S3Client.getObject(...).
        final ResponseInputStream<GetObjectResponse> spyResponseInputStream = spy(
                new ResponseInputStream<>(GetObjectResponse.builder().build(),
                        AbortableInputStream.create(new InputStream() {
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
                        })));
        when(mockS3Client.getObject(GetObjectRequest.builder()
                .bucket("bucketName")
                .key("path")
                .build())).thenReturn(spyResponseInputStream);

        // Run the test
        final Artifact result = myClassUnderTest.getArtifact("path");

        // Verify the results
        assertNull(result);
        verify(spyResponseInputStream).close();
        verify(mockMetricAdapter).recordEvent(MetricEvent.Success);
    }

    @Test
    void testGetArtifact_S3ClientThrowsNoSuchKeyException() {
        // Setup
        when(mockS3Client.getObject(GetObjectRequest.builder()
                .bucket("bucketName")
                .key("path")
                .build())).thenThrow(NoSuchKeyException.class);

        // Run the test
        final Artifact result = myClassUnderTest.getArtifact("path");

        // Verify the results
        assertNull(result);
        verify(mockMetricAdapter).recordEvent(MetricEvent.Success);
    }

    @Test
    void testGetArtifact_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.getObject(GetObjectRequest.builder()
                .bucket("bucketName")
                .key("path")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.getArtifact("path"));
    }

    @Test
    void testGetArtifact_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.getObject(GetObjectRequest.builder()
                .bucket("bucketName")
                .key("path")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        final Artifact result = myClassUnderTest.getArtifact("path");

        // Verify the results
        assertNull(result);
        verify(mockMetricAdapter).recordEvent(MetricEvent.Success);
    }

    @Test
    void testGetArtifact_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.getObject(GetObjectRequest.builder()
                .bucket("bucketName")
                .key("path")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        final Artifact result = myClassUnderTest.getArtifact("path");

        // Verify the results
        assertNull(result);
        verify(mockMetricAdapter).recordEvent(MetricEvent.Success);
    }

    @Test
    void testGetArtifactWithConsumer() throws Exception {
        // Setup
        // Configure S3Client.getObject(...).
        final ResponseInputStream<GetObjectResponse> spyResponseInputStream = spy(
                new ResponseInputStream<>(GetObjectResponse.builder().build(),
                        AbortableInputStream.create(new ByteArrayInputStream("objectContent".getBytes()))));
        when(mockS3Client.getObject(any(Consumer.class))).thenReturn(spyResponseInputStream);

        // Run the test
        final Artifact result = myClassUnderTest.getArtifactWithConsumer("path");

        // Verify the results
        verify(spyResponseInputStream).close();
        verify(mockMetricAdapter).recordEvent(MetricEvent.Success);
    }

    @Test
    void testGetArtifactWithConsumer_S3ClientReturnsNoContent() throws Exception {
        // Setup
        // Configure S3Client.getObject(...).
        final ResponseInputStream<GetObjectResponse> spyResponseInputStream = spy(
                new ResponseInputStream<>(GetObjectResponse.builder().build(),
                        AbortableInputStream.create(new ByteArrayInputStream(new byte[]{}))));
        when(mockS3Client.getObject(any(Consumer.class))).thenReturn(spyResponseInputStream);

        // Run the test
        final Artifact result = myClassUnderTest.getArtifactWithConsumer("path");

        // Verify the results
        verify(spyResponseInputStream).close();
        verify(mockMetricAdapter).recordEvent(MetricEvent.Success);
    }

    @Test
    void testGetArtifactWithConsumer_S3ClientReturnsBrokenIo() throws Exception {
        // Setup
        // Configure S3Client.getObject(...).
        final ResponseInputStream<GetObjectResponse> spyResponseInputStream = spy(
                new ResponseInputStream<>(GetObjectResponse.builder().build(),
                        AbortableInputStream.create(new InputStream() {
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
                        })));
        when(mockS3Client.getObject(any(Consumer.class))).thenReturn(spyResponseInputStream);

        // Run the test
        final Artifact result = myClassUnderTest.getArtifactWithConsumer("path");

        // Verify the results
        assertNull(result);
        verify(spyResponseInputStream).close();
        verify(mockMetricAdapter).recordEvent(MetricEvent.Success);
    }

    @Test
    void testGetArtifactWithConsumer_S3ClientThrowsNoSuchKeyException() {
        // Setup
        when(mockS3Client.getObject(any(Consumer.class))).thenThrow(NoSuchKeyException.class);

        // Run the test
        final Artifact result = myClassUnderTest.getArtifactWithConsumer("path");

        // Verify the results
        assertNull(result);
        verify(mockMetricAdapter).recordEvent(MetricEvent.Success);
    }

    @Test
    void testGetArtifactWithConsumer_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.getObject(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.getArtifactWithConsumer("path"));
    }

    @Test
    void testGetArtifactWithConsumer_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.getObject(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        final Artifact result = myClassUnderTest.getArtifactWithConsumer("path");

        // Verify the results
        assertNull(result);
        verify(mockMetricAdapter).recordEvent(MetricEvent.Success);
    }

    @Test
    void testGetArtifactWithConsumer_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.getObject(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        final Artifact result = myClassUnderTest.getArtifactWithConsumer("path");

        // Verify the results
        assertNull(result);
        verify(mockMetricAdapter).recordEvent(MetricEvent.Success);
    }
}
