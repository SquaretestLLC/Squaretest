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

import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.*;
import software.amazon.awssdk.services.sns.paginators.*;

import java.util.Map;

public class MyClass {

    private SnsClient snsClient;

    public MyClass(final SnsClient snsClient) {
        this.snsClient = snsClient;
    }

    public void tryAddPermission1() {
        final AddPermissionRequest addPermissionRequest = AddPermissionRequest.builder()
                .topicArn("topicArn")
                .label("label")
                .awsAccountIds("awsAccountIds")
                .actionNames("actionNames")
                .build();
        final AddPermissionResponse varThatUsesProps = AddPermissionResponse.builder().build();
        final AddPermissionResponse result = snsClient.addPermission(addPermissionRequest);
    }

    public void tryAddPermission2() {
        final AddPermissionResponse varThatUsesProps = AddPermissionResponse.builder().build();
        final AddPermissionResponse result = snsClient.addPermission(Object::toString);
    }

    public void tryCheckIfPhoneNumberIsOptedOut1() {
        final CheckIfPhoneNumberIsOptedOutRequest checkIfPhoneNumberIsOptedOutRequest = CheckIfPhoneNumberIsOptedOutRequest.builder()
                .phoneNumber("phoneNumber")
                .build();
        final CheckIfPhoneNumberIsOptedOutResponse varThatUsesProps = CheckIfPhoneNumberIsOptedOutResponse.builder()
                .isOptedOut(false)
                .build();
        final CheckIfPhoneNumberIsOptedOutResponse result = snsClient.checkIfPhoneNumberIsOptedOut(
                checkIfPhoneNumberIsOptedOutRequest);
    }

    public void tryCheckIfPhoneNumberIsOptedOut2() {
        final CheckIfPhoneNumberIsOptedOutResponse varThatUsesProps = CheckIfPhoneNumberIsOptedOutResponse.builder()
                .isOptedOut(false)
                .build();
        final CheckIfPhoneNumberIsOptedOutResponse result = snsClient.checkIfPhoneNumberIsOptedOut(Object::toString);
    }

    public void tryConfirmSubscription1() {
        final ConfirmSubscriptionRequest confirmSubscriptionRequest = ConfirmSubscriptionRequest.builder()
                .topicArn("topicArn")
                .token("token")
                .authenticateOnUnsubscribe("authenticateOnUnsubscribe")
                .build();
        final ConfirmSubscriptionResponse varThatUsesProps = ConfirmSubscriptionResponse.builder()
                .subscriptionArn("subscriptionArn")
                .build();
        final ConfirmSubscriptionResponse result = snsClient.confirmSubscription(confirmSubscriptionRequest);
    }

    public void tryConfirmSubscription2() {
        final ConfirmSubscriptionResponse varThatUsesProps = ConfirmSubscriptionResponse.builder()
                .subscriptionArn("subscriptionArn")
                .build();
        final ConfirmSubscriptionResponse result = snsClient.confirmSubscription(Object::toString);
    }

    public void tryCreatePlatformApplication1() {
        final CreatePlatformApplicationRequest createPlatformApplicationRequest = CreatePlatformApplicationRequest.builder()
                .name("name")
                .platform("platform")
                .attributes(Map.ofEntries(Map.entry("value", "value")))
                .build();
        final CreatePlatformApplicationResponse varThatUsesProps = CreatePlatformApplicationResponse.builder()
                .platformApplicationArn("platformApplicationArn")
                .build();
        final CreatePlatformApplicationResponse result = snsClient.createPlatformApplication(
                createPlatformApplicationRequest);
    }

    public void tryCreatePlatformApplication2() {
        final CreatePlatformApplicationResponse varThatUsesProps = CreatePlatformApplicationResponse.builder()
                .platformApplicationArn("platformApplicationArn")
                .build();
        final CreatePlatformApplicationResponse result = snsClient.createPlatformApplication(Object::toString);
    }

    public void tryCreatePlatformEndpoint1() {
        final CreatePlatformEndpointRequest createPlatformEndpointRequest = CreatePlatformEndpointRequest.builder()
                .platformApplicationArn("platformApplicationArn")
                .token("token")
                .customUserData("customUserData")
                .attributes(Map.ofEntries(Map.entry("value", "value")))
                .build();
        final CreatePlatformEndpointResponse varThatUsesProps = CreatePlatformEndpointResponse.builder()
                .endpointArn("endpointArn")
                .build();
        final CreatePlatformEndpointResponse result = snsClient.createPlatformEndpoint(createPlatformEndpointRequest);
    }

