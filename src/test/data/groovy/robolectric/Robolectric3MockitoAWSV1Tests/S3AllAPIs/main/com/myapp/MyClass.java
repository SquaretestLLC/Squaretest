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

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.HttpMethod;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.metrics.RequestMetricCollector;
import com.amazonaws.regions.Region;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.S3ClientOptions;
import com.amazonaws.services.s3.S3ResponseMetadata;
import com.amazonaws.services.s3.internal.SkipMd5CheckStrategy;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.services.s3.model.analytics.AnalyticsConfiguration;
import com.amazonaws.services.s3.model.inventory.InventoryConfiguration;
import com.amazonaws.services.s3.model.metrics.MetricsConfiguration;
import com.amazonaws.services.s3.waiters.AmazonS3Waiters;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

public class MyClass {

    private AWSCredentialsProvider credentialsProvider;
    private ClientConfiguration clientConfiguration;
    private RequestMetricCollector requestMetricCollector;
    private SkipMd5CheckStrategy skipMd5CheckStrategy;

    private AmazonS3Client amazonS3Client;

    public MyClass(AmazonS3Client amazonS3Client) {
        this.amazonS3Client = amazonS3Client;
    }

    public void trySetEndpoint() {
        amazonS3Client.setEndpoint("endpoint");
    }

    public void trySetRegion() {
        final Region region = new Region(null);
        amazonS3Client.setRegion(region);
    }

    public void trySetS3ClientOptions() {
        final S3ClientOptions clientOptions = S3ClientOptions.builder().build();
        amazonS3Client.setS3ClientOptions(clientOptions);
    }

    public void tryListNextBatchOfVersions() {
        final VersionListing previousVersionListing = new VersionListing();
        previousVersionListing.setVersionSummaries(Arrays.asList());
        previousVersionListing.setCommonPrefixes(Arrays.asList());
        previousVersionListing.setBucketName("bucketName");
        previousVersionListing.setPrefix("prefix");
        previousVersionListing.setKeyMarker("keyMarker");
        previousVersionListing.setVersionIdMarker("versionIdMarker");
        previousVersionListing.setMaxKeys(0);
        previousVersionListing.setDelimiter("delimiter");

        final VersionListing result = amazonS3Client.listNextBatchOfVersions(previousVersionListing);
    }

    public void tryListNextBatchOfVersions1() {
        final ListNextBatchOfVersionsRequest listNextBatchOfVersionsRequest = new ListNextBatchOfVersionsRequest(null);
        final VersionListing result = amazonS3Client.listNextBatchOfVersions(listNextBatchOfVersionsRequest);
    }

    public void tryListVersions() {
        final VersionListing result = amazonS3Client.listVersions("bucketName", "prefix");
    }

    public void tryListVersions1() {
        final VersionListing result = amazonS3Client.listVersions("bucketName", "prefix", "keyMarker", "versionIdMarker", "delimiter", 0);
    }

    public void tryListVersions2() {
        final ListVersionsRequest listVersionsRequest = new ListVersionsRequest("bucketName", "prefix", "keyMarker", "versionIdMarker", "delimiter", 0);
        final VersionListing result = amazonS3Client.listVersions(listVersionsRequest);
    }

    public void tryListObjects() {
        final ObjectListing result = amazonS3Client.listObjects("bucketName");
    }

    public void tryListObjects1() {
        final ObjectListing result = amazonS3Client.listObjects("bucketName", "prefix");
    }

    public void tryListObjects2() {
        final ListObjectsRequest listObjectsRequest = new ListObjectsRequest("bucketName", "prefix", "marker", "delimiter", 0);
        final ObjectListing result = amazonS3Client.listObjects(listObjectsRequest);
    }

    public void tryListObjectsV2() {
        final ListObjectsV2Result result = amazonS3Client.listObjectsV2("bucketName");
    }

    public void tryListObjectsV21() {
        final ListObjectsV2Result result = amazonS3Client.listObjectsV2("bucketName", "prefix");
    }

    public void tryListObjectsV22() {
        final ListObjectsV2Request listObjectsV2Request = new ListObjectsV2Request();
        final ListObjectsV2Result result = amazonS3Client.listObjectsV2(listObjectsV2Request);
    }

    public void tryListNextBatchOfObjects() {
        final ObjectListing previousObjectListing = new ObjectListing();
        final ObjectListing result = amazonS3Client.listNextBatchOfObjects(previousObjectListing);
    }

    public void tryListNextBatchOfObjects1() {
        final ListNextBatchOfObjectsRequest listNextBatchOfObjectsRequest = new ListNextBatchOfObjectsRequest(null);
        final ObjectListing result = amazonS3Client.listNextBatchOfObjects(listNextBatchOfObjectsRequest);
    }

    public void tryGetS3AccountOwner() {
        final Owner result = amazonS3Client.getS3AccountOwner();
    }

    public void tryGetS3AccountOwner1() {
        final GetS3AccountOwnerRequest getS3AccountOwnerRequest = new GetS3AccountOwnerRequest();
        final Owner result = amazonS3Client.getS3AccountOwner(getS3AccountOwnerRequest);
    }

    public void tryListBuckets() {
        final ListBucketsRequest listBucketsRequest = new ListBucketsRequest();
        final List<Bucket> result = amazonS3Client.listBuckets(listBucketsRequest);
    }

    public void tryListBuckets1() {
        final List<Bucket> result = amazonS3Client.listBuckets();
    }

    public void tryGetBucketLocation() {
        final GetBucketLocationRequest getBucketLocationRequest = new GetBucketLocationRequest("bucketName");
        final String result = amazonS3Client.getBucketLocation(getBucketLocationRequest);
    }

