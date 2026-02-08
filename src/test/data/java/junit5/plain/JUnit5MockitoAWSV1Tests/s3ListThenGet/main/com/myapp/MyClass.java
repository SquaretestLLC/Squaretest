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
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.util.IOUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    private DataObject downloadObject1(final S3ObjectSummary objectSummary) throws ServiceException {
        final String objectKey = objectSummary.getKey();
        try(final S3Object s3Object = s3Client.getObject(bucketName, objectKey)) {
            ObjectMetadata objectMetadata = s3Object.getObjectMetadata();
            final String specialValue = objectMetadata.getUserMetadata().get("SPECIAL_VALUE");
            return new DataObject(s3Object.getKey(), IOUtils.toString(s3Object.getObjectContent()), specialValue);
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
