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

import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.*;
import software.amazon.awssdk.services.sqs.paginators.ListDeadLetterSourceQueuesIterable;
import software.amazon.awssdk.services.sqs.paginators.ListQueuesIterable;

import java.nio.ByteBuffer;
import java.util.Map;

public class MyClass {

    private SqsClient sqsClient;

    public MyClass(final SqsClient sqsClient) {
        this.sqsClient = sqsClient;
    }

    public void tryAddPermission1() {
        final AddPermissionRequest addPermissionRequest = AddPermissionRequest.builder()
                .queueUrl("queueUrl")
                .label("label")
                .awsAccountIds("awsAccountIds")
                .actions("actions")
                .build();
        final AddPermissionResponse varThatUsesProps = AddPermissionResponse.builder().build();
        final AddPermissionResponse result = sqsClient.addPermission(addPermissionRequest);
    }

    public void tryAddPermission2() {
        final AddPermissionResponse varThatUsesProps = AddPermissionResponse.builder().build();
        final AddPermissionResponse result = sqsClient.addPermission(Object::toString);
    }

    public void tryChangeMessageVisibility1() {
        final ChangeMessageVisibilityRequest changeMessageVisibilityRequest = ChangeMessageVisibilityRequest.builder()
                .queueUrl("queueUrl")
                .visibilityTimeout(10)
                .receiptHandle("receiptHandle")
                .build();
        final ChangeMessageVisibilityResponse varThatUsesProps = ChangeMessageVisibilityResponse.builder().build();
        final ChangeMessageVisibilityResponse result = sqsClient.changeMessageVisibility(
                changeMessageVisibilityRequest);
    }

    public void tryChangeMessageVisibility2() {
        final ChangeMessageVisibilityResponse varThatUsesProps = ChangeMessageVisibilityResponse.builder().build();
        final ChangeMessageVisibilityResponse result = sqsClient.changeMessageVisibility(Object::toString);
    }

    public void tryChangeMessageVisibilityBatch1() {
        final ChangeMessageVisibilityBatchRequest changeMessageVisibilityBatchRequest = ChangeMessageVisibilityBatchRequest.builder()
                .queueUrl("queueUrl")
                .entries(ChangeMessageVisibilityBatchRequestEntry.builder()
                        .id("id")
                        .receiptHandle("receiptHandle")
                        .visibilityTimeout(0)
                        .build())
                .build();
        final ChangeMessageVisibilityBatchResponse varThatUsesProps = ChangeMessageVisibilityBatchResponse.builder()
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
        final ChangeMessageVisibilityBatchResponse result = sqsClient.changeMessageVisibilityBatch(
                changeMessageVisibilityBatchRequest);
    }

    public void tryChangeMessageVisibilityBatch2() {
        final ChangeMessageVisibilityBatchResponse varThatUsesProps = ChangeMessageVisibilityBatchResponse.builder()
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
        final ChangeMessageVisibilityBatchResponse result = sqsClient.changeMessageVisibilityBatch(Object::toString);
    }

    public void tryCreateQueue1() {
        final CreateQueueRequest createQueueRequest = CreateQueueRequest.builder()
                .queueName("queueName")
                .attributes(Map.ofEntries(Map.entry(QueueAttributeName.ALL, "value")))
                .tags(Map.ofEntries(Map.entry("value", "value")))
                .build();
        final CreateQueueResponse varThatUsesProps = CreateQueueResponse.builder()
                .queueUrl("queueUrl")
                .build();
        final CreateQueueResponse result = sqsClient.createQueue(createQueueRequest);
    }

    public void tryCreateQueue2() {
        final CreateQueueResponse varThatUsesProps = CreateQueueResponse.builder()
                .queueUrl("queueUrl")
                .build();
        final CreateQueueResponse result = sqsClient.createQueue(Object::toString);
    }

    public void tryDeleteMessage1() {
        final DeleteMessageRequest deleteMessageRequest = DeleteMessageRequest.builder()
                .queueUrl("queueUrl")
                .receiptHandle("receiptHandle")
                .build();
        final DeleteMessageResponse varThatUsesProps = DeleteMessageResponse.builder().build();
        final DeleteMessageResponse result = sqsClient.deleteMessage(deleteMessageRequest);
    }

