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

import com.google.api.gax.rpc.BidiStreamingCallable;
import com.google.api.gax.rpc.UnaryCallable;
import com.google.cloud.dialogflow.cx.v3.*;
import com.google.cloud.dialogflow.cx.v3.stub.SessionsStub;
import com.google.protobuf.ByteString;
import com.google.protobuf.Struct;
import com.google.protobuf.Value;
import com.google.type.LatLng;

import java.util.concurrent.TimeUnit;

public class MyClass {

    private SessionsClient sessionsClient;

    public MyClass(final SessionsClient sessionsClient) {
        this.sessionsClient = sessionsClient;
    }

    public void tryDetectIntent() {
        final DetectIntentRequest request = DetectIntentRequest.newBuilder()
                .setSession("value")
                .setQueryParams(QueryParameters.newBuilder()
                        .setTimeZone("value")
                        .setGeoLocation(LatLng.newBuilder()
                                .setLatitude(0.0)
                                .setLongitude(0.0)
                                .build())
                        .addSessionEntityTypes(SessionEntityType.newBuilder()
                                .setName("value")
                                .setEntityOverrideMode(SessionEntityType.EntityOverrideMode.ENTITY_OVERRIDE_MODE_UNSPECIFIED)
                                .addEntities(EntityType.Entity.newBuilder()
                                        .setValue("value")
                                        .addSynonyms("value")
                                        .build())
                                .build())
                        .setPayload(Struct.newBuilder()
                                .putFields("key", Value.newBuilder()
                                        .setStringValue("value")
                                        .build())
                                .build())
                        .setParameters(Struct.newBuilder()
                                .putFields("key", Value.newBuilder()
                                        .setStringValue("value")
                                        .build())
                                .build())
                        .setAnalyzeQueryTextSentiment(false)
                        .build())
                .setQueryInput(QueryInput.newBuilder()
                        .setText(TextInput.newBuilder()
                                .setText("value")
                                .build())
                        .setIntent(IntentInput.newBuilder()
                                .setIntent("value")
                                .build())
                        .setAudio(AudioInput.newBuilder()
                                .setConfig(InputAudioConfig.newBuilder()
                                        .setAudioEncoding(AudioEncoding.AUDIO_ENCODING_UNSPECIFIED)
                                        .setSampleRateHertz(0)
                                        .setEnableWordInfo(false)
                                        .addPhraseHints("value")
                                        .setModel("value")
                                        .setModelVariant(SpeechModelVariant.SPEECH_MODEL_VARIANT_UNSPECIFIED)
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
                        .setAudioEncoding(OutputAudioEncoding.OUTPUT_AUDIO_ENCODING_UNSPECIFIED)
                        .setSampleRateHertz(0)
                        .setSynthesizeSpeechConfig(SynthesizeSpeechConfig.newBuilder()
                                .setSpeakingRate(0.0)
                                .setPitch(0.0)
                                .setVolumeGainDb(0.0)
                                .addEffectsProfileId("value")
                                .setVoice(VoiceSelectionParams.newBuilder()
                                        .setName("value")
                                        .setSsmlGender(SsmlVoiceGender.SSML_VOICE_GENDER_UNSPECIFIED)
                                        .build())
                                .build())
                        .build())
                .build();
        final DetectIntentResponse result = sessionsClient.detectIntent(request);
    }

    public void tryDetectIntentCallable() {
        final UnaryCallable<DetectIntentRequest, DetectIntentResponse> result = sessionsClient.detectIntentCallable();
    }

    public void tryStreamingDetectIntentCallable() {
        final BidiStreamingCallable<StreamingDetectIntentRequest, StreamingDetectIntentResponse> result = sessionsClient.streamingDetectIntentCallable();
    }

    public void tryMatchIntent() {
        final MatchIntentRequest request = MatchIntentRequest.newBuilder()
                .setSession("value")
                .setQueryParams(QueryParameters.newBuilder()
                        .setTimeZone("value")
                        .setGeoLocation(LatLng.newBuilder()
                                .setLatitude(0.0)
                                .setLongitude(0.0)
                                .build())
                        .addSessionEntityTypes(SessionEntityType.newBuilder()
                                .setName("value")
                                .setEntityOverrideMode(SessionEntityType.EntityOverrideMode.ENTITY_OVERRIDE_MODE_UNSPECIFIED)
                                .addEntities(EntityType.Entity.newBuilder()
                                        .setValue("value")
                                        .addSynonyms("value")
                                        .build())
                                .build())
                        .setPayload(Struct.newBuilder()
                                .putFields("key", Value.newBuilder()
                                        .setStringValue("value")
                                        .build())
                                .build())
                        .setParameters(Struct.newBuilder()
                                .putFields("key", Value.newBuilder()
                                        .setStringValue("value")
                                        .build())
                                .build())
                        .setAnalyzeQueryTextSentiment(false)
                        .build())
                .setQueryInput(QueryInput.newBuilder()
                        .setText(TextInput.newBuilder()
                                .setText("value")
                                .build())
                        .setIntent(IntentInput.newBuilder()
                                .setIntent("value")
                                .build())
                        .setAudio(AudioInput.newBuilder()
                                .setConfig(InputAudioConfig.newBuilder()
                                        .setAudioEncoding(AudioEncoding.AUDIO_ENCODING_UNSPECIFIED)
                                        .setSampleRateHertz(0)
                                        .setEnableWordInfo(false)
                                        .addPhraseHints("value")
                                        .setModel("value")
                                        .setModelVariant(SpeechModelVariant.SPEECH_MODEL_VARIANT_UNSPECIFIED)
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
                .build();
        final MatchIntentResponse result = sessionsClient.matchIntent(request);
    }

    public void tryMatchIntentCallable() {
        final UnaryCallable<MatchIntentRequest, MatchIntentResponse> result = sessionsClient.matchIntentCallable();
    }

    public void tryFulfillIntent() {
        final FulfillIntentRequest request = FulfillIntentRequest.newBuilder()
                .setMatchIntentRequest(MatchIntentRequest.newBuilder()
                        .setSession("value")
                        .setQueryParams(QueryParameters.newBuilder()
                                .setTimeZone("value")
                                .setGeoLocation(LatLng.newBuilder()
                                        .setLatitude(0.0)
                                        .setLongitude(0.0)
                                        .build())
                                .addSessionEntityTypes(SessionEntityType.newBuilder()
                                        .setName("value")
                                        .setEntityOverrideMode(SessionEntityType.EntityOverrideMode.ENTITY_OVERRIDE_MODE_UNSPECIFIED)
                                        .addEntities(EntityType.Entity.newBuilder()
                                                .setValue("value")
                                                .addSynonyms("value")
                                                .build())
                                        .build())
                                .setPayload(Struct.newBuilder()
                                        .putFields("key", Value.newBuilder()
                                                .setStringValue("value")
                                                .build())
                                        .build())
                                .setParameters(Struct.newBuilder()
                                        .putFields("key", Value.newBuilder()
                                                .setStringValue("value")
                                                .build())
                                        .build())
                                .setAnalyzeQueryTextSentiment(false)
                                .build())
                        .setQueryInput(QueryInput.newBuilder()
                                .setText(TextInput.newBuilder()
                                        .setText("value")
                                        .build())
                                .setIntent(IntentInput.newBuilder()
                                        .setIntent("value")
                                        .build())
                                .setAudio(AudioInput.newBuilder()
                                        .setConfig(InputAudioConfig.newBuilder()
                                                .setAudioEncoding(AudioEncoding.AUDIO_ENCODING_UNSPECIFIED)
                                                .setSampleRateHertz(0)
                                                .setEnableWordInfo(false)
                                                .addPhraseHints("value")
                                                .setModel("value")
                                                .setModelVariant(SpeechModelVariant.SPEECH_MODEL_VARIANT_UNSPECIFIED)
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
                        .build())
                .setMatch(Match.newBuilder()
                        .setIntent(Intent.newBuilder()
                                .setName("value")
                                .setDisplayName("value")
                                .addTrainingPhrases(Intent.TrainingPhrase.newBuilder()
                                        .setId("value")
                                        .addParts(Intent.TrainingPhrase.Part.newBuilder()
                                                .setText("value")
                                                .setParameterId("value")
                                                .build())
                                        .setRepeatCount(0)
                                        .build())
                                .addParameters(Intent.Parameter.newBuilder()
                                        .setId("value")
                                        .setEntityType("value")
                                        .setIsList(false)
                                        .setRedact(false)
                                        .build())
                                .setPriority(0)
                                .setIsFallback(false)
                                .putLabels("key", "value")
                                .setDescription("value")
                                .build())
                        .setEvent("value")
                        .setParameters(Struct.newBuilder()
                                .putFields("key", Value.newBuilder()
                                        .setStringValue("value")
                                        .build())
                                .build())
                        .setResolvedInput("value")
                        .setMatchType(Match.MatchType.MATCH_TYPE_UNSPECIFIED)
                        .setConfidence(0.0f)
                        .build())
                .setOutputAudioConfig(OutputAudioConfig.newBuilder()
                        .setAudioEncoding(OutputAudioEncoding.OUTPUT_AUDIO_ENCODING_UNSPECIFIED)
                        .setSampleRateHertz(0)
                        .setSynthesizeSpeechConfig(SynthesizeSpeechConfig.newBuilder()
                                .setSpeakingRate(0.0)
                                .setPitch(0.0)
                                .setVolumeGainDb(0.0)
                                .addEffectsProfileId("value")
                                .setVoice(VoiceSelectionParams.newBuilder()
                                        .setName("value")
                                        .setSsmlGender(SsmlVoiceGender.SSML_VOICE_GENDER_UNSPECIFIED)
                                        .build())
                                .build())
                        .build())
                .build();
        final FulfillIntentResponse result = sessionsClient.fulfillIntent(request);
    }

    public void tryFulfillIntentCallable() {
        final UnaryCallable<FulfillIntentRequest, FulfillIntentResponse> result = sessionsClient.fulfillIntentCallable();
    }

    public void tryClose() {
        sessionsClient.close();
    }

    public void tryShutdown() {
        sessionsClient.shutdown();
    }

    public void tryIsShutdown() {
        final boolean result = sessionsClient.isShutdown();
    }

    public void tryIsTerminated() {
        final boolean result = sessionsClient.isTerminated();
    }

    public void tryShutdownNow() {
        sessionsClient.shutdownNow();
    }

    public void tryAwaitTermination() throws Exception {
        final boolean result = sessionsClient.awaitTermination(0L, TimeUnit.MILLISECONDS);
    }

    public void tryCreate1() throws Exception {
        final SessionsClient result = SessionsClient.create();
    }

    public void tryCreate2() throws Exception {
        final SessionsSettings settings = SessionsSettings.create(null);
        final SessionsClient result = SessionsClient.create(settings);
    }

    public void tryCreate3() {
        final SessionsStub stub = null;
        final SessionsClient result = SessionsClient.create(stub);
    }
}
