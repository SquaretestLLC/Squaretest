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
import com.google.cloud.language.v1.*;
import com.google.cloud.language.v1.stub.LanguageServiceStub;

import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

public class MyClass {

    private LanguageServiceClient languageServiceClient;

    public MyClass(final LanguageServiceClient languageServiceClient) {
        this.languageServiceClient = languageServiceClient;
    }

    public void tryAnalyzeSentiment1() throws Exception {
        final Document document = Document.parseFrom(ByteBuffer.wrap("content".getBytes()));
        final AnalyzeSentimentResponse result = languageServiceClient.analyzeSentiment(document, EncodingType.NONE);
    }

    public void tryAnalyzeSentiment2() throws Exception {
        final Document document = Document.parseFrom(ByteBuffer.wrap("content".getBytes()));
        final AnalyzeSentimentResponse result = languageServiceClient.analyzeSentiment(document);
    }

    public void tryAnalyzeSentiment3() throws Exception {
        final AnalyzeSentimentRequest request = AnalyzeSentimentRequest.parseFrom(ByteBuffer.wrap("content".getBytes()));
        final AnalyzeSentimentResponse result = languageServiceClient.analyzeSentiment(request);
    }

    public void tryAnalyzeSentimentCallable() {
        final UnaryCallable<AnalyzeSentimentRequest, AnalyzeSentimentResponse> result = languageServiceClient.analyzeSentimentCallable();
    }

    public void tryAnalyzeEntities1() throws Exception {
        final Document document = Document.parseFrom(ByteBuffer.wrap("content".getBytes()));
        final AnalyzeEntitiesResponse result = languageServiceClient.analyzeEntities(document, EncodingType.NONE);
    }

    public void tryAnalyzeEntities2() throws Exception {
        final Document document = Document.parseFrom(ByteBuffer.wrap("content".getBytes()));
        final AnalyzeEntitiesResponse result = languageServiceClient.analyzeEntities(document);
    }

    public void tryAnalyzeEntities3() throws Exception {
        final AnalyzeEntitiesRequest request = AnalyzeEntitiesRequest.parseFrom(ByteBuffer.wrap("content".getBytes()));
        final AnalyzeEntitiesResponse result = languageServiceClient.analyzeEntities(request);
    }

    public void tryAnalyzeEntitiesCallable() {
        final UnaryCallable<AnalyzeEntitiesRequest, AnalyzeEntitiesResponse> result = languageServiceClient.analyzeEntitiesCallable();
    }

    public void tryAnalyzeEntitySentiment1() throws Exception {
        final Document document = Document.parseFrom(ByteBuffer.wrap("content".getBytes()));
        final AnalyzeEntitySentimentResponse result = languageServiceClient.analyzeEntitySentiment(document, EncodingType.NONE);
    }

    public void tryAnalyzeEntitySentiment2() throws Exception {
        final Document document = Document.parseFrom(ByteBuffer.wrap("content".getBytes()));
        final AnalyzeEntitySentimentResponse result = languageServiceClient.analyzeEntitySentiment(document);
    }

    public void tryAnalyzeEntitySentiment3() throws Exception {
        final AnalyzeEntitySentimentRequest request = AnalyzeEntitySentimentRequest.parseFrom(ByteBuffer.wrap("content".getBytes()));
        final AnalyzeEntitySentimentResponse result = languageServiceClient.analyzeEntitySentiment(request);
    }

    public void tryAnalyzeEntitySentimentCallable() {
        final UnaryCallable<AnalyzeEntitySentimentRequest, AnalyzeEntitySentimentResponse> result = languageServiceClient.analyzeEntitySentimentCallable();
    }

    public void tryAnalyzeSyntax1() throws Exception {
        final Document document = Document.parseFrom(ByteBuffer.wrap("content".getBytes()));
        final AnalyzeSyntaxResponse result = languageServiceClient.analyzeSyntax(document, EncodingType.NONE);
    }

    public void tryAnalyzeSyntax2() throws Exception {
        final Document document = Document.parseFrom(ByteBuffer.wrap("content".getBytes()));
        final AnalyzeSyntaxResponse result = languageServiceClient.analyzeSyntax(document);
    }

    public void tryAnalyzeSyntax3() throws Exception {
        final AnalyzeSyntaxRequest request = AnalyzeSyntaxRequest.parseFrom(ByteBuffer.wrap("content".getBytes()));
        final AnalyzeSyntaxResponse result = languageServiceClient.analyzeSyntax(request);
    }

    public void tryAnalyzeSyntaxCallable() {
        final UnaryCallable<AnalyzeSyntaxRequest, AnalyzeSyntaxResponse> result = languageServiceClient.analyzeSyntaxCallable();
    }

    public void tryClassifyText1() throws Exception {
        final Document document = Document.parseFrom(ByteBuffer.wrap("content".getBytes()));
        final ClassifyTextResponse result = languageServiceClient.classifyText(document);
    }

    public void tryClassifyText2() throws Exception {
        final ClassifyTextRequest request = ClassifyTextRequest.parseFrom(ByteBuffer.wrap("content".getBytes()));
        final ClassifyTextResponse result = languageServiceClient.classifyText(request);
    }

    public void tryClassifyTextCallable() {
        final UnaryCallable<ClassifyTextRequest, ClassifyTextResponse> result = languageServiceClient.classifyTextCallable();
    }

    public void tryAnnotateText1() throws Exception {
        final Document document = Document.parseFrom(ByteBuffer.wrap("content".getBytes()));
        final AnnotateTextRequest.Features features = AnnotateTextRequest.Features.parseFrom(ByteBuffer.wrap("content".getBytes()));
        final AnnotateTextResponse result = languageServiceClient.annotateText(document, features, EncodingType.NONE);
    }

    public void tryAnnotateText2() throws Exception {
        final Document document = Document.parseFrom(ByteBuffer.wrap("content".getBytes()));
        final AnnotateTextRequest.Features features = AnnotateTextRequest.Features.parseFrom(ByteBuffer.wrap("content".getBytes()));
        final AnnotateTextResponse result = languageServiceClient.annotateText(document, features);
    }

    public void tryAnnotateText3() throws Exception {
        final AnnotateTextRequest request = AnnotateTextRequest.parseFrom(ByteBuffer.wrap("content".getBytes()));
        final AnnotateTextResponse result = languageServiceClient.annotateText(request);
    }

    public void tryAnnotateTextCallable() {
        final UnaryCallable<AnnotateTextRequest, AnnotateTextResponse> result = languageServiceClient.annotateTextCallable();
    }

    public void tryClose() {
        languageServiceClient.close();
    }

    public void tryShutdown() {
        languageServiceClient.shutdown();
    }

    public void tryIsShutdown() {
        final boolean result = languageServiceClient.isShutdown();
    }

    public void tryIsTerminated() {
        final boolean result = languageServiceClient.isTerminated();
    }

    public void tryShutdownNow() {
        languageServiceClient.shutdownNow();
    }

    public void tryAwaitTermination() throws Exception {
        final boolean result = languageServiceClient.awaitTermination(0L, TimeUnit.MILLISECONDS);
    }

    public void tryCreate1() throws Exception {
        final LanguageServiceClient result = LanguageServiceClient.create();
    }

    public void tryCreate2() throws Exception {
        final LanguageServiceSettings settings = LanguageServiceSettings.create(null);
        final LanguageServiceClient result = LanguageServiceClient.create(settings);
    }

    public void tryCreate3() {
        final LanguageServiceStub stub = null;
        final LanguageServiceClient result = LanguageServiceClient.create(stub);
    }
}