    public void tryGetBucketLocation1() {
        final String result = amazonS3Client.getBucketLocation("bucketName");
    }

    public void tryCreateBucket() {
        final Bucket result = amazonS3Client.createBucket("bucketName");
    }

    public void tryCreateBucket1() {
        final com.amazonaws.services.s3.model.Region region = com.amazonaws.services.s3.model.Region.US_Standard;
        final Bucket result = amazonS3Client.createBucket("bucketName", region);
    }

    public void tryCreateBucket2() {
        final Bucket result = amazonS3Client.createBucket("bucketName", "region");
    }

    public void tryCreateBucket3() {
        final CreateBucketRequest createBucketRequest = new CreateBucketRequest("bucketName", com.amazonaws.services.s3.model.Region.US_Standard);
        final Bucket result = amazonS3Client.createBucket(createBucketRequest);
    }

    public void tryGetObjectAcl() {
        final AccessControlList result = amazonS3Client.getObjectAcl("bucketName", "key");
    }

    public void tryGetObjectAcl1() {
        final AccessControlList result = amazonS3Client.getObjectAcl("bucketName", "key", "versionId");
    }

    public void tryGetObjectAcl2() {
        final GetObjectAclRequest getObjectAclRequest = new GetObjectAclRequest("bucketName", "key", "versionId");
        final AccessControlList result = amazonS3Client.getObjectAcl(getObjectAclRequest);
    }

    public void trySetObjectAcl() {
        final AccessControlList acl = new AccessControlList();
        amazonS3Client.setObjectAcl("bucketName", "key", acl);
    }

    public void trySetObjectAcl1() {
        final CannedAccessControlList acl = null;
        amazonS3Client.setObjectAcl("bucketName", "key", acl);
    }

    public void trySetObjectAcl2() {
        final AccessControlList acl = new AccessControlList();
        amazonS3Client.setObjectAcl("bucketName", "key", "versionId", acl);
    }

    public void trySetObjectAcl3() {
        final AccessControlList acl = new AccessControlList();
        final RequestMetricCollector requestMetricCollector = null;
        amazonS3Client.setObjectAcl("bucketName", "key", "versionId", acl, requestMetricCollector);
    }

    public void trySetObjectAcl4() {
        final CannedAccessControlList acl = null;
        amazonS3Client.setObjectAcl("bucketName", "key", "versionId", acl);
    }

    public void trySetObjectAcl5() {
        final CannedAccessControlList acl = null;
        final RequestMetricCollector requestMetricCollector = null;
        amazonS3Client.setObjectAcl("bucketName", "key", "versionId", acl, requestMetricCollector);
    }

    public void trySetObjectAcl6() {
        final SetObjectAclRequest setObjectAclRequest = new SetObjectAclRequest("bucketName", "key", "versionId", CannedAccessControlList.AuthenticatedRead);
        amazonS3Client.setObjectAcl(setObjectAclRequest);
    }

    public void tryGetBucketAcl() {
        final AccessControlList result = amazonS3Client.getBucketAcl("bucketName");
    }

    public void tryGetBucketAcl1() {
        final GetBucketAclRequest getBucketAclRequest = new GetBucketAclRequest("bucketName");
        final AccessControlList result = amazonS3Client.getBucketAcl(getBucketAclRequest);
    }

    public void trySetBucketAcl() {
        final AccessControlList acl = new AccessControlList();
        amazonS3Client.setBucketAcl("bucketName", acl);
    }

    public void trySetBucketAcl1() {
        final AccessControlList acl = new AccessControlList();
        final RequestMetricCollector requestMetricCollector = null;
        amazonS3Client.setBucketAcl("bucketName", acl, requestMetricCollector);
    }

    public void trySetBucketAcl2() {
        final CannedAccessControlList cannedAcl = null;
        amazonS3Client.setBucketAcl("bucketName", cannedAcl);
    }

    public void trySetBucketAcl3() {
        final CannedAccessControlList cannedAcl = null;
        final RequestMetricCollector requestMetricCollector = null;
        amazonS3Client.setBucketAcl("bucketName", cannedAcl, requestMetricCollector);
    }

    public void trySetBucketAcl4() {
        final SetBucketAclRequest setBucketAclRequest = new SetBucketAclRequest("bucketName", CannedAccessControlList.AuthenticatedRead);
        amazonS3Client.setBucketAcl(setBucketAclRequest);
    }

    public void tryGetObjectMetadata() {
        final ObjectMetadata result = amazonS3Client.getObjectMetadata("bucketName", "key");
    }

    public void tryGetObjectMetadata1() {
        final GetObjectMetadataRequest getObjectMetadataRequest = new GetObjectMetadataRequest("bucketName", "key", "versionId");
        final ObjectMetadata result = amazonS3Client.getObjectMetadata(getObjectMetadataRequest);
    }

    public void tryGetObject() {
        final S3Object result = amazonS3Client.getObject("bucketName", "key");
    }

    public void tryDoesBucketExist() {
        final boolean result = amazonS3Client.doesBucketExist("bucketName");
    }

    public void tryDoesBucketExistV2() {
        final boolean result = amazonS3Client.doesBucketExistV2("bucketName");
    }

    public void tryDoesObjectExist() {
        final boolean result = amazonS3Client.doesObjectExist("bucketName", "objectName");
    }

    public void tryHeadBucket() {
        final HeadBucketRequest headBucketRequest = new HeadBucketRequest("bucketName");
        final HeadBucketResult result = amazonS3Client.headBucket(headBucketRequest);
    }

