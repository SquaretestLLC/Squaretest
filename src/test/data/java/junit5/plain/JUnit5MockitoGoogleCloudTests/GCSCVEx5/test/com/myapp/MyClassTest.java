package com.myapp;

import com.google.api.gax.rpc.ApiException;
import com.google.cloud.vision.v1.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;
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
    void testDetectLocalizedObjectsGcs() {
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
                        .build())
                .build();
        when(mockClient.batchAnnotateImages(Arrays.asList(AnnotateImageRequest.newBuilder()
                .setImage(Image.newBuilder()
                        .setSource(ImageSource.newBuilder()
                                .setGcsImageUri("gcsPath")
                                .build())
                        .build())
                .addFeatures(Feature.newBuilder()
                        .setType(Feature.Type.OBJECT_LOCALIZATION)
                        .build())
                .build()))).thenReturn(batchAnnotateImagesResponse);

        // Run the test
        myClassUnderTest.detectLocalizedObjectsGcs("gcsPath");

        // Verify the results
    }

    @Test
    void testDetectLocalizedObjectsGcs_ImageAnnotatorClientThrowsApiException() {
        // Setup
        when(mockClient.batchAnnotateImages(Arrays.asList(AnnotateImageRequest.newBuilder()
                .setImage(Image.newBuilder()
                        .setSource(ImageSource.newBuilder()
                                .setGcsImageUri("gcsPath")
                                .build())
                        .build())
                .addFeatures(Feature.newBuilder()
                        .setType(Feature.Type.OBJECT_LOCALIZATION)
                        .build())
                .build()))).thenThrow(ApiException.class);

        // Run the test
        assertThrows(ApiException.class, () -> myClassUnderTest.detectLocalizedObjectsGcs("gcsPath"));
    }
}
