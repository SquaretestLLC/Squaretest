package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.UnsupportedOperationException;
import software.amazon.awssdk.services.sqs.model.*;
import software.amazon.awssdk.services.sqs.paginators.ListDeadLetterSourceQueuesIterable;
import software.amazon.awssdk.services.sqs.paginators.ListQueuesIterable;

import java.util.HashMap;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private SqsClient mockSqsClient;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockSqsClient);
    }

    @Test
    void testTryAddPermission1() {
        // Setup
        when(mockSqsClient.addPermission(AddPermissionRequest.builder()
                .queueUrl("queueUrl")
                .label("label")
                .awsAccountIds("awsAccountIds")
                .actions("actions")
                .build())).thenReturn(AddPermissionResponse.builder().build());

        // Run the test
        myClassUnderTest.tryAddPermission1();

        // Verify the results
    }

    @Test
    void testTryAddPermission1_SqsClientThrowsOverLimitException() {
        // Setup
        when(mockSqsClient.addPermission(AddPermissionRequest.builder()
                .queueUrl("queueUrl")
                .label("label")
                .awsAccountIds("awsAccountIds")
                .actions("actions")
                .build())).thenThrow(OverLimitException.class);

        // Run the test
        assertThrows(OverLimitException.class, () -> myClassUnderTest.tryAddPermission1());
    }

    @Test
    void testTryAddPermission1_SqsClientThrowsAwsServiceException() {
        // Setup
        when(mockSqsClient.addPermission(AddPermissionRequest.builder()
                .queueUrl("queueUrl")
                .label("label")
                .awsAccountIds("awsAccountIds")
                .actions("actions")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryAddPermission1());
    }

    @Test
    void testTryAddPermission1_SqsClientThrowsSdkClientException() {
        // Setup
        when(mockSqsClient.addPermission(AddPermissionRequest.builder()
                .queueUrl("queueUrl")
                .label("label")
                .awsAccountIds("awsAccountIds")
                .actions("actions")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryAddPermission1());
    }

    @Test
    void testTryAddPermission1_SqsClientThrowsSqsException() {
        // Setup
        when(mockSqsClient.addPermission(AddPermissionRequest.builder()
                .queueUrl("queueUrl")
                .label("label")
                .awsAccountIds("awsAccountIds")
                .actions("actions")
                .build())).thenThrow(SqsException.class);

        // Run the test
        assertThrows(SqsException.class, () -> myClassUnderTest.tryAddPermission1());
    }

    @Test
    void testTryAddPermission2() {
        // Setup
        when(mockSqsClient.addPermission(any(Consumer.class))).thenReturn(AddPermissionResponse.builder().build());

        // Run the test
        myClassUnderTest.tryAddPermission2();

        // Verify the results
    }

    @Test
    void testTryAddPermission2_SqsClientThrowsOverLimitException() {
        // Setup
        when(mockSqsClient.addPermission(any(Consumer.class))).thenThrow(OverLimitException.class);

        // Run the test
        assertThrows(OverLimitException.class, () -> myClassUnderTest.tryAddPermission2());
    }

    @Test
    void testTryAddPermission2_SqsClientThrowsAwsServiceException() {
        // Setup
        when(mockSqsClient.addPermission(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryAddPermission2());
    }

    @Test
    void testTryAddPermission2_SqsClientThrowsSdkClientException() {
        // Setup
        when(mockSqsClient.addPermission(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryAddPermission2());
    }

    @Test
    void testTryAddPermission2_SqsClientThrowsSqsException() {
        // Setup
        when(mockSqsClient.addPermission(any(Consumer.class))).thenThrow(SqsException.class);

        // Run the test
        assertThrows(SqsException.class, () -> myClassUnderTest.tryAddPermission2());
    }

    @Test
    void testTryChangeMessageVisibility1() {
        // Setup
        // Configure SqsClient.changeMessageVisibility(...).
        final ChangeMessageVisibilityResponse changeMessageVisibilityResponse = ChangeMessageVisibilityResponse.builder().build();
        when(mockSqsClient.changeMessageVisibility(ChangeMessageVisibilityRequest.builder()
                .queueUrl("queueUrl")
                .receiptHandle("receiptHandle")
                .visibilityTimeout(10)
                .build())).thenReturn(changeMessageVisibilityResponse);

        // Run the test
        myClassUnderTest.tryChangeMessageVisibility1();

        // Verify the results
    }

    @Test
    void testTryChangeMessageVisibility1_SqsClientThrowsMessageNotInflightException() {
        // Setup
        when(mockSqsClient.changeMessageVisibility(ChangeMessageVisibilityRequest.builder()
                .queueUrl("queueUrl")
                .receiptHandle("receiptHandle")
                .visibilityTimeout(10)
                .build())).thenThrow(MessageNotInflightException.class);

        // Run the test
        assertThrows(MessageNotInflightException.class, () -> myClassUnderTest.tryChangeMessageVisibility1());
    }

    @Test
    void testTryChangeMessageVisibility1_SqsClientThrowsReceiptHandleIsInvalidException() {
        // Setup
        when(mockSqsClient.changeMessageVisibility(ChangeMessageVisibilityRequest.builder()
                .queueUrl("queueUrl")
                .receiptHandle("receiptHandle")
                .visibilityTimeout(10)
                .build())).thenThrow(ReceiptHandleIsInvalidException.class);

        // Run the test
        assertThrows(ReceiptHandleIsInvalidException.class, () -> myClassUnderTest.tryChangeMessageVisibility1());
    }

    @Test
    void testTryChangeMessageVisibility1_SqsClientThrowsAwsServiceException() {
        // Setup
        when(mockSqsClient.changeMessageVisibility(ChangeMessageVisibilityRequest.builder()
                .queueUrl("queueUrl")
                .receiptHandle("receiptHandle")
                .visibilityTimeout(10)
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryChangeMessageVisibility1());
    }

    @Test
    void testTryChangeMessageVisibility1_SqsClientThrowsSdkClientException() {
        // Setup
        when(mockSqsClient.changeMessageVisibility(ChangeMessageVisibilityRequest.builder()
                .queueUrl("queueUrl")
                .receiptHandle("receiptHandle")
                .visibilityTimeout(10)
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryChangeMessageVisibility1());
    }

    @Test
    void testTryChangeMessageVisibility1_SqsClientThrowsSqsException() {
        // Setup
        when(mockSqsClient.changeMessageVisibility(ChangeMessageVisibilityRequest.builder()
                .queueUrl("queueUrl")
                .receiptHandle("receiptHandle")
                .visibilityTimeout(10)
                .build())).thenThrow(SqsException.class);

        // Run the test
        assertThrows(SqsException.class, () -> myClassUnderTest.tryChangeMessageVisibility1());
    }

    @Test
    void testTryChangeMessageVisibility2() {
        // Setup
        // Configure SqsClient.changeMessageVisibility(...).
        final ChangeMessageVisibilityResponse changeMessageVisibilityResponse = ChangeMessageVisibilityResponse.builder().build();
        when(mockSqsClient.changeMessageVisibility(any(Consumer.class))).thenReturn(changeMessageVisibilityResponse);

        // Run the test
        myClassUnderTest.tryChangeMessageVisibility2();

        // Verify the results
    }

    @Test
    void testTryChangeMessageVisibility2_SqsClientThrowsMessageNotInflightException() {
        // Setup
        when(mockSqsClient.changeMessageVisibility(any(Consumer.class))).thenThrow(MessageNotInflightException.class);

        // Run the test
        assertThrows(MessageNotInflightException.class, () -> myClassUnderTest.tryChangeMessageVisibility2());
    }

    @Test
    void testTryChangeMessageVisibility2_SqsClientThrowsReceiptHandleIsInvalidException() {
        // Setup
        when(mockSqsClient.changeMessageVisibility(any(Consumer.class)))
                .thenThrow(ReceiptHandleIsInvalidException.class);

        // Run the test
        assertThrows(ReceiptHandleIsInvalidException.class, () -> myClassUnderTest.tryChangeMessageVisibility2());
    }

    @Test
    void testTryChangeMessageVisibility2_SqsClientThrowsAwsServiceException() {
        // Setup
        when(mockSqsClient.changeMessageVisibility(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryChangeMessageVisibility2());
    }

    @Test
    void testTryChangeMessageVisibility2_SqsClientThrowsSdkClientException() {
        // Setup
        when(mockSqsClient.changeMessageVisibility(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryChangeMessageVisibility2());
    }

    @Test
    void testTryChangeMessageVisibility2_SqsClientThrowsSqsException() {
        // Setup
        when(mockSqsClient.changeMessageVisibility(any(Consumer.class))).thenThrow(SqsException.class);

        // Run the test
        assertThrows(SqsException.class, () -> myClassUnderTest.tryChangeMessageVisibility2());
    }

    @Test
    void testTryChangeMessageVisibilityBatch1() {
        // Setup
        // Configure SqsClient.changeMessageVisibilityBatch(...).
        final ChangeMessageVisibilityBatchResponse changeMessageVisibilityBatchResponse = ChangeMessageVisibilityBatchResponse.builder()
                .successful(ChangeMessageVisibilityBatchResultEntry.builder()
                        .id("id")
                        .build())
                .failed(BatchResultErrorEntry.builder()
                        .id("id")
                        .senderFault(false)
                        .code("code")
                        .message("message")
                        .build())
                .build();
        when(mockSqsClient.changeMessageVisibilityBatch(ChangeMessageVisibilityBatchRequest.builder()
                .queueUrl("queueUrl")
                .entries(ChangeMessageVisibilityBatchRequestEntry.builder()
                        .id("id")
                        .receiptHandle("receiptHandle")
                        .visibilityTimeout(0)
                        .build())
                .build())).thenReturn(changeMessageVisibilityBatchResponse);

        // Run the test
        myClassUnderTest.tryChangeMessageVisibilityBatch1();

        // Verify the results
    }

    @Test
    void testTryChangeMessageVisibilityBatch1_SqsClientThrowsTooManyEntriesInBatchRequestException() {
        // Setup
        when(mockSqsClient.changeMessageVisibilityBatch(ChangeMessageVisibilityBatchRequest.builder()
                .queueUrl("queueUrl")
                .entries(ChangeMessageVisibilityBatchRequestEntry.builder()
                        .id("id")
                        .receiptHandle("receiptHandle")
                        .visibilityTimeout(0)
                        .build())
                .build())).thenThrow(TooManyEntriesInBatchRequestException.class);

        // Run the test
        assertThrows(TooManyEntriesInBatchRequestException.class,
                () -> myClassUnderTest.tryChangeMessageVisibilityBatch1());
    }

    @Test
    void testTryChangeMessageVisibilityBatch1_SqsClientThrowsEmptyBatchRequestException() {
        // Setup
        when(mockSqsClient.changeMessageVisibilityBatch(ChangeMessageVisibilityBatchRequest.builder()
                .queueUrl("queueUrl")
                .entries(ChangeMessageVisibilityBatchRequestEntry.builder()
                        .id("id")
                        .receiptHandle("receiptHandle")
                        .visibilityTimeout(0)
                        .build())
                .build())).thenThrow(EmptyBatchRequestException.class);

        // Run the test
        assertThrows(EmptyBatchRequestException.class, () -> myClassUnderTest.tryChangeMessageVisibilityBatch1());
    }

    @Test
    void testTryChangeMessageVisibilityBatch1_SqsClientThrowsBatchEntryIdsNotDistinctException() {
        // Setup
        when(mockSqsClient.changeMessageVisibilityBatch(ChangeMessageVisibilityBatchRequest.builder()
                .queueUrl("queueUrl")
                .entries(ChangeMessageVisibilityBatchRequestEntry.builder()
                        .id("id")
                        .receiptHandle("receiptHandle")
                        .visibilityTimeout(0)
                        .build())
                .build())).thenThrow(BatchEntryIdsNotDistinctException.class);

        // Run the test
        assertThrows(BatchEntryIdsNotDistinctException.class,
                () -> myClassUnderTest.tryChangeMessageVisibilityBatch1());
    }

    @Test
    void testTryChangeMessageVisibilityBatch1_SqsClientThrowsInvalidBatchEntryIdException() {
        // Setup
        when(mockSqsClient.changeMessageVisibilityBatch(ChangeMessageVisibilityBatchRequest.builder()
                .queueUrl("queueUrl")
                .entries(ChangeMessageVisibilityBatchRequestEntry.builder()
                        .id("id")
                        .receiptHandle("receiptHandle")
                        .visibilityTimeout(0)
                        .build())
                .build())).thenThrow(InvalidBatchEntryIdException.class);

        // Run the test
        assertThrows(InvalidBatchEntryIdException.class, () -> myClassUnderTest.tryChangeMessageVisibilityBatch1());
    }

    @Test
    void testTryChangeMessageVisibilityBatch1_SqsClientThrowsAwsServiceException() {
        // Setup
        when(mockSqsClient.changeMessageVisibilityBatch(ChangeMessageVisibilityBatchRequest.builder()
                .queueUrl("queueUrl")
                .entries(ChangeMessageVisibilityBatchRequestEntry.builder()
                        .id("id")
                        .receiptHandle("receiptHandle")
                        .visibilityTimeout(0)
                        .build())
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryChangeMessageVisibilityBatch1());
    }

    @Test
    void testTryChangeMessageVisibilityBatch1_SqsClientThrowsSdkClientException() {
        // Setup
        when(mockSqsClient.changeMessageVisibilityBatch(ChangeMessageVisibilityBatchRequest.builder()
                .queueUrl("queueUrl")
                .entries(ChangeMessageVisibilityBatchRequestEntry.builder()
                        .id("id")
                        .receiptHandle("receiptHandle")
                        .visibilityTimeout(0)
                        .build())
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryChangeMessageVisibilityBatch1());
    }

    @Test
    void testTryChangeMessageVisibilityBatch1_SqsClientThrowsSqsException() {
        // Setup
        when(mockSqsClient.changeMessageVisibilityBatch(ChangeMessageVisibilityBatchRequest.builder()
                .queueUrl("queueUrl")
                .entries(ChangeMessageVisibilityBatchRequestEntry.builder()
                        .id("id")
                        .receiptHandle("receiptHandle")
                        .visibilityTimeout(0)
                        .build())
                .build())).thenThrow(SqsException.class);

        // Run the test
        assertThrows(SqsException.class, () -> myClassUnderTest.tryChangeMessageVisibilityBatch1());
    }

    @Test
    void testTryChangeMessageVisibilityBatch2() {
        // Setup
        // Configure SqsClient.changeMessageVisibilityBatch(...).
        final ChangeMessageVisibilityBatchResponse changeMessageVisibilityBatchResponse = ChangeMessageVisibilityBatchResponse.builder()
                .successful(ChangeMessageVisibilityBatchResultEntry.builder()
                        .id("id")
                        .build())
                .failed(BatchResultErrorEntry.builder()
                        .id("id")
                        .senderFault(false)
                        .code("code")
                        .message("message")
                        .build())
                .build();
        when(mockSqsClient.changeMessageVisibilityBatch(any(Consumer.class)))
                .thenReturn(changeMessageVisibilityBatchResponse);

        // Run the test
        myClassUnderTest.tryChangeMessageVisibilityBatch2();

        // Verify the results
    }

    @Test
    void testTryChangeMessageVisibilityBatch2_SqsClientThrowsTooManyEntriesInBatchRequestException() {
        // Setup
        when(mockSqsClient.changeMessageVisibilityBatch(any(Consumer.class)))
                .thenThrow(TooManyEntriesInBatchRequestException.class);

        // Run the test
        assertThrows(TooManyEntriesInBatchRequestException.class,
                () -> myClassUnderTest.tryChangeMessageVisibilityBatch2());
    }

    @Test
    void testTryChangeMessageVisibilityBatch2_SqsClientThrowsEmptyBatchRequestException() {
        // Setup
        when(mockSqsClient.changeMessageVisibilityBatch(any(Consumer.class)))
                .thenThrow(EmptyBatchRequestException.class);

        // Run the test
        assertThrows(EmptyBatchRequestException.class, () -> myClassUnderTest.tryChangeMessageVisibilityBatch2());
    }

    @Test
    void testTryChangeMessageVisibilityBatch2_SqsClientThrowsBatchEntryIdsNotDistinctException() {
        // Setup
        when(mockSqsClient.changeMessageVisibilityBatch(any(Consumer.class)))
                .thenThrow(BatchEntryIdsNotDistinctException.class);

        // Run the test
        assertThrows(BatchEntryIdsNotDistinctException.class,
                () -> myClassUnderTest.tryChangeMessageVisibilityBatch2());
    }

    @Test
    void testTryChangeMessageVisibilityBatch2_SqsClientThrowsInvalidBatchEntryIdException() {
        // Setup
        when(mockSqsClient.changeMessageVisibilityBatch(any(Consumer.class)))
                .thenThrow(InvalidBatchEntryIdException.class);

        // Run the test
        assertThrows(InvalidBatchEntryIdException.class, () -> myClassUnderTest.tryChangeMessageVisibilityBatch2());
    }

    @Test
    void testTryChangeMessageVisibilityBatch2_SqsClientThrowsAwsServiceException() {
        // Setup
        when(mockSqsClient.changeMessageVisibilityBatch(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryChangeMessageVisibilityBatch2());
    }

    @Test
    void testTryChangeMessageVisibilityBatch2_SqsClientThrowsSdkClientException() {
        // Setup
        when(mockSqsClient.changeMessageVisibilityBatch(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryChangeMessageVisibilityBatch2());
    }

    @Test
    void testTryChangeMessageVisibilityBatch2_SqsClientThrowsSqsException() {
        // Setup
        when(mockSqsClient.changeMessageVisibilityBatch(any(Consumer.class))).thenThrow(SqsException.class);

        // Run the test
        assertThrows(SqsException.class, () -> myClassUnderTest.tryChangeMessageVisibilityBatch2());
    }

    @Test
    void testTryCreateQueue1() {
        // Setup
        // Configure SqsClient.createQueue(...).
        final CreateQueueResponse createQueueResponse = CreateQueueResponse.builder()
                .queueUrl("queueUrl")
                .build();
        when(mockSqsClient.createQueue(CreateQueueRequest.builder()
                .queueName("queueName")
                .attributes(new HashMap<>())
                .tags(new HashMap<>())
                .build())).thenReturn(createQueueResponse);

        // Run the test
        myClassUnderTest.tryCreateQueue1();

        // Verify the results
    }

    @Test
    void testTryCreateQueue1_SqsClientThrowsQueueDeletedRecentlyException() {
        // Setup
        when(mockSqsClient.createQueue(CreateQueueRequest.builder()
                .queueName("queueName")
                .attributes(new HashMap<>())
                .tags(new HashMap<>())
                .build())).thenThrow(QueueDeletedRecentlyException.class);

        // Run the test
        assertThrows(QueueDeletedRecentlyException.class, () -> myClassUnderTest.tryCreateQueue1());
    }

    @Test
    void testTryCreateQueue1_SqsClientThrowsQueueNameExistsException() {
        // Setup
        when(mockSqsClient.createQueue(CreateQueueRequest.builder()
                .queueName("queueName")
                .attributes(new HashMap<>())
                .tags(new HashMap<>())
                .build())).thenThrow(QueueNameExistsException.class);

        // Run the test
        assertThrows(QueueNameExistsException.class, () -> myClassUnderTest.tryCreateQueue1());
    }

    @Test
    void testTryCreateQueue1_SqsClientThrowsAwsServiceException() {
        // Setup
        when(mockSqsClient.createQueue(CreateQueueRequest.builder()
                .queueName("queueName")
                .attributes(new HashMap<>())
                .tags(new HashMap<>())
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryCreateQueue1());
    }

    @Test
    void testTryCreateQueue1_SqsClientThrowsSdkClientException() {
        // Setup
        when(mockSqsClient.createQueue(CreateQueueRequest.builder()
                .queueName("queueName")
                .attributes(new HashMap<>())
                .tags(new HashMap<>())
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryCreateQueue1());
    }

    @Test
    void testTryCreateQueue1_SqsClientThrowsSqsException() {
        // Setup
        when(mockSqsClient.createQueue(CreateQueueRequest.builder()
                .queueName("queueName")
                .attributes(new HashMap<>())
                .tags(new HashMap<>())
                .build())).thenThrow(SqsException.class);

        // Run the test
        assertThrows(SqsException.class, () -> myClassUnderTest.tryCreateQueue1());
    }

    @Test
    void testTryCreateQueue2() {
        // Setup
        // Configure SqsClient.createQueue(...).
        final CreateQueueResponse createQueueResponse = CreateQueueResponse.builder()
                .queueUrl("queueUrl")
                .build();
        when(mockSqsClient.createQueue(any(Consumer.class))).thenReturn(createQueueResponse);

        // Run the test
        myClassUnderTest.tryCreateQueue2();

        // Verify the results
    }

    @Test
    void testTryCreateQueue2_SqsClientThrowsQueueDeletedRecentlyException() {
        // Setup
        when(mockSqsClient.createQueue(any(Consumer.class))).thenThrow(QueueDeletedRecentlyException.class);

        // Run the test
        assertThrows(QueueDeletedRecentlyException.class, () -> myClassUnderTest.tryCreateQueue2());
    }

    @Test
    void testTryCreateQueue2_SqsClientThrowsQueueNameExistsException() {
        // Setup
        when(mockSqsClient.createQueue(any(Consumer.class))).thenThrow(QueueNameExistsException.class);

        // Run the test
        assertThrows(QueueNameExistsException.class, () -> myClassUnderTest.tryCreateQueue2());
    }

    @Test
    void testTryCreateQueue2_SqsClientThrowsAwsServiceException() {
        // Setup
        when(mockSqsClient.createQueue(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryCreateQueue2());
    }

    @Test
    void testTryCreateQueue2_SqsClientThrowsSdkClientException() {
        // Setup
        when(mockSqsClient.createQueue(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryCreateQueue2());
    }

    @Test
    void testTryCreateQueue2_SqsClientThrowsSqsException() {
        // Setup
        when(mockSqsClient.createQueue(any(Consumer.class))).thenThrow(SqsException.class);

        // Run the test
        assertThrows(SqsException.class, () -> myClassUnderTest.tryCreateQueue2());
    }

    @Test
    void testTryDeleteMessage1() {
        // Setup
        when(mockSqsClient.deleteMessage(DeleteMessageRequest.builder()
                .queueUrl("queueUrl")
                .receiptHandle("receiptHandle")
                .build())).thenReturn(DeleteMessageResponse.builder().build());

        // Run the test
        myClassUnderTest.tryDeleteMessage1();

        // Verify the results
    }

    @Test
    void testTryDeleteMessage1_SqsClientThrowsInvalidIdFormatException() {
        // Setup
        when(mockSqsClient.deleteMessage(DeleteMessageRequest.builder()
                .queueUrl("queueUrl")
                .receiptHandle("receiptHandle")
                .build())).thenThrow(InvalidIdFormatException.class);

        // Run the test
        assertThrows(InvalidIdFormatException.class, () -> myClassUnderTest.tryDeleteMessage1());
    }

    @Test
    void testTryDeleteMessage1_SqsClientThrowsReceiptHandleIsInvalidException() {
        // Setup
        when(mockSqsClient.deleteMessage(DeleteMessageRequest.builder()
                .queueUrl("queueUrl")
                .receiptHandle("receiptHandle")
                .build())).thenThrow(ReceiptHandleIsInvalidException.class);

        // Run the test
        assertThrows(ReceiptHandleIsInvalidException.class, () -> myClassUnderTest.tryDeleteMessage1());
    }

    @Test
    void testTryDeleteMessage1_SqsClientThrowsAwsServiceException() {
        // Setup
        when(mockSqsClient.deleteMessage(DeleteMessageRequest.builder()
                .queueUrl("queueUrl")
                .receiptHandle("receiptHandle")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDeleteMessage1());
    }

    @Test
    void testTryDeleteMessage1_SqsClientThrowsSdkClientException() {
        // Setup
        when(mockSqsClient.deleteMessage(DeleteMessageRequest.builder()
                .queueUrl("queueUrl")
                .receiptHandle("receiptHandle")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteMessage1());
    }

    @Test
    void testTryDeleteMessage1_SqsClientThrowsSqsException() {
        // Setup
        when(mockSqsClient.deleteMessage(DeleteMessageRequest.builder()
                .queueUrl("queueUrl")
                .receiptHandle("receiptHandle")
                .build())).thenThrow(SqsException.class);

        // Run the test
        assertThrows(SqsException.class, () -> myClassUnderTest.tryDeleteMessage1());
    }

    @Test
    void testTryDeleteMessage2() {
        // Setup
        when(mockSqsClient.deleteMessage(any(Consumer.class))).thenReturn(DeleteMessageResponse.builder().build());

        // Run the test
        myClassUnderTest.tryDeleteMessage2();

        // Verify the results
    }

    @Test
    void testTryDeleteMessage2_SqsClientThrowsInvalidIdFormatException() {
        // Setup
        when(mockSqsClient.deleteMessage(any(Consumer.class))).thenThrow(InvalidIdFormatException.class);

        // Run the test
        assertThrows(InvalidIdFormatException.class, () -> myClassUnderTest.tryDeleteMessage2());
    }

    @Test
    void testTryDeleteMessage2_SqsClientThrowsReceiptHandleIsInvalidException() {
        // Setup
        when(mockSqsClient.deleteMessage(any(Consumer.class))).thenThrow(ReceiptHandleIsInvalidException.class);

        // Run the test
        assertThrows(ReceiptHandleIsInvalidException.class, () -> myClassUnderTest.tryDeleteMessage2());
    }

    @Test
    void testTryDeleteMessage2_SqsClientThrowsAwsServiceException() {
        // Setup
        when(mockSqsClient.deleteMessage(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDeleteMessage2());
    }

    @Test
    void testTryDeleteMessage2_SqsClientThrowsSdkClientException() {
        // Setup
        when(mockSqsClient.deleteMessage(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteMessage2());
    }

    @Test
    void testTryDeleteMessage2_SqsClientThrowsSqsException() {
        // Setup
        when(mockSqsClient.deleteMessage(any(Consumer.class))).thenThrow(SqsException.class);

        // Run the test
        assertThrows(SqsException.class, () -> myClassUnderTest.tryDeleteMessage2());
    }

    @Test
    void testTryDeleteMessageBatch1() {
        // Setup
        // Configure SqsClient.deleteMessageBatch(...).
        final DeleteMessageBatchResponse deleteMessageBatchResponse = DeleteMessageBatchResponse.builder()
                .successful(DeleteMessageBatchResultEntry.builder()
                        .id("id")
                        .build())
                .failed(BatchResultErrorEntry.builder()
                        .id("id")
                        .senderFault(false)
                        .code("code")
                        .message("message")
                        .build())
                .build();
        when(mockSqsClient.deleteMessageBatch(DeleteMessageBatchRequest.builder()
                .queueUrl("queueUrl")
                .entries(DeleteMessageBatchRequestEntry.builder()
                        .id("id")
                        .receiptHandle("receiptHandle")
                        .build())
                .build())).thenReturn(deleteMessageBatchResponse);

        // Run the test
        myClassUnderTest.tryDeleteMessageBatch1();

        // Verify the results
    }

    @Test
    void testTryDeleteMessageBatch1_SqsClientThrowsTooManyEntriesInBatchRequestException() {
        // Setup
        when(mockSqsClient.deleteMessageBatch(DeleteMessageBatchRequest.builder()
                .queueUrl("queueUrl")
                .entries(DeleteMessageBatchRequestEntry.builder()
                        .id("id")
                        .receiptHandle("receiptHandle")
                        .build())
                .build())).thenThrow(TooManyEntriesInBatchRequestException.class);

        // Run the test
        assertThrows(TooManyEntriesInBatchRequestException.class, () -> myClassUnderTest.tryDeleteMessageBatch1());
    }

    @Test
    void testTryDeleteMessageBatch1_SqsClientThrowsEmptyBatchRequestException() {
        // Setup
        when(mockSqsClient.deleteMessageBatch(DeleteMessageBatchRequest.builder()
                .queueUrl("queueUrl")
                .entries(DeleteMessageBatchRequestEntry.builder()
                        .id("id")
                        .receiptHandle("receiptHandle")
                        .build())
                .build())).thenThrow(EmptyBatchRequestException.class);

        // Run the test
        assertThrows(EmptyBatchRequestException.class, () -> myClassUnderTest.tryDeleteMessageBatch1());
    }

    @Test
    void testTryDeleteMessageBatch1_SqsClientThrowsBatchEntryIdsNotDistinctException() {
        // Setup
        when(mockSqsClient.deleteMessageBatch(DeleteMessageBatchRequest.builder()
                .queueUrl("queueUrl")
                .entries(DeleteMessageBatchRequestEntry.builder()
                        .id("id")
                        .receiptHandle("receiptHandle")
                        .build())
                .build())).thenThrow(BatchEntryIdsNotDistinctException.class);

        // Run the test
        assertThrows(BatchEntryIdsNotDistinctException.class, () -> myClassUnderTest.tryDeleteMessageBatch1());
    }

    @Test
    void testTryDeleteMessageBatch1_SqsClientThrowsInvalidBatchEntryIdException() {
        // Setup
        when(mockSqsClient.deleteMessageBatch(DeleteMessageBatchRequest.builder()
                .queueUrl("queueUrl")
                .entries(DeleteMessageBatchRequestEntry.builder()
                        .id("id")
                        .receiptHandle("receiptHandle")
                        .build())
                .build())).thenThrow(InvalidBatchEntryIdException.class);

        // Run the test
        assertThrows(InvalidBatchEntryIdException.class, () -> myClassUnderTest.tryDeleteMessageBatch1());
    }

    @Test
    void testTryDeleteMessageBatch1_SqsClientThrowsAwsServiceException() {
        // Setup
        when(mockSqsClient.deleteMessageBatch(DeleteMessageBatchRequest.builder()
                .queueUrl("queueUrl")
                .entries(DeleteMessageBatchRequestEntry.builder()
                        .id("id")
                        .receiptHandle("receiptHandle")
                        .build())
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDeleteMessageBatch1());
    }

    @Test
    void testTryDeleteMessageBatch1_SqsClientThrowsSdkClientException() {
        // Setup
        when(mockSqsClient.deleteMessageBatch(DeleteMessageBatchRequest.builder()
                .queueUrl("queueUrl")
                .entries(DeleteMessageBatchRequestEntry.builder()
                        .id("id")
                        .receiptHandle("receiptHandle")
                        .build())
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteMessageBatch1());
    }

    @Test
    void testTryDeleteMessageBatch1_SqsClientThrowsSqsException() {
        // Setup
        when(mockSqsClient.deleteMessageBatch(DeleteMessageBatchRequest.builder()
                .queueUrl("queueUrl")
                .entries(DeleteMessageBatchRequestEntry.builder()
                        .id("id")
                        .receiptHandle("receiptHandle")
                        .build())
                .build())).thenThrow(SqsException.class);

        // Run the test
        assertThrows(SqsException.class, () -> myClassUnderTest.tryDeleteMessageBatch1());
    }

    @Test
    void testTryDeleteMessageBatch2() {
        // Setup
        // Configure SqsClient.deleteMessageBatch(...).
        final DeleteMessageBatchResponse deleteMessageBatchResponse = DeleteMessageBatchResponse.builder()
                .successful(DeleteMessageBatchResultEntry.builder()
                        .id("id")
                        .build())
                .failed(BatchResultErrorEntry.builder()
                        .id("id")
                        .senderFault(false)
                        .code("code")
                        .message("message")
                        .build())
                .build();
        when(mockSqsClient.deleteMessageBatch(any(Consumer.class))).thenReturn(deleteMessageBatchResponse);

        // Run the test
        myClassUnderTest.tryDeleteMessageBatch2();

        // Verify the results
    }

    @Test
    void testTryDeleteMessageBatch2_SqsClientThrowsTooManyEntriesInBatchRequestException() {
        // Setup
        when(mockSqsClient.deleteMessageBatch(any(Consumer.class)))
                .thenThrow(TooManyEntriesInBatchRequestException.class);

        // Run the test
        assertThrows(TooManyEntriesInBatchRequestException.class, () -> myClassUnderTest.tryDeleteMessageBatch2());
    }

    @Test
    void testTryDeleteMessageBatch2_SqsClientThrowsEmptyBatchRequestException() {
        // Setup
        when(mockSqsClient.deleteMessageBatch(any(Consumer.class))).thenThrow(EmptyBatchRequestException.class);

        // Run the test
        assertThrows(EmptyBatchRequestException.class, () -> myClassUnderTest.tryDeleteMessageBatch2());
    }

    @Test
    void testTryDeleteMessageBatch2_SqsClientThrowsBatchEntryIdsNotDistinctException() {
        // Setup
        when(mockSqsClient.deleteMessageBatch(any(Consumer.class))).thenThrow(BatchEntryIdsNotDistinctException.class);

        // Run the test
        assertThrows(BatchEntryIdsNotDistinctException.class, () -> myClassUnderTest.tryDeleteMessageBatch2());
    }

    @Test
    void testTryDeleteMessageBatch2_SqsClientThrowsInvalidBatchEntryIdException() {
        // Setup
        when(mockSqsClient.deleteMessageBatch(any(Consumer.class))).thenThrow(InvalidBatchEntryIdException.class);

        // Run the test
        assertThrows(InvalidBatchEntryIdException.class, () -> myClassUnderTest.tryDeleteMessageBatch2());
    }

    @Test
    void testTryDeleteMessageBatch2_SqsClientThrowsAwsServiceException() {
        // Setup
        when(mockSqsClient.deleteMessageBatch(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDeleteMessageBatch2());
    }

    @Test
    void testTryDeleteMessageBatch2_SqsClientThrowsSdkClientException() {
        // Setup
        when(mockSqsClient.deleteMessageBatch(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteMessageBatch2());
    }

    @Test
    void testTryDeleteMessageBatch2_SqsClientThrowsSqsException() {
        // Setup
        when(mockSqsClient.deleteMessageBatch(any(Consumer.class))).thenThrow(SqsException.class);

        // Run the test
        assertThrows(SqsException.class, () -> myClassUnderTest.tryDeleteMessageBatch2());
    }

    @Test
    void testTryDeleteQueue1() {
        // Setup
        when(mockSqsClient.deleteQueue(DeleteQueueRequest.builder()
                .queueUrl("queueUrl")
                .build())).thenReturn(DeleteQueueResponse.builder().build());

        // Run the test
        myClassUnderTest.tryDeleteQueue1();

        // Verify the results
    }

    @Test
    void testTryDeleteQueue1_SqsClientThrowsAwsServiceException() {
        // Setup
        when(mockSqsClient.deleteQueue(DeleteQueueRequest.builder()
                .queueUrl("queueUrl")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDeleteQueue1());
    }

    @Test
    void testTryDeleteQueue1_SqsClientThrowsSdkClientException() {
        // Setup
        when(mockSqsClient.deleteQueue(DeleteQueueRequest.builder()
                .queueUrl("queueUrl")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteQueue1());
    }

    @Test
    void testTryDeleteQueue1_SqsClientThrowsSqsException() {
        // Setup
        when(mockSqsClient.deleteQueue(DeleteQueueRequest.builder()
                .queueUrl("queueUrl")
                .build())).thenThrow(SqsException.class);

        // Run the test
        assertThrows(SqsException.class, () -> myClassUnderTest.tryDeleteQueue1());
    }

    @Test
    void testTryDeleteQueue2() {
        // Setup
        when(mockSqsClient.deleteQueue(any(Consumer.class))).thenReturn(DeleteQueueResponse.builder().build());

        // Run the test
        myClassUnderTest.tryDeleteQueue2();

        // Verify the results
    }

    @Test
    void testTryDeleteQueue2_SqsClientThrowsAwsServiceException() {
        // Setup
        when(mockSqsClient.deleteQueue(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDeleteQueue2());
    }

    @Test
    void testTryDeleteQueue2_SqsClientThrowsSdkClientException() {
        // Setup
        when(mockSqsClient.deleteQueue(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteQueue2());
    }

    @Test
    void testTryDeleteQueue2_SqsClientThrowsSqsException() {
        // Setup
        when(mockSqsClient.deleteQueue(any(Consumer.class))).thenThrow(SqsException.class);

        // Run the test
        assertThrows(SqsException.class, () -> myClassUnderTest.tryDeleteQueue2());
    }

    @Test
    void testTryGetQueueAttributes1() {
        // Setup
        // Configure SqsClient.getQueueAttributes(...).
        final GetQueueAttributesResponse getQueueAttributesResponse = GetQueueAttributesResponse.builder()
                .attributes(new HashMap<>())
                .build();
        when(mockSqsClient.getQueueAttributes(GetQueueAttributesRequest.builder()
                .queueUrl("queueUrl")
                .attributeNames(QueueAttributeName.ALL)
                .build())).thenReturn(getQueueAttributesResponse);

        // Run the test
        myClassUnderTest.tryGetQueueAttributes1();

        // Verify the results
    }

    @Test
    void testTryGetQueueAttributes1_SqsClientThrowsInvalidAttributeNameException() {
        // Setup
        when(mockSqsClient.getQueueAttributes(GetQueueAttributesRequest.builder()
                .queueUrl("queueUrl")
                .attributeNames(QueueAttributeName.ALL)
                .build())).thenThrow(InvalidAttributeNameException.class);

        // Run the test
        assertThrows(InvalidAttributeNameException.class, () -> myClassUnderTest.tryGetQueueAttributes1());
    }

    @Test
    void testTryGetQueueAttributes1_SqsClientThrowsAwsServiceException() {
        // Setup
        when(mockSqsClient.getQueueAttributes(GetQueueAttributesRequest.builder()
                .queueUrl("queueUrl")
                .attributeNames(QueueAttributeName.ALL)
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetQueueAttributes1());
    }

    @Test
    void testTryGetQueueAttributes1_SqsClientThrowsSdkClientException() {
        // Setup
        when(mockSqsClient.getQueueAttributes(GetQueueAttributesRequest.builder()
                .queueUrl("queueUrl")
                .attributeNames(QueueAttributeName.ALL)
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetQueueAttributes1());
    }

    @Test
    void testTryGetQueueAttributes1_SqsClientThrowsSqsException() {
        // Setup
        when(mockSqsClient.getQueueAttributes(GetQueueAttributesRequest.builder()
                .queueUrl("queueUrl")
                .attributeNames(QueueAttributeName.ALL)
                .build())).thenThrow(SqsException.class);

        // Run the test
        assertThrows(SqsException.class, () -> myClassUnderTest.tryGetQueueAttributes1());
    }

    @Test
    void testTryGetQueueAttributes2() {
        // Setup
        // Configure SqsClient.getQueueAttributes(...).
        final GetQueueAttributesResponse getQueueAttributesResponse = GetQueueAttributesResponse.builder()
                .attributes(new HashMap<>())
                .build();
        when(mockSqsClient.getQueueAttributes(any(Consumer.class))).thenReturn(getQueueAttributesResponse);

        // Run the test
        myClassUnderTest.tryGetQueueAttributes2();

        // Verify the results
    }

    @Test
    void testTryGetQueueAttributes2_SqsClientThrowsInvalidAttributeNameException() {
        // Setup
        when(mockSqsClient.getQueueAttributes(any(Consumer.class))).thenThrow(InvalidAttributeNameException.class);

        // Run the test
        assertThrows(InvalidAttributeNameException.class, () -> myClassUnderTest.tryGetQueueAttributes2());
    }

    @Test
    void testTryGetQueueAttributes2_SqsClientThrowsAwsServiceException() {
        // Setup
        when(mockSqsClient.getQueueAttributes(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetQueueAttributes2());
    }

    @Test
    void testTryGetQueueAttributes2_SqsClientThrowsSdkClientException() {
        // Setup
        when(mockSqsClient.getQueueAttributes(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetQueueAttributes2());
    }

    @Test
    void testTryGetQueueAttributes2_SqsClientThrowsSqsException() {
        // Setup
        when(mockSqsClient.getQueueAttributes(any(Consumer.class))).thenThrow(SqsException.class);

        // Run the test
        assertThrows(SqsException.class, () -> myClassUnderTest.tryGetQueueAttributes2());
    }

    @Test
    void testTryGetQueueUrl1() {
        // Setup
        // Configure SqsClient.getQueueUrl(...).
        final GetQueueUrlResponse getQueueUrlResponse = GetQueueUrlResponse.builder()
                .queueUrl("queueUrl")
                .build();
        when(mockSqsClient.getQueueUrl(GetQueueUrlRequest.builder()
                .queueName("queueName")
                .queueOwnerAWSAccountId("queueOwnerAWSAccountId")
                .build())).thenReturn(getQueueUrlResponse);

        // Run the test
        myClassUnderTest.tryGetQueueUrl1();

        // Verify the results
    }

    @Test
    void testTryGetQueueUrl1_SqsClientThrowsQueueDoesNotExistException() {
        // Setup
        when(mockSqsClient.getQueueUrl(GetQueueUrlRequest.builder()
                .queueName("queueName")
                .queueOwnerAWSAccountId("queueOwnerAWSAccountId")
                .build())).thenThrow(QueueDoesNotExistException.class);

        // Run the test
        assertThrows(QueueDoesNotExistException.class, () -> myClassUnderTest.tryGetQueueUrl1());
    }

    @Test
    void testTryGetQueueUrl1_SqsClientThrowsAwsServiceException() {
        // Setup
        when(mockSqsClient.getQueueUrl(GetQueueUrlRequest.builder()
                .queueName("queueName")
                .queueOwnerAWSAccountId("queueOwnerAWSAccountId")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetQueueUrl1());
    }

    @Test
    void testTryGetQueueUrl1_SqsClientThrowsSdkClientException() {
        // Setup
        when(mockSqsClient.getQueueUrl(GetQueueUrlRequest.builder()
                .queueName("queueName")
                .queueOwnerAWSAccountId("queueOwnerAWSAccountId")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetQueueUrl1());
    }

    @Test
    void testTryGetQueueUrl1_SqsClientThrowsSqsException() {
        // Setup
        when(mockSqsClient.getQueueUrl(GetQueueUrlRequest.builder()
                .queueName("queueName")
                .queueOwnerAWSAccountId("queueOwnerAWSAccountId")
                .build())).thenThrow(SqsException.class);

        // Run the test
        assertThrows(SqsException.class, () -> myClassUnderTest.tryGetQueueUrl1());
    }

    @Test
    void testTryGetQueueUrl2() {
        // Setup
        // Configure SqsClient.getQueueUrl(...).
        final GetQueueUrlResponse getQueueUrlResponse = GetQueueUrlResponse.builder()
                .queueUrl("queueUrl")
                .build();
        when(mockSqsClient.getQueueUrl(any(Consumer.class))).thenReturn(getQueueUrlResponse);

        // Run the test
        myClassUnderTest.tryGetQueueUrl2();

        // Verify the results
    }

    @Test
    void testTryGetQueueUrl2_SqsClientThrowsQueueDoesNotExistException() {
        // Setup
        when(mockSqsClient.getQueueUrl(any(Consumer.class))).thenThrow(QueueDoesNotExistException.class);

        // Run the test
        assertThrows(QueueDoesNotExistException.class, () -> myClassUnderTest.tryGetQueueUrl2());
    }

    @Test
    void testTryGetQueueUrl2_SqsClientThrowsAwsServiceException() {
        // Setup
        when(mockSqsClient.getQueueUrl(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetQueueUrl2());
    }

    @Test
    void testTryGetQueueUrl2_SqsClientThrowsSdkClientException() {
        // Setup
        when(mockSqsClient.getQueueUrl(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetQueueUrl2());
    }

    @Test
    void testTryGetQueueUrl2_SqsClientThrowsSqsException() {
        // Setup
        when(mockSqsClient.getQueueUrl(any(Consumer.class))).thenThrow(SqsException.class);

        // Run the test
        assertThrows(SqsException.class, () -> myClassUnderTest.tryGetQueueUrl2());
    }

    @Test
    void testTryListDeadLetterSourceQueues1() {
        // Setup
        // Configure SqsClient.listDeadLetterSourceQueues(...).
        final ListDeadLetterSourceQueuesResponse listDeadLetterSourceQueuesResponse = ListDeadLetterSourceQueuesResponse.builder()
                .queueUrls("queueUrls")
                .nextToken("nextToken")
                .build();
        when(mockSqsClient.listDeadLetterSourceQueues(ListDeadLetterSourceQueuesRequest.builder()
                .queueUrl("queueUrl")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenReturn(listDeadLetterSourceQueuesResponse);

        // Run the test
        myClassUnderTest.tryListDeadLetterSourceQueues1();

        // Verify the results
    }

    @Test
    void testTryListDeadLetterSourceQueues1_SqsClientThrowsQueueDoesNotExistException() {
        // Setup
        when(mockSqsClient.listDeadLetterSourceQueues(ListDeadLetterSourceQueuesRequest.builder()
                .queueUrl("queueUrl")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(QueueDoesNotExistException.class);

        // Run the test
        assertThrows(QueueDoesNotExistException.class, () -> myClassUnderTest.tryListDeadLetterSourceQueues1());
    }

    @Test
    void testTryListDeadLetterSourceQueues1_SqsClientThrowsAwsServiceException() {
        // Setup
        when(mockSqsClient.listDeadLetterSourceQueues(ListDeadLetterSourceQueuesRequest.builder()
                .queueUrl("queueUrl")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListDeadLetterSourceQueues1());
    }

    @Test
    void testTryListDeadLetterSourceQueues1_SqsClientThrowsSdkClientException() {
        // Setup
        when(mockSqsClient.listDeadLetterSourceQueues(ListDeadLetterSourceQueuesRequest.builder()
                .queueUrl("queueUrl")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListDeadLetterSourceQueues1());
    }

    @Test
    void testTryListDeadLetterSourceQueues1_SqsClientThrowsSqsException() {
        // Setup
        when(mockSqsClient.listDeadLetterSourceQueues(ListDeadLetterSourceQueuesRequest.builder()
                .queueUrl("queueUrl")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(SqsException.class);

        // Run the test
        assertThrows(SqsException.class, () -> myClassUnderTest.tryListDeadLetterSourceQueues1());
    }

    @Test
    void testTryListDeadLetterSourceQueues2() {
        // Setup
        // Configure SqsClient.listDeadLetterSourceQueues(...).
        final ListDeadLetterSourceQueuesResponse listDeadLetterSourceQueuesResponse = ListDeadLetterSourceQueuesResponse.builder()
                .queueUrls("queueUrls")
                .nextToken("nextToken")
                .build();
        when(mockSqsClient.listDeadLetterSourceQueues(any(Consumer.class)))
                .thenReturn(listDeadLetterSourceQueuesResponse);

        // Run the test
        myClassUnderTest.tryListDeadLetterSourceQueues2();

        // Verify the results
    }

    @Test
    void testTryListDeadLetterSourceQueues2_SqsClientThrowsQueueDoesNotExistException() {
        // Setup
        when(mockSqsClient.listDeadLetterSourceQueues(any(Consumer.class))).thenThrow(QueueDoesNotExistException.class);

        // Run the test
        assertThrows(QueueDoesNotExistException.class, () -> myClassUnderTest.tryListDeadLetterSourceQueues2());
    }

    @Test
    void testTryListDeadLetterSourceQueues2_SqsClientThrowsAwsServiceException() {
        // Setup
        when(mockSqsClient.listDeadLetterSourceQueues(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListDeadLetterSourceQueues2());
    }

    @Test
    void testTryListDeadLetterSourceQueues2_SqsClientThrowsSdkClientException() {
        // Setup
        when(mockSqsClient.listDeadLetterSourceQueues(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListDeadLetterSourceQueues2());
    }

    @Test
    void testTryListDeadLetterSourceQueues2_SqsClientThrowsSqsException() {
        // Setup
        when(mockSqsClient.listDeadLetterSourceQueues(any(Consumer.class))).thenThrow(SqsException.class);

        // Run the test
        assertThrows(SqsException.class, () -> myClassUnderTest.tryListDeadLetterSourceQueues2());
    }

    @Test
    void testTryListDeadLetterSourceQueuesPaginator1() {
        // Setup
        // Configure SqsClient.listDeadLetterSourceQueuesPaginator(...).
        final ListDeadLetterSourceQueuesIterable mockListDeadLetterSourceQueuesIterable = mock(
                ListDeadLetterSourceQueuesIterable.class);
        when(mockSqsClient.listDeadLetterSourceQueuesPaginator(ListDeadLetterSourceQueuesRequest.builder()
                .queueUrl("queueUrl")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenReturn(mockListDeadLetterSourceQueuesIterable);

        // Run the test
        myClassUnderTest.tryListDeadLetterSourceQueuesPaginator1();

        // Verify the results
    }

    @Test
    void testTryListDeadLetterSourceQueuesPaginator1_SqsClientReturnsNoItems() {
        // Setup
        // Configure SqsClient.listDeadLetterSourceQueuesPaginator(...).
        final ListDeadLetterSourceQueuesIterable mockListDeadLetterSourceQueuesIterable = mock(
                ListDeadLetterSourceQueuesIterable.class);
        when(mockSqsClient.listDeadLetterSourceQueuesPaginator(ListDeadLetterSourceQueuesRequest.builder()
                .queueUrl("queueUrl")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenReturn(mockListDeadLetterSourceQueuesIterable);

        // Run the test
        myClassUnderTest.tryListDeadLetterSourceQueuesPaginator1();

        // Verify the results
    }

    @Test
    void testTryListDeadLetterSourceQueuesPaginator1_SqsClientThrowsQueueDoesNotExistException() {
        // Setup
        when(mockSqsClient.listDeadLetterSourceQueuesPaginator(ListDeadLetterSourceQueuesRequest.builder()
                .queueUrl("queueUrl")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(QueueDoesNotExistException.class);

        // Run the test
        assertThrows(QueueDoesNotExistException.class,
                () -> myClassUnderTest.tryListDeadLetterSourceQueuesPaginator1());
    }

    @Test
    void testTryListDeadLetterSourceQueuesPaginator1_SqsClientThrowsAwsServiceException() {
        // Setup
        when(mockSqsClient.listDeadLetterSourceQueuesPaginator(ListDeadLetterSourceQueuesRequest.builder()
                .queueUrl("queueUrl")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListDeadLetterSourceQueuesPaginator1());
    }

    @Test
    void testTryListDeadLetterSourceQueuesPaginator1_SqsClientThrowsSdkClientException() {
        // Setup
        when(mockSqsClient.listDeadLetterSourceQueuesPaginator(ListDeadLetterSourceQueuesRequest.builder()
                .queueUrl("queueUrl")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListDeadLetterSourceQueuesPaginator1());
    }

    @Test
    void testTryListDeadLetterSourceQueuesPaginator1_SqsClientThrowsSqsException() {
        // Setup
        when(mockSqsClient.listDeadLetterSourceQueuesPaginator(ListDeadLetterSourceQueuesRequest.builder()
                .queueUrl("queueUrl")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(SqsException.class);

        // Run the test
        assertThrows(SqsException.class, () -> myClassUnderTest.tryListDeadLetterSourceQueuesPaginator1());
    }

    @Test
    void testTryListDeadLetterSourceQueuesPaginator2() {
        // Setup
        // Configure SqsClient.listDeadLetterSourceQueuesPaginator(...).
        final ListDeadLetterSourceQueuesIterable mockListDeadLetterSourceQueuesIterable = mock(
                ListDeadLetterSourceQueuesIterable.class);
        when(mockSqsClient.listDeadLetterSourceQueuesPaginator(any(Consumer.class)))
                .thenReturn(mockListDeadLetterSourceQueuesIterable);

        // Run the test
        myClassUnderTest.tryListDeadLetterSourceQueuesPaginator2();

        // Verify the results
    }

    @Test
    void testTryListDeadLetterSourceQueuesPaginator2_SqsClientReturnsNoItems() {
        // Setup
        // Configure SqsClient.listDeadLetterSourceQueuesPaginator(...).
        final ListDeadLetterSourceQueuesIterable mockListDeadLetterSourceQueuesIterable = mock(
                ListDeadLetterSourceQueuesIterable.class);
        when(mockSqsClient.listDeadLetterSourceQueuesPaginator(any(Consumer.class)))
                .thenReturn(mockListDeadLetterSourceQueuesIterable);

        // Run the test
        myClassUnderTest.tryListDeadLetterSourceQueuesPaginator2();

        // Verify the results
    }

    @Test
    void testTryListDeadLetterSourceQueuesPaginator2_SqsClientThrowsQueueDoesNotExistException() {
        // Setup
        when(mockSqsClient.listDeadLetterSourceQueuesPaginator(any(Consumer.class)))
                .thenThrow(QueueDoesNotExistException.class);

        // Run the test
        assertThrows(QueueDoesNotExistException.class,
                () -> myClassUnderTest.tryListDeadLetterSourceQueuesPaginator2());
    }

    @Test
    void testTryListDeadLetterSourceQueuesPaginator2_SqsClientThrowsAwsServiceException() {
        // Setup
        when(mockSqsClient.listDeadLetterSourceQueuesPaginator(any(Consumer.class)))
                .thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListDeadLetterSourceQueuesPaginator2());
    }

    @Test
    void testTryListDeadLetterSourceQueuesPaginator2_SqsClientThrowsSdkClientException() {
        // Setup
        when(mockSqsClient.listDeadLetterSourceQueuesPaginator(any(Consumer.class)))
                .thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListDeadLetterSourceQueuesPaginator2());
    }

    @Test
    void testTryListDeadLetterSourceQueuesPaginator2_SqsClientThrowsSqsException() {
        // Setup
        when(mockSqsClient.listDeadLetterSourceQueuesPaginator(any(Consumer.class))).thenThrow(SqsException.class);

        // Run the test
        assertThrows(SqsException.class, () -> myClassUnderTest.tryListDeadLetterSourceQueuesPaginator2());
    }

    @Test
    void testTryListQueueTags1() {
        // Setup
        // Configure SqsClient.listQueueTags(...).
        final ListQueueTagsResponse listQueueTagsResponse = ListQueueTagsResponse.builder()
                .tags(new HashMap<>())
                .build();
        when(mockSqsClient.listQueueTags(ListQueueTagsRequest.builder()
                .queueUrl("queueUrl")
                .build())).thenReturn(listQueueTagsResponse);

        // Run the test
        myClassUnderTest.tryListQueueTags1();

        // Verify the results
    }

    @Test
    void testTryListQueueTags1_SqsClientThrowsAwsServiceException() {
        // Setup
        when(mockSqsClient.listQueueTags(ListQueueTagsRequest.builder()
                .queueUrl("queueUrl")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListQueueTags1());
    }

    @Test
    void testTryListQueueTags1_SqsClientThrowsSdkClientException() {
        // Setup
        when(mockSqsClient.listQueueTags(ListQueueTagsRequest.builder()
                .queueUrl("queueUrl")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListQueueTags1());
    }

    @Test
    void testTryListQueueTags1_SqsClientThrowsSqsException() {
        // Setup
        when(mockSqsClient.listQueueTags(ListQueueTagsRequest.builder()
                .queueUrl("queueUrl")
                .build())).thenThrow(SqsException.class);

        // Run the test
        assertThrows(SqsException.class, () -> myClassUnderTest.tryListQueueTags1());
    }

    @Test
    void testTryListQueueTags2() {
        // Setup
        // Configure SqsClient.listQueueTags(...).
        final ListQueueTagsResponse listQueueTagsResponse = ListQueueTagsResponse.builder()
                .tags(new HashMap<>())
                .build();
        when(mockSqsClient.listQueueTags(any(Consumer.class))).thenReturn(listQueueTagsResponse);

        // Run the test
        myClassUnderTest.tryListQueueTags2();

        // Verify the results
    }

    @Test
    void testTryListQueueTags2_SqsClientThrowsAwsServiceException() {
        // Setup
        when(mockSqsClient.listQueueTags(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListQueueTags2());
    }

    @Test
    void testTryListQueueTags2_SqsClientThrowsSdkClientException() {
        // Setup
        when(mockSqsClient.listQueueTags(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListQueueTags2());
    }

    @Test
    void testTryListQueueTags2_SqsClientThrowsSqsException() {
        // Setup
        when(mockSqsClient.listQueueTags(any(Consumer.class))).thenThrow(SqsException.class);

        // Run the test
        assertThrows(SqsException.class, () -> myClassUnderTest.tryListQueueTags2());
    }

    @Test
    void testTryListQueues1() {
        // Setup
        // Configure SqsClient.listQueues(...).
        final ListQueuesResponse listQueuesResponse = ListQueuesResponse.builder()
                .queueUrls("queueUrls")
                .nextToken("nextToken")
                .build();
        when(mockSqsClient.listQueues()).thenReturn(listQueuesResponse);

        // Run the test
        myClassUnderTest.tryListQueues1();

        // Verify the results
    }

    @Test
    void testTryListQueues1_SqsClientThrowsAwsServiceException() {
        // Setup
        when(mockSqsClient.listQueues()).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListQueues1());
    }

    @Test
    void testTryListQueues1_SqsClientThrowsSdkClientException() {
        // Setup
        when(mockSqsClient.listQueues()).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListQueues1());
    }

    @Test
    void testTryListQueues1_SqsClientThrowsSqsException() {
        // Setup
        when(mockSqsClient.listQueues()).thenThrow(SqsException.class);

        // Run the test
        assertThrows(SqsException.class, () -> myClassUnderTest.tryListQueues1());
    }

    @Test
    void testTryListQueues2() {
        // Setup
        // Configure SqsClient.listQueues(...).
        final ListQueuesResponse listQueuesResponse = ListQueuesResponse.builder()
                .queueUrls("queueUrls")
                .nextToken("nextToken")
                .build();
        when(mockSqsClient.listQueues(ListQueuesRequest.builder()
                .queueNamePrefix("queueNamePrefix")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenReturn(listQueuesResponse);

        // Run the test
        myClassUnderTest.tryListQueues2();

        // Verify the results
    }

    @Test
    void testTryListQueues2_SqsClientThrowsAwsServiceException() {
        // Setup
        when(mockSqsClient.listQueues(ListQueuesRequest.builder()
                .queueNamePrefix("queueNamePrefix")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListQueues2());
    }

    @Test
    void testTryListQueues2_SqsClientThrowsSdkClientException() {
        // Setup
        when(mockSqsClient.listQueues(ListQueuesRequest.builder()
                .queueNamePrefix("queueNamePrefix")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListQueues2());
    }

    @Test
    void testTryListQueues2_SqsClientThrowsSqsException() {
        // Setup
        when(mockSqsClient.listQueues(ListQueuesRequest.builder()
                .queueNamePrefix("queueNamePrefix")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(SqsException.class);

        // Run the test
        assertThrows(SqsException.class, () -> myClassUnderTest.tryListQueues2());
    }

    @Test
    void testTryListQueues3() {
        // Setup
        // Configure SqsClient.listQueues(...).
        final ListQueuesResponse listQueuesResponse = ListQueuesResponse.builder()
                .queueUrls("queueUrls")
                .nextToken("nextToken")
                .build();
        when(mockSqsClient.listQueues(any(Consumer.class))).thenReturn(listQueuesResponse);

        // Run the test
        myClassUnderTest.tryListQueues3();

        // Verify the results
    }

    @Test
    void testTryListQueues3_SqsClientThrowsAwsServiceException() {
        // Setup
        when(mockSqsClient.listQueues(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListQueues3());
    }

    @Test
    void testTryListQueues3_SqsClientThrowsSdkClientException() {
        // Setup
        when(mockSqsClient.listQueues(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListQueues3());
    }

    @Test
    void testTryListQueues3_SqsClientThrowsSqsException() {
        // Setup
        when(mockSqsClient.listQueues(any(Consumer.class))).thenThrow(SqsException.class);

        // Run the test
        assertThrows(SqsException.class, () -> myClassUnderTest.tryListQueues3());
    }

    @Test
    void testTryListQueuesPaginator1() {
        // Setup
        // Configure SqsClient.listQueuesPaginator(...).
        final ListQueuesIterable mockListQueuesIterable = mock(ListQueuesIterable.class);
        when(mockSqsClient.listQueuesPaginator()).thenReturn(mockListQueuesIterable);

        // Run the test
        myClassUnderTest.tryListQueuesPaginator1();

        // Verify the results
    }

    @Test
    void testTryListQueuesPaginator1_SqsClientReturnsNoItems() {
        // Setup
        // Configure SqsClient.listQueuesPaginator(...).
        final ListQueuesIterable mockListQueuesIterable = mock(ListQueuesIterable.class);
        when(mockSqsClient.listQueuesPaginator()).thenReturn(mockListQueuesIterable);

        // Run the test
        myClassUnderTest.tryListQueuesPaginator1();

        // Verify the results
    }

    @Test
    void testTryListQueuesPaginator1_SqsClientThrowsAwsServiceException() {
        // Setup
        when(mockSqsClient.listQueuesPaginator()).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListQueuesPaginator1());
    }

    @Test
    void testTryListQueuesPaginator1_SqsClientThrowsSdkClientException() {
        // Setup
        when(mockSqsClient.listQueuesPaginator()).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListQueuesPaginator1());
    }

    @Test
    void testTryListQueuesPaginator1_SqsClientThrowsSqsException() {
        // Setup
        when(mockSqsClient.listQueuesPaginator()).thenThrow(SqsException.class);

        // Run the test
        assertThrows(SqsException.class, () -> myClassUnderTest.tryListQueuesPaginator1());
    }

    @Test
    void testTryListQueuesPaginator2() {
        // Setup
        // Configure SqsClient.listQueuesPaginator(...).
        final ListQueuesIterable mockListQueuesIterable = mock(ListQueuesIterable.class);
        when(mockSqsClient.listQueuesPaginator(ListQueuesRequest.builder()
                .queueNamePrefix("queueNamePrefix")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenReturn(mockListQueuesIterable);

        // Run the test
        myClassUnderTest.tryListQueuesPaginator2();

        // Verify the results
    }

    @Test
    void testTryListQueuesPaginator2_SqsClientReturnsNoItems() {
        // Setup
        // Configure SqsClient.listQueuesPaginator(...).
        final ListQueuesIterable mockListQueuesIterable = mock(ListQueuesIterable.class);
        when(mockSqsClient.listQueuesPaginator(ListQueuesRequest.builder()
                .queueNamePrefix("queueNamePrefix")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenReturn(mockListQueuesIterable);

        // Run the test
        myClassUnderTest.tryListQueuesPaginator2();

        // Verify the results
    }

    @Test
    void testTryListQueuesPaginator2_SqsClientThrowsAwsServiceException() {
        // Setup
        when(mockSqsClient.listQueuesPaginator(ListQueuesRequest.builder()
                .queueNamePrefix("queueNamePrefix")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListQueuesPaginator2());
    }

    @Test
    void testTryListQueuesPaginator2_SqsClientThrowsSdkClientException() {
        // Setup
        when(mockSqsClient.listQueuesPaginator(ListQueuesRequest.builder()
                .queueNamePrefix("queueNamePrefix")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListQueuesPaginator2());
    }

    @Test
    void testTryListQueuesPaginator2_SqsClientThrowsSqsException() {
        // Setup
        when(mockSqsClient.listQueuesPaginator(ListQueuesRequest.builder()
                .queueNamePrefix("queueNamePrefix")
                .nextToken("nextToken")
                .maxResults(0)
                .build())).thenThrow(SqsException.class);

        // Run the test
        assertThrows(SqsException.class, () -> myClassUnderTest.tryListQueuesPaginator2());
    }

    @Test
    void testTryListQueuesPaginator3() {
        // Setup
        // Configure SqsClient.listQueuesPaginator(...).
        final ListQueuesIterable mockListQueuesIterable = mock(ListQueuesIterable.class);
        when(mockSqsClient.listQueuesPaginator(any(Consumer.class))).thenReturn(mockListQueuesIterable);

        // Run the test
        myClassUnderTest.tryListQueuesPaginator3();

        // Verify the results
    }

    @Test
    void testTryListQueuesPaginator3_SqsClientReturnsNoItems() {
        // Setup
        // Configure SqsClient.listQueuesPaginator(...).
        final ListQueuesIterable mockListQueuesIterable = mock(ListQueuesIterable.class);
        when(mockSqsClient.listQueuesPaginator(any(Consumer.class))).thenReturn(mockListQueuesIterable);

        // Run the test
        myClassUnderTest.tryListQueuesPaginator3();

        // Verify the results
    }

    @Test
    void testTryListQueuesPaginator3_SqsClientThrowsAwsServiceException() {
        // Setup
        when(mockSqsClient.listQueuesPaginator(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListQueuesPaginator3());
    }

    @Test
    void testTryListQueuesPaginator3_SqsClientThrowsSdkClientException() {
        // Setup
        when(mockSqsClient.listQueuesPaginator(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListQueuesPaginator3());
    }

    @Test
    void testTryListQueuesPaginator3_SqsClientThrowsSqsException() {
        // Setup
        when(mockSqsClient.listQueuesPaginator(any(Consumer.class))).thenThrow(SqsException.class);

        // Run the test
        assertThrows(SqsException.class, () -> myClassUnderTest.tryListQueuesPaginator3());
    }

    @Test
    void testTryPurgeQueue1() {
        // Setup
        when(mockSqsClient.purgeQueue(PurgeQueueRequest.builder()
                .queueUrl("queueUrl")
                .build())).thenReturn(PurgeQueueResponse.builder().build());

        // Run the test
        myClassUnderTest.tryPurgeQueue1();

        // Verify the results
    }

    @Test
    void testTryPurgeQueue1_SqsClientThrowsQueueDoesNotExistException() {
        // Setup
        when(mockSqsClient.purgeQueue(PurgeQueueRequest.builder()
                .queueUrl("queueUrl")
                .build())).thenThrow(QueueDoesNotExistException.class);

        // Run the test
        assertThrows(QueueDoesNotExistException.class, () -> myClassUnderTest.tryPurgeQueue1());
    }

    @Test
    void testTryPurgeQueue1_SqsClientThrowsPurgeQueueInProgressException() {
        // Setup
        when(mockSqsClient.purgeQueue(PurgeQueueRequest.builder()
                .queueUrl("queueUrl")
                .build())).thenThrow(PurgeQueueInProgressException.class);

        // Run the test
        assertThrows(PurgeQueueInProgressException.class, () -> myClassUnderTest.tryPurgeQueue1());
    }

    @Test
    void testTryPurgeQueue1_SqsClientThrowsAwsServiceException() {
        // Setup
        when(mockSqsClient.purgeQueue(PurgeQueueRequest.builder()
                .queueUrl("queueUrl")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryPurgeQueue1());
    }

    @Test
    void testTryPurgeQueue1_SqsClientThrowsSdkClientException() {
        // Setup
        when(mockSqsClient.purgeQueue(PurgeQueueRequest.builder()
                .queueUrl("queueUrl")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryPurgeQueue1());
    }

    @Test
    void testTryPurgeQueue1_SqsClientThrowsSqsException() {
        // Setup
        when(mockSqsClient.purgeQueue(PurgeQueueRequest.builder()
                .queueUrl("queueUrl")
                .build())).thenThrow(SqsException.class);

        // Run the test
        assertThrows(SqsException.class, () -> myClassUnderTest.tryPurgeQueue1());
    }

    @Test
    void testTryPurgeQueue2() {
        // Setup
        when(mockSqsClient.purgeQueue(any(Consumer.class))).thenReturn(PurgeQueueResponse.builder().build());

        // Run the test
        myClassUnderTest.tryPurgeQueue2();

        // Verify the results
    }

    @Test
    void testTryPurgeQueue2_SqsClientThrowsQueueDoesNotExistException() {
        // Setup
        when(mockSqsClient.purgeQueue(any(Consumer.class))).thenThrow(QueueDoesNotExistException.class);

        // Run the test
        assertThrows(QueueDoesNotExistException.class, () -> myClassUnderTest.tryPurgeQueue2());
    }

    @Test
    void testTryPurgeQueue2_SqsClientThrowsPurgeQueueInProgressException() {
        // Setup
        when(mockSqsClient.purgeQueue(any(Consumer.class))).thenThrow(PurgeQueueInProgressException.class);

        // Run the test
        assertThrows(PurgeQueueInProgressException.class, () -> myClassUnderTest.tryPurgeQueue2());
    }

    @Test
    void testTryPurgeQueue2_SqsClientThrowsAwsServiceException() {
        // Setup
        when(mockSqsClient.purgeQueue(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryPurgeQueue2());
    }

    @Test
    void testTryPurgeQueue2_SqsClientThrowsSdkClientException() {
        // Setup
        when(mockSqsClient.purgeQueue(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryPurgeQueue2());
    }

    @Test
    void testTryPurgeQueue2_SqsClientThrowsSqsException() {
        // Setup
        when(mockSqsClient.purgeQueue(any(Consumer.class))).thenThrow(SqsException.class);

        // Run the test
        assertThrows(SqsException.class, () -> myClassUnderTest.tryPurgeQueue2());
    }

    @Test
    void testTryReceiveMessage1() {
        // Setup
        // Configure SqsClient.receiveMessage(...).
        final ReceiveMessageResponse receiveMessageResponse = ReceiveMessageResponse.builder()
                .messages(Message.builder()
                        .messageId("messageId")
                        .receiptHandle("receiptHandle")
                        .md5OfBody("md5OfBody")
                        .body("body")
                        .attributes(new HashMap<>())
                        .md5OfMessageAttributes("md5OfMessageAttributes")
                        .messageAttributes(new HashMap<>())
                        .build())
                .build();
        when(mockSqsClient.receiveMessage(ReceiveMessageRequest.builder()
                .queueUrl("queueUrl")
                .attributeNames(QueueAttributeName.ALL)
                .messageAttributeNames("messageAttributeNames")
                .maxNumberOfMessages(0)
                .visibilityTimeout(0)
                .waitTimeSeconds(0)
                .receiveRequestAttemptId("receiveRequestAttemptId")
                .build())).thenReturn(receiveMessageResponse);

        // Run the test
        myClassUnderTest.tryReceiveMessage1();

        // Verify the results
    }

    @Test
    void testTryReceiveMessage1_SqsClientThrowsOverLimitException() {
        // Setup
        when(mockSqsClient.receiveMessage(ReceiveMessageRequest.builder()
                .queueUrl("queueUrl")
                .attributeNames(QueueAttributeName.ALL)
                .messageAttributeNames("messageAttributeNames")
                .maxNumberOfMessages(0)
                .visibilityTimeout(0)
                .waitTimeSeconds(0)
                .receiveRequestAttemptId("receiveRequestAttemptId")
                .build())).thenThrow(OverLimitException.class);

        // Run the test
        assertThrows(OverLimitException.class, () -> myClassUnderTest.tryReceiveMessage1());
    }

    @Test
    void testTryReceiveMessage1_SqsClientThrowsAwsServiceException() {
        // Setup
        when(mockSqsClient.receiveMessage(ReceiveMessageRequest.builder()
                .queueUrl("queueUrl")
                .attributeNames(QueueAttributeName.ALL)
                .messageAttributeNames("messageAttributeNames")
                .maxNumberOfMessages(0)
                .visibilityTimeout(0)
                .waitTimeSeconds(0)
                .receiveRequestAttemptId("receiveRequestAttemptId")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryReceiveMessage1());
    }

    @Test
    void testTryReceiveMessage1_SqsClientThrowsSdkClientException() {
        // Setup
        when(mockSqsClient.receiveMessage(ReceiveMessageRequest.builder()
                .queueUrl("queueUrl")
                .attributeNames(QueueAttributeName.ALL)
                .messageAttributeNames("messageAttributeNames")
                .maxNumberOfMessages(0)
                .visibilityTimeout(0)
                .waitTimeSeconds(0)
                .receiveRequestAttemptId("receiveRequestAttemptId")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryReceiveMessage1());
    }

    @Test
    void testTryReceiveMessage1_SqsClientThrowsSqsException() {
        // Setup
        when(mockSqsClient.receiveMessage(ReceiveMessageRequest.builder()
                .queueUrl("queueUrl")
                .attributeNames(QueueAttributeName.ALL)
                .messageAttributeNames("messageAttributeNames")
                .maxNumberOfMessages(0)
                .visibilityTimeout(0)
                .waitTimeSeconds(0)
                .receiveRequestAttemptId("receiveRequestAttemptId")
                .build())).thenThrow(SqsException.class);

        // Run the test
        assertThrows(SqsException.class, () -> myClassUnderTest.tryReceiveMessage1());
    }

    @Test
    void testTryReceiveMessage2() {
        // Setup
        // Configure SqsClient.receiveMessage(...).
        final ReceiveMessageResponse receiveMessageResponse = ReceiveMessageResponse.builder()
                .messages(Message.builder()
                        .messageId("messageId")
                        .receiptHandle("receiptHandle")
                        .md5OfBody("md5OfBody")
                        .body("body")
                        .attributes(new HashMap<>())
                        .md5OfMessageAttributes("md5OfMessageAttributes")
                        .messageAttributes(new HashMap<>())
                        .build())
                .build();
        when(mockSqsClient.receiveMessage(any(Consumer.class))).thenReturn(receiveMessageResponse);

        // Run the test
        myClassUnderTest.tryReceiveMessage2();

        // Verify the results
    }

    @Test
    void testTryReceiveMessage2_SqsClientThrowsOverLimitException() {
        // Setup
        when(mockSqsClient.receiveMessage(any(Consumer.class))).thenThrow(OverLimitException.class);

        // Run the test
        assertThrows(OverLimitException.class, () -> myClassUnderTest.tryReceiveMessage2());
    }

    @Test
    void testTryReceiveMessage2_SqsClientThrowsAwsServiceException() {
        // Setup
        when(mockSqsClient.receiveMessage(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryReceiveMessage2());
    }

    @Test
    void testTryReceiveMessage2_SqsClientThrowsSdkClientException() {
        // Setup
        when(mockSqsClient.receiveMessage(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryReceiveMessage2());
    }

    @Test
    void testTryReceiveMessage2_SqsClientThrowsSqsException() {
        // Setup
        when(mockSqsClient.receiveMessage(any(Consumer.class))).thenThrow(SqsException.class);

        // Run the test
        assertThrows(SqsException.class, () -> myClassUnderTest.tryReceiveMessage2());
    }

    @Test
    void testTryRemovePermission1() {
        // Setup
        // Configure SqsClient.removePermission(...).
        final RemovePermissionResponse removePermissionResponse = RemovePermissionResponse.builder().build();
        when(mockSqsClient.removePermission(RemovePermissionRequest.builder()
                .queueUrl("queueUrl")
                .label("label")
                .build())).thenReturn(removePermissionResponse);

        // Run the test
        myClassUnderTest.tryRemovePermission1();

        // Verify the results
    }

    @Test
    void testTryRemovePermission1_SqsClientThrowsAwsServiceException() {
        // Setup
        when(mockSqsClient.removePermission(RemovePermissionRequest.builder()
                .queueUrl("queueUrl")
                .label("label")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryRemovePermission1());
    }

    @Test
    void testTryRemovePermission1_SqsClientThrowsSdkClientException() {
        // Setup
        when(mockSqsClient.removePermission(RemovePermissionRequest.builder()
                .queueUrl("queueUrl")
                .label("label")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryRemovePermission1());
    }

    @Test
    void testTryRemovePermission1_SqsClientThrowsSqsException() {
        // Setup
        when(mockSqsClient.removePermission(RemovePermissionRequest.builder()
                .queueUrl("queueUrl")
                .label("label")
                .build())).thenThrow(SqsException.class);

        // Run the test
        assertThrows(SqsException.class, () -> myClassUnderTest.tryRemovePermission1());
    }

    @Test
    void testTryRemovePermission2() {
        // Setup
        // Configure SqsClient.removePermission(...).
        final RemovePermissionResponse removePermissionResponse = RemovePermissionResponse.builder().build();
        when(mockSqsClient.removePermission(any(Consumer.class))).thenReturn(removePermissionResponse);

        // Run the test
        myClassUnderTest.tryRemovePermission2();

        // Verify the results
    }

    @Test
    void testTryRemovePermission2_SqsClientThrowsAwsServiceException() {
        // Setup
        when(mockSqsClient.removePermission(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryRemovePermission2());
    }

    @Test
    void testTryRemovePermission2_SqsClientThrowsSdkClientException() {
        // Setup
        when(mockSqsClient.removePermission(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryRemovePermission2());
    }

    @Test
    void testTryRemovePermission2_SqsClientThrowsSqsException() {
        // Setup
        when(mockSqsClient.removePermission(any(Consumer.class))).thenThrow(SqsException.class);

        // Run the test
        assertThrows(SqsException.class, () -> myClassUnderTest.tryRemovePermission2());
    }

    @Test
    void testTrySendMessage1() {
        // Setup
        // Configure SqsClient.sendMessage(...).
        final SendMessageResponse sendMessageResponse = SendMessageResponse.builder()
                .md5OfMessageBody("md5OfMessageBody")
                .md5OfMessageAttributes("md5OfMessageAttributes")
                .md5OfMessageSystemAttributes("md5OfMessageSystemAttributes")
                .messageId("messageId")
                .sequenceNumber("sequenceNumber")
                .build();
        when(mockSqsClient.sendMessage(SendMessageRequest.builder()
                .queueUrl("queueUrl")
                .messageBody("messageBody")
                .messageDeduplicationId("messageDeduplicationId")
                .messageGroupId("messageGroupId")
                .build())).thenReturn(sendMessageResponse);

        // Run the test
        myClassUnderTest.trySendMessage1();

        // Verify the results
    }

    @Test
    void testTrySendMessage1_SqsClientThrowsInvalidMessageContentsException() {
        // Setup
        when(mockSqsClient.sendMessage(SendMessageRequest.builder()
                .queueUrl("queueUrl")
                .messageBody("messageBody")
                .messageDeduplicationId("messageDeduplicationId")
                .messageGroupId("messageGroupId")
                .build())).thenThrow(InvalidMessageContentsException.class);

        // Run the test
        assertThrows(InvalidMessageContentsException.class, () -> myClassUnderTest.trySendMessage1());
    }

    @Test
    void testTrySendMessage1_SqsClientThrowsUnsupportedOperationException() {
        // Setup
        when(mockSqsClient.sendMessage(SendMessageRequest.builder()
                .queueUrl("queueUrl")
                .messageBody("messageBody")
                .messageDeduplicationId("messageDeduplicationId")
                .messageGroupId("messageGroupId")
                .build())).thenThrow(UnsupportedOperationException.class);

        // Run the test
        assertThrows(UnsupportedOperationException.class, () -> myClassUnderTest.trySendMessage1());
    }

    @Test
    void testTrySendMessage1_SqsClientThrowsAwsServiceException() {
        // Setup
        when(mockSqsClient.sendMessage(SendMessageRequest.builder()
                .queueUrl("queueUrl")
                .messageBody("messageBody")
                .messageDeduplicationId("messageDeduplicationId")
                .messageGroupId("messageGroupId")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.trySendMessage1());
    }

    @Test
    void testTrySendMessage1_SqsClientThrowsSdkClientException() {
        // Setup
        when(mockSqsClient.sendMessage(SendMessageRequest.builder()
                .queueUrl("queueUrl")
                .messageBody("messageBody")
                .messageDeduplicationId("messageDeduplicationId")
                .messageGroupId("messageGroupId")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.trySendMessage1());
    }

    @Test
    void testTrySendMessage1_SqsClientThrowsSqsException() {
        // Setup
        when(mockSqsClient.sendMessage(SendMessageRequest.builder()
                .queueUrl("queueUrl")
                .messageBody("messageBody")
                .messageDeduplicationId("messageDeduplicationId")
                .messageGroupId("messageGroupId")
                .build())).thenThrow(SqsException.class);

        // Run the test
        assertThrows(SqsException.class, () -> myClassUnderTest.trySendMessage1());
    }

    @Test
    void testTrySendMessage2() {
        // Setup
        // Configure SqsClient.sendMessage(...).
        final SendMessageResponse sendMessageResponse = SendMessageResponse.builder()
                .md5OfMessageBody("md5OfMessageBody")
                .md5OfMessageAttributes("md5OfMessageAttributes")
                .md5OfMessageSystemAttributes("md5OfMessageSystemAttributes")
                .messageId("messageId")
                .sequenceNumber("sequenceNumber")
                .build();
        when(mockSqsClient.sendMessage(any(Consumer.class))).thenReturn(sendMessageResponse);

        // Run the test
        myClassUnderTest.trySendMessage2();

        // Verify the results
    }

    @Test
    void testTrySendMessage2_SqsClientThrowsInvalidMessageContentsException() {
        // Setup
        when(mockSqsClient.sendMessage(any(Consumer.class))).thenThrow(InvalidMessageContentsException.class);

        // Run the test
        assertThrows(InvalidMessageContentsException.class, () -> myClassUnderTest.trySendMessage2());
    }

    @Test
    void testTrySendMessage2_SqsClientThrowsUnsupportedOperationException() {
        // Setup
        when(mockSqsClient.sendMessage(any(Consumer.class))).thenThrow(UnsupportedOperationException.class);

        // Run the test
        assertThrows(UnsupportedOperationException.class, () -> myClassUnderTest.trySendMessage2());
    }

    @Test
    void testTrySendMessage2_SqsClientThrowsAwsServiceException() {
        // Setup
        when(mockSqsClient.sendMessage(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.trySendMessage2());
    }

    @Test
    void testTrySendMessage2_SqsClientThrowsSdkClientException() {
        // Setup
        when(mockSqsClient.sendMessage(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.trySendMessage2());
    }

    @Test
    void testTrySendMessage2_SqsClientThrowsSqsException() {
        // Setup
        when(mockSqsClient.sendMessage(any(Consumer.class))).thenThrow(SqsException.class);

        // Run the test
        assertThrows(SqsException.class, () -> myClassUnderTest.trySendMessage2());
    }

    @Test
    void testTrySendMessageBatch1() {
        // Setup
        // Configure SqsClient.sendMessageBatch(...).
        final SendMessageBatchResponse sendMessageBatchResponse = SendMessageBatchResponse.builder()
                .successful(SendMessageBatchResultEntry.builder()
                        .id("id")
                        .messageId("messageId")
                        .md5OfMessageBody("md5OfMessageBody")
                        .md5OfMessageAttributes("md5OfMessageAttributes")
                        .md5OfMessageSystemAttributes("md5OfMessageSystemAttributes")
                        .sequenceNumber("sequenceNumber")
                        .build())
                .failed(BatchResultErrorEntry.builder()
                        .id("id")
                        .senderFault(false)
                        .code("code")
                        .message("message")
                        .build())
                .build();
        when(mockSqsClient.sendMessageBatch(SendMessageBatchRequest.builder()
                .queueUrl("queueUrl")
                .entries(SendMessageBatchRequestEntry.builder()
                        .id("id")
                        .messageBody("messageBody")
                        .build())
                .build())).thenReturn(sendMessageBatchResponse);

        // Run the test
        myClassUnderTest.trySendMessageBatch1();

        // Verify the results
    }

    @Test
    void testTrySendMessageBatch1_SqsClientThrowsTooManyEntriesInBatchRequestException() {
        // Setup
        when(mockSqsClient.sendMessageBatch(SendMessageBatchRequest.builder()
                .queueUrl("queueUrl")
                .entries(SendMessageBatchRequestEntry.builder()
                        .id("id")
                        .messageBody("messageBody")
                        .build())
                .build())).thenThrow(TooManyEntriesInBatchRequestException.class);

        // Run the test
        assertThrows(TooManyEntriesInBatchRequestException.class, () -> myClassUnderTest.trySendMessageBatch1());
    }

    @Test
    void testTrySendMessageBatch1_SqsClientThrowsEmptyBatchRequestException() {
        // Setup
        when(mockSqsClient.sendMessageBatch(SendMessageBatchRequest.builder()
                .queueUrl("queueUrl")
                .entries(SendMessageBatchRequestEntry.builder()
                        .id("id")
                        .messageBody("messageBody")
                        .build())
                .build())).thenThrow(EmptyBatchRequestException.class);

        // Run the test
        assertThrows(EmptyBatchRequestException.class, () -> myClassUnderTest.trySendMessageBatch1());
    }

    @Test
    void testTrySendMessageBatch1_SqsClientThrowsBatchEntryIdsNotDistinctException() {
        // Setup
        when(mockSqsClient.sendMessageBatch(SendMessageBatchRequest.builder()
                .queueUrl("queueUrl")
                .entries(SendMessageBatchRequestEntry.builder()
                        .id("id")
                        .messageBody("messageBody")
                        .build())
                .build())).thenThrow(BatchEntryIdsNotDistinctException.class);

        // Run the test
        assertThrows(BatchEntryIdsNotDistinctException.class, () -> myClassUnderTest.trySendMessageBatch1());
    }

    @Test
    void testTrySendMessageBatch1_SqsClientThrowsBatchRequestTooLongException() {
        // Setup
        when(mockSqsClient.sendMessageBatch(SendMessageBatchRequest.builder()
                .queueUrl("queueUrl")
                .entries(SendMessageBatchRequestEntry.builder()
                        .id("id")
                        .messageBody("messageBody")
                        .build())
                .build())).thenThrow(BatchRequestTooLongException.class);

        // Run the test
        assertThrows(BatchRequestTooLongException.class, () -> myClassUnderTest.trySendMessageBatch1());
    }

    @Test
    void testTrySendMessageBatch1_SqsClientThrowsInvalidBatchEntryIdException() {
        // Setup
        when(mockSqsClient.sendMessageBatch(SendMessageBatchRequest.builder()
                .queueUrl("queueUrl")
                .entries(SendMessageBatchRequestEntry.builder()
                        .id("id")
                        .messageBody("messageBody")
                        .build())
                .build())).thenThrow(InvalidBatchEntryIdException.class);

        // Run the test
        assertThrows(InvalidBatchEntryIdException.class, () -> myClassUnderTest.trySendMessageBatch1());
    }

    @Test
    void testTrySendMessageBatch1_SqsClientThrowsUnsupportedOperationException() {
        // Setup
        when(mockSqsClient.sendMessageBatch(SendMessageBatchRequest.builder()
                .queueUrl("queueUrl")
                .entries(SendMessageBatchRequestEntry.builder()
                        .id("id")
                        .messageBody("messageBody")
                        .build())
                .build())).thenThrow(UnsupportedOperationException.class);

        // Run the test
        assertThrows(UnsupportedOperationException.class, () -> myClassUnderTest.trySendMessageBatch1());
    }

    @Test
    void testTrySendMessageBatch1_SqsClientThrowsAwsServiceException() {
        // Setup
        when(mockSqsClient.sendMessageBatch(SendMessageBatchRequest.builder()
                .queueUrl("queueUrl")
                .entries(SendMessageBatchRequestEntry.builder()
                        .id("id")
                        .messageBody("messageBody")
                        .build())
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.trySendMessageBatch1());
    }

    @Test
    void testTrySendMessageBatch1_SqsClientThrowsSdkClientException() {
        // Setup
        when(mockSqsClient.sendMessageBatch(SendMessageBatchRequest.builder()
                .queueUrl("queueUrl")
                .entries(SendMessageBatchRequestEntry.builder()
                        .id("id")
                        .messageBody("messageBody")
                        .build())
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.trySendMessageBatch1());
    }

    @Test
    void testTrySendMessageBatch1_SqsClientThrowsSqsException() {
        // Setup
        when(mockSqsClient.sendMessageBatch(SendMessageBatchRequest.builder()
                .queueUrl("queueUrl")
                .entries(SendMessageBatchRequestEntry.builder()
                        .id("id")
                        .messageBody("messageBody")
                        .build())
                .build())).thenThrow(SqsException.class);

        // Run the test
        assertThrows(SqsException.class, () -> myClassUnderTest.trySendMessageBatch1());
    }

    @Test
    void testTrySendMessageBatch2() {
        // Setup
        // Configure SqsClient.sendMessageBatch(...).
        final SendMessageBatchResponse sendMessageBatchResponse = SendMessageBatchResponse.builder()
                .successful(SendMessageBatchResultEntry.builder()
                        .id("id")
                        .messageId("messageId")
                        .md5OfMessageBody("md5OfMessageBody")
                        .md5OfMessageAttributes("md5OfMessageAttributes")
                        .md5OfMessageSystemAttributes("md5OfMessageSystemAttributes")
                        .sequenceNumber("sequenceNumber")
                        .build())
                .failed(BatchResultErrorEntry.builder()
                        .id("id")
                        .senderFault(false)
                        .code("code")
                        .message("message")
                        .build())
                .build();
        when(mockSqsClient.sendMessageBatch(any(Consumer.class))).thenReturn(sendMessageBatchResponse);

        // Run the test
        myClassUnderTest.trySendMessageBatch2();

        // Verify the results
    }

    @Test
    void testTrySendMessageBatch2_SqsClientThrowsTooManyEntriesInBatchRequestException() {
        // Setup
        when(mockSqsClient.sendMessageBatch(any(Consumer.class)))
                .thenThrow(TooManyEntriesInBatchRequestException.class);

        // Run the test
        assertThrows(TooManyEntriesInBatchRequestException.class, () -> myClassUnderTest.trySendMessageBatch2());
    }

    @Test
    void testTrySendMessageBatch2_SqsClientThrowsEmptyBatchRequestException() {
        // Setup
        when(mockSqsClient.sendMessageBatch(any(Consumer.class))).thenThrow(EmptyBatchRequestException.class);

        // Run the test
        assertThrows(EmptyBatchRequestException.class, () -> myClassUnderTest.trySendMessageBatch2());
    }

    @Test
    void testTrySendMessageBatch2_SqsClientThrowsBatchEntryIdsNotDistinctException() {
        // Setup
        when(mockSqsClient.sendMessageBatch(any(Consumer.class))).thenThrow(BatchEntryIdsNotDistinctException.class);

        // Run the test
        assertThrows(BatchEntryIdsNotDistinctException.class, () -> myClassUnderTest.trySendMessageBatch2());
    }

    @Test
    void testTrySendMessageBatch2_SqsClientThrowsBatchRequestTooLongException() {
        // Setup
        when(mockSqsClient.sendMessageBatch(any(Consumer.class))).thenThrow(BatchRequestTooLongException.class);

        // Run the test
        assertThrows(BatchRequestTooLongException.class, () -> myClassUnderTest.trySendMessageBatch2());
    }

    @Test
    void testTrySendMessageBatch2_SqsClientThrowsInvalidBatchEntryIdException() {
        // Setup
        when(mockSqsClient.sendMessageBatch(any(Consumer.class))).thenThrow(InvalidBatchEntryIdException.class);

        // Run the test
        assertThrows(InvalidBatchEntryIdException.class, () -> myClassUnderTest.trySendMessageBatch2());
    }

    @Test
    void testTrySendMessageBatch2_SqsClientThrowsUnsupportedOperationException() {
        // Setup
        when(mockSqsClient.sendMessageBatch(any(Consumer.class))).thenThrow(UnsupportedOperationException.class);

        // Run the test
        assertThrows(UnsupportedOperationException.class, () -> myClassUnderTest.trySendMessageBatch2());
    }

    @Test
    void testTrySendMessageBatch2_SqsClientThrowsAwsServiceException() {
        // Setup
        when(mockSqsClient.sendMessageBatch(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.trySendMessageBatch2());
    }

    @Test
    void testTrySendMessageBatch2_SqsClientThrowsSdkClientException() {
        // Setup
        when(mockSqsClient.sendMessageBatch(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.trySendMessageBatch2());
    }

    @Test
    void testTrySendMessageBatch2_SqsClientThrowsSqsException() {
        // Setup
        when(mockSqsClient.sendMessageBatch(any(Consumer.class))).thenThrow(SqsException.class);

        // Run the test
        assertThrows(SqsException.class, () -> myClassUnderTest.trySendMessageBatch2());
    }

    @Test
    void testTrySetQueueAttributes1() {
        // Setup
        // Configure SqsClient.setQueueAttributes(...).
        final SetQueueAttributesResponse setQueueAttributesResponse = SetQueueAttributesResponse.builder().build();
        when(mockSqsClient.setQueueAttributes(SetQueueAttributesRequest.builder()
                .queueUrl("queueUrl")
                .attributes(new HashMap<>())
                .build())).thenReturn(setQueueAttributesResponse);

        // Run the test
        myClassUnderTest.trySetQueueAttributes1();

        // Verify the results
    }

    @Test
    void testTrySetQueueAttributes1_SqsClientThrowsInvalidAttributeNameException() {
        // Setup
        when(mockSqsClient.setQueueAttributes(SetQueueAttributesRequest.builder()
                .queueUrl("queueUrl")
                .attributes(new HashMap<>())
                .build())).thenThrow(InvalidAttributeNameException.class);

        // Run the test
        assertThrows(InvalidAttributeNameException.class, () -> myClassUnderTest.trySetQueueAttributes1());
    }

    @Test
    void testTrySetQueueAttributes1_SqsClientThrowsAwsServiceException() {
        // Setup
        when(mockSqsClient.setQueueAttributes(SetQueueAttributesRequest.builder()
                .queueUrl("queueUrl")
                .attributes(new HashMap<>())
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.trySetQueueAttributes1());
    }

    @Test
    void testTrySetQueueAttributes1_SqsClientThrowsSdkClientException() {
        // Setup
        when(mockSqsClient.setQueueAttributes(SetQueueAttributesRequest.builder()
                .queueUrl("queueUrl")
                .attributes(new HashMap<>())
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.trySetQueueAttributes1());
    }

    @Test
    void testTrySetQueueAttributes1_SqsClientThrowsSqsException() {
        // Setup
        when(mockSqsClient.setQueueAttributes(SetQueueAttributesRequest.builder()
                .queueUrl("queueUrl")
                .attributes(new HashMap<>())
                .build())).thenThrow(SqsException.class);

        // Run the test
        assertThrows(SqsException.class, () -> myClassUnderTest.trySetQueueAttributes1());
    }

    @Test
    void testTrySetQueueAttributes2() {
        // Setup
        // Configure SqsClient.setQueueAttributes(...).
        final SetQueueAttributesResponse setQueueAttributesResponse = SetQueueAttributesResponse.builder().build();
        when(mockSqsClient.setQueueAttributes(any(Consumer.class))).thenReturn(setQueueAttributesResponse);

        // Run the test
        myClassUnderTest.trySetQueueAttributes2();

        // Verify the results
    }

    @Test
    void testTrySetQueueAttributes2_SqsClientThrowsInvalidAttributeNameException() {
        // Setup
        when(mockSqsClient.setQueueAttributes(any(Consumer.class))).thenThrow(InvalidAttributeNameException.class);

        // Run the test
        assertThrows(InvalidAttributeNameException.class, () -> myClassUnderTest.trySetQueueAttributes2());
    }

    @Test
    void testTrySetQueueAttributes2_SqsClientThrowsAwsServiceException() {
        // Setup
        when(mockSqsClient.setQueueAttributes(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.trySetQueueAttributes2());
    }

    @Test
    void testTrySetQueueAttributes2_SqsClientThrowsSdkClientException() {
        // Setup
        when(mockSqsClient.setQueueAttributes(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.trySetQueueAttributes2());
    }

    @Test
    void testTrySetQueueAttributes2_SqsClientThrowsSqsException() {
        // Setup
        when(mockSqsClient.setQueueAttributes(any(Consumer.class))).thenThrow(SqsException.class);

        // Run the test
        assertThrows(SqsException.class, () -> myClassUnderTest.trySetQueueAttributes2());
    }

    @Test
    void testTryTagQueue1() {
        // Setup
        when(mockSqsClient.tagQueue(TagQueueRequest.builder()
                .queueUrl("queueUrl")
                .tags(new HashMap<>())
                .build())).thenReturn(TagQueueResponse.builder().build());

        // Run the test
        myClassUnderTest.tryTagQueue1();

        // Verify the results
    }

    @Test
    void testTryTagQueue1_SqsClientThrowsAwsServiceException() {
        // Setup
        when(mockSqsClient.tagQueue(TagQueueRequest.builder()
                .queueUrl("queueUrl")
                .tags(new HashMap<>())
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryTagQueue1());
    }

    @Test
    void testTryTagQueue1_SqsClientThrowsSdkClientException() {
        // Setup
        when(mockSqsClient.tagQueue(TagQueueRequest.builder()
                .queueUrl("queueUrl")
                .tags(new HashMap<>())
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryTagQueue1());
    }

    @Test
    void testTryTagQueue1_SqsClientThrowsSqsException() {
        // Setup
        when(mockSqsClient.tagQueue(TagQueueRequest.builder()
                .queueUrl("queueUrl")
                .tags(new HashMap<>())
                .build())).thenThrow(SqsException.class);

        // Run the test
        assertThrows(SqsException.class, () -> myClassUnderTest.tryTagQueue1());
    }

    @Test
    void testTryTagQueue2() {
        // Setup
        when(mockSqsClient.tagQueue(any(Consumer.class))).thenReturn(TagQueueResponse.builder().build());

        // Run the test
        myClassUnderTest.tryTagQueue2();

        // Verify the results
    }

    @Test
    void testTryTagQueue2_SqsClientThrowsAwsServiceException() {
        // Setup
        when(mockSqsClient.tagQueue(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryTagQueue2());
    }

    @Test
    void testTryTagQueue2_SqsClientThrowsSdkClientException() {
        // Setup
        when(mockSqsClient.tagQueue(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryTagQueue2());
    }

    @Test
    void testTryTagQueue2_SqsClientThrowsSqsException() {
        // Setup
        when(mockSqsClient.tagQueue(any(Consumer.class))).thenThrow(SqsException.class);

        // Run the test
        assertThrows(SqsException.class, () -> myClassUnderTest.tryTagQueue2());
    }

    @Test
    void testTryUntagQueue1() {
        // Setup
        when(mockSqsClient.untagQueue(UntagQueueRequest.builder()
                .queueUrl("queueUrl")
                .tagKeys("tagKeys")
                .build())).thenReturn(UntagQueueResponse.builder().build());

        // Run the test
        myClassUnderTest.tryUntagQueue1();

        // Verify the results
    }

    @Test
    void testTryUntagQueue1_SqsClientThrowsAwsServiceException() {
        // Setup
        when(mockSqsClient.untagQueue(UntagQueueRequest.builder()
                .queueUrl("queueUrl")
                .tagKeys("tagKeys")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryUntagQueue1());
    }

    @Test
    void testTryUntagQueue1_SqsClientThrowsSdkClientException() {
        // Setup
        when(mockSqsClient.untagQueue(UntagQueueRequest.builder()
                .queueUrl("queueUrl")
                .tagKeys("tagKeys")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryUntagQueue1());
    }

    @Test
    void testTryUntagQueue1_SqsClientThrowsSqsException() {
        // Setup
        when(mockSqsClient.untagQueue(UntagQueueRequest.builder()
                .queueUrl("queueUrl")
                .tagKeys("tagKeys")
                .build())).thenThrow(SqsException.class);

        // Run the test
        assertThrows(SqsException.class, () -> myClassUnderTest.tryUntagQueue1());
    }

    @Test
    void testTryUntagQueue2() {
        // Setup
        when(mockSqsClient.untagQueue(any(Consumer.class))).thenReturn(UntagQueueResponse.builder().build());

        // Run the test
        myClassUnderTest.tryUntagQueue2();

        // Verify the results
    }

    @Test
    void testTryUntagQueue2_SqsClientThrowsAwsServiceException() {
        // Setup
        when(mockSqsClient.untagQueue(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryUntagQueue2());
    }

    @Test
    void testTryUntagQueue2_SqsClientThrowsSdkClientException() {
        // Setup
        when(mockSqsClient.untagQueue(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryUntagQueue2());
    }

    @Test
    void testTryUntagQueue2_SqsClientThrowsSqsException() {
        // Setup
        when(mockSqsClient.untagQueue(any(Consumer.class))).thenThrow(SqsException.class);

        // Run the test
        assertThrows(SqsException.class, () -> myClassUnderTest.tryUntagQueue2());
    }
}