    public void tryChangeObjectStorageClass() {
        final StorageClass newStorageClass = null;
        amazonS3Client.changeObjectStorageClass("bucketName", "key", newStorageClass);
    }

    public void trySetObjectRedirectLocation() {
        amazonS3Client.setObjectRedirectLocation("bucketName", "key", "newRedirectLocation");
    }

    public void tryGetObject1() {
        final GetObjectRequest getObjectRequest = new GetObjectRequest("bucketName", "key");
        final S3Object result = amazonS3Client.getObject(getObjectRequest);
    }

    public void tryGetObject2() {
        final GetObjectRequest getObjectRequest = new GetObjectRequest("bucketName", "key");
        final ObjectMetadata result = amazonS3Client.getObject(getObjectRequest, new File("filename"));
    }

    public void tryGetObjectAsString() {
        final String result = amazonS3Client.getObjectAsString("bucketName", "key");
    }

    public void tryGetObjectTagging() {
        final GetObjectTaggingRequest getObjectTaggingRequest = new GetObjectTaggingRequest("bucketName", "key", "versionId");
        final GetObjectTaggingResult result = amazonS3Client.getObjectTagging(getObjectTaggingRequest);
    }

    public void trySetObjectTagging() {
        final SetObjectTaggingRequest setObjectTaggingRequest = new SetObjectTaggingRequest("bucketName", "key", null);
        final SetObjectTaggingResult result = amazonS3Client.setObjectTagging(setObjectTaggingRequest);
    }

    public void tryDeleteObjectTagging() {
        final DeleteObjectTaggingRequest deleteObjectTaggingRequest = new DeleteObjectTaggingRequest("bucketName", "key");
        final DeleteObjectTaggingResult result = amazonS3Client.deleteObjectTagging(deleteObjectTaggingRequest);
    }

    public void tryDeleteBucket() {
        amazonS3Client.deleteBucket("bucketName");
    }

    public void tryDeleteBucket1() {
        final DeleteBucketRequest deleteBucketRequest = new DeleteBucketRequest("bucketName");
        amazonS3Client.deleteBucket(deleteBucketRequest);
    }

    public void tryPutObject() {
        final PutObjectResult result = amazonS3Client.putObject("bucketName", "key", new File("filename"));
    }

    public void tryPutObject1() {
        final InputStream input = new ByteArrayInputStream("content".getBytes());
        final ObjectMetadata metadata = new ObjectMetadata();
        final PutObjectResult result = amazonS3Client.putObject("bucketName", "key", input, metadata);
    }

    public void tryPutObject2() {
        final PutObjectRequest putObjectRequest = new PutObjectRequest("bucketName", "key", "");
        final PutObjectResult result = amazonS3Client.putObject(putObjectRequest);
    }

    public void tryCopyObject() {
        final CopyObjectResult result = amazonS3Client.copyObject("sourceBucketName", "sourceKey", "destinationBucketName", "destinationKey");
    }

    public void tryCopyObject1() {
        final CopyObjectRequest copyObjectRequest = new CopyObjectRequest("sourceBucketName", "sourceKey", "sourceVersionId", "destinationBucketName", "destinationKey");
        final CopyObjectResult result = amazonS3Client.copyObject(copyObjectRequest);
    }

    public void tryCopyPart() {
        final CopyPartRequest copyPartRequest = new CopyPartRequest();
        final CopyPartResult result = amazonS3Client.copyPart(copyPartRequest);
    }

    public void tryDeleteObject() {
        amazonS3Client.deleteObject("bucketName", "key");
    }

    public void tryDeleteObject1() {
        final DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest("bucketName", "key");
        amazonS3Client.deleteObject(deleteObjectRequest);
    }

    public void tryDeleteObjects() {
        final DeleteObjectsRequest deleteObjectsRequest = new DeleteObjectsRequest("bucketName");
        final DeleteObjectsResult result = amazonS3Client.deleteObjects(deleteObjectsRequest);
    }

    public void tryDeleteVersion() {
        amazonS3Client.deleteVersion("bucketName", "key", "versionId");
    }

    public void tryDeleteVersion1() {
        final DeleteVersionRequest deleteVersionRequest = new DeleteVersionRequest("bucketName", "key", "versionId");
        amazonS3Client.deleteVersion(deleteVersionRequest);
    }

    public void trySetBucketVersioningConfiguration() {
        final SetBucketVersioningConfigurationRequest setBucketVersioningConfigurationRequest = new SetBucketVersioningConfigurationRequest("bucketName", null);
        amazonS3Client.setBucketVersioningConfiguration(setBucketVersioningConfigurationRequest);
    }

    public void tryGetBucketVersioningConfiguration() {
        final BucketVersioningConfiguration result = amazonS3Client.getBucketVersioningConfiguration("bucketName");
    }

    public void tryGetBucketVersioningConfiguration1() {
        final GetBucketVersioningConfigurationRequest getBucketVersioningConfigurationRequest = new GetBucketVersioningConfigurationRequest("bucketName");
        final BucketVersioningConfiguration result = amazonS3Client.getBucketVersioningConfiguration(getBucketVersioningConfigurationRequest);
    }

    public void tryGetBucketWebsiteConfiguration() {
        final BucketWebsiteConfiguration result = amazonS3Client.getBucketWebsiteConfiguration("bucketName");
    }

    public void tryGetBucketWebsiteConfiguration1() {
        final GetBucketWebsiteConfigurationRequest getBucketWebsiteConfigurationRequest = new GetBucketWebsiteConfigurationRequest("bucketName");
        final BucketWebsiteConfiguration result = amazonS3Client.getBucketWebsiteConfiguration(getBucketWebsiteConfigurationRequest);
    }

