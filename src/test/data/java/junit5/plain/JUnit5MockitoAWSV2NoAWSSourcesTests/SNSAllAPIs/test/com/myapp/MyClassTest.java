package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.*;
import software.amazon.awssdk.services.sns.paginators.*;

import java.util.HashMap;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private SnsClient mockSnsClient;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockSnsClient);
    }

    @Test
    void testTryAddPermission1() {
        // Setup
        when(mockSnsClient.addPermission(AddPermissionRequest.builder()
                .topicArn("topicArn")
                .label("label")
                .awsAccountIds("awsAccountIds")
                .actionNames("actionNames")
                .build())).thenReturn(AddPermissionResponse.builder().build());

        // Run the test
        myClassUnderTest.tryAddPermission1();

        // Verify the results
    }

    @Test
    void testTryAddPermission1_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.addPermission(AddPermissionRequest.builder()
                .topicArn("topicArn")
                .label("label")
                .awsAccountIds("awsAccountIds")
                .actionNames("actionNames")
                .build())).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryAddPermission1());
    }

    @Test
    void testTryAddPermission1_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.addPermission(AddPermissionRequest.builder()
                .topicArn("topicArn")
                .label("label")
                .awsAccountIds("awsAccountIds")
                .actionNames("actionNames")
                .build())).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryAddPermission1());
    }

    @Test
    void testTryAddPermission1_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.addPermission(AddPermissionRequest.builder()
                .topicArn("topicArn")
                .label("label")
                .awsAccountIds("awsAccountIds")
                .actionNames("actionNames")
                .build())).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryAddPermission1());
    }

    @Test
    void testTryAddPermission1_SnsClientThrowsNotFoundException() {
        // Setup
        when(mockSnsClient.addPermission(AddPermissionRequest.builder()
                .topicArn("topicArn")
                .label("label")
                .awsAccountIds("awsAccountIds")
                .actionNames("actionNames")
                .build())).thenThrow(NotFoundException.class);

        // Run the test
        assertThrows(NotFoundException.class, () -> myClassUnderTest.tryAddPermission1());
    }

    @Test
    void testTryAddPermission1_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.addPermission(AddPermissionRequest.builder()
                .topicArn("topicArn")
                .label("label")
                .awsAccountIds("awsAccountIds")
                .actionNames("actionNames")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryAddPermission1());
    }

    @Test
    void testTryAddPermission1_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.addPermission(AddPermissionRequest.builder()
                .topicArn("topicArn")
                .label("label")
                .awsAccountIds("awsAccountIds")
                .actionNames("actionNames")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryAddPermission1());
    }

    @Test
    void testTryAddPermission1_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.addPermission(AddPermissionRequest.builder()
                .topicArn("topicArn")
                .label("label")
                .awsAccountIds("awsAccountIds")
                .actionNames("actionNames")
                .build())).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryAddPermission1());
    }

    @Test
    void testTryAddPermission2() {
        // Setup
        when(mockSnsClient.addPermission(any(Consumer.class))).thenReturn(AddPermissionResponse.builder().build());

        // Run the test
        myClassUnderTest.tryAddPermission2();

        // Verify the results
    }

    @Test
    void testTryAddPermission2_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.addPermission(any(Consumer.class))).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryAddPermission2());
    }

    @Test
    void testTryAddPermission2_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.addPermission(any(Consumer.class))).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryAddPermission2());
    }

    @Test
    void testTryAddPermission2_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.addPermission(any(Consumer.class))).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryAddPermission2());
    }

    @Test
    void testTryAddPermission2_SnsClientThrowsNotFoundException() {
        // Setup
        when(mockSnsClient.addPermission(any(Consumer.class))).thenThrow(NotFoundException.class);

        // Run the test
        assertThrows(NotFoundException.class, () -> myClassUnderTest.tryAddPermission2());
    }

    @Test
    void testTryAddPermission2_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.addPermission(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryAddPermission2());
    }

    @Test
    void testTryAddPermission2_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.addPermission(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryAddPermission2());
    }

    @Test
    void testTryAddPermission2_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.addPermission(any(Consumer.class))).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryAddPermission2());
    }

    @Test
    void testTryCheckIfPhoneNumberIsOptedOut1() {
        // Setup
        // Configure SnsClient.checkIfPhoneNumberIsOptedOut(...).
        final CheckIfPhoneNumberIsOptedOutResponse checkIfPhoneNumberIsOptedOutResponse = CheckIfPhoneNumberIsOptedOutResponse.builder()
                .isOptedOut(false)
                .build();
        when(mockSnsClient.checkIfPhoneNumberIsOptedOut(CheckIfPhoneNumberIsOptedOutRequest.builder()
                .phoneNumber("phoneNumber")
                .build())).thenReturn(checkIfPhoneNumberIsOptedOutResponse);

        // Run the test
        myClassUnderTest.tryCheckIfPhoneNumberIsOptedOut1();

        // Verify the results
    }

    @Test
    void testTryCheckIfPhoneNumberIsOptedOut1_SnsClientThrowsThrottledException() {
        // Setup
        when(mockSnsClient.checkIfPhoneNumberIsOptedOut(CheckIfPhoneNumberIsOptedOutRequest.builder()
                .phoneNumber("phoneNumber")
                .build())).thenThrow(ThrottledException.class);

        // Run the test
        assertThrows(ThrottledException.class, () -> myClassUnderTest.tryCheckIfPhoneNumberIsOptedOut1());
    }

    @Test
    void testTryCheckIfPhoneNumberIsOptedOut1_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.checkIfPhoneNumberIsOptedOut(CheckIfPhoneNumberIsOptedOutRequest.builder()
                .phoneNumber("phoneNumber")
                .build())).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryCheckIfPhoneNumberIsOptedOut1());
    }

    @Test
    void testTryCheckIfPhoneNumberIsOptedOut1_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.checkIfPhoneNumberIsOptedOut(CheckIfPhoneNumberIsOptedOutRequest.builder()
                .phoneNumber("phoneNumber")
                .build())).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryCheckIfPhoneNumberIsOptedOut1());
    }

    @Test
    void testTryCheckIfPhoneNumberIsOptedOut1_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.checkIfPhoneNumberIsOptedOut(CheckIfPhoneNumberIsOptedOutRequest.builder()
                .phoneNumber("phoneNumber")
                .build())).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryCheckIfPhoneNumberIsOptedOut1());
    }

    @Test
    void testTryCheckIfPhoneNumberIsOptedOut1_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.checkIfPhoneNumberIsOptedOut(CheckIfPhoneNumberIsOptedOutRequest.builder()
                .phoneNumber("phoneNumber")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryCheckIfPhoneNumberIsOptedOut1());
    }

    @Test
    void testTryCheckIfPhoneNumberIsOptedOut1_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.checkIfPhoneNumberIsOptedOut(CheckIfPhoneNumberIsOptedOutRequest.builder()
                .phoneNumber("phoneNumber")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryCheckIfPhoneNumberIsOptedOut1());
    }

    @Test
    void testTryCheckIfPhoneNumberIsOptedOut1_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.checkIfPhoneNumberIsOptedOut(CheckIfPhoneNumberIsOptedOutRequest.builder()
                .phoneNumber("phoneNumber")
                .build())).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryCheckIfPhoneNumberIsOptedOut1());
    }

    @Test
    void testTryCheckIfPhoneNumberIsOptedOut2() {
        // Setup
        // Configure SnsClient.checkIfPhoneNumberIsOptedOut(...).
        final CheckIfPhoneNumberIsOptedOutResponse checkIfPhoneNumberIsOptedOutResponse = CheckIfPhoneNumberIsOptedOutResponse.builder()
                .isOptedOut(false)
                .build();
        when(mockSnsClient.checkIfPhoneNumberIsOptedOut(any(Consumer.class)))
                .thenReturn(checkIfPhoneNumberIsOptedOutResponse);

        // Run the test
        myClassUnderTest.tryCheckIfPhoneNumberIsOptedOut2();

        // Verify the results
    }

    @Test
    void testTryCheckIfPhoneNumberIsOptedOut2_SnsClientThrowsThrottledException() {
        // Setup
        when(mockSnsClient.checkIfPhoneNumberIsOptedOut(any(Consumer.class))).thenThrow(ThrottledException.class);

        // Run the test
        assertThrows(ThrottledException.class, () -> myClassUnderTest.tryCheckIfPhoneNumberIsOptedOut2());
    }

    @Test
    void testTryCheckIfPhoneNumberIsOptedOut2_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.checkIfPhoneNumberIsOptedOut(any(Consumer.class))).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryCheckIfPhoneNumberIsOptedOut2());
    }

    @Test
    void testTryCheckIfPhoneNumberIsOptedOut2_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.checkIfPhoneNumberIsOptedOut(any(Consumer.class)))
                .thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryCheckIfPhoneNumberIsOptedOut2());
    }

    @Test
    void testTryCheckIfPhoneNumberIsOptedOut2_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.checkIfPhoneNumberIsOptedOut(any(Consumer.class)))
                .thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryCheckIfPhoneNumberIsOptedOut2());
    }

    @Test
    void testTryCheckIfPhoneNumberIsOptedOut2_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.checkIfPhoneNumberIsOptedOut(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryCheckIfPhoneNumberIsOptedOut2());
    }

    @Test
    void testTryCheckIfPhoneNumberIsOptedOut2_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.checkIfPhoneNumberIsOptedOut(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryCheckIfPhoneNumberIsOptedOut2());
    }

    @Test
    void testTryCheckIfPhoneNumberIsOptedOut2_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.checkIfPhoneNumberIsOptedOut(any(Consumer.class))).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryCheckIfPhoneNumberIsOptedOut2());
    }

    @Test
    void testTryConfirmSubscription1() {
        // Setup
        // Configure SnsClient.confirmSubscription(...).
        final ConfirmSubscriptionResponse confirmSubscriptionResponse = ConfirmSubscriptionResponse.builder()
                .subscriptionArn("subscriptionArn")
                .build();
        when(mockSnsClient.confirmSubscription(ConfirmSubscriptionRequest.builder()
                .topicArn("topicArn")
                .token("token")
                .authenticateOnUnsubscribe("authenticateOnUnsubscribe")
                .build())).thenReturn(confirmSubscriptionResponse);

        // Run the test
        myClassUnderTest.tryConfirmSubscription1();

        // Verify the results
    }

    @Test
    void testTryConfirmSubscription1_SnsClientThrowsSubscriptionLimitExceededException() {
        // Setup
        when(mockSnsClient.confirmSubscription(ConfirmSubscriptionRequest.builder()
                .topicArn("topicArn")
                .token("token")
                .authenticateOnUnsubscribe("authenticateOnUnsubscribe")
                .build())).thenThrow(SubscriptionLimitExceededException.class);

        // Run the test
        assertThrows(SubscriptionLimitExceededException.class, () -> myClassUnderTest.tryConfirmSubscription1());
    }

    @Test
    void testTryConfirmSubscription1_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.confirmSubscription(ConfirmSubscriptionRequest.builder()
                .topicArn("topicArn")
                .token("token")
                .authenticateOnUnsubscribe("authenticateOnUnsubscribe")
                .build())).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryConfirmSubscription1());
    }

    @Test
    void testTryConfirmSubscription1_SnsClientThrowsNotFoundException() {
        // Setup
        when(mockSnsClient.confirmSubscription(ConfirmSubscriptionRequest.builder()
                .topicArn("topicArn")
                .token("token")
                .authenticateOnUnsubscribe("authenticateOnUnsubscribe")
                .build())).thenThrow(NotFoundException.class);

        // Run the test
        assertThrows(NotFoundException.class, () -> myClassUnderTest.tryConfirmSubscription1());
    }

    @Test
    void testTryConfirmSubscription1_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.confirmSubscription(ConfirmSubscriptionRequest.builder()
                .topicArn("topicArn")
                .token("token")
                .authenticateOnUnsubscribe("authenticateOnUnsubscribe")
                .build())).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryConfirmSubscription1());
    }

    @Test
    void testTryConfirmSubscription1_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.confirmSubscription(ConfirmSubscriptionRequest.builder()
                .topicArn("topicArn")
                .token("token")
                .authenticateOnUnsubscribe("authenticateOnUnsubscribe")
                .build())).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryConfirmSubscription1());
    }

    @Test
    void testTryConfirmSubscription1_SnsClientThrowsFilterPolicyLimitExceededException() {
        // Setup
        when(mockSnsClient.confirmSubscription(ConfirmSubscriptionRequest.builder()
                .topicArn("topicArn")
                .token("token")
                .authenticateOnUnsubscribe("authenticateOnUnsubscribe")
                .build())).thenThrow(FilterPolicyLimitExceededException.class);

        // Run the test
        assertThrows(FilterPolicyLimitExceededException.class, () -> myClassUnderTest.tryConfirmSubscription1());
    }

    @Test
    void testTryConfirmSubscription1_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.confirmSubscription(ConfirmSubscriptionRequest.builder()
                .topicArn("topicArn")
                .token("token")
                .authenticateOnUnsubscribe("authenticateOnUnsubscribe")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryConfirmSubscription1());
    }

    @Test
    void testTryConfirmSubscription1_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.confirmSubscription(ConfirmSubscriptionRequest.builder()
                .topicArn("topicArn")
                .token("token")
                .authenticateOnUnsubscribe("authenticateOnUnsubscribe")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryConfirmSubscription1());
    }

    @Test
    void testTryConfirmSubscription1_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.confirmSubscription(ConfirmSubscriptionRequest.builder()
                .topicArn("topicArn")
                .token("token")
                .authenticateOnUnsubscribe("authenticateOnUnsubscribe")
                .build())).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryConfirmSubscription1());
    }

    @Test
    void testTryConfirmSubscription2() {
        // Setup
        // Configure SnsClient.confirmSubscription(...).
        final ConfirmSubscriptionResponse confirmSubscriptionResponse = ConfirmSubscriptionResponse.builder()
                .subscriptionArn("subscriptionArn")
                .build();
        when(mockSnsClient.confirmSubscription(any(Consumer.class))).thenReturn(confirmSubscriptionResponse);

        // Run the test
        myClassUnderTest.tryConfirmSubscription2();

        // Verify the results
    }

    @Test
    void testTryConfirmSubscription2_SnsClientThrowsSubscriptionLimitExceededException() {
        // Setup
        when(mockSnsClient.confirmSubscription(any(Consumer.class)))
                .thenThrow(SubscriptionLimitExceededException.class);

        // Run the test
        assertThrows(SubscriptionLimitExceededException.class, () -> myClassUnderTest.tryConfirmSubscription2());
    }

    @Test
    void testTryConfirmSubscription2_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.confirmSubscription(any(Consumer.class))).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryConfirmSubscription2());
    }

    @Test
    void testTryConfirmSubscription2_SnsClientThrowsNotFoundException() {
        // Setup
        when(mockSnsClient.confirmSubscription(any(Consumer.class))).thenThrow(NotFoundException.class);

        // Run the test
        assertThrows(NotFoundException.class, () -> myClassUnderTest.tryConfirmSubscription2());
    }

    @Test
    void testTryConfirmSubscription2_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.confirmSubscription(any(Consumer.class))).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryConfirmSubscription2());
    }

    @Test
    void testTryConfirmSubscription2_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.confirmSubscription(any(Consumer.class))).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryConfirmSubscription2());
    }

    @Test
    void testTryConfirmSubscription2_SnsClientThrowsFilterPolicyLimitExceededException() {
        // Setup
        when(mockSnsClient.confirmSubscription(any(Consumer.class)))
                .thenThrow(FilterPolicyLimitExceededException.class);

        // Run the test
        assertThrows(FilterPolicyLimitExceededException.class, () -> myClassUnderTest.tryConfirmSubscription2());
    }

    @Test
    void testTryConfirmSubscription2_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.confirmSubscription(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryConfirmSubscription2());
    }

    @Test
    void testTryConfirmSubscription2_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.confirmSubscription(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryConfirmSubscription2());
    }

    @Test
    void testTryConfirmSubscription2_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.confirmSubscription(any(Consumer.class))).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryConfirmSubscription2());
    }

    @Test
    void testTryCreatePlatformApplication1() {
        // Setup
        // Configure SnsClient.createPlatformApplication(...).
        final CreatePlatformApplicationResponse createPlatformApplicationResponse = CreatePlatformApplicationResponse.builder()
                .platformApplicationArn("platformApplicationArn")
                .build();
        when(mockSnsClient.createPlatformApplication(CreatePlatformApplicationRequest.builder()
                .name("name")
                .platform("platform")
                .attributes(new HashMap<>())
                .build())).thenReturn(createPlatformApplicationResponse);

        // Run the test
        myClassUnderTest.tryCreatePlatformApplication1();

        // Verify the results
    }

    @Test
    void testTryCreatePlatformApplication1_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.createPlatformApplication(CreatePlatformApplicationRequest.builder()
                .name("name")
                .platform("platform")
                .attributes(new HashMap<>())
                .build())).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryCreatePlatformApplication1());
    }

    @Test
    void testTryCreatePlatformApplication1_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.createPlatformApplication(CreatePlatformApplicationRequest.builder()
                .name("name")
                .platform("platform")
                .attributes(new HashMap<>())
                .build())).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryCreatePlatformApplication1());
    }

    @Test
    void testTryCreatePlatformApplication1_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.createPlatformApplication(CreatePlatformApplicationRequest.builder()
                .name("name")
                .platform("platform")
                .attributes(new HashMap<>())
                .build())).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryCreatePlatformApplication1());
    }

    @Test
    void testTryCreatePlatformApplication1_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.createPlatformApplication(CreatePlatformApplicationRequest.builder()
                .name("name")
                .platform("platform")
                .attributes(new HashMap<>())
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryCreatePlatformApplication1());
    }

    @Test
    void testTryCreatePlatformApplication1_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.createPlatformApplication(CreatePlatformApplicationRequest.builder()
                .name("name")
                .platform("platform")
                .attributes(new HashMap<>())
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryCreatePlatformApplication1());
    }

    @Test
    void testTryCreatePlatformApplication1_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.createPlatformApplication(CreatePlatformApplicationRequest.builder()
                .name("name")
                .platform("platform")
                .attributes(new HashMap<>())
                .build())).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryCreatePlatformApplication1());
    }

    @Test
    void testTryCreatePlatformApplication2() {
        // Setup
        // Configure SnsClient.createPlatformApplication(...).
        final CreatePlatformApplicationResponse createPlatformApplicationResponse = CreatePlatformApplicationResponse.builder()
                .platformApplicationArn("platformApplicationArn")
                .build();
        when(mockSnsClient.createPlatformApplication(any(Consumer.class)))
                .thenReturn(createPlatformApplicationResponse);

        // Run the test
        myClassUnderTest.tryCreatePlatformApplication2();

        // Verify the results
    }

    @Test
    void testTryCreatePlatformApplication2_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.createPlatformApplication(any(Consumer.class))).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryCreatePlatformApplication2());
    }

    @Test
    void testTryCreatePlatformApplication2_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.createPlatformApplication(any(Consumer.class))).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryCreatePlatformApplication2());
    }

    @Test
    void testTryCreatePlatformApplication2_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.createPlatformApplication(any(Consumer.class))).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryCreatePlatformApplication2());
    }

    @Test
    void testTryCreatePlatformApplication2_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.createPlatformApplication(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryCreatePlatformApplication2());
    }

    @Test
    void testTryCreatePlatformApplication2_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.createPlatformApplication(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryCreatePlatformApplication2());
    }

    @Test
    void testTryCreatePlatformApplication2_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.createPlatformApplication(any(Consumer.class))).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryCreatePlatformApplication2());
    }

    @Test
    void testTryCreatePlatformEndpoint1() {
        // Setup
        // Configure SnsClient.createPlatformEndpoint(...).
        final CreatePlatformEndpointResponse createPlatformEndpointResponse = CreatePlatformEndpointResponse.builder()
                .endpointArn("endpointArn")
                .build();
        when(mockSnsClient.createPlatformEndpoint(CreatePlatformEndpointRequest.builder()
                .platformApplicationArn("platformApplicationArn")
                .token("token")
                .customUserData("customUserData")
                .attributes(new HashMap<>())
                .build())).thenReturn(createPlatformEndpointResponse);

        // Run the test
        myClassUnderTest.tryCreatePlatformEndpoint1();

        // Verify the results
    }

    @Test
    void testTryCreatePlatformEndpoint1_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.createPlatformEndpoint(CreatePlatformEndpointRequest.builder()
                .platformApplicationArn("platformApplicationArn")
                .token("token")
                .customUserData("customUserData")
                .attributes(new HashMap<>())
                .build())).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryCreatePlatformEndpoint1());
    }

    @Test
    void testTryCreatePlatformEndpoint1_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.createPlatformEndpoint(CreatePlatformEndpointRequest.builder()
                .platformApplicationArn("platformApplicationArn")
                .token("token")
                .customUserData("customUserData")
                .attributes(new HashMap<>())
                .build())).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryCreatePlatformEndpoint1());
    }

    @Test
    void testTryCreatePlatformEndpoint1_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.createPlatformEndpoint(CreatePlatformEndpointRequest.builder()
                .platformApplicationArn("platformApplicationArn")
                .token("token")
                .customUserData("customUserData")
                .attributes(new HashMap<>())
                .build())).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryCreatePlatformEndpoint1());
    }

    @Test
    void testTryCreatePlatformEndpoint1_SnsClientThrowsNotFoundException() {
        // Setup
        when(mockSnsClient.createPlatformEndpoint(CreatePlatformEndpointRequest.builder()
                .platformApplicationArn("platformApplicationArn")
                .token("token")
                .customUserData("customUserData")
                .attributes(new HashMap<>())
                .build())).thenThrow(NotFoundException.class);

        // Run the test
        assertThrows(NotFoundException.class, () -> myClassUnderTest.tryCreatePlatformEndpoint1());
    }

    @Test
    void testTryCreatePlatformEndpoint1_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.createPlatformEndpoint(CreatePlatformEndpointRequest.builder()
                .platformApplicationArn("platformApplicationArn")
                .token("token")
                .customUserData("customUserData")
                .attributes(new HashMap<>())
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryCreatePlatformEndpoint1());
    }

    @Test
    void testTryCreatePlatformEndpoint1_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.createPlatformEndpoint(CreatePlatformEndpointRequest.builder()
                .platformApplicationArn("platformApplicationArn")
                .token("token")
                .customUserData("customUserData")
                .attributes(new HashMap<>())
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryCreatePlatformEndpoint1());
    }

    @Test
    void testTryCreatePlatformEndpoint1_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.createPlatformEndpoint(CreatePlatformEndpointRequest.builder()
                .platformApplicationArn("platformApplicationArn")
                .token("token")
                .customUserData("customUserData")
                .attributes(new HashMap<>())
                .build())).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryCreatePlatformEndpoint1());
    }

    @Test
    void testTryCreatePlatformEndpoint2() {
        // Setup
        // Configure SnsClient.createPlatformEndpoint(...).
        final CreatePlatformEndpointResponse createPlatformEndpointResponse = CreatePlatformEndpointResponse.builder()
                .endpointArn("endpointArn")
                .build();
        when(mockSnsClient.createPlatformEndpoint(any(Consumer.class))).thenReturn(createPlatformEndpointResponse);

        // Run the test
        myClassUnderTest.tryCreatePlatformEndpoint2();

        // Verify the results
    }

    @Test
    void testTryCreatePlatformEndpoint2_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.createPlatformEndpoint(any(Consumer.class))).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryCreatePlatformEndpoint2());
    }

    @Test
    void testTryCreatePlatformEndpoint2_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.createPlatformEndpoint(any(Consumer.class))).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryCreatePlatformEndpoint2());
    }

    @Test
    void testTryCreatePlatformEndpoint2_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.createPlatformEndpoint(any(Consumer.class))).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryCreatePlatformEndpoint2());
    }

    @Test
    void testTryCreatePlatformEndpoint2_SnsClientThrowsNotFoundException() {
        // Setup
        when(mockSnsClient.createPlatformEndpoint(any(Consumer.class))).thenThrow(NotFoundException.class);

        // Run the test
        assertThrows(NotFoundException.class, () -> myClassUnderTest.tryCreatePlatformEndpoint2());
    }

    @Test
    void testTryCreatePlatformEndpoint2_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.createPlatformEndpoint(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryCreatePlatformEndpoint2());
    }

    @Test
    void testTryCreatePlatformEndpoint2_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.createPlatformEndpoint(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryCreatePlatformEndpoint2());
    }

    @Test
    void testTryCreatePlatformEndpoint2_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.createPlatformEndpoint(any(Consumer.class))).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryCreatePlatformEndpoint2());
    }

    @Test
    void testTryCreateTopic1() {
        // Setup
        // Configure SnsClient.createTopic(...).
        final CreateTopicResponse createTopicResponse = CreateTopicResponse.builder()
                .topicArn("topicArn")
                .build();
        when(mockSnsClient.createTopic(CreateTopicRequest.builder()
                .name("name")
                .attributes(new HashMap<>())
                .tags(Tag.builder()
                        .key("key")
                        .value("value")
                        .build())
                .build())).thenReturn(createTopicResponse);

        // Run the test
        myClassUnderTest.tryCreateTopic1();

        // Verify the results
    }

    @Test
    void testTryCreateTopic1_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.createTopic(CreateTopicRequest.builder()
                .name("name")
                .attributes(new HashMap<>())
                .tags(Tag.builder()
                        .key("key")
                        .value("value")
                        .build())
                .build())).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryCreateTopic1());
    }

    @Test
    void testTryCreateTopic1_SnsClientThrowsTopicLimitExceededException() {
        // Setup
        when(mockSnsClient.createTopic(CreateTopicRequest.builder()
                .name("name")
                .attributes(new HashMap<>())
                .tags(Tag.builder()
                        .key("key")
                        .value("value")
                        .build())
                .build())).thenThrow(TopicLimitExceededException.class);

        // Run the test
        assertThrows(TopicLimitExceededException.class, () -> myClassUnderTest.tryCreateTopic1());
    }

    @Test
    void testTryCreateTopic1_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.createTopic(CreateTopicRequest.builder()
                .name("name")
                .attributes(new HashMap<>())
                .tags(Tag.builder()
                        .key("key")
                        .value("value")
                        .build())
                .build())).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryCreateTopic1());
    }

    @Test
    void testTryCreateTopic1_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.createTopic(CreateTopicRequest.builder()
                .name("name")
                .attributes(new HashMap<>())
                .tags(Tag.builder()
                        .key("key")
                        .value("value")
                        .build())
                .build())).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryCreateTopic1());
    }

    @Test
    void testTryCreateTopic1_SnsClientThrowsInvalidSecurityException() {
        // Setup
        when(mockSnsClient.createTopic(CreateTopicRequest.builder()
                .name("name")
                .attributes(new HashMap<>())
                .tags(Tag.builder()
                        .key("key")
                        .value("value")
                        .build())
                .build())).thenThrow(InvalidSecurityException.class);

        // Run the test
        assertThrows(InvalidSecurityException.class, () -> myClassUnderTest.tryCreateTopic1());
    }

    @Test
    void testTryCreateTopic1_SnsClientThrowsTagLimitExceededException() {
        // Setup
        when(mockSnsClient.createTopic(CreateTopicRequest.builder()
                .name("name")
                .attributes(new HashMap<>())
                .tags(Tag.builder()
                        .key("key")
                        .value("value")
                        .build())
                .build())).thenThrow(TagLimitExceededException.class);

        // Run the test
        assertThrows(TagLimitExceededException.class, () -> myClassUnderTest.tryCreateTopic1());
    }

    @Test
    void testTryCreateTopic1_SnsClientThrowsStaleTagException() {
        // Setup
        when(mockSnsClient.createTopic(CreateTopicRequest.builder()
                .name("name")
                .attributes(new HashMap<>())
                .tags(Tag.builder()
                        .key("key")
                        .value("value")
                        .build())
                .build())).thenThrow(StaleTagException.class);

        // Run the test
        assertThrows(StaleTagException.class, () -> myClassUnderTest.tryCreateTopic1());
    }

    @Test
    void testTryCreateTopic1_SnsClientThrowsTagPolicyException() {
        // Setup
        when(mockSnsClient.createTopic(CreateTopicRequest.builder()
                .name("name")
                .attributes(new HashMap<>())
                .tags(Tag.builder()
                        .key("key")
                        .value("value")
                        .build())
                .build())).thenThrow(TagPolicyException.class);

        // Run the test
        assertThrows(TagPolicyException.class, () -> myClassUnderTest.tryCreateTopic1());
    }

    @Test
    void testTryCreateTopic1_SnsClientThrowsConcurrentAccessException() {
        // Setup
        when(mockSnsClient.createTopic(CreateTopicRequest.builder()
                .name("name")
                .attributes(new HashMap<>())
                .tags(Tag.builder()
                        .key("key")
                        .value("value")
                        .build())
                .build())).thenThrow(ConcurrentAccessException.class);

        // Run the test
        assertThrows(ConcurrentAccessException.class, () -> myClassUnderTest.tryCreateTopic1());
    }

    @Test
    void testTryCreateTopic1_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.createTopic(CreateTopicRequest.builder()
                .name("name")
                .attributes(new HashMap<>())
                .tags(Tag.builder()
                        .key("key")
                        .value("value")
                        .build())
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryCreateTopic1());
    }

    @Test
    void testTryCreateTopic1_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.createTopic(CreateTopicRequest.builder()
                .name("name")
                .attributes(new HashMap<>())
                .tags(Tag.builder()
                        .key("key")
                        .value("value")
                        .build())
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryCreateTopic1());
    }

    @Test
    void testTryCreateTopic1_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.createTopic(CreateTopicRequest.builder()
                .name("name")
                .attributes(new HashMap<>())
                .tags(Tag.builder()
                        .key("key")
                        .value("value")
                        .build())
                .build())).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryCreateTopic1());
    }

    @Test
    void testTryCreateTopic2() {
        // Setup
        // Configure SnsClient.createTopic(...).
        final CreateTopicResponse createTopicResponse = CreateTopicResponse.builder()
                .topicArn("topicArn")
                .build();
        when(mockSnsClient.createTopic(any(Consumer.class))).thenReturn(createTopicResponse);

        // Run the test
        myClassUnderTest.tryCreateTopic2();

        // Verify the results
    }

    @Test
    void testTryCreateTopic2_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.createTopic(any(Consumer.class))).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryCreateTopic2());
    }

    @Test
    void testTryCreateTopic2_SnsClientThrowsTopicLimitExceededException() {
        // Setup
        when(mockSnsClient.createTopic(any(Consumer.class))).thenThrow(TopicLimitExceededException.class);

        // Run the test
        assertThrows(TopicLimitExceededException.class, () -> myClassUnderTest.tryCreateTopic2());
    }

    @Test
    void testTryCreateTopic2_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.createTopic(any(Consumer.class))).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryCreateTopic2());
    }

    @Test
    void testTryCreateTopic2_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.createTopic(any(Consumer.class))).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryCreateTopic2());
    }

    @Test
    void testTryCreateTopic2_SnsClientThrowsInvalidSecurityException() {
        // Setup
        when(mockSnsClient.createTopic(any(Consumer.class))).thenThrow(InvalidSecurityException.class);

        // Run the test
        assertThrows(InvalidSecurityException.class, () -> myClassUnderTest.tryCreateTopic2());
    }

    @Test
    void testTryCreateTopic2_SnsClientThrowsTagLimitExceededException() {
        // Setup
        when(mockSnsClient.createTopic(any(Consumer.class))).thenThrow(TagLimitExceededException.class);

        // Run the test
        assertThrows(TagLimitExceededException.class, () -> myClassUnderTest.tryCreateTopic2());
    }

    @Test
    void testTryCreateTopic2_SnsClientThrowsStaleTagException() {
        // Setup
        when(mockSnsClient.createTopic(any(Consumer.class))).thenThrow(StaleTagException.class);

        // Run the test
        assertThrows(StaleTagException.class, () -> myClassUnderTest.tryCreateTopic2());
    }

    @Test
    void testTryCreateTopic2_SnsClientThrowsTagPolicyException() {
        // Setup
        when(mockSnsClient.createTopic(any(Consumer.class))).thenThrow(TagPolicyException.class);

        // Run the test
        assertThrows(TagPolicyException.class, () -> myClassUnderTest.tryCreateTopic2());
    }

    @Test
    void testTryCreateTopic2_SnsClientThrowsConcurrentAccessException() {
        // Setup
        when(mockSnsClient.createTopic(any(Consumer.class))).thenThrow(ConcurrentAccessException.class);

        // Run the test
        assertThrows(ConcurrentAccessException.class, () -> myClassUnderTest.tryCreateTopic2());
    }

    @Test
    void testTryCreateTopic2_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.createTopic(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryCreateTopic2());
    }

    @Test
    void testTryCreateTopic2_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.createTopic(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryCreateTopic2());
    }

    @Test
    void testTryCreateTopic2_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.createTopic(any(Consumer.class))).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryCreateTopic2());
    }

    @Test
    void testTryDeleteEndpoint1() {
        // Setup
        when(mockSnsClient.deleteEndpoint(DeleteEndpointRequest.builder()
                .endpointArn("endpointArn")
                .build())).thenReturn(DeleteEndpointResponse.builder().build());

        // Run the test
        myClassUnderTest.tryDeleteEndpoint1();

        // Verify the results
    }

    @Test
    void testTryDeleteEndpoint1_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.deleteEndpoint(DeleteEndpointRequest.builder()
                .endpointArn("endpointArn")
                .build())).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryDeleteEndpoint1());
    }

    @Test
    void testTryDeleteEndpoint1_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.deleteEndpoint(DeleteEndpointRequest.builder()
                .endpointArn("endpointArn")
                .build())).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryDeleteEndpoint1());
    }

    @Test
    void testTryDeleteEndpoint1_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.deleteEndpoint(DeleteEndpointRequest.builder()
                .endpointArn("endpointArn")
                .build())).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryDeleteEndpoint1());
    }

    @Test
    void testTryDeleteEndpoint1_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.deleteEndpoint(DeleteEndpointRequest.builder()
                .endpointArn("endpointArn")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDeleteEndpoint1());
    }

    @Test
    void testTryDeleteEndpoint1_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.deleteEndpoint(DeleteEndpointRequest.builder()
                .endpointArn("endpointArn")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteEndpoint1());
    }

    @Test
    void testTryDeleteEndpoint1_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.deleteEndpoint(DeleteEndpointRequest.builder()
                .endpointArn("endpointArn")
                .build())).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryDeleteEndpoint1());
    }

    @Test
    void testTryDeleteEndpoint2() {
        // Setup
        when(mockSnsClient.deleteEndpoint(any(Consumer.class))).thenReturn(DeleteEndpointResponse.builder().build());

        // Run the test
        myClassUnderTest.tryDeleteEndpoint2();

        // Verify the results
    }

    @Test
    void testTryDeleteEndpoint2_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.deleteEndpoint(any(Consumer.class))).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryDeleteEndpoint2());
    }

    @Test
    void testTryDeleteEndpoint2_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.deleteEndpoint(any(Consumer.class))).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryDeleteEndpoint2());
    }

    @Test
    void testTryDeleteEndpoint2_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.deleteEndpoint(any(Consumer.class))).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryDeleteEndpoint2());
    }

    @Test
    void testTryDeleteEndpoint2_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.deleteEndpoint(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDeleteEndpoint2());
    }

    @Test
    void testTryDeleteEndpoint2_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.deleteEndpoint(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteEndpoint2());
    }

    @Test
    void testTryDeleteEndpoint2_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.deleteEndpoint(any(Consumer.class))).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryDeleteEndpoint2());
    }

    @Test
    void testTryDeletePlatformApplication1() {
        // Setup
        // Configure SnsClient.deletePlatformApplication(...).
        final DeletePlatformApplicationResponse deletePlatformApplicationResponse = DeletePlatformApplicationResponse.builder().build();
        when(mockSnsClient.deletePlatformApplication(DeletePlatformApplicationRequest.builder()
                .platformApplicationArn("platformApplicationArn")
                .build())).thenReturn(deletePlatformApplicationResponse);

        // Run the test
        myClassUnderTest.tryDeletePlatformApplication1();

        // Verify the results
    }

    @Test
    void testTryDeletePlatformApplication1_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.deletePlatformApplication(DeletePlatformApplicationRequest.builder()
                .platformApplicationArn("platformApplicationArn")
                .build())).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryDeletePlatformApplication1());
    }

    @Test
    void testTryDeletePlatformApplication1_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.deletePlatformApplication(DeletePlatformApplicationRequest.builder()
                .platformApplicationArn("platformApplicationArn")
                .build())).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryDeletePlatformApplication1());
    }

    @Test
    void testTryDeletePlatformApplication1_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.deletePlatformApplication(DeletePlatformApplicationRequest.builder()
                .platformApplicationArn("platformApplicationArn")
                .build())).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryDeletePlatformApplication1());
    }

    @Test
    void testTryDeletePlatformApplication1_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.deletePlatformApplication(DeletePlatformApplicationRequest.builder()
                .platformApplicationArn("platformApplicationArn")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDeletePlatformApplication1());
    }

    @Test
    void testTryDeletePlatformApplication1_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.deletePlatformApplication(DeletePlatformApplicationRequest.builder()
                .platformApplicationArn("platformApplicationArn")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeletePlatformApplication1());
    }

    @Test
    void testTryDeletePlatformApplication1_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.deletePlatformApplication(DeletePlatformApplicationRequest.builder()
                .platformApplicationArn("platformApplicationArn")
                .build())).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryDeletePlatformApplication1());
    }

    @Test
    void testTryDeletePlatformApplication2() {
        // Setup
        // Configure SnsClient.deletePlatformApplication(...).
        final DeletePlatformApplicationResponse deletePlatformApplicationResponse = DeletePlatformApplicationResponse.builder().build();
        when(mockSnsClient.deletePlatformApplication(any(Consumer.class)))
                .thenReturn(deletePlatformApplicationResponse);

        // Run the test
        myClassUnderTest.tryDeletePlatformApplication2();

        // Verify the results
    }

    @Test
    void testTryDeletePlatformApplication2_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.deletePlatformApplication(any(Consumer.class))).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryDeletePlatformApplication2());
    }

    @Test
    void testTryDeletePlatformApplication2_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.deletePlatformApplication(any(Consumer.class))).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryDeletePlatformApplication2());
    }

    @Test
    void testTryDeletePlatformApplication2_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.deletePlatformApplication(any(Consumer.class))).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryDeletePlatformApplication2());
    }

    @Test
    void testTryDeletePlatformApplication2_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.deletePlatformApplication(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDeletePlatformApplication2());
    }

    @Test
    void testTryDeletePlatformApplication2_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.deletePlatformApplication(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeletePlatformApplication2());
    }

    @Test
    void testTryDeletePlatformApplication2_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.deletePlatformApplication(any(Consumer.class))).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryDeletePlatformApplication2());
    }

    @Test
    void testTryDeleteTopic1() {
        // Setup
        when(mockSnsClient.deleteTopic(DeleteTopicRequest.builder()
                .topicArn("topicArn")
                .build())).thenReturn(DeleteTopicResponse.builder().build());

        // Run the test
        myClassUnderTest.tryDeleteTopic1();

        // Verify the results
    }

    @Test
    void testTryDeleteTopic1_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.deleteTopic(DeleteTopicRequest.builder()
                .topicArn("topicArn")
                .build())).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryDeleteTopic1());
    }

    @Test
    void testTryDeleteTopic1_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.deleteTopic(DeleteTopicRequest.builder()
                .topicArn("topicArn")
                .build())).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryDeleteTopic1());
    }

    @Test
    void testTryDeleteTopic1_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.deleteTopic(DeleteTopicRequest.builder()
                .topicArn("topicArn")
                .build())).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryDeleteTopic1());
    }

    @Test
    void testTryDeleteTopic1_SnsClientThrowsNotFoundException() {
        // Setup
        when(mockSnsClient.deleteTopic(DeleteTopicRequest.builder()
                .topicArn("topicArn")
                .build())).thenThrow(NotFoundException.class);

        // Run the test
        assertThrows(NotFoundException.class, () -> myClassUnderTest.tryDeleteTopic1());
    }

    @Test
    void testTryDeleteTopic1_SnsClientThrowsStaleTagException() {
        // Setup
        when(mockSnsClient.deleteTopic(DeleteTopicRequest.builder()
                .topicArn("topicArn")
                .build())).thenThrow(StaleTagException.class);

        // Run the test
        assertThrows(StaleTagException.class, () -> myClassUnderTest.tryDeleteTopic1());
    }

    @Test
    void testTryDeleteTopic1_SnsClientThrowsTagPolicyException() {
        // Setup
        when(mockSnsClient.deleteTopic(DeleteTopicRequest.builder()
                .topicArn("topicArn")
                .build())).thenThrow(TagPolicyException.class);

        // Run the test
        assertThrows(TagPolicyException.class, () -> myClassUnderTest.tryDeleteTopic1());
    }

    @Test
    void testTryDeleteTopic1_SnsClientThrowsConcurrentAccessException() {
        // Setup
        when(mockSnsClient.deleteTopic(DeleteTopicRequest.builder()
                .topicArn("topicArn")
                .build())).thenThrow(ConcurrentAccessException.class);

        // Run the test
        assertThrows(ConcurrentAccessException.class, () -> myClassUnderTest.tryDeleteTopic1());
    }

    @Test
    void testTryDeleteTopic1_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.deleteTopic(DeleteTopicRequest.builder()
                .topicArn("topicArn")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDeleteTopic1());
    }

    @Test
    void testTryDeleteTopic1_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.deleteTopic(DeleteTopicRequest.builder()
                .topicArn("topicArn")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteTopic1());
    }

    @Test
    void testTryDeleteTopic1_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.deleteTopic(DeleteTopicRequest.builder()
                .topicArn("topicArn")
                .build())).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryDeleteTopic1());
    }

    @Test
    void testTryDeleteTopic2() {
        // Setup
        when(mockSnsClient.deleteTopic(any(Consumer.class))).thenReturn(DeleteTopicResponse.builder().build());

        // Run the test
        myClassUnderTest.tryDeleteTopic2();

        // Verify the results
    }

    @Test
    void testTryDeleteTopic2_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.deleteTopic(any(Consumer.class))).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryDeleteTopic2());
    }

    @Test
    void testTryDeleteTopic2_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.deleteTopic(any(Consumer.class))).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryDeleteTopic2());
    }

    @Test
    void testTryDeleteTopic2_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.deleteTopic(any(Consumer.class))).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryDeleteTopic2());
    }

    @Test
    void testTryDeleteTopic2_SnsClientThrowsNotFoundException() {
        // Setup
        when(mockSnsClient.deleteTopic(any(Consumer.class))).thenThrow(NotFoundException.class);

        // Run the test
        assertThrows(NotFoundException.class, () -> myClassUnderTest.tryDeleteTopic2());
    }

    @Test
    void testTryDeleteTopic2_SnsClientThrowsStaleTagException() {
        // Setup
        when(mockSnsClient.deleteTopic(any(Consumer.class))).thenThrow(StaleTagException.class);

        // Run the test
        assertThrows(StaleTagException.class, () -> myClassUnderTest.tryDeleteTopic2());
    }

    @Test
    void testTryDeleteTopic2_SnsClientThrowsTagPolicyException() {
        // Setup
        when(mockSnsClient.deleteTopic(any(Consumer.class))).thenThrow(TagPolicyException.class);

        // Run the test
        assertThrows(TagPolicyException.class, () -> myClassUnderTest.tryDeleteTopic2());
    }

    @Test
    void testTryDeleteTopic2_SnsClientThrowsConcurrentAccessException() {
        // Setup
        when(mockSnsClient.deleteTopic(any(Consumer.class))).thenThrow(ConcurrentAccessException.class);

        // Run the test
        assertThrows(ConcurrentAccessException.class, () -> myClassUnderTest.tryDeleteTopic2());
    }

    @Test
    void testTryDeleteTopic2_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.deleteTopic(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryDeleteTopic2());
    }

    @Test
    void testTryDeleteTopic2_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.deleteTopic(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteTopic2());
    }

    @Test
    void testTryDeleteTopic2_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.deleteTopic(any(Consumer.class))).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryDeleteTopic2());
    }

    @Test
    void testTryGetEndpointAttributes1() {
        // Setup
        // Configure SnsClient.getEndpointAttributes(...).
        final GetEndpointAttributesResponse getEndpointAttributesResponse = GetEndpointAttributesResponse.builder()
                .attributes(new HashMap<>())
                .build();
        when(mockSnsClient.getEndpointAttributes(GetEndpointAttributesRequest.builder()
                .endpointArn("endpointArn")
                .build())).thenReturn(getEndpointAttributesResponse);

        // Run the test
        myClassUnderTest.tryGetEndpointAttributes1();

        // Verify the results
    }

    @Test
    void testTryGetEndpointAttributes1_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.getEndpointAttributes(GetEndpointAttributesRequest.builder()
                .endpointArn("endpointArn")
                .build())).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryGetEndpointAttributes1());
    }

    @Test
    void testTryGetEndpointAttributes1_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.getEndpointAttributes(GetEndpointAttributesRequest.builder()
                .endpointArn("endpointArn")
                .build())).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryGetEndpointAttributes1());
    }

    @Test
    void testTryGetEndpointAttributes1_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.getEndpointAttributes(GetEndpointAttributesRequest.builder()
                .endpointArn("endpointArn")
                .build())).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryGetEndpointAttributes1());
    }

    @Test
    void testTryGetEndpointAttributes1_SnsClientThrowsNotFoundException() {
        // Setup
        when(mockSnsClient.getEndpointAttributes(GetEndpointAttributesRequest.builder()
                .endpointArn("endpointArn")
                .build())).thenThrow(NotFoundException.class);

        // Run the test
        assertThrows(NotFoundException.class, () -> myClassUnderTest.tryGetEndpointAttributes1());
    }

    @Test
    void testTryGetEndpointAttributes1_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.getEndpointAttributes(GetEndpointAttributesRequest.builder()
                .endpointArn("endpointArn")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetEndpointAttributes1());
    }

    @Test
    void testTryGetEndpointAttributes1_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.getEndpointAttributes(GetEndpointAttributesRequest.builder()
                .endpointArn("endpointArn")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetEndpointAttributes1());
    }

    @Test
    void testTryGetEndpointAttributes1_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.getEndpointAttributes(GetEndpointAttributesRequest.builder()
                .endpointArn("endpointArn")
                .build())).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryGetEndpointAttributes1());
    }

    @Test
    void testTryGetEndpointAttributes2() {
        // Setup
        // Configure SnsClient.getEndpointAttributes(...).
        final GetEndpointAttributesResponse getEndpointAttributesResponse = GetEndpointAttributesResponse.builder()
                .attributes(new HashMap<>())
                .build();
        when(mockSnsClient.getEndpointAttributes(any(Consumer.class))).thenReturn(getEndpointAttributesResponse);

        // Run the test
        myClassUnderTest.tryGetEndpointAttributes2();

        // Verify the results
    }

    @Test
    void testTryGetEndpointAttributes2_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.getEndpointAttributes(any(Consumer.class))).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryGetEndpointAttributes2());
    }

    @Test
    void testTryGetEndpointAttributes2_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.getEndpointAttributes(any(Consumer.class))).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryGetEndpointAttributes2());
    }

    @Test
    void testTryGetEndpointAttributes2_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.getEndpointAttributes(any(Consumer.class))).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryGetEndpointAttributes2());
    }

    @Test
    void testTryGetEndpointAttributes2_SnsClientThrowsNotFoundException() {
        // Setup
        when(mockSnsClient.getEndpointAttributes(any(Consumer.class))).thenThrow(NotFoundException.class);

        // Run the test
        assertThrows(NotFoundException.class, () -> myClassUnderTest.tryGetEndpointAttributes2());
    }

    @Test
    void testTryGetEndpointAttributes2_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.getEndpointAttributes(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetEndpointAttributes2());
    }

    @Test
    void testTryGetEndpointAttributes2_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.getEndpointAttributes(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetEndpointAttributes2());
    }

    @Test
    void testTryGetEndpointAttributes2_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.getEndpointAttributes(any(Consumer.class))).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryGetEndpointAttributes2());
    }

    @Test
    void testTryGetPlatformApplicationAttributes1() {
        // Setup
        // Configure SnsClient.getPlatformApplicationAttributes(...).
        final GetPlatformApplicationAttributesResponse getPlatformApplicationAttributesResponse = GetPlatformApplicationAttributesResponse.builder()
                .attributes(new HashMap<>())
                .build();
        when(mockSnsClient.getPlatformApplicationAttributes(GetPlatformApplicationAttributesRequest.builder()
                .platformApplicationArn("platformApplicationArn")
                .build())).thenReturn(getPlatformApplicationAttributesResponse);

        // Run the test
        myClassUnderTest.tryGetPlatformApplicationAttributes1();

        // Verify the results
    }

    @Test
    void testTryGetPlatformApplicationAttributes1_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.getPlatformApplicationAttributes(GetPlatformApplicationAttributesRequest.builder()
                .platformApplicationArn("platformApplicationArn")
                .build())).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryGetPlatformApplicationAttributes1());
    }

    @Test
    void testTryGetPlatformApplicationAttributes1_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.getPlatformApplicationAttributes(GetPlatformApplicationAttributesRequest.builder()
                .platformApplicationArn("platformApplicationArn")
                .build())).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryGetPlatformApplicationAttributes1());
    }

    @Test
    void testTryGetPlatformApplicationAttributes1_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.getPlatformApplicationAttributes(GetPlatformApplicationAttributesRequest.builder()
                .platformApplicationArn("platformApplicationArn")
                .build())).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryGetPlatformApplicationAttributes1());
    }

    @Test
    void testTryGetPlatformApplicationAttributes1_SnsClientThrowsNotFoundException() {
        // Setup
        when(mockSnsClient.getPlatformApplicationAttributes(GetPlatformApplicationAttributesRequest.builder()
                .platformApplicationArn("platformApplicationArn")
                .build())).thenThrow(NotFoundException.class);

        // Run the test
        assertThrows(NotFoundException.class, () -> myClassUnderTest.tryGetPlatformApplicationAttributes1());
    }

    @Test
    void testTryGetPlatformApplicationAttributes1_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.getPlatformApplicationAttributes(GetPlatformApplicationAttributesRequest.builder()
                .platformApplicationArn("platformApplicationArn")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetPlatformApplicationAttributes1());
    }

    @Test
    void testTryGetPlatformApplicationAttributes1_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.getPlatformApplicationAttributes(GetPlatformApplicationAttributesRequest.builder()
                .platformApplicationArn("platformApplicationArn")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetPlatformApplicationAttributes1());
    }

    @Test
    void testTryGetPlatformApplicationAttributes1_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.getPlatformApplicationAttributes(GetPlatformApplicationAttributesRequest.builder()
                .platformApplicationArn("platformApplicationArn")
                .build())).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryGetPlatformApplicationAttributes1());
    }

    @Test
    void testTryGetPlatformApplicationAttributes2() {
        // Setup
        // Configure SnsClient.getPlatformApplicationAttributes(...).
        final GetPlatformApplicationAttributesResponse getPlatformApplicationAttributesResponse = GetPlatformApplicationAttributesResponse.builder()
                .attributes(new HashMap<>())
                .build();
        when(mockSnsClient.getPlatformApplicationAttributes(any(Consumer.class)))
                .thenReturn(getPlatformApplicationAttributesResponse);

        // Run the test
        myClassUnderTest.tryGetPlatformApplicationAttributes2();

        // Verify the results
    }

    @Test
    void testTryGetPlatformApplicationAttributes2_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.getPlatformApplicationAttributes(any(Consumer.class)))
                .thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryGetPlatformApplicationAttributes2());
    }

    @Test
    void testTryGetPlatformApplicationAttributes2_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.getPlatformApplicationAttributes(any(Consumer.class)))
                .thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryGetPlatformApplicationAttributes2());
    }

    @Test
    void testTryGetPlatformApplicationAttributes2_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.getPlatformApplicationAttributes(any(Consumer.class)))
                .thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryGetPlatformApplicationAttributes2());
    }

    @Test
    void testTryGetPlatformApplicationAttributes2_SnsClientThrowsNotFoundException() {
        // Setup
        when(mockSnsClient.getPlatformApplicationAttributes(any(Consumer.class))).thenThrow(NotFoundException.class);

        // Run the test
        assertThrows(NotFoundException.class, () -> myClassUnderTest.tryGetPlatformApplicationAttributes2());
    }

    @Test
    void testTryGetPlatformApplicationAttributes2_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.getPlatformApplicationAttributes(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetPlatformApplicationAttributes2());
    }

    @Test
    void testTryGetPlatformApplicationAttributes2_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.getPlatformApplicationAttributes(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetPlatformApplicationAttributes2());
    }

    @Test
    void testTryGetPlatformApplicationAttributes2_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.getPlatformApplicationAttributes(any(Consumer.class))).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryGetPlatformApplicationAttributes2());
    }

    @Test
    void testTryGetSMSAttributes1() {
        // Setup
        // Configure SnsClient.getSMSAttributes(...).
        final GetSmsAttributesResponse getSmsAttributesResponse = GetSmsAttributesResponse.builder()
                .attributes(new HashMap<>())
                .build();
        when(mockSnsClient.getSMSAttributes()).thenReturn(getSmsAttributesResponse);

        // Run the test
        myClassUnderTest.tryGetSMSAttributes1();

        // Verify the results
    }

    @Test
    void testTryGetSMSAttributes1_SnsClientThrowsThrottledException() {
        // Setup
        when(mockSnsClient.getSMSAttributes()).thenThrow(ThrottledException.class);

        // Run the test
        assertThrows(ThrottledException.class, () -> myClassUnderTest.tryGetSMSAttributes1());
    }

    @Test
    void testTryGetSMSAttributes1_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.getSMSAttributes()).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryGetSMSAttributes1());
    }

    @Test
    void testTryGetSMSAttributes1_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.getSMSAttributes()).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryGetSMSAttributes1());
    }

    @Test
    void testTryGetSMSAttributes1_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.getSMSAttributes()).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryGetSMSAttributes1());
    }

    @Test
    void testTryGetSMSAttributes1_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.getSMSAttributes()).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetSMSAttributes1());
    }

    @Test
    void testTryGetSMSAttributes1_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.getSMSAttributes()).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetSMSAttributes1());
    }

    @Test
    void testTryGetSMSAttributes1_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.getSMSAttributes()).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryGetSMSAttributes1());
    }

    @Test
    void testTryGetSMSAttributes2() {
        // Setup
        // Configure SnsClient.getSMSAttributes(...).
        final GetSmsAttributesResponse getSmsAttributesResponse = GetSmsAttributesResponse.builder()
                .attributes(new HashMap<>())
                .build();
        when(mockSnsClient.getSMSAttributes(GetSmsAttributesRequest.builder()
                .attributes("attributes")
                .build())).thenReturn(getSmsAttributesResponse);

        // Run the test
        myClassUnderTest.tryGetSMSAttributes2();

        // Verify the results
    }

    @Test
    void testTryGetSMSAttributes2_SnsClientThrowsThrottledException() {
        // Setup
        when(mockSnsClient.getSMSAttributes(GetSmsAttributesRequest.builder()
                .attributes("attributes")
                .build())).thenThrow(ThrottledException.class);

        // Run the test
        assertThrows(ThrottledException.class, () -> myClassUnderTest.tryGetSMSAttributes2());
    }

    @Test
    void testTryGetSMSAttributes2_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.getSMSAttributes(GetSmsAttributesRequest.builder()
                .attributes("attributes")
                .build())).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryGetSMSAttributes2());
    }

    @Test
    void testTryGetSMSAttributes2_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.getSMSAttributes(GetSmsAttributesRequest.builder()
                .attributes("attributes")
                .build())).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryGetSMSAttributes2());
    }

    @Test
    void testTryGetSMSAttributes2_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.getSMSAttributes(GetSmsAttributesRequest.builder()
                .attributes("attributes")
                .build())).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryGetSMSAttributes2());
    }

    @Test
    void testTryGetSMSAttributes2_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.getSMSAttributes(GetSmsAttributesRequest.builder()
                .attributes("attributes")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetSMSAttributes2());
    }

    @Test
    void testTryGetSMSAttributes2_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.getSMSAttributes(GetSmsAttributesRequest.builder()
                .attributes("attributes")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetSMSAttributes2());
    }

    @Test
    void testTryGetSMSAttributes2_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.getSMSAttributes(GetSmsAttributesRequest.builder()
                .attributes("attributes")
                .build())).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryGetSMSAttributes2());
    }

    @Test
    void testTryGetSMSAttributes3() {
        // Setup
        // Configure SnsClient.getSMSAttributes(...).
        final GetSmsAttributesResponse getSmsAttributesResponse = GetSmsAttributesResponse.builder()
                .attributes(new HashMap<>())
                .build();
        when(mockSnsClient.getSMSAttributes(any(Consumer.class))).thenReturn(getSmsAttributesResponse);

        // Run the test
        myClassUnderTest.tryGetSMSAttributes3();

        // Verify the results
    }

    @Test
    void testTryGetSMSAttributes3_SnsClientThrowsThrottledException() {
        // Setup
        when(mockSnsClient.getSMSAttributes(any(Consumer.class))).thenThrow(ThrottledException.class);

        // Run the test
        assertThrows(ThrottledException.class, () -> myClassUnderTest.tryGetSMSAttributes3());
    }

    @Test
    void testTryGetSMSAttributes3_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.getSMSAttributes(any(Consumer.class))).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryGetSMSAttributes3());
    }

    @Test
    void testTryGetSMSAttributes3_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.getSMSAttributes(any(Consumer.class))).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryGetSMSAttributes3());
    }

    @Test
    void testTryGetSMSAttributes3_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.getSMSAttributes(any(Consumer.class))).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryGetSMSAttributes3());
    }

    @Test
    void testTryGetSMSAttributes3_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.getSMSAttributes(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetSMSAttributes3());
    }

    @Test
    void testTryGetSMSAttributes3_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.getSMSAttributes(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetSMSAttributes3());
    }

    @Test
    void testTryGetSMSAttributes3_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.getSMSAttributes(any(Consumer.class))).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryGetSMSAttributes3());
    }

    @Test
    void testTryGetSubscriptionAttributes1() {
        // Setup
        // Configure SnsClient.getSubscriptionAttributes(...).
        final GetSubscriptionAttributesResponse getSubscriptionAttributesResponse = GetSubscriptionAttributesResponse.builder()
                .attributes(new HashMap<>())
                .build();
        when(mockSnsClient.getSubscriptionAttributes(GetSubscriptionAttributesRequest.builder()
                .subscriptionArn("subscriptionArn")
                .build())).thenReturn(getSubscriptionAttributesResponse);

        // Run the test
        myClassUnderTest.tryGetSubscriptionAttributes1();

        // Verify the results
    }

    @Test
    void testTryGetSubscriptionAttributes1_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.getSubscriptionAttributes(GetSubscriptionAttributesRequest.builder()
                .subscriptionArn("subscriptionArn")
                .build())).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryGetSubscriptionAttributes1());
    }

    @Test
    void testTryGetSubscriptionAttributes1_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.getSubscriptionAttributes(GetSubscriptionAttributesRequest.builder()
                .subscriptionArn("subscriptionArn")
                .build())).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryGetSubscriptionAttributes1());
    }

    @Test
    void testTryGetSubscriptionAttributes1_SnsClientThrowsNotFoundException() {
        // Setup
        when(mockSnsClient.getSubscriptionAttributes(GetSubscriptionAttributesRequest.builder()
                .subscriptionArn("subscriptionArn")
                .build())).thenThrow(NotFoundException.class);

        // Run the test
        assertThrows(NotFoundException.class, () -> myClassUnderTest.tryGetSubscriptionAttributes1());
    }

    @Test
    void testTryGetSubscriptionAttributes1_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.getSubscriptionAttributes(GetSubscriptionAttributesRequest.builder()
                .subscriptionArn("subscriptionArn")
                .build())).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryGetSubscriptionAttributes1());
    }

    @Test
    void testTryGetSubscriptionAttributes1_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.getSubscriptionAttributes(GetSubscriptionAttributesRequest.builder()
                .subscriptionArn("subscriptionArn")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetSubscriptionAttributes1());
    }

    @Test
    void testTryGetSubscriptionAttributes1_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.getSubscriptionAttributes(GetSubscriptionAttributesRequest.builder()
                .subscriptionArn("subscriptionArn")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetSubscriptionAttributes1());
    }

    @Test
    void testTryGetSubscriptionAttributes1_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.getSubscriptionAttributes(GetSubscriptionAttributesRequest.builder()
                .subscriptionArn("subscriptionArn")
                .build())).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryGetSubscriptionAttributes1());
    }

    @Test
    void testTryGetSubscriptionAttributes2() {
        // Setup
        // Configure SnsClient.getSubscriptionAttributes(...).
        final GetSubscriptionAttributesResponse getSubscriptionAttributesResponse = GetSubscriptionAttributesResponse.builder()
                .attributes(new HashMap<>())
                .build();
        when(mockSnsClient.getSubscriptionAttributes(any(Consumer.class)))
                .thenReturn(getSubscriptionAttributesResponse);

        // Run the test
        myClassUnderTest.tryGetSubscriptionAttributes2();

        // Verify the results
    }

    @Test
    void testTryGetSubscriptionAttributes2_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.getSubscriptionAttributes(any(Consumer.class))).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryGetSubscriptionAttributes2());
    }

    @Test
    void testTryGetSubscriptionAttributes2_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.getSubscriptionAttributes(any(Consumer.class))).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryGetSubscriptionAttributes2());
    }

    @Test
    void testTryGetSubscriptionAttributes2_SnsClientThrowsNotFoundException() {
        // Setup
        when(mockSnsClient.getSubscriptionAttributes(any(Consumer.class))).thenThrow(NotFoundException.class);

        // Run the test
        assertThrows(NotFoundException.class, () -> myClassUnderTest.tryGetSubscriptionAttributes2());
    }

    @Test
    void testTryGetSubscriptionAttributes2_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.getSubscriptionAttributes(any(Consumer.class))).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryGetSubscriptionAttributes2());
    }

    @Test
    void testTryGetSubscriptionAttributes2_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.getSubscriptionAttributes(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetSubscriptionAttributes2());
    }

    @Test
    void testTryGetSubscriptionAttributes2_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.getSubscriptionAttributes(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetSubscriptionAttributes2());
    }

    @Test
    void testTryGetSubscriptionAttributes2_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.getSubscriptionAttributes(any(Consumer.class))).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryGetSubscriptionAttributes2());
    }

    @Test
    void testTryGetTopicAttributes1() {
        // Setup
        // Configure SnsClient.getTopicAttributes(...).
        final GetTopicAttributesResponse getTopicAttributesResponse = GetTopicAttributesResponse.builder()
                .attributes(new HashMap<>())
                .build();
        when(mockSnsClient.getTopicAttributes(GetTopicAttributesRequest.builder()
                .topicArn("topicArn")
                .build())).thenReturn(getTopicAttributesResponse);

        // Run the test
        myClassUnderTest.tryGetTopicAttributes1();

        // Verify the results
    }

    @Test
    void testTryGetTopicAttributes1_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.getTopicAttributes(GetTopicAttributesRequest.builder()
                .topicArn("topicArn")
                .build())).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryGetTopicAttributes1());
    }

    @Test
    void testTryGetTopicAttributes1_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.getTopicAttributes(GetTopicAttributesRequest.builder()
                .topicArn("topicArn")
                .build())).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryGetTopicAttributes1());
    }

    @Test
    void testTryGetTopicAttributes1_SnsClientThrowsNotFoundException() {
        // Setup
        when(mockSnsClient.getTopicAttributes(GetTopicAttributesRequest.builder()
                .topicArn("topicArn")
                .build())).thenThrow(NotFoundException.class);

        // Run the test
        assertThrows(NotFoundException.class, () -> myClassUnderTest.tryGetTopicAttributes1());
    }

    @Test
    void testTryGetTopicAttributes1_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.getTopicAttributes(GetTopicAttributesRequest.builder()
                .topicArn("topicArn")
                .build())).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryGetTopicAttributes1());
    }

    @Test
    void testTryGetTopicAttributes1_SnsClientThrowsInvalidSecurityException() {
        // Setup
        when(mockSnsClient.getTopicAttributes(GetTopicAttributesRequest.builder()
                .topicArn("topicArn")
                .build())).thenThrow(InvalidSecurityException.class);

        // Run the test
        assertThrows(InvalidSecurityException.class, () -> myClassUnderTest.tryGetTopicAttributes1());
    }

    @Test
    void testTryGetTopicAttributes1_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.getTopicAttributes(GetTopicAttributesRequest.builder()
                .topicArn("topicArn")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetTopicAttributes1());
    }

    @Test
    void testTryGetTopicAttributes1_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.getTopicAttributes(GetTopicAttributesRequest.builder()
                .topicArn("topicArn")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetTopicAttributes1());
    }

    @Test
    void testTryGetTopicAttributes1_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.getTopicAttributes(GetTopicAttributesRequest.builder()
                .topicArn("topicArn")
                .build())).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryGetTopicAttributes1());
    }

    @Test
    void testTryGetTopicAttributes2() {
        // Setup
        // Configure SnsClient.getTopicAttributes(...).
        final GetTopicAttributesResponse getTopicAttributesResponse = GetTopicAttributesResponse.builder()
                .attributes(new HashMap<>())
                .build();
        when(mockSnsClient.getTopicAttributes(any(Consumer.class))).thenReturn(getTopicAttributesResponse);

        // Run the test
        myClassUnderTest.tryGetTopicAttributes2();

        // Verify the results
    }

    @Test
    void testTryGetTopicAttributes2_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.getTopicAttributes(any(Consumer.class))).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryGetTopicAttributes2());
    }

    @Test
    void testTryGetTopicAttributes2_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.getTopicAttributes(any(Consumer.class))).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryGetTopicAttributes2());
    }

    @Test
    void testTryGetTopicAttributes2_SnsClientThrowsNotFoundException() {
        // Setup
        when(mockSnsClient.getTopicAttributes(any(Consumer.class))).thenThrow(NotFoundException.class);

        // Run the test
        assertThrows(NotFoundException.class, () -> myClassUnderTest.tryGetTopicAttributes2());
    }

    @Test
    void testTryGetTopicAttributes2_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.getTopicAttributes(any(Consumer.class))).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryGetTopicAttributes2());
    }

    @Test
    void testTryGetTopicAttributes2_SnsClientThrowsInvalidSecurityException() {
        // Setup
        when(mockSnsClient.getTopicAttributes(any(Consumer.class))).thenThrow(InvalidSecurityException.class);

        // Run the test
        assertThrows(InvalidSecurityException.class, () -> myClassUnderTest.tryGetTopicAttributes2());
    }

    @Test
    void testTryGetTopicAttributes2_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.getTopicAttributes(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryGetTopicAttributes2());
    }

    @Test
    void testTryGetTopicAttributes2_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.getTopicAttributes(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetTopicAttributes2());
    }

    @Test
    void testTryGetTopicAttributes2_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.getTopicAttributes(any(Consumer.class))).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryGetTopicAttributes2());
    }

    @Test
    void testTryListEndpointsByPlatformApplication1() {
        // Setup
        // Configure SnsClient.listEndpointsByPlatformApplication(...).
        final ListEndpointsByPlatformApplicationResponse listEndpointsByPlatformApplicationResponse = ListEndpointsByPlatformApplicationResponse.builder()
                .endpoints(Endpoint.builder()
                        .endpointArn("endpointArn")
                        .attributes(new HashMap<>())
                        .build())
                .nextToken("nextToken")
                .build();
        when(mockSnsClient.listEndpointsByPlatformApplication(ListEndpointsByPlatformApplicationRequest.builder()
                .platformApplicationArn("platformApplicationArn")
                .nextToken("nextToken")
                .build())).thenReturn(listEndpointsByPlatformApplicationResponse);

        // Run the test
        myClassUnderTest.tryListEndpointsByPlatformApplication1();

        // Verify the results
    }

    @Test
    void testTryListEndpointsByPlatformApplication1_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.listEndpointsByPlatformApplication(ListEndpointsByPlatformApplicationRequest.builder()
                .platformApplicationArn("platformApplicationArn")
                .nextToken("nextToken")
                .build())).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryListEndpointsByPlatformApplication1());
    }

    @Test
    void testTryListEndpointsByPlatformApplication1_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.listEndpointsByPlatformApplication(ListEndpointsByPlatformApplicationRequest.builder()
                .platformApplicationArn("platformApplicationArn")
                .nextToken("nextToken")
                .build())).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryListEndpointsByPlatformApplication1());
    }

    @Test
    void testTryListEndpointsByPlatformApplication1_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.listEndpointsByPlatformApplication(ListEndpointsByPlatformApplicationRequest.builder()
                .platformApplicationArn("platformApplicationArn")
                .nextToken("nextToken")
                .build())).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class,
                () -> myClassUnderTest.tryListEndpointsByPlatformApplication1());
    }

    @Test
    void testTryListEndpointsByPlatformApplication1_SnsClientThrowsNotFoundException() {
        // Setup
        when(mockSnsClient.listEndpointsByPlatformApplication(ListEndpointsByPlatformApplicationRequest.builder()
                .platformApplicationArn("platformApplicationArn")
                .nextToken("nextToken")
                .build())).thenThrow(NotFoundException.class);

        // Run the test
        assertThrows(NotFoundException.class, () -> myClassUnderTest.tryListEndpointsByPlatformApplication1());
    }

    @Test
    void testTryListEndpointsByPlatformApplication1_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.listEndpointsByPlatformApplication(ListEndpointsByPlatformApplicationRequest.builder()
                .platformApplicationArn("platformApplicationArn")
                .nextToken("nextToken")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListEndpointsByPlatformApplication1());
    }

    @Test
    void testTryListEndpointsByPlatformApplication1_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.listEndpointsByPlatformApplication(ListEndpointsByPlatformApplicationRequest.builder()
                .platformApplicationArn("platformApplicationArn")
                .nextToken("nextToken")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListEndpointsByPlatformApplication1());
    }

    @Test
    void testTryListEndpointsByPlatformApplication1_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.listEndpointsByPlatformApplication(ListEndpointsByPlatformApplicationRequest.builder()
                .platformApplicationArn("platformApplicationArn")
                .nextToken("nextToken")
                .build())).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryListEndpointsByPlatformApplication1());
    }

    @Test
    void testTryListEndpointsByPlatformApplication2() {
        // Setup
        // Configure SnsClient.listEndpointsByPlatformApplication(...).
        final ListEndpointsByPlatformApplicationResponse listEndpointsByPlatformApplicationResponse = ListEndpointsByPlatformApplicationResponse.builder()
                .endpoints(Endpoint.builder()
                        .endpointArn("endpointArn")
                        .attributes(new HashMap<>())
                        .build())
                .nextToken("nextToken")
                .build();
        when(mockSnsClient.listEndpointsByPlatformApplication(any(Consumer.class)))
                .thenReturn(listEndpointsByPlatformApplicationResponse);

        // Run the test
        myClassUnderTest.tryListEndpointsByPlatformApplication2();

        // Verify the results
    }

    @Test
    void testTryListEndpointsByPlatformApplication2_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.listEndpointsByPlatformApplication(any(Consumer.class)))
                .thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryListEndpointsByPlatformApplication2());
    }

    @Test
    void testTryListEndpointsByPlatformApplication2_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.listEndpointsByPlatformApplication(any(Consumer.class)))
                .thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryListEndpointsByPlatformApplication2());
    }

    @Test
    void testTryListEndpointsByPlatformApplication2_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.listEndpointsByPlatformApplication(any(Consumer.class)))
                .thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class,
                () -> myClassUnderTest.tryListEndpointsByPlatformApplication2());
    }

    @Test
    void testTryListEndpointsByPlatformApplication2_SnsClientThrowsNotFoundException() {
        // Setup
        when(mockSnsClient.listEndpointsByPlatformApplication(any(Consumer.class))).thenThrow(NotFoundException.class);

        // Run the test
        assertThrows(NotFoundException.class, () -> myClassUnderTest.tryListEndpointsByPlatformApplication2());
    }

    @Test
    void testTryListEndpointsByPlatformApplication2_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.listEndpointsByPlatformApplication(any(Consumer.class)))
                .thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListEndpointsByPlatformApplication2());
    }

    @Test
    void testTryListEndpointsByPlatformApplication2_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.listEndpointsByPlatformApplication(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListEndpointsByPlatformApplication2());
    }

    @Test
    void testTryListEndpointsByPlatformApplication2_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.listEndpointsByPlatformApplication(any(Consumer.class))).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryListEndpointsByPlatformApplication2());
    }

    @Test
    void testTryListEndpointsByPlatformApplicationPaginator1() {
        // Setup
        // Configure SnsClient.listEndpointsByPlatformApplicationPaginator(...).
        final ListEndpointsByPlatformApplicationIterable mockListEndpointsByPlatformApplicationIterable = mock(
                ListEndpointsByPlatformApplicationIterable.class);
        when(mockSnsClient.listEndpointsByPlatformApplicationPaginator(
                ListEndpointsByPlatformApplicationRequest.builder()
                        .platformApplicationArn("platformApplicationArn")
                        .nextToken("nextToken")
                        .build())).thenReturn(mockListEndpointsByPlatformApplicationIterable);

        // Run the test
        myClassUnderTest.tryListEndpointsByPlatformApplicationPaginator1();

        // Verify the results
    }

    @Test
    void testTryListEndpointsByPlatformApplicationPaginator1_SnsClientReturnsNoItems() {
        // Setup
        // Configure SnsClient.listEndpointsByPlatformApplicationPaginator(...).
        final ListEndpointsByPlatformApplicationIterable mockListEndpointsByPlatformApplicationIterable = mock(
                ListEndpointsByPlatformApplicationIterable.class);
        when(mockSnsClient.listEndpointsByPlatformApplicationPaginator(
                ListEndpointsByPlatformApplicationRequest.builder()
                        .platformApplicationArn("platformApplicationArn")
                        .nextToken("nextToken")
                        .build())).thenReturn(mockListEndpointsByPlatformApplicationIterable);

        // Run the test
        myClassUnderTest.tryListEndpointsByPlatformApplicationPaginator1();

        // Verify the results
    }

    @Test
    void testTryListEndpointsByPlatformApplicationPaginator1_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.listEndpointsByPlatformApplicationPaginator(
                ListEndpointsByPlatformApplicationRequest.builder()
                        .platformApplicationArn("platformApplicationArn")
                        .nextToken("nextToken")
                        .build())).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class,
                () -> myClassUnderTest.tryListEndpointsByPlatformApplicationPaginator1());
    }

    @Test
    void testTryListEndpointsByPlatformApplicationPaginator1_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.listEndpointsByPlatformApplicationPaginator(
                ListEndpointsByPlatformApplicationRequest.builder()
                        .platformApplicationArn("platformApplicationArn")
                        .nextToken("nextToken")
                        .build())).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class,
                () -> myClassUnderTest.tryListEndpointsByPlatformApplicationPaginator1());
    }

    @Test
    void testTryListEndpointsByPlatformApplicationPaginator1_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.listEndpointsByPlatformApplicationPaginator(
                ListEndpointsByPlatformApplicationRequest.builder()
                        .platformApplicationArn("platformApplicationArn")
                        .nextToken("nextToken")
                        .build())).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class,
                () -> myClassUnderTest.tryListEndpointsByPlatformApplicationPaginator1());
    }

    @Test
    void testTryListEndpointsByPlatformApplicationPaginator1_SnsClientThrowsNotFoundException() {
        // Setup
        when(mockSnsClient.listEndpointsByPlatformApplicationPaginator(
                ListEndpointsByPlatformApplicationRequest.builder()
                        .platformApplicationArn("platformApplicationArn")
                        .nextToken("nextToken")
                        .build())).thenThrow(NotFoundException.class);

        // Run the test
        assertThrows(NotFoundException.class, () -> myClassUnderTest.tryListEndpointsByPlatformApplicationPaginator1());
    }

    @Test
    void testTryListEndpointsByPlatformApplicationPaginator1_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.listEndpointsByPlatformApplicationPaginator(
                ListEndpointsByPlatformApplicationRequest.builder()
                        .platformApplicationArn("platformApplicationArn")
                        .nextToken("nextToken")
                        .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class,
                () -> myClassUnderTest.tryListEndpointsByPlatformApplicationPaginator1());
    }

    @Test
    void testTryListEndpointsByPlatformApplicationPaginator1_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.listEndpointsByPlatformApplicationPaginator(
                ListEndpointsByPlatformApplicationRequest.builder()
                        .platformApplicationArn("platformApplicationArn")
                        .nextToken("nextToken")
                        .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class,
                () -> myClassUnderTest.tryListEndpointsByPlatformApplicationPaginator1());
    }

    @Test
    void testTryListEndpointsByPlatformApplicationPaginator1_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.listEndpointsByPlatformApplicationPaginator(
                ListEndpointsByPlatformApplicationRequest.builder()
                        .platformApplicationArn("platformApplicationArn")
                        .nextToken("nextToken")
                        .build())).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryListEndpointsByPlatformApplicationPaginator1());
    }

    @Test
    void testTryListEndpointsByPlatformApplicationPaginator2() {
        // Setup
        // Configure SnsClient.listEndpointsByPlatformApplicationPaginator(...).
        final ListEndpointsByPlatformApplicationIterable mockListEndpointsByPlatformApplicationIterable = mock(
                ListEndpointsByPlatformApplicationIterable.class);
        when(mockSnsClient.listEndpointsByPlatformApplicationPaginator(any(Consumer.class)))
                .thenReturn(mockListEndpointsByPlatformApplicationIterable);

        // Run the test
        myClassUnderTest.tryListEndpointsByPlatformApplicationPaginator2();

        // Verify the results
    }

    @Test
    void testTryListEndpointsByPlatformApplicationPaginator2_SnsClientReturnsNoItems() {
        // Setup
        // Configure SnsClient.listEndpointsByPlatformApplicationPaginator(...).
        final ListEndpointsByPlatformApplicationIterable mockListEndpointsByPlatformApplicationIterable = mock(
                ListEndpointsByPlatformApplicationIterable.class);
        when(mockSnsClient.listEndpointsByPlatformApplicationPaginator(any(Consumer.class)))
                .thenReturn(mockListEndpointsByPlatformApplicationIterable);

        // Run the test
        myClassUnderTest.tryListEndpointsByPlatformApplicationPaginator2();

        // Verify the results
    }

    @Test
    void testTryListEndpointsByPlatformApplicationPaginator2_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.listEndpointsByPlatformApplicationPaginator(any(Consumer.class)))
                .thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class,
                () -> myClassUnderTest.tryListEndpointsByPlatformApplicationPaginator2());
    }

    @Test
    void testTryListEndpointsByPlatformApplicationPaginator2_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.listEndpointsByPlatformApplicationPaginator(any(Consumer.class)))
                .thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class,
                () -> myClassUnderTest.tryListEndpointsByPlatformApplicationPaginator2());
    }

    @Test
    void testTryListEndpointsByPlatformApplicationPaginator2_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.listEndpointsByPlatformApplicationPaginator(any(Consumer.class)))
                .thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class,
                () -> myClassUnderTest.tryListEndpointsByPlatformApplicationPaginator2());
    }

    @Test
    void testTryListEndpointsByPlatformApplicationPaginator2_SnsClientThrowsNotFoundException() {
        // Setup
        when(mockSnsClient.listEndpointsByPlatformApplicationPaginator(any(Consumer.class)))
                .thenThrow(NotFoundException.class);

        // Run the test
        assertThrows(NotFoundException.class, () -> myClassUnderTest.tryListEndpointsByPlatformApplicationPaginator2());
    }

    @Test
    void testTryListEndpointsByPlatformApplicationPaginator2_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.listEndpointsByPlatformApplicationPaginator(any(Consumer.class)))
                .thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class,
                () -> myClassUnderTest.tryListEndpointsByPlatformApplicationPaginator2());
    }

    @Test
    void testTryListEndpointsByPlatformApplicationPaginator2_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.listEndpointsByPlatformApplicationPaginator(any(Consumer.class)))
                .thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class,
                () -> myClassUnderTest.tryListEndpointsByPlatformApplicationPaginator2());
    }

    @Test
    void testTryListEndpointsByPlatformApplicationPaginator2_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.listEndpointsByPlatformApplicationPaginator(any(Consumer.class)))
                .thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryListEndpointsByPlatformApplicationPaginator2());
    }

    @Test
    void testTryListPhoneNumbersOptedOut1() {
        // Setup
        // Configure SnsClient.listPhoneNumbersOptedOut(...).
        final ListPhoneNumbersOptedOutResponse listPhoneNumbersOptedOutResponse = ListPhoneNumbersOptedOutResponse.builder()
                .phoneNumbers("phoneNumbers")
                .nextToken("nextToken")
                .build();
        when(mockSnsClient.listPhoneNumbersOptedOut()).thenReturn(listPhoneNumbersOptedOutResponse);

        // Run the test
        myClassUnderTest.tryListPhoneNumbersOptedOut1();

        // Verify the results
    }

    @Test
    void testTryListPhoneNumbersOptedOut1_SnsClientThrowsThrottledException() {
        // Setup
        when(mockSnsClient.listPhoneNumbersOptedOut()).thenThrow(ThrottledException.class);

        // Run the test
        assertThrows(ThrottledException.class, () -> myClassUnderTest.tryListPhoneNumbersOptedOut1());
    }

    @Test
    void testTryListPhoneNumbersOptedOut1_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.listPhoneNumbersOptedOut()).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryListPhoneNumbersOptedOut1());
    }

    @Test
    void testTryListPhoneNumbersOptedOut1_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.listPhoneNumbersOptedOut()).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryListPhoneNumbersOptedOut1());
    }

    @Test
    void testTryListPhoneNumbersOptedOut1_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.listPhoneNumbersOptedOut()).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryListPhoneNumbersOptedOut1());
    }

    @Test
    void testTryListPhoneNumbersOptedOut1_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.listPhoneNumbersOptedOut()).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListPhoneNumbersOptedOut1());
    }

    @Test
    void testTryListPhoneNumbersOptedOut1_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.listPhoneNumbersOptedOut()).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListPhoneNumbersOptedOut1());
    }

    @Test
    void testTryListPhoneNumbersOptedOut1_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.listPhoneNumbersOptedOut()).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryListPhoneNumbersOptedOut1());
    }

    @Test
    void testTryListPhoneNumbersOptedOut2() {
        // Setup
        // Configure SnsClient.listPhoneNumbersOptedOut(...).
        final ListPhoneNumbersOptedOutResponse listPhoneNumbersOptedOutResponse = ListPhoneNumbersOptedOutResponse.builder()
                .phoneNumbers("phoneNumbers")
                .nextToken("nextToken")
                .build();
        when(mockSnsClient.listPhoneNumbersOptedOut(ListPhoneNumbersOptedOutRequest.builder()
                .nextToken("nextToken")
                .build())).thenReturn(listPhoneNumbersOptedOutResponse);

        // Run the test
        myClassUnderTest.tryListPhoneNumbersOptedOut2();

        // Verify the results
    }

    @Test
    void testTryListPhoneNumbersOptedOut2_SnsClientThrowsThrottledException() {
        // Setup
        when(mockSnsClient.listPhoneNumbersOptedOut(ListPhoneNumbersOptedOutRequest.builder()
                .nextToken("nextToken")
                .build())).thenThrow(ThrottledException.class);

        // Run the test
        assertThrows(ThrottledException.class, () -> myClassUnderTest.tryListPhoneNumbersOptedOut2());
    }

    @Test
    void testTryListPhoneNumbersOptedOut2_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.listPhoneNumbersOptedOut(ListPhoneNumbersOptedOutRequest.builder()
                .nextToken("nextToken")
                .build())).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryListPhoneNumbersOptedOut2());
    }

    @Test
    void testTryListPhoneNumbersOptedOut2_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.listPhoneNumbersOptedOut(ListPhoneNumbersOptedOutRequest.builder()
                .nextToken("nextToken")
                .build())).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryListPhoneNumbersOptedOut2());
    }

    @Test
    void testTryListPhoneNumbersOptedOut2_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.listPhoneNumbersOptedOut(ListPhoneNumbersOptedOutRequest.builder()
                .nextToken("nextToken")
                .build())).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryListPhoneNumbersOptedOut2());
    }

    @Test
    void testTryListPhoneNumbersOptedOut2_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.listPhoneNumbersOptedOut(ListPhoneNumbersOptedOutRequest.builder()
                .nextToken("nextToken")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListPhoneNumbersOptedOut2());
    }

    @Test
    void testTryListPhoneNumbersOptedOut2_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.listPhoneNumbersOptedOut(ListPhoneNumbersOptedOutRequest.builder()
                .nextToken("nextToken")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListPhoneNumbersOptedOut2());
    }

    @Test
    void testTryListPhoneNumbersOptedOut2_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.listPhoneNumbersOptedOut(ListPhoneNumbersOptedOutRequest.builder()
                .nextToken("nextToken")
                .build())).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryListPhoneNumbersOptedOut2());
    }

    @Test
    void testTryListPhoneNumbersOptedOut3() {
        // Setup
        // Configure SnsClient.listPhoneNumbersOptedOut(...).
        final ListPhoneNumbersOptedOutResponse listPhoneNumbersOptedOutResponse = ListPhoneNumbersOptedOutResponse.builder()
                .phoneNumbers("phoneNumbers")
                .nextToken("nextToken")
                .build();
        when(mockSnsClient.listPhoneNumbersOptedOut(any(Consumer.class))).thenReturn(listPhoneNumbersOptedOutResponse);

        // Run the test
        myClassUnderTest.tryListPhoneNumbersOptedOut3();

        // Verify the results
    }

    @Test
    void testTryListPhoneNumbersOptedOut3_SnsClientThrowsThrottledException() {
        // Setup
        when(mockSnsClient.listPhoneNumbersOptedOut(any(Consumer.class))).thenThrow(ThrottledException.class);

        // Run the test
        assertThrows(ThrottledException.class, () -> myClassUnderTest.tryListPhoneNumbersOptedOut3());
    }

    @Test
    void testTryListPhoneNumbersOptedOut3_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.listPhoneNumbersOptedOut(any(Consumer.class))).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryListPhoneNumbersOptedOut3());
    }

    @Test
    void testTryListPhoneNumbersOptedOut3_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.listPhoneNumbersOptedOut(any(Consumer.class))).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryListPhoneNumbersOptedOut3());
    }

    @Test
    void testTryListPhoneNumbersOptedOut3_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.listPhoneNumbersOptedOut(any(Consumer.class))).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryListPhoneNumbersOptedOut3());
    }

    @Test
    void testTryListPhoneNumbersOptedOut3_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.listPhoneNumbersOptedOut(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListPhoneNumbersOptedOut3());
    }

    @Test
    void testTryListPhoneNumbersOptedOut3_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.listPhoneNumbersOptedOut(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListPhoneNumbersOptedOut3());
    }

    @Test
    void testTryListPhoneNumbersOptedOut3_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.listPhoneNumbersOptedOut(any(Consumer.class))).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryListPhoneNumbersOptedOut3());
    }

    @Test
    void testTryListPlatformApplications1() {
        // Setup
        // Configure SnsClient.listPlatformApplications(...).
        final ListPlatformApplicationsResponse listPlatformApplicationsResponse = ListPlatformApplicationsResponse.builder()
                .platformApplications(PlatformApplication.builder()
                        .platformApplicationArn("platformApplicationArn")
                        .attributes(new HashMap<>())
                        .build())
                .nextToken("nextToken")
                .build();
        when(mockSnsClient.listPlatformApplications()).thenReturn(listPlatformApplicationsResponse);

        // Run the test
        myClassUnderTest.tryListPlatformApplications1();

        // Verify the results
    }

    @Test
    void testTryListPlatformApplications1_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.listPlatformApplications()).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryListPlatformApplications1());
    }

    @Test
    void testTryListPlatformApplications1_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.listPlatformApplications()).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryListPlatformApplications1());
    }

    @Test
    void testTryListPlatformApplications1_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.listPlatformApplications()).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryListPlatformApplications1());
    }

    @Test
    void testTryListPlatformApplications1_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.listPlatformApplications()).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListPlatformApplications1());
    }

    @Test
    void testTryListPlatformApplications1_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.listPlatformApplications()).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListPlatformApplications1());
    }

    @Test
    void testTryListPlatformApplications1_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.listPlatformApplications()).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryListPlatformApplications1());
    }

    @Test
    void testTryListPlatformApplications2() {
        // Setup
        // Configure SnsClient.listPlatformApplications(...).
        final ListPlatformApplicationsResponse listPlatformApplicationsResponse = ListPlatformApplicationsResponse.builder()
                .platformApplications(PlatformApplication.builder()
                        .platformApplicationArn("platformApplicationArn")
                        .attributes(new HashMap<>())
                        .build())
                .nextToken("nextToken")
                .build();
        when(mockSnsClient.listPlatformApplications(ListPlatformApplicationsRequest.builder()
                .nextToken("nextToken")
                .build())).thenReturn(listPlatformApplicationsResponse);

        // Run the test
        myClassUnderTest.tryListPlatformApplications2();

        // Verify the results
    }

    @Test
    void testTryListPlatformApplications2_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.listPlatformApplications(ListPlatformApplicationsRequest.builder()
                .nextToken("nextToken")
                .build())).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryListPlatformApplications2());
    }

    @Test
    void testTryListPlatformApplications2_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.listPlatformApplications(ListPlatformApplicationsRequest.builder()
                .nextToken("nextToken")
                .build())).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryListPlatformApplications2());
    }

    @Test
    void testTryListPlatformApplications2_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.listPlatformApplications(ListPlatformApplicationsRequest.builder()
                .nextToken("nextToken")
                .build())).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryListPlatformApplications2());
    }

    @Test
    void testTryListPlatformApplications2_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.listPlatformApplications(ListPlatformApplicationsRequest.builder()
                .nextToken("nextToken")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListPlatformApplications2());
    }

    @Test
    void testTryListPlatformApplications2_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.listPlatformApplications(ListPlatformApplicationsRequest.builder()
                .nextToken("nextToken")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListPlatformApplications2());
    }

    @Test
    void testTryListPlatformApplications2_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.listPlatformApplications(ListPlatformApplicationsRequest.builder()
                .nextToken("nextToken")
                .build())).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryListPlatformApplications2());
    }

    @Test
    void testTryListPlatformApplications3() {
        // Setup
        // Configure SnsClient.listPlatformApplications(...).
        final ListPlatformApplicationsResponse listPlatformApplicationsResponse = ListPlatformApplicationsResponse.builder()
                .platformApplications(PlatformApplication.builder()
                        .platformApplicationArn("platformApplicationArn")
                        .attributes(new HashMap<>())
                        .build())
                .nextToken("nextToken")
                .build();
        when(mockSnsClient.listPlatformApplications(any(Consumer.class))).thenReturn(listPlatformApplicationsResponse);

        // Run the test
        myClassUnderTest.tryListPlatformApplications3();

        // Verify the results
    }

    @Test
    void testTryListPlatformApplications3_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.listPlatformApplications(any(Consumer.class))).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryListPlatformApplications3());
    }

    @Test
    void testTryListPlatformApplications3_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.listPlatformApplications(any(Consumer.class))).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryListPlatformApplications3());
    }

    @Test
    void testTryListPlatformApplications3_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.listPlatformApplications(any(Consumer.class))).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryListPlatformApplications3());
    }

    @Test
    void testTryListPlatformApplications3_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.listPlatformApplications(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListPlatformApplications3());
    }

    @Test
    void testTryListPlatformApplications3_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.listPlatformApplications(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListPlatformApplications3());
    }

    @Test
    void testTryListPlatformApplications3_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.listPlatformApplications(any(Consumer.class))).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryListPlatformApplications3());
    }

    @Test
    void testTryListPlatformApplicationsPaginator1() {
        // Setup
        // Configure SnsClient.listPlatformApplicationsPaginator(...).
        final ListPlatformApplicationsIterable mockListPlatformApplicationsIterable = mock(
                ListPlatformApplicationsIterable.class);
        when(mockSnsClient.listPlatformApplicationsPaginator()).thenReturn(mockListPlatformApplicationsIterable);

        // Run the test
        myClassUnderTest.tryListPlatformApplicationsPaginator1();

        // Verify the results
    }

    @Test
    void testTryListPlatformApplicationsPaginator1_SnsClientReturnsNoItems() {
        // Setup
        // Configure SnsClient.listPlatformApplicationsPaginator(...).
        final ListPlatformApplicationsIterable mockListPlatformApplicationsIterable = mock(
                ListPlatformApplicationsIterable.class);
        when(mockSnsClient.listPlatformApplicationsPaginator()).thenReturn(mockListPlatformApplicationsIterable);

        // Run the test
        myClassUnderTest.tryListPlatformApplicationsPaginator1();

        // Verify the results
    }

    @Test
    void testTryListPlatformApplicationsPaginator1_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.listPlatformApplicationsPaginator()).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryListPlatformApplicationsPaginator1());
    }

    @Test
    void testTryListPlatformApplicationsPaginator1_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.listPlatformApplicationsPaginator()).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryListPlatformApplicationsPaginator1());
    }

    @Test
    void testTryListPlatformApplicationsPaginator1_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.listPlatformApplicationsPaginator()).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryListPlatformApplicationsPaginator1());
    }

    @Test
    void testTryListPlatformApplicationsPaginator1_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.listPlatformApplicationsPaginator()).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListPlatformApplicationsPaginator1());
    }

    @Test
    void testTryListPlatformApplicationsPaginator1_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.listPlatformApplicationsPaginator()).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListPlatformApplicationsPaginator1());
    }

    @Test
    void testTryListPlatformApplicationsPaginator1_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.listPlatformApplicationsPaginator()).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryListPlatformApplicationsPaginator1());
    }

    @Test
    void testTryListPlatformApplicationsPaginator2() {
        // Setup
        // Configure SnsClient.listPlatformApplicationsPaginator(...).
        final ListPlatformApplicationsIterable mockListPlatformApplicationsIterable = mock(
                ListPlatformApplicationsIterable.class);
        when(mockSnsClient.listPlatformApplicationsPaginator(ListPlatformApplicationsRequest.builder()
                .nextToken("nextToken")
                .build())).thenReturn(mockListPlatformApplicationsIterable);

        // Run the test
        myClassUnderTest.tryListPlatformApplicationsPaginator2();

        // Verify the results
    }

    @Test
    void testTryListPlatformApplicationsPaginator2_SnsClientReturnsNoItems() {
        // Setup
        // Configure SnsClient.listPlatformApplicationsPaginator(...).
        final ListPlatformApplicationsIterable mockListPlatformApplicationsIterable = mock(
                ListPlatformApplicationsIterable.class);
        when(mockSnsClient.listPlatformApplicationsPaginator(ListPlatformApplicationsRequest.builder()
                .nextToken("nextToken")
                .build())).thenReturn(mockListPlatformApplicationsIterable);

        // Run the test
        myClassUnderTest.tryListPlatformApplicationsPaginator2();

        // Verify the results
    }

    @Test
    void testTryListPlatformApplicationsPaginator2_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.listPlatformApplicationsPaginator(ListPlatformApplicationsRequest.builder()
                .nextToken("nextToken")
                .build())).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryListPlatformApplicationsPaginator2());
    }

    @Test
    void testTryListPlatformApplicationsPaginator2_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.listPlatformApplicationsPaginator(ListPlatformApplicationsRequest.builder()
                .nextToken("nextToken")
                .build())).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryListPlatformApplicationsPaginator2());
    }

    @Test
    void testTryListPlatformApplicationsPaginator2_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.listPlatformApplicationsPaginator(ListPlatformApplicationsRequest.builder()
                .nextToken("nextToken")
                .build())).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryListPlatformApplicationsPaginator2());
    }

    @Test
    void testTryListPlatformApplicationsPaginator2_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.listPlatformApplicationsPaginator(ListPlatformApplicationsRequest.builder()
                .nextToken("nextToken")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListPlatformApplicationsPaginator2());
    }

    @Test
    void testTryListPlatformApplicationsPaginator2_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.listPlatformApplicationsPaginator(ListPlatformApplicationsRequest.builder()
                .nextToken("nextToken")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListPlatformApplicationsPaginator2());
    }

    @Test
    void testTryListPlatformApplicationsPaginator2_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.listPlatformApplicationsPaginator(ListPlatformApplicationsRequest.builder()
                .nextToken("nextToken")
                .build())).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryListPlatformApplicationsPaginator2());
    }

    @Test
    void testTryListPlatformApplicationsPaginator3() {
        // Setup
        // Configure SnsClient.listPlatformApplicationsPaginator(...).
        final ListPlatformApplicationsIterable mockListPlatformApplicationsIterable = mock(
                ListPlatformApplicationsIterable.class);
        when(mockSnsClient.listPlatformApplicationsPaginator(any(Consumer.class)))
                .thenReturn(mockListPlatformApplicationsIterable);

        // Run the test
        myClassUnderTest.tryListPlatformApplicationsPaginator3();

        // Verify the results
    }

    @Test
    void testTryListPlatformApplicationsPaginator3_SnsClientReturnsNoItems() {
        // Setup
        // Configure SnsClient.listPlatformApplicationsPaginator(...).
        final ListPlatformApplicationsIterable mockListPlatformApplicationsIterable = mock(
                ListPlatformApplicationsIterable.class);
        when(mockSnsClient.listPlatformApplicationsPaginator(any(Consumer.class)))
                .thenReturn(mockListPlatformApplicationsIterable);

        // Run the test
        myClassUnderTest.tryListPlatformApplicationsPaginator3();

        // Verify the results
    }

    @Test
    void testTryListPlatformApplicationsPaginator3_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.listPlatformApplicationsPaginator(any(Consumer.class)))
                .thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryListPlatformApplicationsPaginator3());
    }

    @Test
    void testTryListPlatformApplicationsPaginator3_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.listPlatformApplicationsPaginator(any(Consumer.class)))
                .thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryListPlatformApplicationsPaginator3());
    }

    @Test
    void testTryListPlatformApplicationsPaginator3_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.listPlatformApplicationsPaginator(any(Consumer.class)))
                .thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryListPlatformApplicationsPaginator3());
    }

    @Test
    void testTryListPlatformApplicationsPaginator3_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.listPlatformApplicationsPaginator(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListPlatformApplicationsPaginator3());
    }

    @Test
    void testTryListPlatformApplicationsPaginator3_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.listPlatformApplicationsPaginator(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListPlatformApplicationsPaginator3());
    }

    @Test
    void testTryListPlatformApplicationsPaginator3_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.listPlatformApplicationsPaginator(any(Consumer.class))).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryListPlatformApplicationsPaginator3());
    }

    @Test
    void testTryListSubscriptions1() {
        // Setup
        // Configure SnsClient.listSubscriptions(...).
        final ListSubscriptionsResponse listSubscriptionsResponse = ListSubscriptionsResponse.builder()
                .subscriptions(Subscription.builder()
                        .subscriptionArn("subscriptionArn")
                        .owner("owner")
                        .protocol("protocol")
                        .endpoint("endpoint")
                        .topicArn("topicArn")
                        .build())
                .nextToken("nextToken")
                .build();
        when(mockSnsClient.listSubscriptions()).thenReturn(listSubscriptionsResponse);

        // Run the test
        myClassUnderTest.tryListSubscriptions1();

        // Verify the results
    }

    @Test
    void testTryListSubscriptions1_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.listSubscriptions()).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryListSubscriptions1());
    }

    @Test
    void testTryListSubscriptions1_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.listSubscriptions()).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryListSubscriptions1());
    }

    @Test
    void testTryListSubscriptions1_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.listSubscriptions()).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryListSubscriptions1());
    }

    @Test
    void testTryListSubscriptions1_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.listSubscriptions()).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListSubscriptions1());
    }

    @Test
    void testTryListSubscriptions1_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.listSubscriptions()).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListSubscriptions1());
    }

    @Test
    void testTryListSubscriptions1_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.listSubscriptions()).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryListSubscriptions1());
    }

    @Test
    void testTryListSubscriptions2() {
        // Setup
        // Configure SnsClient.listSubscriptions(...).
        final ListSubscriptionsResponse listSubscriptionsResponse = ListSubscriptionsResponse.builder()
                .subscriptions(Subscription.builder()
                        .subscriptionArn("subscriptionArn")
                        .owner("owner")
                        .protocol("protocol")
                        .endpoint("endpoint")
                        .topicArn("topicArn")
                        .build())
                .nextToken("nextToken")
                .build();
        when(mockSnsClient.listSubscriptions(ListSubscriptionsRequest.builder()
                .nextToken("nextToken")
                .build())).thenReturn(listSubscriptionsResponse);

        // Run the test
        myClassUnderTest.tryListSubscriptions2();

        // Verify the results
    }

    @Test
    void testTryListSubscriptions2_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.listSubscriptions(ListSubscriptionsRequest.builder()
                .nextToken("nextToken")
                .build())).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryListSubscriptions2());
    }

    @Test
    void testTryListSubscriptions2_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.listSubscriptions(ListSubscriptionsRequest.builder()
                .nextToken("nextToken")
                .build())).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryListSubscriptions2());
    }

    @Test
    void testTryListSubscriptions2_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.listSubscriptions(ListSubscriptionsRequest.builder()
                .nextToken("nextToken")
                .build())).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryListSubscriptions2());
    }

    @Test
    void testTryListSubscriptions2_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.listSubscriptions(ListSubscriptionsRequest.builder()
                .nextToken("nextToken")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListSubscriptions2());
    }

    @Test
    void testTryListSubscriptions2_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.listSubscriptions(ListSubscriptionsRequest.builder()
                .nextToken("nextToken")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListSubscriptions2());
    }

    @Test
    void testTryListSubscriptions2_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.listSubscriptions(ListSubscriptionsRequest.builder()
                .nextToken("nextToken")
                .build())).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryListSubscriptions2());
    }

    @Test
    void testTryListSubscriptions3() {
        // Setup
        // Configure SnsClient.listSubscriptions(...).
        final ListSubscriptionsResponse listSubscriptionsResponse = ListSubscriptionsResponse.builder()
                .subscriptions(Subscription.builder()
                        .subscriptionArn("subscriptionArn")
                        .owner("owner")
                        .protocol("protocol")
                        .endpoint("endpoint")
                        .topicArn("topicArn")
                        .build())
                .nextToken("nextToken")
                .build();
        when(mockSnsClient.listSubscriptions(any(Consumer.class))).thenReturn(listSubscriptionsResponse);

        // Run the test
        myClassUnderTest.tryListSubscriptions3();

        // Verify the results
    }

    @Test
    void testTryListSubscriptions3_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.listSubscriptions(any(Consumer.class))).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryListSubscriptions3());
    }

    @Test
    void testTryListSubscriptions3_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.listSubscriptions(any(Consumer.class))).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryListSubscriptions3());
    }

    @Test
    void testTryListSubscriptions3_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.listSubscriptions(any(Consumer.class))).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryListSubscriptions3());
    }

    @Test
    void testTryListSubscriptions3_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.listSubscriptions(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListSubscriptions3());
    }

    @Test
    void testTryListSubscriptions3_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.listSubscriptions(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListSubscriptions3());
    }

    @Test
    void testTryListSubscriptions3_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.listSubscriptions(any(Consumer.class))).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryListSubscriptions3());
    }

    @Test
    void testTryListSubscriptionsPaginator1() {
        // Setup
        // Configure SnsClient.listSubscriptionsPaginator(...).
        final ListSubscriptionsIterable mockListSubscriptionsIterable = mock(ListSubscriptionsIterable.class);
        when(mockSnsClient.listSubscriptionsPaginator()).thenReturn(mockListSubscriptionsIterable);

        // Run the test
        myClassUnderTest.tryListSubscriptionsPaginator1();

        // Verify the results
    }

    @Test
    void testTryListSubscriptionsPaginator1_SnsClientReturnsNoItems() {
        // Setup
        // Configure SnsClient.listSubscriptionsPaginator(...).
        final ListSubscriptionsIterable mockListSubscriptionsIterable = mock(ListSubscriptionsIterable.class);
        when(mockSnsClient.listSubscriptionsPaginator()).thenReturn(mockListSubscriptionsIterable);

        // Run the test
        myClassUnderTest.tryListSubscriptionsPaginator1();

        // Verify the results
    }

    @Test
    void testTryListSubscriptionsPaginator1_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.listSubscriptionsPaginator()).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryListSubscriptionsPaginator1());
    }

    @Test
    void testTryListSubscriptionsPaginator1_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.listSubscriptionsPaginator()).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryListSubscriptionsPaginator1());
    }

    @Test
    void testTryListSubscriptionsPaginator1_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.listSubscriptionsPaginator()).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryListSubscriptionsPaginator1());
    }

    @Test
    void testTryListSubscriptionsPaginator1_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.listSubscriptionsPaginator()).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListSubscriptionsPaginator1());
    }

    @Test
    void testTryListSubscriptionsPaginator1_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.listSubscriptionsPaginator()).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListSubscriptionsPaginator1());
    }

    @Test
    void testTryListSubscriptionsPaginator1_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.listSubscriptionsPaginator()).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryListSubscriptionsPaginator1());
    }

    @Test
    void testTryListSubscriptionsPaginator2() {
        // Setup
        // Configure SnsClient.listSubscriptionsPaginator(...).
        final ListSubscriptionsIterable mockListSubscriptionsIterable = mock(ListSubscriptionsIterable.class);
        when(mockSnsClient.listSubscriptionsPaginator(ListSubscriptionsRequest.builder()
                .nextToken("nextToken")
                .build())).thenReturn(mockListSubscriptionsIterable);

        // Run the test
        myClassUnderTest.tryListSubscriptionsPaginator2();

        // Verify the results
    }

    @Test
    void testTryListSubscriptionsPaginator2_SnsClientReturnsNoItems() {
        // Setup
        // Configure SnsClient.listSubscriptionsPaginator(...).
        final ListSubscriptionsIterable mockListSubscriptionsIterable = mock(ListSubscriptionsIterable.class);
        when(mockSnsClient.listSubscriptionsPaginator(ListSubscriptionsRequest.builder()
                .nextToken("nextToken")
                .build())).thenReturn(mockListSubscriptionsIterable);

        // Run the test
        myClassUnderTest.tryListSubscriptionsPaginator2();

        // Verify the results
    }

    @Test
    void testTryListSubscriptionsPaginator2_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.listSubscriptionsPaginator(ListSubscriptionsRequest.builder()
                .nextToken("nextToken")
                .build())).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryListSubscriptionsPaginator2());
    }

    @Test
    void testTryListSubscriptionsPaginator2_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.listSubscriptionsPaginator(ListSubscriptionsRequest.builder()
                .nextToken("nextToken")
                .build())).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryListSubscriptionsPaginator2());
    }

    @Test
    void testTryListSubscriptionsPaginator2_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.listSubscriptionsPaginator(ListSubscriptionsRequest.builder()
                .nextToken("nextToken")
                .build())).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryListSubscriptionsPaginator2());
    }

    @Test
    void testTryListSubscriptionsPaginator2_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.listSubscriptionsPaginator(ListSubscriptionsRequest.builder()
                .nextToken("nextToken")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListSubscriptionsPaginator2());
    }

    @Test
    void testTryListSubscriptionsPaginator2_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.listSubscriptionsPaginator(ListSubscriptionsRequest.builder()
                .nextToken("nextToken")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListSubscriptionsPaginator2());
    }

    @Test
    void testTryListSubscriptionsPaginator2_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.listSubscriptionsPaginator(ListSubscriptionsRequest.builder()
                .nextToken("nextToken")
                .build())).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryListSubscriptionsPaginator2());
    }

    @Test
    void testTryListSubscriptionsPaginator3() {
        // Setup
        // Configure SnsClient.listSubscriptionsPaginator(...).
        final ListSubscriptionsIterable mockListSubscriptionsIterable = mock(ListSubscriptionsIterable.class);
        when(mockSnsClient.listSubscriptionsPaginator(any(Consumer.class))).thenReturn(mockListSubscriptionsIterable);

        // Run the test
        myClassUnderTest.tryListSubscriptionsPaginator3();

        // Verify the results
    }

    @Test
    void testTryListSubscriptionsPaginator3_SnsClientReturnsNoItems() {
        // Setup
        // Configure SnsClient.listSubscriptionsPaginator(...).
        final ListSubscriptionsIterable mockListSubscriptionsIterable = mock(ListSubscriptionsIterable.class);
        when(mockSnsClient.listSubscriptionsPaginator(any(Consumer.class))).thenReturn(mockListSubscriptionsIterable);

        // Run the test
        myClassUnderTest.tryListSubscriptionsPaginator3();

        // Verify the results
    }

    @Test
    void testTryListSubscriptionsPaginator3_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.listSubscriptionsPaginator(any(Consumer.class))).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryListSubscriptionsPaginator3());
    }

    @Test
    void testTryListSubscriptionsPaginator3_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.listSubscriptionsPaginator(any(Consumer.class))).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryListSubscriptionsPaginator3());
    }

    @Test
    void testTryListSubscriptionsPaginator3_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.listSubscriptionsPaginator(any(Consumer.class)))
                .thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryListSubscriptionsPaginator3());
    }

    @Test
    void testTryListSubscriptionsPaginator3_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.listSubscriptionsPaginator(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListSubscriptionsPaginator3());
    }

    @Test
    void testTryListSubscriptionsPaginator3_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.listSubscriptionsPaginator(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListSubscriptionsPaginator3());
    }

    @Test
    void testTryListSubscriptionsPaginator3_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.listSubscriptionsPaginator(any(Consumer.class))).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryListSubscriptionsPaginator3());
    }

    @Test
    void testTryListSubscriptionsByTopic1() {
        // Setup
        // Configure SnsClient.listSubscriptionsByTopic(...).
        final ListSubscriptionsByTopicResponse listSubscriptionsByTopicResponse = ListSubscriptionsByTopicResponse.builder()
                .subscriptions(Subscription.builder()
                        .subscriptionArn("subscriptionArn")
                        .owner("owner")
                        .protocol("protocol")
                        .endpoint("endpoint")
                        .topicArn("topicArn")
                        .build())
                .nextToken("nextToken")
                .build();
        when(mockSnsClient.listSubscriptionsByTopic(ListSubscriptionsByTopicRequest.builder()
                .topicArn("topicArn")
                .nextToken("nextToken")
                .build())).thenReturn(listSubscriptionsByTopicResponse);

        // Run the test
        myClassUnderTest.tryListSubscriptionsByTopic1();

        // Verify the results
    }

    @Test
    void testTryListSubscriptionsByTopic1_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.listSubscriptionsByTopic(ListSubscriptionsByTopicRequest.builder()
                .topicArn("topicArn")
                .nextToken("nextToken")
                .build())).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryListSubscriptionsByTopic1());
    }

    @Test
    void testTryListSubscriptionsByTopic1_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.listSubscriptionsByTopic(ListSubscriptionsByTopicRequest.builder()
                .topicArn("topicArn")
                .nextToken("nextToken")
                .build())).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryListSubscriptionsByTopic1());
    }

    @Test
    void testTryListSubscriptionsByTopic1_SnsClientThrowsNotFoundException() {
        // Setup
        when(mockSnsClient.listSubscriptionsByTopic(ListSubscriptionsByTopicRequest.builder()
                .topicArn("topicArn")
                .nextToken("nextToken")
                .build())).thenThrow(NotFoundException.class);

        // Run the test
        assertThrows(NotFoundException.class, () -> myClassUnderTest.tryListSubscriptionsByTopic1());
    }

    @Test
    void testTryListSubscriptionsByTopic1_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.listSubscriptionsByTopic(ListSubscriptionsByTopicRequest.builder()
                .topicArn("topicArn")
                .nextToken("nextToken")
                .build())).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryListSubscriptionsByTopic1());
    }

    @Test
    void testTryListSubscriptionsByTopic1_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.listSubscriptionsByTopic(ListSubscriptionsByTopicRequest.builder()
                .topicArn("topicArn")
                .nextToken("nextToken")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListSubscriptionsByTopic1());
    }

    @Test
    void testTryListSubscriptionsByTopic1_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.listSubscriptionsByTopic(ListSubscriptionsByTopicRequest.builder()
                .topicArn("topicArn")
                .nextToken("nextToken")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListSubscriptionsByTopic1());
    }

    @Test
    void testTryListSubscriptionsByTopic1_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.listSubscriptionsByTopic(ListSubscriptionsByTopicRequest.builder()
                .topicArn("topicArn")
                .nextToken("nextToken")
                .build())).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryListSubscriptionsByTopic1());
    }

    @Test
    void testTryListSubscriptionsByTopic2() {
        // Setup
        // Configure SnsClient.listSubscriptionsByTopic(...).
        final ListSubscriptionsByTopicResponse listSubscriptionsByTopicResponse = ListSubscriptionsByTopicResponse.builder()
                .subscriptions(Subscription.builder()
                        .subscriptionArn("subscriptionArn")
                        .owner("owner")
                        .protocol("protocol")
                        .endpoint("endpoint")
                        .topicArn("topicArn")
                        .build())
                .nextToken("nextToken")
                .build();
        when(mockSnsClient.listSubscriptionsByTopic(any(Consumer.class))).thenReturn(listSubscriptionsByTopicResponse);

        // Run the test
        myClassUnderTest.tryListSubscriptionsByTopic2();

        // Verify the results
    }

    @Test
    void testTryListSubscriptionsByTopic2_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.listSubscriptionsByTopic(any(Consumer.class))).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryListSubscriptionsByTopic2());
    }

    @Test
    void testTryListSubscriptionsByTopic2_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.listSubscriptionsByTopic(any(Consumer.class))).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryListSubscriptionsByTopic2());
    }

    @Test
    void testTryListSubscriptionsByTopic2_SnsClientThrowsNotFoundException() {
        // Setup
        when(mockSnsClient.listSubscriptionsByTopic(any(Consumer.class))).thenThrow(NotFoundException.class);

        // Run the test
        assertThrows(NotFoundException.class, () -> myClassUnderTest.tryListSubscriptionsByTopic2());
    }

    @Test
    void testTryListSubscriptionsByTopic2_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.listSubscriptionsByTopic(any(Consumer.class))).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryListSubscriptionsByTopic2());
    }

    @Test
    void testTryListSubscriptionsByTopic2_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.listSubscriptionsByTopic(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListSubscriptionsByTopic2());
    }

    @Test
    void testTryListSubscriptionsByTopic2_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.listSubscriptionsByTopic(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListSubscriptionsByTopic2());
    }

    @Test
    void testTryListSubscriptionsByTopic2_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.listSubscriptionsByTopic(any(Consumer.class))).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryListSubscriptionsByTopic2());
    }

    @Test
    void testTryListSubscriptionsByTopicPaginator1() {
        // Setup
        // Configure SnsClient.listSubscriptionsByTopicPaginator(...).
        final ListSubscriptionsByTopicIterable mockListSubscriptionsByTopicIterable = mock(
                ListSubscriptionsByTopicIterable.class);
        when(mockSnsClient.listSubscriptionsByTopicPaginator(ListSubscriptionsByTopicRequest.builder()
                .topicArn("topicArn")
                .nextToken("nextToken")
                .build())).thenReturn(mockListSubscriptionsByTopicIterable);

        // Run the test
        myClassUnderTest.tryListSubscriptionsByTopicPaginator1();

        // Verify the results
    }

    @Test
    void testTryListSubscriptionsByTopicPaginator1_SnsClientReturnsNoItems() {
        // Setup
        // Configure SnsClient.listSubscriptionsByTopicPaginator(...).
        final ListSubscriptionsByTopicIterable mockListSubscriptionsByTopicIterable = mock(
                ListSubscriptionsByTopicIterable.class);
        when(mockSnsClient.listSubscriptionsByTopicPaginator(ListSubscriptionsByTopicRequest.builder()
                .topicArn("topicArn")
                .nextToken("nextToken")
                .build())).thenReturn(mockListSubscriptionsByTopicIterable);

        // Run the test
        myClassUnderTest.tryListSubscriptionsByTopicPaginator1();

        // Verify the results
    }

    @Test
    void testTryListSubscriptionsByTopicPaginator1_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.listSubscriptionsByTopicPaginator(ListSubscriptionsByTopicRequest.builder()
                .topicArn("topicArn")
                .nextToken("nextToken")
                .build())).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryListSubscriptionsByTopicPaginator1());
    }

    @Test
    void testTryListSubscriptionsByTopicPaginator1_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.listSubscriptionsByTopicPaginator(ListSubscriptionsByTopicRequest.builder()
                .topicArn("topicArn")
                .nextToken("nextToken")
                .build())).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryListSubscriptionsByTopicPaginator1());
    }

    @Test
    void testTryListSubscriptionsByTopicPaginator1_SnsClientThrowsNotFoundException() {
        // Setup
        when(mockSnsClient.listSubscriptionsByTopicPaginator(ListSubscriptionsByTopicRequest.builder()
                .topicArn("topicArn")
                .nextToken("nextToken")
                .build())).thenThrow(NotFoundException.class);

        // Run the test
        assertThrows(NotFoundException.class, () -> myClassUnderTest.tryListSubscriptionsByTopicPaginator1());
    }

    @Test
    void testTryListSubscriptionsByTopicPaginator1_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.listSubscriptionsByTopicPaginator(ListSubscriptionsByTopicRequest.builder()
                .topicArn("topicArn")
                .nextToken("nextToken")
                .build())).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryListSubscriptionsByTopicPaginator1());
    }

    @Test
    void testTryListSubscriptionsByTopicPaginator1_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.listSubscriptionsByTopicPaginator(ListSubscriptionsByTopicRequest.builder()
                .topicArn("topicArn")
                .nextToken("nextToken")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListSubscriptionsByTopicPaginator1());
    }

    @Test
    void testTryListSubscriptionsByTopicPaginator1_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.listSubscriptionsByTopicPaginator(ListSubscriptionsByTopicRequest.builder()
                .topicArn("topicArn")
                .nextToken("nextToken")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListSubscriptionsByTopicPaginator1());
    }

    @Test
    void testTryListSubscriptionsByTopicPaginator1_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.listSubscriptionsByTopicPaginator(ListSubscriptionsByTopicRequest.builder()
                .topicArn("topicArn")
                .nextToken("nextToken")
                .build())).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryListSubscriptionsByTopicPaginator1());
    }

    @Test
    void testTryListSubscriptionsByTopicPaginator2() {
        // Setup
        // Configure SnsClient.listSubscriptionsByTopicPaginator(...).
        final ListSubscriptionsByTopicIterable mockListSubscriptionsByTopicIterable = mock(
                ListSubscriptionsByTopicIterable.class);
        when(mockSnsClient.listSubscriptionsByTopicPaginator(any(Consumer.class)))
                .thenReturn(mockListSubscriptionsByTopicIterable);

        // Run the test
        myClassUnderTest.tryListSubscriptionsByTopicPaginator2();

        // Verify the results
    }

    @Test
    void testTryListSubscriptionsByTopicPaginator2_SnsClientReturnsNoItems() {
        // Setup
        // Configure SnsClient.listSubscriptionsByTopicPaginator(...).
        final ListSubscriptionsByTopicIterable mockListSubscriptionsByTopicIterable = mock(
                ListSubscriptionsByTopicIterable.class);
        when(mockSnsClient.listSubscriptionsByTopicPaginator(any(Consumer.class)))
                .thenReturn(mockListSubscriptionsByTopicIterable);

        // Run the test
        myClassUnderTest.tryListSubscriptionsByTopicPaginator2();

        // Verify the results
    }

    @Test
    void testTryListSubscriptionsByTopicPaginator2_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.listSubscriptionsByTopicPaginator(any(Consumer.class)))
                .thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryListSubscriptionsByTopicPaginator2());
    }

    @Test
    void testTryListSubscriptionsByTopicPaginator2_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.listSubscriptionsByTopicPaginator(any(Consumer.class)))
                .thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryListSubscriptionsByTopicPaginator2());
    }

    @Test
    void testTryListSubscriptionsByTopicPaginator2_SnsClientThrowsNotFoundException() {
        // Setup
        when(mockSnsClient.listSubscriptionsByTopicPaginator(any(Consumer.class))).thenThrow(NotFoundException.class);

        // Run the test
        assertThrows(NotFoundException.class, () -> myClassUnderTest.tryListSubscriptionsByTopicPaginator2());
    }

    @Test
    void testTryListSubscriptionsByTopicPaginator2_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.listSubscriptionsByTopicPaginator(any(Consumer.class)))
                .thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryListSubscriptionsByTopicPaginator2());
    }

    @Test
    void testTryListSubscriptionsByTopicPaginator2_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.listSubscriptionsByTopicPaginator(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListSubscriptionsByTopicPaginator2());
    }

    @Test
    void testTryListSubscriptionsByTopicPaginator2_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.listSubscriptionsByTopicPaginator(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListSubscriptionsByTopicPaginator2());
    }

    @Test
    void testTryListSubscriptionsByTopicPaginator2_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.listSubscriptionsByTopicPaginator(any(Consumer.class))).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryListSubscriptionsByTopicPaginator2());
    }

    @Test
    void testTryListTagsForResource1() {
        // Setup
        // Configure SnsClient.listTagsForResource(...).
        final ListTagsForResourceResponse listTagsForResourceResponse = ListTagsForResourceResponse.builder()
                .tags(Tag.builder()
                        .key("key")
                        .value("value")
                        .build())
                .build();
        when(mockSnsClient.listTagsForResource(ListTagsForResourceRequest.builder()
                .resourceArn("resourceArn")
                .build())).thenReturn(listTagsForResourceResponse);

        // Run the test
        myClassUnderTest.tryListTagsForResource1();

        // Verify the results
    }

    @Test
    void testTryListTagsForResource1_SnsClientThrowsResourceNotFoundException() {
        // Setup
        when(mockSnsClient.listTagsForResource(ListTagsForResourceRequest.builder()
                .resourceArn("resourceArn")
                .build())).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryListTagsForResource1());
    }

    @Test
    void testTryListTagsForResource1_SnsClientThrowsTagPolicyException() {
        // Setup
        when(mockSnsClient.listTagsForResource(ListTagsForResourceRequest.builder()
                .resourceArn("resourceArn")
                .build())).thenThrow(TagPolicyException.class);

        // Run the test
        assertThrows(TagPolicyException.class, () -> myClassUnderTest.tryListTagsForResource1());
    }

    @Test
    void testTryListTagsForResource1_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.listTagsForResource(ListTagsForResourceRequest.builder()
                .resourceArn("resourceArn")
                .build())).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryListTagsForResource1());
    }

    @Test
    void testTryListTagsForResource1_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.listTagsForResource(ListTagsForResourceRequest.builder()
                .resourceArn("resourceArn")
                .build())).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryListTagsForResource1());
    }

    @Test
    void testTryListTagsForResource1_SnsClientThrowsConcurrentAccessException() {
        // Setup
        when(mockSnsClient.listTagsForResource(ListTagsForResourceRequest.builder()
                .resourceArn("resourceArn")
                .build())).thenThrow(ConcurrentAccessException.class);

        // Run the test
        assertThrows(ConcurrentAccessException.class, () -> myClassUnderTest.tryListTagsForResource1());
    }

    @Test
    void testTryListTagsForResource1_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.listTagsForResource(ListTagsForResourceRequest.builder()
                .resourceArn("resourceArn")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListTagsForResource1());
    }

    @Test
    void testTryListTagsForResource1_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.listTagsForResource(ListTagsForResourceRequest.builder()
                .resourceArn("resourceArn")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListTagsForResource1());
    }

    @Test
    void testTryListTagsForResource1_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.listTagsForResource(ListTagsForResourceRequest.builder()
                .resourceArn("resourceArn")
                .build())).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryListTagsForResource1());
    }

    @Test
    void testTryListTagsForResource2() {
        // Setup
        // Configure SnsClient.listTagsForResource(...).
        final ListTagsForResourceResponse listTagsForResourceResponse = ListTagsForResourceResponse.builder()
                .tags(Tag.builder()
                        .key("key")
                        .value("value")
                        .build())
                .build();
        when(mockSnsClient.listTagsForResource(any(Consumer.class))).thenReturn(listTagsForResourceResponse);

        // Run the test
        myClassUnderTest.tryListTagsForResource2();

        // Verify the results
    }

    @Test
    void testTryListTagsForResource2_SnsClientThrowsResourceNotFoundException() {
        // Setup
        when(mockSnsClient.listTagsForResource(any(Consumer.class))).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryListTagsForResource2());
    }

    @Test
    void testTryListTagsForResource2_SnsClientThrowsTagPolicyException() {
        // Setup
        when(mockSnsClient.listTagsForResource(any(Consumer.class))).thenThrow(TagPolicyException.class);

        // Run the test
        assertThrows(TagPolicyException.class, () -> myClassUnderTest.tryListTagsForResource2());
    }

    @Test
    void testTryListTagsForResource2_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.listTagsForResource(any(Consumer.class))).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryListTagsForResource2());
    }

    @Test
    void testTryListTagsForResource2_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.listTagsForResource(any(Consumer.class))).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryListTagsForResource2());
    }

    @Test
    void testTryListTagsForResource2_SnsClientThrowsConcurrentAccessException() {
        // Setup
        when(mockSnsClient.listTagsForResource(any(Consumer.class))).thenThrow(ConcurrentAccessException.class);

        // Run the test
        assertThrows(ConcurrentAccessException.class, () -> myClassUnderTest.tryListTagsForResource2());
    }

    @Test
    void testTryListTagsForResource2_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.listTagsForResource(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListTagsForResource2());
    }

    @Test
    void testTryListTagsForResource2_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.listTagsForResource(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListTagsForResource2());
    }

    @Test
    void testTryListTagsForResource2_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.listTagsForResource(any(Consumer.class))).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryListTagsForResource2());
    }

    @Test
    void testTryListTopics1() {
        // Setup
        // Configure SnsClient.listTopics(...).
        final ListTopicsResponse listTopicsResponse = ListTopicsResponse.builder()
                .topics(Topic.builder()
                        .topicArn("topicArn")
                        .build())
                .nextToken("nextToken")
                .build();
        when(mockSnsClient.listTopics()).thenReturn(listTopicsResponse);

        // Run the test
        myClassUnderTest.tryListTopics1();

        // Verify the results
    }

    @Test
    void testTryListTopics1_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.listTopics()).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryListTopics1());
    }

    @Test
    void testTryListTopics1_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.listTopics()).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryListTopics1());
    }

    @Test
    void testTryListTopics1_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.listTopics()).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryListTopics1());
    }

    @Test
    void testTryListTopics1_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.listTopics()).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListTopics1());
    }

    @Test
    void testTryListTopics1_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.listTopics()).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListTopics1());
    }

    @Test
    void testTryListTopics1_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.listTopics()).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryListTopics1());
    }

    @Test
    void testTryListTopics2() {
        // Setup
        // Configure SnsClient.listTopics(...).
        final ListTopicsResponse listTopicsResponse = ListTopicsResponse.builder()
                .topics(Topic.builder()
                        .topicArn("topicArn")
                        .build())
                .nextToken("nextToken")
                .build();
        when(mockSnsClient.listTopics(ListTopicsRequest.builder()
                .nextToken("nextToken")
                .build())).thenReturn(listTopicsResponse);

        // Run the test
        myClassUnderTest.tryListTopics2();

        // Verify the results
    }

    @Test
    void testTryListTopics2_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.listTopics(ListTopicsRequest.builder()
                .nextToken("nextToken")
                .build())).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryListTopics2());
    }

    @Test
    void testTryListTopics2_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.listTopics(ListTopicsRequest.builder()
                .nextToken("nextToken")
                .build())).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryListTopics2());
    }

    @Test
    void testTryListTopics2_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.listTopics(ListTopicsRequest.builder()
                .nextToken("nextToken")
                .build())).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryListTopics2());
    }

    @Test
    void testTryListTopics2_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.listTopics(ListTopicsRequest.builder()
                .nextToken("nextToken")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListTopics2());
    }

    @Test
    void testTryListTopics2_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.listTopics(ListTopicsRequest.builder()
                .nextToken("nextToken")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListTopics2());
    }

    @Test
    void testTryListTopics2_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.listTopics(ListTopicsRequest.builder()
                .nextToken("nextToken")
                .build())).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryListTopics2());
    }

    @Test
    void testTryListTopics3() {
        // Setup
        // Configure SnsClient.listTopics(...).
        final ListTopicsResponse listTopicsResponse = ListTopicsResponse.builder()
                .topics(Topic.builder()
                        .topicArn("topicArn")
                        .build())
                .nextToken("nextToken")
                .build();
        when(mockSnsClient.listTopics(any(Consumer.class))).thenReturn(listTopicsResponse);

        // Run the test
        myClassUnderTest.tryListTopics3();

        // Verify the results
    }

    @Test
    void testTryListTopics3_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.listTopics(any(Consumer.class))).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryListTopics3());
    }

    @Test
    void testTryListTopics3_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.listTopics(any(Consumer.class))).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryListTopics3());
    }

    @Test
    void testTryListTopics3_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.listTopics(any(Consumer.class))).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryListTopics3());
    }

    @Test
    void testTryListTopics3_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.listTopics(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListTopics3());
    }

    @Test
    void testTryListTopics3_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.listTopics(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListTopics3());
    }

    @Test
    void testTryListTopics3_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.listTopics(any(Consumer.class))).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryListTopics3());
    }

    @Test
    void testTryListTopicsPaginator1() {
        // Setup
        // Configure SnsClient.listTopicsPaginator(...).
        final ListTopicsIterable mockListTopicsIterable = mock(ListTopicsIterable.class);
        when(mockSnsClient.listTopicsPaginator()).thenReturn(mockListTopicsIterable);

        // Run the test
        myClassUnderTest.tryListTopicsPaginator1();

        // Verify the results
    }

    @Test
    void testTryListTopicsPaginator1_SnsClientReturnsNoItems() {
        // Setup
        // Configure SnsClient.listTopicsPaginator(...).
        final ListTopicsIterable mockListTopicsIterable = mock(ListTopicsIterable.class);
        when(mockSnsClient.listTopicsPaginator()).thenReturn(mockListTopicsIterable);

        // Run the test
        myClassUnderTest.tryListTopicsPaginator1();

        // Verify the results
    }

    @Test
    void testTryListTopicsPaginator1_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.listTopicsPaginator()).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryListTopicsPaginator1());
    }

    @Test
    void testTryListTopicsPaginator1_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.listTopicsPaginator()).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryListTopicsPaginator1());
    }

    @Test
    void testTryListTopicsPaginator1_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.listTopicsPaginator()).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryListTopicsPaginator1());
    }

    @Test
    void testTryListTopicsPaginator1_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.listTopicsPaginator()).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListTopicsPaginator1());
    }

    @Test
    void testTryListTopicsPaginator1_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.listTopicsPaginator()).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListTopicsPaginator1());
    }

    @Test
    void testTryListTopicsPaginator1_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.listTopicsPaginator()).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryListTopicsPaginator1());
    }

    @Test
    void testTryListTopicsPaginator2() {
        // Setup
        // Configure SnsClient.listTopicsPaginator(...).
        final ListTopicsIterable mockListTopicsIterable = mock(ListTopicsIterable.class);
        when(mockSnsClient.listTopicsPaginator(ListTopicsRequest.builder()
                .nextToken("nextToken")
                .build())).thenReturn(mockListTopicsIterable);

        // Run the test
        myClassUnderTest.tryListTopicsPaginator2();

        // Verify the results
    }

    @Test
    void testTryListTopicsPaginator2_SnsClientReturnsNoItems() {
        // Setup
        // Configure SnsClient.listTopicsPaginator(...).
        final ListTopicsIterable mockListTopicsIterable = mock(ListTopicsIterable.class);
        when(mockSnsClient.listTopicsPaginator(ListTopicsRequest.builder()
                .nextToken("nextToken")
                .build())).thenReturn(mockListTopicsIterable);

        // Run the test
        myClassUnderTest.tryListTopicsPaginator2();

        // Verify the results
    }

    @Test
    void testTryListTopicsPaginator2_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.listTopicsPaginator(ListTopicsRequest.builder()
                .nextToken("nextToken")
                .build())).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryListTopicsPaginator2());
    }

    @Test
    void testTryListTopicsPaginator2_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.listTopicsPaginator(ListTopicsRequest.builder()
                .nextToken("nextToken")
                .build())).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryListTopicsPaginator2());
    }

    @Test
    void testTryListTopicsPaginator2_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.listTopicsPaginator(ListTopicsRequest.builder()
                .nextToken("nextToken")
                .build())).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryListTopicsPaginator2());
    }

    @Test
    void testTryListTopicsPaginator2_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.listTopicsPaginator(ListTopicsRequest.builder()
                .nextToken("nextToken")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListTopicsPaginator2());
    }

    @Test
    void testTryListTopicsPaginator2_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.listTopicsPaginator(ListTopicsRequest.builder()
                .nextToken("nextToken")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListTopicsPaginator2());
    }

    @Test
    void testTryListTopicsPaginator2_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.listTopicsPaginator(ListTopicsRequest.builder()
                .nextToken("nextToken")
                .build())).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryListTopicsPaginator2());
    }

    @Test
    void testTryListTopicsPaginator3() {
        // Setup
        // Configure SnsClient.listTopicsPaginator(...).
        final ListTopicsIterable mockListTopicsIterable = mock(ListTopicsIterable.class);
        when(mockSnsClient.listTopicsPaginator(any(Consumer.class))).thenReturn(mockListTopicsIterable);

        // Run the test
        myClassUnderTest.tryListTopicsPaginator3();

        // Verify the results
    }

    @Test
    void testTryListTopicsPaginator3_SnsClientReturnsNoItems() {
        // Setup
        // Configure SnsClient.listTopicsPaginator(...).
        final ListTopicsIterable mockListTopicsIterable = mock(ListTopicsIterable.class);
        when(mockSnsClient.listTopicsPaginator(any(Consumer.class))).thenReturn(mockListTopicsIterable);

        // Run the test
        myClassUnderTest.tryListTopicsPaginator3();

        // Verify the results
    }

    @Test
    void testTryListTopicsPaginator3_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.listTopicsPaginator(any(Consumer.class))).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryListTopicsPaginator3());
    }

    @Test
    void testTryListTopicsPaginator3_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.listTopicsPaginator(any(Consumer.class))).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryListTopicsPaginator3());
    }

    @Test
    void testTryListTopicsPaginator3_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.listTopicsPaginator(any(Consumer.class))).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryListTopicsPaginator3());
    }

    @Test
    void testTryListTopicsPaginator3_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.listTopicsPaginator(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryListTopicsPaginator3());
    }

    @Test
    void testTryListTopicsPaginator3_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.listTopicsPaginator(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListTopicsPaginator3());
    }

    @Test
    void testTryListTopicsPaginator3_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.listTopicsPaginator(any(Consumer.class))).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryListTopicsPaginator3());
    }

    @Test
    void testTryOptInPhoneNumber1() {
        // Setup
        // Configure SnsClient.optInPhoneNumber(...).
        final OptInPhoneNumberResponse optInPhoneNumberResponse = OptInPhoneNumberResponse.builder().build();
        when(mockSnsClient.optInPhoneNumber(OptInPhoneNumberRequest.builder()
                .phoneNumber("phoneNumber")
                .build())).thenReturn(optInPhoneNumberResponse);

        // Run the test
        myClassUnderTest.tryOptInPhoneNumber1();

        // Verify the results
    }

    @Test
    void testTryOptInPhoneNumber1_SnsClientThrowsThrottledException() {
        // Setup
        when(mockSnsClient.optInPhoneNumber(OptInPhoneNumberRequest.builder()
                .phoneNumber("phoneNumber")
                .build())).thenThrow(ThrottledException.class);

        // Run the test
        assertThrows(ThrottledException.class, () -> myClassUnderTest.tryOptInPhoneNumber1());
    }

    @Test
    void testTryOptInPhoneNumber1_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.optInPhoneNumber(OptInPhoneNumberRequest.builder()
                .phoneNumber("phoneNumber")
                .build())).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryOptInPhoneNumber1());
    }

    @Test
    void testTryOptInPhoneNumber1_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.optInPhoneNumber(OptInPhoneNumberRequest.builder()
                .phoneNumber("phoneNumber")
                .build())).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryOptInPhoneNumber1());
    }

    @Test
    void testTryOptInPhoneNumber1_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.optInPhoneNumber(OptInPhoneNumberRequest.builder()
                .phoneNumber("phoneNumber")
                .build())).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryOptInPhoneNumber1());
    }

    @Test
    void testTryOptInPhoneNumber1_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.optInPhoneNumber(OptInPhoneNumberRequest.builder()
                .phoneNumber("phoneNumber")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryOptInPhoneNumber1());
    }

    @Test
    void testTryOptInPhoneNumber1_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.optInPhoneNumber(OptInPhoneNumberRequest.builder()
                .phoneNumber("phoneNumber")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryOptInPhoneNumber1());
    }

    @Test
    void testTryOptInPhoneNumber1_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.optInPhoneNumber(OptInPhoneNumberRequest.builder()
                .phoneNumber("phoneNumber")
                .build())).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryOptInPhoneNumber1());
    }

    @Test
    void testTryOptInPhoneNumber2() {
        // Setup
        // Configure SnsClient.optInPhoneNumber(...).
        final OptInPhoneNumberResponse optInPhoneNumberResponse = OptInPhoneNumberResponse.builder().build();
        when(mockSnsClient.optInPhoneNumber(any(Consumer.class))).thenReturn(optInPhoneNumberResponse);

        // Run the test
        myClassUnderTest.tryOptInPhoneNumber2();

        // Verify the results
    }

    @Test
    void testTryOptInPhoneNumber2_SnsClientThrowsThrottledException() {
        // Setup
        when(mockSnsClient.optInPhoneNumber(any(Consumer.class))).thenThrow(ThrottledException.class);

        // Run the test
        assertThrows(ThrottledException.class, () -> myClassUnderTest.tryOptInPhoneNumber2());
    }

    @Test
    void testTryOptInPhoneNumber2_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.optInPhoneNumber(any(Consumer.class))).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryOptInPhoneNumber2());
    }

    @Test
    void testTryOptInPhoneNumber2_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.optInPhoneNumber(any(Consumer.class))).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryOptInPhoneNumber2());
    }

    @Test
    void testTryOptInPhoneNumber2_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.optInPhoneNumber(any(Consumer.class))).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryOptInPhoneNumber2());
    }

    @Test
    void testTryOptInPhoneNumber2_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.optInPhoneNumber(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryOptInPhoneNumber2());
    }

    @Test
    void testTryOptInPhoneNumber2_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.optInPhoneNumber(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryOptInPhoneNumber2());
    }

    @Test
    void testTryOptInPhoneNumber2_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.optInPhoneNumber(any(Consumer.class))).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryOptInPhoneNumber2());
    }

    @Test
    void testTryPublish1() {
        // Setup
        // Configure SnsClient.publish(...).
        final PublishResponse publishResponse = PublishResponse.builder()
                .messageId("messageId")
                .build();
        when(mockSnsClient.publish(PublishRequest.builder()
                .topicArn("topicArn")
                .message("message")
                .subject("subject")
                .build())).thenReturn(publishResponse);

        // Run the test
        myClassUnderTest.tryPublish1();

        // Verify the results
    }

    @Test
    void testTryPublish1_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.publish(PublishRequest.builder()
                .topicArn("topicArn")
                .message("message")
                .subject("subject")
                .build())).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryPublish1());
    }

    @Test
    void testTryPublish1_SnsClientThrowsInvalidParameterValueException() {
        // Setup
        when(mockSnsClient.publish(PublishRequest.builder()
                .topicArn("topicArn")
                .message("message")
                .subject("subject")
                .build())).thenThrow(InvalidParameterValueException.class);

        // Run the test
        assertThrows(InvalidParameterValueException.class, () -> myClassUnderTest.tryPublish1());
    }

    @Test
    void testTryPublish1_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.publish(PublishRequest.builder()
                .topicArn("topicArn")
                .message("message")
                .subject("subject")
                .build())).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryPublish1());
    }

    @Test
    void testTryPublish1_SnsClientThrowsNotFoundException() {
        // Setup
        when(mockSnsClient.publish(PublishRequest.builder()
                .topicArn("topicArn")
                .message("message")
                .subject("subject")
                .build())).thenThrow(NotFoundException.class);

        // Run the test
        assertThrows(NotFoundException.class, () -> myClassUnderTest.tryPublish1());
    }

    @Test
    void testTryPublish1_SnsClientThrowsEndpointDisabledException() {
        // Setup
        when(mockSnsClient.publish(PublishRequest.builder()
                .topicArn("topicArn")
                .message("message")
                .subject("subject")
                .build())).thenThrow(EndpointDisabledException.class);

        // Run the test
        assertThrows(EndpointDisabledException.class, () -> myClassUnderTest.tryPublish1());
    }

    @Test
    void testTryPublish1_SnsClientThrowsPlatformApplicationDisabledException() {
        // Setup
        when(mockSnsClient.publish(PublishRequest.builder()
                .topicArn("topicArn")
                .message("message")
                .subject("subject")
                .build())).thenThrow(PlatformApplicationDisabledException.class);

        // Run the test
        assertThrows(PlatformApplicationDisabledException.class, () -> myClassUnderTest.tryPublish1());
    }

    @Test
    void testTryPublish1_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.publish(PublishRequest.builder()
                .topicArn("topicArn")
                .message("message")
                .subject("subject")
                .build())).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryPublish1());
    }

    @Test
    void testTryPublish1_SnsClientThrowsKmsDisabledException() {
        // Setup
        when(mockSnsClient.publish(PublishRequest.builder()
                .topicArn("topicArn")
                .message("message")
                .subject("subject")
                .build())).thenThrow(KmsDisabledException.class);

        // Run the test
        assertThrows(KmsDisabledException.class, () -> myClassUnderTest.tryPublish1());
    }

    @Test
    void testTryPublish1_SnsClientThrowsKmsInvalidStateException() {
        // Setup
        when(mockSnsClient.publish(PublishRequest.builder()
                .topicArn("topicArn")
                .message("message")
                .subject("subject")
                .build())).thenThrow(KmsInvalidStateException.class);

        // Run the test
        assertThrows(KmsInvalidStateException.class, () -> myClassUnderTest.tryPublish1());
    }

    @Test
    void testTryPublish1_SnsClientThrowsKmsNotFoundException() {
        // Setup
        when(mockSnsClient.publish(PublishRequest.builder()
                .topicArn("topicArn")
                .message("message")
                .subject("subject")
                .build())).thenThrow(KmsNotFoundException.class);

        // Run the test
        assertThrows(KmsNotFoundException.class, () -> myClassUnderTest.tryPublish1());
    }

    @Test
    void testTryPublish1_SnsClientThrowsKmsOptInRequiredException() {
        // Setup
        when(mockSnsClient.publish(PublishRequest.builder()
                .topicArn("topicArn")
                .message("message")
                .subject("subject")
                .build())).thenThrow(KmsOptInRequiredException.class);

        // Run the test
        assertThrows(KmsOptInRequiredException.class, () -> myClassUnderTest.tryPublish1());
    }

    @Test
    void testTryPublish1_SnsClientThrowsKmsThrottlingException() {
        // Setup
        when(mockSnsClient.publish(PublishRequest.builder()
                .topicArn("topicArn")
                .message("message")
                .subject("subject")
                .build())).thenThrow(KmsThrottlingException.class);

        // Run the test
        assertThrows(KmsThrottlingException.class, () -> myClassUnderTest.tryPublish1());
    }

    @Test
    void testTryPublish1_SnsClientThrowsKmsAccessDeniedException() {
        // Setup
        when(mockSnsClient.publish(PublishRequest.builder()
                .topicArn("topicArn")
                .message("message")
                .subject("subject")
                .build())).thenThrow(KmsAccessDeniedException.class);

        // Run the test
        assertThrows(KmsAccessDeniedException.class, () -> myClassUnderTest.tryPublish1());
    }

    @Test
    void testTryPublish1_SnsClientThrowsInvalidSecurityException() {
        // Setup
        when(mockSnsClient.publish(PublishRequest.builder()
                .topicArn("topicArn")
                .message("message")
                .subject("subject")
                .build())).thenThrow(InvalidSecurityException.class);

        // Run the test
        assertThrows(InvalidSecurityException.class, () -> myClassUnderTest.tryPublish1());
    }

    @Test
    void testTryPublish1_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.publish(PublishRequest.builder()
                .topicArn("topicArn")
                .message("message")
                .subject("subject")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryPublish1());
    }

    @Test
    void testTryPublish1_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.publish(PublishRequest.builder()
                .topicArn("topicArn")
                .message("message")
                .subject("subject")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryPublish1());
    }

    @Test
    void testTryPublish1_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.publish(PublishRequest.builder()
                .topicArn("topicArn")
                .message("message")
                .subject("subject")
                .build())).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryPublish1());
    }

    @Test
    void testTryPublish2() {
        // Setup
        // Configure SnsClient.publish(...).
        final PublishResponse publishResponse = PublishResponse.builder()
                .messageId("messageId")
                .build();
        when(mockSnsClient.publish(any(Consumer.class))).thenReturn(publishResponse);

        // Run the test
        myClassUnderTest.tryPublish2();

        // Verify the results
    }

    @Test
    void testTryPublish2_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.publish(any(Consumer.class))).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryPublish2());
    }

    @Test
    void testTryPublish2_SnsClientThrowsInvalidParameterValueException() {
        // Setup
        when(mockSnsClient.publish(any(Consumer.class))).thenThrow(InvalidParameterValueException.class);

        // Run the test
        assertThrows(InvalidParameterValueException.class, () -> myClassUnderTest.tryPublish2());
    }

    @Test
    void testTryPublish2_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.publish(any(Consumer.class))).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryPublish2());
    }

    @Test
    void testTryPublish2_SnsClientThrowsNotFoundException() {
        // Setup
        when(mockSnsClient.publish(any(Consumer.class))).thenThrow(NotFoundException.class);

        // Run the test
        assertThrows(NotFoundException.class, () -> myClassUnderTest.tryPublish2());
    }

    @Test
    void testTryPublish2_SnsClientThrowsEndpointDisabledException() {
        // Setup
        when(mockSnsClient.publish(any(Consumer.class))).thenThrow(EndpointDisabledException.class);

        // Run the test
        assertThrows(EndpointDisabledException.class, () -> myClassUnderTest.tryPublish2());
    }

    @Test
    void testTryPublish2_SnsClientThrowsPlatformApplicationDisabledException() {
        // Setup
        when(mockSnsClient.publish(any(Consumer.class))).thenThrow(PlatformApplicationDisabledException.class);

        // Run the test
        assertThrows(PlatformApplicationDisabledException.class, () -> myClassUnderTest.tryPublish2());
    }

    @Test
    void testTryPublish2_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.publish(any(Consumer.class))).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryPublish2());
    }

    @Test
    void testTryPublish2_SnsClientThrowsKmsDisabledException() {
        // Setup
        when(mockSnsClient.publish(any(Consumer.class))).thenThrow(KmsDisabledException.class);

        // Run the test
        assertThrows(KmsDisabledException.class, () -> myClassUnderTest.tryPublish2());
    }

    @Test
    void testTryPublish2_SnsClientThrowsKmsInvalidStateException() {
        // Setup
        when(mockSnsClient.publish(any(Consumer.class))).thenThrow(KmsInvalidStateException.class);

        // Run the test
        assertThrows(KmsInvalidStateException.class, () -> myClassUnderTest.tryPublish2());
    }

    @Test
    void testTryPublish2_SnsClientThrowsKmsNotFoundException() {
        // Setup
        when(mockSnsClient.publish(any(Consumer.class))).thenThrow(KmsNotFoundException.class);

        // Run the test
        assertThrows(KmsNotFoundException.class, () -> myClassUnderTest.tryPublish2());
    }

    @Test
    void testTryPublish2_SnsClientThrowsKmsOptInRequiredException() {
        // Setup
        when(mockSnsClient.publish(any(Consumer.class))).thenThrow(KmsOptInRequiredException.class);

        // Run the test
        assertThrows(KmsOptInRequiredException.class, () -> myClassUnderTest.tryPublish2());
    }

    @Test
    void testTryPublish2_SnsClientThrowsKmsThrottlingException() {
        // Setup
        when(mockSnsClient.publish(any(Consumer.class))).thenThrow(KmsThrottlingException.class);

        // Run the test
        assertThrows(KmsThrottlingException.class, () -> myClassUnderTest.tryPublish2());
    }

    @Test
    void testTryPublish2_SnsClientThrowsKmsAccessDeniedException() {
        // Setup
        when(mockSnsClient.publish(any(Consumer.class))).thenThrow(KmsAccessDeniedException.class);

        // Run the test
        assertThrows(KmsAccessDeniedException.class, () -> myClassUnderTest.tryPublish2());
    }

    @Test
    void testTryPublish2_SnsClientThrowsInvalidSecurityException() {
        // Setup
        when(mockSnsClient.publish(any(Consumer.class))).thenThrow(InvalidSecurityException.class);

        // Run the test
        assertThrows(InvalidSecurityException.class, () -> myClassUnderTest.tryPublish2());
    }

    @Test
    void testTryPublish2_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.publish(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryPublish2());
    }

    @Test
    void testTryPublish2_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.publish(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryPublish2());
    }

    @Test
    void testTryPublish2_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.publish(any(Consumer.class))).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryPublish2());
    }

    @Test
    void testTryRemovePermission1() {
        // Setup
        // Configure SnsClient.removePermission(...).
        final RemovePermissionResponse removePermissionResponse = RemovePermissionResponse.builder().build();
        when(mockSnsClient.removePermission(RemovePermissionRequest.builder()
                .topicArn("topicArn")
                .label("label")
                .build())).thenReturn(removePermissionResponse);

        // Run the test
        myClassUnderTest.tryRemovePermission1();

        // Verify the results
    }

    @Test
    void testTryRemovePermission1_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.removePermission(RemovePermissionRequest.builder()
                .topicArn("topicArn")
                .label("label")
                .build())).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryRemovePermission1());
    }

    @Test
    void testTryRemovePermission1_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.removePermission(RemovePermissionRequest.builder()
                .topicArn("topicArn")
                .label("label")
                .build())).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryRemovePermission1());
    }

    @Test
    void testTryRemovePermission1_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.removePermission(RemovePermissionRequest.builder()
                .topicArn("topicArn")
                .label("label")
                .build())).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryRemovePermission1());
    }

    @Test
    void testTryRemovePermission1_SnsClientThrowsNotFoundException() {
        // Setup
        when(mockSnsClient.removePermission(RemovePermissionRequest.builder()
                .topicArn("topicArn")
                .label("label")
                .build())).thenThrow(NotFoundException.class);

        // Run the test
        assertThrows(NotFoundException.class, () -> myClassUnderTest.tryRemovePermission1());
    }

    @Test
    void testTryRemovePermission1_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.removePermission(RemovePermissionRequest.builder()
                .topicArn("topicArn")
                .label("label")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryRemovePermission1());
    }

    @Test
    void testTryRemovePermission1_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.removePermission(RemovePermissionRequest.builder()
                .topicArn("topicArn")
                .label("label")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryRemovePermission1());
    }

    @Test
    void testTryRemovePermission1_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.removePermission(RemovePermissionRequest.builder()
                .topicArn("topicArn")
                .label("label")
                .build())).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryRemovePermission1());
    }

    @Test
    void testTryRemovePermission2() {
        // Setup
        // Configure SnsClient.removePermission(...).
        final RemovePermissionResponse removePermissionResponse = RemovePermissionResponse.builder().build();
        when(mockSnsClient.removePermission(any(Consumer.class))).thenReturn(removePermissionResponse);

        // Run the test
        myClassUnderTest.tryRemovePermission2();

        // Verify the results
    }

    @Test
    void testTryRemovePermission2_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.removePermission(any(Consumer.class))).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryRemovePermission2());
    }

    @Test
    void testTryRemovePermission2_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.removePermission(any(Consumer.class))).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryRemovePermission2());
    }

    @Test
    void testTryRemovePermission2_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.removePermission(any(Consumer.class))).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryRemovePermission2());
    }

    @Test
    void testTryRemovePermission2_SnsClientThrowsNotFoundException() {
        // Setup
        when(mockSnsClient.removePermission(any(Consumer.class))).thenThrow(NotFoundException.class);

        // Run the test
        assertThrows(NotFoundException.class, () -> myClassUnderTest.tryRemovePermission2());
    }

    @Test
    void testTryRemovePermission2_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.removePermission(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryRemovePermission2());
    }

    @Test
    void testTryRemovePermission2_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.removePermission(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryRemovePermission2());
    }

    @Test
    void testTryRemovePermission2_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.removePermission(any(Consumer.class))).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryRemovePermission2());
    }

    @Test
    void testTrySetEndpointAttributes1() {
        // Setup
        // Configure SnsClient.setEndpointAttributes(...).
        final SetEndpointAttributesResponse setEndpointAttributesResponse = SetEndpointAttributesResponse.builder().build();
        when(mockSnsClient.setEndpointAttributes(SetEndpointAttributesRequest.builder()
                .endpointArn("endpointArn")
                .attributes(new HashMap<>())
                .build())).thenReturn(setEndpointAttributesResponse);

        // Run the test
        myClassUnderTest.trySetEndpointAttributes1();

        // Verify the results
    }

    @Test
    void testTrySetEndpointAttributes1_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.setEndpointAttributes(SetEndpointAttributesRequest.builder()
                .endpointArn("endpointArn")
                .attributes(new HashMap<>())
                .build())).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.trySetEndpointAttributes1());
    }

    @Test
    void testTrySetEndpointAttributes1_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.setEndpointAttributes(SetEndpointAttributesRequest.builder()
                .endpointArn("endpointArn")
                .attributes(new HashMap<>())
                .build())).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.trySetEndpointAttributes1());
    }

    @Test
    void testTrySetEndpointAttributes1_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.setEndpointAttributes(SetEndpointAttributesRequest.builder()
                .endpointArn("endpointArn")
                .attributes(new HashMap<>())
                .build())).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.trySetEndpointAttributes1());
    }

    @Test
    void testTrySetEndpointAttributes1_SnsClientThrowsNotFoundException() {
        // Setup
        when(mockSnsClient.setEndpointAttributes(SetEndpointAttributesRequest.builder()
                .endpointArn("endpointArn")
                .attributes(new HashMap<>())
                .build())).thenThrow(NotFoundException.class);

        // Run the test
        assertThrows(NotFoundException.class, () -> myClassUnderTest.trySetEndpointAttributes1());
    }

    @Test
    void testTrySetEndpointAttributes1_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.setEndpointAttributes(SetEndpointAttributesRequest.builder()
                .endpointArn("endpointArn")
                .attributes(new HashMap<>())
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.trySetEndpointAttributes1());
    }

    @Test
    void testTrySetEndpointAttributes1_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.setEndpointAttributes(SetEndpointAttributesRequest.builder()
                .endpointArn("endpointArn")
                .attributes(new HashMap<>())
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.trySetEndpointAttributes1());
    }

    @Test
    void testTrySetEndpointAttributes1_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.setEndpointAttributes(SetEndpointAttributesRequest.builder()
                .endpointArn("endpointArn")
                .attributes(new HashMap<>())
                .build())).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.trySetEndpointAttributes1());
    }

    @Test
    void testTrySetEndpointAttributes2() {
        // Setup
        // Configure SnsClient.setEndpointAttributes(...).
        final SetEndpointAttributesResponse setEndpointAttributesResponse = SetEndpointAttributesResponse.builder().build();
        when(mockSnsClient.setEndpointAttributes(any(Consumer.class))).thenReturn(setEndpointAttributesResponse);

        // Run the test
        myClassUnderTest.trySetEndpointAttributes2();

        // Verify the results
    }

    @Test
    void testTrySetEndpointAttributes2_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.setEndpointAttributes(any(Consumer.class))).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.trySetEndpointAttributes2());
    }

    @Test
    void testTrySetEndpointAttributes2_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.setEndpointAttributes(any(Consumer.class))).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.trySetEndpointAttributes2());
    }

    @Test
    void testTrySetEndpointAttributes2_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.setEndpointAttributes(any(Consumer.class))).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.trySetEndpointAttributes2());
    }

    @Test
    void testTrySetEndpointAttributes2_SnsClientThrowsNotFoundException() {
        // Setup
        when(mockSnsClient.setEndpointAttributes(any(Consumer.class))).thenThrow(NotFoundException.class);

        // Run the test
        assertThrows(NotFoundException.class, () -> myClassUnderTest.trySetEndpointAttributes2());
    }

    @Test
    void testTrySetEndpointAttributes2_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.setEndpointAttributes(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.trySetEndpointAttributes2());
    }

    @Test
    void testTrySetEndpointAttributes2_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.setEndpointAttributes(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.trySetEndpointAttributes2());
    }

    @Test
    void testTrySetEndpointAttributes2_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.setEndpointAttributes(any(Consumer.class))).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.trySetEndpointAttributes2());
    }

    @Test
    void testTrySetPlatformApplicationAttributes1() {
        // Setup
        // Configure SnsClient.setPlatformApplicationAttributes(...).
        final SetPlatformApplicationAttributesResponse setPlatformApplicationAttributesResponse = SetPlatformApplicationAttributesResponse.builder().build();
        when(mockSnsClient.setPlatformApplicationAttributes(SetPlatformApplicationAttributesRequest.builder()
                .platformApplicationArn("platformApplicationArn")
                .attributes(new HashMap<>())
                .build())).thenReturn(setPlatformApplicationAttributesResponse);

        // Run the test
        myClassUnderTest.trySetPlatformApplicationAttributes1();

        // Verify the results
    }

    @Test
    void testTrySetPlatformApplicationAttributes1_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.setPlatformApplicationAttributes(SetPlatformApplicationAttributesRequest.builder()
                .platformApplicationArn("platformApplicationArn")
                .attributes(new HashMap<>())
                .build())).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.trySetPlatformApplicationAttributes1());
    }

    @Test
    void testTrySetPlatformApplicationAttributes1_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.setPlatformApplicationAttributes(SetPlatformApplicationAttributesRequest.builder()
                .platformApplicationArn("platformApplicationArn")
                .attributes(new HashMap<>())
                .build())).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.trySetPlatformApplicationAttributes1());
    }

    @Test
    void testTrySetPlatformApplicationAttributes1_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.setPlatformApplicationAttributes(SetPlatformApplicationAttributesRequest.builder()
                .platformApplicationArn("platformApplicationArn")
                .attributes(new HashMap<>())
                .build())).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.trySetPlatformApplicationAttributes1());
    }

    @Test
    void testTrySetPlatformApplicationAttributes1_SnsClientThrowsNotFoundException() {
        // Setup
        when(mockSnsClient.setPlatformApplicationAttributes(SetPlatformApplicationAttributesRequest.builder()
                .platformApplicationArn("platformApplicationArn")
                .attributes(new HashMap<>())
                .build())).thenThrow(NotFoundException.class);

        // Run the test
        assertThrows(NotFoundException.class, () -> myClassUnderTest.trySetPlatformApplicationAttributes1());
    }

    @Test
    void testTrySetPlatformApplicationAttributes1_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.setPlatformApplicationAttributes(SetPlatformApplicationAttributesRequest.builder()
                .platformApplicationArn("platformApplicationArn")
                .attributes(new HashMap<>())
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.trySetPlatformApplicationAttributes1());
    }

    @Test
    void testTrySetPlatformApplicationAttributes1_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.setPlatformApplicationAttributes(SetPlatformApplicationAttributesRequest.builder()
                .platformApplicationArn("platformApplicationArn")
                .attributes(new HashMap<>())
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.trySetPlatformApplicationAttributes1());
    }

    @Test
    void testTrySetPlatformApplicationAttributes1_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.setPlatformApplicationAttributes(SetPlatformApplicationAttributesRequest.builder()
                .platformApplicationArn("platformApplicationArn")
                .attributes(new HashMap<>())
                .build())).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.trySetPlatformApplicationAttributes1());
    }

    @Test
    void testTrySetPlatformApplicationAttributes2() {
        // Setup
        // Configure SnsClient.setPlatformApplicationAttributes(...).
        final SetPlatformApplicationAttributesResponse setPlatformApplicationAttributesResponse = SetPlatformApplicationAttributesResponse.builder().build();
        when(mockSnsClient.setPlatformApplicationAttributes(any(Consumer.class)))
                .thenReturn(setPlatformApplicationAttributesResponse);

        // Run the test
        myClassUnderTest.trySetPlatformApplicationAttributes2();

        // Verify the results
    }

    @Test
    void testTrySetPlatformApplicationAttributes2_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.setPlatformApplicationAttributes(any(Consumer.class)))
                .thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.trySetPlatformApplicationAttributes2());
    }

    @Test
    void testTrySetPlatformApplicationAttributes2_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.setPlatformApplicationAttributes(any(Consumer.class)))
                .thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.trySetPlatformApplicationAttributes2());
    }

    @Test
    void testTrySetPlatformApplicationAttributes2_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.setPlatformApplicationAttributes(any(Consumer.class)))
                .thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.trySetPlatformApplicationAttributes2());
    }

    @Test
    void testTrySetPlatformApplicationAttributes2_SnsClientThrowsNotFoundException() {
        // Setup
        when(mockSnsClient.setPlatformApplicationAttributes(any(Consumer.class))).thenThrow(NotFoundException.class);

        // Run the test
        assertThrows(NotFoundException.class, () -> myClassUnderTest.trySetPlatformApplicationAttributes2());
    }

    @Test
    void testTrySetPlatformApplicationAttributes2_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.setPlatformApplicationAttributes(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.trySetPlatformApplicationAttributes2());
    }

    @Test
    void testTrySetPlatformApplicationAttributes2_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.setPlatformApplicationAttributes(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.trySetPlatformApplicationAttributes2());
    }

    @Test
    void testTrySetPlatformApplicationAttributes2_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.setPlatformApplicationAttributes(any(Consumer.class))).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.trySetPlatformApplicationAttributes2());
    }

    @Test
    void testTrySetSMSAttributes1() {
        // Setup
        // Configure SnsClient.setSMSAttributes(...).
        final SetSmsAttributesResponse setSmsAttributesResponse = SetSmsAttributesResponse.builder().build();
        when(mockSnsClient.setSMSAttributes(SetSmsAttributesRequest.builder()
                .attributes(new HashMap<>())
                .build())).thenReturn(setSmsAttributesResponse);

        // Run the test
        myClassUnderTest.trySetSMSAttributes1();

        // Verify the results
    }

    @Test
    void testTrySetSMSAttributes1_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.setSMSAttributes(SetSmsAttributesRequest.builder()
                .attributes(new HashMap<>())
                .build())).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.trySetSMSAttributes1());
    }

    @Test
    void testTrySetSMSAttributes1_SnsClientThrowsThrottledException() {
        // Setup
        when(mockSnsClient.setSMSAttributes(SetSmsAttributesRequest.builder()
                .attributes(new HashMap<>())
                .build())).thenThrow(ThrottledException.class);

        // Run the test
        assertThrows(ThrottledException.class, () -> myClassUnderTest.trySetSMSAttributes1());
    }

    @Test
    void testTrySetSMSAttributes1_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.setSMSAttributes(SetSmsAttributesRequest.builder()
                .attributes(new HashMap<>())
                .build())).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.trySetSMSAttributes1());
    }

    @Test
    void testTrySetSMSAttributes1_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.setSMSAttributes(SetSmsAttributesRequest.builder()
                .attributes(new HashMap<>())
                .build())).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.trySetSMSAttributes1());
    }

    @Test
    void testTrySetSMSAttributes1_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.setSMSAttributes(SetSmsAttributesRequest.builder()
                .attributes(new HashMap<>())
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.trySetSMSAttributes1());
    }

    @Test
    void testTrySetSMSAttributes1_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.setSMSAttributes(SetSmsAttributesRequest.builder()
                .attributes(new HashMap<>())
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.trySetSMSAttributes1());
    }

    @Test
    void testTrySetSMSAttributes1_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.setSMSAttributes(SetSmsAttributesRequest.builder()
                .attributes(new HashMap<>())
                .build())).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.trySetSMSAttributes1());
    }

    @Test
    void testTrySetSMSAttributes2() {
        // Setup
        // Configure SnsClient.setSMSAttributes(...).
        final SetSmsAttributesResponse setSmsAttributesResponse = SetSmsAttributesResponse.builder().build();
        when(mockSnsClient.setSMSAttributes(any(Consumer.class))).thenReturn(setSmsAttributesResponse);

        // Run the test
        myClassUnderTest.trySetSMSAttributes2();

        // Verify the results
    }

    @Test
    void testTrySetSMSAttributes2_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.setSMSAttributes(any(Consumer.class))).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.trySetSMSAttributes2());
    }

    @Test
    void testTrySetSMSAttributes2_SnsClientThrowsThrottledException() {
        // Setup
        when(mockSnsClient.setSMSAttributes(any(Consumer.class))).thenThrow(ThrottledException.class);

        // Run the test
        assertThrows(ThrottledException.class, () -> myClassUnderTest.trySetSMSAttributes2());
    }

    @Test
    void testTrySetSMSAttributes2_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.setSMSAttributes(any(Consumer.class))).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.trySetSMSAttributes2());
    }

    @Test
    void testTrySetSMSAttributes2_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.setSMSAttributes(any(Consumer.class))).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.trySetSMSAttributes2());
    }

    @Test
    void testTrySetSMSAttributes2_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.setSMSAttributes(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.trySetSMSAttributes2());
    }

    @Test
    void testTrySetSMSAttributes2_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.setSMSAttributes(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.trySetSMSAttributes2());
    }

    @Test
    void testTrySetSMSAttributes2_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.setSMSAttributes(any(Consumer.class))).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.trySetSMSAttributes2());
    }

    @Test
    void testTrySetSubscriptionAttributes1() {
        // Setup
        // Configure SnsClient.setSubscriptionAttributes(...).
        final SetSubscriptionAttributesResponse setSubscriptionAttributesResponse = SetSubscriptionAttributesResponse.builder().build();
        when(mockSnsClient.setSubscriptionAttributes(SetSubscriptionAttributesRequest.builder()
                .subscriptionArn("subscriptionArn")
                .attributeName("attributeName")
                .attributeValue("attributeValue")
                .build())).thenReturn(setSubscriptionAttributesResponse);

        // Run the test
        myClassUnderTest.trySetSubscriptionAttributes1();

        // Verify the results
    }

    @Test
    void testTrySetSubscriptionAttributes1_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.setSubscriptionAttributes(SetSubscriptionAttributesRequest.builder()
                .subscriptionArn("subscriptionArn")
                .attributeName("attributeName")
                .attributeValue("attributeValue")
                .build())).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.trySetSubscriptionAttributes1());
    }

    @Test
    void testTrySetSubscriptionAttributes1_SnsClientThrowsFilterPolicyLimitExceededException() {
        // Setup
        when(mockSnsClient.setSubscriptionAttributes(SetSubscriptionAttributesRequest.builder()
                .subscriptionArn("subscriptionArn")
                .attributeName("attributeName")
                .attributeValue("attributeValue")
                .build())).thenThrow(FilterPolicyLimitExceededException.class);

        // Run the test
        assertThrows(FilterPolicyLimitExceededException.class, () -> myClassUnderTest.trySetSubscriptionAttributes1());
    }

    @Test
    void testTrySetSubscriptionAttributes1_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.setSubscriptionAttributes(SetSubscriptionAttributesRequest.builder()
                .subscriptionArn("subscriptionArn")
                .attributeName("attributeName")
                .attributeValue("attributeValue")
                .build())).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.trySetSubscriptionAttributes1());
    }

    @Test
    void testTrySetSubscriptionAttributes1_SnsClientThrowsNotFoundException() {
        // Setup
        when(mockSnsClient.setSubscriptionAttributes(SetSubscriptionAttributesRequest.builder()
                .subscriptionArn("subscriptionArn")
                .attributeName("attributeName")
                .attributeValue("attributeValue")
                .build())).thenThrow(NotFoundException.class);

        // Run the test
        assertThrows(NotFoundException.class, () -> myClassUnderTest.trySetSubscriptionAttributes1());
    }

    @Test
    void testTrySetSubscriptionAttributes1_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.setSubscriptionAttributes(SetSubscriptionAttributesRequest.builder()
                .subscriptionArn("subscriptionArn")
                .attributeName("attributeName")
                .attributeValue("attributeValue")
                .build())).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.trySetSubscriptionAttributes1());
    }

    @Test
    void testTrySetSubscriptionAttributes1_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.setSubscriptionAttributes(SetSubscriptionAttributesRequest.builder()
                .subscriptionArn("subscriptionArn")
                .attributeName("attributeName")
                .attributeValue("attributeValue")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.trySetSubscriptionAttributes1());
    }

    @Test
    void testTrySetSubscriptionAttributes1_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.setSubscriptionAttributes(SetSubscriptionAttributesRequest.builder()
                .subscriptionArn("subscriptionArn")
                .attributeName("attributeName")
                .attributeValue("attributeValue")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.trySetSubscriptionAttributes1());
    }

    @Test
    void testTrySetSubscriptionAttributes1_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.setSubscriptionAttributes(SetSubscriptionAttributesRequest.builder()
                .subscriptionArn("subscriptionArn")
                .attributeName("attributeName")
                .attributeValue("attributeValue")
                .build())).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.trySetSubscriptionAttributes1());
    }

    @Test
    void testTrySetSubscriptionAttributes2() {
        // Setup
        // Configure SnsClient.setSubscriptionAttributes(...).
        final SetSubscriptionAttributesResponse setSubscriptionAttributesResponse = SetSubscriptionAttributesResponse.builder().build();
        when(mockSnsClient.setSubscriptionAttributes(any(Consumer.class)))
                .thenReturn(setSubscriptionAttributesResponse);

        // Run the test
        myClassUnderTest.trySetSubscriptionAttributes2();

        // Verify the results
    }

    @Test
    void testTrySetSubscriptionAttributes2_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.setSubscriptionAttributes(any(Consumer.class))).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.trySetSubscriptionAttributes2());
    }

    @Test
    void testTrySetSubscriptionAttributes2_SnsClientThrowsFilterPolicyLimitExceededException() {
        // Setup
        when(mockSnsClient.setSubscriptionAttributes(any(Consumer.class)))
                .thenThrow(FilterPolicyLimitExceededException.class);

        // Run the test
        assertThrows(FilterPolicyLimitExceededException.class, () -> myClassUnderTest.trySetSubscriptionAttributes2());
    }

    @Test
    void testTrySetSubscriptionAttributes2_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.setSubscriptionAttributes(any(Consumer.class))).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.trySetSubscriptionAttributes2());
    }

    @Test
    void testTrySetSubscriptionAttributes2_SnsClientThrowsNotFoundException() {
        // Setup
        when(mockSnsClient.setSubscriptionAttributes(any(Consumer.class))).thenThrow(NotFoundException.class);

        // Run the test
        assertThrows(NotFoundException.class, () -> myClassUnderTest.trySetSubscriptionAttributes2());
    }

    @Test
    void testTrySetSubscriptionAttributes2_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.setSubscriptionAttributes(any(Consumer.class))).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.trySetSubscriptionAttributes2());
    }

    @Test
    void testTrySetSubscriptionAttributes2_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.setSubscriptionAttributes(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.trySetSubscriptionAttributes2());
    }

    @Test
    void testTrySetSubscriptionAttributes2_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.setSubscriptionAttributes(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.trySetSubscriptionAttributes2());
    }

    @Test
    void testTrySetSubscriptionAttributes2_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.setSubscriptionAttributes(any(Consumer.class))).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.trySetSubscriptionAttributes2());
    }

    @Test
    void testTrySetTopicAttributes1() {
        // Setup
        // Configure SnsClient.setTopicAttributes(...).
        final SetTopicAttributesResponse setTopicAttributesResponse = SetTopicAttributesResponse.builder().build();
        when(mockSnsClient.setTopicAttributes(SetTopicAttributesRequest.builder()
                .topicArn("topicArn")
                .attributeName("attributeName")
                .attributeValue("attributeValue")
                .build())).thenReturn(setTopicAttributesResponse);

        // Run the test
        myClassUnderTest.trySetTopicAttributes1();

        // Verify the results
    }

    @Test
    void testTrySetTopicAttributes1_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.setTopicAttributes(SetTopicAttributesRequest.builder()
                .topicArn("topicArn")
                .attributeName("attributeName")
                .attributeValue("attributeValue")
                .build())).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.trySetTopicAttributes1());
    }

    @Test
    void testTrySetTopicAttributes1_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.setTopicAttributes(SetTopicAttributesRequest.builder()
                .topicArn("topicArn")
                .attributeName("attributeName")
                .attributeValue("attributeValue")
                .build())).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.trySetTopicAttributes1());
    }

    @Test
    void testTrySetTopicAttributes1_SnsClientThrowsNotFoundException() {
        // Setup
        when(mockSnsClient.setTopicAttributes(SetTopicAttributesRequest.builder()
                .topicArn("topicArn")
                .attributeName("attributeName")
                .attributeValue("attributeValue")
                .build())).thenThrow(NotFoundException.class);

        // Run the test
        assertThrows(NotFoundException.class, () -> myClassUnderTest.trySetTopicAttributes1());
    }

    @Test
    void testTrySetTopicAttributes1_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.setTopicAttributes(SetTopicAttributesRequest.builder()
                .topicArn("topicArn")
                .attributeName("attributeName")
                .attributeValue("attributeValue")
                .build())).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.trySetTopicAttributes1());
    }

    @Test
    void testTrySetTopicAttributes1_SnsClientThrowsInvalidSecurityException() {
        // Setup
        when(mockSnsClient.setTopicAttributes(SetTopicAttributesRequest.builder()
                .topicArn("topicArn")
                .attributeName("attributeName")
                .attributeValue("attributeValue")
                .build())).thenThrow(InvalidSecurityException.class);

        // Run the test
        assertThrows(InvalidSecurityException.class, () -> myClassUnderTest.trySetTopicAttributes1());
    }

    @Test
    void testTrySetTopicAttributes1_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.setTopicAttributes(SetTopicAttributesRequest.builder()
                .topicArn("topicArn")
                .attributeName("attributeName")
                .attributeValue("attributeValue")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.trySetTopicAttributes1());
    }

    @Test
    void testTrySetTopicAttributes1_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.setTopicAttributes(SetTopicAttributesRequest.builder()
                .topicArn("topicArn")
                .attributeName("attributeName")
                .attributeValue("attributeValue")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.trySetTopicAttributes1());
    }

    @Test
    void testTrySetTopicAttributes1_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.setTopicAttributes(SetTopicAttributesRequest.builder()
                .topicArn("topicArn")
                .attributeName("attributeName")
                .attributeValue("attributeValue")
                .build())).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.trySetTopicAttributes1());
    }

    @Test
    void testTrySetTopicAttributes2() {
        // Setup
        // Configure SnsClient.setTopicAttributes(...).
        final SetTopicAttributesResponse setTopicAttributesResponse = SetTopicAttributesResponse.builder().build();
        when(mockSnsClient.setTopicAttributes(any(Consumer.class))).thenReturn(setTopicAttributesResponse);

        // Run the test
        myClassUnderTest.trySetTopicAttributes2();

        // Verify the results
    }

    @Test
    void testTrySetTopicAttributes2_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.setTopicAttributes(any(Consumer.class))).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.trySetTopicAttributes2());
    }

    @Test
    void testTrySetTopicAttributes2_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.setTopicAttributes(any(Consumer.class))).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.trySetTopicAttributes2());
    }

    @Test
    void testTrySetTopicAttributes2_SnsClientThrowsNotFoundException() {
        // Setup
        when(mockSnsClient.setTopicAttributes(any(Consumer.class))).thenThrow(NotFoundException.class);

        // Run the test
        assertThrows(NotFoundException.class, () -> myClassUnderTest.trySetTopicAttributes2());
    }

    @Test
    void testTrySetTopicAttributes2_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.setTopicAttributes(any(Consumer.class))).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.trySetTopicAttributes2());
    }

    @Test
    void testTrySetTopicAttributes2_SnsClientThrowsInvalidSecurityException() {
        // Setup
        when(mockSnsClient.setTopicAttributes(any(Consumer.class))).thenThrow(InvalidSecurityException.class);

        // Run the test
        assertThrows(InvalidSecurityException.class, () -> myClassUnderTest.trySetTopicAttributes2());
    }

    @Test
    void testTrySetTopicAttributes2_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.setTopicAttributes(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.trySetTopicAttributes2());
    }

    @Test
    void testTrySetTopicAttributes2_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.setTopicAttributes(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.trySetTopicAttributes2());
    }

    @Test
    void testTrySetTopicAttributes2_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.setTopicAttributes(any(Consumer.class))).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.trySetTopicAttributes2());
    }

    @Test
    void testTrySubscribe1() {
        // Setup
        // Configure SnsClient.subscribe(...).
        final SubscribeResponse subscribeResponse = SubscribeResponse.builder()
                .subscriptionArn("subscriptionArn")
                .build();
        when(mockSnsClient.subscribe(SubscribeRequest.builder()
                .topicArn("topicArn")
                .protocol("protocol")
                .endpoint("endpoint")
                .attributes(new HashMap<>())
                .returnSubscriptionArn(false)
                .build())).thenReturn(subscribeResponse);

        // Run the test
        myClassUnderTest.trySubscribe1();

        // Verify the results
    }

    @Test
    void testTrySubscribe1_SnsClientThrowsSubscriptionLimitExceededException() {
        // Setup
        when(mockSnsClient.subscribe(SubscribeRequest.builder()
                .topicArn("topicArn")
                .protocol("protocol")
                .endpoint("endpoint")
                .attributes(new HashMap<>())
                .returnSubscriptionArn(false)
                .build())).thenThrow(SubscriptionLimitExceededException.class);

        // Run the test
        assertThrows(SubscriptionLimitExceededException.class, () -> myClassUnderTest.trySubscribe1());
    }

    @Test
    void testTrySubscribe1_SnsClientThrowsFilterPolicyLimitExceededException() {
        // Setup
        when(mockSnsClient.subscribe(SubscribeRequest.builder()
                .topicArn("topicArn")
                .protocol("protocol")
                .endpoint("endpoint")
                .attributes(new HashMap<>())
                .returnSubscriptionArn(false)
                .build())).thenThrow(FilterPolicyLimitExceededException.class);

        // Run the test
        assertThrows(FilterPolicyLimitExceededException.class, () -> myClassUnderTest.trySubscribe1());
    }

    @Test
    void testTrySubscribe1_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.subscribe(SubscribeRequest.builder()
                .topicArn("topicArn")
                .protocol("protocol")
                .endpoint("endpoint")
                .attributes(new HashMap<>())
                .returnSubscriptionArn(false)
                .build())).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.trySubscribe1());
    }

    @Test
    void testTrySubscribe1_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.subscribe(SubscribeRequest.builder()
                .topicArn("topicArn")
                .protocol("protocol")
                .endpoint("endpoint")
                .attributes(new HashMap<>())
                .returnSubscriptionArn(false)
                .build())).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.trySubscribe1());
    }

    @Test
    void testTrySubscribe1_SnsClientThrowsNotFoundException() {
        // Setup
        when(mockSnsClient.subscribe(SubscribeRequest.builder()
                .topicArn("topicArn")
                .protocol("protocol")
                .endpoint("endpoint")
                .attributes(new HashMap<>())
                .returnSubscriptionArn(false)
                .build())).thenThrow(NotFoundException.class);

        // Run the test
        assertThrows(NotFoundException.class, () -> myClassUnderTest.trySubscribe1());
    }

    @Test
    void testTrySubscribe1_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.subscribe(SubscribeRequest.builder()
                .topicArn("topicArn")
                .protocol("protocol")
                .endpoint("endpoint")
                .attributes(new HashMap<>())
                .returnSubscriptionArn(false)
                .build())).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.trySubscribe1());
    }

    @Test
    void testTrySubscribe1_SnsClientThrowsInvalidSecurityException() {
        // Setup
        when(mockSnsClient.subscribe(SubscribeRequest.builder()
                .topicArn("topicArn")
                .protocol("protocol")
                .endpoint("endpoint")
                .attributes(new HashMap<>())
                .returnSubscriptionArn(false)
                .build())).thenThrow(InvalidSecurityException.class);

        // Run the test
        assertThrows(InvalidSecurityException.class, () -> myClassUnderTest.trySubscribe1());
    }

    @Test
    void testTrySubscribe1_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.subscribe(SubscribeRequest.builder()
                .topicArn("topicArn")
                .protocol("protocol")
                .endpoint("endpoint")
                .attributes(new HashMap<>())
                .returnSubscriptionArn(false)
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.trySubscribe1());
    }

    @Test
    void testTrySubscribe1_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.subscribe(SubscribeRequest.builder()
                .topicArn("topicArn")
                .protocol("protocol")
                .endpoint("endpoint")
                .attributes(new HashMap<>())
                .returnSubscriptionArn(false)
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.trySubscribe1());
    }

    @Test
    void testTrySubscribe1_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.subscribe(SubscribeRequest.builder()
                .topicArn("topicArn")
                .protocol("protocol")
                .endpoint("endpoint")
                .attributes(new HashMap<>())
                .returnSubscriptionArn(false)
                .build())).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.trySubscribe1());
    }

    @Test
    void testTrySubscribe2() {
        // Setup
        // Configure SnsClient.subscribe(...).
        final SubscribeResponse subscribeResponse = SubscribeResponse.builder()
                .subscriptionArn("subscriptionArn")
                .build();
        when(mockSnsClient.subscribe(any(Consumer.class))).thenReturn(subscribeResponse);

        // Run the test
        myClassUnderTest.trySubscribe2();

        // Verify the results
    }

    @Test
    void testTrySubscribe2_SnsClientThrowsSubscriptionLimitExceededException() {
        // Setup
        when(mockSnsClient.subscribe(any(Consumer.class))).thenThrow(SubscriptionLimitExceededException.class);

        // Run the test
        assertThrows(SubscriptionLimitExceededException.class, () -> myClassUnderTest.trySubscribe2());
    }

    @Test
    void testTrySubscribe2_SnsClientThrowsFilterPolicyLimitExceededException() {
        // Setup
        when(mockSnsClient.subscribe(any(Consumer.class))).thenThrow(FilterPolicyLimitExceededException.class);

        // Run the test
        assertThrows(FilterPolicyLimitExceededException.class, () -> myClassUnderTest.trySubscribe2());
    }

    @Test
    void testTrySubscribe2_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.subscribe(any(Consumer.class))).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.trySubscribe2());
    }

    @Test
    void testTrySubscribe2_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.subscribe(any(Consumer.class))).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.trySubscribe2());
    }

    @Test
    void testTrySubscribe2_SnsClientThrowsNotFoundException() {
        // Setup
        when(mockSnsClient.subscribe(any(Consumer.class))).thenThrow(NotFoundException.class);

        // Run the test
        assertThrows(NotFoundException.class, () -> myClassUnderTest.trySubscribe2());
    }

    @Test
    void testTrySubscribe2_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.subscribe(any(Consumer.class))).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.trySubscribe2());
    }

    @Test
    void testTrySubscribe2_SnsClientThrowsInvalidSecurityException() {
        // Setup
        when(mockSnsClient.subscribe(any(Consumer.class))).thenThrow(InvalidSecurityException.class);

        // Run the test
        assertThrows(InvalidSecurityException.class, () -> myClassUnderTest.trySubscribe2());
    }

    @Test
    void testTrySubscribe2_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.subscribe(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.trySubscribe2());
    }

    @Test
    void testTrySubscribe2_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.subscribe(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.trySubscribe2());
    }

    @Test
    void testTrySubscribe2_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.subscribe(any(Consumer.class))).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.trySubscribe2());
    }

    @Test
    void testTryTagResource1() {
        // Setup
        when(mockSnsClient.tagResource(TagResourceRequest.builder()
                .resourceArn("resourceArn")
                .tags(Tag.builder()
                        .key("key")
                        .value("value")
                        .build())
                .build())).thenReturn(TagResourceResponse.builder().build());

        // Run the test
        myClassUnderTest.tryTagResource1();

        // Verify the results
    }

    @Test
    void testTryTagResource1_SnsClientThrowsResourceNotFoundException() {
        // Setup
        when(mockSnsClient.tagResource(TagResourceRequest.builder()
                .resourceArn("resourceArn")
                .tags(Tag.builder()
                        .key("key")
                        .value("value")
                        .build())
                .build())).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryTagResource1());
    }

    @Test
    void testTryTagResource1_SnsClientThrowsTagLimitExceededException() {
        // Setup
        when(mockSnsClient.tagResource(TagResourceRequest.builder()
                .resourceArn("resourceArn")
                .tags(Tag.builder()
                        .key("key")
                        .value("value")
                        .build())
                .build())).thenThrow(TagLimitExceededException.class);

        // Run the test
        assertThrows(TagLimitExceededException.class, () -> myClassUnderTest.tryTagResource1());
    }

    @Test
    void testTryTagResource1_SnsClientThrowsStaleTagException() {
        // Setup
        when(mockSnsClient.tagResource(TagResourceRequest.builder()
                .resourceArn("resourceArn")
                .tags(Tag.builder()
                        .key("key")
                        .value("value")
                        .build())
                .build())).thenThrow(StaleTagException.class);

        // Run the test
        assertThrows(StaleTagException.class, () -> myClassUnderTest.tryTagResource1());
    }

    @Test
    void testTryTagResource1_SnsClientThrowsTagPolicyException() {
        // Setup
        when(mockSnsClient.tagResource(TagResourceRequest.builder()
                .resourceArn("resourceArn")
                .tags(Tag.builder()
                        .key("key")
                        .value("value")
                        .build())
                .build())).thenThrow(TagPolicyException.class);

        // Run the test
        assertThrows(TagPolicyException.class, () -> myClassUnderTest.tryTagResource1());
    }

    @Test
    void testTryTagResource1_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.tagResource(TagResourceRequest.builder()
                .resourceArn("resourceArn")
                .tags(Tag.builder()
                        .key("key")
                        .value("value")
                        .build())
                .build())).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryTagResource1());
    }

    @Test
    void testTryTagResource1_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.tagResource(TagResourceRequest.builder()
                .resourceArn("resourceArn")
                .tags(Tag.builder()
                        .key("key")
                        .value("value")
                        .build())
                .build())).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryTagResource1());
    }

    @Test
    void testTryTagResource1_SnsClientThrowsConcurrentAccessException() {
        // Setup
        when(mockSnsClient.tagResource(TagResourceRequest.builder()
                .resourceArn("resourceArn")
                .tags(Tag.builder()
                        .key("key")
                        .value("value")
                        .build())
                .build())).thenThrow(ConcurrentAccessException.class);

        // Run the test
        assertThrows(ConcurrentAccessException.class, () -> myClassUnderTest.tryTagResource1());
    }

    @Test
    void testTryTagResource1_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.tagResource(TagResourceRequest.builder()
                .resourceArn("resourceArn")
                .tags(Tag.builder()
                        .key("key")
                        .value("value")
                        .build())
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryTagResource1());
    }

    @Test
    void testTryTagResource1_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.tagResource(TagResourceRequest.builder()
                .resourceArn("resourceArn")
                .tags(Tag.builder()
                        .key("key")
                        .value("value")
                        .build())
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryTagResource1());
    }

    @Test
    void testTryTagResource1_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.tagResource(TagResourceRequest.builder()
                .resourceArn("resourceArn")
                .tags(Tag.builder()
                        .key("key")
                        .value("value")
                        .build())
                .build())).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryTagResource1());
    }

    @Test
    void testTryTagResource2() {
        // Setup
        when(mockSnsClient.tagResource(any(Consumer.class))).thenReturn(TagResourceResponse.builder().build());

        // Run the test
        myClassUnderTest.tryTagResource2();

        // Verify the results
    }

    @Test
    void testTryTagResource2_SnsClientThrowsResourceNotFoundException() {
        // Setup
        when(mockSnsClient.tagResource(any(Consumer.class))).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryTagResource2());
    }

    @Test
    void testTryTagResource2_SnsClientThrowsTagLimitExceededException() {
        // Setup
        when(mockSnsClient.tagResource(any(Consumer.class))).thenThrow(TagLimitExceededException.class);

        // Run the test
        assertThrows(TagLimitExceededException.class, () -> myClassUnderTest.tryTagResource2());
    }

    @Test
    void testTryTagResource2_SnsClientThrowsStaleTagException() {
        // Setup
        when(mockSnsClient.tagResource(any(Consumer.class))).thenThrow(StaleTagException.class);

        // Run the test
        assertThrows(StaleTagException.class, () -> myClassUnderTest.tryTagResource2());
    }

    @Test
    void testTryTagResource2_SnsClientThrowsTagPolicyException() {
        // Setup
        when(mockSnsClient.tagResource(any(Consumer.class))).thenThrow(TagPolicyException.class);

        // Run the test
        assertThrows(TagPolicyException.class, () -> myClassUnderTest.tryTagResource2());
    }

    @Test
    void testTryTagResource2_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.tagResource(any(Consumer.class))).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryTagResource2());
    }

    @Test
    void testTryTagResource2_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.tagResource(any(Consumer.class))).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryTagResource2());
    }

    @Test
    void testTryTagResource2_SnsClientThrowsConcurrentAccessException() {
        // Setup
        when(mockSnsClient.tagResource(any(Consumer.class))).thenThrow(ConcurrentAccessException.class);

        // Run the test
        assertThrows(ConcurrentAccessException.class, () -> myClassUnderTest.tryTagResource2());
    }

    @Test
    void testTryTagResource2_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.tagResource(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryTagResource2());
    }

    @Test
    void testTryTagResource2_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.tagResource(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryTagResource2());
    }

    @Test
    void testTryTagResource2_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.tagResource(any(Consumer.class))).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryTagResource2());
    }

    @Test
    void testTryUnsubscribe1() {
        // Setup
        when(mockSnsClient.unsubscribe(UnsubscribeRequest.builder()
                .subscriptionArn("subscriptionArn")
                .build())).thenReturn(UnsubscribeResponse.builder().build());

        // Run the test
        myClassUnderTest.tryUnsubscribe1();

        // Verify the results
    }

    @Test
    void testTryUnsubscribe1_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.unsubscribe(UnsubscribeRequest.builder()
                .subscriptionArn("subscriptionArn")
                .build())).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryUnsubscribe1());
    }

    @Test
    void testTryUnsubscribe1_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.unsubscribe(UnsubscribeRequest.builder()
                .subscriptionArn("subscriptionArn")
                .build())).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryUnsubscribe1());
    }

    @Test
    void testTryUnsubscribe1_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.unsubscribe(UnsubscribeRequest.builder()
                .subscriptionArn("subscriptionArn")
                .build())).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryUnsubscribe1());
    }

    @Test
    void testTryUnsubscribe1_SnsClientThrowsNotFoundException() {
        // Setup
        when(mockSnsClient.unsubscribe(UnsubscribeRequest.builder()
                .subscriptionArn("subscriptionArn")
                .build())).thenThrow(NotFoundException.class);

        // Run the test
        assertThrows(NotFoundException.class, () -> myClassUnderTest.tryUnsubscribe1());
    }

    @Test
    void testTryUnsubscribe1_SnsClientThrowsInvalidSecurityException() {
        // Setup
        when(mockSnsClient.unsubscribe(UnsubscribeRequest.builder()
                .subscriptionArn("subscriptionArn")
                .build())).thenThrow(InvalidSecurityException.class);

        // Run the test
        assertThrows(InvalidSecurityException.class, () -> myClassUnderTest.tryUnsubscribe1());
    }

    @Test
    void testTryUnsubscribe1_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.unsubscribe(UnsubscribeRequest.builder()
                .subscriptionArn("subscriptionArn")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryUnsubscribe1());
    }

    @Test
    void testTryUnsubscribe1_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.unsubscribe(UnsubscribeRequest.builder()
                .subscriptionArn("subscriptionArn")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryUnsubscribe1());
    }

    @Test
    void testTryUnsubscribe1_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.unsubscribe(UnsubscribeRequest.builder()
                .subscriptionArn("subscriptionArn")
                .build())).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryUnsubscribe1());
    }

    @Test
    void testTryUnsubscribe2() {
        // Setup
        when(mockSnsClient.unsubscribe(any(Consumer.class))).thenReturn(UnsubscribeResponse.builder().build());

        // Run the test
        myClassUnderTest.tryUnsubscribe2();

        // Verify the results
    }

    @Test
    void testTryUnsubscribe2_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.unsubscribe(any(Consumer.class))).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryUnsubscribe2());
    }

    @Test
    void testTryUnsubscribe2_SnsClientThrowsInternalErrorException() {
        // Setup
        when(mockSnsClient.unsubscribe(any(Consumer.class))).thenThrow(InternalErrorException.class);

        // Run the test
        assertThrows(InternalErrorException.class, () -> myClassUnderTest.tryUnsubscribe2());
    }

    @Test
    void testTryUnsubscribe2_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.unsubscribe(any(Consumer.class))).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryUnsubscribe2());
    }

    @Test
    void testTryUnsubscribe2_SnsClientThrowsNotFoundException() {
        // Setup
        when(mockSnsClient.unsubscribe(any(Consumer.class))).thenThrow(NotFoundException.class);

        // Run the test
        assertThrows(NotFoundException.class, () -> myClassUnderTest.tryUnsubscribe2());
    }

    @Test
    void testTryUnsubscribe2_SnsClientThrowsInvalidSecurityException() {
        // Setup
        when(mockSnsClient.unsubscribe(any(Consumer.class))).thenThrow(InvalidSecurityException.class);

        // Run the test
        assertThrows(InvalidSecurityException.class, () -> myClassUnderTest.tryUnsubscribe2());
    }

    @Test
    void testTryUnsubscribe2_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.unsubscribe(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryUnsubscribe2());
    }

    @Test
    void testTryUnsubscribe2_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.unsubscribe(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryUnsubscribe2());
    }

    @Test
    void testTryUnsubscribe2_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.unsubscribe(any(Consumer.class))).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryUnsubscribe2());
    }

    @Test
    void testTryUntagResource1() {
        // Setup
        when(mockSnsClient.untagResource(UntagResourceRequest.builder()
                .resourceArn("resourceArn")
                .tagKeys("tagKeys")
                .build())).thenReturn(UntagResourceResponse.builder().build());

        // Run the test
        myClassUnderTest.tryUntagResource1();

        // Verify the results
    }

    @Test
    void testTryUntagResource1_SnsClientThrowsResourceNotFoundException() {
        // Setup
        when(mockSnsClient.untagResource(UntagResourceRequest.builder()
                .resourceArn("resourceArn")
                .tagKeys("tagKeys")
                .build())).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryUntagResource1());
    }

    @Test
    void testTryUntagResource1_SnsClientThrowsTagLimitExceededException() {
        // Setup
        when(mockSnsClient.untagResource(UntagResourceRequest.builder()
                .resourceArn("resourceArn")
                .tagKeys("tagKeys")
                .build())).thenThrow(TagLimitExceededException.class);

        // Run the test
        assertThrows(TagLimitExceededException.class, () -> myClassUnderTest.tryUntagResource1());
    }

    @Test
    void testTryUntagResource1_SnsClientThrowsStaleTagException() {
        // Setup
        when(mockSnsClient.untagResource(UntagResourceRequest.builder()
                .resourceArn("resourceArn")
                .tagKeys("tagKeys")
                .build())).thenThrow(StaleTagException.class);

        // Run the test
        assertThrows(StaleTagException.class, () -> myClassUnderTest.tryUntagResource1());
    }

    @Test
    void testTryUntagResource1_SnsClientThrowsTagPolicyException() {
        // Setup
        when(mockSnsClient.untagResource(UntagResourceRequest.builder()
                .resourceArn("resourceArn")
                .tagKeys("tagKeys")
                .build())).thenThrow(TagPolicyException.class);

        // Run the test
        assertThrows(TagPolicyException.class, () -> myClassUnderTest.tryUntagResource1());
    }

    @Test
    void testTryUntagResource1_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.untagResource(UntagResourceRequest.builder()
                .resourceArn("resourceArn")
                .tagKeys("tagKeys")
                .build())).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryUntagResource1());
    }

    @Test
    void testTryUntagResource1_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.untagResource(UntagResourceRequest.builder()
                .resourceArn("resourceArn")
                .tagKeys("tagKeys")
                .build())).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryUntagResource1());
    }

    @Test
    void testTryUntagResource1_SnsClientThrowsConcurrentAccessException() {
        // Setup
        when(mockSnsClient.untagResource(UntagResourceRequest.builder()
                .resourceArn("resourceArn")
                .tagKeys("tagKeys")
                .build())).thenThrow(ConcurrentAccessException.class);

        // Run the test
        assertThrows(ConcurrentAccessException.class, () -> myClassUnderTest.tryUntagResource1());
    }

    @Test
    void testTryUntagResource1_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.untagResource(UntagResourceRequest.builder()
                .resourceArn("resourceArn")
                .tagKeys("tagKeys")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryUntagResource1());
    }

    @Test
    void testTryUntagResource1_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.untagResource(UntagResourceRequest.builder()
                .resourceArn("resourceArn")
                .tagKeys("tagKeys")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryUntagResource1());
    }

    @Test
    void testTryUntagResource1_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.untagResource(UntagResourceRequest.builder()
                .resourceArn("resourceArn")
                .tagKeys("tagKeys")
                .build())).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryUntagResource1());
    }

    @Test
    void testTryUntagResource2() {
        // Setup
        when(mockSnsClient.untagResource(any(Consumer.class))).thenReturn(UntagResourceResponse.builder().build());

        // Run the test
        myClassUnderTest.tryUntagResource2();

        // Verify the results
    }

    @Test
    void testTryUntagResource2_SnsClientThrowsResourceNotFoundException() {
        // Setup
        when(mockSnsClient.untagResource(any(Consumer.class))).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryUntagResource2());
    }

    @Test
    void testTryUntagResource2_SnsClientThrowsTagLimitExceededException() {
        // Setup
        when(mockSnsClient.untagResource(any(Consumer.class))).thenThrow(TagLimitExceededException.class);

        // Run the test
        assertThrows(TagLimitExceededException.class, () -> myClassUnderTest.tryUntagResource2());
    }

    @Test
    void testTryUntagResource2_SnsClientThrowsStaleTagException() {
        // Setup
        when(mockSnsClient.untagResource(any(Consumer.class))).thenThrow(StaleTagException.class);

        // Run the test
        assertThrows(StaleTagException.class, () -> myClassUnderTest.tryUntagResource2());
    }

    @Test
    void testTryUntagResource2_SnsClientThrowsTagPolicyException() {
        // Setup
        when(mockSnsClient.untagResource(any(Consumer.class))).thenThrow(TagPolicyException.class);

        // Run the test
        assertThrows(TagPolicyException.class, () -> myClassUnderTest.tryUntagResource2());
    }

    @Test
    void testTryUntagResource2_SnsClientThrowsInvalidParameterException() {
        // Setup
        when(mockSnsClient.untagResource(any(Consumer.class))).thenThrow(InvalidParameterException.class);

        // Run the test
        assertThrows(InvalidParameterException.class, () -> myClassUnderTest.tryUntagResource2());
    }

    @Test
    void testTryUntagResource2_SnsClientThrowsAuthorizationErrorException() {
        // Setup
        when(mockSnsClient.untagResource(any(Consumer.class))).thenThrow(AuthorizationErrorException.class);

        // Run the test
        assertThrows(AuthorizationErrorException.class, () -> myClassUnderTest.tryUntagResource2());
    }

    @Test
    void testTryUntagResource2_SnsClientThrowsConcurrentAccessException() {
        // Setup
        when(mockSnsClient.untagResource(any(Consumer.class))).thenThrow(ConcurrentAccessException.class);

        // Run the test
        assertThrows(ConcurrentAccessException.class, () -> myClassUnderTest.tryUntagResource2());
    }

    @Test
    void testTryUntagResource2_SnsClientThrowsAwsServiceException() {
        // Setup
        when(mockSnsClient.untagResource(any(Consumer.class))).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryUntagResource2());
    }

    @Test
    void testTryUntagResource2_SnsClientThrowsSdkClientException() {
        // Setup
        when(mockSnsClient.untagResource(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryUntagResource2());
    }

    @Test
    void testTryUntagResource2_SnsClientThrowsSnsException() {
        // Setup
        when(mockSnsClient.untagResource(any(Consumer.class))).thenThrow(SnsException.class);

        // Run the test
        assertThrows(SnsException.class, () -> myClassUnderTest.tryUntagResource2());
    }
}
