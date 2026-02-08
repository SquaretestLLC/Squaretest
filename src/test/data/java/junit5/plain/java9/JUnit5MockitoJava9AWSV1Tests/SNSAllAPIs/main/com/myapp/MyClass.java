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
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.*;

import java.util.Arrays;
import java.util.List;

public class MyClass {

    private AmazonSNSClient amazonSNSClient;

    public MyClass(AmazonSNSClient amazonSNSClient) {
        this.amazonSNSClient = amazonSNSClient;
    }

    public void tryAddPermission() {
        final AddPermissionRequest request = new AddPermissionRequest("topicArn", "label", List.of(), List.of());
        final AddPermissionResult result = amazonSNSClient.addPermission(request);
    }

    public void tryAddPermission1() {
        final List<String> aWSAccountIds = List.of();
        final List<String> actionNames = List.of();
        final AddPermissionResult result = amazonSNSClient.addPermission("topicArn", "label", aWSAccountIds, actionNames);
    }

    public void tryCheckIfPhoneNumberIsOptedOut() {
        final CheckIfPhoneNumberIsOptedOutRequest request = new CheckIfPhoneNumberIsOptedOutRequest();
        request.setPhoneNumber("phoneNumber");

        final CheckIfPhoneNumberIsOptedOutResult result = amazonSNSClient.checkIfPhoneNumberIsOptedOut(request);
    }

    public void tryConfirmSubscription() {
        final ConfirmSubscriptionRequest request = new ConfirmSubscriptionRequest("topicArn", "token", "authenticateOnUnsubscribe");
        final ConfirmSubscriptionResult result = amazonSNSClient.confirmSubscription(request);
    }

    public void tryConfirmSubscription1() {
        final ConfirmSubscriptionResult result = amazonSNSClient.confirmSubscription("topicArn", "token", "authenticateOnUnsubscribe");
    }

    public void tryConfirmSubscription2() {
        final ConfirmSubscriptionResult result = amazonSNSClient.confirmSubscription("topicArn", "token");
    }

    public void tryCreatePlatformApplication() {
        final CreatePlatformApplicationRequest request = new CreatePlatformApplicationRequest();
        final CreatePlatformApplicationResult result = amazonSNSClient.createPlatformApplication(request);
    }

    public void tryCreatePlatformEndpoint() {
        final CreatePlatformEndpointRequest request = new CreatePlatformEndpointRequest();
        final CreatePlatformEndpointResult result = amazonSNSClient.createPlatformEndpoint(request);
    }

    public void tryCreateTopic() {
        final CreateTopicRequest request = new CreateTopicRequest("name");
        final CreateTopicResult result = amazonSNSClient.createTopic(request);
    }

    public void tryCreateTopic1() {
        final CreateTopicResult result = amazonSNSClient.createTopic("name");
    }

    public void tryDeleteEndpoint() {
        final DeleteEndpointRequest request = new DeleteEndpointRequest();
        request.setEndpointArn("endpointArn");

        final DeleteEndpointResult result = amazonSNSClient.deleteEndpoint(request);
    }

    public void tryDeletePlatformApplication() {
        final DeletePlatformApplicationRequest request = new DeletePlatformApplicationRequest();
        request.setPlatformApplicationArn("platformApplicationArn");

        final DeletePlatformApplicationResult result = amazonSNSClient.deletePlatformApplication(request);
    }

    public void tryDeleteTopic() {
        final DeleteTopicRequest request = new DeleteTopicRequest("topicArn");
        final DeleteTopicResult result = amazonSNSClient.deleteTopic(request);
    }

    public void tryDeleteTopic1() {
        final DeleteTopicResult result = amazonSNSClient.deleteTopic("topicArn");
    }

    public void tryGetEndpointAttributes() {
        final GetEndpointAttributesRequest request = new GetEndpointAttributesRequest();
        request.setEndpointArn("endpointArn");

        final GetEndpointAttributesResult result = amazonSNSClient.getEndpointAttributes(request);
    }

    public void tryGetPlatformApplicationAttributes() {
        final GetPlatformApplicationAttributesRequest request = new GetPlatformApplicationAttributesRequest();
        request.setPlatformApplicationArn("platformApplicationArn");

        final GetPlatformApplicationAttributesResult result = amazonSNSClient.getPlatformApplicationAttributes(request);
    }

