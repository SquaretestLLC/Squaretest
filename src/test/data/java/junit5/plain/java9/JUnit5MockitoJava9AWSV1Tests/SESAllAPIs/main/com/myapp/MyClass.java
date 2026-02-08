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
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.*;
import com.amazonaws.services.simpleemail.waiters.AmazonSimpleEmailServiceWaiters;

import java.util.Arrays;

public class MyClass {

    private AmazonSimpleEmailServiceClient amazonSimpleEmailServiceClient;

    public MyClass(AmazonSimpleEmailServiceClient amazonSimpleEmailServiceClient) {
        this.amazonSimpleEmailServiceClient = amazonSimpleEmailServiceClient;
    }

    public void tryCloneReceiptRuleSet() {
        final CloneReceiptRuleSetRequest request = new CloneReceiptRuleSetRequest();
        request.setRuleSetName("ruleSetName");
        request.setOriginalRuleSetName("originalRuleSetName");

        final CloneReceiptRuleSetResult result = amazonSimpleEmailServiceClient.cloneReceiptRuleSet(request);
    }

    public void tryCreateConfigurationSet() {
        final CreateConfigurationSetRequest request = new CreateConfigurationSetRequest();
        request.setConfigurationSet(new ConfigurationSet());

        final CreateConfigurationSetResult result = amazonSimpleEmailServiceClient.createConfigurationSet(request);
    }

    public void tryCreateConfigurationSetEventDestination() {
        final CreateConfigurationSetEventDestinationRequest request = new CreateConfigurationSetEventDestinationRequest();
        request.setConfigurationSetName("configurationSetName");
        request.setEventDestination(new EventDestination());

        final CreateConfigurationSetEventDestinationResult result = amazonSimpleEmailServiceClient.createConfigurationSetEventDestination(request);
    }

    public void tryCreateConfigurationSetTrackingOptions() {
        final CreateConfigurationSetTrackingOptionsRequest request = new CreateConfigurationSetTrackingOptionsRequest();
        request.setConfigurationSetName("configurationSetName");
        request.setTrackingOptions(new TrackingOptions());

        final CreateConfigurationSetTrackingOptionsResult result = amazonSimpleEmailServiceClient.createConfigurationSetTrackingOptions(request);
    }

    public void tryCreateCustomVerificationEmailTemplate() {
        final CreateCustomVerificationEmailTemplateRequest request = new CreateCustomVerificationEmailTemplateRequest();
        request.setTemplateName("templateName");
        request.setFromEmailAddress("fromEmailAddress");
        request.setTemplateSubject("templateSubject");
        request.setTemplateContent("templateContent");
        request.setSuccessRedirectionURL("successRedirectionURL");
        request.setFailureRedirectionURL("failureRedirectionURL");

        final CreateCustomVerificationEmailTemplateResult result = amazonSimpleEmailServiceClient.createCustomVerificationEmailTemplate(request);
    }

    public void tryCreateReceiptFilter() {
        final CreateReceiptFilterRequest request = new CreateReceiptFilterRequest();
        request.setFilter(new ReceiptFilter());

        final CreateReceiptFilterResult result = amazonSimpleEmailServiceClient.createReceiptFilter(request);
    }

    public void tryCreateReceiptRule() {
        final CreateReceiptRuleRequest request = new CreateReceiptRuleRequest();
        request.setRuleSetName("ruleSetName");
        request.setAfter("after");
        request.setRule(new ReceiptRule());

        final CreateReceiptRuleResult result = amazonSimpleEmailServiceClient.createReceiptRule(request);
    }

    public void tryCreateReceiptRuleSet() {
        final CreateReceiptRuleSetRequest request = new CreateReceiptRuleSetRequest();
        request.setRuleSetName("ruleSetName");

        final CreateReceiptRuleSetResult result = amazonSimpleEmailServiceClient.createReceiptRuleSet(request);
    }

    public void tryCreateTemplate() {
        final CreateTemplateRequest request = new CreateTemplateRequest();
        request.setTemplate(new Template());

        final CreateTemplateResult result = amazonSimpleEmailServiceClient.createTemplate(request);
    }

    public void tryDeleteConfigurationSet() {
        final DeleteConfigurationSetRequest request = new DeleteConfigurationSetRequest();
        request.setConfigurationSetName("configurationSetName");

        final DeleteConfigurationSetResult result = amazonSimpleEmailServiceClient.deleteConfigurationSet(request);
    }

