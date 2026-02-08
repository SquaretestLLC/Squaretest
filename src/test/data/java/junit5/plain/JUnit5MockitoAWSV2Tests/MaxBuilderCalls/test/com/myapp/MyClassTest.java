package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
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
}