    public void tryGetBucketLifecycleConfiguration() {
        final BucketLifecycleConfiguration result = amazonS3Client.getBucketLifecycleConfiguration("bucketName");
    }

    public void tryGetBucketLifecycleConfiguration1() {
        final GetBucketLifecycleConfigurationRequest getBucketLifecycleConfigurationRequest = new GetBucketLifecycleConfigurationRequest("bucketName");
        final BucketLifecycleConfiguration result = amazonS3Client.getBucketLifecycleConfiguration(getBucketLifecycleConfigurationRequest);
    }

    public void trySetBucketLifecycleConfiguration() {
        final BucketLifecycleConfiguration bucketLifecycleConfiguration = new BucketLifecycleConfiguration(Arrays.asList());
        amazonS3Client.setBucketLifecycleConfiguration("bucketName", bucketLifecycleConfiguration);
    }

    public void trySetBucketLifecycleConfiguration1() {
        final SetBucketLifecycleConfigurationRequest setBucketLifecycleConfigurationRequest = new SetBucketLifecycleConfigurationRequest("bucketName", null);
        amazonS3Client.setBucketLifecycleConfiguration(setBucketLifecycleConfigurationRequest);
    }

    public void tryDeleteBucketLifecycleConfiguration() {
        amazonS3Client.deleteBucketLifecycleConfiguration("bucketName");
    }

    public void tryDeleteBucketLifecycleConfiguration1() {
        final DeleteBucketLifecycleConfigurationRequest deleteBucketLifecycleConfigurationRequest = new DeleteBucketLifecycleConfigurationRequest("bucketName");
        amazonS3Client.deleteBucketLifecycleConfiguration(deleteBucketLifecycleConfigurationRequest);
    }

    public void tryGetBucketCrossOriginConfiguration() {
        final BucketCrossOriginConfiguration result = amazonS3Client.getBucketCrossOriginConfiguration("bucketName");
    }

    public void tryGetBucketCrossOriginConfiguration1() {
        final GetBucketCrossOriginConfigurationRequest getBucketCrossOriginConfigurationRequest = new GetBucketCrossOriginConfigurationRequest("bucketName");
        final BucketCrossOriginConfiguration result = amazonS3Client.getBucketCrossOriginConfiguration(getBucketCrossOriginConfigurationRequest);
    }

    public void trySetBucketCrossOriginConfiguration() {
        final BucketCrossOriginConfiguration bucketCrossOriginConfiguration = new BucketCrossOriginConfiguration(Arrays.asList());
        amazonS3Client.setBucketCrossOriginConfiguration("bucketName", bucketCrossOriginConfiguration);
    }

    public void trySetBucketCrossOriginConfiguration1() {
        final SetBucketCrossOriginConfigurationRequest setBucketCrossOriginConfigurationRequest = new SetBucketCrossOriginConfigurationRequest("bucketName", null);
        amazonS3Client.setBucketCrossOriginConfiguration(setBucketCrossOriginConfigurationRequest);
    }

    public void tryDeleteBucketCrossOriginConfiguration() {
        amazonS3Client.deleteBucketCrossOriginConfiguration("bucketName");
    }

    public void tryDeleteBucketCrossOriginConfiguration1() {
        final DeleteBucketCrossOriginConfigurationRequest deleteBucketCrossOriginConfigurationRequest = new DeleteBucketCrossOriginConfigurationRequest("bucketName");
        amazonS3Client.deleteBucketCrossOriginConfiguration(deleteBucketCrossOriginConfigurationRequest);
    }

    public void tryGetBucketTaggingConfiguration() {
        final BucketTaggingConfiguration result = amazonS3Client.getBucketTaggingConfiguration("bucketName");
    }

    public void tryGetBucketTaggingConfiguration1() {
        final GetBucketTaggingConfigurationRequest getBucketTaggingConfigurationRequest = new GetBucketTaggingConfigurationRequest("bucketName");
        final BucketTaggingConfiguration result = amazonS3Client.getBucketTaggingConfiguration(getBucketTaggingConfigurationRequest);
    }

    public void trySetBucketTaggingConfiguration() {
        final BucketTaggingConfiguration bucketTaggingConfiguration = new BucketTaggingConfiguration(Arrays.asList());
        amazonS3Client.setBucketTaggingConfiguration("bucketName", bucketTaggingConfiguration);
    }

    public void trySetBucketTaggingConfiguration1() {
        final SetBucketTaggingConfigurationRequest setBucketTaggingConfigurationRequest = new SetBucketTaggingConfigurationRequest("bucketName", null);
        amazonS3Client.setBucketTaggingConfiguration(setBucketTaggingConfigurationRequest);
    }

    public void tryDeleteBucketTaggingConfiguration() {
        amazonS3Client.deleteBucketTaggingConfiguration("bucketName");
    }

    public void tryDeleteBucketTaggingConfiguration1() {
        final DeleteBucketTaggingConfigurationRequest deleteBucketTaggingConfigurationRequest = new DeleteBucketTaggingConfigurationRequest("bucketName");
        amazonS3Client.deleteBucketTaggingConfiguration(deleteBucketTaggingConfigurationRequest);
    }

    public void trySetBucketWebsiteConfiguration() {
        final BucketWebsiteConfiguration configuration = new BucketWebsiteConfiguration("indexDocumentSuffix", "errorDocument");
        amazonS3Client.setBucketWebsiteConfiguration("bucketName", configuration);
    }

    public void trySetBucketWebsiteConfiguration1() {
        final SetBucketWebsiteConfigurationRequest setBucketWebsiteConfigurationRequest = new SetBucketWebsiteConfigurationRequest("bucketName", null);
        amazonS3Client.setBucketWebsiteConfiguration(setBucketWebsiteConfigurationRequest);
    }