    public void tryCreatePlatformEndpoint2() {
        final CreatePlatformEndpointResponse varThatUsesProps = CreatePlatformEndpointResponse.builder()
                .endpointArn("endpointArn")
                .build();
        final CreatePlatformEndpointResponse result = snsClient.createPlatformEndpoint(Object::toString);
    }

    public void tryCreateTopic1() {
        final CreateTopicRequest createTopicRequest = CreateTopicRequest.builder()
                .name("name")
                .attributes(Map.ofEntries(Map.entry("value", "value")))
                .tags(Tag.builder()
                        .key("key")
                        .value("value")
                        .build())
                .build();
        final CreateTopicResponse varThatUsesProps = CreateTopicResponse.builder()
                .topicArn("topicArn")
                .build();
        final CreateTopicResponse result = snsClient.createTopic(createTopicRequest);
    }

    public void tryCreateTopic2() {
        final CreateTopicResponse varThatUsesProps = CreateTopicResponse.builder()
                .topicArn("topicArn")
                .build();
        final CreateTopicResponse result = snsClient.createTopic(Object::toString);
    }

    public void tryDeleteEndpoint1() {
        final DeleteEndpointRequest deleteEndpointRequest = DeleteEndpointRequest.builder()
                .endpointArn("endpointArn")
                .build();
        final DeleteEndpointResponse varThatUsesProps = DeleteEndpointResponse.builder().build();
        final DeleteEndpointResponse result = snsClient.deleteEndpoint(deleteEndpointRequest);
    }

    public void tryDeleteEndpoint2() {
        final DeleteEndpointResponse varThatUsesProps = DeleteEndpointResponse.builder().build();
        final DeleteEndpointResponse result = snsClient.deleteEndpoint(Object::toString);
    }

    public void tryDeletePlatformApplication1() {
        final DeletePlatformApplicationRequest deletePlatformApplicationRequest = DeletePlatformApplicationRequest.builder()
                .platformApplicationArn("platformApplicationArn")
                .build();
        final DeletePlatformApplicationResponse varThatUsesProps = DeletePlatformApplicationResponse.builder().build();
        final DeletePlatformApplicationResponse result = snsClient.deletePlatformApplication(
                deletePlatformApplicationRequest);
    }

    public void tryDeletePlatformApplication2() {
        final DeletePlatformApplicationResponse varThatUsesProps = DeletePlatformApplicationResponse.builder().build();
        final DeletePlatformApplicationResponse result = snsClient.deletePlatformApplication(Object::toString);
    }

    public void tryDeleteTopic1() {
        final DeleteTopicRequest deleteTopicRequest = DeleteTopicRequest.builder()
                .topicArn("topicArn")
                .build();
        final DeleteTopicResponse varThatUsesProps = DeleteTopicResponse.builder().build();
        final DeleteTopicResponse result = snsClient.deleteTopic(deleteTopicRequest);
    }

    public void tryDeleteTopic2() {
        final DeleteTopicResponse varThatUsesProps = DeleteTopicResponse.builder().build();
        final DeleteTopicResponse result = snsClient.deleteTopic(Object::toString);
    }

    public void tryGetEndpointAttributes1() {
        final GetEndpointAttributesRequest getEndpointAttributesRequest = GetEndpointAttributesRequest.builder()
                .endpointArn("endpointArn")
                .build();
        final GetEndpointAttributesResponse varThatUsesProps = GetEndpointAttributesResponse.builder()
                .attributes(Map.ofEntries(Map.entry("value", "value")))
                .build();
        final GetEndpointAttributesResponse result = snsClient.getEndpointAttributes(getEndpointAttributesRequest);
    }

    public void tryGetEndpointAttributes2() {
        final GetEndpointAttributesResponse varThatUsesProps = GetEndpointAttributesResponse.builder()
                .attributes(Map.ofEntries(Map.entry("value", "value")))
                .build();
        final GetEndpointAttributesResponse result = snsClient.getEndpointAttributes(Object::toString);
    }

