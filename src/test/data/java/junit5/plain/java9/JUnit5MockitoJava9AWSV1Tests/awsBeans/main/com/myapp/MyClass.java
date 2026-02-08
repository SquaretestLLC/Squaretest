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

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.*;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ListBucketMetricsConfigurationsRequest;
import com.amazonaws.services.s3.model.ListBucketMetricsConfigurationsResult;
import com.amazonaws.services.s3.model.metrics.MetricsConfiguration;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;

import java.util.List;
import java.util.Map;

public class MyClass {
    private final AmazonS3 s3Client;
    private final AmazonDynamoDB amazonDynamoDB;
    private final AmazonSQS amazonSQS;

    public MyClass(
            final AmazonS3 s3Client, final AmazonDynamoDB amazonDynamoDB,
            final AmazonSQS amazonSQS) {
        this.s3Client = s3Client;
        this.amazonDynamoDB = amazonDynamoDB;
        this.amazonSQS = amazonSQS;
    }

    public String tryListBucketMetricConfig(final String bucketName) {
        final StringBuilder ret = new StringBuilder();
        final ListBucketMetricsConfigurationsRequest listBucketConfigurationsRequest = new ListBucketMetricsConfigurationsRequest();
        listBucketConfigurationsRequest.setBucketName(bucketName);
        final ListBucketMetricsConfigurationsResult listBucketMetricsConfigurationsResult = s3Client.listBucketMetricsConfigurations(listBucketConfigurationsRequest);
        ret.append(listBucketMetricsConfigurationsResult.isTruncated());
        ret.append(listBucketMetricsConfigurationsResult.getContinuationToken());
        ret.append(listBucketMetricsConfigurationsResult.getNextContinuationToken());
        final List<MetricsConfiguration> metricsConfigurationList = listBucketMetricsConfigurationsResult.getMetricsConfigurationList();
        for(final MetricsConfiguration metricsConfiguration : metricsConfigurationList) {
            ret.append(metricsConfiguration.getId());
            ret.append(metricsConfiguration.getFilter());
        }
        return ret.toString();
    }

    public String tryDeleteItem(final String tableName, final String id) {
        final DeleteItemResult deleteItemResult = amazonDynamoDB.deleteItem(new DeleteItemRequest().withTableName(tableName).addKeyEntry("HashKey", new AttributeValue(id)));
        final StringBuilder ret = new StringBuilder();
        ret.append(deleteItemResult.getAttributes());
        ret.append(deleteItemResult.getItemCollectionMetrics());
        ret.append(deleteItemResult.getConsumedCapacity());
        return ret.toString();
    }

    public String tryGetItem(final String tableName, final String id) {
        final GetItemResult getItemResult = amazonDynamoDB.getItem(new GetItemRequest(tableName, Map.of("HashKey", new AttributeValue(id)), false));
        final StringBuilder ret = new StringBuilder();
        ret.append(getItemResult.getItem());
        ret.append(getItemResult.getConsumedCapacity());
        return ret.toString();
    }

    public String tryPutItem(final String tableName, final Map<String, AttributeValue> item) {
        final PutItemResult putItemResult = amazonDynamoDB.putItem(new PutItemRequest(tableName, item));
        final StringBuilder ret = new StringBuilder();
        ret.append(putItemResult.getAttributes());
        ret.append(putItemResult.getItemCollectionMetrics());
        return ret.toString();
    }

    public String tryQuery(final String tableName, final String id) {
        final QueryRequest queryRequest = new QueryRequest(tableName);
        queryRequest.withSelect("ALL_ATTRIBUTES");
        queryRequest.withKeyConditionExpression("some filter expression");
        final QueryResult queryResult = amazonDynamoDB.query(queryRequest);
        final StringBuilder ret = new StringBuilder();
        ret.append(queryResult.getItems());
        ret.append(queryResult.getConsumedCapacity());
        ret.append(queryResult.getScannedCount());
        ret.append(queryResult.getLastEvaluatedKey());
        return ret.toString();
    }

    public String tryGetSqsMessage(final String queueUri) {
        final ReceiveMessageResult receiveMessageResult = amazonSQS.receiveMessage(queueUri);
        final List<Message> messages = receiveMessageResult.getMessages();
        final StringBuilder ret = new StringBuilder();
        for(final Message message : messages) {
            ret.append(message.getMessageId());
            ret.append(message.getBody());
            ret.append(message.getAttributes());
            ret.append(message.getReceiptHandle());
        }
        return ret.toString();
    }
}
