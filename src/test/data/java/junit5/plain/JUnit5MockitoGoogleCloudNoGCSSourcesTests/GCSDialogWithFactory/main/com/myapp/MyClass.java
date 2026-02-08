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

import com.google.api.gax.rpc.ApiException;
import com.google.cloud.dialogflow.cx.v3.*;
import com.myapp.gcs.dialog.SessionFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyClass {
    private final SessionFactory sessionFactory;

    public MyClass(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // DialogFlow API Detect Intent sample with text inputs.
    public Map<String, QueryResult> detectIntent(
            String projectId,
            String locationId,
            String agentId,
            String sessionId,
            List<String> texts,
            String languageCode)
            throws IOException, ApiException {
        Map<String, QueryResult> queryResults = new HashMap<>();
        // Instantiates a client
        try (SessionsClient sessionsClient = sessionFactory.openSession()) {
            // Set the session name using the projectID (my-project-id), locationID (global), agentID
            // (OtherId), and sessionId (OtherId).
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
                System.out.format(
                        "Detected Intent: %s (confidence: %f)\n",
                        queryResult.getIntent().getDisplayName(), queryResult.getIntentDetectionConfidence());

                queryResults.put(text, queryResult);
            }
        }
        return queryResults;
    }
}
