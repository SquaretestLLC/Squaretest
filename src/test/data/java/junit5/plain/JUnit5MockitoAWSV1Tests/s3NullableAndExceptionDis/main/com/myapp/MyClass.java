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

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.squaretest.demo1.Artifact;
import com.squaretest.demo1.S3Adapter;
import com.squaretest.demo1.metrics.MetricAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyClass extends S3Adapter {

    private final AmazonS3 amazonS3;
    private final String bucket;

    public MyClass(final AmazonS3Client s3Client, final MetricAdapter metricAdapter, final String bucketName) {
        super(s3Client, metricAdapter, bucketName);
        this.amazonS3 = s3Client;
        this.bucket = bucketName;
    }

    @Override
    public com.squaretest.demo1.Artifact getArtifact(final String path) {
        final com.squaretest.demo1.Artifact superArtifact = super.getArtifact(path);
        if(superArtifact != null) {
            return superArtifact;
        }
        return super.getArtifact("old_" + path);
    }

    public List<com.squaretest.demo1.Artifact> getArtifacts(final String path) {
        final ListObjectsV2Result listObjectsV2Result = amazonS3.listObjectsV2(bucket, path);
        final List<Artifact> ret = new ArrayList<>(listObjectsV2Result.getKeyCount());
        for(final S3ObjectSummary summary : listObjectsV2Result.getObjectSummaries()){
            if(shouldAdd(summary)) {
                ret.add(getArtifact(summary.getKey()));
            }
        }
        return ret;
    }

    private boolean shouldAdd(final S3ObjectSummary summary) {
        return !summary.getETag().isEmpty();
    }
}