    public void tryDeleteMessage2() {
        final DeleteMessageResponse varThatUsesProps = DeleteMessageResponse.builder().build();
        final DeleteMessageResponse result = sqsClient.deleteMessage(Object::toString);
    }

    public void tryDeleteMessageBatch1() {
        final DeleteMessageBatchRequest deleteMessageBatchRequest = DeleteMessageBatchRequest.builder()
                .queueUrl("queueUrl")
                .entries(DeleteMessageBatchRequestEntry.builder()
                        .id("id")
                        .receiptHandle("receiptHandle")
                        .build())
                .build();
        final DeleteMessageBatchResponse varThatUsesProps = DeleteMessageBatchResponse.builder()
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
        final DeleteMessageBatchResponse result = sqsClient.deleteMessageBatch(deleteMessageBatchRequest);
    }

    public void tryDeleteMessageBatch2() {
        final DeleteMessageBatchResponse varThatUsesProps = DeleteMessageBatchResponse.builder()
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
        final DeleteMessageBatchResponse result = sqsClient.deleteMessageBatch(Object::toString);
    }

    public void tryDeleteQueue1() {
        final DeleteQueueRequest deleteQueueRequest = DeleteQueueRequest.builder()
                .queueUrl("queueUrl")
                .build();
        final DeleteQueueResponse varThatUsesProps = DeleteQueueResponse.builder().build();
        final DeleteQueueResponse result = sqsClient.deleteQueue(deleteQueueRequest);
    }

    public void tryDeleteQueue2() {
        final DeleteQueueResponse varThatUsesProps = DeleteQueueResponse.builder().build();
        final DeleteQueueResponse result = sqsClient.deleteQueue(Object::toString);
    }

    public void tryGetQueueAttributes1() {
        final GetQueueAttributesRequest getQueueAttributesRequest = GetQueueAttributesRequest.builder()
                .queueUrl("queueUrl")
                .attributeNames(QueueAttributeName.ALL)
                .build();
        final GetQueueAttributesResponse varThatUsesProps = GetQueueAttributesResponse.builder()
                .attributes(Map.ofEntries(Map.entry(QueueAttributeName.ALL, "value")))
                .build();
        final GetQueueAttributesResponse result = sqsClient.getQueueAttributes(getQueueAttributesRequest);
    }

    public void tryGetQueueAttributes2() {
        final GetQueueAttributesResponse varThatUsesProps = GetQueueAttributesResponse.builder()
                .attributes(Map.ofEntries(Map.entry(QueueAttributeName.ALL, "value")))
                .build();
        final GetQueueAttributesResponse result = sqsClient.getQueueAttributes(Object::toString);
    }

    public void tryGetQueueUrl1() {
        final GetQueueUrlRequest getQueueUrlRequest = GetQueueUrlRequest.builder()
                .queueName("queueName")
                .queueOwnerAWSAccountId("queueOwnerAWSAccountId")
                .build();
        final GetQueueUrlResponse varThatUsesProps = GetQueueUrlResponse.builder()
                .queueUrl("queueUrl")
                .build();
        final GetQueueUrlResponse result = sqsClient.getQueueUrl(getQueueUrlRequest);
    }

    public void tryGetQueueUrl2() {
        final GetQueueUrlResponse varThatUsesProps = GetQueueUrlResponse.builder()
                .queueUrl("queueUrl")
                .build();
        final GetQueueUrlResponse result = sqsClient.getQueueUrl(Object::toString);
    }

    public void tryListDeadLetterSourceQueues1() {
        final ListDeadLetterSourceQueuesRequest listDeadLetterSourceQueuesRequest = ListDeadLetterSourceQueuesRequest.builder()
                .queueUrl("queueUrl")
                .nextToken("nextToken")
                .maxResults(0)
                .build();
        final ListDeadLetterSourceQueuesResponse varThatUsesProps = ListDeadLetterSourceQueuesResponse.builder()
                .queueUrls("queueUrls")
                .nextToken("nextToken")
                .build();
        final ListDeadLetterSourceQueuesResponse result = sqsClient.listDeadLetterSourceQueues(
                listDeadLetterSourceQueuesRequest);
    }

