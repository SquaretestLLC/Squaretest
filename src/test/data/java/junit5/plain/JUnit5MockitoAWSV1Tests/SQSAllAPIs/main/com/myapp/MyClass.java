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

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.ResponseMetadata;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyClass {

    private AmazonSQSClient amazonSQSClient;

    public MyClass(AmazonSQSClient amazonSQSClient) {
        this.amazonSQSClient = amazonSQSClient;
    }

    public void tryAddPermission() {
        final AddPermissionRequest request = new AddPermissionRequest("queueUrl", "label", Arrays.asList(), Arrays.asList());
        final AddPermissionResult result = amazonSQSClient.addPermission(request);
    }

    public void tryAddPermission1() {
        final List<String> aWSAccountIds = Arrays.asList();
        final List<String> actions = Arrays.asList();
        final AddPermissionResult result = amazonSQSClient.addPermission("queueUrl", "label", aWSAccountIds, actions);
    }

    public void tryChangeMessageVisibility() {
        final ChangeMessageVisibilityRequest request = new ChangeMessageVisibilityRequest("queueUrl", "receiptHandle", 0);
        final ChangeMessageVisibilityResult result = amazonSQSClient.changeMessageVisibility(request);
    }

    public void tryChangeMessageVisibility1() {
        final ChangeMessageVisibilityResult result = amazonSQSClient.changeMessageVisibility("queueUrl", "receiptHandle", 0);
    }

    public void tryChangeMessageVisibilityBatch() {
        final ChangeMessageVisibilityBatchRequest request = new ChangeMessageVisibilityBatchRequest("queueUrl", Arrays.asList());
        final ChangeMessageVisibilityBatchResult result = amazonSQSClient.changeMessageVisibilityBatch(request);
    }

    public void tryChangeMessageVisibilityBatch1() {
        final List<ChangeMessageVisibilityBatchRequestEntry> entries = Arrays.asList();
        final ChangeMessageVisibilityBatchResult result = amazonSQSClient.changeMessageVisibilityBatch("queueUrl", entries);
    }

    public void tryCreateQueue() {
        final CreateQueueRequest request = new CreateQueueRequest("queueName");
        final CreateQueueResult result = amazonSQSClient.createQueue(request);
    }

    public void tryCreateQueue1() {
        final CreateQueueResult result = amazonSQSClient.createQueue("queueName");
    }

    public void tryDeleteMessage() {
        final DeleteMessageRequest request = new DeleteMessageRequest("queueUrl", "receiptHandle");
        final DeleteMessageResult result = amazonSQSClient.deleteMessage(request);
    }

    public void tryDeleteMessage1() {
        final DeleteMessageResult result = amazonSQSClient.deleteMessage("queueUrl", "receiptHandle");
    }

    public void tryDeleteMessageBatch() {
        final DeleteMessageBatchRequest request = new DeleteMessageBatchRequest("queueUrl", Arrays.asList());
        final DeleteMessageBatchResult result = amazonSQSClient.deleteMessageBatch(request);
    }

    public void tryDeleteMessageBatch1() {
        final List<DeleteMessageBatchRequestEntry> entries = Arrays.asList();
        final DeleteMessageBatchResult result = amazonSQSClient.deleteMessageBatch("queueUrl", entries);
    }

    public void tryDeleteQueue() {
        final DeleteQueueRequest request = new DeleteQueueRequest("queueUrl");
        final DeleteQueueResult result = amazonSQSClient.deleteQueue(request);
    }

    public void tryDeleteQueue1() {
        final DeleteQueueResult result = amazonSQSClient.deleteQueue("queueUrl");
    }

    public void tryGetQueueAttributes() {
        final GetQueueAttributesRequest request = new GetQueueAttributesRequest("queueUrl", Arrays.asList());
        final GetQueueAttributesResult result = amazonSQSClient.getQueueAttributes(request);
    }

    public void tryGetQueueAttributes1() {
        final List<String> attributeNames = Arrays.asList();
        final GetQueueAttributesResult result = amazonSQSClient.getQueueAttributes("queueUrl", attributeNames);
    }

    public void tryGetQueueUrl() {
        final GetQueueUrlRequest request = new GetQueueUrlRequest("queueName");
        final GetQueueUrlResult result = amazonSQSClient.getQueueUrl(request);
    }

    public void tryGetQueueUrl1() {
        final GetQueueUrlResult result = amazonSQSClient.getQueueUrl("queueName");
    }

    public void tryListDeadLetterSourceQueues() {
        final ListDeadLetterSourceQueuesRequest request = new ListDeadLetterSourceQueuesRequest("queueUrl");
        final ListDeadLetterSourceQueuesResult result = amazonSQSClient.listDeadLetterSourceQueues(request);
    }

    public void tryListQueueTags() {
        final ListQueueTagsRequest request = new ListQueueTagsRequest("queueUrl");
        final ListQueueTagsResult result = amazonSQSClient.listQueueTags(request);
    }

    public void tryListQueueTags1() {
        final ListQueueTagsResult result = amazonSQSClient.listQueueTags("queueUrl");
    }

    public void tryListQueues() {
        final ListQueuesRequest request = new ListQueuesRequest("queueNamePrefix");
        final ListQueuesResult result = amazonSQSClient.listQueues(request);
    }

    public void tryListQueues1() {
        final ListQueuesResult result = amazonSQSClient.listQueues();
    }

    public void tryListQueues2() {
        final ListQueuesResult result = amazonSQSClient.listQueues("queueNamePrefix");
    }

    public void tryPurgeQueue() {
        final PurgeQueueRequest request = new PurgeQueueRequest("queueUrl");
        final PurgeQueueResult result = amazonSQSClient.purgeQueue(request);
    }

    public void tryReceiveMessage() {
        final ReceiveMessageRequest request = new ReceiveMessageRequest("queueUrl");
        final ReceiveMessageResult result = amazonSQSClient.receiveMessage(request);
    }

    public void tryReceiveMessage1() {
        final ReceiveMessageResult result = amazonSQSClient.receiveMessage("queueUrl");
    }

    public void tryRemovePermission() {
        final RemovePermissionRequest request = new RemovePermissionRequest("queueUrl", "label");
        final RemovePermissionResult result = amazonSQSClient.removePermission(request);
    }

    public void tryRemovePermission1() {
        final RemovePermissionResult result = amazonSQSClient.removePermission("queueUrl", "label");
    }

    public void trySendMessage() {
        final SendMessageRequest request = new SendMessageRequest("queueUrl", "messageBody");
        final SendMessageResult result = amazonSQSClient.sendMessage(request);
    }

    public void trySendMessage1() {
        final SendMessageResult result = amazonSQSClient.sendMessage("queueUrl", "messageBody");
    }

    public void trySendMessageBatch() {
        final SendMessageBatchRequest request = new SendMessageBatchRequest("queueUrl", Arrays.asList());
        final SendMessageBatchResult result = amazonSQSClient.sendMessageBatch(request);
    }

    public void trySendMessageBatch1() {
        final List<SendMessageBatchRequestEntry> entries = Arrays.asList();
        final SendMessageBatchResult result = amazonSQSClient.sendMessageBatch("queueUrl", entries);
    }

    public void trySetQueueAttributes() {
        final SetQueueAttributesRequest request = new SetQueueAttributesRequest("queueUrl", new HashMap<>());
        final SetQueueAttributesResult result = amazonSQSClient.setQueueAttributes(request);
    }

    public void trySetQueueAttributes1() {
        final Map<String, String> attributes = new HashMap<>();
        final SetQueueAttributesResult result = amazonSQSClient.setQueueAttributes("queueUrl", attributes);
    }

    public void tryTagQueue() {
        final TagQueueRequest request = new TagQueueRequest("queueUrl", new HashMap<>());
        final TagQueueResult result = amazonSQSClient.tagQueue(request);
    }

    public void tryTagQueue1() {
        final Map<String, String> tags = new HashMap<>();
        final TagQueueResult result = amazonSQSClient.tagQueue("queueUrl", tags);
    }

    public void tryUntagQueue() {
        final UntagQueueRequest request = new UntagQueueRequest("queueUrl", Arrays.asList());
        final UntagQueueResult result = amazonSQSClient.untagQueue(request);
    }

    public void tryUntagQueue1() {
        final List<String> tagKeys = Arrays.asList();
        final UntagQueueResult result = amazonSQSClient.untagQueue("queueUrl", tagKeys);
    }

    public void tryGetCachedResponseMetadata() {
        final AmazonWebServiceRequest request = null;
        final ResponseMetadata result = amazonSQSClient.getCachedResponseMetadata(request);
    }

    public void tryBuilder() {
        final AmazonSQSClientBuilder result = AmazonSQSClient.builder();
    }
}
