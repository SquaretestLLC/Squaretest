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
        final DetectIntentRequest request = DetectIntentRequest.newBuilder()
                .setSession("theSession")
                .setQueryParams(QueryParameters.newBuilder()
                        .setTimeZone("UTC")
                        .setGeoLocation(LatLng.newBuilder()
                                // Set the value multiple times.
                                // Squaretest should set them to the default (0.0).
                                .setLatitude(3.0)
                                .setLatitude(10.0)
                                .setLongitude(4.0)
                                .setLongitude(12.0)
                                .build())
                        .addSessionEntityTypes(SessionEntityType.newBuilder()
                                .setName("theEntityName")
                                // Call the same setter with different values.
                                // Squaretest should set the value to ENTITY_OVERRIDE_MODE_UNSPECIFIED.
                                .setEntityOverrideMode(SessionEntityType.EntityOverrideMode.ENTITY_OVERRIDE_MODE_OVERRIDE)
                                .setEntityOverrideMode(SessionEntityType.EntityOverrideMode.ENTITY_OVERRIDE_MODE_SUPPLEMENT)
                                .addEntities(EntityType.Entity.newBuilder()
                                        .setValue("entityValue")
                                        .addSynonyms("entityySynonym")
                                        .build())
                                .build())
                        .setPayload(Struct.newBuilder()
                                .putFields("payloadKey", Value.newBuilder()
                                        .setStringValue("payloadStringValue")
                                        .build())
                                .build())
                        .setParameters(Struct.newBuilder()
                                .putFields("fieldsKey", Value.newBuilder()
                                        .setStringValue("value")
                                        .build())
                                .build())
                        .setAnalyzeQueryTextSentiment(false)
                        .build())
                .setQueryInput(QueryInput.newBuilder()
                        .setText(TextInput.newBuilder()
                                .setText("queryTextInput")
                                .build())
                        .setIntent(IntentInput.newBuilder()
                                .setIntent("queryIntent")
                                .build())
                        .setAudio(AudioInput.newBuilder()
                                .setConfig(InputAudioConfig.newBuilder()
                                        .setAudioEncoding(AudioEncoding.AUDIO_ENCODING_FLAC)
                                        .setSampleRateHertz(0)
                                        .setEnableWordInfo(false)
                                        .addPhraseHints("value")
                                        .setModel("value")
                                        .setModelVariant(SpeechModelVariant.USE_BEST_AVAILABLE)
                                        .setSingleUtterance(false)
                                        .build())
                                .setAudio(ByteString.copyFrom("content".getBytes()))
                                .build())
                        .setEvent(EventInput.newBuilder()
                                .setEvent("value")
                                .build())
                        .setDtmf(DtmfInput.newBuilder()
                                .setDigits("value")
                                .setFinishDigit("value")
                                .build())
                        .setLanguageCode("value")
                        .build())
                .setOutputAudioConfig(OutputAudioConfig.newBuilder()
                        .setAudioEncoding(OutputAudioEncoding.OUTPUT_AUDIO_ENCODING_MP3)
                        .setSampleRateHertz(5)
                        .setSynthesizeSpeechConfig(SynthesizeSpeechConfig.newBuilder()
                                .setSpeakingRate(2.0)
                                .setPitch(Pitch)
                                .setVolumeGainDb(GainVolume)
                                .addEffectsProfileId("value")
                                .setVoice(VoiceSelectionParams.newBuilder()
                                        .setName("value")
                                        // Set the voice gender to different values using different setters.
                                        // Squaretest should set the value to the default (SSML_VOICE_GENDER_UNSPECIFIED).
                                        .setSsmlGender(SsmlVoiceGender.SSML_VOICE_GENDER_FEMALE)
                                        .setSsmlGenderValue(1)
                                        .build())
                                .build())
                        .build())
                .build();
        final DetectIntentResponse result = sessionsClient.detectIntent(request);
        final String responseId = result.getResponseId();
        final QueryResult queryResult = result.getQueryResult();
        final Match match = queryResult.getMatch();
        final float confidence = match.getConfidence();
    }
}