    public void tryDeleteConfigurationSetEventDestination() {
        final DeleteConfigurationSetEventDestinationRequest request = new DeleteConfigurationSetEventDestinationRequest();
        request.setConfigurationSetName("configurationSetName");
        request.setEventDestinationName("eventDestinationName");

        final DeleteConfigurationSetEventDestinationResult result = amazonSimpleEmailServiceClient.deleteConfigurationSetEventDestination(request);
    }

    public void tryDeleteConfigurationSetTrackingOptions() {
        final DeleteConfigurationSetTrackingOptionsRequest request = new DeleteConfigurationSetTrackingOptionsRequest();
        request.setConfigurationSetName("configurationSetName");

        final DeleteConfigurationSetTrackingOptionsResult result = amazonSimpleEmailServiceClient.deleteConfigurationSetTrackingOptions(request);
    }

    public void tryDeleteCustomVerificationEmailTemplate() {
        final DeleteCustomVerificationEmailTemplateRequest request = new DeleteCustomVerificationEmailTemplateRequest();
        request.setTemplateName("templateName");

        final DeleteCustomVerificationEmailTemplateResult result = amazonSimpleEmailServiceClient.deleteCustomVerificationEmailTemplate(request);
    }

    public void tryDeleteIdentity() {
        final DeleteIdentityRequest request = new DeleteIdentityRequest();
        request.setIdentity("identity");

        final DeleteIdentityResult result = amazonSimpleEmailServiceClient.deleteIdentity(request);
    }

    public void tryDeleteIdentityPolicy() {
        final DeleteIdentityPolicyRequest request = new DeleteIdentityPolicyRequest();
        request.setIdentity("identity");
        request.setPolicyName("policyName");

        final DeleteIdentityPolicyResult result = amazonSimpleEmailServiceClient.deleteIdentityPolicy(request);
    }

    public void tryDeleteReceiptFilter() {
        final DeleteReceiptFilterRequest request = new DeleteReceiptFilterRequest();
        request.setFilterName("filterName");

        final DeleteReceiptFilterResult result = amazonSimpleEmailServiceClient.deleteReceiptFilter(request);
    }

    public void tryDeleteReceiptRule() {
        final DeleteReceiptRuleRequest request = new DeleteReceiptRuleRequest();
        request.setRuleSetName("ruleSetName");
        request.setRuleName("ruleName");

        final DeleteReceiptRuleResult result = amazonSimpleEmailServiceClient.deleteReceiptRule(request);
    }

    public void tryDeleteReceiptRuleSet() {
        final DeleteReceiptRuleSetRequest request = new DeleteReceiptRuleSetRequest();
        request.setRuleSetName("ruleSetName");

        final DeleteReceiptRuleSetResult result = amazonSimpleEmailServiceClient.deleteReceiptRuleSet(request);
    }

    public void tryDeleteTemplate() {
        final DeleteTemplateRequest request = new DeleteTemplateRequest();
        request.setTemplateName("templateName");

        final DeleteTemplateResult result = amazonSimpleEmailServiceClient.deleteTemplate(request);
    }

    public void tryDeleteVerifiedEmailAddress() {
        final DeleteVerifiedEmailAddressRequest request = new DeleteVerifiedEmailAddressRequest();
        request.setEmailAddress("emailAddress");

        final DeleteVerifiedEmailAddressResult result = amazonSimpleEmailServiceClient.deleteVerifiedEmailAddress(request);
    }

    public void tryDescribeActiveReceiptRuleSet() {
        final DescribeActiveReceiptRuleSetRequest request = new DescribeActiveReceiptRuleSetRequest();
        final DescribeActiveReceiptRuleSetResult result = amazonSimpleEmailServiceClient.describeActiveReceiptRuleSet(request);
    }

    public void tryDescribeConfigurationSet() {
        final DescribeConfigurationSetRequest request = new DescribeConfigurationSetRequest();
        request.setConfigurationSetName("configurationSetName");
        request.setConfigurationSetAttributeNames(List.of());

        final DescribeConfigurationSetResult result = amazonSimpleEmailServiceClient.describeConfigurationSet(request);
    }

    public void tryDescribeReceiptRule() {
        final DescribeReceiptRuleRequest request = new DescribeReceiptRuleRequest();
        request.setRuleSetName("ruleSetName");
        request.setRuleName("ruleName");

        final DescribeReceiptRuleResult result = amazonSimpleEmailServiceClient.describeReceiptRule(request);
    }

