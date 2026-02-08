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

import java.io.IOException;
import java.util.List;

public class MyClass {

    private final ImageAnnotatorClient client;

    public MyClass(final ImageAnnotatorClient client) {
        this.client = client;
    }

    /**
     * Performs document text detection on a local image file.
     *
     * @param filePath The path to the local file to detect document text on.
     * @throws IOException on Input/Output errors.
     */
    public void detectDocumentText(String filePath) throws IOException {
        List<AnnotateImageRequest> requests = GCSHelper.createRequest(filePath);

        // Initialize client that will be used to send requests. This client only needs to be created
        // once, and can be reused for multiple requests. After completing all of your requests, call
        // the "close" method on the client to safely clean up any remaining background resources.
        BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
        GCSHelper.printResponse(response);
    }
}