    public void tryListDeadLetterSourceQueues2() {
        final ListDeadLetterSourceQueuesResponse varThatUsesProps = ListDeadLetterSourceQueuesResponse.builder()
                .queueUrls("queueUrls")
                .nextToken("nextToken")
                .build();
        final ListDeadLetterSourceQueuesResponse result = sqsClient.listDeadLetterSourceQueues(Object::toString);
    }

    public void tryListDeadLetterSourceQueuesPaginator1() {
        final ListDeadLetterSourceQueuesRequest listDeadLetterSourceQueuesRequest = ListDeadLetterSourceQueuesRequest.builder()
                .queueUrl("queueUrl")
                .nextToken("nextToken")
                .maxResults(0)
                .build();
        final ListDeadLetterSourceQueuesIterable varThatUsesProps = null;
        final ListDeadLetterSourceQueuesIterable result = sqsClient.listDeadLetterSourceQueuesPaginator(
                listDeadLetterSourceQueuesRequest);
    }

    public void tryListDeadLetterSourceQueuesPaginator2() {
        final ListDeadLetterSourceQueuesIterable varThatUsesProps = null;
        final ListDeadLetterSourceQueuesIterable result = sqsClient.listDeadLetterSourceQueuesPaginator(
                Object::toString);
    }

    public void tryListQueueTags1() {
        final ListQueueTagsRequest listQueueTagsRequest = ListQueueTagsRequest.builder()
                .queueUrl("queueUrl")
                .build();
        final ListQueueTagsResponse varThatUsesProps = ListQueueTagsResponse.builder()
                .tags(Map.ofEntries(Map.entry("value", "value")))
                .build();
        final ListQueueTagsResponse result = sqsClient.listQueueTags(listQueueTagsRequest);
    }

    public void tryListQueueTags2() {
        final ListQueueTagsResponse varThatUsesProps = ListQueueTagsResponse.builder()
                .tags(Map.ofEntries(Map.entry("value", "value")))
                .build();
        final ListQueueTagsResponse result = sqsClient.listQueueTags(Object::toString);
    }

    public void tryListQueues1() {
        final ListQueuesResponse varThatUsesProps = ListQueuesResponse.builder()
                .queueUrls("queueUrls")
                .nextToken("nextToken")
                .build();
        final ListQueuesResponse result = sqsClient.listQueues();
    }

    public void tryListQueues2() {
        final ListQueuesRequest listQueuesRequest = ListQueuesRequest.builder()
                .queueNamePrefix("queueNamePrefix")
                .nextToken("nextToken")
                .maxResults(0)
                .build();
        final ListQueuesResponse varThatUsesProps = ListQueuesResponse.builder()
                .queueUrls("queueUrls")
                .nextToken("nextToken")
                .build();
        final ListQueuesResponse result = sqsClient.listQueues(listQueuesRequest);
    }

    public void tryListQueues3() {
        final ListQueuesResponse varThatUsesProps = ListQueuesResponse.builder()
                .queueUrls("queueUrls")
                .nextToken("nextToken")
                .build();
        final ListQueuesResponse result = sqsClient.listQueues(Object::toString);
    }

    public void tryListQueuesPaginator1() {
        final ListQueuesIterable varThatUsesProps = null;
        final ListQueuesIterable result = sqsClient.listQueuesPaginator();
    }

    public void tryListQueuesPaginator2() {
        final ListQueuesRequest listQueuesRequest = ListQueuesRequest.builder()
                .queueNamePrefix("queueNamePrefix")
                .nextToken("nextToken")
                .maxResults(0)
                .build();
        final ListQueuesIterable varThatUsesProps = null;
        final ListQueuesIterable result = sqsClient.listQueuesPaginator(listQueuesRequest);
    }