    public void tryDeleteBucketWebsiteConfiguration() {
        amazonS3Client.deleteBucketWebsiteConfiguration("bucketName");
    }

    public void tryDeleteBucketWebsiteConfiguration1() {
        final DeleteBucketWebsiteConfigurationRequest deleteBucketWebsiteConfigurationRequest = new DeleteBucketWebsiteConfigurationRequest("bucketName");
        amazonS3Client.deleteBucketWebsiteConfiguration(deleteBucketWebsiteConfigurationRequest);
    }

    public void trySetBucketNotificationConfiguration() {
        final BucketNotificationConfiguration bucketNotificationConfiguration = new BucketNotificationConfiguration();
        amazonS3Client.setBucketNotificationConfiguration("bucketName", bucketNotificationConfiguration);
    }

    public void trySetBucketNotificationConfiguration1() {
        final SetBucketNotificationConfigurationRequest setBucketNotificationConfigurationRequest = new SetBucketNotificationConfigurationRequest("bucketName", null);
        amazonS3Client.setBucketNotificationConfiguration(setBucketNotificationConfigurationRequest);
    }

    public void tryGetBucketNotificationConfiguration() {
        final BucketNotificationConfiguration result = amazonS3Client.getBucketNotificationConfiguration("bucketName");
    }

    public void tryGetBucketNotificationConfiguration1() {
        final GetBucketNotificationConfigurationRequest getBucketNotificationConfigurationRequest = new GetBucketNotificationConfigurationRequest("bucketName");
        final BucketNotificationConfiguration result = amazonS3Client.getBucketNotificationConfiguration(getBucketNotificationConfigurationRequest);
    }

    public void tryGetBucketLoggingConfiguration() {
        final BucketLoggingConfiguration result = amazonS3Client.getBucketLoggingConfiguration("bucketName");
    }

    public void tryGetBucketLoggingConfiguration1() {
        final GetBucketLoggingConfigurationRequest getBucketLoggingConfigurationRequest = new GetBucketLoggingConfigurationRequest("bucketName");
        final BucketLoggingConfiguration result = amazonS3Client.getBucketLoggingConfiguration(getBucketLoggingConfigurationRequest);
    }

    public void trySetBucketLoggingConfiguration() {
        final SetBucketLoggingConfigurationRequest setBucketLoggingConfigurationRequest = new SetBucketLoggingConfigurationRequest("bucketName", null);
        amazonS3Client.setBucketLoggingConfiguration(setBucketLoggingConfigurationRequest);
    }

    public void tryGetBucketAccelerateConfiguration() {
        final BucketAccelerateConfiguration result = amazonS3Client.getBucketAccelerateConfiguration("bucketName");
    }

    public void tryGetBucketAccelerateConfiguration1() {
        final GetBucketAccelerateConfigurationRequest getBucketAccelerateConfigurationRequest = new GetBucketAccelerateConfigurationRequest("bucketName");
        final BucketAccelerateConfiguration result = amazonS3Client.getBucketAccelerateConfiguration(getBucketAccelerateConfigurationRequest);
    }

    public void trySetBucketAccelerateConfiguration() {
        final BucketAccelerateConfiguration accelerateConfiguration = new BucketAccelerateConfiguration("status");
        amazonS3Client.setBucketAccelerateConfiguration("bucketName", accelerateConfiguration);
    }

    public void trySetBucketAccelerateConfiguration1() {
        final SetBucketAccelerateConfigurationRequest setBucketAccelerateConfigurationRequest = new SetBucketAccelerateConfigurationRequest("bucketName", null);
        amazonS3Client.setBucketAccelerateConfiguration(setBucketAccelerateConfigurationRequest);
    }

    public void tryGetBucketPolicy() {
        final BucketPolicy result = amazonS3Client.getBucketPolicy("bucketName");
    }

    public void tryDeleteBucketPolicy() {
        amazonS3Client.deleteBucketPolicy("bucketName");
    }

    public void tryGetBucketPolicy1() {
        final GetBucketPolicyRequest getBucketPolicyRequest = new GetBucketPolicyRequest("bucketName");
        final BucketPolicy result = amazonS3Client.getBucketPolicy(getBucketPolicyRequest);
    }

    public void trySetBucketPolicy() {
        amazonS3Client.setBucketPolicy("bucketName", "policyText");
    }

    public void trySetBucketPolicy1() {
        final SetBucketPolicyRequest setBucketPolicyRequest = new SetBucketPolicyRequest("bucketName", "policyText");
        amazonS3Client.setBucketPolicy(setBucketPolicyRequest);
    }

    public void tryDeleteBucketPolicy1() {
        final DeleteBucketPolicyRequest deleteBucketPolicyRequest = new DeleteBucketPolicyRequest("bucketName");
        amazonS3Client.deleteBucketPolicy(deleteBucketPolicyRequest);
    }

    public void tryDeleteBucketEncryption() {
        final DeleteBucketEncryptionResult result = amazonS3Client.deleteBucketEncryption("bucketName");
    }

    public void tryDeleteBucketEncryption1() {
        final DeleteBucketEncryptionRequest deleteBucketEncryptionRequest = new DeleteBucketEncryptionRequest();
        final DeleteBucketEncryptionResult result = amazonS3Client.deleteBucketEncryption(deleteBucketEncryptionRequest);
    }

    public void tryGetBucketEncryption() {
        final GetBucketEncryptionResult result = amazonS3Client.getBucketEncryption("bucketName");
    }

