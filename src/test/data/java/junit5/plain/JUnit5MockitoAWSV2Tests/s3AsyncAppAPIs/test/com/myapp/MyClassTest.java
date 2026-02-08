package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.async.AsyncRequestBody;
import software.amazon.awssdk.core.async.AsyncResponseTransformer;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.S3Utilities;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.s3.paginators.ListMultipartUploadsPublisher;
import software.amazon.awssdk.services.s3.paginators.ListObjectVersionsPublisher;
import software.amazon.awssdk.services.s3.paginators.ListObjectsV2Publisher;
import software.amazon.awssdk.services.s3.paginators.ListPartsPublisher;

import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private S3AsyncClient mockS3AsyncClient;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockS3AsyncClient);
    }

    @Test
    void testTryAbortMultipartUpload() {
        // Setup
        // Configure S3AsyncClient.abortMultipartUpload(...).
        final CompletableFuture<AbortMultipartUploadResponse> abortMultipartUploadResponseCompletableFuture = CompletableFuture.completedFuture(
                AbortMultipartUploadResponse.builder().build());
        when(mockS3AsyncClient.abortMultipartUpload(AbortMultipartUploadRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build())).thenReturn(abortMultipartUploadResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryAbortMultipartUpload();

        // Verify the results
    }

    @Test
    void testTryAbortMultipartUpload_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.abortMultipartUpload(...).
        final CompletableFuture<AbortMultipartUploadResponse> abortMultipartUploadResponseCompletableFuture = new CompletableFuture<>();
        abortMultipartUploadResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.abortMultipartUpload(AbortMultipartUploadRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build())).thenReturn(abortMultipartUploadResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryAbortMultipartUpload();

        // Verify the results
    }

    @Test
    void testTryAbortMultipartUpload1() {
        // Setup
        // Configure S3AsyncClient.abortMultipartUpload(...).
        final CompletableFuture<AbortMultipartUploadResponse> abortMultipartUploadResponseCompletableFuture = CompletableFuture.completedFuture(
                AbortMultipartUploadResponse.builder().build());
        when(mockS3AsyncClient.abortMultipartUpload(any(Consumer.class)))
                .thenReturn(abortMultipartUploadResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryAbortMultipartUpload1();

        // Verify the results
    }

    @Test
    void testTryAbortMultipartUpload1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.abortMultipartUpload(...).
        final CompletableFuture<AbortMultipartUploadResponse> abortMultipartUploadResponseCompletableFuture = new CompletableFuture<>();
        abortMultipartUploadResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.abortMultipartUpload(any(Consumer.class)))
                .thenReturn(abortMultipartUploadResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryAbortMultipartUpload1();

        // Verify the results
    }

    @Test
    void testTryCompleteMultipartUpload() {
        // Setup
        // Configure S3AsyncClient.completeMultipartUpload(...).
        final CompletableFuture<CompleteMultipartUploadResponse> completeMultipartUploadResponseCompletableFuture = CompletableFuture.completedFuture(
                CompleteMultipartUploadResponse.builder().build());
        when(mockS3AsyncClient.completeMultipartUpload(CompleteMultipartUploadRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build())).thenReturn(completeMultipartUploadResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryCompleteMultipartUpload();

        // Verify the results
    }

    @Test
    void testTryCompleteMultipartUpload_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.completeMultipartUpload(...).
        final CompletableFuture<CompleteMultipartUploadResponse> completeMultipartUploadResponseCompletableFuture = new CompletableFuture<>();
        completeMultipartUploadResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.completeMultipartUpload(CompleteMultipartUploadRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build())).thenReturn(completeMultipartUploadResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryCompleteMultipartUpload();

        // Verify the results
    }

    @Test
    void testTryCompleteMultipartUpload1() {
        // Setup
        // Configure S3AsyncClient.completeMultipartUpload(...).
        final CompletableFuture<CompleteMultipartUploadResponse> completeMultipartUploadResponseCompletableFuture = CompletableFuture.completedFuture(
                CompleteMultipartUploadResponse.builder().build());
        when(mockS3AsyncClient.completeMultipartUpload(any(Consumer.class)))
                .thenReturn(completeMultipartUploadResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryCompleteMultipartUpload1();

        // Verify the results
    }

    @Test
    void testTryCompleteMultipartUpload1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.completeMultipartUpload(...).
        final CompletableFuture<CompleteMultipartUploadResponse> completeMultipartUploadResponseCompletableFuture = new CompletableFuture<>();
        completeMultipartUploadResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.completeMultipartUpload(any(Consumer.class)))
                .thenReturn(completeMultipartUploadResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryCompleteMultipartUpload1();

        // Verify the results
    }

    @Test
    void testTryCopyObject() {
        // Setup
        // Configure S3AsyncClient.copyObject(...).
        final CompletableFuture<CopyObjectResponse> copyObjectResponseCompletableFuture = CompletableFuture.completedFuture(
                CopyObjectResponse.builder().build());
        when(mockS3AsyncClient.copyObject(CopyObjectRequest.builder().build()))
                .thenReturn(copyObjectResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryCopyObject();

        // Verify the results
    }

    @Test
    void testTryCopyObject_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.copyObject(...).
        final CompletableFuture<CopyObjectResponse> copyObjectResponseCompletableFuture = new CompletableFuture<>();
        copyObjectResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.copyObject(CopyObjectRequest.builder().build()))
                .thenReturn(copyObjectResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryCopyObject();

        // Verify the results
    }

    @Test
    void testTryCopyObject1() {
        // Setup
        // Configure S3AsyncClient.copyObject(...).
        final CompletableFuture<CopyObjectResponse> copyObjectResponseCompletableFuture = CompletableFuture.completedFuture(
                CopyObjectResponse.builder().build());
        when(mockS3AsyncClient.copyObject(any(Consumer.class))).thenReturn(copyObjectResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryCopyObject1();

        // Verify the results
    }

    @Test
    void testTryCopyObject1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.copyObject(...).
        final CompletableFuture<CopyObjectResponse> copyObjectResponseCompletableFuture = new CompletableFuture<>();
        copyObjectResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.copyObject(any(Consumer.class))).thenReturn(copyObjectResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryCopyObject1();

        // Verify the results
    }

    @Test
    void testTryCreateBucket() {
        // Setup
        // Configure S3AsyncClient.createBucket(...).
        final CompletableFuture<CreateBucketResponse> createBucketResponseCompletableFuture = CompletableFuture.completedFuture(
                CreateBucketResponse.builder().build());
        when(mockS3AsyncClient.createBucket(CreateBucketRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(createBucketResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryCreateBucket();

        // Verify the results
    }

    @Test
    void testTryCreateBucket_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.createBucket(...).
        final CompletableFuture<CreateBucketResponse> createBucketResponseCompletableFuture = new CompletableFuture<>();
        createBucketResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.createBucket(CreateBucketRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(createBucketResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryCreateBucket();

        // Verify the results
    }

    @Test
    void testTryCreateBucket1() {
        // Setup
        // Configure S3AsyncClient.createBucket(...).
        final CompletableFuture<CreateBucketResponse> createBucketResponseCompletableFuture = CompletableFuture.completedFuture(
                CreateBucketResponse.builder().build());
        when(mockS3AsyncClient.createBucket(any(Consumer.class))).thenReturn(createBucketResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryCreateBucket1();

        // Verify the results
    }

    @Test
    void testTryCreateBucket1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.createBucket(...).
        final CompletableFuture<CreateBucketResponse> createBucketResponseCompletableFuture = new CompletableFuture<>();
        createBucketResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.createBucket(any(Consumer.class))).thenReturn(createBucketResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryCreateBucket1();

        // Verify the results
    }

    @Test
    void testTryCreateMultipartUpload() {
        // Setup
        // Configure S3AsyncClient.createMultipartUpload(...).
        final CompletableFuture<CreateMultipartUploadResponse> createMultipartUploadResponseCompletableFuture = CompletableFuture.completedFuture(
                CreateMultipartUploadResponse.builder().build());
        when(mockS3AsyncClient.createMultipartUpload(CreateMultipartUploadRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build())).thenReturn(createMultipartUploadResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryCreateMultipartUpload();

        // Verify the results
    }

    @Test
    void testTryCreateMultipartUpload_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.createMultipartUpload(...).
        final CompletableFuture<CreateMultipartUploadResponse> createMultipartUploadResponseCompletableFuture = new CompletableFuture<>();
        createMultipartUploadResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.createMultipartUpload(CreateMultipartUploadRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build())).thenReturn(createMultipartUploadResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryCreateMultipartUpload();

        // Verify the results
    }

    @Test
    void testTryCreateMultipartUpload1() {
        // Setup
        // Configure S3AsyncClient.createMultipartUpload(...).
        final CompletableFuture<CreateMultipartUploadResponse> createMultipartUploadResponseCompletableFuture = CompletableFuture.completedFuture(
                CreateMultipartUploadResponse.builder().build());
        when(mockS3AsyncClient.createMultipartUpload(any(Consumer.class)))
                .thenReturn(createMultipartUploadResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryCreateMultipartUpload1();

        // Verify the results
    }

    @Test
    void testTryCreateMultipartUpload1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.createMultipartUpload(...).
        final CompletableFuture<CreateMultipartUploadResponse> createMultipartUploadResponseCompletableFuture = new CompletableFuture<>();
        createMultipartUploadResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.createMultipartUpload(any(Consumer.class)))
                .thenReturn(createMultipartUploadResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryCreateMultipartUpload1();

        // Verify the results
    }

    @Test
    void testTryDeleteBucket() {
        // Setup
        // Configure S3AsyncClient.deleteBucket(...).
        final CompletableFuture<DeleteBucketResponse> deleteBucketResponseCompletableFuture = CompletableFuture.completedFuture(
                DeleteBucketResponse.builder().build());
        when(mockS3AsyncClient.deleteBucket(DeleteBucketRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(deleteBucketResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryDeleteBucket();

        // Verify the results
    }

    @Test
    void testTryDeleteBucket_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.deleteBucket(...).
        final CompletableFuture<DeleteBucketResponse> deleteBucketResponseCompletableFuture = new CompletableFuture<>();
        deleteBucketResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.deleteBucket(DeleteBucketRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(deleteBucketResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryDeleteBucket();

        // Verify the results
    }

    @Test
    void testTryDeleteBucket1() {
        // Setup
        // Configure S3AsyncClient.deleteBucket(...).
        final CompletableFuture<DeleteBucketResponse> deleteBucketResponseCompletableFuture = CompletableFuture.completedFuture(
                DeleteBucketResponse.builder().build());
        when(mockS3AsyncClient.deleteBucket(any(Consumer.class))).thenReturn(deleteBucketResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryDeleteBucket1();

        // Verify the results
    }

    @Test
    void testTryDeleteBucket1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.deleteBucket(...).
        final CompletableFuture<DeleteBucketResponse> deleteBucketResponseCompletableFuture = new CompletableFuture<>();
        deleteBucketResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.deleteBucket(any(Consumer.class))).thenReturn(deleteBucketResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryDeleteBucket1();

        // Verify the results
    }

    @Test
    void testTryDeleteBucketAnalyticsConfiguration() {
        // Setup
        // Configure S3AsyncClient.deleteBucketAnalyticsConfiguration(...).
        final CompletableFuture<DeleteBucketAnalyticsConfigurationResponse> deleteBucketAnalyticsConfigurationResponseCompletableFuture = CompletableFuture.completedFuture(
                DeleteBucketAnalyticsConfigurationResponse.builder().build());
        when(mockS3AsyncClient.deleteBucketAnalyticsConfiguration(DeleteBucketAnalyticsConfigurationRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(deleteBucketAnalyticsConfigurationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryDeleteBucketAnalyticsConfiguration();

        // Verify the results
    }

    @Test
    void testTryDeleteBucketAnalyticsConfiguration_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.deleteBucketAnalyticsConfiguration(...).
        final CompletableFuture<DeleteBucketAnalyticsConfigurationResponse> deleteBucketAnalyticsConfigurationResponseCompletableFuture = new CompletableFuture<>();
        deleteBucketAnalyticsConfigurationResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.deleteBucketAnalyticsConfiguration(DeleteBucketAnalyticsConfigurationRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(deleteBucketAnalyticsConfigurationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryDeleteBucketAnalyticsConfiguration();

        // Verify the results
    }

    @Test
    void testTryDeleteBucketAnalyticsConfiguration1() {
        // Setup
        // Configure S3AsyncClient.deleteBucketAnalyticsConfiguration(...).
        final CompletableFuture<DeleteBucketAnalyticsConfigurationResponse> deleteBucketAnalyticsConfigurationResponseCompletableFuture = CompletableFuture.completedFuture(
                DeleteBucketAnalyticsConfigurationResponse.builder().build());
        when(mockS3AsyncClient.deleteBucketAnalyticsConfiguration(any(Consumer.class)))
                .thenReturn(deleteBucketAnalyticsConfigurationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryDeleteBucketAnalyticsConfiguration1();

        // Verify the results
    }

    @Test
    void testTryDeleteBucketAnalyticsConfiguration1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.deleteBucketAnalyticsConfiguration(...).
        final CompletableFuture<DeleteBucketAnalyticsConfigurationResponse> deleteBucketAnalyticsConfigurationResponseCompletableFuture = new CompletableFuture<>();
        deleteBucketAnalyticsConfigurationResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.deleteBucketAnalyticsConfiguration(any(Consumer.class)))
                .thenReturn(deleteBucketAnalyticsConfigurationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryDeleteBucketAnalyticsConfiguration1();

        // Verify the results
    }

    @Test
    void testTryDeleteBucketCors() {
        // Setup
        // Configure S3AsyncClient.deleteBucketCors(...).
        final CompletableFuture<DeleteBucketCorsResponse> deleteBucketCorsResponseCompletableFuture = CompletableFuture.completedFuture(
                DeleteBucketCorsResponse.builder().build());
        when(mockS3AsyncClient.deleteBucketCors(DeleteBucketCorsRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(deleteBucketCorsResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryDeleteBucketCors();

        // Verify the results
    }

    @Test
    void testTryDeleteBucketCors_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.deleteBucketCors(...).
        final CompletableFuture<DeleteBucketCorsResponse> deleteBucketCorsResponseCompletableFuture = new CompletableFuture<>();
        deleteBucketCorsResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.deleteBucketCors(DeleteBucketCorsRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(deleteBucketCorsResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryDeleteBucketCors();

        // Verify the results
    }

    @Test
    void testTryDeleteBucketCors1() {
        // Setup
        // Configure S3AsyncClient.deleteBucketCors(...).
        final CompletableFuture<DeleteBucketCorsResponse> deleteBucketCorsResponseCompletableFuture = CompletableFuture.completedFuture(
                DeleteBucketCorsResponse.builder().build());
        when(mockS3AsyncClient.deleteBucketCors(any(Consumer.class)))
                .thenReturn(deleteBucketCorsResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryDeleteBucketCors1();

        // Verify the results
    }

    @Test
    void testTryDeleteBucketCors1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.deleteBucketCors(...).
        final CompletableFuture<DeleteBucketCorsResponse> deleteBucketCorsResponseCompletableFuture = new CompletableFuture<>();
        deleteBucketCorsResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.deleteBucketCors(any(Consumer.class)))
                .thenReturn(deleteBucketCorsResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryDeleteBucketCors1();

        // Verify the results
    }

    @Test
    void testTryDeleteBucketEncryption() {
        // Setup
        // Configure S3AsyncClient.deleteBucketEncryption(...).
        final CompletableFuture<DeleteBucketEncryptionResponse> deleteBucketEncryptionResponseCompletableFuture = CompletableFuture.completedFuture(
                DeleteBucketEncryptionResponse.builder().build());
        when(mockS3AsyncClient.deleteBucketEncryption(DeleteBucketEncryptionRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(deleteBucketEncryptionResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryDeleteBucketEncryption();

        // Verify the results
    }

    @Test
    void testTryDeleteBucketEncryption_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.deleteBucketEncryption(...).
        final CompletableFuture<DeleteBucketEncryptionResponse> deleteBucketEncryptionResponseCompletableFuture = new CompletableFuture<>();
        deleteBucketEncryptionResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.deleteBucketEncryption(DeleteBucketEncryptionRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(deleteBucketEncryptionResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryDeleteBucketEncryption();

        // Verify the results
    }

    @Test
    void testTryDeleteBucketEncryption1() {
        // Setup
        // Configure S3AsyncClient.deleteBucketEncryption(...).
        final CompletableFuture<DeleteBucketEncryptionResponse> deleteBucketEncryptionResponseCompletableFuture = CompletableFuture.completedFuture(
                DeleteBucketEncryptionResponse.builder().build());
        when(mockS3AsyncClient.deleteBucketEncryption(any(Consumer.class)))
                .thenReturn(deleteBucketEncryptionResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryDeleteBucketEncryption1();

        // Verify the results
    }

    @Test
    void testTryDeleteBucketEncryption1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.deleteBucketEncryption(...).
        final CompletableFuture<DeleteBucketEncryptionResponse> deleteBucketEncryptionResponseCompletableFuture = new CompletableFuture<>();
        deleteBucketEncryptionResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.deleteBucketEncryption(any(Consumer.class)))
                .thenReturn(deleteBucketEncryptionResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryDeleteBucketEncryption1();

        // Verify the results
    }

    @Test
    void testTryDeleteBucketInventoryConfiguration() {
        // Setup
        // Configure S3AsyncClient.deleteBucketInventoryConfiguration(...).
        final CompletableFuture<DeleteBucketInventoryConfigurationResponse> deleteBucketInventoryConfigurationResponseCompletableFuture = CompletableFuture.completedFuture(
                DeleteBucketInventoryConfigurationResponse.builder().build());
        when(mockS3AsyncClient.deleteBucketInventoryConfiguration(DeleteBucketInventoryConfigurationRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(deleteBucketInventoryConfigurationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryDeleteBucketInventoryConfiguration();

        // Verify the results
    }

    @Test
    void testTryDeleteBucketInventoryConfiguration_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.deleteBucketInventoryConfiguration(...).
        final CompletableFuture<DeleteBucketInventoryConfigurationResponse> deleteBucketInventoryConfigurationResponseCompletableFuture = new CompletableFuture<>();
        deleteBucketInventoryConfigurationResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.deleteBucketInventoryConfiguration(DeleteBucketInventoryConfigurationRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(deleteBucketInventoryConfigurationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryDeleteBucketInventoryConfiguration();

        // Verify the results
    }

    @Test
    void testTryDeleteBucketInventoryConfiguration1() {
        // Setup
        // Configure S3AsyncClient.deleteBucketInventoryConfiguration(...).
        final CompletableFuture<DeleteBucketInventoryConfigurationResponse> deleteBucketInventoryConfigurationResponseCompletableFuture = CompletableFuture.completedFuture(
                DeleteBucketInventoryConfigurationResponse.builder().build());
        when(mockS3AsyncClient.deleteBucketInventoryConfiguration(any(Consumer.class)))
                .thenReturn(deleteBucketInventoryConfigurationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryDeleteBucketInventoryConfiguration1();

        // Verify the results
    }

    @Test
    void testTryDeleteBucketInventoryConfiguration1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.deleteBucketInventoryConfiguration(...).
        final CompletableFuture<DeleteBucketInventoryConfigurationResponse> deleteBucketInventoryConfigurationResponseCompletableFuture = new CompletableFuture<>();
        deleteBucketInventoryConfigurationResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.deleteBucketInventoryConfiguration(any(Consumer.class)))
                .thenReturn(deleteBucketInventoryConfigurationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryDeleteBucketInventoryConfiguration1();

        // Verify the results
    }

    @Test
    void testTryDeleteBucketLifecycle() {
        // Setup
        // Configure S3AsyncClient.deleteBucketLifecycle(...).
        final CompletableFuture<DeleteBucketLifecycleResponse> deleteBucketLifecycleResponseCompletableFuture = CompletableFuture.completedFuture(
                DeleteBucketLifecycleResponse.builder().build());
        when(mockS3AsyncClient.deleteBucketLifecycle(DeleteBucketLifecycleRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(deleteBucketLifecycleResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryDeleteBucketLifecycle();

        // Verify the results
    }

    @Test
    void testTryDeleteBucketLifecycle_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.deleteBucketLifecycle(...).
        final CompletableFuture<DeleteBucketLifecycleResponse> deleteBucketLifecycleResponseCompletableFuture = new CompletableFuture<>();
        deleteBucketLifecycleResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.deleteBucketLifecycle(DeleteBucketLifecycleRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(deleteBucketLifecycleResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryDeleteBucketLifecycle();

        // Verify the results
    }

    @Test
    void testTryDeleteBucketLifecycle1() {
        // Setup
        // Configure S3AsyncClient.deleteBucketLifecycle(...).
        final CompletableFuture<DeleteBucketLifecycleResponse> deleteBucketLifecycleResponseCompletableFuture = CompletableFuture.completedFuture(
                DeleteBucketLifecycleResponse.builder().build());
        when(mockS3AsyncClient.deleteBucketLifecycle(any(Consumer.class)))
                .thenReturn(deleteBucketLifecycleResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryDeleteBucketLifecycle1();

        // Verify the results
    }

    @Test
    void testTryDeleteBucketLifecycle1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.deleteBucketLifecycle(...).
        final CompletableFuture<DeleteBucketLifecycleResponse> deleteBucketLifecycleResponseCompletableFuture = new CompletableFuture<>();
        deleteBucketLifecycleResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.deleteBucketLifecycle(any(Consumer.class)))
                .thenReturn(deleteBucketLifecycleResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryDeleteBucketLifecycle1();

        // Verify the results
    }

    @Test
    void testTryDeleteBucketMetricsConfiguration() {
        // Setup
        // Configure S3AsyncClient.deleteBucketMetricsConfiguration(...).
        final CompletableFuture<DeleteBucketMetricsConfigurationResponse> deleteBucketMetricsConfigurationResponseCompletableFuture = CompletableFuture.completedFuture(
                DeleteBucketMetricsConfigurationResponse.builder().build());
        when(mockS3AsyncClient.deleteBucketMetricsConfiguration(DeleteBucketMetricsConfigurationRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(deleteBucketMetricsConfigurationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryDeleteBucketMetricsConfiguration();

        // Verify the results
    }

    @Test
    void testTryDeleteBucketMetricsConfiguration_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.deleteBucketMetricsConfiguration(...).
        final CompletableFuture<DeleteBucketMetricsConfigurationResponse> deleteBucketMetricsConfigurationResponseCompletableFuture = new CompletableFuture<>();
        deleteBucketMetricsConfigurationResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.deleteBucketMetricsConfiguration(DeleteBucketMetricsConfigurationRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(deleteBucketMetricsConfigurationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryDeleteBucketMetricsConfiguration();

        // Verify the results
    }

    @Test
    void testTryDeleteBucketMetricsConfiguration1() {
        // Setup
        // Configure S3AsyncClient.deleteBucketMetricsConfiguration(...).
        final CompletableFuture<DeleteBucketMetricsConfigurationResponse> deleteBucketMetricsConfigurationResponseCompletableFuture = CompletableFuture.completedFuture(
                DeleteBucketMetricsConfigurationResponse.builder().build());
        when(mockS3AsyncClient.deleteBucketMetricsConfiguration(any(Consumer.class)))
                .thenReturn(deleteBucketMetricsConfigurationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryDeleteBucketMetricsConfiguration1();

        // Verify the results
    }

    @Test
    void testTryDeleteBucketMetricsConfiguration1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.deleteBucketMetricsConfiguration(...).
        final CompletableFuture<DeleteBucketMetricsConfigurationResponse> deleteBucketMetricsConfigurationResponseCompletableFuture = new CompletableFuture<>();
        deleteBucketMetricsConfigurationResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.deleteBucketMetricsConfiguration(any(Consumer.class)))
                .thenReturn(deleteBucketMetricsConfigurationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryDeleteBucketMetricsConfiguration1();

        // Verify the results
    }

    @Test
    void testTryDeleteBucketPolicy() {
        // Setup
        // Configure S3AsyncClient.deleteBucketPolicy(...).
        final CompletableFuture<DeleteBucketPolicyResponse> deleteBucketPolicyResponseCompletableFuture = CompletableFuture.completedFuture(
                DeleteBucketPolicyResponse.builder().build());
        when(mockS3AsyncClient.deleteBucketPolicy(DeleteBucketPolicyRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(deleteBucketPolicyResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryDeleteBucketPolicy();

        // Verify the results
    }

    @Test
    void testTryDeleteBucketPolicy_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.deleteBucketPolicy(...).
        final CompletableFuture<DeleteBucketPolicyResponse> deleteBucketPolicyResponseCompletableFuture = new CompletableFuture<>();
        deleteBucketPolicyResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.deleteBucketPolicy(DeleteBucketPolicyRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(deleteBucketPolicyResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryDeleteBucketPolicy();

        // Verify the results
    }

    @Test
    void testTryDeleteBucketPolicy1() {
        // Setup
        // Configure S3AsyncClient.deleteBucketPolicy(...).
        final CompletableFuture<DeleteBucketPolicyResponse> deleteBucketPolicyResponseCompletableFuture = CompletableFuture.completedFuture(
                DeleteBucketPolicyResponse.builder().build());
        when(mockS3AsyncClient.deleteBucketPolicy(any(Consumer.class)))
                .thenReturn(deleteBucketPolicyResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryDeleteBucketPolicy1();

        // Verify the results
    }

    @Test
    void testTryDeleteBucketPolicy1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.deleteBucketPolicy(...).
        final CompletableFuture<DeleteBucketPolicyResponse> deleteBucketPolicyResponseCompletableFuture = new CompletableFuture<>();
        deleteBucketPolicyResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.deleteBucketPolicy(any(Consumer.class)))
                .thenReturn(deleteBucketPolicyResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryDeleteBucketPolicy1();

        // Verify the results
    }

    @Test
    void testTryDeleteBucketReplication() {
        // Setup
        // Configure S3AsyncClient.deleteBucketReplication(...).
        final CompletableFuture<DeleteBucketReplicationResponse> deleteBucketReplicationResponseCompletableFuture = CompletableFuture.completedFuture(
                DeleteBucketReplicationResponse.builder().build());
        when(mockS3AsyncClient.deleteBucketReplication(DeleteBucketReplicationRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(deleteBucketReplicationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryDeleteBucketReplication();

        // Verify the results
    }

    @Test
    void testTryDeleteBucketReplication_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.deleteBucketReplication(...).
        final CompletableFuture<DeleteBucketReplicationResponse> deleteBucketReplicationResponseCompletableFuture = new CompletableFuture<>();
        deleteBucketReplicationResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.deleteBucketReplication(DeleteBucketReplicationRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(deleteBucketReplicationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryDeleteBucketReplication();

        // Verify the results
    }

    @Test
    void testTryDeleteBucketReplication1() {
        // Setup
        // Configure S3AsyncClient.deleteBucketReplication(...).
        final CompletableFuture<DeleteBucketReplicationResponse> deleteBucketReplicationResponseCompletableFuture = CompletableFuture.completedFuture(
                DeleteBucketReplicationResponse.builder().build());
        when(mockS3AsyncClient.deleteBucketReplication(any(Consumer.class)))
                .thenReturn(deleteBucketReplicationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryDeleteBucketReplication1();

        // Verify the results
    }

    @Test
    void testTryDeleteBucketReplication1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.deleteBucketReplication(...).
        final CompletableFuture<DeleteBucketReplicationResponse> deleteBucketReplicationResponseCompletableFuture = new CompletableFuture<>();
        deleteBucketReplicationResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.deleteBucketReplication(any(Consumer.class)))
                .thenReturn(deleteBucketReplicationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryDeleteBucketReplication1();

        // Verify the results
    }

    @Test
    void testTryDeleteBucketTagging() {
        // Setup
        // Configure S3AsyncClient.deleteBucketTagging(...).
        final CompletableFuture<DeleteBucketTaggingResponse> deleteBucketTaggingResponseCompletableFuture = CompletableFuture.completedFuture(
                DeleteBucketTaggingResponse.builder().build());
        when(mockS3AsyncClient.deleteBucketTagging(DeleteBucketTaggingRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(deleteBucketTaggingResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryDeleteBucketTagging();

        // Verify the results
    }

    @Test
    void testTryDeleteBucketTagging_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.deleteBucketTagging(...).
        final CompletableFuture<DeleteBucketTaggingResponse> deleteBucketTaggingResponseCompletableFuture = new CompletableFuture<>();
        deleteBucketTaggingResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.deleteBucketTagging(DeleteBucketTaggingRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(deleteBucketTaggingResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryDeleteBucketTagging();

        // Verify the results
    }

    @Test
    void testTryDeleteBucketTagging1() {
        // Setup
        // Configure S3AsyncClient.deleteBucketTagging(...).
        final CompletableFuture<DeleteBucketTaggingResponse> deleteBucketTaggingResponseCompletableFuture = CompletableFuture.completedFuture(
                DeleteBucketTaggingResponse.builder().build());
        when(mockS3AsyncClient.deleteBucketTagging(any(Consumer.class)))
                .thenReturn(deleteBucketTaggingResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryDeleteBucketTagging1();

        // Verify the results
    }

    @Test
    void testTryDeleteBucketTagging1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.deleteBucketTagging(...).
        final CompletableFuture<DeleteBucketTaggingResponse> deleteBucketTaggingResponseCompletableFuture = new CompletableFuture<>();
        deleteBucketTaggingResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.deleteBucketTagging(any(Consumer.class)))
                .thenReturn(deleteBucketTaggingResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryDeleteBucketTagging1();

        // Verify the results
    }

    @Test
    void testTryDeleteBucketWebsite() {
        // Setup
        // Configure S3AsyncClient.deleteBucketWebsite(...).
        final CompletableFuture<DeleteBucketWebsiteResponse> deleteBucketWebsiteResponseCompletableFuture = CompletableFuture.completedFuture(
                DeleteBucketWebsiteResponse.builder().build());
        when(mockS3AsyncClient.deleteBucketWebsite(DeleteBucketWebsiteRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(deleteBucketWebsiteResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryDeleteBucketWebsite();

        // Verify the results
    }

    @Test
    void testTryDeleteBucketWebsite_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.deleteBucketWebsite(...).
        final CompletableFuture<DeleteBucketWebsiteResponse> deleteBucketWebsiteResponseCompletableFuture = new CompletableFuture<>();
        deleteBucketWebsiteResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.deleteBucketWebsite(DeleteBucketWebsiteRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(deleteBucketWebsiteResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryDeleteBucketWebsite();

        // Verify the results
    }

    @Test
    void testTryDeleteBucketWebsite1() {
        // Setup
        // Configure S3AsyncClient.deleteBucketWebsite(...).
        final CompletableFuture<DeleteBucketWebsiteResponse> deleteBucketWebsiteResponseCompletableFuture = CompletableFuture.completedFuture(
                DeleteBucketWebsiteResponse.builder().build());
        when(mockS3AsyncClient.deleteBucketWebsite(any(Consumer.class)))
                .thenReturn(deleteBucketWebsiteResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryDeleteBucketWebsite1();

        // Verify the results
    }

    @Test
    void testTryDeleteBucketWebsite1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.deleteBucketWebsite(...).
        final CompletableFuture<DeleteBucketWebsiteResponse> deleteBucketWebsiteResponseCompletableFuture = new CompletableFuture<>();
        deleteBucketWebsiteResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.deleteBucketWebsite(any(Consumer.class)))
                .thenReturn(deleteBucketWebsiteResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryDeleteBucketWebsite1();

        // Verify the results
    }

    @Test
    void testTryDeleteObject() {
        // Setup
        // Configure S3AsyncClient.deleteObject(...).
        final CompletableFuture<DeleteObjectResponse> deleteObjectResponseCompletableFuture = CompletableFuture.completedFuture(
                DeleteObjectResponse.builder().build());
        when(mockS3AsyncClient.deleteObject(DeleteObjectRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build())).thenReturn(deleteObjectResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryDeleteObject();

        // Verify the results
    }

    @Test
    void testTryDeleteObject_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.deleteObject(...).
        final CompletableFuture<DeleteObjectResponse> deleteObjectResponseCompletableFuture = new CompletableFuture<>();
        deleteObjectResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.deleteObject(DeleteObjectRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build())).thenReturn(deleteObjectResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryDeleteObject();

        // Verify the results
    }

    @Test
    void testTryDeleteObject1() {
        // Setup
        // Configure S3AsyncClient.deleteObject(...).
        final CompletableFuture<DeleteObjectResponse> deleteObjectResponseCompletableFuture = CompletableFuture.completedFuture(
                DeleteObjectResponse.builder().build());
        when(mockS3AsyncClient.deleteObject(any(Consumer.class))).thenReturn(deleteObjectResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryDeleteObject1();

        // Verify the results
    }

    @Test
    void testTryDeleteObject1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.deleteObject(...).
        final CompletableFuture<DeleteObjectResponse> deleteObjectResponseCompletableFuture = new CompletableFuture<>();
        deleteObjectResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.deleteObject(any(Consumer.class))).thenReturn(deleteObjectResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryDeleteObject1();

        // Verify the results
    }

    @Test
    void testTryDeleteObjectTagging() {
        // Setup
        // Configure S3AsyncClient.deleteObjectTagging(...).
        final CompletableFuture<DeleteObjectTaggingResponse> deleteObjectTaggingResponseCompletableFuture = CompletableFuture.completedFuture(
                DeleteObjectTaggingResponse.builder().build());
        when(mockS3AsyncClient.deleteObjectTagging(DeleteObjectTaggingRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build())).thenReturn(deleteObjectTaggingResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryDeleteObjectTagging();

        // Verify the results
    }

    @Test
    void testTryDeleteObjectTagging_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.deleteObjectTagging(...).
        final CompletableFuture<DeleteObjectTaggingResponse> deleteObjectTaggingResponseCompletableFuture = new CompletableFuture<>();
        deleteObjectTaggingResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.deleteObjectTagging(DeleteObjectTaggingRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build())).thenReturn(deleteObjectTaggingResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryDeleteObjectTagging();

        // Verify the results
    }

    @Test
    void testTryDeleteObjectTagging1() {
        // Setup
        // Configure S3AsyncClient.deleteObjectTagging(...).
        final CompletableFuture<DeleteObjectTaggingResponse> deleteObjectTaggingResponseCompletableFuture = CompletableFuture.completedFuture(
                DeleteObjectTaggingResponse.builder().build());
        when(mockS3AsyncClient.deleteObjectTagging(any(Consumer.class)))
                .thenReturn(deleteObjectTaggingResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryDeleteObjectTagging1();

        // Verify the results
    }

    @Test
    void testTryDeleteObjectTagging1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.deleteObjectTagging(...).
        final CompletableFuture<DeleteObjectTaggingResponse> deleteObjectTaggingResponseCompletableFuture = new CompletableFuture<>();
        deleteObjectTaggingResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.deleteObjectTagging(any(Consumer.class)))
                .thenReturn(deleteObjectTaggingResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryDeleteObjectTagging1();

        // Verify the results
    }

    @Test
    void testTryDeleteObjects() {
        // Setup
        // Configure S3AsyncClient.deleteObjects(...).
        final CompletableFuture<DeleteObjectsResponse> deleteObjectsResponseCompletableFuture = CompletableFuture.completedFuture(
                DeleteObjectsResponse.builder().build());
        when(mockS3AsyncClient.deleteObjects(DeleteObjectsRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(deleteObjectsResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryDeleteObjects();

        // Verify the results
    }

    @Test
    void testTryDeleteObjects_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.deleteObjects(...).
        final CompletableFuture<DeleteObjectsResponse> deleteObjectsResponseCompletableFuture = new CompletableFuture<>();
        deleteObjectsResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.deleteObjects(DeleteObjectsRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(deleteObjectsResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryDeleteObjects();

        // Verify the results
    }

    @Test
    void testTryDeleteObjects1() {
        // Setup
        // Configure S3AsyncClient.deleteObjects(...).
        final CompletableFuture<DeleteObjectsResponse> deleteObjectsResponseCompletableFuture = CompletableFuture.completedFuture(
                DeleteObjectsResponse.builder().build());
        when(mockS3AsyncClient.deleteObjects(any(Consumer.class))).thenReturn(deleteObjectsResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryDeleteObjects1();

        // Verify the results
    }

    @Test
    void testTryDeleteObjects1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.deleteObjects(...).
        final CompletableFuture<DeleteObjectsResponse> deleteObjectsResponseCompletableFuture = new CompletableFuture<>();
        deleteObjectsResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.deleteObjects(any(Consumer.class))).thenReturn(deleteObjectsResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryDeleteObjects1();

        // Verify the results
    }

    @Test
    void testTryDeletePublicAccessBlock() {
        // Setup
        // Configure S3AsyncClient.deletePublicAccessBlock(...).
        final CompletableFuture<DeletePublicAccessBlockResponse> deletePublicAccessBlockResponseCompletableFuture = CompletableFuture.completedFuture(
                DeletePublicAccessBlockResponse.builder().build());
        when(mockS3AsyncClient.deletePublicAccessBlock(DeletePublicAccessBlockRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(deletePublicAccessBlockResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryDeletePublicAccessBlock();

        // Verify the results
    }

    @Test
    void testTryDeletePublicAccessBlock_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.deletePublicAccessBlock(...).
        final CompletableFuture<DeletePublicAccessBlockResponse> deletePublicAccessBlockResponseCompletableFuture = new CompletableFuture<>();
        deletePublicAccessBlockResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.deletePublicAccessBlock(DeletePublicAccessBlockRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(deletePublicAccessBlockResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryDeletePublicAccessBlock();

        // Verify the results
    }

    @Test
    void testTryDeletePublicAccessBlock1() {
        // Setup
        // Configure S3AsyncClient.deletePublicAccessBlock(...).
        final CompletableFuture<DeletePublicAccessBlockResponse> deletePublicAccessBlockResponseCompletableFuture = CompletableFuture.completedFuture(
                DeletePublicAccessBlockResponse.builder().build());
        when(mockS3AsyncClient.deletePublicAccessBlock(any(Consumer.class)))
                .thenReturn(deletePublicAccessBlockResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryDeletePublicAccessBlock1();

        // Verify the results
    }

    @Test
    void testTryDeletePublicAccessBlock1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.deletePublicAccessBlock(...).
        final CompletableFuture<DeletePublicAccessBlockResponse> deletePublicAccessBlockResponseCompletableFuture = new CompletableFuture<>();
        deletePublicAccessBlockResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.deletePublicAccessBlock(any(Consumer.class)))
                .thenReturn(deletePublicAccessBlockResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryDeletePublicAccessBlock1();

        // Verify the results
    }

    @Test
    void testTryGetBucketAccelerateConfiguration() {
        // Setup
        // Configure S3AsyncClient.getBucketAccelerateConfiguration(...).
        final CompletableFuture<GetBucketAccelerateConfigurationResponse> getBucketAccelerateConfigurationResponseCompletableFuture = CompletableFuture.completedFuture(
                GetBucketAccelerateConfigurationResponse.builder().build());
        when(mockS3AsyncClient.getBucketAccelerateConfiguration(GetBucketAccelerateConfigurationRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(getBucketAccelerateConfigurationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketAccelerateConfiguration();

        // Verify the results
    }

    @Test
    void testTryGetBucketAccelerateConfiguration_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.getBucketAccelerateConfiguration(...).
        final CompletableFuture<GetBucketAccelerateConfigurationResponse> getBucketAccelerateConfigurationResponseCompletableFuture = new CompletableFuture<>();
        getBucketAccelerateConfigurationResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.getBucketAccelerateConfiguration(GetBucketAccelerateConfigurationRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(getBucketAccelerateConfigurationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketAccelerateConfiguration();

        // Verify the results
    }

    @Test
    void testTryGetBucketAccelerateConfiguration1() {
        // Setup
        // Configure S3AsyncClient.getBucketAccelerateConfiguration(...).
        final CompletableFuture<GetBucketAccelerateConfigurationResponse> getBucketAccelerateConfigurationResponseCompletableFuture = CompletableFuture.completedFuture(
                GetBucketAccelerateConfigurationResponse.builder().build());
        when(mockS3AsyncClient.getBucketAccelerateConfiguration(any(Consumer.class)))
                .thenReturn(getBucketAccelerateConfigurationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketAccelerateConfiguration1();

        // Verify the results
    }

    @Test
    void testTryGetBucketAccelerateConfiguration1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.getBucketAccelerateConfiguration(...).
        final CompletableFuture<GetBucketAccelerateConfigurationResponse> getBucketAccelerateConfigurationResponseCompletableFuture = new CompletableFuture<>();
        getBucketAccelerateConfigurationResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.getBucketAccelerateConfiguration(any(Consumer.class)))
                .thenReturn(getBucketAccelerateConfigurationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketAccelerateConfiguration1();

        // Verify the results
    }

    @Test
    void testTryGetBucketAcl() {
        // Setup
        // Configure S3AsyncClient.getBucketAcl(...).
        final CompletableFuture<GetBucketAclResponse> getBucketAclResponseCompletableFuture = CompletableFuture.completedFuture(
                GetBucketAclResponse.builder().build());
        when(mockS3AsyncClient.getBucketAcl(GetBucketAclRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(getBucketAclResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketAcl();

        // Verify the results
    }

    @Test
    void testTryGetBucketAcl_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.getBucketAcl(...).
        final CompletableFuture<GetBucketAclResponse> getBucketAclResponseCompletableFuture = new CompletableFuture<>();
        getBucketAclResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.getBucketAcl(GetBucketAclRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(getBucketAclResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketAcl();

        // Verify the results
    }

    @Test
    void testTryGetBucketAcl1() {
        // Setup
        // Configure S3AsyncClient.getBucketAcl(...).
        final CompletableFuture<GetBucketAclResponse> getBucketAclResponseCompletableFuture = CompletableFuture.completedFuture(
                GetBucketAclResponse.builder().build());
        when(mockS3AsyncClient.getBucketAcl(any(Consumer.class))).thenReturn(getBucketAclResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketAcl1();

        // Verify the results
    }

    @Test
    void testTryGetBucketAcl1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.getBucketAcl(...).
        final CompletableFuture<GetBucketAclResponse> getBucketAclResponseCompletableFuture = new CompletableFuture<>();
        getBucketAclResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.getBucketAcl(any(Consumer.class))).thenReturn(getBucketAclResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketAcl1();

        // Verify the results
    }

    @Test
    void testTryGetBucketAnalyticsConfiguration() {
        // Setup
        // Configure S3AsyncClient.getBucketAnalyticsConfiguration(...).
        final CompletableFuture<GetBucketAnalyticsConfigurationResponse> getBucketAnalyticsConfigurationResponseCompletableFuture = CompletableFuture.completedFuture(
                GetBucketAnalyticsConfigurationResponse.builder().build());
        when(mockS3AsyncClient.getBucketAnalyticsConfiguration(GetBucketAnalyticsConfigurationRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(getBucketAnalyticsConfigurationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketAnalyticsConfiguration();

        // Verify the results
    }

    @Test
    void testTryGetBucketAnalyticsConfiguration_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.getBucketAnalyticsConfiguration(...).
        final CompletableFuture<GetBucketAnalyticsConfigurationResponse> getBucketAnalyticsConfigurationResponseCompletableFuture = new CompletableFuture<>();
        getBucketAnalyticsConfigurationResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.getBucketAnalyticsConfiguration(GetBucketAnalyticsConfigurationRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(getBucketAnalyticsConfigurationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketAnalyticsConfiguration();

        // Verify the results
    }

    @Test
    void testTryGetBucketAnalyticsConfiguration1() {
        // Setup
        // Configure S3AsyncClient.getBucketAnalyticsConfiguration(...).
        final CompletableFuture<GetBucketAnalyticsConfigurationResponse> getBucketAnalyticsConfigurationResponseCompletableFuture = CompletableFuture.completedFuture(
                GetBucketAnalyticsConfigurationResponse.builder().build());
        when(mockS3AsyncClient.getBucketAnalyticsConfiguration(any(Consumer.class)))
                .thenReturn(getBucketAnalyticsConfigurationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketAnalyticsConfiguration1();

        // Verify the results
    }

    @Test
    void testTryGetBucketAnalyticsConfiguration1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.getBucketAnalyticsConfiguration(...).
        final CompletableFuture<GetBucketAnalyticsConfigurationResponse> getBucketAnalyticsConfigurationResponseCompletableFuture = new CompletableFuture<>();
        getBucketAnalyticsConfigurationResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.getBucketAnalyticsConfiguration(any(Consumer.class)))
                .thenReturn(getBucketAnalyticsConfigurationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketAnalyticsConfiguration1();

        // Verify the results
    }

    @Test
    void testTryGetBucketCors() {
        // Setup
        // Configure S3AsyncClient.getBucketCors(...).
        final CompletableFuture<GetBucketCorsResponse> getBucketCorsResponseCompletableFuture = CompletableFuture.completedFuture(
                GetBucketCorsResponse.builder().build());
        when(mockS3AsyncClient.getBucketCors(GetBucketCorsRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(getBucketCorsResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketCors();

        // Verify the results
    }

    @Test
    void testTryGetBucketCors_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.getBucketCors(...).
        final CompletableFuture<GetBucketCorsResponse> getBucketCorsResponseCompletableFuture = new CompletableFuture<>();
        getBucketCorsResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.getBucketCors(GetBucketCorsRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(getBucketCorsResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketCors();

        // Verify the results
    }

    @Test
    void testTryGetBucketCors1() {
        // Setup
        // Configure S3AsyncClient.getBucketCors(...).
        final CompletableFuture<GetBucketCorsResponse> getBucketCorsResponseCompletableFuture = CompletableFuture.completedFuture(
                GetBucketCorsResponse.builder().build());
        when(mockS3AsyncClient.getBucketCors(any(Consumer.class))).thenReturn(getBucketCorsResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketCors1();

        // Verify the results
    }

    @Test
    void testTryGetBucketCors1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.getBucketCors(...).
        final CompletableFuture<GetBucketCorsResponse> getBucketCorsResponseCompletableFuture = new CompletableFuture<>();
        getBucketCorsResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.getBucketCors(any(Consumer.class))).thenReturn(getBucketCorsResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketCors1();

        // Verify the results
    }

    @Test
    void testTryGetBucketEncryption() {
        // Setup
        // Configure S3AsyncClient.getBucketEncryption(...).
        final CompletableFuture<GetBucketEncryptionResponse> getBucketEncryptionResponseCompletableFuture = CompletableFuture.completedFuture(
                GetBucketEncryptionResponse.builder().build());
        when(mockS3AsyncClient.getBucketEncryption(GetBucketEncryptionRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(getBucketEncryptionResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketEncryption();

        // Verify the results
    }

    @Test
    void testTryGetBucketEncryption_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.getBucketEncryption(...).
        final CompletableFuture<GetBucketEncryptionResponse> getBucketEncryptionResponseCompletableFuture = new CompletableFuture<>();
        getBucketEncryptionResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.getBucketEncryption(GetBucketEncryptionRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(getBucketEncryptionResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketEncryption();

        // Verify the results
    }

    @Test
    void testTryGetBucketEncryption1() {
        // Setup
        // Configure S3AsyncClient.getBucketEncryption(...).
        final CompletableFuture<GetBucketEncryptionResponse> getBucketEncryptionResponseCompletableFuture = CompletableFuture.completedFuture(
                GetBucketEncryptionResponse.builder().build());
        when(mockS3AsyncClient.getBucketEncryption(any(Consumer.class)))
                .thenReturn(getBucketEncryptionResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketEncryption1();

        // Verify the results
    }

    @Test
    void testTryGetBucketEncryption1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.getBucketEncryption(...).
        final CompletableFuture<GetBucketEncryptionResponse> getBucketEncryptionResponseCompletableFuture = new CompletableFuture<>();
        getBucketEncryptionResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.getBucketEncryption(any(Consumer.class)))
                .thenReturn(getBucketEncryptionResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketEncryption1();

        // Verify the results
    }

    @Test
    void testTryGetBucketInventoryConfiguration() {
        // Setup
        // Configure S3AsyncClient.getBucketInventoryConfiguration(...).
        final CompletableFuture<GetBucketInventoryConfigurationResponse> getBucketInventoryConfigurationResponseCompletableFuture = CompletableFuture.completedFuture(
                GetBucketInventoryConfigurationResponse.builder().build());
        when(mockS3AsyncClient.getBucketInventoryConfiguration(GetBucketInventoryConfigurationRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(getBucketInventoryConfigurationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketInventoryConfiguration();

        // Verify the results
    }

    @Test
    void testTryGetBucketInventoryConfiguration_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.getBucketInventoryConfiguration(...).
        final CompletableFuture<GetBucketInventoryConfigurationResponse> getBucketInventoryConfigurationResponseCompletableFuture = new CompletableFuture<>();
        getBucketInventoryConfigurationResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.getBucketInventoryConfiguration(GetBucketInventoryConfigurationRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(getBucketInventoryConfigurationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketInventoryConfiguration();

        // Verify the results
    }

    @Test
    void testTryGetBucketInventoryConfiguration1() {
        // Setup
        // Configure S3AsyncClient.getBucketInventoryConfiguration(...).
        final CompletableFuture<GetBucketInventoryConfigurationResponse> getBucketInventoryConfigurationResponseCompletableFuture = CompletableFuture.completedFuture(
                GetBucketInventoryConfigurationResponse.builder().build());
        when(mockS3AsyncClient.getBucketInventoryConfiguration(any(Consumer.class)))
                .thenReturn(getBucketInventoryConfigurationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketInventoryConfiguration1();

        // Verify the results
    }

    @Test
    void testTryGetBucketInventoryConfiguration1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.getBucketInventoryConfiguration(...).
        final CompletableFuture<GetBucketInventoryConfigurationResponse> getBucketInventoryConfigurationResponseCompletableFuture = new CompletableFuture<>();
        getBucketInventoryConfigurationResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.getBucketInventoryConfiguration(any(Consumer.class)))
                .thenReturn(getBucketInventoryConfigurationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketInventoryConfiguration1();

        // Verify the results
    }

    @Test
    void testTryGetBucketLifecycleConfiguration() {
        // Setup
        // Configure S3AsyncClient.getBucketLifecycleConfiguration(...).
        final CompletableFuture<GetBucketLifecycleConfigurationResponse> getBucketLifecycleConfigurationResponseCompletableFuture = CompletableFuture.completedFuture(
                GetBucketLifecycleConfigurationResponse.builder().build());
        when(mockS3AsyncClient.getBucketLifecycleConfiguration(GetBucketLifecycleConfigurationRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(getBucketLifecycleConfigurationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketLifecycleConfiguration();

        // Verify the results
    }

    @Test
    void testTryGetBucketLifecycleConfiguration_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.getBucketLifecycleConfiguration(...).
        final CompletableFuture<GetBucketLifecycleConfigurationResponse> getBucketLifecycleConfigurationResponseCompletableFuture = new CompletableFuture<>();
        getBucketLifecycleConfigurationResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.getBucketLifecycleConfiguration(GetBucketLifecycleConfigurationRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(getBucketLifecycleConfigurationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketLifecycleConfiguration();

        // Verify the results
    }

    @Test
    void testTryGetBucketLifecycleConfiguration1() {
        // Setup
        // Configure S3AsyncClient.getBucketLifecycleConfiguration(...).
        final CompletableFuture<GetBucketLifecycleConfigurationResponse> getBucketLifecycleConfigurationResponseCompletableFuture = CompletableFuture.completedFuture(
                GetBucketLifecycleConfigurationResponse.builder().build());
        when(mockS3AsyncClient.getBucketLifecycleConfiguration(any(Consumer.class)))
                .thenReturn(getBucketLifecycleConfigurationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketLifecycleConfiguration1();

        // Verify the results
    }

    @Test
    void testTryGetBucketLifecycleConfiguration1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.getBucketLifecycleConfiguration(...).
        final CompletableFuture<GetBucketLifecycleConfigurationResponse> getBucketLifecycleConfigurationResponseCompletableFuture = new CompletableFuture<>();
        getBucketLifecycleConfigurationResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.getBucketLifecycleConfiguration(any(Consumer.class)))
                .thenReturn(getBucketLifecycleConfigurationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketLifecycleConfiguration1();

        // Verify the results
    }

    @Test
    void testTryGetBucketLocation() {
        // Setup
        // Configure S3AsyncClient.getBucketLocation(...).
        final CompletableFuture<GetBucketLocationResponse> getBucketLocationResponseCompletableFuture = CompletableFuture.completedFuture(
                GetBucketLocationResponse.builder().build());
        when(mockS3AsyncClient.getBucketLocation(GetBucketLocationRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(getBucketLocationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketLocation();

        // Verify the results
    }

    @Test
    void testTryGetBucketLocation_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.getBucketLocation(...).
        final CompletableFuture<GetBucketLocationResponse> getBucketLocationResponseCompletableFuture = new CompletableFuture<>();
        getBucketLocationResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.getBucketLocation(GetBucketLocationRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(getBucketLocationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketLocation();

        // Verify the results
    }

    @Test
    void testTryGetBucketLocation1() {
        // Setup
        // Configure S3AsyncClient.getBucketLocation(...).
        final CompletableFuture<GetBucketLocationResponse> getBucketLocationResponseCompletableFuture = CompletableFuture.completedFuture(
                GetBucketLocationResponse.builder().build());
        when(mockS3AsyncClient.getBucketLocation(any(Consumer.class)))
                .thenReturn(getBucketLocationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketLocation1();

        // Verify the results
    }

    @Test
    void testTryGetBucketLocation1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.getBucketLocation(...).
        final CompletableFuture<GetBucketLocationResponse> getBucketLocationResponseCompletableFuture = new CompletableFuture<>();
        getBucketLocationResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.getBucketLocation(any(Consumer.class)))
                .thenReturn(getBucketLocationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketLocation1();

        // Verify the results
    }

    @Test
    void testTryGetBucketLogging() {
        // Setup
        // Configure S3AsyncClient.getBucketLogging(...).
        final CompletableFuture<GetBucketLoggingResponse> getBucketLoggingResponseCompletableFuture = CompletableFuture.completedFuture(
                GetBucketLoggingResponse.builder().build());
        when(mockS3AsyncClient.getBucketLogging(GetBucketLoggingRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(getBucketLoggingResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketLogging();

        // Verify the results
    }

    @Test
    void testTryGetBucketLogging_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.getBucketLogging(...).
        final CompletableFuture<GetBucketLoggingResponse> getBucketLoggingResponseCompletableFuture = new CompletableFuture<>();
        getBucketLoggingResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.getBucketLogging(GetBucketLoggingRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(getBucketLoggingResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketLogging();

        // Verify the results
    }

    @Test
    void testTryGetBucketLogging1() {
        // Setup
        // Configure S3AsyncClient.getBucketLogging(...).
        final CompletableFuture<GetBucketLoggingResponse> getBucketLoggingResponseCompletableFuture = CompletableFuture.completedFuture(
                GetBucketLoggingResponse.builder().build());
        when(mockS3AsyncClient.getBucketLogging(any(Consumer.class)))
                .thenReturn(getBucketLoggingResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketLogging1();

        // Verify the results
    }

    @Test
    void testTryGetBucketLogging1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.getBucketLogging(...).
        final CompletableFuture<GetBucketLoggingResponse> getBucketLoggingResponseCompletableFuture = new CompletableFuture<>();
        getBucketLoggingResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.getBucketLogging(any(Consumer.class)))
                .thenReturn(getBucketLoggingResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketLogging1();

        // Verify the results
    }

    @Test
    void testTryGetBucketMetricsConfiguration() {
        // Setup
        // Configure S3AsyncClient.getBucketMetricsConfiguration(...).
        final CompletableFuture<GetBucketMetricsConfigurationResponse> getBucketMetricsConfigurationResponseCompletableFuture = CompletableFuture.completedFuture(
                GetBucketMetricsConfigurationResponse.builder().build());
        when(mockS3AsyncClient.getBucketMetricsConfiguration(GetBucketMetricsConfigurationRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(getBucketMetricsConfigurationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketMetricsConfiguration();

        // Verify the results
    }

    @Test
    void testTryGetBucketMetricsConfiguration_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.getBucketMetricsConfiguration(...).
        final CompletableFuture<GetBucketMetricsConfigurationResponse> getBucketMetricsConfigurationResponseCompletableFuture = new CompletableFuture<>();
        getBucketMetricsConfigurationResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.getBucketMetricsConfiguration(GetBucketMetricsConfigurationRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(getBucketMetricsConfigurationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketMetricsConfiguration();

        // Verify the results
    }

    @Test
    void testTryGetBucketMetricsConfiguration1() {
        // Setup
        // Configure S3AsyncClient.getBucketMetricsConfiguration(...).
        final CompletableFuture<GetBucketMetricsConfigurationResponse> getBucketMetricsConfigurationResponseCompletableFuture = CompletableFuture.completedFuture(
                GetBucketMetricsConfigurationResponse.builder().build());
        when(mockS3AsyncClient.getBucketMetricsConfiguration(any(Consumer.class)))
                .thenReturn(getBucketMetricsConfigurationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketMetricsConfiguration1();

        // Verify the results
    }

    @Test
    void testTryGetBucketMetricsConfiguration1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.getBucketMetricsConfiguration(...).
        final CompletableFuture<GetBucketMetricsConfigurationResponse> getBucketMetricsConfigurationResponseCompletableFuture = new CompletableFuture<>();
        getBucketMetricsConfigurationResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.getBucketMetricsConfiguration(any(Consumer.class)))
                .thenReturn(getBucketMetricsConfigurationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketMetricsConfiguration1();

        // Verify the results
    }

    @Test
    void testTryGetBucketNotificationConfiguration() {
        // Setup
        // Configure S3AsyncClient.getBucketNotificationConfiguration(...).
        final CompletableFuture<GetBucketNotificationConfigurationResponse> getBucketNotificationConfigurationResponseCompletableFuture = CompletableFuture.completedFuture(
                GetBucketNotificationConfigurationResponse.builder().build());
        when(mockS3AsyncClient.getBucketNotificationConfiguration(GetBucketNotificationConfigurationRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(getBucketNotificationConfigurationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketNotificationConfiguration();

        // Verify the results
    }

    @Test
    void testTryGetBucketNotificationConfiguration_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.getBucketNotificationConfiguration(...).
        final CompletableFuture<GetBucketNotificationConfigurationResponse> getBucketNotificationConfigurationResponseCompletableFuture = new CompletableFuture<>();
        getBucketNotificationConfigurationResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.getBucketNotificationConfiguration(GetBucketNotificationConfigurationRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(getBucketNotificationConfigurationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketNotificationConfiguration();

        // Verify the results
    }

    @Test
    void testTryGetBucketNotificationConfiguration1() {
        // Setup
        // Configure S3AsyncClient.getBucketNotificationConfiguration(...).
        final CompletableFuture<GetBucketNotificationConfigurationResponse> getBucketNotificationConfigurationResponseCompletableFuture = CompletableFuture.completedFuture(
                GetBucketNotificationConfigurationResponse.builder().build());
        when(mockS3AsyncClient.getBucketNotificationConfiguration(any(Consumer.class)))
                .thenReturn(getBucketNotificationConfigurationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketNotificationConfiguration1();

        // Verify the results
    }

    @Test
    void testTryGetBucketNotificationConfiguration1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.getBucketNotificationConfiguration(...).
        final CompletableFuture<GetBucketNotificationConfigurationResponse> getBucketNotificationConfigurationResponseCompletableFuture = new CompletableFuture<>();
        getBucketNotificationConfigurationResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.getBucketNotificationConfiguration(any(Consumer.class)))
                .thenReturn(getBucketNotificationConfigurationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketNotificationConfiguration1();

        // Verify the results
    }

    @Test
    void testTryGetBucketPolicy() {
        // Setup
        // Configure S3AsyncClient.getBucketPolicy(...).
        final CompletableFuture<GetBucketPolicyResponse> getBucketPolicyResponseCompletableFuture = CompletableFuture.completedFuture(
                GetBucketPolicyResponse.builder().build());
        when(mockS3AsyncClient.getBucketPolicy(GetBucketPolicyRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(getBucketPolicyResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketPolicy();

        // Verify the results
    }

    @Test
    void testTryGetBucketPolicy_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.getBucketPolicy(...).
        final CompletableFuture<GetBucketPolicyResponse> getBucketPolicyResponseCompletableFuture = new CompletableFuture<>();
        getBucketPolicyResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.getBucketPolicy(GetBucketPolicyRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(getBucketPolicyResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketPolicy();

        // Verify the results
    }

    @Test
    void testTryGetBucketPolicy1() {
        // Setup
        // Configure S3AsyncClient.getBucketPolicy(...).
        final CompletableFuture<GetBucketPolicyResponse> getBucketPolicyResponseCompletableFuture = CompletableFuture.completedFuture(
                GetBucketPolicyResponse.builder().build());
        when(mockS3AsyncClient.getBucketPolicy(any(Consumer.class)))
                .thenReturn(getBucketPolicyResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketPolicy1();

        // Verify the results
    }

    @Test
    void testTryGetBucketPolicy1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.getBucketPolicy(...).
        final CompletableFuture<GetBucketPolicyResponse> getBucketPolicyResponseCompletableFuture = new CompletableFuture<>();
        getBucketPolicyResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.getBucketPolicy(any(Consumer.class)))
                .thenReturn(getBucketPolicyResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketPolicy1();

        // Verify the results
    }

    @Test
    void testTryGetBucketPolicyStatus() {
        // Setup
        // Configure S3AsyncClient.getBucketPolicyStatus(...).
        final CompletableFuture<GetBucketPolicyStatusResponse> getBucketPolicyStatusResponseCompletableFuture = CompletableFuture.completedFuture(
                GetBucketPolicyStatusResponse.builder().build());
        when(mockS3AsyncClient.getBucketPolicyStatus(GetBucketPolicyStatusRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(getBucketPolicyStatusResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketPolicyStatus();

        // Verify the results
    }

    @Test
    void testTryGetBucketPolicyStatus_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.getBucketPolicyStatus(...).
        final CompletableFuture<GetBucketPolicyStatusResponse> getBucketPolicyStatusResponseCompletableFuture = new CompletableFuture<>();
        getBucketPolicyStatusResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.getBucketPolicyStatus(GetBucketPolicyStatusRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(getBucketPolicyStatusResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketPolicyStatus();

        // Verify the results
    }

    @Test
    void testTryGetBucketPolicyStatus1() {
        // Setup
        // Configure S3AsyncClient.getBucketPolicyStatus(...).
        final CompletableFuture<GetBucketPolicyStatusResponse> getBucketPolicyStatusResponseCompletableFuture = CompletableFuture.completedFuture(
                GetBucketPolicyStatusResponse.builder().build());
        when(mockS3AsyncClient.getBucketPolicyStatus(any(Consumer.class)))
                .thenReturn(getBucketPolicyStatusResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketPolicyStatus1();

        // Verify the results
    }

    @Test
    void testTryGetBucketPolicyStatus1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.getBucketPolicyStatus(...).
        final CompletableFuture<GetBucketPolicyStatusResponse> getBucketPolicyStatusResponseCompletableFuture = new CompletableFuture<>();
        getBucketPolicyStatusResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.getBucketPolicyStatus(any(Consumer.class)))
                .thenReturn(getBucketPolicyStatusResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketPolicyStatus1();

        // Verify the results
    }

    @Test
    void testTryGetBucketReplication() {
        // Setup
        // Configure S3AsyncClient.getBucketReplication(...).
        final CompletableFuture<GetBucketReplicationResponse> getBucketReplicationResponseCompletableFuture = CompletableFuture.completedFuture(
                GetBucketReplicationResponse.builder().build());
        when(mockS3AsyncClient.getBucketReplication(GetBucketReplicationRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(getBucketReplicationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketReplication();

        // Verify the results
    }

    @Test
    void testTryGetBucketReplication_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.getBucketReplication(...).
        final CompletableFuture<GetBucketReplicationResponse> getBucketReplicationResponseCompletableFuture = new CompletableFuture<>();
        getBucketReplicationResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.getBucketReplication(GetBucketReplicationRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(getBucketReplicationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketReplication();

        // Verify the results
    }

    @Test
    void testTryGetBucketReplication1() {
        // Setup
        // Configure S3AsyncClient.getBucketReplication(...).
        final CompletableFuture<GetBucketReplicationResponse> getBucketReplicationResponseCompletableFuture = CompletableFuture.completedFuture(
                GetBucketReplicationResponse.builder().build());
        when(mockS3AsyncClient.getBucketReplication(any(Consumer.class)))
                .thenReturn(getBucketReplicationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketReplication1();

        // Verify the results
    }

    @Test
    void testTryGetBucketReplication1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.getBucketReplication(...).
        final CompletableFuture<GetBucketReplicationResponse> getBucketReplicationResponseCompletableFuture = new CompletableFuture<>();
        getBucketReplicationResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.getBucketReplication(any(Consumer.class)))
                .thenReturn(getBucketReplicationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketReplication1();

        // Verify the results
    }

    @Test
    void testTryGetBucketRequestPayment() {
        // Setup
        // Configure S3AsyncClient.getBucketRequestPayment(...).
        final CompletableFuture<GetBucketRequestPaymentResponse> getBucketRequestPaymentResponseCompletableFuture = CompletableFuture.completedFuture(
                GetBucketRequestPaymentResponse.builder().build());
        when(mockS3AsyncClient.getBucketRequestPayment(GetBucketRequestPaymentRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(getBucketRequestPaymentResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketRequestPayment();

        // Verify the results
    }

    @Test
    void testTryGetBucketRequestPayment_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.getBucketRequestPayment(...).
        final CompletableFuture<GetBucketRequestPaymentResponse> getBucketRequestPaymentResponseCompletableFuture = new CompletableFuture<>();
        getBucketRequestPaymentResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.getBucketRequestPayment(GetBucketRequestPaymentRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(getBucketRequestPaymentResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketRequestPayment();

        // Verify the results
    }

    @Test
    void testTryGetBucketRequestPayment1() {
        // Setup
        // Configure S3AsyncClient.getBucketRequestPayment(...).
        final CompletableFuture<GetBucketRequestPaymentResponse> getBucketRequestPaymentResponseCompletableFuture = CompletableFuture.completedFuture(
                GetBucketRequestPaymentResponse.builder().build());
        when(mockS3AsyncClient.getBucketRequestPayment(any(Consumer.class)))
                .thenReturn(getBucketRequestPaymentResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketRequestPayment1();

        // Verify the results
    }

    @Test
    void testTryGetBucketRequestPayment1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.getBucketRequestPayment(...).
        final CompletableFuture<GetBucketRequestPaymentResponse> getBucketRequestPaymentResponseCompletableFuture = new CompletableFuture<>();
        getBucketRequestPaymentResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.getBucketRequestPayment(any(Consumer.class)))
                .thenReturn(getBucketRequestPaymentResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketRequestPayment1();

        // Verify the results
    }

    @Test
    void testTryGetBucketTagging() {
        // Setup
        // Configure S3AsyncClient.getBucketTagging(...).
        final CompletableFuture<GetBucketTaggingResponse> getBucketTaggingResponseCompletableFuture = CompletableFuture.completedFuture(
                GetBucketTaggingResponse.builder().build());
        when(mockS3AsyncClient.getBucketTagging(GetBucketTaggingRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(getBucketTaggingResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketTagging();

        // Verify the results
    }

    @Test
    void testTryGetBucketTagging_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.getBucketTagging(...).
        final CompletableFuture<GetBucketTaggingResponse> getBucketTaggingResponseCompletableFuture = new CompletableFuture<>();
        getBucketTaggingResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.getBucketTagging(GetBucketTaggingRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(getBucketTaggingResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketTagging();

        // Verify the results
    }

    @Test
    void testTryGetBucketTagging1() {
        // Setup
        // Configure S3AsyncClient.getBucketTagging(...).
        final CompletableFuture<GetBucketTaggingResponse> getBucketTaggingResponseCompletableFuture = CompletableFuture.completedFuture(
                GetBucketTaggingResponse.builder().build());
        when(mockS3AsyncClient.getBucketTagging(any(Consumer.class)))
                .thenReturn(getBucketTaggingResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketTagging1();

        // Verify the results
    }

    @Test
    void testTryGetBucketTagging1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.getBucketTagging(...).
        final CompletableFuture<GetBucketTaggingResponse> getBucketTaggingResponseCompletableFuture = new CompletableFuture<>();
        getBucketTaggingResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.getBucketTagging(any(Consumer.class)))
                .thenReturn(getBucketTaggingResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketTagging1();

        // Verify the results
    }

    @Test
    void testTryGetBucketVersioning() {
        // Setup
        // Configure S3AsyncClient.getBucketVersioning(...).
        final CompletableFuture<GetBucketVersioningResponse> getBucketVersioningResponseCompletableFuture = CompletableFuture.completedFuture(
                GetBucketVersioningResponse.builder().build());
        when(mockS3AsyncClient.getBucketVersioning(GetBucketVersioningRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(getBucketVersioningResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketVersioning();

        // Verify the results
    }

    @Test
    void testTryGetBucketVersioning_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.getBucketVersioning(...).
        final CompletableFuture<GetBucketVersioningResponse> getBucketVersioningResponseCompletableFuture = new CompletableFuture<>();
        getBucketVersioningResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.getBucketVersioning(GetBucketVersioningRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(getBucketVersioningResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketVersioning();

        // Verify the results
    }

    @Test
    void testTryGetBucketVersioning1() {
        // Setup
        // Configure S3AsyncClient.getBucketVersioning(...).
        final CompletableFuture<GetBucketVersioningResponse> getBucketVersioningResponseCompletableFuture = CompletableFuture.completedFuture(
                GetBucketVersioningResponse.builder().build());
        when(mockS3AsyncClient.getBucketVersioning(any(Consumer.class)))
                .thenReturn(getBucketVersioningResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketVersioning1();

        // Verify the results
    }

    @Test
    void testTryGetBucketVersioning1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.getBucketVersioning(...).
        final CompletableFuture<GetBucketVersioningResponse> getBucketVersioningResponseCompletableFuture = new CompletableFuture<>();
        getBucketVersioningResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.getBucketVersioning(any(Consumer.class)))
                .thenReturn(getBucketVersioningResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketVersioning1();

        // Verify the results
    }

    @Test
    void testTryGetBucketWebsite() {
        // Setup
        // Configure S3AsyncClient.getBucketWebsite(...).
        final CompletableFuture<GetBucketWebsiteResponse> getBucketWebsiteResponseCompletableFuture = CompletableFuture.completedFuture(
                GetBucketWebsiteResponse.builder().build());
        when(mockS3AsyncClient.getBucketWebsite(GetBucketWebsiteRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(getBucketWebsiteResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketWebsite();

        // Verify the results
    }

    @Test
    void testTryGetBucketWebsite_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.getBucketWebsite(...).
        final CompletableFuture<GetBucketWebsiteResponse> getBucketWebsiteResponseCompletableFuture = new CompletableFuture<>();
        getBucketWebsiteResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.getBucketWebsite(GetBucketWebsiteRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(getBucketWebsiteResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketWebsite();

        // Verify the results
    }

    @Test
    void testTryGetBucketWebsite1() {
        // Setup
        // Configure S3AsyncClient.getBucketWebsite(...).
        final CompletableFuture<GetBucketWebsiteResponse> getBucketWebsiteResponseCompletableFuture = CompletableFuture.completedFuture(
                GetBucketWebsiteResponse.builder().build());
        when(mockS3AsyncClient.getBucketWebsite(any(Consumer.class)))
                .thenReturn(getBucketWebsiteResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketWebsite1();

        // Verify the results
    }

    @Test
    void testTryGetBucketWebsite1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.getBucketWebsite(...).
        final CompletableFuture<GetBucketWebsiteResponse> getBucketWebsiteResponseCompletableFuture = new CompletableFuture<>();
        getBucketWebsiteResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.getBucketWebsite(any(Consumer.class)))
                .thenReturn(getBucketWebsiteResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetBucketWebsite1();

        // Verify the results
    }

    @Test
    void testTryGetObject() {
        // Setup
        // Configure S3AsyncClient.getObject(...).
        final CompletableFuture<GetObjectResponse> getObjectResponseCompletableFuture = CompletableFuture.completedFuture(
                GetObjectResponse.builder().build());
        when(mockS3AsyncClient.getObject(eq(GetObjectRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build()), any(AsyncResponseTransformer.class))).thenReturn(getObjectResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetObject();

        // Verify the results
    }

    @Test
    void testTryGetObject_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.getObject(...).
        final CompletableFuture<GetObjectResponse> getObjectResponseCompletableFuture = new CompletableFuture<>();
        getObjectResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.getObject(eq(GetObjectRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build()), any(AsyncResponseTransformer.class))).thenReturn(getObjectResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetObject();

        // Verify the results
    }

    @Test
    void testTryGetObjectToBytes() {
        // Setup
        // Configure S3AsyncClient.getObject(...).
        final CompletableFuture<ResponseBytes<GetObjectResponse>> responseBytesCompletableFuture = CompletableFuture.completedFuture(
                ResponseBytes.fromByteArray(GetObjectResponse.builder().build(), "content".getBytes()));
        when(mockS3AsyncClient.getObject(eq(GetObjectRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build()), any(AsyncResponseTransformer.class))).thenReturn(responseBytesCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetObjectToBytes();

        // Verify the results
    }

    @Test
    void testTryGetObjectToBytes_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.getObject(...).
        final CompletableFuture<ResponseBytes<GetObjectResponse>> responseBytesCompletableFuture = new CompletableFuture<>();
        responseBytesCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.getObject(eq(GetObjectRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build()), any(AsyncResponseTransformer.class))).thenReturn(responseBytesCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetObjectToBytes();

        // Verify the results
    }

    @Test
    void testTryGetObject1() {
        // Setup
        // Configure S3AsyncClient.getObject(...).
        final CompletableFuture<GetObjectResponse> getObjectResponseCompletableFuture = CompletableFuture.completedFuture(
                GetObjectResponse.builder().build());
        when(mockS3AsyncClient.getObject(any(Consumer.class), any(AsyncResponseTransformer.class)))
                .thenReturn(getObjectResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetObject1();

        // Verify the results
    }

    @Test
    void testTryGetObject1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.getObject(...).
        final CompletableFuture<GetObjectResponse> getObjectResponseCompletableFuture = new CompletableFuture<>();
        getObjectResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.getObject(any(Consumer.class), any(AsyncResponseTransformer.class)))
                .thenReturn(getObjectResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetObject1();

        // Verify the results
    }

    @Test
    void testTryGetObject1ToByteArray() {
        // Setup
        // Configure S3AsyncClient.getObject(...).
        final CompletableFuture<ResponseBytes<GetObjectResponse>> responseBytesCompletableFuture = CompletableFuture.completedFuture(
                ResponseBytes.fromByteArray(GetObjectResponse.builder().build(), "content".getBytes()));
        when(mockS3AsyncClient.getObject(any(Consumer.class), any(AsyncResponseTransformer.class)))
                .thenReturn(responseBytesCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetObject1ToByteArray();

        // Verify the results
    }

    @Test
    void testTryGetObject1ToByteArray_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.getObject(...).
        final CompletableFuture<ResponseBytes<GetObjectResponse>> responseBytesCompletableFuture = new CompletableFuture<>();
        responseBytesCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.getObject(any(Consumer.class), any(AsyncResponseTransformer.class)))
                .thenReturn(responseBytesCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetObject1ToByteArray();

        // Verify the results
    }

    @Test
    void testTryGetObject2() {
        // Setup
        // Configure S3AsyncClient.getObject(...).
        final CompletableFuture<GetObjectResponse> getObjectResponseCompletableFuture = CompletableFuture.completedFuture(
                GetObjectResponse.builder().build());
        when(mockS3AsyncClient.getObject(GetObjectRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build(), Paths.get("filename.txt"))).thenReturn(getObjectResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetObject2();

        // Verify the results
    }

    @Test
    void testTryGetObject2_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.getObject(...).
        final CompletableFuture<GetObjectResponse> getObjectResponseCompletableFuture = new CompletableFuture<>();
        getObjectResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.getObject(GetObjectRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build(), Paths.get("filename.txt"))).thenReturn(getObjectResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetObject2();

        // Verify the results
    }

    @Test
    void testTryGetObject3() {
        // Setup
        // Configure S3AsyncClient.getObject(...).
        final CompletableFuture<GetObjectResponse> getObjectResponseCompletableFuture = CompletableFuture.completedFuture(
                GetObjectResponse.builder().build());
        when(mockS3AsyncClient.getObject(any(Consumer.class), eq(Paths.get("filename.txt"))))
                .thenReturn(getObjectResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetObject3();

        // Verify the results
    }

    @Test
    void testTryGetObject3_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.getObject(...).
        final CompletableFuture<GetObjectResponse> getObjectResponseCompletableFuture = new CompletableFuture<>();
        getObjectResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.getObject(any(Consumer.class), eq(Paths.get("filename.txt"))))
                .thenReturn(getObjectResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetObject3();

        // Verify the results
    }

    @Test
    void testTryGetObjectAcl() {
        // Setup
        // Configure S3AsyncClient.getObjectAcl(...).
        final CompletableFuture<GetObjectAclResponse> getObjectAclResponseCompletableFuture = CompletableFuture.completedFuture(
                GetObjectAclResponse.builder().build());
        when(mockS3AsyncClient.getObjectAcl(GetObjectAclRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build())).thenReturn(getObjectAclResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetObjectAcl();

        // Verify the results
    }

    @Test
    void testTryGetObjectAcl_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.getObjectAcl(...).
        final CompletableFuture<GetObjectAclResponse> getObjectAclResponseCompletableFuture = new CompletableFuture<>();
        getObjectAclResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.getObjectAcl(GetObjectAclRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build())).thenReturn(getObjectAclResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetObjectAcl();

        // Verify the results
    }

    @Test
    void testTryGetObjectAcl1() {
        // Setup
        // Configure S3AsyncClient.getObjectAcl(...).
        final CompletableFuture<GetObjectAclResponse> getObjectAclResponseCompletableFuture = CompletableFuture.completedFuture(
                GetObjectAclResponse.builder().build());
        when(mockS3AsyncClient.getObjectAcl(any(Consumer.class))).thenReturn(getObjectAclResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetObjectAcl1();

        // Verify the results
    }

    @Test
    void testTryGetObjectAcl1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.getObjectAcl(...).
        final CompletableFuture<GetObjectAclResponse> getObjectAclResponseCompletableFuture = new CompletableFuture<>();
        getObjectAclResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.getObjectAcl(any(Consumer.class))).thenReturn(getObjectAclResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetObjectAcl1();

        // Verify the results
    }

    @Test
    void testTryGetObjectLegalHold() {
        // Setup
        // Configure S3AsyncClient.getObjectLegalHold(...).
        final CompletableFuture<GetObjectLegalHoldResponse> getObjectLegalHoldResponseCompletableFuture = CompletableFuture.completedFuture(
                GetObjectLegalHoldResponse.builder().build());
        when(mockS3AsyncClient.getObjectLegalHold(GetObjectLegalHoldRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build())).thenReturn(getObjectLegalHoldResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetObjectLegalHold();

        // Verify the results
    }

    @Test
    void testTryGetObjectLegalHold_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.getObjectLegalHold(...).
        final CompletableFuture<GetObjectLegalHoldResponse> getObjectLegalHoldResponseCompletableFuture = new CompletableFuture<>();
        getObjectLegalHoldResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.getObjectLegalHold(GetObjectLegalHoldRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build())).thenReturn(getObjectLegalHoldResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetObjectLegalHold();

        // Verify the results
    }

    @Test
    void testTryGetObjectLegalHold1() {
        // Setup
        // Configure S3AsyncClient.getObjectLegalHold(...).
        final CompletableFuture<GetObjectLegalHoldResponse> getObjectLegalHoldResponseCompletableFuture = CompletableFuture.completedFuture(
                GetObjectLegalHoldResponse.builder().build());
        when(mockS3AsyncClient.getObjectLegalHold(any(Consumer.class)))
                .thenReturn(getObjectLegalHoldResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetObjectLegalHold1();

        // Verify the results
    }

    @Test
    void testTryGetObjectLegalHold1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.getObjectLegalHold(...).
        final CompletableFuture<GetObjectLegalHoldResponse> getObjectLegalHoldResponseCompletableFuture = new CompletableFuture<>();
        getObjectLegalHoldResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.getObjectLegalHold(any(Consumer.class)))
                .thenReturn(getObjectLegalHoldResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetObjectLegalHold1();

        // Verify the results
    }

    @Test
    void testTryGetObjectLockConfiguration() {
        // Setup
        // Configure S3AsyncClient.getObjectLockConfiguration(...).
        final CompletableFuture<GetObjectLockConfigurationResponse> getObjectLockConfigurationResponseCompletableFuture = CompletableFuture.completedFuture(
                GetObjectLockConfigurationResponse.builder().build());
        when(mockS3AsyncClient.getObjectLockConfiguration(GetObjectLockConfigurationRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(getObjectLockConfigurationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetObjectLockConfiguration();

        // Verify the results
    }

    @Test
    void testTryGetObjectLockConfiguration_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.getObjectLockConfiguration(...).
        final CompletableFuture<GetObjectLockConfigurationResponse> getObjectLockConfigurationResponseCompletableFuture = new CompletableFuture<>();
        getObjectLockConfigurationResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.getObjectLockConfiguration(GetObjectLockConfigurationRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(getObjectLockConfigurationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetObjectLockConfiguration();

        // Verify the results
    }

    @Test
    void testTryGetObjectLockConfiguration1() {
        // Setup
        // Configure S3AsyncClient.getObjectLockConfiguration(...).
        final CompletableFuture<GetObjectLockConfigurationResponse> getObjectLockConfigurationResponseCompletableFuture = CompletableFuture.completedFuture(
                GetObjectLockConfigurationResponse.builder().build());
        when(mockS3AsyncClient.getObjectLockConfiguration(any(Consumer.class)))
                .thenReturn(getObjectLockConfigurationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetObjectLockConfiguration1();

        // Verify the results
    }

    @Test
    void testTryGetObjectLockConfiguration1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.getObjectLockConfiguration(...).
        final CompletableFuture<GetObjectLockConfigurationResponse> getObjectLockConfigurationResponseCompletableFuture = new CompletableFuture<>();
        getObjectLockConfigurationResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.getObjectLockConfiguration(any(Consumer.class)))
                .thenReturn(getObjectLockConfigurationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetObjectLockConfiguration1();

        // Verify the results
    }

    @Test
    void testTryGetObjectRetention() {
        // Setup
        // Configure S3AsyncClient.getObjectRetention(...).
        final CompletableFuture<GetObjectRetentionResponse> getObjectRetentionResponseCompletableFuture = CompletableFuture.completedFuture(
                GetObjectRetentionResponse.builder().build());
        when(mockS3AsyncClient.getObjectRetention(GetObjectRetentionRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build())).thenReturn(getObjectRetentionResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetObjectRetention();

        // Verify the results
    }

    @Test
    void testTryGetObjectRetention_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.getObjectRetention(...).
        final CompletableFuture<GetObjectRetentionResponse> getObjectRetentionResponseCompletableFuture = new CompletableFuture<>();
        getObjectRetentionResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.getObjectRetention(GetObjectRetentionRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build())).thenReturn(getObjectRetentionResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetObjectRetention();

        // Verify the results
    }

    @Test
    void testTryGetObjectRetention1() {
        // Setup
        // Configure S3AsyncClient.getObjectRetention(...).
        final CompletableFuture<GetObjectRetentionResponse> getObjectRetentionResponseCompletableFuture = CompletableFuture.completedFuture(
                GetObjectRetentionResponse.builder().build());
        when(mockS3AsyncClient.getObjectRetention(any(Consumer.class)))
                .thenReturn(getObjectRetentionResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetObjectRetention1();

        // Verify the results
    }

    @Test
    void testTryGetObjectRetention1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.getObjectRetention(...).
        final CompletableFuture<GetObjectRetentionResponse> getObjectRetentionResponseCompletableFuture = new CompletableFuture<>();
        getObjectRetentionResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.getObjectRetention(any(Consumer.class)))
                .thenReturn(getObjectRetentionResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetObjectRetention1();

        // Verify the results
    }

    @Test
    void testTryGetObjectTagging() {
        // Setup
        // Configure S3AsyncClient.getObjectTagging(...).
        final CompletableFuture<GetObjectTaggingResponse> getObjectTaggingResponseCompletableFuture = CompletableFuture.completedFuture(
                GetObjectTaggingResponse.builder().build());
        when(mockS3AsyncClient.getObjectTagging(GetObjectTaggingRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build())).thenReturn(getObjectTaggingResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetObjectTagging();

        // Verify the results
    }

    @Test
    void testTryGetObjectTagging_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.getObjectTagging(...).
        final CompletableFuture<GetObjectTaggingResponse> getObjectTaggingResponseCompletableFuture = new CompletableFuture<>();
        getObjectTaggingResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.getObjectTagging(GetObjectTaggingRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build())).thenReturn(getObjectTaggingResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetObjectTagging();

        // Verify the results
    }

    @Test
    void testTryGetObjectTagging1() {
        // Setup
        // Configure S3AsyncClient.getObjectTagging(...).
        final CompletableFuture<GetObjectTaggingResponse> getObjectTaggingResponseCompletableFuture = CompletableFuture.completedFuture(
                GetObjectTaggingResponse.builder().build());
        when(mockS3AsyncClient.getObjectTagging(any(Consumer.class)))
                .thenReturn(getObjectTaggingResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetObjectTagging1();

        // Verify the results
    }

    @Test
    void testTryGetObjectTagging1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.getObjectTagging(...).
        final CompletableFuture<GetObjectTaggingResponse> getObjectTaggingResponseCompletableFuture = new CompletableFuture<>();
        getObjectTaggingResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.getObjectTagging(any(Consumer.class)))
                .thenReturn(getObjectTaggingResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetObjectTagging1();

        // Verify the results
    }

    @Test
    void testTryGetObjectTorrent() {
        // Setup
        // Configure S3AsyncClient.getObjectTorrent(...).
        final CompletableFuture<GetObjectTorrentResponse> getObjectTorrentResponseCompletableFuture = CompletableFuture.completedFuture(
                GetObjectTorrentResponse.builder().build());
        when(mockS3AsyncClient.getObjectTorrent(eq(GetObjectTorrentRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build()), any(AsyncResponseTransformer.class))).thenReturn(getObjectTorrentResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetObjectTorrent();

        // Verify the results
    }

    @Test
    void testTryGetObjectTorrent_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.getObjectTorrent(...).
        final CompletableFuture<GetObjectTorrentResponse> getObjectTorrentResponseCompletableFuture = new CompletableFuture<>();
        getObjectTorrentResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.getObjectTorrent(eq(GetObjectTorrentRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build()), any(AsyncResponseTransformer.class))).thenReturn(getObjectTorrentResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetObjectTorrent();

        // Verify the results
    }

    @Test
    void testTryGetObjectTorrentToBytes() {
        // Setup
        // Configure S3AsyncClient.getObjectTorrent(...).
        final CompletableFuture<ResponseBytes<GetObjectTorrentResponse>> responseBytesCompletableFuture = CompletableFuture.completedFuture(
                ResponseBytes.fromByteArray(GetObjectTorrentResponse.builder().build(), "content".getBytes()));
        when(mockS3AsyncClient.getObjectTorrent(eq(GetObjectTorrentRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build()), any(AsyncResponseTransformer.class))).thenReturn(responseBytesCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetObjectTorrentToBytes();

        // Verify the results
    }

    @Test
    void testTryGetObjectTorrentToBytes_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.getObjectTorrent(...).
        final CompletableFuture<ResponseBytes<GetObjectTorrentResponse>> responseBytesCompletableFuture = new CompletableFuture<>();
        responseBytesCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.getObjectTorrent(eq(GetObjectTorrentRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build()), any(AsyncResponseTransformer.class))).thenReturn(responseBytesCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetObjectTorrentToBytes();

        // Verify the results
    }

    @Test
    void testTryGetObjectTorrent1() {
        // Setup
        // Configure S3AsyncClient.getObjectTorrent(...).
        final CompletableFuture<GetObjectTorrentResponse> getObjectTorrentResponseCompletableFuture = CompletableFuture.completedFuture(
                GetObjectTorrentResponse.builder().build());
        when(mockS3AsyncClient.getObjectTorrent(any(Consumer.class), any(AsyncResponseTransformer.class)))
                .thenReturn(getObjectTorrentResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetObjectTorrent1();

        // Verify the results
    }

    @Test
    void testTryGetObjectTorrent1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.getObjectTorrent(...).
        final CompletableFuture<GetObjectTorrentResponse> getObjectTorrentResponseCompletableFuture = new CompletableFuture<>();
        getObjectTorrentResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.getObjectTorrent(any(Consumer.class), any(AsyncResponseTransformer.class)))
                .thenReturn(getObjectTorrentResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetObjectTorrent1();

        // Verify the results
    }

    @Test
    void testTryGetObjectTorrent1ToBytes() {
        // Setup
        // Configure S3AsyncClient.getObjectTorrent(...).
        final CompletableFuture<ResponseBytes<GetObjectTorrentResponse>> responseBytesCompletableFuture = CompletableFuture.completedFuture(
                ResponseBytes.fromByteArray(GetObjectTorrentResponse.builder().build(), "content".getBytes()));
        when(mockS3AsyncClient.getObjectTorrent(any(Consumer.class), any(AsyncResponseTransformer.class)))
                .thenReturn(responseBytesCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetObjectTorrent1ToBytes();

        // Verify the results
    }

    @Test
    void testTryGetObjectTorrent1ToBytes_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.getObjectTorrent(...).
        final CompletableFuture<ResponseBytes<GetObjectTorrentResponse>> responseBytesCompletableFuture = new CompletableFuture<>();
        responseBytesCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.getObjectTorrent(any(Consumer.class), any(AsyncResponseTransformer.class)))
                .thenReturn(responseBytesCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetObjectTorrent1ToBytes();

        // Verify the results
    }

    @Test
    void testTryGetObjectTorrent2() {
        // Setup
        // Configure S3AsyncClient.getObjectTorrent(...).
        final CompletableFuture<GetObjectTorrentResponse> getObjectTorrentResponseCompletableFuture = CompletableFuture.completedFuture(
                GetObjectTorrentResponse.builder().build());
        when(mockS3AsyncClient.getObjectTorrent(GetObjectTorrentRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build(), Paths.get("filename.txt"))).thenReturn(getObjectTorrentResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetObjectTorrent2();

        // Verify the results
    }

    @Test
    void testTryGetObjectTorrent2_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.getObjectTorrent(...).
        final CompletableFuture<GetObjectTorrentResponse> getObjectTorrentResponseCompletableFuture = new CompletableFuture<>();
        getObjectTorrentResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.getObjectTorrent(GetObjectTorrentRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build(), Paths.get("filename.txt"))).thenReturn(getObjectTorrentResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetObjectTorrent2();

        // Verify the results
    }

    @Test
    void testTryGetObjectTorrent3() {
        // Setup
        // Configure S3AsyncClient.getObjectTorrent(...).
        final CompletableFuture<GetObjectTorrentResponse> getObjectTorrentResponseCompletableFuture = CompletableFuture.completedFuture(
                GetObjectTorrentResponse.builder().build());
        when(mockS3AsyncClient.getObjectTorrent(any(Consumer.class), eq(Paths.get("filename.txt"))))
                .thenReturn(getObjectTorrentResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetObjectTorrent3();

        // Verify the results
    }

    @Test
    void testTryGetObjectTorrent3_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.getObjectTorrent(...).
        final CompletableFuture<GetObjectTorrentResponse> getObjectTorrentResponseCompletableFuture = new CompletableFuture<>();
        getObjectTorrentResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.getObjectTorrent(any(Consumer.class), eq(Paths.get("filename.txt"))))
                .thenReturn(getObjectTorrentResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetObjectTorrent3();

        // Verify the results
    }

    @Test
    void testTryGetPublicAccessBlock() {
        // Setup
        // Configure S3AsyncClient.getPublicAccessBlock(...).
        final CompletableFuture<GetPublicAccessBlockResponse> getPublicAccessBlockResponseCompletableFuture = CompletableFuture.completedFuture(
                GetPublicAccessBlockResponse.builder().build());
        when(mockS3AsyncClient.getPublicAccessBlock(GetPublicAccessBlockRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(getPublicAccessBlockResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetPublicAccessBlock();

        // Verify the results
    }

    @Test
    void testTryGetPublicAccessBlock_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.getPublicAccessBlock(...).
        final CompletableFuture<GetPublicAccessBlockResponse> getPublicAccessBlockResponseCompletableFuture = new CompletableFuture<>();
        getPublicAccessBlockResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.getPublicAccessBlock(GetPublicAccessBlockRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(getPublicAccessBlockResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetPublicAccessBlock();

        // Verify the results
    }

    @Test
    void testTryGetPublicAccessBlock1() {
        // Setup
        // Configure S3AsyncClient.getPublicAccessBlock(...).
        final CompletableFuture<GetPublicAccessBlockResponse> getPublicAccessBlockResponseCompletableFuture = CompletableFuture.completedFuture(
                GetPublicAccessBlockResponse.builder().build());
        when(mockS3AsyncClient.getPublicAccessBlock(any(Consumer.class)))
                .thenReturn(getPublicAccessBlockResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetPublicAccessBlock1();

        // Verify the results
    }

    @Test
    void testTryGetPublicAccessBlock1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.getPublicAccessBlock(...).
        final CompletableFuture<GetPublicAccessBlockResponse> getPublicAccessBlockResponseCompletableFuture = new CompletableFuture<>();
        getPublicAccessBlockResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.getPublicAccessBlock(any(Consumer.class)))
                .thenReturn(getPublicAccessBlockResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryGetPublicAccessBlock1();

        // Verify the results
    }

    @Test
    void testTryHeadBucket() {
        // Setup
        // Configure S3AsyncClient.headBucket(...).
        final CompletableFuture<HeadBucketResponse> headBucketResponseCompletableFuture = CompletableFuture.completedFuture(
                HeadBucketResponse.builder().build());
        when(mockS3AsyncClient.headBucket(HeadBucketRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(headBucketResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryHeadBucket();

        // Verify the results
    }

    @Test
    void testTryHeadBucket_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.headBucket(...).
        final CompletableFuture<HeadBucketResponse> headBucketResponseCompletableFuture = new CompletableFuture<>();
        headBucketResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.headBucket(HeadBucketRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(headBucketResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryHeadBucket();

        // Verify the results
    }

    @Test
    void testTryHeadBucket1() {
        // Setup
        // Configure S3AsyncClient.headBucket(...).
        final CompletableFuture<HeadBucketResponse> headBucketResponseCompletableFuture = CompletableFuture.completedFuture(
                HeadBucketResponse.builder().build());
        when(mockS3AsyncClient.headBucket(any(Consumer.class))).thenReturn(headBucketResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryHeadBucket1();

        // Verify the results
    }

    @Test
    void testTryHeadBucket1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.headBucket(...).
        final CompletableFuture<HeadBucketResponse> headBucketResponseCompletableFuture = new CompletableFuture<>();
        headBucketResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.headBucket(any(Consumer.class))).thenReturn(headBucketResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryHeadBucket1();

        // Verify the results
    }

    @Test
    void testTryHeadObject() {
        // Setup
        // Configure S3AsyncClient.headObject(...).
        final CompletableFuture<HeadObjectResponse> headObjectResponseCompletableFuture = CompletableFuture.completedFuture(
                HeadObjectResponse.builder().build());
        when(mockS3AsyncClient.headObject(HeadObjectRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build())).thenReturn(headObjectResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryHeadObject();

        // Verify the results
    }

    @Test
    void testTryHeadObject_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.headObject(...).
        final CompletableFuture<HeadObjectResponse> headObjectResponseCompletableFuture = new CompletableFuture<>();
        headObjectResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.headObject(HeadObjectRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build())).thenReturn(headObjectResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryHeadObject();

        // Verify the results
    }

    @Test
    void testTryHeadObject1() {
        // Setup
        // Configure S3AsyncClient.headObject(...).
        final CompletableFuture<HeadObjectResponse> headObjectResponseCompletableFuture = CompletableFuture.completedFuture(
                HeadObjectResponse.builder().build());
        when(mockS3AsyncClient.headObject(any(Consumer.class))).thenReturn(headObjectResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryHeadObject1();

        // Verify the results
    }

    @Test
    void testTryHeadObject1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.headObject(...).
        final CompletableFuture<HeadObjectResponse> headObjectResponseCompletableFuture = new CompletableFuture<>();
        headObjectResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.headObject(any(Consumer.class))).thenReturn(headObjectResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryHeadObject1();

        // Verify the results
    }

    @Test
    void testTryListBucketAnalyticsConfigurations() {
        // Setup
        // Configure S3AsyncClient.listBucketAnalyticsConfigurations(...).
        final CompletableFuture<ListBucketAnalyticsConfigurationsResponse> listBucketAnalyticsConfigurationsResponseCompletableFuture = CompletableFuture.completedFuture(
                ListBucketAnalyticsConfigurationsResponse.builder().build());
        when(mockS3AsyncClient.listBucketAnalyticsConfigurations(ListBucketAnalyticsConfigurationsRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(listBucketAnalyticsConfigurationsResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryListBucketAnalyticsConfigurations();

        // Verify the results
    }

    @Test
    void testTryListBucketAnalyticsConfigurations_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.listBucketAnalyticsConfigurations(...).
        final CompletableFuture<ListBucketAnalyticsConfigurationsResponse> listBucketAnalyticsConfigurationsResponseCompletableFuture = new CompletableFuture<>();
        listBucketAnalyticsConfigurationsResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.listBucketAnalyticsConfigurations(ListBucketAnalyticsConfigurationsRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(listBucketAnalyticsConfigurationsResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryListBucketAnalyticsConfigurations();

        // Verify the results
    }

    @Test
    void testTryListBucketAnalyticsConfigurations1() {
        // Setup
        // Configure S3AsyncClient.listBucketAnalyticsConfigurations(...).
        final CompletableFuture<ListBucketAnalyticsConfigurationsResponse> listBucketAnalyticsConfigurationsResponseCompletableFuture = CompletableFuture.completedFuture(
                ListBucketAnalyticsConfigurationsResponse.builder().build());
        when(mockS3AsyncClient.listBucketAnalyticsConfigurations(any(Consumer.class)))
                .thenReturn(listBucketAnalyticsConfigurationsResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryListBucketAnalyticsConfigurations1();

        // Verify the results
    }

    @Test
    void testTryListBucketAnalyticsConfigurations1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.listBucketAnalyticsConfigurations(...).
        final CompletableFuture<ListBucketAnalyticsConfigurationsResponse> listBucketAnalyticsConfigurationsResponseCompletableFuture = new CompletableFuture<>();
        listBucketAnalyticsConfigurationsResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.listBucketAnalyticsConfigurations(any(Consumer.class)))
                .thenReturn(listBucketAnalyticsConfigurationsResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryListBucketAnalyticsConfigurations1();

        // Verify the results
    }

    @Test
    void testTryListBucketInventoryConfigurations() {
        // Setup
        // Configure S3AsyncClient.listBucketInventoryConfigurations(...).
        final CompletableFuture<ListBucketInventoryConfigurationsResponse> listBucketInventoryConfigurationsResponseCompletableFuture = CompletableFuture.completedFuture(
                ListBucketInventoryConfigurationsResponse.builder().build());
        when(mockS3AsyncClient.listBucketInventoryConfigurations(ListBucketInventoryConfigurationsRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(listBucketInventoryConfigurationsResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryListBucketInventoryConfigurations();

        // Verify the results
    }

    @Test
    void testTryListBucketInventoryConfigurations_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.listBucketInventoryConfigurations(...).
        final CompletableFuture<ListBucketInventoryConfigurationsResponse> listBucketInventoryConfigurationsResponseCompletableFuture = new CompletableFuture<>();
        listBucketInventoryConfigurationsResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.listBucketInventoryConfigurations(ListBucketInventoryConfigurationsRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(listBucketInventoryConfigurationsResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryListBucketInventoryConfigurations();

        // Verify the results
    }

    @Test
    void testTryListBucketInventoryConfigurations1() {
        // Setup
        // Configure S3AsyncClient.listBucketInventoryConfigurations(...).
        final CompletableFuture<ListBucketInventoryConfigurationsResponse> listBucketInventoryConfigurationsResponseCompletableFuture = CompletableFuture.completedFuture(
                ListBucketInventoryConfigurationsResponse.builder().build());
        when(mockS3AsyncClient.listBucketInventoryConfigurations(any(Consumer.class)))
                .thenReturn(listBucketInventoryConfigurationsResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryListBucketInventoryConfigurations1();

        // Verify the results
    }

    @Test
    void testTryListBucketInventoryConfigurations1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.listBucketInventoryConfigurations(...).
        final CompletableFuture<ListBucketInventoryConfigurationsResponse> listBucketInventoryConfigurationsResponseCompletableFuture = new CompletableFuture<>();
        listBucketInventoryConfigurationsResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.listBucketInventoryConfigurations(any(Consumer.class)))
                .thenReturn(listBucketInventoryConfigurationsResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryListBucketInventoryConfigurations1();

        // Verify the results
    }

    @Test
    void testTryListBucketMetricsConfigurations() {
        // Setup
        // Configure S3AsyncClient.listBucketMetricsConfigurations(...).
        final CompletableFuture<ListBucketMetricsConfigurationsResponse> listBucketMetricsConfigurationsResponseCompletableFuture = CompletableFuture.completedFuture(
                ListBucketMetricsConfigurationsResponse.builder().build());
        when(mockS3AsyncClient.listBucketMetricsConfigurations(ListBucketMetricsConfigurationsRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(listBucketMetricsConfigurationsResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryListBucketMetricsConfigurations();

        // Verify the results
    }

    @Test
    void testTryListBucketMetricsConfigurations_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.listBucketMetricsConfigurations(...).
        final CompletableFuture<ListBucketMetricsConfigurationsResponse> listBucketMetricsConfigurationsResponseCompletableFuture = new CompletableFuture<>();
        listBucketMetricsConfigurationsResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.listBucketMetricsConfigurations(ListBucketMetricsConfigurationsRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(listBucketMetricsConfigurationsResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryListBucketMetricsConfigurations();

        // Verify the results
    }

    @Test
    void testTryListBucketMetricsConfigurations1() {
        // Setup
        // Configure S3AsyncClient.listBucketMetricsConfigurations(...).
        final CompletableFuture<ListBucketMetricsConfigurationsResponse> listBucketMetricsConfigurationsResponseCompletableFuture = CompletableFuture.completedFuture(
                ListBucketMetricsConfigurationsResponse.builder().build());
        when(mockS3AsyncClient.listBucketMetricsConfigurations(any(Consumer.class)))
                .thenReturn(listBucketMetricsConfigurationsResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryListBucketMetricsConfigurations1();

        // Verify the results
    }

    @Test
    void testTryListBucketMetricsConfigurations1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.listBucketMetricsConfigurations(...).
        final CompletableFuture<ListBucketMetricsConfigurationsResponse> listBucketMetricsConfigurationsResponseCompletableFuture = new CompletableFuture<>();
        listBucketMetricsConfigurationsResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.listBucketMetricsConfigurations(any(Consumer.class)))
                .thenReturn(listBucketMetricsConfigurationsResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryListBucketMetricsConfigurations1();

        // Verify the results
    }

    @Test
    void testTryListBuckets() {
        // Setup
        // Configure S3AsyncClient.listBuckets(...).
        final CompletableFuture<ListBucketsResponse> listBucketsResponseCompletableFuture = CompletableFuture.completedFuture(
                ListBucketsResponse.builder().build());
        when(mockS3AsyncClient.listBuckets(ListBucketsRequest.builder().build()))
                .thenReturn(listBucketsResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryListBuckets();

        // Verify the results
    }

    @Test
    void testTryListBuckets_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.listBuckets(...).
        final CompletableFuture<ListBucketsResponse> listBucketsResponseCompletableFuture = new CompletableFuture<>();
        listBucketsResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.listBuckets(ListBucketsRequest.builder().build()))
                .thenReturn(listBucketsResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryListBuckets();

        // Verify the results
    }

    @Test
    void testTryListBuckets1() {
        // Setup
        // Configure S3AsyncClient.listBuckets(...).
        final CompletableFuture<ListBucketsResponse> listBucketsResponseCompletableFuture = CompletableFuture.completedFuture(
                ListBucketsResponse.builder().build());
        when(mockS3AsyncClient.listBuckets(any(Consumer.class))).thenReturn(listBucketsResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryListBuckets1();

        // Verify the results
    }

    @Test
    void testTryListBuckets1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.listBuckets(...).
        final CompletableFuture<ListBucketsResponse> listBucketsResponseCompletableFuture = new CompletableFuture<>();
        listBucketsResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.listBuckets(any(Consumer.class))).thenReturn(listBucketsResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryListBuckets1();

        // Verify the results
    }

    @Test
    void testTryListBuckets2() {
        // Setup
        // Configure S3AsyncClient.listBuckets(...).
        final CompletableFuture<ListBucketsResponse> listBucketsResponseCompletableFuture = CompletableFuture.completedFuture(
                ListBucketsResponse.builder().build());
        when(mockS3AsyncClient.listBuckets()).thenReturn(listBucketsResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryListBuckets2();

        // Verify the results
    }

    @Test
    void testTryListBuckets2_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.listBuckets(...).
        final CompletableFuture<ListBucketsResponse> listBucketsResponseCompletableFuture = new CompletableFuture<>();
        listBucketsResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.listBuckets()).thenReturn(listBucketsResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryListBuckets2();

        // Verify the results
    }

    @Test
    void testTryListMultipartUploads() {
        // Setup
        // Configure S3AsyncClient.listMultipartUploads(...).
        final CompletableFuture<ListMultipartUploadsResponse> listMultipartUploadsResponseCompletableFuture = CompletableFuture.completedFuture(
                ListMultipartUploadsResponse.builder().build());
        when(mockS3AsyncClient.listMultipartUploads(ListMultipartUploadsRequest.builder()
                .bucket("bucketName")
                .prefix("prefix")
                .build())).thenReturn(listMultipartUploadsResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryListMultipartUploads();

        // Verify the results
    }

    @Test
    void testTryListMultipartUploads_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.listMultipartUploads(...).
        final CompletableFuture<ListMultipartUploadsResponse> listMultipartUploadsResponseCompletableFuture = new CompletableFuture<>();
        listMultipartUploadsResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.listMultipartUploads(ListMultipartUploadsRequest.builder()
                .bucket("bucketName")
                .prefix("prefix")
                .build())).thenReturn(listMultipartUploadsResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryListMultipartUploads();

        // Verify the results
    }

    @Test
    void testTryListMultipartUploads1() {
        // Setup
        // Configure S3AsyncClient.listMultipartUploads(...).
        final CompletableFuture<ListMultipartUploadsResponse> listMultipartUploadsResponseCompletableFuture = CompletableFuture.completedFuture(
                ListMultipartUploadsResponse.builder().build());
        when(mockS3AsyncClient.listMultipartUploads(any(Consumer.class)))
                .thenReturn(listMultipartUploadsResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryListMultipartUploads1();

        // Verify the results
    }

    @Test
    void testTryListMultipartUploads1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.listMultipartUploads(...).
        final CompletableFuture<ListMultipartUploadsResponse> listMultipartUploadsResponseCompletableFuture = new CompletableFuture<>();
        listMultipartUploadsResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.listMultipartUploads(any(Consumer.class)))
                .thenReturn(listMultipartUploadsResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryListMultipartUploads1();

        // Verify the results
    }

    @Test
    void testTryListMultipartUploadsPaginator() {
        // Setup
        // Configure S3AsyncClient.listMultipartUploadsPaginator(...).
        final ListMultipartUploadsPublisher mockListMultipartUploadsPublisher = mock(
                ListMultipartUploadsPublisher.class);
        when(mockS3AsyncClient.listMultipartUploadsPaginator(ListMultipartUploadsRequest.builder()
                .bucket("bucketName")
                .prefix("prefix")
                .build())).thenReturn(mockListMultipartUploadsPublisher);

        // Run the test
        myClassUnderTest.tryListMultipartUploadsPaginator();

        // Verify the results
    }

    @Test
    void testTryListMultipartUploadsPaginator1() {
        // Setup
        // Configure S3AsyncClient.listMultipartUploadsPaginator(...).
        final ListMultipartUploadsPublisher mockListMultipartUploadsPublisher = mock(
                ListMultipartUploadsPublisher.class);
        when(mockS3AsyncClient.listMultipartUploadsPaginator(any(Consumer.class)))
                .thenReturn(mockListMultipartUploadsPublisher);

        // Run the test
        myClassUnderTest.tryListMultipartUploadsPaginator1();

        // Verify the results
    }

    @Test
    void testTryListObjectVersions() {
        // Setup
        // Configure S3AsyncClient.listObjectVersions(...).
        final CompletableFuture<ListObjectVersionsResponse> listObjectVersionsResponseCompletableFuture = CompletableFuture.completedFuture(
                ListObjectVersionsResponse.builder()
                        .prefix("prefix")
                        .versions(ObjectVersion.builder().key("key").build())
                        .isTruncated(false)
                        .build());
        when(mockS3AsyncClient.listObjectVersions(ListObjectVersionsRequest.builder()
                .bucket("bucketName")
                .prefix("prefix")
                .build())).thenReturn(listObjectVersionsResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryListObjectVersions();

        // Verify the results
    }

    @Test
    void testTryListObjectVersions_S3AsyncClientReturnsNoItems() {
        // Setup
        // Configure S3AsyncClient.listObjectVersions(...).
        final CompletableFuture<ListObjectVersionsResponse> listObjectVersionsResponseCompletableFuture = CompletableFuture.completedFuture(
                ListObjectVersionsResponse.builder()
                        .prefix("prefix")
                        .isTruncated(false)
                        .build());
        when(mockS3AsyncClient.listObjectVersions(ListObjectVersionsRequest.builder()
                .bucket("bucketName")
                .prefix("prefix")
                .build())).thenReturn(listObjectVersionsResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryListObjectVersions();

        // Verify the results
    }

    @Test
    void testTryListObjectVersions_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.listObjectVersions(...).
        final CompletableFuture<ListObjectVersionsResponse> listObjectVersionsResponseCompletableFuture = new CompletableFuture<>();
        listObjectVersionsResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.listObjectVersions(ListObjectVersionsRequest.builder()
                .bucket("bucketName")
                .prefix("prefix")
                .build())).thenReturn(listObjectVersionsResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryListObjectVersions();

        // Verify the results
    }

    @Test
    void testTryListObjectVersions1() {
        // Setup
        // Configure S3AsyncClient.listObjectVersions(...).
        final CompletableFuture<ListObjectVersionsResponse> listObjectVersionsResponseCompletableFuture = CompletableFuture.completedFuture(
                ListObjectVersionsResponse.builder()
                        .prefix("prefix")
                        .versions(ObjectVersion.builder().key("key").build())
                        .isTruncated(false)
                        .build());
        when(mockS3AsyncClient.listObjectVersions(any(Consumer.class)))
                .thenReturn(listObjectVersionsResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryListObjectVersions1();

        // Verify the results
    }

    @Test
    void testTryListObjectVersions1_S3AsyncClientReturnsNoItems() {
        // Setup
        // Configure S3AsyncClient.listObjectVersions(...).
        final CompletableFuture<ListObjectVersionsResponse> listObjectVersionsResponseCompletableFuture = CompletableFuture.completedFuture(
                ListObjectVersionsResponse.builder()
                        .prefix("prefix")
                        .isTruncated(false)
                        .build());
        when(mockS3AsyncClient.listObjectVersions(any(Consumer.class)))
                .thenReturn(listObjectVersionsResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryListObjectVersions1();

        // Verify the results
    }

    @Test
    void testTryListObjectVersions1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.listObjectVersions(...).
        final CompletableFuture<ListObjectVersionsResponse> listObjectVersionsResponseCompletableFuture = new CompletableFuture<>();
        listObjectVersionsResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.listObjectVersions(any(Consumer.class)))
                .thenReturn(listObjectVersionsResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryListObjectVersions1();

        // Verify the results
    }

    @Test
    void testTryListObjectVersionsPaginator() {
        // Setup
        // Configure S3AsyncClient.listObjectVersionsPaginator(...).
        final ListObjectVersionsPublisher mockListObjectVersionsPublisher = mock(ListObjectVersionsPublisher.class);
        when(mockS3AsyncClient.listObjectVersionsPaginator(ListObjectVersionsRequest.builder()
                .bucket("bucketName")
                .prefix("prefix")
                .build())).thenReturn(mockListObjectVersionsPublisher);

        // Run the test
        myClassUnderTest.tryListObjectVersionsPaginator();

        // Verify the results
    }

    @Test
    void testTryListObjectVersionsPaginator1() {
        // Setup
        // Configure S3AsyncClient.listObjectVersionsPaginator(...).
        final ListObjectVersionsPublisher mockListObjectVersionsPublisher = mock(ListObjectVersionsPublisher.class);
        when(mockS3AsyncClient.listObjectVersionsPaginator(any(Consumer.class)))
                .thenReturn(mockListObjectVersionsPublisher);

        // Run the test
        myClassUnderTest.tryListObjectVersionsPaginator1();

        // Verify the results
    }

    @Test
    void testTryListObjects() {
        // Setup
        // Configure S3AsyncClient.listObjects(...).
        final CompletableFuture<ListObjectsResponse> listObjectsResponseCompletableFuture = CompletableFuture.completedFuture(
                ListObjectsResponse.builder()
                        .prefix("prefix")
                        .contents(S3Object.builder().key("key").build())
                        .isTruncated(false)
                        .build());
        when(mockS3AsyncClient.listObjects(ListObjectsRequest.builder()
                .bucket("bucketName")
                .prefix("prefix")
                .build())).thenReturn(listObjectsResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryListObjects();

        // Verify the results
    }

    @Test
    void testTryListObjects_S3AsyncClientReturnsNoItems() {
        // Setup
        // Configure S3AsyncClient.listObjects(...).
        final CompletableFuture<ListObjectsResponse> listObjectsResponseCompletableFuture = CompletableFuture.completedFuture(
                ListObjectsResponse.builder()
                        .prefix("prefix")
                        .isTruncated(false)
                        .build());
        when(mockS3AsyncClient.listObjects(ListObjectsRequest.builder()
                .bucket("bucketName")
                .prefix("prefix")
                .build())).thenReturn(listObjectsResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryListObjects();

        // Verify the results
    }

    @Test
    void testTryListObjects_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.listObjects(...).
        final CompletableFuture<ListObjectsResponse> listObjectsResponseCompletableFuture = new CompletableFuture<>();
        listObjectsResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.listObjects(ListObjectsRequest.builder()
                .bucket("bucketName")
                .prefix("prefix")
                .build())).thenReturn(listObjectsResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryListObjects();

        // Verify the results
    }

    @Test
    void testTryListObjects1() {
        // Setup
        // Configure S3AsyncClient.listObjects(...).
        final CompletableFuture<ListObjectsResponse> listObjectsResponseCompletableFuture = CompletableFuture.completedFuture(
                ListObjectsResponse.builder()
                        .prefix("prefix")
                        .contents(S3Object.builder().key("key").build())
                        .isTruncated(false)
                        .build());
        when(mockS3AsyncClient.listObjects(any(Consumer.class))).thenReturn(listObjectsResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryListObjects1();

        // Verify the results
    }

    @Test
    void testTryListObjects1_S3AsyncClientReturnsNoItems() {
        // Setup
        // Configure S3AsyncClient.listObjects(...).
        final CompletableFuture<ListObjectsResponse> listObjectsResponseCompletableFuture = CompletableFuture.completedFuture(
                ListObjectsResponse.builder()
                        .prefix("prefix")
                        .isTruncated(false)
                        .build());
        when(mockS3AsyncClient.listObjects(any(Consumer.class))).thenReturn(listObjectsResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryListObjects1();

        // Verify the results
    }

    @Test
    void testTryListObjects1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.listObjects(...).
        final CompletableFuture<ListObjectsResponse> listObjectsResponseCompletableFuture = new CompletableFuture<>();
        listObjectsResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.listObjects(any(Consumer.class))).thenReturn(listObjectsResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryListObjects1();

        // Verify the results
    }

    @Test
    void testTryListObjectsV2() {
        // Setup
        // Configure S3AsyncClient.listObjectsV2(...).
        final CompletableFuture<ListObjectsV2Response> listObjectsV2ResponseCompletableFuture = CompletableFuture.completedFuture(
                ListObjectsV2Response.builder()
                        .prefix("prefix")
                        .contents(S3Object.builder()
                                .key("key")
                                .build())
                        .isTruncated(false)
                        .build());
        when(mockS3AsyncClient.listObjectsV2(ListObjectsV2Request.builder()
                .bucket("bucketName")
                .prefix("prefix")
                .build())).thenReturn(listObjectsV2ResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryListObjectsV2();

        // Verify the results
    }

    @Test
    void testTryListObjectsV2_S3AsyncClientReturnsNoItems() {
        // Setup
        // Configure S3AsyncClient.listObjectsV2(...).
        final CompletableFuture<ListObjectsV2Response> listObjectsV2ResponseCompletableFuture = CompletableFuture.completedFuture(
                ListObjectsV2Response.builder()
                        .prefix("prefix")
                        .isTruncated(false)
                        .build());
        when(mockS3AsyncClient.listObjectsV2(ListObjectsV2Request.builder()
                .bucket("bucketName")
                .prefix("prefix")
                .build())).thenReturn(listObjectsV2ResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryListObjectsV2();

        // Verify the results
    }

    @Test
    void testTryListObjectsV2_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.listObjectsV2(...).
        final CompletableFuture<ListObjectsV2Response> listObjectsV2ResponseCompletableFuture = new CompletableFuture<>();
        listObjectsV2ResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.listObjectsV2(ListObjectsV2Request.builder()
                .bucket("bucketName")
                .prefix("prefix")
                .build())).thenReturn(listObjectsV2ResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryListObjectsV2();

        // Verify the results
    }

    @Test
    void testTryListObjectsV21() {
        // Setup
        // Configure S3AsyncClient.listObjectsV2(...).
        final CompletableFuture<ListObjectsV2Response> listObjectsV2ResponseCompletableFuture = CompletableFuture.completedFuture(
                ListObjectsV2Response.builder()
                        .prefix("prefix")
                        .contents(S3Object.builder()
                                .key("key")
                                .build())
                        .isTruncated(false)
                        .build());
        when(mockS3AsyncClient.listObjectsV2(any(Consumer.class))).thenReturn(listObjectsV2ResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryListObjectsV21();

        // Verify the results
    }

    @Test
    void testTryListObjectsV21_S3AsyncClientReturnsNoItems() {
        // Setup
        // Configure S3AsyncClient.listObjectsV2(...).
        final CompletableFuture<ListObjectsV2Response> listObjectsV2ResponseCompletableFuture = CompletableFuture.completedFuture(
                ListObjectsV2Response.builder()
                        .prefix("prefix")
                        .isTruncated(false)
                        .build());
        when(mockS3AsyncClient.listObjectsV2(any(Consumer.class))).thenReturn(listObjectsV2ResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryListObjectsV21();

        // Verify the results
    }

    @Test
    void testTryListObjectsV21_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.listObjectsV2(...).
        final CompletableFuture<ListObjectsV2Response> listObjectsV2ResponseCompletableFuture = new CompletableFuture<>();
        listObjectsV2ResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.listObjectsV2(any(Consumer.class))).thenReturn(listObjectsV2ResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryListObjectsV21();

        // Verify the results
    }

    @Test
    void testTryListObjectsV2Paginator() {
        // Setup
        // Configure S3AsyncClient.listObjectsV2Paginator(...).
        final ListObjectsV2Publisher mockListObjectsV2Publisher = mock(ListObjectsV2Publisher.class);
        when(mockS3AsyncClient.listObjectsV2Paginator(ListObjectsV2Request.builder()
                .bucket("bucketName")
                .prefix("prefix")
                .build())).thenReturn(mockListObjectsV2Publisher);

        // Run the test
        myClassUnderTest.tryListObjectsV2Paginator();

        // Verify the results
    }

    @Test
    void testTryListObjectsV2Paginator1() {
        // Setup
        // Configure S3AsyncClient.listObjectsV2Paginator(...).
        final ListObjectsV2Publisher mockListObjectsV2Publisher = mock(ListObjectsV2Publisher.class);
        when(mockS3AsyncClient.listObjectsV2Paginator(any(Consumer.class))).thenReturn(mockListObjectsV2Publisher);

        // Run the test
        myClassUnderTest.tryListObjectsV2Paginator1();

        // Verify the results
    }

    @Test
    void testTryListParts() {
        // Setup
        // Configure S3AsyncClient.listParts(...).
        final CompletableFuture<ListPartsResponse> listPartsResponseCompletableFuture = CompletableFuture.completedFuture(
                ListPartsResponse.builder()
                        .bucket("bucketName")
                        .key("key")
                        .parts(Part.builder().partNumber(1).build())
                        .build());
        when(mockS3AsyncClient.listParts(ListPartsRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build())).thenReturn(listPartsResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryListParts();

        // Verify the results
    }

    @Test
    void testTryListParts_S3AsyncClientReturnsNoItems() {
        // Setup
        // Configure S3AsyncClient.listParts(...).
        final CompletableFuture<ListPartsResponse> listPartsResponseCompletableFuture = CompletableFuture.completedFuture(
                ListPartsResponse.builder().bucket("bucketName").key("key").build());
        when(mockS3AsyncClient.listParts(ListPartsRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build())).thenReturn(listPartsResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryListParts();

        // Verify the results
    }

    @Test
    void testTryListParts_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.listParts(...).
        final CompletableFuture<ListPartsResponse> listPartsResponseCompletableFuture = new CompletableFuture<>();
        listPartsResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.listParts(ListPartsRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build())).thenReturn(listPartsResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryListParts();

        // Verify the results
    }

    @Test
    void testTryListParts1() {
        // Setup
        // Configure S3AsyncClient.listParts(...).
        final CompletableFuture<ListPartsResponse> listPartsResponseCompletableFuture = CompletableFuture.completedFuture(
                ListPartsResponse.builder()
                        .bucket("bucketName")
                        .key("key")
                        .parts(Part.builder().partNumber(1).build())
                        .build());
        when(mockS3AsyncClient.listParts(any(Consumer.class))).thenReturn(listPartsResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryListParts1();

        // Verify the results
    }

    @Test
    void testTryListParts1_S3AsyncClientReturnsNoItems() {
        // Setup
        // Configure S3AsyncClient.listParts(...).
        final CompletableFuture<ListPartsResponse> listPartsResponseCompletableFuture = CompletableFuture.completedFuture(
                ListPartsResponse.builder().bucket("bucketName").key("key").build());
        when(mockS3AsyncClient.listParts(any(Consumer.class))).thenReturn(listPartsResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryListParts1();

        // Verify the results
    }

    @Test
    void testTryListParts1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.listParts(...).
        final CompletableFuture<ListPartsResponse> listPartsResponseCompletableFuture = new CompletableFuture<>();
        listPartsResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.listParts(any(Consumer.class))).thenReturn(listPartsResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryListParts1();

        // Verify the results
    }

    @Test
    void testTryListPartsPaginator() {
        // Setup
        // Configure S3AsyncClient.listPartsPaginator(...).
        final ListPartsPublisher mockListPartsPublisher = mock(ListPartsPublisher.class);
        when(mockS3AsyncClient.listPartsPaginator(ListPartsRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build())).thenReturn(mockListPartsPublisher);

        // Run the test
        myClassUnderTest.tryListPartsPaginator();

        // Verify the results
    }

    @Test
    void testTryListPartsPaginator1() {
        // Setup
        // Configure S3AsyncClient.listPartsPaginator(...).
        final ListPartsPublisher mockListPartsPublisher = mock(ListPartsPublisher.class);
        when(mockS3AsyncClient.listPartsPaginator(any(Consumer.class))).thenReturn(mockListPartsPublisher);

        // Run the test
        myClassUnderTest.tryListPartsPaginator1();

        // Verify the results
    }

    @Test
    void testTryPutBucketAccelerateConfiguration() {
        // Setup
        // Configure S3AsyncClient.putBucketAccelerateConfiguration(...).
        final CompletableFuture<PutBucketAccelerateConfigurationResponse> putBucketAccelerateConfigurationResponseCompletableFuture = CompletableFuture.completedFuture(
                PutBucketAccelerateConfigurationResponse.builder().build());
        when(mockS3AsyncClient.putBucketAccelerateConfiguration(PutBucketAccelerateConfigurationRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(putBucketAccelerateConfigurationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutBucketAccelerateConfiguration();

        // Verify the results
    }

    @Test
    void testTryPutBucketAccelerateConfiguration_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.putBucketAccelerateConfiguration(...).
        final CompletableFuture<PutBucketAccelerateConfigurationResponse> putBucketAccelerateConfigurationResponseCompletableFuture = new CompletableFuture<>();
        putBucketAccelerateConfigurationResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.putBucketAccelerateConfiguration(PutBucketAccelerateConfigurationRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(putBucketAccelerateConfigurationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutBucketAccelerateConfiguration();

        // Verify the results
    }

    @Test
    void testTryPutBucketAccelerateConfiguration1() {
        // Setup
        // Configure S3AsyncClient.putBucketAccelerateConfiguration(...).
        final CompletableFuture<PutBucketAccelerateConfigurationResponse> putBucketAccelerateConfigurationResponseCompletableFuture = CompletableFuture.completedFuture(
                PutBucketAccelerateConfigurationResponse.builder().build());
        when(mockS3AsyncClient.putBucketAccelerateConfiguration(any(Consumer.class)))
                .thenReturn(putBucketAccelerateConfigurationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutBucketAccelerateConfiguration1();

        // Verify the results
    }

    @Test
    void testTryPutBucketAccelerateConfiguration1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.putBucketAccelerateConfiguration(...).
        final CompletableFuture<PutBucketAccelerateConfigurationResponse> putBucketAccelerateConfigurationResponseCompletableFuture = new CompletableFuture<>();
        putBucketAccelerateConfigurationResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.putBucketAccelerateConfiguration(any(Consumer.class)))
                .thenReturn(putBucketAccelerateConfigurationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutBucketAccelerateConfiguration1();

        // Verify the results
    }

    @Test
    void testTryPutBucketAcl() {
        // Setup
        // Configure S3AsyncClient.putBucketAcl(...).
        final CompletableFuture<PutBucketAclResponse> putBucketAclResponseCompletableFuture = CompletableFuture.completedFuture(
                PutBucketAclResponse.builder().build());
        when(mockS3AsyncClient.putBucketAcl(PutBucketAclRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(putBucketAclResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutBucketAcl();

        // Verify the results
    }

    @Test
    void testTryPutBucketAcl_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.putBucketAcl(...).
        final CompletableFuture<PutBucketAclResponse> putBucketAclResponseCompletableFuture = new CompletableFuture<>();
        putBucketAclResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.putBucketAcl(PutBucketAclRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(putBucketAclResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutBucketAcl();

        // Verify the results
    }

    @Test
    void testTryPutBucketAcl1() {
        // Setup
        // Configure S3AsyncClient.putBucketAcl(...).
        final CompletableFuture<PutBucketAclResponse> putBucketAclResponseCompletableFuture = CompletableFuture.completedFuture(
                PutBucketAclResponse.builder().build());
        when(mockS3AsyncClient.putBucketAcl(any(Consumer.class))).thenReturn(putBucketAclResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutBucketAcl1();

        // Verify the results
    }

    @Test
    void testTryPutBucketAcl1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.putBucketAcl(...).
        final CompletableFuture<PutBucketAclResponse> putBucketAclResponseCompletableFuture = new CompletableFuture<>();
        putBucketAclResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.putBucketAcl(any(Consumer.class))).thenReturn(putBucketAclResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutBucketAcl1();

        // Verify the results
    }

    @Test
    void testTryPutBucketAnalyticsConfiguration() {
        // Setup
        // Configure S3AsyncClient.putBucketAnalyticsConfiguration(...).
        final CompletableFuture<PutBucketAnalyticsConfigurationResponse> putBucketAnalyticsConfigurationResponseCompletableFuture = CompletableFuture.completedFuture(
                PutBucketAnalyticsConfigurationResponse.builder().build());
        when(mockS3AsyncClient.putBucketAnalyticsConfiguration(PutBucketAnalyticsConfigurationRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(putBucketAnalyticsConfigurationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutBucketAnalyticsConfiguration();

        // Verify the results
    }

    @Test
    void testTryPutBucketAnalyticsConfiguration_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.putBucketAnalyticsConfiguration(...).
        final CompletableFuture<PutBucketAnalyticsConfigurationResponse> putBucketAnalyticsConfigurationResponseCompletableFuture = new CompletableFuture<>();
        putBucketAnalyticsConfigurationResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.putBucketAnalyticsConfiguration(PutBucketAnalyticsConfigurationRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(putBucketAnalyticsConfigurationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutBucketAnalyticsConfiguration();

        // Verify the results
    }

    @Test
    void testTryPutBucketAnalyticsConfiguration1() {
        // Setup
        // Configure S3AsyncClient.putBucketAnalyticsConfiguration(...).
        final CompletableFuture<PutBucketAnalyticsConfigurationResponse> putBucketAnalyticsConfigurationResponseCompletableFuture = CompletableFuture.completedFuture(
                PutBucketAnalyticsConfigurationResponse.builder().build());
        when(mockS3AsyncClient.putBucketAnalyticsConfiguration(any(Consumer.class)))
                .thenReturn(putBucketAnalyticsConfigurationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutBucketAnalyticsConfiguration1();

        // Verify the results
    }

    @Test
    void testTryPutBucketAnalyticsConfiguration1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.putBucketAnalyticsConfiguration(...).
        final CompletableFuture<PutBucketAnalyticsConfigurationResponse> putBucketAnalyticsConfigurationResponseCompletableFuture = new CompletableFuture<>();
        putBucketAnalyticsConfigurationResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.putBucketAnalyticsConfiguration(any(Consumer.class)))
                .thenReturn(putBucketAnalyticsConfigurationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutBucketAnalyticsConfiguration1();

        // Verify the results
    }

    @Test
    void testTryPutBucketCors() {
        // Setup
        // Configure S3AsyncClient.putBucketCors(...).
        final CompletableFuture<PutBucketCorsResponse> putBucketCorsResponseCompletableFuture = CompletableFuture.completedFuture(
                PutBucketCorsResponse.builder().build());
        when(mockS3AsyncClient.putBucketCors(PutBucketCorsRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(putBucketCorsResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutBucketCors();

        // Verify the results
    }

    @Test
    void testTryPutBucketCors_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.putBucketCors(...).
        final CompletableFuture<PutBucketCorsResponse> putBucketCorsResponseCompletableFuture = new CompletableFuture<>();
        putBucketCorsResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.putBucketCors(PutBucketCorsRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(putBucketCorsResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutBucketCors();

        // Verify the results
    }

    @Test
    void testTryPutBucketCors1() {
        // Setup
        // Configure S3AsyncClient.putBucketCors(...).
        final CompletableFuture<PutBucketCorsResponse> putBucketCorsResponseCompletableFuture = CompletableFuture.completedFuture(
                PutBucketCorsResponse.builder().build());
        when(mockS3AsyncClient.putBucketCors(any(Consumer.class))).thenReturn(putBucketCorsResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutBucketCors1();

        // Verify the results
    }

    @Test
    void testTryPutBucketCors1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.putBucketCors(...).
        final CompletableFuture<PutBucketCorsResponse> putBucketCorsResponseCompletableFuture = new CompletableFuture<>();
        putBucketCorsResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.putBucketCors(any(Consumer.class))).thenReturn(putBucketCorsResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutBucketCors1();

        // Verify the results
    }

    @Test
    void testTryPutBucketEncryption() {
        // Setup
        // Configure S3AsyncClient.putBucketEncryption(...).
        final CompletableFuture<PutBucketEncryptionResponse> putBucketEncryptionResponseCompletableFuture = CompletableFuture.completedFuture(
                PutBucketEncryptionResponse.builder().build());
        when(mockS3AsyncClient.putBucketEncryption(PutBucketEncryptionRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(putBucketEncryptionResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutBucketEncryption();

        // Verify the results
    }

    @Test
    void testTryPutBucketEncryption_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.putBucketEncryption(...).
        final CompletableFuture<PutBucketEncryptionResponse> putBucketEncryptionResponseCompletableFuture = new CompletableFuture<>();
        putBucketEncryptionResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.putBucketEncryption(PutBucketEncryptionRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(putBucketEncryptionResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutBucketEncryption();

        // Verify the results
    }

    @Test
    void testTryPutBucketEncryption1() {
        // Setup
        // Configure S3AsyncClient.putBucketEncryption(...).
        final CompletableFuture<PutBucketEncryptionResponse> putBucketEncryptionResponseCompletableFuture = CompletableFuture.completedFuture(
                PutBucketEncryptionResponse.builder().build());
        when(mockS3AsyncClient.putBucketEncryption(any(Consumer.class)))
                .thenReturn(putBucketEncryptionResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutBucketEncryption1();

        // Verify the results
    }

    @Test
    void testTryPutBucketEncryption1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.putBucketEncryption(...).
        final CompletableFuture<PutBucketEncryptionResponse> putBucketEncryptionResponseCompletableFuture = new CompletableFuture<>();
        putBucketEncryptionResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.putBucketEncryption(any(Consumer.class)))
                .thenReturn(putBucketEncryptionResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutBucketEncryption1();

        // Verify the results
    }

    @Test
    void testTryPutBucketInventoryConfiguration() {
        // Setup
        // Configure S3AsyncClient.putBucketInventoryConfiguration(...).
        final CompletableFuture<PutBucketInventoryConfigurationResponse> putBucketInventoryConfigurationResponseCompletableFuture = CompletableFuture.completedFuture(
                PutBucketInventoryConfigurationResponse.builder().build());
        when(mockS3AsyncClient.putBucketInventoryConfiguration(PutBucketInventoryConfigurationRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(putBucketInventoryConfigurationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutBucketInventoryConfiguration();

        // Verify the results
    }

    @Test
    void testTryPutBucketInventoryConfiguration_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.putBucketInventoryConfiguration(...).
        final CompletableFuture<PutBucketInventoryConfigurationResponse> putBucketInventoryConfigurationResponseCompletableFuture = new CompletableFuture<>();
        putBucketInventoryConfigurationResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.putBucketInventoryConfiguration(PutBucketInventoryConfigurationRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(putBucketInventoryConfigurationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutBucketInventoryConfiguration();

        // Verify the results
    }

    @Test
    void testTryPutBucketInventoryConfiguration1() {
        // Setup
        // Configure S3AsyncClient.putBucketInventoryConfiguration(...).
        final CompletableFuture<PutBucketInventoryConfigurationResponse> putBucketInventoryConfigurationResponseCompletableFuture = CompletableFuture.completedFuture(
                PutBucketInventoryConfigurationResponse.builder().build());
        when(mockS3AsyncClient.putBucketInventoryConfiguration(any(Consumer.class)))
                .thenReturn(putBucketInventoryConfigurationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutBucketInventoryConfiguration1();

        // Verify the results
    }

    @Test
    void testTryPutBucketInventoryConfiguration1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.putBucketInventoryConfiguration(...).
        final CompletableFuture<PutBucketInventoryConfigurationResponse> putBucketInventoryConfigurationResponseCompletableFuture = new CompletableFuture<>();
        putBucketInventoryConfigurationResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.putBucketInventoryConfiguration(any(Consumer.class)))
                .thenReturn(putBucketInventoryConfigurationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutBucketInventoryConfiguration1();

        // Verify the results
    }

    @Test
    void testTryPutBucketLifecycleConfiguration() {
        // Setup
        // Configure S3AsyncClient.putBucketLifecycleConfiguration(...).
        final CompletableFuture<PutBucketLifecycleConfigurationResponse> putBucketLifecycleConfigurationResponseCompletableFuture = CompletableFuture.completedFuture(
                PutBucketLifecycleConfigurationResponse.builder().build());
        when(mockS3AsyncClient.putBucketLifecycleConfiguration(PutBucketLifecycleConfigurationRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(putBucketLifecycleConfigurationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutBucketLifecycleConfiguration();

        // Verify the results
    }

    @Test
    void testTryPutBucketLifecycleConfiguration_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.putBucketLifecycleConfiguration(...).
        final CompletableFuture<PutBucketLifecycleConfigurationResponse> putBucketLifecycleConfigurationResponseCompletableFuture = new CompletableFuture<>();
        putBucketLifecycleConfigurationResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.putBucketLifecycleConfiguration(PutBucketLifecycleConfigurationRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(putBucketLifecycleConfigurationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutBucketLifecycleConfiguration();

        // Verify the results
    }

    @Test
    void testTryPutBucketLifecycleConfiguration1() {
        // Setup
        // Configure S3AsyncClient.putBucketLifecycleConfiguration(...).
        final CompletableFuture<PutBucketLifecycleConfigurationResponse> putBucketLifecycleConfigurationResponseCompletableFuture = CompletableFuture.completedFuture(
                PutBucketLifecycleConfigurationResponse.builder().build());
        when(mockS3AsyncClient.putBucketLifecycleConfiguration(any(Consumer.class)))
                .thenReturn(putBucketLifecycleConfigurationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutBucketLifecycleConfiguration1();

        // Verify the results
    }

    @Test
    void testTryPutBucketLifecycleConfiguration1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.putBucketLifecycleConfiguration(...).
        final CompletableFuture<PutBucketLifecycleConfigurationResponse> putBucketLifecycleConfigurationResponseCompletableFuture = new CompletableFuture<>();
        putBucketLifecycleConfigurationResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.putBucketLifecycleConfiguration(any(Consumer.class)))
                .thenReturn(putBucketLifecycleConfigurationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutBucketLifecycleConfiguration1();

        // Verify the results
    }

    @Test
    void testTryPutBucketLogging() {
        // Setup
        // Configure S3AsyncClient.putBucketLogging(...).
        final CompletableFuture<PutBucketLoggingResponse> putBucketLoggingResponseCompletableFuture = CompletableFuture.completedFuture(
                PutBucketLoggingResponse.builder().build());
        when(mockS3AsyncClient.putBucketLogging(PutBucketLoggingRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(putBucketLoggingResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutBucketLogging();

        // Verify the results
    }

    @Test
    void testTryPutBucketLogging_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.putBucketLogging(...).
        final CompletableFuture<PutBucketLoggingResponse> putBucketLoggingResponseCompletableFuture = new CompletableFuture<>();
        putBucketLoggingResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.putBucketLogging(PutBucketLoggingRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(putBucketLoggingResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutBucketLogging();

        // Verify the results
    }

    @Test
    void testTryPutBucketLogging1() {
        // Setup
        // Configure S3AsyncClient.putBucketLogging(...).
        final CompletableFuture<PutBucketLoggingResponse> putBucketLoggingResponseCompletableFuture = CompletableFuture.completedFuture(
                PutBucketLoggingResponse.builder().build());
        when(mockS3AsyncClient.putBucketLogging(any(Consumer.class)))
                .thenReturn(putBucketLoggingResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutBucketLogging1();

        // Verify the results
    }

    @Test
    void testTryPutBucketLogging1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.putBucketLogging(...).
        final CompletableFuture<PutBucketLoggingResponse> putBucketLoggingResponseCompletableFuture = new CompletableFuture<>();
        putBucketLoggingResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.putBucketLogging(any(Consumer.class)))
                .thenReturn(putBucketLoggingResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutBucketLogging1();

        // Verify the results
    }

    @Test
    void testTryPutBucketMetricsConfiguration() {
        // Setup
        // Configure S3AsyncClient.putBucketMetricsConfiguration(...).
        final CompletableFuture<PutBucketMetricsConfigurationResponse> putBucketMetricsConfigurationResponseCompletableFuture = CompletableFuture.completedFuture(
                PutBucketMetricsConfigurationResponse.builder().build());
        when(mockS3AsyncClient.putBucketMetricsConfiguration(PutBucketMetricsConfigurationRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(putBucketMetricsConfigurationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutBucketMetricsConfiguration();

        // Verify the results
    }

    @Test
    void testTryPutBucketMetricsConfiguration_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.putBucketMetricsConfiguration(...).
        final CompletableFuture<PutBucketMetricsConfigurationResponse> putBucketMetricsConfigurationResponseCompletableFuture = new CompletableFuture<>();
        putBucketMetricsConfigurationResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.putBucketMetricsConfiguration(PutBucketMetricsConfigurationRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(putBucketMetricsConfigurationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutBucketMetricsConfiguration();

        // Verify the results
    }

    @Test
    void testTryPutBucketMetricsConfiguration1() {
        // Setup
        // Configure S3AsyncClient.putBucketMetricsConfiguration(...).
        final CompletableFuture<PutBucketMetricsConfigurationResponse> putBucketMetricsConfigurationResponseCompletableFuture = CompletableFuture.completedFuture(
                PutBucketMetricsConfigurationResponse.builder().build());
        when(mockS3AsyncClient.putBucketMetricsConfiguration(any(Consumer.class)))
                .thenReturn(putBucketMetricsConfigurationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutBucketMetricsConfiguration1();

        // Verify the results
    }

    @Test
    void testTryPutBucketMetricsConfiguration1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.putBucketMetricsConfiguration(...).
        final CompletableFuture<PutBucketMetricsConfigurationResponse> putBucketMetricsConfigurationResponseCompletableFuture = new CompletableFuture<>();
        putBucketMetricsConfigurationResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.putBucketMetricsConfiguration(any(Consumer.class)))
                .thenReturn(putBucketMetricsConfigurationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutBucketMetricsConfiguration1();

        // Verify the results
    }

    @Test
    void testTryPutBucketNotificationConfiguration() {
        // Setup
        // Configure S3AsyncClient.putBucketNotificationConfiguration(...).
        final CompletableFuture<PutBucketNotificationConfigurationResponse> putBucketNotificationConfigurationResponseCompletableFuture = CompletableFuture.completedFuture(
                PutBucketNotificationConfigurationResponse.builder().build());
        when(mockS3AsyncClient.putBucketNotificationConfiguration(PutBucketNotificationConfigurationRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(putBucketNotificationConfigurationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutBucketNotificationConfiguration();

        // Verify the results
    }

    @Test
    void testTryPutBucketNotificationConfiguration_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.putBucketNotificationConfiguration(...).
        final CompletableFuture<PutBucketNotificationConfigurationResponse> putBucketNotificationConfigurationResponseCompletableFuture = new CompletableFuture<>();
        putBucketNotificationConfigurationResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.putBucketNotificationConfiguration(PutBucketNotificationConfigurationRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(putBucketNotificationConfigurationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutBucketNotificationConfiguration();

        // Verify the results
    }

    @Test
    void testTryPutBucketNotificationConfiguration1() {
        // Setup
        // Configure S3AsyncClient.putBucketNotificationConfiguration(...).
        final CompletableFuture<PutBucketNotificationConfigurationResponse> putBucketNotificationConfigurationResponseCompletableFuture = CompletableFuture.completedFuture(
                PutBucketNotificationConfigurationResponse.builder().build());
        when(mockS3AsyncClient.putBucketNotificationConfiguration(any(Consumer.class)))
                .thenReturn(putBucketNotificationConfigurationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutBucketNotificationConfiguration1();

        // Verify the results
    }

    @Test
    void testTryPutBucketNotificationConfiguration1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.putBucketNotificationConfiguration(...).
        final CompletableFuture<PutBucketNotificationConfigurationResponse> putBucketNotificationConfigurationResponseCompletableFuture = new CompletableFuture<>();
        putBucketNotificationConfigurationResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.putBucketNotificationConfiguration(any(Consumer.class)))
                .thenReturn(putBucketNotificationConfigurationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutBucketNotificationConfiguration1();

        // Verify the results
    }

    @Test
    void testTryPutBucketPolicy() {
        // Setup
        // Configure S3AsyncClient.putBucketPolicy(...).
        final CompletableFuture<PutBucketPolicyResponse> putBucketPolicyResponseCompletableFuture = CompletableFuture.completedFuture(
                PutBucketPolicyResponse.builder().build());
        when(mockS3AsyncClient.putBucketPolicy(PutBucketPolicyRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(putBucketPolicyResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutBucketPolicy();

        // Verify the results
    }

    @Test
    void testTryPutBucketPolicy_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.putBucketPolicy(...).
        final CompletableFuture<PutBucketPolicyResponse> putBucketPolicyResponseCompletableFuture = new CompletableFuture<>();
        putBucketPolicyResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.putBucketPolicy(PutBucketPolicyRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(putBucketPolicyResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutBucketPolicy();

        // Verify the results
    }

    @Test
    void testTryPutBucketPolicy1() {
        // Setup
        // Configure S3AsyncClient.putBucketPolicy(...).
        final CompletableFuture<PutBucketPolicyResponse> putBucketPolicyResponseCompletableFuture = CompletableFuture.completedFuture(
                PutBucketPolicyResponse.builder().build());
        when(mockS3AsyncClient.putBucketPolicy(any(Consumer.class)))
                .thenReturn(putBucketPolicyResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutBucketPolicy1();

        // Verify the results
    }

    @Test
    void testTryPutBucketPolicy1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.putBucketPolicy(...).
        final CompletableFuture<PutBucketPolicyResponse> putBucketPolicyResponseCompletableFuture = new CompletableFuture<>();
        putBucketPolicyResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.putBucketPolicy(any(Consumer.class)))
                .thenReturn(putBucketPolicyResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutBucketPolicy1();

        // Verify the results
    }

    @Test
    void testTryPutBucketReplication() {
        // Setup
        // Configure S3AsyncClient.putBucketReplication(...).
        final CompletableFuture<PutBucketReplicationResponse> putBucketReplicationResponseCompletableFuture = CompletableFuture.completedFuture(
                PutBucketReplicationResponse.builder().build());
        when(mockS3AsyncClient.putBucketReplication(PutBucketReplicationRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(putBucketReplicationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutBucketReplication();

        // Verify the results
    }

    @Test
    void testTryPutBucketReplication_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.putBucketReplication(...).
        final CompletableFuture<PutBucketReplicationResponse> putBucketReplicationResponseCompletableFuture = new CompletableFuture<>();
        putBucketReplicationResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.putBucketReplication(PutBucketReplicationRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(putBucketReplicationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutBucketReplication();

        // Verify the results
    }

    @Test
    void testTryPutBucketReplication1() {
        // Setup
        // Configure S3AsyncClient.putBucketReplication(...).
        final CompletableFuture<PutBucketReplicationResponse> putBucketReplicationResponseCompletableFuture = CompletableFuture.completedFuture(
                PutBucketReplicationResponse.builder().build());
        when(mockS3AsyncClient.putBucketReplication(any(Consumer.class)))
                .thenReturn(putBucketReplicationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutBucketReplication1();

        // Verify the results
    }

    @Test
    void testTryPutBucketReplication1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.putBucketReplication(...).
        final CompletableFuture<PutBucketReplicationResponse> putBucketReplicationResponseCompletableFuture = new CompletableFuture<>();
        putBucketReplicationResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.putBucketReplication(any(Consumer.class)))
                .thenReturn(putBucketReplicationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutBucketReplication1();

        // Verify the results
    }

    @Test
    void testTryPutBucketRequestPayment() {
        // Setup
        // Configure S3AsyncClient.putBucketRequestPayment(...).
        final CompletableFuture<PutBucketRequestPaymentResponse> putBucketRequestPaymentResponseCompletableFuture = CompletableFuture.completedFuture(
                PutBucketRequestPaymentResponse.builder().build());
        when(mockS3AsyncClient.putBucketRequestPayment(PutBucketRequestPaymentRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(putBucketRequestPaymentResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutBucketRequestPayment();

        // Verify the results
    }

    @Test
    void testTryPutBucketRequestPayment_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.putBucketRequestPayment(...).
        final CompletableFuture<PutBucketRequestPaymentResponse> putBucketRequestPaymentResponseCompletableFuture = new CompletableFuture<>();
        putBucketRequestPaymentResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.putBucketRequestPayment(PutBucketRequestPaymentRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(putBucketRequestPaymentResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutBucketRequestPayment();

        // Verify the results
    }

    @Test
    void testTryPutBucketRequestPayment1() {
        // Setup
        // Configure S3AsyncClient.putBucketRequestPayment(...).
        final CompletableFuture<PutBucketRequestPaymentResponse> putBucketRequestPaymentResponseCompletableFuture = CompletableFuture.completedFuture(
                PutBucketRequestPaymentResponse.builder().build());
        when(mockS3AsyncClient.putBucketRequestPayment(any(Consumer.class)))
                .thenReturn(putBucketRequestPaymentResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutBucketRequestPayment1();

        // Verify the results
    }

    @Test
    void testTryPutBucketRequestPayment1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.putBucketRequestPayment(...).
        final CompletableFuture<PutBucketRequestPaymentResponse> putBucketRequestPaymentResponseCompletableFuture = new CompletableFuture<>();
        putBucketRequestPaymentResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.putBucketRequestPayment(any(Consumer.class)))
                .thenReturn(putBucketRequestPaymentResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutBucketRequestPayment1();

        // Verify the results
    }

    @Test
    void testTryPutBucketTagging() {
        // Setup
        // Configure S3AsyncClient.putBucketTagging(...).
        final CompletableFuture<PutBucketTaggingResponse> putBucketTaggingResponseCompletableFuture = CompletableFuture.completedFuture(
                PutBucketTaggingResponse.builder().build());
        when(mockS3AsyncClient.putBucketTagging(PutBucketTaggingRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(putBucketTaggingResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutBucketTagging();

        // Verify the results
    }

    @Test
    void testTryPutBucketTagging_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.putBucketTagging(...).
        final CompletableFuture<PutBucketTaggingResponse> putBucketTaggingResponseCompletableFuture = new CompletableFuture<>();
        putBucketTaggingResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.putBucketTagging(PutBucketTaggingRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(putBucketTaggingResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutBucketTagging();

        // Verify the results
    }

    @Test
    void testTryPutBucketTagging1() {
        // Setup
        // Configure S3AsyncClient.putBucketTagging(...).
        final CompletableFuture<PutBucketTaggingResponse> putBucketTaggingResponseCompletableFuture = CompletableFuture.completedFuture(
                PutBucketTaggingResponse.builder().build());
        when(mockS3AsyncClient.putBucketTagging(any(Consumer.class)))
                .thenReturn(putBucketTaggingResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutBucketTagging1();

        // Verify the results
    }

    @Test
    void testTryPutBucketTagging1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.putBucketTagging(...).
        final CompletableFuture<PutBucketTaggingResponse> putBucketTaggingResponseCompletableFuture = new CompletableFuture<>();
        putBucketTaggingResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.putBucketTagging(any(Consumer.class)))
                .thenReturn(putBucketTaggingResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutBucketTagging1();

        // Verify the results
    }

    @Test
    void testTryPutBucketVersioning() {
        // Setup
        // Configure S3AsyncClient.putBucketVersioning(...).
        final CompletableFuture<PutBucketVersioningResponse> putBucketVersioningResponseCompletableFuture = CompletableFuture.completedFuture(
                PutBucketVersioningResponse.builder().build());
        when(mockS3AsyncClient.putBucketVersioning(PutBucketVersioningRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(putBucketVersioningResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutBucketVersioning();

        // Verify the results
    }

    @Test
    void testTryPutBucketVersioning_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.putBucketVersioning(...).
        final CompletableFuture<PutBucketVersioningResponse> putBucketVersioningResponseCompletableFuture = new CompletableFuture<>();
        putBucketVersioningResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.putBucketVersioning(PutBucketVersioningRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(putBucketVersioningResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutBucketVersioning();

        // Verify the results
    }

    @Test
    void testTryPutBucketVersioning1() {
        // Setup
        // Configure S3AsyncClient.putBucketVersioning(...).
        final CompletableFuture<PutBucketVersioningResponse> putBucketVersioningResponseCompletableFuture = CompletableFuture.completedFuture(
                PutBucketVersioningResponse.builder().build());
        when(mockS3AsyncClient.putBucketVersioning(any(Consumer.class)))
                .thenReturn(putBucketVersioningResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutBucketVersioning1();

        // Verify the results
    }

    @Test
    void testTryPutBucketVersioning1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.putBucketVersioning(...).
        final CompletableFuture<PutBucketVersioningResponse> putBucketVersioningResponseCompletableFuture = new CompletableFuture<>();
        putBucketVersioningResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.putBucketVersioning(any(Consumer.class)))
                .thenReturn(putBucketVersioningResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutBucketVersioning1();

        // Verify the results
    }

    @Test
    void testTryPutBucketWebsite() {
        // Setup
        // Configure S3AsyncClient.putBucketWebsite(...).
        final CompletableFuture<PutBucketWebsiteResponse> putBucketWebsiteResponseCompletableFuture = CompletableFuture.completedFuture(
                PutBucketWebsiteResponse.builder().build());
        when(mockS3AsyncClient.putBucketWebsite(PutBucketWebsiteRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(putBucketWebsiteResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutBucketWebsite();

        // Verify the results
    }

    @Test
    void testTryPutBucketWebsite_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.putBucketWebsite(...).
        final CompletableFuture<PutBucketWebsiteResponse> putBucketWebsiteResponseCompletableFuture = new CompletableFuture<>();
        putBucketWebsiteResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.putBucketWebsite(PutBucketWebsiteRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(putBucketWebsiteResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutBucketWebsite();

        // Verify the results
    }

    @Test
    void testTryPutBucketWebsite1() {
        // Setup
        // Configure S3AsyncClient.putBucketWebsite(...).
        final CompletableFuture<PutBucketWebsiteResponse> putBucketWebsiteResponseCompletableFuture = CompletableFuture.completedFuture(
                PutBucketWebsiteResponse.builder().build());
        when(mockS3AsyncClient.putBucketWebsite(any(Consumer.class)))
                .thenReturn(putBucketWebsiteResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutBucketWebsite1();

        // Verify the results
    }

    @Test
    void testTryPutBucketWebsite1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.putBucketWebsite(...).
        final CompletableFuture<PutBucketWebsiteResponse> putBucketWebsiteResponseCompletableFuture = new CompletableFuture<>();
        putBucketWebsiteResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.putBucketWebsite(any(Consumer.class)))
                .thenReturn(putBucketWebsiteResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutBucketWebsite1();

        // Verify the results
    }

    @Test
    void testTryPutObject() {
        // Setup
        // Configure S3AsyncClient.putObject(...).
        final CompletableFuture<PutObjectResponse> putObjectResponseCompletableFuture = CompletableFuture.completedFuture(
                PutObjectResponse.builder().build());
        when(mockS3AsyncClient.putObject(eq(PutObjectRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build()), any(AsyncRequestBody.class))).thenReturn(putObjectResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutObject();

        // Verify the results
    }

    @Test
    void testTryPutObject_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.putObject(...).
        final CompletableFuture<PutObjectResponse> putObjectResponseCompletableFuture = new CompletableFuture<>();
        putObjectResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.putObject(eq(PutObjectRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build()), any(AsyncRequestBody.class))).thenReturn(putObjectResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutObject();

        // Verify the results
    }

    @Test
    void testTryPutObject1() {
        // Setup
        // Configure S3AsyncClient.putObject(...).
        final CompletableFuture<PutObjectResponse> putObjectResponseCompletableFuture = CompletableFuture.completedFuture(
                PutObjectResponse.builder().build());
        when(mockS3AsyncClient.putObject(any(Consumer.class), any(AsyncRequestBody.class)))
                .thenReturn(putObjectResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutObject1();

        // Verify the results
    }

    @Test
    void testTryPutObject1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.putObject(...).
        final CompletableFuture<PutObjectResponse> putObjectResponseCompletableFuture = new CompletableFuture<>();
        putObjectResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.putObject(any(Consumer.class), any(AsyncRequestBody.class)))
                .thenReturn(putObjectResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutObject1();

        // Verify the results
    }

    @Test
    void testTryPutObject2() {
        // Setup
        // Configure S3AsyncClient.putObject(...).
        final CompletableFuture<PutObjectResponse> putObjectResponseCompletableFuture = CompletableFuture.completedFuture(
                PutObjectResponse.builder().build());
        when(mockS3AsyncClient.putObject(PutObjectRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build(), Paths.get("filename.txt"))).thenReturn(putObjectResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutObject2();

        // Verify the results
    }

    @Test
    void testTryPutObject2_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.putObject(...).
        final CompletableFuture<PutObjectResponse> putObjectResponseCompletableFuture = new CompletableFuture<>();
        putObjectResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.putObject(PutObjectRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build(), Paths.get("filename.txt"))).thenReturn(putObjectResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutObject2();

        // Verify the results
    }

    @Test
    void testTryPutObject3() {
        // Setup
        // Configure S3AsyncClient.putObject(...).
        final CompletableFuture<PutObjectResponse> putObjectResponseCompletableFuture = CompletableFuture.completedFuture(
                PutObjectResponse.builder().build());
        when(mockS3AsyncClient.putObject(any(Consumer.class), eq(Paths.get("filename.txt"))))
                .thenReturn(putObjectResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutObject3();

        // Verify the results
    }

    @Test
    void testTryPutObject3_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.putObject(...).
        final CompletableFuture<PutObjectResponse> putObjectResponseCompletableFuture = new CompletableFuture<>();
        putObjectResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.putObject(any(Consumer.class), eq(Paths.get("filename.txt"))))
                .thenReturn(putObjectResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutObject3();

        // Verify the results
    }

    @Test
    void testTryPutObjectAcl() {
        // Setup
        // Configure S3AsyncClient.putObjectAcl(...).
        final CompletableFuture<PutObjectAclResponse> putObjectAclResponseCompletableFuture = CompletableFuture.completedFuture(
                PutObjectAclResponse.builder().build());
        when(mockS3AsyncClient.putObjectAcl(PutObjectAclRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build())).thenReturn(putObjectAclResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutObjectAcl();

        // Verify the results
    }

    @Test
    void testTryPutObjectAcl_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.putObjectAcl(...).
        final CompletableFuture<PutObjectAclResponse> putObjectAclResponseCompletableFuture = new CompletableFuture<>();
        putObjectAclResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.putObjectAcl(PutObjectAclRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build())).thenReturn(putObjectAclResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutObjectAcl();

        // Verify the results
    }

    @Test
    void testTryPutObjectAcl1() {
        // Setup
        // Configure S3AsyncClient.putObjectAcl(...).
        final CompletableFuture<PutObjectAclResponse> putObjectAclResponseCompletableFuture = CompletableFuture.completedFuture(
                PutObjectAclResponse.builder().build());
        when(mockS3AsyncClient.putObjectAcl(any(Consumer.class))).thenReturn(putObjectAclResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutObjectAcl1();

        // Verify the results
    }

    @Test
    void testTryPutObjectAcl1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.putObjectAcl(...).
        final CompletableFuture<PutObjectAclResponse> putObjectAclResponseCompletableFuture = new CompletableFuture<>();
        putObjectAclResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.putObjectAcl(any(Consumer.class))).thenReturn(putObjectAclResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutObjectAcl1();

        // Verify the results
    }

    @Test
    void testTryPutObjectLegalHold() {
        // Setup
        // Configure S3AsyncClient.putObjectLegalHold(...).
        final CompletableFuture<PutObjectLegalHoldResponse> putObjectLegalHoldResponseCompletableFuture = CompletableFuture.completedFuture(
                PutObjectLegalHoldResponse.builder().build());
        when(mockS3AsyncClient.putObjectLegalHold(PutObjectLegalHoldRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build())).thenReturn(putObjectLegalHoldResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutObjectLegalHold();

        // Verify the results
    }

    @Test
    void testTryPutObjectLegalHold_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.putObjectLegalHold(...).
        final CompletableFuture<PutObjectLegalHoldResponse> putObjectLegalHoldResponseCompletableFuture = new CompletableFuture<>();
        putObjectLegalHoldResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.putObjectLegalHold(PutObjectLegalHoldRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build())).thenReturn(putObjectLegalHoldResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutObjectLegalHold();

        // Verify the results
    }

    @Test
    void testTryPutObjectLegalHold1() {
        // Setup
        // Configure S3AsyncClient.putObjectLegalHold(...).
        final CompletableFuture<PutObjectLegalHoldResponse> putObjectLegalHoldResponseCompletableFuture = CompletableFuture.completedFuture(
                PutObjectLegalHoldResponse.builder().build());
        when(mockS3AsyncClient.putObjectLegalHold(any(Consumer.class)))
                .thenReturn(putObjectLegalHoldResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutObjectLegalHold1();

        // Verify the results
    }

    @Test
    void testTryPutObjectLegalHold1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.putObjectLegalHold(...).
        final CompletableFuture<PutObjectLegalHoldResponse> putObjectLegalHoldResponseCompletableFuture = new CompletableFuture<>();
        putObjectLegalHoldResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.putObjectLegalHold(any(Consumer.class)))
                .thenReturn(putObjectLegalHoldResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutObjectLegalHold1();

        // Verify the results
    }

    @Test
    void testTryPutObjectLockConfiguration() {
        // Setup
        // Configure S3AsyncClient.putObjectLockConfiguration(...).
        final CompletableFuture<PutObjectLockConfigurationResponse> putObjectLockConfigurationResponseCompletableFuture = CompletableFuture.completedFuture(
                PutObjectLockConfigurationResponse.builder().build());
        when(mockS3AsyncClient.putObjectLockConfiguration(PutObjectLockConfigurationRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(putObjectLockConfigurationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutObjectLockConfiguration();

        // Verify the results
    }

    @Test
    void testTryPutObjectLockConfiguration_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.putObjectLockConfiguration(...).
        final CompletableFuture<PutObjectLockConfigurationResponse> putObjectLockConfigurationResponseCompletableFuture = new CompletableFuture<>();
        putObjectLockConfigurationResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.putObjectLockConfiguration(PutObjectLockConfigurationRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(putObjectLockConfigurationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutObjectLockConfiguration();

        // Verify the results
    }

    @Test
    void testTryPutObjectLockConfiguration1() {
        // Setup
        // Configure S3AsyncClient.putObjectLockConfiguration(...).
        final CompletableFuture<PutObjectLockConfigurationResponse> putObjectLockConfigurationResponseCompletableFuture = CompletableFuture.completedFuture(
                PutObjectLockConfigurationResponse.builder().build());
        when(mockS3AsyncClient.putObjectLockConfiguration(any(Consumer.class)))
                .thenReturn(putObjectLockConfigurationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutObjectLockConfiguration1();

        // Verify the results
    }

    @Test
    void testTryPutObjectLockConfiguration1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.putObjectLockConfiguration(...).
        final CompletableFuture<PutObjectLockConfigurationResponse> putObjectLockConfigurationResponseCompletableFuture = new CompletableFuture<>();
        putObjectLockConfigurationResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.putObjectLockConfiguration(any(Consumer.class)))
                .thenReturn(putObjectLockConfigurationResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutObjectLockConfiguration1();

        // Verify the results
    }

    @Test
    void testTryPutObjectRetention() {
        // Setup
        // Configure S3AsyncClient.putObjectRetention(...).
        final CompletableFuture<PutObjectRetentionResponse> putObjectRetentionResponseCompletableFuture = CompletableFuture.completedFuture(
                PutObjectRetentionResponse.builder().build());
        when(mockS3AsyncClient.putObjectRetention(PutObjectRetentionRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build())).thenReturn(putObjectRetentionResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutObjectRetention();

        // Verify the results
    }

    @Test
    void testTryPutObjectRetention_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.putObjectRetention(...).
        final CompletableFuture<PutObjectRetentionResponse> putObjectRetentionResponseCompletableFuture = new CompletableFuture<>();
        putObjectRetentionResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.putObjectRetention(PutObjectRetentionRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build())).thenReturn(putObjectRetentionResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutObjectRetention();

        // Verify the results
    }

    @Test
    void testTryPutObjectRetention1() {
        // Setup
        // Configure S3AsyncClient.putObjectRetention(...).
        final CompletableFuture<PutObjectRetentionResponse> putObjectRetentionResponseCompletableFuture = CompletableFuture.completedFuture(
                PutObjectRetentionResponse.builder().build());
        when(mockS3AsyncClient.putObjectRetention(any(Consumer.class)))
                .thenReturn(putObjectRetentionResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutObjectRetention1();

        // Verify the results
    }

    @Test
    void testTryPutObjectRetention1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.putObjectRetention(...).
        final CompletableFuture<PutObjectRetentionResponse> putObjectRetentionResponseCompletableFuture = new CompletableFuture<>();
        putObjectRetentionResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.putObjectRetention(any(Consumer.class)))
                .thenReturn(putObjectRetentionResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutObjectRetention1();

        // Verify the results
    }

    @Test
    void testTryPutObjectTagging() {
        // Setup
        // Configure S3AsyncClient.putObjectTagging(...).
        final CompletableFuture<PutObjectTaggingResponse> putObjectTaggingResponseCompletableFuture = CompletableFuture.completedFuture(
                PutObjectTaggingResponse.builder().build());
        when(mockS3AsyncClient.putObjectTagging(PutObjectTaggingRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build())).thenReturn(putObjectTaggingResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutObjectTagging();

        // Verify the results
    }

    @Test
    void testTryPutObjectTagging_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.putObjectTagging(...).
        final CompletableFuture<PutObjectTaggingResponse> putObjectTaggingResponseCompletableFuture = new CompletableFuture<>();
        putObjectTaggingResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.putObjectTagging(PutObjectTaggingRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build())).thenReturn(putObjectTaggingResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutObjectTagging();

        // Verify the results
    }

    @Test
    void testTryPutObjectTagging1() {
        // Setup
        // Configure S3AsyncClient.putObjectTagging(...).
        final CompletableFuture<PutObjectTaggingResponse> putObjectTaggingResponseCompletableFuture = CompletableFuture.completedFuture(
                PutObjectTaggingResponse.builder().build());
        when(mockS3AsyncClient.putObjectTagging(any(Consumer.class)))
                .thenReturn(putObjectTaggingResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutObjectTagging1();

        // Verify the results
    }

    @Test
    void testTryPutObjectTagging1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.putObjectTagging(...).
        final CompletableFuture<PutObjectTaggingResponse> putObjectTaggingResponseCompletableFuture = new CompletableFuture<>();
        putObjectTaggingResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.putObjectTagging(any(Consumer.class)))
                .thenReturn(putObjectTaggingResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutObjectTagging1();

        // Verify the results
    }

    @Test
    void testTryPutPublicAccessBlock() {
        // Setup
        // Configure S3AsyncClient.putPublicAccessBlock(...).
        final CompletableFuture<PutPublicAccessBlockResponse> putPublicAccessBlockResponseCompletableFuture = CompletableFuture.completedFuture(
                PutPublicAccessBlockResponse.builder().build());
        when(mockS3AsyncClient.putPublicAccessBlock(PutPublicAccessBlockRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(putPublicAccessBlockResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutPublicAccessBlock();

        // Verify the results
    }

    @Test
    void testTryPutPublicAccessBlock_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.putPublicAccessBlock(...).
        final CompletableFuture<PutPublicAccessBlockResponse> putPublicAccessBlockResponseCompletableFuture = new CompletableFuture<>();
        putPublicAccessBlockResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.putPublicAccessBlock(PutPublicAccessBlockRequest.builder()
                .bucket("bucketName")
                .build())).thenReturn(putPublicAccessBlockResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutPublicAccessBlock();

        // Verify the results
    }

    @Test
    void testTryPutPublicAccessBlock1() {
        // Setup
        // Configure S3AsyncClient.putPublicAccessBlock(...).
        final CompletableFuture<PutPublicAccessBlockResponse> putPublicAccessBlockResponseCompletableFuture = CompletableFuture.completedFuture(
                PutPublicAccessBlockResponse.builder().build());
        when(mockS3AsyncClient.putPublicAccessBlock(any(Consumer.class)))
                .thenReturn(putPublicAccessBlockResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutPublicAccessBlock1();

        // Verify the results
    }

    @Test
    void testTryPutPublicAccessBlock1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.putPublicAccessBlock(...).
        final CompletableFuture<PutPublicAccessBlockResponse> putPublicAccessBlockResponseCompletableFuture = new CompletableFuture<>();
        putPublicAccessBlockResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.putPublicAccessBlock(any(Consumer.class)))
                .thenReturn(putPublicAccessBlockResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryPutPublicAccessBlock1();

        // Verify the results
    }

    @Test
    void testTryRestoreObject() {
        // Setup
        // Configure S3AsyncClient.restoreObject(...).
        final CompletableFuture<RestoreObjectResponse> restoreObjectResponseCompletableFuture = CompletableFuture.completedFuture(
                RestoreObjectResponse.builder().build());
        when(mockS3AsyncClient.restoreObject(RestoreObjectRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build())).thenReturn(restoreObjectResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryRestoreObject();

        // Verify the results
    }

    @Test
    void testTryRestoreObject_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.restoreObject(...).
        final CompletableFuture<RestoreObjectResponse> restoreObjectResponseCompletableFuture = new CompletableFuture<>();
        restoreObjectResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.restoreObject(RestoreObjectRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build())).thenReturn(restoreObjectResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryRestoreObject();

        // Verify the results
    }

    @Test
    void testTryRestoreObject1() {
        // Setup
        // Configure S3AsyncClient.restoreObject(...).
        final CompletableFuture<RestoreObjectResponse> restoreObjectResponseCompletableFuture = CompletableFuture.completedFuture(
                RestoreObjectResponse.builder().build());
        when(mockS3AsyncClient.restoreObject(any(Consumer.class))).thenReturn(restoreObjectResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryRestoreObject1();

        // Verify the results
    }

    @Test
    void testTryRestoreObject1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.restoreObject(...).
        final CompletableFuture<RestoreObjectResponse> restoreObjectResponseCompletableFuture = new CompletableFuture<>();
        restoreObjectResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.restoreObject(any(Consumer.class))).thenReturn(restoreObjectResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryRestoreObject1();

        // Verify the results
    }

    @Test
    void testTryUploadPart() {
        // Setup
        // Configure S3AsyncClient.uploadPart(...).
        final CompletableFuture<UploadPartResponse> uploadPartResponseCompletableFuture = CompletableFuture.completedFuture(
                UploadPartResponse.builder().build());
        when(mockS3AsyncClient.uploadPart(eq(UploadPartRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build()), any(AsyncRequestBody.class))).thenReturn(uploadPartResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryUploadPart();

        // Verify the results
    }

    @Test
    void testTryUploadPart_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.uploadPart(...).
        final CompletableFuture<UploadPartResponse> uploadPartResponseCompletableFuture = new CompletableFuture<>();
        uploadPartResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.uploadPart(eq(UploadPartRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build()), any(AsyncRequestBody.class))).thenReturn(uploadPartResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryUploadPart();

        // Verify the results
    }

    @Test
    void testTryUploadPart1() {
        // Setup
        // Configure S3AsyncClient.uploadPart(...).
        final CompletableFuture<UploadPartResponse> uploadPartResponseCompletableFuture = CompletableFuture.completedFuture(
                UploadPartResponse.builder().build());
        when(mockS3AsyncClient.uploadPart(any(Consumer.class), any(AsyncRequestBody.class)))
                .thenReturn(uploadPartResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryUploadPart1();

        // Verify the results
    }

    @Test
    void testTryUploadPart1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.uploadPart(...).
        final CompletableFuture<UploadPartResponse> uploadPartResponseCompletableFuture = new CompletableFuture<>();
        uploadPartResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.uploadPart(any(Consumer.class), any(AsyncRequestBody.class)))
                .thenReturn(uploadPartResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryUploadPart1();

        // Verify the results
    }

    @Test
    void testTryUploadPart2() {
        // Setup
        // Configure S3AsyncClient.uploadPart(...).
        final CompletableFuture<UploadPartResponse> uploadPartResponseCompletableFuture = CompletableFuture.completedFuture(
                UploadPartResponse.builder().build());
        when(mockS3AsyncClient.uploadPart(UploadPartRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build(), Paths.get("filename.txt"))).thenReturn(uploadPartResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryUploadPart2();

        // Verify the results
    }

    @Test
    void testTryUploadPart2_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.uploadPart(...).
        final CompletableFuture<UploadPartResponse> uploadPartResponseCompletableFuture = new CompletableFuture<>();
        uploadPartResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.uploadPart(UploadPartRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build(), Paths.get("filename.txt"))).thenReturn(uploadPartResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryUploadPart2();

        // Verify the results
    }

    @Test
    void testTryUploadPart3() {
        // Setup
        // Configure S3AsyncClient.uploadPart(...).
        final CompletableFuture<UploadPartResponse> uploadPartResponseCompletableFuture = CompletableFuture.completedFuture(
                UploadPartResponse.builder().build());
        when(mockS3AsyncClient.uploadPart(any(Consumer.class), eq(Paths.get("filename.txt"))))
                .thenReturn(uploadPartResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryUploadPart3();

        // Verify the results
    }

    @Test
    void testTryUploadPart3_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.uploadPart(...).
        final CompletableFuture<UploadPartResponse> uploadPartResponseCompletableFuture = new CompletableFuture<>();
        uploadPartResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.uploadPart(any(Consumer.class), eq(Paths.get("filename.txt"))))
                .thenReturn(uploadPartResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryUploadPart3();

        // Verify the results
    }

    @Test
    void testTryUploadPartCopy() {
        // Setup
        // Configure S3AsyncClient.uploadPartCopy(...).
        final CompletableFuture<UploadPartCopyResponse> uploadPartCopyResponseCompletableFuture = CompletableFuture.completedFuture(
                UploadPartCopyResponse.builder().build());
        when(mockS3AsyncClient.uploadPartCopy(UploadPartCopyRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build())).thenReturn(uploadPartCopyResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryUploadPartCopy();

        // Verify the results
    }

    @Test
    void testTryUploadPartCopy_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.uploadPartCopy(...).
        final CompletableFuture<UploadPartCopyResponse> uploadPartCopyResponseCompletableFuture = new CompletableFuture<>();
        uploadPartCopyResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.uploadPartCopy(UploadPartCopyRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build())).thenReturn(uploadPartCopyResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryUploadPartCopy();

        // Verify the results
    }

    @Test
    void testTryUploadPartCopy1() {
        // Setup
        // Configure S3AsyncClient.uploadPartCopy(...).
        final CompletableFuture<UploadPartCopyResponse> uploadPartCopyResponseCompletableFuture = CompletableFuture.completedFuture(
                UploadPartCopyResponse.builder().build());
        when(mockS3AsyncClient.uploadPartCopy(any(Consumer.class))).thenReturn(uploadPartCopyResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryUploadPartCopy1();

        // Verify the results
    }

    @Test
    void testTryUploadPartCopy1_S3AsyncClientReturnsFailure() {
        // Setup
        // Configure S3AsyncClient.uploadPartCopy(...).
        final CompletableFuture<UploadPartCopyResponse> uploadPartCopyResponseCompletableFuture = new CompletableFuture<>();
        uploadPartCopyResponseCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockS3AsyncClient.uploadPartCopy(any(Consumer.class))).thenReturn(uploadPartCopyResponseCompletableFuture);

        // Run the test
        myClassUnderTest.tryUploadPartCopy1();

        // Verify the results
    }

    @Test
    void testTryUtilities() {
        // Setup
        when(mockS3AsyncClient.utilities()).thenReturn(S3Utilities.builder().build());

        // Run the test
        myClassUnderTest.tryUtilities();

        // Verify the results
    }
}
