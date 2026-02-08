package com.myapp;

import com.google.cloud.vision.v1.*;
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
    void testDetectDocumentTextGcs() {
        // Setup
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
                        .setSource(ImageSource.newBuilder()
                                .setGcsImageUri("gcsPath")
                                .build())
                        .build())
                .addFeatures(Feature.newBuilder()
                        .setType(Feature.Type.DOCUMENT_TEXT_DETECTION)
                        .build())
                .build()))).thenReturn(batchAnnotateImagesResponse);

        // Run the test
        myClassUnderTest.detectDocumentTextGcs("gcsPath");

        // Verify the results
    }
}