    public void tryGetBucketEncryption1() {
        final GetBucketEncryptionRequest getBucketEncryptionRequest = new GetBucketEncryptionRequest();
        final GetBucketEncryptionResult result = amazonS3Client.getBucketEncryption(getBucketEncryptionRequest);
    }

    public void trySetBucketEncryption() {
        final SetBucketEncryptionRequest setBucketEncryptionRequest = new SetBucketEncryptionRequest();
        final SetBucketEncryptionResult result = amazonS3Client.setBucketEncryption(setBucketEncryptionRequest);
    }

    public void trySetPublicAccessBlock() {
        final SetPublicAccessBlockRequest setPublicAccessBlockRequest = new SetPublicAccessBlockRequest();
        final SetPublicAccessBlockResult result = amazonS3Client.setPublicAccessBlock(setPublicAccessBlockRequest);
    }

    public void tryGetPublicAccessBlock() {
        final GetPublicAccessBlockRequest getPublicAccessBlockRequest = new GetPublicAccessBlockRequest();
        final GetPublicAccessBlockResult result = amazonS3Client.getPublicAccessBlock(getPublicAccessBlockRequest);
    }

    public void tryDeletePublicAccessBlock() {
        final DeletePublicAccessBlockRequest deletePublicAccessBlockRequest = new DeletePublicAccessBlockRequest();
        final DeletePublicAccessBlockResult result = amazonS3Client.deletePublicAccessBlock(deletePublicAccessBlockRequest);
    }

    public void tryGetBucketPolicyStatus() {
        final GetBucketPolicyStatusRequest getBucketPolicyStatusRequest = new GetBucketPolicyStatusRequest();
        final GetBucketPolicyStatusResult result = amazonS3Client.getBucketPolicyStatus(getBucketPolicyStatusRequest);
    }

    public void trySelectObjectContent() {
        final SelectObjectContentRequest selectRequest = new SelectObjectContentRequest();
        final SelectObjectContentResult result = amazonS3Client.selectObjectContent(selectRequest);
    }

    public void trySetObjectLegalHold() {
        final SetObjectLegalHoldRequest setObjectLegalHoldRequest = new SetObjectLegalHoldRequest();
        final SetObjectLegalHoldResult result = amazonS3Client.setObjectLegalHold(setObjectLegalHoldRequest);
    }

    public void tryGetObjectLegalHold() {
        final GetObjectLegalHoldRequest getObjectLegalHoldRequest = new GetObjectLegalHoldRequest();
        final GetObjectLegalHoldResult result = amazonS3Client.getObjectLegalHold(getObjectLegalHoldRequest);
    }

    public void trySetObjectLockConfiguration() {
        final SetObjectLockConfigurationRequest setObjectLockConfigurationRequest = new SetObjectLockConfigurationRequest();
        final SetObjectLockConfigurationResult result = amazonS3Client.setObjectLockConfiguration(setObjectLockConfigurationRequest);
    }

    public void tryGetObjectLockConfiguration() {
        final GetObjectLockConfigurationRequest getObjectLockConfigurationRequest = new GetObjectLockConfigurationRequest();
        final GetObjectLockConfigurationResult result = amazonS3Client.getObjectLockConfiguration(getObjectLockConfigurationRequest);
    }

    public void trySetObjectRetention() {
        final SetObjectRetentionRequest setObjectRetentionRequest = new SetObjectRetentionRequest();
        final SetObjectRetentionResult result = amazonS3Client.setObjectRetention(setObjectRetentionRequest);
    }

    public void tryGetObjectRetention() {
        final GetObjectRetentionRequest getObjectRetentionRequest = new GetObjectRetentionRequest();
        final GetObjectRetentionResult result = amazonS3Client.getObjectRetention(getObjectRetentionRequest);
    }

    public void tryGeneratePresignedUrl() {
        final URL result = amazonS3Client.generatePresignedUrl("bucketName", "key", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
    }

    public void tryGeneratePresignedUrl1() {
        final HttpMethod method = null;
        final URL result = amazonS3Client.generatePresignedUrl("bucketName", "key", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), method);
    }

    public void tryGeneratePresignedUrl2() {
        final GeneratePresignedUrlRequest req = new GeneratePresignedUrlRequest("bucketName", "key", null);
        final URL result = amazonS3Client.generatePresignedUrl(req);
    }

    public void tryAbortMultipartUpload() {
        final AbortMultipartUploadRequest abortMultipartUploadRequest = new AbortMultipartUploadRequest("bucketName", "key", "uploadId");
        amazonS3Client.abortMultipartUpload(abortMultipartUploadRequest);
    }

    public void tryCompleteMultipartUpload() {
        final CompleteMultipartUploadRequest completeMultipartUploadRequest = new CompleteMultipartUploadRequest("bucketName", "key", "uploadId", Arrays.asList());
        final CompleteMultipartUploadResult result = amazonS3Client.completeMultipartUpload(completeMultipartUploadRequest);
    }

    public void tryInitiateMultipartUpload() {
        final InitiateMultipartUploadRequest initiateMultipartUploadRequest = new InitiateMultipartUploadRequest("bucketName", "key");
        final InitiateMultipartUploadResult result = amazonS3Client.initiateMultipartUpload(initiateMultipartUploadRequest);
    }

    public void tryListMultipartUploads() {
        final ListMultipartUploadsRequest listMultipartUploadsRequest = new ListMultipartUploadsRequest("bucketName");
        final MultipartUploadListing result = amazonS3Client.listMultipartUploads(listMultipartUploadsRequest);
    }

    public void tryListParts() {
        final ListPartsRequest listPartsRequest = new ListPartsRequest("bucketName", "key", "uploadId");
        final PartListing result = amazonS3Client.listParts(listPartsRequest);
    }