    public void tryGetPlatformApplicationAttributes1() {
        final GetPlatformApplicationAttributesRequest getPlatformApplicationAttributesRequest = GetPlatformApplicationAttributesRequest.builder()
                .platformApplicationArn("platformApplicationArn")
                .build();
        final GetPlatformApplicationAttributesResponse varThatUsesProps = GetPlatformApplicationAttributesResponse.builder()
                .attributes(Map.ofEntries(Map.entry("value", "value")))
                .build();
        final GetPlatformApplicationAttributesResponse result = snsClient.getPlatformApplicationAttributes(
                getPlatformApplicationAttributesRequest);
    }

    public void tryGetPlatformApplicationAttributes2() {
        final GetPlatformApplicationAttributesResponse varThatUsesProps = GetPlatformApplicationAttributesResponse.builder()
                .attributes(Map.ofEntries(Map.entry("value", "value")))
                .build();
        final GetPlatformApplicationAttributesResponse result = snsClient.getPlatformApplicationAttributes(
                Object::toString);
    }

    public void tryGetSMSAttributes1() {
        final GetSmsAttributesResponse varThatUsesProps = GetSmsAttributesResponse.builder()
                .attributes(Map.ofEntries(Map.entry("value", "value")))
                .build();
        final GetSmsAttributesResponse result = snsClient.getSMSAttributes();
    }

    public void tryGetSMSAttributes2() {
        final GetSmsAttributesRequest getSmsAttributesRequest = GetSmsAttributesRequest.builder()
                .attributes("attributes")
                .build();
        final GetSmsAttributesResponse varThatUsesProps = GetSmsAttributesResponse.builder()
                .attributes(Map.ofEntries(Map.entry("value", "value")))
                .build();
        final GetSmsAttributesResponse result = snsClient.getSMSAttributes(getSmsAttributesRequest);
    }

    public void tryGetSMSAttributes3() {
        final GetSmsAttributesResponse varThatUsesProps = GetSmsAttributesResponse.builder()
                .attributes(Map.ofEntries(Map.entry("value", "value")))
                .build();
        final GetSmsAttributesResponse result = snsClient.getSMSAttributes(Object::toString);
    }

    public void tryGetSubscriptionAttributes1() {
        final GetSubscriptionAttributesRequest getSubscriptionAttributesRequest = GetSubscriptionAttributesRequest.builder()
                .subscriptionArn("subscriptionArn")
                .build();
        final GetSubscriptionAttributesResponse varThatUsesProps = GetSubscriptionAttributesResponse.builder()
                .attributes(Map.ofEntries(Map.entry("value", "value")))
                .build();
        final GetSubscriptionAttributesResponse result = snsClient.getSubscriptionAttributes(
                getSubscriptionAttributesRequest);
    }

    public void tryGetSubscriptionAttributes2() {
        final GetSubscriptionAttributesResponse varThatUsesProps = GetSubscriptionAttributesResponse.builder()
                .attributes(Map.ofEntries(Map.entry("value", "value")))
                .build();
        final GetSubscriptionAttributesResponse result = snsClient.getSubscriptionAttributes(Object::toString);
    }

    public void tryGetTopicAttributes1() {
        final GetTopicAttributesRequest getTopicAttributesRequest = GetTopicAttributesRequest.builder()
                .topicArn("topicArn")
                .build();
        final GetTopicAttributesResponse varThatUsesProps = GetTopicAttributesResponse.builder()
                .attributes(Map.ofEntries(Map.entry("value", "value")))
                .build();
        final GetTopicAttributesResponse result = snsClient.getTopicAttributes(getTopicAttributesRequest);
    }

    public void tryGetTopicAttributes2() {
        final GetTopicAttributesResponse varThatUsesProps = GetTopicAttributesResponse.builder()
                .attributes(Map.ofEntries(Map.entry("value", "value")))
                .build();
        final GetTopicAttributesResponse result = snsClient.getTopicAttributes(Object::toString);
    }

    public void tryListEndpointsByPlatformApplication1() {
        final ListEndpointsByPlatformApplicationRequest listEndpointsByPlatformApplicationRequest = ListEndpointsByPlatformApplicationRequest.builder()
                .platformApplicationArn("platformApplicationArn")
                .nextToken("nextToken")
                .build();
        final ListEndpointsByPlatformApplicationResponse varThatUsesProps = ListEndpointsByPlatformApplicationResponse.builder()
                .endpoints(Endpoint.builder()
                        .endpointArn("endpointArn")
                        .attributes(Map.ofEntries(Map.entry("value", "value")))
                        .build())
                .nextToken("nextToken")
                .build();
        final ListEndpointsByPlatformApplicationResponse result = snsClient.listEndpointsByPlatformApplication(
                listEndpointsByPlatformApplicationRequest);
    }

