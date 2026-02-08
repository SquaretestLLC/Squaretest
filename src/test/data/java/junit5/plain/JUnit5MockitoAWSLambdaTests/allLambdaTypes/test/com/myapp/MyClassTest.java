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
import java.util.Arrays;
import java.util.HashMap;

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
                        new CognitoUserPoolPostAuthenticationEvent.Request(new HashMap<>(), new HashMap<>(), false)),
                new LambdaDestinationEvent(), new LexEvent(), new S3ObjectLambdaEvent(),
                new KinesisAnalyticsOutputDeliveryEvent("invocationId", "applicationArn", Arrays.asList(
                        new KinesisAnalyticsOutputDeliveryEvent.Record("recordId",
                                new KinesisAnalyticsOutputDeliveryEvent.Record.LambdaDeliveryRecordMetadata(0L),
                                ByteBuffer.wrap("content".getBytes())))), new APIGatewayV2ProxyResponseEvent(),
                new CognitoUserPoolVerifyAuthChallengeResponseEvent("version", "triggerSource", "region", "userPoolId",
                        "userName", new CognitoUserPoolEvent.CallerContext(),
                        new CognitoUserPoolVerifyAuthChallengeResponseEvent.Request(new HashMap<>(), new HashMap<>(),
                                new HashMap<>(), new HashMap<>(), false),
                        new CognitoUserPoolVerifyAuthChallengeResponseEvent.Response()),
                new AppSyncLambdaAuthorizerResponse(false, new HashMap<>(), Arrays.asList("value"), 0),
                new APIGatewayV2WebSocketEvent(), new APIGatewayProxyRequestEvent(), new S3BatchEvent(),
                new CognitoUserPoolPostConfirmationEvent("version", "triggerSource", "region", "userPoolId", "userName",
                        new CognitoUserPoolEvent.CallerContext(),
                        new CognitoUserPoolPostConfirmationEvent.Request(new HashMap<>(), new HashMap<>())),
                new SQSEvent(), new KafkaEvent(new HashMap<>(), "eventSource", "eventSourceArn", "bootstrapServers"),
                null, new DynamodbEvent(), new APIGatewayV2HTTPResponse(), new CloudWatchLogsEvent(),
                new AppSyncLambdaAuthorizerEvent(), new S3Event(Arrays.asList(
                        new S3EventNotification.S3EventNotificationRecord("awsRegion", "eventName", "eventSource",
                                "eventTime", "eventVersion",
                                new S3EventNotification.RequestParametersEntity("sourceIPAddress"),
                                new S3EventNotification.ResponseElementsEntity("xAmzId2", "xAmzRequestId"),
                                new S3EventNotification.S3Entity("configurationId",
                                        new S3EventNotification.S3BucketEntity("name",
                                                new S3EventNotification.UserIdentityEntity("principalId"), "arn"),
                                        new S3EventNotification.S3ObjectEntity("key", 0L, "eTag", "versionId",
                                                "sequencer"), "s3SchemaVersion"),
                                new S3EventNotification.UserIdentityEntity("principalId")))), new SNSEvent(),
                new ApplicationLoadBalancerRequestEvent(),
                new KinesisTimeWindowEvent(Arrays.asList(new KinesisEvent.KinesisEventRecord()), new TimeWindow(),
                        new HashMap<>(), "shardId", "eventSourceArn", false, false),
                new CognitoUserPoolCustomMessageEvent("version", "triggerSource", "region", "userPoolId", "userName",
                        new CognitoUserPoolEvent.CallerContext(),
                        new CognitoUserPoolCustomMessageEvent.Request(new HashMap<>(), new HashMap<>(), "codeParameter",
                                "usernameParameter"), new CognitoUserPoolCustomMessageEvent.Response()),
                new StreamsEventResponse(Arrays.asList(new StreamsEventResponse.BatchItemFailure("itemIdentifier"))),
                new APIGatewayProxyResponseEvent(), new SimpleIAMPolicyResponse(false, new HashMap<>()),
                new IamPolicyResponse(), new KinesisEvent(), new KinesisFirehoseEvent(),
                new KinesisAnalyticsInputPreprocessingResponse(Arrays.asList(
                        new KinesisAnalyticsInputPreprocessingResponse.Record("recordId",
                                KinesisAnalyticsInputPreprocessingResponse.Result.Ok,
                                ByteBuffer.wrap("content".getBytes())))),
                new KinesisAnalyticsStreamsInputPreprocessingEvent("invocationId", "applicationArn", "streamArn",
                        Arrays.asList(new KinesisAnalyticsStreamsInputPreprocessingEvent.Record("recordId",
                                new KinesisAnalyticsStreamsInputPreprocessingEvent.Record.KinesisStreamRecordMetadata(
                                        "sequenceNumber", "partitionKey", "shardId", 0L),
                                ByteBuffer.wrap("content".getBytes())))), new APIGatewayCustomAuthorizerEvent(),
                new CognitoUserPoolMigrateUserEvent("version", "triggerSource", "region", "userPoolId", "userName",
                        new CognitoUserPoolEvent.CallerContext(),
                        new CognitoUserPoolMigrateUserEvent.Request(new HashMap<>(), new HashMap<>(), new HashMap<>(),
                                "userName", "password"), new CognitoUserPoolMigrateUserEvent.Response()),
                new CognitoUserPoolDefineAuthChallengeEvent("version", "triggerSource", "region", "userPoolId",
                        "userName", new CognitoUserPoolEvent.CallerContext(),
                        new CognitoUserPoolDefineAuthChallengeEvent.Request(new HashMap<>(), new HashMap<>(),
                                new CognitoUserPoolDefineAuthChallengeEvent.ChallengeResult[]{new CognitoUserPoolDefineAuthChallengeEvent.ChallengeResult()},
                                false), new CognitoUserPoolDefineAuthChallengeEvent.Response()), new CognitoEvent(),
                new APIGatewayV2WebSocketResponse(), new ConnectEvent(), new ConfigEvent(),
                new APIGatewayV2CustomAuthorizerEvent(), new CodeCommitEvent(), new APIGatewayV2HTTPEvent(),
                new SecretsManagerRotationEvent("step", "secretId", "clientRequestToken"),
                new CognitoUserPoolPreTokenGenerationEvent("version", "triggerSource", "region", "userPoolId",
                        "userName", new CognitoUserPoolEvent.CallerContext(),
                        new CognitoUserPoolPreTokenGenerationEvent.Request(new HashMap<>(), new HashMap<>(),
                                new CognitoUserPoolPreTokenGenerationEvent.GroupConfiguration()),
                        new CognitoUserPoolPreTokenGenerationEvent.Response()), new S3BatchResponse(),
                new ApplicationLoadBalancerResponseEvent(), new ScheduledEvent(), new ActiveMQEvent(),
                new KinesisAnalyticsOutputDeliveryResponse(Arrays.asList(
                        new KinesisAnalyticsOutputDeliveryResponse.Record("recordId",
                                KinesisAnalyticsOutputDeliveryResponse.Result.Ok))),
                new APIGatewayV2ProxyRequestEvent(), new CloudFormationCustomResourceEvent(),
                new TimeWindowEventResponse(new HashMap<>(),
                        Arrays.asList(new TimeWindowEventResponse.BatchItemFailure("itemIdentifier"))),
                new CognitoUserPoolCreateAuthChallengeEvent("version", "triggerSource", "region", "userPoolId",
                        "userName", new CognitoUserPoolEvent.CallerContext(),
                        new CognitoUserPoolCreateAuthChallengeEvent.Request(new HashMap<>(), new HashMap<>(),
                                "challengeName",
                                new CognitoUserPoolCreateAuthChallengeEvent.ChallengeResult[]{new CognitoUserPoolCreateAuthChallengeEvent.ChallengeResult()},
                                false), new CognitoUserPoolCreateAuthChallengeEvent.Response()),
                new RabbitMQEvent("eventSource", "eventSourceArn", new HashMap<>()), new IoTButtonEvent(),
                new CognitoUserPoolPreAuthenticationEvent("version", "triggerSource", "region", "userPoolId",
                        "userName", new CognitoUserPoolEvent.CallerContext(),
                        new CognitoUserPoolPreAuthenticationEvent.Request(new HashMap<>(), new HashMap<>(), false)),
                new CloudFrontEvent(),
                new CognitoUserPoolPreSignUpEvent("version", "triggerSource", "region", "userPoolId", "userName",
                        new CognitoUserPoolEvent.CallerContext(),
                        new CognitoUserPoolPreSignUpEvent.Request(new HashMap<>(), new HashMap<>(), new HashMap<>()),
                        new CognitoUserPoolPreSignUpEvent.Response()), new IamPolicyResponseV1(),
                new TimeWindow("start", "end"), EncryptionType.NONE, new Record(), StreamViewType.NEW_IMAGE,
                new StreamRecord(), OperationType.INSERT,
                new com.amazonaws.services.lambda.runtime.events.models.dynamodb.Record(), new AttributeValue("s"),
                new Identity(), new S3EventNotification(Arrays.asList(
                        new S3EventNotification.S3EventNotificationRecord("awsRegion", "eventName", "eventSource",
                                "eventTime", "eventVersion",
                                new S3EventNotification.RequestParametersEntity("sourceIPAddress"),
                                new S3EventNotification.ResponseElementsEntity("xAmzId2", "xAmzRequestId"),
                                new S3EventNotification.S3Entity("configurationId",
                                        new S3EventNotification.S3BucketEntity("name",
                                                new S3EventNotification.UserIdentityEntity("principalId"), "arn"),
                                        new S3EventNotification.S3ObjectEntity("key", 0L, "eTag", "versionId",
                                                "sequencer"), "s3SchemaVersion"),
                                new S3EventNotification.UserIdentityEntity("principalId")))),
                new KinesisAnalyticsFirehoseInputPreprocessingEvent("invocationId", "applicationArn", "streamArn",
                        Arrays.asList(new KinesisAnalyticsFirehoseInputPreprocessingEvent.Record("recordId",
                                new KinesisAnalyticsFirehoseInputPreprocessingEvent.Record.KinesisFirehoseRecordMetadata(
                                        0L), ByteBuffer.wrap("content".getBytes())))),
                new DynamodbTimeWindowEvent(Arrays.asList(new DynamodbEvent.DynamodbStreamRecord()), new TimeWindow(),
                        new HashMap<>(), "shardId", "eventSourceArn", false, false));
    }
}
