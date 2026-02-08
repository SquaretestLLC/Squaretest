/*
 * Copyright (c) 2022 Squaretest LLC. All Rights Reserved.
 * Use is subject to license terms.
 * This code is based on an example program created by Google Inc.
 * The code was modified to test Squaretest functionality.
 * The original copyright notice and license are included below.
 */ 
/*
 * Copyright 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.myapp;

import com.google.cloud.vision.v1.*;
import com.google.cloud.vision.v1.Feature.Type;
import com.google.protobuf.ByteString;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyClass {

    private final ImageAnnotatorClient client;

    public MyClass(final ImageAnnotatorClient client) {
        this.client = client;
    }

    /**
     * Detects localized objects in the specified local image.
     *
     * @param filePath The path to the file to perform localized object detection on.
     * @throws Exception   on errors while closing the client.
     * @throws IOException on Input/Output errors.
     */
    public void detectLocalizedObjects(String filePath) throws IOException {
        List<AnnotateImageRequest> requests = new ArrayList<>();

        ByteString imgBytes = ByteString.readFrom(new FileInputStream(filePath));

        Image img = Image.newBuilder().setContent(imgBytes).build();
        AnnotateImageRequest request =
                AnnotateImageRequest.newBuilder()
                        .addFeatures(Feature.newBuilder().setType(Type.OBJECT_LOCALIZATION))
                        .setImage(img)
                        .build();
        requests.add(request);

        // Initialize client that will be used to send requests. This client only needs to be created
        // once, and can be reused for multiple requests. After completing all of your requests, call
        // the "close" method on the client to safely clean up any remaining background resources.
        // Perform the request
        BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
        List<AnnotateImageResponse> responses = response.getResponsesList();

        // Display the results
        for (AnnotateImageResponse res : responses) {
            for (LocalizedObjectAnnotation entity : res.getLocalizedObjectAnnotationsList()) {
                System.out.format("Object name: %s%n", entity.getName());
                System.out.format("Confidence: %s%n", entity.getScore());
                System.out.format("Normalized Vertices:%n");
                entity
                        .getBoundingPoly()
                        .getNormalizedVerticesList()
                        .forEach(vertex -> System.out.format("- (%s, %s)%n", vertex.getX(), vertex.getY()));
            }
        }
    }
}