    public void tryDescribeReceiptRuleSet() {
        final DescribeReceiptRuleSetRequest request = new DescribeReceiptRuleSetRequest();
        request.setRuleSetName("ruleSetName");

        final DescribeReceiptRuleSetResult result = amazonSimpleEmailServiceClient.describeReceiptRuleSet(request);
    }

    public void tryGetAccountSendingEnabled() {
        final GetAccountSendingEnabledRequest request = new GetAccountSendingEnabledRequest();
        final GetAccountSendingEnabledResult result = amazonSimpleEmailServiceClient.getAccountSendingEnabled(request);
    }

    public void tryGetCustomVerificationEmailTemplate() {
        final GetCustomVerificationEmailTemplateRequest request = new GetCustomVerificationEmailTemplateRequest();
        request.setTemplateName("templateName");

        final GetCustomVerificationEmailTemplateResult result = amazonSimpleEmailServiceClient.getCustomVerificationEmailTemplate(request);
    }

    public void tryGetIdentityDkimAttributes() {
        final GetIdentityDkimAttributesRequest request = new GetIdentityDkimAttributesRequest();
        request.setIdentities(List.of());

        final GetIdentityDkimAttributesResult result = amazonSimpleEmailServiceClient.getIdentityDkimAttributes(request);
    }

    public void tryGetIdentityMailFromDomainAttributes() {
        final GetIdentityMailFromDomainAttributesRequest request = new GetIdentityMailFromDomainAttributesRequest();
        request.setIdentities(List.of());

        final GetIdentityMailFromDomainAttributesResult result = amazonSimpleEmailServiceClient.getIdentityMailFromDomainAttributes(request);
    }

    public void tryGetIdentityNotificationAttributes() {
        final GetIdentityNotificationAttributesRequest request = new GetIdentityNotificationAttributesRequest();
        request.setIdentities(List.of());

        final GetIdentityNotificationAttributesResult result = amazonSimpleEmailServiceClient.getIdentityNotificationAttributes(request);
    }

    public void tryGetIdentityPolicies() {
        final GetIdentityPoliciesRequest request = new GetIdentityPoliciesRequest();
        request.setIdentity("identity");
        request.setPolicyNames(List.of());

        final GetIdentityPoliciesResult result = amazonSimpleEmailServiceClient.getIdentityPolicies(request);
    }

    public void tryGetIdentityVerificationAttributes() {
        final GetIdentityVerificationAttributesRequest request = new GetIdentityVerificationAttributesRequest();
        request.setIdentities(List.of());

        final GetIdentityVerificationAttributesResult result = amazonSimpleEmailServiceClient.getIdentityVerificationAttributes(request);
    }

    public void tryGetSendQuota() {
        final GetSendQuotaRequest request = new GetSendQuotaRequest();
        final GetSendQuotaResult result = amazonSimpleEmailServiceClient.getSendQuota(request);
    }

    public void tryGetSendQuota1() {
        final GetSendQuotaResult result = amazonSimpleEmailServiceClient.getSendQuota();
    }

    public void tryGetSendStatistics() {
        final GetSendStatisticsRequest request = new GetSendStatisticsRequest();
        final GetSendStatisticsResult result = amazonSimpleEmailServiceClient.getSendStatistics(request);
    }

    public void tryGetSendStatistics1() {
        final GetSendStatisticsResult result = amazonSimpleEmailServiceClient.getSendStatistics();
    }

    public void tryGetTemplate() {
        final GetTemplateRequest request = new GetTemplateRequest();
        request.setTemplateName("templateName");

        final GetTemplateResult result = amazonSimpleEmailServiceClient.getTemplate(request);
    }

    public void tryListConfigurationSets() {
        final ListConfigurationSetsRequest request = new ListConfigurationSetsRequest();
        request.setNextToken("nextToken");
        request.setMaxItems(0);

        final ListConfigurationSetsResult result = amazonSimpleEmailServiceClient.listConfigurationSets(request);
    }

    public void tryListCustomVerificationEmailTemplates() {
        final ListCustomVerificationEmailTemplatesRequest request = new ListCustomVerificationEmailTemplatesRequest();
        request.setNextToken("nextToken");
        request.setMaxResults(0);

        final ListCustomVerificationEmailTemplatesResult result = amazonSimpleEmailServiceClient.listCustomVerificationEmailTemplates(request);
    }

