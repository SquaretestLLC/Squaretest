package com.myapp;

import com.google.cloud.language.v1.AnalyzeSentimentResponse;
import com.google.cloud.vision.v1.*;
import com.google.protobuf.ByteString;
import com.google.rpc.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private ImageAnnotatorClient mockClient;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockClient);
    }

    @Test
    void testDetectDocumentText() throws Exception {
        // Setup
        final AnalyzeSentimentResponse analyzeSentimentResponse = AnalyzeSentimentResponse.newBuilder()
                .setLanguage("language")
                .build();

        // Configure ImageAnnotatorClient.batchAnnotateImages(...).
        final BatchAnnotateImagesResponse batchAnnotateImagesResponse = BatchAnnotateImagesResponse.newBuilder()
                .addResponses(AnnotateImageResponse.newBuilder()
                        .setFullTextAnnotation(TextAnnotation.newBuilder()
                                .addPages(Page.newBuilder()
                                        .addBlocks(Block.newBuilder()
                                                .addParagraphs(Paragraph.newBuilder()
                                                        .addWords(Word.newBuilder()
                                                                .addSymbols(Symbol.newBuilder()
                                                                        .setText("text")
                                                                        .setConfidence(0.0f)
                                                                        .build())
                                                                .setConfidence(0.0f)
                                                                .build())
                                                        .setConfidence(0.0f)
                                                        .build())
                                                .build())
                                        .build())
                                .setText("text")
                                .build())
                        .setError(Status.newBuilder()
                                .setMessage("message")
                                .build())
                        .build())
                .build();
        when(mockClient.batchAnnotateImages(Arrays.asList(AnnotateImageRequest.newBuilder()
                .setImage(Image.newBuilder()
                        .setContent(ByteString.copyFrom("content".getBytes()))
                        .build())
                .addFeatures(Feature.newBuilder()
                        .setType(Feature.Type.DOCUMENT_TEXT_DETECTION)
                        .setModel("language")
                        .build())
                .build()))).thenReturn(batchAnnotateImagesResponse);

        // Run the test
        myClassUnderTest.detectDocumentText("filePath", analyzeSentimentResponse);

        // Verify the results
    }
}
