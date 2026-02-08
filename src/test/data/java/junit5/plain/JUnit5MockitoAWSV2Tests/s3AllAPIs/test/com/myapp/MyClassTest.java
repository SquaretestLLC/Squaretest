package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.core.sync.ResponseTransformer;
import software.amazon.awssdk.http.AbortableInputStream;
import software.amazon.awssdk.profiles.ProfileFile;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;
import software.amazon.awssdk.services.s3.S3Utilities;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.s3.paginators.ListMultipartUploadsIterable;
import software.amazon.awssdk.services.s3.paginators.ListObjectVersionsIterable;
import software.amazon.awssdk.services.s3.paginators.ListObjectsV2Iterable;
import software.amazon.awssdk.services.s3.paginators.ListPartsIterable;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private S3Client mockS3Client;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockS3Client);
    }

    @Test
    void testTryAbortMultipartUpload1() {
        // Setup
        // Configure S3Client.abortMultipartUpload(...).
        final AbortMultipartUploadResponse abortMultipartUploadResponse = AbortMultipartUploadResponse.builder().build();
        when(mockS3Client.abortMultipartUpload(AbortMultipartUploadRequest.builder()
                .bucket("bucket")
                .key("key")
                .uploadId("uploadId")
                .build())).thenReturn(abortMultipartUploadResponse);

        // Run the test
        myClassUnderTest.tryAbortMultipartUpload1();

        // Verify the results
    }

    @Test
    void testTryAbortMultipartUpload1_S3ClientThrowsNoSuchUploadException() {
        // Setup
        when(mockS3Client.abortMultipartUpload(AbortMultipartUploadRequest.builder()
                .bucket("bucket")
                .key("key")
                .uploadId("uploadId")
                .build())).thenThrow(NoSuchUploadException.class);

        // Run the test
        assertThrows(NoSuchUploadException.class, () -> myClassUnderTest.tryAbortMultipartUpload1());
    }

    @Test
    void testTryAbortMultipartUpload1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.abortMultipartUpload(AbortMultipartUploadRequest.builder()
                .bucket("bucket")
                .key("key")
                .uploadId("uploadId")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryAbortMultipartUpload1());
    }

    @Test
    void testTryAbortMultipartUpload1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.abortMultipartUpload(AbortMultipartUploadRequest.builder()
                .bucket("bucket")
                .key("key")
                .uploadId("uploadId")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryAbortMultipartUpload1());
    }

    @Test
    void testTryAbortMultipartUpload1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.abortMultipartUpload(AbortMultipartUploadRequest.builder()
                .bucket("bucket")
                .key("key")
                .uploadId("uploadId")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryAbortMultipartUpload1());
    }

    @Test
    void testTryAbortMultipartUpload2() {
        // Setup
        // Configure S3Client.abortMultipartUpload(...).
        final AbortMultipartUploadResponse abortMultipartUploadResponse = AbortMultipartUploadResponse.builder().build();
        when(mockS3Client.abortMultipartUpload(any(Consumer.class))).thenReturn(abortMultipartUploadResponse);

        // Run the test
        myClassUnderTest.tryAbortMultipartUpload2();

        // Verify the results
    }

    @Test
    void testTryAbortMultipartUpload2_S3ClientThrowsNoSuchUploadException() {
        // Setup
        when(mockS3Client.abortMultipartUpload(any(Consumer.class))).thenThrow(NoSuchUploadException.class);

        // Run the test
        assertThrows(NoSuchUploadException.class, () -> myClassUnderTest.tryAbortMultipartUpload2());
    }

    @Test
    void testTryAbortMultipartUpload2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.abortMultipartUpload(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryAbortMultipartUpload2());
    }

    @Test
    void testTryAbortMultipartUpload2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.abortMultipartUpload(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryAbortMultipartUpload2());
    }

    @Test
    void testTryAbortMultipartUpload2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.abortMultipartUpload(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryAbortMultipartUpload2());
    }

    @Test
    void testTryCompleteMultipartUpload1() {
        // Setup
        // Configure S3Client.completeMultipartUpload(...).
        final CompleteMultipartUploadResponse completeMultipartUploadResponse = CompleteMultipartUploadResponse.builder()
                .location("location")
                .bucket("bucket")
                .key("key")
                .eTag("eTag")
                .build();
        when(mockS3Client.completeMultipartUpload(CompleteMultipartUploadRequest.builder()
                .bucket("bucket")
                .key("key")
                .multipartUpload(CompletedMultipartUpload.builder()
                        .parts(CompletedPart.builder()
                                .eTag("eTag")
                                .partNumber(0)
                                .build())
                        .build())
                .uploadId("uploadId")
                .build())).thenReturn(completeMultipartUploadResponse);

        // Run the test
        myClassUnderTest.tryCompleteMultipartUpload1();

        // Verify the results
    }

    @Test
    void testTryCompleteMultipartUpload1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.completeMultipartUpload(CompleteMultipartUploadRequest.builder()
                .bucket("bucket")
                .key("key")
                .multipartUpload(CompletedMultipartUpload.builder()
                        .parts(CompletedPart.builder()
                                .eTag("eTag")
                                .partNumber(0)
                                .build())
                        .build())
                .uploadId("uploadId")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryCompleteMultipartUpload1());
    }

    @Test
    void testTryCompleteMultipartUpload1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.completeMultipartUpload(CompleteMultipartUploadRequest.builder()
                .bucket("bucket")
                .key("key")
                .multipartUpload(CompletedMultipartUpload.builder()
                        .parts(CompletedPart.builder()
                                .eTag("eTag")
                                .partNumber(0)
                                .build())
                        .build())
                .uploadId("uploadId")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryCompleteMultipartUpload1());
    }

    @Test
    void testTryCompleteMultipartUpload1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.completeMultipartUpload(CompleteMultipartUploadRequest.builder()
                .bucket("bucket")
                .key("key")
                .multipartUpload(CompletedMultipartUpload.builder()
                        .parts(CompletedPart.builder()
                                .eTag("eTag")
                                .partNumber(0)
                                .build())
                        .build())
                .uploadId("uploadId")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryCompleteMultipartUpload1());
    }

    @Test
    void testTryCompleteMultipartUpload2() {
        // Setup
        // Configure S3Client.completeMultipartUpload(...).
        final CompleteMultipartUploadResponse completeMultipartUploadResponse = CompleteMultipartUploadResponse.builder()
                .location("location")
                .bucket("bucket")
                .key("key")
                .eTag("eTag")
                .build();
        when(mockS3Client.completeMultipartUpload(any(Consumer.class))).thenReturn(completeMultipartUploadResponse);

        // Run the test
        myClassUnderTest.tryCompleteMultipartUpload2();

        // Verify the results
    }

    @Test
    void testTryCompleteMultipartUpload2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.completeMultipartUpload(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryCompleteMultipartUpload2());
    }

    @Test
    void testTryCompleteMultipartUpload2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.completeMultipartUpload(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryCompleteMultipartUpload2());
    }

    @Test
    void testTryCompleteMultipartUpload2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.completeMultipartUpload(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryCompleteMultipartUpload2());
    }

    @Test
    void testTryCopyObject1() {
        // Setup
        // Configure S3Client.copyObject(...).
        final CopyObjectResponse copyObjectResponse = CopyObjectResponse.builder()
                .copyObjectResult(CopyObjectResult.builder()
                        .eTag("eTag")
                        .lastModified(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                        .build())
                .build();
        when(mockS3Client.copyObject(CopyObjectRequest.builder()
                .copySource("encodedUrl")
                .destinationBucket("toBucket")
                .destinationKey("objectKey")
                .build())).thenReturn(copyObjectResponse);

        // Run the test
        myClassUnderTest.tryCopyObject1();

        // Verify the results
    }

    @Test
    void testTryCopyObject1_S3ClientThrowsObjectNotInActiveTierErrorException() {
        // Setup
        when(mockS3Client.copyObject(CopyObjectRequest.builder()
                .copySource("encodedUrl")
                .destinationBucket("toBucket")
                .destinationKey("objectKey")
                .build())).thenThrow(ObjectNotInActiveTierErrorException.class);

        // Run the test
        assertThrows(ObjectNotInActiveTierErrorException.class, () -> myClassUnderTest.tryCopyObject1());
    }

    @Test
    void testTryCopyObject1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.copyObject(CopyObjectRequest.builder()
                .copySource("encodedUrl")
                .destinationBucket("toBucket")
                .destinationKey("objectKey")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryCopyObject1());
    }

    @Test
    void testTryCopyObject1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.copyObject(CopyObjectRequest.builder()
                .copySource("encodedUrl")
                .destinationBucket("toBucket")
                .destinationKey("objectKey")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryCopyObject1());
    }

    @Test
    void testTryCopyObject1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.copyObject(CopyObjectRequest.builder()
                .copySource("encodedUrl")
                .destinationBucket("toBucket")
                .destinationKey("objectKey")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryCopyObject1());
    }

    @Test
    void testTryCopyObject2() {
        // Setup
        // Configure S3Client.copyObject(...).
        final CopyObjectResponse copyObjectResponse = CopyObjectResponse.builder()
                .copyObjectResult(CopyObjectResult.builder()
                        .eTag("eTag")
                        .lastModified(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                        .build())
                .build();
        when(mockS3Client.copyObject(any(Consumer.class))).thenReturn(copyObjectResponse);

        // Run the test
        myClassUnderTest.tryCopyObject2();

        // Verify the results
    }

    @Test
    void testTryCopyObject2_S3ClientThrowsObjectNotInActiveTierErrorException() {
        // Setup
        when(mockS3Client.copyObject(any(Consumer.class))).thenThrow(ObjectNotInActiveTierErrorException.class);

        // Run the test
        assertThrows(ObjectNotInActiveTierErrorException.class, () -> myClassUnderTest.tryCopyObject2());
    }

    @Test
    void testTryCopyObject2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.copyObject(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryCopyObject2());
    }

    @Test
    void testTryCopyObject2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.copyObject(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryCopyObject2());
    }

    @Test
    void testTryCopyObject2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.copyObject(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryCopyObject2());
    }

    @Test
    void testTryCreateBucket1() {
        // Setup
        // Configure S3Client.createBucket(...).
        final CreateBucketResponse createBucketResponse = CreateBucketResponse.builder()
                .location("location")
                .build();
        when(mockS3Client.createBucket(CreateBucketRequest.builder()
                .acl(BucketCannedACL.PRIVATE)
                .bucket("bucket")
                .createBucketConfiguration(CreateBucketConfiguration.builder()
                        .locationConstraint(BucketLocationConstraint.US_EAST_2)
                        .build())
                .grantFullControl("grantFullControl")
                .grantRead("grantRead")
                .grantReadACP("grantReadACP")
                .grantWrite("grantWrite")
                .grantWriteACP("grantWriteACP")
                .objectLockEnabledForBucket(false)
                .build())).thenReturn(createBucketResponse);

        // Run the test
        myClassUnderTest.tryCreateBucket1();

        // Verify the results
    }

    @Test
    void testTryCreateBucket1_S3ClientThrowsBucketAlreadyExistsException() {
        // Setup
        when(mockS3Client.createBucket(CreateBucketRequest.builder()
                .acl(BucketCannedACL.PRIVATE)
                .bucket("bucket")
                .createBucketConfiguration(CreateBucketConfiguration.builder()
                        .locationConstraint(BucketLocationConstraint.US_EAST_2)
                        .build())
                .grantFullControl("grantFullControl")
                .grantRead("grantRead")
                .grantReadACP("grantReadACP")
                .grantWrite("grantWrite")
                .grantWriteACP("grantWriteACP")
                .objectLockEnabledForBucket(false)
                .build())).thenThrow(BucketAlreadyExistsException.class);

        // Run the test
        assertThrows(BucketAlreadyExistsException.class, () -> myClassUnderTest.tryCreateBucket1());
    }

    @Test
    void testTryCreateBucket1_S3ClientThrowsBucketAlreadyOwnedByYouException() {
        // Setup
        when(mockS3Client.createBucket(CreateBucketRequest.builder()
                .acl(BucketCannedACL.PRIVATE)
                .bucket("bucket")
                .createBucketConfiguration(CreateBucketConfiguration.builder()
                        .locationConstraint(BucketLocationConstraint.US_EAST_2)
                        .build())
                .grantFullControl("grantFullControl")
                .grantRead("grantRead")
                .grantReadACP("grantReadACP")
                .grantWrite("grantWrite")
                .grantWriteACP("grantWriteACP")
                .objectLockEnabledForBucket(false)
                .build())).thenThrow(BucketAlreadyOwnedByYouException.class);

        // Run the test
        assertThrows(BucketAlreadyOwnedByYouException.class, () -> myClassUnderTest.tryCreateBucket1());
    }

    @Test
    void testTryCreateBucket1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.createBucket(CreateBucketRequest.builder()
                .acl(BucketCannedACL.PRIVATE)
                .bucket("bucket")
                .createBucketConfiguration(CreateBucketConfiguration.builder()
                        .locationConstraint(BucketLocationConstraint.US_EAST_2)
                        .build())
                .grantFullControl("grantFullControl")
                .grantRead("grantRead")
                .grantReadACP("grantReadACP")
                .grantWrite("grantWrite")
                .grantWriteACP("grantWriteACP")
                .objectLockEnabledForBucket(false)
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryCreateBucket1());
    }

    @Test
    void testTryCreateBucket1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.createBucket(CreateBucketRequest.builder()
                .acl(BucketCannedACL.PRIVATE)
                .bucket("bucket")
                .createBucketConfiguration(CreateBucketConfiguration.builder()
                        .locationConstraint(BucketLocationConstraint.US_EAST_2)
                        .build())
                .grantFullControl("grantFullControl")
                .grantRead("grantRead")
                .grantReadACP("grantReadACP")
                .grantWrite("grantWrite")
                .grantWriteACP("grantWriteACP")
                .objectLockEnabledForBucket(false)
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryCreateBucket1());
    }

    @Test
    void testTryCreateBucket1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.createBucket(CreateBucketRequest.builder()
                .acl(BucketCannedACL.PRIVATE)
                .bucket("bucket")
                .createBucketConfiguration(CreateBucketConfiguration.builder()
                        .locationConstraint(BucketLocationConstraint.US_EAST_2)
                        .build())
                .grantFullControl("grantFullControl")
                .grantRead("grantRead")
                .grantReadACP("grantReadACP")
                .grantWrite("grantWrite")
                .grantWriteACP("grantWriteACP")
                .objectLockEnabledForBucket(false)
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryCreateBucket1());
    }

    @Test
    void testTryCreateBucket2() {
        // Setup
        // Configure S3Client.createBucket(...).
        final CreateBucketResponse createBucketResponse = CreateBucketResponse.builder()
                .location("location")
                .build();
        when(mockS3Client.createBucket(any(Consumer.class))).thenReturn(createBucketResponse);

        // Run the test
        myClassUnderTest.tryCreateBucket2();

        // Verify the results
    }

    @Test
    void testTryCreateBucket2_S3ClientThrowsBucketAlreadyExistsException() {
        // Setup
        when(mockS3Client.createBucket(any(Consumer.class))).thenThrow(BucketAlreadyExistsException.class);

        // Run the test
        assertThrows(BucketAlreadyExistsException.class, () -> myClassUnderTest.tryCreateBucket2());
    }

    @Test
    void testTryCreateBucket2_S3ClientThrowsBucketAlreadyOwnedByYouException() {
        // Setup
        when(mockS3Client.createBucket(any(Consumer.class))).thenThrow(BucketAlreadyOwnedByYouException.class);

        // Run the test
        assertThrows(BucketAlreadyOwnedByYouException.class, () -> myClassUnderTest.tryCreateBucket2());
    }

    @Test
    void testTryCreateBucket2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.createBucket(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryCreateBucket2());
    }

    @Test
    void testTryCreateBucket2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.createBucket(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryCreateBucket2());
    }

    @Test
    void testTryCreateBucket2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.createBucket(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryCreateBucket2());
    }

    @Test
    void testTryCreateMultipartUpload1() {
        // Setup
        // Configure S3Client.createMultipartUpload(...).
        final CreateMultipartUploadResponse createMultipartUploadResponse = CreateMultipartUploadResponse.builder()
                .bucket("bucket")
                .key("key")
                .uploadId("uploadId")
                .build();
        when(mockS3Client.createMultipartUpload(CreateMultipartUploadRequest.builder()
                .bucket("bucket")
                .key("key")
                .build())).thenReturn(createMultipartUploadResponse);

        // Run the test
        myClassUnderTest.tryCreateMultipartUpload1();

        // Verify the results
    }

    @Test
    void testTryCreateMultipartUpload1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.createMultipartUpload(CreateMultipartUploadRequest.builder()
                .bucket("bucket")
                .key("key")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryCreateMultipartUpload1());
    }

    @Test
    void testTryCreateMultipartUpload1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.createMultipartUpload(CreateMultipartUploadRequest.builder()
                .bucket("bucket")
                .key("key")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryCreateMultipartUpload1());
    }

    @Test
    void testTryCreateMultipartUpload1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.createMultipartUpload(CreateMultipartUploadRequest.builder()
                .bucket("bucket")
                .key("key")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryCreateMultipartUpload1());
    }

    @Test
    void testTryCreateMultipartUpload2() {
        // Setup
        // Configure S3Client.createMultipartUpload(...).
        final CreateMultipartUploadResponse createMultipartUploadResponse = CreateMultipartUploadResponse.builder()
                .bucket("bucket")
                .key("key")
                .uploadId("uploadId")
                .build();
        when(mockS3Client.createMultipartUpload(any(Consumer.class))).thenReturn(createMultipartUploadResponse);

        // Run the test
        myClassUnderTest.tryCreateMultipartUpload2();

        // Verify the results
    }

    @Test
    void testTryCreateMultipartUpload2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.createMultipartUpload(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryCreateMultipartUpload2());
    }

    @Test
    void testTryCreateMultipartUpload2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.createMultipartUpload(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryCreateMultipartUpload2());
    }

    @Test
    void testTryCreateMultipartUpload2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.createMultipartUpload(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryCreateMultipartUpload2());
    }

    @Test
    void testTryDeleteBucket1() {
        // Setup
        when(mockS3Client.deleteBucket(DeleteBucketRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(DeleteBucketResponse.builder().build());

        // Run the test
        myClassUnderTest.tryDeleteBucket1();

        // Verify the results
    }

    @Test
    void testTryDeleteBucket1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.deleteBucket(DeleteBucketRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDeleteBucket1());
    }

    @Test
    void testTryDeleteBucket1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.deleteBucket(DeleteBucketRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteBucket1());
    }

    @Test
    void testTryDeleteBucket1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.deleteBucket(DeleteBucketRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryDeleteBucket1());
    }

    @Test
    void testTryDeleteBucket2() {
        // Setup
        when(mockS3Client.deleteBucket(any(Consumer.class))).thenReturn(DeleteBucketResponse.builder().build());

        // Run the test
        myClassUnderTest.tryDeleteBucket2();

        // Verify the results
    }

    @Test
    void testTryDeleteBucket2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.deleteBucket(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDeleteBucket2());
    }

    @Test
    void testTryDeleteBucket2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.deleteBucket(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteBucket2());
    }

    @Test
    void testTryDeleteBucket2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.deleteBucket(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryDeleteBucket2());
    }

    @Test
    void testTryDeleteBucketAnalyticsConfiguration1() {
        // Setup
        // Configure S3Client.deleteBucketAnalyticsConfiguration(...).
        final DeleteBucketAnalyticsConfigurationResponse deleteBucketAnalyticsConfigurationResponse = DeleteBucketAnalyticsConfigurationResponse.builder().build();
        when(mockS3Client.deleteBucketAnalyticsConfiguration(DeleteBucketAnalyticsConfigurationRequest.builder()
                .bucket("bucket")
                .id("id")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(deleteBucketAnalyticsConfigurationResponse);

        // Run the test
        myClassUnderTest.tryDeleteBucketAnalyticsConfiguration1();

        // Verify the results
    }

    @Test
    void testTryDeleteBucketAnalyticsConfiguration1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.deleteBucketAnalyticsConfiguration(DeleteBucketAnalyticsConfigurationRequest.builder()
                .bucket("bucket")
                .id("id")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDeleteBucketAnalyticsConfiguration1());
    }

    @Test
    void testTryDeleteBucketAnalyticsConfiguration1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.deleteBucketAnalyticsConfiguration(DeleteBucketAnalyticsConfigurationRequest.builder()
                .bucket("bucket")
                .id("id")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteBucketAnalyticsConfiguration1());
    }

    @Test
    void testTryDeleteBucketAnalyticsConfiguration1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.deleteBucketAnalyticsConfiguration(DeleteBucketAnalyticsConfigurationRequest.builder()
                .bucket("bucket")
                .id("id")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryDeleteBucketAnalyticsConfiguration1());
    }

    @Test
    void testTryDeleteBucketAnalyticsConfiguration2() {
        // Setup
        // Configure S3Client.deleteBucketAnalyticsConfiguration(...).
        final DeleteBucketAnalyticsConfigurationResponse deleteBucketAnalyticsConfigurationResponse = DeleteBucketAnalyticsConfigurationResponse.builder().build();
        when(mockS3Client.deleteBucketAnalyticsConfiguration(any(Consumer.class)))
                .thenReturn(deleteBucketAnalyticsConfigurationResponse);

        // Run the test
        myClassUnderTest.tryDeleteBucketAnalyticsConfiguration2();

        // Verify the results
    }

    @Test
    void testTryDeleteBucketAnalyticsConfiguration2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.deleteBucketAnalyticsConfiguration(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDeleteBucketAnalyticsConfiguration2());
    }

    @Test
    void testTryDeleteBucketAnalyticsConfiguration2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.deleteBucketAnalyticsConfiguration(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteBucketAnalyticsConfiguration2());
    }

    @Test
    void testTryDeleteBucketAnalyticsConfiguration2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.deleteBucketAnalyticsConfiguration(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryDeleteBucketAnalyticsConfiguration2());
    }

    @Test
    void testTryDeleteBucketCors1() {
        // Setup
        // Configure S3Client.deleteBucketCors(...).
        final DeleteBucketCorsResponse deleteBucketCorsResponse = DeleteBucketCorsResponse.builder().build();
        when(mockS3Client.deleteBucketCors(DeleteBucketCorsRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(deleteBucketCorsResponse);

        // Run the test
        myClassUnderTest.tryDeleteBucketCors1();

        // Verify the results
    }

    @Test
    void testTryDeleteBucketCors1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.deleteBucketCors(DeleteBucketCorsRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDeleteBucketCors1());
    }

    @Test
    void testTryDeleteBucketCors1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.deleteBucketCors(DeleteBucketCorsRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteBucketCors1());
    }

    @Test
    void testTryDeleteBucketCors1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.deleteBucketCors(DeleteBucketCorsRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryDeleteBucketCors1());
    }

    @Test
    void testTryDeleteBucketCors2() {
        // Setup
        // Configure S3Client.deleteBucketCors(...).
        final DeleteBucketCorsResponse deleteBucketCorsResponse = DeleteBucketCorsResponse.builder().build();
        when(mockS3Client.deleteBucketCors(any(Consumer.class))).thenReturn(deleteBucketCorsResponse);

        // Run the test
        myClassUnderTest.tryDeleteBucketCors2();

        // Verify the results
    }

    @Test
    void testTryDeleteBucketCors2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.deleteBucketCors(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDeleteBucketCors2());
    }

    @Test
    void testTryDeleteBucketCors2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.deleteBucketCors(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteBucketCors2());
    }

    @Test
    void testTryDeleteBucketCors2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.deleteBucketCors(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryDeleteBucketCors2());
    }

    @Test
    void testTryDeleteBucketEncryption1() {
        // Setup
        // Configure S3Client.deleteBucketEncryption(...).
        final DeleteBucketEncryptionResponse deleteBucketEncryptionResponse = DeleteBucketEncryptionResponse.builder().build();
        when(mockS3Client.deleteBucketEncryption(DeleteBucketEncryptionRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(deleteBucketEncryptionResponse);

        // Run the test
        myClassUnderTest.tryDeleteBucketEncryption1();

        // Verify the results
    }

    @Test
    void testTryDeleteBucketEncryption1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.deleteBucketEncryption(DeleteBucketEncryptionRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDeleteBucketEncryption1());
    }

    @Test
    void testTryDeleteBucketEncryption1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.deleteBucketEncryption(DeleteBucketEncryptionRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteBucketEncryption1());
    }

    @Test
    void testTryDeleteBucketEncryption1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.deleteBucketEncryption(DeleteBucketEncryptionRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryDeleteBucketEncryption1());
    }

    @Test
    void testTryDeleteBucketEncryption2() {
        // Setup
        // Configure S3Client.deleteBucketEncryption(...).
        final DeleteBucketEncryptionResponse deleteBucketEncryptionResponse = DeleteBucketEncryptionResponse.builder().build();
        when(mockS3Client.deleteBucketEncryption(any(Consumer.class))).thenReturn(deleteBucketEncryptionResponse);

        // Run the test
        myClassUnderTest.tryDeleteBucketEncryption2();

        // Verify the results
    }

    @Test
    void testTryDeleteBucketEncryption2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.deleteBucketEncryption(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDeleteBucketEncryption2());
    }

    @Test
    void testTryDeleteBucketEncryption2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.deleteBucketEncryption(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteBucketEncryption2());
    }

    @Test
    void testTryDeleteBucketEncryption2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.deleteBucketEncryption(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryDeleteBucketEncryption2());
    }

    @Test
    void testTryDeleteBucketInventoryConfiguration1() {
        // Setup
        // Configure S3Client.deleteBucketInventoryConfiguration(...).
        final DeleteBucketInventoryConfigurationResponse deleteBucketInventoryConfigurationResponse = DeleteBucketInventoryConfigurationResponse.builder().build();
        when(mockS3Client.deleteBucketInventoryConfiguration(DeleteBucketInventoryConfigurationRequest.builder()
                .bucket("bucket")
                .id("id")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(deleteBucketInventoryConfigurationResponse);

        // Run the test
        myClassUnderTest.tryDeleteBucketInventoryConfiguration1();

        // Verify the results
    }

    @Test
    void testTryDeleteBucketInventoryConfiguration1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.deleteBucketInventoryConfiguration(DeleteBucketInventoryConfigurationRequest.builder()
                .bucket("bucket")
                .id("id")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDeleteBucketInventoryConfiguration1());
    }

    @Test
    void testTryDeleteBucketInventoryConfiguration1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.deleteBucketInventoryConfiguration(DeleteBucketInventoryConfigurationRequest.builder()
                .bucket("bucket")
                .id("id")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteBucketInventoryConfiguration1());
    }

    @Test
    void testTryDeleteBucketInventoryConfiguration1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.deleteBucketInventoryConfiguration(DeleteBucketInventoryConfigurationRequest.builder()
                .bucket("bucket")
                .id("id")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryDeleteBucketInventoryConfiguration1());
    }

    @Test
    void testTryDeleteBucketInventoryConfiguration2() {
        // Setup
        // Configure S3Client.deleteBucketInventoryConfiguration(...).
        final DeleteBucketInventoryConfigurationResponse deleteBucketInventoryConfigurationResponse = DeleteBucketInventoryConfigurationResponse.builder().build();
        when(mockS3Client.deleteBucketInventoryConfiguration(any(Consumer.class)))
                .thenReturn(deleteBucketInventoryConfigurationResponse);

        // Run the test
        myClassUnderTest.tryDeleteBucketInventoryConfiguration2();

        // Verify the results
    }

    @Test
    void testTryDeleteBucketInventoryConfiguration2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.deleteBucketInventoryConfiguration(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDeleteBucketInventoryConfiguration2());
    }

    @Test
    void testTryDeleteBucketInventoryConfiguration2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.deleteBucketInventoryConfiguration(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteBucketInventoryConfiguration2());
    }

    @Test
    void testTryDeleteBucketInventoryConfiguration2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.deleteBucketInventoryConfiguration(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryDeleteBucketInventoryConfiguration2());
    }

    @Test
    void testTryDeleteBucketLifecycle1() {
        // Setup
        // Configure S3Client.deleteBucketLifecycle(...).
        final DeleteBucketLifecycleResponse deleteBucketLifecycleResponse = DeleteBucketLifecycleResponse.builder().build();
        when(mockS3Client.deleteBucketLifecycle(DeleteBucketLifecycleRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(deleteBucketLifecycleResponse);

        // Run the test
        myClassUnderTest.tryDeleteBucketLifecycle1();

        // Verify the results
    }

    @Test
    void testTryDeleteBucketLifecycle1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.deleteBucketLifecycle(DeleteBucketLifecycleRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDeleteBucketLifecycle1());
    }

    @Test
    void testTryDeleteBucketLifecycle1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.deleteBucketLifecycle(DeleteBucketLifecycleRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteBucketLifecycle1());
    }

    @Test
    void testTryDeleteBucketLifecycle1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.deleteBucketLifecycle(DeleteBucketLifecycleRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryDeleteBucketLifecycle1());
    }

    @Test
    void testTryDeleteBucketLifecycle2() {
        // Setup
        // Configure S3Client.deleteBucketLifecycle(...).
        final DeleteBucketLifecycleResponse deleteBucketLifecycleResponse = DeleteBucketLifecycleResponse.builder().build();
        when(mockS3Client.deleteBucketLifecycle(any(Consumer.class))).thenReturn(deleteBucketLifecycleResponse);

        // Run the test
        myClassUnderTest.tryDeleteBucketLifecycle2();

        // Verify the results
    }

    @Test
    void testTryDeleteBucketLifecycle2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.deleteBucketLifecycle(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDeleteBucketLifecycle2());
    }

    @Test
    void testTryDeleteBucketLifecycle2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.deleteBucketLifecycle(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteBucketLifecycle2());
    }

    @Test
    void testTryDeleteBucketLifecycle2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.deleteBucketLifecycle(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryDeleteBucketLifecycle2());
    }

    @Test
    void testTryDeleteBucketMetricsConfiguration1() {
        // Setup
        // Configure S3Client.deleteBucketMetricsConfiguration(...).
        final DeleteBucketMetricsConfigurationResponse deleteBucketMetricsConfigurationResponse = DeleteBucketMetricsConfigurationResponse.builder().build();
        when(mockS3Client.deleteBucketMetricsConfiguration(DeleteBucketMetricsConfigurationRequest.builder()
                .bucket("bucket")
                .id("id")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(deleteBucketMetricsConfigurationResponse);

        // Run the test
        myClassUnderTest.tryDeleteBucketMetricsConfiguration1();

        // Verify the results
    }

    @Test
    void testTryDeleteBucketMetricsConfiguration1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.deleteBucketMetricsConfiguration(DeleteBucketMetricsConfigurationRequest.builder()
                .bucket("bucket")
                .id("id")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDeleteBucketMetricsConfiguration1());
    }

    @Test
    void testTryDeleteBucketMetricsConfiguration1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.deleteBucketMetricsConfiguration(DeleteBucketMetricsConfigurationRequest.builder()
                .bucket("bucket")
                .id("id")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteBucketMetricsConfiguration1());
    }

    @Test
    void testTryDeleteBucketMetricsConfiguration1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.deleteBucketMetricsConfiguration(DeleteBucketMetricsConfigurationRequest.builder()
                .bucket("bucket")
                .id("id")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryDeleteBucketMetricsConfiguration1());
    }

    @Test
    void testTryDeleteBucketMetricsConfiguration2() {
        // Setup
        // Configure S3Client.deleteBucketMetricsConfiguration(...).
        final DeleteBucketMetricsConfigurationResponse deleteBucketMetricsConfigurationResponse = DeleteBucketMetricsConfigurationResponse.builder().build();
        when(mockS3Client.deleteBucketMetricsConfiguration(any(Consumer.class)))
                .thenReturn(deleteBucketMetricsConfigurationResponse);

        // Run the test
        myClassUnderTest.tryDeleteBucketMetricsConfiguration2();

        // Verify the results
    }

    @Test
    void testTryDeleteBucketMetricsConfiguration2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.deleteBucketMetricsConfiguration(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDeleteBucketMetricsConfiguration2());
    }

    @Test
    void testTryDeleteBucketMetricsConfiguration2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.deleteBucketMetricsConfiguration(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteBucketMetricsConfiguration2());
    }

    @Test
    void testTryDeleteBucketMetricsConfiguration2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.deleteBucketMetricsConfiguration(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryDeleteBucketMetricsConfiguration2());
    }

    @Test
    void testTryDeleteBucketPolicy1() {
        // Setup
        // Configure S3Client.deleteBucketPolicy(...).
        final DeleteBucketPolicyResponse deleteBucketPolicyResponse = DeleteBucketPolicyResponse.builder().build();
        when(mockS3Client.deleteBucketPolicy(DeleteBucketPolicyRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(deleteBucketPolicyResponse);

        // Run the test
        myClassUnderTest.tryDeleteBucketPolicy1();

        // Verify the results
    }

    @Test
    void testTryDeleteBucketPolicy1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.deleteBucketPolicy(DeleteBucketPolicyRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDeleteBucketPolicy1());
    }

    @Test
    void testTryDeleteBucketPolicy1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.deleteBucketPolicy(DeleteBucketPolicyRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteBucketPolicy1());
    }

    @Test
    void testTryDeleteBucketPolicy1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.deleteBucketPolicy(DeleteBucketPolicyRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryDeleteBucketPolicy1());
    }

    @Test
    void testTryDeleteBucketPolicy2() {
        // Setup
        // Configure S3Client.deleteBucketPolicy(...).
        final DeleteBucketPolicyResponse deleteBucketPolicyResponse = DeleteBucketPolicyResponse.builder().build();
        when(mockS3Client.deleteBucketPolicy(any(Consumer.class))).thenReturn(deleteBucketPolicyResponse);

        // Run the test
        myClassUnderTest.tryDeleteBucketPolicy2();

        // Verify the results
    }

    @Test
    void testTryDeleteBucketPolicy2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.deleteBucketPolicy(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDeleteBucketPolicy2());
    }

    @Test
    void testTryDeleteBucketPolicy2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.deleteBucketPolicy(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteBucketPolicy2());
    }

    @Test
    void testTryDeleteBucketPolicy2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.deleteBucketPolicy(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryDeleteBucketPolicy2());
    }

    @Test
    void testTryDeleteBucketReplication1() {
        // Setup
        // Configure S3Client.deleteBucketReplication(...).
        final DeleteBucketReplicationResponse deleteBucketReplicationResponse = DeleteBucketReplicationResponse.builder().build();
        when(mockS3Client.deleteBucketReplication(DeleteBucketReplicationRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(deleteBucketReplicationResponse);

        // Run the test
        myClassUnderTest.tryDeleteBucketReplication1();

        // Verify the results
    }

    @Test
    void testTryDeleteBucketReplication1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.deleteBucketReplication(DeleteBucketReplicationRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDeleteBucketReplication1());
    }

    @Test
    void testTryDeleteBucketReplication1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.deleteBucketReplication(DeleteBucketReplicationRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteBucketReplication1());
    }

    @Test
    void testTryDeleteBucketReplication1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.deleteBucketReplication(DeleteBucketReplicationRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryDeleteBucketReplication1());
    }

    @Test
    void testTryDeleteBucketReplication2() {
        // Setup
        // Configure S3Client.deleteBucketReplication(...).
        final DeleteBucketReplicationResponse deleteBucketReplicationResponse = DeleteBucketReplicationResponse.builder().build();
        when(mockS3Client.deleteBucketReplication(any(Consumer.class))).thenReturn(deleteBucketReplicationResponse);

        // Run the test
        myClassUnderTest.tryDeleteBucketReplication2();

        // Verify the results
    }

    @Test
    void testTryDeleteBucketReplication2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.deleteBucketReplication(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDeleteBucketReplication2());
    }

    @Test
    void testTryDeleteBucketReplication2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.deleteBucketReplication(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteBucketReplication2());
    }

    @Test
    void testTryDeleteBucketReplication2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.deleteBucketReplication(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryDeleteBucketReplication2());
    }

    @Test
    void testTryDeleteBucketTagging1() {
        // Setup
        // Configure S3Client.deleteBucketTagging(...).
        final DeleteBucketTaggingResponse deleteBucketTaggingResponse = DeleteBucketTaggingResponse.builder().build();
        when(mockS3Client.deleteBucketTagging(DeleteBucketTaggingRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(deleteBucketTaggingResponse);

        // Run the test
        myClassUnderTest.tryDeleteBucketTagging1();

        // Verify the results
    }

    @Test
    void testTryDeleteBucketTagging1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.deleteBucketTagging(DeleteBucketTaggingRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDeleteBucketTagging1());
    }

    @Test
    void testTryDeleteBucketTagging1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.deleteBucketTagging(DeleteBucketTaggingRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteBucketTagging1());
    }

    @Test
    void testTryDeleteBucketTagging1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.deleteBucketTagging(DeleteBucketTaggingRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryDeleteBucketTagging1());
    }

    @Test
    void testTryDeleteBucketTagging2() {
        // Setup
        // Configure S3Client.deleteBucketTagging(...).
        final DeleteBucketTaggingResponse deleteBucketTaggingResponse = DeleteBucketTaggingResponse.builder().build();
        when(mockS3Client.deleteBucketTagging(any(Consumer.class))).thenReturn(deleteBucketTaggingResponse);

        // Run the test
        myClassUnderTest.tryDeleteBucketTagging2();

        // Verify the results
    }

    @Test
    void testTryDeleteBucketTagging2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.deleteBucketTagging(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDeleteBucketTagging2());
    }

    @Test
    void testTryDeleteBucketTagging2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.deleteBucketTagging(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteBucketTagging2());
    }

    @Test
    void testTryDeleteBucketTagging2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.deleteBucketTagging(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryDeleteBucketTagging2());
    }

    @Test
    void testTryDeleteBucketWebsite1() {
        // Setup
        // Configure S3Client.deleteBucketWebsite(...).
        final DeleteBucketWebsiteResponse deleteBucketWebsiteResponse = DeleteBucketWebsiteResponse.builder().build();
        when(mockS3Client.deleteBucketWebsite(DeleteBucketWebsiteRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(deleteBucketWebsiteResponse);

        // Run the test
        myClassUnderTest.tryDeleteBucketWebsite1();

        // Verify the results
    }

    @Test
    void testTryDeleteBucketWebsite1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.deleteBucketWebsite(DeleteBucketWebsiteRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDeleteBucketWebsite1());
    }

    @Test
    void testTryDeleteBucketWebsite1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.deleteBucketWebsite(DeleteBucketWebsiteRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteBucketWebsite1());
    }

    @Test
    void testTryDeleteBucketWebsite1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.deleteBucketWebsite(DeleteBucketWebsiteRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryDeleteBucketWebsite1());
    }

    @Test
    void testTryDeleteBucketWebsite2() {
        // Setup
        // Configure S3Client.deleteBucketWebsite(...).
        final DeleteBucketWebsiteResponse deleteBucketWebsiteResponse = DeleteBucketWebsiteResponse.builder().build();
        when(mockS3Client.deleteBucketWebsite(any(Consumer.class))).thenReturn(deleteBucketWebsiteResponse);

        // Run the test
        myClassUnderTest.tryDeleteBucketWebsite2();

        // Verify the results
    }

    @Test
    void testTryDeleteBucketWebsite2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.deleteBucketWebsite(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDeleteBucketWebsite2());
    }

    @Test
    void testTryDeleteBucketWebsite2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.deleteBucketWebsite(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteBucketWebsite2());
    }

    @Test
    void testTryDeleteBucketWebsite2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.deleteBucketWebsite(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryDeleteBucketWebsite2());
    }

    @Test
    void testTryDeleteObject1() {
        // Setup
        when(mockS3Client.deleteObject(DeleteObjectRequest.builder()
                .bucket("bucket")
                .key("key")
                .build())).thenReturn(DeleteObjectResponse.builder().build());

        // Run the test
        myClassUnderTest.tryDeleteObject1();

        // Verify the results
    }

    @Test
    void testTryDeleteObject1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.deleteObject(DeleteObjectRequest.builder()
                .bucket("bucket")
                .key("key")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDeleteObject1());
    }

    @Test
    void testTryDeleteObject1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.deleteObject(DeleteObjectRequest.builder()
                .bucket("bucket")
                .key("key")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteObject1());
    }

    @Test
    void testTryDeleteObject1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.deleteObject(DeleteObjectRequest.builder()
                .bucket("bucket")
                .key("key")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryDeleteObject1());
    }

    @Test
    void testTryDeleteObject2() {
        // Setup
        when(mockS3Client.deleteObject(any(Consumer.class))).thenReturn(DeleteObjectResponse.builder().build());

        // Run the test
        myClassUnderTest.tryDeleteObject2();

        // Verify the results
    }

    @Test
    void testTryDeleteObject2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.deleteObject(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDeleteObject2());
    }

    @Test
    void testTryDeleteObject2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.deleteObject(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteObject2());
    }

    @Test
    void testTryDeleteObject2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.deleteObject(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryDeleteObject2());
    }

    @Test
    void testTryDeleteObjectTagging1() {
        // Setup
        // Configure S3Client.deleteObjectTagging(...).
        final DeleteObjectTaggingResponse deleteObjectTaggingResponse = DeleteObjectTaggingResponse.builder()
                .versionId("versionId")
                .build();
        when(mockS3Client.deleteObjectTagging(DeleteObjectTaggingRequest.builder()
                .bucket("bucket")
                .key("key")
                .versionId("versionId")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(deleteObjectTaggingResponse);

        // Run the test
        myClassUnderTest.tryDeleteObjectTagging1();

        // Verify the results
    }

    @Test
    void testTryDeleteObjectTagging1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.deleteObjectTagging(DeleteObjectTaggingRequest.builder()
                .bucket("bucket")
                .key("key")
                .versionId("versionId")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDeleteObjectTagging1());
    }

    @Test
    void testTryDeleteObjectTagging1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.deleteObjectTagging(DeleteObjectTaggingRequest.builder()
                .bucket("bucket")
                .key("key")
                .versionId("versionId")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteObjectTagging1());
    }

    @Test
    void testTryDeleteObjectTagging1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.deleteObjectTagging(DeleteObjectTaggingRequest.builder()
                .bucket("bucket")
                .key("key")
                .versionId("versionId")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryDeleteObjectTagging1());
    }

    @Test
    void testTryDeleteObjectTagging2() {
        // Setup
        // Configure S3Client.deleteObjectTagging(...).
        final DeleteObjectTaggingResponse deleteObjectTaggingResponse = DeleteObjectTaggingResponse.builder()
                .versionId("versionId")
                .build();
        when(mockS3Client.deleteObjectTagging(any(Consumer.class))).thenReturn(deleteObjectTaggingResponse);

        // Run the test
        myClassUnderTest.tryDeleteObjectTagging2();

        // Verify the results
    }

    @Test
    void testTryDeleteObjectTagging2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.deleteObjectTagging(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDeleteObjectTagging2());
    }

    @Test
    void testTryDeleteObjectTagging2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.deleteObjectTagging(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteObjectTagging2());
    }

    @Test
    void testTryDeleteObjectTagging2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.deleteObjectTagging(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryDeleteObjectTagging2());
    }

    @Test
    void testTryDeleteObjects1() {
        // Setup
        // Configure S3Client.deleteObjects(...).
        final DeleteObjectsResponse deleteObjectsResponse = DeleteObjectsResponse.builder()
                .deleted(DeletedObject.builder()
                        .key("key")
                        .versionId("versionId")
                        .deleteMarker(false)
                        .deleteMarkerVersionId("deleteMarkerVersionId")
                        .build())
                .requestCharged(RequestCharged.REQUESTER)
                .errors(S3Error.builder()
                        .key("key")
                        .versionId("versionId")
                        .code("code")
                        .message("message")
                        .build())
                .build();
        when(mockS3Client.deleteObjects(DeleteObjectsRequest.builder()
                .bucket("bucket")
                .delete(Delete.builder()
                        .objects(ObjectIdentifier.builder()
                                .key("key")
                                .versionId("versionId")
                                .build())
                        .quiet(false)
                        .build())
                .mfa("mfa")
                .requestPayer(RequestPayer.REQUESTER)
                .bypassGovernanceRetention(false)
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(deleteObjectsResponse);

        // Run the test
        myClassUnderTest.tryDeleteObjects1();

        // Verify the results
    }

    @Test
    void testTryDeleteObjects1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.deleteObjects(DeleteObjectsRequest.builder()
                .bucket("bucket")
                .delete(Delete.builder()
                        .objects(ObjectIdentifier.builder()
                                .key("key")
                                .versionId("versionId")
                                .build())
                        .quiet(false)
                        .build())
                .mfa("mfa")
                .requestPayer(RequestPayer.REQUESTER)
                .bypassGovernanceRetention(false)
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDeleteObjects1());
    }

    @Test
    void testTryDeleteObjects1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.deleteObjects(DeleteObjectsRequest.builder()
                .bucket("bucket")
                .delete(Delete.builder()
                        .objects(ObjectIdentifier.builder()
                                .key("key")
                                .versionId("versionId")
                                .build())
                        .quiet(false)
                        .build())
                .mfa("mfa")
                .requestPayer(RequestPayer.REQUESTER)
                .bypassGovernanceRetention(false)
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteObjects1());
    }

    @Test
    void testTryDeleteObjects1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.deleteObjects(DeleteObjectsRequest.builder()
                .bucket("bucket")
                .delete(Delete.builder()
                        .objects(ObjectIdentifier.builder()
                                .key("key")
                                .versionId("versionId")
                                .build())
                        .quiet(false)
                        .build())
                .mfa("mfa")
                .requestPayer(RequestPayer.REQUESTER)
                .bypassGovernanceRetention(false)
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryDeleteObjects1());
    }

    @Test
    void testTryDeleteObjects2() {
        // Setup
        // Configure S3Client.deleteObjects(...).
        final DeleteObjectsResponse deleteObjectsResponse = DeleteObjectsResponse.builder()
                .deleted(DeletedObject.builder()
                        .key("key")
                        .versionId("versionId")
                        .deleteMarker(false)
                        .deleteMarkerVersionId("deleteMarkerVersionId")
                        .build())
                .requestCharged(RequestCharged.REQUESTER)
                .errors(S3Error.builder()
                        .key("key")
                        .versionId("versionId")
                        .code("code")
                        .message("message")
                        .build())
                .build();
        when(mockS3Client.deleteObjects(any(Consumer.class))).thenReturn(deleteObjectsResponse);

        // Run the test
        myClassUnderTest.tryDeleteObjects2();

        // Verify the results
    }

    @Test
    void testTryDeleteObjects2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.deleteObjects(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDeleteObjects2());
    }

    @Test
    void testTryDeleteObjects2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.deleteObjects(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteObjects2());
    }

    @Test
    void testTryDeleteObjects2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.deleteObjects(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryDeleteObjects2());
    }

    @Test
    void testTryDeletePublicAccessBlock1() {
        // Setup
        // Configure S3Client.deletePublicAccessBlock(...).
        final DeletePublicAccessBlockResponse deletePublicAccessBlockResponse = DeletePublicAccessBlockResponse.builder().build();
        when(mockS3Client.deletePublicAccessBlock(DeletePublicAccessBlockRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(deletePublicAccessBlockResponse);

        // Run the test
        myClassUnderTest.tryDeletePublicAccessBlock1();

        // Verify the results
    }

    @Test
    void testTryDeletePublicAccessBlock1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.deletePublicAccessBlock(DeletePublicAccessBlockRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDeletePublicAccessBlock1());
    }

    @Test
    void testTryDeletePublicAccessBlock1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.deletePublicAccessBlock(DeletePublicAccessBlockRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeletePublicAccessBlock1());
    }

    @Test
    void testTryDeletePublicAccessBlock1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.deletePublicAccessBlock(DeletePublicAccessBlockRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryDeletePublicAccessBlock1());
    }

    @Test
    void testTryDeletePublicAccessBlock2() {
        // Setup
        // Configure S3Client.deletePublicAccessBlock(...).
        final DeletePublicAccessBlockResponse deletePublicAccessBlockResponse = DeletePublicAccessBlockResponse.builder().build();
        when(mockS3Client.deletePublicAccessBlock(any(Consumer.class))).thenReturn(deletePublicAccessBlockResponse);

        // Run the test
        myClassUnderTest.tryDeletePublicAccessBlock2();

        // Verify the results
    }

    @Test
    void testTryDeletePublicAccessBlock2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.deletePublicAccessBlock(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDeletePublicAccessBlock2());
    }

    @Test
    void testTryDeletePublicAccessBlock2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.deletePublicAccessBlock(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeletePublicAccessBlock2());
    }

    @Test
    void testTryDeletePublicAccessBlock2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.deletePublicAccessBlock(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryDeletePublicAccessBlock2());
    }

    @Test
    void testTryGetBucketAccelerateConfiguration1() {
        // Setup
        // Configure S3Client.getBucketAccelerateConfiguration(...).
        final GetBucketAccelerateConfigurationResponse getBucketAccelerateConfigurationResponse = GetBucketAccelerateConfigurationResponse.builder()
                .status(BucketAccelerateStatus.ENABLED)
                .build();
        when(mockS3Client.getBucketAccelerateConfiguration(GetBucketAccelerateConfigurationRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(getBucketAccelerateConfigurationResponse);

        // Run the test
        myClassUnderTest.tryGetBucketAccelerateConfiguration1();

        // Verify the results
    }

    @Test
    void testTryGetBucketAccelerateConfiguration1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.getBucketAccelerateConfiguration(GetBucketAccelerateConfigurationRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetBucketAccelerateConfiguration1());
    }

    @Test
    void testTryGetBucketAccelerateConfiguration1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.getBucketAccelerateConfiguration(GetBucketAccelerateConfigurationRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetBucketAccelerateConfiguration1());
    }

    @Test
    void testTryGetBucketAccelerateConfiguration1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.getBucketAccelerateConfiguration(GetBucketAccelerateConfigurationRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryGetBucketAccelerateConfiguration1());
    }

    @Test
    void testTryGetBucketAccelerateConfiguration2() {
        // Setup
        // Configure S3Client.getBucketAccelerateConfiguration(...).
        final GetBucketAccelerateConfigurationResponse getBucketAccelerateConfigurationResponse = GetBucketAccelerateConfigurationResponse.builder()
                .status(BucketAccelerateStatus.ENABLED)
                .build();
        when(mockS3Client.getBucketAccelerateConfiguration(any(Consumer.class)))
                .thenReturn(getBucketAccelerateConfigurationResponse);

        // Run the test
        myClassUnderTest.tryGetBucketAccelerateConfiguration2();

        // Verify the results
    }

    @Test
    void testTryGetBucketAccelerateConfiguration2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.getBucketAccelerateConfiguration(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetBucketAccelerateConfiguration2());
    }

    @Test
    void testTryGetBucketAccelerateConfiguration2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.getBucketAccelerateConfiguration(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetBucketAccelerateConfiguration2());
    }

    @Test
    void testTryGetBucketAccelerateConfiguration2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.getBucketAccelerateConfiguration(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryGetBucketAccelerateConfiguration2());
    }

    @Test
    void testTryGetBucketAcl1() {
        // Setup
        // Configure S3Client.getBucketAcl(...).
        final GetBucketAclResponse getBucketAclResponse = GetBucketAclResponse.builder()
                .owner(Owner.builder()
                        .displayName("displayName")
                        .id("id")
                        .build())
                .grants(Grant.builder()
                        .grantee(Grantee.builder()
                                .displayName("displayName")
                                .emailAddress("emailAddress")
                                .id("id")
                                .type(Type.CANONICAL_USER)
                                .uri("uri")
                                .build())
                        .permission(Permission.FULL_CONTROL)
                        .build())
                .build();
        when(mockS3Client.getBucketAcl(GetBucketAclRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(getBucketAclResponse);

        // Run the test
        myClassUnderTest.tryGetBucketAcl1();

        // Verify the results
    }

    @Test
    void testTryGetBucketAcl1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.getBucketAcl(GetBucketAclRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetBucketAcl1());
    }

    @Test
    void testTryGetBucketAcl1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.getBucketAcl(GetBucketAclRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetBucketAcl1());
    }

    @Test
    void testTryGetBucketAcl1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.getBucketAcl(GetBucketAclRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryGetBucketAcl1());
    }

    @Test
    void testTryGetBucketAcl2() {
        // Setup
        // Configure S3Client.getBucketAcl(...).
        final GetBucketAclResponse getBucketAclResponse = GetBucketAclResponse.builder()
                .owner(Owner.builder()
                        .displayName("displayName")
                        .id("id")
                        .build())
                .grants(Grant.builder()
                        .grantee(Grantee.builder()
                                .displayName("displayName")
                                .emailAddress("emailAddress")
                                .id("id")
                                .type(Type.CANONICAL_USER)
                                .uri("uri")
                                .build())
                        .permission(Permission.FULL_CONTROL)
                        .build())
                .build();
        when(mockS3Client.getBucketAcl(any(Consumer.class))).thenReturn(getBucketAclResponse);

        // Run the test
        myClassUnderTest.tryGetBucketAcl2();

        // Verify the results
    }

    @Test
    void testTryGetBucketAcl2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.getBucketAcl(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetBucketAcl2());
    }

    @Test
    void testTryGetBucketAcl2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.getBucketAcl(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetBucketAcl2());
    }

    @Test
    void testTryGetBucketAcl2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.getBucketAcl(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryGetBucketAcl2());
    }

    @Test
    void testTryGetBucketAnalyticsConfiguration1() {
        // Setup
        // Configure S3Client.getBucketAnalyticsConfiguration(...).
        final GetBucketAnalyticsConfigurationResponse getBucketAnalyticsConfigurationResponse = GetBucketAnalyticsConfigurationResponse.builder()
                .analyticsConfiguration(AnalyticsConfiguration.builder()
                        .id("id")
                        .filter(AnalyticsFilter.builder()
                                .prefix("prefix")
                                .tag(Tag.builder()
                                        .key("key")
                                        .value("value")
                                        .build())
                                .and(AnalyticsAndOperator.builder()
                                        .prefix("prefix")
                                        .tags(Tag.builder()
                                                .key("key")
                                                .value("value")
                                                .build())
                                        .build())
                                .build())
                        .storageClassAnalysis(StorageClassAnalysis.builder()
                                .dataExport(StorageClassAnalysisDataExport.builder()
                                        .outputSchemaVersion(StorageClassAnalysisSchemaVersion.V_1)
                                        .destination(AnalyticsExportDestination.builder()
                                                .s3BucketDestination(AnalyticsS3BucketDestination.builder()
                                                        .format(AnalyticsS3ExportFileFormat.CSV)
                                                        .bucketAccountId("bucketAccountId")
                                                        .bucket("bucket")
                                                        .prefix("prefix")
                                                        .build())
                                                .build())
                                        .build())
                                .build())
                        .build())
                .build();
        when(mockS3Client.getBucketAnalyticsConfiguration(GetBucketAnalyticsConfigurationRequest.builder()
                .bucket("bucket")
                .id("id")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(getBucketAnalyticsConfigurationResponse);

        // Run the test
        myClassUnderTest.tryGetBucketAnalyticsConfiguration1();

        // Verify the results
    }

    @Test
    void testTryGetBucketAnalyticsConfiguration1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.getBucketAnalyticsConfiguration(GetBucketAnalyticsConfigurationRequest.builder()
                .bucket("bucket")
                .id("id")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetBucketAnalyticsConfiguration1());
    }

    @Test
    void testTryGetBucketAnalyticsConfiguration1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.getBucketAnalyticsConfiguration(GetBucketAnalyticsConfigurationRequest.builder()
                .bucket("bucket")
                .id("id")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetBucketAnalyticsConfiguration1());
    }

    @Test
    void testTryGetBucketAnalyticsConfiguration1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.getBucketAnalyticsConfiguration(GetBucketAnalyticsConfigurationRequest.builder()
                .bucket("bucket")
                .id("id")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryGetBucketAnalyticsConfiguration1());
    }

    @Test
    void testTryGetBucketAnalyticsConfiguration2() {
        // Setup
        // Configure S3Client.getBucketAnalyticsConfiguration(...).
        final GetBucketAnalyticsConfigurationResponse getBucketAnalyticsConfigurationResponse = GetBucketAnalyticsConfigurationResponse.builder()
                .analyticsConfiguration(AnalyticsConfiguration.builder()
                        .id("id")
                        .filter(AnalyticsFilter.builder()
                                .prefix("prefix")
                                .tag(Tag.builder()
                                        .key("key")
                                        .value("value")
                                        .build())
                                .and(AnalyticsAndOperator.builder()
                                        .prefix("prefix")
                                        .tags(Tag.builder()
                                                .key("key")
                                                .value("value")
                                                .build())
                                        .build())
                                .build())
                        .storageClassAnalysis(StorageClassAnalysis.builder()
                                .dataExport(StorageClassAnalysisDataExport.builder()
                                        .outputSchemaVersion(StorageClassAnalysisSchemaVersion.V_1)
                                        .destination(AnalyticsExportDestination.builder()
                                                .s3BucketDestination(AnalyticsS3BucketDestination.builder()
                                                        .format(AnalyticsS3ExportFileFormat.CSV)
                                                        .bucketAccountId("bucketAccountId")
                                                        .bucket("bucket")
                                                        .prefix("prefix")
                                                        .build())
                                                .build())
                                        .build())
                                .build())
                        .build())
                .build();
        when(mockS3Client.getBucketAnalyticsConfiguration(any(Consumer.class)))
                .thenReturn(getBucketAnalyticsConfigurationResponse);

        // Run the test
        myClassUnderTest.tryGetBucketAnalyticsConfiguration2();

        // Verify the results
    }

    @Test
    void testTryGetBucketAnalyticsConfiguration2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.getBucketAnalyticsConfiguration(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetBucketAnalyticsConfiguration2());
    }

    @Test
    void testTryGetBucketAnalyticsConfiguration2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.getBucketAnalyticsConfiguration(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetBucketAnalyticsConfiguration2());
    }

    @Test
    void testTryGetBucketAnalyticsConfiguration2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.getBucketAnalyticsConfiguration(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryGetBucketAnalyticsConfiguration2());
    }

    @Test
    void testTryGetBucketCors1() {
        // Setup
        // Configure S3Client.getBucketCors(...).
        final GetBucketCorsResponse getBucketCorsResponse = GetBucketCorsResponse.builder()
                .corsRules(CORSRule.builder()
                        .allowedHeaders("allowedHeaders")
                        .allowedMethods("allowedMethods")
                        .allowedOrigins("allowedOrigins")
                        .exposeHeaders("exposeHeaders")
                        .maxAgeSeconds(0)
                        .build())
                .build();
        when(mockS3Client.getBucketCors(GetBucketCorsRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(getBucketCorsResponse);

        // Run the test
        myClassUnderTest.tryGetBucketCors1();

        // Verify the results
    }

    @Test
    void testTryGetBucketCors1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.getBucketCors(GetBucketCorsRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetBucketCors1());
    }

    @Test
    void testTryGetBucketCors1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.getBucketCors(GetBucketCorsRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetBucketCors1());
    }

    @Test
    void testTryGetBucketCors1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.getBucketCors(GetBucketCorsRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryGetBucketCors1());
    }

    @Test
    void testTryGetBucketCors2() {
        // Setup
        // Configure S3Client.getBucketCors(...).
        final GetBucketCorsResponse getBucketCorsResponse = GetBucketCorsResponse.builder()
                .corsRules(CORSRule.builder()
                        .allowedHeaders("allowedHeaders")
                        .allowedMethods("allowedMethods")
                        .allowedOrigins("allowedOrigins")
                        .exposeHeaders("exposeHeaders")
                        .maxAgeSeconds(0)
                        .build())
                .build();
        when(mockS3Client.getBucketCors(any(Consumer.class))).thenReturn(getBucketCorsResponse);

        // Run the test
        myClassUnderTest.tryGetBucketCors2();

        // Verify the results
    }

    @Test
    void testTryGetBucketCors2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.getBucketCors(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetBucketCors2());
    }

    @Test
    void testTryGetBucketCors2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.getBucketCors(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetBucketCors2());
    }

    @Test
    void testTryGetBucketCors2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.getBucketCors(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryGetBucketCors2());
    }

    @Test
    void testTryGetBucketEncryption1() {
        // Setup
        // Configure S3Client.getBucketEncryption(...).
        final GetBucketEncryptionResponse getBucketEncryptionResponse = GetBucketEncryptionResponse.builder()
                .serverSideEncryptionConfiguration(ServerSideEncryptionConfiguration.builder()
                        .rules(ServerSideEncryptionRule.builder()
                                .applyServerSideEncryptionByDefault(ServerSideEncryptionByDefault.builder()
                                        .sseAlgorithm(ServerSideEncryption.AES256)
                                        .kmsMasterKeyID("kmsMasterKeyID")
                                        .build())
                                .build())
                        .build())
                .build();
        when(mockS3Client.getBucketEncryption(GetBucketEncryptionRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(getBucketEncryptionResponse);

        // Run the test
        myClassUnderTest.tryGetBucketEncryption1();

        // Verify the results
    }

    @Test
    void testTryGetBucketEncryption1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.getBucketEncryption(GetBucketEncryptionRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetBucketEncryption1());
    }

    @Test
    void testTryGetBucketEncryption1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.getBucketEncryption(GetBucketEncryptionRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetBucketEncryption1());
    }

    @Test
    void testTryGetBucketEncryption1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.getBucketEncryption(GetBucketEncryptionRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryGetBucketEncryption1());
    }

    @Test
    void testTryGetBucketEncryption2() {
        // Setup
        // Configure S3Client.getBucketEncryption(...).
        final GetBucketEncryptionResponse getBucketEncryptionResponse = GetBucketEncryptionResponse.builder()
                .serverSideEncryptionConfiguration(ServerSideEncryptionConfiguration.builder()
                        .rules(ServerSideEncryptionRule.builder()
                                .applyServerSideEncryptionByDefault(ServerSideEncryptionByDefault.builder()
                                        .sseAlgorithm(ServerSideEncryption.AES256)
                                        .kmsMasterKeyID("kmsMasterKeyID")
                                        .build())
                                .build())
                        .build())
                .build();
        when(mockS3Client.getBucketEncryption(any(Consumer.class))).thenReturn(getBucketEncryptionResponse);

        // Run the test
        myClassUnderTest.tryGetBucketEncryption2();

        // Verify the results
    }

    @Test
    void testTryGetBucketEncryption2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.getBucketEncryption(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetBucketEncryption2());
    }

    @Test
    void testTryGetBucketEncryption2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.getBucketEncryption(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetBucketEncryption2());
    }

    @Test
    void testTryGetBucketEncryption2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.getBucketEncryption(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryGetBucketEncryption2());
    }

    @Test
    void testTryGetBucketInventoryConfiguration1() {
        // Setup
        // Configure S3Client.getBucketInventoryConfiguration(...).
        final GetBucketInventoryConfigurationResponse getBucketInventoryConfigurationResponse = GetBucketInventoryConfigurationResponse.builder()
                .inventoryConfiguration(InventoryConfiguration.builder()
                        .destination(InventoryDestination.builder()
                                .s3BucketDestination(InventoryS3BucketDestination.builder()
                                        .accountId("accountId")
                                        .bucket("bucket")
                                        .format(InventoryFormat.CSV)
                                        .prefix("prefix")
                                        .encryption(InventoryEncryption.builder()
                                                .sses3(SSES3.builder().build())
                                                .ssekms(SSEKMS.builder()
                                                        .keyId("keyId")
                                                        .build())
                                                .build())
                                        .build())
                                .build())
                        .isEnabled(false)
                        .filter(InventoryFilter.builder()
                                .prefix("prefix")
                                .build())
                        .id("id")
                        .includedObjectVersions(InventoryIncludedObjectVersions.ALL)
                        .optionalFields(InventoryOptionalField.SIZE)
                        .schedule(InventorySchedule.builder()
                                .frequency(InventoryFrequency.DAILY)
                                .build())
                        .build())
                .build();
        when(mockS3Client.getBucketInventoryConfiguration(GetBucketInventoryConfigurationRequest.builder()
                .bucket("bucket")
                .id("id")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(getBucketInventoryConfigurationResponse);

        // Run the test
        myClassUnderTest.tryGetBucketInventoryConfiguration1();

        // Verify the results
    }

    @Test
    void testTryGetBucketInventoryConfiguration1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.getBucketInventoryConfiguration(GetBucketInventoryConfigurationRequest.builder()
                .bucket("bucket")
                .id("id")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetBucketInventoryConfiguration1());
    }

    @Test
    void testTryGetBucketInventoryConfiguration1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.getBucketInventoryConfiguration(GetBucketInventoryConfigurationRequest.builder()
                .bucket("bucket")
                .id("id")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetBucketInventoryConfiguration1());
    }

    @Test
    void testTryGetBucketInventoryConfiguration1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.getBucketInventoryConfiguration(GetBucketInventoryConfigurationRequest.builder()
                .bucket("bucket")
                .id("id")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryGetBucketInventoryConfiguration1());
    }

    @Test
    void testTryGetBucketInventoryConfiguration2() {
        // Setup
        // Configure S3Client.getBucketInventoryConfiguration(...).
        final GetBucketInventoryConfigurationResponse getBucketInventoryConfigurationResponse = GetBucketInventoryConfigurationResponse.builder()
                .inventoryConfiguration(InventoryConfiguration.builder()
                        .destination(InventoryDestination.builder()
                                .s3BucketDestination(InventoryS3BucketDestination.builder()
                                        .accountId("accountId")
                                        .bucket("bucket")
                                        .format(InventoryFormat.CSV)
                                        .prefix("prefix")
                                        .encryption(InventoryEncryption.builder()
                                                .sses3(SSES3.builder().build())
                                                .ssekms(SSEKMS.builder()
                                                        .keyId("keyId")
                                                        .build())
                                                .build())
                                        .build())
                                .build())
                        .isEnabled(false)
                        .filter(InventoryFilter.builder()
                                .prefix("prefix")
                                .build())
                        .id("id")
                        .includedObjectVersions(InventoryIncludedObjectVersions.ALL)
                        .optionalFields(InventoryOptionalField.SIZE)
                        .schedule(InventorySchedule.builder()
                                .frequency(InventoryFrequency.DAILY)
                                .build())
                        .build())
                .build();
        when(mockS3Client.getBucketInventoryConfiguration(any(Consumer.class)))
                .thenReturn(getBucketInventoryConfigurationResponse);

        // Run the test
        myClassUnderTest.tryGetBucketInventoryConfiguration2();

        // Verify the results
    }

    @Test
    void testTryGetBucketInventoryConfiguration2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.getBucketInventoryConfiguration(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetBucketInventoryConfiguration2());
    }

    @Test
    void testTryGetBucketInventoryConfiguration2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.getBucketInventoryConfiguration(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetBucketInventoryConfiguration2());
    }

    @Test
    void testTryGetBucketInventoryConfiguration2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.getBucketInventoryConfiguration(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryGetBucketInventoryConfiguration2());
    }

    @Test
    void testTryGetBucketLifecycleConfiguration1() {
        // Setup
        // Configure S3Client.getBucketLifecycleConfiguration(...).
        final GetBucketLifecycleConfigurationResponse getBucketLifecycleConfigurationResponse = GetBucketLifecycleConfigurationResponse.builder()
                .rules(LifecycleRule.builder()
                        .expiration(LifecycleExpiration.builder()
                                .date(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                .days(0)
                                .expiredObjectDeleteMarker(false)
                                .build())
                        .id("id")
                        .prefix("prefix")
                        .filter(LifecycleRuleFilter.builder()
                                .prefix("prefix")
                                .tag(Tag.builder()
                                        .key("key")
                                        .value("value")
                                        .build())
                                .and(LifecycleRuleAndOperator.builder()
                                        .prefix("prefix")
                                        .tags(Tag.builder()
                                                .key("key")
                                                .value("value")
                                                .build())
                                        .build())
                                .build())
                        .status(ExpirationStatus.ENABLED)
                        .transitions(Transition.builder()
                                .date(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                .days(0)
                                .storageClass(TransitionStorageClass.GLACIER)
                                .build())
                        .noncurrentVersionTransitions(NoncurrentVersionTransition.builder()
                                .noncurrentDays(0)
                                .storageClass(TransitionStorageClass.GLACIER)
                                .build())
                        .noncurrentVersionExpiration(NoncurrentVersionExpiration.builder()
                                .noncurrentDays(0)
                                .build())
                        .abortIncompleteMultipartUpload(AbortIncompleteMultipartUpload.builder().build())
                        .build())
                .build();
        when(mockS3Client.getBucketLifecycleConfiguration(GetBucketLifecycleConfigurationRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(getBucketLifecycleConfigurationResponse);

        // Run the test
        myClassUnderTest.tryGetBucketLifecycleConfiguration1();

        // Verify the results
    }

    @Test
    void testTryGetBucketLifecycleConfiguration1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.getBucketLifecycleConfiguration(GetBucketLifecycleConfigurationRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetBucketLifecycleConfiguration1());
    }

    @Test
    void testTryGetBucketLifecycleConfiguration1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.getBucketLifecycleConfiguration(GetBucketLifecycleConfigurationRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetBucketLifecycleConfiguration1());
    }

    @Test
    void testTryGetBucketLifecycleConfiguration1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.getBucketLifecycleConfiguration(GetBucketLifecycleConfigurationRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryGetBucketLifecycleConfiguration1());
    }

    @Test
    void testTryGetBucketLifecycleConfiguration2() {
        // Setup
        // Configure S3Client.getBucketLifecycleConfiguration(...).
        final GetBucketLifecycleConfigurationResponse getBucketLifecycleConfigurationResponse = GetBucketLifecycleConfigurationResponse.builder()
                .rules(LifecycleRule.builder()
                        .expiration(LifecycleExpiration.builder()
                                .date(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                .days(0)
                                .expiredObjectDeleteMarker(false)
                                .build())
                        .id("id")
                        .prefix("prefix")
                        .filter(LifecycleRuleFilter.builder()
                                .prefix("prefix")
                                .tag(Tag.builder()
                                        .key("key")
                                        .value("value")
                                        .build())
                                .and(LifecycleRuleAndOperator.builder()
                                        .prefix("prefix")
                                        .tags(Tag.builder()
                                                .key("key")
                                                .value("value")
                                                .build())
                                        .build())
                                .build())
                        .status(ExpirationStatus.ENABLED)
                        .transitions(Transition.builder()
                                .date(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                .days(0)
                                .storageClass(TransitionStorageClass.GLACIER)
                                .build())
                        .noncurrentVersionTransitions(NoncurrentVersionTransition.builder()
                                .noncurrentDays(0)
                                .storageClass(TransitionStorageClass.GLACIER)
                                .build())
                        .noncurrentVersionExpiration(NoncurrentVersionExpiration.builder()
                                .noncurrentDays(0)
                                .build())
                        .abortIncompleteMultipartUpload(AbortIncompleteMultipartUpload.builder().build())
                        .build())
                .build();
        when(mockS3Client.getBucketLifecycleConfiguration(any(Consumer.class)))
                .thenReturn(getBucketLifecycleConfigurationResponse);

        // Run the test
        myClassUnderTest.tryGetBucketLifecycleConfiguration2();

        // Verify the results
    }

    @Test
    void testTryGetBucketLifecycleConfiguration2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.getBucketLifecycleConfiguration(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetBucketLifecycleConfiguration2());
    }

    @Test
    void testTryGetBucketLifecycleConfiguration2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.getBucketLifecycleConfiguration(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetBucketLifecycleConfiguration2());
    }

    @Test
    void testTryGetBucketLifecycleConfiguration2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.getBucketLifecycleConfiguration(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryGetBucketLifecycleConfiguration2());
    }

    @Test
    void testTryGetBucketLocation1() {
        // Setup
        // Configure S3Client.getBucketLocation(...).
        final GetBucketLocationResponse getBucketLocationResponse = GetBucketLocationResponse.builder()
                .locationConstraint(BucketLocationConstraint.US_EAST_2)
                .build();
        when(mockS3Client.getBucketLocation(GetBucketLocationRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(getBucketLocationResponse);

        // Run the test
        myClassUnderTest.tryGetBucketLocation1();

        // Verify the results
    }

    @Test
    void testTryGetBucketLocation1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.getBucketLocation(GetBucketLocationRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetBucketLocation1());
    }

    @Test
    void testTryGetBucketLocation1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.getBucketLocation(GetBucketLocationRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetBucketLocation1());
    }

    @Test
    void testTryGetBucketLocation1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.getBucketLocation(GetBucketLocationRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryGetBucketLocation1());
    }

    @Test
    void testTryGetBucketLocation2() {
        // Setup
        // Configure S3Client.getBucketLocation(...).
        final GetBucketLocationResponse getBucketLocationResponse = GetBucketLocationResponse.builder()
                .locationConstraint(BucketLocationConstraint.US_EAST_2)
                .build();
        when(mockS3Client.getBucketLocation(any(Consumer.class))).thenReturn(getBucketLocationResponse);

        // Run the test
        myClassUnderTest.tryGetBucketLocation2();

        // Verify the results
    }

    @Test
    void testTryGetBucketLocation2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.getBucketLocation(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetBucketLocation2());
    }

    @Test
    void testTryGetBucketLocation2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.getBucketLocation(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetBucketLocation2());
    }

    @Test
    void testTryGetBucketLocation2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.getBucketLocation(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryGetBucketLocation2());
    }

    @Test
    void testTryGetBucketLogging1() {
        // Setup
        // Configure S3Client.getBucketLogging(...).
        final GetBucketLoggingResponse getBucketLoggingResponse = GetBucketLoggingResponse.builder()
                .loggingEnabled(LoggingEnabled.builder()
                        .targetBucket("targetBucket")
                        .targetGrants(TargetGrant.builder()
                                .grantee(Grantee.builder()
                                        .displayName("displayName")
                                        .emailAddress("emailAddress")
                                        .id("id")
                                        .type(Type.CANONICAL_USER)
                                        .uri("uri")
                                        .build())
                                .permission(BucketLogsPermission.FULL_CONTROL)
                                .build())
                        .targetPrefix("targetPrefix")
                        .build())
                .build();
        when(mockS3Client.getBucketLogging(GetBucketLoggingRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(getBucketLoggingResponse);

        // Run the test
        myClassUnderTest.tryGetBucketLogging1();

        // Verify the results
    }

    @Test
    void testTryGetBucketLogging1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.getBucketLogging(GetBucketLoggingRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetBucketLogging1());
    }

    @Test
    void testTryGetBucketLogging1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.getBucketLogging(GetBucketLoggingRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetBucketLogging1());
    }

    @Test
    void testTryGetBucketLogging1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.getBucketLogging(GetBucketLoggingRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryGetBucketLogging1());
    }

    @Test
    void testTryGetBucketLogging2() {
        // Setup
        // Configure S3Client.getBucketLogging(...).
        final GetBucketLoggingResponse getBucketLoggingResponse = GetBucketLoggingResponse.builder()
                .loggingEnabled(LoggingEnabled.builder()
                        .targetBucket("targetBucket")
                        .targetGrants(TargetGrant.builder()
                                .grantee(Grantee.builder()
                                        .displayName("displayName")
                                        .emailAddress("emailAddress")
                                        .id("id")
                                        .type(Type.CANONICAL_USER)
                                        .uri("uri")
                                        .build())
                                .permission(BucketLogsPermission.FULL_CONTROL)
                                .build())
                        .targetPrefix("targetPrefix")
                        .build())
                .build();
        when(mockS3Client.getBucketLogging(any(Consumer.class))).thenReturn(getBucketLoggingResponse);

        // Run the test
        myClassUnderTest.tryGetBucketLogging2();

        // Verify the results
    }

    @Test
    void testTryGetBucketLogging2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.getBucketLogging(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetBucketLogging2());
    }

    @Test
    void testTryGetBucketLogging2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.getBucketLogging(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetBucketLogging2());
    }

    @Test
    void testTryGetBucketLogging2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.getBucketLogging(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryGetBucketLogging2());
    }

    @Test
    void testTryGetBucketMetricsConfiguration1() {
        // Setup
        // Configure S3Client.getBucketMetricsConfiguration(...).
        final GetBucketMetricsConfigurationResponse getBucketMetricsConfigurationResponse = GetBucketMetricsConfigurationResponse.builder()
                .metricsConfiguration(MetricsConfiguration.builder()
                        .id("id")
                        .filter(MetricsFilter.builder()
                                .prefix("prefix")
                                .tag(Tag.builder()
                                        .key("key")
                                        .value("value")
                                        .build())
                                .and(MetricsAndOperator.builder()
                                        .prefix("prefix")
                                        .tags(Tag.builder()
                                                .key("key")
                                                .value("value")
                                                .build())
                                        .build())
                                .build())
                        .build())
                .build();
        when(mockS3Client.getBucketMetricsConfiguration(GetBucketMetricsConfigurationRequest.builder()
                .bucket("bucket")
                .id("id")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(getBucketMetricsConfigurationResponse);

        // Run the test
        myClassUnderTest.tryGetBucketMetricsConfiguration1();

        // Verify the results
    }

    @Test
    void testTryGetBucketMetricsConfiguration1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.getBucketMetricsConfiguration(GetBucketMetricsConfigurationRequest.builder()
                .bucket("bucket")
                .id("id")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetBucketMetricsConfiguration1());
    }

    @Test
    void testTryGetBucketMetricsConfiguration1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.getBucketMetricsConfiguration(GetBucketMetricsConfigurationRequest.builder()
                .bucket("bucket")
                .id("id")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetBucketMetricsConfiguration1());
    }

    @Test
    void testTryGetBucketMetricsConfiguration1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.getBucketMetricsConfiguration(GetBucketMetricsConfigurationRequest.builder()
                .bucket("bucket")
                .id("id")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryGetBucketMetricsConfiguration1());
    }

    @Test
    void testTryGetBucketMetricsConfiguration2() {
        // Setup
        // Configure S3Client.getBucketMetricsConfiguration(...).
        final GetBucketMetricsConfigurationResponse getBucketMetricsConfigurationResponse = GetBucketMetricsConfigurationResponse.builder()
                .metricsConfiguration(MetricsConfiguration.builder()
                        .id("id")
                        .filter(MetricsFilter.builder()
                                .prefix("prefix")
                                .tag(Tag.builder()
                                        .key("key")
                                        .value("value")
                                        .build())
                                .and(MetricsAndOperator.builder()
                                        .prefix("prefix")
                                        .tags(Tag.builder()
                                                .key("key")
                                                .value("value")
                                                .build())
                                        .build())
                                .build())
                        .build())
                .build();
        when(mockS3Client.getBucketMetricsConfiguration(any(Consumer.class)))
                .thenReturn(getBucketMetricsConfigurationResponse);

        // Run the test
        myClassUnderTest.tryGetBucketMetricsConfiguration2();

        // Verify the results
    }

    @Test
    void testTryGetBucketMetricsConfiguration2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.getBucketMetricsConfiguration(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetBucketMetricsConfiguration2());
    }

    @Test
    void testTryGetBucketMetricsConfiguration2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.getBucketMetricsConfiguration(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetBucketMetricsConfiguration2());
    }

    @Test
    void testTryGetBucketMetricsConfiguration2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.getBucketMetricsConfiguration(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryGetBucketMetricsConfiguration2());
    }

    @Test
    void testTryGetBucketNotificationConfiguration1() {
        // Setup
        // Configure S3Client.getBucketNotificationConfiguration(...).
        final GetBucketNotificationConfigurationResponse getBucketNotificationConfigurationResponse = GetBucketNotificationConfigurationResponse.builder()
                .topicConfigurations(TopicConfiguration.builder()
                        .id("id")
                        .topicArn("topicArn")
                        .events(Event.S3_REDUCED_REDUNDANCY_LOST_OBJECT)
                        .filter(NotificationConfigurationFilter.builder()
                                .key(S3KeyFilter.builder()
                                        .filterRules(FilterRule.builder()
                                                .name(FilterRuleName.PREFIX)
                                                .value("value")
                                                .build())
                                        .build())
                                .build())
                        .build())
                .queueConfigurations(QueueConfiguration.builder()
                        .id("id")
                        .queueArn("queueArn")
                        .events(Event.S3_REDUCED_REDUNDANCY_LOST_OBJECT)
                        .filter(NotificationConfigurationFilter.builder()
                                .key(S3KeyFilter.builder()
                                        .filterRules(FilterRule.builder()
                                                .name(FilterRuleName.PREFIX)
                                                .value("value")
                                                .build())
                                        .build())
                                .build())
                        .build())
                .lambdaFunctionConfigurations(LambdaFunctionConfiguration.builder()
                        .id("id")
                        .lambdaFunctionArn("lambdaFunctionArn")
                        .events(Event.S3_REDUCED_REDUNDANCY_LOST_OBJECT)
                        .filter(NotificationConfigurationFilter.builder()
                                .key(S3KeyFilter.builder()
                                        .filterRules(FilterRule.builder()
                                                .name(FilterRuleName.PREFIX)
                                                .value("value")
                                                .build())
                                        .build())
                                .build())
                        .build())
                .build();
        when(mockS3Client.getBucketNotificationConfiguration(GetBucketNotificationConfigurationRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(getBucketNotificationConfigurationResponse);

        // Run the test
        myClassUnderTest.tryGetBucketNotificationConfiguration1();

        // Verify the results
    }

    @Test
    void testTryGetBucketNotificationConfiguration1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.getBucketNotificationConfiguration(GetBucketNotificationConfigurationRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetBucketNotificationConfiguration1());
    }

    @Test
    void testTryGetBucketNotificationConfiguration1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.getBucketNotificationConfiguration(GetBucketNotificationConfigurationRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetBucketNotificationConfiguration1());
    }

    @Test
    void testTryGetBucketNotificationConfiguration1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.getBucketNotificationConfiguration(GetBucketNotificationConfigurationRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryGetBucketNotificationConfiguration1());
    }

    @Test
    void testTryGetBucketNotificationConfiguration2() {
        // Setup
        // Configure S3Client.getBucketNotificationConfiguration(...).
        final GetBucketNotificationConfigurationResponse getBucketNotificationConfigurationResponse = GetBucketNotificationConfigurationResponse.builder()
                .topicConfigurations(TopicConfiguration.builder()
                        .id("id")
                        .topicArn("topicArn")
                        .events(Event.S3_REDUCED_REDUNDANCY_LOST_OBJECT)
                        .filter(NotificationConfigurationFilter.builder()
                                .key(S3KeyFilter.builder()
                                        .filterRules(FilterRule.builder()
                                                .name(FilterRuleName.PREFIX)
                                                .value("value")
                                                .build())
                                        .build())
                                .build())
                        .build())
                .queueConfigurations(QueueConfiguration.builder()
                        .id("id")
                        .queueArn("queueArn")
                        .events(Event.S3_REDUCED_REDUNDANCY_LOST_OBJECT)
                        .filter(NotificationConfigurationFilter.builder()
                                .key(S3KeyFilter.builder()
                                        .filterRules(FilterRule.builder()
                                                .name(FilterRuleName.PREFIX)
                                                .value("value")
                                                .build())
                                        .build())
                                .build())
                        .build())
                .lambdaFunctionConfigurations(LambdaFunctionConfiguration.builder()
                        .id("id")
                        .lambdaFunctionArn("lambdaFunctionArn")
                        .events(Event.S3_REDUCED_REDUNDANCY_LOST_OBJECT)
                        .filter(NotificationConfigurationFilter.builder()
                                .key(S3KeyFilter.builder()
                                        .filterRules(FilterRule.builder()
                                                .name(FilterRuleName.PREFIX)
                                                .value("value")
                                                .build())
                                        .build())
                                .build())
                        .build())
                .build();
        when(mockS3Client.getBucketNotificationConfiguration(any(Consumer.class)))
                .thenReturn(getBucketNotificationConfigurationResponse);

        // Run the test
        myClassUnderTest.tryGetBucketNotificationConfiguration2();

        // Verify the results
    }

    @Test
    void testTryGetBucketNotificationConfiguration2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.getBucketNotificationConfiguration(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetBucketNotificationConfiguration2());
    }

    @Test
    void testTryGetBucketNotificationConfiguration2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.getBucketNotificationConfiguration(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetBucketNotificationConfiguration2());
    }

    @Test
    void testTryGetBucketNotificationConfiguration2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.getBucketNotificationConfiguration(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryGetBucketNotificationConfiguration2());
    }

    @Test
    void testTryGetBucketPolicy1() {
        // Setup
        // Configure S3Client.getBucketPolicy(...).
        final GetBucketPolicyResponse getBucketPolicyResponse = GetBucketPolicyResponse.builder()
                .policy("policy")
                .build();
        when(mockS3Client.getBucketPolicy(GetBucketPolicyRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(getBucketPolicyResponse);

        // Run the test
        myClassUnderTest.tryGetBucketPolicy1();

        // Verify the results
    }

    @Test
    void testTryGetBucketPolicy1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.getBucketPolicy(GetBucketPolicyRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetBucketPolicy1());
    }

    @Test
    void testTryGetBucketPolicy1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.getBucketPolicy(GetBucketPolicyRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetBucketPolicy1());
    }

    @Test
    void testTryGetBucketPolicy1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.getBucketPolicy(GetBucketPolicyRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryGetBucketPolicy1());
    }

    @Test
    void testTryGetBucketPolicy2() {
        // Setup
        // Configure S3Client.getBucketPolicy(...).
        final GetBucketPolicyResponse getBucketPolicyResponse = GetBucketPolicyResponse.builder()
                .policy("policy")
                .build();
        when(mockS3Client.getBucketPolicy(any(Consumer.class))).thenReturn(getBucketPolicyResponse);

        // Run the test
        myClassUnderTest.tryGetBucketPolicy2();

        // Verify the results
    }

    @Test
    void testTryGetBucketPolicy2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.getBucketPolicy(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetBucketPolicy2());
    }

    @Test
    void testTryGetBucketPolicy2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.getBucketPolicy(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetBucketPolicy2());
    }

    @Test
    void testTryGetBucketPolicy2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.getBucketPolicy(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryGetBucketPolicy2());
    }

    @Test
    void testTryGetBucketPolicyStatus1() {
        // Setup
        // Configure S3Client.getBucketPolicyStatus(...).
        final GetBucketPolicyStatusResponse getBucketPolicyStatusResponse = GetBucketPolicyStatusResponse.builder()
                .policyStatus(PolicyStatus.builder()
                        .isPublic(false)
                        .build())
                .build();
        when(mockS3Client.getBucketPolicyStatus(GetBucketPolicyStatusRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(getBucketPolicyStatusResponse);

        // Run the test
        myClassUnderTest.tryGetBucketPolicyStatus1();

        // Verify the results
    }

    @Test
    void testTryGetBucketPolicyStatus1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.getBucketPolicyStatus(GetBucketPolicyStatusRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetBucketPolicyStatus1());
    }

    @Test
    void testTryGetBucketPolicyStatus1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.getBucketPolicyStatus(GetBucketPolicyStatusRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetBucketPolicyStatus1());
    }

    @Test
    void testTryGetBucketPolicyStatus1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.getBucketPolicyStatus(GetBucketPolicyStatusRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryGetBucketPolicyStatus1());
    }

    @Test
    void testTryGetBucketPolicyStatus2() {
        // Setup
        // Configure S3Client.getBucketPolicyStatus(...).
        final GetBucketPolicyStatusResponse getBucketPolicyStatusResponse = GetBucketPolicyStatusResponse.builder()
                .policyStatus(PolicyStatus.builder()
                        .isPublic(false)
                        .build())
                .build();
        when(mockS3Client.getBucketPolicyStatus(any(Consumer.class))).thenReturn(getBucketPolicyStatusResponse);

        // Run the test
        myClassUnderTest.tryGetBucketPolicyStatus2();

        // Verify the results
    }

    @Test
    void testTryGetBucketPolicyStatus2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.getBucketPolicyStatus(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetBucketPolicyStatus2());
    }

    @Test
    void testTryGetBucketPolicyStatus2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.getBucketPolicyStatus(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetBucketPolicyStatus2());
    }

    @Test
    void testTryGetBucketPolicyStatus2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.getBucketPolicyStatus(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryGetBucketPolicyStatus2());
    }

    @Test
    void testTryGetBucketReplication1() {
        // Setup
        // Configure S3Client.getBucketReplication(...).
        final GetBucketReplicationResponse getBucketReplicationResponse = GetBucketReplicationResponse.builder()
                .replicationConfiguration(ReplicationConfiguration.builder()
                        .role("role")
                        .rules(ReplicationRule.builder()
                                .id("id")
                                .priority(0)
                                .prefix("prefix")
                                .filter(ReplicationRuleFilter.builder()
                                        .prefix("prefix")
                                        .tag(Tag.builder()
                                                .key("key")
                                                .value("value")
                                                .build())
                                        .and(ReplicationRuleAndOperator.builder()
                                                .prefix("prefix")
                                                .tags(Tag.builder()
                                                        .key("key")
                                                        .value("value")
                                                        .build())
                                                .build())
                                        .build())
                                .status(ReplicationRuleStatus.ENABLED)
                                .sourceSelectionCriteria(SourceSelectionCriteria.builder()
                                        .sseKmsEncryptedObjects(SseKmsEncryptedObjects.builder()
                                                .status(SseKmsEncryptedObjectsStatus.ENABLED)
                                                .build())
                                        .build())
                                .existingObjectReplication(ExistingObjectReplication.builder()
                                        .status(ExistingObjectReplicationStatus.ENABLED)
                                        .build())
                                .destination(Destination.builder()
                                        .bucket("bucket")
                                        .account("account")
                                        .storageClass(StorageClass.STANDARD)
                                        .accessControlTranslation(AccessControlTranslation.builder()
                                                .owner(OwnerOverride.DESTINATION)
                                                .build())
                                        .encryptionConfiguration(EncryptionConfiguration.builder().build())
                                        .build())
                                .build())
                        .build())
                .build();
        when(mockS3Client.getBucketReplication(GetBucketReplicationRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(getBucketReplicationResponse);

        // Run the test
        myClassUnderTest.tryGetBucketReplication1();

        // Verify the results
    }

    @Test
    void testTryGetBucketReplication1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.getBucketReplication(GetBucketReplicationRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetBucketReplication1());
    }

    @Test
    void testTryGetBucketReplication1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.getBucketReplication(GetBucketReplicationRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetBucketReplication1());
    }

    @Test
    void testTryGetBucketReplication1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.getBucketReplication(GetBucketReplicationRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryGetBucketReplication1());
    }

    @Test
    void testTryGetBucketReplication2() {
        // Setup
        // Configure S3Client.getBucketReplication(...).
        final GetBucketReplicationResponse getBucketReplicationResponse = GetBucketReplicationResponse.builder()
                .replicationConfiguration(ReplicationConfiguration.builder()
                        .role("role")
                        .rules(ReplicationRule.builder()
                                .id("id")
                                .priority(0)
                                .prefix("prefix")
                                .filter(ReplicationRuleFilter.builder()
                                        .prefix("prefix")
                                        .tag(Tag.builder()
                                                .key("key")
                                                .value("value")
                                                .build())
                                        .and(ReplicationRuleAndOperator.builder()
                                                .prefix("prefix")
                                                .tags(Tag.builder()
                                                        .key("key")
                                                        .value("value")
                                                        .build())
                                                .build())
                                        .build())
                                .status(ReplicationRuleStatus.ENABLED)
                                .sourceSelectionCriteria(SourceSelectionCriteria.builder()
                                        .sseKmsEncryptedObjects(SseKmsEncryptedObjects.builder()
                                                .status(SseKmsEncryptedObjectsStatus.ENABLED)
                                                .build())
                                        .build())
                                .existingObjectReplication(ExistingObjectReplication.builder()
                                        .status(ExistingObjectReplicationStatus.ENABLED)
                                        .build())
                                .destination(Destination.builder()
                                        .bucket("bucket")
                                        .account("account")
                                        .storageClass(StorageClass.STANDARD)
                                        .accessControlTranslation(AccessControlTranslation.builder()
                                                .owner(OwnerOverride.DESTINATION)
                                                .build())
                                        .encryptionConfiguration(EncryptionConfiguration.builder().build())
                                        .build())
                                .build())
                        .build())
                .build();
        when(mockS3Client.getBucketReplication(any(Consumer.class))).thenReturn(getBucketReplicationResponse);

        // Run the test
        myClassUnderTest.tryGetBucketReplication2();

        // Verify the results
    }

    @Test
    void testTryGetBucketReplication2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.getBucketReplication(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetBucketReplication2());
    }

    @Test
    void testTryGetBucketReplication2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.getBucketReplication(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetBucketReplication2());
    }

    @Test
    void testTryGetBucketReplication2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.getBucketReplication(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryGetBucketReplication2());
    }

    @Test
    void testTryGetBucketRequestPayment1() {
        // Setup
        // Configure S3Client.getBucketRequestPayment(...).
        final GetBucketRequestPaymentResponse getBucketRequestPaymentResponse = GetBucketRequestPaymentResponse.builder()
                .payer(Payer.REQUESTER)
                .build();
        when(mockS3Client.getBucketRequestPayment(GetBucketRequestPaymentRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(getBucketRequestPaymentResponse);

        // Run the test
        myClassUnderTest.tryGetBucketRequestPayment1();

        // Verify the results
    }

    @Test
    void testTryGetBucketRequestPayment1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.getBucketRequestPayment(GetBucketRequestPaymentRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetBucketRequestPayment1());
    }

    @Test
    void testTryGetBucketRequestPayment1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.getBucketRequestPayment(GetBucketRequestPaymentRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetBucketRequestPayment1());
    }

    @Test
    void testTryGetBucketRequestPayment1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.getBucketRequestPayment(GetBucketRequestPaymentRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryGetBucketRequestPayment1());
    }

    @Test
    void testTryGetBucketRequestPayment2() {
        // Setup
        // Configure S3Client.getBucketRequestPayment(...).
        final GetBucketRequestPaymentResponse getBucketRequestPaymentResponse = GetBucketRequestPaymentResponse.builder()
                .payer(Payer.REQUESTER)
                .build();
        when(mockS3Client.getBucketRequestPayment(any(Consumer.class))).thenReturn(getBucketRequestPaymentResponse);

        // Run the test
        myClassUnderTest.tryGetBucketRequestPayment2();

        // Verify the results
    }

    @Test
    void testTryGetBucketRequestPayment2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.getBucketRequestPayment(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetBucketRequestPayment2());
    }

    @Test
    void testTryGetBucketRequestPayment2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.getBucketRequestPayment(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetBucketRequestPayment2());
    }

    @Test
    void testTryGetBucketRequestPayment2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.getBucketRequestPayment(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryGetBucketRequestPayment2());
    }

    @Test
    void testTryGetBucketTagging1() {
        // Setup
        // Configure S3Client.getBucketTagging(...).
        final GetBucketTaggingResponse getBucketTaggingResponse = GetBucketTaggingResponse.builder()
                .tagSet(Tag.builder()
                        .key("key")
                        .value("value")
                        .build())
                .build();
        when(mockS3Client.getBucketTagging(GetBucketTaggingRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(getBucketTaggingResponse);

        // Run the test
        myClassUnderTest.tryGetBucketTagging1();

        // Verify the results
    }

    @Test
    void testTryGetBucketTagging1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.getBucketTagging(GetBucketTaggingRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetBucketTagging1());
    }

    @Test
    void testTryGetBucketTagging1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.getBucketTagging(GetBucketTaggingRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetBucketTagging1());
    }

    @Test
    void testTryGetBucketTagging1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.getBucketTagging(GetBucketTaggingRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryGetBucketTagging1());
    }

    @Test
    void testTryGetBucketTagging2() {
        // Setup
        // Configure S3Client.getBucketTagging(...).
        final GetBucketTaggingResponse getBucketTaggingResponse = GetBucketTaggingResponse.builder()
                .tagSet(Tag.builder()
                        .key("key")
                        .value("value")
                        .build())
                .build();
        when(mockS3Client.getBucketTagging(any(Consumer.class))).thenReturn(getBucketTaggingResponse);

        // Run the test
        myClassUnderTest.tryGetBucketTagging2();

        // Verify the results
    }

    @Test
    void testTryGetBucketTagging2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.getBucketTagging(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetBucketTagging2());
    }

    @Test
    void testTryGetBucketTagging2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.getBucketTagging(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetBucketTagging2());
    }

    @Test
    void testTryGetBucketTagging2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.getBucketTagging(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryGetBucketTagging2());
    }

    @Test
    void testTryGetBucketVersioning1() {
        // Setup
        // Configure S3Client.getBucketVersioning(...).
        final GetBucketVersioningResponse getBucketVersioningResponse = GetBucketVersioningResponse.builder()
                .status(BucketVersioningStatus.ENABLED)
                .mfaDelete(MFADeleteStatus.ENABLED)
                .build();
        when(mockS3Client.getBucketVersioning(GetBucketVersioningRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(getBucketVersioningResponse);

        // Run the test
        myClassUnderTest.tryGetBucketVersioning1();

        // Verify the results
    }

    @Test
    void testTryGetBucketVersioning1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.getBucketVersioning(GetBucketVersioningRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetBucketVersioning1());
    }

    @Test
    void testTryGetBucketVersioning1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.getBucketVersioning(GetBucketVersioningRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetBucketVersioning1());
    }

    @Test
    void testTryGetBucketVersioning1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.getBucketVersioning(GetBucketVersioningRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryGetBucketVersioning1());
    }

    @Test
    void testTryGetBucketVersioning2() {
        // Setup
        // Configure S3Client.getBucketVersioning(...).
        final GetBucketVersioningResponse getBucketVersioningResponse = GetBucketVersioningResponse.builder()
                .status(BucketVersioningStatus.ENABLED)
                .mfaDelete(MFADeleteStatus.ENABLED)
                .build();
        when(mockS3Client.getBucketVersioning(any(Consumer.class))).thenReturn(getBucketVersioningResponse);

        // Run the test
        myClassUnderTest.tryGetBucketVersioning2();

        // Verify the results
    }

    @Test
    void testTryGetBucketVersioning2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.getBucketVersioning(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetBucketVersioning2());
    }

    @Test
    void testTryGetBucketVersioning2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.getBucketVersioning(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetBucketVersioning2());
    }

    @Test
    void testTryGetBucketVersioning2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.getBucketVersioning(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryGetBucketVersioning2());
    }

    @Test
    void testTryGetBucketWebsite1() {
        // Setup
        // Configure S3Client.getBucketWebsite(...).
        final GetBucketWebsiteResponse getBucketWebsiteResponse = GetBucketWebsiteResponse.builder()
                .redirectAllRequestsTo(RedirectAllRequestsTo.builder()
                        .hostName("hostName")
                        .protocol(Protocol.HTTP)
                        .build())
                .indexDocument(IndexDocument.builder()
                        .suffix("suffix")
                        .build())
                .errorDocument(ErrorDocument.builder()
                        .key("key")
                        .build())
                .routingRules(RoutingRule.builder()
                        .condition(Condition.builder()
                                .httpErrorCodeReturnedEquals("httpErrorCodeReturnedEquals")
                                .keyPrefixEquals("keyPrefixEquals")
                                .build())
                        .redirect(Redirect.builder()
                                .hostName("hostName")
                                .httpRedirectCode("httpRedirectCode")
                                .protocol(Protocol.HTTP)
                                .replaceKeyPrefixWith("replaceKeyPrefixWith")
                                .replaceKeyWith("replaceKeyWith")
                                .build())
                        .build())
                .build();
        when(mockS3Client.getBucketWebsite(GetBucketWebsiteRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(getBucketWebsiteResponse);

        // Run the test
        myClassUnderTest.tryGetBucketWebsite1();

        // Verify the results
    }

    @Test
    void testTryGetBucketWebsite1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.getBucketWebsite(GetBucketWebsiteRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetBucketWebsite1());
    }

    @Test
    void testTryGetBucketWebsite1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.getBucketWebsite(GetBucketWebsiteRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetBucketWebsite1());
    }

    @Test
    void testTryGetBucketWebsite1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.getBucketWebsite(GetBucketWebsiteRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryGetBucketWebsite1());
    }

    @Test
    void testTryGetBucketWebsite2() {
        // Setup
        // Configure S3Client.getBucketWebsite(...).
        final GetBucketWebsiteResponse getBucketWebsiteResponse = GetBucketWebsiteResponse.builder()
                .redirectAllRequestsTo(RedirectAllRequestsTo.builder()
                        .hostName("hostName")
                        .protocol(Protocol.HTTP)
                        .build())
                .indexDocument(IndexDocument.builder()
                        .suffix("suffix")
                        .build())
                .errorDocument(ErrorDocument.builder()
                        .key("key")
                        .build())
                .routingRules(RoutingRule.builder()
                        .condition(Condition.builder()
                                .httpErrorCodeReturnedEquals("httpErrorCodeReturnedEquals")
                                .keyPrefixEquals("keyPrefixEquals")
                                .build())
                        .redirect(Redirect.builder()
                                .hostName("hostName")
                                .httpRedirectCode("httpRedirectCode")
                                .protocol(Protocol.HTTP)
                                .replaceKeyPrefixWith("replaceKeyPrefixWith")
                                .replaceKeyWith("replaceKeyWith")
                                .build())
                        .build())
                .build();
        when(mockS3Client.getBucketWebsite(any(Consumer.class))).thenReturn(getBucketWebsiteResponse);

        // Run the test
        myClassUnderTest.tryGetBucketWebsite2();

        // Verify the results
    }

    @Test
    void testTryGetBucketWebsite2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.getBucketWebsite(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetBucketWebsite2());
    }

    @Test
    void testTryGetBucketWebsite2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.getBucketWebsite(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetBucketWebsite2());
    }

    @Test
    void testTryGetBucketWebsite2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.getBucketWebsite(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryGetBucketWebsite2());
    }

    @Test
    void testTryGetObjectToInputStream() throws Exception {
        // Setup
        // Configure S3Client.getObject(...).
        final ResponseInputStream<GetObjectResponse> spyResponseInputStream = spy(
                new ResponseInputStream<>(GetObjectResponse.builder().build(),
                        AbortableInputStream.create(new ByteArrayInputStream("objectContent".getBytes()))));
        when(mockS3Client.getObject(eq(GetObjectRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build()), any(ResponseTransformer.class))).thenReturn(spyResponseInputStream);

        // Run the test
        myClassUnderTest.tryGetObjectToInputStream();

        // Verify the results
        verify(spyResponseInputStream).close();
    }

    @Test
    void testTryGetObjectToInputStream_S3ClientReturnsNoContent() throws Exception {
        // Setup
        // Configure S3Client.getObject(...).
        final ResponseInputStream<GetObjectResponse> spyResponseInputStream = spy(
                new ResponseInputStream<>(GetObjectResponse.builder().build(),
                        AbortableInputStream.create(new ByteArrayInputStream(new byte[]{}))));
        when(mockS3Client.getObject(eq(GetObjectRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build()), any(ResponseTransformer.class))).thenReturn(spyResponseInputStream);

        // Run the test
        myClassUnderTest.tryGetObjectToInputStream();

        // Verify the results
        verify(spyResponseInputStream).close();
    }

    @Test
    void testTryGetObjectToInputStream_S3ClientReturnsBrokenIo() throws Exception {
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
        when(mockS3Client.getObject(eq(GetObjectRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build()), any(ResponseTransformer.class))).thenReturn(spyResponseInputStream);

        // Run the test
        myClassUnderTest.tryGetObjectToInputStream();

        // Verify the results
        verify(spyResponseInputStream).close();
    }

    @Test
    void testTryGetObjectToInputStream_S3ClientThrowsNoSuchKeyException() {
        // Setup
        when(mockS3Client.getObject(eq(GetObjectRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build()), any(ResponseTransformer.class))).thenThrow(NoSuchKeyException.class);

        // Run the test
        assertThrows(NoSuchKeyException.class, () -> myClassUnderTest.tryGetObjectToInputStream());
    }

    @Test
    void testTryGetObjectToInputStream_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.getObject(eq(GetObjectRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build()), any(ResponseTransformer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetObjectToInputStream());
    }

    @Test
    void testTryGetObjectToInputStream_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.getObject(eq(GetObjectRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build()), any(ResponseTransformer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetObjectToInputStream());
    }

    @Test
    void testTryGetObjectToInputStream_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.getObject(eq(GetObjectRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build()), any(ResponseTransformer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryGetObjectToInputStream());
    }

    @Test
    void testTryGetObjectToFile() {
        // Setup
        // Run the test
        myClassUnderTest.tryGetObjectToFile();

        // Verify the results
        verify(mockS3Client).getObject(eq(GetObjectRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build()), any(ResponseTransformer.class));
    }

    @Test
    void testTryGetObjectToFile_S3ClientThrowsNoSuchKeyException() {
        // Setup
        when(mockS3Client.getObject(eq(GetObjectRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build()), any(ResponseTransformer.class))).thenThrow(NoSuchKeyException.class);

        // Run the test
        assertThrows(NoSuchKeyException.class, () -> myClassUnderTest.tryGetObjectToFile());
    }

    @Test
    void testTryGetObjectToFile_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.getObject(eq(GetObjectRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build()), any(ResponseTransformer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetObjectToFile());
    }

    @Test
    void testTryGetObjectToFile_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.getObject(eq(GetObjectRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build()), any(ResponseTransformer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetObjectToFile());
    }

    @Test
    void testTryGetObjectToFile_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.getObject(eq(GetObjectRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build()), any(ResponseTransformer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryGetObjectToFile());
    }

    @Test
    void testTryGetObject3() {
        // Setup
        when(mockS3Client.getObject(GetObjectRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build(), Paths.get("filename.txt"))).thenReturn(GetObjectResponse.builder().build());

        // Run the test
        myClassUnderTest.tryGetObject3();

        // Verify the results
    }

    @Test
    void testTryGetObject3_S3ClientThrowsNoSuchKeyException() {
        // Setup
        when(mockS3Client.getObject(GetObjectRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build(), Paths.get("filename.txt"))).thenThrow(NoSuchKeyException.class);

        // Run the test
        assertThrows(NoSuchKeyException.class, () -> myClassUnderTest.tryGetObject3());
    }

    @Test
    void testTryGetObject3_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.getObject(GetObjectRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build(), Paths.get("filename.txt"))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetObject3());
    }

    @Test
    void testTryGetObject3_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.getObject(GetObjectRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build(), Paths.get("filename.txt"))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetObject3());
    }

    @Test
    void testTryGetObject3_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.getObject(GetObjectRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build(), Paths.get("filename.txt"))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryGetObject3());
    }

    @Test
    void testTryGetObject4() {
        // Setup
        when(mockS3Client.getObject(any(Consumer.class), eq(Paths.get("filename.txt"))))
                .thenReturn(GetObjectResponse.builder().build());

        // Run the test
        myClassUnderTest.tryGetObject4();

        // Verify the results
    }

    @Test
    void testTryGetObject4_S3ClientThrowsNoSuchKeyException() {
        // Setup
        when(mockS3Client.getObject(any(Consumer.class), eq(Paths.get("filename.txt"))))
                .thenThrow(NoSuchKeyException.class);

        // Run the test
        assertThrows(NoSuchKeyException.class, () -> myClassUnderTest.tryGetObject4());
    }

    @Test
    void testTryGetObject4_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.getObject(any(Consumer.class), eq(Paths.get("filename.txt"))))
                .thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetObject4());
    }

    @Test
    void testTryGetObject4_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.getObject(any(Consumer.class), eq(Paths.get("filename.txt"))))
                .thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetObject4());
    }

    @Test
    void testTryGetObject4_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.getObject(any(Consumer.class), eq(Paths.get("filename.txt")))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryGetObject4());
    }

    @Test
    void testTryGetObject5() throws Exception {
        // Setup
        // Configure S3Client.getObject(...).
        final ResponseInputStream<GetObjectResponse> spyResponseInputStream = spy(
                new ResponseInputStream<>(GetObjectResponse.builder().build(),
                        AbortableInputStream.create(new ByteArrayInputStream("objectContent".getBytes()))));
        when(mockS3Client.getObject(GetObjectRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build())).thenReturn(spyResponseInputStream);

        // Run the test
        myClassUnderTest.tryGetObject5();

        // Verify the results
        verify(spyResponseInputStream).close();
    }

    @Test
    void testTryGetObject5_S3ClientReturnsNoContent() throws Exception {
        // Setup
        // Configure S3Client.getObject(...).
        final ResponseInputStream<GetObjectResponse> spyResponseInputStream = spy(
                new ResponseInputStream<>(GetObjectResponse.builder().build(),
                        AbortableInputStream.create(new ByteArrayInputStream(new byte[]{}))));
        when(mockS3Client.getObject(GetObjectRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build())).thenReturn(spyResponseInputStream);

        // Run the test
        myClassUnderTest.tryGetObject5();

        // Verify the results
        verify(spyResponseInputStream).close();
    }

    @Test
    void testTryGetObject5_S3ClientReturnsBrokenIo() throws Exception {
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
                .key("key")
                .build())).thenReturn(spyResponseInputStream);

        // Run the test
        myClassUnderTest.tryGetObject5();

        // Verify the results
        verify(spyResponseInputStream).close();
    }

    @Test
    void testTryGetObject5_S3ClientThrowsNoSuchKeyException() {
        // Setup
        when(mockS3Client.getObject(GetObjectRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build())).thenThrow(NoSuchKeyException.class);

        // Run the test
        assertThrows(NoSuchKeyException.class, () -> myClassUnderTest.tryGetObject5());
    }

    @Test
    void testTryGetObject5_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.getObject(GetObjectRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetObject5());
    }

    @Test
    void testTryGetObject5_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.getObject(GetObjectRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetObject5());
    }

    @Test
    void testTryGetObject5_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.getObject(GetObjectRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryGetObject5());
    }

    @Test
    void testTryGetObject6() throws Exception {
        // Setup
        // Configure S3Client.getObject(...).
        final ResponseInputStream<GetObjectResponse> spyResponseInputStream = spy(
                new ResponseInputStream<>(GetObjectResponse.builder().build(),
                        AbortableInputStream.create(new ByteArrayInputStream("objectContent".getBytes()))));
        when(mockS3Client.getObject(any(Consumer.class))).thenReturn(spyResponseInputStream);

        // Run the test
        myClassUnderTest.tryGetObject6();

        // Verify the results
        verify(spyResponseInputStream).close();
    }

    @Test
    void testTryGetObject6_S3ClientReturnsNoContent() throws Exception {
        // Setup
        // Configure S3Client.getObject(...).
        final ResponseInputStream<GetObjectResponse> spyResponseInputStream = spy(
                new ResponseInputStream<>(GetObjectResponse.builder().build(),
                        AbortableInputStream.create(new ByteArrayInputStream(new byte[]{}))));
        when(mockS3Client.getObject(any(Consumer.class))).thenReturn(spyResponseInputStream);

        // Run the test
        myClassUnderTest.tryGetObject6();

        // Verify the results
        verify(spyResponseInputStream).close();
    }

    @Test
    void testTryGetObject6_S3ClientReturnsBrokenIo() throws Exception {
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
        myClassUnderTest.tryGetObject6();

        // Verify the results
        verify(spyResponseInputStream).close();
    }

    @Test
    void testTryGetObject6_S3ClientThrowsNoSuchKeyException() {
        // Setup
        when(mockS3Client.getObject(any(Consumer.class))).thenThrow(NoSuchKeyException.class);

        // Run the test
        assertThrows(NoSuchKeyException.class, () -> myClassUnderTest.tryGetObject6());
    }

    @Test
    void testTryGetObject6_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.getObject(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetObject6());
    }

    @Test
    void testTryGetObject6_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.getObject(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetObject6());
    }

    @Test
    void testTryGetObject6_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.getObject(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryGetObject6());
    }

    @Test
    void testTryGetObjectAsBytes1() {
        // Setup
        // Configure S3Client.getObjectAsBytes(...).
        final ResponseBytes<GetObjectResponse> getObjectResponseBytes = ResponseBytes.fromByteArray(
                GetObjectResponse.builder().build(), "content".getBytes());
        when(mockS3Client.getObjectAsBytes(GetObjectRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build())).thenReturn(getObjectResponseBytes);

        // Run the test
        myClassUnderTest.tryGetObjectAsBytes1();

        // Verify the results
    }

    @Test
    void testTryGetObjectAsBytes1_S3ClientThrowsNoSuchKeyException() {
        // Setup
        when(mockS3Client.getObjectAsBytes(GetObjectRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build())).thenThrow(NoSuchKeyException.class);

        // Run the test
        assertThrows(NoSuchKeyException.class, () -> myClassUnderTest.tryGetObjectAsBytes1());
    }

    @Test
    void testTryGetObjectAsBytes1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.getObjectAsBytes(GetObjectRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetObjectAsBytes1());
    }

    @Test
    void testTryGetObjectAsBytes1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.getObjectAsBytes(GetObjectRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetObjectAsBytes1());
    }

    @Test
    void testTryGetObjectAsBytes1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.getObjectAsBytes(GetObjectRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryGetObjectAsBytes1());
    }

    @Test
    void testTryGetObjectAsBytes2() {
        // Setup
        // Configure S3Client.getObjectAsBytes(...).
        final ResponseBytes<GetObjectResponse> getObjectResponseBytes = ResponseBytes.fromByteArray(
                GetObjectResponse.builder().build(), "content".getBytes());
        when(mockS3Client.getObjectAsBytes(any(Consumer.class))).thenReturn(getObjectResponseBytes);

        // Run the test
        myClassUnderTest.tryGetObjectAsBytes2();

        // Verify the results
    }

    @Test
    void testTryGetObjectAsBytes2_S3ClientThrowsNoSuchKeyException() {
        // Setup
        when(mockS3Client.getObjectAsBytes(any(Consumer.class))).thenThrow(NoSuchKeyException.class);

        // Run the test
        assertThrows(NoSuchKeyException.class, () -> myClassUnderTest.tryGetObjectAsBytes2());
    }

    @Test
    void testTryGetObjectAsBytes2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.getObjectAsBytes(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetObjectAsBytes2());
    }

    @Test
    void testTryGetObjectAsBytes2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.getObjectAsBytes(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetObjectAsBytes2());
    }

    @Test
    void testTryGetObjectAsBytes2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.getObjectAsBytes(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryGetObjectAsBytes2());
    }

    @Test
    void testTryGetObjectAcl1() {
        // Setup
        // Configure S3Client.getObjectAcl(...).
        final GetObjectAclResponse getObjectAclResponse = GetObjectAclResponse.builder()
                .owner(Owner.builder()
                        .displayName("displayName")
                        .id("id")
                        .build())
                .grants(Grant.builder()
                        .grantee(Grantee.builder()
                                .displayName("displayName")
                                .emailAddress("emailAddress")
                                .id("id")
                                .type(Type.CANONICAL_USER)
                                .uri("uri")
                                .build())
                        .permission(Permission.FULL_CONTROL)
                        .build())
                .requestCharged(RequestCharged.REQUESTER)
                .build();
        when(mockS3Client.getObjectAcl(GetObjectAclRequest.builder()
                .bucket("bucket")
                .key("key")
                .versionId("versionId")
                .requestPayer(RequestPayer.REQUESTER)
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(getObjectAclResponse);

        // Run the test
        myClassUnderTest.tryGetObjectAcl1();

        // Verify the results
    }

    @Test
    void testTryGetObjectAcl1_S3ClientThrowsNoSuchKeyException() {
        // Setup
        when(mockS3Client.getObjectAcl(GetObjectAclRequest.builder()
                .bucket("bucket")
                .key("key")
                .versionId("versionId")
                .requestPayer(RequestPayer.REQUESTER)
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(NoSuchKeyException.class);

        // Run the test
        assertThrows(NoSuchKeyException.class, () -> myClassUnderTest.tryGetObjectAcl1());
    }

    @Test
    void testTryGetObjectAcl1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.getObjectAcl(GetObjectAclRequest.builder()
                .bucket("bucket")
                .key("key")
                .versionId("versionId")
                .requestPayer(RequestPayer.REQUESTER)
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetObjectAcl1());
    }

    @Test
    void testTryGetObjectAcl1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.getObjectAcl(GetObjectAclRequest.builder()
                .bucket("bucket")
                .key("key")
                .versionId("versionId")
                .requestPayer(RequestPayer.REQUESTER)
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetObjectAcl1());
    }

    @Test
    void testTryGetObjectAcl1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.getObjectAcl(GetObjectAclRequest.builder()
                .bucket("bucket")
                .key("key")
                .versionId("versionId")
                .requestPayer(RequestPayer.REQUESTER)
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryGetObjectAcl1());
    }

    @Test
    void testTryGetObjectAcl2() {
        // Setup
        // Configure S3Client.getObjectAcl(...).
        final GetObjectAclResponse getObjectAclResponse = GetObjectAclResponse.builder()
                .owner(Owner.builder()
                        .displayName("displayName")
                        .id("id")
                        .build())
                .grants(Grant.builder()
                        .grantee(Grantee.builder()
                                .displayName("displayName")
                                .emailAddress("emailAddress")
                                .id("id")
                                .type(Type.CANONICAL_USER)
                                .uri("uri")
                                .build())
                        .permission(Permission.FULL_CONTROL)
                        .build())
                .requestCharged(RequestCharged.REQUESTER)
                .build();
        when(mockS3Client.getObjectAcl(any(Consumer.class))).thenReturn(getObjectAclResponse);

        // Run the test
        myClassUnderTest.tryGetObjectAcl2();

        // Verify the results
    }

    @Test
    void testTryGetObjectAcl2_S3ClientThrowsNoSuchKeyException() {
        // Setup
        when(mockS3Client.getObjectAcl(any(Consumer.class))).thenThrow(NoSuchKeyException.class);

        // Run the test
        assertThrows(NoSuchKeyException.class, () -> myClassUnderTest.tryGetObjectAcl2());
    }

    @Test
    void testTryGetObjectAcl2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.getObjectAcl(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetObjectAcl2());
    }

    @Test
    void testTryGetObjectAcl2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.getObjectAcl(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetObjectAcl2());
    }

    @Test
    void testTryGetObjectAcl2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.getObjectAcl(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryGetObjectAcl2());
    }

    @Test
    void testTryGetObjectLegalHold1() {
        // Setup
        // Configure S3Client.getObjectLegalHold(...).
        final GetObjectLegalHoldResponse getObjectLegalHoldResponse = GetObjectLegalHoldResponse.builder()
                .legalHold(ObjectLockLegalHold.builder()
                        .status(ObjectLockLegalHoldStatus.ON)
                        .build())
                .build();
        when(mockS3Client.getObjectLegalHold(GetObjectLegalHoldRequest.builder()
                .bucket("bucket")
                .key("key")
                .versionId("versionId")
                .requestPayer(RequestPayer.REQUESTER)
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(getObjectLegalHoldResponse);

        // Run the test
        myClassUnderTest.tryGetObjectLegalHold1();

        // Verify the results
    }

    @Test
    void testTryGetObjectLegalHold1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.getObjectLegalHold(GetObjectLegalHoldRequest.builder()
                .bucket("bucket")
                .key("key")
                .versionId("versionId")
                .requestPayer(RequestPayer.REQUESTER)
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetObjectLegalHold1());
    }

    @Test
    void testTryGetObjectLegalHold1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.getObjectLegalHold(GetObjectLegalHoldRequest.builder()
                .bucket("bucket")
                .key("key")
                .versionId("versionId")
                .requestPayer(RequestPayer.REQUESTER)
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetObjectLegalHold1());
    }

    @Test
    void testTryGetObjectLegalHold1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.getObjectLegalHold(GetObjectLegalHoldRequest.builder()
                .bucket("bucket")
                .key("key")
                .versionId("versionId")
                .requestPayer(RequestPayer.REQUESTER)
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryGetObjectLegalHold1());
    }

    @Test
    void testTryGetObjectLegalHold2() {
        // Setup
        // Configure S3Client.getObjectLegalHold(...).
        final GetObjectLegalHoldResponse getObjectLegalHoldResponse = GetObjectLegalHoldResponse.builder()
                .legalHold(ObjectLockLegalHold.builder()
                        .status(ObjectLockLegalHoldStatus.ON)
                        .build())
                .build();
        when(mockS3Client.getObjectLegalHold(any(Consumer.class))).thenReturn(getObjectLegalHoldResponse);

        // Run the test
        myClassUnderTest.tryGetObjectLegalHold2();

        // Verify the results
    }

    @Test
    void testTryGetObjectLegalHold2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.getObjectLegalHold(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetObjectLegalHold2());
    }

    @Test
    void testTryGetObjectLegalHold2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.getObjectLegalHold(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetObjectLegalHold2());
    }

    @Test
    void testTryGetObjectLegalHold2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.getObjectLegalHold(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryGetObjectLegalHold2());
    }

    @Test
    void testTryGetObjectLockConfiguration1() {
        // Setup
        // Configure S3Client.getObjectLockConfiguration(...).
        final GetObjectLockConfigurationResponse getObjectLockConfigurationResponse = GetObjectLockConfigurationResponse.builder()
                .objectLockConfiguration(ObjectLockConfiguration.builder()
                        .objectLockEnabled(ObjectLockEnabled.ENABLED)
                        .rule(ObjectLockRule.builder()
                                .defaultRetention(DefaultRetention.builder()
                                        .mode(ObjectLockRetentionMode.GOVERNANCE)
                                        .days(0)
                                        .years(0)
                                        .build())
                                .build())
                        .build())
                .build();
        when(mockS3Client.getObjectLockConfiguration(GetObjectLockConfigurationRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(getObjectLockConfigurationResponse);

        // Run the test
        myClassUnderTest.tryGetObjectLockConfiguration1();

        // Verify the results
    }

    @Test
    void testTryGetObjectLockConfiguration1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.getObjectLockConfiguration(GetObjectLockConfigurationRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetObjectLockConfiguration1());
    }

    @Test
    void testTryGetObjectLockConfiguration1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.getObjectLockConfiguration(GetObjectLockConfigurationRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetObjectLockConfiguration1());
    }

    @Test
    void testTryGetObjectLockConfiguration1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.getObjectLockConfiguration(GetObjectLockConfigurationRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryGetObjectLockConfiguration1());
    }

    @Test
    void testTryGetObjectLockConfiguration2() {
        // Setup
        // Configure S3Client.getObjectLockConfiguration(...).
        final GetObjectLockConfigurationResponse getObjectLockConfigurationResponse = GetObjectLockConfigurationResponse.builder()
                .objectLockConfiguration(ObjectLockConfiguration.builder()
                        .objectLockEnabled(ObjectLockEnabled.ENABLED)
                        .rule(ObjectLockRule.builder()
                                .defaultRetention(DefaultRetention.builder()
                                        .mode(ObjectLockRetentionMode.GOVERNANCE)
                                        .days(0)
                                        .years(0)
                                        .build())
                                .build())
                        .build())
                .build();
        when(mockS3Client.getObjectLockConfiguration(any(Consumer.class)))
                .thenReturn(getObjectLockConfigurationResponse);

        // Run the test
        myClassUnderTest.tryGetObjectLockConfiguration2();

        // Verify the results
    }

    @Test
    void testTryGetObjectLockConfiguration2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.getObjectLockConfiguration(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetObjectLockConfiguration2());
    }

    @Test
    void testTryGetObjectLockConfiguration2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.getObjectLockConfiguration(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetObjectLockConfiguration2());
    }

    @Test
    void testTryGetObjectLockConfiguration2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.getObjectLockConfiguration(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryGetObjectLockConfiguration2());
    }

    @Test
    void testTryGetObjectRetention1() {
        // Setup
        // Configure S3Client.getObjectRetention(...).
        final GetObjectRetentionResponse getObjectRetentionResponse = GetObjectRetentionResponse.builder()
                .retention(ObjectLockRetention.builder()
                        .mode(ObjectLockRetentionMode.GOVERNANCE)
                        .retainUntilDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                        .build())
                .build();
        when(mockS3Client.getObjectRetention(GetObjectRetentionRequest.builder()
                .bucket("bucket")
                .key("key")
                .versionId("versionId")
                .requestPayer(RequestPayer.REQUESTER)
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(getObjectRetentionResponse);

        // Run the test
        myClassUnderTest.tryGetObjectRetention1();

        // Verify the results
    }

    @Test
    void testTryGetObjectRetention1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.getObjectRetention(GetObjectRetentionRequest.builder()
                .bucket("bucket")
                .key("key")
                .versionId("versionId")
                .requestPayer(RequestPayer.REQUESTER)
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetObjectRetention1());
    }

    @Test
    void testTryGetObjectRetention1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.getObjectRetention(GetObjectRetentionRequest.builder()
                .bucket("bucket")
                .key("key")
                .versionId("versionId")
                .requestPayer(RequestPayer.REQUESTER)
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetObjectRetention1());
    }

    @Test
    void testTryGetObjectRetention1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.getObjectRetention(GetObjectRetentionRequest.builder()
                .bucket("bucket")
                .key("key")
                .versionId("versionId")
                .requestPayer(RequestPayer.REQUESTER)
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryGetObjectRetention1());
    }

    @Test
    void testTryGetObjectRetention2() {
        // Setup
        // Configure S3Client.getObjectRetention(...).
        final GetObjectRetentionResponse getObjectRetentionResponse = GetObjectRetentionResponse.builder()
                .retention(ObjectLockRetention.builder()
                        .mode(ObjectLockRetentionMode.GOVERNANCE)
                        .retainUntilDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                        .build())
                .build();
        when(mockS3Client.getObjectRetention(any(Consumer.class))).thenReturn(getObjectRetentionResponse);

        // Run the test
        myClassUnderTest.tryGetObjectRetention2();

        // Verify the results
    }

    @Test
    void testTryGetObjectRetention2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.getObjectRetention(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetObjectRetention2());
    }

    @Test
    void testTryGetObjectRetention2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.getObjectRetention(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetObjectRetention2());
    }

    @Test
    void testTryGetObjectRetention2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.getObjectRetention(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryGetObjectRetention2());
    }

    @Test
    void testTryGetObjectTagging1() {
        // Setup
        // Configure S3Client.getObjectTagging(...).
        final GetObjectTaggingResponse getObjectTaggingResponse = GetObjectTaggingResponse.builder()
                .versionId("versionId")
                .tagSet(Tag.builder()
                        .key("key")
                        .value("value")
                        .build())
                .build();
        when(mockS3Client.getObjectTagging(GetObjectTaggingRequest.builder()
                .bucket("bucket")
                .key("key")
                .versionId("versionId")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(getObjectTaggingResponse);

        // Run the test
        myClassUnderTest.tryGetObjectTagging1();

        // Verify the results
    }

    @Test
    void testTryGetObjectTagging1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.getObjectTagging(GetObjectTaggingRequest.builder()
                .bucket("bucket")
                .key("key")
                .versionId("versionId")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetObjectTagging1());
    }

    @Test
    void testTryGetObjectTagging1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.getObjectTagging(GetObjectTaggingRequest.builder()
                .bucket("bucket")
                .key("key")
                .versionId("versionId")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetObjectTagging1());
    }

    @Test
    void testTryGetObjectTagging1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.getObjectTagging(GetObjectTaggingRequest.builder()
                .bucket("bucket")
                .key("key")
                .versionId("versionId")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryGetObjectTagging1());
    }

    @Test
    void testTryGetObjectTagging2() {
        // Setup
        // Configure S3Client.getObjectTagging(...).
        final GetObjectTaggingResponse getObjectTaggingResponse = GetObjectTaggingResponse.builder()
                .versionId("versionId")
                .tagSet(Tag.builder()
                        .key("key")
                        .value("value")
                        .build())
                .build();
        when(mockS3Client.getObjectTagging(any(Consumer.class))).thenReturn(getObjectTaggingResponse);

        // Run the test
        myClassUnderTest.tryGetObjectTagging2();

        // Verify the results
    }

    @Test
    void testTryGetObjectTagging2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.getObjectTagging(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetObjectTagging2());
    }

    @Test
    void testTryGetObjectTagging2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.getObjectTagging(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetObjectTagging2());
    }

    @Test
    void testTryGetObjectTagging2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.getObjectTagging(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryGetObjectTagging2());
    }

    @Test
    void testTryGetObjectTorrentToInputStream() throws Exception {
        // Setup
        // Configure S3Client.getObjectTorrent(...).
        final ResponseInputStream<GetObjectTorrentResponse> spyResponseInputStream = spy(
                new ResponseInputStream<>(GetObjectTorrentResponse.builder().build(),
                        AbortableInputStream.create(new ByteArrayInputStream("objectContent".getBytes()))));
        when(mockS3Client.getObjectTorrent(eq(GetObjectTorrentRequest.builder()
                .bucket("bucket")
                .key("key")
                .requestPayer(RequestPayer.REQUESTER)
                .expectedBucketOwner("expectedBucketOwner")
                .build()), any(ResponseTransformer.class))).thenReturn(spyResponseInputStream);

        // Run the test
        myClassUnderTest.tryGetObjectTorrentToInputStream();

        // Verify the results
        verify(spyResponseInputStream).close();
    }

    @Test
    void testTryGetObjectTorrentToInputStream_S3ClientReturnsNoContent() throws Exception {
        // Setup
        // Configure S3Client.getObjectTorrent(...).
        final ResponseInputStream<GetObjectTorrentResponse> spyResponseInputStream = spy(
                new ResponseInputStream<>(GetObjectTorrentResponse.builder().build(),
                        AbortableInputStream.create(new ByteArrayInputStream(new byte[]{}))));
        when(mockS3Client.getObjectTorrent(eq(GetObjectTorrentRequest.builder()
                .bucket("bucket")
                .key("key")
                .requestPayer(RequestPayer.REQUESTER)
                .expectedBucketOwner("expectedBucketOwner")
                .build()), any(ResponseTransformer.class))).thenReturn(spyResponseInputStream);

        // Run the test
        myClassUnderTest.tryGetObjectTorrentToInputStream();

        // Verify the results
        verify(spyResponseInputStream).close();
    }

    @Test
    void testTryGetObjectTorrentToInputStream_S3ClientReturnsBrokenIo() throws Exception {
        // Setup
        // Configure S3Client.getObjectTorrent(...).
        final ResponseInputStream<GetObjectTorrentResponse> spyResponseInputStream = spy(
                new ResponseInputStream<>(GetObjectTorrentResponse.builder().build(),
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
        when(mockS3Client.getObjectTorrent(eq(GetObjectTorrentRequest.builder()
                .bucket("bucket")
                .key("key")
                .requestPayer(RequestPayer.REQUESTER)
                .expectedBucketOwner("expectedBucketOwner")
                .build()), any(ResponseTransformer.class))).thenReturn(spyResponseInputStream);

        // Run the test
        myClassUnderTest.tryGetObjectTorrentToInputStream();

        // Verify the results
        verify(spyResponseInputStream).close();
    }

    @Test
    void testTryGetObjectTorrentToInputStream_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.getObjectTorrent(eq(GetObjectTorrentRequest.builder()
                .bucket("bucket")
                .key("key")
                .requestPayer(RequestPayer.REQUESTER)
                .expectedBucketOwner("expectedBucketOwner")
                .build()), any(ResponseTransformer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetObjectTorrentToInputStream());
    }

    @Test
    void testTryGetObjectTorrentToInputStream_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.getObjectTorrent(eq(GetObjectTorrentRequest.builder()
                .bucket("bucket")
                .key("key")
                .requestPayer(RequestPayer.REQUESTER)
                .expectedBucketOwner("expectedBucketOwner")
                .build()), any(ResponseTransformer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetObjectTorrentToInputStream());
    }

    @Test
    void testTryGetObjectTorrentToInputStream_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.getObjectTorrent(eq(GetObjectTorrentRequest.builder()
                .bucket("bucket")
                .key("key")
                .requestPayer(RequestPayer.REQUESTER)
                .expectedBucketOwner("expectedBucketOwner")
                .build()), any(ResponseTransformer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryGetObjectTorrentToInputStream());
    }

    @Test
    void testTryGetObjectTorrentToFile() {
        // Setup
        // Run the test
        myClassUnderTest.tryGetObjectTorrentToFile();

        // Verify the results
        verify(mockS3Client).getObjectTorrent(eq(GetObjectTorrentRequest.builder()
                .bucket("bucket")
                .key("key")
                .requestPayer(RequestPayer.REQUESTER)
                .expectedBucketOwner("expectedBucketOwner")
                .build()), any(ResponseTransformer.class));
    }

    @Test
    void testTryGetObjectTorrentToFile_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.getObjectTorrent(eq(GetObjectTorrentRequest.builder()
                .bucket("bucket")
                .key("key")
                .requestPayer(RequestPayer.REQUESTER)
                .expectedBucketOwner("expectedBucketOwner")
                .build()), any(ResponseTransformer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetObjectTorrentToFile());
    }

    @Test
    void testTryGetObjectTorrentToFile_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.getObjectTorrent(eq(GetObjectTorrentRequest.builder()
                .bucket("bucket")
                .key("key")
                .requestPayer(RequestPayer.REQUESTER)
                .expectedBucketOwner("expectedBucketOwner")
                .build()), any(ResponseTransformer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetObjectTorrentToFile());
    }

    @Test
    void testTryGetObjectTorrentToFile_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.getObjectTorrent(eq(GetObjectTorrentRequest.builder()
                .bucket("bucket")
                .key("key")
                .requestPayer(RequestPayer.REQUESTER)
                .expectedBucketOwner("expectedBucketOwner")
                .build()), any(ResponseTransformer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryGetObjectTorrentToFile());
    }

    @Test
    void testTryGetObjectTorrent3() {
        // Setup
        // Configure S3Client.getObjectTorrent(...).
        final GetObjectTorrentResponse getObjectTorrentResponse = GetObjectTorrentResponse.builder().build();
        when(mockS3Client.getObjectTorrent(GetObjectTorrentRequest.builder()
                .bucket("bucket")
                .key("key")
                .requestPayer(RequestPayer.REQUESTER)
                .expectedBucketOwner("expectedBucketOwner")
                .build(), Paths.get("filename.txt"))).thenReturn(getObjectTorrentResponse);

        // Run the test
        myClassUnderTest.tryGetObjectTorrent3();

        // Verify the results
    }

    @Test
    void testTryGetObjectTorrent3_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.getObjectTorrent(GetObjectTorrentRequest.builder()
                .bucket("bucket")
                .key("key")
                .requestPayer(RequestPayer.REQUESTER)
                .expectedBucketOwner("expectedBucketOwner")
                .build(), Paths.get("filename.txt"))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetObjectTorrent3());
    }

    @Test
    void testTryGetObjectTorrent3_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.getObjectTorrent(GetObjectTorrentRequest.builder()
                .bucket("bucket")
                .key("key")
                .requestPayer(RequestPayer.REQUESTER)
                .expectedBucketOwner("expectedBucketOwner")
                .build(), Paths.get("filename.txt"))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetObjectTorrent3());
    }

    @Test
    void testTryGetObjectTorrent3_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.getObjectTorrent(GetObjectTorrentRequest.builder()
                .bucket("bucket")
                .key("key")
                .requestPayer(RequestPayer.REQUESTER)
                .expectedBucketOwner("expectedBucketOwner")
                .build(), Paths.get("filename.txt"))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryGetObjectTorrent3());
    }

    @Test
    void testTryGetObjectTorrent4() {
        // Setup
        // Configure S3Client.getObjectTorrent(...).
        final GetObjectTorrentResponse getObjectTorrentResponse = GetObjectTorrentResponse.builder().build();
        when(mockS3Client.getObjectTorrent(any(Consumer.class), eq(Paths.get("filename.txt"))))
                .thenReturn(getObjectTorrentResponse);

        // Run the test
        myClassUnderTest.tryGetObjectTorrent4();

        // Verify the results
    }

    @Test
    void testTryGetObjectTorrent4_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.getObjectTorrent(any(Consumer.class), eq(Paths.get("filename.txt"))))
                .thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetObjectTorrent4());
    }

    @Test
    void testTryGetObjectTorrent4_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.getObjectTorrent(any(Consumer.class), eq(Paths.get("filename.txt"))))
                .thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetObjectTorrent4());
    }

    @Test
    void testTryGetObjectTorrent4_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.getObjectTorrent(any(Consumer.class), eq(Paths.get("filename.txt"))))
                .thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryGetObjectTorrent4());
    }

    @Test
    void testTryGetObjectTorrent5() throws Exception {
        // Setup
        // Configure S3Client.getObjectTorrent(...).
        final ResponseInputStream<GetObjectTorrentResponse> spyResponseInputStream = spy(
                new ResponseInputStream<>(GetObjectTorrentResponse.builder().build(),
                        AbortableInputStream.create(new ByteArrayInputStream("objectContent".getBytes()))));
        when(mockS3Client.getObjectTorrent(GetObjectTorrentRequest.builder()
                .bucket("bucket")
                .key("key")
                .requestPayer(RequestPayer.REQUESTER)
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(spyResponseInputStream);

        // Run the test
        myClassUnderTest.tryGetObjectTorrent5();

        // Verify the results
        verify(spyResponseInputStream).close();
    }

    @Test
    void testTryGetObjectTorrent5_S3ClientReturnsNoContent() throws Exception {
        // Setup
        // Configure S3Client.getObjectTorrent(...).
        final ResponseInputStream<GetObjectTorrentResponse> spyResponseInputStream = spy(
                new ResponseInputStream<>(GetObjectTorrentResponse.builder().build(),
                        AbortableInputStream.create(new ByteArrayInputStream(new byte[]{}))));
        when(mockS3Client.getObjectTorrent(GetObjectTorrentRequest.builder()
                .bucket("bucket")
                .key("key")
                .requestPayer(RequestPayer.REQUESTER)
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(spyResponseInputStream);

        // Run the test
        myClassUnderTest.tryGetObjectTorrent5();

        // Verify the results
        verify(spyResponseInputStream).close();
    }

    @Test
    void testTryGetObjectTorrent5_S3ClientReturnsBrokenIo() throws Exception {
        // Setup
        // Configure S3Client.getObjectTorrent(...).
        final ResponseInputStream<GetObjectTorrentResponse> spyResponseInputStream = spy(
                new ResponseInputStream<>(GetObjectTorrentResponse.builder().build(),
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
        when(mockS3Client.getObjectTorrent(GetObjectTorrentRequest.builder()
                .bucket("bucket")
                .key("key")
                .requestPayer(RequestPayer.REQUESTER)
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(spyResponseInputStream);

        // Run the test
        myClassUnderTest.tryGetObjectTorrent5();

        // Verify the results
        verify(spyResponseInputStream).close();
    }

    @Test
    void testTryGetObjectTorrent5_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.getObjectTorrent(GetObjectTorrentRequest.builder()
                .bucket("bucket")
                .key("key")
                .requestPayer(RequestPayer.REQUESTER)
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetObjectTorrent5());
    }

    @Test
    void testTryGetObjectTorrent5_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.getObjectTorrent(GetObjectTorrentRequest.builder()
                .bucket("bucket")
                .key("key")
                .requestPayer(RequestPayer.REQUESTER)
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetObjectTorrent5());
    }

    @Test
    void testTryGetObjectTorrent5_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.getObjectTorrent(GetObjectTorrentRequest.builder()
                .bucket("bucket")
                .key("key")
                .requestPayer(RequestPayer.REQUESTER)
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryGetObjectTorrent5());
    }

    @Test
    void testTryGetObjectTorrent6() throws Exception {
        // Setup
        // Configure S3Client.getObjectTorrent(...).
        final ResponseInputStream<GetObjectTorrentResponse> spyResponseInputStream = spy(
                new ResponseInputStream<>(GetObjectTorrentResponse.builder().build(),
                        AbortableInputStream.create(new ByteArrayInputStream("objectContent".getBytes()))));
        when(mockS3Client.getObjectTorrent(any(Consumer.class))).thenReturn(spyResponseInputStream);

        // Run the test
        myClassUnderTest.tryGetObjectTorrent6();

        // Verify the results
        verify(spyResponseInputStream).close();
    }

    @Test
    void testTryGetObjectTorrent6_S3ClientReturnsNoContent() throws Exception {
        // Setup
        // Configure S3Client.getObjectTorrent(...).
        final ResponseInputStream<GetObjectTorrentResponse> spyResponseInputStream = spy(
                new ResponseInputStream<>(GetObjectTorrentResponse.builder().build(),
                        AbortableInputStream.create(new ByteArrayInputStream(new byte[]{}))));
        when(mockS3Client.getObjectTorrent(any(Consumer.class))).thenReturn(spyResponseInputStream);

        // Run the test
        myClassUnderTest.tryGetObjectTorrent6();

        // Verify the results
        verify(spyResponseInputStream).close();
    }

    @Test
    void testTryGetObjectTorrent6_S3ClientReturnsBrokenIo() throws Exception {
        // Setup
        // Configure S3Client.getObjectTorrent(...).
        final ResponseInputStream<GetObjectTorrentResponse> spyResponseInputStream = spy(
                new ResponseInputStream<>(GetObjectTorrentResponse.builder().build(),
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
        when(mockS3Client.getObjectTorrent(any(Consumer.class))).thenReturn(spyResponseInputStream);

        // Run the test
        myClassUnderTest.tryGetObjectTorrent6();

        // Verify the results
        verify(spyResponseInputStream).close();
    }

    @Test
    void testTryGetObjectTorrent6_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.getObjectTorrent(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetObjectTorrent6());
    }

    @Test
    void testTryGetObjectTorrent6_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.getObjectTorrent(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetObjectTorrent6());
    }

    @Test
    void testTryGetObjectTorrent6_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.getObjectTorrent(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryGetObjectTorrent6());
    }

    @Test
    void testTryGetObjectTorrentAsBytes1() {
        // Setup
        // Configure S3Client.getObjectTorrentAsBytes(...).
        final ResponseBytes<GetObjectTorrentResponse> getObjectTorrentResponseBytes = ResponseBytes.fromByteArray(
                GetObjectTorrentResponse.builder().build(), "content".getBytes());
        when(mockS3Client.getObjectTorrentAsBytes(GetObjectTorrentRequest.builder()
                .bucket("bucket")
                .key("key")
                .requestPayer(RequestPayer.REQUESTER)
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(getObjectTorrentResponseBytes);

        // Run the test
        myClassUnderTest.tryGetObjectTorrentAsBytes1();

        // Verify the results
    }

    @Test
    void testTryGetObjectTorrentAsBytes1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.getObjectTorrentAsBytes(GetObjectTorrentRequest.builder()
                .bucket("bucket")
                .key("key")
                .requestPayer(RequestPayer.REQUESTER)
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetObjectTorrentAsBytes1());
    }

    @Test
    void testTryGetObjectTorrentAsBytes1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.getObjectTorrentAsBytes(GetObjectTorrentRequest.builder()
                .bucket("bucket")
                .key("key")
                .requestPayer(RequestPayer.REQUESTER)
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetObjectTorrentAsBytes1());
    }

    @Test
    void testTryGetObjectTorrentAsBytes1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.getObjectTorrentAsBytes(GetObjectTorrentRequest.builder()
                .bucket("bucket")
                .key("key")
                .requestPayer(RequestPayer.REQUESTER)
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryGetObjectTorrentAsBytes1());
    }

    @Test
    void testTryGetObjectTorrentAsBytes2() {
        // Setup
        // Configure S3Client.getObjectTorrentAsBytes(...).
        final ResponseBytes<GetObjectTorrentResponse> getObjectTorrentResponseBytes = ResponseBytes.fromByteArray(
                GetObjectTorrentResponse.builder().build(), "content".getBytes());
        when(mockS3Client.getObjectTorrentAsBytes(any(Consumer.class))).thenReturn(getObjectTorrentResponseBytes);

        // Run the test
        myClassUnderTest.tryGetObjectTorrentAsBytes2();

        // Verify the results
    }

    @Test
    void testTryGetObjectTorrentAsBytes2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.getObjectTorrentAsBytes(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetObjectTorrentAsBytes2());
    }

    @Test
    void testTryGetObjectTorrentAsBytes2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.getObjectTorrentAsBytes(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetObjectTorrentAsBytes2());
    }

    @Test
    void testTryGetObjectTorrentAsBytes2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.getObjectTorrentAsBytes(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryGetObjectTorrentAsBytes2());
    }

    @Test
    void testTryGetPublicAccessBlock1() {
        // Setup
        // Configure S3Client.getPublicAccessBlock(...).
        final GetPublicAccessBlockResponse getPublicAccessBlockResponse = GetPublicAccessBlockResponse.builder()
                .publicAccessBlockConfiguration(PublicAccessBlockConfiguration.builder()
                        .blockPublicAcls(false)
                        .ignorePublicAcls(false)
                        .blockPublicPolicy(false)
                        .restrictPublicBuckets(false)
                        .build())
                .build();
        when(mockS3Client.getPublicAccessBlock(GetPublicAccessBlockRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(getPublicAccessBlockResponse);

        // Run the test
        myClassUnderTest.tryGetPublicAccessBlock1();

        // Verify the results
    }

    @Test
    void testTryGetPublicAccessBlock1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.getPublicAccessBlock(GetPublicAccessBlockRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetPublicAccessBlock1());
    }

    @Test
    void testTryGetPublicAccessBlock1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.getPublicAccessBlock(GetPublicAccessBlockRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetPublicAccessBlock1());
    }

    @Test
    void testTryGetPublicAccessBlock1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.getPublicAccessBlock(GetPublicAccessBlockRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryGetPublicAccessBlock1());
    }

    @Test
    void testTryGetPublicAccessBlock2() {
        // Setup
        // Configure S3Client.getPublicAccessBlock(...).
        final GetPublicAccessBlockResponse getPublicAccessBlockResponse = GetPublicAccessBlockResponse.builder()
                .publicAccessBlockConfiguration(PublicAccessBlockConfiguration.builder()
                        .blockPublicAcls(false)
                        .ignorePublicAcls(false)
                        .blockPublicPolicy(false)
                        .restrictPublicBuckets(false)
                        .build())
                .build();
        when(mockS3Client.getPublicAccessBlock(any(Consumer.class))).thenReturn(getPublicAccessBlockResponse);

        // Run the test
        myClassUnderTest.tryGetPublicAccessBlock2();

        // Verify the results
    }

    @Test
    void testTryGetPublicAccessBlock2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.getPublicAccessBlock(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetPublicAccessBlock2());
    }

    @Test
    void testTryGetPublicAccessBlock2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.getPublicAccessBlock(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetPublicAccessBlock2());
    }

    @Test
    void testTryGetPublicAccessBlock2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.getPublicAccessBlock(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryGetPublicAccessBlock2());
    }

    @Test
    void testTryHeadBucket1() {
        // Setup
        when(mockS3Client.headBucket(HeadBucketRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(HeadBucketResponse.builder().build());

        // Run the test
        myClassUnderTest.tryHeadBucket1();

        // Verify the results
    }

    @Test
    void testTryHeadBucket1_S3ClientThrowsNoSuchBucketException() {
        // Setup
        when(mockS3Client.headBucket(HeadBucketRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(NoSuchBucketException.class);

        // Run the test
        assertThrows(NoSuchBucketException.class, () -> myClassUnderTest.tryHeadBucket1());
    }

    @Test
    void testTryHeadBucket1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.headBucket(HeadBucketRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryHeadBucket1());
    }

    @Test
    void testTryHeadBucket1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.headBucket(HeadBucketRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryHeadBucket1());
    }

    @Test
    void testTryHeadBucket1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.headBucket(HeadBucketRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryHeadBucket1());
    }

    @Test
    void testTryHeadBucket2() {
        // Setup
        when(mockS3Client.headBucket(any(Consumer.class))).thenReturn(HeadBucketResponse.builder().build());

        // Run the test
        myClassUnderTest.tryHeadBucket2();

        // Verify the results
    }

    @Test
    void testTryHeadBucket2_S3ClientThrowsNoSuchBucketException() {
        // Setup
        when(mockS3Client.headBucket(any(Consumer.class))).thenThrow(NoSuchBucketException.class);

        // Run the test
        assertThrows(NoSuchBucketException.class, () -> myClassUnderTest.tryHeadBucket2());
    }

    @Test
    void testTryHeadBucket2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.headBucket(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryHeadBucket2());
    }

    @Test
    void testTryHeadBucket2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.headBucket(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryHeadBucket2());
    }

    @Test
    void testTryHeadBucket2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.headBucket(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryHeadBucket2());
    }

    @Test
    void testTryHeadObject1() {
        // Setup
        // Configure S3Client.headObject(...).
        final HeadObjectResponse headObjectResponse = HeadObjectResponse.builder()
                .deleteMarker(false)
                .acceptRanges("acceptRanges")
                .expiration("expiration")
                .restore("restore")
                .lastModified(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                .contentLength(0L)
                .eTag("eTag")
                .missingMeta(0)
                .versionId("versionId")
                .cacheControl("cacheControl")
                .contentDisposition("contentDisposition")
                .contentEncoding("contentEncoding")
                .contentLanguage("contentLanguage")
                .contentType("contentType")
                .expires(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                .websiteRedirectLocation("websiteRedirectLocation")
                .serverSideEncryption(ServerSideEncryption.AES256)
                .metadata(new HashMap<>())
                .sseCustomerAlgorithm("sseCustomerAlgorithm")
                .sseCustomerKeyMD5("sseCustomerKeyMD5")
                .ssekmsKeyId("ssekmsKeyId")
                .storageClass(StorageClass.STANDARD)
                .requestCharged(RequestCharged.REQUESTER)
                .replicationStatus(ReplicationStatus.COMPLETE)
                .partsCount(0)
                .objectLockMode(ObjectLockMode.GOVERNANCE)
                .objectLockRetainUntilDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                .objectLockLegalHoldStatus(ObjectLockLegalHoldStatus.ON)
                .build();
        when(mockS3Client.headObject(HeadObjectRequest.builder()
                .bucket("bucket")
                .ifMatch("ifMatch")
                .ifModifiedSince(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                .ifNoneMatch("ifNoneMatch")
                .ifUnmodifiedSince(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                .key("key")
                .range("range")
                .versionId("versionId")
                .sseCustomerAlgorithm("sseCustomerAlgorithm")
                .sseCustomerKey("sseCustomerKey")
                .sseCustomerKeyMD5("sseCustomerKeyMD5")
                .requestPayer(RequestPayer.REQUESTER)
                .partNumber(0)
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(headObjectResponse);

        // Run the test
        myClassUnderTest.tryHeadObject1();

        // Verify the results
    }

    @Test
    void testTryHeadObject1_S3ClientThrowsNoSuchKeyException() {
        // Setup
        when(mockS3Client.headObject(HeadObjectRequest.builder()
                .bucket("bucket")
                .ifMatch("ifMatch")
                .ifModifiedSince(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                .ifNoneMatch("ifNoneMatch")
                .ifUnmodifiedSince(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                .key("key")
                .range("range")
                .versionId("versionId")
                .sseCustomerAlgorithm("sseCustomerAlgorithm")
                .sseCustomerKey("sseCustomerKey")
                .sseCustomerKeyMD5("sseCustomerKeyMD5")
                .requestPayer(RequestPayer.REQUESTER)
                .partNumber(0)
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(NoSuchKeyException.class);

        // Run the test
        assertThrows(NoSuchKeyException.class, () -> myClassUnderTest.tryHeadObject1());
    }

    @Test
    void testTryHeadObject1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.headObject(HeadObjectRequest.builder()
                .bucket("bucket")
                .ifMatch("ifMatch")
                .ifModifiedSince(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                .ifNoneMatch("ifNoneMatch")
                .ifUnmodifiedSince(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                .key("key")
                .range("range")
                .versionId("versionId")
                .sseCustomerAlgorithm("sseCustomerAlgorithm")
                .sseCustomerKey("sseCustomerKey")
                .sseCustomerKeyMD5("sseCustomerKeyMD5")
                .requestPayer(RequestPayer.REQUESTER)
                .partNumber(0)
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryHeadObject1());
    }

    @Test
    void testTryHeadObject1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.headObject(HeadObjectRequest.builder()
                .bucket("bucket")
                .ifMatch("ifMatch")
                .ifModifiedSince(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                .ifNoneMatch("ifNoneMatch")
                .ifUnmodifiedSince(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                .key("key")
                .range("range")
                .versionId("versionId")
                .sseCustomerAlgorithm("sseCustomerAlgorithm")
                .sseCustomerKey("sseCustomerKey")
                .sseCustomerKeyMD5("sseCustomerKeyMD5")
                .requestPayer(RequestPayer.REQUESTER)
                .partNumber(0)
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryHeadObject1());
    }

    @Test
    void testTryHeadObject1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.headObject(HeadObjectRequest.builder()
                .bucket("bucket")
                .ifMatch("ifMatch")
                .ifModifiedSince(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                .ifNoneMatch("ifNoneMatch")
                .ifUnmodifiedSince(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                .key("key")
                .range("range")
                .versionId("versionId")
                .sseCustomerAlgorithm("sseCustomerAlgorithm")
                .sseCustomerKey("sseCustomerKey")
                .sseCustomerKeyMD5("sseCustomerKeyMD5")
                .requestPayer(RequestPayer.REQUESTER)
                .partNumber(0)
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryHeadObject1());
    }

    @Test
    void testTryHeadObject2() {
        // Setup
        // Configure S3Client.headObject(...).
        final HeadObjectResponse headObjectResponse = HeadObjectResponse.builder()
                .deleteMarker(false)
                .acceptRanges("acceptRanges")
                .expiration("expiration")
                .restore("restore")
                .lastModified(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                .contentLength(0L)
                .eTag("eTag")
                .missingMeta(0)
                .versionId("versionId")
                .cacheControl("cacheControl")
                .contentDisposition("contentDisposition")
                .contentEncoding("contentEncoding")
                .contentLanguage("contentLanguage")
                .contentType("contentType")
                .expires(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                .websiteRedirectLocation("websiteRedirectLocation")
                .serverSideEncryption(ServerSideEncryption.AES256)
                .metadata(new HashMap<>())
                .sseCustomerAlgorithm("sseCustomerAlgorithm")
                .sseCustomerKeyMD5("sseCustomerKeyMD5")
                .ssekmsKeyId("ssekmsKeyId")
                .storageClass(StorageClass.STANDARD)
                .requestCharged(RequestCharged.REQUESTER)
                .replicationStatus(ReplicationStatus.COMPLETE)
                .partsCount(0)
                .objectLockMode(ObjectLockMode.GOVERNANCE)
                .objectLockRetainUntilDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                .objectLockLegalHoldStatus(ObjectLockLegalHoldStatus.ON)
                .build();
        when(mockS3Client.headObject(any(Consumer.class))).thenReturn(headObjectResponse);

        // Run the test
        myClassUnderTest.tryHeadObject2();

        // Verify the results
    }

    @Test
    void testTryHeadObject2_S3ClientThrowsNoSuchKeyException() {
        // Setup
        when(mockS3Client.headObject(any(Consumer.class))).thenThrow(NoSuchKeyException.class);

        // Run the test
        assertThrows(NoSuchKeyException.class, () -> myClassUnderTest.tryHeadObject2());
    }

    @Test
    void testTryHeadObject2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.headObject(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryHeadObject2());
    }

    @Test
    void testTryHeadObject2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.headObject(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryHeadObject2());
    }

    @Test
    void testTryHeadObject2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.headObject(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryHeadObject2());
    }

    @Test
    void testTryListBucketAnalyticsConfigurations1() {
        // Setup
        // Configure S3Client.listBucketAnalyticsConfigurations(...).
        final ListBucketAnalyticsConfigurationsResponse listBucketAnalyticsConfigurationsResponse = ListBucketAnalyticsConfigurationsResponse.builder()
                .isTruncated(false)
                .continuationToken("continuationToken")
                .nextContinuationToken("nextContinuationToken")
                .analyticsConfigurationList(AnalyticsConfiguration.builder()
                        .id("id")
                        .filter(AnalyticsFilter.builder()
                                .prefix("prefix")
                                .tag(Tag.builder()
                                        .key("key")
                                        .value("value")
                                        .build())
                                .and(AnalyticsAndOperator.builder()
                                        .prefix("prefix")
                                        .tags(Tag.builder()
                                                .key("key")
                                                .value("value")
                                                .build())
                                        .build())
                                .build())
                        .storageClassAnalysis(StorageClassAnalysis.builder()
                                .dataExport(StorageClassAnalysisDataExport.builder()
                                        .outputSchemaVersion(StorageClassAnalysisSchemaVersion.V_1)
                                        .destination(AnalyticsExportDestination.builder()
                                                .s3BucketDestination(AnalyticsS3BucketDestination.builder()
                                                        .format(AnalyticsS3ExportFileFormat.CSV)
                                                        .bucketAccountId("bucketAccountId")
                                                        .bucket("bucket")
                                                        .prefix("prefix")
                                                        .build())
                                                .build())
                                        .build())
                                .build())
                        .build())
                .build();
        when(mockS3Client.listBucketAnalyticsConfigurations(ListBucketAnalyticsConfigurationsRequest.builder()
                .bucket("bucket")
                .continuationToken("continuationToken")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(listBucketAnalyticsConfigurationsResponse);

        // Run the test
        myClassUnderTest.tryListBucketAnalyticsConfigurations1();

        // Verify the results
    }

    @Test
    void testTryListBucketAnalyticsConfigurations1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.listBucketAnalyticsConfigurations(ListBucketAnalyticsConfigurationsRequest.builder()
                .bucket("bucket")
                .continuationToken("continuationToken")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListBucketAnalyticsConfigurations1());
    }

    @Test
    void testTryListBucketAnalyticsConfigurations1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.listBucketAnalyticsConfigurations(ListBucketAnalyticsConfigurationsRequest.builder()
                .bucket("bucket")
                .continuationToken("continuationToken")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListBucketAnalyticsConfigurations1());
    }

    @Test
    void testTryListBucketAnalyticsConfigurations1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.listBucketAnalyticsConfigurations(ListBucketAnalyticsConfigurationsRequest.builder()
                .bucket("bucket")
                .continuationToken("continuationToken")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryListBucketAnalyticsConfigurations1());
    }

    @Test
    void testTryListBucketAnalyticsConfigurations2() {
        // Setup
        // Configure S3Client.listBucketAnalyticsConfigurations(...).
        final ListBucketAnalyticsConfigurationsResponse listBucketAnalyticsConfigurationsResponse = ListBucketAnalyticsConfigurationsResponse.builder()
                .isTruncated(false)
                .continuationToken("continuationToken")
                .nextContinuationToken("nextContinuationToken")
                .analyticsConfigurationList(AnalyticsConfiguration.builder()
                        .id("id")
                        .filter(AnalyticsFilter.builder()
                                .prefix("prefix")
                                .tag(Tag.builder()
                                        .key("key")
                                        .value("value")
                                        .build())
                                .and(AnalyticsAndOperator.builder()
                                        .prefix("prefix")
                                        .tags(Tag.builder()
                                                .key("key")
                                                .value("value")
                                                .build())
                                        .build())
                                .build())
                        .storageClassAnalysis(StorageClassAnalysis.builder()
                                .dataExport(StorageClassAnalysisDataExport.builder()
                                        .outputSchemaVersion(StorageClassAnalysisSchemaVersion.V_1)
                                        .destination(AnalyticsExportDestination.builder()
                                                .s3BucketDestination(AnalyticsS3BucketDestination.builder()
                                                        .format(AnalyticsS3ExportFileFormat.CSV)
                                                        .bucketAccountId("bucketAccountId")
                                                        .bucket("bucket")
                                                        .prefix("prefix")
                                                        .build())
                                                .build())
                                        .build())
                                .build())
                        .build())
                .build();
        when(mockS3Client.listBucketAnalyticsConfigurations(any(Consumer.class)))
                .thenReturn(listBucketAnalyticsConfigurationsResponse);

        // Run the test
        myClassUnderTest.tryListBucketAnalyticsConfigurations2();

        // Verify the results
    }

    @Test
    void testTryListBucketAnalyticsConfigurations2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.listBucketAnalyticsConfigurations(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListBucketAnalyticsConfigurations2());
    }

    @Test
    void testTryListBucketAnalyticsConfigurations2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.listBucketAnalyticsConfigurations(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListBucketAnalyticsConfigurations2());
    }

    @Test
    void testTryListBucketAnalyticsConfigurations2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.listBucketAnalyticsConfigurations(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryListBucketAnalyticsConfigurations2());
    }

    @Test
    void testTryListBucketInventoryConfigurations1() {
        // Setup
        // Configure S3Client.listBucketInventoryConfigurations(...).
        final ListBucketInventoryConfigurationsResponse listBucketInventoryConfigurationsResponse = ListBucketInventoryConfigurationsResponse.builder()
                .continuationToken("continuationToken")
                .inventoryConfigurationList(InventoryConfiguration.builder()
                        .destination(InventoryDestination.builder()
                                .s3BucketDestination(InventoryS3BucketDestination.builder()
                                        .accountId("accountId")
                                        .bucket("bucket")
                                        .format(InventoryFormat.CSV)
                                        .prefix("prefix")
                                        .encryption(InventoryEncryption.builder()
                                                .sses3(SSES3.builder().build())
                                                .ssekms(SSEKMS.builder()
                                                        .keyId("keyId")
                                                        .build())
                                                .build())
                                        .build())
                                .build())
                        .isEnabled(false)
                        .filter(InventoryFilter.builder()
                                .prefix("prefix")
                                .build())
                        .id("id")
                        .includedObjectVersions(InventoryIncludedObjectVersions.ALL)
                        .optionalFields(InventoryOptionalField.SIZE)
                        .schedule(InventorySchedule.builder()
                                .frequency(InventoryFrequency.DAILY)
                                .build())
                        .build())
                .isTruncated(false)
                .nextContinuationToken("nextContinuationToken")
                .build();
        when(mockS3Client.listBucketInventoryConfigurations(ListBucketInventoryConfigurationsRequest.builder()
                .bucket("bucket")
                .continuationToken("continuationToken")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(listBucketInventoryConfigurationsResponse);

        // Run the test
        myClassUnderTest.tryListBucketInventoryConfigurations1();

        // Verify the results
    }

    @Test
    void testTryListBucketInventoryConfigurations1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.listBucketInventoryConfigurations(ListBucketInventoryConfigurationsRequest.builder()
                .bucket("bucket")
                .continuationToken("continuationToken")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListBucketInventoryConfigurations1());
    }

    @Test
    void testTryListBucketInventoryConfigurations1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.listBucketInventoryConfigurations(ListBucketInventoryConfigurationsRequest.builder()
                .bucket("bucket")
                .continuationToken("continuationToken")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListBucketInventoryConfigurations1());
    }

    @Test
    void testTryListBucketInventoryConfigurations1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.listBucketInventoryConfigurations(ListBucketInventoryConfigurationsRequest.builder()
                .bucket("bucket")
                .continuationToken("continuationToken")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryListBucketInventoryConfigurations1());
    }

    @Test
    void testTryListBucketInventoryConfigurations2() {
        // Setup
        // Configure S3Client.listBucketInventoryConfigurations(...).
        final ListBucketInventoryConfigurationsResponse listBucketInventoryConfigurationsResponse = ListBucketInventoryConfigurationsResponse.builder()
                .continuationToken("continuationToken")
                .inventoryConfigurationList(InventoryConfiguration.builder()
                        .destination(InventoryDestination.builder()
                                .s3BucketDestination(InventoryS3BucketDestination.builder()
                                        .accountId("accountId")
                                        .bucket("bucket")
                                        .format(InventoryFormat.CSV)
                                        .prefix("prefix")
                                        .encryption(InventoryEncryption.builder()
                                                .sses3(SSES3.builder().build())
                                                .ssekms(SSEKMS.builder()
                                                        .keyId("keyId")
                                                        .build())
                                                .build())
                                        .build())
                                .build())
                        .isEnabled(false)
                        .filter(InventoryFilter.builder()
                                .prefix("prefix")
                                .build())
                        .id("id")
                        .includedObjectVersions(InventoryIncludedObjectVersions.ALL)
                        .optionalFields(InventoryOptionalField.SIZE)
                        .schedule(InventorySchedule.builder()
                                .frequency(InventoryFrequency.DAILY)
                                .build())
                        .build())
                .isTruncated(false)
                .nextContinuationToken("nextContinuationToken")
                .build();
        when(mockS3Client.listBucketInventoryConfigurations(any(Consumer.class)))
                .thenReturn(listBucketInventoryConfigurationsResponse);

        // Run the test
        myClassUnderTest.tryListBucketInventoryConfigurations2();

        // Verify the results
    }

    @Test
    void testTryListBucketInventoryConfigurations2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.listBucketInventoryConfigurations(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListBucketInventoryConfigurations2());
    }

    @Test
    void testTryListBucketInventoryConfigurations2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.listBucketInventoryConfigurations(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListBucketInventoryConfigurations2());
    }

    @Test
    void testTryListBucketInventoryConfigurations2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.listBucketInventoryConfigurations(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryListBucketInventoryConfigurations2());
    }

    @Test
    void testTryListBucketMetricsConfigurations1() {
        // Setup
        // Configure S3Client.listBucketMetricsConfigurations(...).
        final ListBucketMetricsConfigurationsResponse listBucketMetricsConfigurationsResponse = ListBucketMetricsConfigurationsResponse.builder()
                .isTruncated(false)
                .continuationToken("continuationToken")
                .nextContinuationToken("nextContinuationToken")
                .metricsConfigurationList(MetricsConfiguration.builder()
                        .id("id")
                        .filter(MetricsFilter.builder()
                                .prefix("prefix")
                                .tag(Tag.builder()
                                        .key("key")
                                        .value("value")
                                        .build())
                                .and(MetricsAndOperator.builder()
                                        .prefix("prefix")
                                        .tags(Tag.builder()
                                                .key("key")
                                                .value("value")
                                                .build())
                                        .build())
                                .build())
                        .build())
                .build();
        when(mockS3Client.listBucketMetricsConfigurations(ListBucketMetricsConfigurationsRequest.builder()
                .bucket("bucket")
                .continuationToken("continuationToken")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(listBucketMetricsConfigurationsResponse);

        // Run the test
        myClassUnderTest.tryListBucketMetricsConfigurations1();

        // Verify the results
    }

    @Test
    void testTryListBucketMetricsConfigurations1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.listBucketMetricsConfigurations(ListBucketMetricsConfigurationsRequest.builder()
                .bucket("bucket")
                .continuationToken("continuationToken")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListBucketMetricsConfigurations1());
    }

    @Test
    void testTryListBucketMetricsConfigurations1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.listBucketMetricsConfigurations(ListBucketMetricsConfigurationsRequest.builder()
                .bucket("bucket")
                .continuationToken("continuationToken")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListBucketMetricsConfigurations1());
    }

    @Test
    void testTryListBucketMetricsConfigurations1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.listBucketMetricsConfigurations(ListBucketMetricsConfigurationsRequest.builder()
                .bucket("bucket")
                .continuationToken("continuationToken")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryListBucketMetricsConfigurations1());
    }

    @Test
    void testTryListBucketMetricsConfigurations2() {
        // Setup
        // Configure S3Client.listBucketMetricsConfigurations(...).
        final ListBucketMetricsConfigurationsResponse listBucketMetricsConfigurationsResponse = ListBucketMetricsConfigurationsResponse.builder()
                .isTruncated(false)
                .continuationToken("continuationToken")
                .nextContinuationToken("nextContinuationToken")
                .metricsConfigurationList(MetricsConfiguration.builder()
                        .id("id")
                        .filter(MetricsFilter.builder()
                                .prefix("prefix")
                                .tag(Tag.builder()
                                        .key("key")
                                        .value("value")
                                        .build())
                                .and(MetricsAndOperator.builder()
                                        .prefix("prefix")
                                        .tags(Tag.builder()
                                                .key("key")
                                                .value("value")
                                                .build())
                                        .build())
                                .build())
                        .build())
                .build();
        when(mockS3Client.listBucketMetricsConfigurations(any(Consumer.class)))
                .thenReturn(listBucketMetricsConfigurationsResponse);

        // Run the test
        myClassUnderTest.tryListBucketMetricsConfigurations2();

        // Verify the results
    }

    @Test
    void testTryListBucketMetricsConfigurations2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.listBucketMetricsConfigurations(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListBucketMetricsConfigurations2());
    }

    @Test
    void testTryListBucketMetricsConfigurations2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.listBucketMetricsConfigurations(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListBucketMetricsConfigurations2());
    }

    @Test
    void testTryListBucketMetricsConfigurations2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.listBucketMetricsConfigurations(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryListBucketMetricsConfigurations2());
    }

    @Test
    void testTryListBuckets1() {
        // Setup
        // Configure S3Client.listBuckets(...).
        final ListBucketsResponse listBucketsResponse = ListBucketsResponse.builder()
                .buckets(Bucket.builder()
                        .name("name")
                        .creationDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                        .build())
                .owner(Owner.builder()
                        .displayName("displayName")
                        .id("id")
                        .build())
                .build();
        when(mockS3Client.listBuckets()).thenReturn(listBucketsResponse);

        // Run the test
        myClassUnderTest.tryListBuckets1();

        // Verify the results
    }

    @Test
    void testTryListBuckets1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.listBuckets()).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListBuckets1());
    }

    @Test
    void testTryListBuckets1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.listBuckets()).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListBuckets1());
    }

    @Test
    void testTryListBuckets1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.listBuckets()).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryListBuckets1());
    }

    @Test
    void testTryListBuckets2() {
        // Setup
        // Configure S3Client.listBuckets(...).
        final ListBucketsResponse listBucketsResponse = ListBucketsResponse.builder()
                .buckets(Bucket.builder()
                        .name("name")
                        .creationDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                        .build())
                .owner(Owner.builder()
                        .displayName("displayName")
                        .id("id")
                        .build())
                .build();
        when(mockS3Client.listBuckets(ListBucketsRequest.builder().build())).thenReturn(listBucketsResponse);

        // Run the test
        myClassUnderTest.tryListBuckets2();

        // Verify the results
    }

    @Test
    void testTryListBuckets2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.listBuckets(ListBucketsRequest.builder().build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListBuckets2());
    }

    @Test
    void testTryListBuckets2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.listBuckets(ListBucketsRequest.builder().build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListBuckets2());
    }

    @Test
    void testTryListBuckets2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.listBuckets(ListBucketsRequest.builder().build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryListBuckets2());
    }

    @Test
    void testTryListBuckets3() {
        // Setup
        // Configure S3Client.listBuckets(...).
        final ListBucketsResponse listBucketsResponse = ListBucketsResponse.builder()
                .buckets(Bucket.builder()
                        .name("name")
                        .creationDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                        .build())
                .owner(Owner.builder()
                        .displayName("displayName")
                        .id("id")
                        .build())
                .build();
        when(mockS3Client.listBuckets(any(Consumer.class))).thenReturn(listBucketsResponse);

        // Run the test
        myClassUnderTest.tryListBuckets3();

        // Verify the results
    }

    @Test
    void testTryListBuckets3_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.listBuckets(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListBuckets3());
    }

    @Test
    void testTryListBuckets3_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.listBuckets(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListBuckets3());
    }

    @Test
    void testTryListBuckets3_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.listBuckets(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryListBuckets3());
    }

    @Test
    void testTryListMultipartUploads1() {
        // Setup
        // Configure S3Client.listMultipartUploads(...).
        final ListMultipartUploadsResponse listMultipartUploadsResponse = ListMultipartUploadsResponse.builder()
                .bucket("bucket")
                .keyMarker("keyMarker")
                .uploadIdMarker("uploadIdMarker")
                .nextKeyMarker("nextKeyMarker")
                .prefix("prefix")
                .delimiter("delimiter")
                .nextUploadIdMarker("nextUploadIdMarker")
                .maxUploads(0)
                .isTruncated(false)
                .uploads(MultipartUpload.builder()
                        .uploadId("uploadId")
                        .key("key")
                        .initiated(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                        .storageClass(StorageClass.STANDARD)
                        .owner(Owner.builder()
                                .displayName("displayName")
                                .id("id")
                                .build())
                        .initiator(Initiator.builder()
                                .id("id")
                                .displayName("displayName")
                                .build())
                        .build())
                .commonPrefixes(CommonPrefix.builder()
                        .prefix("prefix")
                        .build())
                .encodingType(EncodingType.URL)
                .build();
        when(mockS3Client.listMultipartUploads(ListMultipartUploadsRequest.builder()
                .bucket("bucket")
                .delimiter("delimiter")
                .encodingType(EncodingType.URL)
                .keyMarker("keyMarker")
                .maxUploads(0)
                .prefix("prefix")
                .uploadIdMarker("uploadIdMarker")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(listMultipartUploadsResponse);

        // Run the test
        myClassUnderTest.tryListMultipartUploads1();

        // Verify the results
    }

    @Test
    void testTryListMultipartUploads1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.listMultipartUploads(ListMultipartUploadsRequest.builder()
                .bucket("bucket")
                .delimiter("delimiter")
                .encodingType(EncodingType.URL)
                .keyMarker("keyMarker")
                .maxUploads(0)
                .prefix("prefix")
                .uploadIdMarker("uploadIdMarker")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListMultipartUploads1());
    }

    @Test
    void testTryListMultipartUploads1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.listMultipartUploads(ListMultipartUploadsRequest.builder()
                .bucket("bucket")
                .delimiter("delimiter")
                .encodingType(EncodingType.URL)
                .keyMarker("keyMarker")
                .maxUploads(0)
                .prefix("prefix")
                .uploadIdMarker("uploadIdMarker")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListMultipartUploads1());
    }

    @Test
    void testTryListMultipartUploads1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.listMultipartUploads(ListMultipartUploadsRequest.builder()
                .bucket("bucket")
                .delimiter("delimiter")
                .encodingType(EncodingType.URL)
                .keyMarker("keyMarker")
                .maxUploads(0)
                .prefix("prefix")
                .uploadIdMarker("uploadIdMarker")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryListMultipartUploads1());
    }

    @Test
    void testTryListMultipartUploads2() {
        // Setup
        // Configure S3Client.listMultipartUploads(...).
        final ListMultipartUploadsResponse listMultipartUploadsResponse = ListMultipartUploadsResponse.builder()
                .bucket("bucket")
                .keyMarker("keyMarker")
                .uploadIdMarker("uploadIdMarker")
                .nextKeyMarker("nextKeyMarker")
                .prefix("prefix")
                .delimiter("delimiter")
                .nextUploadIdMarker("nextUploadIdMarker")
                .maxUploads(0)
                .isTruncated(false)
                .uploads(MultipartUpload.builder()
                        .uploadId("uploadId")
                        .key("key")
                        .initiated(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                        .storageClass(StorageClass.STANDARD)
                        .owner(Owner.builder()
                                .displayName("displayName")
                                .id("id")
                                .build())
                        .initiator(Initiator.builder()
                                .id("id")
                                .displayName("displayName")
                                .build())
                        .build())
                .commonPrefixes(CommonPrefix.builder()
                        .prefix("prefix")
                        .build())
                .encodingType(EncodingType.URL)
                .build();
        when(mockS3Client.listMultipartUploads(any(Consumer.class))).thenReturn(listMultipartUploadsResponse);

        // Run the test
        myClassUnderTest.tryListMultipartUploads2();

        // Verify the results
    }

    @Test
    void testTryListMultipartUploads2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.listMultipartUploads(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListMultipartUploads2());
    }

    @Test
    void testTryListMultipartUploads2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.listMultipartUploads(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListMultipartUploads2());
    }

    @Test
    void testTryListMultipartUploads2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.listMultipartUploads(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryListMultipartUploads2());
    }

    @Test
    void testTryListMultipartUploadsPaginator1() {
        // Setup
        // Configure S3Client.listMultipartUploadsPaginator(...).
        final ListMultipartUploadsIterable mockListMultipartUploadsIterable = mock(ListMultipartUploadsIterable.class);
        when(mockS3Client.listMultipartUploadsPaginator(ListMultipartUploadsRequest.builder()
                .bucket("bucket")
                .delimiter("delimiter")
                .encodingType(EncodingType.URL)
                .keyMarker("keyMarker")
                .maxUploads(0)
                .prefix("prefix")
                .uploadIdMarker("uploadIdMarker")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(mockListMultipartUploadsIterable);

        // Run the test
        myClassUnderTest.tryListMultipartUploadsPaginator1();

        // Verify the results
    }

    @Test
    void testTryListMultipartUploadsPaginator1_S3ClientReturnsNoItems() {
        // Setup
        // Configure S3Client.listMultipartUploadsPaginator(...).
        final ListMultipartUploadsIterable mockListMultipartUploadsIterable = mock(ListMultipartUploadsIterable.class);
        when(mockS3Client.listMultipartUploadsPaginator(ListMultipartUploadsRequest.builder()
                .bucket("bucket")
                .delimiter("delimiter")
                .encodingType(EncodingType.URL)
                .keyMarker("keyMarker")
                .maxUploads(0)
                .prefix("prefix")
                .uploadIdMarker("uploadIdMarker")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(mockListMultipartUploadsIterable);

        // Run the test
        myClassUnderTest.tryListMultipartUploadsPaginator1();

        // Verify the results
    }

    @Test
    void testTryListMultipartUploadsPaginator1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.listMultipartUploadsPaginator(ListMultipartUploadsRequest.builder()
                .bucket("bucket")
                .delimiter("delimiter")
                .encodingType(EncodingType.URL)
                .keyMarker("keyMarker")
                .maxUploads(0)
                .prefix("prefix")
                .uploadIdMarker("uploadIdMarker")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListMultipartUploadsPaginator1());
    }

    @Test
    void testTryListMultipartUploadsPaginator1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.listMultipartUploadsPaginator(ListMultipartUploadsRequest.builder()
                .bucket("bucket")
                .delimiter("delimiter")
                .encodingType(EncodingType.URL)
                .keyMarker("keyMarker")
                .maxUploads(0)
                .prefix("prefix")
                .uploadIdMarker("uploadIdMarker")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListMultipartUploadsPaginator1());
    }

    @Test
    void testTryListMultipartUploadsPaginator1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.listMultipartUploadsPaginator(ListMultipartUploadsRequest.builder()
                .bucket("bucket")
                .delimiter("delimiter")
                .encodingType(EncodingType.URL)
                .keyMarker("keyMarker")
                .maxUploads(0)
                .prefix("prefix")
                .uploadIdMarker("uploadIdMarker")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryListMultipartUploadsPaginator1());
    }

    @Test
    void testTryListMultipartUploadsPaginator2() {
        // Setup
        // Configure S3Client.listMultipartUploadsPaginator(...).
        final ListMultipartUploadsIterable mockListMultipartUploadsIterable = mock(ListMultipartUploadsIterable.class);
        when(mockS3Client.listMultipartUploadsPaginator(any(Consumer.class)))
                .thenReturn(mockListMultipartUploadsIterable);

        // Run the test
        myClassUnderTest.tryListMultipartUploadsPaginator2();

        // Verify the results
    }

    @Test
    void testTryListMultipartUploadsPaginator2_S3ClientReturnsNoItems() {
        // Setup
        // Configure S3Client.listMultipartUploadsPaginator(...).
        final ListMultipartUploadsIterable mockListMultipartUploadsIterable = mock(ListMultipartUploadsIterable.class);
        when(mockS3Client.listMultipartUploadsPaginator(any(Consumer.class)))
                .thenReturn(mockListMultipartUploadsIterable);

        // Run the test
        myClassUnderTest.tryListMultipartUploadsPaginator2();

        // Verify the results
    }

    @Test
    void testTryListMultipartUploadsPaginator2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.listMultipartUploadsPaginator(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListMultipartUploadsPaginator2());
    }

    @Test
    void testTryListMultipartUploadsPaginator2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.listMultipartUploadsPaginator(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListMultipartUploadsPaginator2());
    }

    @Test
    void testTryListMultipartUploadsPaginator2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.listMultipartUploadsPaginator(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryListMultipartUploadsPaginator2());
    }

    @Test
    void testTryListObjectVersions1() {
        // Setup
        // Configure S3Client.listObjectVersions(...).
        final ListObjectVersionsResponse listObjectVersionsResponse = ListObjectVersionsResponse.builder()
                .prefix("prefix")
                .versions(ObjectVersion.builder().key("key").build())
                .isTruncated(false)
                .build();
        when(mockS3Client.listObjectVersions(ListObjectVersionsRequest.builder()
                .bucket("bucket")
                .prefix("prefix")
                .build())).thenReturn(listObjectVersionsResponse);

        // Run the test
        myClassUnderTest.tryListObjectVersions1();

        // Verify the results
    }

    @Test
    void testTryListObjectVersions1_S3ClientReturnsNoItems() {
        // Setup
        // Configure S3Client.listObjectVersions(...).
        final ListObjectVersionsResponse listObjectVersionsResponse = ListObjectVersionsResponse.builder()
                .prefix("prefix")
                .isTruncated(false)
                .build();
        when(mockS3Client.listObjectVersions(ListObjectVersionsRequest.builder()
                .bucket("bucket")
                .prefix("prefix")
                .build())).thenReturn(listObjectVersionsResponse);

        // Run the test
        myClassUnderTest.tryListObjectVersions1();

        // Verify the results
    }

    @Test
    void testTryListObjectVersions1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.listObjectVersions(ListObjectVersionsRequest.builder()
                .bucket("bucket")
                .prefix("prefix")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListObjectVersions1());
    }

    @Test
    void testTryListObjectVersions1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.listObjectVersions(ListObjectVersionsRequest.builder()
                .bucket("bucket")
                .prefix("prefix")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListObjectVersions1());
    }

    @Test
    void testTryListObjectVersions1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.listObjectVersions(ListObjectVersionsRequest.builder()
                .bucket("bucket")
                .prefix("prefix")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryListObjectVersions1());
    }

    @Test
    void testTryListObjectVersions2() {
        // Setup
        // Configure S3Client.listObjectVersions(...).
        final ListObjectVersionsResponse listObjectVersionsResponse = ListObjectVersionsResponse.builder()
                .prefix("prefix")
                .versions(ObjectVersion.builder().key("key").build())
                .isTruncated(false)
                .build();
        when(mockS3Client.listObjectVersions(any(Consumer.class))).thenReturn(listObjectVersionsResponse);

        // Run the test
        myClassUnderTest.tryListObjectVersions2();

        // Verify the results
    }

    @Test
    void testTryListObjectVersions2_S3ClientReturnsNoItems() {
        // Setup
        // Configure S3Client.listObjectVersions(...).
        final ListObjectVersionsResponse listObjectVersionsResponse = ListObjectVersionsResponse.builder()
                .prefix("prefix")
                .isTruncated(false)
                .build();
        when(mockS3Client.listObjectVersions(any(Consumer.class))).thenReturn(listObjectVersionsResponse);

        // Run the test
        myClassUnderTest.tryListObjectVersions2();

        // Verify the results
    }

    @Test
    void testTryListObjectVersions2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.listObjectVersions(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListObjectVersions2());
    }

    @Test
    void testTryListObjectVersions2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.listObjectVersions(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListObjectVersions2());
    }

    @Test
    void testTryListObjectVersions2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.listObjectVersions(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryListObjectVersions2());
    }

    @Test
    void testTryListObjectVersionsPaginator1() {
        // Setup
        // Configure S3Client.listObjectVersionsPaginator(...).
        final ListObjectVersionsIterable mockListObjectVersionsIterable = mock(ListObjectVersionsIterable.class);
        when(mockS3Client.listObjectVersionsPaginator(ListObjectVersionsRequest.builder()
                .bucket("bucket")
                .prefix("prefix")
                .build())).thenReturn(mockListObjectVersionsIterable);

        // Run the test
        myClassUnderTest.tryListObjectVersionsPaginator1();

        // Verify the results
    }

    @Test
    void testTryListObjectVersionsPaginator1_S3ClientReturnsNoItems() {
        // Setup
        // Configure S3Client.listObjectVersionsPaginator(...).
        final ListObjectVersionsIterable mockListObjectVersionsIterable = mock(ListObjectVersionsIterable.class);
        when(mockS3Client.listObjectVersionsPaginator(ListObjectVersionsRequest.builder()
                .bucket("bucket")
                .prefix("prefix")
                .build())).thenReturn(mockListObjectVersionsIterable);

        // Run the test
        myClassUnderTest.tryListObjectVersionsPaginator1();

        // Verify the results
    }

    @Test
    void testTryListObjectVersionsPaginator1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.listObjectVersionsPaginator(ListObjectVersionsRequest.builder()
                .bucket("bucket")
                .prefix("prefix")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListObjectVersionsPaginator1());
    }

    @Test
    void testTryListObjectVersionsPaginator1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.listObjectVersionsPaginator(ListObjectVersionsRequest.builder()
                .bucket("bucket")
                .prefix("prefix")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListObjectVersionsPaginator1());
    }

    @Test
    void testTryListObjectVersionsPaginator1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.listObjectVersionsPaginator(ListObjectVersionsRequest.builder()
                .bucket("bucket")
                .prefix("prefix")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryListObjectVersionsPaginator1());
    }

    @Test
    void testTryListObjectVersionsPaginator2() {
        // Setup
        // Configure S3Client.listObjectVersionsPaginator(...).
        final ListObjectVersionsIterable mockListObjectVersionsIterable = mock(ListObjectVersionsIterable.class);
        when(mockS3Client.listObjectVersionsPaginator(any(Consumer.class))).thenReturn(mockListObjectVersionsIterable);

        // Run the test
        myClassUnderTest.tryListObjectVersionsPaginator2();

        // Verify the results
    }

    @Test
    void testTryListObjectVersionsPaginator2_S3ClientReturnsNoItems() {
        // Setup
        // Configure S3Client.listObjectVersionsPaginator(...).
        final ListObjectVersionsIterable mockListObjectVersionsIterable = mock(ListObjectVersionsIterable.class);
        when(mockS3Client.listObjectVersionsPaginator(any(Consumer.class))).thenReturn(mockListObjectVersionsIterable);

        // Run the test
        myClassUnderTest.tryListObjectVersionsPaginator2();

        // Verify the results
    }

    @Test
    void testTryListObjectVersionsPaginator2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.listObjectVersionsPaginator(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListObjectVersionsPaginator2());
    }

    @Test
    void testTryListObjectVersionsPaginator2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.listObjectVersionsPaginator(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListObjectVersionsPaginator2());
    }

    @Test
    void testTryListObjectVersionsPaginator2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.listObjectVersionsPaginator(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryListObjectVersionsPaginator2());
    }

    @Test
    void testTryListObjects1() {
        // Setup
        // Configure S3Client.listObjects(...).
        final ListObjectsResponse listObjectsResponse = ListObjectsResponse.builder()
                .prefix("prefix")
                .contents(S3Object.builder().key("key").build())
                .isTruncated(false)
                .build();
        when(mockS3Client.listObjects(ListObjectsRequest.builder()
                .bucket("bucket")
                .prefix("prefix")
                .build())).thenReturn(listObjectsResponse);

        // Run the test
        myClassUnderTest.tryListObjects1();

        // Verify the results
    }

    @Test
    void testTryListObjects1_S3ClientReturnsNoItems() {
        // Setup
        // Configure S3Client.listObjects(...).
        final ListObjectsResponse listObjectsResponse = ListObjectsResponse.builder()
                .prefix("prefix")
                .isTruncated(false)
                .build();
        when(mockS3Client.listObjects(ListObjectsRequest.builder()
                .bucket("bucket")
                .prefix("prefix")
                .build())).thenReturn(listObjectsResponse);

        // Run the test
        myClassUnderTest.tryListObjects1();

        // Verify the results
    }

    @Test
    void testTryListObjects1_S3ClientThrowsNoSuchBucketException() {
        // Setup
        when(mockS3Client.listObjects(ListObjectsRequest.builder()
                .bucket("bucket")
                .prefix("prefix")
                .build())).thenThrow(NoSuchBucketException.class);

        // Run the test
        assertThrows(NoSuchBucketException.class, () -> myClassUnderTest.tryListObjects1());
    }

    @Test
    void testTryListObjects1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.listObjects(ListObjectsRequest.builder()
                .bucket("bucket")
                .prefix("prefix")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListObjects1());
    }

    @Test
    void testTryListObjects1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.listObjects(ListObjectsRequest.builder()
                .bucket("bucket")
                .prefix("prefix")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListObjects1());
    }

    @Test
    void testTryListObjects1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.listObjects(ListObjectsRequest.builder()
                .bucket("bucket")
                .prefix("prefix")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryListObjects1());
    }

    @Test
    void testTryListObjects2() {
        // Setup
        // Configure S3Client.listObjects(...).
        final ListObjectsResponse listObjectsResponse = ListObjectsResponse.builder()
                .prefix("prefix")
                .contents(S3Object.builder().key("key").build())
                .isTruncated(false)
                .build();
        when(mockS3Client.listObjects(any(Consumer.class))).thenReturn(listObjectsResponse);

        // Run the test
        myClassUnderTest.tryListObjects2();

        // Verify the results
    }

    @Test
    void testTryListObjects2_S3ClientReturnsNoItems() {
        // Setup
        // Configure S3Client.listObjects(...).
        final ListObjectsResponse listObjectsResponse = ListObjectsResponse.builder()
                .prefix("prefix")
                .isTruncated(false)
                .build();
        when(mockS3Client.listObjects(any(Consumer.class))).thenReturn(listObjectsResponse);

        // Run the test
        myClassUnderTest.tryListObjects2();

        // Verify the results
    }

    @Test
    void testTryListObjects2_S3ClientThrowsNoSuchBucketException() {
        // Setup
        when(mockS3Client.listObjects(any(Consumer.class))).thenThrow(NoSuchBucketException.class);

        // Run the test
        assertThrows(NoSuchBucketException.class, () -> myClassUnderTest.tryListObjects2());
    }

    @Test
    void testTryListObjects2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.listObjects(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListObjects2());
    }

    @Test
    void testTryListObjects2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.listObjects(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListObjects2());
    }

    @Test
    void testTryListObjects2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.listObjects(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryListObjects2());
    }

    @Test
    void testTryListObjectsV21() {
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
                .bucket("bucket")
                .prefix("prefix")
                .build())).thenReturn(listObjectsV2Response);

        // Run the test
        myClassUnderTest.tryListObjectsV21();

        // Verify the results
    }

    @Test
    void testTryListObjectsV21_S3ClientReturnsNoItems() {
        // Setup
        // Configure S3Client.listObjectsV2(...).
        final ListObjectsV2Response listObjectsV2Response = ListObjectsV2Response.builder()
                .prefix("prefix")
                .isTruncated(false)
                .build();
        when(mockS3Client.listObjectsV2(ListObjectsV2Request.builder()
                .bucket("bucket")
                .prefix("prefix")
                .build())).thenReturn(listObjectsV2Response);

        // Run the test
        myClassUnderTest.tryListObjectsV21();

        // Verify the results
    }

    @Test
    void testTryListObjectsV21_S3ClientThrowsNoSuchBucketException() {
        // Setup
        when(mockS3Client.listObjectsV2(ListObjectsV2Request.builder()
                .bucket("bucket")
                .prefix("prefix")
                .build())).thenThrow(NoSuchBucketException.class);

        // Run the test
        assertThrows(NoSuchBucketException.class, () -> myClassUnderTest.tryListObjectsV21());
    }

    @Test
    void testTryListObjectsV21_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.listObjectsV2(ListObjectsV2Request.builder()
                .bucket("bucket")
                .prefix("prefix")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListObjectsV21());
    }

    @Test
    void testTryListObjectsV21_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.listObjectsV2(ListObjectsV2Request.builder()
                .bucket("bucket")
                .prefix("prefix")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListObjectsV21());
    }

    @Test
    void testTryListObjectsV21_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.listObjectsV2(ListObjectsV2Request.builder()
                .bucket("bucket")
                .prefix("prefix")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryListObjectsV21());
    }

    @Test
    void testTryListObjectsV22() {
        // Setup
        // Configure S3Client.listObjectsV2(...).
        final ListObjectsV2Response listObjectsV2Response = ListObjectsV2Response.builder()
                .prefix("prefix")
                .contents(S3Object.builder()
                        .key("key")
                        .build())
                .isTruncated(false)
                .build();
        when(mockS3Client.listObjectsV2(any(Consumer.class))).thenReturn(listObjectsV2Response);

        // Run the test
        myClassUnderTest.tryListObjectsV22();

        // Verify the results
    }

    @Test
    void testTryListObjectsV22_S3ClientReturnsNoItems() {
        // Setup
        // Configure S3Client.listObjectsV2(...).
        final ListObjectsV2Response listObjectsV2Response = ListObjectsV2Response.builder()
                .prefix("prefix")
                .isTruncated(false)
                .build();
        when(mockS3Client.listObjectsV2(any(Consumer.class))).thenReturn(listObjectsV2Response);

        // Run the test
        myClassUnderTest.tryListObjectsV22();

        // Verify the results
    }

    @Test
    void testTryListObjectsV22_S3ClientThrowsNoSuchBucketException() {
        // Setup
        when(mockS3Client.listObjectsV2(any(Consumer.class))).thenThrow(NoSuchBucketException.class);

        // Run the test
        assertThrows(NoSuchBucketException.class, () -> myClassUnderTest.tryListObjectsV22());
    }

    @Test
    void testTryListObjectsV22_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.listObjectsV2(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListObjectsV22());
    }

    @Test
    void testTryListObjectsV22_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.listObjectsV2(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListObjectsV22());
    }

    @Test
    void testTryListObjectsV22_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.listObjectsV2(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryListObjectsV22());
    }

    @Test
    void testTryListObjectsV2Paginator1() {
        // Setup
        // Configure S3Client.listObjectsV2Paginator(...).
        final ListObjectsV2Iterable mockListObjectsV2Iterable = mock(ListObjectsV2Iterable.class);
        when(mockS3Client.listObjectsV2Paginator(ListObjectsV2Request.builder()
                .bucket("bucket")
                .prefix("prefix")
                .build())).thenReturn(mockListObjectsV2Iterable);

        // Run the test
        myClassUnderTest.tryListObjectsV2Paginator1();

        // Verify the results
    }

    @Test
    void testTryListObjectsV2Paginator1_S3ClientReturnsNoItems() {
        // Setup
        // Configure S3Client.listObjectsV2Paginator(...).
        final ListObjectsV2Iterable mockListObjectsV2Iterable = mock(ListObjectsV2Iterable.class);
        when(mockS3Client.listObjectsV2Paginator(ListObjectsV2Request.builder()
                .bucket("bucket")
                .prefix("prefix")
                .build())).thenReturn(mockListObjectsV2Iterable);

        // Run the test
        myClassUnderTest.tryListObjectsV2Paginator1();

        // Verify the results
    }

    @Test
    void testTryListObjectsV2Paginator1_S3ClientThrowsNoSuchBucketException() {
        // Setup
        when(mockS3Client.listObjectsV2Paginator(ListObjectsV2Request.builder()
                .bucket("bucket")
                .prefix("prefix")
                .build())).thenThrow(NoSuchBucketException.class);

        // Run the test
        assertThrows(NoSuchBucketException.class, () -> myClassUnderTest.tryListObjectsV2Paginator1());
    }

    @Test
    void testTryListObjectsV2Paginator1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.listObjectsV2Paginator(ListObjectsV2Request.builder()
                .bucket("bucket")
                .prefix("prefix")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListObjectsV2Paginator1());
    }

    @Test
    void testTryListObjectsV2Paginator1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.listObjectsV2Paginator(ListObjectsV2Request.builder()
                .bucket("bucket")
                .prefix("prefix")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListObjectsV2Paginator1());
    }

    @Test
    void testTryListObjectsV2Paginator1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.listObjectsV2Paginator(ListObjectsV2Request.builder()
                .bucket("bucket")
                .prefix("prefix")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryListObjectsV2Paginator1());
    }

    @Test
    void testTryListObjectsV2Paginator2() {
        // Setup
        // Configure S3Client.listObjectsV2Paginator(...).
        final ListObjectsV2Iterable mockListObjectsV2Iterable = mock(ListObjectsV2Iterable.class);
        when(mockS3Client.listObjectsV2Paginator(any(Consumer.class))).thenReturn(mockListObjectsV2Iterable);

        // Run the test
        myClassUnderTest.tryListObjectsV2Paginator2();

        // Verify the results
    }

    @Test
    void testTryListObjectsV2Paginator2_S3ClientReturnsNoItems() {
        // Setup
        // Configure S3Client.listObjectsV2Paginator(...).
        final ListObjectsV2Iterable mockListObjectsV2Iterable = mock(ListObjectsV2Iterable.class);
        when(mockS3Client.listObjectsV2Paginator(any(Consumer.class))).thenReturn(mockListObjectsV2Iterable);

        // Run the test
        myClassUnderTest.tryListObjectsV2Paginator2();

        // Verify the results
    }

    @Test
    void testTryListObjectsV2Paginator2_S3ClientThrowsNoSuchBucketException() {
        // Setup
        when(mockS3Client.listObjectsV2Paginator(any(Consumer.class))).thenThrow(NoSuchBucketException.class);

        // Run the test
        assertThrows(NoSuchBucketException.class, () -> myClassUnderTest.tryListObjectsV2Paginator2());
    }

    @Test
    void testTryListObjectsV2Paginator2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.listObjectsV2Paginator(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListObjectsV2Paginator2());
    }

    @Test
    void testTryListObjectsV2Paginator2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.listObjectsV2Paginator(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListObjectsV2Paginator2());
    }

    @Test
    void testTryListObjectsV2Paginator2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.listObjectsV2Paginator(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryListObjectsV2Paginator2());
    }

    @Test
    void testTryListParts1() {
        // Setup
        // Configure S3Client.listParts(...).
        final ListPartsResponse listPartsResponse = ListPartsResponse.builder()
                .bucket("bucketName")
                .key("key")
                .parts(Part.builder().partNumber(1).build())
                .build();
        when(mockS3Client.listParts(ListPartsRequest.builder()
                .bucket("bucket")
                .key("key")
                .maxParts(0)
                .partNumberMarker(0)
                .uploadId("uploadId")
                .requestPayer(RequestPayer.REQUESTER)
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(listPartsResponse);

        // Run the test
        myClassUnderTest.tryListParts1();

        // Verify the results
    }

    @Test
    void testTryListParts1_S3ClientReturnsNoItems() {
        // Setup
        // Configure S3Client.listParts(...).
        final ListPartsResponse listPartsResponse = ListPartsResponse.builder().bucket("bucketName").key("key").build();
        when(mockS3Client.listParts(ListPartsRequest.builder()
                .bucket("bucket")
                .key("key")
                .maxParts(0)
                .partNumberMarker(0)
                .uploadId("uploadId")
                .requestPayer(RequestPayer.REQUESTER)
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(listPartsResponse);

        // Run the test
        myClassUnderTest.tryListParts1();

        // Verify the results
    }

    @Test
    void testTryListParts1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.listParts(ListPartsRequest.builder()
                .bucket("bucket")
                .key("key")
                .maxParts(0)
                .partNumberMarker(0)
                .uploadId("uploadId")
                .requestPayer(RequestPayer.REQUESTER)
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListParts1());
    }

    @Test
    void testTryListParts1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.listParts(ListPartsRequest.builder()
                .bucket("bucket")
                .key("key")
                .maxParts(0)
                .partNumberMarker(0)
                .uploadId("uploadId")
                .requestPayer(RequestPayer.REQUESTER)
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListParts1());
    }

    @Test
    void testTryListParts1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.listParts(ListPartsRequest.builder()
                .bucket("bucket")
                .key("key")
                .maxParts(0)
                .partNumberMarker(0)
                .uploadId("uploadId")
                .requestPayer(RequestPayer.REQUESTER)
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryListParts1());
    }

    @Test
    void testTryListParts2() {
        // Setup
        // Configure S3Client.listParts(...).
        final ListPartsResponse listPartsResponse = ListPartsResponse.builder()
                .bucket("bucketName")
                .key("key")
                .parts(Part.builder().partNumber(1).build())
                .build();
        when(mockS3Client.listParts(any(Consumer.class))).thenReturn(listPartsResponse);

        // Run the test
        myClassUnderTest.tryListParts2();

        // Verify the results
    }

    @Test
    void testTryListParts2_S3ClientReturnsNoItems() {
        // Setup
        // Configure S3Client.listParts(...).
        final ListPartsResponse listPartsResponse = ListPartsResponse.builder().bucket("bucketName").key("key").build();
        when(mockS3Client.listParts(any(Consumer.class))).thenReturn(listPartsResponse);

        // Run the test
        myClassUnderTest.tryListParts2();

        // Verify the results
    }

    @Test
    void testTryListParts2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.listParts(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListParts2());
    }

    @Test
    void testTryListParts2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.listParts(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListParts2());
    }

    @Test
    void testTryListParts2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.listParts(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryListParts2());
    }

    @Test
    void testTryListPartsPaginator1() {
        // Setup
        // Configure S3Client.listPartsPaginator(...).
        final ListPartsIterable mockListPartsIterable = mock(ListPartsIterable.class);
        when(mockS3Client.listPartsPaginator(ListPartsRequest.builder()
                .bucket("bucket")
                .key("key")
                .maxParts(0)
                .partNumberMarker(0)
                .uploadId("uploadId")
                .requestPayer(RequestPayer.REQUESTER)
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(mockListPartsIterable);

        // Run the test
        myClassUnderTest.tryListPartsPaginator1();

        // Verify the results
    }

    @Test
    void testTryListPartsPaginator1_S3ClientReturnsNoItems() {
        // Setup
        // Configure S3Client.listPartsPaginator(...).
        final ListPartsIterable mockListPartsIterable = mock(ListPartsIterable.class);
        when(mockS3Client.listPartsPaginator(ListPartsRequest.builder()
                .bucket("bucket")
                .key("key")
                .maxParts(0)
                .partNumberMarker(0)
                .uploadId("uploadId")
                .requestPayer(RequestPayer.REQUESTER)
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(mockListPartsIterable);

        // Run the test
        myClassUnderTest.tryListPartsPaginator1();

        // Verify the results
    }

    @Test
    void testTryListPartsPaginator1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.listPartsPaginator(ListPartsRequest.builder()
                .bucket("bucket")
                .key("key")
                .maxParts(0)
                .partNumberMarker(0)
                .uploadId("uploadId")
                .requestPayer(RequestPayer.REQUESTER)
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListPartsPaginator1());
    }

    @Test
    void testTryListPartsPaginator1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.listPartsPaginator(ListPartsRequest.builder()
                .bucket("bucket")
                .key("key")
                .maxParts(0)
                .partNumberMarker(0)
                .uploadId("uploadId")
                .requestPayer(RequestPayer.REQUESTER)
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListPartsPaginator1());
    }

    @Test
    void testTryListPartsPaginator1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.listPartsPaginator(ListPartsRequest.builder()
                .bucket("bucket")
                .key("key")
                .maxParts(0)
                .partNumberMarker(0)
                .uploadId("uploadId")
                .requestPayer(RequestPayer.REQUESTER)
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryListPartsPaginator1());
    }

    @Test
    void testTryListPartsPaginator2() {
        // Setup
        // Configure S3Client.listPartsPaginator(...).
        final ListPartsIterable mockListPartsIterable = mock(ListPartsIterable.class);
        when(mockS3Client.listPartsPaginator(any(Consumer.class))).thenReturn(mockListPartsIterable);

        // Run the test
        myClassUnderTest.tryListPartsPaginator2();

        // Verify the results
    }

    @Test
    void testTryListPartsPaginator2_S3ClientReturnsNoItems() {
        // Setup
        // Configure S3Client.listPartsPaginator(...).
        final ListPartsIterable mockListPartsIterable = mock(ListPartsIterable.class);
        when(mockS3Client.listPartsPaginator(any(Consumer.class))).thenReturn(mockListPartsIterable);

        // Run the test
        myClassUnderTest.tryListPartsPaginator2();

        // Verify the results
    }

    @Test
    void testTryListPartsPaginator2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.listPartsPaginator(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListPartsPaginator2());
    }

    @Test
    void testTryListPartsPaginator2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.listPartsPaginator(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListPartsPaginator2());
    }

    @Test
    void testTryListPartsPaginator2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.listPartsPaginator(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryListPartsPaginator2());
    }

    @Test
    void testTryPutBucketAccelerateConfiguration1() {
        // Setup
        // Configure S3Client.putBucketAccelerateConfiguration(...).
        final PutBucketAccelerateConfigurationResponse putBucketAccelerateConfigurationResponse = PutBucketAccelerateConfigurationResponse.builder().build();
        when(mockS3Client.putBucketAccelerateConfiguration(PutBucketAccelerateConfigurationRequest.builder()
                .bucket("bucket")
                .accelerateConfiguration(AccelerateConfiguration.builder()
                        .status(BucketAccelerateStatus.ENABLED)
                        .build())
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(putBucketAccelerateConfigurationResponse);

        // Run the test
        myClassUnderTest.tryPutBucketAccelerateConfiguration1();

        // Verify the results
    }

    @Test
    void testTryPutBucketAccelerateConfiguration1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.putBucketAccelerateConfiguration(PutBucketAccelerateConfigurationRequest.builder()
                .bucket("bucket")
                .accelerateConfiguration(AccelerateConfiguration.builder()
                        .status(BucketAccelerateStatus.ENABLED)
                        .build())
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryPutBucketAccelerateConfiguration1());
    }

    @Test
    void testTryPutBucketAccelerateConfiguration1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.putBucketAccelerateConfiguration(PutBucketAccelerateConfigurationRequest.builder()
                .bucket("bucket")
                .accelerateConfiguration(AccelerateConfiguration.builder()
                        .status(BucketAccelerateStatus.ENABLED)
                        .build())
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryPutBucketAccelerateConfiguration1());
    }

    @Test
    void testTryPutBucketAccelerateConfiguration1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.putBucketAccelerateConfiguration(PutBucketAccelerateConfigurationRequest.builder()
                .bucket("bucket")
                .accelerateConfiguration(AccelerateConfiguration.builder()
                        .status(BucketAccelerateStatus.ENABLED)
                        .build())
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryPutBucketAccelerateConfiguration1());
    }

    @Test
    void testTryPutBucketAccelerateConfiguration2() {
        // Setup
        // Configure S3Client.putBucketAccelerateConfiguration(...).
        final PutBucketAccelerateConfigurationResponse putBucketAccelerateConfigurationResponse = PutBucketAccelerateConfigurationResponse.builder().build();
        when(mockS3Client.putBucketAccelerateConfiguration(any(Consumer.class)))
                .thenReturn(putBucketAccelerateConfigurationResponse);

        // Run the test
        myClassUnderTest.tryPutBucketAccelerateConfiguration2();

        // Verify the results
    }

    @Test
    void testTryPutBucketAccelerateConfiguration2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.putBucketAccelerateConfiguration(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryPutBucketAccelerateConfiguration2());
    }

    @Test
    void testTryPutBucketAccelerateConfiguration2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.putBucketAccelerateConfiguration(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryPutBucketAccelerateConfiguration2());
    }

    @Test
    void testTryPutBucketAccelerateConfiguration2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.putBucketAccelerateConfiguration(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryPutBucketAccelerateConfiguration2());
    }

    @Test
    void testTryPutBucketAcl1() {
        // Setup
        when(mockS3Client.putBucketAcl(PutBucketAclRequest.builder()
                .acl(BucketCannedACL.PRIVATE)
                .accessControlPolicy(AccessControlPolicy.builder()
                        .grants(Grant.builder()
                                .grantee(Grantee.builder()
                                        .displayName("displayName")
                                        .emailAddress("emailAddress")
                                        .id("id")
                                        .type(Type.CANONICAL_USER)
                                        .uri("uri")
                                        .build())
                                .permission(Permission.FULL_CONTROL)
                                .build())
                        .owner(Owner.builder()
                                .displayName("displayName")
                                .id("id")
                                .build())
                        .build())
                .bucket("bucket")
                .contentMD5("contentMD5")
                .grantFullControl("grantFullControl")
                .grantRead("grantRead")
                .grantReadACP("grantReadACP")
                .grantWrite("grantWrite")
                .grantWriteACP("grantWriteACP")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(PutBucketAclResponse.builder().build());

        // Run the test
        myClassUnderTest.tryPutBucketAcl1();

        // Verify the results
    }

    @Test
    void testTryPutBucketAcl1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.putBucketAcl(PutBucketAclRequest.builder()
                .acl(BucketCannedACL.PRIVATE)
                .accessControlPolicy(AccessControlPolicy.builder()
                        .grants(Grant.builder()
                                .grantee(Grantee.builder()
                                        .displayName("displayName")
                                        .emailAddress("emailAddress")
                                        .id("id")
                                        .type(Type.CANONICAL_USER)
                                        .uri("uri")
                                        .build())
                                .permission(Permission.FULL_CONTROL)
                                .build())
                        .owner(Owner.builder()
                                .displayName("displayName")
                                .id("id")
                                .build())
                        .build())
                .bucket("bucket")
                .contentMD5("contentMD5")
                .grantFullControl("grantFullControl")
                .grantRead("grantRead")
                .grantReadACP("grantReadACP")
                .grantWrite("grantWrite")
                .grantWriteACP("grantWriteACP")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryPutBucketAcl1());
    }

    @Test
    void testTryPutBucketAcl1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.putBucketAcl(PutBucketAclRequest.builder()
                .acl(BucketCannedACL.PRIVATE)
                .accessControlPolicy(AccessControlPolicy.builder()
                        .grants(Grant.builder()
                                .grantee(Grantee.builder()
                                        .displayName("displayName")
                                        .emailAddress("emailAddress")
                                        .id("id")
                                        .type(Type.CANONICAL_USER)
                                        .uri("uri")
                                        .build())
                                .permission(Permission.FULL_CONTROL)
                                .build())
                        .owner(Owner.builder()
                                .displayName("displayName")
                                .id("id")
                                .build())
                        .build())
                .bucket("bucket")
                .contentMD5("contentMD5")
                .grantFullControl("grantFullControl")
                .grantRead("grantRead")
                .grantReadACP("grantReadACP")
                .grantWrite("grantWrite")
                .grantWriteACP("grantWriteACP")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryPutBucketAcl1());
    }

    @Test
    void testTryPutBucketAcl1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.putBucketAcl(PutBucketAclRequest.builder()
                .acl(BucketCannedACL.PRIVATE)
                .accessControlPolicy(AccessControlPolicy.builder()
                        .grants(Grant.builder()
                                .grantee(Grantee.builder()
                                        .displayName("displayName")
                                        .emailAddress("emailAddress")
                                        .id("id")
                                        .type(Type.CANONICAL_USER)
                                        .uri("uri")
                                        .build())
                                .permission(Permission.FULL_CONTROL)
                                .build())
                        .owner(Owner.builder()
                                .displayName("displayName")
                                .id("id")
                                .build())
                        .build())
                .bucket("bucket")
                .contentMD5("contentMD5")
                .grantFullControl("grantFullControl")
                .grantRead("grantRead")
                .grantReadACP("grantReadACP")
                .grantWrite("grantWrite")
                .grantWriteACP("grantWriteACP")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryPutBucketAcl1());
    }

    @Test
    void testTryPutBucketAcl2() {
        // Setup
        when(mockS3Client.putBucketAcl(any(Consumer.class))).thenReturn(PutBucketAclResponse.builder().build());

        // Run the test
        myClassUnderTest.tryPutBucketAcl2();

        // Verify the results
    }

    @Test
    void testTryPutBucketAcl2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.putBucketAcl(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryPutBucketAcl2());
    }

    @Test
    void testTryPutBucketAcl2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.putBucketAcl(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryPutBucketAcl2());
    }

    @Test
    void testTryPutBucketAcl2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.putBucketAcl(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryPutBucketAcl2());
    }

    @Test
    void testTryPutBucketAnalyticsConfiguration1() {
        // Setup
        // Configure S3Client.putBucketAnalyticsConfiguration(...).
        final PutBucketAnalyticsConfigurationResponse putBucketAnalyticsConfigurationResponse = PutBucketAnalyticsConfigurationResponse.builder().build();
        when(mockS3Client.putBucketAnalyticsConfiguration(PutBucketAnalyticsConfigurationRequest.builder()
                .bucket("bucket")
                .id("id")
                .analyticsConfiguration(AnalyticsConfiguration.builder()
                        .id("id")
                        .filter(AnalyticsFilter.builder()
                                .prefix("prefix")
                                .tag(Tag.builder()
                                        .key("key")
                                        .value("value")
                                        .build())
                                .and(AnalyticsAndOperator.builder()
                                        .prefix("prefix")
                                        .tags(Tag.builder()
                                                .key("key")
                                                .value("value")
                                                .build())
                                        .build())
                                .build())
                        .storageClassAnalysis(StorageClassAnalysis.builder()
                                .dataExport(StorageClassAnalysisDataExport.builder()
                                        .outputSchemaVersion(StorageClassAnalysisSchemaVersion.V_1)
                                        .destination(AnalyticsExportDestination.builder()
                                                .s3BucketDestination(AnalyticsS3BucketDestination.builder()
                                                        .format(AnalyticsS3ExportFileFormat.CSV)
                                                        .bucketAccountId("bucketAccountId")
                                                        .bucket("bucket")
                                                        .prefix("prefix")
                                                        .build())
                                                .build())
                                        .build())
                                .build())
                        .build())
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(putBucketAnalyticsConfigurationResponse);

        // Run the test
        myClassUnderTest.tryPutBucketAnalyticsConfiguration1();

        // Verify the results
    }

    @Test
    void testTryPutBucketAnalyticsConfiguration1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.putBucketAnalyticsConfiguration(PutBucketAnalyticsConfigurationRequest.builder()
                .bucket("bucket")
                .id("id")
                .analyticsConfiguration(AnalyticsConfiguration.builder()
                        .id("id")
                        .filter(AnalyticsFilter.builder()
                                .prefix("prefix")
                                .tag(Tag.builder()
                                        .key("key")
                                        .value("value")
                                        .build())
                                .and(AnalyticsAndOperator.builder()
                                        .prefix("prefix")
                                        .tags(Tag.builder()
                                                .key("key")
                                                .value("value")
                                                .build())
                                        .build())
                                .build())
                        .storageClassAnalysis(StorageClassAnalysis.builder()
                                .dataExport(StorageClassAnalysisDataExport.builder()
                                        .outputSchemaVersion(StorageClassAnalysisSchemaVersion.V_1)
                                        .destination(AnalyticsExportDestination.builder()
                                                .s3BucketDestination(AnalyticsS3BucketDestination.builder()
                                                        .format(AnalyticsS3ExportFileFormat.CSV)
                                                        .bucketAccountId("bucketAccountId")
                                                        .bucket("bucket")
                                                        .prefix("prefix")
                                                        .build())
                                                .build())
                                        .build())
                                .build())
                        .build())
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryPutBucketAnalyticsConfiguration1());
    }

    @Test
    void testTryPutBucketAnalyticsConfiguration1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.putBucketAnalyticsConfiguration(PutBucketAnalyticsConfigurationRequest.builder()
                .bucket("bucket")
                .id("id")
                .analyticsConfiguration(AnalyticsConfiguration.builder()
                        .id("id")
                        .filter(AnalyticsFilter.builder()
                                .prefix("prefix")
                                .tag(Tag.builder()
                                        .key("key")
                                        .value("value")
                                        .build())
                                .and(AnalyticsAndOperator.builder()
                                        .prefix("prefix")
                                        .tags(Tag.builder()
                                                .key("key")
                                                .value("value")
                                                .build())
                                        .build())
                                .build())
                        .storageClassAnalysis(StorageClassAnalysis.builder()
                                .dataExport(StorageClassAnalysisDataExport.builder()
                                        .outputSchemaVersion(StorageClassAnalysisSchemaVersion.V_1)
                                        .destination(AnalyticsExportDestination.builder()
                                                .s3BucketDestination(AnalyticsS3BucketDestination.builder()
                                                        .format(AnalyticsS3ExportFileFormat.CSV)
                                                        .bucketAccountId("bucketAccountId")
                                                        .bucket("bucket")
                                                        .prefix("prefix")
                                                        .build())
                                                .build())
                                        .build())
                                .build())
                        .build())
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryPutBucketAnalyticsConfiguration1());
    }

    @Test
    void testTryPutBucketAnalyticsConfiguration1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.putBucketAnalyticsConfiguration(PutBucketAnalyticsConfigurationRequest.builder()
                .bucket("bucket")
                .id("id")
                .analyticsConfiguration(AnalyticsConfiguration.builder()
                        .id("id")
                        .filter(AnalyticsFilter.builder()
                                .prefix("prefix")
                                .tag(Tag.builder()
                                        .key("key")
                                        .value("value")
                                        .build())
                                .and(AnalyticsAndOperator.builder()
                                        .prefix("prefix")
                                        .tags(Tag.builder()
                                                .key("key")
                                                .value("value")
                                                .build())
                                        .build())
                                .build())
                        .storageClassAnalysis(StorageClassAnalysis.builder()
                                .dataExport(StorageClassAnalysisDataExport.builder()
                                        .outputSchemaVersion(StorageClassAnalysisSchemaVersion.V_1)
                                        .destination(AnalyticsExportDestination.builder()
                                                .s3BucketDestination(AnalyticsS3BucketDestination.builder()
                                                        .format(AnalyticsS3ExportFileFormat.CSV)
                                                        .bucketAccountId("bucketAccountId")
                                                        .bucket("bucket")
                                                        .prefix("prefix")
                                                        .build())
                                                .build())
                                        .build())
                                .build())
                        .build())
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryPutBucketAnalyticsConfiguration1());
    }

    @Test
    void testTryPutBucketAnalyticsConfiguration2() {
        // Setup
        // Configure S3Client.putBucketAnalyticsConfiguration(...).
        final PutBucketAnalyticsConfigurationResponse putBucketAnalyticsConfigurationResponse = PutBucketAnalyticsConfigurationResponse.builder().build();
        when(mockS3Client.putBucketAnalyticsConfiguration(any(Consumer.class)))
                .thenReturn(putBucketAnalyticsConfigurationResponse);

        // Run the test
        myClassUnderTest.tryPutBucketAnalyticsConfiguration2();

        // Verify the results
    }

    @Test
    void testTryPutBucketAnalyticsConfiguration2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.putBucketAnalyticsConfiguration(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryPutBucketAnalyticsConfiguration2());
    }

    @Test
    void testTryPutBucketAnalyticsConfiguration2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.putBucketAnalyticsConfiguration(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryPutBucketAnalyticsConfiguration2());
    }

    @Test
    void testTryPutBucketAnalyticsConfiguration2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.putBucketAnalyticsConfiguration(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryPutBucketAnalyticsConfiguration2());
    }

    @Test
    void testTryPutBucketCors1() {
        // Setup
        when(mockS3Client.putBucketCors(PutBucketCorsRequest.builder()
                .bucket("bucket")
                .corsConfiguration(CORSConfiguration.builder()
                        .corsRules(CORSRule.builder()
                                .allowedHeaders("allowedHeaders")
                                .allowedMethods("allowedMethods")
                                .allowedOrigins("allowedOrigins")
                                .exposeHeaders("exposeHeaders")
                                .maxAgeSeconds(0)
                                .build())
                        .build())
                .contentMD5("contentMD5")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(PutBucketCorsResponse.builder().build());

        // Run the test
        myClassUnderTest.tryPutBucketCors1();

        // Verify the results
    }

    @Test
    void testTryPutBucketCors1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.putBucketCors(PutBucketCorsRequest.builder()
                .bucket("bucket")
                .corsConfiguration(CORSConfiguration.builder()
                        .corsRules(CORSRule.builder()
                                .allowedHeaders("allowedHeaders")
                                .allowedMethods("allowedMethods")
                                .allowedOrigins("allowedOrigins")
                                .exposeHeaders("exposeHeaders")
                                .maxAgeSeconds(0)
                                .build())
                        .build())
                .contentMD5("contentMD5")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryPutBucketCors1());
    }

    @Test
    void testTryPutBucketCors1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.putBucketCors(PutBucketCorsRequest.builder()
                .bucket("bucket")
                .corsConfiguration(CORSConfiguration.builder()
                        .corsRules(CORSRule.builder()
                                .allowedHeaders("allowedHeaders")
                                .allowedMethods("allowedMethods")
                                .allowedOrigins("allowedOrigins")
                                .exposeHeaders("exposeHeaders")
                                .maxAgeSeconds(0)
                                .build())
                        .build())
                .contentMD5("contentMD5")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryPutBucketCors1());
    }

    @Test
    void testTryPutBucketCors1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.putBucketCors(PutBucketCorsRequest.builder()
                .bucket("bucket")
                .corsConfiguration(CORSConfiguration.builder()
                        .corsRules(CORSRule.builder()
                                .allowedHeaders("allowedHeaders")
                                .allowedMethods("allowedMethods")
                                .allowedOrigins("allowedOrigins")
                                .exposeHeaders("exposeHeaders")
                                .maxAgeSeconds(0)
                                .build())
                        .build())
                .contentMD5("contentMD5")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryPutBucketCors1());
    }

    @Test
    void testTryPutBucketCors2() {
        // Setup
        when(mockS3Client.putBucketCors(any(Consumer.class))).thenReturn(PutBucketCorsResponse.builder().build());

        // Run the test
        myClassUnderTest.tryPutBucketCors2();

        // Verify the results
    }

    @Test
    void testTryPutBucketCors2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.putBucketCors(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryPutBucketCors2());
    }

    @Test
    void testTryPutBucketCors2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.putBucketCors(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryPutBucketCors2());
    }

    @Test
    void testTryPutBucketCors2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.putBucketCors(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryPutBucketCors2());
    }

    @Test
    void testTryPutBucketEncryption1() {
        // Setup
        // Configure S3Client.putBucketEncryption(...).
        final PutBucketEncryptionResponse putBucketEncryptionResponse = PutBucketEncryptionResponse.builder().build();
        when(mockS3Client.putBucketEncryption(PutBucketEncryptionRequest.builder()
                .bucket("bucket")
                .contentMD5("contentMD5")
                .serverSideEncryptionConfiguration(ServerSideEncryptionConfiguration.builder()
                        .rules(ServerSideEncryptionRule.builder()
                                .applyServerSideEncryptionByDefault(ServerSideEncryptionByDefault.builder()
                                        .sseAlgorithm(ServerSideEncryption.AES256)
                                        .kmsMasterKeyID("kmsMasterKeyID")
                                        .build())
                                .build())
                        .build())
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(putBucketEncryptionResponse);

        // Run the test
        myClassUnderTest.tryPutBucketEncryption1();

        // Verify the results
    }

    @Test
    void testTryPutBucketEncryption1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.putBucketEncryption(PutBucketEncryptionRequest.builder()
                .bucket("bucket")
                .contentMD5("contentMD5")
                .serverSideEncryptionConfiguration(ServerSideEncryptionConfiguration.builder()
                        .rules(ServerSideEncryptionRule.builder()
                                .applyServerSideEncryptionByDefault(ServerSideEncryptionByDefault.builder()
                                        .sseAlgorithm(ServerSideEncryption.AES256)
                                        .kmsMasterKeyID("kmsMasterKeyID")
                                        .build())
                                .build())
                        .build())
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryPutBucketEncryption1());
    }

    @Test
    void testTryPutBucketEncryption1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.putBucketEncryption(PutBucketEncryptionRequest.builder()
                .bucket("bucket")
                .contentMD5("contentMD5")
                .serverSideEncryptionConfiguration(ServerSideEncryptionConfiguration.builder()
                        .rules(ServerSideEncryptionRule.builder()
                                .applyServerSideEncryptionByDefault(ServerSideEncryptionByDefault.builder()
                                        .sseAlgorithm(ServerSideEncryption.AES256)
                                        .kmsMasterKeyID("kmsMasterKeyID")
                                        .build())
                                .build())
                        .build())
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryPutBucketEncryption1());
    }

    @Test
    void testTryPutBucketEncryption1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.putBucketEncryption(PutBucketEncryptionRequest.builder()
                .bucket("bucket")
                .contentMD5("contentMD5")
                .serverSideEncryptionConfiguration(ServerSideEncryptionConfiguration.builder()
                        .rules(ServerSideEncryptionRule.builder()
                                .applyServerSideEncryptionByDefault(ServerSideEncryptionByDefault.builder()
                                        .sseAlgorithm(ServerSideEncryption.AES256)
                                        .kmsMasterKeyID("kmsMasterKeyID")
                                        .build())
                                .build())
                        .build())
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryPutBucketEncryption1());
    }

    @Test
    void testTryPutBucketEncryption2() {
        // Setup
        // Configure S3Client.putBucketEncryption(...).
        final PutBucketEncryptionResponse putBucketEncryptionResponse = PutBucketEncryptionResponse.builder().build();
        when(mockS3Client.putBucketEncryption(any(Consumer.class))).thenReturn(putBucketEncryptionResponse);

        // Run the test
        myClassUnderTest.tryPutBucketEncryption2();

        // Verify the results
    }

    @Test
    void testTryPutBucketEncryption2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.putBucketEncryption(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryPutBucketEncryption2());
    }

    @Test
    void testTryPutBucketEncryption2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.putBucketEncryption(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryPutBucketEncryption2());
    }

    @Test
    void testTryPutBucketEncryption2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.putBucketEncryption(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryPutBucketEncryption2());
    }

    @Test
    void testTryPutBucketInventoryConfiguration1() {
        // Setup
        // Configure S3Client.putBucketInventoryConfiguration(...).
        final PutBucketInventoryConfigurationResponse putBucketInventoryConfigurationResponse = PutBucketInventoryConfigurationResponse.builder().build();
        when(mockS3Client.putBucketInventoryConfiguration(PutBucketInventoryConfigurationRequest.builder()
                .bucket("bucket")
                .id("id")
                .inventoryConfiguration(InventoryConfiguration.builder()
                        .destination(InventoryDestination.builder()
                                .s3BucketDestination(InventoryS3BucketDestination.builder()
                                        .accountId("accountId")
                                        .bucket("bucket")
                                        .format(InventoryFormat.CSV)
                                        .prefix("prefix")
                                        .encryption(InventoryEncryption.builder()
                                                .sses3(SSES3.builder().build())
                                                .ssekms(SSEKMS.builder()
                                                        .keyId("keyId")
                                                        .build())
                                                .build())
                                        .build())
                                .build())
                        .isEnabled(false)
                        .filter(InventoryFilter.builder()
                                .prefix("prefix")
                                .build())
                        .id("id")
                        .includedObjectVersions(InventoryIncludedObjectVersions.ALL)
                        .optionalFields(InventoryOptionalField.SIZE)
                        .schedule(InventorySchedule.builder()
                                .frequency(InventoryFrequency.DAILY)
                                .build())
                        .build())
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(putBucketInventoryConfigurationResponse);

        // Run the test
        myClassUnderTest.tryPutBucketInventoryConfiguration1();

        // Verify the results
    }

    @Test
    void testTryPutBucketInventoryConfiguration1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.putBucketInventoryConfiguration(PutBucketInventoryConfigurationRequest.builder()
                .bucket("bucket")
                .id("id")
                .inventoryConfiguration(InventoryConfiguration.builder()
                        .destination(InventoryDestination.builder()
                                .s3BucketDestination(InventoryS3BucketDestination.builder()
                                        .accountId("accountId")
                                        .bucket("bucket")
                                        .format(InventoryFormat.CSV)
                                        .prefix("prefix")
                                        .encryption(InventoryEncryption.builder()
                                                .sses3(SSES3.builder().build())
                                                .ssekms(SSEKMS.builder()
                                                        .keyId("keyId")
                                                        .build())
                                                .build())
                                        .build())
                                .build())
                        .isEnabled(false)
                        .filter(InventoryFilter.builder()
                                .prefix("prefix")
                                .build())
                        .id("id")
                        .includedObjectVersions(InventoryIncludedObjectVersions.ALL)
                        .optionalFields(InventoryOptionalField.SIZE)
                        .schedule(InventorySchedule.builder()
                                .frequency(InventoryFrequency.DAILY)
                                .build())
                        .build())
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryPutBucketInventoryConfiguration1());
    }

    @Test
    void testTryPutBucketInventoryConfiguration1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.putBucketInventoryConfiguration(PutBucketInventoryConfigurationRequest.builder()
                .bucket("bucket")
                .id("id")
                .inventoryConfiguration(InventoryConfiguration.builder()
                        .destination(InventoryDestination.builder()
                                .s3BucketDestination(InventoryS3BucketDestination.builder()
                                        .accountId("accountId")
                                        .bucket("bucket")
                                        .format(InventoryFormat.CSV)
                                        .prefix("prefix")
                                        .encryption(InventoryEncryption.builder()
                                                .sses3(SSES3.builder().build())
                                                .ssekms(SSEKMS.builder()
                                                        .keyId("keyId")
                                                        .build())
                                                .build())
                                        .build())
                                .build())
                        .isEnabled(false)
                        .filter(InventoryFilter.builder()
                                .prefix("prefix")
                                .build())
                        .id("id")
                        .includedObjectVersions(InventoryIncludedObjectVersions.ALL)
                        .optionalFields(InventoryOptionalField.SIZE)
                        .schedule(InventorySchedule.builder()
                                .frequency(InventoryFrequency.DAILY)
                                .build())
                        .build())
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryPutBucketInventoryConfiguration1());
    }

    @Test
    void testTryPutBucketInventoryConfiguration1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.putBucketInventoryConfiguration(PutBucketInventoryConfigurationRequest.builder()
                .bucket("bucket")
                .id("id")
                .inventoryConfiguration(InventoryConfiguration.builder()
                        .destination(InventoryDestination.builder()
                                .s3BucketDestination(InventoryS3BucketDestination.builder()
                                        .accountId("accountId")
                                        .bucket("bucket")
                                        .format(InventoryFormat.CSV)
                                        .prefix("prefix")
                                        .encryption(InventoryEncryption.builder()
                                                .sses3(SSES3.builder().build())
                                                .ssekms(SSEKMS.builder()
                                                        .keyId("keyId")
                                                        .build())
                                                .build())
                                        .build())
                                .build())
                        .isEnabled(false)
                        .filter(InventoryFilter.builder()
                                .prefix("prefix")
                                .build())
                        .id("id")
                        .includedObjectVersions(InventoryIncludedObjectVersions.ALL)
                        .optionalFields(InventoryOptionalField.SIZE)
                        .schedule(InventorySchedule.builder()
                                .frequency(InventoryFrequency.DAILY)
                                .build())
                        .build())
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryPutBucketInventoryConfiguration1());
    }

    @Test
    void testTryPutBucketInventoryConfiguration2() {
        // Setup
        // Configure S3Client.putBucketInventoryConfiguration(...).
        final PutBucketInventoryConfigurationResponse putBucketInventoryConfigurationResponse = PutBucketInventoryConfigurationResponse.builder().build();
        when(mockS3Client.putBucketInventoryConfiguration(any(Consumer.class)))
                .thenReturn(putBucketInventoryConfigurationResponse);

        // Run the test
        myClassUnderTest.tryPutBucketInventoryConfiguration2();

        // Verify the results
    }

    @Test
    void testTryPutBucketInventoryConfiguration2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.putBucketInventoryConfiguration(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryPutBucketInventoryConfiguration2());
    }

    @Test
    void testTryPutBucketInventoryConfiguration2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.putBucketInventoryConfiguration(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryPutBucketInventoryConfiguration2());
    }

    @Test
    void testTryPutBucketInventoryConfiguration2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.putBucketInventoryConfiguration(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryPutBucketInventoryConfiguration2());
    }

    @Test
    void testTryPutBucketLifecycleConfiguration1() {
        // Setup
        // Configure S3Client.putBucketLifecycleConfiguration(...).
        final PutBucketLifecycleConfigurationResponse putBucketLifecycleConfigurationResponse = PutBucketLifecycleConfigurationResponse.builder().build();
        when(mockS3Client.putBucketLifecycleConfiguration(PutBucketLifecycleConfigurationRequest.builder()
                .bucket("bucket")
                .lifecycleConfiguration(BucketLifecycleConfiguration.builder()
                        .rules(LifecycleRule.builder()
                                .expiration(LifecycleExpiration.builder()
                                        .date(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                        .days(0)
                                        .expiredObjectDeleteMarker(false)
                                        .build())
                                .id("id")
                                .prefix("prefix")
                                .filter(LifecycleRuleFilter.builder()
                                        .prefix("prefix")
                                        .tag(Tag.builder()
                                                .key("key")
                                                .value("value")
                                                .build())
                                        .and(LifecycleRuleAndOperator.builder()
                                                .prefix("prefix")
                                                .tags(Tag.builder()
                                                        .key("key")
                                                        .value("value")
                                                        .build())
                                                .build())
                                        .build())
                                .status(ExpirationStatus.ENABLED)
                                .transitions(Transition.builder()
                                        .date(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                        .days(0)
                                        .storageClass(TransitionStorageClass.GLACIER)
                                        .build())
                                .noncurrentVersionTransitions(NoncurrentVersionTransition.builder()
                                        .noncurrentDays(0)
                                        .storageClass(TransitionStorageClass.GLACIER)
                                        .build())
                                .noncurrentVersionExpiration(NoncurrentVersionExpiration.builder()
                                        .noncurrentDays(0)
                                        .build())
                                .abortIncompleteMultipartUpload(AbortIncompleteMultipartUpload.builder().build())
                                .build())
                        .build())
                .build())).thenReturn(putBucketLifecycleConfigurationResponse);

        // Run the test
        myClassUnderTest.tryPutBucketLifecycleConfiguration1();

        // Verify the results
    }

    @Test
    void testTryPutBucketLifecycleConfiguration1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.putBucketLifecycleConfiguration(PutBucketLifecycleConfigurationRequest.builder()
                .bucket("bucket")
                .lifecycleConfiguration(BucketLifecycleConfiguration.builder()
                        .rules(LifecycleRule.builder()
                                .expiration(LifecycleExpiration.builder()
                                        .date(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                        .days(0)
                                        .expiredObjectDeleteMarker(false)
                                        .build())
                                .id("id")
                                .prefix("prefix")
                                .filter(LifecycleRuleFilter.builder()
                                        .prefix("prefix")
                                        .tag(Tag.builder()
                                                .key("key")
                                                .value("value")
                                                .build())
                                        .and(LifecycleRuleAndOperator.builder()
                                                .prefix("prefix")
                                                .tags(Tag.builder()
                                                        .key("key")
                                                        .value("value")
                                                        .build())
                                                .build())
                                        .build())
                                .status(ExpirationStatus.ENABLED)
                                .transitions(Transition.builder()
                                        .date(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                        .days(0)
                                        .storageClass(TransitionStorageClass.GLACIER)
                                        .build())
                                .noncurrentVersionTransitions(NoncurrentVersionTransition.builder()
                                        .noncurrentDays(0)
                                        .storageClass(TransitionStorageClass.GLACIER)
                                        .build())
                                .noncurrentVersionExpiration(NoncurrentVersionExpiration.builder()
                                        .noncurrentDays(0)
                                        .build())
                                .abortIncompleteMultipartUpload(AbortIncompleteMultipartUpload.builder().build())
                                .build())
                        .build())
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryPutBucketLifecycleConfiguration1());
    }

    @Test
    void testTryPutBucketLifecycleConfiguration1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.putBucketLifecycleConfiguration(PutBucketLifecycleConfigurationRequest.builder()
                .bucket("bucket")
                .lifecycleConfiguration(BucketLifecycleConfiguration.builder()
                        .rules(LifecycleRule.builder()
                                .expiration(LifecycleExpiration.builder()
                                        .date(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                        .days(0)
                                        .expiredObjectDeleteMarker(false)
                                        .build())
                                .id("id")
                                .prefix("prefix")
                                .filter(LifecycleRuleFilter.builder()
                                        .prefix("prefix")
                                        .tag(Tag.builder()
                                                .key("key")
                                                .value("value")
                                                .build())
                                        .and(LifecycleRuleAndOperator.builder()
                                                .prefix("prefix")
                                                .tags(Tag.builder()
                                                        .key("key")
                                                        .value("value")
                                                        .build())
                                                .build())
                                        .build())
                                .status(ExpirationStatus.ENABLED)
                                .transitions(Transition.builder()
                                        .date(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                        .days(0)
                                        .storageClass(TransitionStorageClass.GLACIER)
                                        .build())
                                .noncurrentVersionTransitions(NoncurrentVersionTransition.builder()
                                        .noncurrentDays(0)
                                        .storageClass(TransitionStorageClass.GLACIER)
                                        .build())
                                .noncurrentVersionExpiration(NoncurrentVersionExpiration.builder()
                                        .noncurrentDays(0)
                                        .build())
                                .abortIncompleteMultipartUpload(AbortIncompleteMultipartUpload.builder().build())
                                .build())
                        .build())
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryPutBucketLifecycleConfiguration1());
    }

    @Test
    void testTryPutBucketLifecycleConfiguration1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.putBucketLifecycleConfiguration(PutBucketLifecycleConfigurationRequest.builder()
                .bucket("bucket")
                .lifecycleConfiguration(BucketLifecycleConfiguration.builder()
                        .rules(LifecycleRule.builder()
                                .expiration(LifecycleExpiration.builder()
                                        .date(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                        .days(0)
                                        .expiredObjectDeleteMarker(false)
                                        .build())
                                .id("id")
                                .prefix("prefix")
                                .filter(LifecycleRuleFilter.builder()
                                        .prefix("prefix")
                                        .tag(Tag.builder()
                                                .key("key")
                                                .value("value")
                                                .build())
                                        .and(LifecycleRuleAndOperator.builder()
                                                .prefix("prefix")
                                                .tags(Tag.builder()
                                                        .key("key")
                                                        .value("value")
                                                        .build())
                                                .build())
                                        .build())
                                .status(ExpirationStatus.ENABLED)
                                .transitions(Transition.builder()
                                        .date(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                                        .days(0)
                                        .storageClass(TransitionStorageClass.GLACIER)
                                        .build())
                                .noncurrentVersionTransitions(NoncurrentVersionTransition.builder()
                                        .noncurrentDays(0)
                                        .storageClass(TransitionStorageClass.GLACIER)
                                        .build())
                                .noncurrentVersionExpiration(NoncurrentVersionExpiration.builder()
                                        .noncurrentDays(0)
                                        .build())
                                .abortIncompleteMultipartUpload(AbortIncompleteMultipartUpload.builder().build())
                                .build())
                        .build())
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryPutBucketLifecycleConfiguration1());
    }

    @Test
    void testTryPutBucketLifecycleConfiguration2() {
        // Setup
        // Configure S3Client.putBucketLifecycleConfiguration(...).
        final PutBucketLifecycleConfigurationResponse putBucketLifecycleConfigurationResponse = PutBucketLifecycleConfigurationResponse.builder().build();
        when(mockS3Client.putBucketLifecycleConfiguration(any(Consumer.class)))
                .thenReturn(putBucketLifecycleConfigurationResponse);

        // Run the test
        myClassUnderTest.tryPutBucketLifecycleConfiguration2();

        // Verify the results
    }

    @Test
    void testTryPutBucketLifecycleConfiguration2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.putBucketLifecycleConfiguration(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryPutBucketLifecycleConfiguration2());
    }

    @Test
    void testTryPutBucketLifecycleConfiguration2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.putBucketLifecycleConfiguration(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryPutBucketLifecycleConfiguration2());
    }

    @Test
    void testTryPutBucketLifecycleConfiguration2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.putBucketLifecycleConfiguration(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryPutBucketLifecycleConfiguration2());
    }

    @Test
    void testTryPutBucketLogging1() {
        // Setup
        // Configure S3Client.putBucketLogging(...).
        final PutBucketLoggingResponse putBucketLoggingResponse = PutBucketLoggingResponse.builder().build();
        when(mockS3Client.putBucketLogging(PutBucketLoggingRequest.builder()
                .bucket("bucket")
                .bucketLoggingStatus(BucketLoggingStatus.builder()
                        .loggingEnabled(LoggingEnabled.builder()
                                .targetBucket("targetBucket")
                                .targetGrants(TargetGrant.builder()
                                        .grantee(Grantee.builder()
                                                .displayName("displayName")
                                                .emailAddress("emailAddress")
                                                .id("id")
                                                .type(Type.CANONICAL_USER)
                                                .uri("uri")
                                                .build())
                                        .permission(BucketLogsPermission.FULL_CONTROL)
                                        .build())
                                .targetPrefix("targetPrefix")
                                .build())
                        .build())
                .contentMD5("contentMD5")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(putBucketLoggingResponse);

        // Run the test
        myClassUnderTest.tryPutBucketLogging1();

        // Verify the results
    }

    @Test
    void testTryPutBucketLogging1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.putBucketLogging(PutBucketLoggingRequest.builder()
                .bucket("bucket")
                .bucketLoggingStatus(BucketLoggingStatus.builder()
                        .loggingEnabled(LoggingEnabled.builder()
                                .targetBucket("targetBucket")
                                .targetGrants(TargetGrant.builder()
                                        .grantee(Grantee.builder()
                                                .displayName("displayName")
                                                .emailAddress("emailAddress")
                                                .id("id")
                                                .type(Type.CANONICAL_USER)
                                                .uri("uri")
                                                .build())
                                        .permission(BucketLogsPermission.FULL_CONTROL)
                                        .build())
                                .targetPrefix("targetPrefix")
                                .build())
                        .build())
                .contentMD5("contentMD5")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryPutBucketLogging1());
    }

    @Test
    void testTryPutBucketLogging1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.putBucketLogging(PutBucketLoggingRequest.builder()
                .bucket("bucket")
                .bucketLoggingStatus(BucketLoggingStatus.builder()
                        .loggingEnabled(LoggingEnabled.builder()
                                .targetBucket("targetBucket")
                                .targetGrants(TargetGrant.builder()
                                        .grantee(Grantee.builder()
                                                .displayName("displayName")
                                                .emailAddress("emailAddress")
                                                .id("id")
                                                .type(Type.CANONICAL_USER)
                                                .uri("uri")
                                                .build())
                                        .permission(BucketLogsPermission.FULL_CONTROL)
                                        .build())
                                .targetPrefix("targetPrefix")
                                .build())
                        .build())
                .contentMD5("contentMD5")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryPutBucketLogging1());
    }

    @Test
    void testTryPutBucketLogging1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.putBucketLogging(PutBucketLoggingRequest.builder()
                .bucket("bucket")
                .bucketLoggingStatus(BucketLoggingStatus.builder()
                        .loggingEnabled(LoggingEnabled.builder()
                                .targetBucket("targetBucket")
                                .targetGrants(TargetGrant.builder()
                                        .grantee(Grantee.builder()
                                                .displayName("displayName")
                                                .emailAddress("emailAddress")
                                                .id("id")
                                                .type(Type.CANONICAL_USER)
                                                .uri("uri")
                                                .build())
                                        .permission(BucketLogsPermission.FULL_CONTROL)
                                        .build())
                                .targetPrefix("targetPrefix")
                                .build())
                        .build())
                .contentMD5("contentMD5")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryPutBucketLogging1());
    }

    @Test
    void testTryPutBucketLogging2() {
        // Setup
        // Configure S3Client.putBucketLogging(...).
        final PutBucketLoggingResponse putBucketLoggingResponse = PutBucketLoggingResponse.builder().build();
        when(mockS3Client.putBucketLogging(any(Consumer.class))).thenReturn(putBucketLoggingResponse);

        // Run the test
        myClassUnderTest.tryPutBucketLogging2();

        // Verify the results
    }

    @Test
    void testTryPutBucketLogging2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.putBucketLogging(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryPutBucketLogging2());
    }

    @Test
    void testTryPutBucketLogging2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.putBucketLogging(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryPutBucketLogging2());
    }

    @Test
    void testTryPutBucketLogging2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.putBucketLogging(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryPutBucketLogging2());
    }

    @Test
    void testTryPutBucketMetricsConfiguration1() {
        // Setup
        // Configure S3Client.putBucketMetricsConfiguration(...).
        final PutBucketMetricsConfigurationResponse putBucketMetricsConfigurationResponse = PutBucketMetricsConfigurationResponse.builder().build();
        when(mockS3Client.putBucketMetricsConfiguration(PutBucketMetricsConfigurationRequest.builder()
                .bucket("bucket")
                .id("id")
                .metricsConfiguration(MetricsConfiguration.builder()
                        .id("id")
                        .filter(MetricsFilter.builder()
                                .prefix("prefix")
                                .tag(Tag.builder()
                                        .key("key")
                                        .value("value")
                                        .build())
                                .and(MetricsAndOperator.builder()
                                        .prefix("prefix")
                                        .tags(Tag.builder()
                                                .key("key")
                                                .value("value")
                                                .build())
                                        .build())
                                .build())
                        .build())
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(putBucketMetricsConfigurationResponse);

        // Run the test
        myClassUnderTest.tryPutBucketMetricsConfiguration1();

        // Verify the results
    }

    @Test
    void testTryPutBucketMetricsConfiguration1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.putBucketMetricsConfiguration(PutBucketMetricsConfigurationRequest.builder()
                .bucket("bucket")
                .id("id")
                .metricsConfiguration(MetricsConfiguration.builder()
                        .id("id")
                        .filter(MetricsFilter.builder()
                                .prefix("prefix")
                                .tag(Tag.builder()
                                        .key("key")
                                        .value("value")
                                        .build())
                                .and(MetricsAndOperator.builder()
                                        .prefix("prefix")
                                        .tags(Tag.builder()
                                                .key("key")
                                                .value("value")
                                                .build())
                                        .build())
                                .build())
                        .build())
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryPutBucketMetricsConfiguration1());
    }

    @Test
    void testTryPutBucketMetricsConfiguration1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.putBucketMetricsConfiguration(PutBucketMetricsConfigurationRequest.builder()
                .bucket("bucket")
                .id("id")
                .metricsConfiguration(MetricsConfiguration.builder()
                        .id("id")
                        .filter(MetricsFilter.builder()
                                .prefix("prefix")
                                .tag(Tag.builder()
                                        .key("key")
                                        .value("value")
                                        .build())
                                .and(MetricsAndOperator.builder()
                                        .prefix("prefix")
                                        .tags(Tag.builder()
                                                .key("key")
                                                .value("value")
                                                .build())
                                        .build())
                                .build())
                        .build())
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryPutBucketMetricsConfiguration1());
    }

    @Test
    void testTryPutBucketMetricsConfiguration1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.putBucketMetricsConfiguration(PutBucketMetricsConfigurationRequest.builder()
                .bucket("bucket")
                .id("id")
                .metricsConfiguration(MetricsConfiguration.builder()
                        .id("id")
                        .filter(MetricsFilter.builder()
                                .prefix("prefix")
                                .tag(Tag.builder()
                                        .key("key")
                                        .value("value")
                                        .build())
                                .and(MetricsAndOperator.builder()
                                        .prefix("prefix")
                                        .tags(Tag.builder()
                                                .key("key")
                                                .value("value")
                                                .build())
                                        .build())
                                .build())
                        .build())
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryPutBucketMetricsConfiguration1());
    }

    @Test
    void testTryPutBucketMetricsConfiguration2() {
        // Setup
        // Configure S3Client.putBucketMetricsConfiguration(...).
        final PutBucketMetricsConfigurationResponse putBucketMetricsConfigurationResponse = PutBucketMetricsConfigurationResponse.builder().build();
        when(mockS3Client.putBucketMetricsConfiguration(any(Consumer.class)))
                .thenReturn(putBucketMetricsConfigurationResponse);

        // Run the test
        myClassUnderTest.tryPutBucketMetricsConfiguration2();

        // Verify the results
    }

    @Test
    void testTryPutBucketMetricsConfiguration2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.putBucketMetricsConfiguration(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryPutBucketMetricsConfiguration2());
    }

    @Test
    void testTryPutBucketMetricsConfiguration2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.putBucketMetricsConfiguration(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryPutBucketMetricsConfiguration2());
    }

    @Test
    void testTryPutBucketMetricsConfiguration2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.putBucketMetricsConfiguration(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryPutBucketMetricsConfiguration2());
    }

    @Test
    void testTryPutBucketNotificationConfiguration1() {
        // Setup
        // Configure S3Client.putBucketNotificationConfiguration(...).
        final PutBucketNotificationConfigurationResponse putBucketNotificationConfigurationResponse = PutBucketNotificationConfigurationResponse.builder().build();
        when(mockS3Client.putBucketNotificationConfiguration(PutBucketNotificationConfigurationRequest.builder()
                .bucket("bucket")
                .notificationConfiguration(NotificationConfiguration.builder()
                        .topicConfigurations(TopicConfiguration.builder()
                                .id("id")
                                .topicArn("topicArn")
                                .events(Event.S3_REDUCED_REDUNDANCY_LOST_OBJECT)
                                .filter(NotificationConfigurationFilter.builder()
                                        .key(S3KeyFilter.builder()
                                                .filterRules(FilterRule.builder()
                                                        .name(FilterRuleName.PREFIX)
                                                        .value("value")
                                                        .build())
                                                .build())
                                        .build())
                                .build())
                        .queueConfigurations(QueueConfiguration.builder()
                                .id("id")
                                .queueArn("queueArn")
                                .events(Event.S3_REDUCED_REDUNDANCY_LOST_OBJECT)
                                .filter(NotificationConfigurationFilter.builder()
                                        .key(S3KeyFilter.builder()
                                                .filterRules(FilterRule.builder()
                                                        .name(FilterRuleName.PREFIX)
                                                        .value("value")
                                                        .build())
                                                .build())
                                        .build())
                                .build())
                        .lambdaFunctionConfigurations(LambdaFunctionConfiguration.builder()
                                .id("id")
                                .lambdaFunctionArn("lambdaFunctionArn")
                                .events(Event.S3_REDUCED_REDUNDANCY_LOST_OBJECT)
                                .filter(NotificationConfigurationFilter.builder()
                                        .key(S3KeyFilter.builder()
                                                .filterRules(FilterRule.builder()
                                                        .name(FilterRuleName.PREFIX)
                                                        .value("value")
                                                        .build())
                                                .build())
                                        .build())
                                .build())
                        .build())
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(putBucketNotificationConfigurationResponse);

        // Run the test
        myClassUnderTest.tryPutBucketNotificationConfiguration1();

        // Verify the results
    }

    @Test
    void testTryPutBucketNotificationConfiguration1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.putBucketNotificationConfiguration(PutBucketNotificationConfigurationRequest.builder()
                .bucket("bucket")
                .notificationConfiguration(NotificationConfiguration.builder()
                        .topicConfigurations(TopicConfiguration.builder()
                                .id("id")
                                .topicArn("topicArn")
                                .events(Event.S3_REDUCED_REDUNDANCY_LOST_OBJECT)
                                .filter(NotificationConfigurationFilter.builder()
                                        .key(S3KeyFilter.builder()
                                                .filterRules(FilterRule.builder()
                                                        .name(FilterRuleName.PREFIX)
                                                        .value("value")
                                                        .build())
                                                .build())
                                        .build())
                                .build())
                        .queueConfigurations(QueueConfiguration.builder()
                                .id("id")
                                .queueArn("queueArn")
                                .events(Event.S3_REDUCED_REDUNDANCY_LOST_OBJECT)
                                .filter(NotificationConfigurationFilter.builder()
                                        .key(S3KeyFilter.builder()
                                                .filterRules(FilterRule.builder()
                                                        .name(FilterRuleName.PREFIX)
                                                        .value("value")
                                                        .build())
                                                .build())
                                        .build())
                                .build())
                        .lambdaFunctionConfigurations(LambdaFunctionConfiguration.builder()
                                .id("id")
                                .lambdaFunctionArn("lambdaFunctionArn")
                                .events(Event.S3_REDUCED_REDUNDANCY_LOST_OBJECT)
                                .filter(NotificationConfigurationFilter.builder()
                                        .key(S3KeyFilter.builder()
                                                .filterRules(FilterRule.builder()
                                                        .name(FilterRuleName.PREFIX)
                                                        .value("value")
                                                        .build())
                                                .build())
                                        .build())
                                .build())
                        .build())
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryPutBucketNotificationConfiguration1());
    }

    @Test
    void testTryPutBucketNotificationConfiguration1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.putBucketNotificationConfiguration(PutBucketNotificationConfigurationRequest.builder()
                .bucket("bucket")
                .notificationConfiguration(NotificationConfiguration.builder()
                        .topicConfigurations(TopicConfiguration.builder()
                                .id("id")
                                .topicArn("topicArn")
                                .events(Event.S3_REDUCED_REDUNDANCY_LOST_OBJECT)
                                .filter(NotificationConfigurationFilter.builder()
                                        .key(S3KeyFilter.builder()
                                                .filterRules(FilterRule.builder()
                                                        .name(FilterRuleName.PREFIX)
                                                        .value("value")
                                                        .build())
                                                .build())
                                        .build())
                                .build())
                        .queueConfigurations(QueueConfiguration.builder()
                                .id("id")
                                .queueArn("queueArn")
                                .events(Event.S3_REDUCED_REDUNDANCY_LOST_OBJECT)
                                .filter(NotificationConfigurationFilter.builder()
                                        .key(S3KeyFilter.builder()
                                                .filterRules(FilterRule.builder()
                                                        .name(FilterRuleName.PREFIX)
                                                        .value("value")
                                                        .build())
                                                .build())
                                        .build())
                                .build())
                        .lambdaFunctionConfigurations(LambdaFunctionConfiguration.builder()
                                .id("id")
                                .lambdaFunctionArn("lambdaFunctionArn")
                                .events(Event.S3_REDUCED_REDUNDANCY_LOST_OBJECT)
                                .filter(NotificationConfigurationFilter.builder()
                                        .key(S3KeyFilter.builder()
                                                .filterRules(FilterRule.builder()
                                                        .name(FilterRuleName.PREFIX)
                                                        .value("value")
                                                        .build())
                                                .build())
                                        .build())
                                .build())
                        .build())
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryPutBucketNotificationConfiguration1());
    }

    @Test
    void testTryPutBucketNotificationConfiguration1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.putBucketNotificationConfiguration(PutBucketNotificationConfigurationRequest.builder()
                .bucket("bucket")
                .notificationConfiguration(NotificationConfiguration.builder()
                        .topicConfigurations(TopicConfiguration.builder()
                                .id("id")
                                .topicArn("topicArn")
                                .events(Event.S3_REDUCED_REDUNDANCY_LOST_OBJECT)
                                .filter(NotificationConfigurationFilter.builder()
                                        .key(S3KeyFilter.builder()
                                                .filterRules(FilterRule.builder()
                                                        .name(FilterRuleName.PREFIX)
                                                        .value("value")
                                                        .build())
                                                .build())
                                        .build())
                                .build())
                        .queueConfigurations(QueueConfiguration.builder()
                                .id("id")
                                .queueArn("queueArn")
                                .events(Event.S3_REDUCED_REDUNDANCY_LOST_OBJECT)
                                .filter(NotificationConfigurationFilter.builder()
                                        .key(S3KeyFilter.builder()
                                                .filterRules(FilterRule.builder()
                                                        .name(FilterRuleName.PREFIX)
                                                        .value("value")
                                                        .build())
                                                .build())
                                        .build())
                                .build())
                        .lambdaFunctionConfigurations(LambdaFunctionConfiguration.builder()
                                .id("id")
                                .lambdaFunctionArn("lambdaFunctionArn")
                                .events(Event.S3_REDUCED_REDUNDANCY_LOST_OBJECT)
                                .filter(NotificationConfigurationFilter.builder()
                                        .key(S3KeyFilter.builder()
                                                .filterRules(FilterRule.builder()
                                                        .name(FilterRuleName.PREFIX)
                                                        .value("value")
                                                        .build())
                                                .build())
                                        .build())
                                .build())
                        .build())
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryPutBucketNotificationConfiguration1());
    }

    @Test
    void testTryPutBucketNotificationConfiguration2() {
        // Setup
        // Configure S3Client.putBucketNotificationConfiguration(...).
        final PutBucketNotificationConfigurationResponse putBucketNotificationConfigurationResponse = PutBucketNotificationConfigurationResponse.builder().build();
        when(mockS3Client.putBucketNotificationConfiguration(any(Consumer.class)))
                .thenReturn(putBucketNotificationConfigurationResponse);

        // Run the test
        myClassUnderTest.tryPutBucketNotificationConfiguration2();

        // Verify the results
    }

    @Test
    void testTryPutBucketNotificationConfiguration2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.putBucketNotificationConfiguration(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryPutBucketNotificationConfiguration2());
    }

    @Test
    void testTryPutBucketNotificationConfiguration2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.putBucketNotificationConfiguration(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryPutBucketNotificationConfiguration2());
    }

    @Test
    void testTryPutBucketNotificationConfiguration2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.putBucketNotificationConfiguration(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryPutBucketNotificationConfiguration2());
    }

    @Test
    void testTryPutBucketPolicy1() {
        // Setup
        // Configure S3Client.putBucketPolicy(...).
        final PutBucketPolicyResponse putBucketPolicyResponse = PutBucketPolicyResponse.builder().build();
        when(mockS3Client.putBucketPolicy(PutBucketPolicyRequest.builder()
                .bucket("bucket")
                .contentMD5("contentMD5")
                .confirmRemoveSelfBucketAccess(false)
                .policy("policy")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(putBucketPolicyResponse);

        // Run the test
        myClassUnderTest.tryPutBucketPolicy1();

        // Verify the results
    }

    @Test
    void testTryPutBucketPolicy1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.putBucketPolicy(PutBucketPolicyRequest.builder()
                .bucket("bucket")
                .contentMD5("contentMD5")
                .confirmRemoveSelfBucketAccess(false)
                .policy("policy")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryPutBucketPolicy1());
    }

    @Test
    void testTryPutBucketPolicy1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.putBucketPolicy(PutBucketPolicyRequest.builder()
                .bucket("bucket")
                .contentMD5("contentMD5")
                .confirmRemoveSelfBucketAccess(false)
                .policy("policy")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryPutBucketPolicy1());
    }

    @Test
    void testTryPutBucketPolicy1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.putBucketPolicy(PutBucketPolicyRequest.builder()
                .bucket("bucket")
                .contentMD5("contentMD5")
                .confirmRemoveSelfBucketAccess(false)
                .policy("policy")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryPutBucketPolicy1());
    }

    @Test
    void testTryPutBucketPolicy2() {
        // Setup
        // Configure S3Client.putBucketPolicy(...).
        final PutBucketPolicyResponse putBucketPolicyResponse = PutBucketPolicyResponse.builder().build();
        when(mockS3Client.putBucketPolicy(any(Consumer.class))).thenReturn(putBucketPolicyResponse);

        // Run the test
        myClassUnderTest.tryPutBucketPolicy2();

        // Verify the results
    }

    @Test
    void testTryPutBucketPolicy2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.putBucketPolicy(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryPutBucketPolicy2());
    }

    @Test
    void testTryPutBucketPolicy2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.putBucketPolicy(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryPutBucketPolicy2());
    }

    @Test
    void testTryPutBucketPolicy2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.putBucketPolicy(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryPutBucketPolicy2());
    }

    @Test
    void testTryPutBucketReplication1() {
        // Setup
        // Configure S3Client.putBucketReplication(...).
        final PutBucketReplicationResponse putBucketReplicationResponse = PutBucketReplicationResponse.builder().build();
        when(mockS3Client.putBucketReplication(PutBucketReplicationRequest.builder()
                .bucket("bucket")
                .contentMD5("contentMD5")
                .replicationConfiguration(ReplicationConfiguration.builder()
                        .role("role")
                        .rules(ReplicationRule.builder()
                                .id("id")
                                .priority(0)
                                .prefix("prefix")
                                .filter(ReplicationRuleFilter.builder()
                                        .prefix("prefix")
                                        .tag(Tag.builder()
                                                .key("key")
                                                .value("value")
                                                .build())
                                        .and(ReplicationRuleAndOperator.builder()
                                                .prefix("prefix")
                                                .tags(Tag.builder()
                                                        .key("key")
                                                        .value("value")
                                                        .build())
                                                .build())
                                        .build())
                                .status(ReplicationRuleStatus.ENABLED)
                                .sourceSelectionCriteria(SourceSelectionCriteria.builder()
                                        .sseKmsEncryptedObjects(SseKmsEncryptedObjects.builder()
                                                .status(SseKmsEncryptedObjectsStatus.ENABLED)
                                                .build())
                                        .build())
                                .existingObjectReplication(ExistingObjectReplication.builder()
                                        .status(ExistingObjectReplicationStatus.ENABLED)
                                        .build())
                                .destination(Destination.builder()
                                        .bucket("bucket")
                                        .account("account")
                                        .storageClass(StorageClass.STANDARD)
                                        .accessControlTranslation(AccessControlTranslation.builder()
                                                .owner(OwnerOverride.DESTINATION)
                                                .build())
                                        .encryptionConfiguration(EncryptionConfiguration.builder().build())
                                        .build())
                                .build())
                        .build())
                .build())).thenReturn(putBucketReplicationResponse);

        // Run the test
        myClassUnderTest.tryPutBucketReplication1();

        // Verify the results
    }

    @Test
    void testTryPutBucketReplication1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.putBucketReplication(PutBucketReplicationRequest.builder()
                .bucket("bucket")
                .contentMD5("contentMD5")
                .replicationConfiguration(ReplicationConfiguration.builder()
                        .role("role")
                        .rules(ReplicationRule.builder()
                                .id("id")
                                .priority(0)
                                .prefix("prefix")
                                .filter(ReplicationRuleFilter.builder()
                                        .prefix("prefix")
                                        .tag(Tag.builder()
                                                .key("key")
                                                .value("value")
                                                .build())
                                        .and(ReplicationRuleAndOperator.builder()
                                                .prefix("prefix")
                                                .tags(Tag.builder()
                                                        .key("key")
                                                        .value("value")
                                                        .build())
                                                .build())
                                        .build())
                                .status(ReplicationRuleStatus.ENABLED)
                                .sourceSelectionCriteria(SourceSelectionCriteria.builder()
                                        .sseKmsEncryptedObjects(SseKmsEncryptedObjects.builder()
                                                .status(SseKmsEncryptedObjectsStatus.ENABLED)
                                                .build())
                                        .build())
                                .existingObjectReplication(ExistingObjectReplication.builder()
                                        .status(ExistingObjectReplicationStatus.ENABLED)
                                        .build())
                                .destination(Destination.builder()
                                        .bucket("bucket")
                                        .account("account")
                                        .storageClass(StorageClass.STANDARD)
                                        .accessControlTranslation(AccessControlTranslation.builder()
                                                .owner(OwnerOverride.DESTINATION)
                                                .build())
                                        .encryptionConfiguration(EncryptionConfiguration.builder().build())
                                        .build())
                                .build())
                        .build())
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryPutBucketReplication1());
    }

    @Test
    void testTryPutBucketReplication1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.putBucketReplication(PutBucketReplicationRequest.builder()
                .bucket("bucket")
                .contentMD5("contentMD5")
                .replicationConfiguration(ReplicationConfiguration.builder()
                        .role("role")
                        .rules(ReplicationRule.builder()
                                .id("id")
                                .priority(0)
                                .prefix("prefix")
                                .filter(ReplicationRuleFilter.builder()
                                        .prefix("prefix")
                                        .tag(Tag.builder()
                                                .key("key")
                                                .value("value")
                                                .build())
                                        .and(ReplicationRuleAndOperator.builder()
                                                .prefix("prefix")
                                                .tags(Tag.builder()
                                                        .key("key")
                                                        .value("value")
                                                        .build())
                                                .build())
                                        .build())
                                .status(ReplicationRuleStatus.ENABLED)
                                .sourceSelectionCriteria(SourceSelectionCriteria.builder()
                                        .sseKmsEncryptedObjects(SseKmsEncryptedObjects.builder()
                                                .status(SseKmsEncryptedObjectsStatus.ENABLED)
                                                .build())
                                        .build())
                                .existingObjectReplication(ExistingObjectReplication.builder()
                                        .status(ExistingObjectReplicationStatus.ENABLED)
                                        .build())
                                .destination(Destination.builder()
                                        .bucket("bucket")
                                        .account("account")
                                        .storageClass(StorageClass.STANDARD)
                                        .accessControlTranslation(AccessControlTranslation.builder()
                                                .owner(OwnerOverride.DESTINATION)
                                                .build())
                                        .encryptionConfiguration(EncryptionConfiguration.builder().build())
                                        .build())
                                .build())
                        .build())
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryPutBucketReplication1());
    }

    @Test
    void testTryPutBucketReplication1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.putBucketReplication(PutBucketReplicationRequest.builder()
                .bucket("bucket")
                .contentMD5("contentMD5")
                .replicationConfiguration(ReplicationConfiguration.builder()
                        .role("role")
                        .rules(ReplicationRule.builder()
                                .id("id")
                                .priority(0)
                                .prefix("prefix")
                                .filter(ReplicationRuleFilter.builder()
                                        .prefix("prefix")
                                        .tag(Tag.builder()
                                                .key("key")
                                                .value("value")
                                                .build())
                                        .and(ReplicationRuleAndOperator.builder()
                                                .prefix("prefix")
                                                .tags(Tag.builder()
                                                        .key("key")
                                                        .value("value")
                                                        .build())
                                                .build())
                                        .build())
                                .status(ReplicationRuleStatus.ENABLED)
                                .sourceSelectionCriteria(SourceSelectionCriteria.builder()
                                        .sseKmsEncryptedObjects(SseKmsEncryptedObjects.builder()
                                                .status(SseKmsEncryptedObjectsStatus.ENABLED)
                                                .build())
                                        .build())
                                .existingObjectReplication(ExistingObjectReplication.builder()
                                        .status(ExistingObjectReplicationStatus.ENABLED)
                                        .build())
                                .destination(Destination.builder()
                                        .bucket("bucket")
                                        .account("account")
                                        .storageClass(StorageClass.STANDARD)
                                        .accessControlTranslation(AccessControlTranslation.builder()
                                                .owner(OwnerOverride.DESTINATION)
                                                .build())
                                        .encryptionConfiguration(EncryptionConfiguration.builder().build())
                                        .build())
                                .build())
                        .build())
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryPutBucketReplication1());
    }

    @Test
    void testTryPutBucketReplication2() {
        // Setup
        // Configure S3Client.putBucketReplication(...).
        final PutBucketReplicationResponse putBucketReplicationResponse = PutBucketReplicationResponse.builder().build();
        when(mockS3Client.putBucketReplication(any(Consumer.class))).thenReturn(putBucketReplicationResponse);

        // Run the test
        myClassUnderTest.tryPutBucketReplication2();

        // Verify the results
    }

    @Test
    void testTryPutBucketReplication2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.putBucketReplication(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryPutBucketReplication2());
    }

    @Test
    void testTryPutBucketReplication2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.putBucketReplication(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryPutBucketReplication2());
    }

    @Test
    void testTryPutBucketReplication2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.putBucketReplication(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryPutBucketReplication2());
    }

    @Test
    void testTryPutBucketRequestPayment1() {
        // Setup
        // Configure S3Client.putBucketRequestPayment(...).
        final PutBucketRequestPaymentResponse putBucketRequestPaymentResponse = PutBucketRequestPaymentResponse.builder().build();
        when(mockS3Client.putBucketRequestPayment(PutBucketRequestPaymentRequest.builder()
                .bucket("bucket")
                .contentMD5("contentMD5")
                .requestPaymentConfiguration(RequestPaymentConfiguration.builder()
                        .payer(Payer.REQUESTER)
                        .build())
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(putBucketRequestPaymentResponse);

        // Run the test
        myClassUnderTest.tryPutBucketRequestPayment1();

        // Verify the results
    }

    @Test
    void testTryPutBucketRequestPayment1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.putBucketRequestPayment(PutBucketRequestPaymentRequest.builder()
                .bucket("bucket")
                .contentMD5("contentMD5")
                .requestPaymentConfiguration(RequestPaymentConfiguration.builder()
                        .payer(Payer.REQUESTER)
                        .build())
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryPutBucketRequestPayment1());
    }

    @Test
    void testTryPutBucketRequestPayment1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.putBucketRequestPayment(PutBucketRequestPaymentRequest.builder()
                .bucket("bucket")
                .contentMD5("contentMD5")
                .requestPaymentConfiguration(RequestPaymentConfiguration.builder()
                        .payer(Payer.REQUESTER)
                        .build())
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryPutBucketRequestPayment1());
    }

    @Test
    void testTryPutBucketRequestPayment1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.putBucketRequestPayment(PutBucketRequestPaymentRequest.builder()
                .bucket("bucket")
                .contentMD5("contentMD5")
                .requestPaymentConfiguration(RequestPaymentConfiguration.builder()
                        .payer(Payer.REQUESTER)
                        .build())
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryPutBucketRequestPayment1());
    }

    @Test
    void testTryPutBucketRequestPayment2() {
        // Setup
        // Configure S3Client.putBucketRequestPayment(...).
        final PutBucketRequestPaymentResponse putBucketRequestPaymentResponse = PutBucketRequestPaymentResponse.builder().build();
        when(mockS3Client.putBucketRequestPayment(any(Consumer.class))).thenReturn(putBucketRequestPaymentResponse);

        // Run the test
        myClassUnderTest.tryPutBucketRequestPayment2();

        // Verify the results
    }

    @Test
    void testTryPutBucketRequestPayment2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.putBucketRequestPayment(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryPutBucketRequestPayment2());
    }

    @Test
    void testTryPutBucketRequestPayment2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.putBucketRequestPayment(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryPutBucketRequestPayment2());
    }

    @Test
    void testTryPutBucketRequestPayment2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.putBucketRequestPayment(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryPutBucketRequestPayment2());
    }

    @Test
    void testTryPutBucketTagging1() {
        // Setup
        // Configure S3Client.putBucketTagging(...).
        final PutBucketTaggingResponse putBucketTaggingResponse = PutBucketTaggingResponse.builder().build();
        when(mockS3Client.putBucketTagging(PutBucketTaggingRequest.builder()
                .bucket("bucket")
                .contentMD5("contentMD5")
                .tagging(Tagging.builder()
                        .tagSet(Tag.builder()
                                .key("key")
                                .value("value")
                                .build())
                        .build())
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(putBucketTaggingResponse);

        // Run the test
        myClassUnderTest.tryPutBucketTagging1();

        // Verify the results
    }

    @Test
    void testTryPutBucketTagging1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.putBucketTagging(PutBucketTaggingRequest.builder()
                .bucket("bucket")
                .contentMD5("contentMD5")
                .tagging(Tagging.builder()
                        .tagSet(Tag.builder()
                                .key("key")
                                .value("value")
                                .build())
                        .build())
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryPutBucketTagging1());
    }

    @Test
    void testTryPutBucketTagging1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.putBucketTagging(PutBucketTaggingRequest.builder()
                .bucket("bucket")
                .contentMD5("contentMD5")
                .tagging(Tagging.builder()
                        .tagSet(Tag.builder()
                                .key("key")
                                .value("value")
                                .build())
                        .build())
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryPutBucketTagging1());
    }

    @Test
    void testTryPutBucketTagging1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.putBucketTagging(PutBucketTaggingRequest.builder()
                .bucket("bucket")
                .contentMD5("contentMD5")
                .tagging(Tagging.builder()
                        .tagSet(Tag.builder()
                                .key("key")
                                .value("value")
                                .build())
                        .build())
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryPutBucketTagging1());
    }

    @Test
    void testTryPutBucketTagging2() {
        // Setup
        // Configure S3Client.putBucketTagging(...).
        final PutBucketTaggingResponse putBucketTaggingResponse = PutBucketTaggingResponse.builder().build();
        when(mockS3Client.putBucketTagging(any(Consumer.class))).thenReturn(putBucketTaggingResponse);

        // Run the test
        myClassUnderTest.tryPutBucketTagging2();

        // Verify the results
    }

    @Test
    void testTryPutBucketTagging2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.putBucketTagging(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryPutBucketTagging2());
    }

    @Test
    void testTryPutBucketTagging2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.putBucketTagging(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryPutBucketTagging2());
    }

    @Test
    void testTryPutBucketTagging2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.putBucketTagging(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryPutBucketTagging2());
    }

    @Test
    void testTryPutBucketVersioning1() {
        // Setup
        // Configure S3Client.putBucketVersioning(...).
        final PutBucketVersioningResponse putBucketVersioningResponse = PutBucketVersioningResponse.builder().build();
        when(mockS3Client.putBucketVersioning(PutBucketVersioningRequest.builder()
                .bucket("bucket")
                .contentMD5("contentMD5")
                .mfa("mfa")
                .versioningConfiguration(VersioningConfiguration.builder()
                        .mfaDelete(MFADelete.ENABLED)
                        .status(BucketVersioningStatus.ENABLED)
                        .build())
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(putBucketVersioningResponse);

        // Run the test
        myClassUnderTest.tryPutBucketVersioning1();

        // Verify the results
    }

    @Test
    void testTryPutBucketVersioning1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.putBucketVersioning(PutBucketVersioningRequest.builder()
                .bucket("bucket")
                .contentMD5("contentMD5")
                .mfa("mfa")
                .versioningConfiguration(VersioningConfiguration.builder()
                        .mfaDelete(MFADelete.ENABLED)
                        .status(BucketVersioningStatus.ENABLED)
                        .build())
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryPutBucketVersioning1());
    }

    @Test
    void testTryPutBucketVersioning1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.putBucketVersioning(PutBucketVersioningRequest.builder()
                .bucket("bucket")
                .contentMD5("contentMD5")
                .mfa("mfa")
                .versioningConfiguration(VersioningConfiguration.builder()
                        .mfaDelete(MFADelete.ENABLED)
                        .status(BucketVersioningStatus.ENABLED)
                        .build())
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryPutBucketVersioning1());
    }

    @Test
    void testTryPutBucketVersioning1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.putBucketVersioning(PutBucketVersioningRequest.builder()
                .bucket("bucket")
                .contentMD5("contentMD5")
                .mfa("mfa")
                .versioningConfiguration(VersioningConfiguration.builder()
                        .mfaDelete(MFADelete.ENABLED)
                        .status(BucketVersioningStatus.ENABLED)
                        .build())
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryPutBucketVersioning1());
    }

    @Test
    void testTryPutBucketVersioning2() {
        // Setup
        // Configure S3Client.putBucketVersioning(...).
        final PutBucketVersioningResponse putBucketVersioningResponse = PutBucketVersioningResponse.builder().build();
        when(mockS3Client.putBucketVersioning(any(Consumer.class))).thenReturn(putBucketVersioningResponse);

        // Run the test
        myClassUnderTest.tryPutBucketVersioning2();

        // Verify the results
    }

    @Test
    void testTryPutBucketVersioning2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.putBucketVersioning(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryPutBucketVersioning2());
    }

    @Test
    void testTryPutBucketVersioning2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.putBucketVersioning(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryPutBucketVersioning2());
    }

    @Test
    void testTryPutBucketVersioning2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.putBucketVersioning(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryPutBucketVersioning2());
    }

    @Test
    void testTryPutBucketWebsite1() {
        // Setup
        // Configure S3Client.putBucketWebsite(...).
        final PutBucketWebsiteResponse putBucketWebsiteResponse = PutBucketWebsiteResponse.builder().build();
        when(mockS3Client.putBucketWebsite(PutBucketWebsiteRequest.builder()
                .bucket("bucket")
                .contentMD5("contentMD5")
                .websiteConfiguration(WebsiteConfiguration.builder()
                        .errorDocument(ErrorDocument.builder()
                                .key("key")
                                .build())
                        .indexDocument(IndexDocument.builder()
                                .suffix("suffix")
                                .build())
                        .redirectAllRequestsTo(RedirectAllRequestsTo.builder()
                                .hostName("hostName")
                                .protocol(Protocol.HTTP)
                                .build())
                        .routingRules(RoutingRule.builder()
                                .condition(Condition.builder()
                                        .httpErrorCodeReturnedEquals("httpErrorCodeReturnedEquals")
                                        .keyPrefixEquals("keyPrefixEquals")
                                        .build())
                                .redirect(Redirect.builder()
                                        .hostName("hostName")
                                        .httpRedirectCode("httpRedirectCode")
                                        .protocol(Protocol.HTTP)
                                        .replaceKeyPrefixWith("replaceKeyPrefixWith")
                                        .replaceKeyWith("replaceKeyWith")
                                        .build())
                                .build())
                        .build())
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(putBucketWebsiteResponse);

        // Run the test
        myClassUnderTest.tryPutBucketWebsite1();

        // Verify the results
    }

    @Test
    void testTryPutBucketWebsite1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.putBucketWebsite(PutBucketWebsiteRequest.builder()
                .bucket("bucket")
                .contentMD5("contentMD5")
                .websiteConfiguration(WebsiteConfiguration.builder()
                        .errorDocument(ErrorDocument.builder()
                                .key("key")
                                .build())
                        .indexDocument(IndexDocument.builder()
                                .suffix("suffix")
                                .build())
                        .redirectAllRequestsTo(RedirectAllRequestsTo.builder()
                                .hostName("hostName")
                                .protocol(Protocol.HTTP)
                                .build())
                        .routingRules(RoutingRule.builder()
                                .condition(Condition.builder()
                                        .httpErrorCodeReturnedEquals("httpErrorCodeReturnedEquals")
                                        .keyPrefixEquals("keyPrefixEquals")
                                        .build())
                                .redirect(Redirect.builder()
                                        .hostName("hostName")
                                        .httpRedirectCode("httpRedirectCode")
                                        .protocol(Protocol.HTTP)
                                        .replaceKeyPrefixWith("replaceKeyPrefixWith")
                                        .replaceKeyWith("replaceKeyWith")
                                        .build())
                                .build())
                        .build())
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryPutBucketWebsite1());
    }

    @Test
    void testTryPutBucketWebsite1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.putBucketWebsite(PutBucketWebsiteRequest.builder()
                .bucket("bucket")
                .contentMD5("contentMD5")
                .websiteConfiguration(WebsiteConfiguration.builder()
                        .errorDocument(ErrorDocument.builder()
                                .key("key")
                                .build())
                        .indexDocument(IndexDocument.builder()
                                .suffix("suffix")
                                .build())
                        .redirectAllRequestsTo(RedirectAllRequestsTo.builder()
                                .hostName("hostName")
                                .protocol(Protocol.HTTP)
                                .build())
                        .routingRules(RoutingRule.builder()
                                .condition(Condition.builder()
                                        .httpErrorCodeReturnedEquals("httpErrorCodeReturnedEquals")
                                        .keyPrefixEquals("keyPrefixEquals")
                                        .build())
                                .redirect(Redirect.builder()
                                        .hostName("hostName")
                                        .httpRedirectCode("httpRedirectCode")
                                        .protocol(Protocol.HTTP)
                                        .replaceKeyPrefixWith("replaceKeyPrefixWith")
                                        .replaceKeyWith("replaceKeyWith")
                                        .build())
                                .build())
                        .build())
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryPutBucketWebsite1());
    }

    @Test
    void testTryPutBucketWebsite1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.putBucketWebsite(PutBucketWebsiteRequest.builder()
                .bucket("bucket")
                .contentMD5("contentMD5")
                .websiteConfiguration(WebsiteConfiguration.builder()
                        .errorDocument(ErrorDocument.builder()
                                .key("key")
                                .build())
                        .indexDocument(IndexDocument.builder()
                                .suffix("suffix")
                                .build())
                        .redirectAllRequestsTo(RedirectAllRequestsTo.builder()
                                .hostName("hostName")
                                .protocol(Protocol.HTTP)
                                .build())
                        .routingRules(RoutingRule.builder()
                                .condition(Condition.builder()
                                        .httpErrorCodeReturnedEquals("httpErrorCodeReturnedEquals")
                                        .keyPrefixEquals("keyPrefixEquals")
                                        .build())
                                .redirect(Redirect.builder()
                                        .hostName("hostName")
                                        .httpRedirectCode("httpRedirectCode")
                                        .protocol(Protocol.HTTP)
                                        .replaceKeyPrefixWith("replaceKeyPrefixWith")
                                        .replaceKeyWith("replaceKeyWith")
                                        .build())
                                .build())
                        .build())
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryPutBucketWebsite1());
    }

    @Test
    void testTryPutBucketWebsite2() {
        // Setup
        // Configure S3Client.putBucketWebsite(...).
        final PutBucketWebsiteResponse putBucketWebsiteResponse = PutBucketWebsiteResponse.builder().build();
        when(mockS3Client.putBucketWebsite(any(Consumer.class))).thenReturn(putBucketWebsiteResponse);

        // Run the test
        myClassUnderTest.tryPutBucketWebsite2();

        // Verify the results
    }

    @Test
    void testTryPutBucketWebsite2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.putBucketWebsite(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryPutBucketWebsite2());
    }

    @Test
    void testTryPutBucketWebsite2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.putBucketWebsite(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryPutBucketWebsite2());
    }

    @Test
    void testTryPutBucketWebsite2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.putBucketWebsite(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryPutBucketWebsite2());
    }

    @Test
    void testTryPutObject1() {
        // Setup
        when(mockS3Client.putObject(eq(PutObjectRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build()), any(RequestBody.class))).thenReturn(PutObjectResponse.builder().build());

        // Run the test
        myClassUnderTest.tryPutObject1();

        // Verify the results
    }

    @Test
    void testTryPutObject1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.putObject(eq(PutObjectRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build()), any(RequestBody.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryPutObject1());
    }

    @Test
    void testTryPutObject1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.putObject(eq(PutObjectRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build()), any(RequestBody.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryPutObject1());
    }

    @Test
    void testTryPutObject1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.putObject(eq(PutObjectRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build()), any(RequestBody.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryPutObject1());
    }

    @Test
    void testTryPutObject2() {
        // Setup
        when(mockS3Client.putObject(any(Consumer.class), any(RequestBody.class)))
                .thenReturn(PutObjectResponse.builder().build());

        // Run the test
        myClassUnderTest.tryPutObject2();

        // Verify the results
    }

    @Test
    void testTryPutObject2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.putObject(any(Consumer.class), any(RequestBody.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryPutObject2());
    }

    @Test
    void testTryPutObject2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.putObject(any(Consumer.class), any(RequestBody.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryPutObject2());
    }

    @Test
    void testTryPutObject2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.putObject(any(Consumer.class), any(RequestBody.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryPutObject2());
    }

    @Test
    void testTryPutObject3() {
        // Setup
        when(mockS3Client.putObject(PutObjectRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build(), Paths.get("filename.txt"))).thenReturn(PutObjectResponse.builder().build());

        // Run the test
        myClassUnderTest.tryPutObject3();

        // Verify the results
    }

    @Test
    void testTryPutObject3_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.putObject(PutObjectRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build(), Paths.get("filename.txt"))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryPutObject3());
    }

    @Test
    void testTryPutObject3_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.putObject(PutObjectRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build(), Paths.get("filename.txt"))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryPutObject3());
    }

    @Test
    void testTryPutObject3_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.putObject(PutObjectRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build(), Paths.get("filename.txt"))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryPutObject3());
    }

    @Test
    void testTryPutObject4() {
        // Setup
        when(mockS3Client.putObject(any(Consumer.class), eq(Paths.get("filename.txt"))))
                .thenReturn(PutObjectResponse.builder().build());

        // Run the test
        myClassUnderTest.tryPutObject4();

        // Verify the results
    }

    @Test
    void testTryPutObject4_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.putObject(any(Consumer.class), eq(Paths.get("filename.txt"))))
                .thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryPutObject4());
    }

    @Test
    void testTryPutObject4_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.putObject(any(Consumer.class), eq(Paths.get("filename.txt"))))
                .thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryPutObject4());
    }

    @Test
    void testTryPutObject4_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.putObject(any(Consumer.class), eq(Paths.get("filename.txt")))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryPutObject4());
    }

    @Test
    void testTryPutObjectAcl1() {
        // Setup
        // Configure S3Client.putObjectAcl(...).
        final PutObjectAclResponse putObjectAclResponse = PutObjectAclResponse.builder()
                .requestCharged(RequestCharged.REQUESTER)
                .build();
        when(mockS3Client.putObjectAcl(PutObjectAclRequest.builder()
                .acl(ObjectCannedACL.PRIVATE)
                .accessControlPolicy(AccessControlPolicy.builder()
                        .grants(Grant.builder()
                                .grantee(Grantee.builder()
                                        .displayName("displayName")
                                        .emailAddress("emailAddress")
                                        .id("id")
                                        .type(Type.CANONICAL_USER)
                                        .uri("uri")
                                        .build())
                                .permission(Permission.FULL_CONTROL)
                                .build())
                        .owner(Owner.builder()
                                .displayName("displayName")
                                .id("id")
                                .build())
                        .build())
                .bucket("bucket")
                .contentMD5("contentMD5")
                .grantFullControl("grantFullControl")
                .grantRead("grantRead")
                .grantReadACP("grantReadACP")
                .grantWrite("grantWrite")
                .grantWriteACP("grantWriteACP")
                .key("key")
                .requestPayer(RequestPayer.REQUESTER)
                .versionId("versionId")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(putObjectAclResponse);

        // Run the test
        myClassUnderTest.tryPutObjectAcl1();

        // Verify the results
    }

    @Test
    void testTryPutObjectAcl1_S3ClientThrowsNoSuchKeyException() {
        // Setup
        when(mockS3Client.putObjectAcl(PutObjectAclRequest.builder()
                .acl(ObjectCannedACL.PRIVATE)
                .accessControlPolicy(AccessControlPolicy.builder()
                        .grants(Grant.builder()
                                .grantee(Grantee.builder()
                                        .displayName("displayName")
                                        .emailAddress("emailAddress")
                                        .id("id")
                                        .type(Type.CANONICAL_USER)
                                        .uri("uri")
                                        .build())
                                .permission(Permission.FULL_CONTROL)
                                .build())
                        .owner(Owner.builder()
                                .displayName("displayName")
                                .id("id")
                                .build())
                        .build())
                .bucket("bucket")
                .contentMD5("contentMD5")
                .grantFullControl("grantFullControl")
                .grantRead("grantRead")
                .grantReadACP("grantReadACP")
                .grantWrite("grantWrite")
                .grantWriteACP("grantWriteACP")
                .key("key")
                .requestPayer(RequestPayer.REQUESTER)
                .versionId("versionId")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(NoSuchKeyException.class);

        // Run the test
        assertThrows(NoSuchKeyException.class, () -> myClassUnderTest.tryPutObjectAcl1());
    }

    @Test
    void testTryPutObjectAcl1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.putObjectAcl(PutObjectAclRequest.builder()
                .acl(ObjectCannedACL.PRIVATE)
                .accessControlPolicy(AccessControlPolicy.builder()
                        .grants(Grant.builder()
                                .grantee(Grantee.builder()
                                        .displayName("displayName")
                                        .emailAddress("emailAddress")
                                        .id("id")
                                        .type(Type.CANONICAL_USER)
                                        .uri("uri")
                                        .build())
                                .permission(Permission.FULL_CONTROL)
                                .build())
                        .owner(Owner.builder()
                                .displayName("displayName")
                                .id("id")
                                .build())
                        .build())
                .bucket("bucket")
                .contentMD5("contentMD5")
                .grantFullControl("grantFullControl")
                .grantRead("grantRead")
                .grantReadACP("grantReadACP")
                .grantWrite("grantWrite")
                .grantWriteACP("grantWriteACP")
                .key("key")
                .requestPayer(RequestPayer.REQUESTER)
                .versionId("versionId")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryPutObjectAcl1());
    }

    @Test
    void testTryPutObjectAcl1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.putObjectAcl(PutObjectAclRequest.builder()
                .acl(ObjectCannedACL.PRIVATE)
                .accessControlPolicy(AccessControlPolicy.builder()
                        .grants(Grant.builder()
                                .grantee(Grantee.builder()
                                        .displayName("displayName")
                                        .emailAddress("emailAddress")
                                        .id("id")
                                        .type(Type.CANONICAL_USER)
                                        .uri("uri")
                                        .build())
                                .permission(Permission.FULL_CONTROL)
                                .build())
                        .owner(Owner.builder()
                                .displayName("displayName")
                                .id("id")
                                .build())
                        .build())
                .bucket("bucket")
                .contentMD5("contentMD5")
                .grantFullControl("grantFullControl")
                .grantRead("grantRead")
                .grantReadACP("grantReadACP")
                .grantWrite("grantWrite")
                .grantWriteACP("grantWriteACP")
                .key("key")
                .requestPayer(RequestPayer.REQUESTER)
                .versionId("versionId")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryPutObjectAcl1());
    }

    @Test
    void testTryPutObjectAcl1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.putObjectAcl(PutObjectAclRequest.builder()
                .acl(ObjectCannedACL.PRIVATE)
                .accessControlPolicy(AccessControlPolicy.builder()
                        .grants(Grant.builder()
                                .grantee(Grantee.builder()
                                        .displayName("displayName")
                                        .emailAddress("emailAddress")
                                        .id("id")
                                        .type(Type.CANONICAL_USER)
                                        .uri("uri")
                                        .build())
                                .permission(Permission.FULL_CONTROL)
                                .build())
                        .owner(Owner.builder()
                                .displayName("displayName")
                                .id("id")
                                .build())
                        .build())
                .bucket("bucket")
                .contentMD5("contentMD5")
                .grantFullControl("grantFullControl")
                .grantRead("grantRead")
                .grantReadACP("grantReadACP")
                .grantWrite("grantWrite")
                .grantWriteACP("grantWriteACP")
                .key("key")
                .requestPayer(RequestPayer.REQUESTER)
                .versionId("versionId")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryPutObjectAcl1());
    }

    @Test
    void testTryPutObjectAcl2() {
        // Setup
        // Configure S3Client.putObjectAcl(...).
        final PutObjectAclResponse putObjectAclResponse = PutObjectAclResponse.builder()
                .requestCharged(RequestCharged.REQUESTER)
                .build();
        when(mockS3Client.putObjectAcl(any(Consumer.class))).thenReturn(putObjectAclResponse);

        // Run the test
        myClassUnderTest.tryPutObjectAcl2();

        // Verify the results
    }

    @Test
    void testTryPutObjectAcl2_S3ClientThrowsNoSuchKeyException() {
        // Setup
        when(mockS3Client.putObjectAcl(any(Consumer.class))).thenThrow(NoSuchKeyException.class);

        // Run the test
        assertThrows(NoSuchKeyException.class, () -> myClassUnderTest.tryPutObjectAcl2());
    }

    @Test
    void testTryPutObjectAcl2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.putObjectAcl(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryPutObjectAcl2());
    }

    @Test
    void testTryPutObjectAcl2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.putObjectAcl(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryPutObjectAcl2());
    }

    @Test
    void testTryPutObjectAcl2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.putObjectAcl(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryPutObjectAcl2());
    }

    @Test
    void testTryPutObjectLegalHold1() {
        // Setup
        // Configure S3Client.putObjectLegalHold(...).
        final PutObjectLegalHoldResponse putObjectLegalHoldResponse = PutObjectLegalHoldResponse.builder()
                .requestCharged(RequestCharged.REQUESTER)
                .build();
        when(mockS3Client.putObjectLegalHold(PutObjectLegalHoldRequest.builder()
                .bucket("bucket")
                .key("key")
                .legalHold(ObjectLockLegalHold.builder()
                        .status(ObjectLockLegalHoldStatus.ON)
                        .build())
                .requestPayer(RequestPayer.REQUESTER)
                .versionId("versionId")
                .contentMD5("contentMD5")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(putObjectLegalHoldResponse);

        // Run the test
        myClassUnderTest.tryPutObjectLegalHold1();

        // Verify the results
    }

    @Test
    void testTryPutObjectLegalHold1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.putObjectLegalHold(PutObjectLegalHoldRequest.builder()
                .bucket("bucket")
                .key("key")
                .legalHold(ObjectLockLegalHold.builder()
                        .status(ObjectLockLegalHoldStatus.ON)
                        .build())
                .requestPayer(RequestPayer.REQUESTER)
                .versionId("versionId")
                .contentMD5("contentMD5")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryPutObjectLegalHold1());
    }

    @Test
    void testTryPutObjectLegalHold1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.putObjectLegalHold(PutObjectLegalHoldRequest.builder()
                .bucket("bucket")
                .key("key")
                .legalHold(ObjectLockLegalHold.builder()
                        .status(ObjectLockLegalHoldStatus.ON)
                        .build())
                .requestPayer(RequestPayer.REQUESTER)
                .versionId("versionId")
                .contentMD5("contentMD5")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryPutObjectLegalHold1());
    }

    @Test
    void testTryPutObjectLegalHold1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.putObjectLegalHold(PutObjectLegalHoldRequest.builder()
                .bucket("bucket")
                .key("key")
                .legalHold(ObjectLockLegalHold.builder()
                        .status(ObjectLockLegalHoldStatus.ON)
                        .build())
                .requestPayer(RequestPayer.REQUESTER)
                .versionId("versionId")
                .contentMD5("contentMD5")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryPutObjectLegalHold1());
    }

    @Test
    void testTryPutObjectLegalHold2() {
        // Setup
        // Configure S3Client.putObjectLegalHold(...).
        final PutObjectLegalHoldResponse putObjectLegalHoldResponse = PutObjectLegalHoldResponse.builder()
                .requestCharged(RequestCharged.REQUESTER)
                .build();
        when(mockS3Client.putObjectLegalHold(any(Consumer.class))).thenReturn(putObjectLegalHoldResponse);

        // Run the test
        myClassUnderTest.tryPutObjectLegalHold2();

        // Verify the results
    }

    @Test
    void testTryPutObjectLegalHold2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.putObjectLegalHold(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryPutObjectLegalHold2());
    }

    @Test
    void testTryPutObjectLegalHold2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.putObjectLegalHold(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryPutObjectLegalHold2());
    }

    @Test
    void testTryPutObjectLegalHold2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.putObjectLegalHold(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryPutObjectLegalHold2());
    }

    @Test
    void testTryPutObjectLockConfiguration1() {
        // Setup
        // Configure S3Client.putObjectLockConfiguration(...).
        final PutObjectLockConfigurationResponse putObjectLockConfigurationResponse = PutObjectLockConfigurationResponse.builder()
                .requestCharged(RequestCharged.REQUESTER)
                .build();
        when(mockS3Client.putObjectLockConfiguration(PutObjectLockConfigurationRequest.builder()
                .bucket("bucket")
                .objectLockConfiguration(ObjectLockConfiguration.builder()
                        .objectLockEnabled(ObjectLockEnabled.ENABLED)
                        .rule(ObjectLockRule.builder()
                                .defaultRetention(DefaultRetention.builder()
                                        .mode(ObjectLockRetentionMode.GOVERNANCE)
                                        .days(0)
                                        .years(0)
                                        .build())
                                .build())
                        .build())
                .requestPayer(RequestPayer.REQUESTER)
                .token("token")
                .contentMD5("contentMD5")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(putObjectLockConfigurationResponse);

        // Run the test
        myClassUnderTest.tryPutObjectLockConfiguration1();

        // Verify the results
    }

    @Test
    void testTryPutObjectLockConfiguration1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.putObjectLockConfiguration(PutObjectLockConfigurationRequest.builder()
                .bucket("bucket")
                .objectLockConfiguration(ObjectLockConfiguration.builder()
                        .objectLockEnabled(ObjectLockEnabled.ENABLED)
                        .rule(ObjectLockRule.builder()
                                .defaultRetention(DefaultRetention.builder()
                                        .mode(ObjectLockRetentionMode.GOVERNANCE)
                                        .days(0)
                                        .years(0)
                                        .build())
                                .build())
                        .build())
                .requestPayer(RequestPayer.REQUESTER)
                .token("token")
                .contentMD5("contentMD5")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryPutObjectLockConfiguration1());
    }

    @Test
    void testTryPutObjectLockConfiguration1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.putObjectLockConfiguration(PutObjectLockConfigurationRequest.builder()
                .bucket("bucket")
                .objectLockConfiguration(ObjectLockConfiguration.builder()
                        .objectLockEnabled(ObjectLockEnabled.ENABLED)
                        .rule(ObjectLockRule.builder()
                                .defaultRetention(DefaultRetention.builder()
                                        .mode(ObjectLockRetentionMode.GOVERNANCE)
                                        .days(0)
                                        .years(0)
                                        .build())
                                .build())
                        .build())
                .requestPayer(RequestPayer.REQUESTER)
                .token("token")
                .contentMD5("contentMD5")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryPutObjectLockConfiguration1());
    }

    @Test
    void testTryPutObjectLockConfiguration1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.putObjectLockConfiguration(PutObjectLockConfigurationRequest.builder()
                .bucket("bucket")
                .objectLockConfiguration(ObjectLockConfiguration.builder()
                        .objectLockEnabled(ObjectLockEnabled.ENABLED)
                        .rule(ObjectLockRule.builder()
                                .defaultRetention(DefaultRetention.builder()
                                        .mode(ObjectLockRetentionMode.GOVERNANCE)
                                        .days(0)
                                        .years(0)
                                        .build())
                                .build())
                        .build())
                .requestPayer(RequestPayer.REQUESTER)
                .token("token")
                .contentMD5("contentMD5")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryPutObjectLockConfiguration1());
    }

    @Test
    void testTryPutObjectLockConfiguration2() {
        // Setup
        // Configure S3Client.putObjectLockConfiguration(...).
        final PutObjectLockConfigurationResponse putObjectLockConfigurationResponse = PutObjectLockConfigurationResponse.builder()
                .requestCharged(RequestCharged.REQUESTER)
                .build();
        when(mockS3Client.putObjectLockConfiguration(any(Consumer.class)))
                .thenReturn(putObjectLockConfigurationResponse);

        // Run the test
        myClassUnderTest.tryPutObjectLockConfiguration2();

        // Verify the results
    }

    @Test
    void testTryPutObjectLockConfiguration2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.putObjectLockConfiguration(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryPutObjectLockConfiguration2());
    }

    @Test
    void testTryPutObjectLockConfiguration2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.putObjectLockConfiguration(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryPutObjectLockConfiguration2());
    }

    @Test
    void testTryPutObjectLockConfiguration2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.putObjectLockConfiguration(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryPutObjectLockConfiguration2());
    }

    @Test
    void testTryPutObjectRetention1() {
        // Setup
        // Configure S3Client.putObjectRetention(...).
        final PutObjectRetentionResponse putObjectRetentionResponse = PutObjectRetentionResponse.builder()
                .requestCharged(RequestCharged.REQUESTER)
                .build();
        when(mockS3Client.putObjectRetention(PutObjectRetentionRequest.builder()
                .bucket("bucket")
                .key("key")
                .retention(ObjectLockRetention.builder()
                        .mode(ObjectLockRetentionMode.GOVERNANCE)
                        .retainUntilDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                        .build())
                .requestPayer(RequestPayer.REQUESTER)
                .versionId("versionId")
                .bypassGovernanceRetention(false)
                .contentMD5("contentMD5")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(putObjectRetentionResponse);

        // Run the test
        myClassUnderTest.tryPutObjectRetention1();

        // Verify the results
    }

    @Test
    void testTryPutObjectRetention1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.putObjectRetention(PutObjectRetentionRequest.builder()
                .bucket("bucket")
                .key("key")
                .retention(ObjectLockRetention.builder()
                        .mode(ObjectLockRetentionMode.GOVERNANCE)
                        .retainUntilDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                        .build())
                .requestPayer(RequestPayer.REQUESTER)
                .versionId("versionId")
                .bypassGovernanceRetention(false)
                .contentMD5("contentMD5")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryPutObjectRetention1());
    }

    @Test
    void testTryPutObjectRetention1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.putObjectRetention(PutObjectRetentionRequest.builder()
                .bucket("bucket")
                .key("key")
                .retention(ObjectLockRetention.builder()
                        .mode(ObjectLockRetentionMode.GOVERNANCE)
                        .retainUntilDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                        .build())
                .requestPayer(RequestPayer.REQUESTER)
                .versionId("versionId")
                .bypassGovernanceRetention(false)
                .contentMD5("contentMD5")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryPutObjectRetention1());
    }

    @Test
    void testTryPutObjectRetention1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.putObjectRetention(PutObjectRetentionRequest.builder()
                .bucket("bucket")
                .key("key")
                .retention(ObjectLockRetention.builder()
                        .mode(ObjectLockRetentionMode.GOVERNANCE)
                        .retainUntilDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                        .build())
                .requestPayer(RequestPayer.REQUESTER)
                .versionId("versionId")
                .bypassGovernanceRetention(false)
                .contentMD5("contentMD5")
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryPutObjectRetention1());
    }

    @Test
    void testTryPutObjectRetention2() {
        // Setup
        // Configure S3Client.putObjectRetention(...).
        final PutObjectRetentionResponse putObjectRetentionResponse = PutObjectRetentionResponse.builder()
                .requestCharged(RequestCharged.REQUESTER)
                .build();
        when(mockS3Client.putObjectRetention(any(Consumer.class))).thenReturn(putObjectRetentionResponse);

        // Run the test
        myClassUnderTest.tryPutObjectRetention2();

        // Verify the results
    }

    @Test
    void testTryPutObjectRetention2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.putObjectRetention(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryPutObjectRetention2());
    }

    @Test
    void testTryPutObjectRetention2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.putObjectRetention(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryPutObjectRetention2());
    }

    @Test
    void testTryPutObjectRetention2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.putObjectRetention(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryPutObjectRetention2());
    }

    @Test
    void testTryPutObjectTagging1() {
        // Setup
        // Configure S3Client.putObjectTagging(...).
        final PutObjectTaggingResponse putObjectTaggingResponse = PutObjectTaggingResponse.builder()
                .versionId("versionId")
                .build();
        when(mockS3Client.putObjectTagging(PutObjectTaggingRequest.builder()
                .bucket("bucket")
                .key("key")
                .versionId("versionId")
                .contentMD5("contentMD5")
                .tagging(Tagging.builder()
                        .tagSet(Tag.builder()
                                .key("key")
                                .value("value")
                                .build())
                        .build())
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(putObjectTaggingResponse);

        // Run the test
        myClassUnderTest.tryPutObjectTagging1();

        // Verify the results
    }

    @Test
    void testTryPutObjectTagging1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.putObjectTagging(PutObjectTaggingRequest.builder()
                .bucket("bucket")
                .key("key")
                .versionId("versionId")
                .contentMD5("contentMD5")
                .tagging(Tagging.builder()
                        .tagSet(Tag.builder()
                                .key("key")
                                .value("value")
                                .build())
                        .build())
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryPutObjectTagging1());
    }

    @Test
    void testTryPutObjectTagging1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.putObjectTagging(PutObjectTaggingRequest.builder()
                .bucket("bucket")
                .key("key")
                .versionId("versionId")
                .contentMD5("contentMD5")
                .tagging(Tagging.builder()
                        .tagSet(Tag.builder()
                                .key("key")
                                .value("value")
                                .build())
                        .build())
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryPutObjectTagging1());
    }

    @Test
    void testTryPutObjectTagging1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.putObjectTagging(PutObjectTaggingRequest.builder()
                .bucket("bucket")
                .key("key")
                .versionId("versionId")
                .contentMD5("contentMD5")
                .tagging(Tagging.builder()
                        .tagSet(Tag.builder()
                                .key("key")
                                .value("value")
                                .build())
                        .build())
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryPutObjectTagging1());
    }

    @Test
    void testTryPutObjectTagging2() {
        // Setup
        // Configure S3Client.putObjectTagging(...).
        final PutObjectTaggingResponse putObjectTaggingResponse = PutObjectTaggingResponse.builder()
                .versionId("versionId")
                .build();
        when(mockS3Client.putObjectTagging(any(Consumer.class))).thenReturn(putObjectTaggingResponse);

        // Run the test
        myClassUnderTest.tryPutObjectTagging2();

        // Verify the results
    }

    @Test
    void testTryPutObjectTagging2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.putObjectTagging(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryPutObjectTagging2());
    }

    @Test
    void testTryPutObjectTagging2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.putObjectTagging(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryPutObjectTagging2());
    }

    @Test
    void testTryPutObjectTagging2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.putObjectTagging(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryPutObjectTagging2());
    }

    @Test
    void testTryPutPublicAccessBlock1() {
        // Setup
        // Configure S3Client.putPublicAccessBlock(...).
        final PutPublicAccessBlockResponse putPublicAccessBlockResponse = PutPublicAccessBlockResponse.builder().build();
        when(mockS3Client.putPublicAccessBlock(PutPublicAccessBlockRequest.builder()
                .bucket("bucket")
                .contentMD5("contentMD5")
                .publicAccessBlockConfiguration(PublicAccessBlockConfiguration.builder()
                        .blockPublicAcls(false)
                        .ignorePublicAcls(false)
                        .blockPublicPolicy(false)
                        .restrictPublicBuckets(false)
                        .build())
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenReturn(putPublicAccessBlockResponse);

        // Run the test
        myClassUnderTest.tryPutPublicAccessBlock1();

        // Verify the results
    }

    @Test
    void testTryPutPublicAccessBlock1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.putPublicAccessBlock(PutPublicAccessBlockRequest.builder()
                .bucket("bucket")
                .contentMD5("contentMD5")
                .publicAccessBlockConfiguration(PublicAccessBlockConfiguration.builder()
                        .blockPublicAcls(false)
                        .ignorePublicAcls(false)
                        .blockPublicPolicy(false)
                        .restrictPublicBuckets(false)
                        .build())
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryPutPublicAccessBlock1());
    }

    @Test
    void testTryPutPublicAccessBlock1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.putPublicAccessBlock(PutPublicAccessBlockRequest.builder()
                .bucket("bucket")
                .contentMD5("contentMD5")
                .publicAccessBlockConfiguration(PublicAccessBlockConfiguration.builder()
                        .blockPublicAcls(false)
                        .ignorePublicAcls(false)
                        .blockPublicPolicy(false)
                        .restrictPublicBuckets(false)
                        .build())
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryPutPublicAccessBlock1());
    }

    @Test
    void testTryPutPublicAccessBlock1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.putPublicAccessBlock(PutPublicAccessBlockRequest.builder()
                .bucket("bucket")
                .contentMD5("contentMD5")
                .publicAccessBlockConfiguration(PublicAccessBlockConfiguration.builder()
                        .blockPublicAcls(false)
                        .ignorePublicAcls(false)
                        .blockPublicPolicy(false)
                        .restrictPublicBuckets(false)
                        .build())
                .expectedBucketOwner("expectedBucketOwner")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryPutPublicAccessBlock1());
    }

    @Test
    void testTryPutPublicAccessBlock2() {
        // Setup
        // Configure S3Client.putPublicAccessBlock(...).
        final PutPublicAccessBlockResponse putPublicAccessBlockResponse = PutPublicAccessBlockResponse.builder().build();
        when(mockS3Client.putPublicAccessBlock(any(Consumer.class))).thenReturn(putPublicAccessBlockResponse);

        // Run the test
        myClassUnderTest.tryPutPublicAccessBlock2();

        // Verify the results
    }

    @Test
    void testTryPutPublicAccessBlock2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.putPublicAccessBlock(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryPutPublicAccessBlock2());
    }

    @Test
    void testTryPutPublicAccessBlock2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.putPublicAccessBlock(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryPutPublicAccessBlock2());
    }

    @Test
    void testTryPutPublicAccessBlock2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.putPublicAccessBlock(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryPutPublicAccessBlock2());
    }

    @Test
    void testTryRestoreObject1() {
        // Setup
        // Configure S3Client.restoreObject(...).
        final RestoreObjectResponse restoreObjectResponse = RestoreObjectResponse.builder()
                .requestCharged(RequestCharged.REQUESTER)
                .restoreOutputPath("restoreOutputPath")
                .build();
        when(mockS3Client.restoreObject(RestoreObjectRequest.builder()
                .bucket("bucket")
                .key("key")
                .versionId("versionId")
                .restoreRequest(RestoreRequest.builder()
                        .days(0)
                        .glacierJobParameters(GlacierJobParameters.builder()
                                .tier(Tier.STANDARD)
                                .build())
                        .type(RestoreRequestType.SELECT)
                        .tier(Tier.STANDARD)
                        .description("description")
                        .selectParameters(SelectParameters.builder()
                                .inputSerialization(InputSerialization.builder()
                                        .csv(CSVInput.builder()
                                                .fileHeaderInfo(FileHeaderInfo.USE)
                                                .comments("comments")
                                                .quoteEscapeCharacter("quoteEscapeCharacter")
                                                .recordDelimiter("recordDelimiter")
                                                .fieldDelimiter("fieldDelimiter")
                                                .quoteCharacter("quoteCharacter")
                                                .allowQuotedRecordDelimiter(false)
                                                .build())
                                        .compressionType(CompressionType.NONE)
                                        .json(JSONInput.builder()
                                                .type(JSONType.DOCUMENT)
                                                .build())
                                        .parquet(ParquetInput.builder().build())
                                        .build())
                                .expressionType(ExpressionType.SQL)
                                .expression("expression")
                                .outputSerialization(OutputSerialization.builder()
                                        .csv(CSVOutput.builder()
                                                .quoteFields(QuoteFields.ALWAYS)
                                                .quoteEscapeCharacter("quoteEscapeCharacter")
                                                .recordDelimiter("recordDelimiter")
                                                .fieldDelimiter("fieldDelimiter")
                                                .quoteCharacter("quoteCharacter")
                                                .build())
                                        .json(JSONOutput.builder()
                                                .recordDelimiter("recordDelimiter")
                                                .build())
                                        .build())
                                .build())
                        .outputLocation(OutputLocation.builder()
                                .s3(S3Location.builder()
                                        .bucketName("bucketName")
                                        .prefix("prefix")
                                        .encryption(Encryption.builder()
                                                .encryptionType(ServerSideEncryption.AES256)
                                                .kmsKeyId("kmsKeyId")
                                                .kmsContext("kmsContext")
                                                .build())
                                        .cannedACL(ObjectCannedACL.PRIVATE)
                                        .accessControlList(Grant.builder()
                                                .grantee(Grantee.builder()
                                                        .displayName("displayName")
                                                        .emailAddress("emailAddress")
                                                        .id("id")
                                                        .type(Type.CANONICAL_USER)
                                                        .uri("uri")
                                                        .build())
                                                .permission(Permission.FULL_CONTROL)
                                                .build())
                                        .tagging(Tagging.builder()
                                                .tagSet(Tag.builder()
                                                        .key("key")
                                                        .value("value")
                                                        .build())
                                                .build())
                                        .userMetadata(MetadataEntry.builder()
                                                .name("name")
                                                .value("value")
                                                .build())
                                        .build())
                                .build())
                        .build())
                .build())).thenReturn(restoreObjectResponse);

        // Run the test
        myClassUnderTest.tryRestoreObject1();

        // Verify the results
    }

    @Test
    void testTryRestoreObject1_S3ClientThrowsObjectAlreadyInActiveTierErrorException() {
        // Setup
        when(mockS3Client.restoreObject(RestoreObjectRequest.builder()
                .bucket("bucket")
                .key("key")
                .versionId("versionId")
                .restoreRequest(RestoreRequest.builder()
                        .days(0)
                        .glacierJobParameters(GlacierJobParameters.builder()
                                .tier(Tier.STANDARD)
                                .build())
                        .type(RestoreRequestType.SELECT)
                        .tier(Tier.STANDARD)
                        .description("description")
                        .selectParameters(SelectParameters.builder()
                                .inputSerialization(InputSerialization.builder()
                                        .csv(CSVInput.builder()
                                                .fileHeaderInfo(FileHeaderInfo.USE)
                                                .comments("comments")
                                                .quoteEscapeCharacter("quoteEscapeCharacter")
                                                .recordDelimiter("recordDelimiter")
                                                .fieldDelimiter("fieldDelimiter")
                                                .quoteCharacter("quoteCharacter")
                                                .allowQuotedRecordDelimiter(false)
                                                .build())
                                        .compressionType(CompressionType.NONE)
                                        .json(JSONInput.builder()
                                                .type(JSONType.DOCUMENT)
                                                .build())
                                        .parquet(ParquetInput.builder().build())
                                        .build())
                                .expressionType(ExpressionType.SQL)
                                .expression("expression")
                                .outputSerialization(OutputSerialization.builder()
                                        .csv(CSVOutput.builder()
                                                .quoteFields(QuoteFields.ALWAYS)
                                                .quoteEscapeCharacter("quoteEscapeCharacter")
                                                .recordDelimiter("recordDelimiter")
                                                .fieldDelimiter("fieldDelimiter")
                                                .quoteCharacter("quoteCharacter")
                                                .build())
                                        .json(JSONOutput.builder()
                                                .recordDelimiter("recordDelimiter")
                                                .build())
                                        .build())
                                .build())
                        .outputLocation(OutputLocation.builder()
                                .s3(S3Location.builder()
                                        .bucketName("bucketName")
                                        .prefix("prefix")
                                        .encryption(Encryption.builder()
                                                .encryptionType(ServerSideEncryption.AES256)
                                                .kmsKeyId("kmsKeyId")
                                                .kmsContext("kmsContext")
                                                .build())
                                        .cannedACL(ObjectCannedACL.PRIVATE)
                                        .accessControlList(Grant.builder()
                                                .grantee(Grantee.builder()
                                                        .displayName("displayName")
                                                        .emailAddress("emailAddress")
                                                        .id("id")
                                                        .type(Type.CANONICAL_USER)
                                                        .uri("uri")
                                                        .build())
                                                .permission(Permission.FULL_CONTROL)
                                                .build())
                                        .tagging(Tagging.builder()
                                                .tagSet(Tag.builder()
                                                        .key("key")
                                                        .value("value")
                                                        .build())
                                                .build())
                                        .userMetadata(MetadataEntry.builder()
                                                .name("name")
                                                .value("value")
                                                .build())
                                        .build())
                                .build())
                        .build())
                .build())).thenThrow(ObjectAlreadyInActiveTierErrorException.class);

        // Run the test
        assertThrows(ObjectAlreadyInActiveTierErrorException.class, () -> myClassUnderTest.tryRestoreObject1());
    }

    @Test
    void testTryRestoreObject1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.restoreObject(RestoreObjectRequest.builder()
                .bucket("bucket")
                .key("key")
                .versionId("versionId")
                .restoreRequest(RestoreRequest.builder()
                        .days(0)
                        .glacierJobParameters(GlacierJobParameters.builder()
                                .tier(Tier.STANDARD)
                                .build())
                        .type(RestoreRequestType.SELECT)
                        .tier(Tier.STANDARD)
                        .description("description")
                        .selectParameters(SelectParameters.builder()
                                .inputSerialization(InputSerialization.builder()
                                        .csv(CSVInput.builder()
                                                .fileHeaderInfo(FileHeaderInfo.USE)
                                                .comments("comments")
                                                .quoteEscapeCharacter("quoteEscapeCharacter")
                                                .recordDelimiter("recordDelimiter")
                                                .fieldDelimiter("fieldDelimiter")
                                                .quoteCharacter("quoteCharacter")
                                                .allowQuotedRecordDelimiter(false)
                                                .build())
                                        .compressionType(CompressionType.NONE)
                                        .json(JSONInput.builder()
                                                .type(JSONType.DOCUMENT)
                                                .build())
                                        .parquet(ParquetInput.builder().build())
                                        .build())
                                .expressionType(ExpressionType.SQL)
                                .expression("expression")
                                .outputSerialization(OutputSerialization.builder()
                                        .csv(CSVOutput.builder()
                                                .quoteFields(QuoteFields.ALWAYS)
                                                .quoteEscapeCharacter("quoteEscapeCharacter")
                                                .recordDelimiter("recordDelimiter")
                                                .fieldDelimiter("fieldDelimiter")
                                                .quoteCharacter("quoteCharacter")
                                                .build())
                                        .json(JSONOutput.builder()
                                                .recordDelimiter("recordDelimiter")
                                                .build())
                                        .build())
                                .build())
                        .outputLocation(OutputLocation.builder()
                                .s3(S3Location.builder()
                                        .bucketName("bucketName")
                                        .prefix("prefix")
                                        .encryption(Encryption.builder()
                                                .encryptionType(ServerSideEncryption.AES256)
                                                .kmsKeyId("kmsKeyId")
                                                .kmsContext("kmsContext")
                                                .build())
                                        .cannedACL(ObjectCannedACL.PRIVATE)
                                        .accessControlList(Grant.builder()
                                                .grantee(Grantee.builder()
                                                        .displayName("displayName")
                                                        .emailAddress("emailAddress")
                                                        .id("id")
                                                        .type(Type.CANONICAL_USER)
                                                        .uri("uri")
                                                        .build())
                                                .permission(Permission.FULL_CONTROL)
                                                .build())
                                        .tagging(Tagging.builder()
                                                .tagSet(Tag.builder()
                                                        .key("key")
                                                        .value("value")
                                                        .build())
                                                .build())
                                        .userMetadata(MetadataEntry.builder()
                                                .name("name")
                                                .value("value")
                                                .build())
                                        .build())
                                .build())
                        .build())
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryRestoreObject1());
    }

    @Test
    void testTryRestoreObject1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.restoreObject(RestoreObjectRequest.builder()
                .bucket("bucket")
                .key("key")
                .versionId("versionId")
                .restoreRequest(RestoreRequest.builder()
                        .days(0)
                        .glacierJobParameters(GlacierJobParameters.builder()
                                .tier(Tier.STANDARD)
                                .build())
                        .type(RestoreRequestType.SELECT)
                        .tier(Tier.STANDARD)
                        .description("description")
                        .selectParameters(SelectParameters.builder()
                                .inputSerialization(InputSerialization.builder()
                                        .csv(CSVInput.builder()
                                                .fileHeaderInfo(FileHeaderInfo.USE)
                                                .comments("comments")
                                                .quoteEscapeCharacter("quoteEscapeCharacter")
                                                .recordDelimiter("recordDelimiter")
                                                .fieldDelimiter("fieldDelimiter")
                                                .quoteCharacter("quoteCharacter")
                                                .allowQuotedRecordDelimiter(false)
                                                .build())
                                        .compressionType(CompressionType.NONE)
                                        .json(JSONInput.builder()
                                                .type(JSONType.DOCUMENT)
                                                .build())
                                        .parquet(ParquetInput.builder().build())
                                        .build())
                                .expressionType(ExpressionType.SQL)
                                .expression("expression")
                                .outputSerialization(OutputSerialization.builder()
                                        .csv(CSVOutput.builder()
                                                .quoteFields(QuoteFields.ALWAYS)
                                                .quoteEscapeCharacter("quoteEscapeCharacter")
                                                .recordDelimiter("recordDelimiter")
                                                .fieldDelimiter("fieldDelimiter")
                                                .quoteCharacter("quoteCharacter")
                                                .build())
                                        .json(JSONOutput.builder()
                                                .recordDelimiter("recordDelimiter")
                                                .build())
                                        .build())
                                .build())
                        .outputLocation(OutputLocation.builder()
                                .s3(S3Location.builder()
                                        .bucketName("bucketName")
                                        .prefix("prefix")
                                        .encryption(Encryption.builder()
                                                .encryptionType(ServerSideEncryption.AES256)
                                                .kmsKeyId("kmsKeyId")
                                                .kmsContext("kmsContext")
                                                .build())
                                        .cannedACL(ObjectCannedACL.PRIVATE)
                                        .accessControlList(Grant.builder()
                                                .grantee(Grantee.builder()
                                                        .displayName("displayName")
                                                        .emailAddress("emailAddress")
                                                        .id("id")
                                                        .type(Type.CANONICAL_USER)
                                                        .uri("uri")
                                                        .build())
                                                .permission(Permission.FULL_CONTROL)
                                                .build())
                                        .tagging(Tagging.builder()
                                                .tagSet(Tag.builder()
                                                        .key("key")
                                                        .value("value")
                                                        .build())
                                                .build())
                                        .userMetadata(MetadataEntry.builder()
                                                .name("name")
                                                .value("value")
                                                .build())
                                        .build())
                                .build())
                        .build())
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryRestoreObject1());
    }

    @Test
    void testTryRestoreObject1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.restoreObject(RestoreObjectRequest.builder()
                .bucket("bucket")
                .key("key")
                .versionId("versionId")
                .restoreRequest(RestoreRequest.builder()
                        .days(0)
                        .glacierJobParameters(GlacierJobParameters.builder()
                                .tier(Tier.STANDARD)
                                .build())
                        .type(RestoreRequestType.SELECT)
                        .tier(Tier.STANDARD)
                        .description("description")
                        .selectParameters(SelectParameters.builder()
                                .inputSerialization(InputSerialization.builder()
                                        .csv(CSVInput.builder()
                                                .fileHeaderInfo(FileHeaderInfo.USE)
                                                .comments("comments")
                                                .quoteEscapeCharacter("quoteEscapeCharacter")
                                                .recordDelimiter("recordDelimiter")
                                                .fieldDelimiter("fieldDelimiter")
                                                .quoteCharacter("quoteCharacter")
                                                .allowQuotedRecordDelimiter(false)
                                                .build())
                                        .compressionType(CompressionType.NONE)
                                        .json(JSONInput.builder()
                                                .type(JSONType.DOCUMENT)
                                                .build())
                                        .parquet(ParquetInput.builder().build())
                                        .build())
                                .expressionType(ExpressionType.SQL)
                                .expression("expression")
                                .outputSerialization(OutputSerialization.builder()
                                        .csv(CSVOutput.builder()
                                                .quoteFields(QuoteFields.ALWAYS)
                                                .quoteEscapeCharacter("quoteEscapeCharacter")
                                                .recordDelimiter("recordDelimiter")
                                                .fieldDelimiter("fieldDelimiter")
                                                .quoteCharacter("quoteCharacter")
                                                .build())
                                        .json(JSONOutput.builder()
                                                .recordDelimiter("recordDelimiter")
                                                .build())
                                        .build())
                                .build())
                        .outputLocation(OutputLocation.builder()
                                .s3(S3Location.builder()
                                        .bucketName("bucketName")
                                        .prefix("prefix")
                                        .encryption(Encryption.builder()
                                                .encryptionType(ServerSideEncryption.AES256)
                                                .kmsKeyId("kmsKeyId")
                                                .kmsContext("kmsContext")
                                                .build())
                                        .cannedACL(ObjectCannedACL.PRIVATE)
                                        .accessControlList(Grant.builder()
                                                .grantee(Grantee.builder()
                                                        .displayName("displayName")
                                                        .emailAddress("emailAddress")
                                                        .id("id")
                                                        .type(Type.CANONICAL_USER)
                                                        .uri("uri")
                                                        .build())
                                                .permission(Permission.FULL_CONTROL)
                                                .build())
                                        .tagging(Tagging.builder()
                                                .tagSet(Tag.builder()
                                                        .key("key")
                                                        .value("value")
                                                        .build())
                                                .build())
                                        .userMetadata(MetadataEntry.builder()
                                                .name("name")
                                                .value("value")
                                                .build())
                                        .build())
                                .build())
                        .build())
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryRestoreObject1());
    }

    @Test
    void testTryRestoreObject2() {
        // Setup
        // Configure S3Client.restoreObject(...).
        final RestoreObjectResponse restoreObjectResponse = RestoreObjectResponse.builder()
                .requestCharged(RequestCharged.REQUESTER)
                .restoreOutputPath("restoreOutputPath")
                .build();
        when(mockS3Client.restoreObject(any(Consumer.class))).thenReturn(restoreObjectResponse);

        // Run the test
        myClassUnderTest.tryRestoreObject2();

        // Verify the results
    }

    @Test
    void testTryRestoreObject2_S3ClientThrowsObjectAlreadyInActiveTierErrorException() {
        // Setup
        when(mockS3Client.restoreObject(any(Consumer.class))).thenThrow(ObjectAlreadyInActiveTierErrorException.class);

        // Run the test
        assertThrows(ObjectAlreadyInActiveTierErrorException.class, () -> myClassUnderTest.tryRestoreObject2());
    }

    @Test
    void testTryRestoreObject2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.restoreObject(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryRestoreObject2());
    }

    @Test
    void testTryRestoreObject2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.restoreObject(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryRestoreObject2());
    }

    @Test
    void testTryRestoreObject2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.restoreObject(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryRestoreObject2());
    }

    @Test
    void testTryUploadPart1() {
        // Setup
        // Configure S3Client.uploadPart(...).
        final UploadPartResponse uploadPartResponse = UploadPartResponse.builder()
                .eTag("eTag")
                .build();
        when(mockS3Client.uploadPart(eq(UploadPartRequest.builder()
                .bucket("bucket")
                .key("key")
                .partNumber(0)
                .uploadId("uploadId")
                .build()), any(RequestBody.class))).thenReturn(uploadPartResponse);

        // Run the test
        myClassUnderTest.tryUploadPart1();

        // Verify the results
    }

    @Test
    void testTryUploadPart1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.uploadPart(eq(UploadPartRequest.builder()
                .bucket("bucket")
                .key("key")
                .partNumber(0)
                .uploadId("uploadId")
                .build()), any(RequestBody.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryUploadPart1());
    }

    @Test
    void testTryUploadPart1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.uploadPart(eq(UploadPartRequest.builder()
                .bucket("bucket")
                .key("key")
                .partNumber(0)
                .uploadId("uploadId")
                .build()), any(RequestBody.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryUploadPart1());
    }

    @Test
    void testTryUploadPart1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.uploadPart(eq(UploadPartRequest.builder()
                .bucket("bucket")
                .key("key")
                .partNumber(0)
                .uploadId("uploadId")
                .build()), any(RequestBody.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryUploadPart1());
    }

    @Test
    void testTryUploadPart2() {
        // Setup
        // Configure S3Client.uploadPart(...).
        final UploadPartResponse uploadPartResponse = UploadPartResponse.builder()
                .eTag("eTag")
                .build();
        when(mockS3Client.uploadPart(any(Consumer.class), any(RequestBody.class))).thenReturn(uploadPartResponse);

        // Run the test
        myClassUnderTest.tryUploadPart2();

        // Verify the results
    }

    @Test
    void testTryUploadPart2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.uploadPart(any(Consumer.class), any(RequestBody.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryUploadPart2());
    }

    @Test
    void testTryUploadPart2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.uploadPart(any(Consumer.class), any(RequestBody.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryUploadPart2());
    }

    @Test
    void testTryUploadPart2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.uploadPart(any(Consumer.class), any(RequestBody.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryUploadPart2());
    }

    @Test
    void testTryUploadPart3() {
        // Setup
        // Configure S3Client.uploadPart(...).
        final UploadPartResponse uploadPartResponse = UploadPartResponse.builder()
                .eTag("eTag")
                .build();
        when(mockS3Client.uploadPart(UploadPartRequest.builder()
                .bucket("bucket")
                .key("key")
                .partNumber(0)
                .uploadId("uploadId")
                .build(), Paths.get("filename.txt"))).thenReturn(uploadPartResponse);

        // Run the test
        myClassUnderTest.tryUploadPart3();

        // Verify the results
    }

    @Test
    void testTryUploadPart3_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.uploadPart(UploadPartRequest.builder()
                .bucket("bucket")
                .key("key")
                .partNumber(0)
                .uploadId("uploadId")
                .build(), Paths.get("filename.txt"))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryUploadPart3());
    }

    @Test
    void testTryUploadPart3_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.uploadPart(UploadPartRequest.builder()
                .bucket("bucket")
                .key("key")
                .partNumber(0)
                .uploadId("uploadId")
                .build(), Paths.get("filename.txt"))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryUploadPart3());
    }

    @Test
    void testTryUploadPart3_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.uploadPart(UploadPartRequest.builder()
                .bucket("bucket")
                .key("key")
                .partNumber(0)
                .uploadId("uploadId")
                .build(), Paths.get("filename.txt"))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryUploadPart3());
    }

    @Test
    void testTryUploadPart4() {
        // Setup
        // Configure S3Client.uploadPart(...).
        final UploadPartResponse uploadPartResponse = UploadPartResponse.builder()
                .eTag("eTag")
                .build();
        when(mockS3Client.uploadPart(any(Consumer.class), eq(Paths.get("filename.txt"))))
                .thenReturn(uploadPartResponse);

        // Run the test
        myClassUnderTest.tryUploadPart4();

        // Verify the results
    }

    @Test
    void testTryUploadPart4_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.uploadPart(any(Consumer.class), eq(Paths.get("filename.txt"))))
                .thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryUploadPart4());
    }

    @Test
    void testTryUploadPart4_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.uploadPart(any(Consumer.class), eq(Paths.get("filename.txt"))))
                .thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryUploadPart4());
    }

    @Test
    void testTryUploadPart4_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.uploadPart(any(Consumer.class), eq(Paths.get("filename.txt")))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryUploadPart4());
    }

    @Test
    void testTryUploadPartCopy1() {
        // Setup
        // Configure S3Client.uploadPartCopy(...).
        final UploadPartCopyResponse uploadPartCopyResponse = UploadPartCopyResponse.builder()
                .copySourceVersionId("copySourceVersionId")
                .copyPartResult(CopyPartResult.builder()
                        .eTag("eTag")
                        .lastModified(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                        .build())
                .serverSideEncryption(ServerSideEncryption.AES256)
                .sseCustomerAlgorithm("sseCustomerAlgorithm")
                .sseCustomerKeyMD5("sseCustomerKeyMD5")
                .ssekmsKeyId("ssekmsKeyId")
                .requestCharged(RequestCharged.REQUESTER)
                .build();
        when(mockS3Client.uploadPartCopy(UploadPartCopyRequest.builder()
                .bucket("bucket")
                .copySource("copySource")
                .copySourceIfMatch("copySourceIfMatch")
                .copySourceIfModifiedSince(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                .copySourceIfNoneMatch("copySourceIfNoneMatch")
                .copySourceIfUnmodifiedSince(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                .copySourceRange("copySourceRange")
                .key("key")
                .partNumber(0)
                .uploadId("uploadId")
                .sseCustomerAlgorithm("sseCustomerAlgorithm")
                .sseCustomerKey("sseCustomerKey")
                .sseCustomerKeyMD5("sseCustomerKeyMD5")
                .copySourceSSECustomerAlgorithm("copySourceSSECustomerAlgorithm")
                .copySourceSSECustomerKey("copySourceSSECustomerKey")
                .copySourceSSECustomerKeyMD5("copySourceSSECustomerKeyMD5")
                .requestPayer(RequestPayer.REQUESTER)
                .expectedBucketOwner("expectedBucketOwner")
                .expectedSourceBucketOwner("expectedSourceBucketOwner")
                .build())).thenReturn(uploadPartCopyResponse);

        // Run the test
        myClassUnderTest.tryUploadPartCopy1();

        // Verify the results
    }

    @Test
    void testTryUploadPartCopy1_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.uploadPartCopy(UploadPartCopyRequest.builder()
                .bucket("bucket")
                .copySource("copySource")
                .copySourceIfMatch("copySourceIfMatch")
                .copySourceIfModifiedSince(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                .copySourceIfNoneMatch("copySourceIfNoneMatch")
                .copySourceIfUnmodifiedSince(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                .copySourceRange("copySourceRange")
                .key("key")
                .partNumber(0)
                .uploadId("uploadId")
                .sseCustomerAlgorithm("sseCustomerAlgorithm")
                .sseCustomerKey("sseCustomerKey")
                .sseCustomerKeyMD5("sseCustomerKeyMD5")
                .copySourceSSECustomerAlgorithm("copySourceSSECustomerAlgorithm")
                .copySourceSSECustomerKey("copySourceSSECustomerKey")
                .copySourceSSECustomerKeyMD5("copySourceSSECustomerKeyMD5")
                .requestPayer(RequestPayer.REQUESTER)
                .expectedBucketOwner("expectedBucketOwner")
                .expectedSourceBucketOwner("expectedSourceBucketOwner")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryUploadPartCopy1());
    }

    @Test
    void testTryUploadPartCopy1_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.uploadPartCopy(UploadPartCopyRequest.builder()
                .bucket("bucket")
                .copySource("copySource")
                .copySourceIfMatch("copySourceIfMatch")
                .copySourceIfModifiedSince(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                .copySourceIfNoneMatch("copySourceIfNoneMatch")
                .copySourceIfUnmodifiedSince(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                .copySourceRange("copySourceRange")
                .key("key")
                .partNumber(0)
                .uploadId("uploadId")
                .sseCustomerAlgorithm("sseCustomerAlgorithm")
                .sseCustomerKey("sseCustomerKey")
                .sseCustomerKeyMD5("sseCustomerKeyMD5")
                .copySourceSSECustomerAlgorithm("copySourceSSECustomerAlgorithm")
                .copySourceSSECustomerKey("copySourceSSECustomerKey")
                .copySourceSSECustomerKeyMD5("copySourceSSECustomerKeyMD5")
                .requestPayer(RequestPayer.REQUESTER)
                .expectedBucketOwner("expectedBucketOwner")
                .expectedSourceBucketOwner("expectedSourceBucketOwner")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryUploadPartCopy1());
    }

    @Test
    void testTryUploadPartCopy1_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.uploadPartCopy(UploadPartCopyRequest.builder()
                .bucket("bucket")
                .copySource("copySource")
                .copySourceIfMatch("copySourceIfMatch")
                .copySourceIfModifiedSince(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                .copySourceIfNoneMatch("copySourceIfNoneMatch")
                .copySourceIfUnmodifiedSince(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                .copySourceRange("copySourceRange")
                .key("key")
                .partNumber(0)
                .uploadId("uploadId")
                .sseCustomerAlgorithm("sseCustomerAlgorithm")
                .sseCustomerKey("sseCustomerKey")
                .sseCustomerKeyMD5("sseCustomerKeyMD5")
                .copySourceSSECustomerAlgorithm("copySourceSSECustomerAlgorithm")
                .copySourceSSECustomerKey("copySourceSSECustomerKey")
                .copySourceSSECustomerKeyMD5("copySourceSSECustomerKeyMD5")
                .requestPayer(RequestPayer.REQUESTER)
                .expectedBucketOwner("expectedBucketOwner")
                .expectedSourceBucketOwner("expectedSourceBucketOwner")
                .build())).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryUploadPartCopy1());
    }

    @Test
    void testTryUploadPartCopy2() {
        // Setup
        // Configure S3Client.uploadPartCopy(...).
        final UploadPartCopyResponse uploadPartCopyResponse = UploadPartCopyResponse.builder()
                .copySourceVersionId("copySourceVersionId")
                .copyPartResult(CopyPartResult.builder()
                        .eTag("eTag")
                        .lastModified(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                        .build())
                .serverSideEncryption(ServerSideEncryption.AES256)
                .sseCustomerAlgorithm("sseCustomerAlgorithm")
                .sseCustomerKeyMD5("sseCustomerKeyMD5")
                .ssekmsKeyId("ssekmsKeyId")
                .requestCharged(RequestCharged.REQUESTER)
                .build();
        when(mockS3Client.uploadPartCopy(any(Consumer.class))).thenReturn(uploadPartCopyResponse);

        // Run the test
        myClassUnderTest.tryUploadPartCopy2();

        // Verify the results
    }

    @Test
    void testTryUploadPartCopy2_S3ClientThrowsAwsServiceException() {
        // Setup
        when(mockS3Client.uploadPartCopy(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryUploadPartCopy2());
    }

    @Test
    void testTryUploadPartCopy2_S3ClientThrowsSdkClientException() {
        // Setup
        when(mockS3Client.uploadPartCopy(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryUploadPartCopy2());
    }

    @Test
    void testTryUploadPartCopy2_S3ClientThrowsS3Exception() {
        // Setup
        when(mockS3Client.uploadPartCopy(any(Consumer.class))).thenThrow(S3Exception.class);

        // Run the test
        assertThrows(S3Exception.class, () -> myClassUnderTest.tryUploadPartCopy2());
    }

    @Test
    void testTryUtilities() {
        // Setup
        // Configure S3Client.utilities(...).
        final S3Utilities s3Utilities = S3Utilities.builder()
                .region(Region.of("value"))
                .s3Configuration(S3Configuration.builder()
                        .dualstackEnabled(false)
                        .accelerateModeEnabled(false)
                        .pathStyleAccessEnabled(false)
                        .checksumValidationEnabled(false)
                        .chunkedEncodingEnabled(false)
                        .useArnRegionEnabled(false)
                        .profileFile(ProfileFile.builder()
                                .content(new ByteArrayInputStream("content".getBytes()))
                                .type(ProfileFile.Type.CONFIGURATION)
                                .build())
                        .profileName("profileName")
                        .build())
                .build();
        when(mockS3Client.utilities()).thenReturn(s3Utilities);

        // Run the test
        myClassUnderTest.tryUtilities();

        // Verify the results
    }

    @Test
    void testTryWaiter() {
        // Setup
        // Run the test
        myClassUnderTest.tryWaiter();

        // Verify the results
    }
}
