package com.myapp;

import com.google.api.gax.rpc.ApiException;
import com.google.cloud.dialogflow.cx.v3.*;
import com.google.protobuf.Struct;
import com.google.protobuf.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private SessionsClient mockSessionsClient;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockSessionsClient);
    }

    @Test
    void testDetectIntent() {
        // Setup
        final Map<String, QueryInfo> expectedResult = new HashMap<>();

        // Configure SessionsClient.detectIntent(...).
        final DetectIntentResponse detectIntentResponse = DetectIntentResponse.newBuilder()
                .setQueryResult(QueryResult.newBuilder()
                        .setMatch(Match.newBuilder()
                                .setIntent(Intent.newBuilder()
                                        .setDisplayName("displayName")
                                        .addTrainingPhrases(Intent.TrainingPhrase.newBuilder().build())
                                        .addParameters(Intent.Parameter.newBuilder().build())
                                        .setIsFallback(false)
                                        .build())
                                .setParameters(Struct.newBuilder()
                                        .putFields("key", Value.newBuilder()
                                                .setStringValue("value")
                                                .build())
                                        .build())
                                .setMatchType(Match.MatchType.MATCH_TYPE_UNSPECIFIED)
                                .setConfidence(0.0f)
                                .build())
                        .build())
                .build();
        when(mockSessionsClient.detectIntent(DetectIntentRequest.newBuilder()
                .setSession("session")
                .setQueryInput(QueryInput.newBuilder()
                        .setText(TextInput.newBuilder()
                                .setText("text")
                                .build())
                        .setLanguageCode("languageCode")
                        .build())
                .build())).thenReturn(detectIntentResponse);

        // Run the test
        final Map<String, QueryInfo> result = myClassUnderTest.detectIntent("projectId", "locationId", "agentId",
                "sessionId", Arrays.asList("value"), "languageCode");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testDetectIntent_SessionsClientThrowsApiException() {
        // Setup
        when(mockSessionsClient.detectIntent(DetectIntentRequest.newBuilder()
                .setSession("session")
                .setQueryInput(QueryInput.newBuilder()
                        .setText(TextInput.newBuilder()
                                .setText("text")
                                .build())
                        .setLanguageCode("languageCode")
                        .build())
                .build())).thenThrow(ApiException.class);

        // Run the test
        assertThrows(ApiException.class,
                () -> myClassUnderTest.detectIntent("projectId", "locationId", "agentId", "sessionId",
                        Arrays.asList("value"), "languageCode"));
    }
}