    public void tryListEndpointsByPlatformApplication2() {
        final ListEndpointsByPlatformApplicationResponse varThatUsesProps = ListEndpointsByPlatformApplicationResponse.builder()
                .endpoints(Endpoint.builder()
                        .endpointArn("endpointArn")
                        .attributes(Map.ofEntries(Map.entry("value", "value")))
                        .build())
                .nextToken("nextToken")
                .build();
        final ListEndpointsByPlatformApplicationResponse result = snsClient.listEndpointsByPlatformApplication(
                Object::toString);
    }

    public void tryListEndpointsByPlatformApplicationPaginator1() {
        final ListEndpointsByPlatformApplicationRequest listEndpointsByPlatformApplicationRequest = ListEndpointsByPlatformApplicationRequest.builder()
                .platformApplicationArn("platformApplicationArn")
                .nextToken("nextToken")
                .build();
        final ListEndpointsByPlatformApplicationIterable varThatUsesProps = null;
        final ListEndpointsByPlatformApplicationIterable result = snsClient.listEndpointsByPlatformApplicationPaginator(
                listEndpointsByPlatformApplicationRequest);
    }

    public void tryListEndpointsByPlatformApplicationPaginator2() {
        final ListEndpointsByPlatformApplicationIterable varThatUsesProps = null;
        final ListEndpointsByPlatformApplicationIterable result = snsClient.listEndpointsByPlatformApplicationPaginator(
                Object::toString);
    }

    public void tryListPhoneNumbersOptedOut1() {
        final ListPhoneNumbersOptedOutResponse varThatUsesProps = ListPhoneNumbersOptedOutResponse.builder()
                .phoneNumbers("phoneNumbers")
                .nextToken("nextToken")
                .build();
        final ListPhoneNumbersOptedOutResponse result = snsClient.listPhoneNumbersOptedOut();
    }

    public void tryListPhoneNumbersOptedOut2() {
        final ListPhoneNumbersOptedOutRequest listPhoneNumbersOptedOutRequest = ListPhoneNumbersOptedOutRequest.builder()
                .nextToken("nextToken")
                .build();
        final ListPhoneNumbersOptedOutResponse varThatUsesProps = ListPhoneNumbersOptedOutResponse.builder()
                .phoneNumbers("phoneNumbers")
                .nextToken("nextToken")
                .build();
        final ListPhoneNumbersOptedOutResponse result = snsClient.listPhoneNumbersOptedOut(
                listPhoneNumbersOptedOutRequest);
    }

    public void tryListPhoneNumbersOptedOut3() {
        final ListPhoneNumbersOptedOutResponse varThatUsesProps = ListPhoneNumbersOptedOutResponse.builder()
                .phoneNumbers("phoneNumbers")
                .nextToken("nextToken")
                .build();
        final ListPhoneNumbersOptedOutResponse result = snsClient.listPhoneNumbersOptedOut(Object::toString);
    }

    public void tryListPlatformApplications1() {
        final ListPlatformApplicationsResponse varThatUsesProps = ListPlatformApplicationsResponse.builder()
                .platformApplications(PlatformApplication.builder()
                        .platformApplicationArn("platformApplicationArn")
                        .attributes(Map.ofEntries(Map.entry("value", "value")))
                        .build())
                .nextToken("nextToken")
                .build();
        final ListPlatformApplicationsResponse result = snsClient.listPlatformApplications();
    }

    public void tryListPlatformApplications2() {
        final ListPlatformApplicationsRequest listPlatformApplicationsRequest = ListPlatformApplicationsRequest.builder()
                .nextToken("nextToken")
                .build();
        final ListPlatformApplicationsResponse varThatUsesProps = ListPlatformApplicationsResponse.builder()
                .platformApplications(PlatformApplication.builder()
                        .platformApplicationArn("platformApplicationArn")
                        .attributes(Map.ofEntries(Map.entry("value", "value")))
                        .build())
                .nextToken("nextToken")
                .build();
        final ListPlatformApplicationsResponse result = snsClient.listPlatformApplications(
                listPlatformApplicationsRequest);
    }

