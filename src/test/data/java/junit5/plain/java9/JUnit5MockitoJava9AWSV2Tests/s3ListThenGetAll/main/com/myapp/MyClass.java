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
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.s3.paginators.ListObjectVersionsIterable;
import software.amazon.awssdk.services.s3.paginators.ListObjectsV2Iterable;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyClass {
    private final S3Client s3Client;
    private final MetricsAdapter metricsAdapter;
    private final String bucketName;

    public MyClass(final S3Client s3Client, final MetricsAdapter metricsAdapter, final String bucketName) {
        this.s3Client = s3Client;
        this.metricsAdapter = metricsAdapter;
        this.bucketName = bucketName;
    }

    public List<DataObject> getFirstPage1(final String path) throws ServiceException {
        final List<DataObject> ret = new ArrayList<>();
        final ListObjectsV2Response listObjectsV2Result = s3Client.listObjectsV2(ListObjectsV2Request.builder().bucket(bucketName).prefix(path).build());
        final List<S3Object> objectSummaries = listObjectsV2Result.contents();
        for(final S3Object objectSummary : objectSummaries) {
            ret.add(downloadObject1(objectSummary));
        }
        return ret;
    }

    public List<DataObject> getFirstPage2(final String path) throws ServiceException {
        final ListObjectsV2Response listObjectsV2Result = s3Client.listObjectsV2(ListObjectsV2Request.builder().bucket(bucketName).prefix(path).build());
        final List<S3Object> objectSummaries = listObjectsV2Result.contents();
        final List<DataObject> ret = new ArrayList<>(objectSummaries.size());
        for(final S3Object objectSummary : objectSummaries) {
            ret.add(downloadObject1(objectSummary));
        }
        return ret;
    }

    public List<DataObject> getFirstPage3(final String path) throws ServiceException {
        final List<DataObject> ret = new ArrayList<>();
        final ListObjectsV2Response listObjectsV2Result = s3Client.listObjectsV2(ListObjectsV2Request.builder().bucket(bucketName).prefix(path).build());
        for(final S3Object objectSummary : listObjectsV2Result.contents()) {
            ret.add(downloadObject1(objectSummary));
        }
        return ret;
    }

    public Set<DataObject> getAllObjects1(final String path) throws ServiceException {
        final Set<DataObject> ret = new HashSet<>();
        String nextContinuationToken = null;
        ListObjectsV2Response listObjectsV2Result;
        do {
            final ListObjectsV2Request request = ListObjectsV2Request.builder().bucket(bucketName)
                    .prefix(path).continuationToken(nextContinuationToken).build();
            listObjectsV2Result = s3Client.listObjectsV2(request);
            final List<S3Object> objectSummaries = listObjectsV2Result.contents();
            for (final S3Object objectSummary : objectSummaries) {
                ret.add(downloadObject1(objectSummary));
            }
            nextContinuationToken = listObjectsV2Result.nextContinuationToken();
        } while(listObjectsV2Result.isTruncated() && nextContinuationToken != null);
        return ret;
    }

    public Set<DataObject> getAllObjects2(final String path) throws ServiceException {
        final Set<DataObject> ret = new HashSet<>();
        String marker = null;
        ListObjectsResponse objectListing;
        do {
            final ListObjectsRequest request = ListObjectsRequest.builder().bucket(bucketName)
                    .prefix(path).marker(marker).build();
            objectListing = s3Client.listObjects(request);
            final List<S3Object> objectSummaries = objectListing.contents();
            for (final S3Object objectSummary : objectSummaries) {
                ret.add(downloadObject1(objectSummary));
            }
            marker = objectListing.nextMarker();
        } while(objectListing.isTruncated() && marker != null);
        return ret;
    }

    public Set<DataObject> getAllObjects3(final String path) throws ServiceException {
        final Set<DataObject> ret = new HashSet<>();
        String keyMarker = null;
        String versionMarker = null;
        ListObjectVersionsResponse versionListing;
        do {
            final ListObjectVersionsRequest request = ListObjectVersionsRequest.builder().bucket(bucketName)
                    .prefix(path).keyMarker(keyMarker).versionIdMarker(versionMarker).build();
            versionListing = s3Client.listObjectVersions(request);
            final List<ObjectVersion> objectSummaries = versionListing.versions();
            for (final ObjectVersion objectSummary : objectSummaries) {
                ret.add(downloadObject2(objectSummary));
            }
            keyMarker = versionListing.nextKeyMarker();
            versionMarker = versionListing.nextVersionIdMarker();
        } while(versionListing.isTruncated() && (keyMarker != null || versionMarker != null));
        return ret;
    }

    public List<DataObject> getAllObjects4(final String path) throws ServiceException {
        final List<DataObject> ret = new ArrayList<>();
        final ListObjectsV2Iterable listObjectsV2Responses = s3Client.listObjectsV2Paginator(ListObjectsV2Request.builder().bucket(bucketName).prefix(path).build());
        for(final ListObjectsV2Response response : listObjectsV2Responses) {
            for(final S3Object objectSummary : response.contents()) {
                ret.add(downloadObject1(objectSummary));
            }
        }
        return ret;
    }

    public List<DataObject> getAllObjects5(final String path) throws ServiceException {
        final List<DataObject> ret = new ArrayList<>();
        final ListObjectVersionsIterable listObjectsV2Responses = s3Client.listObjectVersionsPaginator(ListObjectVersionsRequest.builder().bucket(bucketName).prefix(path).build());
        for(final ListObjectVersionsResponse response : listObjectsV2Responses) {
            for(final ObjectVersion objectSummary : response.versions()) {
                ret.add(downloadObject2(objectSummary));
            }
        }
        return ret;
    }

    public List<DataObject> getAllObjects6(final String path) {
        final List<DataObject> ret = new ArrayList<>();
        final ListObjectsV2Iterable listObjectsV2Responses = s3Client.listObjectsV2Paginator(ListObjectsV2Request.builder().bucket(bucketName).prefix(path).build());
        for(final ListObjectsV2Response response : listObjectsV2Responses) {
            for(final S3Object objectSummary : response.contents()) {
                ret.add(downloadObject3(objectSummary));
            }
        }
        return ret;
    }

    private DataObject downloadObject3(final S3Object objectSummary) {
        final String objectKey = objectSummary.key();
        final ResponseBytes<GetObjectResponse> objectAsBytes = s3Client.getObjectAsBytes(GetObjectRequest.builder().bucket(bucketName).key(objectKey).build());
        return new DataObject(objectKey, objectAsBytes.asUtf8String());
    }

    private DataObject downloadObject2(final ObjectVersion versionSummary) throws ServiceException {
        final String objectKey = versionSummary.key();
        final String versionId = versionSummary.versionId();
        try(final ResponseInputStream<GetObjectResponse> s3Object = s3Client.getObject(GetObjectRequest.builder().bucket(bucketName).key(objectKey).versionId(versionId).build())) {
            return new DataObject(objectKey, new String(s3Object.readAllBytes(), StandardCharsets.UTF_8));
        } catch (final IOException e) {
            metricsAdapter.recordIOException(objectKey, e);
            throw new ServiceException(e);
        } catch (final S3Exception e) {
            metricsAdapter.recordAmazonServiceException(objectKey, e);
            throw new ServiceException(e);
        } catch (final SdkClientException e) {
            metricsAdapter.recordClientException(objectKey, e);
            throw new ServiceException(e);
        }
    }

    private DataObject downloadObject1(final S3Object objectSummary) throws ServiceException {
        final String objectKey = objectSummary.key();
        try(final ResponseInputStream<GetObjectResponse> s3Object = s3Client.getObject(GetObjectRequest.builder().bucket(bucketName).key(objectKey).build())) {
            return new DataObject(objectKey, new String(s3Object.readAllBytes(), StandardCharsets.UTF_8));
        } catch (final IOException e) {
            metricsAdapter.recordIOException(objectKey, e);
            throw new ServiceException(e);
        } catch (final S3Exception e) {
            metricsAdapter.recordAmazonServiceException(objectKey, e);
            throw new ServiceException(e);
        } catch (final SdkClientException e) {
            metricsAdapter.recordClientException(objectKey, e);
            throw new ServiceException(e);
        }
    }
}