    public void tryListQueuesPaginator3() {
        final ListQueuesIterable varThatUsesProps = null;
        final ListQueuesIterable result = sqsClient.listQueuesPaginator(Object::toString);
    }

    public void tryPurgeQueue1() {
        final PurgeQueueRequest purgeQueueRequest = PurgeQueueRequest.builder()
                .queueUrl("queueUrl")
                .build();
        final PurgeQueueResponse varThatUsesProps = PurgeQueueResponse.builder().build();
        final PurgeQueueResponse result = sqsClient.purgeQueue(purgeQueueRequest);
    }

    public void tryPurgeQueue2() {
        final PurgeQueueResponse varThatUsesProps = PurgeQueueResponse.builder().build();
        final PurgeQueueResponse result = sqsClient.purgeQueue(Object::toString);
    }

    public void tryReceiveMessage1() {
        final ReceiveMessageRequest receiveMessageRequest = ReceiveMessageRequest.builder()
                .queueUrl("queueUrl")
                .attributeNames(QueueAttributeName.ALL)
                .messageAttributeNames("messageAttributeNames")
                .maxNumberOfMessages(0)
                .visibilityTimeout(0)
                .waitTimeSeconds(0)
                .receiveRequestAttemptId("receiveRequestAttemptId")
                .build();
        final ReceiveMessageResponse varThatUsesProps = ReceiveMessageResponse.builder()
                .messages(Message.builder()
                        .messageId("messageId")
                        .receiptHandle("receiptHandle")
                        .md5OfBody("md5OfBody")
                        .body("body")
                        .attributes(Map.ofEntries(Map.entry(MessageSystemAttributeName.SENDER_ID, "value")))
                        .md5OfMessageAttributes("md5OfMessageAttributes")
                        .messageAttributes(Map.ofEntries(Map.entry("value", MessageAttributeValue.builder()
                                .stringValue("stringValue")
                                .binaryValue(SdkBytes.fromByteBuffer(ByteBuffer.wrap("content".getBytes())))
                                .stringListValues("stringListValues")
                                .binaryListValues(SdkBytes.fromByteBuffer(ByteBuffer.wrap("content".getBytes())))
                                .dataType("dataType")
                                .build())))
                        .build())
                .build();
        final ReceiveMessageResponse result = sqsClient.receiveMessage(receiveMessageRequest);
    }

    public void tryReceiveMessage2() {
        final ReceiveMessageResponse varThatUsesProps = ReceiveMessageResponse.builder()
                .messages(Message.builder()
                        .messageId("messageId")
                        .receiptHandle("receiptHandle")
                        .md5OfBody("md5OfBody")
                        .body("body")
                        .attributes(Map.ofEntries(Map.entry(MessageSystemAttributeName.SENDER_ID, "value")))
                        .md5OfMessageAttributes("md5OfMessageAttributes")
                        .messageAttributes(Map.ofEntries(Map.entry("value", MessageAttributeValue.builder()
                                .stringValue("stringValue")
                                .binaryValue(SdkBytes.fromByteBuffer(ByteBuffer.wrap("content".getBytes())))
                                .stringListValues("stringListValues")
                                .binaryListValues(SdkBytes.fromByteBuffer(ByteBuffer.wrap("content".getBytes())))
                                .dataType("dataType")
                                .build())))
                        .build())
                .build();
        final ReceiveMessageResponse result = sqsClient.receiveMessage(Object::toString);
    }

    public void tryRemovePermission1() {
        final RemovePermissionRequest removePermissionRequest = RemovePermissionRequest.builder()
                .queueUrl("queueUrl")
                .label("label")
                .build();
        final RemovePermissionResponse varThatUsesProps = RemovePermissionResponse.builder().build();
        final RemovePermissionResponse result = sqsClient.removePermission(removePermissionRequest);
    }

    public void tryRemovePermission2() {
        final RemovePermissionResponse varThatUsesProps = RemovePermissionResponse.builder().build();
        final RemovePermissionResponse result = sqsClient.removePermission(Object::toString);
    }

