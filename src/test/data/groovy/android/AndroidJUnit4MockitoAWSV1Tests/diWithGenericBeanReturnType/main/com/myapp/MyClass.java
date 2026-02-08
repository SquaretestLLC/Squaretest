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
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.InternalServerErrorException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import com.myapp.metrics.MetricAdapter;
import com.myapp.orders.StoredOrder;
import com.myapp.metrics.MetricEvent;

import javax.annotation.Nonnull;
import java.io.IOException;

public class MyClass {

    @Nonnull
    private final DynamoDBMapper dynamoDBMapper;
    @Nonnull
    private final AmazonS3Client s3Client;
    @Nonnull
    private final MetricAdapter metricAdapter;

    @Nonnull
    private final String bucketName;

    public MyClass(
            @Nonnull final DynamoDBMapper dynamoDBMapper,
            @Nonnull final AmazonS3Client s3Client,
            @Nonnull final MetricAdapter metricAdapter,
            @Nonnull final String bucketName) {
        this.dynamoDBMapper = dynamoDBMapper;
        this.s3Client = s3Client;
        this.metricAdapter = metricAdapter;
        this.bucketName = bucketName;
    }

    @Nonnull
    public StoredOrder getOrder(final String orderId) throws OrderStoreException {
        final StoredOrder order = getOrderFromDynamoDB(orderId);
        final String orderDoc = getOrderDoc(order.getLoanDocumentS3Path()).getContent();
        order.setLoanDocumentText(orderDoc);
        return order;
    }

    @Nonnull
    private StoredOrder getOrderFromDynamoDB(final String orderId) throws OrderStoreException {
        try {
            final StoredOrder order = dynamoDBMapper.load(StoredOrder.class, orderId);
            if (order == null) {
                metricAdapter.recordEvent(MetricEvent.NoOrderFound);
                throw new OrderStoreException(String.format("No order with id:%s exists", orderId));
            }
            metricAdapter.recordEvent(MetricEvent.Success);
            return order;
        } catch (final InternalServerErrorException e) {
            metricAdapter.recordEvent(MetricEvent.DynamoDBServiceException);
            throw new OrderStoreException(
                    String.format("DynamoDB InternalServerErrorException retrieving order: %s", orderId), e);
        } catch (final SdkClientException e) {
            metricAdapter.recordEvent(MetricEvent.SDKException);
            throw new OrderStoreException(
                    String.format("SdkClientException while retrieving order: %s", orderId), e);
        }
    }

    @Nonnull
    private Artifact getOrderDoc(final String path) throws OrderStoreException {
        try (final S3Object s3Object = s3Client.getObject(bucketName, path)) {
            if (s3Object == null) {
                metricAdapter.recordEvent(MetricEvent.NoOrderFound);
                throw new OrderStoreException(String.format("S3Object with path: %s not found", path));
            }
            final String objString = IOUtils.toString(s3Object.getObjectContent());
            metricAdapter.recordEvent(MetricEvent.Success);
            return new Artifact(path, objString);
        } catch (final AmazonServiceException e) {
            metricAdapter.recordEvent(MetricEvent.AmazonServiceException);
            throw new OrderStoreException(
                    String.format("AmazonServiceException while downloading object with path: %s", path), e);
        } catch (final SdkClientException e) {
            metricAdapter.recordEvent(MetricEvent.SDKException);
            throw new OrderStoreException(
                    String.format("SdkClientException while downloading object with path: %s", path), e);
        } catch (final IOException e) {
            metricAdapter.recordEvent(MetricEvent.IOException);
            throw new OrderStoreException(
                    String.format("IOException while downloading object with path: %s", path), e);
        }
    }
}
