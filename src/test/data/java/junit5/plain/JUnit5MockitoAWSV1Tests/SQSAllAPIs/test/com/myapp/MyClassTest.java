package com.myapp;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.ResponseMetadata;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private AmazonSQSClient mockAmazonSQSClient;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockAmazonSQSClient);
    }

    @Test
    void testTryAddPermission() {
        // Setup
        when(mockAmazonSQSClient.addPermission(new AddPermissionRequest("queueUrl", "label", Arrays.asList("value"),
                Arrays.asList("value")))).thenReturn(new AddPermissionResult());

        // Run the test
        myClassUnderTest.tryAddPermission();

        // Verify the results
    }

    @Test
    void testTryAddPermission_AmazonSQSClientThrowsOverLimitException() {
        // Setup
        when(mockAmazonSQSClient.addPermission(new AddPermissionRequest("queueUrl", "label", Arrays.asList("value"),
                Arrays.asList("value")))).thenThrow(OverLimitException.class);

        // Run the test
        assertThrows(OverLimitException.class, () -> myClassUnderTest.tryAddPermission());
    }

    @Test
    void testTryAddPermission1() {
        // Setup
        when(mockAmazonSQSClient.addPermission("queueUrl", "label", Arrays.asList("value"),
                Arrays.asList("value"))).thenReturn(new AddPermissionResult());

        // Run the test
        myClassUnderTest.tryAddPermission1();

        // Verify the results
    }

    @Test
    void testTryChangeMessageVisibility() {
        // Setup
        when(mockAmazonSQSClient.changeMessageVisibility(
                new ChangeMessageVisibilityRequest("queueUrl", "receiptHandle", 0)))
                .thenReturn(new ChangeMessageVisibilityResult());

        // Run the test
        myClassUnderTest.tryChangeMessageVisibility();

        // Verify the results
    }

    @Test
    void testTryChangeMessageVisibility_AmazonSQSClientThrowsMessageNotInflightException() {
        // Setup
        when(mockAmazonSQSClient.changeMessageVisibility(
                new ChangeMessageVisibilityRequest("queueUrl", "receiptHandle", 0)))
                .thenThrow(MessageNotInflightException.class);

        // Run the test
        assertThrows(MessageNotInflightException.class, () -> myClassUnderTest.tryChangeMessageVisibility());
    }

    @Test
    void testTryChangeMessageVisibility_AmazonSQSClientThrowsReceiptHandleIsInvalidException() {
        // Setup
        when(mockAmazonSQSClient.changeMessageVisibility(
                new ChangeMessageVisibilityRequest("queueUrl", "receiptHandle", 0)))
                .thenThrow(ReceiptHandleIsInvalidException.class);

        // Run the test
        assertThrows(ReceiptHandleIsInvalidException.class, () -> myClassUnderTest.tryChangeMessageVisibility());
    }

    @Test
    void testTryChangeMessageVisibility1() {
        // Setup
        when(mockAmazonSQSClient.changeMessageVisibility("queueUrl", "receiptHandle", 0))
                .thenReturn(new ChangeMessageVisibilityResult());

        // Run the test
        myClassUnderTest.tryChangeMessageVisibility1();

        // Verify the results
    }

    @Test
    void testTryChangeMessageVisibilityBatch() {
        // Setup
        // Configure AmazonSQSClient.changeMessageVisibilityBatch(...).
        final ChangeMessageVisibilityBatchResult changeMessageVisibilityBatchResult = new ChangeMessageVisibilityBatchResult();
        when(mockAmazonSQSClient.changeMessageVisibilityBatch(new ChangeMessageVisibilityBatchRequest("queueUrl",
                Arrays.asList(new ChangeMessageVisibilityBatchRequestEntry("id", "receiptHandle")))))
                .thenReturn(changeMessageVisibilityBatchResult);

        // Run the test
        myClassUnderTest.tryChangeMessageVisibilityBatch();

        // Verify the results
    }

    @Test
    void testTryChangeMessageVisibilityBatch_AmazonSQSClientThrowsTooManyEntriesInBatchRequestException() {
        // Setup
        when(mockAmazonSQSClient.changeMessageVisibilityBatch(new ChangeMessageVisibilityBatchRequest("queueUrl",
                Arrays.asList(new ChangeMessageVisibilityBatchRequestEntry("id", "receiptHandle")))))
                .thenThrow(TooManyEntriesInBatchRequestException.class);

        // Run the test
        assertThrows(TooManyEntriesInBatchRequestException.class,
                () -> myClassUnderTest.tryChangeMessageVisibilityBatch());
    }

    @Test
    void testTryChangeMessageVisibilityBatch_AmazonSQSClientThrowsEmptyBatchRequestException() {
        // Setup
        when(mockAmazonSQSClient.changeMessageVisibilityBatch(new ChangeMessageVisibilityBatchRequest("queueUrl",
                Arrays.asList(new ChangeMessageVisibilityBatchRequestEntry("id", "receiptHandle")))))
                .thenThrow(EmptyBatchRequestException.class);

        // Run the test
        assertThrows(EmptyBatchRequestException.class, () -> myClassUnderTest.tryChangeMessageVisibilityBatch());
    }

    @Test
    void testTryChangeMessageVisibilityBatch_AmazonSQSClientThrowsBatchEntryIdsNotDistinctException() {
        // Setup
        when(mockAmazonSQSClient.changeMessageVisibilityBatch(new ChangeMessageVisibilityBatchRequest("queueUrl",
                Arrays.asList(new ChangeMessageVisibilityBatchRequestEntry("id", "receiptHandle")))))
                .thenThrow(BatchEntryIdsNotDistinctException.class);

        // Run the test
        assertThrows(BatchEntryIdsNotDistinctException.class, () -> myClassUnderTest.tryChangeMessageVisibilityBatch());
    }

    @Test
    void testTryChangeMessageVisibilityBatch_AmazonSQSClientThrowsInvalidBatchEntryIdException() {
        // Setup
        when(mockAmazonSQSClient.changeMessageVisibilityBatch(new ChangeMessageVisibilityBatchRequest("queueUrl",
                Arrays.asList(new ChangeMessageVisibilityBatchRequestEntry("id", "receiptHandle")))))
                .thenThrow(InvalidBatchEntryIdException.class);

        // Run the test
        assertThrows(InvalidBatchEntryIdException.class, () -> myClassUnderTest.tryChangeMessageVisibilityBatch());
    }

    @Test
    void testTryChangeMessageVisibilityBatch1() {
        // Setup
        // Configure AmazonSQSClient.changeMessageVisibilityBatch(...).
        final ChangeMessageVisibilityBatchResult changeMessageVisibilityBatchResult = new ChangeMessageVisibilityBatchResult();
        when(mockAmazonSQSClient.changeMessageVisibilityBatch("queueUrl",
                Arrays.asList(new ChangeMessageVisibilityBatchRequestEntry("id", "receiptHandle"))))
                .thenReturn(changeMessageVisibilityBatchResult);

        // Run the test
        myClassUnderTest.tryChangeMessageVisibilityBatch1();

        // Verify the results
    }

    @Test
    void testTryCreateQueue() {
        // Setup
        // Configure AmazonSQSClient.createQueue(...).
        final CreateQueueResult createQueueResult = new CreateQueueResult();
        when(mockAmazonSQSClient.createQueue(new CreateQueueRequest("queueName"))).thenReturn(createQueueResult);

        // Run the test
        myClassUnderTest.tryCreateQueue();

        // Verify the results
    }

    @Test
    void testTryCreateQueue_AmazonSQSClientThrowsQueueDeletedRecentlyException() {
        // Setup
        when(mockAmazonSQSClient.createQueue(new CreateQueueRequest("queueName")))
                .thenThrow(QueueDeletedRecentlyException.class);

        // Run the test
        assertThrows(QueueDeletedRecentlyException.class, () -> myClassUnderTest.tryCreateQueue());
    }

    @Test
    void testTryCreateQueue_AmazonSQSClientThrowsQueueNameExistsException() {
        // Setup
        when(mockAmazonSQSClient.createQueue(new CreateQueueRequest("queueName")))
                .thenThrow(QueueNameExistsException.class);

        // Run the test
        assertThrows(QueueNameExistsException.class, () -> myClassUnderTest.tryCreateQueue());
    }

    @Test
    void testTryCreateQueue1() {
        // Setup
        // Configure AmazonSQSClient.createQueue(...).
        final CreateQueueResult createQueueResult = new CreateQueueResult();
        when(mockAmazonSQSClient.createQueue("queueName")).thenReturn(createQueueResult);

        // Run the test
        myClassUnderTest.tryCreateQueue1();

        // Verify the results
    }

    @Test
    void testTryDeleteMessage() {
        // Setup
        when(mockAmazonSQSClient.deleteMessage(new DeleteMessageRequest("queueUrl", "receiptHandle")))
                .thenReturn(new DeleteMessageResult());

        // Run the test
        myClassUnderTest.tryDeleteMessage();

        // Verify the results
    }

    @Test
    void testTryDeleteMessage_AmazonSQSClientThrowsInvalidIdFormatException() {
        // Setup
        when(mockAmazonSQSClient.deleteMessage(new DeleteMessageRequest("queueUrl", "receiptHandle")))
                .thenThrow(InvalidIdFormatException.class);

        // Run the test
        assertThrows(InvalidIdFormatException.class, () -> myClassUnderTest.tryDeleteMessage());
    }

    @Test
    void testTryDeleteMessage_AmazonSQSClientThrowsReceiptHandleIsInvalidException() {
        // Setup
        when(mockAmazonSQSClient.deleteMessage(new DeleteMessageRequest("queueUrl", "receiptHandle")))
                .thenThrow(ReceiptHandleIsInvalidException.class);

        // Run the test
        assertThrows(ReceiptHandleIsInvalidException.class, () -> myClassUnderTest.tryDeleteMessage());
    }

    @Test
    void testTryDeleteMessage1() {
        // Setup
        when(mockAmazonSQSClient.deleteMessage("queueUrl", "receiptHandle")).thenReturn(new DeleteMessageResult());

        // Run the test
        myClassUnderTest.tryDeleteMessage1();

        // Verify the results
    }

    @Test
    void testTryDeleteMessageBatch() {
        // Setup
        // Configure AmazonSQSClient.deleteMessageBatch(...).
        final DeleteMessageBatchResult deleteMessageBatchResult = new DeleteMessageBatchResult();
        when(mockAmazonSQSClient.deleteMessageBatch(new DeleteMessageBatchRequest("queueUrl",
                Arrays.asList(new DeleteMessageBatchRequestEntry("id", "receiptHandle")))))
                .thenReturn(deleteMessageBatchResult);

        // Run the test
        myClassUnderTest.tryDeleteMessageBatch();

        // Verify the results
    }

    @Test
    void testTryDeleteMessageBatch_AmazonSQSClientThrowsTooManyEntriesInBatchRequestException() {
        // Setup
        when(mockAmazonSQSClient.deleteMessageBatch(new DeleteMessageBatchRequest("queueUrl",
                Arrays.asList(new DeleteMessageBatchRequestEntry("id", "receiptHandle")))))
                .thenThrow(TooManyEntriesInBatchRequestException.class);

        // Run the test
        assertThrows(TooManyEntriesInBatchRequestException.class, () -> myClassUnderTest.tryDeleteMessageBatch());
    }

    @Test
    void testTryDeleteMessageBatch_AmazonSQSClientThrowsEmptyBatchRequestException() {
        // Setup
        when(mockAmazonSQSClient.deleteMessageBatch(new DeleteMessageBatchRequest("queueUrl",
                Arrays.asList(new DeleteMessageBatchRequestEntry("id", "receiptHandle")))))
                .thenThrow(EmptyBatchRequestException.class);

        // Run the test
        assertThrows(EmptyBatchRequestException.class, () -> myClassUnderTest.tryDeleteMessageBatch());
    }

    @Test
    void testTryDeleteMessageBatch_AmazonSQSClientThrowsBatchEntryIdsNotDistinctException() {
        // Setup
        when(mockAmazonSQSClient.deleteMessageBatch(new DeleteMessageBatchRequest("queueUrl",
                Arrays.asList(new DeleteMessageBatchRequestEntry("id", "receiptHandle")))))
                .thenThrow(BatchEntryIdsNotDistinctException.class);

        // Run the test
        assertThrows(BatchEntryIdsNotDistinctException.class, () -> myClassUnderTest.tryDeleteMessageBatch());
    }

    @Test
    void testTryDeleteMessageBatch_AmazonSQSClientThrowsInvalidBatchEntryIdException() {
        // Setup
        when(mockAmazonSQSClient.deleteMessageBatch(new DeleteMessageBatchRequest("queueUrl",
                Arrays.asList(new DeleteMessageBatchRequestEntry("id", "receiptHandle")))))
                .thenThrow(InvalidBatchEntryIdException.class);

        // Run the test
        assertThrows(InvalidBatchEntryIdException.class, () -> myClassUnderTest.tryDeleteMessageBatch());
    }

    @Test
    void testTryDeleteMessageBatch1() {
        // Setup
        // Configure AmazonSQSClient.deleteMessageBatch(...).
        final DeleteMessageBatchResult deleteMessageBatchResult = new DeleteMessageBatchResult();
        when(mockAmazonSQSClient.deleteMessageBatch("queueUrl",
                Arrays.asList(new DeleteMessageBatchRequestEntry("id", "receiptHandle"))))
                .thenReturn(deleteMessageBatchResult);

        // Run the test
        myClassUnderTest.tryDeleteMessageBatch1();

        // Verify the results
    }

    @Test
    void testTryDeleteQueue() {
        // Setup
        when(mockAmazonSQSClient.deleteQueue(new DeleteQueueRequest("queueUrl"))).thenReturn(new DeleteQueueResult());

        // Run the test
        myClassUnderTest.tryDeleteQueue();

        // Verify the results
    }

    @Test
    void testTryDeleteQueue1() {
        // Setup
        when(mockAmazonSQSClient.deleteQueue("queueUrl")).thenReturn(new DeleteQueueResult());

        // Run the test
        myClassUnderTest.tryDeleteQueue1();

        // Verify the results
    }

    @Test
    void testTryGetQueueAttributes() {
        // Setup
        // Configure AmazonSQSClient.getQueueAttributes(...).
        final GetQueueAttributesResult getQueueAttributesResult = new GetQueueAttributesResult();
        when(mockAmazonSQSClient.getQueueAttributes(
                new GetQueueAttributesRequest("queueUrl", Arrays.asList("value"))))
                .thenReturn(getQueueAttributesResult);

        // Run the test
        myClassUnderTest.tryGetQueueAttributes();

        // Verify the results
    }

    @Test
    void testTryGetQueueAttributes_AmazonSQSClientThrowsInvalidAttributeNameException() {
        // Setup
        when(mockAmazonSQSClient.getQueueAttributes(
                new GetQueueAttributesRequest("queueUrl", Arrays.asList("value"))))
                .thenThrow(InvalidAttributeNameException.class);

        // Run the test
        assertThrows(InvalidAttributeNameException.class, () -> myClassUnderTest.tryGetQueueAttributes());
    }

    @Test
    void testTryGetQueueAttributes1() {
        // Setup
        // Configure AmazonSQSClient.getQueueAttributes(...).
        final GetQueueAttributesResult getQueueAttributesResult = new GetQueueAttributesResult();
        when(mockAmazonSQSClient.getQueueAttributes("queueUrl", Arrays.asList("value")))
                .thenReturn(getQueueAttributesResult);

        // Run the test
        myClassUnderTest.tryGetQueueAttributes1();

        // Verify the results
    }

    @Test
    void testTryGetQueueUrl() {
        // Setup
        // Configure AmazonSQSClient.getQueueUrl(...).
        final GetQueueUrlResult getQueueUrlResult = new GetQueueUrlResult();
        when(mockAmazonSQSClient.getQueueUrl(new GetQueueUrlRequest("queueName"))).thenReturn(getQueueUrlResult);

        // Run the test
        myClassUnderTest.tryGetQueueUrl();

        // Verify the results
    }

    @Test
    void testTryGetQueueUrl_AmazonSQSClientThrowsQueueDoesNotExistException() {
        // Setup
        when(mockAmazonSQSClient.getQueueUrl(new GetQueueUrlRequest("queueName")))
                .thenThrow(QueueDoesNotExistException.class);

        // Run the test
        assertThrows(QueueDoesNotExistException.class, () -> myClassUnderTest.tryGetQueueUrl());
    }

    @Test
    void testTryGetQueueUrl1() {
        // Setup
        // Configure AmazonSQSClient.getQueueUrl(...).
        final GetQueueUrlResult getQueueUrlResult = new GetQueueUrlResult();
        when(mockAmazonSQSClient.getQueueUrl("queueName")).thenReturn(getQueueUrlResult);

        // Run the test
        myClassUnderTest.tryGetQueueUrl1();

        // Verify the results
    }

    @Test
    void testTryListDeadLetterSourceQueues() {
        // Setup
        // Configure AmazonSQSClient.listDeadLetterSourceQueues(...).
        final ListDeadLetterSourceQueuesResult listDeadLetterSourceQueuesResult = new ListDeadLetterSourceQueuesResult();
        when(mockAmazonSQSClient.listDeadLetterSourceQueues(
                new ListDeadLetterSourceQueuesRequest("queueUrl"))).thenReturn(listDeadLetterSourceQueuesResult);

        // Run the test
        myClassUnderTest.tryListDeadLetterSourceQueues();

        // Verify the results
    }

    @Test
    void testTryListDeadLetterSourceQueues_AmazonSQSClientThrowsQueueDoesNotExistException() {
        // Setup
        when(mockAmazonSQSClient.listDeadLetterSourceQueues(
                new ListDeadLetterSourceQueuesRequest("queueUrl"))).thenThrow(QueueDoesNotExistException.class);

        // Run the test
        assertThrows(QueueDoesNotExistException.class, () -> myClassUnderTest.tryListDeadLetterSourceQueues());
    }

    @Test
    void testTryListQueueTags() {
        // Setup
        // Configure AmazonSQSClient.listQueueTags(...).
        final ListQueueTagsResult listQueueTagsResult = new ListQueueTagsResult();
        when(mockAmazonSQSClient.listQueueTags(new ListQueueTagsRequest("queueUrl"))).thenReturn(listQueueTagsResult);

        // Run the test
        myClassUnderTest.tryListQueueTags();

        // Verify the results
    }

    @Test
    void testTryListQueueTags1() {
        // Setup
        // Configure AmazonSQSClient.listQueueTags(...).
        final ListQueueTagsResult listQueueTagsResult = new ListQueueTagsResult();
        when(mockAmazonSQSClient.listQueueTags("queueUrl")).thenReturn(listQueueTagsResult);

        // Run the test
        myClassUnderTest.tryListQueueTags1();

        // Verify the results
    }

    @Test
    void testTryListQueues() {
        // Setup
        // Configure AmazonSQSClient.listQueues(...).
        final ListQueuesResult listQueuesResult = new ListQueuesResult();
        when(mockAmazonSQSClient.listQueues(new ListQueuesRequest("queueNamePrefix"))).thenReturn(listQueuesResult);

        // Run the test
        myClassUnderTest.tryListQueues();

        // Verify the results
    }

    @Test
    void testTryListQueues1() {
        // Setup
        // Configure AmazonSQSClient.listQueues(...).
        final ListQueuesResult listQueuesResult = new ListQueuesResult();
        when(mockAmazonSQSClient.listQueues()).thenReturn(listQueuesResult);

        // Run the test
        myClassUnderTest.tryListQueues1();

        // Verify the results
    }

    @Test
    void testTryListQueues2() {
        // Setup
        // Configure AmazonSQSClient.listQueues(...).
        final ListQueuesResult listQueuesResult = new ListQueuesResult();
        when(mockAmazonSQSClient.listQueues("queueNamePrefix")).thenReturn(listQueuesResult);

        // Run the test
        myClassUnderTest.tryListQueues2();

        // Verify the results
    }

    @Test
    void testTryPurgeQueue() {
        // Setup
        when(mockAmazonSQSClient.purgeQueue(new PurgeQueueRequest("queueUrl"))).thenReturn(new PurgeQueueResult());

        // Run the test
        myClassUnderTest.tryPurgeQueue();

        // Verify the results
    }

    @Test
    void testTryPurgeQueue_AmazonSQSClientThrowsQueueDoesNotExistException() {
        // Setup
        when(mockAmazonSQSClient.purgeQueue(new PurgeQueueRequest("queueUrl")))
                .thenThrow(QueueDoesNotExistException.class);

        // Run the test
        assertThrows(QueueDoesNotExistException.class, () -> myClassUnderTest.tryPurgeQueue());
    }

    @Test
    void testTryPurgeQueue_AmazonSQSClientThrowsPurgeQueueInProgressException() {
        // Setup
        when(mockAmazonSQSClient.purgeQueue(new PurgeQueueRequest("queueUrl")))
                .thenThrow(PurgeQueueInProgressException.class);

        // Run the test
        assertThrows(PurgeQueueInProgressException.class, () -> myClassUnderTest.tryPurgeQueue());
    }

    @Test
    void testTryReceiveMessage() {
        // Setup
        // Configure AmazonSQSClient.receiveMessage(...).
        final ReceiveMessageResult receiveMessageResult = new ReceiveMessageResult();
        when(mockAmazonSQSClient.receiveMessage(new ReceiveMessageRequest("queueUrl")))
                .thenReturn(receiveMessageResult);

        // Run the test
        myClassUnderTest.tryReceiveMessage();

        // Verify the results
    }

    @Test
    void testTryReceiveMessage_AmazonSQSClientThrowsOverLimitException() {
        // Setup
        when(mockAmazonSQSClient.receiveMessage(new ReceiveMessageRequest("queueUrl")))
                .thenThrow(OverLimitException.class);

        // Run the test
        assertThrows(OverLimitException.class, () -> myClassUnderTest.tryReceiveMessage());
    }

    @Test
    void testTryReceiveMessage1() {
        // Setup
        // Configure AmazonSQSClient.receiveMessage(...).
        final ReceiveMessageResult receiveMessageResult = new ReceiveMessageResult();
        when(mockAmazonSQSClient.receiveMessage("queueUrl")).thenReturn(receiveMessageResult);

        // Run the test
        myClassUnderTest.tryReceiveMessage1();

        // Verify the results
    }

    @Test
    void testTryRemovePermission() {
        // Setup
        when(mockAmazonSQSClient.removePermission(new RemovePermissionRequest("queueUrl", "label")))
                .thenReturn(new RemovePermissionResult());

        // Run the test
        myClassUnderTest.tryRemovePermission();

        // Verify the results
    }

    @Test
    void testTryRemovePermission1() {
        // Setup
        when(mockAmazonSQSClient.removePermission("queueUrl", "label")).thenReturn(new RemovePermissionResult());

        // Run the test
        myClassUnderTest.tryRemovePermission1();

        // Verify the results
    }

    @Test
    void testTrySendMessage() {
        // Setup
        // Configure AmazonSQSClient.sendMessage(...).
        final SendMessageResult sendMessageResult = new SendMessageResult();
        when(mockAmazonSQSClient.sendMessage(new SendMessageRequest("queueUrl", "messageBody")))
                .thenReturn(sendMessageResult);

        // Run the test
        myClassUnderTest.trySendMessage();

        // Verify the results
    }

    @Test
    void testTrySendMessage_AmazonSQSClientThrowsInvalidMessageContentsException() {
        // Setup
        when(mockAmazonSQSClient.sendMessage(new SendMessageRequest("queueUrl", "messageBody")))
                .thenThrow(InvalidMessageContentsException.class);

        // Run the test
        assertThrows(InvalidMessageContentsException.class, () -> myClassUnderTest.trySendMessage());
    }

    @Test
    void testTrySendMessage1() {
        // Setup
        // Configure AmazonSQSClient.sendMessage(...).
        final SendMessageResult sendMessageResult = new SendMessageResult();
        when(mockAmazonSQSClient.sendMessage("queueUrl", "messageBody")).thenReturn(sendMessageResult);

        // Run the test
        myClassUnderTest.trySendMessage1();

        // Verify the results
    }

    @Test
    void testTrySendMessageBatch() {
        // Setup
        // Configure AmazonSQSClient.sendMessageBatch(...).
        final SendMessageBatchResult sendMessageBatchResult = new SendMessageBatchResult();
        when(mockAmazonSQSClient.sendMessageBatch(new SendMessageBatchRequest("queueUrl",
                Arrays.asList(new SendMessageBatchRequestEntry("id", "messageBody")))))
                .thenReturn(sendMessageBatchResult);

        // Run the test
        myClassUnderTest.trySendMessageBatch();

        // Verify the results
    }

    @Test
    void testTrySendMessageBatch_AmazonSQSClientThrowsTooManyEntriesInBatchRequestException() {
        // Setup
        when(mockAmazonSQSClient.sendMessageBatch(new SendMessageBatchRequest("queueUrl",
                Arrays.asList(new SendMessageBatchRequestEntry("id", "messageBody")))))
                .thenThrow(TooManyEntriesInBatchRequestException.class);

        // Run the test
        assertThrows(TooManyEntriesInBatchRequestException.class, () -> myClassUnderTest.trySendMessageBatch());
    }

    @Test
    void testTrySendMessageBatch_AmazonSQSClientThrowsEmptyBatchRequestException() {
        // Setup
        when(mockAmazonSQSClient.sendMessageBatch(new SendMessageBatchRequest("queueUrl",
                Arrays.asList(new SendMessageBatchRequestEntry("id", "messageBody")))))
                .thenThrow(EmptyBatchRequestException.class);

        // Run the test
        assertThrows(EmptyBatchRequestException.class, () -> myClassUnderTest.trySendMessageBatch());
    }

    @Test
    void testTrySendMessageBatch_AmazonSQSClientThrowsBatchEntryIdsNotDistinctException() {
        // Setup
        when(mockAmazonSQSClient.sendMessageBatch(new SendMessageBatchRequest("queueUrl",
                Arrays.asList(new SendMessageBatchRequestEntry("id", "messageBody")))))
                .thenThrow(BatchEntryIdsNotDistinctException.class);

        // Run the test
        assertThrows(BatchEntryIdsNotDistinctException.class, () -> myClassUnderTest.trySendMessageBatch());
    }

    @Test
    void testTrySendMessageBatch_AmazonSQSClientThrowsBatchRequestTooLongException() {
        // Setup
        when(mockAmazonSQSClient.sendMessageBatch(new SendMessageBatchRequest("queueUrl",
                Arrays.asList(new SendMessageBatchRequestEntry("id", "messageBody")))))
                .thenThrow(BatchRequestTooLongException.class);

        // Run the test
        assertThrows(BatchRequestTooLongException.class, () -> myClassUnderTest.trySendMessageBatch());
    }

    @Test
    void testTrySendMessageBatch_AmazonSQSClientThrowsInvalidBatchEntryIdException() {
        // Setup
        when(mockAmazonSQSClient.sendMessageBatch(new SendMessageBatchRequest("queueUrl",
                Arrays.asList(new SendMessageBatchRequestEntry("id", "messageBody")))))
                .thenThrow(InvalidBatchEntryIdException.class);

        // Run the test
        assertThrows(InvalidBatchEntryIdException.class, () -> myClassUnderTest.trySendMessageBatch());
    }

    @Test
    void testTrySendMessageBatch1() {
        // Setup
        // Configure AmazonSQSClient.sendMessageBatch(...).
        final SendMessageBatchResult sendMessageBatchResult = new SendMessageBatchResult();
        when(mockAmazonSQSClient.sendMessageBatch("queueUrl",
                Arrays.asList(new SendMessageBatchRequestEntry("id", "messageBody"))))
                .thenReturn(sendMessageBatchResult);

        // Run the test
        myClassUnderTest.trySendMessageBatch1();

        // Verify the results
    }

    @Test
    void testTrySetQueueAttributes() {
        // Setup
        when(mockAmazonSQSClient.setQueueAttributes(
                new SetQueueAttributesRequest("queueUrl", new HashMap<>()))).thenReturn(new SetQueueAttributesResult());

        // Run the test
        myClassUnderTest.trySetQueueAttributes();

        // Verify the results
    }

    @Test
    void testTrySetQueueAttributes_AmazonSQSClientThrowsInvalidAttributeNameException() {
        // Setup
        when(mockAmazonSQSClient.setQueueAttributes(
                new SetQueueAttributesRequest("queueUrl", new HashMap<>())))
                .thenThrow(InvalidAttributeNameException.class);

        // Run the test
        assertThrows(InvalidAttributeNameException.class, () -> myClassUnderTest.trySetQueueAttributes());
    }

    @Test
    void testTrySetQueueAttributes1() {
        // Setup
        when(mockAmazonSQSClient.setQueueAttributes("queueUrl", new HashMap<>()))
                .thenReturn(new SetQueueAttributesResult());

        // Run the test
        myClassUnderTest.trySetQueueAttributes1();

        // Verify the results
    }

    @Test
    void testTryTagQueue() {
        // Setup
        when(mockAmazonSQSClient.tagQueue(new TagQueueRequest("queueUrl", new HashMap<>())))
                .thenReturn(new TagQueueResult());

        // Run the test
        myClassUnderTest.tryTagQueue();

        // Verify the results
    }

    @Test
    void testTryTagQueue1() {
        // Setup
        when(mockAmazonSQSClient.tagQueue("queueUrl", new HashMap<>())).thenReturn(new TagQueueResult());

        // Run the test
        myClassUnderTest.tryTagQueue1();

        // Verify the results
    }

    @Test
    void testTryUntagQueue() {
        // Setup
        when(mockAmazonSQSClient.untagQueue(new UntagQueueRequest("queueUrl", Arrays.asList("value"))))
                .thenReturn(new UntagQueueResult());

        // Run the test
        myClassUnderTest.tryUntagQueue();

        // Verify the results
    }

    @Test
    void testTryUntagQueue1() {
        // Setup
        when(mockAmazonSQSClient.untagQueue("queueUrl", Arrays.asList("value"))).thenReturn(new UntagQueueResult());

        // Run the test
        myClassUnderTest.tryUntagQueue1();

        // Verify the results
    }

    @Test
    void testTryGetCachedResponseMetadata() {
        // Setup
        when(mockAmazonSQSClient.getCachedResponseMetadata(any(AmazonWebServiceRequest.class)))
                .thenReturn(new ResponseMetadata(new HashMap<>()));

        // Run the test
        myClassUnderTest.tryGetCachedResponseMetadata();

        // Verify the results
    }

    @Test
    void testTryGetCachedResponseMetadata_AmazonSQSClientReturnsNull() {
        // Setup
        when(mockAmazonSQSClient.getCachedResponseMetadata(any(AmazonWebServiceRequest.class))).thenReturn(null);

        // Run the test
        myClassUnderTest.tryGetCachedResponseMetadata();

        // Verify the results
    }

    @Test
    void testTryBuilder() {
        // Setup
        // Run the test
        myClassUnderTest.tryBuilder();

        // Verify the results
    }
}