    public void tryGetSMSAttributes() {
        final GetSMSAttributesRequest request = new GetSMSAttributesRequest();
        request.setAttributes(List.of());

        final GetSMSAttributesResult result = amazonSNSClient.getSMSAttributes(request);
    }

    public void tryGetSubscriptionAttributes() {
        final GetSubscriptionAttributesRequest request = new GetSubscriptionAttributesRequest("subscriptionArn");
        final GetSubscriptionAttributesResult result = amazonSNSClient.getSubscriptionAttributes(request);
    }

    public void tryGetSubscriptionAttributes1() {
        final GetSubscriptionAttributesResult result = amazonSNSClient.getSubscriptionAttributes("subscriptionArn");
    }

    public void tryGetTopicAttributes() {
        final GetTopicAttributesRequest request = new GetTopicAttributesRequest("topicArn");
        final GetTopicAttributesResult result = amazonSNSClient.getTopicAttributes(request);
    }

    public void tryGetTopicAttributes1() {
        final GetTopicAttributesResult result = amazonSNSClient.getTopicAttributes("topicArn");
    }

    public void tryListEndpointsByPlatformApplication() {
        final ListEndpointsByPlatformApplicationRequest request = new ListEndpointsByPlatformApplicationRequest();
        request.setPlatformApplicationArn("platformApplicationArn");
        request.setNextToken("nextToken");

        final ListEndpointsByPlatformApplicationResult result = amazonSNSClient.listEndpointsByPlatformApplication(request);
    }

    public void tryListPhoneNumbersOptedOut() {
        final ListPhoneNumbersOptedOutRequest request = new ListPhoneNumbersOptedOutRequest();
        request.setNextToken("nextToken");

        final ListPhoneNumbersOptedOutResult result = amazonSNSClient.listPhoneNumbersOptedOut(request);
    }

    public void tryListPlatformApplications() {
        final ListPlatformApplicationsRequest request = new ListPlatformApplicationsRequest();
        request.setNextToken("nextToken");

        final ListPlatformApplicationsResult result = amazonSNSClient.listPlatformApplications(request);
    }

    public void tryListPlatformApplications1() {
        final ListPlatformApplicationsResult result = amazonSNSClient.listPlatformApplications();
    }

    public void tryListSubscriptions() {
        final ListSubscriptionsRequest request = new ListSubscriptionsRequest("nextToken");
        final ListSubscriptionsResult result = amazonSNSClient.listSubscriptions(request);
    }

    public void tryListSubscriptions1() {
        final ListSubscriptionsResult result = amazonSNSClient.listSubscriptions();
    }

    public void tryListSubscriptions2() {
        final ListSubscriptionsResult result = amazonSNSClient.listSubscriptions("nextToken");
    }

    public void tryListSubscriptionsByTopic() {
        final ListSubscriptionsByTopicRequest request = new ListSubscriptionsByTopicRequest("topicArn", "nextToken");
        final ListSubscriptionsByTopicResult result = amazonSNSClient.listSubscriptionsByTopic(request);
    }

    public void tryListSubscriptionsByTopic1() {
        final ListSubscriptionsByTopicResult result = amazonSNSClient.listSubscriptionsByTopic("topicArn");
    }

    public void tryListSubscriptionsByTopic2() {
        final ListSubscriptionsByTopicResult result = amazonSNSClient.listSubscriptionsByTopic("topicArn", "nextToken");
    }

    public void tryListTagsForResource() {
        final ListTagsForResourceRequest request = new ListTagsForResourceRequest();
        request.setResourceArn("resourceArn");

        final ListTagsForResourceResult result = amazonSNSClient.listTagsForResource(request);
    }

    public void tryListTopics() {
        final ListTopicsRequest request = new ListTopicsRequest("nextToken");
        final ListTopicsResult result = amazonSNSClient.listTopics(request);
    }

    public void tryListTopics1() {
        final ListTopicsResult result = amazonSNSClient.listTopics();
    }

    public void tryListTopics2() {
        final ListTopicsResult result = amazonSNSClient.listTopics("nextToken");
    }

