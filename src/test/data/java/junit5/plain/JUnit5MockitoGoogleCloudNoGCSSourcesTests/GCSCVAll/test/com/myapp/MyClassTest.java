package com.myapp;

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
        // Configure ImageAnnotatorClient.batchAnnotateImages(...).
        final BatchAnnotateImagesResponse batchAnnotateImagesResponse = BatchAnnotateImagesResponse.newBuilder()
                .addResponses(AnnotateImageResponse.newBuilder()
                        .addLocalizedObjectAnnotations(LocalizedObjectAnnotation.newBuilder()
                                .setName("name")
                                .setScore(0.0f)
                                .setBoundingPoly(BoundingPoly.newBuilder()
                                        .addNormalizedVertices(NormalizedVertex.newBuilder()
                                                .setX(0.0f)
                                                .setY(0.0f)
                                                .build())
                                        .build())
                                .build())
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
                        .setSource(ImageSource.newBuilder()
                                .setGcsImageUri("gcsPath")
                                .build())
                        .build())
                .addFeatures(Feature.newBuilder()
                        .setType(Feature.Type.TYPE_UNSPECIFIED)
                        .build())
                .build()))).thenReturn(batchAnnotateImagesResponse);

        // Run the test
        myClassUnderTest.detectDocumentText("filePath");

        // Verify the results
    }

    @Test
    void testDetectDocumentTextGcs() throws Exception {
        // Setup
        // Configure ImageAnnotatorClient.batchAnnotateImages(...).
        final BatchAnnotateImagesResponse batchAnnotateImagesResponse = BatchAnnotateImagesResponse.newBuilder()
                .addResponses(AnnotateImageResponse.newBuilder()
                        .addLocalizedObjectAnnotations(LocalizedObjectAnnotation.newBuilder()
                                .setName("name")
                                .setScore(0.0f)
                                .setBoundingPoly(BoundingPoly.newBuilder()
                                        .addNormalizedVertices(NormalizedVertex.newBuilder()
                                                .setX(0.0f)
                                                .setY(0.0f)
                                                .build())
                                        .build())
                                .build())
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
                        .setSource(ImageSource.newBuilder()
                                .setGcsImageUri("gcsPath")
                                .build())
                        .build())
                .addFeatures(Feature.newBuilder()
                        .setType(Feature.Type.TYPE_UNSPECIFIED)
                        .build())
                .build()))).thenReturn(batchAnnotateImagesResponse);

        // Run the test
        myClassUnderTest.detectDocumentTextGcs("gcsPath");

        // Verify the results
    }

    @Test
    void testDetectDocumentsGcs() throws Exception {
        // Setup
        when(mockClient.asyncBatchAnnotateFilesAsync(Arrays.asList(AsyncAnnotateFileRequest.newBuilder()
                .setInputConfig(InputConfig.newBuilder()
                        .setGcsSource(GcsSource.newBuilder()
                                .setUri("gcsSourcePath")
                                .build())
                        .setMimeType("application/pdf")
                        .build())
                .addFeatures(Feature.newBuilder()
                        .setType(Feature.Type.TYPE_UNSPECIFIED)
                        .build())
                .setOutputConfig(OutputConfig.newBuilder()
                        .setGcsDestination(GcsDestination.newBuilder()
                                .setUri("gcsDestinationPath")
                                .build())
                        .setBatchSize(2)
                        .build())
                .build()))).thenReturn(null);

        // Run the test
        myClassUnderTest.detectDocumentsGcs("gcsSourcePath", "gcsDestinationPath");

        // Verify the results
    }

    @Test
    void testDetectLocalizedObjects() throws Exception {
        // Setup
        // Configure ImageAnnotatorClient.batchAnnotateImages(...).
        final BatchAnnotateImagesResponse batchAnnotateImagesResponse = BatchAnnotateImagesResponse.newBuilder()
                .addResponses(AnnotateImageResponse.newBuilder()
                        .addLocalizedObjectAnnotations(LocalizedObjectAnnotation.newBuilder()
                                .setName("name")
                                .setScore(0.0f)
                                .setBoundingPoly(BoundingPoly.newBuilder()
                                        .addNormalizedVertices(NormalizedVertex.newBuilder()
                                                .setX(0.0f)
                                                .setY(0.0f)
                                                .build())
                                        .build())
                                .build())
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
                        .setSource(ImageSource.newBuilder()
                                .setGcsImageUri("gcsPath")
                                .build())
                        .build())
                .addFeatures(Feature.newBuilder()
                        .setType(Feature.Type.TYPE_UNSPECIFIED)
                        .build())
                .build()))).thenReturn(batchAnnotateImagesResponse);

        // Run the test
        myClassUnderTest.detectLocalizedObjects("filePath");

        // Verify the results
    }

    @Test
    void testDetectLocalizedObjectsGcs() throws Exception {
        // Setup
        // Configure ImageAnnotatorClient.batchAnnotateImages(...).
        final BatchAnnotateImagesResponse batchAnnotateImagesResponse = BatchAnnotateImagesResponse.newBuilder()
                .addResponses(AnnotateImageResponse.newBuilder()
                        .addLocalizedObjectAnnotations(LocalizedObjectAnnotation.newBuilder()
                                .setName("name")
                                .setScore(0.0f)
                                .setBoundingPoly(BoundingPoly.newBuilder()
                                        .addNormalizedVertices(NormalizedVertex.newBuilder()
                                                .setX(0.0f)
                                                .setY(0.0f)
                                                .build())
                                        .build())
                                .build())
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
                        .setSource(ImageSource.newBuilder()
                                .setGcsImageUri("gcsPath")
                                .build())
                        .build())
                .addFeatures(Feature.newBuilder()
                        .setType(Feature.Type.TYPE_UNSPECIFIED)
                        .build())
                .build()))).thenReturn(batchAnnotateImagesResponse);

        // Run the test
        myClassUnderTest.detectLocalizedObjectsGcs("gcsPath");

        // Verify the results
    }
}
