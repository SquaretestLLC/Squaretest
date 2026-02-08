package com.myapp;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.ResponseMetadata;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private AmazonSNSClient mockAmazonSNSClient;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockAmazonSNSClient);
    }

    @Test
    void testTryAddPermission() {
        // Setup
        when(mockAmazonSNSClient.addPermission(
                new AddPermissionRequest("topicArn", "label", List.of("value"), List.of("value"))))
                .thenReturn(new AddPermissionResult());

        // Run the test
        myClassUnderTest.tryAddPermission();

        // Verify the results
    }

    @Test
    void testTryAddPermission_AmazonSNSClientThrowsInvalidParameterException() {
        // Setup
        when(mockAmazonSNSClient.addPermission(
                new AddPermissionRequest("topicArn", "label", List.of("value"), List.of("value"))))
                .thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryAddPermission());
    }

    @Test
    void testTryAddPermission_AmazonSNSClientThrowsInternalErrorException() {
        // Setup
        when(mockAmazonSNSClient.addPermission(
                new AddPermissionRequest("topicArn", "label", List.of("value"), List.of("value"))))
                .thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryAddPermission());
    }

    @Test
    void testTryAddPermission_AmazonSNSClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockAmazonSNSClient.addPermission(
                new AddPermissionRequest("topicArn", "label", List.of("value"), List.of("value"))))
                .thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryAddPermission());
    }

    @Test
    void testTryAddPermission_AmazonSNSClientThrowsNotFoundException() {
        // Setup
        when(mockAmazonSNSClient.addPermission(
                new AddPermissionRequest("topicArn", "label", List.of("value"), List.of("value"))))
                .thenThrow(NotFoundException.class);

        // Run the test
        assertThrows(NotFoundException.class, () -> myClassUnderTest.tryAddPermission());
    }

    @Test
    void testTryAddPermission1() {
        // Setup
        when(mockAmazonSNSClient.addPermission("topicArn", "label", List.of("value"), List.of("value")))
                .thenReturn(new AddPermissionResult());

        // Run the test
        myClassUnderTest.tryAddPermission1();

        // Verify the results
    }

    @Test
    void testTryCheckIfPhoneNumberIsOptedOut() {
        // Setup
        // Configure AmazonSNSClient.checkIfPhoneNumberIsOptedOut(...).
        final CheckIfPhoneNumberIsOptedOutResult checkIfPhoneNumberIsOptedOutResult = new CheckIfPhoneNumberIsOptedOutResult();
        final CheckIfPhoneNumberIsOptedOutRequest request = new CheckIfPhoneNumberIsOptedOutRequest();
        request.setPhoneNumber("phoneNumber");
        when(mockAmazonSNSClient.checkIfPhoneNumberIsOptedOut(request)).thenReturn(checkIfPhoneNumberIsOptedOutResult);

        // Run the test
        myClassUnderTest.tryCheckIfPhoneNumberIsOptedOut();

        // Verify the results
    }

    @Test
    void testTryCheckIfPhoneNumberIsOptedOut_AmazonSNSClientThrowsThrottledException() {
        // Setup
        // Configure AmazonSNSClient.checkIfPhoneNumberIsOptedOut(...).
        final CheckIfPhoneNumberIsOptedOutRequest request = new CheckIfPhoneNumberIsOptedOutRequest();
        request.setPhoneNumber("phoneNumber");
        when(mockAmazonSNSClient.checkIfPhoneNumberIsOptedOut(request)).thenThrow(ThrottledException.class);

        // Run the test
        assertThrows(ThrottledException.class, () -> myClassUnderTest.tryCheckIfPhoneNumberIsOptedOut());
    }

    @Test
    void testTryCheckIfPhoneNumberIsOptedOut_AmazonSNSClientThrowsInternalErrorException() {
        // Setup
        // Configure AmazonSNSClient.checkIfPhoneNumberIsOptedOut(...).
        final CheckIfPhoneNumberIsOptedOutRequest request = new CheckIfPhoneNumberIsOptedOutRequest();
        request.setPhoneNumber("phoneNumber");
        when(mockAmazonSNSClient.checkIfPhoneNumberIsOptedOut(request)).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryCheckIfPhoneNumberIsOptedOut());
    }

    @Test
    void testTryCheckIfPhoneNumberIsOptedOut_AmazonSNSClientThrowsAuthorizationErrorException() {
        // Setup
        // Configure AmazonSNSClient.checkIfPhoneNumberIsOptedOut(...).
        final CheckIfPhoneNumberIsOptedOutRequest request = new CheckIfPhoneNumberIsOptedOutRequest();
        request.setPhoneNumber("phoneNumber");
        when(mockAmazonSNSClient.checkIfPhoneNumberIsOptedOut(request)).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryCheckIfPhoneNumberIsOptedOut());
    }

    @Test
    void testTryCheckIfPhoneNumberIsOptedOut_AmazonSNSClientThrowsInvalidParameterException() {
        // Setup
        // Configure AmazonSNSClient.checkIfPhoneNumberIsOptedOut(...).
        final CheckIfPhoneNumberIsOptedOutRequest request = new CheckIfPhoneNumberIsOptedOutRequest();
        request.setPhoneNumber("phoneNumber");
        when(mockAmazonSNSClient.checkIfPhoneNumberIsOptedOut(request)).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryCheckIfPhoneNumberIsOptedOut());
    }

    @Test
    void testTryConfirmSubscription() {
        // Setup
        // Configure AmazonSNSClient.confirmSubscription(...).
        final ConfirmSubscriptionResult confirmSubscriptionResult = new ConfirmSubscriptionResult();
        when(mockAmazonSNSClient.confirmSubscription(
                new ConfirmSubscriptionRequest("topicArn", "token", "authenticateOnUnsubscribe")))
                .thenReturn(confirmSubscriptionResult);

        // Run the test
        myClassUnderTest.tryConfirmSubscription();

        // Verify the results
    }

    @Test
    void testTryConfirmSubscription_AmazonSNSClientThrowsSubscriptionLimitExceededException() {
        // Setup
        when(mockAmazonSNSClient.confirmSubscription(
                new ConfirmSubscriptionRequest("topicArn", "token", "authenticateOnUnsubscribe")))
                .thenThrow(SubscriptionLimitExceededException.class);

        // Run the test
        assertThrows(SubscriptionLimitExceededException.class, () -> myClassUnderTest.tryConfirmSubscription());
    }

    @Test
    void testTryConfirmSubscription_AmazonSNSClientThrowsInvalidParameterException() {
        // Setup
        when(mockAmazonSNSClient.confirmSubscription(
                new ConfirmSubscriptionRequest("topicArn", "token", "authenticateOnUnsubscribe")))
                .thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryConfirmSubscription());
    }

    @Test
    void testTryConfirmSubscription_AmazonSNSClientThrowsNotFoundException() {
        // Setup
        when(mockAmazonSNSClient.confirmSubscription(
                new ConfirmSubscriptionRequest("topicArn", "token", "authenticateOnUnsubscribe")))
                .thenThrow(NotFoundException.class);

        // Run the test
        assertThrows(NotFoundException.class, () -> myClassUnderTest.tryConfirmSubscription());
    }

    @Test
    void testTryConfirmSubscription_AmazonSNSClientThrowsInternalErrorException() {
        // Setup
        when(mockAmazonSNSClient.confirmSubscription(
                new ConfirmSubscriptionRequest("topicArn", "token", "authenticateOnUnsubscribe")))
                .thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryConfirmSubscription());
    }

    @Test
    void testTryConfirmSubscription_AmazonSNSClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockAmazonSNSClient.confirmSubscription(
                new ConfirmSubscriptionRequest("topicArn", "token", "authenticateOnUnsubscribe")))
                .thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryConfirmSubscription());
    }

    @Test
    void testTryConfirmSubscription_AmazonSNSClientThrowsFilterPolicyLimitExceededException() {
        // Setup
        when(mockAmazonSNSClient.confirmSubscription(
                new ConfirmSubscriptionRequest("topicArn", "token", "authenticateOnUnsubscribe")))
                .thenThrow(FilterPolicyLimitExceededException.class);

        // Run the test
        assertThrows(FilterPolicyLimitExceededException.class, () -> myClassUnderTest.tryConfirmSubscription());
    }

    @Test
    void testTryConfirmSubscription1() {
        // Setup
        // Configure AmazonSNSClient.confirmSubscription(...).
        final ConfirmSubscriptionResult confirmSubscriptionResult = new ConfirmSubscriptionResult();
        when(mockAmazonSNSClient.confirmSubscription("topicArn", "token", "authenticateOnUnsubscribe"))
                .thenReturn(confirmSubscriptionResult);

        // Run the test
        myClassUnderTest.tryConfirmSubscription1();

        // Verify the results
    }

    @Test
    void testTryConfirmSubscription2() {
        // Setup
        // Configure AmazonSNSClient.confirmSubscription(...).
        final ConfirmSubscriptionResult confirmSubscriptionResult = new ConfirmSubscriptionResult();
        when(mockAmazonSNSClient.confirmSubscription("topicArn", "token")).thenReturn(confirmSubscriptionResult);

        // Run the test
        myClassUnderTest.tryConfirmSubscription2();

        // Verify the results
    }

    @Test
    void testTryCreatePlatformApplication() {
        // Setup
        // Configure AmazonSNSClient.createPlatformApplication(...).
        final CreatePlatformApplicationResult createPlatformApplicationResult = new CreatePlatformApplicationResult();
        final CreatePlatformApplicationRequest request = new CreatePlatformApplicationRequest();
        when(mockAmazonSNSClient.createPlatformApplication(request)).thenReturn(createPlatformApplicationResult);

        // Run the test
        myClassUnderTest.tryCreatePlatformApplication();

        // Verify the results
    }

    @Test
    void testTryCreatePlatformApplication_AmazonSNSClientThrowsInvalidParameterException() {
        // Setup
        // Configure AmazonSNSClient.createPlatformApplication(...).
        final CreatePlatformApplicationRequest request = new CreatePlatformApplicationRequest();
        when(mockAmazonSNSClient.createPlatformApplication(request)).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryCreatePlatformApplication());
    }

    @Test
    void testTryCreatePlatformApplication_AmazonSNSClientThrowsInternalErrorException() {
        // Setup
        // Configure AmazonSNSClient.createPlatformApplication(...).
        final CreatePlatformApplicationRequest request = new CreatePlatformApplicationRequest();
        when(mockAmazonSNSClient.createPlatformApplication(request)).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryCreatePlatformApplication());
    }

    @Test
    void testTryCreatePlatformApplication_AmazonSNSClientThrowsAuthorizationErrorException() {
        // Setup
        // Configure AmazonSNSClient.createPlatformApplication(...).
        final CreatePlatformApplicationRequest request = new CreatePlatformApplicationRequest();
        when(mockAmazonSNSClient.createPlatformApplication(request)).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryCreatePlatformApplication());
    }

    @Test
    void testTryCreatePlatformEndpoint() {
        // Setup
        // Configure AmazonSNSClient.createPlatformEndpoint(...).
        final CreatePlatformEndpointResult createPlatformEndpointResult = new CreatePlatformEndpointResult();
        final CreatePlatformEndpointRequest request = new CreatePlatformEndpointRequest();
        when(mockAmazonSNSClient.createPlatformEndpoint(request)).thenReturn(createPlatformEndpointResult);

        // Run the test
        myClassUnderTest.tryCreatePlatformEndpoint();

        // Verify the results
    }

    @Test
    void testTryCreatePlatformEndpoint_AmazonSNSClientThrowsInvalidParameterException() {
        // Setup
        // Configure AmazonSNSClient.createPlatformEndpoint(...).
        final CreatePlatformEndpointRequest request = new CreatePlatformEndpointRequest();
        when(mockAmazonSNSClient.createPlatformEndpoint(request)).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryCreatePlatformEndpoint());
    }

    @Test
    void testTryCreatePlatformEndpoint_AmazonSNSClientThrowsInternalErrorException() {
        // Setup
        // Configure AmazonSNSClient.createPlatformEndpoint(...).
        final CreatePlatformEndpointRequest request = new CreatePlatformEndpointRequest();
        when(mockAmazonSNSClient.createPlatformEndpoint(request)).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryCreatePlatformEndpoint());
    }

    @Test
    void testTryCreatePlatformEndpoint_AmazonSNSClientThrowsAuthorizationErrorException() {
        // Setup
        // Configure AmazonSNSClient.createPlatformEndpoint(...).
        final CreatePlatformEndpointRequest request = new CreatePlatformEndpointRequest();
        when(mockAmazonSNSClient.createPlatformEndpoint(request)).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryCreatePlatformEndpoint());
    }

    @Test
    void testTryCreatePlatformEndpoint_AmazonSNSClientThrowsNotFoundException() {
        // Setup
        // Configure AmazonSNSClient.createPlatformEndpoint(...).
        final CreatePlatformEndpointRequest request = new CreatePlatformEndpointRequest();
        when(mockAmazonSNSClient.createPlatformEndpoint(request)).thenThrow(NotFoundException.class);

        // Run the test
        assertThrows(NotFoundException.class, () -> myClassUnderTest.tryCreatePlatformEndpoint());
    }

    @Test
    void testTryCreateTopic() {
        // Setup
        // Configure AmazonSNSClient.createTopic(...).
        final CreateTopicResult createTopicResult = new CreateTopicResult();
        when(mockAmazonSNSClient.createTopic(new CreateTopicRequest("name"))).thenReturn(createTopicResult);

        // Run the test
        myClassUnderTest.tryCreateTopic();

        // Verify the results
    }

    @Test
    void testTryCreateTopic_AmazonSNSClientThrowsInvalidParameterException() {
        // Setup
        when(mockAmazonSNSClient.createTopic(new CreateTopicRequest("name")))
                .thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryCreateTopic());
    }

    @Test
    void testTryCreateTopic_AmazonSNSClientThrowsTopicLimitExceededException() {
        // Setup
        when(mockAmazonSNSClient.createTopic(new CreateTopicRequest("name")))
                .thenThrow(TopicLimitExceededException.class);

        // Run the test
        assertThrows(TopicLimitExceededException.class, () -> myClassUnderTest.tryCreateTopic());
    }

    @Test
    void testTryCreateTopic_AmazonSNSClientThrowsInternalErrorException() {
        // Setup
        when(mockAmazonSNSClient.createTopic(new CreateTopicRequest("name"))).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryCreateTopic());
    }

    @Test
    void testTryCreateTopic_AmazonSNSClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockAmazonSNSClient.createTopic(new CreateTopicRequest("name")))
                .thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryCreateTopic());
    }

    @Test
    void testTryCreateTopic_AmazonSNSClientThrowsInvalidSecurityException() {
        // Setup
        when(mockAmazonSNSClient.createTopic(new CreateTopicRequest("name"))).thenThrow(InvalidSecurityException.class);

        // Run the test
        assertThrows(InvalidSecurityException.class, () -> myClassUnderTest.tryCreateTopic());
    }

    @Test
    void testTryCreateTopic_AmazonSNSClientThrowsTagLimitExceededException() {
        // Setup
        when(mockAmazonSNSClient.createTopic(new CreateTopicRequest("name")))
                .thenThrow(TagLimitExceededException.class);

        // Run the test
        assertThrows(TagLimitExceededException.class, () -> myClassUnderTest.tryCreateTopic());
    }

    @Test
    void testTryCreateTopic_AmazonSNSClientThrowsStaleTagException() {
        // Setup
        when(mockAmazonSNSClient.createTopic(new CreateTopicRequest("name"))).thenThrow(StaleTagException.class);

        // Run the test
        assertThrows(StaleTagException.class, () -> myClassUnderTest.tryCreateTopic());
    }

    @Test
    void testTryCreateTopic_AmazonSNSClientThrowsTagPolicyException() {
        // Setup
        when(mockAmazonSNSClient.createTopic(new CreateTopicRequest("name"))).thenThrow(TagPolicyException.class);

        // Run the test
        assertThrows(TagPolicyException.class, () -> myClassUnderTest.tryCreateTopic());
    }

    @Test
    void testTryCreateTopic_AmazonSNSClientThrowsConcurrentAccessException() {
        // Setup
        when(mockAmazonSNSClient.createTopic(new CreateTopicRequest("name")))
                .thenThrow(ConcurrentAccessException.class);

        // Run the test
        assertThrows(ConcurrentAccessException.class, () -> myClassUnderTest.tryCreateTopic());
    }

    @Test
    void testTryCreateTopic1() {
        // Setup
        // Configure AmazonSNSClient.createTopic(...).
        final CreateTopicResult createTopicResult = new CreateTopicResult();
        when(mockAmazonSNSClient.createTopic("name")).thenReturn(createTopicResult);

        // Run the test
        myClassUnderTest.tryCreateTopic1();

        // Verify the results
    }

    @Test
    void testTryDeleteEndpoint() {
        // Setup
        // Configure AmazonSNSClient.deleteEndpoint(...).
        final DeleteEndpointRequest request = new DeleteEndpointRequest();
        request.setEndpointArn("endpointArn");
        when(mockAmazonSNSClient.deleteEndpoint(request)).thenReturn(new DeleteEndpointResult());

        // Run the test
        myClassUnderTest.tryDeleteEndpoint();

        // Verify the results
    }

    @Test
    void testTryDeleteEndpoint_AmazonSNSClientThrowsInvalidParameterException() {
        // Setup
        // Configure AmazonSNSClient.deleteEndpoint(...).
        final DeleteEndpointRequest request = new DeleteEndpointRequest();
        request.setEndpointArn("endpointArn");
        when(mockAmazonSNSClient.deleteEndpoint(request)).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryDeleteEndpoint());
    }

    @Test
    void testTryDeleteEndpoint_AmazonSNSClientThrowsInternalErrorException() {
        // Setup
        // Configure AmazonSNSClient.deleteEndpoint(...).
        final DeleteEndpointRequest request = new DeleteEndpointRequest();
        request.setEndpointArn("endpointArn");
        when(mockAmazonSNSClient.deleteEndpoint(request)).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryDeleteEndpoint());
    }

    @Test
    void testTryDeleteEndpoint_AmazonSNSClientThrowsAuthorizationErrorException() {
        // Setup
        // Configure AmazonSNSClient.deleteEndpoint(...).
        final DeleteEndpointRequest request = new DeleteEndpointRequest();
        request.setEndpointArn("endpointArn");
        when(mockAmazonSNSClient.deleteEndpoint(request)).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryDeleteEndpoint());
    }

    @Test
    void testTryDeletePlatformApplication() {
        // Setup
        // Configure AmazonSNSClient.deletePlatformApplication(...).
        final DeletePlatformApplicationRequest request = new DeletePlatformApplicationRequest();
        request.setPlatformApplicationArn("platformApplicationArn");
        when(mockAmazonSNSClient.deletePlatformApplication(request)).thenReturn(new DeletePlatformApplicationResult());

        // Run the test
        myClassUnderTest.tryDeletePlatformApplication();

        // Verify the results
    }

    @Test
    void testTryDeletePlatformApplication_AmazonSNSClientThrowsInvalidParameterException() {
        // Setup
        // Configure AmazonSNSClient.deletePlatformApplication(...).
        final DeletePlatformApplicationRequest request = new DeletePlatformApplicationRequest();
        request.setPlatformApplicationArn("platformApplicationArn");
        when(mockAmazonSNSClient.deletePlatformApplication(request)).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryDeletePlatformApplication());
    }

    @Test
    void testTryDeletePlatformApplication_AmazonSNSClientThrowsInternalErrorException() {
        // Setup
        // Configure AmazonSNSClient.deletePlatformApplication(...).
        final DeletePlatformApplicationRequest request = new DeletePlatformApplicationRequest();
        request.setPlatformApplicationArn("platformApplicationArn");
        when(mockAmazonSNSClient.deletePlatformApplication(request)).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryDeletePlatformApplication());
    }

    @Test
    void testTryDeletePlatformApplication_AmazonSNSClientThrowsAuthorizationErrorException() {
        // Setup
        // Configure AmazonSNSClient.deletePlatformApplication(...).
        final DeletePlatformApplicationRequest request = new DeletePlatformApplicationRequest();
        request.setPlatformApplicationArn("platformApplicationArn");
        when(mockAmazonSNSClient.deletePlatformApplication(request)).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryDeletePlatformApplication());
    }

    @Test
    void testTryDeleteTopic() {
        // Setup
        when(mockAmazonSNSClient.deleteTopic(new DeleteTopicRequest("topicArn"))).thenReturn(new DeleteTopicResult());

        // Run the test
        myClassUnderTest.tryDeleteTopic();

        // Verify the results
    }

    @Test
    void testTryDeleteTopic_AmazonSNSClientThrowsInvalidParameterException() {
        // Setup
        when(mockAmazonSNSClient.deleteTopic(new DeleteTopicRequest("topicArn")))
                .thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryDeleteTopic());
    }

    @Test
    void testTryDeleteTopic_AmazonSNSClientThrowsInternalErrorException() {
        // Setup
        when(mockAmazonSNSClient.deleteTopic(new DeleteTopicRequest("topicArn")))
                .thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryDeleteTopic());
    }

    @Test
    void testTryDeleteTopic_AmazonSNSClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockAmazonSNSClient.deleteTopic(new DeleteTopicRequest("topicArn")))
                .thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryDeleteTopic());
    }

    @Test
    void testTryDeleteTopic_AmazonSNSClientThrowsNotFoundException() {
        // Setup
        when(mockAmazonSNSClient.deleteTopic(new DeleteTopicRequest("topicArn"))).thenThrow(NotFoundException.class);

        // Run the test
        assertThrows(NotFoundException.class, () -> myClassUnderTest.tryDeleteTopic());
    }

    @Test
    void testTryDeleteTopic_AmazonSNSClientThrowsStaleTagException() {
        // Setup
        when(mockAmazonSNSClient.deleteTopic(new DeleteTopicRequest("topicArn"))).thenThrow(StaleTagException.class);

        // Run the test
        assertThrows(StaleTagException.class, () -> myClassUnderTest.tryDeleteTopic());
    }

    @Test
    void testTryDeleteTopic_AmazonSNSClientThrowsTagPolicyException() {
        // Setup
        when(mockAmazonSNSClient.deleteTopic(new DeleteTopicRequest("topicArn"))).thenThrow(TagPolicyException.class);

        // Run the test
        assertThrows(TagPolicyException.class, () -> myClassUnderTest.tryDeleteTopic());
    }

    @Test
    void testTryDeleteTopic_AmazonSNSClientThrowsConcurrentAccessException() {
        // Setup
        when(mockAmazonSNSClient.deleteTopic(new DeleteTopicRequest("topicArn")))
                .thenThrow(ConcurrentAccessException.class);

        // Run the test
        assertThrows(ConcurrentAccessException.class, () -> myClassUnderTest.tryDeleteTopic());
    }

    @Test
    void testTryDeleteTopic1() {
        // Setup
        when(mockAmazonSNSClient.deleteTopic("topicArn")).thenReturn(new DeleteTopicResult());

        // Run the test
        myClassUnderTest.tryDeleteTopic1();

        // Verify the results
    }

    @Test
    void testTryGetEndpointAttributes() {
        // Setup
        // Configure AmazonSNSClient.getEndpointAttributes(...).
        final GetEndpointAttributesResult getEndpointAttributesResult = new GetEndpointAttributesResult();
        final GetEndpointAttributesRequest request = new GetEndpointAttributesRequest();
        request.setEndpointArn("endpointArn");
        when(mockAmazonSNSClient.getEndpointAttributes(request)).thenReturn(getEndpointAttributesResult);

        // Run the test
        myClassUnderTest.tryGetEndpointAttributes();

        // Verify the results
    }

    @Test
    void testTryGetEndpointAttributes_AmazonSNSClientThrowsInvalidParameterException() {
        // Setup
        // Configure AmazonSNSClient.getEndpointAttributes(...).
        final GetEndpointAttributesRequest request = new GetEndpointAttributesRequest();
        request.setEndpointArn("endpointArn");
        when(mockAmazonSNSClient.getEndpointAttributes(request)).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryGetEndpointAttributes());
    }

    @Test
    void testTryGetEndpointAttributes_AmazonSNSClientThrowsInternalErrorException() {
        // Setup
        // Configure AmazonSNSClient.getEndpointAttributes(...).
        final GetEndpointAttributesRequest request = new GetEndpointAttributesRequest();
        request.setEndpointArn("endpointArn");
        when(mockAmazonSNSClient.getEndpointAttributes(request)).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryGetEndpointAttributes());
    }

    @Test
    void testTryGetEndpointAttributes_AmazonSNSClientThrowsAuthorizationErrorException() {
        // Setup
        // Configure AmazonSNSClient.getEndpointAttributes(...).
        final GetEndpointAttributesRequest request = new GetEndpointAttributesRequest();
        request.setEndpointArn("endpointArn");
        when(mockAmazonSNSClient.getEndpointAttributes(request)).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryGetEndpointAttributes());
    }

    @Test
    void testTryGetEndpointAttributes_AmazonSNSClientThrowsNotFoundException() {
        // Setup
        // Configure AmazonSNSClient.getEndpointAttributes(...).
        final GetEndpointAttributesRequest request = new GetEndpointAttributesRequest();
        request.setEndpointArn("endpointArn");
        when(mockAmazonSNSClient.getEndpointAttributes(request)).thenThrow(NotFoundException.class);

        // Run the test
        assertThrows(NotFoundException.class, () -> myClassUnderTest.tryGetEndpointAttributes());
    }

    @Test
    void testTryGetPlatformApplicationAttributes() {
        // Setup
        // Configure AmazonSNSClient.getPlatformApplicationAttributes(...).
        final GetPlatformApplicationAttributesResult getPlatformApplicationAttributesResult = new GetPlatformApplicationAttributesResult();
        final GetPlatformApplicationAttributesRequest request = new GetPlatformApplicationAttributesRequest();
        request.setPlatformApplicationArn("platformApplicationArn");
        when(mockAmazonSNSClient.getPlatformApplicationAttributes(request))
                .thenReturn(getPlatformApplicationAttributesResult);

        // Run the test
        myClassUnderTest.tryGetPlatformApplicationAttributes();

        // Verify the results
    }

    @Test
    void testTryGetPlatformApplicationAttributes_AmazonSNSClientThrowsInvalidParameterException() {
        // Setup
        // Configure AmazonSNSClient.getPlatformApplicationAttributes(...).
        final GetPlatformApplicationAttributesRequest request = new GetPlatformApplicationAttributesRequest();
        request.setPlatformApplicationArn("platformApplicationArn");
        when(mockAmazonSNSClient.getPlatformApplicationAttributes(request)).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryGetPlatformApplicationAttributes());
    }

    @Test
    void testTryGetPlatformApplicationAttributes_AmazonSNSClientThrowsInternalErrorException() {
        // Setup
        // Configure AmazonSNSClient.getPlatformApplicationAttributes(...).
        final GetPlatformApplicationAttributesRequest request = new GetPlatformApplicationAttributesRequest();
        request.setPlatformApplicationArn("platformApplicationArn");
        when(mockAmazonSNSClient.getPlatformApplicationAttributes(request)).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryGetPlatformApplicationAttributes());
    }

    @Test
    void testTryGetPlatformApplicationAttributes_AmazonSNSClientThrowsAuthorizationErrorException() {
        // Setup
        // Configure AmazonSNSClient.getPlatformApplicationAttributes(...).
        final GetPlatformApplicationAttributesRequest request = new GetPlatformApplicationAttributesRequest();
        request.setPlatformApplicationArn("platformApplicationArn");
        when(mockAmazonSNSClient.getPlatformApplicationAttributes(request))
                .thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryGetPlatformApplicationAttributes());
    }

    @Test
    void testTryGetPlatformApplicationAttributes_AmazonSNSClientThrowsNotFoundException() {
        // Setup
        // Configure AmazonSNSClient.getPlatformApplicationAttributes(...).
        final GetPlatformApplicationAttributesRequest request = new GetPlatformApplicationAttributesRequest();
        request.setPlatformApplicationArn("platformApplicationArn");
        when(mockAmazonSNSClient.getPlatformApplicationAttributes(request)).thenThrow(NotFoundException.class);

        // Run the test
        assertThrows(NotFoundException.class, () -> myClassUnderTest.tryGetPlatformApplicationAttributes());
    }

    @Test
    void testTryGetSMSAttributes() {
        // Setup
        // Configure AmazonSNSClient.getSMSAttributes(...).
        final GetSMSAttributesResult getSMSAttributesResult = new GetSMSAttributesResult();
        final GetSMSAttributesRequest request = new GetSMSAttributesRequest();
        request.setAttributes(List.of("value"));
        when(mockAmazonSNSClient.getSMSAttributes(request)).thenReturn(getSMSAttributesResult);

        // Run the test
        myClassUnderTest.tryGetSMSAttributes();

        // Verify the results
    }

    @Test
    void testTryGetSMSAttributes_AmazonSNSClientThrowsThrottledException() {
        // Setup
        // Configure AmazonSNSClient.getSMSAttributes(...).
        final GetSMSAttributesRequest request = new GetSMSAttributesRequest();
        request.setAttributes(List.of("value"));
        when(mockAmazonSNSClient.getSMSAttributes(request)).thenThrow(ThrottledException.class);

        // Run the test
        assertThrows(ThrottledException.class, () -> myClassUnderTest.tryGetSMSAttributes());
    }

    @Test
    void testTryGetSMSAttributes_AmazonSNSClientThrowsInternalErrorException() {
        // Setup
        // Configure AmazonSNSClient.getSMSAttributes(...).
        final GetSMSAttributesRequest request = new GetSMSAttributesRequest();
        request.setAttributes(List.of("value"));
        when(mockAmazonSNSClient.getSMSAttributes(request)).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryGetSMSAttributes());
    }

    @Test
    void testTryGetSMSAttributes_AmazonSNSClientThrowsAuthorizationErrorException() {
        // Setup
        // Configure AmazonSNSClient.getSMSAttributes(...).
        final GetSMSAttributesRequest request = new GetSMSAttributesRequest();
        request.setAttributes(List.of("value"));
        when(mockAmazonSNSClient.getSMSAttributes(request)).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryGetSMSAttributes());
    }

    @Test
    void testTryGetSMSAttributes_AmazonSNSClientThrowsInvalidParameterException() {
        // Setup
        // Configure AmazonSNSClient.getSMSAttributes(...).
        final GetSMSAttributesRequest request = new GetSMSAttributesRequest();
        request.setAttributes(List.of("value"));
        when(mockAmazonSNSClient.getSMSAttributes(request)).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryGetSMSAttributes());
    }

    @Test
    void testTryGetSubscriptionAttributes() {
        // Setup
        // Configure AmazonSNSClient.getSubscriptionAttributes(...).
        final GetSubscriptionAttributesResult getSubscriptionAttributesResult = new GetSubscriptionAttributesResult();
        when(mockAmazonSNSClient.getSubscriptionAttributes(
                new GetSubscriptionAttributesRequest("subscriptionArn"))).thenReturn(getSubscriptionAttributesResult);

        // Run the test
        myClassUnderTest.tryGetSubscriptionAttributes();

        // Verify the results
    }

    @Test
    void testTryGetSubscriptionAttributes_AmazonSNSClientThrowsInvalidParameterException() {
        // Setup
        when(mockAmazonSNSClient.getSubscriptionAttributes(
                new GetSubscriptionAttributesRequest("subscriptionArn"))).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryGetSubscriptionAttributes());
    }

    @Test
    void testTryGetSubscriptionAttributes_AmazonSNSClientThrowsInternalErrorException() {
        // Setup
        when(mockAmazonSNSClient.getSubscriptionAttributes(
                new GetSubscriptionAttributesRequest("subscriptionArn"))).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryGetSubscriptionAttributes());
    }

    @Test
    void testTryGetSubscriptionAttributes_AmazonSNSClientThrowsNotFoundException() {
        // Setup
        when(mockAmazonSNSClient.getSubscriptionAttributes(
                new GetSubscriptionAttributesRequest("subscriptionArn"))).thenThrow(NotFoundException.class);

        // Run the test
        assertThrows(NotFoundException.class, () -> myClassUnderTest.tryGetSubscriptionAttributes());
    }

    @Test
    void testTryGetSubscriptionAttributes_AmazonSNSClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockAmazonSNSClient.getSubscriptionAttributes(
                new GetSubscriptionAttributesRequest("subscriptionArn"))).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryGetSubscriptionAttributes());
    }

    @Test
    void testTryGetSubscriptionAttributes1() {
        // Setup
        // Configure AmazonSNSClient.getSubscriptionAttributes(...).
        final GetSubscriptionAttributesResult getSubscriptionAttributesResult = new GetSubscriptionAttributesResult();
        when(mockAmazonSNSClient.getSubscriptionAttributes("subscriptionArn"))
                .thenReturn(getSubscriptionAttributesResult);

        // Run the test
        myClassUnderTest.tryGetSubscriptionAttributes1();

        // Verify the results
    }

    @Test
    void testTryGetTopicAttributes() {
        // Setup
        // Configure AmazonSNSClient.getTopicAttributes(...).
        final GetTopicAttributesResult getTopicAttributesResult = new GetTopicAttributesResult();
        when(mockAmazonSNSClient.getTopicAttributes(new GetTopicAttributesRequest("topicArn")))
                .thenReturn(getTopicAttributesResult);

        // Run the test
        myClassUnderTest.tryGetTopicAttributes();

        // Verify the results
    }

    @Test
    void testTryGetTopicAttributes_AmazonSNSClientThrowsInvalidParameterException() {
        // Setup
        when(mockAmazonSNSClient.getTopicAttributes(new GetTopicAttributesRequest("topicArn")))
                .thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryGetTopicAttributes());
    }

    @Test
    void testTryGetTopicAttributes_AmazonSNSClientThrowsInternalErrorException() {
        // Setup
        when(mockAmazonSNSClient.getTopicAttributes(new GetTopicAttributesRequest("topicArn")))
                .thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryGetTopicAttributes());
    }

    @Test
    void testTryGetTopicAttributes_AmazonSNSClientThrowsNotFoundException() {
        // Setup
        when(mockAmazonSNSClient.getTopicAttributes(new GetTopicAttributesRequest("topicArn")))
                .thenThrow(NotFoundException.class);

        // Run the test
        assertThrows(NotFoundException.class, () -> myClassUnderTest.tryGetTopicAttributes());
    }

    @Test
    void testTryGetTopicAttributes_AmazonSNSClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockAmazonSNSClient.getTopicAttributes(new GetTopicAttributesRequest("topicArn")))
                .thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryGetTopicAttributes());
    }

    @Test
    void testTryGetTopicAttributes_AmazonSNSClientThrowsInvalidSecurityException() {
        // Setup
        when(mockAmazonSNSClient.getTopicAttributes(new GetTopicAttributesRequest("topicArn")))
                .thenThrow(InvalidSecurityException.class);

        // Run the test
        assertThrows(InvalidSecurityException.class, () -> myClassUnderTest.tryGetTopicAttributes());
    }

    @Test
    void testTryGetTopicAttributes1() {
        // Setup
        // Configure AmazonSNSClient.getTopicAttributes(...).
        final GetTopicAttributesResult getTopicAttributesResult = new GetTopicAttributesResult();
        when(mockAmazonSNSClient.getTopicAttributes("topicArn")).thenReturn(getTopicAttributesResult);

        // Run the test
        myClassUnderTest.tryGetTopicAttributes1();

        // Verify the results
    }

    @Test
    void testTryListEndpointsByPlatformApplication() {
        // Setup
        // Configure AmazonSNSClient.listEndpointsByPlatformApplication(...).
        final ListEndpointsByPlatformApplicationResult listEndpointsByPlatformApplicationResult = new ListEndpointsByPlatformApplicationResult();
        final ListEndpointsByPlatformApplicationRequest request = new ListEndpointsByPlatformApplicationRequest();
        request.setPlatformApplicationArn("platformApplicationArn");
        request.setNextToken("nextToken");
        when(mockAmazonSNSClient.listEndpointsByPlatformApplication(request))
                .thenReturn(listEndpointsByPlatformApplicationResult);

        // Run the test
        myClassUnderTest.tryListEndpointsByPlatformApplication();

        // Verify the results
    }

    @Test
    void testTryListEndpointsByPlatformApplication_AmazonSNSClientThrowsInvalidParameterException() {
        // Setup
        // Configure AmazonSNSClient.listEndpointsByPlatformApplication(...).
        final ListEndpointsByPlatformApplicationRequest request = new ListEndpointsByPlatformApplicationRequest();
        request.setPlatformApplicationArn("platformApplicationArn");
        request.setNextToken("nextToken");
        when(mockAmazonSNSClient.listEndpointsByPlatformApplication(request))
                .thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryListEndpointsByPlatformApplication());
    }

    @Test
    void testTryListEndpointsByPlatformApplication_AmazonSNSClientThrowsInternalErrorException() {
        // Setup
        // Configure AmazonSNSClient.listEndpointsByPlatformApplication(...).
        final ListEndpointsByPlatformApplicationRequest request = new ListEndpointsByPlatformApplicationRequest();
        request.setPlatformApplicationArn("platformApplicationArn");
        request.setNextToken("nextToken");
        when(mockAmazonSNSClient.listEndpointsByPlatformApplication(request)).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryListEndpointsByPlatformApplication());
    }

    @Test
    void testTryListEndpointsByPlatformApplication_AmazonSNSClientThrowsAuthorizationErrorException() {
        // Setup
        // Configure AmazonSNSClient.listEndpointsByPlatformApplication(...).
        final ListEndpointsByPlatformApplicationRequest request = new ListEndpointsByPlatformApplicationRequest();
        request.setPlatformApplicationArn("platformApplicationArn");
        request.setNextToken("nextToken");
        when(mockAmazonSNSClient.listEndpointsByPlatformApplication(request))
                .thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryListEndpointsByPlatformApplication());
    }

    @Test
    void testTryListEndpointsByPlatformApplication_AmazonSNSClientThrowsNotFoundException() {
        // Setup
        // Configure AmazonSNSClient.listEndpointsByPlatformApplication(...).
        final ListEndpointsByPlatformApplicationRequest request = new ListEndpointsByPlatformApplicationRequest();
        request.setPlatformApplicationArn("platformApplicationArn");
        request.setNextToken("nextToken");
        when(mockAmazonSNSClient.listEndpointsByPlatformApplication(request)).thenThrow(NotFoundException.class);

        // Run the test
        assertThrows(NotFoundException.class, () -> myClassUnderTest.tryListEndpointsByPlatformApplication());
    }

    @Test
    void testTryListPhoneNumbersOptedOut() {
        // Setup
        // Configure AmazonSNSClient.listPhoneNumbersOptedOut(...).
        final ListPhoneNumbersOptedOutResult listPhoneNumbersOptedOutResult = new ListPhoneNumbersOptedOutResult();
        final ListPhoneNumbersOptedOutRequest request = new ListPhoneNumbersOptedOutRequest();
        request.setNextToken("nextToken");
        when(mockAmazonSNSClient.listPhoneNumbersOptedOut(request)).thenReturn(listPhoneNumbersOptedOutResult);

        // Run the test
        myClassUnderTest.tryListPhoneNumbersOptedOut();

        // Verify the results
    }

    @Test
    void testTryListPhoneNumbersOptedOut_AmazonSNSClientThrowsThrottledException() {
        // Setup
        // Configure AmazonSNSClient.listPhoneNumbersOptedOut(...).
        final ListPhoneNumbersOptedOutRequest request = new ListPhoneNumbersOptedOutRequest();
        request.setNextToken("nextToken");
        when(mockAmazonSNSClient.listPhoneNumbersOptedOut(request)).thenThrow(ThrottledException.class);

        // Run the test
        assertThrows(ThrottledException.class, () -> myClassUnderTest.tryListPhoneNumbersOptedOut());
    }

    @Test
    void testTryListPhoneNumbersOptedOut_AmazonSNSClientThrowsInternalErrorException() {
        // Setup
        // Configure AmazonSNSClient.listPhoneNumbersOptedOut(...).
        final ListPhoneNumbersOptedOutRequest request = new ListPhoneNumbersOptedOutRequest();
        request.setNextToken("nextToken");
        when(mockAmazonSNSClient.listPhoneNumbersOptedOut(request)).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryListPhoneNumbersOptedOut());
    }

    @Test
    void testTryListPhoneNumbersOptedOut_AmazonSNSClientThrowsAuthorizationErrorException() {
        // Setup
        // Configure AmazonSNSClient.listPhoneNumbersOptedOut(...).
        final ListPhoneNumbersOptedOutRequest request = new ListPhoneNumbersOptedOutRequest();
        request.setNextToken("nextToken");
        when(mockAmazonSNSClient.listPhoneNumbersOptedOut(request)).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryListPhoneNumbersOptedOut());
    }

    @Test
    void testTryListPhoneNumbersOptedOut_AmazonSNSClientThrowsInvalidParameterException() {
        // Setup
        // Configure AmazonSNSClient.listPhoneNumbersOptedOut(...).
        final ListPhoneNumbersOptedOutRequest request = new ListPhoneNumbersOptedOutRequest();
        request.setNextToken("nextToken");
        when(mockAmazonSNSClient.listPhoneNumbersOptedOut(request)).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryListPhoneNumbersOptedOut());
    }

    @Test
    void testTryListPlatformApplications() {
        // Setup
        // Configure AmazonSNSClient.listPlatformApplications(...).
        final ListPlatformApplicationsResult listPlatformApplicationsResult = new ListPlatformApplicationsResult();
        final ListPlatformApplicationsRequest request = new ListPlatformApplicationsRequest();
        request.setNextToken("nextToken");
        when(mockAmazonSNSClient.listPlatformApplications(request)).thenReturn(listPlatformApplicationsResult);

        // Run the test
        myClassUnderTest.tryListPlatformApplications();

        // Verify the results
    }

    @Test
    void testTryListPlatformApplications_AmazonSNSClientThrowsInvalidParameterException() {
        // Setup
        // Configure AmazonSNSClient.listPlatformApplications(...).
        final ListPlatformApplicationsRequest request = new ListPlatformApplicationsRequest();
        request.setNextToken("nextToken");
        when(mockAmazonSNSClient.listPlatformApplications(request)).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryListPlatformApplications());
    }

    @Test
    void testTryListPlatformApplications_AmazonSNSClientThrowsInternalErrorException() {
        // Setup
        // Configure AmazonSNSClient.listPlatformApplications(...).
        final ListPlatformApplicationsRequest request = new ListPlatformApplicationsRequest();
        request.setNextToken("nextToken");
        when(mockAmazonSNSClient.listPlatformApplications(request)).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryListPlatformApplications());
    }

    @Test
    void testTryListPlatformApplications_AmazonSNSClientThrowsAuthorizationErrorException() {
        // Setup
        // Configure AmazonSNSClient.listPlatformApplications(...).
        final ListPlatformApplicationsRequest request = new ListPlatformApplicationsRequest();
        request.setNextToken("nextToken");
        when(mockAmazonSNSClient.listPlatformApplications(request)).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryListPlatformApplications());
    }

    @Test
    void testTryListPlatformApplications1() {
        // Setup
        // Configure AmazonSNSClient.listPlatformApplications(...).
        final ListPlatformApplicationsResult listPlatformApplicationsResult = new ListPlatformApplicationsResult();
        when(mockAmazonSNSClient.listPlatformApplications()).thenReturn(listPlatformApplicationsResult);

        // Run the test
        myClassUnderTest.tryListPlatformApplications1();

        // Verify the results
    }

    @Test
    void testTryListSubscriptions() {
        // Setup
        // Configure AmazonSNSClient.listSubscriptions(...).
        final ListSubscriptionsResult listSubscriptionsResult = new ListSubscriptionsResult();
        when(mockAmazonSNSClient.listSubscriptions(new ListSubscriptionsRequest("nextToken")))
                .thenReturn(listSubscriptionsResult);

        // Run the test
        myClassUnderTest.tryListSubscriptions();

        // Verify the results
    }

    @Test
    void testTryListSubscriptions_AmazonSNSClientThrowsInvalidParameterException() {
        // Setup
        when(mockAmazonSNSClient.listSubscriptions(new ListSubscriptionsRequest("nextToken")))
                .thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryListSubscriptions());
    }

    @Test
    void testTryListSubscriptions_AmazonSNSClientThrowsInternalErrorException() {
        // Setup
        when(mockAmazonSNSClient.listSubscriptions(new ListSubscriptionsRequest("nextToken")))
                .thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryListSubscriptions());
    }

    @Test
    void testTryListSubscriptions_AmazonSNSClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockAmazonSNSClient.listSubscriptions(new ListSubscriptionsRequest("nextToken")))
                .thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryListSubscriptions());
    }

    @Test
    void testTryListSubscriptions1() {
        // Setup
        // Configure AmazonSNSClient.listSubscriptions(...).
        final ListSubscriptionsResult listSubscriptionsResult = new ListSubscriptionsResult();
        when(mockAmazonSNSClient.listSubscriptions()).thenReturn(listSubscriptionsResult);

        // Run the test
        myClassUnderTest.tryListSubscriptions1();

        // Verify the results
    }

    @Test
    void testTryListSubscriptions2() {
        // Setup
        // Configure AmazonSNSClient.listSubscriptions(...).
        final ListSubscriptionsResult listSubscriptionsResult = new ListSubscriptionsResult();
        when(mockAmazonSNSClient.listSubscriptions("nextToken")).thenReturn(listSubscriptionsResult);

        // Run the test
        myClassUnderTest.tryListSubscriptions2();

        // Verify the results
    }

    @Test
    void testTryListSubscriptionsByTopic() {
        // Setup
        // Configure AmazonSNSClient.listSubscriptionsByTopic(...).
        final ListSubscriptionsByTopicResult listSubscriptionsByTopicResult = new ListSubscriptionsByTopicResult();
        when(mockAmazonSNSClient.listSubscriptionsByTopic(
                new ListSubscriptionsByTopicRequest("topicArn", "nextToken")))
                .thenReturn(listSubscriptionsByTopicResult);

        // Run the test
        myClassUnderTest.tryListSubscriptionsByTopic();

        // Verify the results
    }

    @Test
    void testTryListSubscriptionsByTopic_AmazonSNSClientThrowsInvalidParameterException() {
        // Setup
        when(mockAmazonSNSClient.listSubscriptionsByTopic(
                new ListSubscriptionsByTopicRequest("topicArn", "nextToken")))
                .thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryListSubscriptionsByTopic());
    }

    @Test
    void testTryListSubscriptionsByTopic_AmazonSNSClientThrowsInternalErrorException() {
        // Setup
        when(mockAmazonSNSClient.listSubscriptionsByTopic(
                new ListSubscriptionsByTopicRequest("topicArn", "nextToken"))).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryListSubscriptionsByTopic());
    }

    @Test
    void testTryListSubscriptionsByTopic_AmazonSNSClientThrowsNotFoundException() {
        // Setup
        when(mockAmazonSNSClient.listSubscriptionsByTopic(
                new ListSubscriptionsByTopicRequest("topicArn", "nextToken"))).thenThrow(NotFoundException.class);

        // Run the test
        assertThrows(NotFoundException.class, () -> myClassUnderTest.tryListSubscriptionsByTopic());
    }

    @Test
    void testTryListSubscriptionsByTopic_AmazonSNSClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockAmazonSNSClient.listSubscriptionsByTopic(
                new ListSubscriptionsByTopicRequest("topicArn", "nextToken")))
                .thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryListSubscriptionsByTopic());
    }

    @Test
    void testTryListSubscriptionsByTopic1() {
        // Setup
        // Configure AmazonSNSClient.listSubscriptionsByTopic(...).
        final ListSubscriptionsByTopicResult listSubscriptionsByTopicResult = new ListSubscriptionsByTopicResult();
        when(mockAmazonSNSClient.listSubscriptionsByTopic("topicArn")).thenReturn(listSubscriptionsByTopicResult);

        // Run the test
        myClassUnderTest.tryListSubscriptionsByTopic1();

        // Verify the results
    }

    @Test
    void testTryListSubscriptionsByTopic2() {
        // Setup
        // Configure AmazonSNSClient.listSubscriptionsByTopic(...).
        final ListSubscriptionsByTopicResult listSubscriptionsByTopicResult = new ListSubscriptionsByTopicResult();
        when(mockAmazonSNSClient.listSubscriptionsByTopic("topicArn", "nextToken"))
                .thenReturn(listSubscriptionsByTopicResult);

        // Run the test
        myClassUnderTest.tryListSubscriptionsByTopic2();

        // Verify the results
    }

    @Test
    void testTryListTagsForResource() {
        // Setup
        // Configure AmazonSNSClient.listTagsForResource(...).
        final ListTagsForResourceResult listTagsForResourceResult = new ListTagsForResourceResult();
        final ListTagsForResourceRequest request = new ListTagsForResourceRequest();
        request.setResourceArn("resourceArn");
        when(mockAmazonSNSClient.listTagsForResource(request)).thenReturn(listTagsForResourceResult);

        // Run the test
        myClassUnderTest.tryListTagsForResource();

        // Verify the results
    }

    @Test
    void testTryListTagsForResource_AmazonSNSClientThrowsResourceNotFoundException() {
        // Setup
        // Configure AmazonSNSClient.listTagsForResource(...).
        final ListTagsForResourceRequest request = new ListTagsForResourceRequest();
        request.setResourceArn("resourceArn");
        when(mockAmazonSNSClient.listTagsForResource(request)).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryListTagsForResource());
    }

    @Test
    void testTryListTagsForResource_AmazonSNSClientThrowsTagPolicyException() {
        // Setup
        // Configure AmazonSNSClient.listTagsForResource(...).
        final ListTagsForResourceRequest request = new ListTagsForResourceRequest();
        request.setResourceArn("resourceArn");
        when(mockAmazonSNSClient.listTagsForResource(request)).thenThrow(TagPolicyException.class);

        // Run the test
        assertThrows(TagPolicyException.class, () -> myClassUnderTest.tryListTagsForResource());
    }

    @Test
    void testTryListTagsForResource_AmazonSNSClientThrowsInvalidParameterException() {
        // Setup
        // Configure AmazonSNSClient.listTagsForResource(...).
        final ListTagsForResourceRequest request = new ListTagsForResourceRequest();
        request.setResourceArn("resourceArn");
        when(mockAmazonSNSClient.listTagsForResource(request)).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryListTagsForResource());
    }

    @Test
    void testTryListTagsForResource_AmazonSNSClientThrowsAuthorizationErrorException() {
        // Setup
        // Configure AmazonSNSClient.listTagsForResource(...).
        final ListTagsForResourceRequest request = new ListTagsForResourceRequest();
        request.setResourceArn("resourceArn");
        when(mockAmazonSNSClient.listTagsForResource(request)).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryListTagsForResource());
    }

    @Test
    void testTryListTagsForResource_AmazonSNSClientThrowsConcurrentAccessException() {
        // Setup
        // Configure AmazonSNSClient.listTagsForResource(...).
        final ListTagsForResourceRequest request = new ListTagsForResourceRequest();
        request.setResourceArn("resourceArn");
        when(mockAmazonSNSClient.listTagsForResource(request)).thenThrow(ConcurrentAccessException.class);

        // Run the test
        assertThrows(ConcurrentAccessException.class, () -> myClassUnderTest.tryListTagsForResource());
    }

    @Test
    void testTryListTopics() {
        // Setup
        // Configure AmazonSNSClient.listTopics(...).
        final ListTopicsResult listTopicsResult = new ListTopicsResult();
        when(mockAmazonSNSClient.listTopics(new ListTopicsRequest("nextToken"))).thenReturn(listTopicsResult);

        // Run the test
        myClassUnderTest.tryListTopics();

        // Verify the results
    }

    @Test
    void testTryListTopics_AmazonSNSClientThrowsInvalidParameterException() {
        // Setup
        when(mockAmazonSNSClient.listTopics(new ListTopicsRequest("nextToken")))
                .thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryListTopics());
    }

    @Test
    void testTryListTopics_AmazonSNSClientThrowsInternalErrorException() {
        // Setup
        when(mockAmazonSNSClient.listTopics(new ListTopicsRequest("nextToken")))
                .thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryListTopics());
    }

    @Test
    void testTryListTopics_AmazonSNSClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockAmazonSNSClient.listTopics(new ListTopicsRequest("nextToken")))
                .thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryListTopics());
    }

    @Test
    void testTryListTopics1() {
        // Setup
        // Configure AmazonSNSClient.listTopics(...).
        final ListTopicsResult listTopicsResult = new ListTopicsResult();
        when(mockAmazonSNSClient.listTopics()).thenReturn(listTopicsResult);

        // Run the test
        myClassUnderTest.tryListTopics1();

        // Verify the results
    }

    @Test
    void testTryListTopics2() {
        // Setup
        // Configure AmazonSNSClient.listTopics(...).
        final ListTopicsResult listTopicsResult = new ListTopicsResult();
        when(mockAmazonSNSClient.listTopics("nextToken")).thenReturn(listTopicsResult);

        // Run the test
        myClassUnderTest.tryListTopics2();

        // Verify the results
    }

    @Test
    void testTryOptInPhoneNumber() {
        // Setup
        // Configure AmazonSNSClient.optInPhoneNumber(...).
        final OptInPhoneNumberRequest request = new OptInPhoneNumberRequest();
        request.setPhoneNumber("phoneNumber");
        when(mockAmazonSNSClient.optInPhoneNumber(request)).thenReturn(new OptInPhoneNumberResult());

        // Run the test
        myClassUnderTest.tryOptInPhoneNumber();

        // Verify the results
    }

    @Test
    void testTryOptInPhoneNumber_AmazonSNSClientThrowsThrottledException() {
        // Setup
        // Configure AmazonSNSClient.optInPhoneNumber(...).
        final OptInPhoneNumberRequest request = new OptInPhoneNumberRequest();
        request.setPhoneNumber("phoneNumber");
        when(mockAmazonSNSClient.optInPhoneNumber(request)).thenThrow(ThrottledException.class);

        // Run the test
        assertThrows(ThrottledException.class, () -> myClassUnderTest.tryOptInPhoneNumber());
    }

    @Test
    void testTryOptInPhoneNumber_AmazonSNSClientThrowsInternalErrorException() {
        // Setup
        // Configure AmazonSNSClient.optInPhoneNumber(...).
        final OptInPhoneNumberRequest request = new OptInPhoneNumberRequest();
        request.setPhoneNumber("phoneNumber");
        when(mockAmazonSNSClient.optInPhoneNumber(request)).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryOptInPhoneNumber());
    }

    @Test
    void testTryOptInPhoneNumber_AmazonSNSClientThrowsAuthorizationErrorException() {
        // Setup
        // Configure AmazonSNSClient.optInPhoneNumber(...).
        final OptInPhoneNumberRequest request = new OptInPhoneNumberRequest();
        request.setPhoneNumber("phoneNumber");
        when(mockAmazonSNSClient.optInPhoneNumber(request)).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryOptInPhoneNumber());
    }

    @Test
    void testTryOptInPhoneNumber_AmazonSNSClientThrowsInvalidParameterException() {
        // Setup
        // Configure AmazonSNSClient.optInPhoneNumber(...).
        final OptInPhoneNumberRequest request = new OptInPhoneNumberRequest();
        request.setPhoneNumber("phoneNumber");
        when(mockAmazonSNSClient.optInPhoneNumber(request)).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryOptInPhoneNumber());
    }

    @Test
    void testTryPublish() {
        // Setup
        // Configure AmazonSNSClient.publish(...).
        final PublishResult publishResult = new PublishResult();
        when(mockAmazonSNSClient.publish(new PublishRequest("topicArn", "message", "subject")))
                .thenReturn(publishResult);

        // Run the test
        myClassUnderTest.tryPublish();

        // Verify the results
    }

    @Test
    void testTryPublish_AmazonSNSClientThrowsInvalidParameterException() {
        // Setup
        when(mockAmazonSNSClient.publish(new PublishRequest("topicArn", "message", "subject")))
                .thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryPublish());
    }

    @Test
    void testTryPublish_AmazonSNSClientThrowsInvalidParameterValueException() {
        // Setup
        when(mockAmazonSNSClient.publish(new PublishRequest("topicArn", "message", "subject")))
                .thenThrow(InvalidParameterValueException.class);

        // Run the test
        assertThrows(InvalidParameterValueException.class, () -> myClassUnderTest.tryPublish());
    }

    @Test
    void testTryPublish_AmazonSNSClientThrowsInternalErrorException() {
        // Setup
        when(mockAmazonSNSClient.publish(new PublishRequest("topicArn", "message", "subject")))
                .thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryPublish());
    }

    @Test
    void testTryPublish_AmazonSNSClientThrowsNotFoundException() {
        // Setup
        when(mockAmazonSNSClient.publish(new PublishRequest("topicArn", "message", "subject")))
                .thenThrow(NotFoundException.class);

        // Run the test
        assertThrows(NotFoundException.class, () -> myClassUnderTest.tryPublish());
    }

    @Test
    void testTryPublish_AmazonSNSClientThrowsEndpointDisabledException() {
        // Setup
        when(mockAmazonSNSClient.publish(new PublishRequest("topicArn", "message", "subject")))
                .thenThrow(EndpointDisabledException.class);

        // Run the test
        assertThrows(EndpointDisabledException.class, () -> myClassUnderTest.tryPublish());
    }

    @Test
    void testTryPublish_AmazonSNSClientThrowsPlatformApplicationDisabledException() {
        // Setup
        when(mockAmazonSNSClient.publish(new PublishRequest("topicArn", "message", "subject")))
                .thenThrow(PlatformApplicationDisabledException.class);

        // Run the test
        assertThrows(PlatformApplicationDisabledException.class, () -> myClassUnderTest.tryPublish());
    }

    @Test
    void testTryPublish_AmazonSNSClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockAmazonSNSClient.publish(new PublishRequest("topicArn", "message", "subject")))
                .thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryPublish());
    }

    @Test
    void testTryPublish_AmazonSNSClientThrowsKMSDisabledException() {
        // Setup
        when(mockAmazonSNSClient.publish(new PublishRequest("topicArn", "message", "subject")))
                .thenThrow(KMSDisabledException.class);

        // Run the test
        assertThrows(KMSDisabledException.class, () -> myClassUnderTest.tryPublish());
    }

    @Test
    void testTryPublish_AmazonSNSClientThrowsKMSInvalidStateException() {
        // Setup
        when(mockAmazonSNSClient.publish(new PublishRequest("topicArn", "message", "subject")))
                .thenThrow(KMSInvalidStateException.class);

        // Run the test
        assertThrows(KMSInvalidStateException.class, () -> myClassUnderTest.tryPublish());
    }

    @Test
    void testTryPublish_AmazonSNSClientThrowsKMSNotFoundException() {
        // Setup
        when(mockAmazonSNSClient.publish(new PublishRequest("topicArn", "message", "subject")))
                .thenThrow(KMSNotFoundException.class);

        // Run the test
        assertThrows(KMSNotFoundException.class, () -> myClassUnderTest.tryPublish());
    }

    @Test
    void testTryPublish_AmazonSNSClientThrowsKMSOptInRequiredException() {
        // Setup
        when(mockAmazonSNSClient.publish(new PublishRequest("topicArn", "message", "subject")))
                .thenThrow(KMSOptInRequiredException.class);

        // Run the test
        assertThrows(KMSOptInRequiredException.class, () -> myClassUnderTest.tryPublish());
    }

    @Test
    void testTryPublish_AmazonSNSClientThrowsKMSThrottlingException() {
        // Setup
        when(mockAmazonSNSClient.publish(new PublishRequest("topicArn", "message", "subject")))
                .thenThrow(KMSThrottlingException.class);

        // Run the test
        assertThrows(KMSThrottlingException.class, () -> myClassUnderTest.tryPublish());
    }

    @Test
    void testTryPublish_AmazonSNSClientThrowsKMSAccessDeniedException() {
        // Setup
        when(mockAmazonSNSClient.publish(new PublishRequest("topicArn", "message", "subject")))
                .thenThrow(KMSAccessDeniedException.class);

        // Run the test
        assertThrows(KMSAccessDeniedException.class, () -> myClassUnderTest.tryPublish());
    }

    @Test
    void testTryPublish_AmazonSNSClientThrowsInvalidSecurityException() {
        // Setup
        when(mockAmazonSNSClient.publish(new PublishRequest("topicArn", "message", "subject")))
                .thenThrow(InvalidSecurityException.class);

        // Run the test
        assertThrows(InvalidSecurityException.class, () -> myClassUnderTest.tryPublish());
    }

    @Test
    void testTryPublish1() {
        // Setup
        // Configure AmazonSNSClient.publish(...).
        final PublishResult publishResult = new PublishResult();
        when(mockAmazonSNSClient.publish("topicArn", "message")).thenReturn(publishResult);

        // Run the test
        myClassUnderTest.tryPublish1();

        // Verify the results
    }

    @Test
    void testTryPublish2() {
        // Setup
        // Configure AmazonSNSClient.publish(...).
        final PublishResult publishResult = new PublishResult();
        when(mockAmazonSNSClient.publish("topicArn", "message", "subject")).thenReturn(publishResult);

        // Run the test
        myClassUnderTest.tryPublish2();

        // Verify the results
    }

    @Test
    void testTryRemovePermission() {
        // Setup
        when(mockAmazonSNSClient.removePermission(new RemovePermissionRequest("topicArn", "label")))
                .thenReturn(new RemovePermissionResult());

        // Run the test
        myClassUnderTest.tryRemovePermission();

        // Verify the results
    }

    @Test
    void testTryRemovePermission_AmazonSNSClientThrowsInvalidParameterException() {
        // Setup
        when(mockAmazonSNSClient.removePermission(new RemovePermissionRequest("topicArn", "label")))
                .thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryRemovePermission());
    }

    @Test
    void testTryRemovePermission_AmazonSNSClientThrowsInternalErrorException() {
        // Setup
        when(mockAmazonSNSClient.removePermission(new RemovePermissionRequest("topicArn", "label")))
                .thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryRemovePermission());
    }

    @Test
    void testTryRemovePermission_AmazonSNSClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockAmazonSNSClient.removePermission(new RemovePermissionRequest("topicArn", "label")))
                .thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryRemovePermission());
    }

    @Test
    void testTryRemovePermission_AmazonSNSClientThrowsNotFoundException() {
        // Setup
        when(mockAmazonSNSClient.removePermission(new RemovePermissionRequest("topicArn", "label")))
                .thenThrow(NotFoundException.class);

        // Run the test
        assertThrows(NotFoundException.class, () -> myClassUnderTest.tryRemovePermission());
    }

    @Test
    void testTryRemovePermission1() {
        // Setup
        when(mockAmazonSNSClient.removePermission("topicArn", "label")).thenReturn(new RemovePermissionResult());

        // Run the test
        myClassUnderTest.tryRemovePermission1();

        // Verify the results
    }

    @Test
    void testTrySetEndpointAttributes() {
        // Setup
        // Configure AmazonSNSClient.setEndpointAttributes(...).
        final SetEndpointAttributesRequest request = new SetEndpointAttributesRequest();
        when(mockAmazonSNSClient.setEndpointAttributes(request)).thenReturn(new SetEndpointAttributesResult());

        // Run the test
        myClassUnderTest.trySetEndpointAttributes();

        // Verify the results
    }

    @Test
    void testTrySetEndpointAttributes_AmazonSNSClientThrowsInvalidParameterException() {
        // Setup
        // Configure AmazonSNSClient.setEndpointAttributes(...).
        final SetEndpointAttributesRequest request = new SetEndpointAttributesRequest();
        when(mockAmazonSNSClient.setEndpointAttributes(request)).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.trySetEndpointAttributes());
    }

    @Test
    void testTrySetEndpointAttributes_AmazonSNSClientThrowsInternalErrorException() {
        // Setup
        // Configure AmazonSNSClient.setEndpointAttributes(...).
        final SetEndpointAttributesRequest request = new SetEndpointAttributesRequest();
        when(mockAmazonSNSClient.setEndpointAttributes(request)).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.trySetEndpointAttributes());
    }

    @Test
    void testTrySetEndpointAttributes_AmazonSNSClientThrowsAuthorizationErrorException() {
        // Setup
        // Configure AmazonSNSClient.setEndpointAttributes(...).
        final SetEndpointAttributesRequest request = new SetEndpointAttributesRequest();
        when(mockAmazonSNSClient.setEndpointAttributes(request)).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.trySetEndpointAttributes());
    }

    @Test
    void testTrySetEndpointAttributes_AmazonSNSClientThrowsNotFoundException() {
        // Setup
        // Configure AmazonSNSClient.setEndpointAttributes(...).
        final SetEndpointAttributesRequest request = new SetEndpointAttributesRequest();
        when(mockAmazonSNSClient.setEndpointAttributes(request)).thenThrow(NotFoundException.class);

        // Run the test
        assertThrows(NotFoundException.class, () -> myClassUnderTest.trySetEndpointAttributes());
    }

    @Test
    void testTrySetPlatformApplicationAttributes() {
        // Setup
        // Configure AmazonSNSClient.setPlatformApplicationAttributes(...).
        final SetPlatformApplicationAttributesResult setPlatformApplicationAttributesResult = new SetPlatformApplicationAttributesResult();
        final SetPlatformApplicationAttributesRequest request = new SetPlatformApplicationAttributesRequest();
        when(mockAmazonSNSClient.setPlatformApplicationAttributes(request))
                .thenReturn(setPlatformApplicationAttributesResult);

        // Run the test
        myClassUnderTest.trySetPlatformApplicationAttributes();

        // Verify the results
    }

    @Test
    void testTrySetPlatformApplicationAttributes_AmazonSNSClientThrowsInvalidParameterException() {
        // Setup
        // Configure AmazonSNSClient.setPlatformApplicationAttributes(...).
        final SetPlatformApplicationAttributesRequest request = new SetPlatformApplicationAttributesRequest();
        when(mockAmazonSNSClient.setPlatformApplicationAttributes(request)).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.trySetPlatformApplicationAttributes());
    }

    @Test
    void testTrySetPlatformApplicationAttributes_AmazonSNSClientThrowsInternalErrorException() {
        // Setup
        // Configure AmazonSNSClient.setPlatformApplicationAttributes(...).
        final SetPlatformApplicationAttributesRequest request = new SetPlatformApplicationAttributesRequest();
        when(mockAmazonSNSClient.setPlatformApplicationAttributes(request)).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.trySetPlatformApplicationAttributes());
    }

    @Test
    void testTrySetPlatformApplicationAttributes_AmazonSNSClientThrowsAuthorizationErrorException() {
        // Setup
        // Configure AmazonSNSClient.setPlatformApplicationAttributes(...).
        final SetPlatformApplicationAttributesRequest request = new SetPlatformApplicationAttributesRequest();
        when(mockAmazonSNSClient.setPlatformApplicationAttributes(request))
                .thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.trySetPlatformApplicationAttributes());
    }

    @Test
    void testTrySetPlatformApplicationAttributes_AmazonSNSClientThrowsNotFoundException() {
        // Setup
        // Configure AmazonSNSClient.setPlatformApplicationAttributes(...).
        final SetPlatformApplicationAttributesRequest request = new SetPlatformApplicationAttributesRequest();
        when(mockAmazonSNSClient.setPlatformApplicationAttributes(request)).thenThrow(NotFoundException.class);

        // Run the test
        assertThrows(NotFoundException.class, () -> myClassUnderTest.trySetPlatformApplicationAttributes());
    }

    @Test
    void testTrySetSMSAttributes() {
        // Setup
        // Configure AmazonSNSClient.setSMSAttributes(...).
        final SetSMSAttributesRequest request = new SetSMSAttributesRequest();
        when(mockAmazonSNSClient.setSMSAttributes(request)).thenReturn(new SetSMSAttributesResult());

        // Run the test
        myClassUnderTest.trySetSMSAttributes();

        // Verify the results
    }

    @Test
    void testTrySetSMSAttributes_AmazonSNSClientThrowsInvalidParameterException() {
        // Setup
        // Configure AmazonSNSClient.setSMSAttributes(...).
        final SetSMSAttributesRequest request = new SetSMSAttributesRequest();
        when(mockAmazonSNSClient.setSMSAttributes(request)).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.trySetSMSAttributes());
    }

    @Test
    void testTrySetSMSAttributes_AmazonSNSClientThrowsThrottledException() {
        // Setup
        // Configure AmazonSNSClient.setSMSAttributes(...).
        final SetSMSAttributesRequest request = new SetSMSAttributesRequest();
        when(mockAmazonSNSClient.setSMSAttributes(request)).thenThrow(ThrottledException.class);

        // Run the test
        assertThrows(ThrottledException.class, () -> myClassUnderTest.trySetSMSAttributes());
    }

    @Test
    void testTrySetSMSAttributes_AmazonSNSClientThrowsInternalErrorException() {
        // Setup
        // Configure AmazonSNSClient.setSMSAttributes(...).
        final SetSMSAttributesRequest request = new SetSMSAttributesRequest();
        when(mockAmazonSNSClient.setSMSAttributes(request)).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.trySetSMSAttributes());
    }

    @Test
    void testTrySetSMSAttributes_AmazonSNSClientThrowsAuthorizationErrorException() {
        // Setup
        // Configure AmazonSNSClient.setSMSAttributes(...).
        final SetSMSAttributesRequest request = new SetSMSAttributesRequest();
        when(mockAmazonSNSClient.setSMSAttributes(request)).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.trySetSMSAttributes());
    }

    @Test
    void testTrySetSubscriptionAttributes() {
        // Setup
        when(mockAmazonSNSClient.setSubscriptionAttributes(
                new SetSubscriptionAttributesRequest("subscriptionArn", "attributeName", "attributeValue")))
                .thenReturn(new SetSubscriptionAttributesResult());

        // Run the test
        myClassUnderTest.trySetSubscriptionAttributes();

        // Verify the results
    }

    @Test
    void testTrySetSubscriptionAttributes_AmazonSNSClientThrowsInvalidParameterException() {
        // Setup
        when(mockAmazonSNSClient.setSubscriptionAttributes(
                new SetSubscriptionAttributesRequest("subscriptionArn", "attributeName", "attributeValue")))
                .thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.trySetSubscriptionAttributes());
    }

    @Test
    void testTrySetSubscriptionAttributes_AmazonSNSClientThrowsFilterPolicyLimitExceededException() {
        // Setup
        when(mockAmazonSNSClient.setSubscriptionAttributes(
                new SetSubscriptionAttributesRequest("subscriptionArn", "attributeName", "attributeValue")))
                .thenThrow(FilterPolicyLimitExceededException.class);

        // Run the test
        assertThrows(FilterPolicyLimitExceededException.class, () -> myClassUnderTest.trySetSubscriptionAttributes());
    }

    @Test
    void testTrySetSubscriptionAttributes_AmazonSNSClientThrowsInternalErrorException() {
        // Setup
        when(mockAmazonSNSClient.setSubscriptionAttributes(
                new SetSubscriptionAttributesRequest("subscriptionArn", "attributeName", "attributeValue")))
                .thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.trySetSubscriptionAttributes());
    }

    @Test
    void testTrySetSubscriptionAttributes_AmazonSNSClientThrowsNotFoundException() {
        // Setup
        when(mockAmazonSNSClient.setSubscriptionAttributes(
                new SetSubscriptionAttributesRequest("subscriptionArn", "attributeName", "attributeValue")))
                .thenThrow(NotFoundException.class);

        // Run the test
        assertThrows(NotFoundException.class, () -> myClassUnderTest.trySetSubscriptionAttributes());
    }

    @Test
    void testTrySetSubscriptionAttributes_AmazonSNSClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockAmazonSNSClient.setSubscriptionAttributes(
                new SetSubscriptionAttributesRequest("subscriptionArn", "attributeName", "attributeValue")))
                .thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.trySetSubscriptionAttributes());
    }

    @Test
    void testTrySetSubscriptionAttributes1() {
        // Setup
        when(mockAmazonSNSClient.setSubscriptionAttributes("subscriptionArn", "attributeName",
                "attributeValue")).thenReturn(new SetSubscriptionAttributesResult());

        // Run the test
        myClassUnderTest.trySetSubscriptionAttributes1();

        // Verify the results
    }

    @Test
    void testTrySetTopicAttributes() {
        // Setup
        when(mockAmazonSNSClient.setTopicAttributes(
                new SetTopicAttributesRequest("topicArn", "attributeName", "attributeValue")))
                .thenReturn(new SetTopicAttributesResult());

        // Run the test
        myClassUnderTest.trySetTopicAttributes();

        // Verify the results
    }

    @Test
    void testTrySetTopicAttributes_AmazonSNSClientThrowsInvalidParameterException() {
        // Setup
        when(mockAmazonSNSClient.setTopicAttributes(
                new SetTopicAttributesRequest("topicArn", "attributeName", "attributeValue")))
                .thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.trySetTopicAttributes());
    }

    @Test
    void testTrySetTopicAttributes_AmazonSNSClientThrowsInternalErrorException() {
        // Setup
        when(mockAmazonSNSClient.setTopicAttributes(
                new SetTopicAttributesRequest("topicArn", "attributeName", "attributeValue")))
                .thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.trySetTopicAttributes());
    }

    @Test
    void testTrySetTopicAttributes_AmazonSNSClientThrowsNotFoundException() {
        // Setup
        when(mockAmazonSNSClient.setTopicAttributes(
                new SetTopicAttributesRequest("topicArn", "attributeName", "attributeValue")))
                .thenThrow(NotFoundException.class);

        // Run the test
        assertThrows(NotFoundException.class, () -> myClassUnderTest.trySetTopicAttributes());
    }

    @Test
    void testTrySetTopicAttributes_AmazonSNSClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockAmazonSNSClient.setTopicAttributes(
                new SetTopicAttributesRequest("topicArn", "attributeName", "attributeValue")))
                .thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.trySetTopicAttributes());
    }

    @Test
    void testTrySetTopicAttributes_AmazonSNSClientThrowsInvalidSecurityException() {
        // Setup
        when(mockAmazonSNSClient.setTopicAttributes(
                new SetTopicAttributesRequest("topicArn", "attributeName", "attributeValue")))
                .thenThrow(InvalidSecurityException.class);

        // Run the test
        assertThrows(InvalidSecurityException.class, () -> myClassUnderTest.trySetTopicAttributes());
    }

    @Test
    void testTrySetTopicAttributes1() {
        // Setup
        when(mockAmazonSNSClient.setTopicAttributes("topicArn", "attributeName", "attributeValue"))
                .thenReturn(new SetTopicAttributesResult());

        // Run the test
        myClassUnderTest.trySetTopicAttributes1();

        // Verify the results
    }

    @Test
    void testTrySubscribe() {
        // Setup
        // Configure AmazonSNSClient.subscribe(...).
        final SubscribeResult subscribeResult = new SubscribeResult();
        when(mockAmazonSNSClient.subscribe(new SubscribeRequest("topicArn", "protocol", "endpoint")))
                .thenReturn(subscribeResult);

        // Run the test
        myClassUnderTest.trySubscribe();

        // Verify the results
    }

    @Test
    void testTrySubscribe_AmazonSNSClientThrowsSubscriptionLimitExceededException() {
        // Setup
        when(mockAmazonSNSClient.subscribe(new SubscribeRequest("topicArn", "protocol", "endpoint")))
                .thenThrow(SubscriptionLimitExceededException.class);

        // Run the test
        assertThrows(SubscriptionLimitExceededException.class, () -> myClassUnderTest.trySubscribe());
    }

    @Test
    void testTrySubscribe_AmazonSNSClientThrowsFilterPolicyLimitExceededException() {
        // Setup
        when(mockAmazonSNSClient.subscribe(new SubscribeRequest("topicArn", "protocol", "endpoint")))
                .thenThrow(FilterPolicyLimitExceededException.class);

        // Run the test
        assertThrows(FilterPolicyLimitExceededException.class, () -> myClassUnderTest.trySubscribe());
    }

    @Test
    void testTrySubscribe_AmazonSNSClientThrowsInvalidParameterException() {
        // Setup
        when(mockAmazonSNSClient.subscribe(new SubscribeRequest("topicArn", "protocol", "endpoint")))
                .thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.trySubscribe());
    }

    @Test
    void testTrySubscribe_AmazonSNSClientThrowsInternalErrorException() {
        // Setup
        when(mockAmazonSNSClient.subscribe(new SubscribeRequest("topicArn", "protocol", "endpoint")))
                .thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.trySubscribe());
    }

    @Test
    void testTrySubscribe_AmazonSNSClientThrowsNotFoundException() {
        // Setup
        when(mockAmazonSNSClient.subscribe(new SubscribeRequest("topicArn", "protocol", "endpoint")))
                .thenThrow(NotFoundException.class);

        // Run the test
        assertThrows(NotFoundException.class, () -> myClassUnderTest.trySubscribe());
    }

    @Test
    void testTrySubscribe_AmazonSNSClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockAmazonSNSClient.subscribe(new SubscribeRequest("topicArn", "protocol", "endpoint")))
                .thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.trySubscribe());
    }

    @Test
    void testTrySubscribe_AmazonSNSClientThrowsInvalidSecurityException() {
        // Setup
        when(mockAmazonSNSClient.subscribe(new SubscribeRequest("topicArn", "protocol", "endpoint")))
                .thenThrow(InvalidSecurityException.class);

        // Run the test
        assertThrows(InvalidSecurityException.class, () -> myClassUnderTest.trySubscribe());
    }

    @Test
    void testTrySubscribe1() {
        // Setup
        // Configure AmazonSNSClient.subscribe(...).
        final SubscribeResult subscribeResult = new SubscribeResult();
        when(mockAmazonSNSClient.subscribe("topicArn", "protocol", "endpoint")).thenReturn(subscribeResult);

        // Run the test
        myClassUnderTest.trySubscribe1();

        // Verify the results
    }

    @Test
    void testTryTagResource() {
        // Setup
        // Configure AmazonSNSClient.tagResource(...).
        final TagResourceRequest request = new TagResourceRequest();
        request.setResourceArn("resourceArn");
        final Tag tag = new Tag();
        request.setTags(List.of(tag));
        when(mockAmazonSNSClient.tagResource(request)).thenReturn(new TagResourceResult());

        // Run the test
        myClassUnderTest.tryTagResource();

        // Verify the results
    }

    @Test
    void testTryTagResource_AmazonSNSClientThrowsResourceNotFoundException() {
        // Setup
        // Configure AmazonSNSClient.tagResource(...).
        final TagResourceRequest request = new TagResourceRequest();
        request.setResourceArn("resourceArn");
        final Tag tag = new Tag();
        request.setTags(List.of(tag));
        when(mockAmazonSNSClient.tagResource(request)).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryTagResource());
    }

    @Test
    void testTryTagResource_AmazonSNSClientThrowsTagLimitExceededException() {
        // Setup
        // Configure AmazonSNSClient.tagResource(...).
        final TagResourceRequest request = new TagResourceRequest();
        request.setResourceArn("resourceArn");
        final Tag tag = new Tag();
        request.setTags(List.of(tag));
        when(mockAmazonSNSClient.tagResource(request)).thenThrow(TagLimitExceededException.class);

        // Run the test
        assertThrows(TagLimitExceededException.class, () -> myClassUnderTest.tryTagResource());
    }

    @Test
    void testTryTagResource_AmazonSNSClientThrowsStaleTagException() {
        // Setup
        // Configure AmazonSNSClient.tagResource(...).
        final TagResourceRequest request = new TagResourceRequest();
        request.setResourceArn("resourceArn");
        final Tag tag = new Tag();
        request.setTags(List.of(tag));
        when(mockAmazonSNSClient.tagResource(request)).thenThrow(StaleTagException.class);

        // Run the test
        assertThrows(StaleTagException.class, () -> myClassUnderTest.tryTagResource());
    }

    @Test
    void testTryTagResource_AmazonSNSClientThrowsTagPolicyException() {
        // Setup
        // Configure AmazonSNSClient.tagResource(...).
        final TagResourceRequest request = new TagResourceRequest();
        request.setResourceArn("resourceArn");
        final Tag tag = new Tag();
        request.setTags(List.of(tag));
        when(mockAmazonSNSClient.tagResource(request)).thenThrow(TagPolicyException.class);

        // Run the test
        assertThrows(TagPolicyException.class, () -> myClassUnderTest.tryTagResource());
    }

    @Test
    void testTryTagResource_AmazonSNSClientThrowsInvalidParameterException() {
        // Setup
        // Configure AmazonSNSClient.tagResource(...).
        final TagResourceRequest request = new TagResourceRequest();
        request.setResourceArn("resourceArn");
        final Tag tag = new Tag();
        request.setTags(List.of(tag));
        when(mockAmazonSNSClient.tagResource(request)).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryTagResource());
    }

    @Test
    void testTryTagResource_AmazonSNSClientThrowsAuthorizationErrorException() {
        // Setup
        // Configure AmazonSNSClient.tagResource(...).
        final TagResourceRequest request = new TagResourceRequest();
        request.setResourceArn("resourceArn");
        final Tag tag = new Tag();
        request.setTags(List.of(tag));
        when(mockAmazonSNSClient.tagResource(request)).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryTagResource());
    }

    @Test
    void testTryTagResource_AmazonSNSClientThrowsConcurrentAccessException() {
        // Setup
        // Configure AmazonSNSClient.tagResource(...).
        final TagResourceRequest request = new TagResourceRequest();
        request.setResourceArn("resourceArn");
        final Tag tag = new Tag();
        request.setTags(List.of(tag));
        when(mockAmazonSNSClient.tagResource(request)).thenThrow(ConcurrentAccessException.class);

        // Run the test
        assertThrows(ConcurrentAccessException.class, () -> myClassUnderTest.tryTagResource());
    }

    @Test
    void testTryUnsubscribe() {
        // Setup
        when(mockAmazonSNSClient.unsubscribe(new UnsubscribeRequest("subscriptionArn")))
                .thenReturn(new UnsubscribeResult());

        // Run the test
        myClassUnderTest.tryUnsubscribe();

        // Verify the results
    }

    @Test
    void testTryUnsubscribe_AmazonSNSClientThrowsInvalidParameterException() {
        // Setup
        when(mockAmazonSNSClient.unsubscribe(new UnsubscribeRequest("subscriptionArn")))
                .thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryUnsubscribe());
    }

    @Test
    void testTryUnsubscribe_AmazonSNSClientThrowsInternalErrorException() {
        // Setup
        when(mockAmazonSNSClient.unsubscribe(new UnsubscribeRequest("subscriptionArn")))
                .thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryUnsubscribe());
    }

    @Test
    void testTryUnsubscribe_AmazonSNSClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockAmazonSNSClient.unsubscribe(new UnsubscribeRequest("subscriptionArn")))
                .thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryUnsubscribe());
    }

    @Test
    void testTryUnsubscribe_AmazonSNSClientThrowsNotFoundException() {
        // Setup
        when(mockAmazonSNSClient.unsubscribe(new UnsubscribeRequest("subscriptionArn")))
                .thenThrow(NotFoundException.class);

        // Run the test
        assertThrows(NotFoundException.class, () -> myClassUnderTest.tryUnsubscribe());
    }

    @Test
    void testTryUnsubscribe_AmazonSNSClientThrowsInvalidSecurityException() {
        // Setup
        when(mockAmazonSNSClient.unsubscribe(new UnsubscribeRequest("subscriptionArn")))
                .thenThrow(InvalidSecurityException.class);

        // Run the test
        assertThrows(InvalidSecurityException.class, () -> myClassUnderTest.tryUnsubscribe());
    }

    @Test
    void testTryUnsubscribe1() {
        // Setup
        when(mockAmazonSNSClient.unsubscribe("subscriptionArn")).thenReturn(new UnsubscribeResult());

        // Run the test
        myClassUnderTest.tryUnsubscribe1();

        // Verify the results
    }

    @Test
    void testTryUntagResource() {
        // Setup
        // Configure AmazonSNSClient.untagResource(...).
        final UntagResourceRequest request = new UntagResourceRequest();
        request.setResourceArn("resourceArn");
        request.setTagKeys(List.of("value"));
        when(mockAmazonSNSClient.untagResource(request)).thenReturn(new UntagResourceResult());

        // Run the test
        myClassUnderTest.tryUntagResource();

        // Verify the results
    }

    @Test
    void testTryUntagResource_AmazonSNSClientThrowsResourceNotFoundException() {
        // Setup
        // Configure AmazonSNSClient.untagResource(...).
        final UntagResourceRequest request = new UntagResourceRequest();
        request.setResourceArn("resourceArn");
        request.setTagKeys(List.of("value"));
        when(mockAmazonSNSClient.untagResource(request)).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryUntagResource());
    }

    @Test
    void testTryUntagResource_AmazonSNSClientThrowsTagLimitExceededException() {
        // Setup
        // Configure AmazonSNSClient.untagResource(...).
        final UntagResourceRequest request = new UntagResourceRequest();
        request.setResourceArn("resourceArn");
        request.setTagKeys(List.of("value"));
        when(mockAmazonSNSClient.untagResource(request)).thenThrow(TagLimitExceededException.class);

        // Run the test
        assertThrows(TagLimitExceededException.class, () -> myClassUnderTest.tryUntagResource());
    }

    @Test
    void testTryUntagResource_AmazonSNSClientThrowsStaleTagException() {
        // Setup
        // Configure AmazonSNSClient.untagResource(...).
        final UntagResourceRequest request = new UntagResourceRequest();
        request.setResourceArn("resourceArn");
        request.setTagKeys(List.of("value"));
        when(mockAmazonSNSClient.untagResource(request)).thenThrow(StaleTagException.class);

        // Run the test
        assertThrows(StaleTagException.class, () -> myClassUnderTest.tryUntagResource());
    }

    @Test
    void testTryUntagResource_AmazonSNSClientThrowsTagPolicyException() {
        // Setup
        // Configure AmazonSNSClient.untagResource(...).
        final UntagResourceRequest request = new UntagResourceRequest();
        request.setResourceArn("resourceArn");
        request.setTagKeys(List.of("value"));
        when(mockAmazonSNSClient.untagResource(request)).thenThrow(TagPolicyException.class);

        // Run the test
        assertThrows(TagPolicyException.class, () -> myClassUnderTest.tryUntagResource());
    }

    @Test
    void testTryUntagResource_AmazonSNSClientThrowsInvalidParameterException() {
        // Setup
        // Configure AmazonSNSClient.untagResource(...).
        final UntagResourceRequest request = new UntagResourceRequest();
        request.setResourceArn("resourceArn");
        request.setTagKeys(List.of("value"));
        when(mockAmazonSNSClient.untagResource(request)).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryUntagResource());
    }

    @Test
    void testTryUntagResource_AmazonSNSClientThrowsAuthorizationErrorException() {
        // Setup
        // Configure AmazonSNSClient.untagResource(...).
        final UntagResourceRequest request = new UntagResourceRequest();
        request.setResourceArn("resourceArn");
        request.setTagKeys(List.of("value"));
        when(mockAmazonSNSClient.untagResource(request)).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryUntagResource());
    }

    @Test
    void testTryUntagResource_AmazonSNSClientThrowsConcurrentAccessException() {
        // Setup
        // Configure AmazonSNSClient.untagResource(...).
        final UntagResourceRequest request = new UntagResourceRequest();
        request.setResourceArn("resourceArn");
        request.setTagKeys(List.of("value"));
        when(mockAmazonSNSClient.untagResource(request)).thenThrow(ConcurrentAccessException.class);

        // Run the test
        assertThrows(ConcurrentAccessException.class, () -> myClassUnderTest.tryUntagResource());
    }

    @Test
    void testTryGetCachedResponseMetadata() {
        // Setup
        // Configure AmazonSNSClient.getCachedResponseMetadata(...).
        final ResponseMetadata responseMetadata = new ResponseMetadata(Map.ofEntries(Map.entry("value", "value")));
        when(mockAmazonSNSClient.getCachedResponseMetadata(any(AmazonWebServiceRequest.class)))
                .thenReturn(responseMetadata);

        // Run the test
        myClassUnderTest.tryGetCachedResponseMetadata();

        // Verify the results
    }

    @Test
    void testTryGetCachedResponseMetadata_AmazonSNSClientReturnsNull() {
        // Setup
        when(mockAmazonSNSClient.getCachedResponseMetadata(any(AmazonWebServiceRequest.class))).thenReturn(null);

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
