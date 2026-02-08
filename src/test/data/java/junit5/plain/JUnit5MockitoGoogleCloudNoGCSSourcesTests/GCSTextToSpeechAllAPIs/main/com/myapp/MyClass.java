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

import com.google.api.gax.rpc.UnaryCallable;
import com.google.cloud.texttospeech.v1.*;
import com.google.cloud.texttospeech.v1.stub.TextToSpeechStub;

import java.util.concurrent.TimeUnit;

public class MyClass {

    private TextToSpeechSettings settings;

    private TextToSpeechClient textToSpeechClient;

    public MyClass(final TextToSpeechClient textToSpeechClient) {
        this.textToSpeechClient = textToSpeechClient;
    }

    public void tryListVoices1() {
        final ListVoicesResponse result = textToSpeechClient.listVoices("languageCode");
    }

    public void tryListVoices2() {
        final ListVoicesRequest request = ListVoicesRequest.newBuilder()
                .setLanguageCode("value")
                .build();
        final ListVoicesResponse result = textToSpeechClient.listVoices(request);
    }

    public void tryListVoicesCallable() {
        final UnaryCallable<ListVoicesRequest, ListVoicesResponse> result = textToSpeechClient.listVoicesCallable();
    }

    public void trySynthesizeSpeech1() {
        final SynthesisInput input = SynthesisInput.newBuilder()
                .setText("value")
                .setSsml("value")
                .build();
        final VoiceSelectionParams voice = VoiceSelectionParams.newBuilder()
                .setLanguageCode("value")
                .setName("value")
                .setSsmlGender(SsmlVoiceGender.SSML_VOICE_GENDER_UNSPECIFIED)
                .build();
        final AudioConfig audioConfig = AudioConfig.newBuilder()
                .setAudioEncoding(AudioEncoding.AUDIO_ENCODING_UNSPECIFIED)
                .setSpeakingRate(0.0)
                .setPitch(0.0)
                .setVolumeGainDb(0.0)
                .setSampleRateHertz(0)
                .addEffectsProfileId("value")
                .build();
        final SynthesizeSpeechResponse result = textToSpeechClient.synthesizeSpeech(input, voice, audioConfig);
    }

    public void trySynthesizeSpeech2() {
        final SynthesizeSpeechRequest request = SynthesizeSpeechRequest.newBuilder()
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
                        .setSpeakingRate(0.0)
                        .setPitch(0.0)
                        .setVolumeGainDb(0.0)
                        .setSampleRateHertz(0)
                        .addEffectsProfileId("value")
                        .build())
                .build();
        final SynthesizeSpeechResponse result = textToSpeechClient.synthesizeSpeech(request);
    }

    public void trySynthesizeSpeechCallable() {
        final UnaryCallable<SynthesizeSpeechRequest, SynthesizeSpeechResponse> result = textToSpeechClient.synthesizeSpeechCallable();
    }

    public void tryClose() throws Exception {
        textToSpeechClient.close();
    }

    public void tryShutdown() {
        textToSpeechClient.shutdown();
    }

    public void tryIsShutdown() {
        final boolean result = textToSpeechClient.isShutdown();
    }

    public void tryIsTerminated() {
        final boolean result = textToSpeechClient.isTerminated();
    }

    public void tryShutdownNow() {
        textToSpeechClient.shutdownNow();
    }

    public void tryAwaitTermination() throws Exception {
        final boolean result = textToSpeechClient.awaitTermination(0L, TimeUnit.MILLISECONDS);
    }

    public void tryCreate1() throws Exception {
        final TextToSpeechClient result = TextToSpeechClient.create();
    }

    public void tryCreate2() throws Exception {
        final TextToSpeechSettings settings = TextToSpeechSettings.create(null);
        final TextToSpeechClient result = TextToSpeechClient.create(settings);
    }

    public void tryCreate3() throws Exception {
        final TextToSpeechStub stub = null;
        final TextToSpeechClient result = TextToSpeechClient.create(stub);
    }
}