    public void tryUploadPart() {
        final UploadPartRequest uploadPartRequest = new UploadPartRequest();
        final UploadPartResult result = amazonS3Client.uploadPart(uploadPartRequest);
    }

    public void tryGetCachedResponseMetadata() {
        final AmazonWebServiceRequest request = null;
        final S3ResponseMetadata result = amazonS3Client.getCachedResponseMetadata(request);
    }

    public void tryRestoreObject() {
        final RestoreObjectRequest restoreObjectRequest = new RestoreObjectRequest("bucketName", "key", 0);
        amazonS3Client.restoreObject(restoreObjectRequest);
    }

    public void tryRestoreObjectV2() {
        final RestoreObjectRequest restoreObjectRequest = new RestoreObjectRequest("bucketName", "key", 0);
        final RestoreObjectResult result = amazonS3Client.restoreObjectV2(restoreObjectRequest);
    }

    public void tryRestoreObject1() {
        amazonS3Client.restoreObject("bucketName", "key", 0);
    }

    public void tryPutObject3() {
        final PutObjectResult result = amazonS3Client.putObject("bucketName", "key", "content");
    }

    public void tryGetResourceUrl() {
        final String result = amazonS3Client.getResourceUrl("bucketName", "key");
    }

    public void tryGetUrl() {
        final URL result = amazonS3Client.getUrl("bucketName", "key");
    }

    public void tryGetRegion() {
        final com.amazonaws.services.s3.model.Region result = amazonS3Client.getRegion();
    }

    public void tryGetRegionName() {
        final String result = amazonS3Client.getRegionName();
    }

    public void tryDownload() {
        final PresignedUrlDownloadRequest presignedUrlDownloadRequest = new PresignedUrlDownloadRequest(null);
        final PresignedUrlDownloadResult result = amazonS3Client.download(presignedUrlDownloadRequest);
    }

    public void tryDownload1() {
        final PresignedUrlDownloadRequest presignedUrlDownloadRequest = new PresignedUrlDownloadRequest(null);
        amazonS3Client.download(presignedUrlDownloadRequest, new File("filename"));
    }

    public void tryUpload() {
        final PresignedUrlUploadRequest presignedUrlUploadRequest = new PresignedUrlUploadRequest(null);
        final PresignedUrlUploadResult result = amazonS3Client.upload(presignedUrlUploadRequest);
    }

    public void tryEnableRequesterPays() {
        amazonS3Client.enableRequesterPays("bucketName");
    }

    public void tryDisableRequesterPays() {
        amazonS3Client.disableRequesterPays("bucketName");
    }

    public void tryIsRequesterPaysEnabled() {
        final boolean result = amazonS3Client.isRequesterPaysEnabled("bucketName");
    }

    public void trySetBucketReplicationConfiguration() {
        final BucketReplicationConfiguration configuration = new BucketReplicationConfiguration();
        amazonS3Client.setBucketReplicationConfiguration("bucketName", configuration);
    }

    public void trySetBucketReplicationConfiguration1() {
        final SetBucketReplicationConfigurationRequest setBucketReplicationConfigurationRequest = new SetBucketReplicationConfigurationRequest();
        amazonS3Client.setBucketReplicationConfiguration(setBucketReplicationConfigurationRequest);
    }

    public void tryGetBucketReplicationConfiguration() {
        final BucketReplicationConfiguration result = amazonS3Client.getBucketReplicationConfiguration("bucketName");
    }

    public void tryGetBucketReplicationConfiguration1() {
        final GetBucketReplicationConfigurationRequest getBucketReplicationConfigurationRequest = new GetBucketReplicationConfigurationRequest("bucketName");
        final BucketReplicationConfiguration result = amazonS3Client.getBucketReplicationConfiguration(getBucketReplicationConfigurationRequest);
    }

    public void tryDeleteBucketReplicationConfiguration() {
        amazonS3Client.deleteBucketReplicationConfiguration("bucketName");
    }

    public void tryDeleteBucketReplicationConfiguration1() {
        final DeleteBucketReplicationConfigurationRequest deleteBucketReplicationConfigurationRequest = new DeleteBucketReplicationConfigurationRequest("bucketName");
        amazonS3Client.deleteBucketReplicationConfiguration(deleteBucketReplicationConfigurationRequest);
    }

    public void tryDeleteBucketMetricsConfiguration() {
        final DeleteBucketMetricsConfigurationResult result = amazonS3Client.deleteBucketMetricsConfiguration("bucketName", "id");
    }

    public void tryDeleteBucketMetricsConfiguration1() {
        final DeleteBucketMetricsConfigurationRequest deleteBucketMetricsConfigurationRequest = new DeleteBucketMetricsConfigurationRequest("bucketName", "id");
        final DeleteBucketMetricsConfigurationResult result = amazonS3Client.deleteBucketMetricsConfiguration(deleteBucketMetricsConfigurationRequest);
    }

    public void tryGetBucketMetricsConfiguration() {
        final GetBucketMetricsConfigurationResult result = amazonS3Client.getBucketMetricsConfiguration("bucketName", "id");
    }

    public void tryGetBucketMetricsConfiguration1() {
        final GetBucketMetricsConfigurationRequest getBucketMetricsConfigurationRequest = new GetBucketMetricsConfigurationRequest("bucketName", "id");
        final GetBucketMetricsConfigurationResult result = amazonS3Client.getBucketMetricsConfiguration(getBucketMetricsConfigurationRequest);
    }

    public void trySetBucketMetricsConfiguration() {
        final MetricsConfiguration metricsConfiguration = new MetricsConfiguration();
        final SetBucketMetricsConfigurationResult result = amazonS3Client.setBucketMetricsConfiguration("bucketName", metricsConfiguration);
    }