    public void tryListPlatformApplications3() {
        final ListPlatformApplicationsResponse varThatUsesProps = ListPlatformApplicationsResponse.builder()
                .platformApplications(PlatformApplication.builder()
                        .platformApplicationArn("platformApplicationArn")
                        .attributes(Map.ofEntries(Map.entry("value", "value")))
                        .build())
                .nextToken("nextToken")
                .build();
        final ListPlatformApplicationsResponse result = snsClient.listPlatformApplications(Object::toString);
    }

    public void tryListPlatformApplicationsPaginator1() {
        final ListPlatformApplicationsIterable varThatUsesProps = null;
        final ListPlatformApplicationsIterable result = snsClient.listPlatformApplicationsPaginator();
    }

    public void tryListPlatformApplicationsPaginator2() {
        final ListPlatformApplicationsRequest listPlatformApplicationsRequest = ListPlatformApplicationsRequest.builder()
                .nextToken("nextToken")
                .build();
        final ListPlatformApplicationsIterable varThatUsesProps = null;
        final ListPlatformApplicationsIterable result = snsClient.listPlatformApplicationsPaginator(
                listPlatformApplicationsRequest);
    }

    public void tryListPlatformApplicationsPaginator3() {
        final ListPlatformApplicationsIterable varThatUsesProps = null;
        final ListPlatformApplicationsIterable result = snsClient.listPlatformApplicationsPaginator(Object::toString);
    }

    public void tryListSubscriptions1() {
        final ListSubscriptionsResponse varThatUsesProps = ListSubscriptionsResponse.builder()
                .subscriptions(Subscription.builder()
                        .subscriptionArn("subscriptionArn")
                        .owner("owner")
                        .protocol("protocol")
                        .endpoint("endpoint")
                        .topicArn("topicArn")
                        .build())
                .nextToken("nextToken")
                .build();
        final ListSubscriptionsResponse result = snsClient.listSubscriptions();
    }

    public void tryListSubscriptions2() {
        final ListSubscriptionsRequest listSubscriptionsRequest = ListSubscriptionsRequest.builder()
                .nextToken("nextToken")
                .build();
        final ListSubscriptionsResponse varThatUsesProps = ListSubscriptionsResponse.builder()
                .subscriptions(Subscription.builder()
                        .subscriptionArn("subscriptionArn")
                        .owner("owner")
                        .protocol("protocol")
                        .endpoint("endpoint")
                        .topicArn("topicArn")
                        .build())
                .nextToken("nextToken")
                .build();
        final ListSubscriptionsResponse result = snsClient.listSubscriptions(listSubscriptionsRequest);
    }

    public void tryListSubscriptions3() {
        final ListSubscriptionsResponse varThatUsesProps = ListSubscriptionsResponse.builder()
                .subscriptions(Subscription.builder()
                        .subscriptionArn("subscriptionArn")
                        .owner("owner")
                        .protocol("protocol")
                        .endpoint("endpoint")
                        .topicArn("topicArn")
                        .build())
                .nextToken("nextToken")
                .build();
        final ListSubscriptionsResponse result = snsClient.listSubscriptions(Object::toString);
    }

    public void tryListSubscriptionsPaginator1() {
        final ListSubscriptionsIterable varThatUsesProps = null;
        final ListSubscriptionsIterable result = snsClient.listSubscriptionsPaginator();
    }

    public void tryListSubscriptionsPaginator2() {
        final ListSubscriptionsRequest listSubscriptionsRequest = ListSubscriptionsRequest.builder()
                .nextToken("nextToken")
                .build();
        final ListSubscriptionsIterable varThatUsesProps = null;
        final ListSubscriptionsIterable result = snsClient.listSubscriptionsPaginator(listSubscriptionsRequest);
    }

    public void tryListSubscriptionsPaginator3() {
        final ListSubscriptionsIterable varThatUsesProps = null;
        final ListSubscriptionsIterable result = snsClient.listSubscriptionsPaginator(Object::toString);
    }

