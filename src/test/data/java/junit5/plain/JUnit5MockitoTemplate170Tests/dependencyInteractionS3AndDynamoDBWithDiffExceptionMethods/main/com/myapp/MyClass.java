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
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import com.myapp.metrics.MetricAdapter;
import com.myapp.metrics.MetricEvent;
import com.myapp.other.FooService;
import com.myapp.orders.StoredOrder;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;

public class MyClass {

    @Nonnull
    private final DynamoDBMapper dynamoDBMapper;
    @Nonnull
    private final AmazonS3Client s3Client;
    @Nonnull
    private final MetricAdapter metricAdapter;
    @Nonnull
    private final FooService fooService;
    @Nonnull
    private final String bucketName;

    public MyClass(@Nonnull final DynamoDBMapper dynamoDBMapper,
                   @Nonnull AmazonS3Client s3Client,
                   @Nonnull String bucketName, final @Nonnull MetricAdapter metricAdapter, final @Nonnull FooService fooService) {
        this.dynamoDBMapper = dynamoDBMapper;
        this.s3Client = s3Client;
        this.bucketName = bucketName;
        this.metricAdapter = metricAdapter;
        this.fooService = fooService;
    }

    public Object getTheNull() {
        return dynamoDBMapper.load(null);
    }

    @Nullable
    public StoredOrder getOrder(final String orderId) {
        final StoredOrder order = getOrderFromDynamoDB(orderId);
        if (order == null) {
            return null;
        }
        final String loanDoc = getLoanDocumentForOrder(order);
        if (loanDoc != null) {
            order.setLoanDocumentText(loanDoc);
            return order;
        }
        return null;
    }

    @Nullable
    private String getLoanDocumentForOrder(StoredOrder order) {
        S3Object s3Object = null;
        try {
            s3Object = s3Client.getObject(new GetObjectRequest(bucketName, order.getLoanDocumentS3Path()));
            if (s3Object == null) {
                metricAdapter.recordEvent(MetricEvent.NoObjectFound);
                return null;
            }
            final String objString = IOUtils.toString(s3Object.getObjectContent());
            metricAdapter.recordEvent(MetricEvent.Success);
            return objString;
        } catch (final AmazonServiceException e) {
            metricAdapter.recordAmazonServiceException(order.getPurchaseId());
            return null;
        } catch (final SdkClientException e) {
            metricAdapter.recordSDKClientException(order.getPurchaseId());
            return null;
        } catch (final IOException e) {
            metricAdapter.recordIOException(order.getPurchaseId());
            return null;
        } finally {
            IOUtils.closeQuietly(s3Object, null);
            metricAdapter.recordFinallyBlock(order.getPurchaseId());
        }
    }

    private StoredOrder getOrderFromDynamoDB(final String orderId) {
        try {
            final String activatedOrderId = fooService.activateBar(computeCodeForOrder(orderId));
            final StoredOrder order = dynamoDBMapper.load(StoredOrder.class, activatedOrderId);
            metricAdapter.recordEvent(MetricEvent.Success);
            order.setCreator(fooService.doSomething(order.getPurchaseId()));
            return order;
        } catch (final AmazonServiceException e) {
            metricAdapter.recordEvent(MetricEvent.S3Exception);
            return null;
        } catch (final SdkClientException e) {
            metricAdapter.recordEvent(MetricEvent.SDKException);
            return null;
        }
    }

    private String computeCodeForOrder(String orderId) {
        return StringUtils.capitalize(orderId);
    }

    public StoredOrder getOrderWithExistingOrder(final StoredOrder order) {
        return dynamoDBMapper.load(order);
    }

    public StoredOrder getOrderWithExistingOrderAndConfig(final StoredOrder order) {
        return dynamoDBMapper.load(order, DynamoDBMapperConfig.DEFAULT);
    }

    public StoredOrder getOrderWithConfig(final String orderId) {
        return dynamoDBMapper.load(StoredOrder.class, orderId, DynamoDBMapperConfig.DEFAULT);
    }

    public StoredOrder getOrderWithRangeKey(final String orderId, final String rangeKeyValue) {
        return dynamoDBMapper.load(StoredOrder.class, orderId, rangeKeyValue);
    }

    public StoredOrder getOrderWithRangeKeyAndConfig(final String orderId, final String rangeKeyValue) {
        return dynamoDBMapper.load(StoredOrder.class, orderId, rangeKeyValue, DynamoDBMapperConfig.DEFAULT);
    }

    public StoredOrder getOrderWithWeirdGenerics(final String orderId) {
        final Class<? extends StoredOrder> theClass = StoredOrder.class;
        return dynamoDBMapper.load(theClass, orderId);
    }
}