    public void trySetBucketMetricsConfiguration1() {
        final SetBucketMetricsConfigurationRequest setBucketMetricsConfigurationRequest = new SetBucketMetricsConfigurationRequest();
        final SetBucketMetricsConfigurationResult result = amazonS3Client.setBucketMetricsConfiguration(setBucketMetricsConfigurationRequest);
    }

    public void tryListBucketMetricsConfigurations() {
        final ListBucketMetricsConfigurationsRequest listBucketMetricsConfigurationsRequest = new ListBucketMetricsConfigurationsRequest();
        final ListBucketMetricsConfigurationsResult result = amazonS3Client.listBucketMetricsConfigurations(listBucketMetricsConfigurationsRequest);
    }

    public void tryDeleteBucketAnalyticsConfiguration() {
        final DeleteBucketAnalyticsConfigurationResult result = amazonS3Client.deleteBucketAnalyticsConfiguration("bucketName", "id");
    }

    public void tryDeleteBucketAnalyticsConfiguration1() {
        final DeleteBucketAnalyticsConfigurationRequest deleteBucketAnalyticsConfigurationRequest = new DeleteBucketAnalyticsConfigurationRequest("bucketName", "id");
        final DeleteBucketAnalyticsConfigurationResult result = amazonS3Client.deleteBucketAnalyticsConfiguration(deleteBucketAnalyticsConfigurationRequest);
    }

    public void tryGetBucketAnalyticsConfiguration() {
        final GetBucketAnalyticsConfigurationResult result = amazonS3Client.getBucketAnalyticsConfiguration("bucketName", "id");
    }

    public void tryGetBucketAnalyticsConfiguration1() {
        final GetBucketAnalyticsConfigurationRequest getBucketAnalyticsConfigurationRequest = new GetBucketAnalyticsConfigurationRequest("bucketName", "id");
        final GetBucketAnalyticsConfigurationResult result = amazonS3Client.getBucketAnalyticsConfiguration(getBucketAnalyticsConfigurationRequest);
    }

    public void trySetBucketAnalyticsConfiguration() {
        final AnalyticsConfiguration analyticsConfiguration = new AnalyticsConfiguration();
        final SetBucketAnalyticsConfigurationResult result = amazonS3Client.setBucketAnalyticsConfiguration("bucketName", analyticsConfiguration);
    }

    public void trySetBucketAnalyticsConfiguration1() {
        final SetBucketAnalyticsConfigurationRequest setBucketAnalyticsConfigurationRequest = new SetBucketAnalyticsConfigurationRequest();
        final SetBucketAnalyticsConfigurationResult result = amazonS3Client.setBucketAnalyticsConfiguration(setBucketAnalyticsConfigurationRequest);
    }

    public void tryListBucketAnalyticsConfigurations() {
        final ListBucketAnalyticsConfigurationsRequest listBucketAnalyticsConfigurationsRequest = new ListBucketAnalyticsConfigurationsRequest();
        final ListBucketAnalyticsConfigurationsResult result = amazonS3Client.listBucketAnalyticsConfigurations(listBucketAnalyticsConfigurationsRequest);
    }

    public void tryDeleteBucketInventoryConfiguration() {
        final DeleteBucketInventoryConfigurationResult result = amazonS3Client.deleteBucketInventoryConfiguration("bucketName", "id");
    }

    public void tryDeleteBucketInventoryConfiguration1() {
        final DeleteBucketInventoryConfigurationRequest deleteBucketInventoryConfigurationRequest = new DeleteBucketInventoryConfigurationRequest("bucketName", "id");
        final DeleteBucketInventoryConfigurationResult result = amazonS3Client.deleteBucketInventoryConfiguration(deleteBucketInventoryConfigurationRequest);
    }

    public void tryGetBucketInventoryConfiguration() {
        final GetBucketInventoryConfigurationResult result = amazonS3Client.getBucketInventoryConfiguration("bucketName", "id");
    }

    public void tryGetBucketInventoryConfiguration1() {
        final GetBucketInventoryConfigurationRequest getBucketInventoryConfigurationRequest = new GetBucketInventoryConfigurationRequest("bucketName", "id");
        final GetBucketInventoryConfigurationResult result = amazonS3Client.getBucketInventoryConfiguration(getBucketInventoryConfigurationRequest);
    }

    public void trySetBucketInventoryConfiguration() {
        final InventoryConfiguration inventoryConfiguration = new InventoryConfiguration();
        final SetBucketInventoryConfigurationResult result = amazonS3Client.setBucketInventoryConfiguration("bucketName", inventoryConfiguration);
    }

    public void trySetBucketInventoryConfiguration1() {
        final SetBucketInventoryConfigurationRequest setBucketInventoryConfigurationRequest = new SetBucketInventoryConfigurationRequest();
        final SetBucketInventoryConfigurationResult result = amazonS3Client.setBucketInventoryConfiguration(setBucketInventoryConfigurationRequest);
    }

    public void tryListBucketInventoryConfigurations() {
        final ListBucketInventoryConfigurationsRequest listBucketInventoryConfigurationsRequest = new ListBucketInventoryConfigurationsRequest();
        final ListBucketInventoryConfigurationsResult result = amazonS3Client.listBucketInventoryConfigurations(listBucketInventoryConfigurationsRequest);
    }

    public void tryWaiters() {
        final AmazonS3Waiters result = amazonS3Client.waiters();
    }

    public void tryBuilder() {
        final AmazonS3ClientBuilder result = AmazonS3Client.builder();
    }
}
