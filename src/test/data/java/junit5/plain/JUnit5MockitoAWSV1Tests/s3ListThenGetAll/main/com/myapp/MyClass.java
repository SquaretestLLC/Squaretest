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

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyClass {
    private final AmazonS3 s3Client;
    private final MetricsAdapter metricsAdapter;
    private final String bucketName;

    public MyClass(final AmazonS3 s3Client, final MetricsAdapter metricsAdapter, final String bucketName) {
        this.s3Client = s3Client;
        this.metricsAdapter = metricsAdapter;
        this.bucketName = bucketName;
    }

    public List<DataObject> getFirstPage1(final String path) throws ServiceException {
        final List<DataObject> ret = new ArrayList<>();
        final ListObjectsV2Result listObjectsV2Result = s3Client.listObjectsV2(bucketName, path);
        final List<S3ObjectSummary> objectSummaries = listObjectsV2Result.getObjectSummaries();
        for(final S3ObjectSummary objectSummary : objectSummaries) {
            ret.add(downloadObject1(objectSummary));
        }
        return ret;
    }

    public Set<DataObject> getAllObjects1(final String path) throws ServiceException {
        final Set<DataObject> ret = new HashSet<>();
        String nextContinuationToken = null;
        ListObjectsV2Result listObjectsV2Result;
        do {
            final ListObjectsV2Request request = new ListObjectsV2Request().withBucketName(bucketName)
                    .withPrefix(path).withContinuationToken(nextContinuationToken);
            listObjectsV2Result = s3Client.listObjectsV2(request);
            final List<S3ObjectSummary> objectSummaries = listObjectsV2Result.getObjectSummaries();
            for (final S3ObjectSummary objectSummary : objectSummaries) {
                ret.add(downloadObject1(objectSummary));
            }
            nextContinuationToken = listObjectsV2Result.getNextContinuationToken();
        } while(listObjectsV2Result.isTruncated() && nextContinuationToken != null);
        return ret;
    }

    public Set<DataObject> getAllObjects2(final String path) throws ServiceException {
        final Set<DataObject> ret = new HashSet<>();
        String marker = null;
        ObjectListing objectListing;
        do {
            final ListObjectsRequest request = new ListObjectsRequest().withBucketName(bucketName)
                    .withPrefix(path).withMarker(marker);
            objectListing = s3Client.listObjects(request);
            final List<S3ObjectSummary> objectSummaries = objectListing.getObjectSummaries();
            for (final S3ObjectSummary objectSummary : objectSummaries) {
                ret.add(downloadObject1(objectSummary));
            }
            marker = objectListing.getNextMarker();
        } while(objectListing.isTruncated() && marker != null);
        return ret;
    }

    public Set<DataObject> getAllObjects3(final String path) throws ServiceException {
        final Set<DataObject> ret = new HashSet<>();
        String keyMarker = null;
        String versionMarker = null;
        VersionListing versionListing;
        do {
            final ListVersionsRequest request = new ListVersionsRequest().withBucketName(bucketName)
                    .withPrefix(path).withKeyMarker(keyMarker).withVersionIdMarker(versionMarker);
            versionListing = s3Client.listVersions(request);
            final List<S3VersionSummary> objectSummaries = versionListing.getVersionSummaries();
            for (final S3VersionSummary objectSummary : objectSummaries) {
                ret.add(downloadObject2(objectSummary));
            }
            keyMarker = versionListing.getNextKeyMarker();
            versionMarker = versionListing.getNextVersionIdMarker();
        } while(versionListing.isTruncated() && (keyMarker != null || versionMarker != null));
        return ret;
    }

    private DataObject downloadObject2(final S3VersionSummary versionSummary) throws ServiceException {
        final String objectKey = versionSummary.getKey();
        final GetObjectRequest request = new GetObjectRequest(bucketName, objectKey).withVersionId(versionSummary.getVersionId());
        try(final S3Object s3Object = s3Client.getObject(request)) {
            return new DataObject(objectKey, IOUtils.toString(s3Object.getObjectContent()));
        } catch (final IOException e) {
            metricsAdapter.recordIOException(objectKey, e);
            throw new ServiceException(e);
        } catch (final AmazonServiceException e) {
            metricsAdapter.recordAmazonServiceException(objectKey, e);
            throw new ServiceException(e);
        } catch (final SdkClientException e) {
            metricsAdapter.recordClientException(objectKey, e);
            throw new ServiceException(e);
        }
    }

    private DataObject downloadObject1(final S3ObjectSummary objectSummary) throws ServiceException {
        final String objectKey = objectSummary.getKey();
        try(final S3Object s3Object = s3Client.getObject(bucketName, objectKey)) {
            return new DataObject(objectKey, IOUtils.toString(s3Object.getObjectContent()));
        } catch (final IOException e) {
            metricsAdapter.recordIOException(objectKey, e);
            throw new ServiceException(e);
        } catch (final AmazonServiceException e) {
            metricsAdapter.recordAmazonServiceException(objectKey, e);
            throw new ServiceException(e);
        } catch (final SdkClientException e) {
            metricsAdapter.recordClientException(objectKey, e);
            throw new ServiceException(e);
        }
    }
}
