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
package com.squaretest.demo1;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import com.squaretest.demo1.metrics.MetricAdapter;
import com.squaretest.demo1.metrics.MetricEvent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class S3Adapter {

    private static final Logger Log = Logger.getLogger(S3Adapter.class.getName());

    @Nonnull
    private final AmazonS3Client s3Client;
    @Nonnull
    private final MetricAdapter metricAdapter;

    @Nonnull
    private final String bucketName;

    public S3Adapter(
            @Nonnull final AmazonS3Client s3Client,
            @Nonnull MetricAdapter metricAdapter,
            @Nonnull final String bucketName) {
        this.s3Client = s3Client;
        this.metricAdapter = metricAdapter;
        this.bucketName = bucketName;
    }

    @Nullable
    public Artifact getArtifact(final String path) {
        try (final S3Object s3Object = s3Client.getObject(new GetObjectRequest(bucketName, path))) {
            if (s3Object == null) {
                metricAdapter.recordEvent(MetricEvent.NoObjectFound);
                return null;
            }
            final String objString = IOUtils.toString(s3Object.getObjectContent());
            metricAdapter.recordEvent(MetricEvent.Success);
            return new Artifact(path, objString);
        } catch (final AmazonServiceException e) {
            metricAdapter.recordEvent(MetricEvent.AmazonServiceException);
            logException(path, e);
            return null;
        } catch (final SdkClientException e) {
            metricAdapter.recordEvent(MetricEvent.SDKException);
            logException(path, e);
            return null;
        } catch (final IOException e) {
            metricAdapter.recordEvent(MetricEvent.IOException);
            logException(path, e);
            return null;
        }
    }

    @Nullable
    public void deleteArtifact(final String path) {
        s3Client.deleteObject(new DeleteObjectRequest(bucketName, path));
    }

    private static void logException(final String path, final Exception e) {
        Log.log(Level.WARNING, "Exception downloading object with path: " + path, e);
    }
}
