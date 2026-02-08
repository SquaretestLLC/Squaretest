package com.myapp;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.ResponseMetadata;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simpleemail.model.*;
import com.amazonaws.services.simpleemail.waiters.AmazonSimpleEmailServiceWaiters;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private AmazonSimpleEmailServiceClient mockAmazonSimpleEmailServiceClient;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockAmazonSimpleEmailServiceClient);
    }

    @Test
    void testTryCloneReceiptRuleSet() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.cloneReceiptRuleSet(...).
        final CloneReceiptRuleSetRequest request = new CloneReceiptRuleSetRequest();
        request.setRuleSetName("ruleSetName");
        request.setOriginalRuleSetName("originalRuleSetName");
        when(mockAmazonSimpleEmailServiceClient.cloneReceiptRuleSet(request))
                .thenReturn(new CloneReceiptRuleSetResult());

        // Run the test
        myClassUnderTest.tryCloneReceiptRuleSet();

        // Verify the results
    }

    @Test
    void testTryCloneReceiptRuleSet_AmazonSimpleEmailServiceClientThrowsRuleSetDoesNotExistException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.cloneReceiptRuleSet(...).
        final CloneReceiptRuleSetRequest request = new CloneReceiptRuleSetRequest();
        request.setRuleSetName("ruleSetName");
        request.setOriginalRuleSetName("originalRuleSetName");
        when(mockAmazonSimpleEmailServiceClient.cloneReceiptRuleSet(request))
                .thenThrow(RuleSetDoesNotExistException.class);

        // Run the test
        assertThrows(RuleSetDoesNotExistException.class, () -> myClassUnderTest.tryCloneReceiptRuleSet());
    }

    @Test
    void testTryCloneReceiptRuleSet_AmazonSimpleEmailServiceClientThrowsAlreadyExistsException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.cloneReceiptRuleSet(...).
        final CloneReceiptRuleSetRequest request = new CloneReceiptRuleSetRequest();
        request.setRuleSetName("ruleSetName");
        request.setOriginalRuleSetName("originalRuleSetName");
        when(mockAmazonSimpleEmailServiceClient.cloneReceiptRuleSet(request)).thenThrow(AlreadyExistsException.class);

        // Run the test
        assertThrows(AlreadyExistsException.class, () -> myClassUnderTest.tryCloneReceiptRuleSet());
    }

    @Test
    void testTryCloneReceiptRuleSet_AmazonSimpleEmailServiceClientThrowsLimitExceededException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.cloneReceiptRuleSet(...).
        final CloneReceiptRuleSetRequest request = new CloneReceiptRuleSetRequest();
        request.setRuleSetName("ruleSetName");
        request.setOriginalRuleSetName("originalRuleSetName");
        when(mockAmazonSimpleEmailServiceClient.cloneReceiptRuleSet(request)).thenThrow(LimitExceededException.class);

        // Run the test
        assertThrows(LimitExceededException.class, () -> myClassUnderTest.tryCloneReceiptRuleSet());
    }

    @Test
    void testTryCreateConfigurationSet() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.createConfigurationSet(...).
        final CreateConfigurationSetRequest request = new CreateConfigurationSetRequest();
        final ConfigurationSet configurationSet = new ConfigurationSet();
        request.setConfigurationSet(configurationSet);
        when(mockAmazonSimpleEmailServiceClient.createConfigurationSet(request))
                .thenReturn(new CreateConfigurationSetResult());

        // Run the test
        myClassUnderTest.tryCreateConfigurationSet();

        // Verify the results
    }

    @Test
    void testTryCreateConfigurationSet_AmazonSimpleEmailServiceClientThrowsConfigurationSetAlreadyExistsException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.createConfigurationSet(...).
        final CreateConfigurationSetRequest request = new CreateConfigurationSetRequest();
        final ConfigurationSet configurationSet = new ConfigurationSet();
        request.setConfigurationSet(configurationSet);
        when(mockAmazonSimpleEmailServiceClient.createConfigurationSet(request))
                .thenThrow(ConfigurationSetAlreadyExistsException.class);

        // Run the test
        assertThrows(ConfigurationSetAlreadyExistsException.class, () -> myClassUnderTest.tryCreateConfigurationSet());
    }

    @Test
    void testTryCreateConfigurationSet_AmazonSimpleEmailServiceClientThrowsInvalidConfigurationSetException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.createConfigurationSet(...).
        final CreateConfigurationSetRequest request = new CreateConfigurationSetRequest();
        final ConfigurationSet configurationSet = new ConfigurationSet();
        request.setConfigurationSet(configurationSet);
        when(mockAmazonSimpleEmailServiceClient.createConfigurationSet(request))
                .thenThrow(InvalidConfigurationSetException.class);

        // Run the test
        assertThrows(InvalidConfigurationSetException.class, () -> myClassUnderTest.tryCreateConfigurationSet());
    }

    @Test
    void testTryCreateConfigurationSet_AmazonSimpleEmailServiceClientThrowsLimitExceededException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.createConfigurationSet(...).
        final CreateConfigurationSetRequest request = new CreateConfigurationSetRequest();
        final ConfigurationSet configurationSet = new ConfigurationSet();
        request.setConfigurationSet(configurationSet);
        when(mockAmazonSimpleEmailServiceClient.createConfigurationSet(request))
                .thenThrow(LimitExceededException.class);

        // Run the test
        assertThrows(LimitExceededException.class, () -> myClassUnderTest.tryCreateConfigurationSet());
    }

    @Test
    void testTryCreateConfigurationSetEventDestination() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.createConfigurationSetEventDestination(...).
        final CreateConfigurationSetEventDestinationResult createConfigurationSetEventDestinationResult = new CreateConfigurationSetEventDestinationResult();
        final CreateConfigurationSetEventDestinationRequest request = new CreateConfigurationSetEventDestinationRequest();
        request.setConfigurationSetName("configurationSetName");
        final EventDestination eventDestination = new EventDestination();
        request.setEventDestination(eventDestination);
        when(mockAmazonSimpleEmailServiceClient.createConfigurationSetEventDestination(request))
                .thenReturn(createConfigurationSetEventDestinationResult);

        // Run the test
        myClassUnderTest.tryCreateConfigurationSetEventDestination();

        // Verify the results
    }

    @Test
    void testTryCreateConfigurationSetEventDestination_AmazonSimpleEmailServiceClientThrowsConfigurationSetDoesNotExistException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.createConfigurationSetEventDestination(...).
        final CreateConfigurationSetEventDestinationRequest request = new CreateConfigurationSetEventDestinationRequest();
        request.setConfigurationSetName("configurationSetName");
        final EventDestination eventDestination = new EventDestination();
        request.setEventDestination(eventDestination);
        when(mockAmazonSimpleEmailServiceClient.createConfigurationSetEventDestination(request))
                .thenThrow(ConfigurationSetDoesNotExistException.class);

        // Run the test
        assertThrows(ConfigurationSetDoesNotExistException.class,
                () -> myClassUnderTest.tryCreateConfigurationSetEventDestination());
    }

    @Test
    void testTryCreateConfigurationSetEventDestination_AmazonSimpleEmailServiceClientThrowsEventDestinationAlreadyExistsException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.createConfigurationSetEventDestination(...).
        final CreateConfigurationSetEventDestinationRequest request = new CreateConfigurationSetEventDestinationRequest();
        request.setConfigurationSetName("configurationSetName");
        final EventDestination eventDestination = new EventDestination();
        request.setEventDestination(eventDestination);
        when(mockAmazonSimpleEmailServiceClient.createConfigurationSetEventDestination(request))
                .thenThrow(EventDestinationAlreadyExistsException.class);

        // Run the test
        assertThrows(EventDestinationAlreadyExistsException.class,
                () -> myClassUnderTest.tryCreateConfigurationSetEventDestination());
    }

    @Test
    void testTryCreateConfigurationSetEventDestination_AmazonSimpleEmailServiceClientThrowsInvalidCloudWatchDestinationException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.createConfigurationSetEventDestination(...).
        final CreateConfigurationSetEventDestinationRequest request = new CreateConfigurationSetEventDestinationRequest();
        request.setConfigurationSetName("configurationSetName");
        final EventDestination eventDestination = new EventDestination();
        request.setEventDestination(eventDestination);
        when(mockAmazonSimpleEmailServiceClient.createConfigurationSetEventDestination(request))
                .thenThrow(InvalidCloudWatchDestinationException.class);

        // Run the test
        assertThrows(InvalidCloudWatchDestinationException.class,
                () -> myClassUnderTest.tryCreateConfigurationSetEventDestination());
    }

    @Test
    void testTryCreateConfigurationSetEventDestination_AmazonSimpleEmailServiceClientThrowsInvalidFirehoseDestinationException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.createConfigurationSetEventDestination(...).
        final CreateConfigurationSetEventDestinationRequest request = new CreateConfigurationSetEventDestinationRequest();
        request.setConfigurationSetName("configurationSetName");
        final EventDestination eventDestination = new EventDestination();
        request.setEventDestination(eventDestination);
        when(mockAmazonSimpleEmailServiceClient.createConfigurationSetEventDestination(request))
                .thenThrow(InvalidFirehoseDestinationException.class);

        // Run the test
        assertThrows(InvalidFirehoseDestinationException.class,
                () -> myClassUnderTest.tryCreateConfigurationSetEventDestination());
    }

    @Test
    void testTryCreateConfigurationSetEventDestination_AmazonSimpleEmailServiceClientThrowsInvalidSNSDestinationException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.createConfigurationSetEventDestination(...).
        final CreateConfigurationSetEventDestinationRequest request = new CreateConfigurationSetEventDestinationRequest();
        request.setConfigurationSetName("configurationSetName");
        final EventDestination eventDestination = new EventDestination();
        request.setEventDestination(eventDestination);
        when(mockAmazonSimpleEmailServiceClient.createConfigurationSetEventDestination(request))
                .thenThrow(InvalidSNSDestinationException.class);

        // Run the test
        assertThrows(InvalidSNSDestinationException.class,
                () -> myClassUnderTest.tryCreateConfigurationSetEventDestination());
    }

    @Test
    void testTryCreateConfigurationSetEventDestination_AmazonSimpleEmailServiceClientThrowsLimitExceededException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.createConfigurationSetEventDestination(...).
        final CreateConfigurationSetEventDestinationRequest request = new CreateConfigurationSetEventDestinationRequest();
        request.setConfigurationSetName("configurationSetName");
        final EventDestination eventDestination = new EventDestination();
        request.setEventDestination(eventDestination);
        when(mockAmazonSimpleEmailServiceClient.createConfigurationSetEventDestination(request))
                .thenThrow(LimitExceededException.class);

        // Run the test
        assertThrows(LimitExceededException.class, () -> myClassUnderTest.tryCreateConfigurationSetEventDestination());
    }

    @Test
    void testTryCreateConfigurationSetTrackingOptions() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.createConfigurationSetTrackingOptions(...).
        final CreateConfigurationSetTrackingOptionsResult createConfigurationSetTrackingOptionsResult = new CreateConfigurationSetTrackingOptionsResult();
        final CreateConfigurationSetTrackingOptionsRequest request = new CreateConfigurationSetTrackingOptionsRequest();
        request.setConfigurationSetName("configurationSetName");
        final TrackingOptions trackingOptions = new TrackingOptions();
        request.setTrackingOptions(trackingOptions);
        when(mockAmazonSimpleEmailServiceClient.createConfigurationSetTrackingOptions(request))
                .thenReturn(createConfigurationSetTrackingOptionsResult);

        // Run the test
        myClassUnderTest.tryCreateConfigurationSetTrackingOptions();

        // Verify the results
    }

    @Test
    void testTryCreateConfigurationSetTrackingOptions_AmazonSimpleEmailServiceClientThrowsConfigurationSetDoesNotExistException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.createConfigurationSetTrackingOptions(...).
        final CreateConfigurationSetTrackingOptionsRequest request = new CreateConfigurationSetTrackingOptionsRequest();
        request.setConfigurationSetName("configurationSetName");
        final TrackingOptions trackingOptions = new TrackingOptions();
        request.setTrackingOptions(trackingOptions);
        when(mockAmazonSimpleEmailServiceClient.createConfigurationSetTrackingOptions(request))
                .thenThrow(ConfigurationSetDoesNotExistException.class);

        // Run the test
        assertThrows(ConfigurationSetDoesNotExistException.class,
                () -> myClassUnderTest.tryCreateConfigurationSetTrackingOptions());
    }

    @Test
    void testTryCreateConfigurationSetTrackingOptions_AmazonSimpleEmailServiceClientThrowsTrackingOptionsAlreadyExistsException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.createConfigurationSetTrackingOptions(...).
        final CreateConfigurationSetTrackingOptionsRequest request = new CreateConfigurationSetTrackingOptionsRequest();
        request.setConfigurationSetName("configurationSetName");
        final TrackingOptions trackingOptions = new TrackingOptions();
        request.setTrackingOptions(trackingOptions);
        when(mockAmazonSimpleEmailServiceClient.createConfigurationSetTrackingOptions(request))
                .thenThrow(TrackingOptionsAlreadyExistsException.class);

        // Run the test
        assertThrows(TrackingOptionsAlreadyExistsException.class,
                () -> myClassUnderTest.tryCreateConfigurationSetTrackingOptions());
    }

    @Test
    void testTryCreateConfigurationSetTrackingOptions_AmazonSimpleEmailServiceClientThrowsInvalidTrackingOptionsException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.createConfigurationSetTrackingOptions(...).
        final CreateConfigurationSetTrackingOptionsRequest request = new CreateConfigurationSetTrackingOptionsRequest();
        request.setConfigurationSetName("configurationSetName");
        final TrackingOptions trackingOptions = new TrackingOptions();
        request.setTrackingOptions(trackingOptions);
        when(mockAmazonSimpleEmailServiceClient.createConfigurationSetTrackingOptions(request))
                .thenThrow(InvalidTrackingOptionsException.class);

        // Run the test
        assertThrows(InvalidTrackingOptionsException.class,
                () -> myClassUnderTest.tryCreateConfigurationSetTrackingOptions());
    }

    @Test
    void testTryCreateCustomVerificationEmailTemplate() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.createCustomVerificationEmailTemplate(...).
        final CreateCustomVerificationEmailTemplateResult createCustomVerificationEmailTemplateResult = new CreateCustomVerificationEmailTemplateResult();
        final CreateCustomVerificationEmailTemplateRequest request = new CreateCustomVerificationEmailTemplateRequest();
        request.setTemplateName("templateName");
        request.setFromEmailAddress("fromEmailAddress");
        request.setTemplateSubject("templateSubject");
        request.setTemplateContent("templateContent");
        request.setSuccessRedirectionURL("successRedirectionURL");
        request.setFailureRedirectionURL("failureRedirectionURL");
        when(mockAmazonSimpleEmailServiceClient.createCustomVerificationEmailTemplate(request))
                .thenReturn(createCustomVerificationEmailTemplateResult);

        // Run the test
        myClassUnderTest.tryCreateCustomVerificationEmailTemplate();

        // Verify the results
    }

    @Test
    void testTryCreateCustomVerificationEmailTemplate_AmazonSimpleEmailServiceClientThrowsCustomVerificationEmailTemplateAlreadyExistsException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.createCustomVerificationEmailTemplate(...).
        final CreateCustomVerificationEmailTemplateRequest request = new CreateCustomVerificationEmailTemplateRequest();
        request.setTemplateName("templateName");
        request.setFromEmailAddress("fromEmailAddress");
        request.setTemplateSubject("templateSubject");
        request.setTemplateContent("templateContent");
        request.setSuccessRedirectionURL("successRedirectionURL");
        request.setFailureRedirectionURL("failureRedirectionURL");
        when(mockAmazonSimpleEmailServiceClient.createCustomVerificationEmailTemplate(request))
                .thenThrow(CustomVerificationEmailTemplateAlreadyExistsException.class);

        // Run the test
        assertThrows(CustomVerificationEmailTemplateAlreadyExistsException.class,
                () -> myClassUnderTest.tryCreateCustomVerificationEmailTemplate());
    }

    @Test
    void testTryCreateCustomVerificationEmailTemplate_AmazonSimpleEmailServiceClientThrowsFromEmailAddressNotVerifiedException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.createCustomVerificationEmailTemplate(...).
        final CreateCustomVerificationEmailTemplateRequest request = new CreateCustomVerificationEmailTemplateRequest();
        request.setTemplateName("templateName");
        request.setFromEmailAddress("fromEmailAddress");
        request.setTemplateSubject("templateSubject");
        request.setTemplateContent("templateContent");
        request.setSuccessRedirectionURL("successRedirectionURL");
        request.setFailureRedirectionURL("failureRedirectionURL");
        when(mockAmazonSimpleEmailServiceClient.createCustomVerificationEmailTemplate(request))
                .thenThrow(FromEmailAddressNotVerifiedException.class);

        // Run the test
        assertThrows(FromEmailAddressNotVerifiedException.class,
                () -> myClassUnderTest.tryCreateCustomVerificationEmailTemplate());
    }

    @Test
    void testTryCreateCustomVerificationEmailTemplate_AmazonSimpleEmailServiceClientThrowsCustomVerificationEmailInvalidContentException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.createCustomVerificationEmailTemplate(...).
        final CreateCustomVerificationEmailTemplateRequest request = new CreateCustomVerificationEmailTemplateRequest();
        request.setTemplateName("templateName");
        request.setFromEmailAddress("fromEmailAddress");
        request.setTemplateSubject("templateSubject");
        request.setTemplateContent("templateContent");
        request.setSuccessRedirectionURL("successRedirectionURL");
        request.setFailureRedirectionURL("failureRedirectionURL");
        when(mockAmazonSimpleEmailServiceClient.createCustomVerificationEmailTemplate(request))
                .thenThrow(CustomVerificationEmailInvalidContentException.class);

        // Run the test
        assertThrows(CustomVerificationEmailInvalidContentException.class,
                () -> myClassUnderTest.tryCreateCustomVerificationEmailTemplate());
    }

    @Test
    void testTryCreateCustomVerificationEmailTemplate_AmazonSimpleEmailServiceClientThrowsLimitExceededException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.createCustomVerificationEmailTemplate(...).
        final CreateCustomVerificationEmailTemplateRequest request = new CreateCustomVerificationEmailTemplateRequest();
        request.setTemplateName("templateName");
        request.setFromEmailAddress("fromEmailAddress");
        request.setTemplateSubject("templateSubject");
        request.setTemplateContent("templateContent");
        request.setSuccessRedirectionURL("successRedirectionURL");
        request.setFailureRedirectionURL("failureRedirectionURL");
        when(mockAmazonSimpleEmailServiceClient.createCustomVerificationEmailTemplate(request))
                .thenThrow(LimitExceededException.class);

        // Run the test
        assertThrows(LimitExceededException.class, () -> myClassUnderTest.tryCreateCustomVerificationEmailTemplate());
    }

    @Test
    void testTryCreateReceiptFilter() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.createReceiptFilter(...).
        final CreateReceiptFilterRequest request = new CreateReceiptFilterRequest();
        final ReceiptFilter filter = new ReceiptFilter();
        request.setFilter(filter);
        when(mockAmazonSimpleEmailServiceClient.createReceiptFilter(request))
                .thenReturn(new CreateReceiptFilterResult());

        // Run the test
        myClassUnderTest.tryCreateReceiptFilter();

        // Verify the results
    }

    @Test
    void testTryCreateReceiptFilter_AmazonSimpleEmailServiceClientThrowsLimitExceededException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.createReceiptFilter(...).
        final CreateReceiptFilterRequest request = new CreateReceiptFilterRequest();
        final ReceiptFilter filter = new ReceiptFilter();
        request.setFilter(filter);
        when(mockAmazonSimpleEmailServiceClient.createReceiptFilter(request)).thenThrow(LimitExceededException.class);

        // Run the test
        assertThrows(LimitExceededException.class, () -> myClassUnderTest.tryCreateReceiptFilter());
    }

    @Test
    void testTryCreateReceiptFilter_AmazonSimpleEmailServiceClientThrowsAlreadyExistsException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.createReceiptFilter(...).
        final CreateReceiptFilterRequest request = new CreateReceiptFilterRequest();
        final ReceiptFilter filter = new ReceiptFilter();
        request.setFilter(filter);
        when(mockAmazonSimpleEmailServiceClient.createReceiptFilter(request)).thenThrow(AlreadyExistsException.class);

        // Run the test
        assertThrows(AlreadyExistsException.class, () -> myClassUnderTest.tryCreateReceiptFilter());
    }

    @Test
    void testTryCreateReceiptRule() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.createReceiptRule(...).
        final CreateReceiptRuleRequest request = new CreateReceiptRuleRequest();
        request.setRuleSetName("ruleSetName");
        request.setAfter("after");
        final ReceiptRule rule = new ReceiptRule();
        request.setRule(rule);
        when(mockAmazonSimpleEmailServiceClient.createReceiptRule(request)).thenReturn(new CreateReceiptRuleResult());

        // Run the test
        myClassUnderTest.tryCreateReceiptRule();

        // Verify the results
    }

    @Test
    void testTryCreateReceiptRule_AmazonSimpleEmailServiceClientThrowsInvalidSnsTopicException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.createReceiptRule(...).
        final CreateReceiptRuleRequest request = new CreateReceiptRuleRequest();
        request.setRuleSetName("ruleSetName");
        request.setAfter("after");
        final ReceiptRule rule = new ReceiptRule();
        request.setRule(rule);
        when(mockAmazonSimpleEmailServiceClient.createReceiptRule(request)).thenThrow(InvalidSnsTopicException.class);

        // Run the test
        assertThrows(InvalidSnsTopicException.class, () -> myClassUnderTest.tryCreateReceiptRule());
    }

    @Test
    void testTryCreateReceiptRule_AmazonSimpleEmailServiceClientThrowsInvalidS3ConfigurationException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.createReceiptRule(...).
        final CreateReceiptRuleRequest request = new CreateReceiptRuleRequest();
        request.setRuleSetName("ruleSetName");
        request.setAfter("after");
        final ReceiptRule rule = new ReceiptRule();
        request.setRule(rule);
        when(mockAmazonSimpleEmailServiceClient.createReceiptRule(request))
                .thenThrow(InvalidS3ConfigurationException.class);

        // Run the test
        assertThrows(InvalidS3ConfigurationException.class, () -> myClassUnderTest.tryCreateReceiptRule());
    }

    @Test
    void testTryCreateReceiptRule_AmazonSimpleEmailServiceClientThrowsInvalidLambdaFunctionException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.createReceiptRule(...).
        final CreateReceiptRuleRequest request = new CreateReceiptRuleRequest();
        request.setRuleSetName("ruleSetName");
        request.setAfter("after");
        final ReceiptRule rule = new ReceiptRule();
        request.setRule(rule);
        when(mockAmazonSimpleEmailServiceClient.createReceiptRule(request))
                .thenThrow(InvalidLambdaFunctionException.class);

        // Run the test
        assertThrows(InvalidLambdaFunctionException.class, () -> myClassUnderTest.tryCreateReceiptRule());
    }

    @Test
    void testTryCreateReceiptRule_AmazonSimpleEmailServiceClientThrowsAlreadyExistsException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.createReceiptRule(...).
        final CreateReceiptRuleRequest request = new CreateReceiptRuleRequest();
        request.setRuleSetName("ruleSetName");
        request.setAfter("after");
        final ReceiptRule rule = new ReceiptRule();
        request.setRule(rule);
        when(mockAmazonSimpleEmailServiceClient.createReceiptRule(request)).thenThrow(AlreadyExistsException.class);

        // Run the test
        assertThrows(AlreadyExistsException.class, () -> myClassUnderTest.tryCreateReceiptRule());
    }

    @Test
    void testTryCreateReceiptRule_AmazonSimpleEmailServiceClientThrowsRuleDoesNotExistException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.createReceiptRule(...).
        final CreateReceiptRuleRequest request = new CreateReceiptRuleRequest();
        request.setRuleSetName("ruleSetName");
        request.setAfter("after");
        final ReceiptRule rule = new ReceiptRule();
        request.setRule(rule);
        when(mockAmazonSimpleEmailServiceClient.createReceiptRule(request)).thenThrow(RuleDoesNotExistException.class);

        // Run the test
        assertThrows(RuleDoesNotExistException.class, () -> myClassUnderTest.tryCreateReceiptRule());
    }

    @Test
    void testTryCreateReceiptRule_AmazonSimpleEmailServiceClientThrowsRuleSetDoesNotExistException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.createReceiptRule(...).
        final CreateReceiptRuleRequest request = new CreateReceiptRuleRequest();
        request.setRuleSetName("ruleSetName");
        request.setAfter("after");
        final ReceiptRule rule = new ReceiptRule();
        request.setRule(rule);
        when(mockAmazonSimpleEmailServiceClient.createReceiptRule(request))
                .thenThrow(RuleSetDoesNotExistException.class);

        // Run the test
        assertThrows(RuleSetDoesNotExistException.class, () -> myClassUnderTest.tryCreateReceiptRule());
    }

    @Test
    void testTryCreateReceiptRule_AmazonSimpleEmailServiceClientThrowsLimitExceededException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.createReceiptRule(...).
        final CreateReceiptRuleRequest request = new CreateReceiptRuleRequest();
        request.setRuleSetName("ruleSetName");
        request.setAfter("after");
        final ReceiptRule rule = new ReceiptRule();
        request.setRule(rule);
        when(mockAmazonSimpleEmailServiceClient.createReceiptRule(request)).thenThrow(LimitExceededException.class);

        // Run the test
        assertThrows(LimitExceededException.class, () -> myClassUnderTest.tryCreateReceiptRule());
    }

    @Test
    void testTryCreateReceiptRuleSet() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.createReceiptRuleSet(...).
        final CreateReceiptRuleSetRequest request = new CreateReceiptRuleSetRequest();
        request.setRuleSetName("ruleSetName");
        when(mockAmazonSimpleEmailServiceClient.createReceiptRuleSet(request))
                .thenReturn(new CreateReceiptRuleSetResult());

        // Run the test
        myClassUnderTest.tryCreateReceiptRuleSet();

        // Verify the results
    }

    @Test
    void testTryCreateReceiptRuleSet_AmazonSimpleEmailServiceClientThrowsAlreadyExistsException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.createReceiptRuleSet(...).
        final CreateReceiptRuleSetRequest request = new CreateReceiptRuleSetRequest();
        request.setRuleSetName("ruleSetName");
        when(mockAmazonSimpleEmailServiceClient.createReceiptRuleSet(request)).thenThrow(AlreadyExistsException.class);

        // Run the test
        assertThrows(AlreadyExistsException.class, () -> myClassUnderTest.tryCreateReceiptRuleSet());
    }

    @Test
    void testTryCreateReceiptRuleSet_AmazonSimpleEmailServiceClientThrowsLimitExceededException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.createReceiptRuleSet(...).
        final CreateReceiptRuleSetRequest request = new CreateReceiptRuleSetRequest();
        request.setRuleSetName("ruleSetName");
        when(mockAmazonSimpleEmailServiceClient.createReceiptRuleSet(request)).thenThrow(LimitExceededException.class);

        // Run the test
        assertThrows(LimitExceededException.class, () -> myClassUnderTest.tryCreateReceiptRuleSet());
    }

    @Test
    void testTryCreateTemplate() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.createTemplate(...).
        final CreateTemplateRequest request = new CreateTemplateRequest();
        final Template template = new Template();
        request.setTemplate(template);
        when(mockAmazonSimpleEmailServiceClient.createTemplate(request)).thenReturn(new CreateTemplateResult());

        // Run the test
        myClassUnderTest.tryCreateTemplate();

        // Verify the results
    }

    @Test
    void testTryCreateTemplate_AmazonSimpleEmailServiceClientThrowsAlreadyExistsException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.createTemplate(...).
        final CreateTemplateRequest request = new CreateTemplateRequest();
        final Template template = new Template();
        request.setTemplate(template);
        when(mockAmazonSimpleEmailServiceClient.createTemplate(request)).thenThrow(AlreadyExistsException.class);

        // Run the test
        assertThrows(AlreadyExistsException.class, () -> myClassUnderTest.tryCreateTemplate());
    }

    @Test
    void testTryCreateTemplate_AmazonSimpleEmailServiceClientThrowsInvalidTemplateException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.createTemplate(...).
        final CreateTemplateRequest request = new CreateTemplateRequest();
        final Template template = new Template();
        request.setTemplate(template);
        when(mockAmazonSimpleEmailServiceClient.createTemplate(request)).thenThrow(InvalidTemplateException.class);

        // Run the test
        assertThrows(InvalidTemplateException.class, () -> myClassUnderTest.tryCreateTemplate());
    }

    @Test
    void testTryCreateTemplate_AmazonSimpleEmailServiceClientThrowsLimitExceededException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.createTemplate(...).
        final CreateTemplateRequest request = new CreateTemplateRequest();
        final Template template = new Template();
        request.setTemplate(template);
        when(mockAmazonSimpleEmailServiceClient.createTemplate(request)).thenThrow(LimitExceededException.class);

        // Run the test
        assertThrows(LimitExceededException.class, () -> myClassUnderTest.tryCreateTemplate());
    }

    @Test
    void testTryDeleteConfigurationSet() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.deleteConfigurationSet(...).
        final DeleteConfigurationSetRequest request = new DeleteConfigurationSetRequest();
        request.setConfigurationSetName("configurationSetName");
        when(mockAmazonSimpleEmailServiceClient.deleteConfigurationSet(request))
                .thenReturn(new DeleteConfigurationSetResult());

        // Run the test
        myClassUnderTest.tryDeleteConfigurationSet();

        // Verify the results
    }

    @Test
    void testTryDeleteConfigurationSet_AmazonSimpleEmailServiceClientThrowsConfigurationSetDoesNotExistException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.deleteConfigurationSet(...).
        final DeleteConfigurationSetRequest request = new DeleteConfigurationSetRequest();
        request.setConfigurationSetName("configurationSetName");
        when(mockAmazonSimpleEmailServiceClient.deleteConfigurationSet(request))
                .thenThrow(ConfigurationSetDoesNotExistException.class);

        // Run the test
        assertThrows(ConfigurationSetDoesNotExistException.class, () -> myClassUnderTest.tryDeleteConfigurationSet());
    }

    @Test
    void testTryDeleteConfigurationSetEventDestination() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.deleteConfigurationSetEventDestination(...).
        final DeleteConfigurationSetEventDestinationResult deleteConfigurationSetEventDestinationResult = new DeleteConfigurationSetEventDestinationResult();
        final DeleteConfigurationSetEventDestinationRequest request = new DeleteConfigurationSetEventDestinationRequest();
        request.setConfigurationSetName("configurationSetName");
        request.setEventDestinationName("eventDestinationName");
        when(mockAmazonSimpleEmailServiceClient.deleteConfigurationSetEventDestination(request))
                .thenReturn(deleteConfigurationSetEventDestinationResult);

        // Run the test
        myClassUnderTest.tryDeleteConfigurationSetEventDestination();

        // Verify the results
    }

    @Test
    void testTryDeleteConfigurationSetEventDestination_AmazonSimpleEmailServiceClientThrowsConfigurationSetDoesNotExistException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.deleteConfigurationSetEventDestination(...).
        final DeleteConfigurationSetEventDestinationRequest request = new DeleteConfigurationSetEventDestinationRequest();
        request.setConfigurationSetName("configurationSetName");
        request.setEventDestinationName("eventDestinationName");
        when(mockAmazonSimpleEmailServiceClient.deleteConfigurationSetEventDestination(request))
                .thenThrow(ConfigurationSetDoesNotExistException.class);

        // Run the test
        assertThrows(ConfigurationSetDoesNotExistException.class,
                () -> myClassUnderTest.tryDeleteConfigurationSetEventDestination());
    }

    @Test
    void testTryDeleteConfigurationSetEventDestination_AmazonSimpleEmailServiceClientThrowsEventDestinationDoesNotExistException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.deleteConfigurationSetEventDestination(...).
        final DeleteConfigurationSetEventDestinationRequest request = new DeleteConfigurationSetEventDestinationRequest();
        request.setConfigurationSetName("configurationSetName");
        request.setEventDestinationName("eventDestinationName");
        when(mockAmazonSimpleEmailServiceClient.deleteConfigurationSetEventDestination(request))
                .thenThrow(EventDestinationDoesNotExistException.class);

        // Run the test
        assertThrows(EventDestinationDoesNotExistException.class,
                () -> myClassUnderTest.tryDeleteConfigurationSetEventDestination());
    }

    @Test
    void testTryDeleteConfigurationSetTrackingOptions() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.deleteConfigurationSetTrackingOptions(...).
        final DeleteConfigurationSetTrackingOptionsResult deleteConfigurationSetTrackingOptionsResult = new DeleteConfigurationSetTrackingOptionsResult();
        final DeleteConfigurationSetTrackingOptionsRequest request = new DeleteConfigurationSetTrackingOptionsRequest();
        request.setConfigurationSetName("configurationSetName");
        when(mockAmazonSimpleEmailServiceClient.deleteConfigurationSetTrackingOptions(request))
                .thenReturn(deleteConfigurationSetTrackingOptionsResult);

        // Run the test
        myClassUnderTest.tryDeleteConfigurationSetTrackingOptions();

        // Verify the results
    }

    @Test
    void testTryDeleteConfigurationSetTrackingOptions_AmazonSimpleEmailServiceClientThrowsConfigurationSetDoesNotExistException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.deleteConfigurationSetTrackingOptions(...).
        final DeleteConfigurationSetTrackingOptionsRequest request = new DeleteConfigurationSetTrackingOptionsRequest();
        request.setConfigurationSetName("configurationSetName");
        when(mockAmazonSimpleEmailServiceClient.deleteConfigurationSetTrackingOptions(request))
                .thenThrow(ConfigurationSetDoesNotExistException.class);

        // Run the test
        assertThrows(ConfigurationSetDoesNotExistException.class,
                () -> myClassUnderTest.tryDeleteConfigurationSetTrackingOptions());
    }

    @Test
    void testTryDeleteConfigurationSetTrackingOptions_AmazonSimpleEmailServiceClientThrowsTrackingOptionsDoesNotExistException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.deleteConfigurationSetTrackingOptions(...).
        final DeleteConfigurationSetTrackingOptionsRequest request = new DeleteConfigurationSetTrackingOptionsRequest();
        request.setConfigurationSetName("configurationSetName");
        when(mockAmazonSimpleEmailServiceClient.deleteConfigurationSetTrackingOptions(request))
                .thenThrow(TrackingOptionsDoesNotExistException.class);

        // Run the test
        assertThrows(TrackingOptionsDoesNotExistException.class,
                () -> myClassUnderTest.tryDeleteConfigurationSetTrackingOptions());
    }

    @Test
    void testTryDeleteCustomVerificationEmailTemplate() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.deleteCustomVerificationEmailTemplate(...).
        final DeleteCustomVerificationEmailTemplateResult deleteCustomVerificationEmailTemplateResult = new DeleteCustomVerificationEmailTemplateResult();
        final DeleteCustomVerificationEmailTemplateRequest request = new DeleteCustomVerificationEmailTemplateRequest();
        request.setTemplateName("templateName");
        when(mockAmazonSimpleEmailServiceClient.deleteCustomVerificationEmailTemplate(request))
                .thenReturn(deleteCustomVerificationEmailTemplateResult);

        // Run the test
        myClassUnderTest.tryDeleteCustomVerificationEmailTemplate();

        // Verify the results
    }

    @Test
    void testTryDeleteIdentity() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.deleteIdentity(...).
        final DeleteIdentityRequest request = new DeleteIdentityRequest();
        request.setIdentity("identity");
        when(mockAmazonSimpleEmailServiceClient.deleteIdentity(request)).thenReturn(new DeleteIdentityResult());

        // Run the test
        myClassUnderTest.tryDeleteIdentity();

        // Verify the results
    }

    @Test
    void testTryDeleteIdentityPolicy() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.deleteIdentityPolicy(...).
        final DeleteIdentityPolicyRequest request = new DeleteIdentityPolicyRequest();
        request.setIdentity("identity");
        request.setPolicyName("policyName");
        when(mockAmazonSimpleEmailServiceClient.deleteIdentityPolicy(request))
                .thenReturn(new DeleteIdentityPolicyResult());

        // Run the test
        myClassUnderTest.tryDeleteIdentityPolicy();

        // Verify the results
    }

    @Test
    void testTryDeleteReceiptFilter() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.deleteReceiptFilter(...).
        final DeleteReceiptFilterRequest request = new DeleteReceiptFilterRequest();
        request.setFilterName("filterName");
        when(mockAmazonSimpleEmailServiceClient.deleteReceiptFilter(request))
                .thenReturn(new DeleteReceiptFilterResult());

        // Run the test
        myClassUnderTest.tryDeleteReceiptFilter();

        // Verify the results
    }

    @Test
    void testTryDeleteReceiptRule() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.deleteReceiptRule(...).
        final DeleteReceiptRuleRequest request = new DeleteReceiptRuleRequest();
        request.setRuleSetName("ruleSetName");
        request.setRuleName("ruleName");
        when(mockAmazonSimpleEmailServiceClient.deleteReceiptRule(request)).thenReturn(new DeleteReceiptRuleResult());

        // Run the test
        myClassUnderTest.tryDeleteReceiptRule();

        // Verify the results
    }

    @Test
    void testTryDeleteReceiptRule_AmazonSimpleEmailServiceClientThrowsRuleSetDoesNotExistException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.deleteReceiptRule(...).
        final DeleteReceiptRuleRequest request = new DeleteReceiptRuleRequest();
        request.setRuleSetName("ruleSetName");
        request.setRuleName("ruleName");
        when(mockAmazonSimpleEmailServiceClient.deleteReceiptRule(request))
                .thenThrow(RuleSetDoesNotExistException.class);

        // Run the test
        assertThrows(RuleSetDoesNotExistException.class, () -> myClassUnderTest.tryDeleteReceiptRule());
    }

    @Test
    void testTryDeleteReceiptRuleSet() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.deleteReceiptRuleSet(...).
        final DeleteReceiptRuleSetRequest request = new DeleteReceiptRuleSetRequest();
        request.setRuleSetName("ruleSetName");
        when(mockAmazonSimpleEmailServiceClient.deleteReceiptRuleSet(request))
                .thenReturn(new DeleteReceiptRuleSetResult());

        // Run the test
        myClassUnderTest.tryDeleteReceiptRuleSet();

        // Verify the results
    }

    @Test
    void testTryDeleteReceiptRuleSet_AmazonSimpleEmailServiceClientThrowsCannotDeleteException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.deleteReceiptRuleSet(...).
        final DeleteReceiptRuleSetRequest request = new DeleteReceiptRuleSetRequest();
        request.setRuleSetName("ruleSetName");
        when(mockAmazonSimpleEmailServiceClient.deleteReceiptRuleSet(request)).thenThrow(CannotDeleteException.class);

        // Run the test
        assertThrows(CannotDeleteException.class, () -> myClassUnderTest.tryDeleteReceiptRuleSet());
    }

    @Test
    void testTryDeleteTemplate() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.deleteTemplate(...).
        final DeleteTemplateRequest request = new DeleteTemplateRequest();
        request.setTemplateName("templateName");
        when(mockAmazonSimpleEmailServiceClient.deleteTemplate(request)).thenReturn(new DeleteTemplateResult());

        // Run the test
        myClassUnderTest.tryDeleteTemplate();

        // Verify the results
    }

    @Test
    void testTryDeleteVerifiedEmailAddress() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.deleteVerifiedEmailAddress(...).
        final DeleteVerifiedEmailAddressRequest request = new DeleteVerifiedEmailAddressRequest();
        request.setEmailAddress("emailAddress");
        when(mockAmazonSimpleEmailServiceClient.deleteVerifiedEmailAddress(request))
                .thenReturn(new DeleteVerifiedEmailAddressResult());

        // Run the test
        myClassUnderTest.tryDeleteVerifiedEmailAddress();

        // Verify the results
    }

    @Test
    void testTryDescribeActiveReceiptRuleSet() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.describeActiveReceiptRuleSet(...).
        final DescribeActiveReceiptRuleSetResult describeActiveReceiptRuleSetResult = new DescribeActiveReceiptRuleSetResult();
        when(mockAmazonSimpleEmailServiceClient.describeActiveReceiptRuleSet(
                new DescribeActiveReceiptRuleSetRequest())).thenReturn(describeActiveReceiptRuleSetResult);

        // Run the test
        myClassUnderTest.tryDescribeActiveReceiptRuleSet();

        // Verify the results
    }

    @Test
    void testTryDescribeConfigurationSet() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.describeConfigurationSet(...).
        final DescribeConfigurationSetResult describeConfigurationSetResult = new DescribeConfigurationSetResult();
        final DescribeConfigurationSetRequest request = new DescribeConfigurationSetRequest();
        request.setConfigurationSetName("configurationSetName");
        request.setConfigurationSetAttributeNames(Arrays.asList("value"));
        when(mockAmazonSimpleEmailServiceClient.describeConfigurationSet(request))
                .thenReturn(describeConfigurationSetResult);

        // Run the test
        myClassUnderTest.tryDescribeConfigurationSet();

        // Verify the results
    }

    @Test
    void testTryDescribeConfigurationSet_AmazonSimpleEmailServiceClientThrowsConfigurationSetDoesNotExistException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.describeConfigurationSet(...).
        final DescribeConfigurationSetRequest request = new DescribeConfigurationSetRequest();
        request.setConfigurationSetName("configurationSetName");
        request.setConfigurationSetAttributeNames(Arrays.asList("value"));
        when(mockAmazonSimpleEmailServiceClient.describeConfigurationSet(request))
                .thenThrow(ConfigurationSetDoesNotExistException.class);

        // Run the test
        assertThrows(ConfigurationSetDoesNotExistException.class, () -> myClassUnderTest.tryDescribeConfigurationSet());
    }

    @Test
    void testTryDescribeReceiptRule() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.describeReceiptRule(...).
        final DescribeReceiptRuleResult describeReceiptRuleResult = new DescribeReceiptRuleResult();
        final DescribeReceiptRuleRequest request = new DescribeReceiptRuleRequest();
        request.setRuleSetName("ruleSetName");
        request.setRuleName("ruleName");
        when(mockAmazonSimpleEmailServiceClient.describeReceiptRule(request)).thenReturn(describeReceiptRuleResult);

        // Run the test
        myClassUnderTest.tryDescribeReceiptRule();

        // Verify the results
    }

    @Test
    void testTryDescribeReceiptRule_AmazonSimpleEmailServiceClientThrowsRuleDoesNotExistException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.describeReceiptRule(...).
        final DescribeReceiptRuleRequest request = new DescribeReceiptRuleRequest();
        request.setRuleSetName("ruleSetName");
        request.setRuleName("ruleName");
        when(mockAmazonSimpleEmailServiceClient.describeReceiptRule(request))
                .thenThrow(RuleDoesNotExistException.class);

        // Run the test
        assertThrows(RuleDoesNotExistException.class, () -> myClassUnderTest.tryDescribeReceiptRule());
    }

    @Test
    void testTryDescribeReceiptRule_AmazonSimpleEmailServiceClientThrowsRuleSetDoesNotExistException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.describeReceiptRule(...).
        final DescribeReceiptRuleRequest request = new DescribeReceiptRuleRequest();
        request.setRuleSetName("ruleSetName");
        request.setRuleName("ruleName");
        when(mockAmazonSimpleEmailServiceClient.describeReceiptRule(request))
                .thenThrow(RuleSetDoesNotExistException.class);

        // Run the test
        assertThrows(RuleSetDoesNotExistException.class, () -> myClassUnderTest.tryDescribeReceiptRule());
    }

    @Test
    void testTryDescribeReceiptRuleSet() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.describeReceiptRuleSet(...).
        final DescribeReceiptRuleSetResult describeReceiptRuleSetResult = new DescribeReceiptRuleSetResult();
        final DescribeReceiptRuleSetRequest request = new DescribeReceiptRuleSetRequest();
        request.setRuleSetName("ruleSetName");
        when(mockAmazonSimpleEmailServiceClient.describeReceiptRuleSet(request))
                .thenReturn(describeReceiptRuleSetResult);

        // Run the test
        myClassUnderTest.tryDescribeReceiptRuleSet();

        // Verify the results
    }

    @Test
    void testTryDescribeReceiptRuleSet_AmazonSimpleEmailServiceClientThrowsRuleSetDoesNotExistException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.describeReceiptRuleSet(...).
        final DescribeReceiptRuleSetRequest request = new DescribeReceiptRuleSetRequest();
        request.setRuleSetName("ruleSetName");
        when(mockAmazonSimpleEmailServiceClient.describeReceiptRuleSet(request))
                .thenThrow(RuleSetDoesNotExistException.class);

        // Run the test
        assertThrows(RuleSetDoesNotExistException.class, () -> myClassUnderTest.tryDescribeReceiptRuleSet());
    }

    @Test
    void testTryGetAccountSendingEnabled() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.getAccountSendingEnabled(...).
        final GetAccountSendingEnabledResult getAccountSendingEnabledResult = new GetAccountSendingEnabledResult();
        when(mockAmazonSimpleEmailServiceClient.getAccountSendingEnabled(
                new GetAccountSendingEnabledRequest())).thenReturn(getAccountSendingEnabledResult);

        // Run the test
        myClassUnderTest.tryGetAccountSendingEnabled();

        // Verify the results
    }

    @Test
    void testTryGetCustomVerificationEmailTemplate() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.getCustomVerificationEmailTemplate(...).
        final GetCustomVerificationEmailTemplateResult getCustomVerificationEmailTemplateResult = new GetCustomVerificationEmailTemplateResult();
        final GetCustomVerificationEmailTemplateRequest request = new GetCustomVerificationEmailTemplateRequest();
        request.setTemplateName("templateName");
        when(mockAmazonSimpleEmailServiceClient.getCustomVerificationEmailTemplate(request))
                .thenReturn(getCustomVerificationEmailTemplateResult);

        // Run the test
        myClassUnderTest.tryGetCustomVerificationEmailTemplate();

        // Verify the results
    }

    @Test
    void testTryGetCustomVerificationEmailTemplate_AmazonSimpleEmailServiceClientThrowsCustomVerificationEmailTemplateDoesNotExistException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.getCustomVerificationEmailTemplate(...).
        final GetCustomVerificationEmailTemplateRequest request = new GetCustomVerificationEmailTemplateRequest();
        request.setTemplateName("templateName");
        when(mockAmazonSimpleEmailServiceClient.getCustomVerificationEmailTemplate(request))
                .thenThrow(CustomVerificationEmailTemplateDoesNotExistException.class);

        // Run the test
        assertThrows(CustomVerificationEmailTemplateDoesNotExistException.class,
                () -> myClassUnderTest.tryGetCustomVerificationEmailTemplate());
    }

    @Test
    void testTryGetIdentityDkimAttributes() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.getIdentityDkimAttributes(...).
        final GetIdentityDkimAttributesResult getIdentityDkimAttributesResult = new GetIdentityDkimAttributesResult();
        final GetIdentityDkimAttributesRequest request = new GetIdentityDkimAttributesRequest();
        request.setIdentities(Arrays.asList("value"));
        when(mockAmazonSimpleEmailServiceClient.getIdentityDkimAttributes(request))
                .thenReturn(getIdentityDkimAttributesResult);

        // Run the test
        myClassUnderTest.tryGetIdentityDkimAttributes();

        // Verify the results
    }

    @Test
    void testTryGetIdentityMailFromDomainAttributes() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.getIdentityMailFromDomainAttributes(...).
        final GetIdentityMailFromDomainAttributesResult getIdentityMailFromDomainAttributesResult = new GetIdentityMailFromDomainAttributesResult();
        final GetIdentityMailFromDomainAttributesRequest request = new GetIdentityMailFromDomainAttributesRequest();
        request.setIdentities(Arrays.asList("value"));
        when(mockAmazonSimpleEmailServiceClient.getIdentityMailFromDomainAttributes(request))
                .thenReturn(getIdentityMailFromDomainAttributesResult);

        // Run the test
        myClassUnderTest.tryGetIdentityMailFromDomainAttributes();

        // Verify the results
    }

    @Test
    void testTryGetIdentityNotificationAttributes() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.getIdentityNotificationAttributes(...).
        final GetIdentityNotificationAttributesResult getIdentityNotificationAttributesResult = new GetIdentityNotificationAttributesResult();
        final GetIdentityNotificationAttributesRequest request = new GetIdentityNotificationAttributesRequest();
        request.setIdentities(Arrays.asList("value"));
        when(mockAmazonSimpleEmailServiceClient.getIdentityNotificationAttributes(request))
                .thenReturn(getIdentityNotificationAttributesResult);

        // Run the test
        myClassUnderTest.tryGetIdentityNotificationAttributes();

        // Verify the results
    }

    @Test
    void testTryGetIdentityPolicies() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.getIdentityPolicies(...).
        final GetIdentityPoliciesResult getIdentityPoliciesResult = new GetIdentityPoliciesResult();
        final GetIdentityPoliciesRequest request = new GetIdentityPoliciesRequest();
        request.setIdentity("identity");
        request.setPolicyNames(Arrays.asList("value"));
        when(mockAmazonSimpleEmailServiceClient.getIdentityPolicies(request)).thenReturn(getIdentityPoliciesResult);

        // Run the test
        myClassUnderTest.tryGetIdentityPolicies();

        // Verify the results
    }

    @Test
    void testTryGetIdentityVerificationAttributes() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.getIdentityVerificationAttributes(...).
        final GetIdentityVerificationAttributesResult getIdentityVerificationAttributesResult = new GetIdentityVerificationAttributesResult();
        final GetIdentityVerificationAttributesRequest request = new GetIdentityVerificationAttributesRequest();
        request.setIdentities(Arrays.asList("value"));
        when(mockAmazonSimpleEmailServiceClient.getIdentityVerificationAttributes(request))
                .thenReturn(getIdentityVerificationAttributesResult);

        // Run the test
        myClassUnderTest.tryGetIdentityVerificationAttributes();

        // Verify the results
    }

    @Test
    void testTryGetSendQuota() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.getSendQuota(...).
        final GetSendQuotaResult getSendQuotaResult = new GetSendQuotaResult();
        when(mockAmazonSimpleEmailServiceClient.getSendQuota(new GetSendQuotaRequest())).thenReturn(getSendQuotaResult);

        // Run the test
        myClassUnderTest.tryGetSendQuota();

        // Verify the results
    }

    @Test
    void testTryGetSendQuota1() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.getSendQuota(...).
        final GetSendQuotaResult getSendQuotaResult = new GetSendQuotaResult();
        when(mockAmazonSimpleEmailServiceClient.getSendQuota()).thenReturn(getSendQuotaResult);

        // Run the test
        myClassUnderTest.tryGetSendQuota1();

        // Verify the results
    }

    @Test
    void testTryGetSendStatistics() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.getSendStatistics(...).
        final GetSendStatisticsResult getSendStatisticsResult = new GetSendStatisticsResult();
        when(mockAmazonSimpleEmailServiceClient.getSendStatistics(new GetSendStatisticsRequest()))
                .thenReturn(getSendStatisticsResult);

        // Run the test
        myClassUnderTest.tryGetSendStatistics();

        // Verify the results
    }

    @Test
    void testTryGetSendStatistics1() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.getSendStatistics(...).
        final GetSendStatisticsResult getSendStatisticsResult = new GetSendStatisticsResult();
        when(mockAmazonSimpleEmailServiceClient.getSendStatistics()).thenReturn(getSendStatisticsResult);

        // Run the test
        myClassUnderTest.tryGetSendStatistics1();

        // Verify the results
    }

    @Test
    void testTryGetTemplate() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.getTemplate(...).
        final GetTemplateResult getTemplateResult = new GetTemplateResult();
        final GetTemplateRequest request = new GetTemplateRequest();
        request.setTemplateName("templateName");
        when(mockAmazonSimpleEmailServiceClient.getTemplate(request)).thenReturn(getTemplateResult);

        // Run the test
        myClassUnderTest.tryGetTemplate();

        // Verify the results
    }

    @Test
    void testTryGetTemplate_AmazonSimpleEmailServiceClientThrowsTemplateDoesNotExistException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.getTemplate(...).
        final GetTemplateRequest request = new GetTemplateRequest();
        request.setTemplateName("templateName");
        when(mockAmazonSimpleEmailServiceClient.getTemplate(request)).thenThrow(TemplateDoesNotExistException.class);

        // Run the test
        assertThrows(TemplateDoesNotExistException.class, () -> myClassUnderTest.tryGetTemplate());
    }

    @Test
    void testTryListConfigurationSets() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.listConfigurationSets(...).
        final ListConfigurationSetsResult listConfigurationSetsResult = new ListConfigurationSetsResult();
        final ListConfigurationSetsRequest request = new ListConfigurationSetsRequest();
        request.setNextToken("nextToken");
        request.setMaxItems(0);
        when(mockAmazonSimpleEmailServiceClient.listConfigurationSets(request)).thenReturn(listConfigurationSetsResult);

        // Run the test
        myClassUnderTest.tryListConfigurationSets();

        // Verify the results
    }

    @Test
    void testTryListCustomVerificationEmailTemplates() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.listCustomVerificationEmailTemplates(...).
        final ListCustomVerificationEmailTemplatesResult listCustomVerificationEmailTemplatesResult = new ListCustomVerificationEmailTemplatesResult();
        final ListCustomVerificationEmailTemplatesRequest request = new ListCustomVerificationEmailTemplatesRequest();
        request.setNextToken("nextToken");
        request.setMaxResults(0);
        when(mockAmazonSimpleEmailServiceClient.listCustomVerificationEmailTemplates(request))
                .thenReturn(listCustomVerificationEmailTemplatesResult);

        // Run the test
        myClassUnderTest.tryListCustomVerificationEmailTemplates();

        // Verify the results
    }

    @Test
    void testTryListIdentities() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.listIdentities(...).
        final ListIdentitiesResult listIdentitiesResult = new ListIdentitiesResult();
        final ListIdentitiesRequest request = new ListIdentitiesRequest();
        when(mockAmazonSimpleEmailServiceClient.listIdentities(request)).thenReturn(listIdentitiesResult);

        // Run the test
        myClassUnderTest.tryListIdentities();

        // Verify the results
    }

    @Test
    void testTryListIdentities1() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.listIdentities(...).
        final ListIdentitiesResult listIdentitiesResult = new ListIdentitiesResult();
        when(mockAmazonSimpleEmailServiceClient.listIdentities()).thenReturn(listIdentitiesResult);

        // Run the test
        myClassUnderTest.tryListIdentities1();

        // Verify the results
    }

    @Test
    void testTryListIdentityPolicies() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.listIdentityPolicies(...).
        final ListIdentityPoliciesResult listIdentityPoliciesResult = new ListIdentityPoliciesResult();
        final ListIdentityPoliciesRequest request = new ListIdentityPoliciesRequest();
        request.setIdentity("identity");
        when(mockAmazonSimpleEmailServiceClient.listIdentityPolicies(request)).thenReturn(listIdentityPoliciesResult);

        // Run the test
        myClassUnderTest.tryListIdentityPolicies();

        // Verify the results
    }

    @Test
    void testTryListReceiptFilters() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.listReceiptFilters(...).
        final ListReceiptFiltersResult listReceiptFiltersResult = new ListReceiptFiltersResult();
        when(mockAmazonSimpleEmailServiceClient.listReceiptFilters(new ListReceiptFiltersRequest()))
                .thenReturn(listReceiptFiltersResult);

        // Run the test
        myClassUnderTest.tryListReceiptFilters();

        // Verify the results
    }

    @Test
    void testTryListReceiptRuleSets() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.listReceiptRuleSets(...).
        final ListReceiptRuleSetsResult listReceiptRuleSetsResult = new ListReceiptRuleSetsResult();
        final ListReceiptRuleSetsRequest request = new ListReceiptRuleSetsRequest();
        request.setNextToken("nextToken");
        when(mockAmazonSimpleEmailServiceClient.listReceiptRuleSets(request)).thenReturn(listReceiptRuleSetsResult);

        // Run the test
        myClassUnderTest.tryListReceiptRuleSets();

        // Verify the results
    }

    @Test
    void testTryListTemplates() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.listTemplates(...).
        final ListTemplatesResult listTemplatesResult = new ListTemplatesResult();
        final ListTemplatesRequest request = new ListTemplatesRequest();
        request.setNextToken("nextToken");
        request.setMaxItems(0);
        when(mockAmazonSimpleEmailServiceClient.listTemplates(request)).thenReturn(listTemplatesResult);

        // Run the test
        myClassUnderTest.tryListTemplates();

        // Verify the results
    }

    @Test
    void testTryListVerifiedEmailAddresses() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.listVerifiedEmailAddresses(...).
        final ListVerifiedEmailAddressesResult listVerifiedEmailAddressesResult = new ListVerifiedEmailAddressesResult();
        when(mockAmazonSimpleEmailServiceClient.listVerifiedEmailAddresses(
                new ListVerifiedEmailAddressesRequest())).thenReturn(listVerifiedEmailAddressesResult);

        // Run the test
        myClassUnderTest.tryListVerifiedEmailAddresses();

        // Verify the results
    }

    @Test
    void testTryListVerifiedEmailAddresses1() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.listVerifiedEmailAddresses(...).
        final ListVerifiedEmailAddressesResult listVerifiedEmailAddressesResult = new ListVerifiedEmailAddressesResult();
        when(mockAmazonSimpleEmailServiceClient.listVerifiedEmailAddresses())
                .thenReturn(listVerifiedEmailAddressesResult);

        // Run the test
        myClassUnderTest.tryListVerifiedEmailAddresses1();

        // Verify the results
    }

    @Test
    void testTryPutConfigurationSetDeliveryOptions() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.putConfigurationSetDeliveryOptions(...).
        final PutConfigurationSetDeliveryOptionsResult putConfigurationSetDeliveryOptionsResult = new PutConfigurationSetDeliveryOptionsResult();
        final PutConfigurationSetDeliveryOptionsRequest request = new PutConfigurationSetDeliveryOptionsRequest();
        request.setConfigurationSetName("configurationSetName");
        final DeliveryOptions deliveryOptions = new DeliveryOptions();
        request.setDeliveryOptions(deliveryOptions);
        when(mockAmazonSimpleEmailServiceClient.putConfigurationSetDeliveryOptions(request))
                .thenReturn(putConfigurationSetDeliveryOptionsResult);

        // Run the test
        myClassUnderTest.tryPutConfigurationSetDeliveryOptions();

        // Verify the results
    }

    @Test
    void testTryPutConfigurationSetDeliveryOptions_AmazonSimpleEmailServiceClientThrowsConfigurationSetDoesNotExistException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.putConfigurationSetDeliveryOptions(...).
        final PutConfigurationSetDeliveryOptionsRequest request = new PutConfigurationSetDeliveryOptionsRequest();
        request.setConfigurationSetName("configurationSetName");
        final DeliveryOptions deliveryOptions = new DeliveryOptions();
        request.setDeliveryOptions(deliveryOptions);
        when(mockAmazonSimpleEmailServiceClient.putConfigurationSetDeliveryOptions(request))
                .thenThrow(ConfigurationSetDoesNotExistException.class);

        // Run the test
        assertThrows(ConfigurationSetDoesNotExistException.class,
                () -> myClassUnderTest.tryPutConfigurationSetDeliveryOptions());
    }

    @Test
    void testTryPutConfigurationSetDeliveryOptions_AmazonSimpleEmailServiceClientThrowsInvalidDeliveryOptionsException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.putConfigurationSetDeliveryOptions(...).
        final PutConfigurationSetDeliveryOptionsRequest request = new PutConfigurationSetDeliveryOptionsRequest();
        request.setConfigurationSetName("configurationSetName");
        final DeliveryOptions deliveryOptions = new DeliveryOptions();
        request.setDeliveryOptions(deliveryOptions);
        when(mockAmazonSimpleEmailServiceClient.putConfigurationSetDeliveryOptions(request))
                .thenThrow(InvalidDeliveryOptionsException.class);

        // Run the test
        assertThrows(InvalidDeliveryOptionsException.class,
                () -> myClassUnderTest.tryPutConfigurationSetDeliveryOptions());
    }

    @Test
    void testTryPutIdentityPolicy() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.putIdentityPolicy(...).
        final PutIdentityPolicyRequest request = new PutIdentityPolicyRequest();
        request.setIdentity("identity");
        request.setPolicyName("policyName");
        request.setPolicy("policy");
        when(mockAmazonSimpleEmailServiceClient.putIdentityPolicy(request)).thenReturn(new PutIdentityPolicyResult());

        // Run the test
        myClassUnderTest.tryPutIdentityPolicy();

        // Verify the results
    }

    @Test
    void testTryPutIdentityPolicy_AmazonSimpleEmailServiceClientThrowsInvalidPolicyException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.putIdentityPolicy(...).
        final PutIdentityPolicyRequest request = new PutIdentityPolicyRequest();
        request.setIdentity("identity");
        request.setPolicyName("policyName");
        request.setPolicy("policy");
        when(mockAmazonSimpleEmailServiceClient.putIdentityPolicy(request)).thenThrow(InvalidPolicyException.class);

        // Run the test
        assertThrows(InvalidPolicyException.class, () -> myClassUnderTest.tryPutIdentityPolicy());
    }

    @Test
    void testTryReorderReceiptRuleSet() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.reorderReceiptRuleSet(...).
        final ReorderReceiptRuleSetRequest request = new ReorderReceiptRuleSetRequest();
        request.setRuleSetName("ruleSetName");
        request.setRuleNames(Arrays.asList("value"));
        when(mockAmazonSimpleEmailServiceClient.reorderReceiptRuleSet(request))
                .thenReturn(new ReorderReceiptRuleSetResult());

        // Run the test
        myClassUnderTest.tryReorderReceiptRuleSet();

        // Verify the results
    }

    @Test
    void testTryReorderReceiptRuleSet_AmazonSimpleEmailServiceClientThrowsRuleSetDoesNotExistException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.reorderReceiptRuleSet(...).
        final ReorderReceiptRuleSetRequest request = new ReorderReceiptRuleSetRequest();
        request.setRuleSetName("ruleSetName");
        request.setRuleNames(Arrays.asList("value"));
        when(mockAmazonSimpleEmailServiceClient.reorderReceiptRuleSet(request))
                .thenThrow(RuleSetDoesNotExistException.class);

        // Run the test
        assertThrows(RuleSetDoesNotExistException.class, () -> myClassUnderTest.tryReorderReceiptRuleSet());
    }

    @Test
    void testTryReorderReceiptRuleSet_AmazonSimpleEmailServiceClientThrowsRuleDoesNotExistException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.reorderReceiptRuleSet(...).
        final ReorderReceiptRuleSetRequest request = new ReorderReceiptRuleSetRequest();
        request.setRuleSetName("ruleSetName");
        request.setRuleNames(Arrays.asList("value"));
        when(mockAmazonSimpleEmailServiceClient.reorderReceiptRuleSet(request))
                .thenThrow(RuleDoesNotExistException.class);

        // Run the test
        assertThrows(RuleDoesNotExistException.class, () -> myClassUnderTest.tryReorderReceiptRuleSet());
    }

    @Test
    void testTrySendBounce() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.sendBounce(...).
        final SendBounceResult sendBounceResult = new SendBounceResult();
        final SendBounceRequest request = new SendBounceRequest();
        request.setOriginalMessageId("originalMessageId");
        request.setBounceSender("bounceSender");
        request.setExplanation("explanation");
        final MessageDsn messageDsn = new MessageDsn();
        request.setMessageDsn(messageDsn);
        final BouncedRecipientInfo bouncedRecipientInfo = new BouncedRecipientInfo();
        request.setBouncedRecipientInfoList(Arrays.asList(bouncedRecipientInfo));
        request.setBounceSenderArn("bounceSenderArn");
        when(mockAmazonSimpleEmailServiceClient.sendBounce(request)).thenReturn(sendBounceResult);

        // Run the test
        myClassUnderTest.trySendBounce();

        // Verify the results
    }

    @Test
    void testTrySendBounce_AmazonSimpleEmailServiceClientThrowsMessageRejectedException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.sendBounce(...).
        final SendBounceRequest request = new SendBounceRequest();
        request.setOriginalMessageId("originalMessageId");
        request.setBounceSender("bounceSender");
        request.setExplanation("explanation");
        final MessageDsn messageDsn = new MessageDsn();
        request.setMessageDsn(messageDsn);
        final BouncedRecipientInfo bouncedRecipientInfo = new BouncedRecipientInfo();
        request.setBouncedRecipientInfoList(Arrays.asList(bouncedRecipientInfo));
        request.setBounceSenderArn("bounceSenderArn");
        when(mockAmazonSimpleEmailServiceClient.sendBounce(request)).thenThrow(MessageRejectedException.class);

        // Run the test
        assertThrows(MessageRejectedException.class, () -> myClassUnderTest.trySendBounce());
    }

    @Test
    void testTrySendBulkTemplatedEmail() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.sendBulkTemplatedEmail(...).
        final SendBulkTemplatedEmailResult sendBulkTemplatedEmailResult = new SendBulkTemplatedEmailResult();
        final SendBulkTemplatedEmailRequest request = new SendBulkTemplatedEmailRequest();
        request.setSource("source");
        request.setSourceArn("sourceArn");
        request.setReplyToAddresses(Arrays.asList("value"));
        request.setReturnPath("returnPath");
        request.setReturnPathArn("returnPathArn");
        request.setConfigurationSetName("configurationSetName");
        final MessageTag messageTag = new MessageTag();
        request.setDefaultTags(Arrays.asList(messageTag));
        request.setTemplate("template");
        when(mockAmazonSimpleEmailServiceClient.sendBulkTemplatedEmail(request))
                .thenReturn(sendBulkTemplatedEmailResult);

        // Run the test
        myClassUnderTest.trySendBulkTemplatedEmail();

        // Verify the results
    }

    @Test
    void testTrySendBulkTemplatedEmail_AmazonSimpleEmailServiceClientThrowsMessageRejectedException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.sendBulkTemplatedEmail(...).
        final SendBulkTemplatedEmailRequest request = new SendBulkTemplatedEmailRequest();
        request.setSource("source");
        request.setSourceArn("sourceArn");
        request.setReplyToAddresses(Arrays.asList("value"));
        request.setReturnPath("returnPath");
        request.setReturnPathArn("returnPathArn");
        request.setConfigurationSetName("configurationSetName");
        final MessageTag messageTag = new MessageTag();
        request.setDefaultTags(Arrays.asList(messageTag));
        request.setTemplate("template");
        when(mockAmazonSimpleEmailServiceClient.sendBulkTemplatedEmail(request))
                .thenThrow(MessageRejectedException.class);

        // Run the test
        assertThrows(MessageRejectedException.class, () -> myClassUnderTest.trySendBulkTemplatedEmail());
    }

    @Test
    void testTrySendBulkTemplatedEmail_AmazonSimpleEmailServiceClientThrowsMailFromDomainNotVerifiedException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.sendBulkTemplatedEmail(...).
        final SendBulkTemplatedEmailRequest request = new SendBulkTemplatedEmailRequest();
        request.setSource("source");
        request.setSourceArn("sourceArn");
        request.setReplyToAddresses(Arrays.asList("value"));
        request.setReturnPath("returnPath");
        request.setReturnPathArn("returnPathArn");
        request.setConfigurationSetName("configurationSetName");
        final MessageTag messageTag = new MessageTag();
        request.setDefaultTags(Arrays.asList(messageTag));
        request.setTemplate("template");
        when(mockAmazonSimpleEmailServiceClient.sendBulkTemplatedEmail(request))
                .thenThrow(MailFromDomainNotVerifiedException.class);

        // Run the test
        assertThrows(MailFromDomainNotVerifiedException.class, () -> myClassUnderTest.trySendBulkTemplatedEmail());
    }

    @Test
    void testTrySendBulkTemplatedEmail_AmazonSimpleEmailServiceClientThrowsConfigurationSetDoesNotExistException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.sendBulkTemplatedEmail(...).
        final SendBulkTemplatedEmailRequest request = new SendBulkTemplatedEmailRequest();
        request.setSource("source");
        request.setSourceArn("sourceArn");
        request.setReplyToAddresses(Arrays.asList("value"));
        request.setReturnPath("returnPath");
        request.setReturnPathArn("returnPathArn");
        request.setConfigurationSetName("configurationSetName");
        final MessageTag messageTag = new MessageTag();
        request.setDefaultTags(Arrays.asList(messageTag));
        request.setTemplate("template");
        when(mockAmazonSimpleEmailServiceClient.sendBulkTemplatedEmail(request))
                .thenThrow(ConfigurationSetDoesNotExistException.class);

        // Run the test
        assertThrows(ConfigurationSetDoesNotExistException.class, () -> myClassUnderTest.trySendBulkTemplatedEmail());
    }

    @Test
    void testTrySendBulkTemplatedEmail_AmazonSimpleEmailServiceClientThrowsTemplateDoesNotExistException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.sendBulkTemplatedEmail(...).
        final SendBulkTemplatedEmailRequest request = new SendBulkTemplatedEmailRequest();
        request.setSource("source");
        request.setSourceArn("sourceArn");
        request.setReplyToAddresses(Arrays.asList("value"));
        request.setReturnPath("returnPath");
        request.setReturnPathArn("returnPathArn");
        request.setConfigurationSetName("configurationSetName");
        final MessageTag messageTag = new MessageTag();
        request.setDefaultTags(Arrays.asList(messageTag));
        request.setTemplate("template");
        when(mockAmazonSimpleEmailServiceClient.sendBulkTemplatedEmail(request))
                .thenThrow(TemplateDoesNotExistException.class);

        // Run the test
        assertThrows(TemplateDoesNotExistException.class, () -> myClassUnderTest.trySendBulkTemplatedEmail());
    }

    @Test
    void testTrySendBulkTemplatedEmail_AmazonSimpleEmailServiceClientThrowsConfigurationSetSendingPausedException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.sendBulkTemplatedEmail(...).
        final SendBulkTemplatedEmailRequest request = new SendBulkTemplatedEmailRequest();
        request.setSource("source");
        request.setSourceArn("sourceArn");
        request.setReplyToAddresses(Arrays.asList("value"));
        request.setReturnPath("returnPath");
        request.setReturnPathArn("returnPathArn");
        request.setConfigurationSetName("configurationSetName");
        final MessageTag messageTag = new MessageTag();
        request.setDefaultTags(Arrays.asList(messageTag));
        request.setTemplate("template");
        when(mockAmazonSimpleEmailServiceClient.sendBulkTemplatedEmail(request))
                .thenThrow(ConfigurationSetSendingPausedException.class);

        // Run the test
        assertThrows(ConfigurationSetSendingPausedException.class, () -> myClassUnderTest.trySendBulkTemplatedEmail());
    }

    @Test
    void testTrySendBulkTemplatedEmail_AmazonSimpleEmailServiceClientThrowsAccountSendingPausedException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.sendBulkTemplatedEmail(...).
        final SendBulkTemplatedEmailRequest request = new SendBulkTemplatedEmailRequest();
        request.setSource("source");
        request.setSourceArn("sourceArn");
        request.setReplyToAddresses(Arrays.asList("value"));
        request.setReturnPath("returnPath");
        request.setReturnPathArn("returnPathArn");
        request.setConfigurationSetName("configurationSetName");
        final MessageTag messageTag = new MessageTag();
        request.setDefaultTags(Arrays.asList(messageTag));
        request.setTemplate("template");
        when(mockAmazonSimpleEmailServiceClient.sendBulkTemplatedEmail(request))
                .thenThrow(AccountSendingPausedException.class);

        // Run the test
        assertThrows(AccountSendingPausedException.class, () -> myClassUnderTest.trySendBulkTemplatedEmail());
    }

    @Test
    void testTrySendCustomVerificationEmail() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.sendCustomVerificationEmail(...).
        final SendCustomVerificationEmailResult sendCustomVerificationEmailResult = new SendCustomVerificationEmailResult();
        final SendCustomVerificationEmailRequest request = new SendCustomVerificationEmailRequest();
        request.setEmailAddress("emailAddress");
        request.setTemplateName("templateName");
        request.setConfigurationSetName("configurationSetName");
        when(mockAmazonSimpleEmailServiceClient.sendCustomVerificationEmail(request))
                .thenReturn(sendCustomVerificationEmailResult);

        // Run the test
        myClassUnderTest.trySendCustomVerificationEmail();

        // Verify the results
    }

    @Test
    void testTrySendCustomVerificationEmail_AmazonSimpleEmailServiceClientThrowsMessageRejectedException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.sendCustomVerificationEmail(...).
        final SendCustomVerificationEmailRequest request = new SendCustomVerificationEmailRequest();
        request.setEmailAddress("emailAddress");
        request.setTemplateName("templateName");
        request.setConfigurationSetName("configurationSetName");
        when(mockAmazonSimpleEmailServiceClient.sendCustomVerificationEmail(request))
                .thenThrow(MessageRejectedException.class);

        // Run the test
        assertThrows(MessageRejectedException.class, () -> myClassUnderTest.trySendCustomVerificationEmail());
    }

    @Test
    void testTrySendCustomVerificationEmail_AmazonSimpleEmailServiceClientThrowsConfigurationSetDoesNotExistException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.sendCustomVerificationEmail(...).
        final SendCustomVerificationEmailRequest request = new SendCustomVerificationEmailRequest();
        request.setEmailAddress("emailAddress");
        request.setTemplateName("templateName");
        request.setConfigurationSetName("configurationSetName");
        when(mockAmazonSimpleEmailServiceClient.sendCustomVerificationEmail(request))
                .thenThrow(ConfigurationSetDoesNotExistException.class);

        // Run the test
        assertThrows(ConfigurationSetDoesNotExistException.class,
                () -> myClassUnderTest.trySendCustomVerificationEmail());
    }

    @Test
    void testTrySendCustomVerificationEmail_AmazonSimpleEmailServiceClientThrowsCustomVerificationEmailTemplateDoesNotExistException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.sendCustomVerificationEmail(...).
        final SendCustomVerificationEmailRequest request = new SendCustomVerificationEmailRequest();
        request.setEmailAddress("emailAddress");
        request.setTemplateName("templateName");
        request.setConfigurationSetName("configurationSetName");
        when(mockAmazonSimpleEmailServiceClient.sendCustomVerificationEmail(request))
                .thenThrow(CustomVerificationEmailTemplateDoesNotExistException.class);

        // Run the test
        assertThrows(CustomVerificationEmailTemplateDoesNotExistException.class,
                () -> myClassUnderTest.trySendCustomVerificationEmail());
    }

    @Test
    void testTrySendCustomVerificationEmail_AmazonSimpleEmailServiceClientThrowsFromEmailAddressNotVerifiedException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.sendCustomVerificationEmail(...).
        final SendCustomVerificationEmailRequest request = new SendCustomVerificationEmailRequest();
        request.setEmailAddress("emailAddress");
        request.setTemplateName("templateName");
        request.setConfigurationSetName("configurationSetName");
        when(mockAmazonSimpleEmailServiceClient.sendCustomVerificationEmail(request))
                .thenThrow(FromEmailAddressNotVerifiedException.class);

        // Run the test
        assertThrows(FromEmailAddressNotVerifiedException.class,
                () -> myClassUnderTest.trySendCustomVerificationEmail());
    }

    @Test
    void testTrySendCustomVerificationEmail_AmazonSimpleEmailServiceClientThrowsProductionAccessNotGrantedException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.sendCustomVerificationEmail(...).
        final SendCustomVerificationEmailRequest request = new SendCustomVerificationEmailRequest();
        request.setEmailAddress("emailAddress");
        request.setTemplateName("templateName");
        request.setConfigurationSetName("configurationSetName");
        when(mockAmazonSimpleEmailServiceClient.sendCustomVerificationEmail(request))
                .thenThrow(ProductionAccessNotGrantedException.class);

        // Run the test
        assertThrows(ProductionAccessNotGrantedException.class,
                () -> myClassUnderTest.trySendCustomVerificationEmail());
    }

    @Test
    void testTrySendEmail() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.sendEmail(...).
        final SendEmailResult sendEmailResult = new SendEmailResult();
        when(mockAmazonSimpleEmailServiceClient.sendEmail(
                new SendEmailRequest("source", new Destination(Arrays.asList("value")),
                        new Message(new Content("data"), new Body(new Content("data")))))).thenReturn(sendEmailResult);

        // Run the test
        myClassUnderTest.trySendEmail();

        // Verify the results
    }

    @Test
    void testTrySendEmail_AmazonSimpleEmailServiceClientThrowsMessageRejectedException() {
        // Setup
        when(mockAmazonSimpleEmailServiceClient.sendEmail(
                new SendEmailRequest("source", new Destination(Arrays.asList("value")),
                        new Message(new Content("data"), new Body(new Content("data"))))))
                .thenThrow(MessageRejectedException.class);

        // Run the test
        assertThrows(MessageRejectedException.class, () -> myClassUnderTest.trySendEmail());
    }

    @Test
    void testTrySendEmail_AmazonSimpleEmailServiceClientThrowsMailFromDomainNotVerifiedException() {
        // Setup
        when(mockAmazonSimpleEmailServiceClient.sendEmail(
                new SendEmailRequest("source", new Destination(Arrays.asList("value")),
                        new Message(new Content("data"), new Body(new Content("data"))))))
                .thenThrow(MailFromDomainNotVerifiedException.class);

        // Run the test
        assertThrows(MailFromDomainNotVerifiedException.class, () -> myClassUnderTest.trySendEmail());
    }

    @Test
    void testTrySendEmail_AmazonSimpleEmailServiceClientThrowsConfigurationSetDoesNotExistException() {
        // Setup
        when(mockAmazonSimpleEmailServiceClient.sendEmail(
                new SendEmailRequest("source", new Destination(Arrays.asList("value")),
                        new Message(new Content("data"), new Body(new Content("data"))))))
                .thenThrow(ConfigurationSetDoesNotExistException.class);

        // Run the test
        assertThrows(ConfigurationSetDoesNotExistException.class, () -> myClassUnderTest.trySendEmail());
    }

    @Test
    void testTrySendEmail_AmazonSimpleEmailServiceClientThrowsConfigurationSetSendingPausedException() {
        // Setup
        when(mockAmazonSimpleEmailServiceClient.sendEmail(
                new SendEmailRequest("source", new Destination(Arrays.asList("value")),
                        new Message(new Content("data"), new Body(new Content("data"))))))
                .thenThrow(ConfigurationSetSendingPausedException.class);

        // Run the test
        assertThrows(ConfigurationSetSendingPausedException.class, () -> myClassUnderTest.trySendEmail());
    }

    @Test
    void testTrySendEmail_AmazonSimpleEmailServiceClientThrowsAccountSendingPausedException() {
        // Setup
        when(mockAmazonSimpleEmailServiceClient.sendEmail(
                new SendEmailRequest("source", new Destination(Arrays.asList("value")),
                        new Message(new Content("data"), new Body(new Content("data"))))))
                .thenThrow(AccountSendingPausedException.class);

        // Run the test
        assertThrows(AccountSendingPausedException.class, () -> myClassUnderTest.trySendEmail());
    }

    @Test
    void testTrySendRawEmail() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.sendRawEmail(...).
        final SendRawEmailResult sendRawEmailResult = new SendRawEmailResult();
        when(mockAmazonSimpleEmailServiceClient.sendRawEmail(
                new SendRawEmailRequest(new RawMessage(ByteBuffer.wrap("content".getBytes())))))
                .thenReturn(sendRawEmailResult);

        // Run the test
        myClassUnderTest.trySendRawEmail();

        // Verify the results
    }

    @Test
    void testTrySendRawEmail_AmazonSimpleEmailServiceClientThrowsMessageRejectedException() {
        // Setup
        when(mockAmazonSimpleEmailServiceClient.sendRawEmail(
                new SendRawEmailRequest(new RawMessage(ByteBuffer.wrap("content".getBytes())))))
                .thenThrow(MessageRejectedException.class);

        // Run the test
        assertThrows(MessageRejectedException.class, () -> myClassUnderTest.trySendRawEmail());
    }

    @Test
    void testTrySendRawEmail_AmazonSimpleEmailServiceClientThrowsMailFromDomainNotVerifiedException() {
        // Setup
        when(mockAmazonSimpleEmailServiceClient.sendRawEmail(
                new SendRawEmailRequest(new RawMessage(ByteBuffer.wrap("content".getBytes())))))
                .thenThrow(MailFromDomainNotVerifiedException.class);

        // Run the test
        assertThrows(MailFromDomainNotVerifiedException.class, () -> myClassUnderTest.trySendRawEmail());
    }

    @Test
    void testTrySendRawEmail_AmazonSimpleEmailServiceClientThrowsConfigurationSetDoesNotExistException() {
        // Setup
        when(mockAmazonSimpleEmailServiceClient.sendRawEmail(
                new SendRawEmailRequest(new RawMessage(ByteBuffer.wrap("content".getBytes())))))
                .thenThrow(ConfigurationSetDoesNotExistException.class);

        // Run the test
        assertThrows(ConfigurationSetDoesNotExistException.class, () -> myClassUnderTest.trySendRawEmail());
    }

    @Test
    void testTrySendRawEmail_AmazonSimpleEmailServiceClientThrowsConfigurationSetSendingPausedException() {
        // Setup
        when(mockAmazonSimpleEmailServiceClient.sendRawEmail(
                new SendRawEmailRequest(new RawMessage(ByteBuffer.wrap("content".getBytes())))))
                .thenThrow(ConfigurationSetSendingPausedException.class);

        // Run the test
        assertThrows(ConfigurationSetSendingPausedException.class, () -> myClassUnderTest.trySendRawEmail());
    }

    @Test
    void testTrySendRawEmail_AmazonSimpleEmailServiceClientThrowsAccountSendingPausedException() {
        // Setup
        when(mockAmazonSimpleEmailServiceClient.sendRawEmail(
                new SendRawEmailRequest(new RawMessage(ByteBuffer.wrap("content".getBytes())))))
                .thenThrow(AccountSendingPausedException.class);

        // Run the test
        assertThrows(AccountSendingPausedException.class, () -> myClassUnderTest.trySendRawEmail());
    }

    @Test
    void testTrySendTemplatedEmail() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.sendTemplatedEmail(...).
        final SendTemplatedEmailResult sendTemplatedEmailResult = new SendTemplatedEmailResult();
        final SendTemplatedEmailRequest request = new SendTemplatedEmailRequest();
        request.setSource("source");
        request.setDestination(new Destination(Arrays.asList("value")));
        request.setReplyToAddresses(Arrays.asList("value"));
        request.setReturnPath("returnPath");
        request.setSourceArn("sourceArn");
        request.setReturnPathArn("returnPathArn");
        final MessageTag messageTag = new MessageTag();
        request.setTags(Arrays.asList(messageTag));
        request.setConfigurationSetName("configurationSetName");
        when(mockAmazonSimpleEmailServiceClient.sendTemplatedEmail(request)).thenReturn(sendTemplatedEmailResult);

        // Run the test
        myClassUnderTest.trySendTemplatedEmail();

        // Verify the results
    }

    @Test
    void testTrySendTemplatedEmail_AmazonSimpleEmailServiceClientThrowsMessageRejectedException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.sendTemplatedEmail(...).
        final SendTemplatedEmailRequest request = new SendTemplatedEmailRequest();
        request.setSource("source");
        request.setDestination(new Destination(Arrays.asList("value")));
        request.setReplyToAddresses(Arrays.asList("value"));
        request.setReturnPath("returnPath");
        request.setSourceArn("sourceArn");
        request.setReturnPathArn("returnPathArn");
        final MessageTag messageTag = new MessageTag();
        request.setTags(Arrays.asList(messageTag));
        request.setConfigurationSetName("configurationSetName");
        when(mockAmazonSimpleEmailServiceClient.sendTemplatedEmail(request)).thenThrow(MessageRejectedException.class);

        // Run the test
        assertThrows(MessageRejectedException.class, () -> myClassUnderTest.trySendTemplatedEmail());
    }

    @Test
    void testTrySendTemplatedEmail_AmazonSimpleEmailServiceClientThrowsMailFromDomainNotVerifiedException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.sendTemplatedEmail(...).
        final SendTemplatedEmailRequest request = new SendTemplatedEmailRequest();
        request.setSource("source");
        request.setDestination(new Destination(Arrays.asList("value")));
        request.setReplyToAddresses(Arrays.asList("value"));
        request.setReturnPath("returnPath");
        request.setSourceArn("sourceArn");
        request.setReturnPathArn("returnPathArn");
        final MessageTag messageTag = new MessageTag();
        request.setTags(Arrays.asList(messageTag));
        request.setConfigurationSetName("configurationSetName");
        when(mockAmazonSimpleEmailServiceClient.sendTemplatedEmail(request))
                .thenThrow(MailFromDomainNotVerifiedException.class);

        // Run the test
        assertThrows(MailFromDomainNotVerifiedException.class, () -> myClassUnderTest.trySendTemplatedEmail());
    }

    @Test
    void testTrySendTemplatedEmail_AmazonSimpleEmailServiceClientThrowsConfigurationSetDoesNotExistException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.sendTemplatedEmail(...).
        final SendTemplatedEmailRequest request = new SendTemplatedEmailRequest();
        request.setSource("source");
        request.setDestination(new Destination(Arrays.asList("value")));
        request.setReplyToAddresses(Arrays.asList("value"));
        request.setReturnPath("returnPath");
        request.setSourceArn("sourceArn");
        request.setReturnPathArn("returnPathArn");
        final MessageTag messageTag = new MessageTag();
        request.setTags(Arrays.asList(messageTag));
        request.setConfigurationSetName("configurationSetName");
        when(mockAmazonSimpleEmailServiceClient.sendTemplatedEmail(request))
                .thenThrow(ConfigurationSetDoesNotExistException.class);

        // Run the test
        assertThrows(ConfigurationSetDoesNotExistException.class, () -> myClassUnderTest.trySendTemplatedEmail());
    }

    @Test
    void testTrySendTemplatedEmail_AmazonSimpleEmailServiceClientThrowsTemplateDoesNotExistException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.sendTemplatedEmail(...).
        final SendTemplatedEmailRequest request = new SendTemplatedEmailRequest();
        request.setSource("source");
        request.setDestination(new Destination(Arrays.asList("value")));
        request.setReplyToAddresses(Arrays.asList("value"));
        request.setReturnPath("returnPath");
        request.setSourceArn("sourceArn");
        request.setReturnPathArn("returnPathArn");
        final MessageTag messageTag = new MessageTag();
        request.setTags(Arrays.asList(messageTag));
        request.setConfigurationSetName("configurationSetName");
        when(mockAmazonSimpleEmailServiceClient.sendTemplatedEmail(request))
                .thenThrow(TemplateDoesNotExistException.class);

        // Run the test
        assertThrows(TemplateDoesNotExistException.class, () -> myClassUnderTest.trySendTemplatedEmail());
    }

    @Test
    void testTrySendTemplatedEmail_AmazonSimpleEmailServiceClientThrowsConfigurationSetSendingPausedException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.sendTemplatedEmail(...).
        final SendTemplatedEmailRequest request = new SendTemplatedEmailRequest();
        request.setSource("source");
        request.setDestination(new Destination(Arrays.asList("value")));
        request.setReplyToAddresses(Arrays.asList("value"));
        request.setReturnPath("returnPath");
        request.setSourceArn("sourceArn");
        request.setReturnPathArn("returnPathArn");
        final MessageTag messageTag = new MessageTag();
        request.setTags(Arrays.asList(messageTag));
        request.setConfigurationSetName("configurationSetName");
        when(mockAmazonSimpleEmailServiceClient.sendTemplatedEmail(request))
                .thenThrow(ConfigurationSetSendingPausedException.class);

        // Run the test
        assertThrows(ConfigurationSetSendingPausedException.class, () -> myClassUnderTest.trySendTemplatedEmail());
    }

    @Test
    void testTrySendTemplatedEmail_AmazonSimpleEmailServiceClientThrowsAccountSendingPausedException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.sendTemplatedEmail(...).
        final SendTemplatedEmailRequest request = new SendTemplatedEmailRequest();
        request.setSource("source");
        request.setDestination(new Destination(Arrays.asList("value")));
        request.setReplyToAddresses(Arrays.asList("value"));
        request.setReturnPath("returnPath");
        request.setSourceArn("sourceArn");
        request.setReturnPathArn("returnPathArn");
        final MessageTag messageTag = new MessageTag();
        request.setTags(Arrays.asList(messageTag));
        request.setConfigurationSetName("configurationSetName");
        when(mockAmazonSimpleEmailServiceClient.sendTemplatedEmail(request))
                .thenThrow(AccountSendingPausedException.class);

        // Run the test
        assertThrows(AccountSendingPausedException.class, () -> myClassUnderTest.trySendTemplatedEmail());
    }

    @Test
    void testTrySetActiveReceiptRuleSet() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.setActiveReceiptRuleSet(...).
        final SetActiveReceiptRuleSetRequest request = new SetActiveReceiptRuleSetRequest();
        request.setRuleSetName("ruleSetName");
        when(mockAmazonSimpleEmailServiceClient.setActiveReceiptRuleSet(request))
                .thenReturn(new SetActiveReceiptRuleSetResult());

        // Run the test
        myClassUnderTest.trySetActiveReceiptRuleSet();

        // Verify the results
    }

    @Test
    void testTrySetActiveReceiptRuleSet_AmazonSimpleEmailServiceClientThrowsRuleSetDoesNotExistException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.setActiveReceiptRuleSet(...).
        final SetActiveReceiptRuleSetRequest request = new SetActiveReceiptRuleSetRequest();
        request.setRuleSetName("ruleSetName");
        when(mockAmazonSimpleEmailServiceClient.setActiveReceiptRuleSet(request))
                .thenThrow(RuleSetDoesNotExistException.class);

        // Run the test
        assertThrows(RuleSetDoesNotExistException.class, () -> myClassUnderTest.trySetActiveReceiptRuleSet());
    }

    @Test
    void testTrySetIdentityDkimEnabled() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.setIdentityDkimEnabled(...).
        final SetIdentityDkimEnabledRequest request = new SetIdentityDkimEnabledRequest();
        when(mockAmazonSimpleEmailServiceClient.setIdentityDkimEnabled(request))
                .thenReturn(new SetIdentityDkimEnabledResult());

        // Run the test
        myClassUnderTest.trySetIdentityDkimEnabled();

        // Verify the results
    }

    @Test
    void testTrySetIdentityFeedbackForwardingEnabled() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.setIdentityFeedbackForwardingEnabled(...).
        final SetIdentityFeedbackForwardingEnabledResult setIdentityFeedbackForwardingEnabledResult = new SetIdentityFeedbackForwardingEnabledResult();
        final SetIdentityFeedbackForwardingEnabledRequest request = new SetIdentityFeedbackForwardingEnabledRequest();
        when(mockAmazonSimpleEmailServiceClient.setIdentityFeedbackForwardingEnabled(request))
                .thenReturn(setIdentityFeedbackForwardingEnabledResult);

        // Run the test
        myClassUnderTest.trySetIdentityFeedbackForwardingEnabled();

        // Verify the results
    }

    @Test
    void testTrySetIdentityHeadersInNotificationsEnabled() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.setIdentityHeadersInNotificationsEnabled(...).
        final SetIdentityHeadersInNotificationsEnabledResult setIdentityHeadersInNotificationsEnabledResult = new SetIdentityHeadersInNotificationsEnabledResult();
        final SetIdentityHeadersInNotificationsEnabledRequest request = new SetIdentityHeadersInNotificationsEnabledRequest();
        when(mockAmazonSimpleEmailServiceClient.setIdentityHeadersInNotificationsEnabled(request))
                .thenReturn(setIdentityHeadersInNotificationsEnabledResult);

        // Run the test
        myClassUnderTest.trySetIdentityHeadersInNotificationsEnabled();

        // Verify the results
    }

    @Test
    void testTrySetIdentityMailFromDomain() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.setIdentityMailFromDomain(...).
        final SetIdentityMailFromDomainRequest request = new SetIdentityMailFromDomainRequest();
        when(mockAmazonSimpleEmailServiceClient.setIdentityMailFromDomain(request))
                .thenReturn(new SetIdentityMailFromDomainResult());

        // Run the test
        myClassUnderTest.trySetIdentityMailFromDomain();

        // Verify the results
    }

    @Test
    void testTrySetIdentityNotificationTopic() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.setIdentityNotificationTopic(...).
        final SetIdentityNotificationTopicRequest request = new SetIdentityNotificationTopicRequest();
        when(mockAmazonSimpleEmailServiceClient.setIdentityNotificationTopic(request))
                .thenReturn(new SetIdentityNotificationTopicResult());

        // Run the test
        myClassUnderTest.trySetIdentityNotificationTopic();

        // Verify the results
    }

    @Test
    void testTrySetReceiptRulePosition() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.setReceiptRulePosition(...).
        final SetReceiptRulePositionRequest request = new SetReceiptRulePositionRequest();
        request.setRuleSetName("ruleSetName");
        request.setRuleName("ruleName");
        request.setAfter("after");
        when(mockAmazonSimpleEmailServiceClient.setReceiptRulePosition(request))
                .thenReturn(new SetReceiptRulePositionResult());

        // Run the test
        myClassUnderTest.trySetReceiptRulePosition();

        // Verify the results
    }

    @Test
    void testTrySetReceiptRulePosition_AmazonSimpleEmailServiceClientThrowsRuleSetDoesNotExistException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.setReceiptRulePosition(...).
        final SetReceiptRulePositionRequest request = new SetReceiptRulePositionRequest();
        request.setRuleSetName("ruleSetName");
        request.setRuleName("ruleName");
        request.setAfter("after");
        when(mockAmazonSimpleEmailServiceClient.setReceiptRulePosition(request))
                .thenThrow(RuleSetDoesNotExistException.class);

        // Run the test
        assertThrows(RuleSetDoesNotExistException.class, () -> myClassUnderTest.trySetReceiptRulePosition());
    }

    @Test
    void testTrySetReceiptRulePosition_AmazonSimpleEmailServiceClientThrowsRuleDoesNotExistException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.setReceiptRulePosition(...).
        final SetReceiptRulePositionRequest request = new SetReceiptRulePositionRequest();
        request.setRuleSetName("ruleSetName");
        request.setRuleName("ruleName");
        request.setAfter("after");
        when(mockAmazonSimpleEmailServiceClient.setReceiptRulePosition(request))
                .thenThrow(RuleDoesNotExistException.class);

        // Run the test
        assertThrows(RuleDoesNotExistException.class, () -> myClassUnderTest.trySetReceiptRulePosition());
    }

    @Test
    void testTryTestRenderTemplate() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.testRenderTemplate(...).
        final TestRenderTemplateResult testRenderTemplateResult = new TestRenderTemplateResult();
        final TestRenderTemplateRequest request = new TestRenderTemplateRequest();
        request.setTemplateName("templateName");
        request.setTemplateData("templateData");
        when(mockAmazonSimpleEmailServiceClient.testRenderTemplate(request)).thenReturn(testRenderTemplateResult);

        // Run the test
        myClassUnderTest.tryTestRenderTemplate();

        // Verify the results
    }

    @Test
    void testTryTestRenderTemplate_AmazonSimpleEmailServiceClientThrowsTemplateDoesNotExistException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.testRenderTemplate(...).
        final TestRenderTemplateRequest request = new TestRenderTemplateRequest();
        request.setTemplateName("templateName");
        request.setTemplateData("templateData");
        when(mockAmazonSimpleEmailServiceClient.testRenderTemplate(request))
                .thenThrow(TemplateDoesNotExistException.class);

        // Run the test
        assertThrows(TemplateDoesNotExistException.class, () -> myClassUnderTest.tryTestRenderTemplate());
    }

    @Test
    void testTryTestRenderTemplate_AmazonSimpleEmailServiceClientThrowsInvalidRenderingParameterException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.testRenderTemplate(...).
        final TestRenderTemplateRequest request = new TestRenderTemplateRequest();
        request.setTemplateName("templateName");
        request.setTemplateData("templateData");
        when(mockAmazonSimpleEmailServiceClient.testRenderTemplate(request))
                .thenThrow(InvalidRenderingParameterException.class);

        // Run the test
        assertThrows(InvalidRenderingParameterException.class, () -> myClassUnderTest.tryTestRenderTemplate());
    }

    @Test
    void testTryTestRenderTemplate_AmazonSimpleEmailServiceClientThrowsMissingRenderingAttributeException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.testRenderTemplate(...).
        final TestRenderTemplateRequest request = new TestRenderTemplateRequest();
        request.setTemplateName("templateName");
        request.setTemplateData("templateData");
        when(mockAmazonSimpleEmailServiceClient.testRenderTemplate(request))
                .thenThrow(MissingRenderingAttributeException.class);

        // Run the test
        assertThrows(MissingRenderingAttributeException.class, () -> myClassUnderTest.tryTestRenderTemplate());
    }

    @Test
    void testTryUpdateAccountSendingEnabled() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.updateAccountSendingEnabled(...).
        final UpdateAccountSendingEnabledRequest request = new UpdateAccountSendingEnabledRequest();
        when(mockAmazonSimpleEmailServiceClient.updateAccountSendingEnabled(request))
                .thenReturn(new UpdateAccountSendingEnabledResult());

        // Run the test
        myClassUnderTest.tryUpdateAccountSendingEnabled();

        // Verify the results
    }

    @Test
    void testTryUpdateConfigurationSetEventDestination() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.updateConfigurationSetEventDestination(...).
        final UpdateConfigurationSetEventDestinationResult updateConfigurationSetEventDestinationResult = new UpdateConfigurationSetEventDestinationResult();
        final UpdateConfigurationSetEventDestinationRequest request = new UpdateConfigurationSetEventDestinationRequest();
        request.setConfigurationSetName("configurationSetName");
        final EventDestination eventDestination = new EventDestination();
        request.setEventDestination(eventDestination);
        when(mockAmazonSimpleEmailServiceClient.updateConfigurationSetEventDestination(request))
                .thenReturn(updateConfigurationSetEventDestinationResult);

        // Run the test
        myClassUnderTest.tryUpdateConfigurationSetEventDestination();

        // Verify the results
    }

    @Test
    void testTryUpdateConfigurationSetEventDestination_AmazonSimpleEmailServiceClientThrowsConfigurationSetDoesNotExistException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.updateConfigurationSetEventDestination(...).
        final UpdateConfigurationSetEventDestinationRequest request = new UpdateConfigurationSetEventDestinationRequest();
        request.setConfigurationSetName("configurationSetName");
        final EventDestination eventDestination = new EventDestination();
        request.setEventDestination(eventDestination);
        when(mockAmazonSimpleEmailServiceClient.updateConfigurationSetEventDestination(request))
                .thenThrow(ConfigurationSetDoesNotExistException.class);

        // Run the test
        assertThrows(ConfigurationSetDoesNotExistException.class,
                () -> myClassUnderTest.tryUpdateConfigurationSetEventDestination());
    }

    @Test
    void testTryUpdateConfigurationSetEventDestination_AmazonSimpleEmailServiceClientThrowsEventDestinationDoesNotExistException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.updateConfigurationSetEventDestination(...).
        final UpdateConfigurationSetEventDestinationRequest request = new UpdateConfigurationSetEventDestinationRequest();
        request.setConfigurationSetName("configurationSetName");
        final EventDestination eventDestination = new EventDestination();
        request.setEventDestination(eventDestination);
        when(mockAmazonSimpleEmailServiceClient.updateConfigurationSetEventDestination(request))
                .thenThrow(EventDestinationDoesNotExistException.class);

        // Run the test
        assertThrows(EventDestinationDoesNotExistException.class,
                () -> myClassUnderTest.tryUpdateConfigurationSetEventDestination());
    }

    @Test
    void testTryUpdateConfigurationSetEventDestination_AmazonSimpleEmailServiceClientThrowsInvalidCloudWatchDestinationException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.updateConfigurationSetEventDestination(...).
        final UpdateConfigurationSetEventDestinationRequest request = new UpdateConfigurationSetEventDestinationRequest();
        request.setConfigurationSetName("configurationSetName");
        final EventDestination eventDestination = new EventDestination();
        request.setEventDestination(eventDestination);
        when(mockAmazonSimpleEmailServiceClient.updateConfigurationSetEventDestination(request))
                .thenThrow(InvalidCloudWatchDestinationException.class);

        // Run the test
        assertThrows(InvalidCloudWatchDestinationException.class,
                () -> myClassUnderTest.tryUpdateConfigurationSetEventDestination());
    }

    @Test
    void testTryUpdateConfigurationSetEventDestination_AmazonSimpleEmailServiceClientThrowsInvalidFirehoseDestinationException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.updateConfigurationSetEventDestination(...).
        final UpdateConfigurationSetEventDestinationRequest request = new UpdateConfigurationSetEventDestinationRequest();
        request.setConfigurationSetName("configurationSetName");
        final EventDestination eventDestination = new EventDestination();
        request.setEventDestination(eventDestination);
        when(mockAmazonSimpleEmailServiceClient.updateConfigurationSetEventDestination(request))
                .thenThrow(InvalidFirehoseDestinationException.class);

        // Run the test
        assertThrows(InvalidFirehoseDestinationException.class,
                () -> myClassUnderTest.tryUpdateConfigurationSetEventDestination());
    }

    @Test
    void testTryUpdateConfigurationSetEventDestination_AmazonSimpleEmailServiceClientThrowsInvalidSNSDestinationException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.updateConfigurationSetEventDestination(...).
        final UpdateConfigurationSetEventDestinationRequest request = new UpdateConfigurationSetEventDestinationRequest();
        request.setConfigurationSetName("configurationSetName");
        final EventDestination eventDestination = new EventDestination();
        request.setEventDestination(eventDestination);
        when(mockAmazonSimpleEmailServiceClient.updateConfigurationSetEventDestination(request))
                .thenThrow(InvalidSNSDestinationException.class);

        // Run the test
        assertThrows(InvalidSNSDestinationException.class,
                () -> myClassUnderTest.tryUpdateConfigurationSetEventDestination());
    }

    @Test
    void testTryUpdateConfigurationSetReputationMetricsEnabled() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.updateConfigurationSetReputationMetricsEnabled(...).
        final UpdateConfigurationSetReputationMetricsEnabledResult updateConfigurationSetReputationMetricsEnabledResult = new UpdateConfigurationSetReputationMetricsEnabledResult();
        final UpdateConfigurationSetReputationMetricsEnabledRequest request = new UpdateConfigurationSetReputationMetricsEnabledRequest();
        when(mockAmazonSimpleEmailServiceClient.updateConfigurationSetReputationMetricsEnabled(request))
                .thenReturn(updateConfigurationSetReputationMetricsEnabledResult);

        // Run the test
        myClassUnderTest.tryUpdateConfigurationSetReputationMetricsEnabled();

        // Verify the results
    }

    @Test
    void testTryUpdateConfigurationSetReputationMetricsEnabled_AmazonSimpleEmailServiceClientThrowsConfigurationSetDoesNotExistException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.updateConfigurationSetReputationMetricsEnabled(...).
        final UpdateConfigurationSetReputationMetricsEnabledRequest request = new UpdateConfigurationSetReputationMetricsEnabledRequest();
        when(mockAmazonSimpleEmailServiceClient.updateConfigurationSetReputationMetricsEnabled(request))
                .thenThrow(ConfigurationSetDoesNotExistException.class);

        // Run the test
        assertThrows(ConfigurationSetDoesNotExistException.class,
                () -> myClassUnderTest.tryUpdateConfigurationSetReputationMetricsEnabled());
    }

    @Test
    void testTryUpdateConfigurationSetSendingEnabled() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.updateConfigurationSetSendingEnabled(...).
        final UpdateConfigurationSetSendingEnabledResult updateConfigurationSetSendingEnabledResult = new UpdateConfigurationSetSendingEnabledResult();
        final UpdateConfigurationSetSendingEnabledRequest request = new UpdateConfigurationSetSendingEnabledRequest();
        when(mockAmazonSimpleEmailServiceClient.updateConfigurationSetSendingEnabled(request))
                .thenReturn(updateConfigurationSetSendingEnabledResult);

        // Run the test
        myClassUnderTest.tryUpdateConfigurationSetSendingEnabled();

        // Verify the results
    }

    @Test
    void testTryUpdateConfigurationSetSendingEnabled_AmazonSimpleEmailServiceClientThrowsConfigurationSetDoesNotExistException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.updateConfigurationSetSendingEnabled(...).
        final UpdateConfigurationSetSendingEnabledRequest request = new UpdateConfigurationSetSendingEnabledRequest();
        when(mockAmazonSimpleEmailServiceClient.updateConfigurationSetSendingEnabled(request))
                .thenThrow(ConfigurationSetDoesNotExistException.class);

        // Run the test
        assertThrows(ConfigurationSetDoesNotExistException.class,
                () -> myClassUnderTest.tryUpdateConfigurationSetSendingEnabled());
    }

    @Test
    void testTryUpdateConfigurationSetTrackingOptions() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.updateConfigurationSetTrackingOptions(...).
        final UpdateConfigurationSetTrackingOptionsResult updateConfigurationSetTrackingOptionsResult = new UpdateConfigurationSetTrackingOptionsResult();
        final UpdateConfigurationSetTrackingOptionsRequest request = new UpdateConfigurationSetTrackingOptionsRequest();
        request.setConfigurationSetName("configurationSetName");
        final TrackingOptions trackingOptions = new TrackingOptions();
        request.setTrackingOptions(trackingOptions);
        when(mockAmazonSimpleEmailServiceClient.updateConfigurationSetTrackingOptions(request))
                .thenReturn(updateConfigurationSetTrackingOptionsResult);

        // Run the test
        myClassUnderTest.tryUpdateConfigurationSetTrackingOptions();

        // Verify the results
    }

    @Test
    void testTryUpdateConfigurationSetTrackingOptions_AmazonSimpleEmailServiceClientThrowsConfigurationSetDoesNotExistException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.updateConfigurationSetTrackingOptions(...).
        final UpdateConfigurationSetTrackingOptionsRequest request = new UpdateConfigurationSetTrackingOptionsRequest();
        request.setConfigurationSetName("configurationSetName");
        final TrackingOptions trackingOptions = new TrackingOptions();
        request.setTrackingOptions(trackingOptions);
        when(mockAmazonSimpleEmailServiceClient.updateConfigurationSetTrackingOptions(request))
                .thenThrow(ConfigurationSetDoesNotExistException.class);

        // Run the test
        assertThrows(ConfigurationSetDoesNotExistException.class,
                () -> myClassUnderTest.tryUpdateConfigurationSetTrackingOptions());
    }

    @Test
    void testTryUpdateConfigurationSetTrackingOptions_AmazonSimpleEmailServiceClientThrowsTrackingOptionsDoesNotExistException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.updateConfigurationSetTrackingOptions(...).
        final UpdateConfigurationSetTrackingOptionsRequest request = new UpdateConfigurationSetTrackingOptionsRequest();
        request.setConfigurationSetName("configurationSetName");
        final TrackingOptions trackingOptions = new TrackingOptions();
        request.setTrackingOptions(trackingOptions);
        when(mockAmazonSimpleEmailServiceClient.updateConfigurationSetTrackingOptions(request))
                .thenThrow(TrackingOptionsDoesNotExistException.class);

        // Run the test
        assertThrows(TrackingOptionsDoesNotExistException.class,
                () -> myClassUnderTest.tryUpdateConfigurationSetTrackingOptions());
    }

    @Test
    void testTryUpdateConfigurationSetTrackingOptions_AmazonSimpleEmailServiceClientThrowsInvalidTrackingOptionsException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.updateConfigurationSetTrackingOptions(...).
        final UpdateConfigurationSetTrackingOptionsRequest request = new UpdateConfigurationSetTrackingOptionsRequest();
        request.setConfigurationSetName("configurationSetName");
        final TrackingOptions trackingOptions = new TrackingOptions();
        request.setTrackingOptions(trackingOptions);
        when(mockAmazonSimpleEmailServiceClient.updateConfigurationSetTrackingOptions(request))
                .thenThrow(InvalidTrackingOptionsException.class);

        // Run the test
        assertThrows(InvalidTrackingOptionsException.class,
                () -> myClassUnderTest.tryUpdateConfigurationSetTrackingOptions());
    }

    @Test
    void testTryUpdateCustomVerificationEmailTemplate() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.updateCustomVerificationEmailTemplate(...).
        final UpdateCustomVerificationEmailTemplateResult updateCustomVerificationEmailTemplateResult = new UpdateCustomVerificationEmailTemplateResult();
        final UpdateCustomVerificationEmailTemplateRequest request = new UpdateCustomVerificationEmailTemplateRequest();
        request.setTemplateName("templateName");
        request.setFromEmailAddress("fromEmailAddress");
        request.setTemplateSubject("templateSubject");
        request.setTemplateContent("templateContent");
        request.setSuccessRedirectionURL("successRedirectionURL");
        request.setFailureRedirectionURL("failureRedirectionURL");
        when(mockAmazonSimpleEmailServiceClient.updateCustomVerificationEmailTemplate(request))
                .thenReturn(updateCustomVerificationEmailTemplateResult);

        // Run the test
        myClassUnderTest.tryUpdateCustomVerificationEmailTemplate();

        // Verify the results
    }

    @Test
    void testTryUpdateCustomVerificationEmailTemplate_AmazonSimpleEmailServiceClientThrowsCustomVerificationEmailTemplateDoesNotExistException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.updateCustomVerificationEmailTemplate(...).
        final UpdateCustomVerificationEmailTemplateRequest request = new UpdateCustomVerificationEmailTemplateRequest();
        request.setTemplateName("templateName");
        request.setFromEmailAddress("fromEmailAddress");
        request.setTemplateSubject("templateSubject");
        request.setTemplateContent("templateContent");
        request.setSuccessRedirectionURL("successRedirectionURL");
        request.setFailureRedirectionURL("failureRedirectionURL");
        when(mockAmazonSimpleEmailServiceClient.updateCustomVerificationEmailTemplate(request))
                .thenThrow(CustomVerificationEmailTemplateDoesNotExistException.class);

        // Run the test
        assertThrows(CustomVerificationEmailTemplateDoesNotExistException.class,
                () -> myClassUnderTest.tryUpdateCustomVerificationEmailTemplate());
    }

    @Test
    void testTryUpdateCustomVerificationEmailTemplate_AmazonSimpleEmailServiceClientThrowsFromEmailAddressNotVerifiedException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.updateCustomVerificationEmailTemplate(...).
        final UpdateCustomVerificationEmailTemplateRequest request = new UpdateCustomVerificationEmailTemplateRequest();
        request.setTemplateName("templateName");
        request.setFromEmailAddress("fromEmailAddress");
        request.setTemplateSubject("templateSubject");
        request.setTemplateContent("templateContent");
        request.setSuccessRedirectionURL("successRedirectionURL");
        request.setFailureRedirectionURL("failureRedirectionURL");
        when(mockAmazonSimpleEmailServiceClient.updateCustomVerificationEmailTemplate(request))
                .thenThrow(FromEmailAddressNotVerifiedException.class);

        // Run the test
        assertThrows(FromEmailAddressNotVerifiedException.class,
                () -> myClassUnderTest.tryUpdateCustomVerificationEmailTemplate());
    }

    @Test
    void testTryUpdateCustomVerificationEmailTemplate_AmazonSimpleEmailServiceClientThrowsCustomVerificationEmailInvalidContentException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.updateCustomVerificationEmailTemplate(...).
        final UpdateCustomVerificationEmailTemplateRequest request = new UpdateCustomVerificationEmailTemplateRequest();
        request.setTemplateName("templateName");
        request.setFromEmailAddress("fromEmailAddress");
        request.setTemplateSubject("templateSubject");
        request.setTemplateContent("templateContent");
        request.setSuccessRedirectionURL("successRedirectionURL");
        request.setFailureRedirectionURL("failureRedirectionURL");
        when(mockAmazonSimpleEmailServiceClient.updateCustomVerificationEmailTemplate(request))
                .thenThrow(CustomVerificationEmailInvalidContentException.class);

        // Run the test
        assertThrows(CustomVerificationEmailInvalidContentException.class,
                () -> myClassUnderTest.tryUpdateCustomVerificationEmailTemplate());
    }

    @Test
    void testTryUpdateReceiptRule() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.updateReceiptRule(...).
        final UpdateReceiptRuleRequest request = new UpdateReceiptRuleRequest();
        request.setRuleSetName("ruleSetName");
        final ReceiptRule rule = new ReceiptRule();
        request.setRule(rule);
        when(mockAmazonSimpleEmailServiceClient.updateReceiptRule(request)).thenReturn(new UpdateReceiptRuleResult());

        // Run the test
        myClassUnderTest.tryUpdateReceiptRule();

        // Verify the results
    }

    @Test
    void testTryUpdateReceiptRule_AmazonSimpleEmailServiceClientThrowsInvalidSnsTopicException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.updateReceiptRule(...).
        final UpdateReceiptRuleRequest request = new UpdateReceiptRuleRequest();
        request.setRuleSetName("ruleSetName");
        final ReceiptRule rule = new ReceiptRule();
        request.setRule(rule);
        when(mockAmazonSimpleEmailServiceClient.updateReceiptRule(request)).thenThrow(InvalidSnsTopicException.class);

        // Run the test
        assertThrows(InvalidSnsTopicException.class, () -> myClassUnderTest.tryUpdateReceiptRule());
    }

    @Test
    void testTryUpdateReceiptRule_AmazonSimpleEmailServiceClientThrowsInvalidS3ConfigurationException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.updateReceiptRule(...).
        final UpdateReceiptRuleRequest request = new UpdateReceiptRuleRequest();
        request.setRuleSetName("ruleSetName");
        final ReceiptRule rule = new ReceiptRule();
        request.setRule(rule);
        when(mockAmazonSimpleEmailServiceClient.updateReceiptRule(request))
                .thenThrow(InvalidS3ConfigurationException.class);

        // Run the test
        assertThrows(InvalidS3ConfigurationException.class, () -> myClassUnderTest.tryUpdateReceiptRule());
    }

    @Test
    void testTryUpdateReceiptRule_AmazonSimpleEmailServiceClientThrowsInvalidLambdaFunctionException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.updateReceiptRule(...).
        final UpdateReceiptRuleRequest request = new UpdateReceiptRuleRequest();
        request.setRuleSetName("ruleSetName");
        final ReceiptRule rule = new ReceiptRule();
        request.setRule(rule);
        when(mockAmazonSimpleEmailServiceClient.updateReceiptRule(request))
                .thenThrow(InvalidLambdaFunctionException.class);

        // Run the test
        assertThrows(InvalidLambdaFunctionException.class, () -> myClassUnderTest.tryUpdateReceiptRule());
    }

    @Test
    void testTryUpdateReceiptRule_AmazonSimpleEmailServiceClientThrowsRuleSetDoesNotExistException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.updateReceiptRule(...).
        final UpdateReceiptRuleRequest request = new UpdateReceiptRuleRequest();
        request.setRuleSetName("ruleSetName");
        final ReceiptRule rule = new ReceiptRule();
        request.setRule(rule);
        when(mockAmazonSimpleEmailServiceClient.updateReceiptRule(request))
                .thenThrow(RuleSetDoesNotExistException.class);

        // Run the test
        assertThrows(RuleSetDoesNotExistException.class, () -> myClassUnderTest.tryUpdateReceiptRule());
    }

    @Test
    void testTryUpdateReceiptRule_AmazonSimpleEmailServiceClientThrowsRuleDoesNotExistException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.updateReceiptRule(...).
        final UpdateReceiptRuleRequest request = new UpdateReceiptRuleRequest();
        request.setRuleSetName("ruleSetName");
        final ReceiptRule rule = new ReceiptRule();
        request.setRule(rule);
        when(mockAmazonSimpleEmailServiceClient.updateReceiptRule(request)).thenThrow(RuleDoesNotExistException.class);

        // Run the test
        assertThrows(RuleDoesNotExistException.class, () -> myClassUnderTest.tryUpdateReceiptRule());
    }

    @Test
    void testTryUpdateReceiptRule_AmazonSimpleEmailServiceClientThrowsLimitExceededException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.updateReceiptRule(...).
        final UpdateReceiptRuleRequest request = new UpdateReceiptRuleRequest();
        request.setRuleSetName("ruleSetName");
        final ReceiptRule rule = new ReceiptRule();
        request.setRule(rule);
        when(mockAmazonSimpleEmailServiceClient.updateReceiptRule(request)).thenThrow(LimitExceededException.class);

        // Run the test
        assertThrows(LimitExceededException.class, () -> myClassUnderTest.tryUpdateReceiptRule());
    }

    @Test
    void testTryUpdateTemplate() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.updateTemplate(...).
        final UpdateTemplateRequest request = new UpdateTemplateRequest();
        final Template template = new Template();
        request.setTemplate(template);
        when(mockAmazonSimpleEmailServiceClient.updateTemplate(request)).thenReturn(new UpdateTemplateResult());

        // Run the test
        myClassUnderTest.tryUpdateTemplate();

        // Verify the results
    }

    @Test
    void testTryUpdateTemplate_AmazonSimpleEmailServiceClientThrowsTemplateDoesNotExistException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.updateTemplate(...).
        final UpdateTemplateRequest request = new UpdateTemplateRequest();
        final Template template = new Template();
        request.setTemplate(template);
        when(mockAmazonSimpleEmailServiceClient.updateTemplate(request)).thenThrow(TemplateDoesNotExistException.class);

        // Run the test
        assertThrows(TemplateDoesNotExistException.class, () -> myClassUnderTest.tryUpdateTemplate());
    }

    @Test
    void testTryUpdateTemplate_AmazonSimpleEmailServiceClientThrowsInvalidTemplateException() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.updateTemplate(...).
        final UpdateTemplateRequest request = new UpdateTemplateRequest();
        final Template template = new Template();
        request.setTemplate(template);
        when(mockAmazonSimpleEmailServiceClient.updateTemplate(request)).thenThrow(InvalidTemplateException.class);

        // Run the test
        assertThrows(InvalidTemplateException.class, () -> myClassUnderTest.tryUpdateTemplate());
    }

    @Test
    void testTryVerifyDomainDkim() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.verifyDomainDkim(...).
        final VerifyDomainDkimResult verifyDomainDkimResult = new VerifyDomainDkimResult();
        final VerifyDomainDkimRequest request = new VerifyDomainDkimRequest();
        request.setDomain("domain");
        when(mockAmazonSimpleEmailServiceClient.verifyDomainDkim(request)).thenReturn(verifyDomainDkimResult);

        // Run the test
        myClassUnderTest.tryVerifyDomainDkim();

        // Verify the results
    }

    @Test
    void testTryVerifyDomainIdentity() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.verifyDomainIdentity(...).
        final VerifyDomainIdentityResult verifyDomainIdentityResult = new VerifyDomainIdentityResult();
        final VerifyDomainIdentityRequest request = new VerifyDomainIdentityRequest();
        request.setDomain("domain");
        when(mockAmazonSimpleEmailServiceClient.verifyDomainIdentity(request)).thenReturn(verifyDomainIdentityResult);

        // Run the test
        myClassUnderTest.tryVerifyDomainIdentity();

        // Verify the results
    }

    @Test
    void testTryVerifyEmailAddress() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.verifyEmailAddress(...).
        final VerifyEmailAddressRequest request = new VerifyEmailAddressRequest();
        request.setEmailAddress("emailAddress");
        when(mockAmazonSimpleEmailServiceClient.verifyEmailAddress(request)).thenReturn(new VerifyEmailAddressResult());

        // Run the test
        myClassUnderTest.tryVerifyEmailAddress();

        // Verify the results
    }

    @Test
    void testTryVerifyEmailIdentity() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.verifyEmailIdentity(...).
        final VerifyEmailIdentityRequest request = new VerifyEmailIdentityRequest();
        request.setEmailAddress("emailAddress");
        when(mockAmazonSimpleEmailServiceClient.verifyEmailIdentity(request))
                .thenReturn(new VerifyEmailIdentityResult());

        // Run the test
        myClassUnderTest.tryVerifyEmailIdentity();

        // Verify the results
    }

    @Test
    void testTryGetCachedResponseMetadata() {
        // Setup
        when(mockAmazonSimpleEmailServiceClient.getCachedResponseMetadata(
                any(AmazonWebServiceRequest.class))).thenReturn(new ResponseMetadata(new HashMap<>()));

        // Run the test
        myClassUnderTest.tryGetCachedResponseMetadata();

        // Verify the results
    }

    @Test
    void testTryGetCachedResponseMetadata_AmazonSimpleEmailServiceClientReturnsNull() {
        // Setup
        when(mockAmazonSimpleEmailServiceClient.getCachedResponseMetadata(
                any(AmazonWebServiceRequest.class))).thenReturn(null);

        // Run the test
        myClassUnderTest.tryGetCachedResponseMetadata();

        // Verify the results
    }

    @Test
    void testTryWaiters() {
        // Setup
        // Configure AmazonSimpleEmailServiceClient.waiters(...).
        final AmazonSimpleEmailServiceWaiters amazonSimpleEmailServiceWaiters = new AmazonSimpleEmailServiceWaiters(
                null);
        when(mockAmazonSimpleEmailServiceClient.waiters()).thenReturn(amazonSimpleEmailServiceWaiters);

        // Run the test
        myClassUnderTest.tryWaiters();

        // Verify the results
    }

    @Test
    void testTryShutdown() {
        // Setup
        // Run the test
        myClassUnderTest.tryShutdown();

        // Verify the results
        verify(mockAmazonSimpleEmailServiceClient).shutdown();
    }

    @Test
    void testTryBuilder() {
        // Setup
        // Run the test
        myClassUnderTest.tryBuilder();

        // Verify the results
    }
}
