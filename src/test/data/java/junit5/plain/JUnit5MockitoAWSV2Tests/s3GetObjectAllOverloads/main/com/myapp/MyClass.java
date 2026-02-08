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
import com.amazonaws.util.IOUtils;
import com.myapp.metrics.MetricAdapter;
import com.myapp.metrics.MetricEvent;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.NoSuchKeyException;
import software.amazon.awssdk.services.s3.model.S3Exception;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyClass {

    private static final Logger Log = Logger.getLogger(MyClass.class.getName());

    @Nonnull
    private final S3Client s3Client;
    @Nonnull
    private final MetricAdapter metricAdapter;

    @Nonnull
    private final String bucketName;

    public MyClass(
            @Nonnull final S3Client s3Client,
            @Nonnull MetricAdapter metricAdapter,
            @Nonnull final String bucketName) {
        this.s3Client = s3Client;
        this.metricAdapter = metricAdapter;
        this.bucketName = bucketName;
    }

    @Nullable
    public Artifact getArtifact(final String path) {
        final GetObjectRequest request = GetObjectRequest.builder().bucket(bucketName).key(path).build();
        try (final ResponseInputStream<GetObjectResponse> s3Object = s3Client.getObject(request)) {
            final String objString = IOUtils.toString(s3Object);
            metricAdapter.recordEvent(MetricEvent.Success);
            return new Artifact(path, objString);
        } catch (final NoSuchKeyException e) {
            metricAdapter.recordEvent(MetricEvent.NoObjectFound);
            logException(path, e);
            return null;
        } catch (final S3Exception e) {
            metricAdapter.recordEvent(MetricEvent.S3Exception);
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
    public Artifact getArtifactWithConsumer(final String path) {
        final Consumer<GetObjectRequest.Builder> consumer = builder -> builder.bucket(bucketName).key(path);

        try (final ResponseInputStream<GetObjectResponse> s3Object = s3Client.getObject(consumer)) {
            final String objString = IOUtils.toString(s3Object);
            metricAdapter.recordEvent(MetricEvent.Success);
            return new Artifact(path, objString);
        } catch (final NoSuchKeyException e) {
            metricAdapter.recordEvent(MetricEvent.NoObjectFound);
            logException(path, e);
            return null;
        } catch (final S3Exception e) {
            metricAdapter.recordEvent(MetricEvent.S3Exception);
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

    private static void logException(final String path, final Exception e) {
        Log.log(Level.WARNING, "Exception downloading object with path: " + path, e);
    }
}
