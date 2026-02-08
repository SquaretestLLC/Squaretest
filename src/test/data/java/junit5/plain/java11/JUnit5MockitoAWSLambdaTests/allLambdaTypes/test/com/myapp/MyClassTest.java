package com.myapp;

import com.amazonaws.services.lambda.runtime.events.*;
import com.amazonaws.services.lambda.runtime.events.models.TimeWindow;
import com.amazonaws.services.lambda.runtime.events.models.dynamodb.*;
import com.amazonaws.services.lambda.runtime.events.models.kinesis.EncryptionType;
import com.amazonaws.services.lambda.runtime.events.models.kinesis.Record;
import com.amazonaws.services.lambda.runtime.events.models.s3.S3EventNotification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass();
    }

    @Test
    void testHandleEvent() {
        myClassUnderTest.handleEvent(
                new CognitoUserPoolPostAuthenticationEvent("version", "triggerSource", "region", "userPoolId",
                        "userName", new CognitoUserPoolEvent.CallerContext(),
                        new CognitoUserPoolPostAuthenticationEvent.Request(Map.ofEntries(Map.entry("value", "value")),
                                Map.ofEntries(Map.entry("value", "value")), false)), new LambdaDestinationEvent(),
                new LexEvent(), new S3ObjectLambdaEvent(),
                new KinesisAnalyticsOutputDeliveryEvent("invocationId", "applicationArn",
                        List.of(new KinesisAnalyticsOutputDeliveryEvent.Record("recordId",
                                new KinesisAnalyticsOutputDeliveryEvent.Record.LambdaDeliveryRecordMetadata(0L),
                                ByteBuffer.wrap("content".getBytes())))), new APIGatewayV2ProxyResponseEvent(),
                new CognitoUserPoolVerifyAuthChallengeResponseEvent("version", "triggerSource", "region", "userPoolId",
                        "userName", new CognitoUserPoolEvent.CallerContext(),
                        new CognitoUserPoolVerifyAuthChallengeResponseEvent.Request(
                                Map.ofEntries(Map.entry("value", "value")), Map.ofEntries(Map.entry("value", "value")),
                                Map.ofEntries(Map.entry("value", "value")), Map.ofEntries(Map.entry("value", "value")),
                                false), new CognitoUserPoolVerifyAuthChallengeResponseEvent.Response()),
                new AppSyncLambdaAuthorizerResponse(false, Map.ofEntries(Map.entry("value", "value")), List.of("value"),
                        0), new APIGatewayV2WebSocketEvent(), new APIGatewayProxyRequestEvent(), new S3BatchEvent(),
                new CognitoUserPoolPostConfirmationEvent("version", "triggerSource", "region", "userPoolId", "userName",
                        new CognitoUserPoolEvent.CallerContext(),
                        new CognitoUserPoolPostConfirmationEvent.Request(Map.ofEntries(Map.entry("value", "value")),
                                Map.ofEntries(Map.entry("value", "value")))), new SQSEvent(), new KafkaEvent(), null,
                new DynamodbEvent(), new APIGatewayV2HTTPResponse(), new CloudWatchLogsEvent(),
                new AppSyncLambdaAuthorizerEvent(), new S3Event(
                        List.of(new S3EventNotification.S3EventNotificationRecord("awsRegion", "eventName",
                                "eventSource", "eventTime", "eventVersion",
                                new S3EventNotification.RequestParametersEntity("sourceIPAddress"),
                                new S3EventNotification.ResponseElementsEntity("xAmzId2", "xAmzRequestId"),
                                new S3EventNotification.S3Entity("configurationId",
                                        new S3EventNotification.S3BucketEntity("name",
                                                new S3EventNotification.UserIdentityEntity("principalId"), "arn"),
                                        new S3EventNotification.S3ObjectEntity("key", 0L, "eTag", "versionId",
                                                "sequencer"), "s3SchemaVersion"),
                                new S3EventNotification.UserIdentityEntity("principalId")))), new SNSEvent(),
                new ApplicationLoadBalancerRequestEvent(),
                new KinesisTimeWindowEvent(List.of(new KinesisEvent.KinesisEventRecord()), new TimeWindow(),
                        Map.ofEntries(Map.entry("value", "value")), "shardId", "eventSourceArn", false, false),
                new CognitoUserPoolCustomMessageEvent("version", "triggerSource", "region", "userPoolId", "userName",
                        new CognitoUserPoolEvent.CallerContext(),
                        new CognitoUserPoolCustomMessageEvent.Request(Map.ofEntries(Map.entry("value", "value")),
                                Map.ofEntries(Map.entry("value", "value")), "codeParameter", "usernameParameter"),
                        new CognitoUserPoolCustomMessageEvent.Response()),
                new StreamsEventResponse(List.of(new StreamsEventResponse.BatchItemFailure("itemIdentifier"))),
                new APIGatewayProxyResponseEvent(),
                new SimpleIAMPolicyResponse(false, Map.ofEntries(Map.entry("value", "value"))), new IamPolicyResponse(),
                new KinesisEvent(), new KinesisFirehoseEvent(), new KinesisAnalyticsInputPreprocessingResponse(
                        List.of(new KinesisAnalyticsInputPreprocessingResponse.Record("recordId",
                                KinesisAnalyticsInputPreprocessingResponse.Result.Ok,
                                ByteBuffer.wrap("content".getBytes())))),
                new KinesisAnalyticsStreamsInputPreprocessingEvent("invocationId", "applicationArn", "streamArn",
                        List.of(new KinesisAnalyticsStreamsInputPreprocessingEvent.Record("recordId",
                                new KinesisAnalyticsStreamsInputPreprocessingEvent.Record.KinesisStreamRecordMetadata(
                                        "sequenceNumber", "partitionKey", "shardId", 0L),
                                ByteBuffer.wrap("content".getBytes())))), new APIGatewayCustomAuthorizerEvent(),
                new CognitoUserPoolMigrateUserEvent("version", "triggerSource", "region", "userPoolId", "userName",
                        new CognitoUserPoolEvent.CallerContext(),
                        new CognitoUserPoolMigrateUserEvent.Request(Map.ofEntries(Map.entry("value", "value")),
                                Map.ofEntries(Map.entry("value", "value")), Map.ofEntries(Map.entry("value", "value")),
                                "userName", "password"), new CognitoUserPoolMigrateUserEvent.Response()),
                new CognitoUserPoolDefineAuthChallengeEvent("version", "triggerSource", "region", "userPoolId",
                        "userName", new CognitoUserPoolEvent.CallerContext(),
                        new CognitoUserPoolDefineAuthChallengeEvent.Request(Map.ofEntries(Map.entry("value", "value")),
                                Map.ofEntries(Map.entry("value", "value")),
                                new CognitoUserPoolDefineAuthChallengeEvent.ChallengeResult[]{new CognitoUserPoolDefineAuthChallengeEvent.ChallengeResult()},
                                false), new CognitoUserPoolDefineAuthChallengeEvent.Response()), new CognitoEvent(),
                new APIGatewayV2WebSocketResponse(), new ConnectEvent(), new ConfigEvent(),
                new APIGatewayV2CustomAuthorizerEvent(), new CodeCommitEvent(), new APIGatewayV2HTTPEvent(),
                new SecretsManagerRotationEvent("step", "secretId", "clientRequestToken"),
                new CognitoUserPoolPreTokenGenerationEvent("version", "triggerSource", "region", "userPoolId",
                        "userName", new CognitoUserPoolEvent.CallerContext(),
                        new CognitoUserPoolPreTokenGenerationEvent.Request(Map.ofEntries(Map.entry("value", "value")),
                                Map.ofEntries(Map.entry("value", "value")),
                                new CognitoUserPoolPreTokenGenerationEvent.GroupConfiguration()),
                        new CognitoUserPoolPreTokenGenerationEvent.Response()), new S3BatchResponse(),
                new ApplicationLoadBalancerResponseEvent(), new ScheduledEvent(), new ActiveMQEvent(),
                new KinesisAnalyticsOutputDeliveryResponse(
                        List.of(new KinesisAnalyticsOutputDeliveryResponse.Record("recordId",
                                KinesisAnalyticsOutputDeliveryResponse.Result.Ok))),
                new APIGatewayV2ProxyRequestEvent(), new CloudFormationCustomResourceEvent(),
                new TimeWindowEventResponse(Map.ofEntries(Map.entry("value", "value")),
                        List.of(new TimeWindowEventResponse.BatchItemFailure("itemIdentifier"))),
                new CognitoUserPoolCreateAuthChallengeEvent("version", "triggerSource", "region", "userPoolId",
                        "userName", new CognitoUserPoolEvent.CallerContext(),
                        new CognitoUserPoolCreateAuthChallengeEvent.Request(Map.ofEntries(Map.entry("value", "value")),
                                Map.ofEntries(Map.entry("value", "value")), "challengeName",
                                new CognitoUserPoolCreateAuthChallengeEvent.ChallengeResult[]{new CognitoUserPoolCreateAuthChallengeEvent.ChallengeResult()},
                                false), new CognitoUserPoolCreateAuthChallengeEvent.Response()), new RabbitMQEvent(),
                new IoTButtonEvent(),
                new CognitoUserPoolPreAuthenticationEvent("version", "triggerSource", "region", "userPoolId",
                        "userName", new CognitoUserPoolEvent.CallerContext(),
                        new CognitoUserPoolPreAuthenticationEvent.Request(Map.ofEntries(Map.entry("value", "value")),
                                Map.ofEntries(Map.entry("value", "value")), false)), new CloudFrontEvent(),
                new CognitoUserPoolPreSignUpEvent("version", "triggerSource", "region", "userPoolId", "userName",
                        new CognitoUserPoolEvent.CallerContext(),
                        new CognitoUserPoolPreSignUpEvent.Request(Map.ofEntries(Map.entry("value", "value")),
                                Map.ofEntries(Map.entry("value", "value")), Map.ofEntries(Map.entry("value", "value"))),
                        new CognitoUserPoolPreSignUpEvent.Response()), new IamPolicyResponseV1(),
                new TimeWindow("start", "end"), EncryptionType.NONE, new Record(), StreamViewType.NEW_IMAGE,
                new StreamRecord(), OperationType.INSERT,
                new com.amazonaws.services.lambda.runtime.events.models.dynamodb.Record(), new AttributeValue("s"),
                new Identity(), new S3EventNotification(
                        List.of(new S3EventNotification.S3EventNotificationRecord("awsRegion", "eventName",
                                "eventSource", "eventTime", "eventVersion",
                                new S3EventNotification.RequestParametersEntity("sourceIPAddress"),
                                new S3EventNotification.ResponseElementsEntity("xAmzId2", "xAmzRequestId"),
                                new S3EventNotification.S3Entity("configurationId",
                                        new S3EventNotification.S3BucketEntity("name",
                                                new S3EventNotification.UserIdentityEntity("principalId"), "arn"),
                                        new S3EventNotification.S3ObjectEntity("key", 0L, "eTag", "versionId",
                                                "sequencer"), "s3SchemaVersion"),
                                new S3EventNotification.UserIdentityEntity("principalId")))),
                new KinesisAnalyticsFirehoseInputPreprocessingEvent("invocationId", "applicationArn", "streamArn",
                        List.of(new KinesisAnalyticsFirehoseInputPreprocessingEvent.Record("recordId",
                                new KinesisAnalyticsFirehoseInputPreprocessingEvent.Record.KinesisFirehoseRecordMetadata(
                                        0L), ByteBuffer.wrap("content".getBytes())))),
                new DynamodbTimeWindowEvent(List.of(new DynamodbEvent.DynamodbStreamRecord()), new TimeWindow(),
                        Map.ofEntries(Map.entry("value", "value")), "shardId", "eventSourceArn", false, false));
    }
}
