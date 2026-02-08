package com.myapp

import com.google.api.core.ApiFuture
import com.google.api.core.SettableApiFuture
import com.google.api.gax.rpc.ApiCallContext
import com.google.api.gax.rpc.ApiException
import com.google.api.gax.rpc.UnaryCallable
import com.google.cloud.texttospeech.v1.*
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
    private TextToSpeechClient mockTextToSpeechClient

    private MyClass myClassUnderTest

    @BeforeEach
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass(mockTextToSpeechClient)
    }

    @Test
    void testTryListVoices1() {
        // Setup
        when(mockTextToSpeechClient.listVoices("languageCode")).thenReturn(ListVoicesResponse.newBuilder().build())

        // Run the test
        myClassUnderTest.tryListVoices1()

        // Verify the results
    }

    @Test
    void testTryListVoices1_TextToSpeechClientThrowsApiException() {
        // Setup
        when(mockTextToSpeechClient.listVoices("languageCode")).thenThrow(ApiException.class)

        // Run the test
        assertThrows(ApiException.class, {
            myClassUnderTest.tryListVoices1()
        })
    }

    @Test
    void testTryListVoices2() {
        // Setup
        when(mockTextToSpeechClient.listVoices(ListVoicesRequest.newBuilder()
                .setLanguageCode("value")
                .build())).thenReturn(ListVoicesResponse.newBuilder().build())

        // Run the test
        myClassUnderTest.tryListVoices2()

        // Verify the results
    }

    @Test
    void testTryListVoices2_TextToSpeechClientThrowsApiException() {
        // Setup
        when(mockTextToSpeechClient.listVoices(ListVoicesRequest.newBuilder()
                .setLanguageCode("value")
                .build())).thenThrow(ApiException.class)

        // Run the test
        assertThrows(ApiException.class, {
            myClassUnderTest.tryListVoices2()
        })
    }

    @Test
    void testTryListVoicesCallable() {
        // Setup
        // Configure TextToSpeechClient.listVoicesCallable(...).
        def listVoicesRequestListVoicesResponseUnaryCallable = new UnaryCallable<ListVoicesRequest, ListVoicesResponse>() {
            @Override
            ApiFuture<ListVoicesResponse> futureCall(final ListVoicesRequest request, final ApiCallContext context) {
                def settableApiFuture = SettableApiFuture.create()
                settableApiFuture.set(ListVoicesResponse.newBuilder().build())
                return settableApiFuture
            }
        }
        when(mockTextToSpeechClient.listVoicesCallable()).thenReturn(listVoicesRequestListVoicesResponseUnaryCallable)

        // Run the test
        myClassUnderTest.tryListVoicesCallable()

        // Verify the results
    }

    @Test
    void testTryListVoicesCallable_TextToSpeechClientReturnsFailure() {
        // Setup
        // Configure TextToSpeechClient.listVoicesCallable(...).
        def listVoicesRequestListVoicesResponseUnaryCallable = new UnaryCallable<ListVoicesRequest, ListVoicesResponse>() {
            @Override
            ApiFuture<ListVoicesResponse> futureCall(final ListVoicesRequest request, final ApiCallContext context) {
                def settableApiFuture = SettableApiFuture.create()
                settableApiFuture.setException(new Exception("Message"))
                return settableApiFuture
            }
        }
        when(mockTextToSpeechClient.listVoicesCallable()).thenReturn(listVoicesRequestListVoicesResponseUnaryCallable)

        // Run the test
        myClassUnderTest.tryListVoicesCallable()

        // Verify the results
    }

    @Test
    void testTrySynthesizeSpeech1() {
        // Setup
        // Configure TextToSpeechClient.synthesizeSpeech(...).
        def synthesizeSpeechResponse = SynthesizeSpeechResponse.newBuilder().build()
        when(mockTextToSpeechClient.synthesizeSpeech(SynthesisInput.newBuilder()
                .setText("value")
                .setSsml("value")
                .build(), VoiceSelectionParams.newBuilder()
                .setLanguageCode("value")
                .setName("value")
                .setSsmlGender(SsmlVoiceGender.SSML_VOICE_GENDER_UNSPECIFIED)
                .build(), AudioConfig.newBuilder()
                .setAudioEncoding(AudioEncoding.AUDIO_ENCODING_UNSPECIFIED)
                .setSpeakingRate(0.0d)
                .setPitch(0.0d)
                .setVolumeGainDb(0.0d)
                .setSampleRateHertz(0)
                .addEffectsProfileId("value")
                .build())).thenReturn(synthesizeSpeechResponse)

        // Run the test
        myClassUnderTest.trySynthesizeSpeech1()

        // Verify the results
    }

    @Test
    void testTrySynthesizeSpeech1_TextToSpeechClientThrowsApiException() {
        // Setup
        when(mockTextToSpeechClient.synthesizeSpeech(SynthesisInput.newBuilder()
                .setText("value")
                .setSsml("value")
                .build(), VoiceSelectionParams.newBuilder()
                .setLanguageCode("value")
                .setName("value")
                .setSsmlGender(SsmlVoiceGender.SSML_VOICE_GENDER_UNSPECIFIED)
                .build(), AudioConfig.newBuilder()
                .setAudioEncoding(AudioEncoding.AUDIO_ENCODING_UNSPECIFIED)
                .setSpeakingRate(0.0d)
                .setPitch(0.0d)
                .setVolumeGainDb(0.0d)
                .setSampleRateHertz(0)
                .addEffectsProfileId("value")
                .build())).thenThrow(ApiException.class)

        // Run the test
        assertThrows(ApiException.class, {
            myClassUnderTest.trySynthesizeSpeech1()
        })
    }

    @Test
    void testTrySynthesizeSpeech2() {
        // Setup
        // Configure TextToSpeechClient.synthesizeSpeech(...).
        def synthesizeSpeechResponse = SynthesizeSpeechResponse.newBuilder().build()
        when(mockTextToSpeechClient.synthesizeSpeech(SynthesizeSpeechRequest.newBuilder()
                .setInput(SynthesisInput.newBuilder()
                        .setText("value")
                        .setSsml("value")
                        .build())
                .setVoice(VoiceSelectionParams.newBuilder()
                        .setLanguageCode("value")
                        .setName("value")
                        .setSsmlGender(SsmlVoiceGender.SSML_VOICE_GENDER_UNSPECIFIED)
                        .build())
                .setAudioConfig(AudioConfig.newBuilder()
                        .setAudioEncoding(AudioEncoding.AUDIO_ENCODING_UNSPECIFIED)
                        .setSpeakingRate(0.0d)
                        .setPitch(0.0d)
                        .setVolumeGainDb(0.0d)
                        .setSampleRateHertz(0)
                        .addEffectsProfileId("value")
                        .build())
                .build())).thenReturn(synthesizeSpeechResponse)

        // Run the test
        myClassUnderTest.trySynthesizeSpeech2()

        // Verify the results
    }

    @Test
    void testTrySynthesizeSpeech2_TextToSpeechClientThrowsApiException() {
        // Setup
        when(mockTextToSpeechClient.synthesizeSpeech(SynthesizeSpeechRequest.newBuilder()
                .setInput(SynthesisInput.newBuilder()
                        .setText("value")
                        .setSsml("value")
                        .build())
                .setVoice(VoiceSelectionParams.newBuilder()
                        .setLanguageCode("value")
                        .setName("value")
                        .setSsmlGender(SsmlVoiceGender.SSML_VOICE_GENDER_UNSPECIFIED)
                        .build())
                .setAudioConfig(AudioConfig.newBuilder()
                        .setAudioEncoding(AudioEncoding.AUDIO_ENCODING_UNSPECIFIED)
                        .setSpeakingRate(0.0d)
                        .setPitch(0.0d)
                        .setVolumeGainDb(0.0d)
                        .setSampleRateHertz(0)
                        .addEffectsProfileId("value")
                        .build())
                .build())).thenThrow(ApiException.class)

        // Run the test
        assertThrows(ApiException.class, {
            myClassUnderTest.trySynthesizeSpeech2()
        })
    }

    @Test
    void testTrySynthesizeSpeechCallable() {
        // Setup
        // Configure TextToSpeechClient.synthesizeSpeechCallable(...).
        def synthesizeSpeechRequestSynthesizeSpeechResponseUnaryCallable = new UnaryCallable<SynthesizeSpeechRequest, SynthesizeSpeechResponse>() {
            @Override
            ApiFuture<SynthesizeSpeechResponse> futureCall(final SynthesizeSpeechRequest request, final ApiCallContext context) {
                def settableApiFuture = SettableApiFuture.create()
                settableApiFuture.set(SynthesizeSpeechResponse.newBuilder().build())
                return settableApiFuture
            }
        }
        when(mockTextToSpeechClient.synthesizeSpeechCallable())
                .thenReturn(synthesizeSpeechRequestSynthesizeSpeechResponseUnaryCallable)

        // Run the test
        myClassUnderTest.trySynthesizeSpeechCallable()

        // Verify the results
    }

    @Test
    void testTrySynthesizeSpeechCallable_TextToSpeechClientReturnsFailure() {
        // Setup
        // Configure TextToSpeechClient.synthesizeSpeechCallable(...).
        def synthesizeSpeechRequestSynthesizeSpeechResponseUnaryCallable = new UnaryCallable<SynthesizeSpeechRequest, SynthesizeSpeechResponse>() {
            @Override
            ApiFuture<SynthesizeSpeechResponse> futureCall(final SynthesizeSpeechRequest request, final ApiCallContext context) {
                def settableApiFuture = SettableApiFuture.create()
                settableApiFuture.setException(new Exception("Message"))
                return settableApiFuture
            }
        }
        when(mockTextToSpeechClient.synthesizeSpeechCallable())
                .thenReturn(synthesizeSpeechRequestSynthesizeSpeechResponseUnaryCallable)

        // Run the test
        myClassUnderTest.trySynthesizeSpeechCallable()

        // Verify the results
    }

    @Test
    void testTryClose() {
        // Setup
        // Run the test
        myClassUnderTest.tryClose()

        // Verify the results
        verify(mockTextToSpeechClient).close()
    }

    @Test
    void testTryShutdown() {
        // Setup
        // Run the test
        myClassUnderTest.tryShutdown()

        // Verify the results
        verify(mockTextToSpeechClient).shutdown()
    }

    @Test
    void testTryIsShutdown() {
        // Setup
        when(mockTextToSpeechClient.isShutdown()).thenReturn(false)

        // Run the test
        myClassUnderTest.tryIsShutdown()

        // Verify the results
    }

    @Test
    void testTryIsTerminated() {
        // Setup
        when(mockTextToSpeechClient.isTerminated()).thenReturn(false)

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
        verify(mockTextToSpeechClient).shutdownNow()
    }

    @Test
    void testTryAwaitTermination() {
        // Setup
        when(mockTextToSpeechClient.awaitTermination(0L, TimeUnit.MILLISECONDS)).thenReturn(false)

        // Run the test
        myClassUnderTest.tryAwaitTermination()

        // Verify the results
    }

    @Test
    void testTryAwaitTermination_TextToSpeechClientThrowsInterruptedException() {
        // Setup
        when(mockTextToSpeechClient.awaitTermination(0L, TimeUnit.MILLISECONDS)).thenThrow(InterruptedException.class)

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

    @Test
    void testTryCreate3_ThrowsException() {
        // Setup
        // Run the test
        assertThrows(Exception.class, {
            myClassUnderTest.tryCreate3()
        })
    }
}