    public void tryListIdentities() {
        final ListIdentitiesRequest request = new ListIdentitiesRequest();
        final ListIdentitiesResult result = amazonSimpleEmailServiceClient.listIdentities(request);
    }

    public void tryListIdentities1() {
        final ListIdentitiesResult result = amazonSimpleEmailServiceClient.listIdentities();
    }

    public void tryListIdentityPolicies() {
        final ListIdentityPoliciesRequest request = new ListIdentityPoliciesRequest();
        request.setIdentity("identity");

        final ListIdentityPoliciesResult result = amazonSimpleEmailServiceClient.listIdentityPolicies(request);
    }

    public void tryListReceiptFilters() {
        final ListReceiptFiltersRequest request = new ListReceiptFiltersRequest();
        final ListReceiptFiltersResult result = amazonSimpleEmailServiceClient.listReceiptFilters(request);
    }

    public void tryListReceiptRuleSets() {
        final ListReceiptRuleSetsRequest request = new ListReceiptRuleSetsRequest();
        request.setNextToken("nextToken");

        final ListReceiptRuleSetsResult result = amazonSimpleEmailServiceClient.listReceiptRuleSets(request);
    }

    public void tryListTemplates() {
        final ListTemplatesRequest request = new ListTemplatesRequest();
        request.setNextToken("nextToken");
        request.setMaxItems(0);

        final ListTemplatesResult result = amazonSimpleEmailServiceClient.listTemplates(request);
    }

    public void tryListVerifiedEmailAddresses() {
        final ListVerifiedEmailAddressesRequest request = new ListVerifiedEmailAddressesRequest();
        final ListVerifiedEmailAddressesResult result = amazonSimpleEmailServiceClient.listVerifiedEmailAddresses(request);
    }

    public void tryListVerifiedEmailAddresses1() {
        final ListVerifiedEmailAddressesResult result = amazonSimpleEmailServiceClient.listVerifiedEmailAddresses();
    }

    public void tryPutConfigurationSetDeliveryOptions() {
        final PutConfigurationSetDeliveryOptionsRequest request = new PutConfigurationSetDeliveryOptionsRequest();
        request.setConfigurationSetName("configurationSetName");
        request.setDeliveryOptions(new DeliveryOptions());

        final PutConfigurationSetDeliveryOptionsResult result = amazonSimpleEmailServiceClient.putConfigurationSetDeliveryOptions(request);
    }

    public void tryPutIdentityPolicy() {
        final PutIdentityPolicyRequest request = new PutIdentityPolicyRequest();
        request.setIdentity("identity");
        request.setPolicyName("policyName");
        request.setPolicy("policy");

        final PutIdentityPolicyResult result = amazonSimpleEmailServiceClient.putIdentityPolicy(request);
    }

    public void tryReorderReceiptRuleSet() {
        final ReorderReceiptRuleSetRequest request = new ReorderReceiptRuleSetRequest();
        request.setRuleSetName("ruleSetName");
        request.setRuleNames(List.of());

        final ReorderReceiptRuleSetResult result = amazonSimpleEmailServiceClient.reorderReceiptRuleSet(request);
    }

    public void trySendBounce() {
        final SendBounceRequest request = new SendBounceRequest();
        request.setOriginalMessageId("originalMessageId");
        request.setBounceSender("bounceSender");
        request.setExplanation("explanation");
        request.setMessageDsn(new MessageDsn());
        request.setBouncedRecipientInfoList(List.of());
        request.setBounceSenderArn("bounceSenderArn");

        final SendBounceResult result = amazonSimpleEmailServiceClient.sendBounce(request);
    }

    public void trySendBulkTemplatedEmail() {
        final SendBulkTemplatedEmailRequest request = new SendBulkTemplatedEmailRequest();
        request.setSource("source");
        request.setSourceArn("sourceArn");
        request.setReplyToAddresses(List.of());
        request.setReturnPath("returnPath");
        request.setReturnPathArn("returnPathArn");
        request.setConfigurationSetName("configurationSetName");
        request.setDefaultTags(List.of());
        request.setTemplate("template");

        final SendBulkTemplatedEmailResult result = amazonSimpleEmailServiceClient.sendBulkTemplatedEmail(request);
    }