    public void tryListSubscriptionsByTopic1() {
        final ListSubscriptionsByTopicRequest listSubscriptionsByTopicRequest = ListSubscriptionsByTopicRequest.builder()
                .topicArn("topicArn")
                .nextToken("nextToken")
                .build();
        final ListSubscriptionsByTopicResponse varThatUsesProps = ListSubscriptionsByTopicResponse.builder()
                .subscriptions(Subscription.builder()
                        .subscriptionArn("subscriptionArn")
                        .owner("owner")
                        .protocol("protocol")
                        .endpoint("endpoint")
                        .topicArn("topicArn")
                        .build())
                .nextToken("nextToken")
                .build();
        final ListSubscriptionsByTopicResponse result = snsClient.listSubscriptionsByTopic(
                listSubscriptionsByTopicRequest);
    }

    public void tryListSubscriptionsByTopic2() {
        final ListSubscriptionsByTopicResponse varThatUsesProps = ListSubscriptionsByTopicResponse.builder()
                .subscriptions(Subscription.builder()
                        .subscriptionArn("subscriptionArn")
                        .owner("owner")
                        .protocol("protocol")
                        .endpoint("endpoint")
                        .topicArn("topicArn")
                        .build())
                .nextToken("nextToken")
                .build();
        final ListSubscriptionsByTopicResponse result = snsClient.listSubscriptionsByTopic(Object::toString);
    }

    public void tryListSubscriptionsByTopicPaginator1() {
        final ListSubscriptionsByTopicRequest listSubscriptionsByTopicRequest = ListSubscriptionsByTopicRequest.builder()
                .topicArn("topicArn")
                .nextToken("nextToken")
                .build();
        final ListSubscriptionsByTopicIterable varThatUsesProps = null;
        final ListSubscriptionsByTopicIterable result = snsClient.listSubscriptionsByTopicPaginator(
                listSubscriptionsByTopicRequest);
    }

    public void tryListSubscriptionsByTopicPaginator2() {
        final ListSubscriptionsByTopicIterable varThatUsesProps = null;
        final ListSubscriptionsByTopicIterable result = snsClient.listSubscriptionsByTopicPaginator(Object::toString);
    }

    public void tryListTagsForResource1() {
        final ListTagsForResourceRequest listTagsForResourceRequest = ListTagsForResourceRequest.builder()
                .resourceArn("resourceArn")
                .build();
        final ListTagsForResourceResponse varThatUsesProps = ListTagsForResourceResponse.builder()
                .tags(Tag.builder()
                        .key("key")
                        .value("value")
                        .build())
                .build();
        final ListTagsForResourceResponse result = snsClient.listTagsForResource(listTagsForResourceRequest);
    }

    public void tryListTagsForResource2() {
        final ListTagsForResourceResponse varThatUsesProps = ListTagsForResourceResponse.builder()
                .tags(Tag.builder()
                        .key("key")
                        .value("value")
                        .build())
                .build();
        final ListTagsForResourceResponse result = snsClient.listTagsForResource(Object::toString);
    }

    public void tryListTopics1() {
        final ListTopicsResponse varThatUsesProps = ListTopicsResponse.builder()
                .topics(Topic.builder()
                        .topicArn("topicArn")
                        .build())
                .nextToken("nextToken")
                .build();
        final ListTopicsResponse result = snsClient.listTopics();
    }

    public void tryListTopics2() {
        final ListTopicsRequest listTopicsRequest = ListTopicsRequest.builder()
                .nextToken("nextToken")
                .build();
        final ListTopicsResponse varThatUsesProps = ListTopicsResponse.builder()
                .topics(Topic.builder()
                        .topicArn("topicArn")
                        .build())
                .nextToken("nextToken")
                .build();
        final ListTopicsResponse result = snsClient.listTopics(listTopicsRequest);
    }

    public void tryListTopics3() {
        final ListTopicsResponse varThatUsesProps = ListTopicsResponse.builder()
                .topics(Topic.builder()
                        .topicArn("topicArn")
                        .build())
                .nextToken("nextToken")
                .build();
        final ListTopicsResponse result = snsClient.listTopics(Object::toString);
    }

    public void tryListTopicsPaginator1() {
        final ListTopicsIterable varThatUsesProps = null;
        final ListTopicsIterable result = snsClient.listTopicsPaginator();
    }

    public void tryListTopicsPaginator2() {
        final ListTopicsRequest listTopicsRequest = ListTopicsRequest.builder()
                .nextToken("nextToken")
                .build();
        final ListTopicsIterable varThatUsesProps = null;
        final ListTopicsIterable result = snsClient.listTopicsPaginator(listTopicsRequest);
    }

