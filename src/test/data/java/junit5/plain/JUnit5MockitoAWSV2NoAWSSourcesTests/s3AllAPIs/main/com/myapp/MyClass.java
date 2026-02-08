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
import software.amazon.awssdk.core.ResponseInputStream;
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
import software.amazon.awssdk.services.s3.waiters.S3Waiter;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Map;

public class MyClass {

    private S3Client s3Client;

    public MyClass(final S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public void tryAbortMultipartUpload1() {
        final AbortMultipartUploadRequest abortMultipartUploadRequest = AbortMultipartUploadRequest.builder()
                .bucket("bucket")
                .key("key")
                .uploadId("uploadId")
                .build();
        final AbortMultipartUploadResponse varThatUsesProps = AbortMultipartUploadResponse.builder().build();
        final AbortMultipartUploadResponse result = s3Client.abortMultipartUpload(abortMultipartUploadRequest);
    }

    public void tryAbortMultipartUpload2() {
        final AbortMultipartUploadResponse varThatUsesProps = AbortMultipartUploadResponse.builder().build();
        final AbortMultipartUploadResponse result = s3Client.abortMultipartUpload(Object::toString);
    }

    public void tryCompleteMultipartUpload1() {
        final CompleteMultipartUploadRequest completeMultipartUploadRequest = CompleteMultipartUploadRequest.builder()
                .bucket("bucket")
                .key("key")
                .multipartUpload(CompletedMultipartUpload.builder()
                        .parts(CompletedPart.builder()
                                .eTag("eTag")
                                .partNumber(0)
                                .build())
                        .build())
                .uploadId("uploadId")
                .build();
        final CompleteMultipartUploadResponse varThatUsesProps = CompleteMultipartUploadResponse.builder()
                .location("location")
                .bucket("bucket")
                .key("key")
                .eTag("eTag")
                .build();
        final CompleteMultipartUploadResponse result = s3Client.completeMultipartUpload(completeMultipartUploadRequest);
    }

    public void tryCompleteMultipartUpload2() {
        final CompleteMultipartUploadResponse varThatUsesProps = CompleteMultipartUploadResponse.builder()
                .location("location")
                .bucket("bucket")
                .key("key")
                .eTag("eTag")
                .build();
        final CompleteMultipartUploadResponse result = s3Client.completeMultipartUpload(Object::toString);
    }

    public void tryCopyObject1() {
        final CopyObjectRequest copyObjectRequest = CopyObjectRequest.builder()
                .copySource("encodedUrl")
                .destinationBucket("toBucket")
                .destinationKey("objectKey")
                .build();
        final CopyObjectResponse varThatUsesProps = CopyObjectResponse.builder()
                .copyObjectResult(CopyObjectResult.builder()
                        .eTag("eTag")
                        .lastModified(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                        .build())
                .build();
        final CopyObjectResponse result = s3Client.copyObject(copyObjectRequest);
    }

    public void tryCopyObject2() {
        final CopyObjectResponse varThatUsesProps = CopyObjectResponse.builder()
                .copyObjectResult(CopyObjectResult.builder()
                        .eTag("eTag")
                        .lastModified(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                        .build())
                .build();
        final CopyObjectResponse result = s3Client.copyObject(Object::toString);
    }

    public void tryCreateBucket1() {
        final CreateBucketRequest createBucketRequest = CreateBucketRequest.builder()
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
                .build();
        final CreateBucketResponse varThatUsesProps = CreateBucketResponse.builder()
                .location("location")
                .build();
        final CreateBucketResponse result = s3Client.createBucket(createBucketRequest);
    }

    public void tryCreateBucket2() {
        final CreateBucketResponse varThatUsesProps = CreateBucketResponse.builder()
                .location("location")
                .build();
        final CreateBucketResponse result = s3Client.createBucket(Object::toString);
    }

    public void tryCreateMultipartUpload1() {
        final CreateMultipartUploadRequest createMultipartUploadRequest = CreateMultipartUploadRequest.builder()
                .bucket("bucket")
                .key("key")
                .build();
        final CreateMultipartUploadResponse varThatUsesProps = CreateMultipartUploadResponse.builder()
                .bucket("bucket")
                .key("key")
                .uploadId("uploadId")
                .build();
        final CreateMultipartUploadResponse result = s3Client.createMultipartUpload(createMultipartUploadRequest);
    }

    public void tryCreateMultipartUpload2() {
        final CreateMultipartUploadResponse varThatUsesProps = CreateMultipartUploadResponse.builder()
                .bucket("bucket")
                .key("key")
                .uploadId("uploadId")
                .build();
        final CreateMultipartUploadResponse result = s3Client.createMultipartUpload(Object::toString);
    }

    public void tryDeleteBucket1() {
        final DeleteBucketRequest deleteBucketRequest = DeleteBucketRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build();
        final DeleteBucketResponse varThatUsesProps = DeleteBucketResponse.builder().build();
        final DeleteBucketResponse result = s3Client.deleteBucket(deleteBucketRequest);
    }

    public void tryDeleteBucket2() {
        final DeleteBucketResponse varThatUsesProps = DeleteBucketResponse.builder().build();
        final DeleteBucketResponse result = s3Client.deleteBucket(Object::toString);
    }

    public void tryDeleteBucketAnalyticsConfiguration1() {
        final DeleteBucketAnalyticsConfigurationRequest deleteBucketAnalyticsConfigurationRequest = DeleteBucketAnalyticsConfigurationRequest.builder()
                .bucket("bucket")
                .id("id")
                .expectedBucketOwner("expectedBucketOwner")
                .build();
        final DeleteBucketAnalyticsConfigurationResponse varThatUsesProps = DeleteBucketAnalyticsConfigurationResponse.builder().build();
        final DeleteBucketAnalyticsConfigurationResponse result = s3Client.deleteBucketAnalyticsConfiguration(
                deleteBucketAnalyticsConfigurationRequest);
    }

    public void tryDeleteBucketAnalyticsConfiguration2() {
        final DeleteBucketAnalyticsConfigurationResponse varThatUsesProps = DeleteBucketAnalyticsConfigurationResponse.builder().build();
        final DeleteBucketAnalyticsConfigurationResponse result = s3Client.deleteBucketAnalyticsConfiguration(
                Object::toString);
    }

    public void tryDeleteBucketCors1() {
        final DeleteBucketCorsRequest deleteBucketCorsRequest = DeleteBucketCorsRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build();
        final DeleteBucketCorsResponse varThatUsesProps = DeleteBucketCorsResponse.builder().build();
        final DeleteBucketCorsResponse result = s3Client.deleteBucketCors(deleteBucketCorsRequest);
    }

    public void tryDeleteBucketCors2() {
        final DeleteBucketCorsResponse varThatUsesProps = DeleteBucketCorsResponse.builder().build();
        final DeleteBucketCorsResponse result = s3Client.deleteBucketCors(Object::toString);
    }

    public void tryDeleteBucketEncryption1() {
        final DeleteBucketEncryptionRequest deleteBucketEncryptionRequest = DeleteBucketEncryptionRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build();
        final DeleteBucketEncryptionResponse varThatUsesProps = DeleteBucketEncryptionResponse.builder().build();
        final DeleteBucketEncryptionResponse result = s3Client.deleteBucketEncryption(deleteBucketEncryptionRequest);
    }

    public void tryDeleteBucketEncryption2() {
        final DeleteBucketEncryptionResponse varThatUsesProps = DeleteBucketEncryptionResponse.builder().build();
        final DeleteBucketEncryptionResponse result = s3Client.deleteBucketEncryption(Object::toString);
    }

    public void tryDeleteBucketInventoryConfiguration1() {
        final DeleteBucketInventoryConfigurationRequest deleteBucketInventoryConfigurationRequest = DeleteBucketInventoryConfigurationRequest.builder()
                .bucket("bucket")
                .id("id")
                .expectedBucketOwner("expectedBucketOwner")
                .build();
        final DeleteBucketInventoryConfigurationResponse varThatUsesProps = DeleteBucketInventoryConfigurationResponse.builder().build();
        final DeleteBucketInventoryConfigurationResponse result = s3Client.deleteBucketInventoryConfiguration(
                deleteBucketInventoryConfigurationRequest);
    }

    public void tryDeleteBucketInventoryConfiguration2() {
        final DeleteBucketInventoryConfigurationResponse varThatUsesProps = DeleteBucketInventoryConfigurationResponse.builder().build();
        final DeleteBucketInventoryConfigurationResponse result = s3Client.deleteBucketInventoryConfiguration(
                Object::toString);
    }

    public void tryDeleteBucketLifecycle1() {
        final DeleteBucketLifecycleRequest deleteBucketLifecycleRequest = DeleteBucketLifecycleRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build();
        final DeleteBucketLifecycleResponse varThatUsesProps = DeleteBucketLifecycleResponse.builder().build();
        final DeleteBucketLifecycleResponse result = s3Client.deleteBucketLifecycle(deleteBucketLifecycleRequest);
    }

    public void tryDeleteBucketLifecycle2() {
        final DeleteBucketLifecycleResponse varThatUsesProps = DeleteBucketLifecycleResponse.builder().build();
        final DeleteBucketLifecycleResponse result = s3Client.deleteBucketLifecycle(Object::toString);
    }

    public void tryDeleteBucketMetricsConfiguration1() {
        final DeleteBucketMetricsConfigurationRequest deleteBucketMetricsConfigurationRequest = DeleteBucketMetricsConfigurationRequest.builder()
                .bucket("bucket")
                .id("id")
                .expectedBucketOwner("expectedBucketOwner")
                .build();
        final DeleteBucketMetricsConfigurationResponse varThatUsesProps = DeleteBucketMetricsConfigurationResponse.builder().build();
        final DeleteBucketMetricsConfigurationResponse result = s3Client.deleteBucketMetricsConfiguration(
                deleteBucketMetricsConfigurationRequest);
    }

    public void tryDeleteBucketMetricsConfiguration2() {
        final DeleteBucketMetricsConfigurationResponse varThatUsesProps = DeleteBucketMetricsConfigurationResponse.builder().build();
        final DeleteBucketMetricsConfigurationResponse result = s3Client.deleteBucketMetricsConfiguration(
                Object::toString);
    }

    public void tryDeleteBucketPolicy1() {
        final DeleteBucketPolicyRequest deleteBucketPolicyRequest = DeleteBucketPolicyRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build();
        final DeleteBucketPolicyResponse varThatUsesProps = DeleteBucketPolicyResponse.builder().build();
        final DeleteBucketPolicyResponse result = s3Client.deleteBucketPolicy(deleteBucketPolicyRequest);
    }

    public void tryDeleteBucketPolicy2() {
        final DeleteBucketPolicyResponse varThatUsesProps = DeleteBucketPolicyResponse.builder().build();
        final DeleteBucketPolicyResponse result = s3Client.deleteBucketPolicy(Object::toString);
    }

    public void tryDeleteBucketReplication1() {
        final DeleteBucketReplicationRequest deleteBucketReplicationRequest = DeleteBucketReplicationRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build();
        final DeleteBucketReplicationResponse varThatUsesProps = DeleteBucketReplicationResponse.builder().build();
        final DeleteBucketReplicationResponse result = s3Client.deleteBucketReplication(deleteBucketReplicationRequest);
    }

    public void tryDeleteBucketReplication2() {
        final DeleteBucketReplicationResponse varThatUsesProps = DeleteBucketReplicationResponse.builder().build();
        final DeleteBucketReplicationResponse result = s3Client.deleteBucketReplication(Object::toString);
    }

    public void tryDeleteBucketTagging1() {
        final DeleteBucketTaggingRequest deleteBucketTaggingRequest = DeleteBucketTaggingRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build();
        final DeleteBucketTaggingResponse varThatUsesProps = DeleteBucketTaggingResponse.builder().build();
        final DeleteBucketTaggingResponse result = s3Client.deleteBucketTagging(deleteBucketTaggingRequest);
    }

    public void tryDeleteBucketTagging2() {
        final DeleteBucketTaggingResponse varThatUsesProps = DeleteBucketTaggingResponse.builder().build();
        final DeleteBucketTaggingResponse result = s3Client.deleteBucketTagging(Object::toString);
    }

    public void tryDeleteBucketWebsite1() {
        final DeleteBucketWebsiteRequest deleteBucketWebsiteRequest = DeleteBucketWebsiteRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build();
        final DeleteBucketWebsiteResponse varThatUsesProps = DeleteBucketWebsiteResponse.builder().build();
        final DeleteBucketWebsiteResponse result = s3Client.deleteBucketWebsite(deleteBucketWebsiteRequest);
    }

    public void tryDeleteBucketWebsite2() {
        final DeleteBucketWebsiteResponse varThatUsesProps = DeleteBucketWebsiteResponse.builder().build();
        final DeleteBucketWebsiteResponse result = s3Client.deleteBucketWebsite(Object::toString);
    }

    public void tryDeleteObject1() {
        final DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .bucket("bucket")
                .key("key")
                .build();
        final DeleteObjectResponse varThatUsesProps = DeleteObjectResponse.builder().build();
        final DeleteObjectResponse result = s3Client.deleteObject(deleteObjectRequest);
    }

    public void tryDeleteObject2() {
        final DeleteObjectResponse varThatUsesProps = DeleteObjectResponse.builder().build();
        final DeleteObjectResponse result = s3Client.deleteObject(Object::toString);
    }

    public void tryDeleteObjectTagging1() {
        final DeleteObjectTaggingRequest deleteObjectTaggingRequest = DeleteObjectTaggingRequest.builder()
                .bucket("bucket")
                .key("key")
                .versionId("versionId")
                .expectedBucketOwner("expectedBucketOwner")
                .build();
        final DeleteObjectTaggingResponse varThatUsesProps = DeleteObjectTaggingResponse.builder()
                .versionId("versionId")
                .build();
        final DeleteObjectTaggingResponse result = s3Client.deleteObjectTagging(deleteObjectTaggingRequest);
    }

    public void tryDeleteObjectTagging2() {
        final DeleteObjectTaggingResponse varThatUsesProps = DeleteObjectTaggingResponse.builder()
                .versionId("versionId")
                .build();
        final DeleteObjectTaggingResponse result = s3Client.deleteObjectTagging(Object::toString);
    }

    public void tryDeleteObjects1() {
        final DeleteObjectsRequest deleteObjectsRequest = DeleteObjectsRequest.builder()
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
                .build();
        final DeleteObjectsResponse varThatUsesProps = DeleteObjectsResponse.builder()
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
        final DeleteObjectsResponse result = s3Client.deleteObjects(deleteObjectsRequest);
    }

    public void tryDeleteObjects2() {
        final DeleteObjectsResponse varThatUsesProps = DeleteObjectsResponse.builder()
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
        final DeleteObjectsResponse result = s3Client.deleteObjects(Object::toString);
    }

    public void tryDeletePublicAccessBlock1() {
        final DeletePublicAccessBlockRequest deletePublicAccessBlockRequest = DeletePublicAccessBlockRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build();
        final DeletePublicAccessBlockResponse varThatUsesProps = DeletePublicAccessBlockResponse.builder().build();
        final DeletePublicAccessBlockResponse result = s3Client.deletePublicAccessBlock(deletePublicAccessBlockRequest);
    }

    public void tryDeletePublicAccessBlock2() {
        final DeletePublicAccessBlockResponse varThatUsesProps = DeletePublicAccessBlockResponse.builder().build();
        final DeletePublicAccessBlockResponse result = s3Client.deletePublicAccessBlock(Object::toString);
    }

    public void tryGetBucketAccelerateConfiguration1() {
        final GetBucketAccelerateConfigurationRequest getBucketAccelerateConfigurationRequest = GetBucketAccelerateConfigurationRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build();
        final GetBucketAccelerateConfigurationResponse varThatUsesProps = GetBucketAccelerateConfigurationResponse.builder()
                .status(BucketAccelerateStatus.ENABLED)
                .build();
        final GetBucketAccelerateConfigurationResponse result = s3Client.getBucketAccelerateConfiguration(
                getBucketAccelerateConfigurationRequest);
    }

    public void tryGetBucketAccelerateConfiguration2() {
        final GetBucketAccelerateConfigurationResponse varThatUsesProps = GetBucketAccelerateConfigurationResponse.builder()
                .status(BucketAccelerateStatus.ENABLED)
                .build();
        final GetBucketAccelerateConfigurationResponse result = s3Client.getBucketAccelerateConfiguration(
                Object::toString);
    }

    public void tryGetBucketAcl1() {
        final GetBucketAclRequest getBucketAclRequest = GetBucketAclRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build();
        final GetBucketAclResponse varThatUsesProps = GetBucketAclResponse.builder()
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
        final GetBucketAclResponse result = s3Client.getBucketAcl(getBucketAclRequest);
    }

    public void tryGetBucketAcl2() {
        final GetBucketAclResponse varThatUsesProps = GetBucketAclResponse.builder()
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
        final GetBucketAclResponse result = s3Client.getBucketAcl(Object::toString);
    }

    public void tryGetBucketAnalyticsConfiguration1() {
        final GetBucketAnalyticsConfigurationRequest getBucketAnalyticsConfigurationRequest = GetBucketAnalyticsConfigurationRequest.builder()
                .bucket("bucket")
                .id("id")
                .expectedBucketOwner("expectedBucketOwner")
                .build();
        final GetBucketAnalyticsConfigurationResponse varThatUsesProps = GetBucketAnalyticsConfigurationResponse.builder()
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
        final GetBucketAnalyticsConfigurationResponse result = s3Client.getBucketAnalyticsConfiguration(
                getBucketAnalyticsConfigurationRequest);
    }

    public void tryGetBucketAnalyticsConfiguration2() {
        final GetBucketAnalyticsConfigurationResponse varThatUsesProps = GetBucketAnalyticsConfigurationResponse.builder()
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
        final GetBucketAnalyticsConfigurationResponse result = s3Client.getBucketAnalyticsConfiguration(
                Object::toString);
    }

    public void tryGetBucketCors1() {
        final GetBucketCorsRequest getBucketCorsRequest = GetBucketCorsRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build();
        final GetBucketCorsResponse varThatUsesProps = GetBucketCorsResponse.builder()
                .corsRules(CORSRule.builder()
                        .allowedHeaders("allowedHeaders")
                        .allowedMethods("allowedMethods")
                        .allowedOrigins("allowedOrigins")
                        .exposeHeaders("exposeHeaders")
                        .maxAgeSeconds(0)
                        .build())
                .build();
        final GetBucketCorsResponse result = s3Client.getBucketCors(getBucketCorsRequest);
    }

    public void tryGetBucketCors2() {
        final GetBucketCorsResponse varThatUsesProps = GetBucketCorsResponse.builder()
                .corsRules(CORSRule.builder()
                        .allowedHeaders("allowedHeaders")
                        .allowedMethods("allowedMethods")
                        .allowedOrigins("allowedOrigins")
                        .exposeHeaders("exposeHeaders")
                        .maxAgeSeconds(0)
                        .build())
                .build();
        final GetBucketCorsResponse result = s3Client.getBucketCors(Object::toString);
    }

    public void tryGetBucketEncryption1() {
        final GetBucketEncryptionRequest getBucketEncryptionRequest = GetBucketEncryptionRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build();
        final GetBucketEncryptionResponse varThatUsesProps = GetBucketEncryptionResponse.builder()
                .serverSideEncryptionConfiguration(ServerSideEncryptionConfiguration.builder()
                        .rules(ServerSideEncryptionRule.builder()
                                .applyServerSideEncryptionByDefault(ServerSideEncryptionByDefault.builder()
                                        .sseAlgorithm(ServerSideEncryption.AES256)
                                        .kmsMasterKeyID("kmsMasterKeyID")
                                        .build())
                                .build())
                        .build())
                .build();
        final GetBucketEncryptionResponse result = s3Client.getBucketEncryption(getBucketEncryptionRequest);
    }

    public void tryGetBucketEncryption2() {
        final GetBucketEncryptionResponse varThatUsesProps = GetBucketEncryptionResponse.builder()
                .serverSideEncryptionConfiguration(ServerSideEncryptionConfiguration.builder()
                        .rules(ServerSideEncryptionRule.builder()
                                .applyServerSideEncryptionByDefault(ServerSideEncryptionByDefault.builder()
                                        .sseAlgorithm(ServerSideEncryption.AES256)
                                        .kmsMasterKeyID("kmsMasterKeyID")
                                        .build())
                                .build())
                        .build())
                .build();
        final GetBucketEncryptionResponse result = s3Client.getBucketEncryption(Object::toString);
    }

    public void tryGetBucketInventoryConfiguration1() {
        final GetBucketInventoryConfigurationRequest getBucketInventoryConfigurationRequest = GetBucketInventoryConfigurationRequest.builder()
                .bucket("bucket")
                .id("id")
                .expectedBucketOwner("expectedBucketOwner")
                .build();
        final GetBucketInventoryConfigurationResponse varThatUsesProps = GetBucketInventoryConfigurationResponse.builder()
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
        final GetBucketInventoryConfigurationResponse result = s3Client.getBucketInventoryConfiguration(
                getBucketInventoryConfigurationRequest);
    }

    public void tryGetBucketInventoryConfiguration2() {
        final GetBucketInventoryConfigurationResponse varThatUsesProps = GetBucketInventoryConfigurationResponse.builder()
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
        final GetBucketInventoryConfigurationResponse result = s3Client.getBucketInventoryConfiguration(
                Object::toString);
    }

    public void tryGetBucketLifecycleConfiguration1() {
        final GetBucketLifecycleConfigurationRequest getBucketLifecycleConfigurationRequest = GetBucketLifecycleConfigurationRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build();
        final GetBucketLifecycleConfigurationResponse varThatUsesProps = GetBucketLifecycleConfigurationResponse.builder()
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
        final GetBucketLifecycleConfigurationResponse result = s3Client.getBucketLifecycleConfiguration(
                getBucketLifecycleConfigurationRequest);
    }

    public void tryGetBucketLifecycleConfiguration2() {
        final GetBucketLifecycleConfigurationResponse varThatUsesProps = GetBucketLifecycleConfigurationResponse.builder()
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
        final GetBucketLifecycleConfigurationResponse result = s3Client.getBucketLifecycleConfiguration(
                Object::toString);
    }

    public void tryGetBucketLocation1() {
        final GetBucketLocationRequest getBucketLocationRequest = GetBucketLocationRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build();
        final GetBucketLocationResponse varThatUsesProps = GetBucketLocationResponse.builder()
                .locationConstraint(BucketLocationConstraint.US_EAST_2)
                .build();
        final GetBucketLocationResponse result = s3Client.getBucketLocation(getBucketLocationRequest);
    }

    public void tryGetBucketLocation2() {
        final GetBucketLocationResponse varThatUsesProps = GetBucketLocationResponse.builder()
                .locationConstraint(BucketLocationConstraint.US_EAST_2)
                .build();
        final GetBucketLocationResponse result = s3Client.getBucketLocation(Object::toString);
    }

    public void tryGetBucketLogging1() {
        final GetBucketLoggingRequest getBucketLoggingRequest = GetBucketLoggingRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build();
        final GetBucketLoggingResponse varThatUsesProps = GetBucketLoggingResponse.builder()
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
        final GetBucketLoggingResponse result = s3Client.getBucketLogging(getBucketLoggingRequest);
    }

    public void tryGetBucketLogging2() {
        final GetBucketLoggingResponse varThatUsesProps = GetBucketLoggingResponse.builder()
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
        final GetBucketLoggingResponse result = s3Client.getBucketLogging(Object::toString);
    }

    public void tryGetBucketMetricsConfiguration1() {
        final GetBucketMetricsConfigurationRequest getBucketMetricsConfigurationRequest = GetBucketMetricsConfigurationRequest.builder()
                .bucket("bucket")
                .id("id")
                .expectedBucketOwner("expectedBucketOwner")
                .build();
        final GetBucketMetricsConfigurationResponse varThatUsesProps = GetBucketMetricsConfigurationResponse.builder()
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
        final GetBucketMetricsConfigurationResponse result = s3Client.getBucketMetricsConfiguration(
                getBucketMetricsConfigurationRequest);
    }

    public void tryGetBucketMetricsConfiguration2() {
        final GetBucketMetricsConfigurationResponse varThatUsesProps = GetBucketMetricsConfigurationResponse.builder()
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
        final GetBucketMetricsConfigurationResponse result = s3Client.getBucketMetricsConfiguration(Object::toString);
    }

    public void tryGetBucketNotificationConfiguration1() {
        final GetBucketNotificationConfigurationRequest getBucketNotificationConfigurationRequest = GetBucketNotificationConfigurationRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build();
        final GetBucketNotificationConfigurationResponse varThatUsesProps = GetBucketNotificationConfigurationResponse.builder()
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
        final GetBucketNotificationConfigurationResponse result = s3Client.getBucketNotificationConfiguration(
                getBucketNotificationConfigurationRequest);
    }

    public void tryGetBucketNotificationConfiguration2() {
        final GetBucketNotificationConfigurationResponse varThatUsesProps = GetBucketNotificationConfigurationResponse.builder()
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
        final GetBucketNotificationConfigurationResponse result = s3Client.getBucketNotificationConfiguration(
                Object::toString);
    }

    public void tryGetBucketPolicy1() {
        final GetBucketPolicyRequest getBucketPolicyRequest = GetBucketPolicyRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build();
        final GetBucketPolicyResponse varThatUsesProps = GetBucketPolicyResponse.builder()
                .policy("policy")
                .build();
        final GetBucketPolicyResponse result = s3Client.getBucketPolicy(getBucketPolicyRequest);
    }

    public void tryGetBucketPolicy2() {
        final GetBucketPolicyResponse varThatUsesProps = GetBucketPolicyResponse.builder()
                .policy("policy")
                .build();
        final GetBucketPolicyResponse result = s3Client.getBucketPolicy(Object::toString);
    }

    public void tryGetBucketPolicyStatus1() {
        final GetBucketPolicyStatusRequest getBucketPolicyStatusRequest = GetBucketPolicyStatusRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build();
        final GetBucketPolicyStatusResponse varThatUsesProps = GetBucketPolicyStatusResponse.builder()
                .policyStatus(PolicyStatus.builder()
                        .isPublic(false)
                        .build())
                .build();
        final GetBucketPolicyStatusResponse result = s3Client.getBucketPolicyStatus(getBucketPolicyStatusRequest);
    }

    public void tryGetBucketPolicyStatus2() {
        final GetBucketPolicyStatusResponse varThatUsesProps = GetBucketPolicyStatusResponse.builder()
                .policyStatus(PolicyStatus.builder()
                        .isPublic(false)
                        .build())
                .build();
        final GetBucketPolicyStatusResponse result = s3Client.getBucketPolicyStatus(Object::toString);
    }

    public void tryGetBucketReplication1() {
        final GetBucketReplicationRequest getBucketReplicationRequest = GetBucketReplicationRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build();
        final GetBucketReplicationResponse varThatUsesProps = GetBucketReplicationResponse.builder()
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
        final GetBucketReplicationResponse result = s3Client.getBucketReplication(getBucketReplicationRequest);
    }

    public void tryGetBucketReplication2() {
        final GetBucketReplicationResponse varThatUsesProps = GetBucketReplicationResponse.builder()
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
        final GetBucketReplicationResponse result = s3Client.getBucketReplication(Object::toString);
    }

    public void tryGetBucketRequestPayment1() {
        final GetBucketRequestPaymentRequest getBucketRequestPaymentRequest = GetBucketRequestPaymentRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build();
        final GetBucketRequestPaymentResponse varThatUsesProps = GetBucketRequestPaymentResponse.builder()
                .payer(Payer.REQUESTER)
                .build();
        final GetBucketRequestPaymentResponse result = s3Client.getBucketRequestPayment(getBucketRequestPaymentRequest);
    }

    public void tryGetBucketRequestPayment2() {
        final GetBucketRequestPaymentResponse varThatUsesProps = GetBucketRequestPaymentResponse.builder()
                .payer(Payer.REQUESTER)
                .build();
        final GetBucketRequestPaymentResponse result = s3Client.getBucketRequestPayment(Object::toString);
    }

    public void tryGetBucketTagging1() {
        final GetBucketTaggingRequest getBucketTaggingRequest = GetBucketTaggingRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build();
        final GetBucketTaggingResponse varThatUsesProps = GetBucketTaggingResponse.builder()
                .tagSet(Tag.builder()
                        .key("key")
                        .value("value")
                        .build())
                .build();
        final GetBucketTaggingResponse result = s3Client.getBucketTagging(getBucketTaggingRequest);
    }

    public void tryGetBucketTagging2() {
        final GetBucketTaggingResponse varThatUsesProps = GetBucketTaggingResponse.builder()
                .tagSet(Tag.builder()
                        .key("key")
                        .value("value")
                        .build())
                .build();
        final GetBucketTaggingResponse result = s3Client.getBucketTagging(Object::toString);
    }

    public void tryGetBucketVersioning1() {
        final GetBucketVersioningRequest getBucketVersioningRequest = GetBucketVersioningRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build();
        final GetBucketVersioningResponse varThatUsesProps = GetBucketVersioningResponse.builder()
                .status(BucketVersioningStatus.ENABLED)
                .mfaDelete(MFADeleteStatus.ENABLED)
                .build();
        final GetBucketVersioningResponse result = s3Client.getBucketVersioning(getBucketVersioningRequest);
    }

    public void tryGetBucketVersioning2() {
        final GetBucketVersioningResponse varThatUsesProps = GetBucketVersioningResponse.builder()
                .status(BucketVersioningStatus.ENABLED)
                .mfaDelete(MFADeleteStatus.ENABLED)
                .build();
        final GetBucketVersioningResponse result = s3Client.getBucketVersioning(Object::toString);
    }

    public void tryGetBucketWebsite1() {
        final GetBucketWebsiteRequest getBucketWebsiteRequest = GetBucketWebsiteRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build();
        final GetBucketWebsiteResponse varThatUsesProps = GetBucketWebsiteResponse.builder()
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
        final GetBucketWebsiteResponse result = s3Client.getBucketWebsite(getBucketWebsiteRequest);
    }

    public void tryGetBucketWebsite2() {
        final GetBucketWebsiteResponse varThatUsesProps = GetBucketWebsiteResponse.builder()
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
        final GetBucketWebsiteResponse result = s3Client.getBucketWebsite(Object::toString);
    }

    public void tryGetObjectToInputStream() {
        final GetObjectRequest getObjectRequest = GetObjectRequest.builder().bucket("bucketName").key("key").build();
        final ResponseInputStream<GetObjectResponse> res = s3Client.getObject(getObjectRequest, ResponseTransformer.toInputStream());
    }

    public void tryGetObjectToFile() {
        final GetObjectRequest getObjectRequest = GetObjectRequest.builder().bucket("bucketName").key("key").build();
        s3Client.getObject(getObjectRequest, ResponseTransformer.toFile(new File("theFile.txt")));
    }

    public void tryGetObject3() {
        final GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build();
        final GetObjectResponse varThatUsesProps = GetObjectResponse.builder().build();
        final GetObjectResponse result = s3Client.getObject(getObjectRequest, Paths.get("filename.txt"));
    }

    public void tryGetObject4() {
        final GetObjectResponse varThatUsesProps = GetObjectResponse.builder().build();
        final GetObjectResponse result = s3Client.getObject(Object::toString, Paths.get("filename.txt"));
    }

    public void tryGetObject5() {
        final GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build();
        final ResponseInputStream<GetObjectResponse> varThatUsesProps = new ResponseInputStream<>(
                GetObjectResponse.builder().build(),
                AbortableInputStream.create(new ByteArrayInputStream("objectContent".getBytes())));
        final ResponseInputStream<GetObjectResponse> result = s3Client.getObject(getObjectRequest);
    }

    public void tryGetObject6() {
        final ResponseInputStream<GetObjectResponse> varThatUsesProps = new ResponseInputStream<>(
                GetObjectResponse.builder().build(),
                AbortableInputStream.create(new ByteArrayInputStream("objectContent".getBytes())));
        final ResponseInputStream<GetObjectResponse> result = s3Client.getObject(Object::toString);
    }

    public void tryGetObjectAsBytes1() {
        final GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build();
        final ResponseBytes<GetObjectResponse> varThatUsesProps = ResponseBytes.fromByteArray(
                GetObjectResponse.builder().build(), "content".getBytes());
        final ResponseBytes<GetObjectResponse> result = s3Client.getObjectAsBytes(getObjectRequest);
    }

    public void tryGetObjectAsBytes2() {
        final ResponseBytes<GetObjectResponse> varThatUsesProps = ResponseBytes.fromByteArray(
                GetObjectResponse.builder().build(), "content".getBytes());
        final ResponseBytes<GetObjectResponse> result = s3Client.getObjectAsBytes(Object::toString);
    }

    public void tryGetObjectAcl1() {
        final GetObjectAclRequest getObjectAclRequest = GetObjectAclRequest.builder()
                .bucket("bucket")
                .key("key")
                .versionId("versionId")
                .requestPayer(RequestPayer.REQUESTER)
                .expectedBucketOwner("expectedBucketOwner")
                .build();
        final GetObjectAclResponse varThatUsesProps = GetObjectAclResponse.builder()
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
        final GetObjectAclResponse result = s3Client.getObjectAcl(getObjectAclRequest);
    }

    public void tryGetObjectAcl2() {
        final GetObjectAclResponse varThatUsesProps = GetObjectAclResponse.builder()
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
        final GetObjectAclResponse result = s3Client.getObjectAcl(Object::toString);
    }

    public void tryGetObjectLegalHold1() {
        final GetObjectLegalHoldRequest getObjectLegalHoldRequest = GetObjectLegalHoldRequest.builder()
                .bucket("bucket")
                .key("key")
                .versionId("versionId")
                .requestPayer(RequestPayer.REQUESTER)
                .expectedBucketOwner("expectedBucketOwner")
                .build();
        final GetObjectLegalHoldResponse varThatUsesProps = GetObjectLegalHoldResponse.builder()
                .legalHold(ObjectLockLegalHold.builder()
                        .status(ObjectLockLegalHoldStatus.ON)
                        .build())
                .build();
        final GetObjectLegalHoldResponse result = s3Client.getObjectLegalHold(getObjectLegalHoldRequest);
    }

    public void tryGetObjectLegalHold2() {
        final GetObjectLegalHoldResponse varThatUsesProps = GetObjectLegalHoldResponse.builder()
                .legalHold(ObjectLockLegalHold.builder()
                        .status(ObjectLockLegalHoldStatus.ON)
                        .build())
                .build();
        final GetObjectLegalHoldResponse result = s3Client.getObjectLegalHold(Object::toString);
    }

    public void tryGetObjectLockConfiguration1() {
        final GetObjectLockConfigurationRequest getObjectLockConfigurationRequest = GetObjectLockConfigurationRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build();
        final GetObjectLockConfigurationResponse varThatUsesProps = GetObjectLockConfigurationResponse.builder()
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
        final GetObjectLockConfigurationResponse result = s3Client.getObjectLockConfiguration(
                getObjectLockConfigurationRequest);
    }

    public void tryGetObjectLockConfiguration2() {
        final GetObjectLockConfigurationResponse varThatUsesProps = GetObjectLockConfigurationResponse.builder()
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
        final GetObjectLockConfigurationResponse result = s3Client.getObjectLockConfiguration(Object::toString);
    }

    public void tryGetObjectRetention1() {
        final GetObjectRetentionRequest getObjectRetentionRequest = GetObjectRetentionRequest.builder()
                .bucket("bucket")
                .key("key")
                .versionId("versionId")
                .requestPayer(RequestPayer.REQUESTER)
                .expectedBucketOwner("expectedBucketOwner")
                .build();
        final GetObjectRetentionResponse varThatUsesProps = GetObjectRetentionResponse.builder()
                .retention(ObjectLockRetention.builder()
                        .mode(ObjectLockRetentionMode.GOVERNANCE)
                        .retainUntilDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                        .build())
                .build();
        final GetObjectRetentionResponse result = s3Client.getObjectRetention(getObjectRetentionRequest);
    }

    public void tryGetObjectRetention2() {
        final GetObjectRetentionResponse varThatUsesProps = GetObjectRetentionResponse.builder()
                .retention(ObjectLockRetention.builder()
                        .mode(ObjectLockRetentionMode.GOVERNANCE)
                        .retainUntilDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                        .build())
                .build();
        final GetObjectRetentionResponse result = s3Client.getObjectRetention(Object::toString);
    }

    public void tryGetObjectTagging1() {
        final GetObjectTaggingRequest getObjectTaggingRequest = GetObjectTaggingRequest.builder()
                .bucket("bucket")
                .key("key")
                .versionId("versionId")
                .expectedBucketOwner("expectedBucketOwner")
                .build();
        final GetObjectTaggingResponse varThatUsesProps = GetObjectTaggingResponse.builder()
                .versionId("versionId")
                .tagSet(Tag.builder()
                        .key("key")
                        .value("value")
                        .build())
                .build();
        final GetObjectTaggingResponse result = s3Client.getObjectTagging(getObjectTaggingRequest);
    }

    public void tryGetObjectTagging2() {
        final GetObjectTaggingResponse varThatUsesProps = GetObjectTaggingResponse.builder()
                .versionId("versionId")
                .tagSet(Tag.builder()
                        .key("key")
                        .value("value")
                        .build())
                .build();
        final GetObjectTaggingResponse result = s3Client.getObjectTagging(Object::toString);
    }

    public void tryGetObjectTorrentToInputStream() {
        final GetObjectTorrentRequest getObjectTorrentRequest = GetObjectTorrentRequest.builder()
                .bucket("bucket")
                .key("key")
                .requestPayer(RequestPayer.REQUESTER)
                .expectedBucketOwner("expectedBucketOwner")
                .build();
        final ResponseInputStream<GetObjectTorrentResponse> res = s3Client.getObjectTorrent(getObjectTorrentRequest, ResponseTransformer.toInputStream());
    }

    public void tryGetObjectTorrentToFile() {
        final GetObjectTorrentRequest getObjectTorrentRequest = GetObjectTorrentRequest.builder().bucket("bucketName").key("key").build();
        s3Client.getObjectTorrent(getObjectTorrentRequest, ResponseTransformer.toFile(new File("theFile.txt")));
    }

    public void tryGetObjectTorrent3() {
        final GetObjectTorrentRequest getObjectTorrentRequest = GetObjectTorrentRequest.builder()
                .bucket("bucket")
                .key("key")
                .requestPayer(RequestPayer.REQUESTER)
                .expectedBucketOwner("expectedBucketOwner")
                .build();
        final GetObjectTorrentResponse varThatUsesProps = GetObjectTorrentResponse.builder().build();
        final GetObjectTorrentResponse result = s3Client.getObjectTorrent(getObjectTorrentRequest,
                Paths.get("filename.txt"));
    }

    public void tryGetObjectTorrent4() {
        final GetObjectTorrentResponse varThatUsesProps = GetObjectTorrentResponse.builder().build();
        final GetObjectTorrentResponse result = s3Client.getObjectTorrent(Object::toString, Paths.get("filename.txt"));
    }

    public void tryGetObjectTorrent5() {
        final GetObjectTorrentRequest getObjectTorrentRequest = GetObjectTorrentRequest.builder()
                .bucket("bucket")
                .key("key")
                .requestPayer(RequestPayer.REQUESTER)
                .expectedBucketOwner("expectedBucketOwner")
                .build();
        final ResponseInputStream<GetObjectTorrentResponse> varThatUsesProps = new ResponseInputStream<>(
                GetObjectTorrentResponse.builder().build(),
                AbortableInputStream.create(new ByteArrayInputStream("objectContent".getBytes())));
        final ResponseInputStream<GetObjectTorrentResponse> result = s3Client.getObjectTorrent(getObjectTorrentRequest);
    }

    public void tryGetObjectTorrent6() {
        final ResponseInputStream<GetObjectTorrentResponse> varThatUsesProps = new ResponseInputStream<>(
                GetObjectTorrentResponse.builder().build(),
                AbortableInputStream.create(new ByteArrayInputStream("objectContent".getBytes())));
        final ResponseInputStream<GetObjectTorrentResponse> result = s3Client.getObjectTorrent(Object::toString);
    }

    public void tryGetObjectTorrentAsBytes1() {
        final GetObjectTorrentRequest getObjectTorrentRequest = GetObjectTorrentRequest.builder()
                .bucket("bucket")
                .key("key")
                .requestPayer(RequestPayer.REQUESTER)
                .expectedBucketOwner("expectedBucketOwner")
                .build();
        final ResponseBytes<GetObjectTorrentResponse> varThatUsesProps = ResponseBytes.fromByteArray(
                GetObjectTorrentResponse.builder().build(), "content".getBytes());
        final ResponseBytes<GetObjectTorrentResponse> result = s3Client.getObjectTorrentAsBytes(
                getObjectTorrentRequest);
    }

    public void tryGetObjectTorrentAsBytes2() {
        final ResponseBytes<GetObjectTorrentResponse> varThatUsesProps = ResponseBytes.fromByteArray(
                GetObjectTorrentResponse.builder().build(), "content".getBytes());
        final ResponseBytes<GetObjectTorrentResponse> result = s3Client.getObjectTorrentAsBytes(Object::toString);
    }

    public void tryGetPublicAccessBlock1() {
        final GetPublicAccessBlockRequest getPublicAccessBlockRequest = GetPublicAccessBlockRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build();
        final GetPublicAccessBlockResponse varThatUsesProps = GetPublicAccessBlockResponse.builder()
                .publicAccessBlockConfiguration(PublicAccessBlockConfiguration.builder()
                        .blockPublicAcls(false)
                        .ignorePublicAcls(false)
                        .blockPublicPolicy(false)
                        .restrictPublicBuckets(false)
                        .build())
                .build();
        final GetPublicAccessBlockResponse result = s3Client.getPublicAccessBlock(getPublicAccessBlockRequest);
    }

    public void tryGetPublicAccessBlock2() {
        final GetPublicAccessBlockResponse varThatUsesProps = GetPublicAccessBlockResponse.builder()
                .publicAccessBlockConfiguration(PublicAccessBlockConfiguration.builder()
                        .blockPublicAcls(false)
                        .ignorePublicAcls(false)
                        .blockPublicPolicy(false)
                        .restrictPublicBuckets(false)
                        .build())
                .build();
        final GetPublicAccessBlockResponse result = s3Client.getPublicAccessBlock(Object::toString);
    }

    public void tryHeadBucket1() {
        final HeadBucketRequest headBucketRequest = HeadBucketRequest.builder()
                .bucket("bucket")
                .expectedBucketOwner("expectedBucketOwner")
                .build();
        final HeadBucketResponse varThatUsesProps = HeadBucketResponse.builder().build();
        final HeadBucketResponse result = s3Client.headBucket(headBucketRequest);
    }

    public void tryHeadBucket2() {
        final HeadBucketResponse varThatUsesProps = HeadBucketResponse.builder().build();
        final HeadBucketResponse result = s3Client.headBucket(Object::toString);
    }

    public void tryHeadObject1() {
        final HeadObjectRequest headObjectRequest = HeadObjectRequest.builder()
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
                .build();
        final HeadObjectResponse varThatUsesProps = HeadObjectResponse.builder()
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
                .metadata(Map.ofEntries(Map.entry("value", "value")))
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
        final HeadObjectResponse result = s3Client.headObject(headObjectRequest);
    }

    public void tryHeadObject2() {
        final HeadObjectResponse varThatUsesProps = HeadObjectResponse.builder()
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
                .metadata(Map.ofEntries(Map.entry("value", "value")))
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
        final HeadObjectResponse result = s3Client.headObject(Object::toString);
    }

    public void tryListBucketAnalyticsConfigurations1() {
        final ListBucketAnalyticsConfigurationsRequest listBucketAnalyticsConfigurationsRequest = ListBucketAnalyticsConfigurationsRequest.builder()
                .bucket("bucket")
                .continuationToken("continuationToken")
                .expectedBucketOwner("expectedBucketOwner")
                .build();
        final ListBucketAnalyticsConfigurationsResponse varThatUsesProps = ListBucketAnalyticsConfigurationsResponse.builder()
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
        final ListBucketAnalyticsConfigurationsResponse result = s3Client.listBucketAnalyticsConfigurations(
                listBucketAnalyticsConfigurationsRequest);
    }

    public void tryListBucketAnalyticsConfigurations2() {
        final ListBucketAnalyticsConfigurationsResponse varThatUsesProps = ListBucketAnalyticsConfigurationsResponse.builder()
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
        final ListBucketAnalyticsConfigurationsResponse result = s3Client.listBucketAnalyticsConfigurations(
                Object::toString);
    }

    public void tryListBucketInventoryConfigurations1() {
        final ListBucketInventoryConfigurationsRequest listBucketInventoryConfigurationsRequest = ListBucketInventoryConfigurationsRequest.builder()
                .bucket("bucket")
                .continuationToken("continuationToken")
                .expectedBucketOwner("expectedBucketOwner")
                .build();
        final ListBucketInventoryConfigurationsResponse varThatUsesProps = ListBucketInventoryConfigurationsResponse.builder()
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
        final ListBucketInventoryConfigurationsResponse result = s3Client.listBucketInventoryConfigurations(
                listBucketInventoryConfigurationsRequest);
    }

    public void tryListBucketInventoryConfigurations2() {
        final ListBucketInventoryConfigurationsResponse varThatUsesProps = ListBucketInventoryConfigurationsResponse.builder()
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
        final ListBucketInventoryConfigurationsResponse result = s3Client.listBucketInventoryConfigurations(
                Object::toString);
    }

    public void tryListBucketMetricsConfigurations1() {
        final ListBucketMetricsConfigurationsRequest listBucketMetricsConfigurationsRequest = ListBucketMetricsConfigurationsRequest.builder()
                .bucket("bucket")
                .continuationToken("continuationToken")
                .expectedBucketOwner("expectedBucketOwner")
                .build();
        final ListBucketMetricsConfigurationsResponse varThatUsesProps = ListBucketMetricsConfigurationsResponse.builder()
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
        final ListBucketMetricsConfigurationsResponse result = s3Client.listBucketMetricsConfigurations(
                listBucketMetricsConfigurationsRequest);
    }

    public void tryListBucketMetricsConfigurations2() {
        final ListBucketMetricsConfigurationsResponse varThatUsesProps = ListBucketMetricsConfigurationsResponse.builder()
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
        final ListBucketMetricsConfigurationsResponse result = s3Client.listBucketMetricsConfigurations(
                Object::toString);
    }

    public void tryListBuckets1() {
        final ListBucketsResponse varThatUsesProps = ListBucketsResponse.builder()
                .buckets(Bucket.builder()
                        .name("name")
                        .creationDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                        .build())
                .owner(Owner.builder()
                        .displayName("displayName")
                        .id("id")
                        .build())
                .build();
        final ListBucketsResponse result = s3Client.listBuckets();
    }

    public void tryListBuckets2() {
        final ListBucketsRequest listBucketsRequest = ListBucketsRequest.builder().build();
        final ListBucketsResponse varThatUsesProps = ListBucketsResponse.builder()
                .buckets(Bucket.builder()
                        .name("name")
                        .creationDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                        .build())
                .owner(Owner.builder()
                        .displayName("displayName")
                        .id("id")
                        .build())
                .build();
        final ListBucketsResponse result = s3Client.listBuckets(listBucketsRequest);
    }

    public void tryListBuckets3() {
        final ListBucketsResponse varThatUsesProps = ListBucketsResponse.builder()
                .buckets(Bucket.builder()
                        .name("name")
                        .creationDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                        .build())
                .owner(Owner.builder()
                        .displayName("displayName")
                        .id("id")
                        .build())
                .build();
        final ListBucketsResponse result = s3Client.listBuckets(Object::toString);
    }

    public void tryListMultipartUploads1() {
        final ListMultipartUploadsRequest listMultipartUploadsRequest = ListMultipartUploadsRequest.builder()
                .bucket("bucket")
                .delimiter("delimiter")
                .encodingType(EncodingType.URL)
                .keyMarker("keyMarker")
                .maxUploads(0)
                .prefix("prefix")
                .uploadIdMarker("uploadIdMarker")
                .expectedBucketOwner("expectedBucketOwner")
                .build();
        final ListMultipartUploadsResponse varThatUsesProps = ListMultipartUploadsResponse.builder()
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
        final ListMultipartUploadsResponse result = s3Client.listMultipartUploads(listMultipartUploadsRequest);
    }

    public void tryListMultipartUploads2() {
        final ListMultipartUploadsResponse varThatUsesProps = ListMultipartUploadsResponse.builder()
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
        final ListMultipartUploadsResponse result = s3Client.listMultipartUploads(Object::toString);
    }

    public void tryListMultipartUploadsPaginator1() {
        final ListMultipartUploadsRequest listMultipartUploadsRequest = ListMultipartUploadsRequest.builder()
                .bucket("bucket")
                .delimiter("delimiter")
                .encodingType(EncodingType.URL)
                .keyMarker("keyMarker")
                .maxUploads(0)
                .prefix("prefix")
                .uploadIdMarker("uploadIdMarker")
                .expectedBucketOwner("expectedBucketOwner")
                .build();
        final ListMultipartUploadsIterable varThatUsesProps = null;
        final ListMultipartUploadsIterable result = s3Client.listMultipartUploadsPaginator(listMultipartUploadsRequest);
    }

    public void tryListMultipartUploadsPaginator2() {
        final ListMultipartUploadsIterable varThatUsesProps = null;
        final ListMultipartUploadsIterable result = s3Client.listMultipartUploadsPaginator(Object::toString);
    }

    public void tryListObjectVersions1() {
        final ListObjectVersionsRequest listObjectVersionsRequest = ListObjectVersionsRequest.builder()
                .bucket("bucket")
                .prefix("prefix")
                .build();
        final ListObjectVersionsResponse varThatUsesProps = ListObjectVersionsResponse.builder()
                .prefix("prefix")
                .versions(ObjectVersion.builder().key("key").build())
                .isTruncated(false)
                .build();
        final ListObjectVersionsResponse result = s3Client.listObjectVersions(listObjectVersionsRequest);
    }

    public void tryListObjectVersions2() {
        final ListObjectVersionsResponse varThatUsesProps = ListObjectVersionsResponse.builder()
                .prefix("prefix")
                .versions(ObjectVersion.builder().key("key").build())
                .isTruncated(false)
                .build();
        final ListObjectVersionsResponse result = s3Client.listObjectVersions(Object::toString);
    }

    public void tryListObjectVersionsPaginator1() {
        final ListObjectVersionsRequest listObjectVersionsRequest = ListObjectVersionsRequest.builder()
                .bucket("bucket")
                .prefix("prefix")
                .build();
        final ListObjectVersionsIterable varThatUsesProps = null;
        final ListObjectVersionsIterable result = s3Client.listObjectVersionsPaginator(listObjectVersionsRequest);
    }

    public void tryListObjectVersionsPaginator2() {
        final ListObjectVersionsIterable varThatUsesProps = null;
        final ListObjectVersionsIterable result = s3Client.listObjectVersionsPaginator(Object::toString);
    }

    public void tryListObjects1() {
        final ListObjectsRequest listObjectsRequest = ListObjectsRequest.builder()
                .bucket("bucket")
                .prefix("prefix")
                .build();
        final ListObjectsResponse varThatUsesProps = ListObjectsResponse.builder()
                .prefix("prefix")
                .contents(S3Object.builder().key("key").build())
                .isTruncated(false)
                .build();
        final ListObjectsResponse result = s3Client.listObjects(listObjectsRequest);
    }

    public void tryListObjects2() {
        final ListObjectsResponse varThatUsesProps = ListObjectsResponse.builder()
                .prefix("prefix")
                .contents(S3Object.builder().key("key").build())
                .isTruncated(false)
                .build();
        final ListObjectsResponse result = s3Client.listObjects(Object::toString);
    }

    public void tryListObjectsV21() {
        final ListObjectsV2Request listObjectsV2Request = ListObjectsV2Request.builder()
                .bucket("bucket")
                .prefix("prefix")
                .build();
        final ListObjectsV2Response varThatUsesProps = ListObjectsV2Response.builder()
                .prefix("prefix")
                .contents(S3Object.builder()
                        .key("key")
                        .build())
                .isTruncated(false)
                .build();
        final ListObjectsV2Response result = s3Client.listObjectsV2(listObjectsV2Request);
    }

    public void tryListObjectsV22() {
        final ListObjectsV2Response varThatUsesProps = ListObjectsV2Response.builder()
                .prefix("prefix")
                .contents(S3Object.builder()
                        .key("key")
                        .build())
                .isTruncated(false)
                .build();
        final ListObjectsV2Response result = s3Client.listObjectsV2(Object::toString);
    }

    public void tryListObjectsV2Paginator1() {
        final ListObjectsV2Request listObjectsV2Request = ListObjectsV2Request.builder()
                .bucket("bucket")
                .prefix("prefix")
                .build();
        final ListObjectsV2Iterable varThatUsesProps = null;
        final ListObjectsV2Iterable result = s3Client.listObjectsV2Paginator(listObjectsV2Request);
    }

    public void tryListObjectsV2Paginator2() {
        final ListObjectsV2Iterable varThatUsesProps = null;
        final ListObjectsV2Iterable result = s3Client.listObjectsV2Paginator(Object::toString);
    }

    public void tryListParts1() {
        final ListPartsRequest listPartsRequest = ListPartsRequest.builder()
                .bucket("bucket")
                .key("key")
                .maxParts(0)
                .partNumberMarker(0)
                .uploadId("uploadId")
                .requestPayer(RequestPayer.REQUESTER)
                .expectedBucketOwner("expectedBucketOwner")
                .build();
        final ListPartsResponse varThatUsesProps = ListPartsResponse.builder()
                .bucket("bucketName")
                .key("key")
                .parts(Part.builder().partNumber(1).build())
                .build();
        final ListPartsResponse result = s3Client.listParts(listPartsRequest);
    }

    public void tryListParts2() {
        final ListPartsResponse varThatUsesProps = ListPartsResponse.builder()
                .bucket("bucketName")
                .key("key")
                .parts(Part.builder().partNumber(1).build())
                .build();
        final ListPartsResponse result = s3Client.listParts(Object::toString);
    }

    public void tryListPartsPaginator1() {
        final ListPartsRequest listPartsRequest = ListPartsRequest.builder()
                .bucket("bucket")
                .key("key")
                .maxParts(0)
                .partNumberMarker(0)
                .uploadId("uploadId")
                .requestPayer(RequestPayer.REQUESTER)
                .expectedBucketOwner("expectedBucketOwner")
                .build();
        final ListPartsIterable varThatUsesProps = null;
        final ListPartsIterable result = s3Client.listPartsPaginator(listPartsRequest);
    }

    public void tryListPartsPaginator2() {
        final ListPartsIterable varThatUsesProps = null;
        final ListPartsIterable result = s3Client.listPartsPaginator(Object::toString);
    }

    public void tryPutBucketAccelerateConfiguration1() {
        final PutBucketAccelerateConfigurationRequest putBucketAccelerateConfigurationRequest = PutBucketAccelerateConfigurationRequest.builder()
                .bucket("bucket")
                .accelerateConfiguration(AccelerateConfiguration.builder()
                        .status(BucketAccelerateStatus.ENABLED)
                        .build())
                .expectedBucketOwner("expectedBucketOwner")
                .build();
        final PutBucketAccelerateConfigurationResponse varThatUsesProps = PutBucketAccelerateConfigurationResponse.builder().build();
        final PutBucketAccelerateConfigurationResponse result = s3Client.putBucketAccelerateConfiguration(
                putBucketAccelerateConfigurationRequest);
    }

    public void tryPutBucketAccelerateConfiguration2() {
        final PutBucketAccelerateConfigurationResponse varThatUsesProps = PutBucketAccelerateConfigurationResponse.builder().build();
        final PutBucketAccelerateConfigurationResponse result = s3Client.putBucketAccelerateConfiguration(
                Object::toString);
    }

    public void tryPutBucketAcl1() {
        final PutBucketAclRequest putBucketAclRequest = PutBucketAclRequest.builder()
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
                .build();
        final PutBucketAclResponse varThatUsesProps = PutBucketAclResponse.builder().build();
        final PutBucketAclResponse result = s3Client.putBucketAcl(putBucketAclRequest);
    }

    public void tryPutBucketAcl2() {
        final PutBucketAclResponse varThatUsesProps = PutBucketAclResponse.builder().build();
        final PutBucketAclResponse result = s3Client.putBucketAcl(Object::toString);
    }

    public void tryPutBucketAnalyticsConfiguration1() {
        final PutBucketAnalyticsConfigurationRequest putBucketAnalyticsConfigurationRequest = PutBucketAnalyticsConfigurationRequest.builder()
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
                .build();
        final PutBucketAnalyticsConfigurationResponse varThatUsesProps = PutBucketAnalyticsConfigurationResponse.builder().build();
        final PutBucketAnalyticsConfigurationResponse result = s3Client.putBucketAnalyticsConfiguration(
                putBucketAnalyticsConfigurationRequest);
    }

    public void tryPutBucketAnalyticsConfiguration2() {
        final PutBucketAnalyticsConfigurationResponse varThatUsesProps = PutBucketAnalyticsConfigurationResponse.builder().build();
        final PutBucketAnalyticsConfigurationResponse result = s3Client.putBucketAnalyticsConfiguration(
                Object::toString);
    }

    public void tryPutBucketCors1() {
        final PutBucketCorsRequest putBucketCorsRequest = PutBucketCorsRequest.builder()
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
                .build();
        final PutBucketCorsResponse varThatUsesProps = PutBucketCorsResponse.builder().build();
        final PutBucketCorsResponse result = s3Client.putBucketCors(putBucketCorsRequest);
    }

    public void tryPutBucketCors2() {
        final PutBucketCorsResponse varThatUsesProps = PutBucketCorsResponse.builder().build();
        final PutBucketCorsResponse result = s3Client.putBucketCors(Object::toString);
    }

    public void tryPutBucketEncryption1() {
        final PutBucketEncryptionRequest putBucketEncryptionRequest = PutBucketEncryptionRequest.builder()
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
                .build();
        final PutBucketEncryptionResponse varThatUsesProps = PutBucketEncryptionResponse.builder().build();
        final PutBucketEncryptionResponse result = s3Client.putBucketEncryption(putBucketEncryptionRequest);
    }

    public void tryPutBucketEncryption2() {
        final PutBucketEncryptionResponse varThatUsesProps = PutBucketEncryptionResponse.builder().build();
        final PutBucketEncryptionResponse result = s3Client.putBucketEncryption(Object::toString);
    }

    public void tryPutBucketInventoryConfiguration1() {
        final PutBucketInventoryConfigurationRequest putBucketInventoryConfigurationRequest = PutBucketInventoryConfigurationRequest.builder()
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
                .build();
        final PutBucketInventoryConfigurationResponse varThatUsesProps = PutBucketInventoryConfigurationResponse.builder().build();
        final PutBucketInventoryConfigurationResponse result = s3Client.putBucketInventoryConfiguration(
                putBucketInventoryConfigurationRequest);
    }

    public void tryPutBucketInventoryConfiguration2() {
        final PutBucketInventoryConfigurationResponse varThatUsesProps = PutBucketInventoryConfigurationResponse.builder().build();
        final PutBucketInventoryConfigurationResponse result = s3Client.putBucketInventoryConfiguration(
                Object::toString);
    }

    public void tryPutBucketLifecycleConfiguration1() {
        final PutBucketLifecycleConfigurationRequest putBucketLifecycleConfigurationRequest = PutBucketLifecycleConfigurationRequest.builder()
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
                                .noncurrentVersionExpiration(NoncurrentVersionExpiration.builder().build())
                                .build())
                        .build())
                .build();
        final PutBucketLifecycleConfigurationResponse varThatUsesProps = PutBucketLifecycleConfigurationResponse.builder().build();
        final PutBucketLifecycleConfigurationResponse result = s3Client.putBucketLifecycleConfiguration(
                putBucketLifecycleConfigurationRequest);
    }

    public void tryPutBucketLifecycleConfiguration2() {
        final PutBucketLifecycleConfigurationResponse varThatUsesProps = PutBucketLifecycleConfigurationResponse.builder().build();
        final PutBucketLifecycleConfigurationResponse result = s3Client.putBucketLifecycleConfiguration(
                Object::toString);
    }

    public void tryPutBucketLogging1() {
        final PutBucketLoggingRequest putBucketLoggingRequest = PutBucketLoggingRequest.builder()
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
                .build();
        final PutBucketLoggingResponse varThatUsesProps = PutBucketLoggingResponse.builder().build();
        final PutBucketLoggingResponse result = s3Client.putBucketLogging(putBucketLoggingRequest);
    }

    public void tryPutBucketLogging2() {
        final PutBucketLoggingResponse varThatUsesProps = PutBucketLoggingResponse.builder().build();
        final PutBucketLoggingResponse result = s3Client.putBucketLogging(Object::toString);
    }

    public void tryPutBucketMetricsConfiguration1() {
        final PutBucketMetricsConfigurationRequest putBucketMetricsConfigurationRequest = PutBucketMetricsConfigurationRequest.builder()
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
                .build();
        final PutBucketMetricsConfigurationResponse varThatUsesProps = PutBucketMetricsConfigurationResponse.builder().build();
        final PutBucketMetricsConfigurationResponse result = s3Client.putBucketMetricsConfiguration(
                putBucketMetricsConfigurationRequest);
    }

    public void tryPutBucketMetricsConfiguration2() {
        final PutBucketMetricsConfigurationResponse varThatUsesProps = PutBucketMetricsConfigurationResponse.builder().build();
        final PutBucketMetricsConfigurationResponse result = s3Client.putBucketMetricsConfiguration(Object::toString);
    }

    public void tryPutBucketNotificationConfiguration1() {
        final PutBucketNotificationConfigurationRequest putBucketNotificationConfigurationRequest = PutBucketNotificationConfigurationRequest.builder()
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
                .build();
        final PutBucketNotificationConfigurationResponse varThatUsesProps = PutBucketNotificationConfigurationResponse.builder().build();
        final PutBucketNotificationConfigurationResponse result = s3Client.putBucketNotificationConfiguration(
                putBucketNotificationConfigurationRequest);
    }

    public void tryPutBucketNotificationConfiguration2() {
        final PutBucketNotificationConfigurationResponse varThatUsesProps = PutBucketNotificationConfigurationResponse.builder().build();
        final PutBucketNotificationConfigurationResponse result = s3Client.putBucketNotificationConfiguration(
                Object::toString);
    }

    public void tryPutBucketPolicy1() {
        final PutBucketPolicyRequest putBucketPolicyRequest = PutBucketPolicyRequest.builder()
                .bucket("bucket")
                .contentMD5("contentMD5")
                .confirmRemoveSelfBucketAccess(false)
                .policy("policy")
                .expectedBucketOwner("expectedBucketOwner")
                .build();
        final PutBucketPolicyResponse varThatUsesProps = PutBucketPolicyResponse.builder().build();
        final PutBucketPolicyResponse result = s3Client.putBucketPolicy(putBucketPolicyRequest);
    }

    public void tryPutBucketPolicy2() {
        final PutBucketPolicyResponse varThatUsesProps = PutBucketPolicyResponse.builder().build();
        final PutBucketPolicyResponse result = s3Client.putBucketPolicy(Object::toString);
    }

    public void tryPutBucketReplication1() {
        final PutBucketReplicationRequest putBucketReplicationRequest = PutBucketReplicationRequest.builder()
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
                                        .accessControlTranslation(AccessControlTranslation.builder().build())
                                        .build())
                                .build())
                        .build())
                .build();
        final PutBucketReplicationResponse varThatUsesProps = PutBucketReplicationResponse.builder().build();
        final PutBucketReplicationResponse result = s3Client.putBucketReplication(putBucketReplicationRequest);
    }

    public void tryPutBucketReplication2() {
        final PutBucketReplicationResponse varThatUsesProps = PutBucketReplicationResponse.builder().build();
        final PutBucketReplicationResponse result = s3Client.putBucketReplication(Object::toString);
    }

    public void tryPutBucketRequestPayment1() {
        final PutBucketRequestPaymentRequest putBucketRequestPaymentRequest = PutBucketRequestPaymentRequest.builder()
                .bucket("bucket")
                .contentMD5("contentMD5")
                .requestPaymentConfiguration(RequestPaymentConfiguration.builder()
                        .payer(Payer.REQUESTER)
                        .build())
                .expectedBucketOwner("expectedBucketOwner")
                .build();
        final PutBucketRequestPaymentResponse varThatUsesProps = PutBucketRequestPaymentResponse.builder().build();
        final PutBucketRequestPaymentResponse result = s3Client.putBucketRequestPayment(putBucketRequestPaymentRequest);
    }

    public void tryPutBucketRequestPayment2() {
        final PutBucketRequestPaymentResponse varThatUsesProps = PutBucketRequestPaymentResponse.builder().build();
        final PutBucketRequestPaymentResponse result = s3Client.putBucketRequestPayment(Object::toString);
    }

    public void tryPutBucketTagging1() {
        final PutBucketTaggingRequest putBucketTaggingRequest = PutBucketTaggingRequest.builder()
                .bucket("bucket")
                .contentMD5("contentMD5")
                .tagging(Tagging.builder()
                        .tagSet(Tag.builder()
                                .key("key")
                                .value("value")
                                .build())
                        .build())
                .expectedBucketOwner("expectedBucketOwner")
                .build();
        final PutBucketTaggingResponse varThatUsesProps = PutBucketTaggingResponse.builder().build();
        final PutBucketTaggingResponse result = s3Client.putBucketTagging(putBucketTaggingRequest);
    }

    public void tryPutBucketTagging2() {
        final PutBucketTaggingResponse varThatUsesProps = PutBucketTaggingResponse.builder().build();
        final PutBucketTaggingResponse result = s3Client.putBucketTagging(Object::toString);
    }

    public void tryPutBucketVersioning1() {
        final PutBucketVersioningRequest putBucketVersioningRequest = PutBucketVersioningRequest.builder()
                .bucket("bucket")
                .contentMD5("contentMD5")
                .mfa("mfa")
                .versioningConfiguration(VersioningConfiguration.builder()
                        .mfaDelete(MFADelete.ENABLED)
                        .status(BucketVersioningStatus.ENABLED)
                        .build())
                .expectedBucketOwner("expectedBucketOwner")
                .build();
        final PutBucketVersioningResponse varThatUsesProps = PutBucketVersioningResponse.builder().build();
        final PutBucketVersioningResponse result = s3Client.putBucketVersioning(putBucketVersioningRequest);
    }

    public void tryPutBucketVersioning2() {
        final PutBucketVersioningResponse varThatUsesProps = PutBucketVersioningResponse.builder().build();
        final PutBucketVersioningResponse result = s3Client.putBucketVersioning(Object::toString);
    }

    public void tryPutBucketWebsite1() {
        final PutBucketWebsiteRequest putBucketWebsiteRequest = PutBucketWebsiteRequest.builder()
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
                .build();
        final PutBucketWebsiteResponse varThatUsesProps = PutBucketWebsiteResponse.builder().build();
        final PutBucketWebsiteResponse result = s3Client.putBucketWebsite(putBucketWebsiteRequest);
    }

    public void tryPutBucketWebsite2() {
        final PutBucketWebsiteResponse varThatUsesProps = PutBucketWebsiteResponse.builder().build();
        final PutBucketWebsiteResponse result = s3Client.putBucketWebsite(Object::toString);
    }

    public void tryPutObject1() {
        final PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build();
        final RequestBody requestBody = RequestBody.fromFile(Paths.get("filename.txt"));
        final PutObjectResponse varThatUsesProps = PutObjectResponse.builder().build();
        final PutObjectResponse result = s3Client.putObject(putObjectRequest, requestBody);
    }

    public void tryPutObject2() {
        final RequestBody requestBody = RequestBody.fromFile(Paths.get("filename.txt"));
        final PutObjectResponse varThatUsesProps = PutObjectResponse.builder().build();
        final PutObjectResponse result = s3Client.putObject(Object::toString, requestBody);
    }

    public void tryPutObject3() {
        final PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket("bucketName")
                .key("key")
                .build();
        final PutObjectResponse varThatUsesProps = PutObjectResponse.builder().build();
        final PutObjectResponse result = s3Client.putObject(putObjectRequest, Paths.get("filename.txt"));
    }

    public void tryPutObject4() {
        final PutObjectResponse varThatUsesProps = PutObjectResponse.builder().build();
        final PutObjectResponse result = s3Client.putObject(Object::toString, Paths.get("filename.txt"));
    }

    public void tryPutObjectAcl1() {
        final PutObjectAclRequest putObjectAclRequest = PutObjectAclRequest.builder()
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
                .build();
        final PutObjectAclResponse varThatUsesProps = PutObjectAclResponse.builder()
                .requestCharged(RequestCharged.REQUESTER)
                .build();
        final PutObjectAclResponse result = s3Client.putObjectAcl(putObjectAclRequest);
    }

    public void tryPutObjectAcl2() {
        final PutObjectAclResponse varThatUsesProps = PutObjectAclResponse.builder()
                .requestCharged(RequestCharged.REQUESTER)
                .build();
        final PutObjectAclResponse result = s3Client.putObjectAcl(Object::toString);
    }

    public void tryPutObjectLegalHold1() {
        final PutObjectLegalHoldRequest putObjectLegalHoldRequest = PutObjectLegalHoldRequest.builder()
                .bucket("bucket")
                .key("key")
                .legalHold(ObjectLockLegalHold.builder()
                        .status(ObjectLockLegalHoldStatus.ON)
                        .build())
                .requestPayer(RequestPayer.REQUESTER)
                .versionId("versionId")
                .contentMD5("contentMD5")
                .expectedBucketOwner("expectedBucketOwner")
                .build();
        final PutObjectLegalHoldResponse varThatUsesProps = PutObjectLegalHoldResponse.builder()
                .requestCharged(RequestCharged.REQUESTER)
                .build();
        final PutObjectLegalHoldResponse result = s3Client.putObjectLegalHold(putObjectLegalHoldRequest);
    }

    public void tryPutObjectLegalHold2() {
        final PutObjectLegalHoldResponse varThatUsesProps = PutObjectLegalHoldResponse.builder()
                .requestCharged(RequestCharged.REQUESTER)
                .build();
        final PutObjectLegalHoldResponse result = s3Client.putObjectLegalHold(Object::toString);
    }

    public void tryPutObjectLockConfiguration1() {
        final PutObjectLockConfigurationRequest putObjectLockConfigurationRequest = PutObjectLockConfigurationRequest.builder()
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
                .build();
        final PutObjectLockConfigurationResponse varThatUsesProps = PutObjectLockConfigurationResponse.builder()
                .requestCharged(RequestCharged.REQUESTER)
                .build();
        final PutObjectLockConfigurationResponse result = s3Client.putObjectLockConfiguration(
                putObjectLockConfigurationRequest);
    }

    public void tryPutObjectLockConfiguration2() {
        final PutObjectLockConfigurationResponse varThatUsesProps = PutObjectLockConfigurationResponse.builder()
                .requestCharged(RequestCharged.REQUESTER)
                .build();
        final PutObjectLockConfigurationResponse result = s3Client.putObjectLockConfiguration(Object::toString);
    }

    public void tryPutObjectRetention1() {
        final PutObjectRetentionRequest putObjectRetentionRequest = PutObjectRetentionRequest.builder()
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
                .build();
        final PutObjectRetentionResponse varThatUsesProps = PutObjectRetentionResponse.builder()
                .requestCharged(RequestCharged.REQUESTER)
                .build();
        final PutObjectRetentionResponse result = s3Client.putObjectRetention(putObjectRetentionRequest);
    }

    public void tryPutObjectRetention2() {
        final PutObjectRetentionResponse varThatUsesProps = PutObjectRetentionResponse.builder()
                .requestCharged(RequestCharged.REQUESTER)
                .build();
        final PutObjectRetentionResponse result = s3Client.putObjectRetention(Object::toString);
    }

    public void tryPutObjectTagging1() {
        final PutObjectTaggingRequest putObjectTaggingRequest = PutObjectTaggingRequest.builder()
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
                .build();
        final PutObjectTaggingResponse varThatUsesProps = PutObjectTaggingResponse.builder()
                .versionId("versionId")
                .build();
        final PutObjectTaggingResponse result = s3Client.putObjectTagging(putObjectTaggingRequest);
    }

    public void tryPutObjectTagging2() {
        final PutObjectTaggingResponse varThatUsesProps = PutObjectTaggingResponse.builder()
                .versionId("versionId")
                .build();
        final PutObjectTaggingResponse result = s3Client.putObjectTagging(Object::toString);
    }

    public void tryPutPublicAccessBlock1() {
        final PutPublicAccessBlockRequest putPublicAccessBlockRequest = PutPublicAccessBlockRequest.builder()
                .bucket("bucket")
                .contentMD5("contentMD5")
                .publicAccessBlockConfiguration(PublicAccessBlockConfiguration.builder()
                        .blockPublicAcls(false)
                        .ignorePublicAcls(false)
                        .blockPublicPolicy(false)
                        .restrictPublicBuckets(false)
                        .build())
                .expectedBucketOwner("expectedBucketOwner")
                .build();
        final PutPublicAccessBlockResponse varThatUsesProps = PutPublicAccessBlockResponse.builder().build();
        final PutPublicAccessBlockResponse result = s3Client.putPublicAccessBlock(putPublicAccessBlockRequest);
    }

    public void tryPutPublicAccessBlock2() {
        final PutPublicAccessBlockResponse varThatUsesProps = PutPublicAccessBlockResponse.builder().build();
        final PutPublicAccessBlockResponse result = s3Client.putPublicAccessBlock(Object::toString);
    }

    public void tryRestoreObject1() {
        final RestoreObjectRequest restoreObjectRequest = RestoreObjectRequest.builder()
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
                .build();
        final RestoreObjectResponse varThatUsesProps = RestoreObjectResponse.builder()
                .requestCharged(RequestCharged.REQUESTER)
                .restoreOutputPath("restoreOutputPath")
                .build();
        final RestoreObjectResponse result = s3Client.restoreObject(restoreObjectRequest);
    }

    public void tryRestoreObject2() {
        final RestoreObjectResponse varThatUsesProps = RestoreObjectResponse.builder()
                .requestCharged(RequestCharged.REQUESTER)
                .restoreOutputPath("restoreOutputPath")
                .build();
        final RestoreObjectResponse result = s3Client.restoreObject(Object::toString);
    }

    public void tryUploadPart1() {
        final UploadPartRequest uploadPartRequest = UploadPartRequest.builder()
                .bucket("bucket")
                .key("key")
                .uploadId("uploadId")
                .partNumber(0)
                .build();
        final RequestBody requestBody = RequestBody.fromFile(Paths.get("filename.txt"));
        final UploadPartResponse varThatUsesProps = UploadPartResponse.builder()
                .eTag("eTag")
                .build();
        final UploadPartResponse result = s3Client.uploadPart(uploadPartRequest, requestBody);
    }

    public void tryUploadPart2() {
        final RequestBody requestBody = RequestBody.fromFile(Paths.get("filename.txt"));
        final UploadPartResponse varThatUsesProps = UploadPartResponse.builder()
                .eTag("eTag")
                .build();
        final UploadPartResponse result = s3Client.uploadPart(Object::toString, requestBody);
    }

    public void tryUploadPart3() {
        final UploadPartRequest uploadPartRequest = UploadPartRequest.builder()
                .bucket("bucket")
                .key("key")
                .uploadId("uploadId")
                .partNumber(0)
                .build();
        final UploadPartResponse varThatUsesProps = UploadPartResponse.builder()
                .eTag("eTag")
                .build();
        final UploadPartResponse result = s3Client.uploadPart(uploadPartRequest, Paths.get("filename.txt"));
    }

    public void tryUploadPart4() {
        final UploadPartResponse varThatUsesProps = UploadPartResponse.builder()
                .eTag("eTag")
                .build();
        final UploadPartResponse result = s3Client.uploadPart(Object::toString, Paths.get("filename.txt"));
    }

    public void tryUploadPartCopy1() {
        final UploadPartCopyRequest uploadPartCopyRequest = UploadPartCopyRequest.builder()
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
                .build();
        final UploadPartCopyResponse varThatUsesProps = UploadPartCopyResponse.builder()
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
        final UploadPartCopyResponse result = s3Client.uploadPartCopy(uploadPartCopyRequest);
    }

    public void tryUploadPartCopy2() {
        final UploadPartCopyResponse varThatUsesProps = UploadPartCopyResponse.builder()
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
        final UploadPartCopyResponse result = s3Client.uploadPartCopy(Object::toString);
    }

    public void tryUtilities() {
        final S3Utilities varThatUsesProps = S3Utilities.builder()
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
        final S3Utilities result = s3Client.utilities();
    }

    public void tryWaiter() {
        final S3Waiter varThatUsesProps = S3Waiter.builder()
                .client(S3Client.create())
                .build();
        final S3Waiter result = s3Client.waiter();
    }
}
