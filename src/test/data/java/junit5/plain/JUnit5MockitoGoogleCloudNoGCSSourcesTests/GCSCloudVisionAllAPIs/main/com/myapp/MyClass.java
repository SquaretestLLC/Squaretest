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

import com.google.api.gax.longrunning.OperationFuture;
import com.google.api.gax.rpc.OperationCallable;
import com.google.api.gax.rpc.UnaryCallable;
import com.google.cloud.vision.v1.*;
import com.google.cloud.vision.v1.stub.ImageAnnotatorStub;
import com.google.longrunning.Operation;
import com.google.protobuf.ByteString;
import com.google.type.LatLng;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MyClass {

    private ImageAnnotatorSettings settings;

    private ImageAnnotatorClient imageAnnotatorClient;

    public MyClass(final ImageAnnotatorClient imageAnnotatorClient) {
        this.imageAnnotatorClient = imageAnnotatorClient;
    }

    public void tryBatchAnnotateFiles1() {
        final List<AnnotateFileRequest> requests = Arrays.asList(AnnotateFileRequest.newBuilder()
                .setInputConfig(InputConfig.newBuilder()
                        .setGcsSource(GcsSource.newBuilder()
                                .setUri("theUri")
                                .build())
                        .setContent(ByteString.copyFrom("content".getBytes()))
                        .setMimeType("application/json")
                        .build())
                .addFeatures(Feature.newBuilder()
                        .setType(Feature.Type.TYPE_UNSPECIFIED)
                        .setMaxResults(0)
                        .setModel("theModel")
                        .build())
                .setImageContext(ImageContext.newBuilder()
                        .setLatLongRect(LatLongRect.newBuilder()
                                .setMinLatLng(LatLng.newBuilder()
                                        .setLatitude(0.0)
                                        .setLongitude(0.0)
                                        .build())
                                .setMaxLatLng(LatLng.newBuilder()
                                        .setLatitude(0.0)
                                        .setLongitude(0.0)
                                        .build())
                                .build())
                        .addLanguageHints("theLanguageHint")
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
                                .setProductSet("theProductSet")
                                .addProductCategories("value")
                                .setFilter("theFilter")
                                .build())
                        .setWebDetectionParams(WebDetectionParams.newBuilder()
                                .setIncludeGeoResults(false)
                                .build())
                        .build())
                .addPages(0)
                .build());
        final BatchAnnotateFilesResponse result = imageAnnotatorClient.batchAnnotateFiles(requests);
    }

    public void tryBatchAnnotateFiles2() {
        final BatchAnnotateFilesRequest request = BatchAnnotateFilesRequest.newBuilder()
                .addRequests(AnnotateFileRequest.newBuilder()
                        .setInputConfig(InputConfig.newBuilder()
                                .setGcsSource(GcsSource.newBuilder()
                                        .setUri("theUri")
                                        .build())
                                .setContent(ByteString.copyFrom("content".getBytes()))
                                .setMimeType("application/json")
                                .build())
                        .addFeatures(Feature.newBuilder()
                                .setType(Feature.Type.TYPE_UNSPECIFIED)
                                .setMaxResults(0)
                                .setModel("theModel")
                                .build())
                        .setImageContext(ImageContext.newBuilder()
                                .setLatLongRect(LatLongRect.newBuilder()
                                        .setMinLatLng(LatLng.newBuilder()
                                                .setLatitude(0.0)
                                                .setLongitude(0.0)
                                                .build())
                                        .setMaxLatLng(LatLng.newBuilder()
                                                .setLatitude(0.0)
                                                .setLongitude(0.0)
                                                .build())
                                        .build())
                                .addLanguageHints("theLanguageHint")
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
                                        .setProductSet("theProductSet")
                                        .addProductCategories("value")
                                        .setFilter("theFilter")
                                        .build())
                                .setWebDetectionParams(WebDetectionParams.newBuilder()
                                        .setIncludeGeoResults(false)
                                        .build())
                                .build())
                        .addPages(0)
                        .build())
                .setParent("theParent")
                .build();
        final BatchAnnotateFilesResponse result = imageAnnotatorClient.batchAnnotateFiles(request);
    }

    public void tryBatchAnnotateFilesCallable() {
        final UnaryCallable<BatchAnnotateFilesRequest, BatchAnnotateFilesResponse> result = imageAnnotatorClient.batchAnnotateFilesCallable();
    }

    public void tryAsyncBatchAnnotateImagesAsync1() {
        final List<AnnotateImageRequest> requests = Arrays.asList(AnnotateImageRequest.newBuilder()
                .setImage(Image.newBuilder()
                        .setContent(ByteString.copyFrom("content".getBytes()))
                        .setSource(ImageSource.newBuilder()
                                .setGcsImageUri("theGcsImageUri")
                                .setImageUri("theImageUri")
                                .build())
                        .build())
                .addFeatures(Feature.newBuilder()
                        .setType(Feature.Type.TYPE_UNSPECIFIED)
                        .setMaxResults(0)
                        .setModel("theModel")
                        .build())
                .setImageContext(ImageContext.newBuilder()
                        .setLatLongRect(LatLongRect.newBuilder()
                                .setMinLatLng(LatLng.newBuilder()
                                        .setLatitude(0.0)
                                        .setLongitude(0.0)
                                        .build())
                                .setMaxLatLng(LatLng.newBuilder()
                                        .setLatitude(0.0)
                                        .setLongitude(0.0)
                                        .build())
                                .build())
                        .addLanguageHints("theLanguageHint")
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
                                .setProductSet("theProductSet")
                                .addProductCategories("value")
                                .setFilter("theFilter")
                                .build())
                        .setWebDetectionParams(WebDetectionParams.newBuilder()
                                .setIncludeGeoResults(false)
                                .build())
                        .build())
                .build());
        final OutputConfig outputConfig = OutputConfig.newBuilder()
                .setGcsDestination(GcsDestination.newBuilder()
                        .setUri("theUri")
                        .build())
                .setBatchSize(0)
                .build();
        final OperationFuture<AsyncBatchAnnotateImagesResponse, OperationMetadata> result = imageAnnotatorClient.asyncBatchAnnotateImagesAsync(requests, outputConfig);
    }

    public void tryAsyncBatchAnnotateImagesAsync2() {
        final AsyncBatchAnnotateImagesRequest request = AsyncBatchAnnotateImagesRequest.newBuilder()
                .addRequests(AnnotateImageRequest.newBuilder()
                        .setImage(Image.newBuilder()
                                .setContent(ByteString.copyFrom("content".getBytes()))
                                .setSource(ImageSource.newBuilder()
                                        .setGcsImageUri("theGcsImageUri")
                                        .setImageUri("theImageUri")
                                        .build())
                                .build())
                        .addFeatures(Feature.newBuilder()
                                .setType(Feature.Type.TYPE_UNSPECIFIED)
                                .setMaxResults(0)
                                .setModel("theModel")
                                .build())
                        .setImageContext(ImageContext.newBuilder()
                                .setLatLongRect(LatLongRect.newBuilder()
                                        .setMinLatLng(LatLng.newBuilder()
                                                .setLatitude(0.0)
                                                .setLongitude(0.0)
                                                .build())
                                        .setMaxLatLng(LatLng.newBuilder()
                                                .setLatitude(0.0)
                                                .setLongitude(0.0)
                                                .build())
                                        .build())
                                .addLanguageHints("theLanguageHint")
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
                                        .setProductSet("theProductSet")
                                        .addProductCategories("value")
                                        .setFilter("theFilter")
                                        .build())
                                .setWebDetectionParams(WebDetectionParams.newBuilder()
                                        .setIncludeGeoResults(false)
                                        .build())
                                .build())
                        .build())
                .setOutputConfig(OutputConfig.newBuilder()
                        .setGcsDestination(GcsDestination.newBuilder()
                                .setUri("theUri")
                                .build())
                        .setBatchSize(0)
                        .build())
                .setParent("theParent")
                .build();
        final OperationFuture<AsyncBatchAnnotateImagesResponse, OperationMetadata> result = imageAnnotatorClient.asyncBatchAnnotateImagesAsync(request);
    }

    public void tryAsyncBatchAnnotateImagesOperationCallable() {
        final OperationCallable<AsyncBatchAnnotateImagesRequest, AsyncBatchAnnotateImagesResponse, OperationMetadata> result = imageAnnotatorClient.asyncBatchAnnotateImagesOperationCallable();
    }

    public void tryAsyncBatchAnnotateImagesCallable() {
        final UnaryCallable<AsyncBatchAnnotateImagesRequest, Operation> result = imageAnnotatorClient.asyncBatchAnnotateImagesCallable();
    }

    public void tryAsyncBatchAnnotateFilesAsync1() {
        final List<AsyncAnnotateFileRequest> requests = Arrays.asList(AsyncAnnotateFileRequest.newBuilder()
                .setInputConfig(InputConfig.newBuilder()
                        .setGcsSource(GcsSource.newBuilder()
                                .setUri("theUri")
                                .build())
                        .setContent(ByteString.copyFrom("content".getBytes()))
                        .setMimeType("application/json")
                        .build())
                .addFeatures(Feature.newBuilder()
                        .setType(Feature.Type.TYPE_UNSPECIFIED)
                        .setMaxResults(0)
                        .setModel("theModel")
                        .build())
                .setImageContext(ImageContext.newBuilder()
                        .setLatLongRect(LatLongRect.newBuilder()
                                .setMinLatLng(LatLng.newBuilder()
                                        .setLatitude(0.0)
                                        .setLongitude(0.0)
                                        .build())
                                .setMaxLatLng(LatLng.newBuilder()
                                        .setLatitude(0.0)
                                        .setLongitude(0.0)
                                        .build())
                                .build())
                        .addLanguageHints("theLanguageHint")
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
                                .setProductSet("theProductSet")
                                .addProductCategories("value")
                                .setFilter("theFilter")
                                .build())
                        .setWebDetectionParams(WebDetectionParams.newBuilder()
                                .setIncludeGeoResults(false)
                                .build())
                        .build())
                .setOutputConfig(OutputConfig.newBuilder()
                        .setGcsDestination(GcsDestination.newBuilder()
                                .setUri("theUri")
                                .build())
                        .setBatchSize(0)
                        .build())
                .build());
        final OperationFuture<AsyncBatchAnnotateFilesResponse, OperationMetadata> result = imageAnnotatorClient.asyncBatchAnnotateFilesAsync(requests);
    }

    public void tryAsyncBatchAnnotateFilesAsync2() {
        final AsyncBatchAnnotateFilesRequest request = AsyncBatchAnnotateFilesRequest.newBuilder()
                .addRequests(AsyncAnnotateFileRequest.newBuilder()
                        .setInputConfig(InputConfig.newBuilder()
                                .setGcsSource(GcsSource.newBuilder()
                                        .setUri("theUri")
                                        .build())
                                .setContent(ByteString.copyFrom("content".getBytes()))
                                .setMimeType("application/json")
                                .build())
                        .addFeatures(Feature.newBuilder()
                                .setType(Feature.Type.TYPE_UNSPECIFIED)
                                .setMaxResults(0)
                                .setModel("theModel")
                                .build())
                        .setImageContext(ImageContext.newBuilder()
                                .setLatLongRect(LatLongRect.newBuilder()
                                        .setMinLatLng(LatLng.newBuilder()
                                                .setLatitude(0.0)
                                                .setLongitude(0.0)
                                                .build())
                                        .setMaxLatLng(LatLng.newBuilder()
                                                .setLatitude(0.0)
                                                .setLongitude(0.0)
                                                .build())
                                        .build())
                                .addLanguageHints("theLanguageHint")
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
                                        .setProductSet("theProductSet")
                                        .addProductCategories("value")
                                        .setFilter("theFilter")
                                        .build())
                                .setWebDetectionParams(WebDetectionParams.newBuilder()
                                        .setIncludeGeoResults(false)
                                        .build())
                                .build())
                        .setOutputConfig(OutputConfig.newBuilder()
                                .setGcsDestination(GcsDestination.newBuilder()
                                        .setUri("theUri")
                                        .build())
                                .setBatchSize(0)
                                .build())
                        .build())
                .setParent("theParent")
                .build();
        final OperationFuture<AsyncBatchAnnotateFilesResponse, OperationMetadata> result = imageAnnotatorClient.asyncBatchAnnotateFilesAsync(request);
    }

    public void tryAsyncBatchAnnotateFilesOperationCallable() {
        final OperationCallable<AsyncBatchAnnotateFilesRequest, AsyncBatchAnnotateFilesResponse, OperationMetadata> result = imageAnnotatorClient.asyncBatchAnnotateFilesOperationCallable();
    }

    public void tryAsyncBatchAnnotateFilesCallable() {
        final UnaryCallable<AsyncBatchAnnotateFilesRequest, Operation> result = imageAnnotatorClient.asyncBatchAnnotateFilesCallable();
    }

    public void tryBatchAnnotateImages1() {
        final List<AnnotateImageRequest> requests = Arrays.asList(AnnotateImageRequest.newBuilder()
                .setImage(Image.newBuilder()
                        .setContent(ByteString.copyFrom("content".getBytes()))
                        .setSource(ImageSource.newBuilder()
                                .setGcsImageUri("theGcsImageUri")
                                .setImageUri("theImageUri")
                                .build())
                        .build())
                .addFeatures(Feature.newBuilder()
                        .setType(Feature.Type.TYPE_UNSPECIFIED)
                        .setMaxResults(0)
                        .setModel("theModel")
                        .build())
                .setImageContext(ImageContext.newBuilder()
                        .setLatLongRect(LatLongRect.newBuilder()
                                .setMinLatLng(LatLng.newBuilder()
                                        .setLatitude(0.0)
                                        .setLongitude(0.0)
                                        .build())
                                .setMaxLatLng(LatLng.newBuilder()
                                        .setLatitude(0.0)
                                        .setLongitude(0.0)
                                        .build())
                                .build())
                        .addLanguageHints("theLanguageHint")
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
                                .setProductSet("theProductSet")
                                .addProductCategories("value")
                                .setFilter("theFilter")
                                .build())
                        .setWebDetectionParams(WebDetectionParams.newBuilder()
                                .setIncludeGeoResults(false)
                                .build())
                        .build())
                .build());
        final BatchAnnotateImagesResponse result = imageAnnotatorClient.batchAnnotateImages(requests);
    }

    public void tryBatchAnnotateImages2() {
        final BatchAnnotateImagesRequest request = BatchAnnotateImagesRequest.newBuilder()
                .addRequests(AnnotateImageRequest.newBuilder()
                        .setImage(Image.newBuilder()
                                .setContent(ByteString.copyFrom("content".getBytes()))
                                .setSource(ImageSource.newBuilder()
                                        .setGcsImageUri("theGcsImageUri")
                                        .setImageUri("theImageUri")
                                        .build())
                                .build())
                        .addFeatures(Feature.newBuilder()
                                .setType(Feature.Type.TYPE_UNSPECIFIED)
                                .setMaxResults(0)
                                .setModel("theModel")
                                .build())
                        .setImageContext(ImageContext.newBuilder()
                                .setLatLongRect(LatLongRect.newBuilder()
                                        .setMinLatLng(LatLng.newBuilder()
                                                .setLatitude(0.0)
                                                .setLongitude(0.0)
                                                .build())
                                        .setMaxLatLng(LatLng.newBuilder()
                                                .setLatitude(0.0)
                                                .setLongitude(0.0)
                                                .build())
                                        .build())
                                .addLanguageHints("theLanguageHint")
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
                                        .setProductSet("theProductSet")
                                        .addProductCategories("value")
                                        .setFilter("theFilter")
                                        .build())
                                .setWebDetectionParams(WebDetectionParams.newBuilder()
                                        .setIncludeGeoResults(false)
                                        .build())
                                .build())
                        .build())
                .setParent("theParent")
                .build();
        final BatchAnnotateImagesResponse result = imageAnnotatorClient.batchAnnotateImages(request);
    }

    public void tryBatchAnnotateImagesCallable() {
        final UnaryCallable<BatchAnnotateImagesRequest, BatchAnnotateImagesResponse> result = imageAnnotatorClient.batchAnnotateImagesCallable();
    }

    public void tryClose() throws Exception {
        imageAnnotatorClient.close();
    }

    public void tryShutdown() {
        imageAnnotatorClient.shutdown();
    }

    public void tryIsShutdown() {
        final boolean result = imageAnnotatorClient.isShutdown();
    }

    public void tryIsTerminated() {
        final boolean result = imageAnnotatorClient.isTerminated();
    }

    public void tryShutdownNow() {
        imageAnnotatorClient.shutdownNow();
    }

    public void tryAwaitTermination() throws Exception {
        final boolean result = imageAnnotatorClient.awaitTermination(0L, TimeUnit.MILLISECONDS);
    }

    public void tryCreate1() throws Exception {
        final ImageAnnotatorClient result = ImageAnnotatorClient.create();
    }

    public void tryCreate2() throws Exception {
        final ImageAnnotatorSettings settings = ImageAnnotatorSettings.create(null);
        final ImageAnnotatorClient result = ImageAnnotatorClient.create(settings);
    }

    public void tryCreate3() {
        final ImageAnnotatorStub stub = null;
        final ImageAnnotatorClient result = ImageAnnotatorClient.create(stub);
    }
}
