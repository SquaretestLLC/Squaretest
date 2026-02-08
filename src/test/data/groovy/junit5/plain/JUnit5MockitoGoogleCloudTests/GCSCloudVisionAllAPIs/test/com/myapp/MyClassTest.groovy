package com.myapp

import com.google.api.core.ApiFuture
import com.google.api.core.SettableApiFuture
import com.google.api.gax.rpc.ApiCallContext
import com.google.api.gax.rpc.ApiException
import com.google.api.gax.rpc.UnaryCallable
import com.google.cloud.vision.v1.*
import com.google.longrunning.Operation
import com.google.protobuf.ByteString
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
    private ImageAnnotatorClient mockImageAnnotatorClient

    private MyClass myClassUnderTest

    @BeforeEach
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass(mockImageAnnotatorClient)
    }

    @Test
    void testTryBatchAnnotateFiles1() {
        // Setup
        // Configure ImageAnnotatorClient.batchAnnotateFiles(...).
        def batchAnnotateFilesResponse = BatchAnnotateFilesResponse.newBuilder().build()
        when(mockImageAnnotatorClient.batchAnnotateFiles([AnnotateFileRequest.newBuilder()
                                                                  .setInputConfig(InputConfig.newBuilder()
                                                                          .setGcsSource(GcsSource.newBuilder()
                                                                                  .setUri("value")
                                                                                  .build())
                                                                          .setContent(ByteString.copyFrom(
                                                                                  "content".getBytes()))
                                                                          .setMimeType("value")
                                                                          .build())
                                                                  .addFeatures(Feature.newBuilder()
                                                                          .setType(Feature.Type.TYPE_UNSPECIFIED)
                                                                          .setMaxResults(0)
                                                                          .setModel("value")
                                                                          .build())
                                                                  .setImageContext(ImageContext.newBuilder()
                                                                          .setLatLongRect(LatLongRect.newBuilder()
                                                                                  .setMinLatLng(LatLng.newBuilder()
                                                                                          .setLatitude(0.0d)
                                                                                          .setLongitude(0.0d)
                                                                                          .build())
                                                                                  .setMaxLatLng(LatLng.newBuilder()
                                                                                          .setLatitude(0.0d)
                                                                                          .setLongitude(0.0d)
                                                                                          .build())
                                                                                  .build())
                                                                          .addLanguageHints("value")
                                                                          .setCropHintsParams(
                                                                                  CropHintsParams.newBuilder()
                                                                                          .addAspectRatios(0.0f)
                                                                                          .build())
                                                                          .setProductSearchParams(
                                                                                  ProductSearchParams.newBuilder()
                                                                                          .setBoundingPoly(
                                                                                                  BoundingPoly.newBuilder()
                                                                                                          .addVertices(
                                                                                                                  Vertex.newBuilder()
                                                                                                                          .setX(0)
                                                                                                                          .setY(0)
                                                                                                                          .build())
                                                                                                          .addNormalizedVertices(
                                                                                                                  NormalizedVertex.newBuilder()
                                                                                                                          .setX(0.0f)
                                                                                                                          .setY(0.0f)
                                                                                                                          .build())
                                                                                                          .build())
                                                                                          .setProductSet("value")
                                                                                          .addProductCategories("value")
                                                                                          .setFilter("value")
                                                                                          .build())
                                                                          .setWebDetectionParams(
                                                                                  WebDetectionParams.newBuilder()
                                                                                          .setIncludeGeoResults(false)
                                                                                          .build())
                                                                          .build())
                                                                  .addPages(0)
                                                                  .build()])).thenReturn(batchAnnotateFilesResponse)

        // Run the test
        myClassUnderTest.tryBatchAnnotateFiles1()

        // Verify the results
    }

    @Test
    void testTryBatchAnnotateFiles1_ImageAnnotatorClientThrowsApiException() {
        // Setup
        when(mockImageAnnotatorClient.batchAnnotateFiles([AnnotateFileRequest.newBuilder()
                                                                  .setInputConfig(InputConfig.newBuilder()
                                                                          .setGcsSource(GcsSource.newBuilder()
                                                                                  .setUri("value")
                                                                                  .build())
                                                                          .setContent(ByteString.copyFrom(
                                                                                  "content".getBytes()))
                                                                          .setMimeType("value")
                                                                          .build())
                                                                  .addFeatures(Feature.newBuilder()
                                                                          .setType(Feature.Type.TYPE_UNSPECIFIED)
                                                                          .setMaxResults(0)
                                                                          .setModel("value")
                                                                          .build())
                                                                  .setImageContext(ImageContext.newBuilder()
                                                                          .setLatLongRect(LatLongRect.newBuilder()
                                                                                  .setMinLatLng(LatLng.newBuilder()
                                                                                          .setLatitude(0.0d)
                                                                                          .setLongitude(0.0d)
                                                                                          .build())
                                                                                  .setMaxLatLng(LatLng.newBuilder()
                                                                                          .setLatitude(0.0d)
                                                                                          .setLongitude(0.0d)
                                                                                          .build())
                                                                                  .build())
                                                                          .addLanguageHints("value")
                                                                          .setCropHintsParams(
                                                                                  CropHintsParams.newBuilder()
                                                                                          .addAspectRatios(0.0f)
                                                                                          .build())
                                                                          .setProductSearchParams(
                                                                                  ProductSearchParams.newBuilder()
                                                                                          .setBoundingPoly(
                                                                                                  BoundingPoly.newBuilder()
                                                                                                          .addVertices(
                                                                                                                  Vertex.newBuilder()
                                                                                                                          .setX(0)
                                                                                                                          .setY(0)
                                                                                                                          .build())
                                                                                                          .addNormalizedVertices(
                                                                                                                  NormalizedVertex.newBuilder()
                                                                                                                          .setX(0.0f)
                                                                                                                          .setY(0.0f)
                                                                                                                          .build())
                                                                                                          .build())
                                                                                          .setProductSet("value")
                                                                                          .addProductCategories("value")
                                                                                          .setFilter("value")
                                                                                          .build())
                                                                          .setWebDetectionParams(
                                                                                  WebDetectionParams.newBuilder()
                                                                                          .setIncludeGeoResults(false)
                                                                                          .build())
                                                                          .build())
                                                                  .addPages(0)
                                                                  .build()])).thenThrow(ApiException.class)

        // Run the test
        assertThrows(ApiException.class, {
            myClassUnderTest.tryBatchAnnotateFiles1()
        })
    }

    @Test
    void testTryBatchAnnotateFiles2() {
        // Setup
        // Configure ImageAnnotatorClient.batchAnnotateFiles(...).
        def batchAnnotateFilesResponse = BatchAnnotateFilesResponse.newBuilder().build()
        when(mockImageAnnotatorClient.batchAnnotateFiles(BatchAnnotateFilesRequest.newBuilder()
                .addRequests(AnnotateFileRequest.newBuilder()
                        .setInputConfig(InputConfig.newBuilder()
                                .setGcsSource(GcsSource.newBuilder()
                                        .setUri("value")
                                        .build())
                                .setContent(ByteString.copyFrom("content".getBytes()))
                                .setMimeType("value")
                                .build())
                        .addFeatures(Feature.newBuilder()
                                .setType(Feature.Type.TYPE_UNSPECIFIED)
                                .setMaxResults(0)
                                .setModel("value")
                                .build())
                        .setImageContext(ImageContext.newBuilder()
                                .setLatLongRect(LatLongRect.newBuilder()
                                        .setMinLatLng(LatLng.newBuilder()
                                                .setLatitude(0.0d)
                                                .setLongitude(0.0d)
                                                .build())
                                        .setMaxLatLng(LatLng.newBuilder()
                                                .setLatitude(0.0d)
                                                .setLongitude(0.0d)
                                                .build())
                                        .build())
                                .addLanguageHints("value")
                                .setCropHintsParams(CropHintsParams.newBuilder()
                                        .addAspectRatios(0.0f)
                                        .build())
                                .setProductSearchParams(ProductSearchParams.newBuilder()
                                        .setBoundingPoly(BoundingPoly.newBuilder()
                                                .addVertices(Vertex.newBuilder()
                                                        .setX(0)
                                                        .setY(0)
                                                        .build())
                                                .addNormalizedVertices(NormalizedVertex.newBuilder()
                                                        .setX(0.0f)
                                                        .setY(0.0f)
                                                        .build())
                                                .build())
                                        .setProductSet("value")
                                        .addProductCategories("value")
                                        .setFilter("value")
                                        .build())
                                .setWebDetectionParams(WebDetectionParams.newBuilder()
                                        .setIncludeGeoResults(false)
                                        .build())
                                .build())
                        .addPages(0)
                        .build())
                .setParent("value")
                .build())).thenReturn(batchAnnotateFilesResponse)

        // Run the test
        myClassUnderTest.tryBatchAnnotateFiles2()

        // Verify the results
    }

    @Test
    void testTryBatchAnnotateFiles2_ImageAnnotatorClientThrowsApiException() {
        // Setup
        when(mockImageAnnotatorClient.batchAnnotateFiles(BatchAnnotateFilesRequest.newBuilder()
                .addRequests(AnnotateFileRequest.newBuilder()
                        .setInputConfig(InputConfig.newBuilder()
                                .setGcsSource(GcsSource.newBuilder()
                                        .setUri("value")
                                        .build())
                                .setContent(ByteString.copyFrom("content".getBytes()))
                                .setMimeType("value")
                                .build())
                        .addFeatures(Feature.newBuilder()
                                .setType(Feature.Type.TYPE_UNSPECIFIED)
                                .setMaxResults(0)
                                .setModel("value")
                                .build())
                        .setImageContext(ImageContext.newBuilder()
                                .setLatLongRect(LatLongRect.newBuilder()
                                        .setMinLatLng(LatLng.newBuilder()
                                                .setLatitude(0.0d)
                                                .setLongitude(0.0d)
                                                .build())
                                        .setMaxLatLng(LatLng.newBuilder()
                                                .setLatitude(0.0d)
                                                .setLongitude(0.0d)
                                                .build())
                                        .build())
                                .addLanguageHints("value")
                                .setCropHintsParams(CropHintsParams.newBuilder()
                                        .addAspectRatios(0.0f)
                                        .build())
                                .setProductSearchParams(ProductSearchParams.newBuilder()
                                        .setBoundingPoly(BoundingPoly.newBuilder()
                                                .addVertices(Vertex.newBuilder()
                                                        .setX(0)
                                                        .setY(0)
                                                        .build())
                                                .addNormalizedVertices(NormalizedVertex.newBuilder()
                                                        .setX(0.0f)
                                                        .setY(0.0f)
                                                        .build())
                                                .build())
                                        .setProductSet("value")
                                        .addProductCategories("value")
                                        .setFilter("value")
                                        .build())
                                .setWebDetectionParams(WebDetectionParams.newBuilder()
                                        .setIncludeGeoResults(false)
                                        .build())
                                .build())
                        .addPages(0)
                        .build())
                .setParent("value")
                .build())).thenThrow(ApiException.class)

        // Run the test
        assertThrows(ApiException.class, {
            myClassUnderTest.tryBatchAnnotateFiles2()
        })
    }

    @Test
    void testTryBatchAnnotateFilesCallable() {
        // Setup
        // Configure ImageAnnotatorClient.batchAnnotateFilesCallable(...).
        def batchAnnotateFilesRequestBatchAnnotateFilesResponseUnaryCallable = new UnaryCallable<BatchAnnotateFilesRequest, BatchAnnotateFilesResponse>() {
            @Override
            ApiFuture<BatchAnnotateFilesResponse> futureCall(final BatchAnnotateFilesRequest request, final ApiCallContext context) {
                def settableApiFuture = SettableApiFuture.create()
                settableApiFuture.set(BatchAnnotateFilesResponse.newBuilder().build())
                return settableApiFuture
            }
        }
        when(mockImageAnnotatorClient.batchAnnotateFilesCallable())
                .thenReturn(batchAnnotateFilesRequestBatchAnnotateFilesResponseUnaryCallable)

        // Run the test
        myClassUnderTest.tryBatchAnnotateFilesCallable()

        // Verify the results
    }

    @Test
    void testTryBatchAnnotateFilesCallable_ImageAnnotatorClientReturnsFailure() {
        // Setup
        // Configure ImageAnnotatorClient.batchAnnotateFilesCallable(...).
        def batchAnnotateFilesRequestBatchAnnotateFilesResponseUnaryCallable = new UnaryCallable<BatchAnnotateFilesRequest, BatchAnnotateFilesResponse>() {
            @Override
            ApiFuture<BatchAnnotateFilesResponse> futureCall(final BatchAnnotateFilesRequest request, final ApiCallContext context) {
                def settableApiFuture = SettableApiFuture.create()
                settableApiFuture.setException(new Exception("Message"))
                return settableApiFuture
            }
        }
        when(mockImageAnnotatorClient.batchAnnotateFilesCallable())
                .thenReturn(batchAnnotateFilesRequestBatchAnnotateFilesResponseUnaryCallable)

        // Run the test
        myClassUnderTest.tryBatchAnnotateFilesCallable()

        // Verify the results
    }

    @Test
    void testTryAsyncBatchAnnotateImagesAsync1() {
        // Setup
        when(mockImageAnnotatorClient.asyncBatchAnnotateImagesAsync([AnnotateImageRequest.newBuilder()
                                                                             .setImage(Image.newBuilder()
                                                                                     .setContent(ByteString.copyFrom(
                                                                                             "content".getBytes()))
                                                                                     .setSource(ImageSource.newBuilder()
                                                                                             .setGcsImageUri("value")
                                                                                             .setImageUri("value")
                                                                                             .build())
                                                                                     .build())
                                                                             .addFeatures(Feature.newBuilder()
                                                                                     .setType(
                                                                                             Feature.Type.TYPE_UNSPECIFIED)
                                                                                     .setMaxResults(0)
                                                                                     .setModel("value")
                                                                                     .build())
                                                                             .setImageContext(ImageContext.newBuilder()
                                                                                     .setLatLongRect(
                                                                                             LatLongRect.newBuilder()
                                                                                                     .setMinLatLng(
                                                                                                             LatLng.newBuilder()
                                                                                                                     .setLatitude(
                                                                                                                             0.0d)
                                                                                                                     .setLongitude(
                                                                                                                             0.0d)
                                                                                                                     .build())
                                                                                                     .setMaxLatLng(
                                                                                                             LatLng.newBuilder()
                                                                                                                     .setLatitude(
                                                                                                                             0.0d)
                                                                                                                     .setLongitude(
                                                                                                                             0.0d)
                                                                                                                     .build())
                                                                                                     .build())
                                                                                     .addLanguageHints("value")
                                                                                     .setCropHintsParams(
                                                                                             CropHintsParams.newBuilder()
                                                                                                     .addAspectRatios(
                                                                                                             0.0f)
                                                                                                     .build())
                                                                                     .setProductSearchParams(
                                                                                             ProductSearchParams.newBuilder()
                                                                                                     .setBoundingPoly(
                                                                                                             BoundingPoly.newBuilder()
                                                                                                                     .addVertices(
                                                                                                                             Vertex.newBuilder()
                                                                                                                                     .setX(0)
                                                                                                                                     .setY(0)
                                                                                                                                     .build())
                                                                                                                     .addNormalizedVertices(
                                                                                                                             NormalizedVertex.newBuilder()
                                                                                                                                     .setX(0.0f)
                                                                                                                                     .setY(0.0f)
                                                                                                                                     .build())
                                                                                                                     .build())
                                                                                                     .setProductSet(
                                                                                                             "value")
                                                                                                     .addProductCategories(
                                                                                                             "value")
                                                                                                     .setFilter("value")
                                                                                                     .build())
                                                                                     .setWebDetectionParams(
                                                                                             WebDetectionParams.newBuilder()
                                                                                                     .setIncludeGeoResults(
                                                                                                             false)
                                                                                                     .build())
                                                                                     .build())
                                                                             .build()], OutputConfig.newBuilder()
                .setGcsDestination(GcsDestination.newBuilder()
                        .setUri("value")
                        .build())
                .setBatchSize(0)
                .build())).thenReturn(null)

        // Run the test
        myClassUnderTest.tryAsyncBatchAnnotateImagesAsync1()

        // Verify the results
    }

    @Test
    void testTryAsyncBatchAnnotateImagesAsync1_ImageAnnotatorClientThrowsApiException() {
        // Setup
        when(mockImageAnnotatorClient.asyncBatchAnnotateImagesAsync([AnnotateImageRequest.newBuilder()
                                                                             .setImage(Image.newBuilder()
                                                                                     .setContent(ByteString.copyFrom(
                                                                                             "content".getBytes()))
                                                                                     .setSource(ImageSource.newBuilder()
                                                                                             .setGcsImageUri("value")
                                                                                             .setImageUri("value")
                                                                                             .build())
                                                                                     .build())
                                                                             .addFeatures(Feature.newBuilder()
                                                                                     .setType(
                                                                                             Feature.Type.TYPE_UNSPECIFIED)
                                                                                     .setMaxResults(0)
                                                                                     .setModel("value")
                                                                                     .build())
                                                                             .setImageContext(ImageContext.newBuilder()
                                                                                     .setLatLongRect(
                                                                                             LatLongRect.newBuilder()
                                                                                                     .setMinLatLng(
                                                                                                             LatLng.newBuilder()
                                                                                                                     .setLatitude(
                                                                                                                             0.0d)
                                                                                                                     .setLongitude(
                                                                                                                             0.0d)
                                                                                                                     .build())
                                                                                                     .setMaxLatLng(
                                                                                                             LatLng.newBuilder()
                                                                                                                     .setLatitude(
                                                                                                                             0.0d)
                                                                                                                     .setLongitude(
                                                                                                                             0.0d)
                                                                                                                     .build())
                                                                                                     .build())
                                                                                     .addLanguageHints("value")
                                                                                     .setCropHintsParams(
                                                                                             CropHintsParams.newBuilder()
                                                                                                     .addAspectRatios(
                                                                                                             0.0f)
                                                                                                     .build())
                                                                                     .setProductSearchParams(
                                                                                             ProductSearchParams.newBuilder()
                                                                                                     .setBoundingPoly(
                                                                                                             BoundingPoly.newBuilder()
                                                                                                                     .addVertices(
                                                                                                                             Vertex.newBuilder()
                                                                                                                                     .setX(0)
                                                                                                                                     .setY(0)
                                                                                                                                     .build())
                                                                                                                     .addNormalizedVertices(
                                                                                                                             NormalizedVertex.newBuilder()
                                                                                                                                     .setX(0.0f)
                                                                                                                                     .setY(0.0f)
                                                                                                                                     .build())
                                                                                                                     .build())
                                                                                                     .setProductSet(
                                                                                                             "value")
                                                                                                     .addProductCategories(
                                                                                                             "value")
                                                                                                     .setFilter("value")
                                                                                                     .build())
                                                                                     .setWebDetectionParams(
                                                                                             WebDetectionParams.newBuilder()
                                                                                                     .setIncludeGeoResults(
                                                                                                             false)
                                                                                                     .build())
                                                                                     .build())
                                                                             .build()], OutputConfig.newBuilder()
                .setGcsDestination(GcsDestination.newBuilder()
                        .setUri("value")
                        .build())
                .setBatchSize(0)
                .build())).thenThrow(ApiException.class)

        // Run the test
        assertThrows(ApiException.class, {
            myClassUnderTest.tryAsyncBatchAnnotateImagesAsync1()
        })
    }

    @Test
    void testTryAsyncBatchAnnotateImagesAsync2() {
        // Setup
        when(mockImageAnnotatorClient.asyncBatchAnnotateImagesAsync(AsyncBatchAnnotateImagesRequest.newBuilder()
                .addRequests(AnnotateImageRequest.newBuilder()
                        .setImage(Image.newBuilder()
                                .setContent(ByteString.copyFrom("content".getBytes()))
                                .setSource(ImageSource.newBuilder()
                                        .setGcsImageUri("value")
                                        .setImageUri("value")
                                        .build())
                                .build())
                        .addFeatures(Feature.newBuilder()
                                .setType(Feature.Type.TYPE_UNSPECIFIED)
                                .setMaxResults(0)
                                .setModel("value")
                                .build())
                        .setImageContext(ImageContext.newBuilder()
                                .setLatLongRect(LatLongRect.newBuilder()
                                        .setMinLatLng(LatLng.newBuilder()
                                                .setLatitude(0.0d)
                                                .setLongitude(0.0d)
                                                .build())
                                        .setMaxLatLng(LatLng.newBuilder()
                                                .setLatitude(0.0d)
                                                .setLongitude(0.0d)
                                                .build())
                                        .build())
                                .addLanguageHints("value")
                                .setCropHintsParams(CropHintsParams.newBuilder()
                                        .addAspectRatios(0.0f)
                                        .build())
                                .setProductSearchParams(ProductSearchParams.newBuilder()
                                        .setBoundingPoly(BoundingPoly.newBuilder()
                                                .addVertices(Vertex.newBuilder()
                                                        .setX(0)
                                                        .setY(0)
                                                        .build())
                                                .addNormalizedVertices(NormalizedVertex.newBuilder()
                                                        .setX(0.0f)
                                                        .setY(0.0f)
                                                        .build())
                                                .build())
                                        .setProductSet("value")
                                        .addProductCategories("value")
                                        .setFilter("value")
                                        .build())
                                .setWebDetectionParams(WebDetectionParams.newBuilder()
                                        .setIncludeGeoResults(false)
                                        .build())
                                .build())
                        .build())
                .setOutputConfig(OutputConfig.newBuilder()
                        .setGcsDestination(GcsDestination.newBuilder()
                                .setUri("value")
                                .build())
                        .setBatchSize(0)
                        .build())
                .setParent("value")
                .build())).thenReturn(null)

        // Run the test
        myClassUnderTest.tryAsyncBatchAnnotateImagesAsync2()

        // Verify the results
    }

    @Test
    void testTryAsyncBatchAnnotateImagesAsync2_ImageAnnotatorClientThrowsApiException() {
        // Setup
        when(mockImageAnnotatorClient.asyncBatchAnnotateImagesAsync(AsyncBatchAnnotateImagesRequest.newBuilder()
                .addRequests(AnnotateImageRequest.newBuilder()
                        .setImage(Image.newBuilder()
                                .setContent(ByteString.copyFrom("content".getBytes()))
                                .setSource(ImageSource.newBuilder()
                                        .setGcsImageUri("value")
                                        .setImageUri("value")
                                        .build())
                                .build())
                        .addFeatures(Feature.newBuilder()
                                .setType(Feature.Type.TYPE_UNSPECIFIED)
                                .setMaxResults(0)
                                .setModel("value")
                                .build())
                        .setImageContext(ImageContext.newBuilder()
                                .setLatLongRect(LatLongRect.newBuilder()
                                        .setMinLatLng(LatLng.newBuilder()
                                                .setLatitude(0.0d)
                                                .setLongitude(0.0d)
                                                .build())
                                        .setMaxLatLng(LatLng.newBuilder()
                                                .setLatitude(0.0d)
                                                .setLongitude(0.0d)
                                                .build())
                                        .build())
                                .addLanguageHints("value")
                                .setCropHintsParams(CropHintsParams.newBuilder()
                                        .addAspectRatios(0.0f)
                                        .build())
                                .setProductSearchParams(ProductSearchParams.newBuilder()
                                        .setBoundingPoly(BoundingPoly.newBuilder()
                                                .addVertices(Vertex.newBuilder()
                                                        .setX(0)
                                                        .setY(0)
                                                        .build())
                                                .addNormalizedVertices(NormalizedVertex.newBuilder()
                                                        .setX(0.0f)
                                                        .setY(0.0f)
                                                        .build())
                                                .build())
                                        .setProductSet("value")
                                        .addProductCategories("value")
                                        .setFilter("value")
                                        .build())
                                .setWebDetectionParams(WebDetectionParams.newBuilder()
                                        .setIncludeGeoResults(false)
                                        .build())
                                .build())
                        .build())
                .setOutputConfig(OutputConfig.newBuilder()
                        .setGcsDestination(GcsDestination.newBuilder()
                                .setUri("value")
                                .build())
                        .setBatchSize(0)
                        .build())
                .setParent("value")
                .build())).thenThrow(ApiException.class)

        // Run the test
        assertThrows(ApiException.class, {
            myClassUnderTest.tryAsyncBatchAnnotateImagesAsync2()
        })
    }

    @Test
    void testTryAsyncBatchAnnotateImagesOperationCallable() {
        // Setup
        when(mockImageAnnotatorClient.asyncBatchAnnotateImagesOperationCallable()).thenReturn(null)

        // Run the test
        myClassUnderTest.tryAsyncBatchAnnotateImagesOperationCallable()

        // Verify the results
    }

    @Test
    void testTryAsyncBatchAnnotateImagesCallable() {
        // Setup
        // Configure ImageAnnotatorClient.asyncBatchAnnotateImagesCallable(...).
        def asyncBatchAnnotateImagesRequestOperationUnaryCallable = new UnaryCallable<AsyncBatchAnnotateImagesRequest, Operation>() {
            @Override
            ApiFuture<Operation> futureCall(final AsyncBatchAnnotateImagesRequest request, final ApiCallContext context) {
                def settableApiFuture = SettableApiFuture.create()
                settableApiFuture.set(Operation.newBuilder().build())
                return settableApiFuture
            }
        }
        when(mockImageAnnotatorClient.asyncBatchAnnotateImagesCallable())
                .thenReturn(asyncBatchAnnotateImagesRequestOperationUnaryCallable)

        // Run the test
        myClassUnderTest.tryAsyncBatchAnnotateImagesCallable()

        // Verify the results
    }

    @Test
    void testTryAsyncBatchAnnotateImagesCallable_ImageAnnotatorClientReturnsFailure() {
        // Setup
        // Configure ImageAnnotatorClient.asyncBatchAnnotateImagesCallable(...).
        def asyncBatchAnnotateImagesRequestOperationUnaryCallable = new UnaryCallable<AsyncBatchAnnotateImagesRequest, Operation>() {
            @Override
            ApiFuture<Operation> futureCall(final AsyncBatchAnnotateImagesRequest request, final ApiCallContext context) {
                def settableApiFuture = SettableApiFuture.create()
                settableApiFuture.setException(new Exception("Message"))
                return settableApiFuture
            }
        }
        when(mockImageAnnotatorClient.asyncBatchAnnotateImagesCallable())
                .thenReturn(asyncBatchAnnotateImagesRequestOperationUnaryCallable)

        // Run the test
        myClassUnderTest.tryAsyncBatchAnnotateImagesCallable()

        // Verify the results
    }

    @Test
    void testTryAsyncBatchAnnotateFilesAsync1() {
        // Setup
        when(mockImageAnnotatorClient.asyncBatchAnnotateFilesAsync([AsyncAnnotateFileRequest.newBuilder()
                                                                            .setInputConfig(InputConfig.newBuilder()
                                                                                    .setGcsSource(GcsSource.newBuilder()
                                                                                            .setUri("value")
                                                                                            .build())
                                                                                    .setContent(ByteString.copyFrom(
                                                                                            "content".getBytes()))
                                                                                    .setMimeType("value")
                                                                                    .build())
                                                                            .addFeatures(Feature.newBuilder()
                                                                                    .setType(
                                                                                            Feature.Type.TYPE_UNSPECIFIED)
                                                                                    .setMaxResults(0)
                                                                                    .setModel("value")
                                                                                    .build())
                                                                            .setImageContext(ImageContext.newBuilder()
                                                                                    .setLatLongRect(
                                                                                            LatLongRect.newBuilder()
                                                                                                    .setMinLatLng(
                                                                                                            LatLng.newBuilder()
                                                                                                                    .setLatitude(
                                                                                                                            0.0d)
                                                                                                                    .setLongitude(
                                                                                                                            0.0d)
                                                                                                                    .build())
                                                                                                    .setMaxLatLng(
                                                                                                            LatLng.newBuilder()
                                                                                                                    .setLatitude(
                                                                                                                            0.0d)
                                                                                                                    .setLongitude(
                                                                                                                            0.0d)
                                                                                                                    .build())
                                                                                                    .build())
                                                                                    .addLanguageHints("value")
                                                                                    .setCropHintsParams(
                                                                                            CropHintsParams.newBuilder()
                                                                                                    .addAspectRatios(
                                                                                                            0.0f)
                                                                                                    .build())
                                                                                    .setProductSearchParams(
                                                                                            ProductSearchParams.newBuilder()
                                                                                                    .setBoundingPoly(
                                                                                                            BoundingPoly.newBuilder()
                                                                                                                    .addVertices(
                                                                                                                            Vertex.newBuilder()
                                                                                                                                    .setX(0)
                                                                                                                                    .setY(0)
                                                                                                                                    .build())
                                                                                                                    .addNormalizedVertices(
                                                                                                                            NormalizedVertex.newBuilder()
                                                                                                                                    .setX(0.0f)
                                                                                                                                    .setY(0.0f)
                                                                                                                                    .build())
                                                                                                                    .build())
                                                                                                    .setProductSet(
                                                                                                            "value")
                                                                                                    .addProductCategories(
                                                                                                            "value")
                                                                                                    .setFilter("value")
                                                                                                    .build())
                                                                                    .setWebDetectionParams(
                                                                                            WebDetectionParams.newBuilder()
                                                                                                    .setIncludeGeoResults(
                                                                                                            false)
                                                                                                    .build())
                                                                                    .build())
                                                                            .setOutputConfig(OutputConfig.newBuilder()
                                                                                    .setGcsDestination(
                                                                                            GcsDestination.newBuilder()
                                                                                                    .setUri("value")
                                                                                                    .build())
                                                                                    .setBatchSize(0)
                                                                                    .build())
                                                                            .build()])).thenReturn(null)

        // Run the test
        myClassUnderTest.tryAsyncBatchAnnotateFilesAsync1()

        // Verify the results
    }

    @Test
    void testTryAsyncBatchAnnotateFilesAsync1_ImageAnnotatorClientThrowsApiException() {
        // Setup
        when(mockImageAnnotatorClient.asyncBatchAnnotateFilesAsync([AsyncAnnotateFileRequest.newBuilder()
                                                                            .setInputConfig(InputConfig.newBuilder()
                                                                                    .setGcsSource(GcsSource.newBuilder()
                                                                                            .setUri("value")
                                                                                            .build())
                                                                                    .setContent(ByteString.copyFrom(
                                                                                            "content".getBytes()))
                                                                                    .setMimeType("value")
                                                                                    .build())
                                                                            .addFeatures(Feature.newBuilder()
                                                                                    .setType(
                                                                                            Feature.Type.TYPE_UNSPECIFIED)
                                                                                    .setMaxResults(0)
                                                                                    .setModel("value")
                                                                                    .build())
                                                                            .setImageContext(ImageContext.newBuilder()
                                                                                    .setLatLongRect(
                                                                                            LatLongRect.newBuilder()
                                                                                                    .setMinLatLng(
                                                                                                            LatLng.newBuilder()
                                                                                                                    .setLatitude(
                                                                                                                            0.0d)
                                                                                                                    .setLongitude(
                                                                                                                            0.0d)
                                                                                                                    .build())
                                                                                                    .setMaxLatLng(
                                                                                                            LatLng.newBuilder()
                                                                                                                    .setLatitude(
                                                                                                                            0.0d)
                                                                                                                    .setLongitude(
                                                                                                                            0.0d)
                                                                                                                    .build())
                                                                                                    .build())
                                                                                    .addLanguageHints("value")
                                                                                    .setCropHintsParams(
                                                                                            CropHintsParams.newBuilder()
                                                                                                    .addAspectRatios(
                                                                                                            0.0f)
                                                                                                    .build())
                                                                                    .setProductSearchParams(
                                                                                            ProductSearchParams.newBuilder()
                                                                                                    .setBoundingPoly(
                                                                                                            BoundingPoly.newBuilder()
                                                                                                                    .addVertices(
                                                                                                                            Vertex.newBuilder()
                                                                                                                                    .setX(0)
                                                                                                                                    .setY(0)
                                                                                                                                    .build())
                                                                                                                    .addNormalizedVertices(
                                                                                                                            NormalizedVertex.newBuilder()
                                                                                                                                    .setX(0.0f)
                                                                                                                                    .setY(0.0f)
                                                                                                                                    .build())
                                                                                                                    .build())
                                                                                                    .setProductSet(
                                                                                                            "value")
                                                                                                    .addProductCategories(
                                                                                                            "value")
                                                                                                    .setFilter("value")
                                                                                                    .build())
                                                                                    .setWebDetectionParams(
                                                                                            WebDetectionParams.newBuilder()
                                                                                                    .setIncludeGeoResults(
                                                                                                            false)
                                                                                                    .build())
                                                                                    .build())
                                                                            .setOutputConfig(OutputConfig.newBuilder()
                                                                                    .setGcsDestination(
                                                                                            GcsDestination.newBuilder()
                                                                                                    .setUri("value")
                                                                                                    .build())
                                                                                    .setBatchSize(0)
                                                                                    .build())
                                                                            .build()])).thenThrow(ApiException.class)

        // Run the test
        assertThrows(ApiException.class, {
            myClassUnderTest.tryAsyncBatchAnnotateFilesAsync1()
        })
    }

    @Test
    void testTryAsyncBatchAnnotateFilesAsync2() {
        // Setup
        when(mockImageAnnotatorClient.asyncBatchAnnotateFilesAsync(AsyncBatchAnnotateFilesRequest.newBuilder()
                .addRequests(AsyncAnnotateFileRequest.newBuilder()
                        .setInputConfig(InputConfig.newBuilder()
                                .setGcsSource(GcsSource.newBuilder()
                                        .setUri("value")
                                        .build())
                                .setContent(ByteString.copyFrom("content".getBytes()))
                                .setMimeType("value")
                                .build())
                        .addFeatures(Feature.newBuilder()
                                .setType(Feature.Type.TYPE_UNSPECIFIED)
                                .setMaxResults(0)
                                .setModel("value")
                                .build())
                        .setImageContext(ImageContext.newBuilder()
                                .setLatLongRect(LatLongRect.newBuilder()
                                        .setMinLatLng(LatLng.newBuilder()
                                                .setLatitude(0.0d)
                                                .setLongitude(0.0d)
                                                .build())
                                        .setMaxLatLng(LatLng.newBuilder()
                                                .setLatitude(0.0d)
                                                .setLongitude(0.0d)
                                                .build())
                                        .build())
                                .addLanguageHints("value")
                                .setCropHintsParams(CropHintsParams.newBuilder()
                                        .addAspectRatios(0.0f)
                                        .build())
                                .setProductSearchParams(ProductSearchParams.newBuilder()
                                        .setBoundingPoly(BoundingPoly.newBuilder()
                                                .addVertices(Vertex.newBuilder()
                                                        .setX(0)
                                                        .setY(0)
                                                        .build())
                                                .addNormalizedVertices(NormalizedVertex.newBuilder()
                                                        .setX(0.0f)
                                                        .setY(0.0f)
                                                        .build())
                                                .build())
                                        .setProductSet("value")
                                        .addProductCategories("value")
                                        .setFilter("value")
                                        .build())
                                .setWebDetectionParams(WebDetectionParams.newBuilder()
                                        .setIncludeGeoResults(false)
                                        .build())
                                .build())
                        .setOutputConfig(OutputConfig.newBuilder()
                                .setGcsDestination(GcsDestination.newBuilder()
                                        .setUri("value")
                                        .build())
                                .setBatchSize(0)
                                .build())
                        .build())
                .setParent("value")
                .build())).thenReturn(null)

        // Run the test
        myClassUnderTest.tryAsyncBatchAnnotateFilesAsync2()

        // Verify the results
    }

    @Test
    void testTryAsyncBatchAnnotateFilesAsync2_ImageAnnotatorClientThrowsApiException() {
        // Setup
        when(mockImageAnnotatorClient.asyncBatchAnnotateFilesAsync(AsyncBatchAnnotateFilesRequest.newBuilder()
                .addRequests(AsyncAnnotateFileRequest.newBuilder()
                        .setInputConfig(InputConfig.newBuilder()
                                .setGcsSource(GcsSource.newBuilder()
                                        .setUri("value")
                                        .build())
                                .setContent(ByteString.copyFrom("content".getBytes()))
                                .setMimeType("value")
                                .build())
                        .addFeatures(Feature.newBuilder()
                                .setType(Feature.Type.TYPE_UNSPECIFIED)
                                .setMaxResults(0)
                                .setModel("value")
                                .build())
                        .setImageContext(ImageContext.newBuilder()
                                .setLatLongRect(LatLongRect.newBuilder()
                                        .setMinLatLng(LatLng.newBuilder()
                                                .setLatitude(0.0d)
                                                .setLongitude(0.0d)
                                                .build())
                                        .setMaxLatLng(LatLng.newBuilder()
                                                .setLatitude(0.0d)
                                                .setLongitude(0.0d)
                                                .build())
                                        .build())
                                .addLanguageHints("value")
                                .setCropHintsParams(CropHintsParams.newBuilder()
                                        .addAspectRatios(0.0f)
                                        .build())
                                .setProductSearchParams(ProductSearchParams.newBuilder()
                                        .setBoundingPoly(BoundingPoly.newBuilder()
                                                .addVertices(Vertex.newBuilder()
                                                        .setX(0)
                                                        .setY(0)
                                                        .build())
                                                .addNormalizedVertices(NormalizedVertex.newBuilder()
                                                        .setX(0.0f)
                                                        .setY(0.0f)
                                                        .build())
                                                .build())
                                        .setProductSet("value")
                                        .addProductCategories("value")
                                        .setFilter("value")
                                        .build())
                                .setWebDetectionParams(WebDetectionParams.newBuilder()
                                        .setIncludeGeoResults(false)
                                        .build())
                                .build())
                        .setOutputConfig(OutputConfig.newBuilder()
                                .setGcsDestination(GcsDestination.newBuilder()
                                        .setUri("value")
                                        .build())
                                .setBatchSize(0)
                                .build())
                        .build())
                .setParent("value")
                .build())).thenThrow(ApiException.class)

        // Run the test
        assertThrows(ApiException.class, {
            myClassUnderTest.tryAsyncBatchAnnotateFilesAsync2()
        })
    }

    @Test
    void testTryAsyncBatchAnnotateFilesOperationCallable() {
        // Setup
        when(mockImageAnnotatorClient.asyncBatchAnnotateFilesOperationCallable()).thenReturn(null)

        // Run the test
        myClassUnderTest.tryAsyncBatchAnnotateFilesOperationCallable()

        // Verify the results
    }

    @Test
    void testTryAsyncBatchAnnotateFilesCallable() {
        // Setup
        // Configure ImageAnnotatorClient.asyncBatchAnnotateFilesCallable(...).
        def asyncBatchAnnotateFilesRequestOperationUnaryCallable = new UnaryCallable<AsyncBatchAnnotateFilesRequest, Operation>() {
            @Override
            ApiFuture<Operation> futureCall(final AsyncBatchAnnotateFilesRequest request, final ApiCallContext context) {
                def settableApiFuture = SettableApiFuture.create()
                settableApiFuture.set(Operation.newBuilder().build())
                return settableApiFuture
            }
        }
        when(mockImageAnnotatorClient.asyncBatchAnnotateFilesCallable())
                .thenReturn(asyncBatchAnnotateFilesRequestOperationUnaryCallable)

        // Run the test
        myClassUnderTest.tryAsyncBatchAnnotateFilesCallable()

        // Verify the results
    }

    @Test
    void testTryAsyncBatchAnnotateFilesCallable_ImageAnnotatorClientReturnsFailure() {
        // Setup
        // Configure ImageAnnotatorClient.asyncBatchAnnotateFilesCallable(...).
        def asyncBatchAnnotateFilesRequestOperationUnaryCallable = new UnaryCallable<AsyncBatchAnnotateFilesRequest, Operation>() {
            @Override
            ApiFuture<Operation> futureCall(final AsyncBatchAnnotateFilesRequest request, final ApiCallContext context) {
                def settableApiFuture = SettableApiFuture.create()
                settableApiFuture.setException(new Exception("Message"))
                return settableApiFuture
            }
        }
        when(mockImageAnnotatorClient.asyncBatchAnnotateFilesCallable())
                .thenReturn(asyncBatchAnnotateFilesRequestOperationUnaryCallable)

        // Run the test
        myClassUnderTest.tryAsyncBatchAnnotateFilesCallable()

        // Verify the results
    }

    @Test
    void testTryBatchAnnotateImages1() {
        // Setup
        // Configure ImageAnnotatorClient.batchAnnotateImages(...).
        def batchAnnotateImagesResponse = BatchAnnotateImagesResponse.newBuilder().build()
        when(mockImageAnnotatorClient.batchAnnotateImages([AnnotateImageRequest.newBuilder()
                                                                   .setImage(Image.newBuilder()
                                                                           .setContent(ByteString.copyFrom(
                                                                                   "content".getBytes()))
                                                                           .setSource(ImageSource.newBuilder()
                                                                                   .setGcsImageUri("value")
                                                                                   .setImageUri("value")
                                                                                   .build())
                                                                           .build())
                                                                   .addFeatures(Feature.newBuilder()
                                                                           .setType(Feature.Type.TYPE_UNSPECIFIED)
                                                                           .setMaxResults(0)
                                                                           .setModel("value")
                                                                           .build())
                                                                   .setImageContext(ImageContext.newBuilder()
                                                                           .setLatLongRect(LatLongRect.newBuilder()
                                                                                   .setMinLatLng(LatLng.newBuilder()
                                                                                           .setLatitude(0.0d)
                                                                                           .setLongitude(0.0d)
                                                                                           .build())
                                                                                   .setMaxLatLng(LatLng.newBuilder()
                                                                                           .setLatitude(0.0d)
                                                                                           .setLongitude(0.0d)
                                                                                           .build())
                                                                                   .build())
                                                                           .addLanguageHints("value")
                                                                           .setCropHintsParams(
                                                                                   CropHintsParams.newBuilder()
                                                                                           .addAspectRatios(0.0f)
                                                                                           .build())
                                                                           .setProductSearchParams(
                                                                                   ProductSearchParams.newBuilder()
                                                                                           .setBoundingPoly(
                                                                                                   BoundingPoly.newBuilder()
                                                                                                           .addVertices(
                                                                                                                   Vertex.newBuilder()
                                                                                                                           .setX(0)
                                                                                                                           .setY(0)
                                                                                                                           .build())
                                                                                                           .addNormalizedVertices(
                                                                                                                   NormalizedVertex.newBuilder()
                                                                                                                           .setX(0.0f)
                                                                                                                           .setY(0.0f)
                                                                                                                           .build())
                                                                                                           .build())
                                                                                           .setProductSet("value")
                                                                                           .addProductCategories(
                                                                                                   "value")
                                                                                           .setFilter("value")
                                                                                           .build())
                                                                           .setWebDetectionParams(
                                                                                   WebDetectionParams.newBuilder()
                                                                                           .setIncludeGeoResults(false)
                                                                                           .build())
                                                                           .build())
                                                                   .build()])).thenReturn(batchAnnotateImagesResponse)

        // Run the test
        myClassUnderTest.tryBatchAnnotateImages1()

        // Verify the results
    }

    @Test
    void testTryBatchAnnotateImages1_ImageAnnotatorClientThrowsApiException() {
        // Setup
        when(mockImageAnnotatorClient.batchAnnotateImages([AnnotateImageRequest.newBuilder()
                                                                   .setImage(Image.newBuilder()
                                                                           .setContent(ByteString.copyFrom(
                                                                                   "content".getBytes()))
                                                                           .setSource(ImageSource.newBuilder()
                                                                                   .setGcsImageUri("value")
                                                                                   .setImageUri("value")
                                                                                   .build())
                                                                           .build())
                                                                   .addFeatures(Feature.newBuilder()
                                                                           .setType(Feature.Type.TYPE_UNSPECIFIED)
                                                                           .setMaxResults(0)
                                                                           .setModel("value")
                                                                           .build())
                                                                   .setImageContext(ImageContext.newBuilder()
                                                                           .setLatLongRect(LatLongRect.newBuilder()
                                                                                   .setMinLatLng(LatLng.newBuilder()
                                                                                           .setLatitude(0.0d)
                                                                                           .setLongitude(0.0d)
                                                                                           .build())
                                                                                   .setMaxLatLng(LatLng.newBuilder()
                                                                                           .setLatitude(0.0d)
                                                                                           .setLongitude(0.0d)
                                                                                           .build())
                                                                                   .build())
                                                                           .addLanguageHints("value")
                                                                           .setCropHintsParams(
                                                                                   CropHintsParams.newBuilder()
                                                                                           .addAspectRatios(0.0f)
                                                                                           .build())
                                                                           .setProductSearchParams(
                                                                                   ProductSearchParams.newBuilder()
                                                                                           .setBoundingPoly(
                                                                                                   BoundingPoly.newBuilder()
                                                                                                           .addVertices(
                                                                                                                   Vertex.newBuilder()
                                                                                                                           .setX(0)
                                                                                                                           .setY(0)
                                                                                                                           .build())
                                                                                                           .addNormalizedVertices(
                                                                                                                   NormalizedVertex.newBuilder()
                                                                                                                           .setX(0.0f)
                                                                                                                           .setY(0.0f)
                                                                                                                           .build())
                                                                                                           .build())
                                                                                           .setProductSet("value")
                                                                                           .addProductCategories(
                                                                                                   "value")
                                                                                           .setFilter("value")
                                                                                           .build())
                                                                           .setWebDetectionParams(
                                                                                   WebDetectionParams.newBuilder()
                                                                                           .setIncludeGeoResults(false)
                                                                                           .build())
                                                                           .build())
                                                                   .build()])).thenThrow(ApiException.class)

        // Run the test
        assertThrows(ApiException.class, {
            myClassUnderTest.tryBatchAnnotateImages1()
        })
    }

    @Test
    void testTryBatchAnnotateImages2() {
        // Setup
        // Configure ImageAnnotatorClient.batchAnnotateImages(...).
        def batchAnnotateImagesResponse = BatchAnnotateImagesResponse.newBuilder().build()
        when(mockImageAnnotatorClient.batchAnnotateImages(BatchAnnotateImagesRequest.newBuilder()
                .addRequests(AnnotateImageRequest.newBuilder()
                        .setImage(Image.newBuilder()
                                .setContent(ByteString.copyFrom("content".getBytes()))
                                .setSource(ImageSource.newBuilder()
                                        .setGcsImageUri("value")
                                        .setImageUri("value")
                                        .build())
                                .build())
                        .addFeatures(Feature.newBuilder()
                                .setType(Feature.Type.TYPE_UNSPECIFIED)
                                .setMaxResults(0)
                                .setModel("value")
                                .build())
                        .setImageContext(ImageContext.newBuilder()
                                .setLatLongRect(LatLongRect.newBuilder()
                                        .setMinLatLng(LatLng.newBuilder()
                                                .setLatitude(0.0d)
                                                .setLongitude(0.0d)
                                                .build())
                                        .setMaxLatLng(LatLng.newBuilder()
                                                .setLatitude(0.0d)
                                                .setLongitude(0.0d)
                                                .build())
                                        .build())
                                .addLanguageHints("value")
                                .setCropHintsParams(CropHintsParams.newBuilder()
                                        .addAspectRatios(0.0f)
                                        .build())
                                .setProductSearchParams(ProductSearchParams.newBuilder()
                                        .setBoundingPoly(BoundingPoly.newBuilder()
                                                .addVertices(Vertex.newBuilder()
                                                        .setX(0)
                                                        .setY(0)
                                                        .build())
                                                .addNormalizedVertices(NormalizedVertex.newBuilder()
                                                        .setX(0.0f)
                                                        .setY(0.0f)
                                                        .build())
                                                .build())
                                        .setProductSet("value")
                                        .addProductCategories("value")
                                        .setFilter("value")
                                        .build())
                                .setWebDetectionParams(WebDetectionParams.newBuilder()
                                        .setIncludeGeoResults(false)
                                        .build())
                                .build())
                        .build())
                .setParent("value")
                .build())).thenReturn(batchAnnotateImagesResponse)

        // Run the test
        myClassUnderTest.tryBatchAnnotateImages2()

        // Verify the results
    }

    @Test
    void testTryBatchAnnotateImages2_ImageAnnotatorClientThrowsApiException() {
        // Setup
        when(mockImageAnnotatorClient.batchAnnotateImages(BatchAnnotateImagesRequest.newBuilder()
                .addRequests(AnnotateImageRequest.newBuilder()
                        .setImage(Image.newBuilder()
                                .setContent(ByteString.copyFrom("content".getBytes()))
                                .setSource(ImageSource.newBuilder()
                                        .setGcsImageUri("value")
                                        .setImageUri("value")
                                        .build())
                                .build())
                        .addFeatures(Feature.newBuilder()
                                .setType(Feature.Type.TYPE_UNSPECIFIED)
                                .setMaxResults(0)
                                .setModel("value")
                                .build())
                        .setImageContext(ImageContext.newBuilder()
                                .setLatLongRect(LatLongRect.newBuilder()
                                        .setMinLatLng(LatLng.newBuilder()
                                                .setLatitude(0.0d)
                                                .setLongitude(0.0d)
                                                .build())
                                        .setMaxLatLng(LatLng.newBuilder()
                                                .setLatitude(0.0d)
                                                .setLongitude(0.0d)
                                                .build())
                                        .build())
                                .addLanguageHints("value")
                                .setCropHintsParams(CropHintsParams.newBuilder()
                                        .addAspectRatios(0.0f)
                                        .build())
                                .setProductSearchParams(ProductSearchParams.newBuilder()
                                        .setBoundingPoly(BoundingPoly.newBuilder()
                                                .addVertices(Vertex.newBuilder()
                                                        .setX(0)
                                                        .setY(0)
                                                        .build())
                                                .addNormalizedVertices(NormalizedVertex.newBuilder()
                                                        .setX(0.0f)
                                                        .setY(0.0f)
                                                        .build())
                                                .build())
                                        .setProductSet("value")
                                        .addProductCategories("value")
                                        .setFilter("value")
                                        .build())
                                .setWebDetectionParams(WebDetectionParams.newBuilder()
                                        .setIncludeGeoResults(false)
                                        .build())
                                .build())
                        .build())
                .setParent("value")
                .build())).thenThrow(ApiException.class)

        // Run the test
        assertThrows(ApiException.class, {
            myClassUnderTest.tryBatchAnnotateImages2()
        })
    }

    @Test
    void testTryBatchAnnotateImagesCallable() {
        // Setup
        // Configure ImageAnnotatorClient.batchAnnotateImagesCallable(...).
        def batchAnnotateImagesRequestBatchAnnotateImagesResponseUnaryCallable = new UnaryCallable<BatchAnnotateImagesRequest, BatchAnnotateImagesResponse>() {
            @Override
            ApiFuture<BatchAnnotateImagesResponse> futureCall(final BatchAnnotateImagesRequest request, final ApiCallContext context) {
                def settableApiFuture = SettableApiFuture.create()
                settableApiFuture.set(BatchAnnotateImagesResponse.newBuilder().build())
                return settableApiFuture
            }
        }
        when(mockImageAnnotatorClient.batchAnnotateImagesCallable())
                .thenReturn(batchAnnotateImagesRequestBatchAnnotateImagesResponseUnaryCallable)

        // Run the test
        myClassUnderTest.tryBatchAnnotateImagesCallable()

        // Verify the results
    }

    @Test
    void testTryBatchAnnotateImagesCallable_ImageAnnotatorClientReturnsFailure() {
        // Setup
        // Configure ImageAnnotatorClient.batchAnnotateImagesCallable(...).
        def batchAnnotateImagesRequestBatchAnnotateImagesResponseUnaryCallable = new UnaryCallable<BatchAnnotateImagesRequest, BatchAnnotateImagesResponse>() {
            @Override
            ApiFuture<BatchAnnotateImagesResponse> futureCall(final BatchAnnotateImagesRequest request, final ApiCallContext context) {
                def settableApiFuture = SettableApiFuture.create()
                settableApiFuture.setException(new Exception("Message"))
                return settableApiFuture
            }
        }
        when(mockImageAnnotatorClient.batchAnnotateImagesCallable())
                .thenReturn(batchAnnotateImagesRequestBatchAnnotateImagesResponseUnaryCallable)

        // Run the test
        myClassUnderTest.tryBatchAnnotateImagesCallable()

        // Verify the results
    }

    @Test
    void testTryClose() {
        // Setup
        // Run the test
        myClassUnderTest.tryClose()

        // Verify the results
        verify(mockImageAnnotatorClient).close()
    }

    @Test
    void testTryShutdown() {
        // Setup
        // Run the test
        myClassUnderTest.tryShutdown()

        // Verify the results
        verify(mockImageAnnotatorClient).shutdown()
    }

    @Test
    void testTryIsShutdown() {
        // Setup
        when(mockImageAnnotatorClient.isShutdown()).thenReturn(false)

        // Run the test
        myClassUnderTest.tryIsShutdown()

        // Verify the results
    }

    @Test
    void testTryIsTerminated() {
        // Setup
        when(mockImageAnnotatorClient.isTerminated()).thenReturn(false)

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
        verify(mockImageAnnotatorClient).shutdownNow()
    }

    @Test
    void testTryAwaitTermination() {
        // Setup
        when(mockImageAnnotatorClient.awaitTermination(0L, TimeUnit.MILLISECONDS)).thenReturn(false)

        // Run the test
        myClassUnderTest.tryAwaitTermination()

        // Verify the results
    }

    @Test
    void testTryAwaitTermination_ImageAnnotatorClientThrowsInterruptedException() {
        // Setup
        when(mockImageAnnotatorClient.awaitTermination(0L, TimeUnit.MILLISECONDS))
                .thenThrow(InterruptedException.class)

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