    public void trySendCustomVerificationEmail() {
        final SendCustomVerificationEmailRequest request = new SendCustomVerificationEmailRequest();
        request.setEmailAddress("emailAddress");
        request.setTemplateName("templateName");
        request.setConfigurationSetName("configurationSetName");

        final SendCustomVerificationEmailResult result = amazonSimpleEmailServiceClient.sendCustomVerificationEmail(request);
    }

    public void trySendEmail() {
        final SendEmailRequest request = new SendEmailRequest();
        final SendEmailResult result = amazonSimpleEmailServiceClient.sendEmail(request);
    }

    public void trySendRawEmail() {
        final SendRawEmailRequest request = new SendRawEmailRequest();
        final SendRawEmailResult result = amazonSimpleEmailServiceClient.sendRawEmail(request);
    }

    public void trySendTemplatedEmail() {
        final SendTemplatedEmailRequest request = new SendTemplatedEmailRequest();
        request.setSource("source");
        request.setDestination(new Destination(List.of()));
        request.setReplyToAddresses(List.of());
        request.setReturnPath("returnPath");
        request.setSourceArn("sourceArn");
        request.setReturnPathArn("returnPathArn");
        request.setTags(List.of());
        request.setConfigurationSetName("configurationSetName");

        final SendTemplatedEmailResult result = amazonSimpleEmailServiceClient.sendTemplatedEmail(request);
    }

    public void trySetActiveReceiptRuleSet() {
        final SetActiveReceiptRuleSetRequest request = new SetActiveReceiptRuleSetRequest();
        request.setRuleSetName("ruleSetName");

        final SetActiveReceiptRuleSetResult result = amazonSimpleEmailServiceClient.setActiveReceiptRuleSet(request);
    }

    public void trySetIdentityDkimEnabled() {
        final SetIdentityDkimEnabledRequest request = new SetIdentityDkimEnabledRequest();
        final SetIdentityDkimEnabledResult result = amazonSimpleEmailServiceClient.setIdentityDkimEnabled(request);
    }

    public void trySetIdentityFeedbackForwardingEnabled() {
        final SetIdentityFeedbackForwardingEnabledRequest request = new SetIdentityFeedbackForwardingEnabledRequest();
        final SetIdentityFeedbackForwardingEnabledResult result = amazonSimpleEmailServiceClient.setIdentityFeedbackForwardingEnabled(request);
    }

    public void trySetIdentityHeadersInNotificationsEnabled() {
        final SetIdentityHeadersInNotificationsEnabledRequest request = new SetIdentityHeadersInNotificationsEnabledRequest();
        final SetIdentityHeadersInNotificationsEnabledResult result = amazonSimpleEmailServiceClient.setIdentityHeadersInNotificationsEnabled(request);
    }

    public void trySetIdentityMailFromDomain() {
        final SetIdentityMailFromDomainRequest request = new SetIdentityMailFromDomainRequest();
        final SetIdentityMailFromDomainResult result = amazonSimpleEmailServiceClient.setIdentityMailFromDomain(request);
    }

    public void trySetIdentityNotificationTopic() {
        final SetIdentityNotificationTopicRequest request = new SetIdentityNotificationTopicRequest();
        final SetIdentityNotificationTopicResult result = amazonSimpleEmailServiceClient.setIdentityNotificationTopic(request);
    }

    public void trySetReceiptRulePosition() {
        final SetReceiptRulePositionRequest request = new SetReceiptRulePositionRequest();
        request.setRuleSetName("ruleSetName");
        request.setRuleName("ruleName");
        request.setAfter("after");

        final SetReceiptRulePositionResult result = amazonSimpleEmailServiceClient.setReceiptRulePosition(request);
    }

    public void tryTestRenderTemplate() {
        final TestRenderTemplateRequest request = new TestRenderTemplateRequest();
        request.setTemplateName("templateName");
        request.setTemplateData("templateData");

        final TestRenderTemplateResult result = amazonSimpleEmailServiceClient.testRenderTemplate(request);
    }

    public void tryUpdateAccountSendingEnabled() {
        final UpdateAccountSendingEnabledRequest request = new UpdateAccountSendingEnabledRequest();
        final UpdateAccountSendingEnabledResult result = amazonSimpleEmailServiceClient.updateAccountSendingEnabled(request);
    }

    public void tryUpdateConfigurationSetEventDestination() {
        final UpdateConfigurationSetEventDestinationRequest request = new UpdateConfigurationSetEventDestinationRequest();
        request.setConfigurationSetName("configurationSetName");
        request.setEventDestination(new EventDestination());

        final UpdateConfigurationSetEventDestinationResult result = amazonSimpleEmailServiceClient.updateConfigurationSetEventDestination(request);
    }