    public void tryListTopicsPaginator3() {
        final ListTopicsIterable varThatUsesProps = null;
        final ListTopicsIterable result = snsClient.listTopicsPaginator(Object::toString);
    }

    public void tryOptInPhoneNumber1() {
        final OptInPhoneNumberRequest optInPhoneNumberRequest = OptInPhoneNumberRequest.builder()
                .phoneNumber("phoneNumber")
                .build();
        final OptInPhoneNumberResponse varThatUsesProps = OptInPhoneNumberResponse.builder().build();
        final OptInPhoneNumberResponse result = snsClient.optInPhoneNumber(optInPhoneNumberRequest);
    }

    public void tryOptInPhoneNumber2() {
        final OptInPhoneNumberResponse varThatUsesProps = OptInPhoneNumberResponse.builder().build();
        final OptInPhoneNumberResponse result = snsClient.optInPhoneNumber(Object::toString);
    }

    public void tryPublish1() {
        final PublishRequest publishRequest = PublishRequest.builder()
                .topicArn("topicArn")
                .subject("subject")
                .message("message")
                .build();
        final PublishResponse varThatUsesProps = PublishResponse.builder()
                .messageId("messageId")
                .build();
        final PublishResponse result = snsClient.publish(publishRequest);
    }

    public void tryPublish2() {
        final PublishResponse varThatUsesProps = PublishResponse.builder()
                .messageId("messageId")
                .build();
        final PublishResponse result = snsClient.publish(Object::toString);
    }

    public void tryRemovePermission1() {
        final RemovePermissionRequest removePermissionRequest = RemovePermissionRequest.builder()
                .topicArn("topicArn")
                .label("label")
                .build();
        final RemovePermissionResponse varThatUsesProps = RemovePermissionResponse.builder().build();
        final RemovePermissionResponse result = snsClient.removePermission(removePermissionRequest);
    }

    public void tryRemovePermission2() {
        final RemovePermissionResponse varThatUsesProps = RemovePermissionResponse.builder().build();
        final RemovePermissionResponse result = snsClient.removePermission(Object::toString);
    }

    public void trySetEndpointAttributes1() {
        final SetEndpointAttributesRequest setEndpointAttributesRequest = SetEndpointAttributesRequest.builder()
                .endpointArn("endpointArn")
                .attributes(Map.ofEntries(Map.entry("value", "value")))
                .build();
        final SetEndpointAttributesResponse varThatUsesProps = SetEndpointAttributesResponse.builder().build();
        final SetEndpointAttributesResponse result = snsClient.setEndpointAttributes(setEndpointAttributesRequest);
    }

    public void trySetEndpointAttributes2() {
        final SetEndpointAttributesResponse varThatUsesProps = SetEndpointAttributesResponse.builder().build();
        final SetEndpointAttributesResponse result = snsClient.setEndpointAttributes(Object::toString);
    }

    public void trySetPlatformApplicationAttributes1() {
        final SetPlatformApplicationAttributesRequest setPlatformApplicationAttributesRequest = SetPlatformApplicationAttributesRequest.builder()
                .platformApplicationArn("platformApplicationArn")
                .attributes(Map.ofEntries(Map.entry("value", "value")))
                .build();
        final SetPlatformApplicationAttributesResponse varThatUsesProps = SetPlatformApplicationAttributesResponse.builder().build();
        final SetPlatformApplicationAttributesResponse result = snsClient.setPlatformApplicationAttributes(
                setPlatformApplicationAttributesRequest);
    }

    public void trySetPlatformApplicationAttributes2() {
        final SetPlatformApplicationAttributesResponse varThatUsesProps = SetPlatformApplicationAttributesResponse.builder().build();
        final SetPlatformApplicationAttributesResponse result = snsClient.setPlatformApplicationAttributes(
                Object::toString);
    }

    public void trySetSMSAttributes1() {
        final SetSmsAttributesRequest setSmsAttributesRequest = SetSmsAttributesRequest.builder()
                .attributes(Map.ofEntries(Map.entry("value", "value")))
                .build();
        final SetSmsAttributesResponse varThatUsesProps = SetSmsAttributesResponse.builder().build();
        final SetSmsAttributesResponse result = snsClient.setSMSAttributes(setSmsAttributesRequest);
    }

    public void trySetSMSAttributes2() {
        final SetSmsAttributesResponse varThatUsesProps = SetSmsAttributesResponse.builder().build();
        final SetSmsAttributesResponse result = snsClient.setSMSAttributes(Object::toString);
    }

