package com.myapp;

import com.google.api.gax.rpc.ApiException;
import com.google.cloud.dialogflow.cx.v3.*;
import com.google.protobuf.ByteString;
import com.google.protobuf.Struct;
import com.google.protobuf.Value;
import com.google.type.LatLng;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

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
    void testTryDetectIntent() {
        // Setup
        // Configure SessionsClient.detectIntent(...).
        final DetectIntentResponse detectIntentResponse = DetectIntentResponse.newBuilder()
                .setResponseId("responseId")
                .setQueryResult(QueryResult.newBuilder()
                        .setMatch(Match.newBuilder()
                                .setConfidence(0.0f)
                                .build())
                        .build())
                .build();
        when(mockSessionsClient.detectIntent(DetectIntentRequest.newBuilder()
                .setSession("theSession")
                .setQueryParams(QueryParameters.newBuilder()
                        .setTimeZone("UTC")
                        .setGeoLocation(LatLng.newBuilder()
                                .setLatitude(0.0)
                                .setLongitude(0.0)
                                .build())
                        .addSessionEntityTypes(SessionEntityType.newBuilder()
                                .setName("theEntityName")
                                .setEntityOverrideMode(
                                        SessionEntityType.EntityOverrideMode.ENTITY_OVERRIDE_MODE_UNSPECIFIED)
                                .addEntities(EntityType.Entity.newBuilder()
                                        .setValue("entityValue")
                                        .addSynonyms("entityySynonym")
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
                                .setText("queryTextInput")
                                .build())
                        .setIntent(IntentInput.newBuilder()
                                .setIntent("queryIntent")
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
                .build())).thenReturn(detectIntentResponse);

        // Run the test
        myClassUnderTest.tryDetectIntent();

        // Verify the results
    }

    @Test
    void testTryDetectIntent_SessionsClientThrowsApiException() {
        // Setup
        when(mockSessionsClient.detectIntent(DetectIntentRequest.newBuilder()
                .setSession("theSession")
                .setQueryParams(QueryParameters.newBuilder()
                        .setTimeZone("UTC")
                        .setGeoLocation(LatLng.newBuilder()
                                .setLatitude(0.0)
                                .setLongitude(0.0)
                                .build())
                        .addSessionEntityTypes(SessionEntityType.newBuilder()
                                .setName("theEntityName")
                                .setEntityOverrideMode(
                                        SessionEntityType.EntityOverrideMode.ENTITY_OVERRIDE_MODE_UNSPECIFIED)
                                .addEntities(EntityType.Entity.newBuilder()
                                        .setValue("entityValue")
                                        .addSynonyms("entityySynonym")
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
                                .setText("queryTextInput")
                                .build())
                        .setIntent(IntentInput.newBuilder()
                                .setIntent("queryIntent")
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
                .build())).thenThrow(ApiException.class);

        // Run the test
        assertThrows(ApiException.class, () -> myClassUnderTest.tryDetectIntent());
    }
}