    public void trySendMessage1() {
        final SendMessageRequest sendMessageRequest = SendMessageRequest.builder()
                .queueUrl("queueUrl")
                .messageBody("messageBody")
                .messageGroupId("messageGroupId")
                .messageDeduplicationId("messageDeduplicationId")
                .build();
        final SendMessageResponse varThatUsesProps = SendMessageResponse.builder()
                .md5OfMessageBody("md5OfMessageBody")
                .md5OfMessageAttributes("md5OfMessageAttributes")
                .md5OfMessageSystemAttributes("md5OfMessageSystemAttributes")
                .messageId("messageId")
                .sequenceNumber("sequenceNumber")
                .build();
        final SendMessageResponse result = sqsClient.sendMessage(sendMessageRequest);
    }

    public void trySendMessage2() {
        final SendMessageResponse varThatUsesProps = SendMessageResponse.builder()
                .md5OfMessageBody("md5OfMessageBody")
                .md5OfMessageAttributes("md5OfMessageAttributes")
                .md5OfMessageSystemAttributes("md5OfMessageSystemAttributes")
                .messageId("messageId")
                .sequenceNumber("sequenceNumber")
                .build();
        final SendMessageResponse result = sqsClient.sendMessage(Object::toString);
    }

    public void trySendMessageBatch1() {
        final SendMessageBatchRequest sendMessageBatchRequest = SendMessageBatchRequest.builder()
                .queueUrl("queueUrl")
                .entries(SendMessageBatchRequestEntry.builder()
                        .id("id")
                        .messageBody("messageBody")
                        .build())
                .build();
        final SendMessageBatchResponse varThatUsesProps = SendMessageBatchResponse.builder()
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
        final SendMessageBatchResponse result = sqsClient.sendMessageBatch(sendMessageBatchRequest);
    }

    public void trySendMessageBatch2() {
        final SendMessageBatchResponse varThatUsesProps = SendMessageBatchResponse.builder()
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
        final SendMessageBatchResponse result = sqsClient.sendMessageBatch(Object::toString);
    }

    public void trySetQueueAttributes1() {
        final SetQueueAttributesRequest setQueueAttributesRequest = SetQueueAttributesRequest.builder()
                .queueUrl("queueUrl")
                .attributes(Map.ofEntries(Map.entry(QueueAttributeName.ALL, "value")))
                .build();
        final SetQueueAttributesResponse varThatUsesProps = SetQueueAttributesResponse.builder().build();
        final SetQueueAttributesResponse result = sqsClient.setQueueAttributes(setQueueAttributesRequest);
    }

    public void trySetQueueAttributes2() {
        final SetQueueAttributesResponse varThatUsesProps = SetQueueAttributesResponse.builder().build();
        final SetQueueAttributesResponse result = sqsClient.setQueueAttributes(Object::toString);
    }

    public void tryTagQueue1() {
        final TagQueueRequest tagQueueRequest = TagQueueRequest.builder()
                .queueUrl("queueUrl")
                .tags(Map.ofEntries(Map.entry("value", "value")))
                .build();
        final TagQueueResponse varThatUsesProps = TagQueueResponse.builder().build();
        final TagQueueResponse result = sqsClient.tagQueue(tagQueueRequest);
    }

    public void tryTagQueue2() {
        final TagQueueResponse varThatUsesProps = TagQueueResponse.builder().build();
        final TagQueueResponse result = sqsClient.tagQueue(Object::toString);
    }

    public void tryUntagQueue1() {
        final UntagQueueRequest untagQueueRequest = UntagQueueRequest.builder()
                .queueUrl("queueUrl")
                .tagKeys("tagKeys")
                .build();
        final UntagQueueResponse varThatUsesProps = UntagQueueResponse.builder().build();
        final UntagQueueResponse result = sqsClient.untagQueue(untagQueueRequest);
    }

    public void tryUntagQueue2() {
        final UntagQueueResponse varThatUsesProps = UntagQueueResponse.builder().build();
        final UntagQueueResponse result = sqsClient.untagQueue(Object::toString);
    }
}
