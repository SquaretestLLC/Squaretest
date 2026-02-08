package com.myapp;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.*;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ListBucketMetricsConfigurationsRequest;
import com.amazonaws.services.s3.model.ListBucketMetricsConfigurationsResult;
import com.amazonaws.services.s3.model.metrics.MetricsConfiguration;
import com.amazonaws.services.s3.model.metrics.MetricsFilter;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private AmazonS3 mockS3Client;
    @Mock
    private AmazonDynamoDB mockAmazonDynamoDB;
    @Mock
    private AmazonSQS mockAmazonSQS;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockS3Client, mockAmazonDynamoDB, mockAmazonSQS);
    }

    @Test
    void testTryListBucketMetricConfig() {
        // Setup
        // Configure AmazonS3.listBucketMetricsConfigurations(...).
        final ListBucketMetricsConfigurationsResult listBucketMetricsConfigurationsResult = new ListBucketMetricsConfigurationsResult();
        final MetricsConfiguration metricsConfiguration = new MetricsConfiguration();
        metricsConfiguration.setId("id");
        metricsConfiguration.setFilter(new MetricsFilter(null));
        listBucketMetricsConfigurationsResult.setMetricsConfigurationList(List.of(metricsConfiguration));
        listBucketMetricsConfigurationsResult.setTruncated(false);
        listBucketMetricsConfigurationsResult.setContinuationToken("continuationToken");
        listBucketMetricsConfigurationsResult.setNextContinuationToken("nextContinuationToken");
        when(mockS3Client.listBucketMetricsConfigurations(
                any(ListBucketMetricsConfigurationsRequest.class))).thenReturn(listBucketMetricsConfigurationsResult);

        // Run the test
        final String result = myClassUnderTest.tryListBucketMetricConfig("bucketName");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testTryListBucketMetricConfig_AmazonS3ReturnsNoItems() {
        // Setup
        // Configure AmazonS3.listBucketMetricsConfigurations(...).
        final ListBucketMetricsConfigurationsResult listBucketMetricsConfigurationsResult = new ListBucketMetricsConfigurationsResult();
        when(mockS3Client.listBucketMetricsConfigurations(
                any(ListBucketMetricsConfigurationsRequest.class))).thenReturn(listBucketMetricsConfigurationsResult);

        // Run the test
        final String result = myClassUnderTest.tryListBucketMetricConfig("bucketName");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testTryListBucketMetricConfig_AmazonS3ThrowsAmazonServiceException() {
        // Setup
        when(mockS3Client.listBucketMetricsConfigurations(any(ListBucketMetricsConfigurationsRequest.class)))
                .thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryListBucketMetricConfig("bucketName"));
    }

    @Test
    void testTryListBucketMetricConfig_AmazonS3ThrowsSdkClientException() {
        // Setup
        when(mockS3Client.listBucketMetricsConfigurations(any(ListBucketMetricsConfigurationsRequest.class)))
                .thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListBucketMetricConfig("bucketName"));
    }

    @Test
    void testTryDeleteItem() {
        // Setup
        // Configure AmazonDynamoDB.deleteItem(...).
        final DeleteItemResult deleteItemResult = new DeleteItemResult();
        deleteItemResult.setAttributes(Map.ofEntries(Map.entry("value", new AttributeValue("s"))));
        final ConsumedCapacity consumedCapacity = new ConsumedCapacity();
        deleteItemResult.setConsumedCapacity(consumedCapacity);
        final ItemCollectionMetrics itemCollectionMetrics = new ItemCollectionMetrics();
        deleteItemResult.setItemCollectionMetrics(itemCollectionMetrics);
        when(mockAmazonDynamoDB.deleteItem(new DeleteItemRequest("TableName",
                Map.of("PrimaryKeyName", new AttributeValue("PrimaryKeyValue"))))).thenReturn(deleteItemResult);

        // Run the test
        final String result = myClassUnderTest.tryDeleteItem("tableName", "id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testTryDeleteItem_AmazonDynamoDBThrowsConditionalCheckFailedException() {
        // Setup
        when(mockAmazonDynamoDB.deleteItem(new DeleteItemRequest("TableName",
                Map.of("PrimaryKeyName", new AttributeValue("PrimaryKeyValue")))))
                .thenThrow(ConditionalCheckFailedException.class);

        // Run the test
        assertThrows(ConditionalCheckFailedException.class, () -> myClassUnderTest.tryDeleteItem("tableName", "id"));
    }

    @Test
    void testTryDeleteItem_AmazonDynamoDBThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockAmazonDynamoDB.deleteItem(new DeleteItemRequest("TableName",
                Map.of("PrimaryKeyName", new AttributeValue("PrimaryKeyValue")))))
                .thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class,
                () -> myClassUnderTest.tryDeleteItem("tableName", "id"));
    }

    @Test
    void testTryDeleteItem_AmazonDynamoDBThrowsResourceNotFoundException() {
        // Setup
        when(mockAmazonDynamoDB.deleteItem(new DeleteItemRequest("TableName",
                Map.of("PrimaryKeyName", new AttributeValue("PrimaryKeyValue")))))
                .thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryDeleteItem("tableName", "id"));
    }

    @Test
    void testTryDeleteItem_AmazonDynamoDBThrowsItemCollectionSizeLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDB.deleteItem(new DeleteItemRequest("TableName",
                Map.of("PrimaryKeyName", new AttributeValue("PrimaryKeyValue")))))
                .thenThrow(ItemCollectionSizeLimitExceededException.class);

        // Run the test
        assertThrows(ItemCollectionSizeLimitExceededException.class,
                () -> myClassUnderTest.tryDeleteItem("tableName", "id"));
    }

    @Test
    void testTryDeleteItem_AmazonDynamoDBThrowsTransactionConflictException() {
        // Setup
        when(mockAmazonDynamoDB.deleteItem(new DeleteItemRequest("TableName",
                Map.of("PrimaryKeyName", new AttributeValue("PrimaryKeyValue")))))
                .thenThrow(TransactionConflictException.class);

        // Run the test
        assertThrows(TransactionConflictException.class, () -> myClassUnderTest.tryDeleteItem("tableName", "id"));
    }

    @Test
    void testTryDeleteItem_AmazonDynamoDBThrowsRequestLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDB.deleteItem(new DeleteItemRequest("TableName",
                Map.of("PrimaryKeyName", new AttributeValue("PrimaryKeyValue")))))
                .thenThrow(RequestLimitExceededException.class);

        // Run the test
        assertThrows(RequestLimitExceededException.class, () -> myClassUnderTest.tryDeleteItem("tableName", "id"));
    }

    @Test
    void testTryDeleteItem_AmazonDynamoDBThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDB.deleteItem(new DeleteItemRequest("TableName",
                Map.of("PrimaryKeyName", new AttributeValue("PrimaryKeyValue")))))
                .thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryDeleteItem("tableName", "id"));
    }

    @Test
    void testTryGetItem() {
        // Setup
        // Configure AmazonDynamoDB.getItem(...).
        final GetItemResult getItemResult = new GetItemResult().withItem(Map.ofEntries(
                Map.entry("PrimaryKeyName", new AttributeValue().withS("PrimaryKeyValue")),
                Map.entry("KeyName2", new AttributeValue().withS("Value2"))
        ));
        when(mockAmazonDynamoDB.getItem(new GetItemRequest("TableName",
                Map.of("PrimaryKeyName", new AttributeValue().withS("PrimaryKeyValue"))
        ))).thenReturn(getItemResult);

        // Run the test
        final String result = myClassUnderTest.tryGetItem("tableName", "id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testTryGetItem_AmazonDynamoDBReturnsNoItem() {
        // Setup
        when(mockAmazonDynamoDB.getItem(new GetItemRequest("TableName",
                Map.of("PrimaryKeyName", new AttributeValue().withS("PrimaryKeyValue"))
        ))).thenReturn(new GetItemResult());

        // Run the test
        final String result = myClassUnderTest.tryGetItem("tableName", "id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testTryGetItem_AmazonDynamoDBThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockAmazonDynamoDB.getItem(new GetItemRequest("TableName",
                Map.of("PrimaryKeyName", new AttributeValue().withS("PrimaryKeyValue"))
        ))).thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class,
                () -> myClassUnderTest.tryGetItem("tableName", "id"));
    }

    @Test
    void testTryGetItem_AmazonDynamoDBThrowsResourceNotFoundException() {
        // Setup
        when(mockAmazonDynamoDB.getItem(new GetItemRequest("TableName",
                Map.of("PrimaryKeyName", new AttributeValue().withS("PrimaryKeyValue"))
        ))).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryGetItem("tableName", "id"));
    }

    @Test
    void testTryGetItem_AmazonDynamoDBThrowsRequestLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDB.getItem(new GetItemRequest("TableName",
                Map.of("PrimaryKeyName", new AttributeValue().withS("PrimaryKeyValue"))
        ))).thenThrow(RequestLimitExceededException.class);

        // Run the test
        assertThrows(RequestLimitExceededException.class, () -> myClassUnderTest.tryGetItem("tableName", "id"));
    }

    @Test
    void testTryGetItem_AmazonDynamoDBThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDB.getItem(new GetItemRequest("TableName",
                Map.of("PrimaryKeyName", new AttributeValue().withS("PrimaryKeyValue"))
        ))).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryGetItem("tableName", "id"));
    }

    @Test
    void testTryPutItem() {
        // Setup
        final Map<String, AttributeValue> item = Map.ofEntries(Map.entry("value", new AttributeValue("s")));

        // Configure AmazonDynamoDB.putItem(...).
        final PutItemResult putItemResult = new PutItemResult();
        putItemResult.setAttributes(Map.ofEntries(Map.entry("value", new AttributeValue("s"))));
        final ItemCollectionMetrics itemCollectionMetrics = new ItemCollectionMetrics();
        putItemResult.setItemCollectionMetrics(itemCollectionMetrics);
        when(mockAmazonDynamoDB.putItem(new PutItemRequest("TableName", Map.ofEntries(
                Map.entry("PrimaryKeyName", new AttributeValue().withS("PrimaryKeyValue")),
                Map.entry("KeyName2", new AttributeValue().withS("Value2"))
        )))).thenReturn(putItemResult);

        // Run the test
        final String result = myClassUnderTest.tryPutItem("tableName", item);

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testTryPutItem_AmazonDynamoDBThrowsConditionalCheckFailedException() {
        // Setup
        final Map<String, AttributeValue> item = Map.ofEntries(Map.entry("value", new AttributeValue("s")));
        when(mockAmazonDynamoDB.putItem(new PutItemRequest("TableName", Map.ofEntries(
                Map.entry("PrimaryKeyName", new AttributeValue().withS("PrimaryKeyValue")),
                Map.entry("KeyName2", new AttributeValue().withS("Value2"))
        )))).thenThrow(ConditionalCheckFailedException.class);

        // Run the test
        assertThrows(ConditionalCheckFailedException.class, () -> myClassUnderTest.tryPutItem("tableName", item));
    }

    @Test
    void testTryPutItem_AmazonDynamoDBThrowsProvisionedThroughputExceededException() {
        // Setup
        final Map<String, AttributeValue> item = Map.ofEntries(Map.entry("value", new AttributeValue("s")));
        when(mockAmazonDynamoDB.putItem(new PutItemRequest("TableName", Map.ofEntries(
                Map.entry("PrimaryKeyName", new AttributeValue().withS("PrimaryKeyValue")),
                Map.entry("KeyName2", new AttributeValue().withS("Value2"))
        )))).thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class,
                () -> myClassUnderTest.tryPutItem("tableName", item));
    }

    @Test
    void testTryPutItem_AmazonDynamoDBThrowsResourceNotFoundException() {
        // Setup
        final Map<String, AttributeValue> item = Map.ofEntries(Map.entry("value", new AttributeValue("s")));
        when(mockAmazonDynamoDB.putItem(new PutItemRequest("TableName", Map.ofEntries(
                Map.entry("PrimaryKeyName", new AttributeValue().withS("PrimaryKeyValue")),
                Map.entry("KeyName2", new AttributeValue().withS("Value2"))
        )))).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryPutItem("tableName", item));
    }

    @Test
    void testTryPutItem_AmazonDynamoDBThrowsItemCollectionSizeLimitExceededException() {
        // Setup
        final Map<String, AttributeValue> item = Map.ofEntries(Map.entry("value", new AttributeValue("s")));
        when(mockAmazonDynamoDB.putItem(new PutItemRequest("TableName", Map.ofEntries(
                Map.entry("PrimaryKeyName", new AttributeValue().withS("PrimaryKeyValue")),
                Map.entry("KeyName2", new AttributeValue().withS("Value2"))
        )))).thenThrow(ItemCollectionSizeLimitExceededException.class);

        // Run the test
        assertThrows(ItemCollectionSizeLimitExceededException.class,
                () -> myClassUnderTest.tryPutItem("tableName", item));
    }

    @Test
    void testTryPutItem_AmazonDynamoDBThrowsTransactionConflictException() {
        // Setup
        final Map<String, AttributeValue> item = Map.ofEntries(Map.entry("value", new AttributeValue("s")));
        when(mockAmazonDynamoDB.putItem(new PutItemRequest("TableName", Map.ofEntries(
                Map.entry("PrimaryKeyName", new AttributeValue().withS("PrimaryKeyValue")),
                Map.entry("KeyName2", new AttributeValue().withS("Value2"))
        )))).thenThrow(TransactionConflictException.class);

        // Run the test
        assertThrows(TransactionConflictException.class, () -> myClassUnderTest.tryPutItem("tableName", item));
    }

    @Test
    void testTryPutItem_AmazonDynamoDBThrowsRequestLimitExceededException() {
        // Setup
        final Map<String, AttributeValue> item = Map.ofEntries(Map.entry("value", new AttributeValue("s")));
        when(mockAmazonDynamoDB.putItem(new PutItemRequest("TableName", Map.ofEntries(
                Map.entry("PrimaryKeyName", new AttributeValue().withS("PrimaryKeyValue")),
                Map.entry("KeyName2", new AttributeValue().withS("Value2"))
        )))).thenThrow(RequestLimitExceededException.class);

        // Run the test
        assertThrows(RequestLimitExceededException.class, () -> myClassUnderTest.tryPutItem("tableName", item));
    }

    @Test
    void testTryPutItem_AmazonDynamoDBThrowsInternalServerErrorException() {
        // Setup
        final Map<String, AttributeValue> item = Map.ofEntries(Map.entry("value", new AttributeValue("s")));
        when(mockAmazonDynamoDB.putItem(new PutItemRequest("TableName", Map.ofEntries(
                Map.entry("PrimaryKeyName", new AttributeValue().withS("PrimaryKeyValue")),
                Map.entry("KeyName2", new AttributeValue().withS("Value2"))
        )))).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryPutItem("tableName", item));
    }

    @Test
    void testTryQuery() {
        // Setup
        // Configure AmazonDynamoDB.query(...).
        final QueryResult queryResult = new QueryResult();
        queryResult.setItems(List.of(Map.ofEntries(Map.entry("value", new AttributeValue("s")))));
        queryResult.setScannedCount(0);
        queryResult.setLastEvaluatedKey(Map.ofEntries(Map.entry("value", new AttributeValue("s"))));
        final ConsumedCapacity consumedCapacity = new ConsumedCapacity();
        queryResult.setConsumedCapacity(consumedCapacity);
        final QueryRequest queryRequest = new QueryRequest();
        queryRequest.setTableName("tableName");
        queryRequest.setSelect(Select.ALL_ATTRIBUTES);
        queryRequest.setKeyConditionExpression("some filter expression");
        when(mockAmazonDynamoDB.query(queryRequest)).thenReturn(queryResult);

        // Run the test
        final String result = myClassUnderTest.tryQuery("tableName", "id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testTryQuery_AmazonDynamoDBReturnsNoItems() {
        // Setup
        // Configure AmazonDynamoDB.query(...).
        final QueryRequest queryRequest = new QueryRequest();
        queryRequest.setTableName("tableName");
        queryRequest.setSelect(Select.ALL_ATTRIBUTES);
        queryRequest.setKeyConditionExpression("some filter expression");
        when(mockAmazonDynamoDB.query(queryRequest)).thenReturn(new QueryResult());

        // Run the test
        final String result = myClassUnderTest.tryQuery("tableName", "id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testTryQuery_AmazonDynamoDBThrowsProvisionedThroughputExceededException() {
        // Setup
        // Configure AmazonDynamoDB.query(...).
        final QueryRequest queryRequest = new QueryRequest();
        queryRequest.setTableName("tableName");
        queryRequest.setSelect(Select.ALL_ATTRIBUTES);
        queryRequest.setKeyConditionExpression("some filter expression");
        when(mockAmazonDynamoDB.query(queryRequest)).thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, () -> myClassUnderTest.tryQuery("tableName", "id"));
    }

    @Test
    void testTryQuery_AmazonDynamoDBThrowsResourceNotFoundException() {
        // Setup
        // Configure AmazonDynamoDB.query(...).
        final QueryRequest queryRequest = new QueryRequest();
        queryRequest.setTableName("tableName");
        queryRequest.setSelect(Select.ALL_ATTRIBUTES);
        queryRequest.setKeyConditionExpression("some filter expression");
        when(mockAmazonDynamoDB.query(queryRequest)).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryQuery("tableName", "id"));
    }

    @Test
    void testTryQuery_AmazonDynamoDBThrowsRequestLimitExceededException() {
        // Setup
        // Configure AmazonDynamoDB.query(...).
        final QueryRequest queryRequest = new QueryRequest();
        queryRequest.setTableName("tableName");
        queryRequest.setSelect(Select.ALL_ATTRIBUTES);
        queryRequest.setKeyConditionExpression("some filter expression");
        when(mockAmazonDynamoDB.query(queryRequest)).thenThrow(RequestLimitExceededException.class);

        // Run the test
        assertThrows(RequestLimitExceededException.class, () -> myClassUnderTest.tryQuery("tableName", "id"));
    }

    @Test
    void testTryQuery_AmazonDynamoDBThrowsInternalServerErrorException() {
        // Setup
        // Configure AmazonDynamoDB.query(...).
        final QueryRequest queryRequest = new QueryRequest();
        queryRequest.setTableName("tableName");
        queryRequest.setSelect(Select.ALL_ATTRIBUTES);
        queryRequest.setKeyConditionExpression("some filter expression");
        when(mockAmazonDynamoDB.query(queryRequest)).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryQuery("tableName", "id"));
    }

    @Test
    void testTryGetSqsMessage() {
        // Setup
        // Configure AmazonSQS.receiveMessage(...).
        final ReceiveMessageResult receiveMessageResult = new ReceiveMessageResult();
        final Message message = new Message();
        message.setMessageId("messageId");
        message.setReceiptHandle("receiptHandle");
        message.setBody("body");
        message.setAttributes(Map.ofEntries(Map.entry("value", "value")));
        receiveMessageResult.setMessages(List.of(message));
        when(mockAmazonSQS.receiveMessage("queueUri")).thenReturn(receiveMessageResult);

        // Run the test
        final String result = myClassUnderTest.tryGetSqsMessage("queueUri");

        // Verify the results
        assertEquals("result", result);
    }
}
