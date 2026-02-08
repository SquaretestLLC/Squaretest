package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.http.AbortableInputStream;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.s3.paginators.ListObjectsV2Iterable;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private S3Client mockS3Client;
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
        final List<DataObject> expectedResult = List.of(new DataObject("id", "content"));

        // Configure S3Client.listObjectsV2(...).
        final ListObjectsV2Response listObjectsV2Response = ListObjectsV2Response.builder()
                .prefix("prefix")
                .contents(S3Object.builder()
                        .key("key")
                        .build())
                .isTruncated(false)
                .build();
        when(mockS3Client.listObjectsV2(ListObjectsV2Request.builder()
                .bucket("bucketName")
                .prefix("path")
                .build())).thenReturn(listObjectsV2Response);

        // Configure S3Client.getObject(...).
        final ResponseInputStream<GetObjectResponse> spyResponseInputStream = spy(
                new ResponseInputStream<>(GetObjectResponse.builder().build(),
                        AbortableInputStream.create(new ByteArrayInputStream("objectContent".getBytes()))));
        when(mockS3Client.getObject(GetObjectRequest.builder()
                .bucket("bucketName")
                .key("id")
                .build())).thenReturn(spyResponseInputStream);

        // Run the test
        final List<DataObject> result = myClassUnderTest.getFirstPage1("path");

        // Verify the results
        assertEquals(expectedResult, result);
        verify(spyResponseInputStream).close();
    }

    @Test
    void testGetFirstPage1_S3ClientListObjectsV2ReturnsNoItems() throws Exception {
        // Setup
        // Configure S3Client.listObjectsV2(...).
        final ListObjectsV2Response listObjectsV2Response = ListObjectsV2Response.builder()
                .prefix("prefix")
                .isTruncated(false)
                .build();
        when(mockS3Client.listObjectsV2(ListObjectsV2Request.builder()
                .bucket("bucketName")
                .prefix("path")
                .build())).thenReturn(listObjectsV2Response);

        // Run the test
        final List<DataObject> result = myClassUnderTest.getFirstPage1("path");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetFirstPage1_S3ClientListObjectsV2ThrowsNoSuchBucketException() {
        // Setup
        when(mockS3Client.listObjectsV2(ListObjectsV2Request.builder()
                .bucket("bucketName")
                .prefix("path")
                .build())).thenThrow(NoSuchBucketException.class);

        // Run the test
        assertThrows(NoSuchBucketException.class, () -> myClassUnderTest.getFirstPage1("path"));
    }

    @Test
    void testGetFirstPage1_S3ClientListObjectsV2ThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.listObjectsV2(ListObjectsV2Request.builder()
                .bucket("bucketName")
                .prefix("path")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.getFirstPage1("path"));
    }

    @Test
    void testGetFirstPage1_S3ClientListObjectsV2ThrowsSdkClientException() {
        // Setup
        when(mockS3Client.listObjectsV2(ListObjectsV2Request.builder()
                .bucket("bucketName")
                .prefix("path")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.getFirstPage1("path"));
    }

    @Test
    void testGetFirstPage1_S3ClientListObjectsV2ThrowsS3Exception() {
        // Setup
        when(mockS3Client.listObjectsV2(ListObjectsV2Request.builder()
                .bucket("bucketName")
                .prefix("path")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.getFirstPage1("path"));
    }

    @Test
    void testGetFirstPage1_S3ClientGetObjectReturnsNoContent() throws Exception {
        // Setup
        final List<DataObject> expectedResult = List.of(new DataObject("id", "content"));

        // Configure S3Client.listObjectsV2(...).
        final ListObjectsV2Response listObjectsV2Response = ListObjectsV2Response.builder()
                .prefix("prefix")
                .contents(S3Object.builder()
                        .key("key")
                        .build())
                .isTruncated(false)
                .build();
        when(mockS3Client.listObjectsV2(ListObjectsV2Request.builder()
                .bucket("bucketName")
                .prefix("path")
                .build())).thenReturn(listObjectsV2Response);

        // Configure S3Client.getObject(...).
        final ResponseInputStream<GetObjectResponse> spyResponseInputStream = spy(
                new ResponseInputStream<>(GetObjectResponse.builder().build(),
                        AbortableInputStream.create(new ByteArrayInputStream(new byte[]{}))));
        when(mockS3Client.getObject(GetObjectRequest.builder()
                .bucket("bucketName")
                .key("id")
                .build())).thenReturn(spyResponseInputStream);

        // Run the test
        final List<DataObject> result = myClassUnderTest.getFirstPage1("path");

        // Verify the results
        assertEquals(expectedResult, result);
        verify(spyResponseInputStream).close();
    }

    @Test
    void testGetFirstPage1_S3ClientGetObjectReturnsBrokenIo() throws Exception {
        // Setup
        // Configure S3Client.listObjectsV2(...).
        final ListObjectsV2Response listObjectsV2Response = ListObjectsV2Response.builder()
                .prefix("prefix")
                .contents(S3Object.builder()
                        .key("key")
                        .build())
                .isTruncated(false)
                .build();
        when(mockS3Client.listObjectsV2(ListObjectsV2Request.builder()
                .bucket("bucketName")
                .prefix("path")
                .build())).thenReturn(listObjectsV2Response);

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
                .key("id")
                .build())).thenReturn(spyResponseInputStream);

        // Run the test
        assertThrows(ServiceException.class, () -> myClassUnderTest.getFirstPage1("path"));
        verify(spyResponseInputStream).close();
        verify(mockMetricsAdapter).recordIOException(eq("id"), any(IOException.class));
    }

    @Test
    void testGetFirstPage1_S3ClientGetObjectThrowsNoSuchKeyException() {
        // Setup
        // Configure S3Client.listObjectsV2(...).
        final ListObjectsV2Response listObjectsV2Response = ListObjectsV2Response.builder()
                .prefix("prefix")
                .contents(S3Object.builder()
                        .key("key")
                        .build())
                .isTruncated(false)
                .build();
        when(mockS3Client.listObjectsV2(ListObjectsV2Request.builder()
                .bucket("bucketName")
                .prefix("path")
                .build())).thenReturn(listObjectsV2Response);

        when(mockS3Client.getObject(GetObjectRequest.builder()
                .bucket("bucketName")
                .key("id")
                .build())).thenThrow(NoSuchKeyException.class);

        // Run the test
        assertThrows(ServiceException.class, () -> myClassUnderTest.getFirstPage1("path"));
        verify(mockMetricsAdapter).recordAmazonServiceException(eq("id"), any(S3Exception.class));
    }

    @Test
    void testGetFirstPage1_S3ClientGetObjectThrowsAwsServiceException() {
        // Setup
        // Configure S3Client.listObjectsV2(...).
        final ListObjectsV2Response listObjectsV2Response = ListObjectsV2Response.builder()
                .prefix("prefix")
                .contents(S3Object.builder()
                        .key("key")
                        .build())
                .isTruncated(false)
                .build();
        when(mockS3Client.listObjectsV2(ListObjectsV2Request.builder()
                .bucket("bucketName")
                .prefix("path")
                .build())).thenReturn(listObjectsV2Response);

        when(mockS3Client.getObject(GetObjectRequest.builder()
                .bucket("bucketName")
                .key("id")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.getFirstPage1("path"));
    }

    @Test
    void testGetFirstPage1_S3ClientGetObjectThrowsSdkClientException() {
        // Setup
        // Configure S3Client.listObjectsV2(...).
        final ListObjectsV2Response listObjectsV2Response = ListObjectsV2Response.builder()
                .prefix("prefix")
                .contents(S3Object.builder()
                        .key("key")
                        .build())
                .isTruncated(false)
                .build();
        when(mockS3Client.listObjectsV2(ListObjectsV2Request.builder()
                .bucket("bucketName")
                .prefix("path")
                .build())).thenReturn(listObjectsV2Response);

        when(mockS3Client.getObject(GetObjectRequest.builder()
                .bucket("bucketName")
                .key("id")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(ServiceException.class, () -> myClassUnderTest.getFirstPage1("path"));
        verify(mockMetricsAdapter).recordClientException(eq("id"), any(SdkClientException.class));
    }

    @Test
    void testGetFirstPage1_S3ClientGetObjectThrowsS3Exception() {
        // Setup
        // Configure S3Client.listObjectsV2(...).
        final ListObjectsV2Response listObjectsV2Response = ListObjectsV2Response.builder()
                .prefix("prefix")
                .contents(S3Object.builder()
                        .key("key")
                        .build())
                .isTruncated(false)
                .build();
        when(mockS3Client.listObjectsV2(ListObjectsV2Request.builder()
                .bucket("bucketName")
                .prefix("path")
                .build())).thenReturn(listObjectsV2Response);

        when(mockS3Client.getObject(GetObjectRequest.builder()
                .bucket("bucketName")
                .key("id")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(ServiceException.class, () -> myClassUnderTest.getFirstPage1("path"));
        verify(mockMetricsAdapter).recordAmazonServiceException(eq("id"), any(S3Exception.class));
    }

    @Test
    void testGetFirstPage2() throws Exception {
        // Setup
        final List<DataObject> expectedResult = List.of(new DataObject("id", "content"));

        // Configure S3Client.listObjectsV2(...).
        final ListObjectsV2Response listObjectsV2Response = ListObjectsV2Response.builder()
                .prefix("prefix")
                .contents(S3Object.builder()
                        .key("key")
                        .build())
                .isTruncated(false)
                .build();
        when(mockS3Client.listObjectsV2(ListObjectsV2Request.builder()
                .bucket("bucketName")
                .prefix("path")
                .build())).thenReturn(listObjectsV2Response);

        // Configure S3Client.getObject(...).
        final ResponseInputStream<GetObjectResponse> spyResponseInputStream = spy(
                new ResponseInputStream<>(GetObjectResponse.builder().build(),
                        AbortableInputStream.create(new ByteArrayInputStream("objectContent".getBytes()))));
        when(mockS3Client.getObject(GetObjectRequest.builder()
                .bucket("bucketName")
                .key("id")
                .build())).thenReturn(spyResponseInputStream);

        // Run the test
        final List<DataObject> result = myClassUnderTest.getFirstPage2("path");

        // Verify the results
        assertEquals(expectedResult, result);
        verify(spyResponseInputStream).close();
    }

    @Test
    void testGetFirstPage2_S3ClientListObjectsV2ReturnsNoItems() throws Exception {
        // Setup
        // Configure S3Client.listObjectsV2(...).
        final ListObjectsV2Response listObjectsV2Response = ListObjectsV2Response.builder()
                .prefix("prefix")
                .isTruncated(false)
                .build();
        when(mockS3Client.listObjectsV2(ListObjectsV2Request.builder()
                .bucket("bucketName")
                .prefix("path")
                .build())).thenReturn(listObjectsV2Response);

        // Run the test
        final List<DataObject> result = myClassUnderTest.getFirstPage2("path");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetFirstPage2_S3ClientListObjectsV2ThrowsNoSuchBucketException() {
        // Setup
        when(mockS3Client.listObjectsV2(ListObjectsV2Request.builder()
                .bucket("bucketName")
                .prefix("path")
                .build())).thenThrow(NoSuchBucketException.class);

        // Run the test
        assertThrows(NoSuchBucketException.class, () -> myClassUnderTest.getFirstPage2("path"));
    }

    @Test
    void testGetFirstPage2_S3ClientListObjectsV2ThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.listObjectsV2(ListObjectsV2Request.builder()
                .bucket("bucketName")
                .prefix("path")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.getFirstPage2("path"));
    }

    @Test
    void testGetFirstPage2_S3ClientListObjectsV2ThrowsSdkClientException() {
        // Setup
        when(mockS3Client.listObjectsV2(ListObjectsV2Request.builder()
                .bucket("bucketName")
                .prefix("path")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.getFirstPage2("path"));
    }

    @Test
    void testGetFirstPage2_S3ClientListObjectsV2ThrowsS3Exception() {
        // Setup
        when(mockS3Client.listObjectsV2(ListObjectsV2Request.builder()
                .bucket("bucketName")
                .prefix("path")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.getFirstPage2("path"));
    }

    @Test
    void testGetFirstPage2_S3ClientGetObjectReturnsNoContent() throws Exception {
        // Setup
        final List<DataObject> expectedResult = List.of(new DataObject("id", "content"));

        // Configure S3Client.listObjectsV2(...).
        final ListObjectsV2Response listObjectsV2Response = ListObjectsV2Response.builder()
                .prefix("prefix")
                .contents(S3Object.builder()
                        .key("key")
                        .build())
                .isTruncated(false)
                .build();
        when(mockS3Client.listObjectsV2(ListObjectsV2Request.builder()
                .bucket("bucketName")
                .prefix("path")
                .build())).thenReturn(listObjectsV2Response);

        // Configure S3Client.getObject(...).
        final ResponseInputStream<GetObjectResponse> spyResponseInputStream = spy(
                new ResponseInputStream<>(GetObjectResponse.builder().build(),
                        AbortableInputStream.create(new ByteArrayInputStream(new byte[]{}))));
        when(mockS3Client.getObject(GetObjectRequest.builder()
                .bucket("bucketName")
                .key("id")
                .build())).thenReturn(spyResponseInputStream);

        // Run the test
        final List<DataObject> result = myClassUnderTest.getFirstPage2("path");

        // Verify the results
        assertEquals(expectedResult, result);
        verify(spyResponseInputStream).close();
    }

    @Test
    void testGetFirstPage2_S3ClientGetObjectReturnsBrokenIo() throws Exception {
        // Setup
        // Configure S3Client.listObjectsV2(...).
        final ListObjectsV2Response listObjectsV2Response = ListObjectsV2Response.builder()
                .prefix("prefix")
                .contents(S3Object.builder()
                        .key("key")
                        .build())
                .isTruncated(false)
                .build();
        when(mockS3Client.listObjectsV2(ListObjectsV2Request.builder()
                .bucket("bucketName")
                .prefix("path")
                .build())).thenReturn(listObjectsV2Response);

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
                .key("id")
                .build())).thenReturn(spyResponseInputStream);

        // Run the test
        assertThrows(ServiceException.class, () -> myClassUnderTest.getFirstPage2("path"));
        verify(spyResponseInputStream).close();
        verify(mockMetricsAdapter).recordIOException(eq("id"), any(IOException.class));
    }

    @Test
    void testGetFirstPage2_S3ClientGetObjectThrowsNoSuchKeyException() {
        // Setup
        // Configure S3Client.listObjectsV2(...).
        final ListObjectsV2Response listObjectsV2Response = ListObjectsV2Response.builder()
                .prefix("prefix")
                .contents(S3Object.builder()
                        .key("key")
                        .build())
                .isTruncated(false)
                .build();
        when(mockS3Client.listObjectsV2(ListObjectsV2Request.builder()
                .bucket("bucketName")
                .prefix("path")
                .build())).thenReturn(listObjectsV2Response);

        when(mockS3Client.getObject(GetObjectRequest.builder()
                .bucket("bucketName")
                .key("id")
                .build())).thenThrow(NoSuchKeyException.class);

        // Run the test
        assertThrows(ServiceException.class, () -> myClassUnderTest.getFirstPage2("path"));
        verify(mockMetricsAdapter).recordAmazonServiceException(eq("id"), any(S3Exception.class));
    }

    @Test
    void testGetFirstPage2_S3ClientGetObjectThrowsAwsServiceException() {
        // Setup
        // Configure S3Client.listObjectsV2(...).
        final ListObjectsV2Response listObjectsV2Response = ListObjectsV2Response.builder()
                .prefix("prefix")
                .contents(S3Object.builder()
                        .key("key")
                        .build())
                .isTruncated(false)
                .build();
        when(mockS3Client.listObjectsV2(ListObjectsV2Request.builder()
                .bucket("bucketName")
                .prefix("path")
                .build())).thenReturn(listObjectsV2Response);

        when(mockS3Client.getObject(GetObjectRequest.builder()
                .bucket("bucketName")
                .key("id")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.getFirstPage2("path"));
    }

    @Test
    void testGetFirstPage2_S3ClientGetObjectThrowsSdkClientException() {
        // Setup
        // Configure S3Client.listObjectsV2(...).
        final ListObjectsV2Response listObjectsV2Response = ListObjectsV2Response.builder()
                .prefix("prefix")
                .contents(S3Object.builder()
                        .key("key")
                        .build())
                .isTruncated(false)
                .build();
        when(mockS3Client.listObjectsV2(ListObjectsV2Request.builder()
                .bucket("bucketName")
                .prefix("path")
                .build())).thenReturn(listObjectsV2Response);

        when(mockS3Client.getObject(GetObjectRequest.builder()
                .bucket("bucketName")
                .key("id")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(ServiceException.class, () -> myClassUnderTest.getFirstPage2("path"));
        verify(mockMetricsAdapter).recordClientException(eq("id"), any(SdkClientException.class));
    }

    @Test
    void testGetFirstPage2_S3ClientGetObjectThrowsS3Exception() {
        // Setup
        // Configure S3Client.listObjectsV2(...).
        final ListObjectsV2Response listObjectsV2Response = ListObjectsV2Response.builder()
                .prefix("prefix")
                .contents(S3Object.builder()
                        .key("key")
                        .build())
                .isTruncated(false)
                .build();
        when(mockS3Client.listObjectsV2(ListObjectsV2Request.builder()
                .bucket("bucketName")
                .prefix("path")
                .build())).thenReturn(listObjectsV2Response);

        when(mockS3Client.getObject(GetObjectRequest.builder()
                .bucket("bucketName")
                .key("id")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(ServiceException.class, () -> myClassUnderTest.getFirstPage2("path"));
        verify(mockMetricsAdapter).recordAmazonServiceException(eq("id"), any(S3Exception.class));
    }

    @Test
    void testGetFirstPage3() throws Exception {
        // Setup
        final List<DataObject> expectedResult = List.of(new DataObject("id", "content"));

        // Configure S3Client.listObjectsV2(...).
        final ListObjectsV2Response listObjectsV2Response = ListObjectsV2Response.builder()
                .prefix("prefix")
                .contents(S3Object.builder()
                        .key("key")
                        .build())
                .isTruncated(false)
                .build();
        when(mockS3Client.listObjectsV2(ListObjectsV2Request.builder()
                .bucket("bucketName")
                .prefix("path")
                .build())).thenReturn(listObjectsV2Response);

        // Configure S3Client.getObject(...).
        final ResponseInputStream<GetObjectResponse> spyResponseInputStream = spy(
                new ResponseInputStream<>(GetObjectResponse.builder().build(),
                        AbortableInputStream.create(new ByteArrayInputStream("objectContent".getBytes()))));
        when(mockS3Client.getObject(GetObjectRequest.builder()
                .bucket("bucketName")
                .key("id")
                .build())).thenReturn(spyResponseInputStream);

        // Run the test
        final List<DataObject> result = myClassUnderTest.getFirstPage3("path");

        // Verify the results
        assertEquals(expectedResult, result);
        verify(spyResponseInputStream).close();
    }

    @Test
    void testGetFirstPage3_S3ClientListObjectsV2ReturnsNoItems() throws Exception {
        // Setup
        // Configure S3Client.listObjectsV2(...).
        final ListObjectsV2Response listObjectsV2Response = ListObjectsV2Response.builder()
                .prefix("prefix")
                .isTruncated(false)
                .build();
        when(mockS3Client.listObjectsV2(ListObjectsV2Request.builder()
                .bucket("bucketName")
                .prefix("path")
                .build())).thenReturn(listObjectsV2Response);

        // Run the test
        final List<DataObject> result = myClassUnderTest.getFirstPage3("path");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetFirstPage3_S3ClientListObjectsV2ThrowsNoSuchBucketException() {
        // Setup
        when(mockS3Client.listObjectsV2(ListObjectsV2Request.builder()
                .bucket("bucketName")
                .prefix("path")
                .build())).thenThrow(NoSuchBucketException.class);

        // Run the test
        assertThrows(NoSuchBucketException.class, () -> myClassUnderTest.getFirstPage3("path"));
    }

    @Test
    void testGetFirstPage3_S3ClientListObjectsV2ThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.listObjectsV2(ListObjectsV2Request.builder()
                .bucket("bucketName")
                .prefix("path")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.getFirstPage3("path"));
    }

    @Test
    void testGetFirstPage3_S3ClientListObjectsV2ThrowsSdkClientException() {
        // Setup
        when(mockS3Client.listObjectsV2(ListObjectsV2Request.builder()
                .bucket("bucketName")
                .prefix("path")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.getFirstPage3("path"));
    }

    @Test
    void testGetFirstPage3_S3ClientListObjectsV2ThrowsS3Exception() {
        // Setup
        when(mockS3Client.listObjectsV2(ListObjectsV2Request.builder()
                .bucket("bucketName")
                .prefix("path")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.getFirstPage3("path"));
    }

    @Test
    void testGetFirstPage3_S3ClientGetObjectReturnsNoContent() throws Exception {
        // Setup
        final List<DataObject> expectedResult = List.of(new DataObject("id", "content"));

        // Configure S3Client.listObjectsV2(...).
        final ListObjectsV2Response listObjectsV2Response = ListObjectsV2Response.builder()
                .prefix("prefix")
                .contents(S3Object.builder()
                        .key("key")
                        .build())
                .isTruncated(false)
                .build();
        when(mockS3Client.listObjectsV2(ListObjectsV2Request.builder()
                .bucket("bucketName")
                .prefix("path")
                .build())).thenReturn(listObjectsV2Response);

        // Configure S3Client.getObject(...).
        final ResponseInputStream<GetObjectResponse> spyResponseInputStream = spy(
                new ResponseInputStream<>(GetObjectResponse.builder().build(),
                        AbortableInputStream.create(new ByteArrayInputStream(new byte[]{}))));
        when(mockS3Client.getObject(GetObjectRequest.builder()
                .bucket("bucketName")
                .key("id")
                .build())).thenReturn(spyResponseInputStream);

        // Run the test
        final List<DataObject> result = myClassUnderTest.getFirstPage3("path");

        // Verify the results
        assertEquals(expectedResult, result);
        verify(spyResponseInputStream).close();
    }

    @Test
    void testGetFirstPage3_S3ClientGetObjectReturnsBrokenIo() throws Exception {
        // Setup
        // Configure S3Client.listObjectsV2(...).
        final ListObjectsV2Response listObjectsV2Response = ListObjectsV2Response.builder()
                .prefix("prefix")
                .contents(S3Object.builder()
                        .key("key")
                        .build())
                .isTruncated(false)
                .build();
        when(mockS3Client.listObjectsV2(ListObjectsV2Request.builder()
                .bucket("bucketName")
                .prefix("path")
                .build())).thenReturn(listObjectsV2Response);

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
                .key("id")
                .build())).thenReturn(spyResponseInputStream);

        // Run the test
        assertThrows(ServiceException.class, () -> myClassUnderTest.getFirstPage3("path"));
        verify(spyResponseInputStream).close();
        verify(mockMetricsAdapter).recordIOException(eq("id"), any(IOException.class));
    }

    @Test
    void testGetFirstPage3_S3ClientGetObjectThrowsNoSuchKeyException() {
        // Setup
        // Configure S3Client.listObjectsV2(...).
        final ListObjectsV2Response listObjectsV2Response = ListObjectsV2Response.builder()
                .prefix("prefix")
                .contents(S3Object.builder()
                        .key("key")
                        .build())
                .isTruncated(false)
                .build();
        when(mockS3Client.listObjectsV2(ListObjectsV2Request.builder()
                .bucket("bucketName")
                .prefix("path")
                .build())).thenReturn(listObjectsV2Response);

        when(mockS3Client.getObject(GetObjectRequest.builder()
                .bucket("bucketName")
                .key("id")
                .build())).thenThrow(NoSuchKeyException.class);

        // Run the test
        assertThrows(ServiceException.class, () -> myClassUnderTest.getFirstPage3("path"));
        verify(mockMetricsAdapter).recordAmazonServiceException(eq("id"), any(S3Exception.class));
    }

    @Test
    void testGetFirstPage3_S3ClientGetObjectThrowsAwsServiceException() {
        // Setup
        // Configure S3Client.listObjectsV2(...).
        final ListObjectsV2Response listObjectsV2Response = ListObjectsV2Response.builder()
                .prefix("prefix")
                .contents(S3Object.builder()
                        .key("key")
                        .build())
                .isTruncated(false)
                .build();
        when(mockS3Client.listObjectsV2(ListObjectsV2Request.builder()
                .bucket("bucketName")
                .prefix("path")
                .build())).thenReturn(listObjectsV2Response);

        when(mockS3Client.getObject(GetObjectRequest.builder()
                .bucket("bucketName")
                .key("id")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.getFirstPage3("path"));
    }

    @Test
    void testGetFirstPage3_S3ClientGetObjectThrowsSdkClientException() {
        // Setup
        // Configure S3Client.listObjectsV2(...).
        final ListObjectsV2Response listObjectsV2Response = ListObjectsV2Response.builder()
                .prefix("prefix")
                .contents(S3Object.builder()
                        .key("key")
                        .build())
                .isTruncated(false)
                .build();
        when(mockS3Client.listObjectsV2(ListObjectsV2Request.builder()
                .bucket("bucketName")
                .prefix("path")
                .build())).thenReturn(listObjectsV2Response);

        when(mockS3Client.getObject(GetObjectRequest.builder()
                .bucket("bucketName")
                .key("id")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(ServiceException.class, () -> myClassUnderTest.getFirstPage3("path"));
        verify(mockMetricsAdapter).recordClientException(eq("id"), any(SdkClientException.class));
    }

    @Test
    void testGetFirstPage3_S3ClientGetObjectThrowsS3Exception() {
        // Setup
        // Configure S3Client.listObjectsV2(...).
        final ListObjectsV2Response listObjectsV2Response = ListObjectsV2Response.builder()
                .prefix("prefix")
                .contents(S3Object.builder()
                        .key("key")
                        .build())
                .isTruncated(false)
                .build();
        when(mockS3Client.listObjectsV2(ListObjectsV2Request.builder()
                .bucket("bucketName")
                .prefix("path")
                .build())).thenReturn(listObjectsV2Response);

        when(mockS3Client.getObject(GetObjectRequest.builder()
                .bucket("bucketName")
                .key("id")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(ServiceException.class, () -> myClassUnderTest.getFirstPage3("path"));
        verify(mockMetricsAdapter).recordAmazonServiceException(eq("id"), any(S3Exception.class));
    }

    @Test
    void testGetAllObjects4() throws Exception {
        // Setup
        final List<DataObject> expectedResult = List.of(new DataObject("id", "content"));

        // Configure S3Client.listObjectsV2Paginator(...).
        final ListObjectsV2Iterable mockListObjectsV2Iterable = mock(ListObjectsV2Iterable.class);
        when(mockS3Client.listObjectsV2Paginator(ListObjectsV2Request.builder()
                .bucket("bucketName")
                .prefix("path")
                .build())).thenReturn(mockListObjectsV2Iterable);

        // Configure S3Client.getObject(...).
        final ResponseInputStream<GetObjectResponse> spyResponseInputStream = spy(
                new ResponseInputStream<>(GetObjectResponse.builder().build(),
                        AbortableInputStream.create(new ByteArrayInputStream("objectContent".getBytes()))));
        when(mockS3Client.getObject(GetObjectRequest.builder()
                .bucket("bucketName")
                .key("id")
                .build())).thenReturn(spyResponseInputStream);

        // Run the test
        final List<DataObject> result = myClassUnderTest.getAllObjects4("path");

        // Verify the results
        assertEquals(expectedResult, result);
        verify(spyResponseInputStream).close();
    }

    @Test
    void testGetAllObjects4_S3ClientListObjectsV2PaginatorReturnsNoItems() throws Exception {
        // Setup
        // Configure S3Client.listObjectsV2Paginator(...).
        final ListObjectsV2Iterable mockListObjectsV2Iterable = mock(ListObjectsV2Iterable.class);
        when(mockS3Client.listObjectsV2Paginator(ListObjectsV2Request.builder()
                .bucket("bucketName")
                .prefix("path")
                .build())).thenReturn(mockListObjectsV2Iterable);

        // Run the test
        final List<DataObject> result = myClassUnderTest.getAllObjects4("path");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetAllObjects4_S3ClientListObjectsV2PaginatorThrowsNoSuchBucketException() {
        // Setup
        when(mockS3Client.listObjectsV2Paginator(ListObjectsV2Request.builder()
                .bucket("bucketName")
                .prefix("path")
                .build())).thenThrow(NoSuchBucketException.class);

        // Run the test
        assertThrows(NoSuchBucketException.class, () -> myClassUnderTest.getAllObjects4("path"));
    }

    @Test
    void testGetAllObjects4_S3ClientListObjectsV2PaginatorThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.listObjectsV2Paginator(ListObjectsV2Request.builder()
                .bucket("bucketName")
                .prefix("path")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.getAllObjects4("path"));
    }

    @Test
    void testGetAllObjects4_S3ClientListObjectsV2PaginatorThrowsSdkClientException() {
        // Setup
        when(mockS3Client.listObjectsV2Paginator(ListObjectsV2Request.builder()
                .bucket("bucketName")
                .prefix("path")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.getAllObjects4("path"));
    }

    @Test
    void testGetAllObjects4_S3ClientListObjectsV2PaginatorThrowsS3Exception() {
        // Setup
        when(mockS3Client.listObjectsV2Paginator(ListObjectsV2Request.builder()
                .bucket("bucketName")
                .prefix("path")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.getAllObjects4("path"));
    }

    @Test
    void testGetAllObjects4_S3ClientGetObjectReturnsNoContent() throws Exception {
        // Setup
        final List<DataObject> expectedResult = List.of(new DataObject("id", "content"));

        // Configure S3Client.listObjectsV2Paginator(...).
        final ListObjectsV2Iterable mockListObjectsV2Iterable = mock(ListObjectsV2Iterable.class);
        when(mockS3Client.listObjectsV2Paginator(ListObjectsV2Request.builder()
                .bucket("bucketName")
                .prefix("path")
                .build())).thenReturn(mockListObjectsV2Iterable);

        // Configure S3Client.getObject(...).
        final ResponseInputStream<GetObjectResponse> spyResponseInputStream = spy(
                new ResponseInputStream<>(GetObjectResponse.builder().build(),
                        AbortableInputStream.create(new ByteArrayInputStream(new byte[]{}))));
        when(mockS3Client.getObject(GetObjectRequest.builder()
                .bucket("bucketName")
                .key("id")
                .build())).thenReturn(spyResponseInputStream);

        // Run the test
        final List<DataObject> result = myClassUnderTest.getAllObjects4("path");

        // Verify the results
        assertEquals(expectedResult, result);
        verify(spyResponseInputStream).close();
    }

    @Test
    void testGetAllObjects4_S3ClientGetObjectReturnsBrokenIo() throws Exception {
        // Setup
        // Configure S3Client.listObjectsV2Paginator(...).
        final ListObjectsV2Iterable mockListObjectsV2Iterable = mock(ListObjectsV2Iterable.class);
        when(mockS3Client.listObjectsV2Paginator(ListObjectsV2Request.builder()
                .bucket("bucketName")
                .prefix("path")
                .build())).thenReturn(mockListObjectsV2Iterable);

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
                .key("id")
                .build())).thenReturn(spyResponseInputStream);

        // Run the test
        assertThrows(ServiceException.class, () -> myClassUnderTest.getAllObjects4("path"));
        verify(spyResponseInputStream).close();
        verify(mockMetricsAdapter).recordIOException(eq("id"), any(IOException.class));
    }

    @Test
    void testGetAllObjects4_S3ClientGetObjectThrowsNoSuchKeyException() {
        // Setup
        // Configure S3Client.listObjectsV2Paginator(...).
        final ListObjectsV2Iterable mockListObjectsV2Iterable = mock(ListObjectsV2Iterable.class);
        when(mockS3Client.listObjectsV2Paginator(ListObjectsV2Request.builder()
                .bucket("bucketName")
                .prefix("path")
                .build())).thenReturn(mockListObjectsV2Iterable);

        when(mockS3Client.getObject(GetObjectRequest.builder()
                .bucket("bucketName")
                .key("id")
                .build())).thenThrow(NoSuchKeyException.class);

        // Run the test
        assertThrows(ServiceException.class, () -> myClassUnderTest.getAllObjects4("path"));
        verify(mockMetricsAdapter).recordAmazonServiceException(eq("id"), any(S3Exception.class));
    }

    @Test
    void testGetAllObjects4_S3ClientGetObjectThrowsAwsServiceException() {
        // Setup
        // Configure S3Client.listObjectsV2Paginator(...).
        final ListObjectsV2Iterable mockListObjectsV2Iterable = mock(ListObjectsV2Iterable.class);
        when(mockS3Client.listObjectsV2Paginator(ListObjectsV2Request.builder()
                .bucket("bucketName")
                .prefix("path")
                .build())).thenReturn(mockListObjectsV2Iterable);

        when(mockS3Client.getObject(GetObjectRequest.builder()
                .bucket("bucketName")
                .key("id")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.getAllObjects4("path"));
    }

    @Test
    void testGetAllObjects4_S3ClientGetObjectThrowsSdkClientException() {
        // Setup
        // Configure S3Client.listObjectsV2Paginator(...).
        final ListObjectsV2Iterable mockListObjectsV2Iterable = mock(ListObjectsV2Iterable.class);
        when(mockS3Client.listObjectsV2Paginator(ListObjectsV2Request.builder()
                .bucket("bucketName")
                .prefix("path")
                .build())).thenReturn(mockListObjectsV2Iterable);

        when(mockS3Client.getObject(GetObjectRequest.builder()
                .bucket("bucketName")
                .key("id")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(ServiceException.class, () -> myClassUnderTest.getAllObjects4("path"));
        verify(mockMetricsAdapter).recordClientException(eq("id"), any(SdkClientException.class));
    }

    @Test
    void testGetAllObjects4_S3ClientGetObjectThrowsS3Exception() {
        // Setup
        // Configure S3Client.listObjectsV2Paginator(...).
        final ListObjectsV2Iterable mockListObjectsV2Iterable = mock(ListObjectsV2Iterable.class);
        when(mockS3Client.listObjectsV2Paginator(ListObjectsV2Request.builder()
                .bucket("bucketName")
                .prefix("path")
                .build())).thenReturn(mockListObjectsV2Iterable);

        when(mockS3Client.getObject(GetObjectRequest.builder()
                .bucket("bucketName")
                .key("id")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(ServiceException.class, () -> myClassUnderTest.getAllObjects4("path"));
        verify(mockMetricsAdapter).recordAmazonServiceException(eq("id"), any(S3Exception.class));
    }
}
