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
import com.google.protobuf.ByteString;
import com.google.protobuf.Struct;
import com.google.protobuf.Value;
import com.google.type.LatLng;

public class MyClass {

    private static final double Pitch = 1.5;
    private static final double GainVolume = Pitch + 1.5;

    private SessionsClient sessionsClient;

    public MyClass(final SessionsClient sessionsClient) {
        this.sessionsClient = sessionsClient;
    }

    public void tryDetectIntent() {
        DetectIntentRequest request = DialogHelpers.createRequest("theParam");
        final DetectIntentResponse result = sessionsClient.detectIntent(request);
        final String responseId = result.getResponseId();
        final QueryResult queryResult = result.getQueryResult();
        final Match match = queryResult.getMatch();
        final float confidence = match.getConfidence();
    }
}