    public void tryOptInPhoneNumber() {
        final OptInPhoneNumberRequest request = new OptInPhoneNumberRequest();
        request.setPhoneNumber("phoneNumber");

        final OptInPhoneNumberResult result = amazonSNSClient.optInPhoneNumber(request);
    }

    public void tryPublish() {
        final PublishRequest request = new PublishRequest("topicArn", "message", "subject");
        final PublishResult result = amazonSNSClient.publish(request);
    }

    public void tryPublish1() {
        final PublishResult result = amazonSNSClient.publish("topicArn", "message");
    }

    public void tryPublish2() {
        final PublishResult result = amazonSNSClient.publish("topicArn", "message", "subject");
    }

    public void tryRemovePermission() {
        final RemovePermissionRequest request = new RemovePermissionRequest("topicArn", "label");
        final RemovePermissionResult result = amazonSNSClient.removePermission(request);
    }

    public void tryRemovePermission1() {
        final RemovePermissionResult result = amazonSNSClient.removePermission("topicArn", "label");
    }

    public void trySetEndpointAttributes() {
        final SetEndpointAttributesRequest request = new SetEndpointAttributesRequest();
        final SetEndpointAttributesResult result = amazonSNSClient.setEndpointAttributes(request);
    }

    public void trySetPlatformApplicationAttributes() {
        final SetPlatformApplicationAttributesRequest request = new SetPlatformApplicationAttributesRequest();
        final SetPlatformApplicationAttributesResult result = amazonSNSClient.setPlatformApplicationAttributes(request);
    }

    public void trySetSMSAttributes() {
        final SetSMSAttributesRequest request = new SetSMSAttributesRequest();
        final SetSMSAttributesResult result = amazonSNSClient.setSMSAttributes(request);
    }

    public void trySetSubscriptionAttributes() {
        final SetSubscriptionAttributesRequest request = new SetSubscriptionAttributesRequest("subscriptionArn", "attributeName", "attributeValue");
        final SetSubscriptionAttributesResult result = amazonSNSClient.setSubscriptionAttributes(request);
    }

    public void trySetSubscriptionAttributes1() {
        final SetSubscriptionAttributesResult result = amazonSNSClient.setSubscriptionAttributes("subscriptionArn", "attributeName", "attributeValue");
    }

    public void trySetTopicAttributes() {
        final SetTopicAttributesRequest request = new SetTopicAttributesRequest("topicArn", "attributeName", "attributeValue");
        final SetTopicAttributesResult result = amazonSNSClient.setTopicAttributes(request);
    }

    public void trySetTopicAttributes1() {
        final SetTopicAttributesResult result = amazonSNSClient.setTopicAttributes("topicArn", "attributeName", "attributeValue");
    }

    public void trySubscribe() {
        final SubscribeRequest request = new SubscribeRequest("topicArn", "protocol", "endpoint");
        final SubscribeResult result = amazonSNSClient.subscribe(request);
    }

    public void trySubscribe1() {
        final SubscribeResult result = amazonSNSClient.subscribe("topicArn", "protocol", "endpoint");
    }

    public void tryTagResource() {
        final TagResourceRequest request = new TagResourceRequest();
        request.setResourceArn("resourceArn");
        request.setTags(List.of());

        final TagResourceResult result = amazonSNSClient.tagResource(request);
    }

    public void tryUnsubscribe() {
        final UnsubscribeRequest request = new UnsubscribeRequest("subscriptionArn");
        final UnsubscribeResult result = amazonSNSClient.unsubscribe(request);
    }

    public void tryUnsubscribe1() {
        final UnsubscribeResult result = amazonSNSClient.unsubscribe("subscriptionArn");
    }

    public void tryUntagResource() {
        final UntagResourceRequest request = new UntagResourceRequest();
        request.setResourceArn("resourceArn");
        request.setTagKeys(List.of());

        final UntagResourceResult result = amazonSNSClient.untagResource(request);
    }

    public void tryGetCachedResponseMetadata() {
        final AmazonWebServiceRequest request = null;
        final ResponseMetadata result = amazonSNSClient.getCachedResponseMetadata(request);
    }

    public void tryBuilder() {
        final AmazonSNSClientBuilder result = AmazonSNSClient.builder();
    }
}
