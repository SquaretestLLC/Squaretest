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

import com.google.cloud.dialogflow.cx.v3.*;
import com.google.protobuf.StructOrBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyClass {
    private final SessionsClient sessionsClient;

    public MyClass(final SessionsClient sessionsClient) {
        this.sessionsClient = sessionsClient;
    }

    // DialogFlow API Detect Intent sample with text inputs.
    public Map<String, QueryInfo> detectIntent(
            String projectId,
            String locationId,
            String agentId,
            String sessionId,
            List<String> texts,
            String languageCode) {
        Map<String, QueryInfo> queryResults = new HashMap<>();
        // Instantiates a client
        // Set the session name using the projectID (my-project-id), locationID (global), agentID
        // (UUID), and sessionId (UUID).
        SessionName session = SessionName.of(projectId, locationId, agentId, sessionId);
        System.out.println("Session Path: " + session.toString());

        // Detect intents for each text input.
        for (String text : texts) {
            // Set the text (hello) for the query.
            TextInput.Builder textInput = TextInput.newBuilder().setText(text);

            // Build the query with the TextInput and language code (en-US).
            QueryInput queryInput =
                    QueryInput.newBuilder().setText(textInput).setLanguageCode(languageCode).build();

            // Build the DetectIntentRequest with the SessionName and QueryInput.
            DetectIntentRequest request =
                    DetectIntentRequest.newBuilder()
                            .setSession(session.toString())
                            .setQueryInput(queryInput)
                            .build();

            // Performs the detect intent request.
            DetectIntentResponse response = sessionsClient.detectIntent(request);

            // Display the query result.
            QueryResult queryResult = response.getQueryResult();

            System.out.println("====================");
            System.out.format("Query Text: '%s'\n", queryResult.getText());
            final Match match = queryResult.getMatch();
            final Intent intent = match.getIntent();
            final String displayName = intent.getDisplayName();
            final float confidence = match.getConfidence();
            final int matchType = match.getMatchTypeValue();
            final String descriptionStr = intent.getDescriptionBytes().toString();
            final boolean isFallback = intent.getIsFallback();
            final List<Intent.Parameter> intentParams = intent.getParametersList();
            final List<? extends Intent.TrainingPhraseOrBuilder> trainingPhrasesOrBuilderList = intent.getTrainingPhrasesOrBuilderList();
            for(final Intent.TrainingPhraseOrBuilder phrase : trainingPhrasesOrBuilderList) {
                // Note: Squaretest won't find this. It can't link the TrainingPhraseOrBuilder to the builder method.
                final Intent.TrainingPhrase.Part partZero = phrase.getParts(0);
                final String phraseId = phrase.getId();
            }
            final StructOrBuilder matchParamsOrBuilder = match.getParametersOrBuilder();
            matchParamsOrBuilder.getAllFields();

            System.out.format(
                    "Detected Intent: %s (confidence: %f)\n",
                    displayName, confidence);

            queryResults.put(text, new QueryInfo(displayName, confidence, matchType));
        }
        return queryResults;
    }
}
