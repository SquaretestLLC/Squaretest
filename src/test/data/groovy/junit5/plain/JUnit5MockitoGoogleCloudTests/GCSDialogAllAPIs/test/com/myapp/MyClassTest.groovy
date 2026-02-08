package com.myapp

import com.google.api.core.ApiFuture
import com.google.api.core.SettableApiFuture
import com.google.api.gax.rpc.ApiCallContext
import com.google.api.gax.rpc.ApiException
import com.google.api.gax.rpc.UnaryCallable
import com.google.cloud.dialogflow.cx.v3.*
import com.google.protobuf.ByteString
import com.google.protobuf.Struct
import com.google.protobuf.Value
import com.google.type.LatLng
import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock

import java.util.concurrent.TimeUnit

import static org.junit.jupiter.api.Assertions.assertThrows
import static org.mockito.Mockito.verify
import static org.mockito.Mockito.when
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
class MyClassTest {

    @Mock
    private SessionsClient mockSessionsClient

    private MyClass myClassUnderTest

    @BeforeEach
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass(mockSessionsClient)
    }

    @Test
    void testTryDetectIntent() {
        // Setup
        // Configure SessionsClient.detectIntent(...).
        def detectIntentResponse = DetectIntentResponse.newBuilder().build()
        when(mockSessionsClient.detectIntent(DetectIntentRequest.newBuilder()
                .setSession("value")
                .setQueryParams(QueryParameters.newBuilder()
                        .setTimeZone("value")
                        .setGeoLocation(LatLng.newBuilder()
                                .setLatitude(0.0d)
                                .setLongitude(0.0d)
                                .build())
                        .addSessionEntityTypes(SessionEntityType.newBuilder()
                                .setName("value")
                                .setEntityOverrideMode(
                                        SessionEntityType.EntityOverrideMode.ENTITY_OVERRIDE_MODE_UNSPECIFIED)
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
                                .setSpeakingRate(0.0d)
                                .setPitch(0.0d)
                                .setVolumeGainDb(0.0d)
                                .addEffectsProfileId("value")
                                .setVoice(VoiceSelectionParams.newBuilder()
                                        .setName("value")
                                        .setSsmlGender(SsmlVoiceGender.SSML_VOICE_GENDER_UNSPECIFIED)
                                        .build())
                                .build())
                        .build())
                .build())).thenReturn(detectIntentResponse)

        // Run the test
        myClassUnderTest.tryDetectIntent()

        // Verify the results
    }

    @Test
    void testTryDetectIntent_SessionsClientThrowsApiException() {
        // Setup
        when(mockSessionsClient.detectIntent(DetectIntentRequest.newBuilder()
                .setSession("value")
                .setQueryParams(QueryParameters.newBuilder()
                        .setTimeZone("value")
                        .setGeoLocation(LatLng.newBuilder()
                                .setLatitude(0.0d)
                                .setLongitude(0.0d)
                                .build())
                        .addSessionEntityTypes(SessionEntityType.newBuilder()
                                .setName("value")
                                .setEntityOverrideMode(
                                        SessionEntityType.EntityOverrideMode.ENTITY_OVERRIDE_MODE_UNSPECIFIED)
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
                                .setSpeakingRate(0.0d)
                                .setPitch(0.0d)
                                .setVolumeGainDb(0.0d)
                                .addEffectsProfileId("value")
                                .setVoice(VoiceSelectionParams.newBuilder()
                                        .setName("value")
                                        .setSsmlGender(SsmlVoiceGender.SSML_VOICE_GENDER_UNSPECIFIED)
                                        .build())
                                .build())
                        .build())
                .build())).thenThrow(ApiException.class)

        // Run the test
        assertThrows(ApiException.class, {
            myClassUnderTest.tryDetectIntent()
        })
    }

    @Test
    void testTryDetectIntentCallable() {
        // Setup
        // Configure SessionsClient.detectIntentCallable(...).
        def detectIntentRequestDetectIntentResponseUnaryCallable = new UnaryCallable<DetectIntentRequest, DetectIntentResponse>() {
            @Override
            ApiFuture<DetectIntentResponse> futureCall(final DetectIntentRequest request, final ApiCallContext context) {
                def settableApiFuture = SettableApiFuture.create()
                settableApiFuture.set(DetectIntentResponse.newBuilder().build())
                return settableApiFuture
            }
        }
        when(mockSessionsClient.detectIntentCallable())
                .thenReturn(detectIntentRequestDetectIntentResponseUnaryCallable)

        // Run the test
        myClassUnderTest.tryDetectIntentCallable()

        // Verify the results
    }

    @Test
    void testTryDetectIntentCallable_SessionsClientReturnsFailure() {
        // Setup
        // Configure SessionsClient.detectIntentCallable(...).
        def detectIntentRequestDetectIntentResponseUnaryCallable = new UnaryCallable<DetectIntentRequest, DetectIntentResponse>() {
            @Override
            ApiFuture<DetectIntentResponse> futureCall(final DetectIntentRequest request, final ApiCallContext context) {
                def settableApiFuture = SettableApiFuture.create()
                settableApiFuture.setException(new Exception("Message"))
                return settableApiFuture
            }
        }
        when(mockSessionsClient.detectIntentCallable())
                .thenReturn(detectIntentRequestDetectIntentResponseUnaryCallable)

        // Run the test
        myClassUnderTest.tryDetectIntentCallable()

        // Verify the results
    }

    @Test
    void testTryStreamingDetectIntentCallable() {
        // Setup
        when(mockSessionsClient.streamingDetectIntentCallable()).thenReturn(null)

        // Run the test
        myClassUnderTest.tryStreamingDetectIntentCallable()

        // Verify the results
    }

    @Test
    void testTryMatchIntent() {
        // Setup
        when(mockSessionsClient.matchIntent(MatchIntentRequest.newBuilder()
                .setSession("value")
                .setQueryParams(QueryParameters.newBuilder()
                        .setTimeZone("value")
                        .setGeoLocation(LatLng.newBuilder()
                                .setLatitude(0.0d)
                                .setLongitude(0.0d)
                                .build())
                        .addSessionEntityTypes(SessionEntityType.newBuilder()
                                .setName("value")
                                .setEntityOverrideMode(
                                        SessionEntityType.EntityOverrideMode.ENTITY_OVERRIDE_MODE_UNSPECIFIED)
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
                .build())).thenReturn(MatchIntentResponse.newBuilder().build())

        // Run the test
        myClassUnderTest.tryMatchIntent()

        // Verify the results
    }

    @Test
    void testTryMatchIntent_SessionsClientThrowsApiException() {
        // Setup
        when(mockSessionsClient.matchIntent(MatchIntentRequest.newBuilder()
                .setSession("value")
                .setQueryParams(QueryParameters.newBuilder()
                        .setTimeZone("value")
                        .setGeoLocation(LatLng.newBuilder()
                                .setLatitude(0.0d)
                                .setLongitude(0.0d)
                                .build())
                        .addSessionEntityTypes(SessionEntityType.newBuilder()
                                .setName("value")
                                .setEntityOverrideMode(
                                        SessionEntityType.EntityOverrideMode.ENTITY_OVERRIDE_MODE_UNSPECIFIED)
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
                .build())).thenThrow(ApiException.class)

        // Run the test
        assertThrows(ApiException.class, {
            myClassUnderTest.tryMatchIntent()
        })
    }

    @Test
    void testTryMatchIntentCallable() {
        // Setup
        // Configure SessionsClient.matchIntentCallable(...).
        def matchIntentRequestMatchIntentResponseUnaryCallable = new UnaryCallable<MatchIntentRequest, MatchIntentResponse>() {
            @Override
            ApiFuture<MatchIntentResponse> futureCall(final MatchIntentRequest request, final ApiCallContext context) {
                def settableApiFuture = SettableApiFuture.create()
                settableApiFuture.set(MatchIntentResponse.newBuilder().build())
                return settableApiFuture
            }
        }
        when(mockSessionsClient.matchIntentCallable()).thenReturn(matchIntentRequestMatchIntentResponseUnaryCallable)

        // Run the test
        myClassUnderTest.tryMatchIntentCallable()

        // Verify the results
    }

    @Test
    void testTryMatchIntentCallable_SessionsClientReturnsFailure() {
        // Setup
        // Configure SessionsClient.matchIntentCallable(...).
        def matchIntentRequestMatchIntentResponseUnaryCallable = new UnaryCallable<MatchIntentRequest, MatchIntentResponse>() {
            @Override
            ApiFuture<MatchIntentResponse> futureCall(final MatchIntentRequest request, final ApiCallContext context) {
                def settableApiFuture = SettableApiFuture.create()
                settableApiFuture.setException(new Exception("Message"))
                return settableApiFuture
            }
        }
        when(mockSessionsClient.matchIntentCallable()).thenReturn(matchIntentRequestMatchIntentResponseUnaryCallable)

        // Run the test
        myClassUnderTest.tryMatchIntentCallable()

        // Verify the results
    }

    @Test
    void testTryFulfillIntent() {
        // Setup
        // Configure SessionsClient.fulfillIntent(...).
        def fulfillIntentResponse = FulfillIntentResponse.newBuilder().build()
        when(mockSessionsClient.fulfillIntent(FulfillIntentRequest.newBuilder()
                .setMatchIntentRequest(MatchIntentRequest.newBuilder()
                        .setSession("value")
                        .setQueryParams(QueryParameters.newBuilder()
                                .setTimeZone("value")
                                .setGeoLocation(LatLng.newBuilder()
                                        .setLatitude(0.0d)
                                        .setLongitude(0.0d)
                                        .build())
                                .addSessionEntityTypes(SessionEntityType.newBuilder()
                                        .setName("value")
                                        .setEntityOverrideMode(
                                                SessionEntityType.EntityOverrideMode.ENTITY_OVERRIDE_MODE_UNSPECIFIED)
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
                                .setSpeakingRate(0.0d)
                                .setPitch(0.0d)
                                .setVolumeGainDb(0.0d)
                                .addEffectsProfileId("value")
                                .setVoice(VoiceSelectionParams.newBuilder().build())
                                .build())
                        .build())
                .build())).thenReturn(fulfillIntentResponse)

        // Run the test
        myClassUnderTest.tryFulfillIntent()

        // Verify the results
    }

    @Test
    void testTryFulfillIntent_SessionsClientThrowsApiException() {
        // Setup
        when(mockSessionsClient.fulfillIntent(FulfillIntentRequest.newBuilder()
                .setMatchIntentRequest(MatchIntentRequest.newBuilder()
                        .setSession("value")
                        .setQueryParams(QueryParameters.newBuilder()
                                .setTimeZone("value")
                                .setGeoLocation(LatLng.newBuilder()
                                        .setLatitude(0.0d)
                                        .setLongitude(0.0d)
                                        .build())
                                .addSessionEntityTypes(SessionEntityType.newBuilder()
                                        .setName("value")
                                        .setEntityOverrideMode(
                                                SessionEntityType.EntityOverrideMode.ENTITY_OVERRIDE_MODE_UNSPECIFIED)
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
                                .setSpeakingRate(0.0d)
                                .setPitch(0.0d)
                                .setVolumeGainDb(0.0d)
                                .addEffectsProfileId("value")
                                .setVoice(VoiceSelectionParams.newBuilder().build())
                                .build())
                        .build())
                .build())).thenThrow(ApiException.class)

        // Run the test
        assertThrows(ApiException.class, {
            myClassUnderTest.tryFulfillIntent()
        })
    }

    @Test
    void testTryFulfillIntentCallable() {
        // Setup
        // Configure SessionsClient.fulfillIntentCallable(...).
        def fulfillIntentRequestFulfillIntentResponseUnaryCallable = new UnaryCallable<FulfillIntentRequest, FulfillIntentResponse>() {
            @Override
            ApiFuture<FulfillIntentResponse> futureCall(final FulfillIntentRequest request, final ApiCallContext context) {
                def settableApiFuture = SettableApiFuture.create()
                settableApiFuture.set(FulfillIntentResponse.newBuilder().build())
                return settableApiFuture
            }
        }
        when(mockSessionsClient.fulfillIntentCallable())
                .thenReturn(fulfillIntentRequestFulfillIntentResponseUnaryCallable)

        // Run the test
        myClassUnderTest.tryFulfillIntentCallable()

        // Verify the results
    }

    @Test
    void testTryFulfillIntentCallable_SessionsClientReturnsFailure() {
        // Setup
        // Configure SessionsClient.fulfillIntentCallable(...).
        def fulfillIntentRequestFulfillIntentResponseUnaryCallable = new UnaryCallable<FulfillIntentRequest, FulfillIntentResponse>() {
            @Override
            ApiFuture<FulfillIntentResponse> futureCall(final FulfillIntentRequest request, final ApiCallContext context) {
                def settableApiFuture = SettableApiFuture.create()
                settableApiFuture.setException(new Exception("Message"))
                return settableApiFuture
            }
        }
        when(mockSessionsClient.fulfillIntentCallable())
                .thenReturn(fulfillIntentRequestFulfillIntentResponseUnaryCallable)

        // Run the test
        myClassUnderTest.tryFulfillIntentCallable()

        // Verify the results
    }

    @Test
    void testTryClose() {
        // Setup
        // Run the test
        myClassUnderTest.tryClose()

        // Verify the results
        verify(mockSessionsClient).close()
    }

    @Test
    void testTryShutdown() {
        // Setup
        // Run the test
        myClassUnderTest.tryShutdown()

        // Verify the results
        verify(mockSessionsClient).shutdown()
    }

    @Test
    void testTryIsShutdown() {
        // Setup
        when(mockSessionsClient.isShutdown()).thenReturn(false)

        // Run the test
        myClassUnderTest.tryIsShutdown()

        // Verify the results
    }

    @Test
    void testTryIsTerminated() {
        // Setup
        when(mockSessionsClient.isTerminated()).thenReturn(false)

        // Run the test
        myClassUnderTest.tryIsTerminated()

        // Verify the results
    }

    @Test
    void testTryShutdownNow() {
        // Setup
        // Run the test
        myClassUnderTest.tryShutdownNow()

        // Verify the results
        verify(mockSessionsClient).shutdownNow()
    }

    @Test
    void testTryAwaitTermination() {
        // Setup
        when(mockSessionsClient.awaitTermination(0L, TimeUnit.MILLISECONDS)).thenReturn(false)

        // Run the test
        myClassUnderTest.tryAwaitTermination()

        // Verify the results
    }

    @Test
    void testTryAwaitTermination_SessionsClientThrowsInterruptedException() {
        // Setup
        when(mockSessionsClient.awaitTermination(0L, TimeUnit.MILLISECONDS)).thenThrow(InterruptedException.class)

        // Run the test
        assertThrows(InterruptedException.class, {
            myClassUnderTest.tryAwaitTermination()
        })
    }

    @Test
    void testTryCreate1() {
        // Setup
        // Run the test
        myClassUnderTest.tryCreate1()

        // Verify the results
    }

    @Test
    void testTryCreate1_ThrowsException() {
        // Setup
        // Run the test
        assertThrows(Exception.class, {
            myClassUnderTest.tryCreate1()
        })
    }

    @Test
    void testTryCreate2() {
        // Setup
        // Run the test
        myClassUnderTest.tryCreate2()

        // Verify the results
    }

    @Test
    void testTryCreate2_ThrowsException() {
        // Setup
        // Run the test
        assertThrows(Exception.class, {
            myClassUnderTest.tryCreate2()
        })
    }

    @Test
    void testTryCreate3() {
        // Setup
        // Run the test
        myClassUnderTest.tryCreate3()

        // Verify the results
    }
}