    public void trySetSubscriptionAttributes1() {
        final SetSubscriptionAttributesRequest setSubscriptionAttributesRequest = SetSubscriptionAttributesRequest.builder()
                .subscriptionArn("subscriptionArn")
                .attributeName("attributeName")
                .attributeValue("attributeValue")
                .build();
        final SetSubscriptionAttributesResponse varThatUsesProps = SetSubscriptionAttributesResponse.builder().build();
        final SetSubscriptionAttributesResponse result = snsClient.setSubscriptionAttributes(
                setSubscriptionAttributesRequest);
    }

    public void trySetSubscriptionAttributes2() {
        final SetSubscriptionAttributesResponse varThatUsesProps = SetSubscriptionAttributesResponse.builder().build();
        final SetSubscriptionAttributesResponse result = snsClient.setSubscriptionAttributes(Object::toString);
    }

    public void trySetTopicAttributes1() {
        final SetTopicAttributesRequest setTopicAttributesRequest = SetTopicAttributesRequest.builder()
                .topicArn("topicArn")
                .attributeName("attributeName")
                .attributeValue("attributeValue")
                .build();
        final SetTopicAttributesResponse varThatUsesProps = SetTopicAttributesResponse.builder().build();
        final SetTopicAttributesResponse result = snsClient.setTopicAttributes(setTopicAttributesRequest);
    }

    public void trySetTopicAttributes2() {
        final SetTopicAttributesResponse varThatUsesProps = SetTopicAttributesResponse.builder().build();
        final SetTopicAttributesResponse result = snsClient.setTopicAttributes(Object::toString);
    }

    public void trySubscribe1() {
        final SubscribeRequest subscribeRequest = SubscribeRequest.builder()
                .topicArn("topicArn")
                .protocol("protocol")
                .endpoint("endpoint")
                .attributes(Map.ofEntries(Map.entry("value", "value")))
                .returnSubscriptionArn(false)
                .build();
        final SubscribeResponse varThatUsesProps = SubscribeResponse.builder()
                .subscriptionArn("subscriptionArn")
                .build();
        final SubscribeResponse result = snsClient.subscribe(subscribeRequest);
    }

    public void trySubscribe2() {
        final SubscribeResponse varThatUsesProps = SubscribeResponse.builder()
                .subscriptionArn("subscriptionArn")
                .build();
        final SubscribeResponse result = snsClient.subscribe(Object::toString);
    }

    public void tryTagResource1() {
        final TagResourceRequest tagResourceRequest = TagResourceRequest.builder()
                .resourceArn("resourceArn")
                .tags(Tag.builder()
                        .key("key")
                        .value("value")
                        .build())
                .build();
        final TagResourceResponse varThatUsesProps = TagResourceResponse.builder().build();
        final TagResourceResponse result = snsClient.tagResource(tagResourceRequest);
    }

    public void tryTagResource2() {
        final TagResourceResponse varThatUsesProps = TagResourceResponse.builder().build();
        final TagResourceResponse result = snsClient.tagResource(Object::toString);
    }

    public void tryUnsubscribe1() {
        final UnsubscribeRequest unsubscribeRequest = UnsubscribeRequest.builder()
                .subscriptionArn("subscriptionArn")
                .build();
        final UnsubscribeResponse varThatUsesProps = UnsubscribeResponse.builder().build();
        final UnsubscribeResponse result = snsClient.unsubscribe(unsubscribeRequest);
    }

    public void tryUnsubscribe2() {
        final UnsubscribeResponse varThatUsesProps = UnsubscribeResponse.builder().build();
        final UnsubscribeResponse result = snsClient.unsubscribe(Object::toString);
    }

    public void tryUntagResource1() {
        final UntagResourceRequest untagResourceRequest = UntagResourceRequest.builder()
                .resourceArn("resourceArn")
                .tagKeys("tagKeys")
                .build();
        final UntagResourceResponse varThatUsesProps = UntagResourceResponse.builder().build();
        final UntagResourceResponse result = snsClient.untagResource(untagResourceRequest);
    }

    public void tryUntagResource2() {
        final UntagResourceResponse varThatUsesProps = UntagResourceResponse.builder().build();
        final UntagResourceResponse result = snsClient.untagResource(Object::toString);
    }
}
