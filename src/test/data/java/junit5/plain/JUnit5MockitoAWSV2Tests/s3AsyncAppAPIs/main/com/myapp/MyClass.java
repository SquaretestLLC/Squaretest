/*
 * Copyright 2026 Squaretest LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.myapp;

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

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;

public class MyClass {

    private S3AsyncClient s3AsyncClient;

    public MyClass(final S3AsyncClient s3AsyncClient) {
        this.s3AsyncClient = s3AsyncClient;
    }

    public void tryAbortMultipartUpload() {
        final AbortMultipartUploadRequest abortMultipartUploadRequest = AbortMultipartUploadRequest.builder().bucket("bucketName").key("key").build();
        final CompletableFuture<AbortMultipartUploadResponse> result = s3AsyncClient.abortMultipartUpload(abortMultipartUploadRequest);
    }

    public void tryAbortMultipartUpload1() {
        final CompletableFuture<AbortMultipartUploadResponse> result = s3AsyncClient.abortMultipartUpload(Object::toString);
    }

    public void tryCompleteMultipartUpload() {
        final CompleteMultipartUploadRequest completeMultipartUploadRequest = CompleteMultipartUploadRequest.builder().bucket("bucketName").key("key").build();
        final CompletableFuture<CompleteMultipartUploadResponse> result = s3AsyncClient.completeMultipartUpload(completeMultipartUploadRequest);
    }

    public void tryCompleteMultipartUpload1() {
        final CompletableFuture<CompleteMultipartUploadResponse> result = s3AsyncClient.completeMultipartUpload(Object::toString);
    }

    public void tryCopyObject() {
        final CopyObjectRequest copyObjectRequest = CopyObjectRequest.builder().bucket("bucketName").key("key").build();
        final CompletableFuture<CopyObjectResponse> result = s3AsyncClient.copyObject(copyObjectRequest);
    }

    public void tryCopyObject1() {
        final CompletableFuture<CopyObjectResponse> result = s3AsyncClient.copyObject(Object::toString);
    }

    public void tryCreateBucket() {
        final CreateBucketRequest createBucketRequest = CreateBucketRequest.builder().bucket("bucketName").build();
        final CompletableFuture<CreateBucketResponse> result = s3AsyncClient.createBucket(createBucketRequest);
    }

    public void tryCreateBucket1() {
        final CompletableFuture<CreateBucketResponse> result = s3AsyncClient.createBucket(Object::toString);
    }

    public void tryCreateMultipartUpload() {
        final CreateMultipartUploadRequest createMultipartUploadRequest = CreateMultipartUploadRequest.builder().bucket("bucketName").key("key").build();
        final CompletableFuture<CreateMultipartUploadResponse> result = s3AsyncClient.createMultipartUpload(createMultipartUploadRequest);
    }

    public void tryCreateMultipartUpload1() {
        final CompletableFuture<CreateMultipartUploadResponse> result = s3AsyncClient.createMultipartUpload(Object::toString);
    }

    public void tryDeleteBucket() {
        final DeleteBucketRequest deleteBucketRequest = DeleteBucketRequest.builder().bucket("bucketName").build();
        final CompletableFuture<DeleteBucketResponse> result = s3AsyncClient.deleteBucket(deleteBucketRequest);
    }

    public void tryDeleteBucket1() {
        final CompletableFuture<DeleteBucketResponse> result = s3AsyncClient.deleteBucket(Object::toString);
    }

    public void tryDeleteBucketAnalyticsConfiguration() {
        final DeleteBucketAnalyticsConfigurationRequest deleteBucketAnalyticsConfigurationRequest = DeleteBucketAnalyticsConfigurationRequest.builder().bucket("bucketName").build();
        final CompletableFuture<DeleteBucketAnalyticsConfigurationResponse> result = s3AsyncClient.deleteBucketAnalyticsConfiguration(deleteBucketAnalyticsConfigurationRequest);
    }

    public void tryDeleteBucketAnalyticsConfiguration1() {
        final CompletableFuture<DeleteBucketAnalyticsConfigurationResponse> result = s3AsyncClient.deleteBucketAnalyticsConfiguration(Object::toString);
    }

    public void tryDeleteBucketCors() {
        final DeleteBucketCorsRequest deleteBucketCorsRequest = DeleteBucketCorsRequest.builder().bucket("bucketName").build();
        final CompletableFuture<DeleteBucketCorsResponse> result = s3AsyncClient.deleteBucketCors(deleteBucketCorsRequest);
    }

    public void tryDeleteBucketCors1() {
        final CompletableFuture<DeleteBucketCorsResponse> result = s3AsyncClient.deleteBucketCors(Object::toString);
    }

    public void tryDeleteBucketEncryption() {
        final DeleteBucketEncryptionRequest deleteBucketEncryptionRequest = DeleteBucketEncryptionRequest.builder().bucket("bucketName").build();
        final CompletableFuture<DeleteBucketEncryptionResponse> result = s3AsyncClient.deleteBucketEncryption(deleteBucketEncryptionRequest);
    }

    public void tryDeleteBucketEncryption1() {
        final CompletableFuture<DeleteBucketEncryptionResponse> result = s3AsyncClient.deleteBucketEncryption(Object::toString);
    }

    public void tryDeleteBucketInventoryConfiguration() {
        final DeleteBucketInventoryConfigurationRequest deleteBucketInventoryConfigurationRequest = DeleteBucketInventoryConfigurationRequest.builder().bucket("bucketName").build();
        final CompletableFuture<DeleteBucketInventoryConfigurationResponse> result = s3AsyncClient.deleteBucketInventoryConfiguration(deleteBucketInventoryConfigurationRequest);
    }

    public void tryDeleteBucketInventoryConfiguration1() {
        final CompletableFuture<DeleteBucketInventoryConfigurationResponse> result = s3AsyncClient.deleteBucketInventoryConfiguration(Object::toString);
    }

    public void tryDeleteBucketLifecycle() {
        final DeleteBucketLifecycleRequest deleteBucketLifecycleRequest = DeleteBucketLifecycleRequest.builder().bucket("bucketName").build();
        final CompletableFuture<DeleteBucketLifecycleResponse> result = s3AsyncClient.deleteBucketLifecycle(deleteBucketLifecycleRequest);
    }

    public void tryDeleteBucketLifecycle1() {
        final CompletableFuture<DeleteBucketLifecycleResponse> result = s3AsyncClient.deleteBucketLifecycle(Object::toString);
    }

    public void tryDeleteBucketMetricsConfiguration() {
        final DeleteBucketMetricsConfigurationRequest deleteBucketMetricsConfigurationRequest = DeleteBucketMetricsConfigurationRequest.builder().bucket("bucketName").build();
        final CompletableFuture<DeleteBucketMetricsConfigurationResponse> result = s3AsyncClient.deleteBucketMetricsConfiguration(deleteBucketMetricsConfigurationRequest);
    }

    public void tryDeleteBucketMetricsConfiguration1() {
        final CompletableFuture<DeleteBucketMetricsConfigurationResponse> result = s3AsyncClient.deleteBucketMetricsConfiguration(Object::toString);
    }

    public void tryDeleteBucketPolicy() {
        final DeleteBucketPolicyRequest deleteBucketPolicyRequest = DeleteBucketPolicyRequest.builder().bucket("bucketName").build();
        final CompletableFuture<DeleteBucketPolicyResponse> result = s3AsyncClient.deleteBucketPolicy(deleteBucketPolicyRequest);
    }

    public void tryDeleteBucketPolicy1() {
        final CompletableFuture<DeleteBucketPolicyResponse> result = s3AsyncClient.deleteBucketPolicy(Object::toString);
    }

    public void tryDeleteBucketReplication() {
        final DeleteBucketReplicationRequest deleteBucketReplicationRequest = DeleteBucketReplicationRequest.builder().bucket("bucketName").build();
        final CompletableFuture<DeleteBucketReplicationResponse> result = s3AsyncClient.deleteBucketReplication(deleteBucketReplicationRequest);
    }

    public void tryDeleteBucketReplication1() {
        final CompletableFuture<DeleteBucketReplicationResponse> result = s3AsyncClient.deleteBucketReplication(Object::toString);
    }

    public void tryDeleteBucketTagging() {
        final DeleteBucketTaggingRequest deleteBucketTaggingRequest = DeleteBucketTaggingRequest.builder().bucket("bucketName").build();
        final CompletableFuture<DeleteBucketTaggingResponse> result = s3AsyncClient.deleteBucketTagging(deleteBucketTaggingRequest);
    }

    public void tryDeleteBucketTagging1() {
        final CompletableFuture<DeleteBucketTaggingResponse> result = s3AsyncClient.deleteBucketTagging(Object::toString);
    }

    public void tryDeleteBucketWebsite() {
        final DeleteBucketWebsiteRequest deleteBucketWebsiteRequest = DeleteBucketWebsiteRequest.builder().bucket("bucketName").build();
        final CompletableFuture<DeleteBucketWebsiteResponse> result = s3AsyncClient.deleteBucketWebsite(deleteBucketWebsiteRequest);
    }

    public void tryDeleteBucketWebsite1() {
        final CompletableFuture<DeleteBucketWebsiteResponse> result = s3AsyncClient.deleteBucketWebsite(Object::toString);
    }

    public void tryDeleteObject() {
        final DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder().bucket("bucketName").key("key").build();
        final CompletableFuture<DeleteObjectResponse> result = s3AsyncClient.deleteObject(deleteObjectRequest);
    }

    public void tryDeleteObject1() {
        final CompletableFuture<DeleteObjectResponse> result = s3AsyncClient.deleteObject(Object::toString);
    }

    public void tryDeleteObjectTagging() {
        final DeleteObjectTaggingRequest deleteObjectTaggingRequest = DeleteObjectTaggingRequest.builder().bucket("bucketName").key("key").build();
        final CompletableFuture<DeleteObjectTaggingResponse> result = s3AsyncClient.deleteObjectTagging(deleteObjectTaggingRequest);
    }

    public void tryDeleteObjectTagging1() {
        final CompletableFuture<DeleteObjectTaggingResponse> result = s3AsyncClient.deleteObjectTagging(Object::toString);
    }

    public void tryDeleteObjects() {
        final DeleteObjectsRequest deleteObjectsRequest = DeleteObjectsRequest.builder().bucket("bucketName").build();
        final CompletableFuture<DeleteObjectsResponse> result = s3AsyncClient.deleteObjects(deleteObjectsRequest);
    }

    public void tryDeleteObjects1() {
        final CompletableFuture<DeleteObjectsResponse> result = s3AsyncClient.deleteObjects(Object::toString);
    }

    public void tryDeletePublicAccessBlock() {
        final DeletePublicAccessBlockRequest deletePublicAccessBlockRequest = DeletePublicAccessBlockRequest.builder().bucket("bucketName").build();
        final CompletableFuture<DeletePublicAccessBlockResponse> result = s3AsyncClient.deletePublicAccessBlock(deletePublicAccessBlockRequest);
    }

    public void tryDeletePublicAccessBlock1() {
        final CompletableFuture<DeletePublicAccessBlockResponse> result = s3AsyncClient.deletePublicAccessBlock(Object::toString);
    }

    public void tryGetBucketAccelerateConfiguration() {
        final GetBucketAccelerateConfigurationRequest getBucketAccelerateConfigurationRequest = GetBucketAccelerateConfigurationRequest.builder().bucket("bucketName").build();
        final CompletableFuture<GetBucketAccelerateConfigurationResponse> result = s3AsyncClient.getBucketAccelerateConfiguration(getBucketAccelerateConfigurationRequest);
    }

    public void tryGetBucketAccelerateConfiguration1() {
        final CompletableFuture<GetBucketAccelerateConfigurationResponse> result = s3AsyncClient.getBucketAccelerateConfiguration(Object::toString);
    }

    public void tryGetBucketAcl() {
        final GetBucketAclRequest getBucketAclRequest = GetBucketAclRequest.builder().bucket("bucketName").build();
        final CompletableFuture<GetBucketAclResponse> result = s3AsyncClient.getBucketAcl(getBucketAclRequest);
    }

    public void tryGetBucketAcl1() {
        final CompletableFuture<GetBucketAclResponse> result = s3AsyncClient.getBucketAcl(Object::toString);
    }

    public void tryGetBucketAnalyticsConfiguration() {
        final GetBucketAnalyticsConfigurationRequest getBucketAnalyticsConfigurationRequest = GetBucketAnalyticsConfigurationRequest.builder().bucket("bucketName").build();
        final CompletableFuture<GetBucketAnalyticsConfigurationResponse> result = s3AsyncClient.getBucketAnalyticsConfiguration(getBucketAnalyticsConfigurationRequest);
    }

    public void tryGetBucketAnalyticsConfiguration1() {
        final CompletableFuture<GetBucketAnalyticsConfigurationResponse> result = s3AsyncClient.getBucketAnalyticsConfiguration(Object::toString);
    }

    public void tryGetBucketCors() {
        final GetBucketCorsRequest getBucketCorsRequest = GetBucketCorsRequest.builder().bucket("bucketName").build();
        final CompletableFuture<GetBucketCorsResponse> result = s3AsyncClient.getBucketCors(getBucketCorsRequest);
    }

    public void tryGetBucketCors1() {
        final CompletableFuture<GetBucketCorsResponse> result = s3AsyncClient.getBucketCors(Object::toString);
    }

    public void tryGetBucketEncryption() {
        final GetBucketEncryptionRequest getBucketEncryptionRequest = GetBucketEncryptionRequest.builder().bucket("bucketName").build();
        final CompletableFuture<GetBucketEncryptionResponse> result = s3AsyncClient.getBucketEncryption(getBucketEncryptionRequest);
    }

    public void tryGetBucketEncryption1() {
        final CompletableFuture<GetBucketEncryptionResponse> result = s3AsyncClient.getBucketEncryption(Object::toString);
    }

    public void tryGetBucketInventoryConfiguration() {
        final GetBucketInventoryConfigurationRequest getBucketInventoryConfigurationRequest = GetBucketInventoryConfigurationRequest.builder().bucket("bucketName").build();
        final CompletableFuture<GetBucketInventoryConfigurationResponse> result = s3AsyncClient.getBucketInventoryConfiguration(getBucketInventoryConfigurationRequest);
    }

    public void tryGetBucketInventoryConfiguration1() {
        final CompletableFuture<GetBucketInventoryConfigurationResponse> result = s3AsyncClient.getBucketInventoryConfiguration(Object::toString);
    }

    public void tryGetBucketLifecycleConfiguration() {
        final GetBucketLifecycleConfigurationRequest getBucketLifecycleConfigurationRequest = GetBucketLifecycleConfigurationRequest.builder().bucket("bucketName").build();
        final CompletableFuture<GetBucketLifecycleConfigurationResponse> result = s3AsyncClient.getBucketLifecycleConfiguration(getBucketLifecycleConfigurationRequest);
    }

    public void tryGetBucketLifecycleConfiguration1() {
        final CompletableFuture<GetBucketLifecycleConfigurationResponse> result = s3AsyncClient.getBucketLifecycleConfiguration(Object::toString);
    }

    public void tryGetBucketLocation() {
        final GetBucketLocationRequest getBucketLocationRequest = GetBucketLocationRequest.builder().bucket("bucketName").build();
        final CompletableFuture<GetBucketLocationResponse> result = s3AsyncClient.getBucketLocation(getBucketLocationRequest);
    }

    public void tryGetBucketLocation1() {
        final CompletableFuture<GetBucketLocationResponse> result = s3AsyncClient.getBucketLocation(Object::toString);
    }

    public void tryGetBucketLogging() {
        final GetBucketLoggingRequest getBucketLoggingRequest = GetBucketLoggingRequest.builder().bucket("bucketName").build();
        final CompletableFuture<GetBucketLoggingResponse> result = s3AsyncClient.getBucketLogging(getBucketLoggingRequest);
    }

    public void tryGetBucketLogging1() {
        final CompletableFuture<GetBucketLoggingResponse> result = s3AsyncClient.getBucketLogging(Object::toString);
    }

    public void tryGetBucketMetricsConfiguration() {
        final GetBucketMetricsConfigurationRequest getBucketMetricsConfigurationRequest = GetBucketMetricsConfigurationRequest.builder().bucket("bucketName").build();
        final CompletableFuture<GetBucketMetricsConfigurationResponse> result = s3AsyncClient.getBucketMetricsConfiguration(getBucketMetricsConfigurationRequest);
    }

    public void tryGetBucketMetricsConfiguration1() {
        final CompletableFuture<GetBucketMetricsConfigurationResponse> result = s3AsyncClient.getBucketMetricsConfiguration(Object::toString);
    }

    public void tryGetBucketNotificationConfiguration() {
        final GetBucketNotificationConfigurationRequest getBucketNotificationConfigurationRequest = GetBucketNotificationConfigurationRequest.builder().bucket("bucketName").build();
        final CompletableFuture<GetBucketNotificationConfigurationResponse> result = s3AsyncClient.getBucketNotificationConfiguration(getBucketNotificationConfigurationRequest);
    }

    public void tryGetBucketNotificationConfiguration1() {
        final CompletableFuture<GetBucketNotificationConfigurationResponse> result = s3AsyncClient.getBucketNotificationConfiguration(Object::toString);
    }

    public void tryGetBucketPolicy() {
        final GetBucketPolicyRequest getBucketPolicyRequest = GetBucketPolicyRequest.builder().bucket("bucketName").build();
        final CompletableFuture<GetBucketPolicyResponse> result = s3AsyncClient.getBucketPolicy(getBucketPolicyRequest);
    }

    public void tryGetBucketPolicy1() {
        final CompletableFuture<GetBucketPolicyResponse> result = s3AsyncClient.getBucketPolicy(Object::toString);
    }

    public void tryGetBucketPolicyStatus() {
        final GetBucketPolicyStatusRequest getBucketPolicyStatusRequest = GetBucketPolicyStatusRequest.builder().bucket("bucketName").build();
        final CompletableFuture<GetBucketPolicyStatusResponse> result = s3AsyncClient.getBucketPolicyStatus(getBucketPolicyStatusRequest);
    }

    public void tryGetBucketPolicyStatus1() {
        final CompletableFuture<GetBucketPolicyStatusResponse> result = s3AsyncClient.getBucketPolicyStatus(Object::toString);
    }

    public void tryGetBucketReplication() {
        final GetBucketReplicationRequest getBucketReplicationRequest = GetBucketReplicationRequest.builder().bucket("bucketName").build();
        final CompletableFuture<GetBucketReplicationResponse> result = s3AsyncClient.getBucketReplication(getBucketReplicationRequest);
    }

    public void tryGetBucketReplication1() {
        final CompletableFuture<GetBucketReplicationResponse> result = s3AsyncClient.getBucketReplication(Object::toString);
    }

    public void tryGetBucketRequestPayment() {
        final GetBucketRequestPaymentRequest getBucketRequestPaymentRequest = GetBucketRequestPaymentRequest.builder().bucket("bucketName").build();
        final CompletableFuture<GetBucketRequestPaymentResponse> result = s3AsyncClient.getBucketRequestPayment(getBucketRequestPaymentRequest);
    }

    public void tryGetBucketRequestPayment1() {
        final CompletableFuture<GetBucketRequestPaymentResponse> result = s3AsyncClient.getBucketRequestPayment(Object::toString);
    }

    public void tryGetBucketTagging() {
        final GetBucketTaggingRequest getBucketTaggingRequest = GetBucketTaggingRequest.builder().bucket("bucketName").build();
        final CompletableFuture<GetBucketTaggingResponse> result = s3AsyncClient.getBucketTagging(getBucketTaggingRequest);
    }

    public void tryGetBucketTagging1() {
        final CompletableFuture<GetBucketTaggingResponse> result = s3AsyncClient.getBucketTagging(Object::toString);
    }

    public void tryGetBucketVersioning() {
        final GetBucketVersioningRequest getBucketVersioningRequest = GetBucketVersioningRequest.builder().bucket("bucketName").build();
        final CompletableFuture<GetBucketVersioningResponse> result = s3AsyncClient.getBucketVersioning(getBucketVersioningRequest);
    }

    public void tryGetBucketVersioning1() {
        final CompletableFuture<GetBucketVersioningResponse> result = s3AsyncClient.getBucketVersioning(Object::toString);
    }

    public void tryGetBucketWebsite() {
        final GetBucketWebsiteRequest getBucketWebsiteRequest = GetBucketWebsiteRequest.builder().bucket("bucketName").build();
        final CompletableFuture<GetBucketWebsiteResponse> result = s3AsyncClient.getBucketWebsite(getBucketWebsiteRequest);
    }

    public void tryGetBucketWebsite1() {
        final CompletableFuture<GetBucketWebsiteResponse> result = s3AsyncClient.getBucketWebsite(Object::toString);
    }

    public void tryGetObject() {
        final GetObjectRequest getObjectRequest = GetObjectRequest.builder().bucket("bucketName").key("key").build();
        final CompletableFuture<GetObjectResponse> result = s3AsyncClient.getObject(getObjectRequest, AsyncResponseTransformer.toFile(Paths.get("filename.txt")));
    }

    public void tryGetObjectToBytes() {
        final GetObjectRequest getObjectRequest = GetObjectRequest.builder().bucket("bucketName").key("key").build();
        final CompletableFuture<ResponseBytes<GetObjectResponse>> result = s3AsyncClient.getObject(getObjectRequest, AsyncResponseTransformer.toBytes());
    }

    public void tryGetObject1() {
        final CompletableFuture<GetObjectResponse> result = s3AsyncClient.getObject(Object::toString, AsyncResponseTransformer.toFile(Paths.get("filename.txt")));
    }

    public void tryGetObject1ToByteArray() {
        final CompletableFuture<ResponseBytes<GetObjectResponse>> result = s3AsyncClient.getObject(Object::toString, AsyncResponseTransformer.toBytes());
    }

    public void tryGetObject2() {
        final GetObjectRequest getObjectRequest = GetObjectRequest.builder().bucket("bucketName").key("key").build();
        final Path destinationPath = Paths.get("filename.txt");
        final CompletableFuture<GetObjectResponse> result = s3AsyncClient.getObject(getObjectRequest, destinationPath);
    }

    public void tryGetObject3() {
        final Path destinationPath = Paths.get("filename.txt");
        final CompletableFuture<GetObjectResponse> result = s3AsyncClient.getObject(Object::toString, destinationPath);
    }

    public void tryGetObjectAcl() {
        final GetObjectAclRequest getObjectAclRequest = GetObjectAclRequest.builder().bucket("bucketName").key("key").build();
        final CompletableFuture<GetObjectAclResponse> result = s3AsyncClient.getObjectAcl(getObjectAclRequest);
    }

    public void tryGetObjectAcl1() {
        final CompletableFuture<GetObjectAclResponse> result = s3AsyncClient.getObjectAcl(Object::toString);
    }

    public void tryGetObjectLegalHold() {
        final GetObjectLegalHoldRequest getObjectLegalHoldRequest = GetObjectLegalHoldRequest.builder().bucket("bucketName").key("key").build();
        final CompletableFuture<GetObjectLegalHoldResponse> result = s3AsyncClient.getObjectLegalHold(getObjectLegalHoldRequest);
    }

    public void tryGetObjectLegalHold1() {
        final CompletableFuture<GetObjectLegalHoldResponse> result = s3AsyncClient.getObjectLegalHold(Object::toString);
    }

    public void tryGetObjectLockConfiguration() {
        final GetObjectLockConfigurationRequest getObjectLockConfigurationRequest = GetObjectLockConfigurationRequest.builder().bucket("bucketName").build();
        final CompletableFuture<GetObjectLockConfigurationResponse> result = s3AsyncClient.getObjectLockConfiguration(getObjectLockConfigurationRequest);
    }

    public void tryGetObjectLockConfiguration1() {
        final CompletableFuture<GetObjectLockConfigurationResponse> result = s3AsyncClient.getObjectLockConfiguration(Object::toString);
    }

    public void tryGetObjectRetention() {
        final GetObjectRetentionRequest getObjectRetentionRequest = GetObjectRetentionRequest.builder().bucket("bucketName").key("key").build();
        final CompletableFuture<GetObjectRetentionResponse> result = s3AsyncClient.getObjectRetention(getObjectRetentionRequest);
    }

    public void tryGetObjectRetention1() {
        final CompletableFuture<GetObjectRetentionResponse> result = s3AsyncClient.getObjectRetention(Object::toString);
    }

    public void tryGetObjectTagging() {
        final GetObjectTaggingRequest getObjectTaggingRequest = GetObjectTaggingRequest.builder().bucket("bucketName").key("key").build();
        final CompletableFuture<GetObjectTaggingResponse> result = s3AsyncClient.getObjectTagging(getObjectTaggingRequest);
    }

    public void tryGetObjectTagging1() {
        final CompletableFuture<GetObjectTaggingResponse> result = s3AsyncClient.getObjectTagging(Object::toString);
    }

    public void tryGetObjectTorrent() {
        final GetObjectTorrentRequest getObjectTorrentRequest = GetObjectTorrentRequest.builder().bucket("bucketName").key("key").build();
        final CompletableFuture<GetObjectTorrentResponse> result = s3AsyncClient.getObjectTorrent(getObjectTorrentRequest, AsyncResponseTransformer.toFile(Paths.get("filename.txt")));
    }

    public void tryGetObjectTorrentToBytes() {
        final GetObjectTorrentRequest getObjectTorrentRequest = GetObjectTorrentRequest.builder().bucket("bucketName").key("key").build();
        final CompletableFuture<ResponseBytes<GetObjectTorrentResponse>> result = s3AsyncClient.getObjectTorrent(getObjectTorrentRequest, AsyncResponseTransformer.toBytes());
    }

    public void tryGetObjectTorrent1() {
        final CompletableFuture<GetObjectTorrentResponse> result = s3AsyncClient.getObjectTorrent(Object::toString, AsyncResponseTransformer.toFile(Paths.get("filename.txt")));
    }

    public void tryGetObjectTorrent1ToBytes() {
        final CompletableFuture<ResponseBytes<GetObjectTorrentResponse>> result = s3AsyncClient.getObjectTorrent(Object::toString, AsyncResponseTransformer.toBytes());
    }

    public void tryGetObjectTorrent2() {
        final GetObjectTorrentRequest getObjectTorrentRequest = GetObjectTorrentRequest.builder().bucket("bucketName").key("key").build();
        final Path destinationPath = Paths.get("filename.txt");
        final CompletableFuture<GetObjectTorrentResponse> result = s3AsyncClient.getObjectTorrent(getObjectTorrentRequest, destinationPath);
    }

    public void tryGetObjectTorrent3() {
        final Path destinationPath = Paths.get("filename.txt");
        final CompletableFuture<GetObjectTorrentResponse> result = s3AsyncClient.getObjectTorrent(Object::toString, destinationPath);
    }

    public void tryGetPublicAccessBlock() {
        final GetPublicAccessBlockRequest getPublicAccessBlockRequest = GetPublicAccessBlockRequest.builder().bucket("bucketName").build();
        final CompletableFuture<GetPublicAccessBlockResponse> result = s3AsyncClient.getPublicAccessBlock(getPublicAccessBlockRequest);
    }

    public void tryGetPublicAccessBlock1() {
        final CompletableFuture<GetPublicAccessBlockResponse> result = s3AsyncClient.getPublicAccessBlock(Object::toString);
    }

    public void tryHeadBucket() {
        final HeadBucketRequest headBucketRequest = HeadBucketRequest.builder().bucket("bucketName").build();
        final CompletableFuture<HeadBucketResponse> result = s3AsyncClient.headBucket(headBucketRequest);
    }

    public void tryHeadBucket1() {
        final CompletableFuture<HeadBucketResponse> result = s3AsyncClient.headBucket(Object::toString);
    }

    public void tryHeadObject() {
        final HeadObjectRequest headObjectRequest = HeadObjectRequest.builder().bucket("bucketName").key("key").build();
        final CompletableFuture<HeadObjectResponse> result = s3AsyncClient.headObject(headObjectRequest);
    }

    public void tryHeadObject1() {
        final CompletableFuture<HeadObjectResponse> result = s3AsyncClient.headObject(Object::toString);
    }

    public void tryListBucketAnalyticsConfigurations() {
        final ListBucketAnalyticsConfigurationsRequest listBucketAnalyticsConfigurationsRequest = ListBucketAnalyticsConfigurationsRequest.builder().bucket("bucketName").build();
        final CompletableFuture<ListBucketAnalyticsConfigurationsResponse> result = s3AsyncClient.listBucketAnalyticsConfigurations(listBucketAnalyticsConfigurationsRequest);
    }

    public void tryListBucketAnalyticsConfigurations1() {
        final CompletableFuture<ListBucketAnalyticsConfigurationsResponse> result = s3AsyncClient.listBucketAnalyticsConfigurations(Object::toString);
    }

    public void tryListBucketInventoryConfigurations() {
        final ListBucketInventoryConfigurationsRequest listBucketInventoryConfigurationsRequest = ListBucketInventoryConfigurationsRequest.builder().bucket("bucketName").build();
        final CompletableFuture<ListBucketInventoryConfigurationsResponse> result = s3AsyncClient.listBucketInventoryConfigurations(listBucketInventoryConfigurationsRequest);
    }

    public void tryListBucketInventoryConfigurations1() {
        final CompletableFuture<ListBucketInventoryConfigurationsResponse> result = s3AsyncClient.listBucketInventoryConfigurations(Object::toString);
    }

    public void tryListBucketMetricsConfigurations() {
        final ListBucketMetricsConfigurationsRequest listBucketMetricsConfigurationsRequest = ListBucketMetricsConfigurationsRequest.builder().bucket("bucketName").build();
        final CompletableFuture<ListBucketMetricsConfigurationsResponse> result = s3AsyncClient.listBucketMetricsConfigurations(listBucketMetricsConfigurationsRequest);
    }

    public void tryListBucketMetricsConfigurations1() {
        final CompletableFuture<ListBucketMetricsConfigurationsResponse> result = s3AsyncClient.listBucketMetricsConfigurations(Object::toString);
    }

    public void tryListBuckets() {
        final ListBucketsRequest listBucketsRequest = ListBucketsRequest.builder().build();
        final CompletableFuture<ListBucketsResponse> result = s3AsyncClient.listBuckets(listBucketsRequest);
    }

    public void tryListBuckets1() {
        final CompletableFuture<ListBucketsResponse> result = s3AsyncClient.listBuckets(Object::toString);
    }

    public void tryListBuckets2() {
        final CompletableFuture<ListBucketsResponse> result = s3AsyncClient.listBuckets();
    }

    public void tryListMultipartUploads() {
        final ListMultipartUploadsRequest listMultipartUploadsRequest = ListMultipartUploadsRequest.builder().bucket("bucketName").prefix("prefix").build();
        final CompletableFuture<ListMultipartUploadsResponse> result = s3AsyncClient.listMultipartUploads(listMultipartUploadsRequest);
    }

    public void tryListMultipartUploads1() {
        final CompletableFuture<ListMultipartUploadsResponse> result = s3AsyncClient.listMultipartUploads(Object::toString);
    }

    public void tryListMultipartUploadsPaginator() {
        final ListMultipartUploadsRequest listMultipartUploadsRequest = ListMultipartUploadsRequest.builder().bucket("bucketName").prefix("prefix").build();
        final ListMultipartUploadsPublisher result = s3AsyncClient.listMultipartUploadsPaginator(listMultipartUploadsRequest);
    }

    public void tryListMultipartUploadsPaginator1() {
        final ListMultipartUploadsPublisher result = s3AsyncClient.listMultipartUploadsPaginator(Object::toString);
    }

    public void tryListObjectVersions() {
        final ListObjectVersionsRequest listObjectVersionsRequest = ListObjectVersionsRequest.builder().bucket("bucketName").prefix("prefix").build();
        final CompletableFuture<ListObjectVersionsResponse> result = s3AsyncClient.listObjectVersions(listObjectVersionsRequest);
    }

    public void tryListObjectVersions1() {
        final CompletableFuture<ListObjectVersionsResponse> result = s3AsyncClient.listObjectVersions(Object::toString);
    }

    public void tryListObjectVersionsPaginator() {
        final ListObjectVersionsRequest listObjectVersionsRequest = ListObjectVersionsRequest.builder().bucket("bucketName").prefix("prefix").build();
        final ListObjectVersionsPublisher result = s3AsyncClient.listObjectVersionsPaginator(listObjectVersionsRequest);
    }

    public void tryListObjectVersionsPaginator1() {
        final ListObjectVersionsPublisher result = s3AsyncClient.listObjectVersionsPaginator(Object::toString);
    }

    public void tryListObjects() {
        final ListObjectsRequest listObjectsRequest = ListObjectsRequest.builder().bucket("bucketName").prefix("prefix").build();
        final CompletableFuture<ListObjectsResponse> result = s3AsyncClient.listObjects(listObjectsRequest);
    }

    public void tryListObjects1() {
        final CompletableFuture<ListObjectsResponse> result = s3AsyncClient.listObjects(Object::toString);
    }

    public void tryListObjectsV2() {
        final ListObjectsV2Request listObjectsV2Request = ListObjectsV2Request.builder().bucket("bucketName").prefix("prefix").build();
        final CompletableFuture<ListObjectsV2Response> result = s3AsyncClient.listObjectsV2(listObjectsV2Request);
    }

    public void tryListObjectsV21() {
        final CompletableFuture<ListObjectsV2Response> result = s3AsyncClient.listObjectsV2(Object::toString);
    }

    public void tryListObjectsV2Paginator() {
        final ListObjectsV2Request listObjectsV2Request = ListObjectsV2Request.builder().bucket("bucketName").prefix("prefix").build();
        final ListObjectsV2Publisher result = s3AsyncClient.listObjectsV2Paginator(listObjectsV2Request);
    }

    public void tryListObjectsV2Paginator1() {
        final ListObjectsV2Publisher result = s3AsyncClient.listObjectsV2Paginator(Object::toString);
    }

    public void tryListParts() {
        final ListPartsRequest listPartsRequest = ListPartsRequest.builder().bucket("bucketName").key("key").build();
        final CompletableFuture<ListPartsResponse> result = s3AsyncClient.listParts(listPartsRequest);
    }

    public void tryListParts1() {
        final CompletableFuture<ListPartsResponse> result = s3AsyncClient.listParts(Object::toString);
    }

    public void tryListPartsPaginator() {
        final ListPartsRequest listPartsRequest = ListPartsRequest.builder().bucket("bucketName").key("key").build();
        final ListPartsPublisher result = s3AsyncClient.listPartsPaginator(listPartsRequest);
    }

    public void tryListPartsPaginator1() {
        final ListPartsPublisher result = s3AsyncClient.listPartsPaginator(Object::toString);
    }

    public void tryPutBucketAccelerateConfiguration() {
        final PutBucketAccelerateConfigurationRequest putBucketAccelerateConfigurationRequest = PutBucketAccelerateConfigurationRequest.builder().bucket("bucketName").build();
        final CompletableFuture<PutBucketAccelerateConfigurationResponse> result = s3AsyncClient.putBucketAccelerateConfiguration(putBucketAccelerateConfigurationRequest);
    }

    public void tryPutBucketAccelerateConfiguration1() {
        final CompletableFuture<PutBucketAccelerateConfigurationResponse> result = s3AsyncClient.putBucketAccelerateConfiguration(Object::toString);
    }

    public void tryPutBucketAcl() {
        final PutBucketAclRequest putBucketAclRequest = PutBucketAclRequest.builder().bucket("bucketName").build();
        final CompletableFuture<PutBucketAclResponse> result = s3AsyncClient.putBucketAcl(putBucketAclRequest);
    }

    public void tryPutBucketAcl1() {
        final CompletableFuture<PutBucketAclResponse> result = s3AsyncClient.putBucketAcl(Object::toString);
    }

    public void tryPutBucketAnalyticsConfiguration() {
        final PutBucketAnalyticsConfigurationRequest putBucketAnalyticsConfigurationRequest = PutBucketAnalyticsConfigurationRequest.builder().bucket("bucketName").build();
        final CompletableFuture<PutBucketAnalyticsConfigurationResponse> result = s3AsyncClient.putBucketAnalyticsConfiguration(putBucketAnalyticsConfigurationRequest);
    }

    public void tryPutBucketAnalyticsConfiguration1() {
        final CompletableFuture<PutBucketAnalyticsConfigurationResponse> result = s3AsyncClient.putBucketAnalyticsConfiguration(Object::toString);
    }

    public void tryPutBucketCors() {
        final PutBucketCorsRequest putBucketCorsRequest = PutBucketCorsRequest.builder().bucket("bucketName").build();
        final CompletableFuture<PutBucketCorsResponse> result = s3AsyncClient.putBucketCors(putBucketCorsRequest);
    }

    public void tryPutBucketCors1() {
        final CompletableFuture<PutBucketCorsResponse> result = s3AsyncClient.putBucketCors(Object::toString);
    }

    public void tryPutBucketEncryption() {
        final PutBucketEncryptionRequest putBucketEncryptionRequest = PutBucketEncryptionRequest.builder().bucket("bucketName").build();
        final CompletableFuture<PutBucketEncryptionResponse> result = s3AsyncClient.putBucketEncryption(putBucketEncryptionRequest);
    }

    public void tryPutBucketEncryption1() {
        final CompletableFuture<PutBucketEncryptionResponse> result = s3AsyncClient.putBucketEncryption(Object::toString);
    }

    public void tryPutBucketInventoryConfiguration() {
        final PutBucketInventoryConfigurationRequest putBucketInventoryConfigurationRequest = PutBucketInventoryConfigurationRequest.builder().bucket("bucketName").build();
        final CompletableFuture<PutBucketInventoryConfigurationResponse> result = s3AsyncClient.putBucketInventoryConfiguration(putBucketInventoryConfigurationRequest);
    }

    public void tryPutBucketInventoryConfiguration1() {
        final CompletableFuture<PutBucketInventoryConfigurationResponse> result = s3AsyncClient.putBucketInventoryConfiguration(Object::toString);
    }

    public void tryPutBucketLifecycleConfiguration() {
        final PutBucketLifecycleConfigurationRequest putBucketLifecycleConfigurationRequest = PutBucketLifecycleConfigurationRequest.builder().bucket("bucketName").build();
        final CompletableFuture<PutBucketLifecycleConfigurationResponse> result = s3AsyncClient.putBucketLifecycleConfiguration(putBucketLifecycleConfigurationRequest);
    }

    public void tryPutBucketLifecycleConfiguration1() {
        final CompletableFuture<PutBucketLifecycleConfigurationResponse> result = s3AsyncClient.putBucketLifecycleConfiguration(Object::toString);
    }

    public void tryPutBucketLogging() {
        final PutBucketLoggingRequest putBucketLoggingRequest = PutBucketLoggingRequest.builder().bucket("bucketName").build();
        final CompletableFuture<PutBucketLoggingResponse> result = s3AsyncClient.putBucketLogging(putBucketLoggingRequest);
    }

    public void tryPutBucketLogging1() {
        final CompletableFuture<PutBucketLoggingResponse> result = s3AsyncClient.putBucketLogging(Object::toString);
    }

    public void tryPutBucketMetricsConfiguration() {
        final PutBucketMetricsConfigurationRequest putBucketMetricsConfigurationRequest = PutBucketMetricsConfigurationRequest.builder().bucket("bucketName").build();
        final CompletableFuture<PutBucketMetricsConfigurationResponse> result = s3AsyncClient.putBucketMetricsConfiguration(putBucketMetricsConfigurationRequest);
    }

    public void tryPutBucketMetricsConfiguration1() {
        final CompletableFuture<PutBucketMetricsConfigurationResponse> result = s3AsyncClient.putBucketMetricsConfiguration(Object::toString);
    }

    public void tryPutBucketNotificationConfiguration() {
        final PutBucketNotificationConfigurationRequest putBucketNotificationConfigurationRequest = PutBucketNotificationConfigurationRequest.builder().bucket("bucketName").build();
        final CompletableFuture<PutBucketNotificationConfigurationResponse> result = s3AsyncClient.putBucketNotificationConfiguration(putBucketNotificationConfigurationRequest);
    }

    public void tryPutBucketNotificationConfiguration1() {
        final CompletableFuture<PutBucketNotificationConfigurationResponse> result = s3AsyncClient.putBucketNotificationConfiguration(Object::toString);
    }

    public void tryPutBucketPolicy() {
        final PutBucketPolicyRequest putBucketPolicyRequest = PutBucketPolicyRequest.builder().bucket("bucketName").build();
        final CompletableFuture<PutBucketPolicyResponse> result = s3AsyncClient.putBucketPolicy(putBucketPolicyRequest);
    }

    public void tryPutBucketPolicy1() {
        final CompletableFuture<PutBucketPolicyResponse> result = s3AsyncClient.putBucketPolicy(Object::toString);
    }

    public void tryPutBucketReplication() {
        final PutBucketReplicationRequest putBucketReplicationRequest = PutBucketReplicationRequest.builder().bucket("bucketName").build();
        final CompletableFuture<PutBucketReplicationResponse> result = s3AsyncClient.putBucketReplication(putBucketReplicationRequest);
    }

    public void tryPutBucketReplication1() {
        final CompletableFuture<PutBucketReplicationResponse> result = s3AsyncClient.putBucketReplication(Object::toString);
    }

    public void tryPutBucketRequestPayment() {
        final PutBucketRequestPaymentRequest putBucketRequestPaymentRequest = PutBucketRequestPaymentRequest.builder().bucket("bucketName").build();
        final CompletableFuture<PutBucketRequestPaymentResponse> result = s3AsyncClient.putBucketRequestPayment(putBucketRequestPaymentRequest);
    }

    public void tryPutBucketRequestPayment1() {
        final CompletableFuture<PutBucketRequestPaymentResponse> result = s3AsyncClient.putBucketRequestPayment(Object::toString);
    }

    public void tryPutBucketTagging() {
        final PutBucketTaggingRequest putBucketTaggingRequest = PutBucketTaggingRequest.builder().bucket("bucketName").build();
        final CompletableFuture<PutBucketTaggingResponse> result = s3AsyncClient.putBucketTagging(putBucketTaggingRequest);
    }

    public void tryPutBucketTagging1() {
        final CompletableFuture<PutBucketTaggingResponse> result = s3AsyncClient.putBucketTagging(Object::toString);
    }

    public void tryPutBucketVersioning() {
        final PutBucketVersioningRequest putBucketVersioningRequest = PutBucketVersioningRequest.builder().bucket("bucketName").build();
        final CompletableFuture<PutBucketVersioningResponse> result = s3AsyncClient.putBucketVersioning(putBucketVersioningRequest);
    }

    public void tryPutBucketVersioning1() {
        final CompletableFuture<PutBucketVersioningResponse> result = s3AsyncClient.putBucketVersioning(Object::toString);
    }

    public void tryPutBucketWebsite() {
        final PutBucketWebsiteRequest putBucketWebsiteRequest = PutBucketWebsiteRequest.builder().bucket("bucketName").build();
        final CompletableFuture<PutBucketWebsiteResponse> result = s3AsyncClient.putBucketWebsite(putBucketWebsiteRequest);
    }

    public void tryPutBucketWebsite1() {
        final CompletableFuture<PutBucketWebsiteResponse> result = s3AsyncClient.putBucketWebsite(Object::toString);
    }

    public void tryPutObject() {
        final PutObjectRequest putObjectRequest = PutObjectRequest.builder().bucket("bucketName").key("key").build();
        final AsyncRequestBody requestBody = AsyncRequestBody.fromFile(Paths.get("filename.txt"));
        final CompletableFuture<PutObjectResponse> result = s3AsyncClient.putObject(putObjectRequest, requestBody);
    }

    public void tryPutObject1() {
        final AsyncRequestBody requestBody = AsyncRequestBody.fromFile(Paths.get("filename.txt"));
        final CompletableFuture<PutObjectResponse> result = s3AsyncClient.putObject(Object::toString, requestBody);
    }

    public void tryPutObject2() {
        final PutObjectRequest putObjectRequest = PutObjectRequest.builder().bucket("bucketName").key("key").build();
        final Path sourcePath = Paths.get("filename.txt");
        final CompletableFuture<PutObjectResponse> result = s3AsyncClient.putObject(putObjectRequest, sourcePath);
    }

    public void tryPutObject3() {
        final Path sourcePath = Paths.get("filename.txt");
        final CompletableFuture<PutObjectResponse> result = s3AsyncClient.putObject(Object::toString, sourcePath);
    }

    public void tryPutObjectAcl() {
        final PutObjectAclRequest putObjectAclRequest = PutObjectAclRequest.builder().bucket("bucketName").key("key").build();
        final CompletableFuture<PutObjectAclResponse> result = s3AsyncClient.putObjectAcl(putObjectAclRequest);
    }

    public void tryPutObjectAcl1() {
        final CompletableFuture<PutObjectAclResponse> result = s3AsyncClient.putObjectAcl(Object::toString);
    }

    public void tryPutObjectLegalHold() {
        final PutObjectLegalHoldRequest putObjectLegalHoldRequest = PutObjectLegalHoldRequest.builder().bucket("bucketName").key("key").build();
        final CompletableFuture<PutObjectLegalHoldResponse> result = s3AsyncClient.putObjectLegalHold(putObjectLegalHoldRequest);
    }

    public void tryPutObjectLegalHold1() {
        final CompletableFuture<PutObjectLegalHoldResponse> result = s3AsyncClient.putObjectLegalHold(Object::toString);
    }

    public void tryPutObjectLockConfiguration() {
        final PutObjectLockConfigurationRequest putObjectLockConfigurationRequest = PutObjectLockConfigurationRequest.builder().bucket("bucketName").build();
        final CompletableFuture<PutObjectLockConfigurationResponse> result = s3AsyncClient.putObjectLockConfiguration(putObjectLockConfigurationRequest);
    }

    public void tryPutObjectLockConfiguration1() {
        final CompletableFuture<PutObjectLockConfigurationResponse> result = s3AsyncClient.putObjectLockConfiguration(Object::toString);
    }

    public void tryPutObjectRetention() {
        final PutObjectRetentionRequest putObjectRetentionRequest = PutObjectRetentionRequest.builder().bucket("bucketName").key("key").build();
        final CompletableFuture<PutObjectRetentionResponse> result = s3AsyncClient.putObjectRetention(putObjectRetentionRequest);
    }

    public void tryPutObjectRetention1() {
        final CompletableFuture<PutObjectRetentionResponse> result = s3AsyncClient.putObjectRetention(Object::toString);
    }

    public void tryPutObjectTagging() {
        final PutObjectTaggingRequest putObjectTaggingRequest = PutObjectTaggingRequest.builder().bucket("bucketName").key("key").build();
        final CompletableFuture<PutObjectTaggingResponse> result = s3AsyncClient.putObjectTagging(putObjectTaggingRequest);
    }

    public void tryPutObjectTagging1() {
        final CompletableFuture<PutObjectTaggingResponse> result = s3AsyncClient.putObjectTagging(Object::toString);
    }

    public void tryPutPublicAccessBlock() {
        final PutPublicAccessBlockRequest putPublicAccessBlockRequest = PutPublicAccessBlockRequest.builder().bucket("bucketName").build();
        final CompletableFuture<PutPublicAccessBlockResponse> result = s3AsyncClient.putPublicAccessBlock(putPublicAccessBlockRequest);
    }

    public void tryPutPublicAccessBlock1() {
        final CompletableFuture<PutPublicAccessBlockResponse> result = s3AsyncClient.putPublicAccessBlock(Object::toString);
    }

    public void tryRestoreObject() {
        final RestoreObjectRequest restoreObjectRequest = RestoreObjectRequest.builder().bucket("bucketName").key("key").build();
        final CompletableFuture<RestoreObjectResponse> result = s3AsyncClient.restoreObject(restoreObjectRequest);
    }

    public void tryRestoreObject1() {
        final CompletableFuture<RestoreObjectResponse> result = s3AsyncClient.restoreObject(Object::toString);
    }

    public void tryUploadPart() {
        final UploadPartRequest uploadPartRequest = UploadPartRequest.builder().bucket("bucketName").key("key").build();
        final AsyncRequestBody requestBody = AsyncRequestBody.fromFile(Paths.get("filename.txt"));
        final CompletableFuture<UploadPartResponse> result = s3AsyncClient.uploadPart(uploadPartRequest, requestBody);
    }

    public void tryUploadPart1() {
        final AsyncRequestBody requestBody = AsyncRequestBody.fromFile(Paths.get("filename.txt"));
        final CompletableFuture<UploadPartResponse> result = s3AsyncClient.uploadPart(Object::toString, requestBody);
    }

    public void tryUploadPart2() {
        final UploadPartRequest uploadPartRequest = UploadPartRequest.builder().bucket("bucketName").key("key").build();
        final Path sourcePath = Paths.get("filename.txt");
        final CompletableFuture<UploadPartResponse> result = s3AsyncClient.uploadPart(uploadPartRequest, sourcePath);
    }

    public void tryUploadPart3() {
        final Path sourcePath = Paths.get("filename.txt");
        final CompletableFuture<UploadPartResponse> result = s3AsyncClient.uploadPart(Object::toString, sourcePath);
    }

    public void tryUploadPartCopy() {
        final UploadPartCopyRequest uploadPartCopyRequest = UploadPartCopyRequest.builder().bucket("bucketName").key("key").build();
        final CompletableFuture<UploadPartCopyResponse> result = s3AsyncClient.uploadPartCopy(uploadPartCopyRequest);
    }

    public void tryUploadPartCopy1() {
        final CompletableFuture<UploadPartCopyResponse> result = s3AsyncClient.uploadPartCopy(Object::toString);
    }

    public void tryUtilities() {
        final S3Utilities result = s3AsyncClient.utilities();
    }
}