    public void tryUpdateConfigurationSetReputationMetricsEnabled() {
        final UpdateConfigurationSetReputationMetricsEnabledRequest request = new UpdateConfigurationSetReputationMetricsEnabledRequest();
        final UpdateConfigurationSetReputationMetricsEnabledResult result = amazonSimpleEmailServiceClient.updateConfigurationSetReputationMetricsEnabled(request);
    }

    public void tryUpdateConfigurationSetSendingEnabled() {
        final UpdateConfigurationSetSendingEnabledRequest request = new UpdateConfigurationSetSendingEnabledRequest();
        final UpdateConfigurationSetSendingEnabledResult result = amazonSimpleEmailServiceClient.updateConfigurationSetSendingEnabled(request);
    }

    public void tryUpdateConfigurationSetTrackingOptions() {
        final UpdateConfigurationSetTrackingOptionsRequest request = new UpdateConfigurationSetTrackingOptionsRequest();
        request.setConfigurationSetName("configurationSetName");
        request.setTrackingOptions(new TrackingOptions());

        final UpdateConfigurationSetTrackingOptionsResult result = amazonSimpleEmailServiceClient.updateConfigurationSetTrackingOptions(request);
    }

    public void tryUpdateCustomVerificationEmailTemplate() {
        final UpdateCustomVerificationEmailTemplateRequest request = new UpdateCustomVerificationEmailTemplateRequest();
        request.setTemplateName("templateName");
        request.setFromEmailAddress("fromEmailAddress");
        request.setTemplateSubject("templateSubject");
        request.setTemplateContent("templateContent");
        request.setSuccessRedirectionURL("successRedirectionURL");
        request.setFailureRedirectionURL("failureRedirectionURL");

        final UpdateCustomVerificationEmailTemplateResult result = amazonSimpleEmailServiceClient.updateCustomVerificationEmailTemplate(request);
    }

    public void tryUpdateReceiptRule() {
        final UpdateReceiptRuleRequest request = new UpdateReceiptRuleRequest();
        request.setRuleSetName("ruleSetName");
        request.setRule(new ReceiptRule());

        final UpdateReceiptRuleResult result = amazonSimpleEmailServiceClient.updateReceiptRule(request);
    }

    public void tryUpdateTemplate() {
        final UpdateTemplateRequest request = new UpdateTemplateRequest();
        request.setTemplate(new Template());

        final UpdateTemplateResult result = amazonSimpleEmailServiceClient.updateTemplate(request);
    }

    public void tryVerifyDomainDkim() {
        final VerifyDomainDkimRequest request = new VerifyDomainDkimRequest();
        request.setDomain("domain");

        final VerifyDomainDkimResult result = amazonSimpleEmailServiceClient.verifyDomainDkim(request);
    }

    public void tryVerifyDomainIdentity() {
        final VerifyDomainIdentityRequest request = new VerifyDomainIdentityRequest();
        request.setDomain("domain");

        final VerifyDomainIdentityResult result = amazonSimpleEmailServiceClient.verifyDomainIdentity(request);
    }

    public void tryVerifyEmailAddress() {
        final VerifyEmailAddressRequest request = new VerifyEmailAddressRequest();
        request.setEmailAddress("emailAddress");

        final VerifyEmailAddressResult result = amazonSimpleEmailServiceClient.verifyEmailAddress(request);
    }

    public void tryVerifyEmailIdentity() {
        final VerifyEmailIdentityRequest request = new VerifyEmailIdentityRequest();
        request.setEmailAddress("emailAddress");

        final VerifyEmailIdentityResult result = amazonSimpleEmailServiceClient.verifyEmailIdentity(request);
    }

    public void tryGetCachedResponseMetadata() {
        final AmazonWebServiceRequest request = null;
        final ResponseMetadata result = amazonSimpleEmailServiceClient.getCachedResponseMetadata(request);
    }

    public void tryWaiters() {
        final AmazonSimpleEmailServiceWaiters result = amazonSimpleEmailServiceClient.waiters();
    }

    public void tryShutdown() {
        amazonSimpleEmailServiceClient.shutdown();
    }

    public void tryBuilder() {
        final AmazonSimpleEmailServiceClientBuilder result = AmazonSimpleEmailServiceClient.builder();
    }
}
